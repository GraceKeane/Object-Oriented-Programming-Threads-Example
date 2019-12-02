//Grace Keane
//Implementing object lock and showing how dangerous it can be
package ie.gmit.dip;

public class ObjectLockSyncExample {
	private volatile boolean keepRunning = true;
	
	public void go() {
		//Anonymous inner class
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while(keepRunning) {
					synchronized (ObjectLockSyncExample.this) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ObjectLockSyncExample.this.request("T - 1");
					}
				}
			}
		});
		
		//Anonymous inner class
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				ObjectLockSyncExample.this.request("T - 2");
				keepRunning = false; //set in main memory
		}	
	});
	
	t1.start();
	t2.start();
}
	
	private synchronized void request(String thread) {
		System.out.println("Thread " + thread + " making request()");
	}
	
	public static void main(String[] args) {
		new ObjectLockSyncExample().go();
	}
}
