package test.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Quiz03 extends JFrame implements ActionListener{
	//필드
	JTextArea area; // 메뉴 아이템 new를 눌렀을 때 나와야할 JTextArea의 공간 설정을 위한 필드
	
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
			while(true) {
				Scanner scan = new Scanner(System.in);
				String str = scan.nextLine();
				if(str==null) { //더이상 읽을 코드가 없으면
					break; //반복문 탈출
				}
				area.append(str);
			}
			
			
		}else if(command.equals("open")) {
			
		}
//		}else if(command.equals("save")) {
//			JOptionPane msg = new JOptionPane();
//			int select = msg.showConfirmDialog(this, "저장하시겠습니까?");
//			if(select == msg.YES_OPTION) {
//				File memoFile = new File("c:/acorn2020/myFolder/Memo.txt");
//				try {
//					FileWriter fw = new FileWriter(memoFile);
//					fw.
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}

			
	}
		
}
