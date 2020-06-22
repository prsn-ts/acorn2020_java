package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemberDto;
import test.util.DBConnect;

/*
 *  DAO(Data Access Object) 의 약자
 *  
 *  - 만드는 방법
 *  
 *  1. 외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private 로 지정.
 *  2. 자신의 참조값을 저장할 수 있는 필드를 private로 선언.
 *  3. 자신의 참조값을 오직 하나만 생성해서 리턴해주는 static 메소드 만들기
 *  4. 나머지 기능(select, insert, update, delete)들은 non static 으로 만들기
 */
public class MemberDao {
	//자신의 참조값을 저장할 private 필드
	private static MemberDao dao;
	
	//외부에서 객체 생성하지 못하도록 한다.
	private MemberDao() {}
	
	//참조값을 리턴해주는 메소드(생성자가 private라서 new 키워드를 이용해서 객체 생성을 못하기 때문에 메소드를 통해 객체 생성)
	public static MemberDao getInstance() {
		if(dao == null) {//최초 호출시에는 null 이므로
			dao = new MemberDao();//한번 객체를 생성해서 static 필드에 담는다. 이후에는 dao 필드에 들어간 참조값을 재활용한다.
		}
		return dao;
	}
	
	//회원 한명의 정보를 리턴해주는 메소드
	public MemberDto getData(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto dto = null;
		conn = new DBConnect().getConn();
		String sql = "SELECT num, name, addr"
				+ " FROM member WHERE num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			//반복문 돌면서 select 된 회원정보 읽어오기
			while(rs.next()) {
				//MemberDto 객체 생성해서
				dto = new MemberDto();
				//회원 한명의 정보를 담는다.
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		return dto;
	}
	//회원 목록을 리턴해주는 메소드
	public List<MemberDto> getList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> list = new ArrayList<>();
		conn = new DBConnect().getConn();
		String sql = "SELECT * FROM member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//MemberDto 타입의 dto 변수에 num, name, addr의 값을 세팅.
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//list에 dto 변수 정보를 넣기.
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//회원 한명의 정보를 DB 에서 삭제하는 메소드
	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = new DBConnect().getConn();
			String sql = "DELETE FROM member"
					+ " WHERE num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			System.out.println("회원 정보가 삭제 되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
	}
	
	//회원 정보를 DB 에 저장하는 메소드
	public void insert(MemberDto dto) {
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
	//회원 정보를 DB 에서 수정하는 메소드
	public void update(MemberDto dto) {
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
