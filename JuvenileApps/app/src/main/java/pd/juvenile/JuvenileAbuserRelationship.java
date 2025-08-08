package pd.juvenile;

import java.util.Iterator;
import messaging.juvenile.GetJuvenileAbusePerpsListEvent;
import messaging.juvenile.GetJuvenileAbuserRelationshipListEvent;
import messaging.juvenile.SaveJuvenileAbusePerpEvent;
import messaging.juvenile.SaveJuvenileAbuserRelationshipEvent;
import messaging.juvenile.reply.JuvenileAbusePerpatratorResponseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenilecase.TraitType;

public class JuvenileAbuserRelationship extends PersistentObject {
	
	//private int abusePerpId;

	private String abuseID;
	private String allegedabuserRelationship;	
	private String juvenileNumber;	
	private String supervisionNumber;	
	

	/**
	 * @roseuid 42B062E1016F
	 */
	public JuvenileAbuserRelationship()
	{
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
	
		/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setAllegedabuserRelationship(String allegedabuserRelationship)
	{
		if (this.allegedabuserRelationship == null || !this.allegedabuserRelationship.equals(allegedabuserRelationship))
		{
			markModified();
		}
		//setAllegedabuserRelationship(null);
		this.allegedabuserRelationship = allegedabuserRelationship;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getAllegedabuserRelationship()
	{
		fetch();
		return allegedabuserRelationship;
	}
	
	public String getJuvenileNumber()
	{
	    fetch();
	    return juvenileNumber;
	}

	public void setJuvenileNumber(String juvenileNumber)
	{
	    if (this.juvenileNumber == null || !this.juvenileNumber.equals(juvenileNumber))
		{
			markModified();
		}
	    this.juvenileNumber = juvenileNumber;
	}
	public String getSupervisionNumber()
	{
	    fetch();
	    return supervisionNumber;
	}

	public void setSupervisionNumber(String supervisionNumber)
	{
	    if (this.supervisionNumber == null || !this.supervisionNumber.equals(supervisionNumber))
		{
			markModified();
		}
	    this.supervisionNumber = supervisionNumber;
	}
	
	
	/**
	 * 
	 * @param saveEvent
	 */
	public void hydrate(SaveJuvenileAbuserRelationshipEvent saveEvent) {
		
		this.setAbuseID(saveEvent.getAbuseId());
		this.setJuvenileNumber(saveEvent.getJuvenileNumber());
		this.setSupervisionNumber(saveEvent.getSupervisionNumber());
		this.setAllegedabuserRelationship(saveEvent.getAllegedabuserRelationship());
	}


	public static Iterator findJuvenileAbuserRelationships(GetJuvenileAbuserRelationshipListEvent event) {
		IHome home = new Home();
		return home.findAll(event, JuvenileAbuserRelationship.class);
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
