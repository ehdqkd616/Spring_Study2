import java.util.Random;

class Horse2 implements Runnable{

	String name;
	private int sleepTime;
	private final static Random g = new Random();
	
	public Horse2(String name) {
		this.name = name;
		sleepTime = g.nextInt(3000);
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name+"말이 경주를 완료 하였습니다.");
	}
	
}

public class Horse {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Horse2("1번"));
		Thread t2 = new Thread(new Horse2("2번"));
		Thread t3 = new Thread(new Horse2("3번"));
		t1.start();
		t2.start();
		t3.start();
	}

}
