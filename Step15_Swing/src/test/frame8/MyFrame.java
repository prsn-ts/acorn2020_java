package test.frame8;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener{
	JTextField inputMsg;
	
	//default 생성자
	public MyFrame() {
		this.setLayout(new FlowLayout());
		//문자열 한줄을 입력할 수 있는 JTextField
		inputMsg = new JTextField(10);
		this.add(inputMsg);
		
		JButton sendBtn = new JButton("전송");
		sendBtn.addActionListener(this);
		add(sendBtn);
	}
	
	public static void main(String[] args) {
		//MyFrame 클래스를 이용해서 객체 생성하고 참조값을 지역변수 frame 에 담기
		MyFrame frame = new MyFrame();
		//프레임의 제목 설정
		frame.setTitle("나의 프레임");
		//프레임을 닫으면 자동으로 프로세스가 종료되도록 하는 메소드.
		frame.setBounds(100, 100, 500, 500);
		frame.setVisible(true);
		System.out.println("메소드 종료");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//ActionListener 인터페이스를 구현 해서 강제 오버라이드된 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		//JOptionPane.showMessageDialog(this, "버튼을 눌렀네");
		String Text = inputMsg.getText();
		System.out.println(Text);
		JOptionPane.showMessageDialog(this, Text);
	}
}
