/*
 * Created on Oct 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddFamilyMemberEvent extends CompositeRequest
{
	private String familyId;
	private String memberId;

	private boolean guardian;
	private boolean primaryCareGiver; //ER changes 11063
	private String  juvenileAgeAtDeath;
	private Date    confirmDate;
	private String  involvmentLevelId;
	private String  relationToJuvenileId;
	private boolean inHomeStauts;
	private boolean parentalRightsTerminated;

	/**
	 * @return
	 */
	public String getFamilyId()
	{
		return familyId;
	}

	/**
	 * @param string
	 */
	public void setFamilyId(String string)
	{
		familyId = string;
	}

	/**
	 * @return
	 */
	public Date getConfirmDate()
	{
		return confirmDate;
	}

	/**
	 * @return
	 */
	public boolean isGuardian()
	{
		return guardian;
	}

	/**
	 * @return
	 */
	public String getInvolvmentLevelId()
	{
		return involvmentLevelId;
	}

	/**
	 * @return
	 */
	public String getJuvenileAgeAtDeath()
	{
		return juvenileAgeAtDeath;
	}

	/**
	 * @return
	 */
	public String getMemberId()
	{
		return memberId;
	}

	/**
	 * @return
	 */
	public String getRelationToJuvenileId()
	{
		return relationToJuvenileId;
	}

	/**
	 * @param date
	 */
	public void setConfirmDate(Date date)
	{
		confirmDate = date;
	}

	/**
	 * @param b
	 */
	public void setGuardian(boolean b)
	{
		guardian = b;
	}

	/**
	 * @param string
	 */
	public void setInvolvmentLevelId(String string)
	{
		involvmentLevelId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileAgeAtDeath(String string)
	{
		juvenileAgeAtDeath = string;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		memberId = string;
	}

	/**
	 * @param string
	 */
	public void setRelationToJuvenileId(String string)
	{
		relationToJuvenileId = string;
	}

	/**
	 * @return
	 */
	public boolean isInHomeStauts()
	{
		return inHomeStauts;
	}

	/**
	 * @param b
	 */
	public void setInHomeStauts(boolean b)
	{
		inHomeStauts = b;
	}

	/**
	 * @return 
	 */
	public boolean isParentalRightsTerminated() {
		return parentalRightsTerminated;
	}

	/**
	 * @param b
	 */
	public void setParentalRightsTerminated(boolean b) {
		parentalRightsTerminated = b;
	}

	public boolean isPrimaryCareGiver() {
		return primaryCareGiver;
	}

	public void setPrimaryCareGiver(boolean primaryCareGiver) {
		this.primaryCareGiver = primaryCareGiver;
	}

}
