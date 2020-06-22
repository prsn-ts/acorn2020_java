package test.main;

import java.util.List;

import test.dao.MemberDao;
import test.dto.MemberDto;

public class MainClass10 {
	public static void main(String[] args) {
		//만일 MemberDao 객체의 참조값이 필요 하다면?
		//MemberDao dao = new MemberDao(); 직접 객체 생성 불가
//		
//		//static 메소드를 이용해서 MemberDao 객체의 참조값을 받아올 수 있다.
//		MemberDao dao = MemberDao.getInstance(); //dao 객체는 한번만 만들어지고 이후에는 하나의 참조값을 재활용하게된다. 계속 참조값이 생기면 메모리사용에 비효율적이기 때문. 
//		//새로 추가할 회원의 정보라면
//		MemberDto dto = new MemberDto();
//		dto.setName("덩어리");
//		dto.setAddr("상도동");
//		//MemberDao 객체의 메소드를 활용해서 저장할 수 있다.
//		dao.insert(dto);
//		
//		MemberDao dao2 = MemberDao.getInstance();
//		//수정할 회원의 정보라면
//		MemberDto dto2 = new MemberDto(1, "김구라", "독산동");
//		dao2.update(dto2);
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.getData(2);
		System.out.println(dto.getNum()+" | "+dto.getName()+" | "+dto.getAddr());
		
		List<MemberDto> list = dao.getList();
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getNum()+" | "+list.get(i).getName()+" | "+list.get(i).getAddr());	
		}
		
	}
}
