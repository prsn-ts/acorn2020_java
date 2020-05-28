package test.main;

public class MainClass08 {
	public static void main(String[] args) {
		//String type 의 메소드 사용해 보기
		String str = "abcde12345";
		//문자열의 길이
		int size = str.length(); //함수가 호출되고 난 후 호출된 그 자리에 리턴값으로 바뀐다.(java의 경우 안바뀌는 경우도 있다.)
		//5번째 인덱스의 문자 1개(char)
		char ch = str.charAt(5);
		//소문자를 모두 대문자로 변환한 문자열 얻어내기
		String result = str.toUpperCase();
	}
}
