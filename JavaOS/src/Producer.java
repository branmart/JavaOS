
/**
 * Creates the numbers for the calculator.
 * @author Mike
 *
 */
public class Producer extends Process
{
	/**
	 * Amount of memory this procedure occupies.
	 */
	private static final int MY_SIZE = 2;

	public Producer(final int the_start, final int the_end, final State the_state)
	{
		super(the_start, the_end, the_state);
	}

	@Override
	public int getSize()
	{
		return MY_SIZE;
	}

	@Override
	protected void setUpCommands()
	{
		final Command wait = new Command(){public int execute(){return waitForInput();}};
		final Command write = new Command(){public int execute(){return writeToMemory();}};
		addCommand(getStartAddress(), wait);
		addCommand(getEndAddress() + 1, write);		
	}

	private int waitForInput()
	{
		//TODO tell cpu i need keyboard input.
		//TODO block
		//TODO Wait for keyboard input.
		return getStartAddress() + 1;
	}
	
	private int writeToMemory()
	{
		//lock memory
		//TODO Put the value in memory for the calculator.
		//release memory
//		signal end of process
		return -1;
	}
	
}
