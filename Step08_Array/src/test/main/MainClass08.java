package test.main;
/*
 *  1. cherry, apple, banana, melon, 7
 *     5개의 문자열 중에서 1개가 랜덤하게 출력되게 해 보세요.
 *  
 *  2. 5개의 문자열 중에서 3개가 한줄에 한번에 랜덤하게 출력되게 해 보세요.
 *     예) cherry | apple | cherry
 *         7 | apple | melon
 *         7 | 7 | 7
 */

import java.util.Random;

public class MainClass08 {
	public static void main(String[] args) {
//		//랜덤한 문자열 출력을 위한 Random 객체 생성
//		Random rand = new Random();
//		//5개의 문자열을 저장할 배열을 생성.
//		String[] savedStr = new String[5];
//		//배열에 순서대로 문자열 값을 저장.
//		savedStr[0] = "cherry";
//		savedStr[1] = "apple";
//		savedStr[2] = "banana";
//		savedStr[3] = "melon";
//		savedStr[4] = "7";
//		//Random 객체의 참조값을 가지고 있는 rand 변수를 이용해서 하나의 랜덤한 값을 ranV 변수에 저장.
//		String ranV = savedStr[rand.nextInt(5)];
//		//ranV 변수 값을 콘솔창에 출력.
//		System.out.println(ranV);
	
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
		//ranV 배열에 저장된 값을 콘솔창에 출력
		System.out.println(ranV[0]+" "+ranV[1]+" "+ranV[2]);
		
	}
}
