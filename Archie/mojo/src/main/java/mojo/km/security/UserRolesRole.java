// no longer in use. Migrated to SM. Refer US #87188.references in the mapping file commented.
package mojo.km.security;

import mojo.km.security.Role;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.*;
import java.util.Iterator;


public class UserRolesRole extends PersistentObject {
	/*User parent;
	private String childId;
	private String parentId;
	Role child;
	*//**
	* Set the reference value to class :: mojo.km.security.Role
	*//*
	public void setChildId(String childId) {
		if (this.childId == null || !this.childId.equals(childId)) {
			markModified();
		}
		child = null;
		this.childId = childId;
	}
	*//**
	* Get the reference value to class :: mojo.km.security.Role
	*//*
	public String getChildId() {
		fetch();
		return childId;
	}
	*//**
	* Initialize class relationship to class mojo.km.security.Role
	*//*
	private void initChild() {
		if (child == null) {
			try {
				child = (mojo.km.security.Role) new mojo.km.persistence.Reference(childId, mojo.km.security.Role.class).getObject();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
	*//**
	* Gets referenced type mojo.km.security.Role
	*//*
	public mojo.km.security.Role getChild() {
		fetch();
		initChild();
		return child;
	}
	*//**
	* set the type reference for class member child
	*//*
	public void setChild(mojo.km.security.Role child) {
		if (this.child == null || !this.child.equals(child)) {
			markModified();
		}
		if (child.getOID() == null) {
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (mojo.km.security.Role) new mojo.km.persistence.Reference(child).getObject();
	}
	*//**
	* Set the reference value to class :: mojo.km.security.User
	*//*
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId)) {
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
	*//**
	* Get the reference value to class :: mojo.km.security.User
	*//*
	public String getParentId() {
		fetch();
		return parentId;
	}
	*//**
	* Initialize class relationship to class mojo.km.security.User
	*//*
	private void initParent() {
		if (parent == null) {
			try {
				parent = (mojo.km.security.User) new mojo.km.persistence.Reference(parentId, mojo.km.security.User.class).getObject();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
	*//**
	* Gets referenced type mojo.km.security.User
	*//*
	public mojo.km.security.User getParent() {
		fetch();
		initParent();
		return parent;
	}
	*//**
	* set the type reference for class member parent
	*//*
	public void setParent(mojo.km.security.User parent) {
		if (this.parent == null || !this.parent.equals(parent)) {
			markModified();
		}
		if (parent.getOID() == null) {
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (mojo.km.security.User) new mojo.km.persistence.Reference(parent).getObject();
	}
	
	public static Iterator findAll(String attributeName, String attributeValue){
        Home home = new Home();
        return home.findAll(attributeName, attributeValue, UserRolesRole.class);
	}

	public static Iterator findAll(IEvent event){
        Home home = new Home();
        return home.findAll(event, UserRolesRole.class);
	}*/
}
