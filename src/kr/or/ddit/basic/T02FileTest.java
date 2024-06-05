package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) throws IOException {
		
		// exists : 존재하는지 여부 반환
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if (f1.exists()) {
			System.out.println(f1.getAbsolutePath() + "은/는 존재합니다.");
		} else {
			System.out.println(f1.getAbsolutePath() + "은/는 없는 파일입니다.");
			
			// 파일을 새로 만드는 메서드
			if (f1.createNewFile()) { 
				System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
			}
		}
		
		
		if (f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은/는 존재합니다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은/는 없는 파일입니다.");
		}
		
		
		System.out.println("------------------------------");
		
		
		File f3 = new File("d:/D_Other");
		// 파일 내부에 있는 파일들을 리스트 형태로 불러오는 메서드
		// 파일 목록을 객체 형태로 반환
		System.out.println("-----.listFiles()-----");
		File[] files = f3.listFiles();
		
		for (File f : files) {
			System.out.print(f.getName() + " => ");
			
			if (f.isFile()) {
				System.out.println("파일");
			} else if (f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		
		System.out.println("==============================");
		System.out.println("-----.list()-----");
		
		// 파일 목록을 스트링값으로 반환
		String[] strFiles = f3.list();
		
		for (String name : strFiles) {
			System.out.println(name);
		}
		
		
		System.out.println("------------------------------");
		System.out.println();
		
		
		displayFileList(new File("d:/D_Other"));
		
	}

	
	
	
	// 지정한 디렉토리(경로)에 포함된 파일과 디렉토리 목록을 보여주기 위한 메서드
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용");
		
		// 디렉토리 안의 모든 파일 목록을 가져온다.
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보를 저장하기 위한 List 객체 생성 (File 배열의 인덱스값 저장용)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		// 날짜 정보를 출력하기 위한 포맷터 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for (int i=0; i<files.length; i++) {
			String attr = ""; // 파일 속성 정보 (읽기권한, 쓰기권한, 숨김파일, 폴더 등)
			String size = ""; // 파일 용량
			
			// 
			if (files[i].isDirectory()) {
				attr = "<DIR>";
				// 폴더가 있을 때만 인덱스 추가
				subDirList.add(i);
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
			}
			
			System.out.printf("%s %5s %12s %s\n",
					sdf.format( new Date(files[i].lastModified()) ),
					attr, size, files[i].getName());
		}
		
		int dirCount = subDirList.size(); // 폴더 갯수, 인덱스 갯수 세기
		int fileCount = files.length - dirCount; // 파일 갯수, 인덱스 갯수 - 폴더 갯수
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
		
	}
	
	
}
