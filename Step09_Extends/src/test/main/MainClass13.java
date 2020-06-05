package test.main;

import test.human.Blood;
import test.human.Men;

public class MainClass13 {
	public static void main(String[] args) {
		/*
		 *  프로그래밍의 목적: 아래의 useMen() 메소드를 호출하는 것
		 *  아래의 useMen() 메소드를 호출해 보세요.
		 */
		
//		첫번째 방법 -> 생성자의 인자값으로 변수를 전달하기
//		Men m = new Men(new Blood("-", "O"));
//		MainClass13.useMen(m);
		
		//두번째 방법 -> 생성자의 인자값으로 변수를 전달하기(세분화하기)
		Blood b = new Blood("-", "O");
		Men m = new Men(b);
		MainClass13.useMen(m);
		
		//세번째 방법 -> 객체의 참조값을 저장할 변수없이 일회용으로 객체 생성 후 메소드 호출하기.
		MainClass13.useMen(new Men(new Blood("-", "O")));
		
	}
	
	public static void useMen(Men m) {
		m.walk();
		m.study();
		m.seeMovie();
		//m.blood.getRh();
	}
}
