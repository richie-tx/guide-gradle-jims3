/*
 * Created on Nov 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_nnagaraju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateCodetableRegistrationEvent   extends RequestEvent  {
	
	private String name;
	private String description;
	private String codeRegId;
	private String contextKey;
	private String entityName;
	private String type;
	private Collection agenciesList = null;
	
	

	/**
	 * @return Returns the codeRegId.
	 */
	public String getCodeRegId() {
		return codeRegId;
	}
	/**
	 * @param codeRegId The codeRegId to set.
	 */
	public void setCodeRegId(String codeRegId) {
		this.codeRegId = codeRegId;
	}
	/**
	 * @return Returns the contextKey.
	 */
	public String getContextKey() {
		return contextKey;
	}
	/**
	 * @param contextKey The contextKey to set.
	 */
	public void setContextKey(String contextKey) {
		this.contextKey = contextKey;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
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
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	public Collection getAgenciesList()
	{
		return agenciesList;
	}
	public void setAgenciesList(Collection agenciesList)
	{
		this.agenciesList = agenciesList;
	}
}
