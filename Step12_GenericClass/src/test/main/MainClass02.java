package test.main;

import test.mypac.Apple;
import test.mypac.Banana;
import test.mypac.FruitBox;
import test.mypac.Orange;

public class MainClass02 {
	public static void main(String[] args) {
		//객체를 생성할 때 Generic 클래스는 생략이 가능하다.(객체 생성할 때 복잡해질 수가 있어서 생략가능하다.)
		FruitBox<Apple> box1 = new FruitBox<>();
		FruitBox<Orange> box2 = new FruitBox<>();
		FruitBox<Banana> box3 = new FruitBox<>();
	}
}
