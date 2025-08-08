package mojo.km.logging;

import mojo.km.config.SessionProperties;

/**
 * This is a factory class that provides the interface that needs to be implemented for
 * each adapter's LogManager, as well as a static factory method to retrieve the appropriate
 * adapter's LogManager.
 *  
 * @author Saleem Shafi
 * @modelguid {B10DD95D-BE20-4CB0-BAA1-288B86D49469}
 */
public abstract class LogManager {
	/**
	 * This is a factory method that returns an instance of LogManager based on the
	 * loaded configuration.  The current server's LogManager session property is used
	 * to determine the appropriate LogManager instance.  If the property is null or if
	 * there is an error loading this instance of the LogManager, and instance of the 
	 * mojo.km.logging.system.LogManager is returned.
	 * 
	 * @return the server's mojo.km.logging.LogManager instance
	 * @modelguid {B08734FC-2B1C-4F50-B4FD-CBC5AAB73A89}
	 */
	public static final LogManager getInstance() {
		String lLogManagerName = SessionProperties.getInstance().getProperty("LogManager");
		if (lLogManagerName == null) {
			lLogManagerName = "mojo.km.logging.system.LogManager";		
		}
		try {
			return (LogManager)Class.forName(lLogManagerName).newInstance();
		} catch (Exception e) {
			LogManager lLogManager = new mojo.km.logging.system.LogManager();
			//LogUtil.log(lLogManager.getLogger(), Level.ERROR, "Could not create instance of log manager: "+lLogManagerName, e);
			return lLogManager;
		}
	}
	
	/**
	 * Returns the default ILogger for this LogManager.  By default, this method calls
	 * the mojo.km.logging.LogManager.getLogger(String) method with a null parameter, but
	 * should be overridden by the concrete implementation if the behavior should be
	 * different.
	 * 
	 * @return default ILogger
	 * @see LogManager#getLogger(String)
	 * @modelguid {42C42C06-800A-4F3A-8726-2DB3867433BC}
	 */
	public ILogger getLogger() {
		return getLogger(null);
	}
	
	/**
	 * Returns the ILogger associated with the given name.  By default, this method calls
	 * the mojo.km.logging.LogManager.getLogger(String, String) method with a null second
	 * parameter.  This method should be overridden by the concrete implementation if the
	 * behavior should be different.
	 * 
	 * @param aName the name of the logger
	 * @return the named ILogger
	 * @see LogManager#getLogger(String,String)
	 * @modelguid {4F9EC801-0031-4FC4-A55D-AAE158C54C61}
	 */
	public ILogger getLogger(String aName) {
		return getLogger(aName, null);
	}
	
	/**
	 * Returns the ILogger associated with the given name and ResourceBundle.  This method
	 * must be implemented by the concrete implementation.
	 * 
	 * @param aName the name of the logger
	 * @param aResourceBundleName the name of the ResourceBundle to use for the logger
	 * @return the named ILogger
	 * @modelguid {99516C3A-0AB9-4C8A-8579-99DBBC9ECA60}
	 */
	abstract public ILogger getLogger(String aName, String aResourceBundleName);
	
	/*
	 * Test method.  DELETE ME.
	 * @modelguid {98F804DF-E9AE-4472-A43E-AE7DC6E8B388}
	 */
	public static void main(String[] args) {
		mojo.km.context.ContextManager.currentContext();
		long time = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			//LogUtil.entering(LogManager.class, "main", args);
			//LogUtil.log(Level.INFO, "This is just a test.");
			//LogUtil.log(Level.ERROR, "Example error", new RuntimeException());
			//LogUtil.exiting(LogManager.class, "main", args);
		}
		time = System.currentTimeMillis() - time;
		System.err.println("with logging = total ("+time+"), average ("+(time/1000)+")");

//		long time = System.currentTimeMillis();
//		for (int i = 0; i < 1000; i++) {
//			System.out.println("Entering LogManager.main() method");
//			System.out.println("This is just a test.");
//			System.out.println("Example error");
//			new RuntimeException().printStackTrace(System.out);
//			System.out.println("Leaving LogManager.main() method");
//		}
//		time = System.currentTimeMillis() - time;
//		System.err.println("without logging = total ("+time+"), average ("+(time/1000)+")");
	}
}
