//Grace Keane
//Illustrating another example of deadlock
package ie.gmit.dip;

public class MonitorLockExample {
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void go() {
		//Running into a deadlock situation
		Thread t1 = new Thread(new LockOneTask());
		Thread t2 = new Thread(new LockTwoTask());
		
		//Race condition happening
		//Hollywood Principle
		//Waiting & hanging indefinitely
		t1.start();
		t2.start();
	}
	
	//Calling synchronized methods
	private class LockOneTask implements Runnable{
		public void run() {
			synchronized (lock1) {
				System.out.println("LockOneTask is holding lock1");
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("LockOneTask is waiting for lock2");
				synchronized (lock2) {
					System.out.println("LockOneTask is holding lock1 & lock2");

				}
			}
		}
	}
	
	private class LockTwoTask implements Runnable{
		public void run() {
			synchronized (lock2) {
				System.out.println("LockTwoTask is holding lock2");
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("LockTwoTask is waiting for lock1");
				synchronized (lock1) {
					System.out.println("LockTwoTask is holding lock1 & lock2");

				}
			}
		}
	}
	
	public static void main(String [] args) {
		new MonitorLockExample().go();
	}
}
