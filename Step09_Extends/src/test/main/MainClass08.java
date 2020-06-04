package test.main;

import test.auto.Engine;
import test.auto.SportsCar;
import test.auto.Taxi;
import test.auto.Truck;

public class MainClass08 {
	public static void main(String[] args) {
		//Truck 객체를 생성해서 .drive() .dump() 메소드를 호출해 보세요.
		Truck tk1 = new Truck(new Engine());
		tk1.drive();
		tk1.dump();
		
		//SportsCar 타입의 s1의 지역변수에 SportsCar 객체의 참조값을 넣기.
		SportsCar s1 = new SportsCar(new Engine());
		//SportsCar 객체의 drive 메소드 출력하기.
		s1.drive();
		
		//Taxi 타입의 tx1의 지역변수에 Taxi 객체의 참조값을 넣기.
		Taxi tx1 = new Taxi(new Engine());
		//Taxi 타입의 drive() 메소드 실행.
		tx1.drive();
		
	}
}
