package test.main;

import java.util.Scanner;

import test.dao.MemberDao;
import test.dto.MemberDto;

public class MainClass12 {
	/*
	 *  Scanner 객체를 이용해서 문자열을 두번 입력 받는다.
	 *  즉 새로 추가할 이름과 주소를 입력 받아서
	 *  MemberDao 객체를 이용해서 DB 에 저장하는 code 를 작성해 보세요.
	 */
	public static void main(String[] args) {
		
		//Scanner 객체를 이용해서 이름과 주소를 문자열로 입력 받기
		Scanner scan = new Scanner(System.in);
		System.out.print("이름을 입력해주세요 : ");
		String name = scan.nextLine();
		System.out.print("주소를 입력해주세요 : ");
		String addr = scan.nextLine();
		
		//이름과 주소를 MemberDto 객체에 저장하기
		MemberDto member = new MemberDto();
		member.setName(name);
		member.setAddr(addr);
		
		//MemberDao 객체의 참조값 얻어오기
		MemberDao dao = MemberDao.getInstance();
		
		//MemberDao 객체의 메소드를 이용해서 DB 에 저장하기
		boolean isSuccess = dao.insert(member); //작업의 성공여부를 알고싶다면 insert()의 리턴값을 불리언으로 받기.
	}
}
