package example5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.JSONObject;

/*
 *  메세지의 종류
 *  
 *  1. 일반 대화 메세지 {"name":"김구라", "msg":"안녕하세요"} -> 중괄호는 JSONObject로 처리
 *  2. 누군가 입장했다는 메세지 {"enter":"김구라"}
 *  3. 누군가 퇴장했다는 메세지 {"out":"원숭이"}
 *  4. 참여자 목록 메세지 {"members":["김구라","해골","원숭이"]} -> 대괄호는 JSONArray
 */

public class ClientMain extends JFrame implements ActionListener, KeyListener{
	//필드
	JTextField tf_msg;
	//서버와 연결된 Socket 객체의 참조값을 담을 필드
	Socket socket;
	//서버에 문자열을 출력할 BufferedWriter 객체의 참조값을 얻어내서 필드에 저장해 놓는다.(버튼을 누르는 등의 사용할 때마다 객체가 생성되면 메모리 공간만 많이 차지하게된다.
	//그래서 한번만 객체생성 후 그 값을 저장한 다음에 이후에 계속 사용한다.)
	BufferedWriter bw; 
	JTextArea area; //채팅 내용을 JTextArea에서 보기 위함.
	//대화명
	String chatName;
	
	//생성자
	public ClientMain() {
		//대화명을 입력받아서 필드에 저장한다.
		chatName = JOptionPane.showInputDialog(this, "대화명을 입력하세요");
		
		setTitle("대화명:"+chatName);
		
		//서버에 소켓 접속을 한다.
		try {
			//접속이 성공되면 Socket 객체의 참조값이 반환된다.
			//반환되는 객체의 참조값을 필드에 저장해 놓는다.
			socket = new Socket("192.168.0.30", 5000);
			//문자열을 서버에 전송(출력Output) 하기/ 서버에 문자열을 출력할 BufferedWriter 객체의 참조값을 얻어내서 필드에 저장해 놓는다.
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
			//내가 입장한다고 서버에 메세지를 보낸다.
			// "{"enter" : "김구라"}" 이런 형식으로 서버에 메세지를 보내기 위함.
			String msg = "{\"enter\":\""+chatName+"\"}"; //JSON 없이 출력하기 위한 모양(복잡하다..)
			
			//JSON 라이브러리를 이용해서 "{"enter" : "김구라"}" 출력.
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("enter", chatName);
			String msg2 = jsonObj.toString();
			
			//BufferedWriter 객체를 이용해서 보내기
			bw.write(msg);
			bw.newLine(); //한 메세지 찍고 개행하기위한 개행 메소드 호출.
			
			System.out.println(msg+" | "+msg2);
			//접속이 성공하는 시점인 socket = new Socket("192.168.0.30", 5000); 이 구문 이후에 서버로부터 메세지를 받을 스레드도 시작을 시킨다.
			new ClientThread().start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
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
		
		//버튼에 리스너 등록
		sendBtn.addActionListener(this);
		
		area = new JTextArea();
		//문자열 출력 전용으로 사용하기 위해 편집 불가능하도록 설정
		area.setEditable(false);
		//배경색
		area.setBackground(Color.PINK);
		//스크롤이 가능하도록
		JScrollPane scroll = new JScrollPane(area, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//프레임의 가운데에 배치하기
		add(scroll, BorderLayout.CENTER);
		
		//엔터키로 메세지 전송가능하게 하기 위해
		tf_msg.addKeyListener(this);
	}
	
	public static void main(String[] args) {
		//프레임 객체 생성
		ClientMain f = new ClientMain();
		//f.setTitle("채팅창"); -> 대화명 설정으로 인해 일단 제외.
		f.setBounds(100, 100, 500, 500);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		finally {
//			try {
//				if(socket!=null)socket.close();
//			}catch(Exception e2) {};
//		}
		sendMessage();
		System.out.println("main 메소드가 종료됩니다.");
	}
	
	//메세지를 전송하는 메소드
	public void sendMessage() {
		//전송할 문자열
		String msg = tf_msg.getText();
		try {
			//필드에 있는 BufferedWriter 객체의 참조값을 이용해서 서버에 문자열 출력하기
			bw.write(chatName+" : "+msg);
			bw.newLine(); //개행기호도 출력 (서버에서 줄단위로 읽어낼 예정)
			bw.flush();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		tf_msg.setText("");
	}
	
	//서버에서 불특정 시점에 도착하는 메세지를 받을 스레드
	public class ClientThread extends Thread {
		
		@Override
		public void run() {
			try {
				//내부 클래스에서 외부 클래스의 필드나 메소드를 자기것처럼 사용할 수 있다.(장점!)
				//서버로 부터 입력받을 수 있는 객체의 참조값 얻어오기
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				while(true) {
					//다른 스레드(작업단위)를 통해 무한루프를 돌면서 서버로부터 문자열이 전송되는지 감시한다.
					String msg = br.readLine();
					//JTextArea 에 출력하기
					area.append(msg);
					area.append("\r\n");
					//최근 추가된 글 내용이 보일 수 있도록
					int docLength = area.getDocument().getLength();
					area.setCaretPosition(docLength);
					//접속이 끊겼을 때 메세지 응답 대기 무한루프를 탈출하기
					if(msg == null) {
						break;
					}
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER) {
			sendMessage();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
