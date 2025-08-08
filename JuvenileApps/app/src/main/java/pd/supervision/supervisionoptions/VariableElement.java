package pd.supervision.supervisionoptions;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 42F7C40102DE
*/
public class VariableElement extends PersistentObject {
	/**
	* Properties for variableElementType
	*/
	public VariableElementType variableElementType = null;
	/**
	* Properties for variableElements
	* @associationType simple
	* @referencedType pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	private java.util.Collection courtSupervisionOptions = null;
	private String variableElementTypeId;
	private String value;
	private boolean isFixed=true;
	
	/**
	* @roseuid 42F7C40102DE
	*/
	public VariableElement() {
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
	* @param courtPolicyId
	* @roseuid 42F7997D0255
	*/
	public void find(String courtPolicyId) {
		fetch();
	}
	/**
	* @roseuid 42F7997D0261
	*/
	public void bind() {
		markModified();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.VariableElementType
	*/
	public void setVariableElementTypeId(String variableElementTypeId) {
		if (this.variableElementTypeId == null || !this.variableElementTypeId.equals(variableElementTypeId)) {
			markModified();
		}
		variableElementType = null;
		this.variableElementTypeId = variableElementTypeId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.VariableElementType
	*/
	public String getVariableElementTypeId() {
		fetch();
		return variableElementTypeId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.VariableElementType
	*/
	private void initVariableElementType() {
		if (variableElementType == null) {
			try {
				variableElementType =
					(VariableElementType) new mojo
						.km
						.persistence
						.Reference(variableElementTypeId, VariableElementType.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.VariableElementType
	*/
	public VariableElementType getVariableElementType() {
		fetch();
		initVariableElementType();
		return variableElementType;
	}
	/**
	* set the type reference for class member variableElementType
	*/
	public void setVariableElementType(VariableElementType variableElementType) {
		if (this.variableElementType == null || !this.variableElementType.equals(variableElementType)) {
			markModified();
		}
		if (variableElementType.getOID() == null) {
			new mojo.km.persistence.Home().bind(variableElementType);
		}
		setVariableElementTypeId("" + variableElementType.getOID());
		this.variableElementType = (VariableElementType) new mojo.km.persistence.Reference(variableElementType).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public boolean getIsFixed() {
		fetch();
		return isFixed;
	}
	/**
	* set the type reference for class member type
	*/
	public void setIsFixed(boolean fixed) {
		if (this.isFixed != fixed) {
			markModified();
		}
		this.isFixed = fixed;
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	private void initCourtSupervisionOptions() {
		if (courtSupervisionOptions == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				courtSupervisionOptions =
					new mojo.km.persistence.ArrayList(
						VariableElementCourtSupervisionOptionsCourtSupervisionOption.class,
						"parentId",
						"" + getOID());
			} catch (Throwable t) {
				courtSupervisionOptions = new java.util.ArrayList();
			}
		}
	}
	/**
	* returns a collection of pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public java.util.Collection getCourtSupervisionOptions() {
		initCourtSupervisionOptions();
		java.util.ArrayList retVal = new java.util.ArrayList();
		java.util.Iterator i = courtSupervisionOptions.iterator();
		while (i.hasNext()) {
			VariableElementCourtSupervisionOptionsCourtSupervisionOption actual =
				(VariableElementCourtSupervisionOptionsCourtSupervisionOption) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* insert a pd.supervision.supervisionoptions.CourtSupervisionOption into class relationship collection.
	*/
	public void insertCourtSupervisionOptions(CourtSupervisionOption anObject) {
		initCourtSupervisionOptions();
		VariableElementCourtSupervisionOptionsCourtSupervisionOption actual =
			new VariableElementCourtSupervisionOptionsCourtSupervisionOption();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		courtSupervisionOptions.add(actual);
	}
	/**
	* Removes a pd.supervision.supervisionoptions.CourtSupervisionOption from class relationship collection.
	*/
	public void removeCourtSupervisionOptions(CourtSupervisionOption anObject) {
		initCourtSupervisionOptions();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			VariableElementCourtSupervisionOptionsCourtSupervisionOption actual =
				(VariableElementCourtSupervisionOptionsCourtSupervisionOption) new mojo
					.km
					.persistence
					.Reference(assocEvent, VariableElementCourtSupervisionOptionsCourtSupervisionOption.class)
					.getObject();
			courtSupervisionOptions.remove(actual);
		} catch (Throwable t) {
		}
	}
	/**
	* Clears all pd.supervision.supervisionoptions.CourtSupervisionOption from class relationship collection.
	*/
	public void clearCourtSupervisionOptions() {
		initCourtSupervisionOptions();
		courtSupervisionOptions.clear();
	}
}
