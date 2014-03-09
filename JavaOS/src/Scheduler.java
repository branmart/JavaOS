import java.util.Observable;

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
    
    //TODO make a singleton
    //TODO implement the method next process for the cpu switching
    
    public Process nextProcess(final Observable the_interrupt){
    	//TODO make this switch proccess. if no switching return null;
    	return null;
    }

     
    /**
     * Sets round robin flag to true and all others to false
     */
    public void setRoundRobin() {
        is_round_robin = true;
        is_priority = false;
        is_lottery = false;
        
    }
    
    /**
     * Sets priority flag true and all others to false
     */
    public void setPriority() {
        is_priority = true;
        is_lottery = false;
        is_round_robin = false;
        
    }
    
    /**
     * Set lottery flag true and all others to false
     */
    public void setLottery() {
        is_lottery = true;
        is_round_robin = false;
        is_priority = false;
        
    }

}
