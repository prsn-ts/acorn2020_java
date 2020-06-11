package test.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass11 {
	public static void main(String[] args) {
		Map<String, String> dic = new HashMap<>();
		dic.put("house", "집");
		dic.put("phone", "전화기");
		dic.put("car", "자동차");
		dic.put("pencil", "연필");
		dic.put("dictionary", "사전");
		/*
		 *  검색할 단어를 입력하세요: house
		 *  
		 *  house 의 뜻은 집입니다.
		 */
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 단어를 입력하세요: ");
		String word = scan.nextLine(); //문자열 입력 받기
		//Map 객체에서 입력한 문자열로 저장된 값을 읽어와 보기
		String mean = dic.get(word);
		//Map 객체에 단어가 있는지 존재 여부 확인
		if(mean == null) { //존재하지 않으면
			System.out.println(word+" 는 목록에 없습니다.");
		}else { //if문 실행 안될 시 
			//출력할 문자열 구성하기
			String line = word+" 의 뜻은 "+dic.get(word)+"입니다.";
			//출력하기
			System.out.println(line);
		}
//		System.out.println(word+" 의 뜻은 "+dic.get(word)+"입니다.");
	}
}
