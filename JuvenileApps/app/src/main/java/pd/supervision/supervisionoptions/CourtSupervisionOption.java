package pd.supervision.supervisionoptions;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.Court;

/**
* @roseuid 42F7C4100271
*/
public class CourtSupervisionOption extends PersistentObject {
	private String courtId;
	/**
	* Properties for court
	*/
	private Court court = null;
	/**
	* Properties for variableElements
	* @associationType simple
	* @referencedType pd.supervision.supervisionoptions.VariableElement
	*/
	private java.util.Collection variableElements = null;
	/**
	* Properties for court
	*/
	private boolean isExceptionCourt;
	/**
	* @roseuid 42F7C4100271
	*/
	public CourtSupervisionOption() {
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.VariableElement
	*/
	private void initVariableElements() {
		if (variableElements == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				variableElements =
					new mojo.km.persistence.ArrayList(CourtSupervisionOptionVariableElementsVariableElement.class, "parentId", "" + getOID());
			} catch (Throwable t) {
				variableElements = new java.util.ArrayList();
			}
		}
	}
	/**
	* returns a collection of pd.supervision.supervisionoptions.VariableElement
	*/
	public java.util.Collection getVariableElements() {
		initVariableElements();
		java.util.ArrayList retVal = new java.util.ArrayList();
		Iterator i = variableElements.iterator();
		while (i.hasNext()) {
			CourtSupervisionOptionVariableElementsVariableElement actual =
				(CourtSupervisionOptionVariableElementsVariableElement) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* insert a pd.supervision.supervisionoptions.VariableElement into class relationship collection.
	*/
	public void insertVariableElements(VariableElement anObject) {
		initVariableElements();
		CourtSupervisionOptionVariableElementsVariableElement actual =
			new CourtSupervisionOptionVariableElementsVariableElement();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		variableElements.add(actual);
	}
	/**
	* Removes a pd.supervision.supervisionoptions.VariableElement from class relationship collection.
	*/
	public void removeVariableElements(VariableElement anObject) {
		initVariableElements();

		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId(anObject.getOID());
		assocEvent.setParentId(this.getOID());
		CourtSupervisionOptionVariableElementsVariableElement actual =
			(CourtSupervisionOptionVariableElementsVariableElement) new mojo
				.km
				.persistence
				.Reference(assocEvent, CourtSupervisionOptionVariableElementsVariableElement.class)
				.getObject();
		variableElements.remove(actual);
	}
	/**
	* Clears all pd.supervision.supervisionoptions.VariableElement from class relationship collection.
	*/
	public void clearVariableElements() {
		initVariableElements();
		variableElements.clear();
	}
	/**
	* Set the reference value to class :: pd.supervision.Court
	*/
	public void setCourtId(String courtId) {
		if (this.courtId == null || !this.courtId.equals(courtId)) {
			markModified();
		}
		court = null;
		this.courtId = courtId;
	}
	/**
	* Get the reference value to class :: pd.supervision.Court
	*/
	public String getCourtId() {
		fetch();
		return courtId;
	}
	/**
	* Initialize class relationship to class pd.supervision.Court
	*/
	private void initCourt() {
		if (court == null) {
			try {
				court = (Court) new mojo.km.persistence.Reference(courtId, Court.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.Court
	*/
	public Court getCourt() {
		fetch();
		initCourt();
		return court;
	}
	/**
	* set the type reference for class member court
	*/
	public void setCourt(Court court) {
		if (this.court == null || !this.court.equals(court)) {
			markModified();
		}
		if (court.getOID() == null) {
			new mojo.km.persistence.Home().bind(court);
		}
		setCourtId("" + court.getOID());
		this.court = (Court) new mojo.km.persistence.Reference(court).getObject();
	}
	/**
	* @return 
	*/
	public boolean getIsExceptionCourt() {
		fetch();
		return isExceptionCourt;
	}
	/**
	* @param b
	*/
	public void setIsExceptionCourt(boolean b) {
		if (this.isExceptionCourt != b) {
			markModified();
		}
		isExceptionCourt = b;
	}
	/**
	* Finds all CourtSupervisionOption by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator courtSupervisionOptions = home.findAll(attributeName, attributeValue, CourtSupervisionOption.class);
		return courtSupervisionOptions;
	}	
}
