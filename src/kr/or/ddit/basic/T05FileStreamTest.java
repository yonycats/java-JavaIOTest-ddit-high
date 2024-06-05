package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05FileStreamTest {
	public static void main(String[] args) {
		
		// FileInputStream => 파일로부터 바이트를 읽을 때 문자 단위 스트림으로 처리해주는 스트림
		FileInputStream fis = null;
		
		// 기본적으로 1바이트씩 읽고 있기 때문에
		// 1바이트를 사용하는 아스키 코드의 문자는 출력이 잘 되지만
		// 2바이트 이상을 사용하는 문자(한글)은 제대로 출력이 되지 않음
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			int data = 0;
			
			while ( (data = fis.read()) != -1 ) {
				// 아스키 코드 값으로 변환해서 문자로 출력
				System.out.print( (char)data );
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
