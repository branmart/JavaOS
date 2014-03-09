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

	SystemTimer the_timer;

	private int my_pc;


	/**
	 * Used to start up the CPU and create the scheduler. Could be passed a reference to the scheduler.
	 */
	public CPU(){
		the_scheduler = new Scheduler();
		startTimer();
		my_pc = 0; //TODO double check
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
		//TODO might not be needed anymore
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Process next = the_scheduler.nextProcess(arg0);
		//stop current process?
		if(next != null){
			current_process.setPc(my_pc);
			current_process = next;
			my_pc = current_process.getPc();
			//TODO run next process again?
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){ //TODO not interrupted or observable 
			try {
				current_process.run(my_pc);

			} catch (SegmentationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	private void startTimer(){
		the_timer = new SystemTimer();
	}

}