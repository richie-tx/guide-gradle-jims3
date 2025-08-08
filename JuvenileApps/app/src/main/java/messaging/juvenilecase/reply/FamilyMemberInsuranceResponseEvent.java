/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberInsuranceResponseEvent extends RequestEvent implements Comparable
{
	private String insuranceId;
	private String typeId;
	private String policyNum;
	private String carrier;
	private Date 	entryDate;
	/**
	 * @return
	 */
	public String getCarrier()
	{
		return carrier;
	}

	/**
	 * @return
	 */
	public String getInsuranceId()
	{
		return insuranceId;
	}

	/**
	 * @return
	 */
	public String getPolicyNum()
	{
		return policyNum;
	}

	/**
	 * @return
	 */
	public String getTypeId()
	{
		return typeId;
	}

	/**
	 * @param string
	 */
	public void setCarrier(String string)
	{
		carrier = string;
	}

	/**
	 * @param string
	 */
	public void setInsuranceId(String string)
	{
		insuranceId = string;
	}

	/**
	 * @param string
	 */
	public void setPolicyNum(String string)
	{
		policyNum = string;
	}

	/**
	 * @param string
	 */
	public void setTypeId(String string)
	{
		typeId = string;
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

	
	/*
	 * (non-Javadoc)O
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) throws ClassCastException {
		if (obj == null)
			return -1;
		if (this.entryDate == null)
			return 1;
		FamilyMemberInsuranceResponseEvent evt = (FamilyMemberInsuranceResponseEvent) obj;
		return evt.getEntryDate().compareTo(entryDate);
	}
}
