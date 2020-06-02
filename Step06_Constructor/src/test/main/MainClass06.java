package test.main;

import javax.swing.JFrame;

public class MainClass06 {
	public static void main(String[] args) {
		JFrame f=new JFrame();
		//JFrame의 객체를 생성할 때 값을 넣어주지 않았으면 setTitle 메소드를 이용해 창의 제목을 정할 수 있음.
		f.setTitle("원숭이");
		//창의 위치와 폭과 높이
		f.setBounds(500, 100, 700, 500);
		//창이 화면상에 보이도록 한다.
		f.setVisible(true);
		//창을 닫았을 때 프로세스도 자동으로 종료 되도록 한다.
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
