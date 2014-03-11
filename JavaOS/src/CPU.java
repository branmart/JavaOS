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
	private static Scheduler the_scheduler;// = Scheduler.getInstance();

	/**
	 * Used to start up the CPU and create the scheduler. Could be passed a reference to the scheduler.
	 */
	public CPU(){

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
		System.out.println("CPU got updated");
		Process next = null;
		if(arg0.getClass().getName().equals("IODevices")){
			next = the_scheduler.nextProcess(true);
		} else {
			next = the_scheduler.nextProcess();
		}
		//might need to tell scheduler either io or timer
		if(next != null){
			current_process = next;
		} else{
			//do nothing i think.
		}
	}


	public void addScheduler(){
		the_scheduler = Scheduler.getInstance();
	}

	public void startTimer(){
		SystemTimer timer = new SystemTimer(this);
		timer.addObserver(this);
		timer.setStarted(true);
		timer.fireInterrupt();
	}

	//upon io, tell scheduler. may get nothing back


	//TODO fix run method
	/**
	 * This is used to run the cpu. Runs the current process until interrupted.
	 */
	@Override
	public void run() {		

		if(current_process == null){
			current_process = the_scheduler.nextProcess();
		}
		while(true){ //TODO not interrupted or observable 
			try {
				current_process.nextInstruction();
				//System.out.println("Its doing things");
			} catch (SegmentationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//yeah should never get here.
			}
		} 
	}

	/*
	 * *************
	 * System calls*
	 * *************
	 */

	//DOnt need to handle exceptions within reason
	public int readMemory(final Process the_process, final int the_address) throws SegmentationException, MutexLockedException
	{
		//		TODO this is most likely empty memory at the moment.
		return SharedMemory.getInstance().read(the_process, the_address);
	}

	public void writeMemory(final Process the_process, final int the_address, final int the_data) throws SegmentationException, MutexLockedException
	{
		SharedMemory.getInstance().write(the_process, the_address, the_data);
	}

	public void lockMemory(final Process the_process, final int the_address) throws SegmentationException, MutexLockedException
	{
		SharedMemory.getInstance().lock(the_process, the_address);
	}

	public void unlockMemory(final Process the_process, final int the_address) throws SegmentationException, MutexLockedException
	{
		SharedMemory.getInstance().unlock(the_process, the_address);
	}

	public int getInput(final Process the_process) throws NoInputBuffered
	{
		//TODO Should throw some kind of exception so that the process knows to block itself.
		//isn't the noInputBuffered the kind of exception that we need to throw?
		//TODO needs the input to be added to shared memory in order for us to call something
		return 0;
	}


	//TODO io
}