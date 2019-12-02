//Grace Keane
//Object Oriented Programming - Volatile Example
//Volatile - Any thread that reads a volatile attribute sees its most recently written value.
//i.e the value is not cached. The volatile keyword is often used to control a loop accessible from 
//multiple threads.  (Shared data between threads).
//Volatile should always be read from and updated in main memory i.e done in Virtual Machine itself
package ie.gmit.dip;

public class VolatileExample implements Runnable {
	private volatile boolean keepRunning = true; //used to keep a thread alive
	
	public void go() throws Exception {
		Thread t = new Thread(this); //A Runnable
		t.start(); //Hollywood Principle
		Thread.sleep(100); //Allowing the thraed to sleep
		
		this.keepRunning = false;
		System.out.println("Variable keepRunning set to false...");
	}

	public void run() {
		long count = 0;
		
		while (keepRunning) {
			count++;
		}
		
		System.out.println("Thread about to die after " + count + " iterations");
	}
	
	public static void main(String [] args) throws Exception {
		new VolatileExample().go();
	}
}
