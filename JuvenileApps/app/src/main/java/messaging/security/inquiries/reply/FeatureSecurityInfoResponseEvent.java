/*
 * Created on Apr 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.inquiries.reply;

import java.util.ArrayList;
import java.util.Collection;

import messaging.security.reply.RoleResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FeatureSecurityInfoResponseEvent extends ResponseEvent
{
	private String featureName;
	private String featureId;
	private Collection roles = new ArrayList();
    
	/**
	 * @return Returns the featureId.
	 */
	public String getFeatureId() {
		return featureId;
	}
	/**
	 * @param featureId The featureId to set.
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	/**
	 * @return Returns the featureName.
	 */
	public String getFeatureName() {
		return featureName;
	}
	/**
	 * @param featureName The featureName to set.
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	/**
	 * @return Returns the roles.
	 */
	public Collection getRoles() {
		return roles;
	}
	/**
	 * @param role The role to add.
	 */
	public void addRole(RoleResponseEvent role) {
		this.roles.add(role);
	}
}
