import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;

/**
 * 
 * 
 * @author Brandon Martin
 *
 */
public class Scheduler {
	
	/**
	 * The running order for the process. 
	 */
	List<Process> round_robin;
	
	/**
	 * The current position in the list used to gather the next process.
	 */
	int the_current_process;
	
	/**
	 * A queue used to keep track of who gets the io in what order.
	 */
	LinkedList<Integer> io_queue = new LinkedList<Integer>();
	//fix queue
	
	/**
	 * Reference to itself for singleton.
	 */
	private static Scheduler ME = null;
	
	/**
	 * Constructor for singleton scheduler. Takes in the list of process to scheduler
	 * @param the_process The list of processes.
	 */
	public Scheduler(List<Process> the_process){
		round_robin = the_process;
		the_current_process = 0;
	}
	
	//under round robin next process goes thru list under it finds unlocked process.
	
	/**
	 * Used to return the next proccess under round robin rules.
	 * @return The next process. 
	 */
	public Process nextProcess(){
		System.out.println("Switching process");
		System.out.println(the_current_process);
		the_current_process +=1;
		if(the_current_process >= round_robin.size()){
			the_current_process = 0;
		}
		Process next = round_robin.get(the_current_process);
		while(next.getState() != Process.State.WAITING){
			the_current_process +=1;
			if(the_current_process >= round_robin.size()){
				the_current_process = 0;
			}
			next = round_robin.get(the_current_process);
		}
		System.out.println(next);
		return next;
	}
	
	/**
	 * Get Instance for singleton. Can be called after the intial getInstance overloaded is called
	 * @return The reference to the scheduler
	 */
	public static Scheduler getInstance(){
		if(ME == null){
			return getInstance(new ArrayList<Process>());
		} 
		return ME;
	}
	
	/**
	 * Overloaded getInstance method used upon first creation
	 * @param the_process The processes needed to be scheduled
	 * @return The reference to the scheduler
	 */
	public static Scheduler getInstance(List<Process> the_process){
		if(ME == null){
			ME = new Scheduler(the_process);
		} 
		return ME;
	}


	/**
	 * Method used to switch methods based upon an io interrupt.
	 * @param io A cheap way to get an overloaded method.
	 * @return The next process to be ran.
	 */
	public Process nextProcess(boolean io){
		//this process for io 
		if(!io_queue.isEmpty()){
			round_robin.get((io_queue.removeFirst())).setState(Process.State.WAITING);
		}
		return nextProcess();
	}

	/**
	 * Used to add a process to 
	 * @param the_process
	 */
	public void waitIO(Process the_process){
		io_queue.add(round_robin.indexOf(the_process));
	}
	
}

// upon getting passed either io, or timer figure out if needs to switch to blocked for io queue, keepgoing
// or switch something else
	
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//    
//    /**
//     * Queue of processes 
//     */
//    Queue<Process> my_process_q;
//    
//    /**
//     * List of jobs that are running
//     */
//    List <Process> my_run_list;
//    
//    /**
//     * List of jobs that are waiting
//     */
//    List <Process> my_waiting_list;
//    
//    /**
//     * List of jobs that are blocked
//     */
//    List <Process> my_blocked_list;
//
//    private int my_index;
//    
//    //TODO remove flags
//    //TODO make a singleton
//    //TODO implement the method next process for the cpu switching
//    
//    /**
//     * Constructor, doesn't set the type of scheduling.
//     * 
//     * @param process a Queue of processes
//     */
//    public Scheduler(Queue<Process> process) {
//        my_process_q = process;
//    }
//    
//    
//    
//    /**
//     * Tells which process is the next process 
//     * 
//     * @param the_interrupt
//     * @return null if doesnt need to switch
//     */
//    public Process nextProcess(final Observable the_interrupt){
//    	//TODO make this tell which process is next process. if no switching return null;
//    	return null;
//    }
//
//    
//     
//    /**
//     * Sets round robin flag to true and all others to false
//     */
//    public void setRoundRobin() {      
//        roundRobin();
//    }
//    
//    /**
//     * Sets priority flag true and all others to false
//     */
//    public void setPriority() {
//        priority();
//    }
//    
//    /**
//     * Set lottery flag true and all others to false
//     */
//    public void setLottery() {    
//  
//        lottery();
//    }
//    
//    /**
//     * Implementation of lottery
//     * 
//     */
//    private void lottery() {
//        Random rand = new Random();
//        rand.setSeed(System.currentTimeMillis());
//    }
//
//
//    /**
//     * Implementation of Round Robin
//     */
//    private void roundRobin() {
//        Process current_processing_job = my_process_q.element(); //puts current job in current job
//        int num_jobs = my_process_q.size();
//        int curr_time = 0;
//        int count = 0;
//        int quantum = 4;
//
//        /**
//         * makes it run forever
//         */
//        while (true) {
//            if(current_processing_job != null ) {
//                my_waiting_list.add(current_processing_job);
//                current_processing_job = null;
//            }
//            if(current_processing_job == null && my_waiting_list.size() != 0) {
//                current_processing_job = my_waiting_list.get(0);
//                
//            }
//            if(current_processing_job != null) {
//
//            }
//        }       
//    }
//    
//    /**
//     * Implementation of Priority
//     */
//    private void priority() {
//        //TODO insert code
//        
//        //4 priorities
//        
//        
//    }
//
//}
