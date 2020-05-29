package test.mypac;

/*
 *  [ 클래스의 용도 ]
 *  
 *  1. 객체의 설계도 역할을 한다.
 *  2. 데이터 type 의 역할을 한다.
 *  3. static 필드(자바에서 속성을 지칭)나 static 메소드(자바에서 함수를 지칭)를 가지고 있는 역할을 한다.
 */

public class Car {
	//자동차의 이름을 저장할 필드
	public static String name;
	
	//달리는 메소드
	public static void drive() {
		System.out.println("부릉 부릉 달려요!");
	}
}
