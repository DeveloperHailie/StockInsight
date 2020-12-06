package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackThread_Stock  implements Runnable {
	private final AtomicBoolean running = new AtomicBoolean(false);
	public void stop() {
		System.out.println("BackThread_Stock Stop");
		running.set(false);
	}
	@Override
	public void run() {
		running.set(true);
		while(running.get()) {
			try {
				// 프로그램 실행
				System.out.println("BackThread_Stock Start");
				// 실시간 데이터 업데이트(회사)
				RunProgram.runProgram("realtime_data_crawling.py");
				// 예측(회사)
				RunProgram.runProgram("predict\\GetPredict.py");
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

