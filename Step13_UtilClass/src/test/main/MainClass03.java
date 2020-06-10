package test.main;

import java.util.ArrayList;
import java.util.List;

public class MainClass03 {
	public static void main(String[] args) {
		//String type 을 저장할 ArrayList 객체 생성하고
		//참조값을 List 인터페이스 type 지역변수 msgs 에 담기
		List<String> msgs = new ArrayList<>();
		msgs.add("김");
		msgs.add("구");
		msgs.add("라");
		msgs.add("해");
		msgs.add("골");
		//List 객체에 담긴 문자열을 for 문을 이용해서 순서대로 콘솔창에 출력해 보세요.
		for(int i=0; i<msgs.size(); i++) {
			//System.out.println(msgs.get(i));
			String tmp = msgs.get(i);
			System.out.println(tmp);
		}
		System.out.println("확장 for 문");
		for(String tmp:msgs) { //msgs list 인터페이스 타입의 타입(String)에 맞게 tmp 변수의 데이터 타입(String)을 정해야한다.
			System.out.println(tmp);
		}
	}
}
