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
