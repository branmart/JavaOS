import java.util.Observable;
import java.util.Observer;

/**
 * Tells which process to run. I have no idea what im doing.
 * @author Cody Shafer
 *
 */
public class CPU extends Thread implements Observer {

	/**
	 * Holds a reference to the current thread to be ran.
	 */
	private Process current_process;

	/**
	 * A reference to the scheduler.
	 */
	private Scheduler the_scheduler;

	/**
	 * A holder for the Program Counter
	 */
	private int my_pc;


	/**
	 * Used to start up the CPU and create the scheduler. Could be passed a reference to the scheduler.
	 */
	public CPU(){
		the_scheduler = new Scheduler();
		my_pc = 0; //TODO double check
		new SystemTimer(this);
		SystemTimer.setStarted(true);
	}

	/**
	 * Called when the cpu receives an interrupt. Asks the scheduler what
	 * process is next and performs the switch.
	 */
	private void interruptMethod(){
	//	Process next = the_scheduler.nextProcess();
	//	current_process.wait();
	//	current_process = next;
	//	run();
		//TODO might not be needed anymore
	}

	/**
	 * Override of the update method. Is used to be notified when there is an interrupt.
	 * and then ask the scheduler what to do.
	 */
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

	/**
	 * This is used to run the cpu. Runs the current process until interrupted.
	 */
	@Override
	public void run() {		
		while(true){ //TODO not interrupted or observable 
			try {
				int check = current_process.run(my_pc);
				if(check == -1){
					//switch process maybe?
				}
				my_pc += 1;

			} catch (SegmentationException e) {
				// TODO Auto-generated catch block
				//TODO should probably do something useful or something.
				//Should call for switching process
				e.printStackTrace();
			} 
			//if(this.isInterrupted()){
			//	interrupt();
			//}

		} 
		
	}



}