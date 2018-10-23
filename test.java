package hongyu;

import java.util.Timer;
import java.util.TimerTask;

public class test {

	public static void main(String[] args) {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){

			@Override
			public void run() {
				System.out.println(1);		
			}	
		};

		timer.schedule(task, 0,1000);
		timer.cancel();
		timer = new Timer();
		TimerTask task1 = new TimerTask(){

			@Override
			public void run() {
				System.out.println(2);		
			}	
		};

		timer.schedule(task1, 0,1000);
		

	}

}
