package messaging.juvenile.reply;
import mojo.km.messaging.ResponseEvent;

public class JuvenileAbusePerpatratorResponseEvent extends ResponseEvent {
	private String abusePerpId;
	private String abuseID;
	private String lastName;
	private String firstName;
	private String middleName;
	private String relationshipToJuvenileCode;
	private String relationshipToJuvenileDescription; 
	

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
	 * @param object
	 */
	public void setAbuseId(String abuseID)
	{
		this.abuseID = abuseID;

	}

	/**
	 * @return
	 */
	public String getAbuseID()
	{
		return this.abuseID;
	}
	
	public String getAbusePerpId() {
		return abusePerpId;
	}

	public void setAbusePerpId(String abusePerpId) {
		this.abusePerpId = abusePerpId;
	}

	public String getRelationshipToJuvenileCode() {
		return relationshipToJuvenileCode;
	}

	public void setRelationshipToJuvenileCode(String relationshipToJuvenileCode) {
		this.relationshipToJuvenileCode = relationshipToJuvenileCode;
	}

	public String getRelationshipToJuvenileDescription() {
		return relationshipToJuvenileDescription;
	}

	public void setRelationshipToJuvenileDescription(
			String relationshipToJuvenileDescription) {
		this.relationshipToJuvenileDescription = relationshipToJuvenileDescription;
	}
}
