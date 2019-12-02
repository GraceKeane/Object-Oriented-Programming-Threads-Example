//Grace Keane
//Software Development
package ie.gmit.dip;
import java.io.IOException;

public class SimpleThreadExample {
	
	public void go() {
		for(int i = 10; i < 20; i++) {
			new Thread(new Task(i), "T-" + i).start(); //Hollywood Principle
		}
	}
	
	private class Task implements Runnable{
		private int value;
		
		private Task(int number) {
			this.value = number;
		}
		
		public void run() {
			int counter = 0;
			while(counter < value) {
				
				if(counter < 15) {
					Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
					System.out.println(Thread.currentThread().getName() + " is running." );
				}else {
					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
				}
				
				try {
					Thread.sleep(1000); //Let sleeping threads lie
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter++;
			}
			
			System.out.println("Task " + value + " has finished." );
		}
	}
	
	public static void main(String[] args) throws IOException {
		int i = System.in.read();//Blocks
		new SimpleThreadExample().go();
	}
}
