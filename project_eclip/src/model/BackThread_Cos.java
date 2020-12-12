package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackThread_Cos implements Runnable {
	private final AtomicBoolean running = new AtomicBoolean(false);
	static RunProgram cos = new RunProgram();
	public void stop() {
		// false로 설정해서 while문 빠져나오게
		running.set(false);
		// 자식 프로세스 죽이기
		cos.stopPrograms();
	}
	@Override
	public void run() {
		running.set(true);
		while(running.get()) {
			try {
				// 프로그램 실행
				// 실시간 데이터 업데이트(코스피, 코스닥)
				cos.runProgram("cospi_cosdap.py");
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				stop();
			}
		}
		stop();
		System.out.println("cospi_cosdap.py run 빠져나옴");
	} 
}
