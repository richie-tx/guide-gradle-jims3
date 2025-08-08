//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\SaveJuvenileAbuseEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileAbuseEvent extends RequestEvent
{
	private String abuseId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String relationshipToJuvenileId;
	//private String allegedAbuserRelationship;
	
	private String abuseLevelId;
	private String informationBasisId;
	private String traitTypeId;
	private String abuseTreatment;
	private boolean cpsInvolvement;
	private boolean cpsCustody;	
	private String abuseDetails;
	private String abuseEvent;
	private String juvenileNum;
	private String supervisionNum;
	private String memberId;
	private String contactId;
	private String cpsCasenumber;
	
	//added for US 40635
	private String informationSrcCd; 

	/**
	 * @roseuid 42BC4DFA00B7
	 */
	public SaveJuvenileAbuseEvent()
	{

	}
	/**
	 * @return
	 */
	public String getAbuseDetails()
	{
		return abuseDetails;
	}

	/**
	 * @return
	 */
	public String getAbuseEvent()
	{
		return abuseEvent;
	}

	/**
	 * @return
	 */
	public String getAbuseId()
	{
		return abuseId;
	}

	/**
	 * @return
	 */
	public String getAbuseLevelId()
	{
		return abuseLevelId;
	}

	/**
	 * @return
	 */
	public String getAbuseTreatment()
	{
		return abuseTreatment;
	}

	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @return
	 */
	public boolean isCpsInvolvement()
	{
		return cpsInvolvement;
	}

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
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenileId()
	{
		return relationshipToJuvenileId;
	}

	/**
	 * @param string
	 */
	public void setAbuseDetails(String string)
	{
		abuseDetails = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseEvent(String string)
	{
		abuseEvent = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseId(String string)
	{
		abuseId = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseLevelId(String string)
	{
		abuseLevelId = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseTreatment(String string)
	{
		abuseTreatment = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

	/**
	 * @param b
	 */
	public void setCpsInvolvement(boolean b)
	{
		cpsInvolvement = b;
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
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenileId(String string)
	{
		relationshipToJuvenileId = string;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}

	/**
	 * @return Returns the informationBasisId.
	 */
	public String getInformationBasisId() {
		return informationBasisId;
	}
	/**
	 * @param informationBasisId The informationBasisId to set.
	 */
	public void setInformationBasisId(String informationBasisId) {
		this.informationBasisId = informationBasisId;
	}
	/**
	 * @return Returns the contactId.
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId The contactId to set.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
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
	public String getInformationSrcCd() {
		return informationSrcCd;
	}
	public void setInformationSrcCd(String informationSrcCd) {
		this.informationSrcCd = informationSrcCd;
	}
	public boolean isCpsCustody()
	{
	    return cpsCustody;
	}
	public void setCpsCustody(boolean b)
	{
	    this.cpsCustody = b;
	}
	/*public String getAllegedAbuserRelationship()
	{
	    return allegedAbuserRelationship;
	}
	public void setAllegedAbuserRelationship(String allegedAbuserRelationship)
	{
	    this.allegedAbuserRelationship = allegedAbuserRelationship;
	}*/
	public String getCpsCasenumber()
	{
	    return cpsCasenumber;
	}
	public void setCpsCasenumber(String cpsCasenumber)
	{
	    this.cpsCasenumber = cpsCasenumber;
	}
	
	
}
