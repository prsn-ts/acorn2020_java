package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemoDto;
import test.util.DBConnect;

public class MemoDao {
	//필드
	private static MemoDao dao;
	
	//외부에서 객체 생성하지 못하도록 한다.
	private MemoDao() {}
	
	public static MemoDao getInstance() {
		if(dao == null) {
			dao = new MemoDao();
		}
		return dao;
	}
	
	//DB에서 목록 불러오기.
	public List<MemoDto> getList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemoDto> list = new ArrayList<MemoDto>();
		conn = new DBConnect().getConn();
		String sql = "SELECT * FROM MEMO";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoDto dto = new MemoDto();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//객체를 사용했던 순서 역순으로 닫아준다.
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		
		return list;
		
	}
	
	//DB에 데이터 삽입.
	public boolean insert(String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = new DBConnect().getConn();
		int flag = 0;
		String sql = "INSERT INTO memo(num, content, regdate)"
				+ " VALUES(memo_seq.NEXTVAL, ?, SYSDATE)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			flag = pstmt.executeUpdate();
			System.out.println("메모 정보를 추가 했습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag > 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public Boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		conn = new DBConnect().getConn();
		int flag = 0;
		String sql = "DELETE FROM MEMO WHERE num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			flag = pstmt.executeUpdate();
			System.out.println("메모 정보를 삭제 했습니다.");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//객체를 사용했던 순서 역순으로 닫아준다.
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}
		}if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//메모 정보를 DB 에서 수정하는 메소드
	public boolean update(MemoDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int flag=0;
		try {
			conn=new DBConnect().getConn();
			String sql="UPDATE memo"
					+ " SET content=?"
					+ " WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getNum());
			//update 된 row 의 갯수가 반환 된다. 
			flag=pstmt.executeUpdate();
			System.out.println("메모 정보를 수정했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		if(flag > 0) {
			return true;
		}else {
			return false;
		}
	}	
}
