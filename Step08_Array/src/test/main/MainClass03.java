package test.main;

public class MainClass03 {
	public static void main(String[] args) {
		//String type 을 담을 수 있는 방 3개짜리 빈 배열 객체를 만들어서 참조값을 지역변수 names에 담기
		String[] names = {null, null, null};
		//각각의 방에 순서대로 String type 담기
		names[0] = "김구라";
		names[1] = "해골";
		names[2] = "원숭이";
		
		String[] names1 = new String[3];
		names1[0] = "호랑이";
		names1[1] = "다이아울프";
		names1[2] = "금빛원숭이";
	}
}
