package test.main;

import java.util.Scanner;

public class MainClass01 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자 입력:");
		
		//숫자 형식의 문자열을  입력받는다. "10", "20" "10.1" 등등
		String inputNum = scan.nextLine();
		
		try { //예외가 발생할 수 있는 곳을 try 블럭으로 묶는다.
			
		//입력한 숫자를 실제 숫자로 바꾼다.
		double num = Double.parseDouble(inputNum);
		//입력한 숫자에 100을 더한다.
		double result = num+100;
		System.out.println("입력한 숫자 + 100 : "+result);
		}catch(NumberFormatException nfe) { //예외 사항이 발생했을 시에 실행되는 부분.
			System.out.println("숫자 형식에 맞게 입력 하세요.");
			
			//예외 객체의 참조값을 가지는 지역변수 nfe에 printStackTrace() 메소드 사용하여 예외 정보를 콘솔창에 출력하기
			nfe.printStackTrace();  
		}
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
