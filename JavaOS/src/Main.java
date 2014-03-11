import java.util.ArrayList;
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

		CPU cpu = new CPU();
		//cpu done
		//timer is created in the cpu at creation
		//sceduler done at bottom
		//processes is also done at bottom.
		//IO?
		
		
		
		System.out.println("Which type of scheduler would you like to use?");
		System.out.println("(1) Round Robin");
		System.out.println("(2) Priority");
		System.out.println("(3) Lottery");

		Scanner s = new Scanner(System.in);
		int input = s.nextInt();
		if(input == 1) {
			//set round robin
		} else if(input == 2) {
			//set priority
		}else if(input == 3) {
			//set lottery
		}
		
		System.out.println("How many Procedure/consumer pairs would you like?\n");
		int pair = s.nextInt();
		s.close();
		
		Process[] procedures = new Process[pair];
		Process[] consumers = new Process[pair];
		for(int i = 0; i < pair; i++){
			procedures[i] = new Producer(cpu, i);
			consumers[i] = new Calculator(cpu,i);
		}
		SharedMemory.getInstance(procedures, consumers);
		ArrayList<Process> processes = new ArrayList<Process>();
		for(int i = 0; i < procedures.length; i++){
			processes.add(procedures[i]);
			processes.add(consumers[i]);
		}
		
		Scheduler.getInstance(processes);
		cpu.run();



	}




}
