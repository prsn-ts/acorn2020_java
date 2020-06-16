package test.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Quiz01 {
	public static void main(String[] args) {
		/*
		 *  Scanner 객체를 이용해서 문자열 한줄을 입력 받은 다음
		 *  c:/acorn2020/myFolder/quiz.txt 파일을 만들어서
		 *  해당 파일에 문자열을 저장해보세요
		 */
		Scanner scan = new Scanner(System.in);
		System.out.println("저장할 문자열 입력:");
		String str = scan.nextLine();
		
		File memoFile = new File("c:/acorn2020/myFolder/quiz.txt");
		try {
			boolean isExist = memoFile.exists();
			if(isExist == false) {
				memoFile.createNewFile();
			}
			FileWriter fw = new FileWriter(memoFile, true);
			//fw.write(str);
			fw.append(str);
			//fw.flush();
			fw.close(); //자동으로 flush된다.
			System.out.println("문자열 저장 완료");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
