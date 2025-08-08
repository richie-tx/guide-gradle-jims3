package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileAbuserRelationshipEvent extends RequestEvent {
	/**
	 * 
	 */
//	private static final long serialVersionUID = 8214394857982122922L;
//	private String abusePerpId;
	private String abuseID;
	private String juvenileNumber;	
	private String supervisionNumber;
	private String allegedabuserRelationship;	
	

	public SaveJuvenileAbuserRelationshipEvent()
	{

	}
	
	
	public String getAbuseId() {
		return abuseID;
	}
	public void setAbuseId(String currentabuseID) {
		this.abuseID = currentabuseID;
	}
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}


	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}
	public String getSupervisionNumber()
	{
	    return supervisionNumber;
	}


	public void setSupervisionNumber(String supervisionNumber)
	{
	    this.supervisionNumber = supervisionNumber;
	}
	public String getAllegedabuserRelationship()
	{
	    return allegedabuserRelationship;
	}


	public void setAllegedabuserRelationship(String allegedabuserRelationship)
	{
	    this.allegedabuserRelationship = allegedabuserRelationship;
	}

	
}
