package mojo.km.logging.log4j;

import mojo.km.logging.ILogger;

/**
 * The LogManager for the log4j logging mechanism.  This implementation always
 * returns a static instance of mojo.km.logging.log4j.Log4JLogger.
 * 
 * @author Saleem Shafi
 * @see Log4JLogger
 * @modelguid {760C8923-5B55-4D99-9008-0463A35552D2}
 */
public class LogManager extends mojo.km.logging.LogManager {

	/**
	 * Returns an instance of mojo.km.logging.log4j.Log4JLogger
	 * 
	 * @return the Log4JLogger
	 * 
	 * @see Log4JLogger
	 * @see mojo.km.logging.LogManager#getLogger(String, String)
	 * @modelguid {83B5F041-A812-49EC-A402-4E16CBAB801A}
	 */
	public ILogger getLogger(String aName, String aResourceBundleName) {
		return new Log4JLogger(aName, aResourceBundleName);
	}

}
