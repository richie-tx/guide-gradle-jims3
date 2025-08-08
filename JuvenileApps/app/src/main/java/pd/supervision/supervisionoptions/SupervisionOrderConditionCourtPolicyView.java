package pd.supervision.supervisionoptions;

import java.util.Iterator;

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
public class SupervisionOrderConditionCourtPolicyView extends PersistentObject {
	private int orderConditionId;
	private int conditionId;
	private int courtPolicyId;
	private String courtPolicyName;
	private String courtPolicyDescription;
	private String defendantId;	
	
	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, SupervisionOrderConditionCourtPolicyView.class);
	}
	
	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAllByNumericParameter(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, new Integer(attributeValue), SupervisionOrderConditionCourtPolicyView.class);
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
	 * @return Returns the courtPolicyDescription.
	 */
	public String getCourtPolicyDescription() {
		return courtPolicyDescription;
	}
	/**
	 * @param courtPolicyDescription The courtPolicyDescription to set.
	 */
	public void setCourtPolicyDescription(String courtPolicyDescription) {
		this.courtPolicyDescription = courtPolicyDescription;
	}
	/**
	 * @return Returns the courtPolicyId.
	 */
	public int getCourtPolicyId() {
		return courtPolicyId;
	}
	/**
	 * @param courtPolicyId The courtPolicyId to set.
	 */
	public void setCourtPolicyId(int courtPolicyId) {
		this.courtPolicyId = courtPolicyId;
	}
	/**
	 * @return Returns the courtPolicyName.
	 */
	public String getCourtPolicyName() {
		return courtPolicyName;
	}
	/**
	 * @param courtPolicyName The courtPolicyName to set.
	 */
	public void setCourtPolicyName(String courtPolicyName) {
		this.courtPolicyName = courtPolicyName;
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

	public OrderConditionCourtPolicyResponseEvent getResponseEvent(){
		OrderConditionCourtPolicyResponseEvent resp = new OrderConditionCourtPolicyResponseEvent();
		resp.setConditionId(this.getConditionId());
		resp.setCourtPolicyDescription(this.getCourtPolicyDescription());
		resp.setCourtPolicyId(this.getCourtPolicyId());
		resp.setCourtPolicyName(this.getCourtPolicyName());
		resp.setOrderConditionId(this.getOrderConditionId());
		resp.setOrderConditionCourtPolicyId(this.getOID());
		return resp;
	}
}
