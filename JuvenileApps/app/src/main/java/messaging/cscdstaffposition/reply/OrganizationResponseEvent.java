/*
 * Created on Apr 4, 2007
 *
 */
package messaging.cscdstaffposition.reply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *  
 */
public class OrganizationResponseEvent extends ResponseEvent implements Serializable, Comparable {
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o!=null && (o instanceof OrganizationResponseEvent)){
			OrganizationResponseEvent myInc=(OrganizationResponseEvent)o;
			if(myInc.getDescription()==null){
				return 1;
			}
			else if(this.getDescription()==null){
				return -1;
			}
			return this.getDescription().compareTo(myInc.getDescription());
		}
		else{
			return 1;
		}
	}
	private String organizationId;
	private String parentId;
	private String description;
	private Collection children;

	public void addChild(OrganizationResponseEvent ore) {
		if (children == null) {
			children = new ArrayList();
		}
		children.add(ore);
	}

	public Collection getChildren() {
		return children;
	}

	public void setChildren(Collection children) {
		this.children = children;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the organizationId.
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId
	 *            The organizationId to set.
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * @return Returns the parentId.
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            The parentId to set.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
