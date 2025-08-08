package mojo.km.logging;

/**
 * This interface defines the basic functionality of any logger.  Any logging mechanism that
 * should be pluggable into the moJo architecture needs to implement this interface.  It has
 * been normalized to this extent so that implementing the interface should be very simple
 * for almost any logging tool.
 * 
 * @author Saleem Shafi
 * @modelguid {EC66DA7D-288D-4C72-A434-57097D37A0B6}
 */
public interface ILogger {
	/**
	 * Returns the name of this logger.
	 * 
	 * @return a String identified of this logger
	 * @modelguid {546104CA-9FA3-419F-9D34-8783C11BFA8C}
	 */
	public String getName();
	
	/**
	 * Fulfills the logging request represented by the given LogData parameter.  In most
	 * cases, this method will not be called by the developer directly, but rather through
	 * a method on mojo.km.logging.LogUtil.
	 * 
	 * @param logData the data to be logged
	 * @see LogUtil
	 * @modelguid {157184E5-BB34-45A4-A827-775594013162}
	 */
	public void log(LogData logData);
}	

