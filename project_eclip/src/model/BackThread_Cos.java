package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackThread_Cos implements Runnable {
	private final AtomicBoolean running = new AtomicBoolean(false);
	static RunProgram cos = new RunProgram();
	public void stop() {
		System.out.println("BackThread_Cos Stop");
		running.set(false);
	}
	@Override
	public void run() {
		running.set(true);
		while(running.get()) {
			try {
				System.out.println("BackThread_Cos Start");
				// 프로그램 실행
				// 실시간 데이터 업데이트(코스피, 코스닥)
				cos.runProgram("cospi_cosdap.py");
				Thread.sleep(180000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
