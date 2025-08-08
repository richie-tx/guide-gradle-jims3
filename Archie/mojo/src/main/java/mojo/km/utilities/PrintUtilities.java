package mojo.km.utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class PrintUtilities {
	
	/**
     * Creates and returns a {@link String} from <code>t</code>�s stacktrace
     * @param t Exception whose stack trace is required
     * @return String representing the stack trace of the exception
     */
    public synchronized static String getStackTrace(Exception e, Throwable t) throws IOException {
        String returnTrace = null;
    	StringWriter buffer = new StringWriter();
    	
        PrintWriter printWriter = new PrintWriter(buffer, true);
        if( e != null) {
        	e.printStackTrace(printWriter);
        } else {
        	t.printStackTrace(printWriter);
        }
        printWriter.flush();
        buffer.flush();
        returnTrace = buffer.toString();
        try{
        	buffer.close();
        } finally {
        	printWriter.close();
        }

       return returnTrace;
    }
}
