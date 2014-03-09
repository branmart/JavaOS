import java.util.List;
import java.util.Observable;
import java.util.Queue;

/**
 * 
 * 
 * @author Brandon Martin
 *
 */
public class Scheduler {
    
    public boolean is_round_robin;
    
    public boolean is_priority;
    
    public boolean is_lottery;
    
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

    
    
    //TODO remove flags
    //TODO make a singleton
    //TODO implement the method next process for the cpu switching
    
    public Process nextProcess(final Observable the_interrupt){
    	//TODO make this tell which process is next process. if no switching return null;
    	return null;
    }

     
    /**
     * Sets round robin flag to true and all others to false
     */
    public void setRoundRobin() {
        is_round_robin = true;      
        roundRobin();
    }
    
    /**
     * Sets priority flag true and all others to false
     */
    public void setPriority() {
        is_priority = true;
        priority();
    }
    
    /**
     * Set lottery flag true and all others to false
     */
    public void setLottery() {       
        is_lottery = true;        
        lottery();
    }
    
    /**
     * Implementation of lottery
     * 
     */
    private void lottery() {
        // TODO insert code   
    }


    /**
     * Implementation of Round Robin
     */
    private void roundRobin() {
        Process current_processing_job = my_process_q.element(); //current job
        
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
    }

}
