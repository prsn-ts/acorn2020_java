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

import javafx.stage.FileChooser;

public class Quiz03 extends JFrame implements ActionListener{
	//필드
	JTextArea area; // 메뉴 아이템 new를 눌렀을 때 나와야할 JTextArea의 공간 설정을 위한 필드
	JOptionPane msg; //메세지 관련 메소드를 쓰기 위한 필드 선언.
	File memoFile; // 파일 관련 처리를 하기위한 필드 선언.
	
	//생성자
	public Quiz03() {
		setTitle("나의 파일");
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
		//메뉴바에 메뉴 추가
		JMenuBar mb = new JMenuBar();
		mb.add(menu1);
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
			area.setVisible(true);
			
		}else if(command.equals("open")) {
//			FileChooser fc = new FileChooser(); 
//	        fc.setTitle("타이틀"); // 창의 제목
//			FileReader fr = new FileReader(memoFile);
//			BufferedReader br=new BufferedReader(fr);
//			while(true) {
//				//반복문 돌면서 문자열을 줄단위로(개행기호 기준) 읽어낸다.
//				String line = br.readLine(); // readLine 메소드가 개행기호는 읽지않는다.
//				if(line==null) { //더이상 읽을 코드가 없으면
//					break; //반복문 탈출
//				}
//				//콘솔창에 개행기호 없이 한글자 한글자 출력하기
//				System.out.println(line);
//			}
			
		}else if(command.equals("save")) {
			msg = new JOptionPane();
			int select = msg.showConfirmDialog(this, "저장하시겠습니까?");
			if(select == msg.YES_OPTION) {
				try {
					memoFile = new File("c:/acorn2020/myFolder/text1.txt");
					//실제로 파일이 존재하는 지 여부
					boolean isExist = memoFile.exists();
					if(!isExist) {
						//파일을 실제로 만든다.
						memoFile.createNewFile();
					}
					//파일에 문자열을 출력할 객체
					FileWriter fw = new FileWriter(memoFile);
					fw.write(area.getText());
					fw.flush();
					fw.close(); // .close() 시점에 파일이 만들어진다.	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    //파일명과 같은 파일명이 존재할시 덧붙여쓸여부판단
				msg.showMessageDialog(this, "저장되었습니다."); //저장 후 확인메세지 출력
				area.setText(""); //저장 후에 입력했던 내용 삭제
			}else if(select == msg.NO_OPTION) {
				msg.showMessageDialog(this, "취소되었습니다");
			}
		}	
	}
		
}
