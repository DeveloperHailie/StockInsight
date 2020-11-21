package model;

public class MainStockData {
	private String date = null;
	
	private String kospi_presentPrice = null;
	private String kospi_sign = null;
	private String kospi_difference = null;
	
	private String kosdaq_presentPrice= null;
	private String kosdaq_sign= null;
	private String kosdaq_difference = null;
	
	MainStockData(){}
	MainStockData(String date, String kospi_presentPrice, String kospi_sign, String kospi_difference, String kosdaq_presentPrice, String kosdaq_sign, String kosdaq_difference){
		this.date = date;
		
		this.kospi_difference = kospi_difference;
		this.kospi_presentPrice = kospi_presentPrice;
		this.kospi_sign = kospi_sign;
		
		this.kosdaq_difference = kosdaq_difference;
		this.kosdaq_presentPrice = kosdaq_presentPrice;
		this.kosdaq_sign = kosdaq_sign;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getKospi_presentPrice() {
		return kospi_presentPrice;
	}
	public void setKospi_presentPrice(String kospi_presentPrice) {
		this.kospi_presentPrice = kospi_presentPrice;
	}
	public String getKospi_sign() {
		return kospi_sign;
	}
	public void setKospi_sign(String kospi_sign) {
		this.kospi_sign = kospi_sign;
	}
	public String getKospi_difference() {
		return kospi_difference;
	}
	public void setKospi_difference(String kospi_difference) {
		this.kospi_difference = kospi_difference;
	}
	public String getKosdaq_presentPrice() {
		return kosdaq_presentPrice;
	}
	public void setKosdaq_presentPrice(String kosdaq_presentPrice) {
		this.kosdaq_presentPrice = kosdaq_presentPrice;
	}
	public String getKosdaq_sign() {
		return kosdaq_sign;
	}
	public void setKosdaq_sign(String kosdaq_sign) {
		this.kosdaq_sign = kosdaq_sign;
	}
	public String getKosdaq_difference() {
		return kosdaq_difference;
	}
	public void setKosdaq_difference(String kosdaq_difference) {
		this.kosdaq_difference = kosdaq_difference;
	}
	@Override
	public String toString() {
		return "MainStockData [date=" + date + ", kospi_presentPrice=" + kospi_presentPrice + ", kospi_sign="
				+ kospi_sign + ", kospi_difference=" + kospi_difference + ", kosdaq_presentPrice=" + kosdaq_presentPrice
				+ ", kosdaq_sign=" + kosdaq_sign + ", kosdaq_difference=" + kosdaq_difference + "]";
	}
	
}
