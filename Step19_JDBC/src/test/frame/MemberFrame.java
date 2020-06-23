package test.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import test.dao.MemberDao;
import test.dto.MemberDto;


public class MemberFrame extends JFrame implements ActionListener{
	//여러곳에서 사용할 필드
	JTextField inputName, inputAddr;
	DefaultTableModel model; //테이블에 연결하여 사용할 모델 객체
	JTable table; //표 형식으로 나타내기위해서 테이블 객체 사용.
	
	//생성자
	public MemberFrame() {
		//프레임의 레이아웃 세팅.
		setLayout(new BorderLayout());
		
		//이름을 저장할 inputName 필드 선언
		JLabel label1 = new JLabel("이름");
		inputName = new JTextField(10);
		
		//주소를 저장할 inputAddr 필드 선언.
		JLabel label2 = new JLabel("주소");
		inputAddr = new JTextField(10);
		
		//버튼 객체 생성 후 저장 버튼 추가, 액션 커맨드 지정, 액션 리스너 지정.
		JButton saveBtn = new JButton("저장");
		saveBtn.setActionCommand("save");
		saveBtn.addActionListener(this);
		
		//버튼 객체 생성 후 삭제 버튼 추가, 액션 커맨드 지정, 액션 리스너 지정.
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(this);
		
		//패널에 JLabel, JTextfield, JButton 등을 추가함.
		JPanel panel = new JPanel();
		panel.add(label1);
		panel.add(inputName);
		panel.add(label2);
		panel.add(inputAddr);
		panel.add(saveBtn);
		panel.add(deleteBtn);
		
		//프레임에 패널을 배치.
		add(panel, BorderLayout.NORTH);
		
		//표 형식으로 정보를 출력하기 위한 JTable
		table = new JTable();
		//칼럼명을 String[] 에 순서대로 준비
		String[] colNames = {"번호", "이름", "주소"};
		//테이블에 출력할 정보를 가지고 있는 모델 객체 (칼럼명, row 개수)
		model = new DefaultTableModel(colNames, 0);
		//모델을 테이블에 연결한다.
		table.setModel(model);
		//스크롤이 가능하도록 테이블을 JScrollPane 에 감싼다.
		JScrollPane scroll = new JScrollPane(table);
		//JScrollPane 을 프레임의 가운데에 배치하기
		add(scroll, BorderLayout.CENTER);
		
		//run 했을 때 DB에 있는 정보 가져오는 메소드
		displayMember();
		
		//JTable 에 sample 데이터 출력해보기
//		Object[] row1 = {1, "김구라", "노량진"};
//		Object[] row2 = {2, "해골", "행신동"};
//		model.addRow(row1);
//		model.addRow(row2);
	}
	
	//main 메소드
	public static void main(String[] args) {
		MemberFrame f = new MemberFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 800, 500);
		f.setVisible(true);
	}
	
	//run 했을 때 DB에 있는 정보 가져오기/회원정보 추가한 다음 회원정보 전체보기
	public void displayMember() {
		MemberDao dao = MemberDao.getInstance();
		List<MemberDto> list = dao.getList();
		for(int i=0; i<list.size(); i++) {
			MemberDto tmp = list.get(i);
			Object[] row = {tmp.getNum(), tmp.getName(),
					tmp.getAddr()};
			model.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//액션 command 읽어오기
		String command = e.getActionCommand();
		if(command.equals("save")) {
			//run 실행시 있던 행들을 모두 삭제(DB에 회원이 추가되면 그 정보를 포함해서 다시 표현하기 위함.)
			model.setRowCount(0);
			//입력한 문자열을 읽어와서
			String name = inputName.getText();
			String addr = inputAddr.getText();
			//MemberDto 객체에 담아서
			MemberDto dto = new MemberDto();
			dto.setName(name);
			dto.setAddr(addr);
			//MemberDao 객체를 이용해서 DB에 저장
			MemberDao dao = MemberDao.getInstance();
			boolean isSuccess = dao.insert(dto);
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, name+" 님의 정보 추가 했습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "추가 실패했습니다!");
			}
			//회원정보 추가한 후 변경된 회원정보 전체출력 해주는 메소드
			displayMember();
		}else if(command.equals("delete")) {
			//1. 선택된 row 인덱스를 읽어온다.
			int selectedIndex = table.getSelectedRow(); //선택된 행이 없을 경우에 getSelectedRow 메소드에서 -1를 반환.
			if(selectedIndex == -1) {
				return; //행을 선택하지 않은 경우에 메소드를 그냥 리턴.
			}
			//2. 선택된 row 의 첫번째(0번째) 칼럼의 숫자를 읽어온다 (삭제할 회원의 번호)
			int num = (int)model.getValueAt(selectedIndex, 0);
			//3. MemberDao 객체를 이용해서 해당 회원의 정보를 삭제한다.
			MemberDao dao = MemberDao.getInstance();
			dao.delete(num);
			//run 실행시 있던 행들을 모두 삭제(DB에 회원이 삭제되면 그 정보를 포함해서 다시 표현하기 위함.)
			model.setRowCount(0);
			//4. table 에 회원목록 다시 출력하기
			displayMember();
		}
	}
}
