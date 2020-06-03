package test.main;

import java.util.Random;

public class MainClass07 {
	public static void main(String[] args) {
		/*
		 *  2. Random 객체를 이용해서 로또 번호를 6개 랜덤하게 얻어내서
		 *  	배열에 저장한다.
		 *  	모두다 저장이 되면 for 문을 이용해서 배열에 저장된 모든 로또 번호를
		 *  	콘솔창에 순서대로 출력하는 코드를 작성해 보세요.
		 */
		//Random 객체 생성 후 참조값을 rand 변수에 담기
		Random rand = new Random();
		//로또 번호를 저장할 수 있는 방 6개까지 배열 객체 생성하기
		int lottoNum[] = new int[6];
//		lottoNum[0] = (rand.nextInt(45))+1;
//		lottoNum[1] = (rand.nextInt(45))+1;
//		lottoNum[2] = (rand.nextInt(45))+1;
//		lottoNum[3] = (rand.nextInt(45))+1;
//		lottoNum[4] = (rand.nextInt(45))+1;
//		lottoNum[5] = (rand.nextInt(45))+1; -> 비효율적이다(for문을 생각해보길)
		//반복문 돌면서 로또번호 6개를 얻어낸다.
		for(int i=0;i<lottoNum.length;i++) {
			lottoNum[i] = (rand.nextInt(45))+1;
		}
		
//		for(int i=0; i<lottoNum.length; i++) {
//			/* 변수 활용
//			int tmp = lottoNum[i];
//			System.out.println(tmp);
//			*/
//			//한줄 정리
//			System.out.println(lottoNum[i]);
//		}
		//기본 for문 형태에서 조금 간편하게 바꾼 문법.
		//다만 for문안에 반복 횟수에 관련된 i 변수를 쓸 수 없으므로 i 변수가 필요없을 때 사용.
		for(int tmp : lottoNum) {
			System.out.println(tmp); 
		}
		
	}
}
