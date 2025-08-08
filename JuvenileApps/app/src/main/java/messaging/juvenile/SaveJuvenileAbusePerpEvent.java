package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileAbusePerpEvent extends RequestEvent {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 8214394857982122922L;
//	private String abusePerpId;
	private String abuseID;
	private String perpetratorFirstName;
	private String perpetratorMiddleName;
	private String perpetratorLastName;
	private String relationshipToJuvenileId;
	private String memberId;
	private String contactId;
	
	public SaveJuvenileAbusePerpEvent()
	{

	}
	
	
	public String getAbuseId() {
		return abuseID;
	}
	public void setAbuseId(String currentabuseID) {
		this.abuseID = currentabuseID;
	}
	public String getFirstName() {
		return perpetratorFirstName;
	}
	public void setFirstName(String firstName) {
		this.perpetratorFirstName = firstName;
	}
	public String getLastName() {
		return perpetratorLastName;
	}
	public void setLastName(String lastName) {
		this.perpetratorLastName = lastName;
	}
	public String getMiddleName() {
		return perpetratorMiddleName;
	}
	public void setMiddleName(String middleName) {
		this.perpetratorMiddleName = middleName;
	}
	public String getRelationshipToJuvenileId() {
		return relationshipToJuvenileId;
	}
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId) {
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}
	public String getMemberId()
	{
	    return memberId;
	}
	public void setMemberId(String memberId)
	{
	    this.memberId = memberId;
	}		
	public String getContactId()
	{
	    return contactId;
	}
	public void setContactId(String contactId)
	{
	    this.contactId = contactId;
	}

}
