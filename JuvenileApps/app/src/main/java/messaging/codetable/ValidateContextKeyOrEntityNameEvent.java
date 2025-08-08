/*
 * Created on Sep 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateContextKeyOrEntityNameEvent  extends RequestEvent {

    private String contextkey;
    private String entityName; 
	private String name;
	
	/**
	 * @return Returns the contextkey.
	 */
	public String getContextkey() {
		return contextkey;
	}
	/**
	 * @param contextkey The contextkey to set.
	 */
	public void setContextkey(String contextkey) {
		this.contextkey = contextkey;
	}
	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}

