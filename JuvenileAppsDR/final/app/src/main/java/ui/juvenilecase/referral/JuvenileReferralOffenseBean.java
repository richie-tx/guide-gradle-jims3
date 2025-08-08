/*
 * Created on Apr 25, 2005
 */

package ui.juvenilecase.referral;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import naming.UIConstants  ;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a thinly populated version of a casefile referral for displaying
 * only needed data required data.  
 * 
 * @author glyons
 */
public class JuvenileReferralOffenseBean extends ResponseEvent implements Comparable
{
	private String juvenileNum;
	private String referralNum;
	private String sequenceNum;
	private Date offenseDate;
	private String offenseDescription;	
	private String offenseCodeId;
	private String offenseCode; 
	private String investigationNum;	
	private String keyMapLocation;	
	
	//US 71177
	private String offenseID;
	
	//Bug #86409
	private String oldOffenseCode;


	/**
	 * @return string
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return offenseDate
	 */
	public Date getOffenseDate()
	{
		return offenseDate;
	}
	
	public String getOffenseDateFormatted()
	{
		if (this.offenseDate != null) 
		{			
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(this.offenseDate);
		}
		return "";
	}

	/**
	 * @return offenseDescription
	 */
	public String getOffenseDescription()
	{
		return offenseDescription;
	}

	/**
	 * @return referralNum
	 */
	public String getReferralNum()
	{
		return referralNum;
	}

	/**
	 * @return sequenceNum
	 */
	public String getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param offenseDate
	 */
	public void setOffenseDate(Date date)
	{
		offenseDate = date;
	}

	/**
	 * @param offenseDescription
	 */
	public void setOffenseDescription(String string)
	{
		offenseDescription = string;
	}

	/**
	 * @param referralNum
	 */
	public void setReferralNum(String string)
	{
		referralNum = string;
	}

	/**
	 * @param sequenceNum
	 */
	public void setSequenceNum(String string)
	{
		sequenceNum = string;
	}

	/**
	 * @return Returns the investigationNum.
	 */
	public String getInvestigationNum() {
		return investigationNum;
	}
	/**
	 * @param investigationNum The investigationNum to set.
	 */
	public void setInvestigationNum(String investigationNum) {
		this.investigationNum = investigationNum;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.offenseDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		JuvenileReferralOffenseBean evt = (JuvenileReferralOffenseBean)o;
		return evt.getOffenseDate().compareTo(offenseDate);
	}
	
	/**
	 * @return Returns the offenseCodeId.
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}
	/**
	 * @param offenseCodeId The offenseCodeId to set.
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}

	/**
	 * @return the offenseCode
	 */
	public String getOffenseCode() {
		return offenseCode;
	}

	/**
	 * @param offenseCode the offenseCode to set
	 */
	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}

	
	/**
	 * @return the keyMapLocation
	 */
	public String getKeyMapLocation()
	{
	    return keyMapLocation;
	}

	/**
	 * @param keyMapLocation the keyMapLocation to set
	 */
	public void setKeyMapLocation(String keyMapLocation)
	{
	    this.keyMapLocation = keyMapLocation;
	}
	
	 public void clear()
	 {
	     offenseCode = "";
	     offenseDescription = "";
	     investigationNum = "";	
	     keyMapLocation = "";
	     offenseDate=null;
	     
		  
	 }

	/**
	 * @return the offenseID
	 */
	public String getOffenseID()
	{
	    return offenseID;
	}

	/**
	 * @param offenseID the offenseID to set
	 */
	public void setOffenseID(String offenseID)
	{
	    this.offenseID = offenseID;
	}

	/**
	 * @return the oldOffenseCode
	 */
	public String getOldOffenseCode()
	{
	    return oldOffenseCode;
	}

	/**
	 * @param oldOffenseCode the oldOffenseCode to set
	 */
	public void setOldOffenseCode(String oldOffenseCode)
	{
	    this.oldOffenseCode = oldOffenseCode;
	}
}