package test.main;

import test.mypac.SmartPhone;

public class MainClass04 {
	public static void main(String[] args) {
		//SmartPhone 타입의 p1이라는 저장공간을 만들 준비만 한 상태.(저장공간이 만들어지지 않음)
		SmartPhone p1;
		//SmartPhone 타입의 지역변수 p2를 만들고 비워둔 상태(참조값 X)
		SmartPhone p2 = null;
		//SmartPhone 타입의 지역변수 p3를 만들고 참조값을 넣은 상태(참조값 O)
		SmartPhone p3 = new SmartPhone();
		
		//p1은 아직 만들어지지 않았기 때문에 문법 성립 X
		//p1.call();
		
		//p2는 비어있는(null 이 들어 있는) 상태이기 때문에
		//실행시(runtime 시)에 NullPointerException 이 발생한다. (문법만 성립, 실행시 오류!)
		p2.call();
		
		//p3에는 참조값이 들어 있으므로 정상적으로 사용가능
	}
}
