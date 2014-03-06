import java.util.Observable;
import java.util.Observer;

/**
 * Tells which process to run. I have no idea what im doing.
 * @author Cody Shafer
 *
 */
public class CPU implements Observer, Runnable {
	
	/**
	 * Holds a reference to the current thread to be ran.
	 */
	Process current_process;
	
	Scheduler the_scheduler;
	
	/**
	 * Used to start up the CPU and create the scheduler. Could be passed a reference to the scheduler.
	 */
	public CPU(){
		the_scheduler = new Scheduler();
	}
	
	/**
	 * This is used to switch Thread.
	 * @param new_thread
	 */
	public void switchThread(Process new_process){
		current_process = new_process;
	}
	
	/**
	 * Called when the cpu receives an interrupt
	 */
	public void interrupt(){
		//Process next = the_scheduler.nextProcess();
		//current_process.wait();
		//current_process = next;
		run();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO 
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//
	}

}