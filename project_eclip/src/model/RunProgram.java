package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class RunProgram {

	ProcessBuilder processBuilder = null;
	Process process = null;
	String processName = null;
	RunProgram() {
		
	}
	
	public void stopPrograms() {
		if(process != null) {
			process.destroyForcibly();
		} 
	}

	public void runProgram(String fileName) { // 프로그램 실행
		// cmd 창에 python crawling.py 입력하는 것과 동일 
		String[] cmd = new String[] { "/bin/sh", "-c", "python", fileName };
		processName = fileName;
		// 프로세스 빌더를 통하여 외부 프로그램 실행
		try {
			System.out.println("외부프로그램실행"+fileName);
			processBuilder = new ProcessBuilder(cmd);

			// C:/StockInsightPython 폴더 안에 파일들 넣기
			// /home/ubuntu/StockInsightPython
			processBuilder.directory(new File("/home/ubuntu/StockInsightPython"));
		
			// 외부 프로그램의 출력을 웹서버의 출력으로 redirect
			processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
			processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

			// 외부 프로그램 실행
			process = processBuilder.start(); 
			System.out.print(fileName);
			System.out.println(" start");

		} catch (IOException e) {
			e.printStackTrace();
			stopPrograms();
		}
		try {
			// 외부 프로그램이 종료될 때까지 기다리기
			System.out.print(fileName);
			System.out.println(" wait");
			process.waitFor();
		} catch (InterruptedException e) {
			stopPrograms();
			e.printStackTrace();
		} 
		// 프로그램 종료 상태 출력 (0이면 정상 종료)
		System.out.print(fileName + ": ");
		System.out.print(process.exitValue());
		System.out.println(" finish");
	}
}
