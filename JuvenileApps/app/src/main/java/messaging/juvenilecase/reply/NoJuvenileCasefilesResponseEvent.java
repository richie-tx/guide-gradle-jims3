/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author glyons
 */
public class NoJuvenileCasefilesResponseEvent extends ResponseEvent
{
	private String message;
	
	/**
	 * 
	 * @return message
	 */
	public String getMessage() 
	{
	        return this.message;
	}
	
	/**
	 * 
	 * @param aMessage
	 */
	public void setMessage(String aMessage) 
	{
	        this.message = aMessage;
	}
}