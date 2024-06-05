package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


// 전화전호부(해시맵)을 저장했다가 시작할 때 다시 읽어서 사용할 수 있도록 할 것

public class Homework07_0604 {
	private Scanner sc = new Scanner(System.in);
	private Map<String, HotelVO> hotelMap = new HashMap<String, HotelVO>();
	
	public static void main(String[] args) throws IOException {
		Homework07_0604 obj = new Homework07_0604();
		obj.hotelOpen();
	}

	public void hotelOpen() throws FileNotFoundException, IOException {
		
		existsFile(); // 저장 파일 존재 여부 확인 (없으면 만들기)
		inputFile(); // 데이터(파일) 읽어오기

		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		
		while (true) {
			menu();
			int sel = sc.nextInt();
			sc.nextLine();

			switch (sel) {
			case 1 : checkIn();		
				break;
			case 2 : checkOut();		
				break;
			case 3 : roomState();		
				break;
			case 4 : hotelClose();	
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}


	private void inputFile() {

		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("d:/D_Other/HotelVoBin.bin")));

			Object obj = null;

			while ( (obj = ois.readObject()) != null ) {
				hotelMap = (Map<String, HotelVO>) obj;
			}
		} catch (EOFException e) {
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	
	private void existsFile() {

		File HotelVoBin = new File("d:/D_Other/HotelVoBin.bin");

		if (HotelVoBin.exists()) {
			return;
		} else {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream("d:/D_Other/HotelVoBin.bin")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	private void outputFile() {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("d:/D_Other/HotelVoBin.bin")));

			oos.writeObject( hotelMap );
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void hotelClose() {
		
		outputFile(); // 데이터 파일로 저장하기

		System.out.println();
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}

	
	private void roomState() {
		Set<String> guestList = hotelMap.keySet();
		TreeSet<String> guestListSort = new TreeSet<String> (guestList);
		
		System.out.println();
		if (guestListSort.size() == 0) {
			System.out.println("투숙객이 없습니다.");
		} else {
			Iterator<String> list = guestListSort.iterator();
			
			while (list.hasNext()) {
				String room = list.next();
				String name = hotelMap.get(room).getName();
				System.out.println("방번호 : " + room + ", 투숙객 : " + name);
			}
			System.out.println();
		}
	}

	
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		String room = sc.nextLine();
		
		if (!hotelMap.containsKey(room)) {
			System.out.println(room + "번 방에는 체크인한 사람이 없습니다.");
			System.out.println();
			return;
		}
		else if (hotelMap.containsKey(room)) {
			hotelMap.remove(room);
			System.out.println("체크아웃 되었습니다.");
			System.out.println();
		}
	}

	
	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		String room = sc.nextLine();
		
		if (hotelMap.containsKey(room)) {
			System.out.println(room + "번방에는 이미 사람이 있습니다.");
			System.out.println();
			return;
		}
		else if (!hotelMap.containsKey(room)) {
			System.out.println();
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("이름 입력 => ");
			String name = sc.nextLine();

			hotelMap.put(room, new HotelVO(room, name));
			System.out.println("체크인 되었습니다.");
			System.out.println();
		} 
	}

	
	public void menu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
	}
}


class HotelVO implements Serializable {
	private String room;
	private String name;
	/**
	 * @param room
	 * @param name
	 */
	public HotelVO(String room, String name) {
		super();
		this.room = room;
		this.name = name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "HotelVO [room=" + room + ", name=" + name + "]";
	}
}
