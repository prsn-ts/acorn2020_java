package test.auto;

public class Taxi extends Car{
	//부모 클래스에 생성자에 값을 전달해야하기 때문에 생성자 정의.
	public Taxi(Engine client) {
		super(client);
	}
	
	//Taxi 클래스 용도에 맞게 Car 클래스에서 사용하던 메소드 재정의.
	@Override
	public void drive() {
		super.drive();
		System.out.println("손님을 안전하게 모셔요");
	}
}
