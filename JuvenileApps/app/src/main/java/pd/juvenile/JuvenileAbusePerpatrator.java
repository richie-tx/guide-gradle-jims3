package pd.juvenile;

import java.util.Iterator;
import messaging.juvenile.GetJuvenileAbusePerpsListEvent;
import messaging.juvenile.SaveJuvenileAbusePerpEvent;
import messaging.juvenile.reply.JuvenileAbusePerpatratorResponseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenilecase.TraitType;

public class JuvenileAbusePerpatrator extends PersistentObject {
	
	private int abusePerpId;

	private String abuseID;
	
	private String relationshipToJuvenileId;
	
	private String perpetratorFirstName;
	
	private String perpetratorMiddleName;
	
	private String perpetratorLastName;

	private Code relationshipToJuvenile = null;
	private String memberId;
	private String  contactId;
	
	//declare memberid and contactid
	
	
	/**
	 * @roseuid 42B062E1016F
	 */
	public JuvenileAbusePerpatrator()
	{
	}
	
	public int getAbusePerpId() {
		return abusePerpId;
	}

	public void setAbusePerpId(int abusePerpId) {
		this.abusePerpId = abusePerpId;
	}
	

	public String getAbuseID() {
		return abuseID;
	}

	public void setAbuseID(String abuseID) {
		if (this.abuseID == null || !this.abuseID.equals(abuseID))
		{
			markModified();
		}
		this.abuseID = abuseID;
	}
	
	public Code getRelationshipToJuvenile() {
		return relationshipToJuvenile;
	}

	public void setRelationshipToJuvenile(Code relationshipToJuvenile) {
		this.relationshipToJuvenile = relationshipToJuvenile;
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId)
	{
		if (this.relationshipToJuvenileId == null || !this.relationshipToJuvenileId.equals(relationshipToJuvenileId))
		{
			markModified();
		}
		setRelationshipToJuvenile(null);
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getRelationshipToJuvenileId()
	{
		fetch();
		return relationshipToJuvenileId;
	}

	
	/**
	 * 
	 * Access method for the perpetratorFirstName property.
	 * 
	 * @return the current value of the perpetratorFirstName property
	 */
	public String getPerpetratorFirstName()
	{
		fetch();
		return perpetratorFirstName;
	}

	/**
	 * Sets the value of the perpetratorFirstName property.
	 * 
	 * @param aPerpetratorFirstName
	 *            the new value of the perpetratorFirstName property
	 */
	public void setPerpetratorFirstName(String aPerpetratorFirstName)
	{
		if (this.perpetratorFirstName == null || !this.perpetratorFirstName.equals(aPerpetratorFirstName))
		{
			markModified();
		}
		perpetratorFirstName = aPerpetratorFirstName;
	}

	public String getPerpetratorMiddleName() {
		return perpetratorMiddleName;
	}

	public void setPerpetratorMiddleName(String perpetratorMiddleName) {
		this.perpetratorMiddleName = perpetratorMiddleName;
	}

	public String getPerpetratorLastName() {
		return perpetratorLastName;
	}

	public void setPerpetratorLastName(String perpetratorLastName) {
		this.perpetratorLastName = perpetratorLastName;
	}
	public String getContactId()
	{
	    return contactId;
	}

	public void setContactId(String contactId)
	{
	    this.contactId = contactId;
	}

	public String getMemberId()
	{
	    return memberId;
	}

	public void setMemberId(String memberId)
	{
	    this.memberId = memberId;
	}

	/**
	 * 
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileAbusePerpEvent saveEvent) {
		
		this.setAbuseID(saveEvent.getAbuseId());
		this.setPerpetratorFirstName(saveEvent.getFirstName());
		this.setPerpetratorMiddleName(saveEvent.getMiddleName());
		this.setPerpetratorLastName(saveEvent.getLastName());
		this.setRelationshipToJuvenileId(saveEvent.getRelationshipToJuvenileId());
		//task 120019
		this.setMemberId(saveEvent.getMemberId());
		this.setContactId(saveEvent.getContactId());
		//
	}


	public static Iterator findJuvenileAbusePerps(GetJuvenileAbusePerpsListEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileAbusePerpatrator.class);
	}
	
	
	/*public JuvenileAbusePerpatratorResponseEvent getValueObject()
	{
		JuvenileAbusePerpatratorResponseEvent event = new JuvenileAbusePerpatratorResponseEvent();
		event.setAbuseId(this.abuseID);
		Code relationship = this.getRelationshipToJuvenile();
		event.setRelationshipToJuvenileCode(this.getRelationshipToJuvenileId());
		if (relationship != null)
		{
			event.setRelationshipToJuvenileDescription(relationship.getDescription());
		}
		else
		{
			event.setRelationshipToJuvenileDescription(null);
		}
		return event;
	}*/
}
