package model;

//StockPredict[10]
public class StockPredict {
	// 종목코드 날짜 종가 시가 고가 저가 거래량
	private String code = null;
	// 날짜 
	private String date = null;
	// 종가
	private String endPrice = null;
	// 시가
	private String start = null;
	// 고가
	private String high = null;
	// 저가
	private String low = null;
	// 거래량
	private String volume = null;
	StockPredict(){}
	StockPredict(String code, String date, String endPrice, String start, String high, String low, String volume){
		endPrice = endPrice.replace(",","");
		start = start.replace(",","");
		high = high.replace(",","");
		low = low.replace(",","");
		volume = volume.replace(",","");
		this.code = code;
		this.date = date;
		this.endPrice = endPrice;
		this.start = start;
		this.high = high;
		this.low = low;
		this.volume = volume;
	}
	
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "StockPredict [code=" + code + ", date=" + date + ", endPrice=" + endPrice + ", start=" + start
				+ ", high=" + high + ", low=" + low + ", volume=" + volume + "]";
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		endPrice = endPrice.replace(",","");
		this.endPrice = endPrice;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		start = start.replace(",","");
		this.start = start;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		high = high.replace(",","");
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		low = low.replace(",","");
		this.low = low;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		volume = volume.replace(",","");
		this.volume = volume;
	}

}
