/*
 * Created on Aug 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.addressValidation;

import mojo.km.messaging.RequestEvent;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ValidateBlockRangeEvent extends RequestEvent
{
	private String streetName;
	private int streetNum;
	private int beginingStreetNum;
	private int endingStreetNum;
	private int zipCode;
	/**
	 * @return
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return
	 */
	public int getStreetNum()
	{
		return streetNum;
	}

	/**
	 * @return
	 */
	public int getZipCode()
	{
		return zipCode;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string)
	{
		streetName = string;
	}

	/**
	 * @param i
	 */
	public void setStreetNum(int i)
	{
		streetNum = i;
	}

	/**
	 * @param i
	 */
	public void setZipCode(int i)
	{
		zipCode = i;
	}

	/**
	 * @return
	 */
	public int getBeginingStreetNum()
	{
		return beginingStreetNum;
	}

	/**
	 * @return
	 */
	public int getEndingStreetNum()
	{
		return endingStreetNum;
	}

	/**
	 * @param i
	 */
	public void setBeginingStreetNum(int i)
	{
		beginingStreetNum = i;
	}

	/**
	 * @param i
	 */
	public void setEndingStreetNum(int i)
	{
		endingStreetNum = i;
	}

}
