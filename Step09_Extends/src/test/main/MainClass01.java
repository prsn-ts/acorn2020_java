package test.main;

import test.mypac.HandPhone;
import test.mypac.Phone;

public class MainClass01 {
	public static void main(String[] args) {
		
		//Phone 클래스로 객체 생성해서 참조값을 지역 변수에 담기
		Phone p1 = new Phone();
		Object p2 = new Phone();
		
		//HandPhone 클래스로 객체 생성해서 참조값을 지역 변수에 담기
		HandPhone p3 = new HandPhone();
		Phone p4 = new HandPhone();
		Object p5 = new HandPhone();
		
		//이미 만들어진 객체의 참조값을 다른 type 으로 받아보기
		
		Phone p6 = p3;
		//HandPhone의 객체의 참조값을 가지고있는 p3의 참조값을 p6에 저장해도 HandPhone의 부모인 Phone타입인 p6은 HandPhone의 기능을 쓸 수 없다.
		//p3 객체가 갖고있는 기능들을 p3의 참조값을 받은 p6도 p3 객체의 기능들을 잠재적으로 갖고있지만 타입을 Phone으로 지정해서 HandPhone의 기능을 안쓰겠다고 선언?! 하는 느낌.
		//사실 이렇게 선언하면 어떤 타입을 선택하냐에 따라서 기능을 사용할 수도 있고 못 사용할 수도 있지만 확실한건 이런 기능이 있다는 것은 어떤걸 선택하냐에 따라서 내가 하고싶은 걸로 결정하겠다의 의미가 담겨있기 때문에 더 자유롭고 유연한 느낌이다.
		
		Object p7 = p3; 
		//HandPhone의 객체의 참조값을 가지고있는 p3의 참조값을 p7에 저장해도 HandPhone, Phone의 부모인 Object타입인 p7은 HandPhone, Phone의 기능을 쓸 수 없다.
		//p3 객체가 갖고있는 기능들을 p3의 참조값을 받은 p7도 p3 객체의 기능들을 잠재적으로 갖고있지만 타입을 Object로 지정해서 HandPhone. Phone의 기능을 안쓰겠다고 선언?! 하는 느낌.
		//사실 이렇게 선언하면 어떤 타입을 선택하냐에 따라서 기능을 사용할 수도 있고 못 사용할 수도 있지만 확실한건 이런 기능이 있다는 것은 어떤걸 선택하냐에 따라서 내가 하고싶은 걸로 결정하겠다의 의미가 담겨있기 때문에 더 자유롭고 유연한 느낌이다.
		
		//String str = p3; -> 오류! String 데이터 타입은 p3 변수가 갖고있는 타입이 아니다.
		
		
	}
}
