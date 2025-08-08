/*
 * Created on Oct 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import messaging.address.reply.AddressResponseEvent;
import naming.PDJuvenileFamilyConstants;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberAddressViewResponseEvent extends AddressResponseEvent
{
	
	private String memberFirstName;
	private String memberMiddleName;
	private String memberLastName;
	private String memberId;
	private String constellationId;
	public  String relationshipToJuvenile;
	public  String memberAddressId;

	public FamilyMemberAddressViewResponseEvent(){
		this.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_VIEW_TOPIC);
	}

	/**
	 * @return
	 */
	public String getConstellationId()
	{
		return constellationId;
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
	public String getMemberId()
	{
		return memberId;
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
	 * @param string
	 */
	public void setConstellationId(String string)
	{
		constellationId = string;
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
	public void setMemberId(String string)
	{
		memberId = string;
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
	
	public String getMemberNameAddress(){
		StringBuffer sb = new StringBuffer();
		sb.append(getMemberLastName());
		sb.append(", ");
		sb.append(getMemberFirstName());
		sb.append(" - ");
		sb.append(getRelationshipToJuvenile());
		sb.append(" - ");
		sb.append(this.toString()); // The toString here is NOT conventional java method, it is user created and exist in this class
		sb.append(" - ");
		sb.append(getValidated());
		return sb.toString();
	}

	/**
	 * @return Returns the memberAddressId.
	 */
	public String getMemberAddressId() {
		return memberAddressId;
	}
	/**
	 * @param memberAddressId The memberAddressId to set.
	 */
	public void setMemberAddressId(String memberAddressId) {
		this.memberAddressId = memberAddressId;
	}
	/**
	 * @return Returns the relationshipToJuvenile.
	 */
	public String getRelationshipToJuvenile() {
		return relationshipToJuvenile;
	}
	/**
	 * @param relationshipToJuvenile The relationshipToJuvenile to set.
	 */
	public void setRelationshipToJuvenile(String relationshipToJuvenile) {
		this.relationshipToJuvenile = relationshipToJuvenile;
	}
}
