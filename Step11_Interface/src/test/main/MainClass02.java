package test.main;

import test.mypac.MyRemocon;
import test.mypac.Remocon;

public class MainClass02 {
	//필드에 Remocon type
	static Remocon r3 = new Remocon() {
		@Override
		public void up() {
			System.out.println("화면밝기를 올려요");
		}
		@Override
		public void down() { 
			System.out.println("화면밝기를 내려요");
		}
	};
	public static void main(String[] args) {
		/*
		 *  Remocon 인터페이스를 구현한 익명의 local inner 클래스의 생성자를 호출해서
		 *  참조값을 얻어내서 Remocon type 의 지역변수 r1에 대입하기
		 */
		Remocon r1 = new Remocon() { //추상 클래스에서의 익명 클래스 사용법과 비슷
			@Override
			public void up() {
				System.out.println("볼륨을 올려요");
			}
			@Override
			public void down() {
				System.out.println("볼륨을 내려요");
			}
		};
		
		useRemocon(r1);
		useRemocon(new Remocon() {
			@Override
			public void up() {
				System.out.println("채널을 올려요");
			}
			@Override
			public void down() {
				System.out.println("채널을 내려요");
			}
		});
		useRemocon(r3);
	}
	public static void useRemocon(Remocon r) {
		r.up();
		r.down();
	}
}
