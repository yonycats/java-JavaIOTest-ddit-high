package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) throws IOException {
		
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		// 데코레이션 패턴 - 필요한 기능만 살짝 덧붙임 (보조 스트림 추가)
		
		// 콘솔 (표준 입출력장치)와 연결된 입력용 문자 스트림 객체 생성하기
		// InputStreamReader => 바이트 기반 스트림을 문자 기반 스트림 Reader로 변환해주기 위한 보조 스트림
		// 기본 스트림이 없이는 보조 스트림 사용이 불가능함, 기본 스트림 꼭 필요
		InputStreamReader isr = new InputStreamReader(System.in);
		
		// FileWriter => 파일에 문자 단위 스트림으로 쓰는 기능을 제공해주는 스트림 
		FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");
		
		int data = 0;
		
		System.out.println("아무거나 입력하세요.");
		
		// 콘솔에서 입력할 때 입력의 끝 (EOF)를 나타내기 위해서는 Ctrl+Z키를 누르면 된다
		// Ctrl+Z를 누른 해당 줄은 저장이 안됨, 엔터치고 Ctrl+Z
		
		// 아래처럼 하면 1바이트씩 읽기 때문에 한글이 오류남(바이트 기반), 문자 기반 스트림으로 사용해야 함
//		while ( (data = System.in.read()) != -1 ) {
		while ( (data = isr.read()) != -1 ) {
			fw.write(data);
		}
		
		System.out.println("출력 작업 끝");
		
		// 스트림을 닫지 않으면 운영체제가 할당한 자원이 반환되지 않고, 
		// 버퍼링된 데이터가 손실될 수 있으므로, 스트림 사용 후에는 반드시 명시적으로 닫아주어야 함.
		isr.close();
		fw.close();
	}
}
 