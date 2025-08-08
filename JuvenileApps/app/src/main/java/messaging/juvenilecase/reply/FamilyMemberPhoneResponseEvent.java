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
public class FamilyMemberPhoneResponseEvent extends ResponseEvent 
{
	private String familyPhoneId;
	private String phoneTypeId;
	private String phoneNum;
	private String extentionNum;
	private Date 	entryDate;
	private boolean primaryInd;
	
	/**
	 * 
	 */
	public FamilyMemberPhoneResponseEvent()
	{
		this.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_CONTACT_TOPIC);
	}

	/**
	 * @return
	 */
	public String getExtentionNum()
	{
		return extentionNum;
	}

	/**
	 * @return
	 */
	public String getFamilyPhoneId()
	{
		return familyPhoneId;
	}

	/**
	 * @return
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @return
	 */
	public String getPhoneTypeId()
	{
		return phoneTypeId;
	}

	/**
	 * @param string
	 */
	public void setExtentionNum(String string)
	{
		extentionNum = string;
	}

	/**
	 * @param string
	 */
	public void setFamilyPhoneId(String string)
	{
		familyPhoneId = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneNum(String string)
	{
		phoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneTypeId(String string)
	{
		phoneTypeId = string;
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

	/**
	 * @return the primaryInd
	 */
	public boolean isPrimaryInd() {
		return primaryInd;
	}

	/**
	 * @param primaryInd the primaryInd to set
	 */
	public void setPrimaryInd(boolean primaryInd) {
		this.primaryInd = primaryInd;
	}

}
