/*
 * Created on Jul 25, 2005
 */
package ui.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import org.apache.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import mojo.km.config.ExceptionProperties;
import mojo.km.context.ContextManager;
import mojo.km.context.Session;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.logging.LogUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;

import ui.common.UIUtil;

/**
 * @author jfisher
 * 
 */
public class StrutsExceptionHandler extends AbstractExceptionHandler
{
	/**
	 * @see org.apache.struts.action.ExceptionHandler#execute(Exception, ExceptionConfig,
	 *      ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
	 */
	public ActionForward execute(Exception anException, ExceptionConfig anExceptionConfig, ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException
	{
		ActionForward forward = null;
		String forwardStr = UIConstants.HANDLE_EXCEPTION;

		forwardStr = this.handleException(aRequest, anException);
		forward = aMapping.findForward(forwardStr);

		return forward;
	}

	protected ErrorResponseEvent getErrorEvent(HttpServletRequest aRequest, Exception anException)
	{
		ErrorResponseEvent errorEvent = new ErrorResponseEvent();
		errorEvent.setDateTimeStamp(new Date());

		String userId = UIUtil.getCurrentUserID();
		errorEvent.setUserId(userId);

		String errorLogId = new Date().getTime() + "-" + userId;
		errorEvent.setErrorLogId(errorLogId);

		String message = this.getMessage(anException);
		errorEvent.setMessage(message);
		errorEvent.setRequestedUrl(aRequest.getRequestURI());
		errorEvent.setException(anException);

		return errorEvent;
	}

	public void outputException(ErrorResponseEvent errorEvent)
	{
		Throwable exception = errorEvent.getException();

		if (exception instanceof InvocationTargetException)
		{
			InvocationTargetException itException = (InvocationTargetException) exception;
			exception = itException.getTargetException();
		}

		Session session = ContextManager.getSession();
		String sourceName = (String) session.get("sourceName");

		StringWriter sw = new StringWriter();
		sw.write("Log ID: ");
		sw.write(errorEvent.getErrorLogId());
		sw.write("\n");
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		LogUtil.log(Level.FATAL, sw.toString());

		String msg = exception.getMessage();

		ExceptionHandler.addError("Exception", errorEvent.getErrorLogId(), sourceName, -1, msg, msg);
	}

	public String getMessage(Exception anException)
	{
		ExceptionProperties properties = ExceptionProperties.getInstance();
		String msg = properties.getReason(anException);

		if ((msg == null || "".equals(msg)) && (anException.getMessage() != null))
		{
			msg = anException.getMessage();
		}
		else if (msg == null && anException.getMessage() == null)
		{
			msg = anException.getClass().getName();
		}
		else if (anException.getMessage() != null)
		{
			msg = msg + " - " + anException.getMessage();
		}

		return msg;
	}

}
