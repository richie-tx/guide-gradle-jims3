/*
 * Created on Oct 19, 2004
 */
package messaging.juvenilewarrant.reply;

import naming.*;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class ActiveWarrantErrorEvent extends ResponseEvent
{
	private String juvenileNum;
	private String message;

	public ActiveWarrantErrorEvent()
	{
		this.setTopic(PDJuvenileWarrantConstants.ERROR_ACTIVE_WARRANT_EXISTS_TOPIC);
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
	public void setMessage(String msg)
	{
		message = msg;
	}
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

}