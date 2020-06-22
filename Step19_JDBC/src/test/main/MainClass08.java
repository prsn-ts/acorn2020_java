package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import test.dto.MemberDto;
import test.util.DBConnect;

public class MainClass08 {
	public static void main(String[] args) {
		//추가할 회원의 정보
		String name = "주뎅이";
		String addr = "봉천동";
		//아래의 insert() 메소드를 호출해서 회원 한명의 정보가 추가 되도록 해 보세요.
		
		//추가할 회원의 정보를 member 변수에 담는다.
		MemberDto member = new MemberDto();
		member.setName(name);
		member.setAddr(addr);
		MainClass08.insert(member);
	}
	//MemberDto 객체를 인자로 전달받아서 회원정보를 DB 에 저장하는 메소드
	public static void insert(MemberDto dto) {
		Connection conn = new DBConnect().getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member(num, name, addr)"
				+ " VALUES(member_seq.NEXTVAL, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.executeUpdate();
			System.out.println("회원 정보가 추가되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				
			}
		}
	}
}
