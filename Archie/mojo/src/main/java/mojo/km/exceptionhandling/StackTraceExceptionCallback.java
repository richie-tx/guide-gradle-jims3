package mojo.km.exceptionhandling;

import java.util.Locale;

import mojo.km.config.ExceptionProperties;

public class StackTraceExceptionCallback implements ExceptionCallback
{
    /**
     * @param locale
     * @param t
     */
    public void execute(Locale locale, Throwable t)
    {
        String reason = ExceptionProperties.getInstance().getReason(t);
        String diagnosis = ExceptionProperties.getInstance().getDiagnosis(t);
        String solution = ExceptionProperties.getInstance().getSolution(t);

        StringBuilder buffer = new StringBuilder(100);
		
		if (reason != null && !reason.equals(""))
		{
			buffer.append("Reason: " + reason);
			buffer.append("\n");
		}
		if (diagnosis != null && !diagnosis.equals(""))
		{
		    buffer.append("Diagnosis: " + diagnosis);
		    buffer.append("\n");
		}
		if (solution != null && !solution.equals(""))
		{
		    buffer.append("Solution: " + solution);
		    buffer.append("\n");
		}

		System.err.println(buffer.toString());

        t.printStackTrace(System.err);
    }
}
