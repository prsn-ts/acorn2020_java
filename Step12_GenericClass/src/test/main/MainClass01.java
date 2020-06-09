package test.main;

import test.mypac.Apple;
import test.mypac.Banana;
import test.mypac.FruitBox;
import test.mypac.Orange;

public class MainClass01 {
	//Generic 클래스를 다양하게 지정을 해서 객체 생성하고 참조값을 담을 수 있다.
	public static void main(String[] args) {
		FruitBox<Apple> box1 = new FruitBox<Apple>();
		box1.push(new Apple());
		Apple result1 = box1.pull();
		
		FruitBox<Orange> box2 = new FruitBox<Orange>();
		box2.push(new Orange());
		
		FruitBox<Banana> box3 = new FruitBox<Banana>();
		box3.push(new Banana());
		
		//Generic 클래스로 지정한 type 객체를 넣어주어야 한다.
		box1.push(new Apple());
		box2.push(new Orange());
		box3.push(new Banana());
		
		//Generic 클래스로 지정한 type 객체가 리턴된다.
		Apple result2 = box1.pull();
		Orange result3 = box2.pull();
		Banana result4 = box3.pull();
		
	}
}
