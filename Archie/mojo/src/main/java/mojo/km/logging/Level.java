package mojo.km.logging;

import java.io.Serializable;

/**
 * This is an enumeration class of the different levels recognized by moJo.  These levels
 * were designed after the levels defined in log4j; the levels in JSR47 were deemed too
 * fine-grained and difficult to distinguish.  Although each logging adapter is free to
 * implement its own mapping to its logging levels, the implied semantics are that the
 * log data is treated with increasing importance from DEBUG, INFO, WARNING, ERROR and
 * the most important. FATAL.  TRACE indicates that the log message is being used to 
 * trace the execution of the program and should be treated just like INFO.
 * 
 * @author Saleem Shafi
 * @modelguid {EE457570-0E6C-4F17-A07B-21692CDF8777}
 */
public class Level implements Serializable {
	/**
	 * Log level indicating the most important type of message.  Generally used
	 * for system failures.
	 * @modelguid {3ABDB8D9-B750-4322-BE31-B7D20D241ABE}
	 */
	public static final Level FATAL = new Level("FATAL");

	/**
	 * Log level indicating an important message, usually an unrecoverable error
	 * of some kind.
	 * @modelguid {858CF57D-48CD-4214-960D-B00FFC08A4F2}
	 */
	public static final Level ERROR = new Level("ERROR");

	/**
	 * Log level indicating a system state that is not normal, but can be
	 * recovered from.
	 * @modelguid {D522AB3D-E353-470C-968E-996ED5DFAD1F}
	 */
	public static final Level WARNING = new Level("WARNING");
	
	/**
	 * Log level for messages that are purely informational.  They do not indicate
	 * that anything is wrong, just provide information about the execution. 
	 * @modelguid {1BBEE36E-1CDE-42FC-AAAB-58501F701FD0}
	 */
	public static final Level INFO = new Level("INFO");
	
	/**
	 * Log level for messages that are intended for use in debugging a problem.
	 * This is the least priority message because their data is of no value unless
	 * an error occurs.
	 * @modelguid {C32418F2-3014-4E08-95F5-D1E9E8854651}
	 */
	public static final Level DEBUG = new Level("DEBUG");
	
	/**
	 * Log level that is equivalent to INFO in terms of priority, but indicates
	 * that the message is used to trace the flow of execution through the program.
	 * @modelguid {74329878-493A-4178-8291-533772F311B6}
	 */
	public static final Level TRACE = new Level("TRACE");
	
	/**
	 * The String representation of the logging level.
	 * @modelguid {89AF4026-BAEB-4DFA-AC90-82A36513835C}
	 */
	private String name;
	
	/**
	 * Creates a Level with the given String representation.
	 * 
	 * @param aName the String representation of the logging level
	 * @modelguid {CD26EE70-FA24-41A6-A5B6-6022AC9E199B}
	 */
	private Level(String aName) {
		name = aName;
	}
	
	/**
	 * Returns the String representation of the Level.
	 * 
	 * @return the String representation of the Level
	 * @modelguid {BDD806D5-4A87-4FFA-9806-E72A47AF7FD2}
	 */
	public String toString() {
		return name;
	}
}
