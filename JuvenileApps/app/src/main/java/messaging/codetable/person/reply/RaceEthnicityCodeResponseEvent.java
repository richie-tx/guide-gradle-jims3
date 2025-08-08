/*
 * Created on Nov 10, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable.person.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RaceEthnicityCodeResponseEvent extends ResponseEvent implements Comparable, ICode
{
	private String ncicEthnicity;
	private String jjsRaceCode;
	private String ncicRaceCode;
	private String description;
	
	public int compareTo(Object obj) throws ClassCastException
	{
		RaceEthnicityCodeResponseEvent evt = (RaceEthnicityCodeResponseEvent) obj;
		return ncicEthnicity.compareTo(evt.getNcicEthnicity());
	}
	
	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	public String getCode()
	{	
		return this.jjsRaceCode;
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getDescription()
	 */
	public String getDescription()
	{		
		return this.description;
	}

	/**
	 * @return
	 */
	public String getNcicEthnicity()
	{
		return ncicEthnicity;
	}

	/**
	 * @return
	 */
	public String getNcicRaceCode()
	{
		return ncicRaceCode;
	}

	/**
	 * @param string
	 */
	public void setNcicEthnicity(String ncicEthnicity)
	{
		this.ncicEthnicity = ncicEthnicity;
	}

	/**
	 * @param string
	 */
	public void setNcicRaceCode(String ncicRaceCode)
	{
		this.ncicRaceCode = ncicRaceCode;
	}

	/**
	 * @param string
	 */
	public void setJjsRaceCode(String acode)
	{
		this.jjsRaceCode = acode;
	}

	/**
	 * @return
	 */
	public String getJjsRaceCode()
	{
		return jjsRaceCode;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

}
