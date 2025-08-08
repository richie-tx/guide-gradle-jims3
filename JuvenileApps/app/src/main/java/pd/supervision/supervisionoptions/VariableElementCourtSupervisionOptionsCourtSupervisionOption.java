package pd.supervision.supervisionoptions;

import pd.supervision.supervisionoptions.CourtSupervisionOption;

public class VariableElementCourtSupervisionOptionsCourtSupervisionOption extends mojo.km.persistence.PersistentObject {
	VariableElement parent;
	private String childId;
	private String parentId;
	CourtSupervisionOption child;
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	private void initChild() {
		if (child == null) {
			try {
				child =
					(CourtSupervisionOption) new mojo
						.km
						.persistence
						.Reference(childId, CourtSupervisionOption.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public CourtSupervisionOption getChild() {
		fetch();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(CourtSupervisionOption child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (CourtSupervisionOption) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.VariableElement
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.VariableElement
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.VariableElement
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent =
					(VariableElement) new mojo
						.km
						.persistence
						.Reference(parentId, VariableElement.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.VariableElement
	*/
	public VariableElement getParent() {
		fetch();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(VariableElement parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (VariableElement) new mojo.km.persistence.Reference(parent).getObject();
	}
}
