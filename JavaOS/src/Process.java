import java.util.Observable;
import java.util.Observer;


public abstract class Process implements Observer
{
	/**
	 * This process' memory space. 
	 */
	private final Command[] my_instructions;
	
	public enum State {RUNNING(), BLOCKED(), WAITING()};
	 
	private State my_state;
	
	private int my_pc = 0;
	
//	So this process knows where to make its calls.
	private final CPU my_cpu;

	private final int my_memory_location;
	

//	TODO keeping it simple. A process may only read or write in one location.
	/**
	 * The ints are this process' memory space. Make sure they are in order or you 
	 * are going to have a bad day:)
	 * 
	 * @param the_memory_address The location this process can read or write to.
	 */
	public Process(final CPU the_cpu, final int the_memory_location)
	{
		super();
		my_cpu = the_cpu;
		my_memory_location = the_memory_location;
		my_instructions = getInstructions();
		my_state = State.WAITING;
		my_pc = 0;
	}
	
	public void nextInstruction(final int the_address) throws SegmentationException
	{
		if (my_state != State.BLOCKED && my_pc < my_instructions.length)
		{
			my_state = State.RUNNING;
//			The individual commands are responsible for incrementing the pc.
			my_instructions[my_pc].execute();
			my_state = State.WAITING;
		}
	}
	
	public int getInstructionCount()
	{
		return my_instructions.length;
	}
	
	public void update(final Observable the_obs, final Object the_condition)
	{
//		TODO make sure process that locks doesnt get blocked.
		if (the_condition != null && the_condition.getClass() == Boolean.class && !the_obs.equals(this))
		{
			//Can not be running because the other thread that locked the mutex is running.
			boolean is_locked = (Boolean) the_condition;
			my_state = is_locked ? State.BLOCKED : State.WAITING;
		}
	}

	public State getState()
	{
		return my_state;
	}
	
//	TODO not sure if this really needs to be public. If we do it right the process will take 
//	care of setting the state.
	public void setState(final State the_state)
	{
		my_state = the_state;
	}
	
	protected CPU getCPU()
	{
		return my_cpu;
	}
	
	protected int getMemoryLocation()
	{
		return my_memory_location;
	}
	
	protected void lockMemory() throws SegmentationException, MutexLockedException
	{
		getCPU().lockMemory(this, getMemoryLocation());
	}
	
	protected void unlockMemory() throws SegmentationException, MutexLockedException
	{
		getCPU().unlockMemory(this, getMemoryLocation());
	}
	
	protected int getPC()
	{
		return my_pc;
	}
	
	protected void setPC(final int the_pc)
	{
		my_pc = the_pc;
	}

	protected abstract Command[] getInstructions();
}
