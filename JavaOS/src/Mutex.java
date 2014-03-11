import java.util.Observable;

/**
 * A mutex is responsible for one memory location. If a process tries to lock
 * an already locked mutex, that process is blocked.
 * @author User
 * 
 */
public class Mutex extends Observable
{
	private Process my_locking_process = null;
	
	private boolean my_is_locked = true;
	
	
//	
	public void lock(final Process the_process) throws MutexLockedException
	{
		//TODO dont update if already locked
		if (!isLocked())
		{
			my_is_locked = true;
			my_locking_process = the_process;
			notifyProcesses();
		}
		else if (!hasLock(the_process))
		{
			throw new MutexLockedException();
		}
	}
	
	public void unlock(final Process the_process) throws MutexLockedException
	{
		if (isLocked() && hasLock(the_process) || my_locking_process == null)
		{
			my_is_locked = false;
			my_locking_process = null;
			notifyProcesses();
		}
		else if (isLocked())
		{
			throw new MutexLockedException();
		}
//		Does not care if you try to unlock an unlocked mutex
	}
	
	
	private void notifyProcesses()
	{
		setChanged();
		notifyObservers(my_is_locked);
	}
	
	public boolean isLocked()
	{
		return my_is_locked;
	}
	
	public boolean hasLock(final Process the_process)
	{
//		TODO ensure equals and hashcode implemented in process
		return isLocked() && the_process.equals(my_locking_process);
	}
}
