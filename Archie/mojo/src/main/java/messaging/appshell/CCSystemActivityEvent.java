/*
 * Created on May 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.appshell;

import mojo.km.config.FolderProperties;

/**
 * @author dapte
 *
 * The base event for SystemActivity.
 */
public class CCSystemActivityEvent {
	
	protected String OID;
	protected String displayName;
	protected String url;
	protected String type;

	public CCSystemActivityEvent() {
		url = "jsp/tempAppBody.jsp";		
	}

	/**
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param string
	 */
	public void setDisplayName(String key) {
		if (FolderProperties.getInstance().getProperty(key) != null) {
			url = FolderProperties.getInstance().getProperty(key);
		}
		displayName = key;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @return
	 */
	public String getOID() {
		return OID;
	}

	/**
	 * @param string
	 */
	public void setOID(String string) {
		OID = string;
	}

}
