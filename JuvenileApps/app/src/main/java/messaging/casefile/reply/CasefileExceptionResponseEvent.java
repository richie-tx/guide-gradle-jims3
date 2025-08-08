/*
 * Created on December 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.casefile.reply;

import mojo.km.messaging.ResponseEvent;


/**
 * @author nraveendran
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CasefileExceptionResponseEvent extends ResponseEvent
{
	private String exceptionType;
	private String exceptionId;
	private String exceptionMessage;	

	/**
	 * @return
	 */
	public String getExceptionId()
	{
		return exceptionId;
	}

	/**
	 * @return
	 */
	public String getExceptionMessage()
	{
		return exceptionMessage;
	}

	/**
	 * @return
	 */
	public String getExceptionType()
	{
		return exceptionType;
	}

	/**
	 * @param string
	 */
	public void setExceptionId(String string)
	{
		exceptionId = string;
	}

	/**
	 * @param string
	 */
	public void setExceptionMessage(String string)
	{
		exceptionMessage = string;
	}

	/**
	 * @param string
	 */
	public void setExceptionType(String string)
	{
		exceptionType = string;
	}

}
