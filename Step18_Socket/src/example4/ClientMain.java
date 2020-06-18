package example4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame implements ActionListener, KeyListener{
	//필드
	JTextField tf_msg;
	//서버와 연결된 Socket 객체의 참조값을 담을 필드
	Socket socket;
	//서버로부터 메세지를 읽어내기위해 설정.
	BufferedReader br;
	//JTextArea를 여러곳에서 사용하기위해 필드 지정
	JTextArea ta;
	
	//생성자
	public ClientMain() {
		//서버에 소켓 접속을 한다.
		try {
			socket = new Socket("192.168.0.15", 5000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		new ClientThread().start();
		
		
		//레이아웃을 BorderLayout 으로 지정하기
		setLayout(new BorderLayout());
		
		//패널 만들기
		JPanel panel = new JPanel();
		
		//JTextField(입력창) 만들기
		tf_msg = new JTextField(10);
		
		//버튼 만들기
		JButton sendBtn = new JButton("전송");
		sendBtn.setActionCommand("send");
		
		//패널에 입력창과 버튼을 추가
		panel.add(tf_msg);
		panel.add(sendBtn);
		
		//프레임의 아래쪽에 배치하기
		this.add(panel, BorderLayout.SOUTH);
		
		//버튼에 액션리스너 지정.
		sendBtn.addActionListener(this);
		//enter 키 눌렀을 때 전송하기
		tf_msg.addKeyListener(this);
		
		//JTextArea 설정.
		ta = new JTextArea();
		add(ta);
		ta.setBackground(Color.LIGHT_GRAY);
	}
	
	public static void main(String[] args) {
		//프레임 객체 생성
		ClientMain f = new ClientMain();
		f.setTitle("채팅창");
		f.setBounds(100, 100, 500, 500);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		send();
//		finally {
//			try {
//				if(socket!=null)socket.close();
//			}catch(Exception e2) {};
//		}
		System.out.println("main 메소드가 종료됩니다.");
	}
	//메세지 서버로 전송하는 기능
	public void send() {
		//전송할 문자열
		String msg = tf_msg.getText();
		//전송 버튼 누를 시 메세지 내용 삭제
		tf_msg.setText(null);
		try {
			//문자열을 서버에 전송(출력Output) 하기
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(msg);
			osw.write("\r\n"); //개행기호도 출력 (서버에서 줄단위로 읽어낼 예정)
			osw.flush();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public class ClientThread extends Thread {
		@Override
		public void run() {
			//서버로부터 입력받은 메세지 출력을 위한 BufferedReader 사용
			try {
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				while(true) {
					String receive = br.readLine();
					ta.append(receive);
					ta.append("\r\n");
					if(receive == null) {
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				System.out.println("접속이 끊겼습니다.");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int getKey = e.getKeyCode();
		if(getKey == KeyEvent.VK_ENTER) {
			send();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int getKey = e.getKeyCode();
		if(getKey == KeyEvent.VK_ENTER) {
			send();
		}
	}
	
}
