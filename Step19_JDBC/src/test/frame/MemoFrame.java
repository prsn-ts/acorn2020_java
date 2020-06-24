package test.frame;
/*
 *  CREATE TABLE MEMO(
 *	num NUMBER PRIMARY KEY,
 *	content VARCHAR2(3),
 *	regdate DATE);
 *	
 *	CREATE SEQUENCE MEMO_SEQ;
 *
 *	위와 같이 테이블과 시퀀스를 만들고 해당 테이블에 데이터를
 *	SELECT, INSERT, UPDATE, DELETE 기능을 수행할 수 있는  MemoFrame 을 만들어 보세요
 *	
 *	조건
 *	1. num 칼럼은 시퀀스를 이용해서 넣으세요.
 *	2. regdate 칼럼(등록일)의 값은 SYSDATE 를 이용해서 넣으세요.
 *	3. 수정은 content 만 수정 가능하게 하세요.
 *	4. MemoDto, MemoDao 를 만들어서 프로그래밍 하세요.
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import test.dao.MemoDao;
import test.dto.MemberDto;
import test.dto.MemoDto;

public class MemoFrame extends JFrame implements ActionListener, PropertyChangeListener{
	//필드
	JTextField tf_content;
	DefaultTableModel model;
	JTable table;
	
	//생성자
	public MemoFrame() {
		setLayout(new BorderLayout());
		
		//패널에 입력창, 추가버튼, 삭제버튼 생성.
		JPanel panel = new JPanel();
		tf_content = new JTextField(20);
		JButton addBtn = new JButton("추가");
		JButton removeBtn = new JButton("삭제");
		panel.add(tf_content);
		panel.add(addBtn);
		panel.add(removeBtn);
		
		//패널을 화면에 배치.
		add(panel, BorderLayout.NORTH);
		
		//JTable 만들기
		table = new JTable();
		//테이블의 칼럼명을 위한 String[] 선언.
		String[] columnNames = {"순서", "할일", "날짜"};
		//model 만들기
		model = new DefaultTableModel(columnNames, 0) {
			//인자로 전달되는 행(row), 열(column) 수정 가능 여부를 리턴하는 메소드  
			@Override
			public boolean isCellEditable(int row, int column) {
				//만일 첫번째(0번째 or 2번째) 칼럼이면 수정이 불가 하도록 한다.
				if(column==0 || column==2) {
					return false;
				}
				return true;
			}
		};
		//테이블에 모델 배치.
		table.setModel(model);
		
		//스크롤이 가능하게
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		//테이블에 회원 목록 출력하기
		displayContent();
		
		//추가버튼 및 삭제버튼의 액션 커맨드 지정.
		addBtn.setActionCommand("add");
		removeBtn.setActionCommand("delete");
		//버튼들의 액션 리스너 지정.
		addBtn.addActionListener(this);
		removeBtn.addActionListener(this);
		//테이블에서 발생하는 이벤트 리스너 등록 하기
		table.addPropertyChangeListener(this);
	}
	
	public static void main(String[] args) {
		MemoFrame f = new MemoFrame();
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setBounds(100, 100, 800, 500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("add")) {
			//입력한 할일을 가져온다.
			String text = tf_content.getText();
			//MemoDto 객체를 만든 후 할일을 dto에 추가한다.
			MemoDto dto = new MemoDto();
			dto.setContent(text);
			//dto에 있는 정보들을 DB에 저장한다.
			MemoDao dao = MemoDao.getInstance();
			boolean isSuccess = dao.insert(dto.getContent());
			
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, text+" 메모 추가 했습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "추가 실패!");
			}
			//DB에 저장된 값을 화면에 출력하기.
			displayContent();
		}else if(command.equals("delete")) {
			int row = table.getSelectedRow();
			int column = table.getSelectedColumn();
			if(row == -1) { //선택한 셀이 없을 때
				JOptionPane.showMessageDialog(this, "셀을 선택해주세요!");
				return;
			}else {
				int msg = JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?");
				if(msg != JOptionPane.YES_OPTION) {
					return;
				}else {
					//선택한 행의 값 얻어내기
					int num = (int) model.getValueAt(row, 0);
					//MemoDao를 통해 DB에서 값을 삭제.
					MemoDao dao = MemoDao.getInstance();
					dao.delete(num);
					//바뀐 DB의 정보로 다시 출력.
					displayContent();
				}
			}
			
			
		}
			
	}
	
	public void displayContent() {
		//run 실행시 있던 행들을 모두 삭제(DB에 회원이 추가되면 그 정보를 포함해서 다시 표현하기 위함.)
		model.setRowCount(0);
		MemoDao dao = MemoDao.getInstance();
		List<MemoDto> list = dao.getList();
		for(int i=0; i<list.size(); i++) {
			MemoDto tmp = list.get(i);
			Object[] rowData = {tmp.getNum(), tmp.getContent(), tmp.getRegdate()};
			model.addRow(rowData);
		}
	}
	//현재 테이블 cell을 수정중인지 여부를 저장할 필드 
	boolean isEditing=false;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("property change!");
		System.out.println(evt.getPropertyName());
		if(evt.getPropertyName().equals("tableCellEditor")) {
			if(isEditing) {//수정중일때 
				//변화된 값을 읽어와서 DB 에 반영한다. 
				//수정된 칼럼에 있는 row  전체의 값을 읽어온다. 
				int selectedIndex=table.getSelectedRow();
				int num=(int)model.getValueAt(selectedIndex, 0);
				String content=(String)model.getValueAt(selectedIndex, 1);
				//수정할 회원의 정보를 MemoDto 객체에 담고 
				MemoDto dto=new MemoDto(num, content, null);
				//DB에 저장하기 
				MemoDao.getInstance().update(dto);
				isEditing=false;//수정중이 아니라고 표시한다.
			}
			isEditing=true;//수정중이라 표시한다.
		}
	}	
}

