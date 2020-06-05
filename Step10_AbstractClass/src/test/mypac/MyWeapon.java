package test.mypac;

public class MyWeapon extends Weapon {
	//상속받은 Weapon 클래스의 추상 메소드를 재정의.
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("주먹싸움을 해요");
	}

}
