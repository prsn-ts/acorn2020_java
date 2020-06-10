package test.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainClass09 {
	public static void main(String[] args) {
		/*
		 *  3명의 회원정보(번호, 이름, 주소)를 HashMap 객체에 담고
		 *  
		 *  HashMap 객체의 참조값을 ArrayList 객체에 순서대로 담아 보세요.
		 */
		Map<String, Object> map1 = new HashMap<>();
		map1.put("num", 1);
		map1.put("name", "김구라");
		map1.put("addr", "노량진");
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("num", 2);
		map2.put("name", "해골");
		map2.put("addr", "동물원");
		
		Map<String, Object> map3 = new HashMap<>();
		map3.put("num", 3);
		map3.put("name", "원숭이");
		map3.put("addr", "동물원");
		
		ArrayList<Map<String, Object>> Plog = new ArrayList<>();
		Plog.add(map1);
		Plog.add(map2);
		Plog.add(map3);
		
		// for문으로 저장된 값 출력해보기
		for(int i=0; i<Plog.size(); i++) {
			Map<String, Object> tmp = Plog.get(i);	
			System.out.println(tmp.get("num")+" | "+tmp.get("name")+" | "+tmp.get("addr"));
		}
		
		//참조 연습
		List<Map<String, Object>> a = Plog;
		Map<String, Object> b = Plog.get(0);
		Object c = Plog.get(0).get("num");
		Object d = Plog.get(0).get("name");
		Object e = Plog.get(0).get("addr");
		int f = (int) Plog.get(0).get("num");
		String g = (String)Plog.get(0).get("name");
		String h = (String)Plog.get(0).get("addr");
		
		int i = ((String)Plog.get(0).get("addr")).length();
	}
}
