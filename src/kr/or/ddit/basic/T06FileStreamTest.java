package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T06FileStreamTest {
	public static void main(String[] args) throws IOException {
		
		// 파일에 출력하기
		
		// FileInputStream => 파일로부터 바이트를 읽을 때 문자 단위 스트림으로 처리해주는 스트림
		// FileOutputStream => 파일에 데이터를 쓸 때 문자 단위 스트림으로 처리해주는 스트림
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/out.txt");
			
			for (char ch='a';ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////
		
		FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
		
		int data = 0;
		
		while ( (data=fis.read()) != -1 ) {
			System.out.print( (char)data+"" ); 
			// 한글은 크기가 1바이트를 넘어가기 때문에 복원이 안됨
		} 

		System.out.println();
		System.out.println("파일 읽기 작업 완료");
		
	}
}
