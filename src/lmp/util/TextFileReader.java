package lmp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileReader {

	public static void main(String[] args) {

//		|1|순번|브랜드|분야|도서형태|도서명|저자|역자|발행일|페이지|가격|ISBN|도서상태|
		Pattern emailPattern = Pattern.compile("/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/(.+)/");
		try {
			File book = new File("C:\\javaFullStack_LDH\\repos\\Library_Management_Program\\examplefiles\\BookList1.txt");
			FileReader fin = new FileReader(book);
			BufferedReader in = new BufferedReader(fin);
			// readLine() : BufferedReader 에 추가된 한줄씩 읽는 기능
			String line;
			while ((line = in.readLine()) != null) {
				Matcher matcher = emailPattern.matcher(line);
				while (matcher.find()) {
					for (int j = 0; j < matcher.groupCount(); j++) {
						System.out.print(matcher.group(j) + "***");
					}
				}
				System.out.println();
			}

			// 최근에 열었던 순서대로 닫아야 한다
			in.close();
			fin.close();
		} catch (Exception e) {
			System.out.println("파일을 찾을 수 없거나 쓰기 도중 에러 발생...");
		}
		System.out.println("프로그램 종료.");

	}

}
