package pd.supervision.supervisionoptions;

import pd.codetable.supervision.SupervisionCode;

public class CourtPolicySupervisionEventTypesSupervisionCode extends mojo.km.persistence.PersistentObject {
	CourtPolicy parent;
	private String childId;
	private String parentId;
	SupervisionCode child;
	/**
	* Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	*/
	private void initChild() {
		if (child == null) {
			try {
				child = (SupervisionCode) new mojo.km.persistence.Reference(childId, SupervisionCode.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.supervision.SupervisionCode
	*/
	public SupervisionCode getChild() {
		fetch();
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(SupervisionCode child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (SupervisionCode) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.CourtPolicy
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.CourtPolicy
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.CourtPolicy
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent =
					(CourtPolicy) new mojo.km.persistence.Reference(parentId, CourtPolicy.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.CourtPolicy
	*/
	public CourtPolicy getParent() {
		fetch();
		initParent();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(CourtPolicy parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (CourtPolicy) new mojo.km.persistence.Reference(parent).getObject();
	}
}
