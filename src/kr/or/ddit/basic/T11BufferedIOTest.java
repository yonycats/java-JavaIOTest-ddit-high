package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11BufferedIOTest {
	public static void main(String[] args) throws IOException {
		
		// 입출력 성능 향상을 위해 버퍼를 이용하는 보조스트림
		
		// FileOutputStream => 파일에 데이터를 쓸 때 문자 단위 스트림으로 처리해주는 스트림
		FileOutputStream fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
		
		// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기는 8192byte(8KB)로 설정된다.
		
		// 버퍼 크기가 5바이트인 보조스트림 객체 생성하기
		// BufferedOutputStream => Writer 스트림에 버퍼 기능을 제공하는 보조 스트림
		BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
		
		for (char ch='1'; ch<='9'; ch++ ) {
			bos.write(ch);
		}
		
		// 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력(방출)시킨다.
		// close() 호출시 자동으로 호출됨.
		bos.flush(); 
		
		bos.close();
		
		System.out.println("버퍼를 이용한 쓰기 작업 끝");
	}
}
