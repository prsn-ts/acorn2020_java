package test.main;

import test.mypac.Car;
import test.mypac.MyObject;

public class MainClass03 {
	public static void main(String[] args) {
		MyObject obj1 = new MyObject();
		// .setNum(), .setName(), .useCar() 메소드를 호출해 보세요.
		obj1.setNum(7);
		obj1.setName("object");
		obj1.useCar(new Car());
	}
}
