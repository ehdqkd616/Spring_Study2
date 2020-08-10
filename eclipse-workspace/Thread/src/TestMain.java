
//class Countings extends Thread {
//	@Override
//	public void run() {
//		System.out.println("파생 쓰레드1 실행");
//		for(int i=0;i<10;i++) {
//			System.out.println(i);
//		}
//	}
//}

class Countings implements Runnable{
	@Override
	public void run() {
		System.out.println("파생 쓰레드1 실행");
		for(int i=1;i<10;i++) {
			System.out.println(i);
		}
	}
}

class Countings2 implements Runnable{
	@Override
	public void run() {
		System.out.println("파생 쓰레드2 실행");
		for(int i=10;i<20;i++) {
			System.out.println(i);
		}
	}
}

class Countings3 implements Runnable{
	@Override
	public void run() {
		System.out.println("파생 쓰레드3 실행");
		for(int i=20;i<30;i++) {
			System.out.println(i);
		}
	}
}


public class TestMain {

	public static void main(String[] args) {
		Thread t = new Thread(new Countings());
		Thread t2 = new Thread(new Countings2());
		Thread t3 = new Thread(new Countings3());
		Thread test = new Thread(new Test01());
		t.start();
		System.out.println("메인쓰레드 실행");
		t2.start();
		t3.start();
	}

}

//Runnable 을 implements 받는 CountDownThread 클래스 작성
//>> 20 ~ 0 까지 카운트 다운을 출력하는 클래스
//
//Runnable 을 implements 받는 RocketThread 클래스를 작성
//>> 10초 : 안정장치 분리
//      6초 : 발사준비 완료
//      0초 : 발사!!!!!!!!!
//
//==========================================
//출력 예) main을 갖는 RocketTest 작성
//실행 : 
//20
//19
//18
//17
//....
//10
//안전장치 분리
//9
//...
//6
//발사준비 완료
//5
//...
//0
//발사!!!
