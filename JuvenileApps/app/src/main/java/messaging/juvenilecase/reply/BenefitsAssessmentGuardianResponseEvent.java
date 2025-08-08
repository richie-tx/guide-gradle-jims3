package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 *
 */
public class BenefitsAssessmentGuardianResponseEvent extends ResponseEvent
{
	private String  constellationMemberId;
	private String  memberId;
	private String  familyId;
	private String	firstName;
	private String	lastName;
	private String	relationshipToJuvenile;
	private String	phoneNumber;
	private String	phoneType;



	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getConstellationMemberId()
	{
		return constellationMemberId;
	}

	/**
	 * @return
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * @return
	 */
	public String getPhoneType()
	{
		return phoneType;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setConstellationMemberId(String string)
	{
		constellationMemberId = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneNumber(String string)
	{
		phoneNumber = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneType(String string)
	{
		phoneType = string;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenile()
	{
		return relationshipToJuvenile;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenile(String string)
	{
		relationshipToJuvenile = string;
	}

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
	 * @return Returns the memberId.
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId The memberId to set.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
