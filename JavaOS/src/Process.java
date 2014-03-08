import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


public abstract class Process extends Observable
{
	/**
	 * This process' memory space. 
	 */
	private final Map<Integer, Command> my_commands = new HashMap<Integer, Command>();
	
	public enum State {RUNNING(), BLOCKED(), WAITING()};
	
	private final int my_start;
	
	private final int my_end;
	
	private State my_state;

	/**
	 * The ints are this process' memory space. Make sure they are in order or you 
	 * are going to have a bad day:)
	 * 
	 * @param the_start The 'start' address of this process.
	 * @param the_end The 'end' address of this process.
	 * @param the_state Current state of this process.
	 */
	public Process(final int the_start, final int the_end, final State the_state)
	{
		super();
		my_start = the_start;
		my_end = the_end;
		my_state = the_state;
		setUpCommands();
	}
	
	public int run(final int the_address) throws SegmentationException
	{
		if (my_state != State.BLOCKED)
		{
			if (my_commands.containsKey(the_address))
			{
				return my_commands.get(the_address).execute();
			}
			{
				throw new SegmentationException(the_address);
			}
		}
		//If we get here it means we failed to run so we return the same address.
		return the_address;
	}
	
	public int getStartAddress()
	{
		return my_start;
	}
	
	public int getEndAddress()
	{
		return my_end;
	}
	
	public void setState(final State the_state)
	{
		my_state = the_state;
	}
	
	public abstract int getSize();
	
	protected void addCommand(final int the_address, final Command the_command)
	{
		if (my_commands.containsKey(the_address) && the_command != null)
		{
			my_commands.put(the_address, the_command);
		}
	}

	protected abstract void setUpCommands();
}
