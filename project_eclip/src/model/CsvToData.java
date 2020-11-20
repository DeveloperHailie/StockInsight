package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvToData {
static Scanner in = new Scanner(System.in);
	
	public static ArrayList<String[]> addToList(String fileName) {
    	try {	
    			ArrayList<String[]> list = new ArrayList<String[]>();
    			// csv 데이터 파일 
    			File csv = new File(fileName); 
    			BufferedReader br = new BufferedReader(new FileReader(csv));
    	
    			// row 읽어서 저장할 string
    			String line = "";
    			// column인지 체크할 flag와 for문 돌리기 위한 int
    			Boolean flag = true; int i; 
    			// csv column 저장할 string
    			String column;
    			
    			while ((line = br.readLine()) != null) { 
    				// -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션 
    				String[] token = line.split(",",-1); 
    				// 칼럼명 적혀있는 부분은 list에 넣지 않는다.
    				if(flag) {
    					// 칼럼명 확인 위한 출력문
    					for(i=0;i<token.length;i++) { 
    						//code	date	presentPrice	sign	difference	prevEndPrice	volume
    						System.out.print(token[i]+" "); 
    					}
    					System.out.println("순으로 저장된 파일입니다.");
    					flag = false;
    					continue;
    				}
    				String[] st = {token[0],token[1],token[2],token[3],token[4],token[5],token[6]};
    				list.add(st);
    			}
    			br.close(); 
    			return list;
    		} 
    		catch (FileNotFoundException e) { 
    			System.out.println("FileNotFoundException");
    			e.printStackTrace(); 
    		}
    		catch (IOException e) { 
    			System.out.println("IOException");
    			e.printStackTrace(); 
    		}
    		catch (Exception e) {
    			System.out.println("Error");
    		}
    	return null;
	}

	public static void addToCsv(String fileName, String code, String date, String presentPrice, String sign, String difference, String prevEndPrice, String volume) throws IOException {
		BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
		//﻿code date presentPrice sign difference prevEndPrice volume
		String line = code + "," + date + "," + presentPrice + "," + sign + "," + difference + "," + prevEndPrice + "," + volume;
		fw.write(line);
		
		fw.newLine();
		fw.flush();
		fw.close();
	}
}
