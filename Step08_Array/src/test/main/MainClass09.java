package test.main;

import java.util.Random;

public class MainClass09 {
	public static void main(String[] args) {
//		cherry, apple, banana, melon, 7
//			 cherry | apple | cherry
//			 0점 입니다.
//			 
//		     7 | apple | melon
//		     0점 입니다.
//		     
//		     7 | 7 | 7
//		     1000점 입니다.
//
//			 cherry | cherry | cherry
//			 10점 입니다.
//			 
//			 apple | apple | apple
//			 20점 입니다.
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
					//ranV 배열에 저장된 값을 콘솔창에 출력
					System.out.println(ranV[0]+" "+ranV[1]+" "+ranV[2]);
					System.out.println(score[i]+"점 입니다.");
				}
			}
		}
		//랜덤한 값 3개가 다를 경우에 실행.
		else {
			System.out.println(ranV[0]+" | "+ranV[1]+" | "+ranV[2]);
			System.out.println("0점 입니다.");
		}
	}
}
