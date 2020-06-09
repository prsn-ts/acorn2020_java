package test.main;

import test.mypac.Drill;

public class MainClass04 {
	public static void main(String[] args) {
		useDrill(new Drill() {
			@Override
			public void hole() {
				System.out.println("바닥에 구멍을 뚫어요");
			}
		});
		
		Drill d1 = ()->{
			System.out.println("벽에 20mm 의 구멍내기");
		}; //자바스크립트의 애로우 함수랑 비슷한 모양.(람다식 표현) -> 데이터 타입에 해당하는 클래스 혹은 인터페이스(Drill)의 메소드가 한개인 경우에만 문법 성립.
		
		useDrill(d1);
		useDrill(()->{
			System.out.println("핸드폰에 1mm 구멍내기?");
		});
	}
	
	public static void useDrill(Drill d) {
		d.hole();
	}
}
