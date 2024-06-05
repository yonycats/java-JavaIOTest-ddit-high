package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  	기본타입 입출력 보조 스트림 예제
 */

public class T13DataIOStreamTest {
	public static void main(String[] args) throws IOException {
		
		// FileOutputStream => 파일에 데이터를 쓸 때 문자 단위 스트림으로 처리해주는 스트림
		// FileInputStream => 파일로부터 바이트를 읽을 때 문자 단위 스트림으로 처리해주는 스트림
		
		// 'dat'는 '데이터'(data)의 약어로, 소프트웨어 개발자가 프로그램 등이 아닌 데이터를 저장하는 것을 가리킨다.
		// dat파일은 소프트웨어 개발자가 지정한 앱 혹은 독자적으로 고안한 파일 형식을 사용할 때 열리는 파일로서,
		// 소프트웨어 내부에서 이용되기 위해서만 사용되는 파일이기 때문에 직접 내용물을 열람할 수 없도록 되어있다.
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
		
		// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해준다.
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동");		// 문자열 데이터 출력 (UTF-8)
		dos.writeInt(17);			// 정수형으로 데이터 출력, 4바이트
		dos.writeFloat(3.14f);		// 실수형(Float)으로 데이터 출력, 4바이트
		dos.writeDouble(3.14);		// 실수형(Double)으로 데이터 출력, 8바이트
		dos.writeBoolean(true);		// 논리형(Boolean)으로 데이터 출력, 1바이트
		
		dos.close(); // 스트림 닫기

		System.out.println("데이터 출력 완료");
		
		// =================================
		// 출력한 데이터 읽어오기
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		
		DataInputStream dis = new DataInputStream(fis);
		
		// 각 타입별 기본 바이트 수에 따라서 순서대로 가져와야 함 
		// (실수형 2가지를 순서를 바꿔서 출력하면 이상하게 나옴)
		System.out.println("문자열 데이터 : " + dis.readUTF());
		System.out.println("정수형 데이터 : " + dis.readInt());
		System.out.println("실수형(Float) 데이터 : " + dis.readFloat());
		System.out.println("실수형(Double) 데이터 : " + dis.readDouble());
		System.out.println("논리형(Boolean) 데이터 : " + dis.readBoolean());
		
		dis.close(); // 스트림 닫기
	}
}
