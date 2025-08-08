package mojo.km.exceptionhandling;

import java.net.InetAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import messaging.exceptionhandling.GetLogEvent;
import messaging.exceptionhandling.LogEntryTO;
import mojo.km.config.ExceptionProperties;
import mojo.km.context.ContextManager;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;

public class ExceptionHandler
{
	public static final String ERROR = "ERROR";

	public static final String FATAL = "FATAL";

	public static final String INFO = "INFO";

	public static final String WARN = "WARN";

	private static void addEntry(String aCategory, String aSourceId, String aSourceName, String type,
			long aMilliseconds, String aShortDesc, String aLongDesc)
	{
		LogEntry logEntry = new LogEntry();
		logEntry.setCategory(aCategory);

		try
		{
			InetAddress localHost = InetAddress.getLocalHost();
			logEntry.setIpAddress(localHost.getHostAddress());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		logEntry.setSourceId(aSourceId);

		String subsystem = (String) ContextManager.getSession().get("subsystem");
		if (subsystem == null)
		{
			subsystem = "FW";
		}
		logEntry.setSubsystem(subsystem);
		logEntry.setThreadName(Thread.currentThread().getName());
		logEntry.setType(type);

		if (aSourceName == null)
		{
			String sourceName = (String) ContextManager.getSession().get("sourceName");
			logEntry.setSourceName(sourceName);
		}
		else
		{
			logEntry.setSourceName(aSourceName);
		}

		logEntry.setMilliseconds(aMilliseconds);
		logEntry.setShortDescription(aShortDesc);
		logEntry.setLongDescription(aLongDesc);
		IHome home = new Home();
		home.bind(logEntry);
	}

	public static void addError(LogEntryTO aLogEntry)
	{
		LogEntry logEntry = new LogEntry();
		aLogEntry.setType(ERROR);
		logEntry.update(aLogEntry);
		IHome home = new Home();
		home.bind(logEntry);
	}

	public static void addError(String category, String sourceId, long milliseconds, String shortDesc, String longDesc)
	{
		addEntry(category, sourceId, null, ERROR, milliseconds, shortDesc, longDesc);
	}

	public static void addError(String category, String sourceId, String sourceName, long milliseconds,
			String shortDesc, String longDesc)
	{
		addEntry(category, sourceId, sourceName, ERROR, milliseconds, shortDesc, longDesc);
	}

	public static void addFatal(LogEntryTO aLogEntry)
	{
		LogEntry logEntry = new LogEntry();
		aLogEntry.setType(FATAL);
		logEntry.update(aLogEntry);
		IHome home = new Home();
		home.bind(logEntry);
	}
	
	public static void addFatal(String category, String sourceId, long milliseconds, String shortDesc, String longDesc)
	{
		addEntry(category, sourceId, null, FATAL, milliseconds, shortDesc, longDesc);
	}

	public static void addFatal(String category, String sourceId, String sourceName, long milliseconds, int aCount,
			String shortDesc, String longDesc)
	{
		addEntry(category, sourceId, sourceName, FATAL, milliseconds, shortDesc, longDesc);
	}

	public static void addWarning(LogEntryTO aLogEntry)
	{
		LogEntry logEntry = new LogEntry();
		aLogEntry.setType(WARN);
		logEntry.update(aLogEntry);
		IHome home = new Home();
		home.bind(logEntry);
	}

	public static void addWarning(String category, String sourceId, long milliseconds, String shortDesc, String longDesc)
	{
		addEntry(category, sourceId, null, WARN, milliseconds, shortDesc, longDesc);
	}

	public static void addWarning(String category, String sourceId, String sourceName, long milliseconds,
			String shortDesc, String longDesc)
	{
		addEntry(category, sourceId, sourceName, WARN, milliseconds, shortDesc, longDesc);
	}

	/**
	 * Executes a set of callback classes related to the exception that is thrown.
	 * 
	 * @param locale
	 * @param t
	 * @modelguid {5A34FA00-F96F-4F28-958A-82ED4ED6DDB7}
	 */
	static public void executeCallbacks(Locale locale, Throwable t)
	{
		try
		{
			for (Iterator i = ExceptionProperties.getInstance().getCallbacks(locale, t); i.hasNext();)
			{
				String className = (String) i.next();
				Class callbackClass = Class.forName(className);
				ExceptionCallback callback = (ExceptionCallback) callbackClass.newInstance();
				callback.execute(locale, t);
			}
		}
		catch (Throwable aT)
		{
		}
	}

	/**
	 * Executes a set of callback classes related to the exception that is thrown.
	 * 
	 * @param t
	 * @modelguid {2FB230F5-AA60-439F-96BD-1E3BCBA935FE}
	 */
	static synchronized public void executeCallbacks(Throwable t)
	{
		try
		{
			for (Iterator i = ExceptionProperties.getInstance().getCallbacks(Locale.getDefault(), t); i.hasNext();)
			{
				String className = (String) i.next();
				Class callbackClass = Class.forName(className);
				ExceptionCallback callback = (ExceptionCallback) callbackClass.newInstance();
				callback.execute(Locale.getDefault(), t);
			}
		}
		catch (Throwable aT)
		{
			System.err.println("Error reporting exception: " + t);
			aT.printStackTrace();
			System.err.println("Original exception: ");
			t.printStackTrace();
		}
	}

	public static List getLogEntries(Date beginDate, Date endDate, String category, String sourceId, String sourceName,
			String subsystem, String type)
	{
		GetLogEvent event = new GetLogEvent();
		event.setBeginDate(beginDate);
		event.setEndDate(endDate);
		event.setCategory(category);
		event.setSourceId(sourceId);
		event.setSourceName(sourceName);
		event.setSubsystem(subsystem);
		event.setType(type);

		IHome home = new Home();
		Iterator i = home.findAll(event, LogEntry.class);

		List results = CollectionUtil.iteratorToList(i);

		return results;
	}

	/**
	 * @param local
	 * @param exceptionObject
	 * @return
	 * @modelguid {0E3FFD79-4B3E-48D6-A2DB-C3FCB9DA3345}
	 */
	static public String getProperties(Locale local, Throwable exceptionObject)
	{
		String retVal = "";

		ExceptionProperties eProps = ExceptionProperties.getInstance();
		String lReason = eProps.getReason(local, exceptionObject);
		String lDiagnosis = eProps.getDiagnosis(local, exceptionObject);
		String lSolution = eProps.getSolution(local, exceptionObject);
		if (lReason != null)
		{
			retVal += lReason + "\n\n";
		}
		else
		{
			retVal += exceptionObject.getMessage();
		}
		if (lDiagnosis != null)
		{
			retVal += lDiagnosis + "\n\n";
		}
		if (lSolution != null)
		{
			retVal += lSolution + "\n";
		}
		return retVal;
	}

	/**
	 * @param throwable
	 * @return
	 * @modelguid {D801813D-7DED-4ECE-A385-E4A4BE4992B6}
	 */
	static public String getProperties(Throwable throwable)
	{
		if (ExceptionProperties.getInstance().hasProperties(throwable))
		{
			return getProperties(Locale.getDefault(), throwable);
		}
		return throwable.getMessage();
	}

	/**
	 * Prints just related editable properties. Properties may be found in Mojo.xml
	 * 
	 * @param throwable -
	 *            the error or exception to be manipulated
	 */
	static public void printProperties(Throwable throwable)
	{
		throwable.printStackTrace();
	}
}
