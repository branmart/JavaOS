import java.util.Scanner;


/*
 * @author Brandon Martin
 */
public class Main {

    /**
     * @param args 
     */
    public static void main(String[] args) {
       //call timer to  start
       //ask user which type of scheduler
       
      
        System.out.println("Which type of scheduler would you like to use?");
        System.out.println("(1) Round Robin");
        System.out.println("(2) Priority");
        System.out.println("(3) Lottery");
        
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        
        if(input == 1) {
            //set round robin
            
        }
        
        if(input == 2) {
            //set priority
            
        }
        
        if(input == 3) {
            //set lottery
            
        }
        
        
        
        
        s.close();
    }
    
    
    

}
