/*
 * Created on Jun 17, 2005
 */
package messaging.common.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class CharacterResponseEvent extends ResponseEvent {
	private String character;
	private String characterValue;
	

	/**
	 * @return
	 */
	public String getCharacter()
	{
		return character;
	}

	/**
	 * @return
	 */
	public String getCharacterValue()
	{
		return characterValue;
	}

	/**
	 * @param string
	 */
	public void setCharacter(String string)
	{
		character = string;
	}

	/**
	 * @param string
	 */
	public void setCharacterValue(String string)
	{
		characterValue = string;
	}

}