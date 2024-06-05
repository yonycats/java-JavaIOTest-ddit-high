package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {9,8,7,6,5,4,3,2,1,0};
		byte[] outSrc = null;
		
		// Byte 배열 클래스를 InputStream 클래스로 IO 작업을 하는 것
		
		// 1바이트씩 읽는게 느려서 4바이트씩 IO 작업을 하도록 함. 퍼포먼스 있게?
		byte[] temp = new byte[4];
		

		// ByteArrayInputStream => 문자 배열로부터 문자를 읽기 위한 스트림
		// ByteArrayOutputStream => 문자 배열에 문자를 쓰기 위한 스트림
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// 읽은 바이트의 갯수를 저장할 곳 
		// readBytes에 들어가는 값의 순서 => 4, 4, 2, -1
		int readBytes = 0;
		
		// 바이트를 읽을 때에는 -1까지 읽어야 함 = 더 이상 읽을 것이 없을 때까지
		// 읽을 것이 없으면 .read()가 -1을 반환함
		// bais.read(temp) 메서드는 최대 temp.length만큼의 바이트를 읽음
		// bais.read(temp) 메서드는 ByteArrayInputStream에서 데이터를 읽어 temp 배열에 저장함
		while ( (readBytes = bais.read(temp)) != -1 ) {
			
//			// temp에 뭐 들어가있는지 확인
//			// 4바이트씩 읽어오기 때문에 3번째 읽을 때 6,7이 여전히 남아서 비정상적인 데이터가 복사되게 됨
//			// 그래서 readBytes를 활용해야 함
//			System.out.println("temp => " + Arrays.toString(temp));
//			baos.write(temp);
			
			
			
			// readBytes 활용
			// baos.write(temp, temp배열의시작인덱스, readBytes=>실제로 읽혀진 바이트 수 );
			// 이 값을 사용하여 temp 배열에서 실제로 읽혀진 만큼의 데이터만 baos에 쓰게 됨
			// temp에 값이 4개, 4개, 2개씩 들어가며 temp의 0번부터 baos에 값이 추가됨
			System.out.println("temp => " + Arrays.toString(temp));
			baos.write(temp, 0, readBytes);
		}

		outSrc = baos.toByteArray();
		System.out.println("스트림 클래스를 이용하여 복사 후 outSrc => " + Arrays.toString(outSrc));
		
	}
	
}
