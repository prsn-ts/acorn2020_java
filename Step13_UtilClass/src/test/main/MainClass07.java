package test.main;

import java.util.ArrayList;
import java.util.List;

import test.mypac.MemberDto;

public class MainClass07 {
	public static void main(String[] args) {
		//default 생성자를 이용해서 객체를 생성한 경우
		MemberDto m1 = new MemberDto();
		m1.setNum(1);
		m1.setName("김구라");
		m1.setAddr("노량진");
		
		//인자로 필드에 저장할 생성자를 이용해서 객체를 생성한 경우
		MemberDto m2 = new MemberDto(2, "해골", "행신동");
		
		//위에서 생성한 객체의 참조값을 List 객체에 담아 보세요.
		List<MemberDto> Plog = new ArrayList<>();
		Plog.add(m1);
		Plog.add(m2);
		
		/*
		 * for 문을 이용해서 List 객체에 담긴 MemberDto 객체를 순서대로 참조해서
		 * 아래와 같은 형식으로 출력해 보세요.
		 * 
		 * 1 | 김구라 | 노량진
		 * 2 | 해골 | 행신동
		 * .
		 * .
		 */
		for(int i=0; i<Plog.size(); i++) {
			System.out.println(Plog.get(i).getNum()+" | "+Plog.get(i).getName()+" | "+Plog.get(i).getAddr());
		}
		for(MemberDto tmp:Plog) {
			System.out.println(tmp.getNum()+" | "+tmp.getName()+" | "+tmp.getAddr());
		}
	}
}
