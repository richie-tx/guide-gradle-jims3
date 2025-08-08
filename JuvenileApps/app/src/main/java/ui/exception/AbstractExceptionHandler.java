/*
 * Created on Jul 25, 2005
 */
package ui.exception;

import javax.servlet.http.HttpServletRequest;

import messaging.error.reply.ErrorResponseEvent;
import mojo.km.config.ExceptionProperties;
import naming.ExceptionConstants;
import naming.UIConstants;

import org.apache.struts.action.ExceptionHandler;

/**
 * @author jfisher
 *
 */
public abstract class AbstractExceptionHandler extends ExceptionHandler
{
	abstract protected ErrorResponseEvent getErrorEvent(HttpServletRequest aRequest, Exception anException);
	
	abstract protected void outputException(ErrorResponseEvent errorEvent);
	
	/**
	 * @param errorEvent
	 */
	protected void processException(ErrorResponseEvent errorEvent)
	{
		String message = errorEvent.getMessage();
		if (message != null && message.length() > ExceptionConstants.MESSAGE_DISPLAY_MAX_LENGTH)
		{
			message = message.substring(0, ExceptionConstants.MESSAGE_DISPLAY_MAX_LENGTH);
			errorEvent.setMessage(message);
		}
	}
	
	/**
	 * @param anException
	 * @return
	 */
	abstract protected String getMessage(Exception anException);

	protected String handleException(HttpServletRequest aRequest, Exception anException)
	{	    
		ExceptionProperties properties = ExceptionProperties.getInstance();
		
		ErrorResponseEvent errorEvent = this.getErrorEvent(aRequest, anException);

		this.processException(errorEvent);

		aRequest.setAttribute(UIConstants.ERROR_EVENT, errorEvent);

		this.outputException(errorEvent);

		return UIConstants.HANDLE_EXCEPTION;
	}



}
