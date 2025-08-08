package pd.supervision.administercompliance;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * 
 * @roseuid 473B85E9012A
 */
public class NonComplianceEventCasenote extends PersistentObject
{
	private int casenoteId;
	private int nonComplianceEventId;
	private int sprOrderConditionId;
	private int conditionId;
	

	/**
	 * @return Returns the conditionId.
	 */
	public int getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public int getSprOrderConditionId() {
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
	/**
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(int nonComplianceEventId) {
		this.nonComplianceEventId = nonComplianceEventId;
	}
	/**
	 * 
	 * @roseuid 473B85E9012A
	 */
	public NonComplianceEventCasenote()
	{
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
	 * @return Returns the nonCompliantEventId.
	 */
	public int getNonComplianceEventId()
	{
		fetch();
		return nonComplianceEventId;
	}

	/**
	 * 
	 * @param nonCompliantEventId The nonCompliantEventId to set.
	 */
	public void setNonCompliantEventId(int nonComplianceEventId)
	{
		if (this.nonComplianceEventId != nonComplianceEventId)
		{
			markModified();
		}
		this.nonComplianceEventId = nonComplianceEventId;
	}
	
	public static Iterator findAllByNumericParam(String attributeName, String attributeValue){
		return new Home().findAll(attributeName, new Integer(attributeValue), NonComplianceEventCasenote.class);
	}
	
	public static NonComplianceEventCasenote find(String oid){
		return (NonComplianceEventCasenote) new Home().find(oid, NonComplianceEventCasenote.class);
	}
	
	public void bind(){
		new Home().bind(this);
	}
}
