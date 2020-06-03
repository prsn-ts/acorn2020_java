package test.main;

import java.util.Scanner;

public class MainClass10 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//외형상 무한 루프
		while(true) {
			System.out.println("종료(q) 계속(Enter):");
			String str = scan.nextLine();
			if(str.equals("q")) {//무한 루프 탈출 조건
				break; //while 문 탈출
			}
			System.out.println("무언가 작업 합니다.~");
		}
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
