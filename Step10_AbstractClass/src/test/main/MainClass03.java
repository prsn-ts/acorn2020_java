package test.main;

import test.mypac.Zoo;
import test.mypac.Zoo.Monkey;

public class MainClass03 {
	public static void main(String[] args) {
		//Zoo 클래스에 있는 getMonkey() 메소드를 호출해서
		//리턴되는 값을 m1 이라는 지역 변수에 담아 보세요.
		Zoo z = new Zoo();
		//inner class type Monkey
		Monkey m1 = z.getMonkey();
		//메소드 호출하기
		m1.say();
		
		//Zoo 클래스에 있는 getTiger() 메소드를 호출해서
		//리턴되는 값을 t1 이라는 지역 변수에 담아 보세요.
		test.mypac.Zoo.Tiger t1 = z.getTiger(); //import 안할거면 데이터 타입을 경로로 지정해서 사용해도 된다.
		t1.say();
		
		//또는 변수 생성 안하고 메소드 호출도 가능하다.
		z.getTiger().say();
	}
}
