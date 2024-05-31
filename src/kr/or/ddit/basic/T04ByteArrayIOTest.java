package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// Byte 배열 클래스를 InputStream 클래스로 IO 작업을 하는 것
		
		// 1바이트씩 읽는게 느려서 4바이트씩 IO 작업을 하도록 함. 퍼포먼스 있게?
		byte[] temp = new byte[4];
		

		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// 읽은 바이트의 갯수
		int readBytes = 0;
		
		// 바이트를 읽을 때에는 -1까지 읽어야 함 = 더 이상 읽을 것이 없을 때까지
		while ( (readBytes = bais.read(temp)) != -1 ) {
			
//			// temp에 뭐 들어가있는지 확인
//			// 4바이트씩 읽어오기 때문에 3번째 읽을 때 6,7이 여전히 남아서 비정상적인 데이터가 복사되게 됨
//			// 그래서 readBytes를 활용해야 함
//			System.out.println("temp => " + Arrays.toString(temp));
//			
//			baos.write(temp);
			
			

			// readBytes 활용
			System.out.println("temp => " + Arrays.toString(temp));
			
			baos.write(temp, 0, readBytes);
		}

		outSrc = baos.toByteArray();
		System.out.println("스트림 클래스를 이용하여 복사 후 outSrc => " + Arrays.toString(outSrc));
		
	}
	
}
