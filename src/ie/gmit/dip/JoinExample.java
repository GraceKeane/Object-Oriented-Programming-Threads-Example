//Grace Keane
//Software Development
//Basic lab to show the use of threads and thread joins in Object Oriented Programming 
package ie.gmit.dip;

public class JoinExample {
	
	public void go() throws Exception{
		Thread t1 = new Thread(new Task(), "T - 1");
		Thread t2 = new Thread(new Task(), "T - 2");
		Thread t3 = new Thread(new Task(), "T - 3");
		
		t1.start(); //Hollywood Principle
		t1.join(1000); //Wait 1 second
		
		t2.start();
		t1.join(); //Wait until the thread is dead
		
		t3.start();
		
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println("All threads are now dead... The VM will exit.");
	
	}
	
	private class Task implements Runnable{
		public void run() {
			System.out.println("Thread started: " +  Thread.currentThread().getName());
			
			try {
				Thread.sleep(4000); //Let sleeping threads lie
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Thread ended " + Thread.currentThread().getName());
		}
	}
	
	public static void main(String [] args) throws Exception{
		new JoinExample().go();
	}
		
}
