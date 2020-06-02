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
		
		Scanner scan = new Scanner(System.in);
		System.out.println("문자열을 입력하세요:");
		String inputStr[] = new String[5];
		inputStr[0] = scan.next();
		inputStr[1] = scan.next();
		inputStr[2] = scan.next();
		inputStr[3] = scan.next();
		inputStr[4] = scan.next();
		for(int i=0;i<inputStr.length;i++) {
			System.out.println(inputStr[i]);
		}
		
		
		/*
		 *  2. Random 객체를 이용해서 로또 번호를 6개 랜덤하게 얻어내서
		 *  	배열에 저장한다.
		 *  	모두다 저장이 되면 for 문을 이용해서 배열에 저장된 모든 로또 번호를
		 *  	콘솔창에 순서대로 출력하는 코드를 작성해 보세요.
		 */
//		Random rand = new Random();
//		int lottoNum[] = new int[6];
//		lottoNum[0] = (rand.nextInt(45))+1;
//		lottoNum[1] = (rand.nextInt(45))+1;
//		lottoNum[2] = (rand.nextInt(45))+1;
//		lottoNum[3] = (rand.nextInt(45))+1;
//		lottoNum[4] = (rand.nextInt(45))+1;
//		lottoNum[5] = (rand.nextInt(45))+1;
//		for(int i=0;i<lottoNum.length;i++) {
//			/* 변수 활용
//			int tmp = lottoNum[i];
//			System.out.println(tmp);
//			*/
//			//한줄 정리
//			System.out.println(lottoNum[i]);
//		}
		
		
		
		
		
//		InputStream a = System.in;
//		PrintStream b = new PrintStream();
//		System.out = b;
		
	}
}
