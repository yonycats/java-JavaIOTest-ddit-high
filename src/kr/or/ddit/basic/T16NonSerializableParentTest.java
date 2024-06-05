package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16NonSerializableParentTest {

	/*
	 	부모 클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
	 	부모 객체의 필드값 IO 작업 방법에 대하여 방법 2가지
	 	
	 	1. 부모 클래스가 Serializable 인터페이스를 구현하도록 해준다.
	 	2. 자식 클래스에 writeObject()와 readObject() 메서드를 이용하여
	 	       부모 클래스의 필드값을 처리할 수 있도록 직접 구현한다.
	 */
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = 
				new ObjectOutputStream(
						new FileOutputStream("d:/D_Other/nonSerializeTest.bin"));
		
		Child child = new Child();
		
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); // 직렬화 작업 수행
		oos.close();
		
		
		//////////////////////////////
		
		
		ObjectInputStream ois = 
				new ObjectInputStream(
						new FileInputStream("d:/D_Other/nonSerializeTest.bin"));
		
		Child child2 = (Child) ois.readObject(); // 역직렬화  작업 수행
		
		System.out.println("ParentName : " + child2.getParentName());
		System.out.println("ChildName : " + child2.getChildName());
		
		ois.close();
	}
}

// 계층형 구조의 직렬화 클래스 구현, IO작업(파일로 객체를 저장하는 작업)

// ParentName : null로 뜸 => 이유 : implements Serializable(직렬화)를 구현한 직렬화가 아니기 때문
// Parent는 Serializable 하고 싶지 않다는 가정 중임
class Parent {
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}


class Child extends Parent implements Serializable {
	
	private String childName;

	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	
	// writeObject, writeObject를 직접 구현해서 처리를 해줌
	// => 이유 : 부모 클래스는 Serializable 하고 싶지 않기 때문에 구현하여 직접 처리함
	
	/*
	 	직렬화 될 때 자동으로 호출됨
	 	(접근제한자가 private가 아니면 자동 호출되지 않음)
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getParentName());
		out.defaultWriteObject();
	}
	
	/*
	 	역직렬화 될 때 자동으로 호출됨
	 	(접근제한자가 private가 아니면 자동 호출되지 않음)
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setParentName(in.readUTF());
		in.defaultReadObject();
	}
	
}