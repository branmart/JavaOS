
public class MutexUpdate
{
	private final boolean my_is_locked;
	
	private final Process my_process;
	
	public MutexUpdate(final boolean the_is_locked, final Process the_process)
	{
		my_is_locked = the_is_locked;
		my_process = the_process;
	}
	
	public boolean isLocked()
	{
		return my_is_locked;
	}
	
	public Process getProcess()
	{
		return my_process;
	}
}
