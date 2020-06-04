package test.main;

import test.auto.Car;
import test.auto.Engine;
import test.auto.Taxi;

public class MainClass09 {
	public static void main(String[] args) {
		//여러분들이 Car 클래스를 상속받아서 만든 클래스로 객체를 생성해서
		//아래의 useCar() 메소드를 호출해 보세요.
		
		//Taxi 타입의 tx1의 지역변수에 Taxi 객체의 참조값을 넣기.
		Taxi tx1 = new Taxi(new Engine());
		//Taxi 타입의 drive() 메소드 실행.
		MainClass09.useCar(tx1);
	}
	
	public static void useCar(Car car) {
		car.drive();
	}
}
