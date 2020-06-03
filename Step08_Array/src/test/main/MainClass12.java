package test.main;

import java.util.Random;
import java.util.Scanner;

/*
 *  [ 컴퓨터와 가위 바위 보 하기 ]
 *  
 *  가위(s) 바위(r) 보(p) :s
 *  
 *  나 : 가위  vs 컴 : 보
 *  win(draw or lose)~
 *  
 */

public class MainClass12 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//무한 루프
		while(true) {
			System.out.println("가위(s) 바위(r) 보(p) :");
			String inputV = scan.nextLine();
			if(inputV.equals("q")) {
				break; //무한 루프(가위바위보 게임 진행) 빠져나오기
			}
			//게임 시작.
			MainClass12.doGame(inputV);
		}
		System.out.println("게임이 종료되었습니다.");
	}
	public static void doGame(String inputC) {
		//컴퓨터가 낼 수 있는 무작위의 랜덤한 가위바위보를 위한 Random 객체 생성.
		Random com_ran = new Random();
		//가위,바위,보라는 글자를 출력하기위한 저장할 배열 선언하기.
		String[] srp = {"가위","바위","보"};
		//사용자가 입력한 값들(가위,바위,보)을 식별하여 특정한 숫자(0 or 1 or 2)와 매핑시켜서 의미를 부여.
		//my 변수는 의미적으로 일치하는 srp 배열안에 있는 곳에 연결해 거기에 있는 값을 출력할 수 있게 도와준디.
		int my = 0;
		if(inputC.equals("s")) {
			my = 0; //사용자가 가위를 선택했을 경우
		}else if(inputC.equals("r")) {
			my = 1; //사용자가 바위를 선택했을 경우
		}else if(inputC.equals("p")) {
			my = 2; //사용자가 보를 선택했을 경우
		}else {
			System.out.println("가위는 s, 바위는 r, 보는 p 로 입력해주세요.");
			return;
		}
		//컴퓨터가 낼 수 있는 경우의 수 3가지(가위,바위,보)의 범위를 설정.
		int com = com_ran.nextInt(3);
		//내가 이기는 경우
		if((my == 0 && com == 2) || (my == 1 && com == 0) || (my == 2 && com == 1)) {
			System.out.println("나 : "+srp[my]+" vs 컴 : "+srp[com]);
			System.out.println("win~!");
		}
		//비기는 경우
		else if((my == 0 && com == 0) || (my == 1 && com == 1) || (my == 1 && com == 1)) {
			System.out.println("나 : "+srp[my]+" vs 컴 : "+srp[com]);
			System.out.println("draw~");
		}
		//내가 지는 경우
		else {
			System.out.println("나 : "+srp[my]+" vs 컴 : "+srp[com]);
			System.out.println("lose...");
		}
//		if(inputC == inputC.equals()) {
//			System.out.println("내가 입력한것은 가위!");
//		}
	}
}
