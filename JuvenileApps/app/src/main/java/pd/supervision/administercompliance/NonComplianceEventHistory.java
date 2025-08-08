package pd.supervision.administercompliance;

import java.sql.Timestamp;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;




/**
 * 
 * @roseuid 473B85EA00AD
 */
public class NonComplianceEventHistory extends PersistentObject
{
	private int nonComplianceEventId;
	private int casenoteId;
	private String eventTypes;
	private String defendentId;
	private int sprOrderConditionId;
	private Timestamp dateTime;
	private String details;

	/**
	 * 
	 * @roseuid 473B85EA00AD
	 */
	public NonComplianceEventHistory()
	{
	}

	/**
	 * 
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(int nonComplianceEventId)
	{
		if (this.nonComplianceEventId != nonComplianceEventId)
		{
			markModified();
		}
		this.nonComplianceEventId = nonComplianceEventId;
	}

	/**
	 * 
	 * @return Returns the casenoteId.
	 */
	public int getCasenoteId()
	{
		fetch();
		return casenoteId;
	}

	/**
	 * 
	 * @param casenoteId The casenoteId to set.
	 */
	public void setCasenoteId(int casenoteId)
	{
		if (this.casenoteId != casenoteId)
		{
			markModified();
		}
		this.casenoteId = casenoteId;
	}

	/**
	 * 
	 * @return Returns the defendentId.
	 */
	public String getDefendentId()
	{
		fetch();
		return defendentId;
	}

	/**
	 * 
	 * @param defendentId The defendentId to set.
	 */
	public void setDefendentId(String defendentId)
	{
		if (this.defendentId == null || !this.defendentId.equals(defendentId))
		{
			markModified();
		}
		this.defendentId = defendentId;
	}

	/**
	 * 
	 * @return Returns the eventTypes.
	 */
	public String getEventTypes()
	{
		fetch();
		return eventTypes;
	}

	/**
	 * 
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(String eventTypes)
	{
		if (this.eventTypes == null || !this.eventTypes.equals(eventTypes))
		{
			markModified();
		}
		this.eventTypes = eventTypes;
	}

	/**
	 * 
	 * @return Returns the nonComplianceEventId.
	 */
	public int getNonComplianceEventId()
	{
		fetch();
		return nonComplianceEventId;
	}
	/**
	 * @return Returns the dateTime.
	 */
	public Timestamp getDateTime() {
		fetch();
		return dateTime;
	}
	/**
	 * @param dateTime The dateTime to set.
	 */
	public void setDateTime(Timestamp dateTime) {
		if (this.dateTime == null || !this.dateTime.equals(dateTime))
		{
			markModified();
		}
		this.dateTime = dateTime;
	}
	/**
	 * @return Returns the details.
	 */
	public String getDetails() {
		fetch();
		return details;
	}
	/**
	 * @param details The details to set.
	 */
	public void setDetails(String details) {
		if (this.details == null || !this.details.equals(details))
		{
			markModified();
		}
		this.details = details;
	}
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public int getSprOrderConditionId() {
		fetch();
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(int sprOrderConditionId) {
		if (this.sprOrderConditionId != sprOrderConditionId)
		{
			markModified();
		}
		this.sprOrderConditionId = sprOrderConditionId;
	}

	public void bind() {
		new Home().bind(this);		
	}
}
