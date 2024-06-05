package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 	객체 입출력을 위한 보조 스트림 예제
 */

public class T15ObjectStreamTest {
	public static void main(String[] args) throws IOException {
	
		MemberVO mem1 = new MemberVO("홍길동", 20, "대전");
		MemberVO mem2 = new MemberVO("이순신", 30, "부산");
		MemberVO mem3 = new MemberVO("일지매", 40, "대구");
		MemberVO mem4 = new MemberVO("강감찬", 50, "광주");
		
		// bin => Binary File 바이너리 파일이라고 부르며, 컴퓨터가 사용하는 이진 텍스트 파일
//		FileOutputStream fos = new FileOutputStream("d:/D_Other/memObj.bin");
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
//		
//		// 객체 출력을 위한 보조 스트림 생성하기
//		// 보조 스트림을 이용해서 IO작업
//		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		
		// 위 3개를 한번에 아래처럼 선언도 가능
		ObjectOutputStream oos = new ObjectOutputStream(
									new BufferedOutputStream(
											new FileOutputStream("d:/D_Other/memObj.bin")));
		
		// 객체 쓰기(저장)
		oos.writeObject(mem1); // 직렬화
		oos.writeObject(mem2); // 직렬화
		oos.writeObject(mem3); // 직렬화
		oos.writeObject(mem4); // 직렬화
		
		oos.close();
		
		System.out.println("객체 쓰기 작업 완료");
		System.out.println("========================");
		
		
		////////////////////////////////////
		
		// 직렬화 - 객체를 저장할 때 사용(쓰기), 객체들의 데이터를 연속적인 데이터(스트림)으로 변형하여 전송 가능한 형태로 만드는 것
		// 역직렬화 - 저장되어 있는 객체 정보를 읽어올 때 사용(읽기), 직렬화된 데이터를 다시 객체의 형태로 만드는 것
		
		/*
		 Person person = new Person("김철수"); 
		  객체를 { "name" : "김철수"} 와 같은 방식으로 변경하는 것을 직렬화,
		 {"name" : "김철수"} 데이터를 받아서 Person이라는 객체의 
		 name 필드에 "김철수" 를 할당하고 객체를 생성하는 것을 역직렬화라고 할 수 있다.
		 */
		
		
		// 역직렬화 작업
		// 저장된 객체 정보를 읽어와 출력하기
//		FileInputStream fis = new FileInputStream("d:/D_Other/memObj.bin");
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		
//		// 객체 입력 처리를 위한 보조 스트림 생성하기
//		ObjectInputStream ois = new ObjectInputStream(bis);
		
		
		// 위 3개를 한번에 아래처럼 선언도 가능
		ObjectInputStream ois = new ObjectInputStream(
									new BufferedInputStream(
											new FileInputStream("d:/D_Other/memObj.bin")));
		
		 
		Object obj = null;
		
		try {
			// readObject 메서드를 호출하는 순간 역직렬화가 일어나서 객체가 만들어지고 있음
			while ( (obj = ois.readObject()) != null ) {
				// 읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
				MemberVO mem = (MemberVO) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("----------------------");
			}
			
		} catch (ClassNotFoundException e) { // 클래스 없어서 못 찾겠다 예외
			e.printStackTrace();
		}  catch (IOException e) {
			// EOFException => 파일의 끝까지 다 읽었다는 의미의 예외 => 주석처리함
//			e.printStackTrace();
			System.out.println("읽기 작업 끝");
		}
		
	}
}


// MemberVO라는 클래스를 손쉽게 찍어내기 위해서 클래스 정의 (예제로 사용)
// Serializable => 추상 메서드가 없음, 타입 정보를 제공하기 위한 목적으로 만든 인터페이스
// 				       태그 인터페이스라고도 부름 (무엇이 가능하다는 정보 표시)

// static은 기본적으로 IO작업의 대상이 아님 => 데이터 노저장
class MemberVO implements Serializable {
	
	/*
	 	transient => 직렬화가 되지 않게 하고 싶은 멤버 변수에 지정한다.
	 				  (static 필드도 직렬화가 되지 않는다.)
	 				  직렬화가 되지 않는 멤버 변수는 기본값으로 저장된다.
	 				  (참조형 변수 : null, 숫자형 변수 : 0, 논리형 변수 : false)
	 */
	
	private String name;
	transient private int age;
	transient private String addr;
	
	public MemberVO(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}
}