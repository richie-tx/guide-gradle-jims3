/*
 * Created on Sep 21, 2006
 *
 */
package pd.supervision.supervisionoptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import messaging.supervisionoptions.GetConditionCourtVariableElementsEvent;
import messaging.supervisionoptions.GetCourtPolicyCourtVariableElementsEvent;
import messaging.supervisionoptions.GetCourtVariableElementsByConditionEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author asrvastava
 */
public class ConditionCourtVariableElement extends PersistentObject{

	private String objectId; // condition or CourtPolicy
	private String courtId;
	private String agencyId;
	private String variableElementTypeId;
	private String value;
	private boolean isFixed;
	private boolean isReference;
	private String name;
	private String type;
	private String variableElementId;
	private String sampleValue;
	private String description;
	private boolean isEnumeration;
	private String elementCodeTableId;
	private String enumerationTypeId;
	private String courtSupOptionId;
	private boolean isExceptionCourt;
	private boolean isCalculated;
	private String dataType;
	
	/**
	 * @return Returns the isExceptionCourt.
	 */
	public boolean getIsExceptionCourt() {
		return isExceptionCourt;
	}
	/**
	 * @param isExceptionCourt The isExceptionCourt to set.
	 */
	public void setIsExceptionCourt(boolean isExceptionCourt) {
		this.isExceptionCourt = isExceptionCourt;
	}
	/**
	 * @return Returns the courtSupOptionId.
	 */
	public String getCourtSupOptionId() {
		return courtSupOptionId;
	}
	/**
	 * @param courtSupOptionId The courtSupOptionId to set.
	 */
	public void setCourtSupOptionId(String courtSupOptionId) {
		this.courtSupOptionId = courtSupOptionId;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the sampleValue.
	 */
	public String getSampleValue() {
		fetch();
		return sampleValue;
	}
	/**
	 * @param sampleValue The sampleValue to set.
	 */
	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}
	/**
	 * @return Returns the variableElementId.
	 */
	public String getVariableElementId() {
		fetch();
		return variableElementId;
	}
	/**
	 * @param variableElementId The variableElementId to set.
	 */
	public void setVariableElementId(String variableElementId) {
		this.variableElementId = variableElementId;
	}
	/**
	 * @return Returns the elementCodeTableId.
	 */
	public String getElementCodeTableId() {
		fetch();
		return elementCodeTableId;
	}
	/**
	 * @param elementCodeTableId The elementCodeTableId to set.
	 */
	public void setElementCodeTableId(String elementCodeTableId) {
		this.elementCodeTableId = elementCodeTableId;
	}
	/**
	 * @return Returns the enumerationTypeId.
	 */
	public String getEnumerationTypeId() {
		fetch();
		return enumerationTypeId;
	}
	/**
	 * @param enumerationTypeId The enumerationTypeId to set.
	 */
	public void setEnumerationTypeId(String enumerationTypeId) {
		fetch();
		this.enumerationTypeId = enumerationTypeId;
	}
	/**
	 * @return Returns the isEnumeration.
	 */
	public boolean getIsEnumeration() {
		return isEnumeration;
	}
	/**
	 * @param isEnumeration The isEnumeration to set.
	 */
	public void setIsEnumeration(boolean isEnumeration) {
		fetch();
		this.isEnumeration = isEnumeration;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		fetch();
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	* @return 
	* @param event
	* @roseuid 438F22CA0277
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, ConditionCourtVariableElement.class);
	}

	/**
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, ConditionCourtVariableElement.class);
	}
	
	/**
	* @return 
	* @param event
	* @roseuid 438F22CA0277
	*/
	static public Iterator findAll(String courtId, String agencyId, Collection conditionIds)
	{
		GetConditionCourtVariableElementsEvent event = new GetConditionCourtVariableElementsEvent();
		event.setAgencyId(agencyId);
		event.setCourtId(courtId);
		event.setConditionIds((List)conditionIds);
		return findAll(event);
	}

	/**
	* @return 
	* @param event
	* @roseuid 438F22CA0277
	*/
	static public Iterator findAllByCondition(String conditionId, String agencyId)
	{
	    GetCourtVariableElementsByConditionEvent event = new GetCourtVariableElementsByConditionEvent();
		event.setAgencyId(agencyId);
		event.setConditionId(conditionId);
		return findAll(event);
	}

	/**
	* @return 
	* @param event
	* @roseuid 438F22CA0277
	*/
	static public Iterator findAllByCourtPolicy(String courtPolicyId, String agencyId)
	{
	    GetCourtPolicyCourtVariableElementsEvent event = new GetCourtPolicyCourtVariableElementsEvent();
		event.setAgencyId(agencyId);
		event.setCourtPolicyId(courtPolicyId);
		return findAll(event);
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		fetch();
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the conditionId.
	 */
	public String getObjectId() {
		fetch();
		return objectId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		fetch();
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @return Returns the isFixed.
	 */
	public boolean getIsFixed() {
		fetch();
		return isFixed;
	}
	/**
	 * @param isFixed The isFixed to set.
	 */
	public void setIsFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}
	/**
	 * @return Returns the isReference.
	 */
	public boolean getIsReference() {
		fetch();
		return isReference;
	}
	/**
	 * @param isReference The isReference to set.
	 */
	public void setIsReference(boolean isReference) {
		this.isReference = isReference;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return Returns the variableElementTypeId.
	 */
	public String getVariableElementTypeId() {
		return variableElementTypeId;
	}
	/**
	 * @param variableElementTypeId The variableElementTypeId to set.
	 */
	public void setVariableElementTypeId(String variableElementTypeId) {
		this.variableElementTypeId = variableElementTypeId;
	}
	public boolean getIsCalculated() {
		return isCalculated;
	}
	public void setIsCalculated(boolean isCalculated) {
		this.isCalculated = isCalculated;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	
}
