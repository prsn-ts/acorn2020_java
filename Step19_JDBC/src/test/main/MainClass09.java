package test.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import test.dto.MemberDto;
import test.util.DBConnect;

public class MainClass09 {
	public static void main(String[] args) {
		//수정할 회원의 정보
		int num = 1;
		String name = "이정호";
		String addr = "독산동";
		MemberDto member = new MemberDto();
		member.setNum(num);
		member.setName(name);
		member.setAddr(addr);
		MainClass09.update(member);
	}
	//인자로 MemberDto 객체를 전달 받아서 DB에 수정 작업을 하는 메소드
	public static void update(MemberDto dto) {
		//DB 연결할 수 있는 객체 생성.
		Connection conn = new DBConnect().getConn();
		//sql 구문 세팅을 위한 pstmt 변수 선언.
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE member SET"
				+ "  name = ?, addr = ?" 
				+ " WHERE num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			pstmt.executeUpdate();
			System.out.println("회원 정보를 수정했습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
	}
}
