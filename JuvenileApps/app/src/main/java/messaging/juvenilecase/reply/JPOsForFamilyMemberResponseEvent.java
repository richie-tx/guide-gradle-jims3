/*
 * Created on May 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;


import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JPOsForFamilyMemberResponseEvent  extends ResponseEvent implements IAddressable
{
	
	private String identity;
	private String memberId;
	private String memberFirstName;
	private String memberMiddleName;
	private String memberLastName;
	private String memberSSN;
	private String officerId;
	private String relationshipId;
	private String relationship;
	private String officerEmail;
	private String officerUserId;
	private String officerFirstName;
	private String officerMiddleName;
	private String officerLastName;
	private String juvenileFirstName;
	private String juvenileMiddleName;
	private String juvenileLastName;
	private String juvenileId;
	private String constellationId;
	private String notificationMessage="test message again";
	
	/**
	 * @return
	 */
	public String getConstellationId()
	{
		return constellationId;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * 
	 * @param identity
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}

	/**
	 * @return
	 */
	public String getJuvenileId()
	{
		return juvenileId;
	}

	/**
	 * @return
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}

	/**
	 * @return
	 */
	public String getJuvenileMiddleName()
	{
		return juvenileMiddleName;
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
	public String getOfficerEmail()
	{
		return officerEmail;
	}

	/**
	 * @return
	 */
	public String getOfficerFirstName()
	{
		return officerFirstName;
	}

	/**
	 * @return
	 */
	public String getOfficerId()
	{
		return officerId;
	}

	/**
	 * @return
	 */
	public String getOfficerLastName()
	{
		return officerLastName;
	}

	/**
	 * @return
	 */
	public String getOfficerMiddleName()
	{
		return officerMiddleName;
	}

	/**
	 * @return
	 */
	public String getOfficerUserId()
	{
		return officerUserId;
	}

	/**
	 * @param string
	 */
	public void setConstellationId(String string)
	{
		constellationId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileFirstName(String string)
	{
		juvenileFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileId(String string)
	{
		juvenileId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileLastName(String string)
	{
		juvenileLastName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileMiddleName(String string)
	{
		juvenileMiddleName = string;
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
	public void setOfficerEmail(String string)
	{
		officerEmail = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerFirstName(String string)
	{
		officerFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerId(String string)
	{
		officerId = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerLastName(String string)
	{
		officerLastName = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerMiddleName(String string)
	{
		officerMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerUserId(String string)
	{
		officerUserId = string;
	}

	/**
	 * @return
	 */
	public String getNotificationMessage()
	{
		return notificationMessage;
	}

	/**
	 * @param string
	 */
	public void setNotificationMessage(String string)
	{
		notificationMessage = string;
	}

	/**
	 * @return
	 */
	public String getMemberFirstName()
	{
		return memberFirstName;
	}

	/**
	 * @return
	 */
	public String getMemberLastName()
	{
		return memberLastName;
	}

	/**
	 * @return
	 */
	public String getMemberMiddleName()
	{
		return memberMiddleName;
	}

	/**
	 * @return
	 */
	public String getRelationship()
	{
		
		return relationship;
	}

	/**
	 * @return
	 */
	public String getRelationshipId()
	{
		return relationshipId;
	}

	

	/**
	 * @param string
	 */
	public void setMemberFirstName(String string)
	{
		memberFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberLastName(String string)
	{
		memberLastName = string;
	}

	/**
	 * @param string
	 */
	public void setMemberMiddleName(String string)
	{
		memberMiddleName = string;
	}

	/**
	 * @param string
	 */
	public void setRelationship(String string)
	{
		relationship = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipId(String string)
	{
		
		relationshipId = string;
	}

	
	/**
	 * @return
	 */
	public String getMemberSSN()
	{
		return memberSSN;
	}

	/**
	 * @param string
	 */
	public void setMemberSSN(String string)
	{
		memberSSN = string;
	}

}
