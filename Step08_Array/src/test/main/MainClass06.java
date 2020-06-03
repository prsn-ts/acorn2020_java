package test.main;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class MainClass06 {
	public static void main(String[] args) {
		/*
		 *  1. Scanner 객체를 이용해서 문자열을 5번 입력 받는다.
		 *  입력 받은 문자열은 차례대로 배열에 저장 되어야 한다.
		 *  모두 다 입력 받은 후 for 문을 이용해서 배열에 저장된
		 *  모든 문자열을 콘솔창에 순서대로 출력하는 코드를 작성해 보세요.
		 */
		//Scanner 객체를 생성해서 참조값을 지역 변수에 담기
		Scanner scan = new Scanner(System.in);
		//문자열(String) 5개를 담을 수 있는 배열 객체 생성해서 참조값을 지역변수에 담기
		String inputStr[] = new String[5];
//		inputStr[0] = scan.next();
//		inputStr[1] = scan.next();
//		inputStr[2] = scan.next();
//		inputStr[3] = scan.next();
//		inputStr[4] = scan.next(); -> 효율적이지 못하다.(for문을 생각해보길 바람)
		for(int i=0;i<inputStr.length;i++) {
			System.out.println("문자열을 입력하세요:");
			inputStr[i] = scan.next();
			System.out.println(inputStr[i]);
		}
		
		for(int i=0; i<inputStr.length; i++) {
			//i번째 방에 있는 String 객체의 참조값을 불러온다.
			String tmp = inputStr[i];
			//콘솔에 출력한다.
			System.out.println(tmp);
		}	
		
		
//		InputStream a = System.in;
//		PrintStream b = new PrintStream();
//		System.out = b;
		
	}
}
