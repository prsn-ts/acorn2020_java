package test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainClass15 {
	public static void main(String[] args) {
		File memoFile = new File("c:/acorn2020/myFolder/memo.txt");
		//필요한 객체의 참조값을 담을 지역 변수를 미리 만든다.
		FileReader fr = null; //new(객체 생성)할 때 예외가 발생할 수도 있다 따라서 FileReader 객체의 참조값을 저장할 변수를 미리 만든다.
		BufferedReader br = null; //new(객체 생성)할 때 예외가 발생할 수도 있다 따라서 FileReader 객체의 참조값을 저장할 변수를 미리 만든다.
		try {
			if(!memoFile.exists()) {
				System.out.println("파일이 존재하지 않습니다");
				return; //메소드 끝내기
			}
			//파일에서 문자열을 읽어들일 객체의 참조값을 기존의 fr, br 변수에 담는다.
			fr = new FileReader(memoFile); //미리 만들었던 변수를 try 문 안에서 사용해 안전하게 객체의 참조값을 저장한다.(클래스 종류에 따라 exception이 발생할 수 있기 때문.)
			br = new BufferedReader(fr); //미리 만들었던 변수를 try 문 안에서 사용해 안전하게 객체의 참조값을 저장한다.(클래스 종류에 따라 exception이 발생할 수 있기 때문.)
			while(true) {
				//반복문 돌면서 문자열을 줄단위로(개행기호 기준) 읽어낸다.
				String line = br.readLine(); // readLine 메소드가 개행기호는 읽지않는다.
				if(line==null) { //더이상 읽을 코드가 없으면
					break; //반복문 탈출
				}
				//콘솔창에 개행기호 없이 한글자 한글자 출력하기
				System.out.println(line);
			}
		}catch(IOException ie) {
			ie.printStackTrace();
		}finally { //예외가 발생하던 안하던 반드시 실행이 보장되는 블럭
			//마무리 작업을 한다 (보통 열었던 스트림 객체를 닫는 작업을 한다)
			try {
				if(fr!=null)fr.close(); //fr 변수의 값이 null일수도 있기 때문에 null이 아닌 참조값일 경우에 close() 수행.
				if(fr!=null)br.close(); //br 변수의 값이 null일수도 있기 때문에 null이 아닌 참조값일 경우에 close() 수행.
			} catch (IOException ie) {
				ie.printStackTrace();
			}
			
		}
	}
}
