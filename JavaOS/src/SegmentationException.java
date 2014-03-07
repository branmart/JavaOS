
public class SegmentationException extends Exception
{
	private final int my_address;

	public SegmentationException(final int the_address)
	{
		my_address = the_address;
	}
	
	public String getMessage()
	{
		return Integer.toString(my_address) + " is not a valid memory address.";
	}
}
