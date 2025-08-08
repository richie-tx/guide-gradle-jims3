/*
 * Created on Jun 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileDrugsResponseEvent extends ResponseEvent implements Comparable
{
	private String frequency;
	private String juvenileNum;
	private String degree;
	private String drugType;
	private String amountSpent;
	private int onsetAge;
	private Date entryDate=null;
	private String locationOfUse;

	/**
	 * @return
	 */
	public String getDegree()
	{
		return degree;
	}

	/**
	 * @return
	 */
	public String getDrugType()
	{
		return drugType;
	}

	/**
	 * @return
	 */
	public String getFrequency()
	{
		return frequency;
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
	public int getOnsetAge()
	{
		return onsetAge;
	}

	/**
	 * @return
	 */
	public String getAmountSpent()
	{
		return amountSpent;
	}

	/**
	 * @param string
	 */
	public void setDegree(String string)
	{
		degree = string;
	}

	/**
	 * @param string
	 */
	public void setDrugType(String string)
	{
		drugType = string;
	}

	/**
	 * @param string
	 */
	public void setFrequency(String string)
	{
		frequency = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param i
	 */
	public void setOnsetAge(int i)
	{
		onsetAge = i;
	}

	/**
	 * @param string
	 */
	public void setAmountSpent(String string)
	{
		amountSpent = string;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		if(this.entryDate==null)
			return 1;
		//JuvenilePhysicalAttributesResponseEvent evt = (JuvenilePhysicalAttributesResponseEvent) obj;
		JuvenileDrugsResponseEvent evt = (JuvenileDrugsResponseEvent)obj;
		return evt.getEntryDate().compareTo(entryDate);
		
	}

	/**
	 * @return
	 */
	public String getLocationOfUse()
	{
		return locationOfUse;
	}

	/**
	 * @param string
	 */
	public void setLocationOfUse(String string)
	{
		locationOfUse = string;
	}

}
