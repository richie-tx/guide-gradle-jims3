package pd.supervision.supervisionoptions;

import pd.supervision.supervisionoptions.SupervisionEventType;

public class ConditionSupervisionEventTypesSupervisionEventType extends mojo.km.persistence.PersistentObject {
	Condition parent;
	private String childId;
	private String parentId;
	SupervisionEventType child;
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.SupervisionEventType
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.SupervisionEventType
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.SupervisionEventType
	*/
	private void initChild() {
		if (child == null) {
			try {
				child =
					(SupervisionEventType) new mojo
						.km
						.persistence
						.Reference(childId, SupervisionEventType.class)
						.getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.SupervisionEventType
	*/
	public SupervisionEventType getChild() {
		fetch();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(SupervisionEventType child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (SupervisionEventType) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.Condition
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent =
					(Condition) new mojo.km.persistence.Reference(parentId, Condition.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.Condition
	*/
	public Condition getParent() {
		fetch();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(Condition parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (Condition) new mojo.km.persistence.Reference(parent).getObject();
	}
}
