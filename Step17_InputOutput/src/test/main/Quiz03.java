package test.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import javafx.stage.FileChooser;

public class Quiz03 extends JFrame implements ActionListener{
	//필드
	JTextArea area; // 메뉴 아이템 new를 눌렀을 때 나와야할 JTextArea의 공간 설정을 위한 필드
	JOptionPane msg; //메세지 관련 메소드를 쓰기 위한 필드 선언.
	File memoFile; // 파일 관련 처리를 하기위한 필드 선언.
	
	//생성자
	public Quiz03() {
		//프레임의 제목 설정
		setTitle("나의 파일");
		//프레임의 레이아웃 지정
		setLayout(new BorderLayout());
		//메뉴 아이템 3개 만들기
		JMenuItem item_new = new JMenuItem("New");
		JMenuItem item_open = new JMenuItem("Open");
		JMenuItem item_save = new JMenuItem("Save");
		//메뉴 아이템들을 구분하기 위한 액션 커맨드 지정.
		item_new.setActionCommand("new");
		item_open.setActionCommand("open");
		item_save.setActionCommand("save");
		
		//메뉴에 아이템 추가
		JMenu menu1 = new JMenu("File");
		menu1.add(item_new);
		menu1.add(item_save);
		menu1.add(item_open);
		
		JMenu menu2 = new JMenu();
		menu2.setText("도움말");
		
		//메뉴바에 메뉴 추가
		JMenuBar mb = new JMenuBar();
		mb.add(menu1);
		mb.add(menu2);
		//프레임에 메뉴바 장착
		setJMenuBar(mb);
		
		//텍스트 area 를 프레임의 가운데에 배치
		area = new JTextArea();
		add(area, BorderLayout.CENTER);
		area.setBackground(Color.LIGHT_GRAY);
		area.setVisible(false);
		
		//메뉴 아이템 클릭시 이벤트 리스너 발동
		item_new.addActionListener(this);
		item_open.addActionListener(this);
		item_save.addActionListener(this);
		
	}
	//main 메소드
	public static void main(String[] args) {
		//프레임을 만들어서 화면에 띄우는 작업을 한다.
		Quiz03 f = new Quiz03();
		f.setBounds(100, 100, 500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	//이벤트리스너 발생시 실행부분
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("new")) {
			area.setText("");
			area.setVisible(true);
			area.grabFocus(); //new 메뉴아이템을 텍스트창을 열면 커서가 깜박이면서 포커스를 잡는다.
		}else if(command.equals("open")) {
			area.setText("");
			openAction();
		}else if(command.equals("save")) {
			saveAction();
		}
	}
	
	public void openAction() {
		try {
			JFileChooser fc = new JFileChooser("c:/");
			int result = fc.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				//open 할 예정인 파일 객체의 참조값 얻어오기
				File file = fc.getSelectedFile();
				FileReader fr = new FileReader(file);
				BufferedReader br=new BufferedReader(fr);
				while(true) {
					String line = br.readLine();
					if(line == null) { // 더이상 읽을 문자열이 없다면
						break; //반복문 탈출
					}
					//JTextArea 에 문자열을 개행기호와 함께 append (누적 출력) 하기
					area.append(line);
					area.append("\r\n");
				}
				//JtextArea 가 화면에 보이도록 설정.
				area.setVisible(true);
				br.close();
			}
		}catch(FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		}catch(IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
	}
	
	public void saveAction() {
		msg = new JOptionPane();
		int select = msg.showConfirmDialog(this, "저장하시겠습니까?");
		if(select == msg.YES_OPTION) {
			JFileChooser fc = new JFileChooser("c:/");
			int result = fc.showSaveDialog(this);
			try {
				if(result == JFileChooser.APPROVE_OPTION) {
					String content = area.getText();
					//새로 만들 예정인 File 객체 의 참조값 얻어오기 
					File file =fc.getSelectedFile();
					FileWriter fr = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fr);
					bw.write(content);
					bw.flush();
					bw.close(); //버퍼 사용 후 종료.
					msg.showMessageDialog(this, "저장되었습니다."); //저장 후 확인메세지 출력
					area.setText(""); //저장 후에 입력했던 내용 삭제
				}	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}    //파일명과 같은 파일명이 존재할시 덧붙여쓸여부판단
		}else if(select == msg.NO_OPTION) {
			msg.showMessageDialog(this, "취소되었습니다");
		}
	}
		
}