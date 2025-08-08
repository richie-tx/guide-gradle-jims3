/*
 * Created on Sep 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import naming.PDJuvenileFamilyConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberEmailResponseEvent extends ResponseEvent 
{
	private String familyEmailId;
	private String emailTypeId;
	private String emailAddress;
	private Date 	entryDate;
	private boolean primaryInd;
	/**
	 * 
	 */
	public FamilyMemberEmailResponseEvent()
	{
		this.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_EMAIL_TOPIC);
	}

	

	/**
	 * @return
	 */
	public String getFamilyEmailId()
	{
		return familyEmailId;
	}

	/**
	 * @return
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	/**
	 * @return
	 */
	public String getEmailTypeId()
	{
		return emailTypeId;
	}

	
	/**
	 * @param string
	 */
	public void setFamilyEmailId(String string)
	{
		familyEmailId = string;
	}

	/**
	 * @param string
	 */
	public void setEmailAddress(String string)
	{
		emailAddress = string;
	}

	/**
	 * @param string
	 */
	public void setEmailTypeId(String string)
	{
		emailTypeId = string;
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
	
	public boolean getPrimaryInd() {
		return this.primaryInd;
	}

	public void setPrimaryInd(boolean primaryInd) {
		this.primaryInd = primaryInd;
	}


}
