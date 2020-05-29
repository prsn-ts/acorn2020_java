package test.main;

import test.mypac.Box;
import test.mypac.Car;
import test.mypac.Member;
import test.mypac.Rect;

public class MainClass06 {
	public static void main(String[] args) {
		Member a = Box.mem; //null
		Rect b = Box.rect; //null
		Car c = Box.car; //참조값이 들어 있다.
		c.drive(); // 메소드 호출가능!
		
		//아래와 같이 사용할 수도 있다.
		Box.car.drive(); //c.drive();와 같은 뜻. c 변수에는 Box 클래스 안에 Car 클래스를 가진 car 필드에 drive() 메소드를 호출함.
		
		//a.showInfo(); // a가 null 이므로(a 변수 안에 객체의 참조값이 없으므로) NullPointerException 발생.
		//b.getArea(); // b가 null 이므로(b 변수 안에 객체의 참조값이 없으므로) NullPointerException 발생
	}
}
