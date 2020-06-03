package test.main;

import java.util.Random;
import java.util.Scanner;

public class MainClass11 {
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
			MainClass11.doGame();
		}
		System.out.println("main 메소드가 종료 됩니다.");
	}
	public static void doGame() {
		//랜덤한 문자열 출력을 위한 Random 객체 생성
		Random rand = new Random();
		//5개의 문자열을 저장할 배열을 생성 후 바로 값을 집어넣기.
		String[] savedStr = {"cherry","apple","banana","melon","7"};
		//savedStr 배열에 저장된 값중에 랜덤한 값을 담을 ranV 배열 생성.
		String[] ranV = new String[3];
		//Random 객체의 참조값을 가지고 있는 rand 변수를 이용해서 랜덤한 값을 ranV 배열에 저장.
		ranV[0] = savedStr[rand.nextInt(5)];
		ranV[1] = savedStr[rand.nextInt(5)];
		ranV[2] = savedStr[rand.nextInt(5)];
		//ranV 배열에 저장된 값에 점수를 매길 int 배열을 생성.
		int[] score = {10,20,30,40,1000};
		//랜덤한 값 3개가 같은 지 확인하는 조건문 생성.
		if(ranV[0]==ranV[1] && ranV[1]==ranV[2]) {
			//ranV 배열에 들어온 문자열의 판별을 위해서 savedStr 배열에 저장된 원소 개수만큼 반복문 실행. 
			for(int i=0; i<savedStr.length;i++) {
				//ranV 배열에 들어온 문자열의 판별에 따른 다른 점수를 보여주기위한 조건문 설정.
				if(ranV[0] == savedStr[i]) {
					//1초 있다가 결과가 출력됨.(for문을 통해 ranV 배열안에 하나하나의 값이 1초있다가 출력되게 설정.)
					for(int j=0; i<ranV.length; j++) {
						try {
							//실행의 흐름(스레드)을 1초 잡아 놓기(1초마다 한 단어가 나오도록 설정)
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//ranV 배열에 저장된 값을 콘솔창에 출력
						System.out.print(ranV[j]+" | ");
					}
					System.out.println();
					System.out.println(score[i]+"점 입니다.");
				}
			}
		}
		//랜덤한 값 3개가 다를 경우에 실행.
		else {
			//1초 있다가 결과가 출력됨.(for문을 통해 ranV 배열안에 하나하나의 값이 1초있다가 출력되게 설정.)
			for(int i=0; i<ranV.length; i++) {
				try {
					//실행의 흐름(스레드)을 1초 잡아 놓기(1초마다 한 단어가 나오도록 설정)
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//ranV 배열에 저장된 값을 콘솔창에 출력
				System.out.print(ranV[i]+" | ");
			}
			System.out.println();
			System.out.println("0점 입니다.");
		}
	}
}
