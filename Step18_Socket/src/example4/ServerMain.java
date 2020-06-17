package example4;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
	//필드
	static List<ServerThread> threadList = new ArrayList<>();
	
	public static void main(String[] args) {
		//필요한 객체를 저장할 지역변수 미리 만들기
		ServerSocket serverSocket = null;
		try {
			//5000번 통신 port 을 열고 클라이언트의 접속을 기다린다.
			serverSocket = new ServerSocket(5000);
			
			/*
			 *  accept() 메소드는 클라이언트가 실제 접속을 할때 까지 리턴하지 않고
			 *  블록킹 되는 메소드이다.
			 *  클라이언트가 접속을 해오면 Socket 객체의 참조값을 반환하면서 리턴된다.
			 */
			while(true) {
				System.out.println("클라이언트의 Socket 연결 요청을 대기합니다.");
				//서버소켓에서 클라이언트 소켓들을 받아들이고 받아들인 소켓 정보를 socket 지역변수에 저장한다.
				Socket socket = serverSocket.accept();
				//socket 지역변수에 참조값
				System.out.println("클라이언트가 접속을 했습니다.");
				String clientIp = socket.getInetAddress().getHostAddress();
				ServerThread thread = new ServerThread(socket);
				thread.start();
				//생성하고 시작한 스레드의 참조값을 List 에 저장하기
				threadList.add(thread);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(serverSocket != null)serverSocket.close();
				
			}catch(Exception e) {}
		}
	}
	//내부 클래스로 스레드 객체를 생성할 클래스를 정의한다.
	public static class ServerThread extends Thread{
		//필드
		Socket socket;
		//클라이언트에게 출력할 문자열 객체
		BufferedWriter bw;
		
		//생성자의 인자로  Socket 객체를 전달받도록 한다.
		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		//인자로 전달된 문자열을 Socket 을 통해서 출력하는 메소드
		public void sendMessage(String msg) throws IOException {
			//반복문 돌면서 모든 스레드의 BufferedWriter 객체를 이용해서
			//문자열을 전송한다.
			for(ServerThread tmp: threadList) {
				bw.write(msg); //문자열 출력
				bw.newLine(); //개행기호 출력
				bw.flush(); //방출
			}
		}
		
		//새로운 작업 단위가 시작되는 run() 메소드
		@Override
		public void run() {
			try {
				String clientIp = socket.getInetAddress().getHostAddress();
				System.out.println("접속한 클라이언트의 아이피: "+clientIp);
				//클라이언트로 부터 읽어들일 (Input) 객체의 참조값 얻어오기
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				//클라이언트에게 출력할 수 있는 객체
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				//BufferedWriter 객체의 참조값을 필드에 저장하기
				bw = new BufferedWriter(osw);
				while(true) {
					//클라이언트가 전송한 문자열 한줄 읽어들이기
					//클라이언트의 접속이 끊기면 Exception 이 발생하면서
					//catch 블럭으로 실행순서가 이동하면서 이 스레드는 종료 된다.
					/*
					 *  클라이언트가 문자열을 한줄 (개행기호와 함께) 보내면
					 *  readLine () 메소드가 리턴하면서 보낸 문자열을 가지고 온다.
					 *  보내지 않으면 계속 블로킹 되어서 대기하고 있다가
					 *  접속이 끊기면 Exception 이 발생하거나 혹은 null 이 리턴된다.
					 *  따라서 null이 리턴되면 반복문을 빠져 나가게 break 문을 만나도록 한다.
					 *  실행순서가 try 블럭을 벗어나면 run() 메소드가 리턴하게 되고
					 *  run() 메소드가 리턴되면 해당 스레드는 종료가 된다.
					 */
					String msg = br.readLine();
					System.out.println("메세지:"+msg);
					//클라이언트에게 동일한 메세지를 보내는 메소드를 호출한다.
					sendMessage(msg);
					if(msg==null) { //클라이언트의 접속이 끊겼기 대문에
						break; //반복문(while)을 빠져나오도록 한다.
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (Exception e) {
					
				}
			}
			
		}
	}
}
