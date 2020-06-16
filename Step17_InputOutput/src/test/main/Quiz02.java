package test.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Quiz02 extends JFrame{
	//생성자
	public Quiz02() {
		setTitle("나의 프레임");
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		JTextField tf_msg = new JTextField(10);
		JTextArea ta_msg = new JTextArea();
		
		//버튼 및 액션커맨드 설정.
		JButton appendBtn = new JButton("버튼");
		appendBtn.setActionCommand("append");
		JButton load = new JButton("load");
		load.setActionCommand("load");
		
		topPanel.add(tf_msg);
		topPanel.add(appendBtn);
		topPanel.add(load);
		add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Color.YELLOW);
		add(ta_msg, FlowLayout.LEFT);
		
		
		
		appendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//1. JTextField 에 입력한 문자열을 읽어와서
				String str = tf_msg.getText();
				//2. memo.txt 파일에 저장하기
				try {	
					//문자열을 저장할 파일을 만들기위한 File 객체
					File memoFile = new File("c:/acorn2020/myFolder/memo.txt");
					
					//실제로 파일이 존재하는 지 여부
					boolean isExist = memoFile.exists();
					if(!isExist) {
						//파일을 실제로 만든다.
						memoFile.createNewFile();
					}
					//파일에 문자열을 출력할 객체
					FileWriter fw = new FileWriter(memoFile, true);
					fw.write("하나");
					fw.write("\r\n"); //개행기호
					fw.write("둘");
					fw.write("\r\n");
					fw.write("셋");
					fw.append(str); //append 메소드를 이용해서 파일에 텍스트에 입력했던 값을 저장한다.
					fw.flush();
					fw.close(); // .close() 시점에 파일이 만들어진다.
					System.out.println("파일에 문자열을 저장했습니다.");
				}catch(IOException ie) {
					ie.printStackTrace();
					}
			}
		});
		
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JTextArea를 정리해주는 부분
				ta_msg.setText(null);
				
				File memoFile = new File("c:/acorn2020/myFolder/memo.txt");
				try {
					if(!memoFile.exists()) {
						System.out.println("파일이 존재하지 않습니다");
						return; //메소드 끝내기
					}
					//파일에서 문자열을 읽어들일 객체
					FileReader fr = new FileReader(memoFile);
					BufferedReader br = new BufferedReader(fr);
					while(true) {
						//반복문 돌면서 문자열을 줄단위로(개행기호 기준) 읽어낸다.
						String line = br.readLine(); // readLine 메소드가 개행기호는 읽지않는다.
						if(line == null) { //더이상 읽을 코드가 없으면
							break; //반복문 탈출
						}
						//JTextArea에 파일 내용 출력
						ta_msg.append(line);
						ta_msg.append("\r\n");
					}
				}catch(IOException ie) {
					ie.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		Quiz02 f = new Quiz02();
		f.setBounds(100, 100, 500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
