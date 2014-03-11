
/**
 * Creates the numbers for the calculator.
 * @author Mike
 *
 */
public class Producer extends Process
{
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
		getCPU().getInput(this);
		
	}
	
	private void writeToMemory()
	{
		//lock memory
		//TODO Put the value in memory for the calculator.
		//release memory
//		signal end of process
		
	}
	
}
