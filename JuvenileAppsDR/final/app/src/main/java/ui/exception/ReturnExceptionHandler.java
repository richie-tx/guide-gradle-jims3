/*
 * Created on Aug 8, 2005
 *
 */
package ui.exception;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import org.apache.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.context.Session;
import mojo.km.context.multidatasource.JDBCException;
import mojo.km.context.multidatasource.M204Exception;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;

/**
 * @author jfisher
 * 
 */
public class ReturnExceptionHandler extends AbstractExceptionHandler
{
	private static final String LINE = "==================================================================\n";

	private static final String LOG_EXCEPTION_STRING = System.getProperty("jims2.log.exceptions");

	private static boolean logExceptions;

	static
	{
		if (LOG_EXCEPTION_STRING != null)
		{
			try
			{
				logExceptions = Boolean.valueOf(LOG_EXCEPTION_STRING).booleanValue();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public ActionForward execute(Exception anException, ExceptionConfig anExceptionConfig, ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException
	{
		ActionForward forward = null;
		String forwardStr = UIConstants.HANDLE_EXCEPTION;
		ReturnException returnException = (ReturnException) anException;

		forwardStr = this.handleException(aRequest, returnException);
		forward = aMapping.findForward(forwardStr);

		return forward;
	}

	public void outputException(ErrorResponseEvent errorEvent)
	{
		StringBuilder buffer = new StringBuilder(1000);

		buffer.append("Log ID: ");
		buffer.append(errorEvent.getErrorLogId());
		buffer.append("\n");

		ReturnException exception = (ReturnException) errorEvent.getException();
		IEvent event = exception.getEvent();
		if (event != null)
		{
			try
			{
				buffer.append("Event service which precipitated the exception: ");
				buffer.append(event.getClass().getName());
				buffer.append("\n");
				Method[] methods = event.getClass().getMethods();
				if (methods.length > 0)
				{
					buffer.append(LINE);
				}
				for (int i = 0; i < methods.length; i++)
				{
					Method m = methods[i];

					// Log the accessor methods with no parms and their values
					if (m.getName().startsWith("get") && m.getParameterTypes().length == 0)
					{
						Object obj = m.invoke(event, (Object[]) null);
						buffer.append(m.getName());
						buffer.append(": ");
						if (obj == null)
						{
							buffer.append("[null]");
						}
						else
						{
							buffer.append(obj);
						}
						buffer.append("\n");
					}
				}
				if (methods.length > 0)
				{
					buffer.append(LINE);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		LogUtil.log(Level.FATAL, buffer.toString());
		LogUtil.log(Level.FATAL, exception);

		if (logExceptions == true)
		{
			String longDesc = this.getLongDescription(exception);

			String shortDesc = null;
			if (exception.getCause() != null)
			{
				shortDesc = exception.getCause().getClass().getSimpleName();
			}

			Session session = ContextManager.getSession();
			String sourceName = (String) session.get("sourceName");

			ExceptionHandler.addError("Exception", errorEvent.getErrorLogId(), sourceName, -1, shortDesc, longDesc);
		}
	}

	private String getLongDescription(ReturnException exception)
	{
		String msg = null;
		if (exception.getCause() != null)
		{
			if (exception.getCause() instanceof NullPointerException)
			{
				Throwable t = exception.getCause();
				StackTraceElement[] stacktrace = t.getStackTrace();
				if (stacktrace.length > 1)
				{
					StackTraceElement st = stacktrace[0];
					msg = st.getClassName() + "." + st.getMethodName() + " at " + st.getLineNumber();
				}
			}
			else if (exception.getCause() instanceof JDBCException)
			{
				Throwable cause = exception.getCause().getCause();
				if (cause != null && cause instanceof SQLException)
				{
					msg = cause.getMessage();
				}
				else
				{
					msg = exception.getCause().getMessage();
				}
			}
			else if (exception.getCause() instanceof M204Exception)
			{
				msg = exception.getCause().getMessage();
			}
		}

		if (msg == null)
		{
			msg = exception.getMessage();
		}

		return msg;
	}

	protected ErrorResponseEvent getErrorEvent(HttpServletRequest aRequest, Exception anException)
	{
		ReturnException retException = (ReturnException) anException;
		ErrorResponseEvent errorEvent = new ErrorResponseEvent();
		errorEvent.setDateTimeStamp(new Date());

		String logId = retException.getErrorLogId();
		errorEvent.setErrorLogId(logId);

		String userId = retException.getErrorLogUser();
		errorEvent.setUserId(userId);

		errorEvent.setMessage(this.getMessage(anException));

		errorEvent.setException(anException);

		errorEvent.setRequestedUrl(aRequest.getRequestURI());

		return errorEvent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.common.AbstractExceptionHandler#getMessage(java.lang.Exception)
	 */
	public String getMessage(Exception exception)
	{
		String message = null;
		ReturnException retException = (ReturnException) exception;

		message = retException.getErrorReason();

		if (message == null)
		{
			message = retException.getErrorExceptionType();
		}

		return message;
	}
}
