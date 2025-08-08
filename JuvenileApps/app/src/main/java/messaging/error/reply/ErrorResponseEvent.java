/*
 * Created on Jul 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.error.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jfisher
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ErrorResponseEvent extends ResponseEvent
{
	private String pageName;

	private Date dateTimeStamp;

	private String errorLogId;

	private String userId;

	private String message;

	private String requestedUrl;

	private Exception exception;

	/**
	 * @return
	 */
	public Date getDateTimeStamp()
	{
		return dateTimeStamp;
	}

	/**
	 * @return
	 */
	public String getErrorLogId()
	{
		return errorLogId;
	}

	/**
	 * @return
	 */
	public String getPageName()
	{
		return pageName;
	}

	/**
	 * @return
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param date
	 */
	public void setDateTimeStamp(Date date)
	{
		dateTimeStamp = date;
	}

	/**
	 * @param string
	 */
	public void setErrorLogId(String string)
	{
		errorLogId = string;
	}

	/**
	 * @param string
	 */
	public void setPageName(String string)
	{
		pageName = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string)
	{
		message = string;
	}

	/**
	 * @return
	 */
	public Exception getException()
	{
		return exception;
	}

	/**
	 * @param exception
	 */
	public void setException(Exception exception)
	{
		this.exception = exception;
	}

	public String getStackTrace()
	{
		StringBuilder buff = new StringBuilder();		
		if (this.exception != null)
		{
			buff.append("<pre>");
			buff.append(this.exception.getMessage());
			buff.append("<br>");
			buff.append(this.exception.getCause());
			buff.append("<br>");
			buff.append("Caused by: ");
			buff.append(this.exception.getClass().getName());
			buff.append("<br>");
			StackTraceElement[] myStackElements = this.exception.getStackTrace();
			if (myStackElements != null && myStackElements.length > 0)
			{
				for (int loopX = 0; loopX < myStackElements.length; loopX++)
				{
					StackTraceElement myElement = myStackElements[loopX];
					if (myElement != null && myElement.toString() != null)
					{
						buff.append("   ");
						buff.append(myElement.toString());
						buff.append("<br>");
					}
				}
			}
		//	buff.append("</pre>");
			
			if(this.exception.getCause() != null)
			{
				buff.append(this.getStackTrace(this.exception.getCause()));
			 }
			buff.append("</pre>");
		}
		
		return buff.toString();
	}
	
	private String getStackTrace(Throwable anException)
	{
		StringBuilder buff = new StringBuilder();		
		if (anException != null)
		{			 
			buff.append(this.exception.getMessage());
			buff.append("<br>");
			buff.append(anException.getCause());
		 
			buff.append("<br>");
			buff.append("Caused by: ");
			buff.append(anException.getClass().getName());
			buff.append("<br>");
			StackTraceElement[] myStackElements = anException.getStackTrace();
			if (myStackElements != null && myStackElements.length > 0)
			{
				for (int loopX = 0; loopX < myStackElements.length; loopX++)
				{
					StackTraceElement myElement = myStackElements[loopX];
					if (myElement != null && myElement.toString() != null)
					{
						buff.append("   ");
						buff.append(myElement.toString());
						buff.append("<br>");
					}
				}
			}
			buff.append("");
			
			if(anException.getCause() != null)
			{
				buff.append(this.getStackTrace(anException.getCause()));
			}
		}
		return buff.toString();
	}

	public String getRequestedUrl()
	{
		return requestedUrl;
	}

	public void setRequestedUrl(String requestingUrl)
	{
		this.requestedUrl = requestingUrl;
	}

}
