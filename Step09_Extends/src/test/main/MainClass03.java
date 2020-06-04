package test.main;

import test.mypac.HandPhone;
import test.mypac.SmartPhone;

public class MainClass03 {
	public static void main(String[] args) {
		//HandPhone 객체를 생성해서 HandPhone type 지역변수 p1 에 담기
		HandPhone p1 = new HandPhone();
		//인터넷을 해야 한다. 즉 SmartPhone type 객체가 필요하다
		//p1 안에 있는 값을 이용해서 인터넷을 할 수 있을까?
		
		//실행시에 ClassCastException 발생 -> p1은 원래 HandPhone 타입인데 아무런 관련없는(상속관계가 아닌) SmartPhone으로 캐스팅해봤자 SmartPhone의 기능을 쓸 수 없다.
		SmartPhone p2 = (SmartPhone)p1; 
		//인터넷 실행 안됨.
		p2.doInternet();
	}
}
