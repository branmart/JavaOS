import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


public abstract class Process extends Observable
{
	private final Map<Integer, Command> my_commands = new HashMap<Integer, Command>();
	
	public enum State {RUNNING(), BLOCKED(), WAITING()};
	
	private final State my_state;
	
	private final int my_start;
	
	private final int my_end;
	
	/**
	 * The ints are this process' memory space. Make sure they are in order.
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
		setUpCommands(the_start, the_end);
	}
	
	public void run(final int the_address) throws SegmentationException
	{
		if (my_state != State.BLOCKED)
		{
			if (my_commands.containsKey(the_address))
			{
				my_commands.get(the_address).execute();
			}
			{
				throw new SegmentationException(the_address);
			}
		}
	}
	
	public int getStartAddress()
	{
		return my_start;
	}
	
	public int getEndAddress()
	{
		return my_end;
	}
	
	protected void addCommand(final int the_address, final Command the_command)
	{
		if (my_commands.containsKey(the_address) && the_command != null)
		{
			my_commands.put(the_address, the_command);
		}
	}
	
	protected abstract void setUpCommands(final int the_start, final int the_end);
}
