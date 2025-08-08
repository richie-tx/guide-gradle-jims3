/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import messaging.juvenile.reply.JuvenileGangsResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberTraitResponseEvent extends ResponseEvent implements Comparable
{
	private String 	traitid;
	private String 	traitTypeId;
	private String 	levelId;
	private String 	comments;
	private String 	statusId;
	private Date 	entryDate;
	private String  memberId;  //bug fix:32652 
	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
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
	public String getLevelId()
	{
		return levelId;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @return
	 */
	public String getTraitid()
	{
		return traitid;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
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
	public void setLevelId(String string)
	{
		levelId = string;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

	/**
	 * @param string
	 */
	public void setTraitid(String string)
	{
		traitid = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
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
		FamilyMemberTraitResponseEvent evt = (FamilyMemberTraitResponseEvent) obj;
		return evt.getEntryDate().compareTo(entryDate);
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
