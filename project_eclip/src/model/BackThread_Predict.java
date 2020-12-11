package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackThread_Predict implements Runnable {
	private final AtomicBoolean running = new AtomicBoolean(false);
	static RunProgram pre = new RunProgram();
	public void stop() {
		running.set(false);
		pre.stopPrograms();
	}
	@Override
	public void run() {
		running.set(true);
		while(running.get()) {
			try {
				// 프로그램 실행
				// 예측(회사)
				pre.runProgram("predict\\GetPredict.py");
				Thread.sleep(180000);
			} catch (InterruptedException e) {
				stop(); break;
			}
		}
		stop();
		System.out.println("GetPredict.py run 빠져나옴");
	}
}

