package mojo.km.logging.multicast;

import mojo.km.logging.ILogger;

/**
 * The LogManager for the multicast logging mechanism.  This implementation always
 * returns a static instance of mojo.km.logging.multicast.MultiCastLogger.
 * 
 * @author Saleem Shafi
 * @see mojo.km.logging.system.SystemLogger
 * @modelguid {5B29E701-EAE5-41A3-87FE-F96A1DA0D060}
 */
public class LogManager extends mojo.km.logging.LogManager {
	/** @modelguid {C503304B-F215-48A5-A250-1A4F01CB0F36} */
	private static final ILogger LOGGER = new MultiCastLogger();

	/**
	 * Returns a static instance of mojo.km.logging.multicast.MultiCastLogger
	 * 
	 * @return the MultiCastLogger
	 * 
	 * @see MultiCastLogger
	 * @see mojo.km.logging.LogManager#getLogger(String, String)
	 * @modelguid {A61C4E6C-F3C8-4ACF-9819-7C07A0EE5361}
	 */
	public ILogger getLogger(String aName, String aResourceBundleName) {
		return LOGGER;
	}

}
