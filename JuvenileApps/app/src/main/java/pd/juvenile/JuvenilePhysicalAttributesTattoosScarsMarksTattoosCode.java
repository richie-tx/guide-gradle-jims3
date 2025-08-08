package pd.juvenile;

import pd.codetable.person.ScarsMarksTattoosCode;

public class JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode extends mojo.km.persistence.PersistentObject {
	JuvenilePhysicalAttributes parent;
	private String childId;
	private String parentId;
	private String otherTattooComment = null;
	private String entryDate;
	ScarsMarksTattoosCode child;
	/**
	* Set the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.ScarsMarksTattoosCode
	*/
	private void initChild() {
		if (child == null) {
			try {
				child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(childId, ScarsMarksTattoosCode.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.person.ScarsMarksTattoosCode
	*/
	public ScarsMarksTattoosCode getChild() {
		fetch();
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(ScarsMarksTattoosCode child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.JuvenilePhysicalAttributes
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.JuvenilePhysicalAttributes
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.JuvenilePhysicalAttributes
	*/
	private void initParent() {
		if (parent == null) {
			try {
				parent = (JuvenilePhysicalAttributes) new mojo.km.persistence.Reference(parentId, JuvenilePhysicalAttributes.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.JuvenilePhysicalAttributes
	*/
	public JuvenilePhysicalAttributes getParent() {
		fetch();
		initParent();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(JuvenilePhysicalAttributes parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (JuvenilePhysicalAttributes) new mojo.km.persistence.Reference(parent).getObject();
	}
	
	public String getOtherTattooComment() {
		return otherTattooComment;
	}
	
	public void setOtherTattooComment(String otherTattooComment) {
		this.otherTattooComment = otherTattooComment;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
}
