package test.frame11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;


public class MyFrame extends JFrame implements ActionListener{
	//필드
	JTextField tf_num1, tf_num2;
	JLabel label_result;
	
	//default 생성자
	public MyFrame() {
		this.setLayout(new BorderLayout());
		
		//Panel
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.YELLOW);
		//Panel을 북쪽에 배치하기
		add(topPanel, BorderLayout.NORTH);
		
		//JTextField 객체를 만들어서 JPanel에 추가하기
		tf_num1 = new JTextField(10);
		topPanel.add(tf_num1);
		
		//기능 버튼 객체를 만들어서 JPanel 에 추가하기
		JButton plusBtn = new JButton("+");
		JButton minusBtn = new JButton("-");
		JButton multiBtn = new JButton("*");
		JButton divideBtn = new JButton("/");
		topPanel.add(plusBtn);
		topPanel.add(minusBtn);
		topPanel.add(multiBtn);
		topPanel.add(divideBtn);
		
		//두번째 JTextField 만들어서 패널에 추가 하기
		tf_num2 = new JTextField(10);
		topPanel.add(tf_num2);
		
		//JLabel
		JLabel label1 = new JLabel("=");
		label_result = new JLabel("0");
		//패널에 레이블 추가하기
		topPanel.add(label1);
		topPanel.add(label_result);
		
		//버튼에 리스너 등록하기
		plusBtn.addActionListener(this);
		minusBtn.addActionListener(this);
		multiBtn.addActionListener(this);
		divideBtn.addActionListener(this);
		
		//버튼에 액션 command 지정하기(각 버튼을 구분하기위함)
		plusBtn.setActionCommand("plus");
		minusBtn.setActionCommand("minus");
		multiBtn.setActionCommand("multi");
		divideBtn.setActionCommand("divide");
	}
	public static void main(String[] args) {
		//MyFrame 클래스를 이용해서 객체 생성하고 참조값을 지역변수 frame 에 담기
		MyFrame frame = new MyFrame();
		//프레임의 제목 설정
		frame.setTitle("계산기");
		//프레임을 닫으면 자동으로 프로세스가 종료되도록 하는 메소드.
		frame.setBounds(100, 100, 500, 500);
		frame.setVisible(true);
		System.out.println("메소드 종료");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void send() {
//		//JTextField 에 입력한 문자열을 읽어와야한다.
//		String msg = inputMsg.getText();
//		//읽어온 문자열을 JLabel에 추가하기
//		lab1.setText(msg);
//		//입력창 문자열 삭제하기
//		inputMsg.setText("");
//		//모델에 입력한 문자열 추가하기
//		model.addElement(msg);
	}
	//ActionListener 인터페이스를 구현 해서 강제 오버라이드된 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		//JTextField 에 입력한 문자열을 읽어와서 숫자(실수)로 바꿔준다.
		double num1 = Double.parseDouble(tf_num1.getText());
		double num2 = Double.parseDouble(tf_num2.getText());
		//연산의 결과값을 담을 지역 변수
		double result = 0;
		//눌러진 버튼의 command 읽어오기
		String command = e.getActionCommand();
		if(command.equals("plus")) {
			result = num1+num2;
		}else if(command.equals("minus")) {
			result = num1-num2;
		}else if(command.equals("multi")) {
			result = num1*num2;
		}else if(command.equals("divide")) {
			result = num1/num2;
		}
		//결과값을 JLabel 에 출력하기
		label_result.setText(Double.toString(result));
		}catch(Exception exe) {
			JOptionPane.showMessageDialog(this, "숫자 형식에 맞게 입력해주세요");
		}
	}
	
//	//키를 눌렀을 때 호출되는 메소드
//	@Override
//	public void keyPressed(KeyEvent e) {
//		System.out.println("pressed");
//		//눌러진 키보드의 코드값을 읽어온다.
//		int code = e.getKeyCode();
//		if(code == KeyEvent.VK_ENTER) {
//			send();
//		}
//	}
//	
//	@Override
//	public void keyReleased(KeyEvent e) {
//		System.out.println("released");
//	}
//	@Override
//	public void keyTyped(KeyEvent e) {
//		System.out.println("typed");
//	}
}
