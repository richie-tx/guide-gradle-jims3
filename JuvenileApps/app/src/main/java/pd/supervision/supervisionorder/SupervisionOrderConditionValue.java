package pd.supervision.supervisionorder;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 442ABAF300EA
*/
public class SupervisionOrderConditionValue extends PersistentObject {
	private String defendantId;
	private String orderId;
	private String orderStatus;
	private String courtId;
	private String variableElementTypeId;
	private String criminalCaseId;
	private String agencyId;
	private String value;
	private String conditionId;
	/**
	* @roseuid 442ABAF300EA
	*/
	public SupervisionOrderConditionValue() {
	}

	/**
	* Finds objects by an event
	* @return Iterator of SupervisionOrderConditionValue
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator orderCondValues = home.findAll(event, SupervisionOrderConditionValue.class);
		return orderCondValues;
	}
	/**
	* Finds all SupervisionOrderConditionValues by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator orderCondValues = home.findAll(attributeName, attributeValue, SupervisionOrderConditionValue.class);
		return orderCondValues;
	}
	
	/**
	* Access method for the orderId property.
	* @return the current value of the orderId property
	*/
	public String getOrderId() {
		fetch();
		return orderId;
	}
	/**
	* Sets the value of the orderId property.
	* @param aOrderId the new value of the orderId property
	*/
	public void setOrderId(String aOrderId) {
		if (this.orderId == null || !this.orderId.equals(aOrderId)) {
			markModified();
		}
		orderId = aOrderId;
	}
	/**
	* Access method for the conditionId property.
	* @return the current value of the conditionId property
	*/
	public String getConditionId() {
		fetch();
		return conditionId;
	}
	/**
	* Sets the value of the conditionId property.
	* @param aConditionId the new value of the conditionId property
	*/
	public void setConditionId(String aConditionId) {
		if (this.conditionId == null || !this.conditionId.equals(aConditionId)) {
			markModified();
		}
		conditionId = aConditionId;
	}
	/**
	* Access method for the defendantId property.
	* @return the current value of the defendantId property
	*/
	public String getDefendantId() {
		fetch();
		return defendantId;
	}
	/**
	* Sets the value of the defendantId property.
	* @param aDefendantId the new value of the defendantId property
	*/
	public void setDefendantId(String aDefendantId) {
		if (this.defendantId == null || !this.defendantId.equals(aDefendantId)) {
			markModified();
		}
		defendantId = aDefendantId;
	}
	/**
	* Access method for the variableElementTypeId property.
	* @return the current value of the variableElementTypeId property
	*/
	public String getVariableElementTypeId() {
		fetch();
		return variableElementTypeId;
	}
	/**
	* Sets the value of the variableElementTypeId property.
	* @param aVariableElementTypeId the new value of the variableElementTypeId property
	*/
	public void setVariableElementTypeId(String aVariableElementTypeId) {
		if (this.variableElementTypeId == null || !this.variableElementTypeId.equals(aVariableElementTypeId)) {
			markModified();
		}
		variableElementTypeId = aVariableElementTypeId;
	}
	/**
	* Access method for the value property.
	* @return the current value of the value property
	*/
	public String getValue() {
		fetch();
		return value;
	}
	/**
	* Sets the value of the value property.
	* @param aValue the new value of the value property
	*/
	public void setValue(String aValue) {
		if (this.value == null || !this.value.equals(aValue)) {
			markModified();
		}
		value = aValue;
	}
	/**
	* Access method for the courtId property.
	* @return the current value of the courtId property
	*/
	public String getCourtId() {
		fetch();
		return courtId;
	}
	/**
	* Sets the value of the courtId property.
	* @param aCourtId the new value of the courtId property
	*/
	public void setCourtId(String aCourtId) {
		if (this.courtId == null || !this.courtId.equals(aCourtId)) {
			markModified();
		}
		courtId = aCourtId;
	}
	/**
	* Access method for the criminalCaseId property.
	* @return the current value of the criminalCaseId property
	*/
	public String getCriminalCaseId() {
		fetch();
		return criminalCaseId;
	}
	/**
	* Sets the value of the criminalCaseId property.
	* @param aCriminalCaseId the new value of the criminalCaseId property
	*/
	public void setCriminalCaseId(String aCriminalCaseId) {
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(aCriminalCaseId)) {
			markModified();
		}
		criminalCaseId = aCriminalCaseId;
	}
	/**
	* Access method for the agencyId property.
	* @return the current value of the agencyId property
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Sets the value of the agencyId property.
	* @param aAgencyId the new value of the agencyId property
	*/
	public void setAgencyId(String aAgencyId) {
		if (this.agencyId == null || !this.agencyId.equals(aAgencyId)) {
			markModified();
		}
		agencyId = aAgencyId;
	}
	/**
	* Access method for the orderStatus property.
	* @return the current value of the orderStatus property
	*/
	public String getOrderStatus() {
		fetch();
		return orderStatus;
	}
	/**
	* Sets the value of the orderStatus property.
	* @param aOrderStatus the new value of the orderStatus property
	*/
	public void setOrderStatus(String aOrderStatus) {
		if (this.orderStatus == null || !this.orderStatus.equals(aOrderStatus)) {
			markModified();
		}
		orderStatus = aOrderStatus;
	}
	/**
	* @roseuid 442AB98C035B
	*/
	public void find() {
		fetch();
	}
}
