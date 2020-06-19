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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 *  JSON
 *  
 *  - Java Script Object Notation (자바스크립트 객체 표기법을 따르는 문자열, 자바스크립트 객체를 보기쉽게 표기하기위해 사용.)
 *  
 *  - 데이터의 type(JSON에서 지원하는 표기하는 방법 6가지)
 *  1. {}
 *  2. []
 *  3. "xxx"
 *  4. 10 or 10.1
 *  5. true or false
 *  6. null
 *  
 *  - JSON 예제
 *  
 *  {"num":1, "name":"김구라", "isMan":true, "phone" : null} -> {"key": value}, value의 값으로 위에 데이터 타입의 6가지에 한해서 올 수 있다.
 *  ex)
 *  [10, 20, 30, 40, 50]
 *  
 *  ["김구라","해골","원숭이"]
 *  
 *  [{},{},{}]
 *  
 *  {"name":"kim", "friends":["김구라","해골","원숭이"] }
 *  
 *  
 *  메세지의 종류
 *  
 *  1. 일반 대화 메세지 {"type":"msg","name":"김구라", "content":"안녕하세요"} -> 중괄호는 JSONObject로 처리
 *  2. 누군가 입장했다는 메세지 {"type":"enter", "name":"김구라"}
 *  3. 누군가 퇴장했다는 메세지 {"type":"out", "name":"원숭이"}
 *  4. 참여자 목록 메세지 {"type":"members", "list":["김구라","해골","원숭이"]} -> 대괄호는 JSONArray
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
	//참여자 목록
	JList<String> jList;
	
	//생성자
	public ClientMain() {
		
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
		
		//Vector 는 ArrayList 와 같다고 생각하고 사용하면된다.
		//추가 기능(스레드동기화)가 있어서 조금 더 무겁다.
		
		//String[]에 JList 공간 확보를 위해 임시 문자열을 넣는다.
		String[] title = {"참여자 목록"};
		jList=new JList<String>(title);
		jList.setBackground(Color.GREEN);
		
		//패널에 JList 를 배치하고
		JPanel rightPanel = new JPanel();
		rightPanel.add(jList);
		rightPanel.setBackground(Color.green);
		//패널을 프레임의 동쪽에 배치
		add(rightPanel, BorderLayout.EAST);
		
		//대화명을 입력받아서 필드에 저장한다.
		chatName = JOptionPane.showInputDialog(this, "대화명을 입력하세요");
		
		setTitle("대화명:"+chatName);
		
		//서버에 소켓 접속을 한다.
		try {
			//접속이 성공되면 Socket 객체의 참조값이 반환된다.
			//반환되는 객체의 참조값을 필드에 저장해 놓는다.
			socket = new Socket("192.168.0.15", 5000);
			//문자열을 서버에 전송(출력Output) 하기/ 서버에 문자열을 출력할 BufferedWriter 객체의 참조값을 얻어내서 필드에 저장해 놓는다.
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
			//내가 입장한다고 서버에 메세지를 보낸다.
			// "{"type":"enter", "name":"대화명"}" 이런 형식으로 서버에 메세지를 보내기 위함.
//			String msg = "{\"enter\":\""+chatName+"\"}"; //JSON 없이 출력하기 위한 모양(복잡하다..)
			
			//접속이 성공하는 시점인 socket = new Socket("192.168.0.30", 5000); 이 구문 이후에 서버로부터 메세지를 받을 스레드도 시작을 시킨다.
			new ClientThread().start();
			
			//JSON 라이브러리를 이용해서 "{"type":"enter", "name":"대화명"}" 출력.
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("type", "enter");
			jsonObj.put("name", chatName);
			String msg2 = jsonObj.toString();
			
			//BufferedWriter 객체를 이용해서 보내기
			bw.write(msg2);
			bw.newLine(); //한 메세지 찍고 개행하기위한 개행 메소드 호출.
			bw.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
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
			//JSONObject 객체를 생성해서 정보를 구성하고
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("type", "msg");
			jsonObj.put("name", chatName);
			jsonObj.put("content", msg);
			//JSON 문자열을 얻어낸다.
			String json = jsonObj.toString();
			
			//필드에 있는 BufferedWriter 객체의 참조값을 이용해서 서버에 문자열 출력하기
			bw.write(json);
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
					//서버로부터 문자열이 전송되는지 대기한다
					String msg = br.readLine();
					//updateTextArea 메소드를 선언하여 run() 간결화, JSONException 처리하는 try,catch문을 넣어 에러를 처리함.
					updateTextArea(msg);
//					//JTextArea 에 출력하기(다른 출력 메세지가 있어서 이 부분은 제외)
//					area.append(msg);
//					area.append("\r\n");
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
		}// run()
		
		//JTextArea 에 문자열을 출력하는 메소드, JSONObject 예외 발생시에 대비하기 좋은 메소드
		public void updateTextArea(String msg) {
			try {
				JSONObject jsonObj = new JSONObject(msg);
				String type = jsonObj.getString("type");
				if(type.equals("enter")) { //입장 메세지라면
					//누가 입장했는지 읽어낸다
					String name = jsonObj.getString("name");
					area.append("["+name+"] 님이 입장했습니다.");
					area.append("\r\n");
				}else if(type.equals("msg")) { //대화 메세지라면
					//누가
					String name = jsonObj.getString("name");
					//어떤 내용을
					String content = jsonObj.getString("content");
					//출력하기
					area.append(name+" : "+content);
					area.append("\r\n");
				}else if(type.equals("out")) { //퇴장 메세지라면
					//누가
					String name = jsonObj.getString("name");
					//출력하기
					area.append("[[ "+name+" ]] 님이 퇴장했습니다.");
					area.append("\r\n");
				}else if(type.equals("members")) {//대화 참여자 목록이 도착 시
					//list 라는 키값으로 저장된 JSONArray 객체를 얻어온다.
					JSONArray arr = jsonObj.getJSONArray("list");
					//참여자 목록을 저장할 Vector
					Vector<String> list = new Vector<>();
					list.add("참여자 목록");
					//반복문을 돌면서 참여자 목록을 다시 넣어준다. 참여자들을 참여자 목록에 추가시킨다. 
					for(int i=0; i<arr.length(); i++) {
						String tmp = arr.getString(i);
						list.add(tmp);
					}
					//JList에 참여자 목록 연결하기
					jList.setListData(list);
				}
			}catch(JSONException je) {
				je.printStackTrace();
			}
		}
		
	}// class ClientThread
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
