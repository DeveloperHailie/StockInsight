package model;

public class StockData {
	@Override
	public String toString() {
		return "StockData [code=" + code + ", presentPrice=" + presentPrice + ", sign=" + sign + ", difference="
				+ difference + ", prevEndPrice=" + prevEndPrice + ", volume=" + volume + "]";
	}
	// 종목코드
	private String code = null;
	// 현재가
	private String presentPrice = null;
	// 부호처리
	private String sign = null;
	// 전일 대비
	private String difference = null;
	// 전일 종가
	private String prevEndPrice = null;
	// 실시간 순위 측정 위한 거래량
	private String volume = null;
	StockData(){}
	StockData(String code, String presentPrice, String sign, String difference, String prevEndPrice, String volume){
		presentPrice = presentPrice.replace(",", "");
		prevEndPrice = prevEndPrice.replace(",", "");
		volume = volume.replace(",", "");
		this.code = code;
		this.presentPrice = presentPrice;
		this.sign = sign;
		this.difference = difference;
		this.prevEndPrice = prevEndPrice;
		this.volume = volume;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPresentPrice() {
		return presentPrice;
	}
	public void setPresentPrice(String presentPrice) {
		presentPrice = presentPrice.replace(",", "");
		this.presentPrice = presentPrice;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	public String getPrevEndPrice() {
		
		return prevEndPrice;
	}
	public void setPrevEndPrice(String prevEndPrice) {
		prevEndPrice = prevEndPrice.replace(",", "");
		this.prevEndPrice = prevEndPrice;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		volume = volume.replace(",", "");
		this.volume = volume;
	}
	
}