package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackThread_Predict implements Runnable {
	private final AtomicBoolean running = new AtomicBoolean(false);
	static RunProgram pre = new RunProgram();
	public void stop() {
		System.out.println("BackThread_Predict Stop");
		running.set(false);
	}
	@Override
	public void run() {
		running.set(true);
		while(running.get()) {
			try {
				// 프로그램 실행
				System.out.println("BackThread_Predict Start");
				// 예측(회사)
				pre.runProgram("predict\\GetPredict.py");
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

