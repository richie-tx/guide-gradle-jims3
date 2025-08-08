package pd.supervision.supervisionoptions;

import java.util.Iterator;

import messaging.supervisionoptions.reply.OrderConditionAgencyPolicyResponseEvent;
import messaging.supervisionoptions.reply.OrderConditionCourtPolicyResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SupervisionOrderConditionAgencyPolicyView extends PersistentObject {
	private int orderConditionId;
	private int conditionId;
	private int agencyPolicyId;
	private String agencyPolicyName;
	private String agencyPolicyDescription;
	private String defendantId;
	

	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, SupervisionOrderConditionAgencyPolicyView.class);
	}
	
	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAllByNumericParameter(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, new Integer(attributeValue), SupervisionOrderConditionAgencyPolicyView.class);
	}
	
	
	/**
	 * @return Returns the agencyPolicyDescription.
	 */
	public String getAgencyPolicyDescription() {
		return agencyPolicyDescription;
	}
	/**
	 * @param agencyPolicyDescription The agencyPolicyDescription to set.
	 */
	public void setAgencyPolicyDescription(String agencyPolicyDescription) {
		this.agencyPolicyDescription = agencyPolicyDescription;
	}
	/**
	 * @return Returns the agencyPolicyId.
	 */
	public int getAgencyPolicyId() {
		return agencyPolicyId;
	}
	/**
	 * @param agencyPolicyId The agencyPolicyId to set.
	 */
	public void setAgencyPolicyId(int agencyPolicyId) {
		this.agencyPolicyId = agencyPolicyId;
	}
	/**
	 * @return Returns the agencyPolicyName.
	 */
	public String getAgencyPolicyName() {
		return agencyPolicyName;
	}
	/**
	 * @param agencyPolicyName The agencyPolicyName to set.
	 */
	public void setAgencyPolicyName(String agencyPolicyName) {
		this.agencyPolicyName = agencyPolicyName;
	}
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
	 * @return Returns the orderConditionId.
	 */
	public int getOrderConditionId() {
		return orderConditionId;
	}
	/**
	 * @param orderConditionId The orderConditionId to set.
	 */
	public void setOrderConditionId(int orderConditionId) {
		this.orderConditionId = orderConditionId;
	}

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	/**
	 * @return
	 */
	public OrderConditionAgencyPolicyResponseEvent getResponseEvent() {
		OrderConditionAgencyPolicyResponseEvent resp = new OrderConditionAgencyPolicyResponseEvent();
		resp.setConditionId(this.getConditionId());
		resp.setAgencyPolicyDescription(this.getAgencyPolicyDescription());
		resp.setAgencyPolicyId(this.getAgencyPolicyId());
		resp.setAgencyPolicyName(this.getAgencyPolicyName());
		resp.setOrderConditionId(this.getOrderConditionId());
		resp.setOrderConditionAgencyPolicyId(this.getOID());
		return resp;
	}
}
