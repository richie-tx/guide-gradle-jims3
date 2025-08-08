package mojo.km.context;

/**
 * Utility class used by context manager to determine the invocation source on a thread to enable
 * the currentContext invocation on context manager to get the right context instance.
 */
public class InvocationSource
{
	private static ThreadLocal<ContextManager> source = new ThreadLocal<ContextManager>(); 
	//private static Hashtable<Thread, ContextManager> sourceTable = new Hashtable<Thread, ContextManager>();	
	
	static public ContextManager getSource()
	{
		return source.get();
	}

	static public boolean hasSource()
	{
		return source.get() != null;
	}

	static public void removeSource()
	{
		source.remove();
	}

	static public void setSource(ContextManager aContext)
	{
		source.set(aContext);
	}
}
