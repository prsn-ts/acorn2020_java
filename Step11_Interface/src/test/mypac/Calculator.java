package test.mypac;

@FunctionalInterface //메소드를 하나만 정의하도록 강제하는 기능
public interface Calculator {
	//인자로 전달되는 두 실수 값을 연산해서 결과를 리턴해주는 메소드
	public int exec (int a, int b);
}
