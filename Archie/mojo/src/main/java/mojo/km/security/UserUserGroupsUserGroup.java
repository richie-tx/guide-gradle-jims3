
// no longer in use. Migrated to SM. Refer US #87188. references in the mapping file commented.
package mojo.km.security;

import mojo.km.security.UserGroup;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.*;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.*;
import java.util.Iterator;

public class UserUserGroupsUserGroup extends
        PersistentObject {
  /*  User parent;

    private String childId;

    private String parentId;

    UserGroup child;

    *//**
     * Set the reference value to class :: mojo.km.security.UserGroup
     *//*
    public void setChildId(String childId) {
        if (this.childId == null || !this.childId.equals(childId)) {
            markModified();
        }
        child = null;
        this.childId = childId;
    }

    *//**
     * Get the reference value to class :: mojo.km.security.UserGroup
     *//*
    public String getChildId() {
        fetch();
        return childId;
    }

    *//**
     * Initialize class relationship to class mojo.km.security.UserGroup
     *//*
    private void initChild() {
        if (child == null) {
            try {
                child = (mojo.km.security.UserGroup) new mojo.km.persistence.Reference(
                        childId, mojo.km.security.UserGroup.class).getObject();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    *//**
     * Gets referenced type mojo.km.security.UserGroup
     *//*
    public mojo.km.security.UserGroup getChild() {
        fetch();
        this.initChild();
        return child;
    }

    *//**
     * set the type reference for class member child
     *//*
    public void setChild(mojo.km.security.UserGroup child) {
        if (this.child == null || !this.child.equals(child)) {
            markModified();
        }
        if (child.getOID() == null) {
            new mojo.km.persistence.Home().bind(child);
        }
        setChildId("" + child.getOID());
        this.child = (mojo.km.security.UserGroup) new mojo.km.persistence.Reference(
                child).getObject();
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
        this.initParent();
        return parentId;
    }

    *//**
     * Initialize class relationship to class mojo.km.security.User
     *//*
    private void initParent() {
        if (parent == null) {
            try {
                parent = (mojo.km.security.User) new mojo.km.persistence.Reference(
                        parentId, mojo.km.security.User.class).getObject();
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
        this.parent = (mojo.km.security.User) new mojo.km.persistence.Reference(
                parent).getObject();
    }

    public static Iterator findAll(String attributeName, String attributeValue) {
        Home home = new Home();
        return home.findAll(attributeName, attributeValue, UserUserGroupsUserGroup.class);
    }

    public static Iterator findAll(IEvent event) {
        Home home = new Home();
        return home.findAll(event, UserUserGroupsUserGroup.class);
    }*/
}
