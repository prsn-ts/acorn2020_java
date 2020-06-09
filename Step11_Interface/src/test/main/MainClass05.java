package test.main;

import test.mypac.Joinner;

public class MainClass05 {
	public static void main(String[] args) {
		Joinner j1 = new Joinner() {
			@Override
			public String join(String first, String second) {
				return first+second;
			}
		}; //Joinner는 인터페이스이므로 추상메소드를 갖고 있기 때문에 구체화시키기위해 메소드 오버라이드를 함.
		String result1 = j1.join("김", "구라");
		
		Joinner j2 = (first, second)->{ //자바스크립트의 애로우 함수를 자바에서는 람다식 표현이라고 함.
			return first+second;
		};
		String result2 = j2.join("금빛", "Monkey");
		
		Joinner j3 = (first,second) -> first+second;
		String result3 = j3.join("원", "효대사");
	}
}
