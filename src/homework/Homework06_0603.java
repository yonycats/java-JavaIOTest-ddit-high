package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Homework06_0603 {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
		FileOutputStream fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		int data = 0;
		
		while ( (data=fis.read()) != -1) {
			bos.write(data);
		}
		System.out.println("파일 복사 작업 완료");
		
		bis.close();
		bos.close(); // 보조 스트림 종료시, 기반 스트림도 같이 닫힘
	}
}
