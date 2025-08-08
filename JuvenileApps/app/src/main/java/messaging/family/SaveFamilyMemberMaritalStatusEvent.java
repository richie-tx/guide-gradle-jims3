/*
 * Created on Oct 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberMaritalStatusEvent extends RequestEvent 
{

	private Date   entryDate;
	private String maritalStatusId;
	private Date   marriageDate;
	private Date   divorceDate;
	private String    noOfChildren;
	private String relatedFamMemId;
	

	/**
	 * @return
	 */
	public Date getDivorceDate()
	{
		return divorceDate;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return
	 */
	public String getMaritalStatusId()
	{
		return maritalStatusId;
	}

	/**
	 * @return
	 */
	public Date getMarriageDate()
	{
		return marriageDate;
	}

	/**
	 * @return
	 */
	public String getNoOfChildren()
	{
		return noOfChildren;
	}

	/**
	 * @param date
	 */
	public void setDivorceDate(Date date)
	{
		divorceDate = date;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setMaritalStatusId(String string)
	{
		maritalStatusId = string;
	}

	/**
	 * @param date
	 */
	public void setMarriageDate(Date date)
	{
		marriageDate = date;
	}

	/**
	 * @param i
	 */
	public void setNoOfChildren(String string)
	{
		noOfChildren = string;
	}

	/**
	 * @return Returns the relatedFamMemId.
	 */
	public String getRelatedFamMemId() {
		return relatedFamMemId;
	}
	/**
	 * @param relatedFamMemId The relatedFamMemId to set.
	 */
	public void setRelatedFamMemId(String relatedFamMemId) {
		this.relatedFamMemId = relatedFamMemId;
	}
}
