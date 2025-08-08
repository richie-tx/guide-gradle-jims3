package pd.supervision.supervisionoptions;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.ICodetable;
import pd.contact.agency.Agency;

public class VariableElementTypeAgenciesAgency extends mojo.km.persistence.PersistentObject implements ICodetable {
	Agency child;
	private String parentId;
	private String childId;
	VariableElementType parent;
	/**
	* Set the reference value to class :: pd.contact.agency.Agency
	*/
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	/**
	* Get the reference value to class :: pd.contact.agency.Agency
	*/
	public String getChildId() {
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initChild() {
		if (child == null) {
			child = (Agency) new mojo.km.persistence.Reference(childId, Agency.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Agency getChild() {
		fetch();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(Agency child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (Agency) new mojo.km.persistence.Reference(child).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionoptions.VariableElementType
	*/
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionoptions.VariableElementType
	*/
	public String getParentId() {
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionoptions.VariableElementType
	*/
	private void initParent() {
		if (parent == null) {
			parent =
				(VariableElementType) new mojo
					.km
					.persistence
					.Reference(parentId, VariableElementType.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.supervision.supervisionoptions.VariableElementType
	*/
	public VariableElementType getParent() {
		fetch();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(VariableElementType parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (VariableElementType) new mojo.km.persistence.Reference(parent).getObject();
	}
	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(VariableElementTypeAgenciesAgency.class);
	}

	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
	static public VariableElementTypeAgenciesAgency find(String vetId) {
		Home home = new Home();
		return (VariableElementTypeAgenciesAgency) home
				.find(vetId, VariableElementTypeAgenciesAgency.class);
	}	
	
}
