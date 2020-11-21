package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.MainStockData;
import model.StockData;
import model.StockPredict;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ApiAnalysis {
	
	public static Boolean api_stock_onmarket(String code) {
		String xmlContent = getHtml(code);
		String[] kosXPath = { "//stockInfo/@myJangGubun"};
		String[][] kos_StockInfo = parseXML(xmlContent, kosXPath, 1, kosXPath.length);
		if(kos_StockInfo[0][0].contentEquals("Closed"))
			return false;
		else
			return true;
	}
	
	
	public static MainStockData api_stock_kos(String code) {
		MainStockData kos = new MainStockData();
		String xmlContent = getHtml(code);
		
		// <stockInfo kosdaqJisu="870.18" kosdaqJisuBuho="2" kosdaqJisuDebi="10.24" myNowTime="2020/11/21 01:16:44" myJangGubun="Closed"
		//  kospiJisu="2553.5" kospiBuho="2" kospiDebi="6.08"
		String[] kosXPath = { "//stockInfo/@kosdaqJisu", "//stockInfo/@kosdaqJisuBuho", "//stockInfo/@kosdaqJisuDebi",
				"//stockInfo/@myNowTime", "//stockInfo/@kospiJisu", "//stockInfo/@kospiBuho", "//stockInfo/@ kospiDebi"};
		
		String[][] kos_StockInfo = parseXML(xmlContent, kosXPath, 1, kosXPath.length);
		kos.setKosdaq_presentPrice(kos_StockInfo[0][0]);
		kos.setKosdaq_sign(kos_StockInfo[0][1]);
		kos.setKosdaq_difference(kos_StockInfo[0][2]);
		kos.setDate(kos_StockInfo[0][3]);
		kos.setKospi_presentPrice(kos_StockInfo[0][4]);
		kos.setKospi_sign(kos_StockInfo[0][5]);
		kos.setKospi_difference(kos_StockInfo[0][6]);
		
		return kos;
	}
	

	// ApiAnalysis.api_stock_data("005930");
	public static StockData api_stock_data(String code) {
		// 코드 현재가 부호처리 전일대비 전일종가 거래량
		StockData stockData = new StockData();
		String xmlContent = getHtml(code);
		stockData.setCode(code);

		String[] TBL_StockInfoXPath = { "//TBL_StockInfo/@CurJuka", "//TBL_StockInfo/@DungRak", "//TBL_StockInfo/@Debi",
				"//TBL_StockInfo/@PrevJuka", "//TBL_StockInfo/@Volume" };

		String[][] tbl_StockInfo = parseXML(xmlContent, TBL_StockInfoXPath, 1, TBL_StockInfoXPath.length);

		stockData.setPresentPrice(tbl_StockInfo[0][0]);
		stockData.setSign(tbl_StockInfo[0][1]);
		stockData.setDifference(tbl_StockInfo[0][2]);
		stockData.setPrevEndPrice(tbl_StockInfo[0][3]);
		stockData.setVolume(tbl_StockInfo[0][4]);
		
		String[] dateXPath = {"//stockInfo/@myNowTime"};
		String[][] tbl_date = parseXML(xmlContent, dateXPath, 1, dateXPath.length);
		stockData.setDate(tbl_date[0][0]);

		return stockData;
	}

	public static StockPredict[] api_stock_predict(String code) {
		// 코드 날짜 종가 시가 고가 저가 거래량
		StockPredict[] stockPredict = new StockPredict[10];
		for (int i = 0; i < 10; i++) {
			stockPredict[i] = new StockPredict();
			stockPredict[i].setCode(code);
		}

		String xmlContent = getHtml(code);

		String[] DailyStockXPath = { "//DailyStock/@day_Date", "//DailyStock/@day_EndPrice", "//DailyStock/@day_Start",
				"//DailyStock/@day_High", "//DailyStock/@day_Low", "//DailyStock/@day_Volume" };

		String[][] dailyStock = parseXML(xmlContent, DailyStockXPath, 10, DailyStockXPath.length);

		for (int i = 0; i < 10; i++) {
			stockPredict[i].setDate(dailyStock[i][0]);
			stockPredict[i].setEndPrice(dailyStock[i][1]);
			stockPredict[i].setStart(dailyStock[i][2]);
			stockPredict[i].setHigh(dailyStock[i][3]);
			stockPredict[i].setLow(dailyStock[i][4]);
			stockPredict[i].setVolume(dailyStock[i][5]);
		}

		return stockPredict;
	}

	// url 통해 xml 가져오기
	public static String getHtml(String code) {
		String url = "http://asp1.krx.co.kr/servlet/krx.asp.XMLSiseEng?code=" + code;
		try {
			URL targetUrl = new URL(url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(targetUrl.openStream()));
			StringBuilder html = new StringBuilder();
			String current = "";
			while ((current = reader.readLine()) != null) {
				html.append(current);
			}
			reader.close();
			return html.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// xml parsing
	public static String[][] parseXML(String xml, String[] xpath_element, int x, int y) {
		String[][] value = new String[x][y];
		try {
			DocumentBuilder builder;
			Document doc = null;

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);

			InputSource is = new InputSource(new StringReader(xml));
			builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();

			// i가 x
			// l이 y
			for (int l = 0; l < xpath_element.length; l++) {
				XPathExpression expr = xpath.compile(xpath_element[l]);
				NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
				for (int i = 0; i < nodeList.getLength(); i++) {
					NodeList child = nodeList.item(i).getChildNodes();
					for (int j = 0; j < child.getLength(); j++) {
						// System.out.print(l + " " + i + " " + j + " : ");
						Node node = child.item(j);
						value[i][l] = node.getTextContent();
						// System.out.println("현재 노드 값 : " + node.getTextContent());
					}
				}
			}

			return value;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
