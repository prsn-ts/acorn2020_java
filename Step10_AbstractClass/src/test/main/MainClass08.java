package test.main;

import test.mypac.Weapon;

public class MainClass08 {
	//클래스의 필드에 Weapon type 의 참조값 넣어두기
	static Weapon w1 = new Weapon() { //중괄호안의 이름은 anonymous inner class.
		@Override
		public void attack() {
			System.out.println("수중 공격을 해요");
		}
	}; //필드에 익명 클래스의 객체를 생성해서 그 객체의 참조값을 w1 필드에 저장.
	
	public static void main(String[] args) {
		//필드에 저장된 Weapon type 의 참조값을 이용해서 useWeapon() 메소드 호출하기
		useWeapon(w1); //필드의 참조값을 useWeapon 메소드 인자값으로 전달.
	}
	
	public static void useWeapon(Weapon w) {
		w.prepare();
		w.attack();
	}
}
