package pd.supervision.supervisionorder;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.supervisionorder.GetOrderValuesFromRelIdsEvent;
import messaging.supervisionorder.SupervisionOrderConditionRelValueEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.supervision.supervisionoptions.ConditionCourtVariableElement;
import pd.supervision.supervisionoptions.VariableElementType;

/**
 * @author dgibler /**
 * @roseuid 43B2E736002E
 */
public class SupervisionOrderConditionRelValue extends PersistentObject {

	/**
	 * @return
	 * @param event
	 * @roseuid 438F22CA0277
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, SupervisionOrderConditionRelValue.class);
	}

	static public Iterator findAllByRelIds(List orderCondRelIds) {
		GetOrderValuesFromRelIdsEvent orderValuesEvent = new GetOrderValuesFromRelIdsEvent();
		orderValuesEvent.setRelIds(orderCondRelIds);
		return findAll(orderValuesEvent);
	}

	/**
	 * @roseuid 43B2B6EF01A5
	 */
	static public SupervisionOrderConditionRelValue create(
			ConditionCourtVariableElement condCrtVarElement,
			Map variableElementReferenceMap) {
		SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = new SupervisionOrderConditionRelValue();
		supervisionOrderConditionRelValue.setIsFixed(condCrtVarElement.getIsFixed());
		supervisionOrderConditionRelValue.setVariableElementTypeId(condCrtVarElement.getVariableElementTypeId());
		if (condCrtVarElement.getIsReference()
				&& variableElementReferenceMap != null) {
			String referenceValue = (String) variableElementReferenceMap.get(condCrtVarElement.getName());
			supervisionOrderConditionRelValue.setValue(referenceValue);
			//2008-07-22 dag - View CSCONCRTVARELTYPE has isFixed set to true for all reference variables which is not
			//correct in PASO when pulling from a suggested order.  
			//What happens if a reference variable VE is updated?  In this case isFixed should be set to true so that the
			//value is not overridden by the resolved reference variable.
			//supervisionOrderConditionRelValue.setIsFixed(true);
			//supervisionOrderConditionRelValue.setIsFixed(true);
			//supervisionOrderConditionRelValue.setIsFixed(false);
		} else  {
			supervisionOrderConditionRelValue.setValue(condCrtVarElement.getValue());
		}

		return supervisionOrderConditionRelValue;
	}
	/**
	 * @roseuid 43B2B6EF01A5
	 */
	static public SupervisionOrderConditionRelValue create(
			SupervisionOrder order,
			ConditionCourtVariableElement condCrtVarElement,
			Map variableElementReferenceMap) {
		SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = new SupervisionOrderConditionRelValue();
		supervisionOrderConditionRelValue.setIsFixed(condCrtVarElement.getIsFixed());
		supervisionOrderConditionRelValue.setVariableElementTypeId(condCrtVarElement.getVariableElementTypeId());
		if (condCrtVarElement.getIsReference()
				&& variableElementReferenceMap != null) {
			String referenceValue = (String) variableElementReferenceMap.get(condCrtVarElement.getName());
			supervisionOrderConditionRelValue.setValue(referenceValue);
			//2008-07-22 dag - View CSCONCRTVARELTYPE has isFixed set to true for all reference variables which is not
			//correct in PASO when pulling from a suggested order.  
			//What happens if a reference variable VE is updated?  In this case isFixed should be set to true so that the
			//value is not overridden by the resolved reference variable.
			//supervisionOrderConditionRelValue.setIsFixed(true);
			//supervisionOrderConditionRelValue.setIsFixed(false);
		} else if (condCrtVarElement.getIsCalculated()){
			Code aCode = Code.find(condCrtVarElement.getElementCodeTableId(), condCrtVarElement.getValue());
			if (aCode != null){
				String aString = SupervisionOrderHelper.calculateDate(order.getCaseSupervisionBeginDate(), aCode.getCode());
				supervisionOrderConditionRelValue.setValue(aString);
			} else {
				supervisionOrderConditionRelValue.setValue(DateUtil.dateToString(order.getCaseSupervisionBeginDate(), DateUtil.DATE_FMT_1));
			}
		} else {
			supervisionOrderConditionRelValue.setValue(condCrtVarElement.getValue());
		}

		return supervisionOrderConditionRelValue;
	}

	/**
	 * @roseuid 43B2B6EF01A5
	 */
//12/29/06 - Dawn commented out.... not being used.
//	static public SupervisionOrderConditionRelValue create(
//			VariableElement variableElement) {
//		SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = new SupervisionOrderConditionRelValue();
//		supervisionOrderConditionRelValue.setIsFixed(variableElement
//				.getIsFixed());
//		supervisionOrderConditionRelValue.setValue(variableElement.getValue());
//		supervisionOrderConditionRelValue
//				.setVariableElementTypeId(variableElement
//						.getVariableElementTypeId());
//		return supervisionOrderConditionRelValue;
//	}

	private boolean isFixed;

	private boolean isRefreshable;

	/**
	 * Properties for supervisionOrderConditionRel
	 */
	private SupervisionOrderConditionRel supervisionOrderConditionRel = null;

	private String supervisionOrderConditionRelId;

	/**
	 * Properties for supervisionOrderRelValueHistories
	 * 
	 * @referencedType pd.supervision.supervisionorder.SupervisionOrderRelValueHistory
	 */
	private java.util.Collection supervisionOrderRelValueHistories = null;

	private String value;

	/**
	 * Properties for variableElementType
	 */
	private VariableElementType variableElementType = null;

	private String variableElementTypeId;

	/**
	 * @roseuid 43B2E736002E
	 */
	public SupervisionOrderConditionRelValue() {
	}

	/**
	 * @return
	 */
	public boolean getIsFixed() {
		fetch();
		return isFixed;
	}

	/**
	 * @return Returns the isRefreshable.
	 */
	public boolean getIsRefreshable() {
		fetch();
		return isRefreshable;
	}

	/**
	 * Gets referenced type
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 */
	public SupervisionOrderConditionRel getSupervisionOrderConditionRel() {
		initSupervisionOrderConditionRel();
		return supervisionOrderConditionRel;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 */
	public String getSupervisionOrderConditionRelId() {
		fetch();
		return supervisionOrderConditionRelId;
	}

	/**
	 * returns a collection of
	 * pd.supervision.supervision.supervisionorder.SupervisionOrderRelValueHistory
	 */
	public java.util.Collection getSupervisionOrderRelValueHistories() {
		fetch();
		initSupervisionOrderRelValueHistories();
		return supervisionOrderRelValueHistories;
	}

	/**
	 * @return
	 */
	public String getValue() {
		fetch();
		return value;
	}

	/**
	 * @return
	 */
	public VariableElementType getVariableElementType() {
		fetch();
		initVariableElementType();
		return variableElementType;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionoptions.VariableElementType
	 */
	public String getVariableElementTypeId() {
		fetch();
		return variableElementTypeId;
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 */
	private void initSupervisionOrderConditionRel() {
		if (supervisionOrderConditionRel == null) {
			supervisionOrderConditionRel = (SupervisionOrderConditionRel) new mojo.km.persistence.Reference(
					supervisionOrderConditionRelId,
					SupervisionOrderConditionRel.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.supervisionorder.SupervisionOrderRelValueHistory
	 */
	private void initSupervisionOrderRelValueHistories() {
		if (supervisionOrderRelValueHistories == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			supervisionOrderRelValueHistories = new mojo.km.persistence.ArrayList(
					SupervisionOrderRelValueHistory.class,
					"supervisionOrderConditionRelValueId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionoptions.VariableElementType
	 */
	private void initVariableElementType() {
		if (variableElementType == null) {
			variableElementType = (VariableElementType) new mojo.km.persistence.Reference(
					variableElementTypeId,
					VariableElementType.class)
					.getObject();
		}
	}

	/**
	 * @roseuid 43B2B6EF01A5
	 */
	public void populate(SupervisionOrderConditionRelValueEvent variableElement) {
		this.setIsFixed(variableElement.isFixed());
		this.setValue(variableElement.getValue());
		this.setVariableElementTypeId(variableElement
				.getVariableElementTypeId());
	}

	/**
	 * @param b
	 */
	public void setIsFixed(boolean b) {
		if (this.isFixed != b) {
			markModified();
		}
		isFixed = b;
	}

	/**
	 * @param aIsRefreshable
	 *            The isRefreshable to set.
	 */
	public void setIsRefreshable(boolean b) {
		if (this.isRefreshable != b) {
			markModified();
		}
		isRefreshable = b;
	}

	/**
	 * set the type reference for class member supervisionOrderConditionRel
	 */
	public void setSupervisionOrderConditionRel(
			SupervisionOrderConditionRel aSupervisionOrderConditionRel) {
		if (this.supervisionOrderConditionRel == null
				|| !this.supervisionOrderConditionRel
						.equals(aSupervisionOrderConditionRel)) {
			markModified();
		}
		if (aSupervisionOrderConditionRel.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSupervisionOrderConditionRel);
		}
		setSupervisionOrderConditionRelId(""
				+ aSupervisionOrderConditionRel.getOID());
		this.supervisionOrderConditionRel = (SupervisionOrderConditionRel) new mojo.km.persistence.Reference(
				aSupervisionOrderConditionRel).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRel
	 */
	public void setSupervisionOrderConditionRelId(
			String aSupervisionOrderConditionRelId) {
		if (this.supervisionOrderConditionRelId == null
				|| !this.supervisionOrderConditionRelId
						.equals(aSupervisionOrderConditionRelId)) {
			markModified();
		}
		supervisionOrderConditionRel = null;
		this.supervisionOrderConditionRelId = aSupervisionOrderConditionRelId;
	}

	/**
	 * @param theValue
	 */
	public void setValue(String theValue) {
		if (this.value == null || !this.value.equals(theValue)) {
			markModified();
		}
		value = theValue;
	}

	/**
	 * set the type reference for class member variableElementType
	 */
	public void setVariableElementType(
			VariableElementType aVariableElementType) {
		if (this.variableElementType == null
				|| !this.variableElementType.equals(aVariableElementType)) {
			markModified();
		}
		if (aVariableElementType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aVariableElementType);
		}
		setVariableElementTypeId("" + aVariableElementType.getOID());
		this.variableElementType = (VariableElementType) new mojo.km.persistence.Reference(
				aVariableElementType).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionoptions.VariableElementType
	 */
	public void setVariableElementTypeId(String aVariableElementTypeId) {
		if (this.variableElementTypeId == null
				|| !this.variableElementTypeId.equals(aVariableElementTypeId)) {
			markModified();
		}
		variableElementType = null;
		this.variableElementTypeId = aVariableElementTypeId;
	}

	/**
	 * insert a pd.supervision.supervisionorder.SupervisionOrderRelValHistory
	 * into class relationship collection.
	 */
	public void insertSupervisionOrderRelValHistories(
			SupervisionOrderRelValueHistory anObject) {
		
		initSupervisionOrderRelValueHistories();
		supervisionOrderRelValueHistories.add(anObject);
	}

}
