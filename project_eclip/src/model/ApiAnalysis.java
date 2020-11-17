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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ApiAnalysis {
	
	// other : (ex) ApiAnalysis("005930");
	// return ? think
	public static void api_stock(String code) {
		// 종목 코드 넣으면 되게 getHtml 수정
		String xmlContent = getHtml(code);
		System.out.println(xmlContent);

		System.out.println("\ndailyStock: ");

		String[] DailyStockXPath = { "//DailyStock/@day_Date", "//DailyStock/@day_EndPrice",
				"//DailyStock/@day_Dungrak", "//DailyStock/@day_getDebi", "//DailyStock/@day_Start",
				"//DailyStock/@day_High", "//DailyStock/@day_Low", "//DailyStock/@day_Volume",
				"//DailyStock/@day_getAmount" };

		String[][] dailyStock = parseXML(xmlContent, DailyStockXPath, 10, DailyStockXPath.length);

		for (int i = 0; i < dailyStock.length; i++) {
			for (int j = 0; j < dailyStock[i].length; j++) {
				System.out.print(dailyStock[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("\naskPrice: ");

		String[] AskPriceXPath = { "//AskPrice/@member_memdoMem", "//AskPrice/@member_memdoVol",
				"//AskPrice/@member_memsoMem", "//AskPrice/@member_mesuoVol" };

		String[][] askPrice = parseXML(xmlContent, AskPriceXPath, 5, AskPriceXPath.length);

		for (int i = 0; i < askPrice.length; i++) {
			for (int j = 0; j < askPrice[i].length; j++) {
				System.out.print(askPrice[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("\nTBL_StockInfo: ");

		String[] TBL_StockInfoXPath = { "//TBL_StockInfo/@JongName", "//TBL_StockInfo/@CurJuka",
				"//TBL_StockInfo/@DungRak", "//TBL_StockInfo/@Debi", "//TBL_StockInfo/@PrevJuka",
				"//TBL_StockInfo/@Volume", "//TBL_StockInfo/@Money", "//TBL_StockInfo/@StartJuka",
				"//TBL_StockInfo/@HighJuka", "//TBL_StockInfo/@LowJuka", "//TBL_StockInfo/@High52",
				"//TBL_StockInfo/@Low52", "//TBL_StockInfo/@UpJuka", "//TBL_StockInfo/@DownJuka",
				"//TBL_StockInfo/@Per", "//TBL_StockInfo/@Amount", "//TBL_StockInfo/@FaceJuka" };

		String[][] tbl_StockInfo = parseXML(xmlContent, TBL_StockInfoXPath, 1, TBL_StockInfoXPath.length);

		for (int i = 0; i < tbl_StockInfo.length; i++) {
			for (int j = 0; j < tbl_StockInfo[i].length; j++) {
				System.out.print(tbl_StockInfo[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("\nTBL_Hoga: ");

		String[] TBL_HogaXPath = { "//TBL_Hoga/@mesuJan0", "//TBL_Hoga/@mesuHoka0", "//TBL_Hoga/@mesuJan1",
				"//TBL_Hoga/@mesuHoka1", "//TBL_Hoga/@mesuJan3", "//TBL_Hoga/@mesuHoka3", "//TBL_Hoga/@mesuJan4",
				"//TBL_Hoga/@mesuHoka4", "//TBL_Hoga/@medoJan0", "//TBL_Hoga/@medoHoka0", "//TBL_Hoga/@medoJan1",
				"//TBL_Hoga/@medoHoka1", "//TBL_Hoga/@medoJan2", "//TBL_Hoga/@medoHoka2", "//TBL_Hoga/@medoJan3",
				"//TBL_Hoga/@medoHoka3", "//TBL_Hoga/@medoJan4", "//TBL_Hoga/@medoHoka4" };

		String[][] tbl_Hoga = parseXML(xmlContent, TBL_HogaXPath, 1, TBL_HogaXPath.length);

		for (int i = 0; i < tbl_Hoga.length; i++) {
			for (int j = 0; j < tbl_Hoga[i].length; j++) {
				System.out.print(tbl_Hoga[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("\nTBL_TimeConclude: ");

		String[] TBL_TimeConcludeXPath = { "//TBL_TimeConclude/@time", "//TBL_TimeConclude/@negoprice",
				"//TBL_TimeConclude/@Dungrak", "//TBL_TimeConclude/@Debi", "//TBL_TimeConclude/@sellprice",
				"//TBL_TimeConclude/@buyprice", "//TBL_TimeConclude/@amount" };

		String[][] tbl_TimeConclude = parseXML(xmlContent, TBL_TimeConcludeXPath, 10, TBL_TimeConcludeXPath.length);

		for (int i = 0; i < tbl_TimeConclude.length; i++) {
			for (int j = 0; j < tbl_TimeConclude[i].length; j++) {
				System.out.print(tbl_TimeConclude[i][j] + " ");
			}
			System.out.println("");
		}

		System.out.println("\nstockInfo: ");

		String[] stockInfoXPath = { "//stockInfo/@kosdaqJisu", "//stockInfo/@kosdaqJisuBuho",
				"//stockInfo/@kosdaqJisuDebi", "//stockInfo/@starJisu", "//stockInfo/@starJisuBuho",
				"//stockInfo/@starJisuDebi", "//stockInfo/@jisu50", "//stockInfo/@jisu50Buho",
				"//stockInfo/@jisu50Debi", "//stockInfo/@myNowTime", "//stockInfo/@myJangGubun",
				"//stockInfo/@myPublicPrice", "//stockInfo/@krx100Jisu", "//stockInfo/@krx100buho",
				"//stockInfo/@krx100Debi", "//stockInfo/@kospiJisu", "//stockInfo/@kospiBuho", "//stockInfo/@kospiDebi",
				"//stockInfo/@kospi200Jisu", "//stockInfo/@kospi200Buho", "//stockInfo/@kospi200Debi" };

		String[][] stock_Info = parseXML(xmlContent, stockInfoXPath, 1, stockInfoXPath.length);

		for (int i = 0; i < stock_Info.length; i++) {
			for (int j = 0; j < stock_Info[i].length; j++) {
				System.out.print(stock_Info[i][j] + " ");
			}
			System.out.println("");
		}
		
		/*
		저장 되는 String[][] 배열들
		dailyStock
		askPrice
		tbl_StockInfo
		tbl_Hoga
		tbl_TimeConclude
		stock_Info
		*/
		
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
