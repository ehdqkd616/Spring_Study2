
public class Test01 extends Thread{
	
	@Override
	public void run() {
		System.out.println("파생 쓰레드 Test01 실행");
		for(int i=11;i<20;i++) {
			System.out.println(i);
		}
	}
}
