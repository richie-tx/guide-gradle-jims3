package pd.supervision.supervisionoptions;

import pd.codetable.Code;

public class ConditionSupervisionTypesCode extends mojo.km.persistence.PersistentObject {
	Code child;
	private String parentId;
	private String childId;
	Condition parent;
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initChild() {
		if (child == null) {
			child =
				(Code) new mojo
					.km
					.persistence
					.Reference(childId, Code.class, "SUPERVISION_TYPE")
					.getObject();
//			child = (pd.codetable.Code) new mojo.km.persistence.Reference(childId, pd.codetable.Code.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getChild() {
		fetch();
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(Code child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
//		setChildId("" + child.getOID());
		setChildId(child.getCode());
		this.child = (Code) new mojo.km.persistence.Reference(child).getObject();
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
		initParent();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	*/
	private void initParent() {
		if (parent == null) {
			parent =
				(Condition) new mojo.km.persistence.Reference(parentId, Condition.class).getObject();
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
