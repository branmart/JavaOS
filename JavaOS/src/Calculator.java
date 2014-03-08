import java.util.Arrays;


public class Calculator extends Process
{
	private static final int MY_SIZE = 3;
	
	private int my_int = 0;
	
	private boolean my_is_prime = false;
	
	public Calculator(int the_start, int the_end, State the_state)
	{
		super(the_start, the_end, the_state);
	}

	@Override
	public int getSize()
	{
		return MY_SIZE;
	}

	protected void setUpCommands()
	{
		if (getEndAddress() - getStartAddress() > 2)
		{
			final Command get = new Command(){public int execute(){return getIntFromMemory();}};
			final Command check = new Command(){public int execute(){return isPrime();}};
			final Command write = new Command(){public int execute(){return writeToOutput();}};
			addCommand(getEndAddress(), get);
			addCommand(getEndAddress() + 1, check);
			addCommand(getEndAddress() + 2, write);
		}
		else
		{
			System.out.println("Not enough memory for the calculator.");
		}
	}
	
	//This will get memory from shared memory.
	private int getIntFromMemory()
	{
		//TODO lock memory location
		my_int = 13;
		return getStartAddress() + 1;
	}
	
	/**
	 * This determines if the number in a memory location is prime.
	 */
	private int isPrime()
	{
		final boolean[] sieve = new boolean[my_int + 1];
		Arrays.fill(sieve, true);
		sieve[0] = sieve[1] = false;
		for (int i = 2; i < sieve.length; i++)
		{
			if (sieve[i])
			{
				for (int j = 2; j < sieve.length; j++)
				{
					sieve[i * j] = false;
				}
			}
			if (!sieve[my_int + 1])
			{
				my_is_prime = false;
				return getStartAddress() + 2;
			}
		}
		my_is_prime = true;
		return getStartAddress() + 2;
		
	}
	
	//This will write to shared memory.
	private int writeToOutput()
	{
		//TODO unlock memory location.
		System.out.println(Integer.toString(getIntFromMemory()) + (my_is_prime ? " is prime. ": " is not prime"));
//		Signal end of process.
		return -1;
	}
}
