package pd.supervision.supervisionoptions;

import pd.supervision.supervisionoptions.VariableElement;

public class CourtSupervisionOptionVariableElementsVariableElement extends mojo.km.persistence.PersistentObject {
	CourtSupervisionOption parent;
	private String childId;
	private String parentId;
	VariableElement child;
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.VariableElement
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.VariableElement
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.VariableElement
	*/
	private void initChild() {
		if (child == null) {
			try {
				child =
					(VariableElement) new mojo
						.km
						.persistence
						.Reference(childId, VariableElement.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.VariableElement
	*/
	public VariableElement getChild() {
		fetch();
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(VariableElement child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (VariableElement) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent =
					(CourtSupervisionOption) new mojo
						.km
						.persistence
						.Reference(parentId, CourtSupervisionOption.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.CourtSupervisionOption
	*/
	public CourtSupervisionOption getParent() {
		fetch();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(CourtSupervisionOption parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (CourtSupervisionOption) new mojo.km.persistence.Reference(parent).getObject();
	}
}
