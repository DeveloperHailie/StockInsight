package model;

import java.io.IOException;
import java.util.HashMap;

public class ApiBackThread  implements Runnable {
	
	// ApiBackThread는 한번만 실행될 것
	static Boolean run = false;
	// 종목 코드 저장
	public static HashMap<String, String> codeDate = null; 
	
	@Override
	public void run() {
		// 한번이라도 실행 안되었다면
		if(run==false) {
			// 실행 체크 
			run = true;
			
			while(true) {
				try {
					
					// 컨트롤러에서 codeDate 값 설정안해줬으면(Stock table에 있는 종목코드-최종수정시간)
					if(codeDate==null) {
						
					}
					// 설정해줬으면 하나씩 꺼내서 api 요청
					// 받은 정보를 csv에 저장 (csv name : code.csv)
					// codeDate의 최종 수정 날짜 업데이트
					// 컨트롤러에서 codeDate값 얻어서 Stock table 수정하기
					
					String naver = "035420";
					StockData st = ApiAnalysis.api_stock_data(naver);
					try {
						CsvToData.addToCsv(naver+".csv", naver, st.getDate(), st.getPresentPrice(), 
										st.getSign(),st.getDifference(),st.getPrevEndPrice(), st.getVolume());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// term 10초
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
}
