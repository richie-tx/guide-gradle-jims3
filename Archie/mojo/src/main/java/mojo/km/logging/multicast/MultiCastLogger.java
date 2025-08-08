package mojo.km.logging.multicast;

import java.io.PrintStream;

import mojo.km.logging.Level;
import mojo.km.logging.LogData;
import mojo.km.logging.SimpleFormatter;
import mojo.km.logging.ILogger;

/**
 * This is the multicast implementation of ILogger.  It uses the logging service found at
 * mojo.km.service.Logging.LoggingService, which runs on a separate process, by making a
 * multicast connect to that process and sends it the data to log.  
 * 
 * @author Saleem Shafi
 * @see mojo.km.service.Logging.LoggingService
 * @modelguid {FDAB1BFB-3139-4DF7-9B8F-8B423F495821}
 */
public class MultiCastLogger implements ILogger {
	/** @modelguid {00E90E75-4C4F-4081-882C-2FD2A1B8D5B5} */
	private PrintStream err;
	/** @modelguid {9E03AD65-64A4-4577-9E22-8AE062109804} */
	private PrintStream out;
	/** @modelguid {8EEFD0AE-6217-42BC-9F01-D029D65E86B0} */
	private static Thread loggingService;
	
	/**
	 * Creates an instance of MultiCastLogger.  If this is the first time a MultiCastLogger
	 * has been created, the logging service is also started.
	 * @modelguid {689DF2B9-8579-4E8D-82F3-B82B40B5FA87}
	 */
	MultiCastLogger() {
		err = new PrintStream(new MultiCastLogStream(MultiCastLogStream.ERRSTREAM));
		out = new PrintStream(new MultiCastLogStream(MultiCastLogStream.OUTSTREAM));

		if (loggingService == null) {
			loggingService = new Thread(new mojo.km.service.Logging.LoggingControlService());
			loggingService.setDaemon(true);
			loggingService.start();
		}
	}

	/**
	 * @return "Multicast Logger"
	 * @see ILogger#getName()
	 * @modelguid {BE7A230C-C69D-4769-8A07-94C8E20F46EF}
	 */
	public String getName() {
		return "Multicast Logger";
	}

	/**
	 * Formats the given data and sends it to the appropriate log.  FATAL and ERROR log
	 * messages are sent to the error stream, all others are sent to the output stream.
	 * 
	 * @param logData the data to be logged
	 * @see ILogger#log(LogData)
	 * @modelguid {28309C84-5CF1-48E9-8405-1303792A4BA2}
	 */
	public void log(LogData logData) {
		PrintStream lOut = out;
		Level lLevel = (Level)logData.getData(LogData.LEVEL);
		if (lLevel != null && (lLevel == Level.FATAL || lLevel == Level.ERROR)) {
			lOut = err;
		}
		lOut.print(SimpleFormatter.format(logData));
	}
}
