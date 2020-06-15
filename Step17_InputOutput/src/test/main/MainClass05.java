package test.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class MainClass05 {
	public static void main(String[] args) {
		PrintStream ps = System.out;
		OutputStream os = ps;
		//2byte 처리 스트림
		OutputStreamWriter osw = new OutputStreamWriter(os);
		try {
			osw.write("하나");
			osw.write("둘");
			osw.write("셋");
			osw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
