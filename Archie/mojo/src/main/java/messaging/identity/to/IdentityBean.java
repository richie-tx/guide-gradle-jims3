/*
 * Created on Mar 15, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.identity.to;

import messaging.identity.domintf.IIdentity;

/**
 * @author Jim Fisher
 *
 */
public class IdentityBean implements IIdentity
{
	private String id;
	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		id = string;
	}

}
