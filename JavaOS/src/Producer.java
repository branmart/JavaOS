
/**
 * Creates the numbers for the calculator.
 * @author Mike
 *
 */
public class Producer extends Process
{
	private int my_data = 0;

	public Producer(final CPU the_cpu, final int the_memory_location)
	{
		super(the_cpu, the_memory_location);
	}

	@Override
	protected Command[] getInstructions()
	{
		final Command wait = new Command(){public void execute(){waitForInput();}};
		final Command write = new Command(){public void execute(){writeToMemory();}};
		final Command[] commands = {wait, write};
		return commands;
		
	}

	private void waitForInput()
	{
		try
		{
			setPC(getPC() + 1);
			my_data  = getCPU().getInput(this);
		} catch (NoInputBuffered e)
		{
			setState(State.BLOCKED);
		}
		
	}
	
	private void writeToMemory()
	{
		try
		{
			getCPU().writeMemory(this, getMemoryLocation(), my_data);
			setPC(0);
		} catch (SegmentationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MutexLockedException e)
		{
			setState(State.BLOCKED);
		}
	}
	
}
