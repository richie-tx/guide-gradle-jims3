//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\reply\\ValidateResponseEvent.java

package messaging.managesupervisioncase.reply;

import mojo.km.messaging.ResponseEvent;

public class ValidateResponseEvent extends ResponseEvent
{
	private boolean valid;
	private String message;

	/**
	 * @roseuid 4443FE9F01B6
	 */
	public ValidateResponseEvent()
	{

	}

	public ValidateResponseEvent(String aMessage)
	{
		this.message = aMessage;
	}

	/**
	 * Access method for the valid property.
	 * 
	 * @return   the current value of the valid property
	 */
	public boolean getValid()
	{
		return valid;
	}

	/**
	 * Access method for the valid property.
	 * 
	 * @return   the current value of the valid property
	 */
	public boolean isValid()
	{
		return valid;
	}
	/**
	 * Sets the value of the valid property.
	 * 
	 * @param aValid the new value of the valid property
	 */
	public void setValid(boolean aValid)
	{
		valid = aValid;
	}
	/**
	 * Access method for the message property.
	 * 
	 * @return   the current value of the message property
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the value of the message property.
	 * 
	 * @param aMessage  the new value of the message property
	 */
	public void setMessage(String aMessage)
	{
		message = aMessage;
	}

}
