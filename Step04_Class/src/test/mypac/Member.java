package test.mypac;

public class Member {
	//non static 필드 정의하기
	//숫자를 저장할 필드
	public int num;
	//이름을 저장할 필드
	public String name;
	//주소를 저장할 필드
	public String addr;
	
	//non static 메소드 정의하기
	public void showInfo() {
		System.out.println(this.num+" | "+this.name+" | "+this.addr);
	}
}
