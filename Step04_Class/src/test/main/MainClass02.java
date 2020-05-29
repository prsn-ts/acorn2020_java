package test.main;

import test.mypac.MyUtil;

public class MainClass02 {
	public static void main(String[] args) {
		//MyUtil 클래스의 static 메소드 호출하기
		MyUtil.send(); //send라는 이름의 static method는 "클래스명.메소드이름();" 이런식으로 사용됨.(객체 생성 X)
	}
}
