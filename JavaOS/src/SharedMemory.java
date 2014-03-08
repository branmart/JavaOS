import java.util.Arrays;


public class SharedMemory
{
	private static SharedMemory ME = null;
	
	private static final int CAPACITY = 10000;
	
	final int[] my_data;
	
	final boolean[] my_locks;
	
//	Singleton so we don't have to worry about multiple shared memories running around.
	protected SharedMemory(final int the_cap)
	{
		my_locks = new boolean[the_cap];
		my_data = new int[the_cap];
		Arrays.fill(my_locks, false);
		Arrays.fill(my_data, 0);
	}
	
	public int read(final int the_address) throws SegmentationException
	{
		if (validAddress(the_address))
		{
			return my_data[the_address];
		}
		else
		{
			throw new SegmentationException(the_address);
		}
	}
	
//	TODO do something if the location is locked.
	public void write(final int the_address, final int the_data) throws SegmentationException
	{
		if (validAddress(the_address))
		{
			my_data[the_address] = the_data;
		}
		else
		{
			throw new SegmentationException(the_address);
		}
	}
	
	public boolean isLocked(final int the_address) throws SegmentationException
	{
		if (validAddress(the_address))
		{
			return my_locks[the_address];
		}
		else 
		{
			throw new SegmentationException(the_address);
		}
	}
	
	public static SharedMemory getInstance()
	{
		if (ME == null)
		{
			ME = new SharedMemory(CAPACITY);
		}
		return ME;
	}
	
	public boolean validAddress(final int the_address)
	{
		return the_address < my_locks.length && 0 <= the_address;
	}
}
