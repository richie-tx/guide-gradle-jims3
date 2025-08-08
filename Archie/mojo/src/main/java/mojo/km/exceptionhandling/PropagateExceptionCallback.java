package mojo.km.exceptionhandling;

import java.util.Locale;

import mojo.km.config.ExceptionProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.exception.ReturnException;

public class PropagateExceptionCallback implements ExceptionCallback
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
		}
		if (diagnosis != null && !diagnosis.equals(""))
		{
			buffer.append("Diagnosis: " + diagnosis);
		}
		if (solution != null && !solution.equals(""))
		{
			buffer.append("Solution: " + solution);
		}

		String msg = prepareMessage(reason, diagnosis, t.getMessage());
		ReturnException exception = new ReturnException(msg, t);

		System.err.println(buffer.toString());

		t.printStackTrace(System.err);

		EventManager.getSharedInstance(EventManager.REPLY).postEvent(exception);		
	}

	private String prepareMessage(String reason, String diagnosis, String excMessage)
	{
		StringBuilder message = new StringBuilder();
		if (!(reason == null || reason.equals("")))
		{
			message.append(reason);
		}
		if (!(diagnosis == null || diagnosis.equals("")))
		{
			if (message.length() > 0)
			{
				message.append(" ");
			}
			message.append(diagnosis);
		}
		if (!(excMessage == null || excMessage.equals("")))
		{
			String info = "Error Information: ";
			if (message.length() > 0)
			{
				message.append(" " + info);
			}
			else
			{
				message.append(info);
			}
			message.append(excMessage);
		}
		return message.toString();
	}
}
