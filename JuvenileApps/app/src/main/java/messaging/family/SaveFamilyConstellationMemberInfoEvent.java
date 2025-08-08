/*
 * Created on Sep 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.Composite.CompositeRequest;



/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyConstellationMemberInfoEvent extends CompositeRequest
{
	
	private String  constellationMemberNum;
	
	private String  memberNum;

	private boolean guardian;
	
	private boolean primaryCareGiver; //ER changes 11063
    
	private String  relationToJuvenileId;
	
	private boolean inHomeStatus;
	
	private boolean detentionHearing;
	
	private boolean isOver21;
	
	private boolean detentionVisitation; 	
	 
	private String  involvmentLevelId;
	
	private boolean  removeMemberFromConstellation;
	
	private boolean parentalRightsTerminated;
	
	private boolean primaryContact;
	

	/**
	 * @return
	 */
	public String getMemberNum()
	{
		return memberNum;
	}

	/**
	 * @param string
	 */
	public void setMemberNum(String string)
	{
		memberNum = string;
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
	public String getRelationToJuvenileId()
	{
		return relationToJuvenileId;
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
	public void setRelationToJuvenileId(String string)
	{
		relationToJuvenileId = string;
	}

	/**
	 * @return
	 */
	public boolean isInHomeStatus()
	{
		return inHomeStatus;
	}
	
	/**
	 * @return
	 */
	public boolean isDetentionHearing()
	{
		return detentionHearing;
	}
	
	/**
	 * @return
	 */
	public boolean isDetentionVisitation()
	{
		return detentionVisitation;
	}

	/**
	 * @return
	 */
	public String getInvolvmentLevelId()
	{
		return involvmentLevelId;
	}

	/**
	 * @param b
	 */
	public void setInHomeStatus(boolean b)
	{
		inHomeStatus = b;
	}

	/**
	 * @param b
	 */
	public void setDetentionHearing(boolean b)
	{
		detentionHearing = b;
	}

	/**
	 * @param b
	 */
	public void setDetentionVisitation(boolean b)
	{
		detentionVisitation = b;
	}

	/**
	 * @param string
	 */
	public void setInvolvmentLevelId(String string)
	{
		involvmentLevelId = string;
	}

	/**
	 * @return
	 */
	public String getConstellationMemberNum()
	{
		return constellationMemberNum;
	}

	/**
	 * @param string
	 */
	public void setConstellationMemberNum(String string)
	{
		constellationMemberNum = string;
	}


	/**
	 * @return
	 */
	public boolean isRemoveMemberFromConstellation()
	{
		return removeMemberFromConstellation;
	}

	/**
	 * @param b
	 */
	public void setRemoveMemberFromConstellation(boolean b)
	{
		removeMemberFromConstellation = b;
	}

	/**
	 * @return the parentalRightsTerminated
	 */
	public boolean isParentalRightsTerminated() {
		return parentalRightsTerminated;
	}

	/**
	 * @param parentalRightsTerminated the parentalRightsTerminated to set
	 */
	public void setParentalRightsTerminated(boolean parentalRightsTerminated) {
		this.parentalRightsTerminated = parentalRightsTerminated;
	}
	
	/**
	 * @return the primaryContact
	 */
	public boolean isPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(boolean primaryContact) {
		this.primaryContact = primaryContact;
	}

	public boolean isPrimaryCareGiver() {
		return primaryCareGiver;
	}

	public void setPrimaryCareGiver(boolean primaryCareGiver) {
		this.primaryCareGiver = primaryCareGiver;
	}
	
	public boolean getIsOver21() {
		return this.isOver21;
	}

	public void setIsOver21(boolean over21) {
		this.isOver21 = over21;
	}
}
