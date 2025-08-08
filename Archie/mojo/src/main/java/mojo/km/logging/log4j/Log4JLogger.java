package mojo.km.logging.log4j;

import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import mojo.km.context.ContextManager;
import mojo.km.logging.LogData;
import mojo.km.logging.ILogger;

/**
 * This is the log4j implementation of ILogger.  It uses the Apache log4j system to provide
 * logging functionality by mapping the moJo concepts to log4j.  The log4j configuration files,
 * etc, will still need to be used to make the logging function properly.
 * 
 * @author Saleem Shafi
 * @modelguid {BA8B1DCC-7B74-4E29-AC1A-256D5FC85754}
 */
public class Log4JLogger implements ILogger {
	/** @modelguid {8B9E5498-A287-4928-9CD6-5FAC7CBE5631} */
	private String name;
	/** @modelguid {59615DDC-7D28-4897-A7FC-49662A35AAE6} */
	private ResourceBundle resourceBundle;

	/**
	 * Creates an instance of Log4JLogger for the given name.  This name is used to identify
	 * the appropriate org.apache.log4j.Logger If the ResourceBundle name
	 * represents a valid ResourceBundle, it is initialized and assigned to the Logger.
	 *  
	 * @param aName the name of the org.apache.log4j.Logger
	 * @param aResourceBundleName the name of the ResourceBundle
	 * @modelguid {E4F0A450-61D4-46E5-A027-8687DA8BFB57}
	 */
	Log4JLogger(String aName, String aResourceBundleName) {
		name = aName;
		if (aResourceBundleName != null) {
			try {
				resourceBundle = ResourceBundle.getBundle(aResourceBundleName);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	/**
	 * @return the name of the logger
	 * @see ILogger#getName()
	 * @modelguid {510C0C81-ACA2-4708-BA74-1CE683732C24}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Formats the given data and uses the named org.apache.log4j.Logger to log it.  If no
	 * name is provided, i.e. default logger, the name of the current server is used.
	 * 
	 * @param logData the data to be logged
	 * @see ILogger#log(LogData)
	 * @modelguid {11451B07-8487-4A05-88EB-596C4DEF55D4}
	 */
	public void log(LogData logData) {
		Logger lLogger = null;
		if (name == null) {
			name = ContextManager.getServerName();
		}
		if (name == null) {
			lLogger = Logger.getRootLogger();
		} else {
			lLogger = Logger.getLogger(name);
		}
		if (resourceBundle != null) {
			lLogger.setResourceBundle(resourceBundle);
		}
		
		mojo.km.logging.Level lMojoLevel = (mojo.km.logging.Level)logData.getData(LogData.LEVEL);
		Level lLevel = getLog4JLevel(lMojoLevel);
		String lMessage = null;
		if (lMojoLevel == mojo.km.logging.Level.TRACE) {
			String lType = (String)logData.getData(LogData.TRACETYPE);
			Class lClass = (Class)logData.getData(LogData.CLASS);
			String lMethodName = (String)logData.getData(LogData.METHOD);
			if (lClass != null && lMethodName != null) {
				StringBuffer lBuffer = new StringBuffer();
				if (lType == LogData.EXITING) {
					lBuffer.append("EXITING METHOD: ").append(lClass.getName()).append(".").append(lMethodName).append("\r\n");
				} else {
					lBuffer.append("ENTERING METHOD: ").append(lClass.getName()).append(".").append(lMethodName).append("\r\n");
				}
				Object[] lParams = (Object[])logData.getData(LogData.PARAMS);
				if (lParams != null) {
					lBuffer.append("WITH PARAMETERS: ");
					for (int i=0; i < lParams.length; i++) {
						if (i != 0) {
							lBuffer.append("                 ");
						}
						lBuffer.append(lParams[i]).append("\r\n");
					}
				}
				Object lResult = (Object)logData.getData(LogData.RESULT);
				if (lResult != null) {
					lBuffer.append("WITH RETURN VALUE: ").append(lResult).append("\r\n");
				}
				lMessage = lBuffer.toString();
			}
		} else {
			lMessage = (String)logData.getData(LogData.MESSAGE);
		}
		Throwable lThrowable = (Throwable)logData.getData(LogData.THROWABLE);
		lLogger.log(lLevel, lMessage, lThrowable);
	}

	/**
	 * Maps the moJo logging levels to the log4j logging levels.  They basically map
	 * one-to-one except for TRACE, which maps for INFO.
	 * 
	 * @param aLevel the moJo level
	 * @return the corresponding log4j level
	 * @modelguid {C37A0EB1-D2D3-4438-97D0-80CD0CC608F8}
	 */
	private Level getLog4JLevel(Object aLevel) {
		if (aLevel != null && aLevel instanceof mojo.km.logging.Level) {
			mojo.km.logging.Level lMojoLevel = (mojo.km.logging.Level)aLevel;
			if (lMojoLevel == mojo.km.logging.Level.DEBUG) return Level.DEBUG;
			if (lMojoLevel == mojo.km.logging.Level.ERROR) return Level.ERROR;
			if (lMojoLevel == mojo.km.logging.Level.FATAL) return Level.FATAL;
			if (lMojoLevel == mojo.km.logging.Level.WARNING) return Level.WARN;
			if (lMojoLevel == mojo.km.logging.Level.INFO) return Level.INFO;
		}
		return Level.INFO;
	}
}
