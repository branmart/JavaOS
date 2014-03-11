import java.util.Arrays;


public class SharedMemory
{
	private static SharedMemory ME = null;
	
	final int[] my_data;
	
	final Mutex[] my_locks; 
	
//	Singleton so we don't have to worry about multiple shared memories running around.
	protected SharedMemory(final Process[] the_producers, final Process[] the_consumers)
	{
		my_locks = new Mutex[the_producers.length];
		my_data = new int[the_producers.length];
		Arrays.fill(my_data, 0);
		for (int i = 0; i < the_producers.length; i++)
		{
			final Mutex m = new Mutex(the_producers[i]);
			m.addObserver(the_producers[i]);
			m.addObserver(the_consumers[i]);
			my_locks[i] = m;
		}
	}
	

//	In theory each process gets one memory slot so we shouldnt have to specify address but better safe than sorry.
	public int read(final Process the_process, final int the_address) throws SegmentationException, MutexLockedException
	{
		if (!validAddress(the_address)){throw new SegmentationException(the_address);};
		//can only read if it process has the lock or it is unlocked
		if (my_locks[the_address].isLocked() && !my_locks[the_address].hasLock(the_process)){throw new MutexLockedException();}
		final int data = my_data[the_address];
		return data;
	}
	
	/**
	 * *******Careful, does not check mutexes. It is up to the thread to lock the memory.*****************
	 * 
	 * @param the_process
	 * @param the_address
	 * @param the_data
	 * @return
	 * @throws SegmentationException
	 * @throws MutexLockedException
	 */
	
//	*******Careful, does not check mutexes. It is up to the thread to lock the memory.*****************
	public void write(final Process the_process, final int the_address, final int the_data) throws SegmentationException, MutexLockedException
	{
		if (!validAddress(the_address)){throw new SegmentationException(the_address);};
		my_data[the_address] = the_data;
	}
	
	public void lock(final Process the_process, final int the_address) throws SegmentationException, MutexLockedException
	{
		if (!validAddress(the_address)){throw new SegmentationException(the_address);};
		my_locks[the_address].lock(the_process);
	}
	
	public void unlock(final Process the_process, final int the_address) throws SegmentationException, MutexLockedException
	{
		if (!validAddress(the_address)){throw new SegmentationException(the_address);};
		my_locks[the_address].unlock(the_process);
	}
	
	public static SharedMemory getInstance()
	{
		if (ME == null)
		{
//			TODO Better call the other get instance first!
			return getInstance(new Producer[0], new Calculator[0]);
		}
		return ME;
	}
	
	public static SharedMemory getInstance(final Process[] the_producers, final Process[] the_consumers)
	{
		if (ME == null)
		{
			ME = new SharedMemory(the_producers, the_consumers);
		}
		return ME;
	}
	
	public boolean validAddress(final int the_address)
	{
		return the_address < my_locks.length && 0 <= the_address;
	}
}