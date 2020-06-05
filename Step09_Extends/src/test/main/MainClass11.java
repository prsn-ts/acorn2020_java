package test.main;

import test.human.Blood;
import test.human.Men;

public class MainClass11 {
	public static void main(String[] args) {
		/*
		 * 프로그래밍의 목적 : 영화를 보고 싶다
		 * 영화를 보는 프로그래밍의 목적을 달성해 보세요.
		 */
		
		/*
		 *  3가지 방법으로 사용 가능
		 */
		
		//첫번째 방법 -> Men 객체의 참조값을 cos에 저장해놓고  cos 변수를 이용한 메소드 호출.
		//영화를 볼 수 있는 Men 객체 생성.
		Men cos = new Men(new Blood("+", "O"));
		//seeMovie() 메소드 호출.
		cos.seeMovie();
		
		//두번째 방법 -> Men 객체 생성시 필요한 Blood 객체의 참조값을 b1 변수에 저장 후 그 변수를 이용해 Men 객체 생성의 인자값으로 전달.
		Blood b1 = new Blood("+", "O");
		Men men2 = new Men(b1);
		men2.seeMovie();
		
		//세번째 방법 -> 객체의 참조값을 저장할 변수없이 일회용으로 객체 생성 후 메소드 호출하기.
		new Men(new Blood("-", "B")).seeMovie();
	}
}
