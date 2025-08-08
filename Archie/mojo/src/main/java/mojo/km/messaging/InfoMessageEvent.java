/*
 * Created on May 15, 2006
 *
 */
package mojo.km.messaging;

/**
 * @author Jim Fisher
 *
 */
public class InfoMessageEvent extends ResponseEvent
{	
	private String message;
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

}
