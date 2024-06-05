package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 	문자기반의 Buffered스트림 예제
 */

public class T12BufferedIOTest {
	public static void main(String[] args) throws IOException {

		// FileReader => 파일로부터 바이트를 읽을 때 문자 단위 스트림으로 처리해주는 스트림
		// BufferedReader => Reader 스트림에 버퍼기능을 제공하는 보조 스트림, 라인 단위 읽기 가능

		FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11BufferedIOTest.java");

		// 버퍼링을 해가면서 읽어들일 것임
		BufferedReader br = new BufferedReader(fr);

		// 데이터를 data에 아스키코드 값으로 하나씩 받아서 문자로 변환해서 출력함
//		int data = 0;
//		while ( (data = fr.read()) != -1 ) {
//			System.out.print( (char)data ); 
//		}
//		fr.close();

		
		// 데이터를 한줄씩 읽음
		// 한줄씩 읽기 때문에 위부터 비교적 빠름
		String readStr = "";
		while ((readStr = br.readLine()) != null) {
			System.out.println(readStr);
		}
		fr.close();
		
		
		// 줄번호도 나오게 하기
//		String readStr = "";
//		int cnt = 1;
//		while ((readStr = br.readLine()) != null) {
//			System.out.printf("%4d :%s\n", cnt++, readStr);
//		}
//		fr.close();

	}
}
