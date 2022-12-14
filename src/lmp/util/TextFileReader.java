package lmp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lmp.db.dao.ExampleDao;

public class TextFileReader {
	
	private static String path = "C:\\javaFullStack_LDH\\repos\\Library_Management_Program\\examplefiles\\BookList.txt";
	
	ExampleDao eDao = new ExampleDao();
	//  /2/1/이지스퍼블리싱/IT전문서/전자책/Do it! 게임 10개 만들며 배우는 파이썬/벤 포터, 쉬무엘 포터/안동현/44915/376/15000/9791163034278/정상 판매중/
	public void textFileReader(String path) {
//		|1|순번|브랜드|분야|도서형태|도서명|저자|역자|발행일|페이지|가격|ISBN|도서상태|
		Pattern pattern = Pattern.compile("(\\d+)/(\\d+)/([가-힣]+)/(.+)/(.+)/(.+)/(.+)/(.+)/(\\d+)/(\\d+)/(\\d+)/(.+)/(.+)");
		try {
			File book = new File(path);
			FileReader fin = new FileReader(book);
			BufferedReader in = new BufferedReader(fin);
			// readLine() : BufferedReader 에 추가된 한줄씩 읽는 기능
			String line;
			while ((line = in.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				
				while (matcher.find()) {
				
					for (int j = 3; j < matcher.groupCount(); j++) {
						System.out.println("["+j+"]");
						System.out.print(matcher.group(j));	
					}
		
				}
			}

			// 최근에 열었던 순서대로 닫아야 한다
			in.close();
			fin.close();
		} catch (Exception e) {
			System.out.println("파일을 찾을 수 없거나 쓰기 도중 에러 발생...");
		}
		System.out.println("프로그램 종료.");
		
	}

	public static void main(String[] args) {
		TextFileReader t = new TextFileReader();
		try {
			t.textFileReader(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
