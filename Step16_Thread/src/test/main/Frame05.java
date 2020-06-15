package test.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import test.mypac.CountRunnable;
import test.mypac.CountThread;

public class Frame05 extends JFrame implements ActionListener {
	
	//생성자
	public Frame05() {
		setLayout(new BorderLayout());
		//패널을 프레임의 상단에 배치
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		add(panel, BorderLayout.NORTH);
		//버튼을 패널에 추가하고
		JButton countBtn = new JButton("1~10까지 세기");
		panel.add(countBtn);
		//버튼에 리스너 등록하기
		countBtn.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		//MyFrame 클래스를 이용해서 객체 생성하고 참조값을 지역변수 frame 에 담기 
		Frame05 frame=new Frame05();
		//프레임의 제목 설정
		frame.setTitle("Frame01");
		//프레임을 닫으면 자동으로 프로세스가 종료 되도록 한다.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 500);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread(new Runnable() { //Runnable 인터페이스를 익명의 inner class로 강제로 메소드를 오버라이드하여 구현해서 Runnable 인터페이스의 참조값을 얻어내어 그 값을 Thread의 인자값으로 넘기는 방법. 
			@Override
			public void run() {
				//새로운 스레드가 실행할 코드 부분
			}
		}).start();
		
		new Thread(()->{ //Runnable 인터페이스를 익명의 inner class로 구현하는데 오버라이드할 Runnable 인터페이스의 메소드가 하나이므로 람다식 표현으로 가능.
			//새로운 스레드가 실행할 코드 부분
		}).start();
		System.out.println("새로운 스레드를 시작했습니다.");
	}
}
