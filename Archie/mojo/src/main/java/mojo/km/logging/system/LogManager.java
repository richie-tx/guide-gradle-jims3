package mojo.km.logging.system;

import mojo.km.logging.ILogger;

/**
 * The LogManager for the default system logging mechanism.  This implementation always
 * returns a static instance of mojo.km.logging.SystemLogger.
 * 
 * @author Saleem Shafi
 * @see SystemLogger
 * @modelguid {3177429B-5A03-402E-8C37-4FEAB8F60664}
 */
public class LogManager extends mojo.km.logging.LogManager {
	/** @modelguid {92E7FD14-E979-471E-B5EB-21A06EED4D86} */
	private static final ILogger LOGGER = new SystemLogger();

	/**
	 * Returns a static instance of mojo.km.logging.SystemLogger
	 * 
	 * @return the SystemLogger
	 * 
	 * @see SystemLogger
	 * @see mojo.km.logging.LogManager#getLogger(String, String)
	 * @modelguid {00728A33-10D0-42F5-94BD-EDC30AB8131B}
	 */
	public ILogger getLogger(String aName, String aResourceBundleName) {
		return LOGGER;
	}

}
