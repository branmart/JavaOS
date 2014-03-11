

public class Calculator extends Process
{
	/**
	 * This process' local memory.
	 */
	private int my_data = 0;
	 
	public Calculator(final CPU the_cpu, final int the_memory_location)
	{
		super(the_cpu, the_memory_location);
	}
	
	protected Command[] getInstructions()
	{
//		TODO Do not forget that individual commands are responsible for setting the process PC.
		final Command get = new Command(){public void execute(){getIntFromMemory();}};
		final Command decrement = new Command(){public void execute(){decrement();}};
//		final Command lock = new Command(){public void execute(){lockMutex();};};
		final Command write = new Command(){public void execute(){writeToOutput();}};
		final Command[] commands = {get, decrement, write};
		return commands;
	}
	
	//This will get memory from shared memory.
	private void getIntFromMemory()
	{
		try
		{
//			TODO I think that once it blocks itself it will recieve two updates before it can run again.
//			one when the producer locks the memory, in which case process remains blocked. And then 
//			again once the producer unlocks, signaling that it has put something in memory.
			getCPU().lockMemory(this, this.getMemoryLocation());
			my_data = getCPU().readMemory(this, getMemoryLocation());
			setPC(getPC() + 1);
//			If the memory is locked it will block itself. 
			if (my_data < 1)
			{
				setState(State.BLOCKED);
			}
		} catch (SegmentationException e)
		{
			System.out.println("Memory out of range.");
		} catch (MutexLockedException e)
		{
		/*TODO I don't think this should be reachable. Once another process locks a mutex that this 
		 * process is listening to, it blocks itself. But, you never know. 	
		 */
			System.out.println("Location is locked by another process.");
		}
	}
	
	/**
	 * This determines if the number in a memory location is prime.
	 */
	private void decrement()
	{
		my_data--;
		setPC(getPC() + 1);
	}

	//This will write to shared memory.
	private void writeToOutput()
	{
		try
		{
			getCPU().writeMemory(this, getMemoryLocation(), my_data);
			getCPU().unlockMemory(this, getMemoryLocation());
			setPC(0);
		} catch (SegmentationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MutexLockedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
