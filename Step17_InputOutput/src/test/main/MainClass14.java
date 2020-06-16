package test.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainClass14 {
	public static void main(String[] args) {
		File memoFile = new File("c:/acorn2020/myFolder/memo.txt");
		try {
			if(!memoFile.exists()) {
				System.out.println("파일이 존재하지 않습니다");
				return; //메소드 끝내기
			}
			//파일에서 문자열을 읽어들일 객체
			FileReader fr = new FileReader(memoFile);
			BufferedReader br = new BufferedReader(fr);
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
		}
	}
}
