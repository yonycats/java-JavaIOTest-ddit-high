package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class T03ByteArrayIOTest {
	public static void main(String[] args) {
		
		// Byte 배열 클래스를 InputStream 클래스로 IO 작업을 하는 것

		
		
		// 1. 직접 복사하는 방법
		
//		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
//		byte[] outSrc = null;
		
//		// outSrc에 inSrc길이만큼 공간 추가 후, 하나씩 복사하기
//		outSrc = new byte[inSrc.length];
//		for(int i=0; i<inSrc.length; i++) {
//			outSrc[i] = inSrc[i];
//		}
//		
//		System.out.println("직접 복사 후 outSrc => " + Arrays.toString(outSrc));
		
		
		
		
		// 2. arraycopy를 이용한 방법
		
//		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
//		byte[] outSrc = new byte[inSrc.length];
//		
////		System.arraycopy(복사할원본, 원본의어디부터, 복사를할사본공간, 사본의어디부터복사할건지, 얼마만큼복사할건지(길이));
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
//		System.out.println("arraycopy 복사 후 outSrc => " + Arrays.toString(outSrc));
		
		
		
		// 3. 스트림 클래스를 이용하는 방법 (1바이트씩 읽어오는게 기본임)
		
		byte[] inSrc = {0,1,8,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// ByteArrayInputStream => 문자 배열로부터 문자를 읽기 위한 스트림
		// ByteArrayOutputStream => 문자 배열에 문자를 쓰기 위한 스트림
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0;
		
		// 바이트를 읽을 때에는 -1까지 읽어야 함 = 더 이상 읽을 것이 없을 때까지
		// 읽을 것이 없으면 .read()가 -1을 반환함
		while ( (data = bais.read()) != -1 ) {
			// baos에 값 복사
			baos.write(data);
		}
		
		// for문으로 작성해보면 이렇게
//		for (int i = 0; i < inSrc.length; i++) {
//		    data = bais.read();
//		    baos.write(data);
//		}

		// baos에 담긴 배열을 반환함 => outSrc에 배열 넣기
		outSrc = baos.toByteArray();
		System.out.println("스트림 클래스를 이용하여 복사 후 outSrc => " + Arrays.toString(outSrc));
		
	}
	
}
