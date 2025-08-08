package pd.juvenilecase;

import pd.juvenilecase.JuvenileTrait;

public class JuvenileTraitTraitsJuvenileTrait extends mojo.km.persistence.PersistentObject {
	JuvenileTrait parent;
	private String childId;
	private String parentId;
	JuvenileTrait child;
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileTrait
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileTrait
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.JuvenileTrait
	*/
	private void initChild() {
		if (child == null) {
			try {
				child = (JuvenileTrait) new mojo.km.persistence.Reference(childId, JuvenileTrait.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.JuvenileTrait
	*/
	public JuvenileTrait getChild() {
		fetch();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(JuvenileTrait child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (JuvenileTrait) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenileTrait
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenileTrait
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.JuvenileTrait
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent = (JuvenileTrait) new mojo.km.persistence.Reference(parentId, JuvenileTrait.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.JuvenileTrait
	*/
	public JuvenileTrait getParent() {
		fetch();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(JuvenileTrait parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (JuvenileTrait) new mojo.km.persistence.Reference(parent).getObject();
	}
}
