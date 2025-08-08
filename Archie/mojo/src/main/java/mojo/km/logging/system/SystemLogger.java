package mojo.km.logging.system;

import java.io.PrintStream;

import mojo.km.logging.ILogger;
import mojo.km.logging.Level;
import mojo.km.logging.LogData;
import mojo.km.logging.SimpleFormatter;

/**
 * This is the default system implementation of ILogger.  It utilizes the normal System.out
 * and System.err streams to write its logs.  LogLevels FATAL and ERROR are mapped to
 * System.err; the others go to System.out.
 * 
 * @author Saleem Shafi
 * @modelguid {7CF6042E-E2D1-4B1F-A739-8041DFCD7E6E}
 */
public class SystemLogger implements ILogger {

	/**
	 * @return "System Logger"
	 * @see ILogger#getName()
	 * @modelguid {C3582AD5-11DB-4696-A7FE-C26D2B8BA606}
	 */
	public String getName() {
		return "System Logger";
	}

	/**
	 * Formats the given data and uses System.out and System.err streams to log it.
	 * 
	 * @param logData the data to be logged
	 * @see ILogger#log(LogData)
	 * @modelguid {4567852B-A823-41C5-B354-B9D31C9B5250}
	 */
	public void log(LogData logData) {
		PrintStream lOut = System.out;
		Level lLevel = (Level)logData.getData(LogData.LEVEL);
		if (lLevel != null && (lLevel == Level.FATAL || lLevel == Level.ERROR)) {
			lOut = System.err;
		}
		lOut.print(SimpleFormatter.format(logData));
	}
}
