package mojo.km.exceptionhandling;

public class SourceCount
{
	public int count;
	
	//private long timeStamp;
	
	private int countThreshold;
	
	public boolean error;

	//private final long TIME_EXPIRATION_NANOS = 1200000000000L; // 120 seconds = 120,000,000,000
	//private final long TIME_EXPIRATION_NANOS = 10000000000L; // 10 seconds = 10,000,000,000
	
	public SourceCount(int aCountThreshold)
	{
		//this.timeStamp = System.nanoTime();
		this.countThreshold = aCountThreshold;
	}
	
	public void addCount()
	{
		count++;	
	}
	
	//public boolean isExpired()
	//{
	//	long elapsedTime = System.nanoTime() - this.timeStamp;
		
	//	return elapsedTime > TIME_EXPIRATION_NANOS;
	//}
	
	public boolean hasExceededThreshold()
	{
		this.error = this.count > countThreshold;
		return this.error;
	}
	
	public void reset()
	{
		count = 0;
		//this.timeStamp = System.nanoTime();
	}
	
	
}
