import java.util.Iterator;
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
     * Queue of processes
     */
    Queue<Process> my_process_q;
    
    /**
     * List of jobs that are running
     */
    List <Process> my_run_list;
    
    /**
     * List of jobs that are waiting
     */
    List <Process> my_waiting_list;
    
    /**
     * List of jobs that are blocked
     */
    List <Process> my_blocked_list;

    private int my_index;
    
    //TODO remove flags
    //TODO make a singleton
    //TODO implement the method next process for the cpu switching
    
    /**
     * Constructor, doesn't set the type of scheduling.
     * 
     * @param process a Queue of processes
     */
    public Scheduler(Queue<Process> process) {
        my_process_q = process;
    }
    
    
    
    /**
     * Tells which process is the next process 
     * 
     * @param the_interrupt
     * @return null if doesnt need to switch
     */
    public Process nextProcess(final Observable the_interrupt){
    	//TODO make this tell which process is next process. if no switching return null;
    	return null;
    }

    
     
    /**
     * Sets round robin flag to true and all others to false
     */
    public void setRoundRobin() {      
        roundRobin();
    }
    
    /**
     * Sets priority flag true and all others to false
     */
    public void setPriority() {
        priority();
    }
    
    /**
     * Set lottery flag true and all others to false
     */
    public void setLottery() {    
  
        lottery();
    }
    
    /**
     * Implementation of lottery
     * 
     */
    private void lottery() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }


    /**
     * Implementation of Round Robin
     */
    private void roundRobin() {
        Process current_processing_job = my_process_q.element(); //puts current job in current job
        int num_jobs = my_process_q.size();
        int curr_time = 0;
        int count = 0;
        int quantum = 4;

        /**
         * makes it run forever
         */
        while (true) {
            if(current_processing_job != null ) {
                my_waiting_list.add(current_processing_job);
                current_processing_job = null;
            }
            if(current_processing_job == null && my_waiting_list.size() != 0) {
                current_processing_job = my_waiting_list.get(0);
                
            }
            if(current_processing_job != null) {

            }
        }       
    }
    
    /**
     * Implementation of Priority
     */
    private void priority() {
        //TODO insert code
        
        //4 priorities
        
        
    }

}
