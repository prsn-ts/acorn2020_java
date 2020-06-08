package test.main;

import test.mypac.Weapon;

public class MainClass07 {
	
	//run 했을 때 실행 순서가 시작되는 특별한 main 메소드
	public static void main(String[] args) {
		/*
		 *  Weapon 추상 클래스를 상속받은 익명의 local inner class
		 *  의 생성자를 호출해서 Weapon type 의 참조값 얻어내서 지역변수 w1에 담기
		 */
		Weapon w1 = new Weapon() //new Weapon()의 부분은 Weapon 추상 클래스를 객체 생성한 것이 아닌 이름이없는(익명)의 클래스의 객체를 생성한 것이다.
		{ // 이 중괄호안에 내용은 익명의 클래스를 정의한 것. 마치 class ?(이름모름) extends Weapon{} 한 것과 같은 느낌.
			@Override
			public void attack() {
				System.out.println("아무나 공격해요!");
			}	
		};
		
		Weapon w2 = new Weapon() {
			@Override
			public void attack() {
				System.out.println("포격 공격을 해요!");
			}
		};
		
		useWeapon(w1);
		useWeapon(w2);
		useWeapon(new Weapon() {
			
			@Override
			public void attack() {
				System.out.println("어뢰 공격을 해요");
			}
		}); // 익명 클래스의 객체 생성을 일회용으로 사용하여 익명 객체의 참조값을 useWeapon 메소드의 인자값으로 전달.
	}
	
	public static void useWeapon(Weapon w) {
		w.prepare();
		w.attack();
	}
}
