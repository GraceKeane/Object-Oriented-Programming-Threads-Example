//Grace Keane
//Synchronized keyword prevents concurrent access to a block of code or an object
//by multiple threads. If a method is modified as Synchronized, then only one
//thread as a time can access and finish the method. 
//i.e the method is locked by a thread. This keyword can aso be used to lock attributes,
//objects and classes.

package ie.gmit.dip;

public class SyncDeadlockExample implements Runnable{
	private SyncObject one = new SyncObject("SyncObject 1");
	private SyncObject two = new SyncObject("SyncObject 2");

	public void go() {
		Thread t = new Thread(this);
		t.start();
		
		one.execute(two);
	}
	
	public void run() {
		two.execute(one);
	}
	
	public class SyncObject{
		private String name;
		
		private SyncObject(String name) {
			this.name = name;
		}
		
		//synchronized - When a thread gets this method all other threads are locked
		private synchronized void execute(SyncObject other) {
			System.out.println("Processing execute() on " + name);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("calling finish() on " + other.getName());
			other.finish();
		}
		
		private synchronized void finish() {
			System.out.println("Executing finish() on " + name);
		}
		
		public String getName() {
			return name;
		}

	}
	public static void main(String [] args) {
		new SyncDeadlockExample().go();
	}
}
