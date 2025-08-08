package mojo.km.logging;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Formats the given LogData into a String.
 * 
 * @author Saleem Shafi
 * @modelguid {63B434F6-049D-4D25-8A4B-09AA3384B925}
 */
public class SimpleFormatter {

	/**
	 * Formats the given LogData into a String.
	 * 
	 * @param logData the data to format
	 * @return the formatted String
	 * @modelguid {2A9FE3A4-CB41-4C12-8EA5-510CE682EE29}
	 */
	public static final String format(LogData logData) {
		StringBuffer lBuffer = new StringBuffer();
		Level lLevel = (Level)logData.getData(LogData.LEVEL);
		if (lLevel == null) {
			lLevel = Level.INFO;
		}
		if (lLevel == Level.TRACE) {
			String lType = (String)logData.getData(LogData.TRACETYPE);
			Class lClass = (Class)logData.getData(LogData.CLASS);
			String lMethodName = (String)logData.getData(LogData.METHOD);
			if (lClass != null && lMethodName != null) {
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
			}
		} else {
			String lMessage = (String)logData.getData(LogData.MESSAGE);
			Throwable lThrowable = (Throwable)logData.getData(LogData.THROWABLE);
			if (lMessage != null) {
				lBuffer.append(lLevel).append(": ").append(lMessage).append("\r\n");
			}
			if (lThrowable != null) {
				lBuffer.append("EXCEPTION: ");
				StringWriter lTempWriter = new StringWriter();
				lThrowable.printStackTrace(new PrintWriter(lTempWriter));
				lBuffer.append(lTempWriter.toString()).append("\r\n");
			}
		}
		return lBuffer.toString();
	}
}
