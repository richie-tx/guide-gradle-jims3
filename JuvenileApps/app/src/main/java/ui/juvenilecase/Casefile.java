package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 */
public class Casefile
{
	String 	supervisionNum = "";
	String 	juvenileNum = "";
	Collection rules = new ArrayList();

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum( String string )
	{
		supervisionNum = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum( String string )
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public Collection getRules()
	{
		return rules;
	}

}


