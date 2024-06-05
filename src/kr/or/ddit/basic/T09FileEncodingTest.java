package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09FileEncodingTest {

/*
 	* 한글 인코딩 방식에 대하여
 	
 	 한글 인코딩 방식은 크게 UTF-8 과 EUC-KR 방식 두가지로 나누어 볼 수 있다.
 	원래 한글 윈도우는 CP949 방식을 사용했는데, 윈도우를 개발한 마이크로소프트사에서 
 	EUC-KR 방식을 확장하여 만들었기 때문에 MS949라고도 부른다.
 	
 	한글 윈도우의 메모장에서 말하는 ANSI 인코딩이란 CP949(Code Page 949)를 말한다.
 	CP949는 EUC-KR의 확장이며, 하위 호환성이다.
 	
 	 - MS949(CP949) => 윈도우의 기본 한글 인코딩 방식 (ANSI 계열)
 	 - UTF-8 => 유니코드 UTF-8 인코딩 방식
 	 - US-ASCII => 영문 전용 인코딩 방식
 
 	참고 )
 	ASCII => extended ASCII(ISO 8859-1) => 조합형, 완성형 => 윈도우계열 : CP949(확장완성형) - 일부문자(8822자) 추가함
 												     => 유닉스 계열 : EUC-KR(확장 유닉스 코드)
 													 
 		  => ANSI 계열
 		  
 		  => 유니코드 (UTF-8)
 */
	
	public static void main(String[] args) throws IOException {
		
		// FileInputStream => 파일로부터 바이트를 읽을 때 문자 단위 스트림으로 처리해주는 스트림
		// InputStreamReader => 바이트 기반 스트림을 문자 기반 스트림 Reader로 변환해주기 위한 보조 스트림
		
//		FileInputStream fis = new FileInputStream("d:/D_Other/test_ansi.txt"); // ansi
		FileInputStream fis = new FileInputStream("d:/D_Other/test_utf8.txt"); // utf8
		
		// 파일 인코딩 정보를 이용하여 읽어오기
		// InputStreamReader를 이용하면  인코딩 방식을 지정하여 읽을 수 있다.
		// new InputStreamReader(바이트 기반 스트림 객체, 인코딩 방식)
		
//		InputStreamReader isr = new InputStreamReader(fis, "CP949"); // ansi
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // utf8
		
		int data = 0;
		
		while ( (data = isr.read()) != -1 ) {
			System.out.print( (char)data );
		}

		isr.close(); // 보조 스트림만 닫아도 됨
		fis.close();
		
		System.out.println();
		System.out.println("출력 끝");
	}
}
