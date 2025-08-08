/*
 * Created on Jun 15, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateServiceLocationRequestEvent extends RequestEvent
{

	private String serviceId;
	private String locationId;
	private String juvLocUnitId;
	private String juvLocUnitName;
	private boolean isDeletable;
	/**
	 * 
	 */
	public CreateServiceLocationRequestEvent()
	{
	}
	

	/**
	 * @return
	 */
	public boolean isDeletable()
	{
		return isDeletable;
	}

	/**
	 * @return
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * @return
	 */
	public String getServiceId()
	{
		return serviceId;
	}

	/**
	 * @param b
	 */
	public void setDeletable(boolean b)
	{
		isDeletable = b;
	}

	/**
	 * @param string
	 */
	public void setLocationId(String string)
	{
		locationId = string;
	}

	/**
	 * @param string
	 */
	public void setServiceId(String string)
	{
		serviceId = string;
	}
	/**
	 * @return Returns the juvLocUnitId.
	 */
	public String getJuvLocUnitId() {
		return juvLocUnitId;
	}
	/**
	 * @param juvLocUnitId The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(String juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}
	/**
	 * @return Returns the juvLocUnitName.
	 */
	public String getJuvLocUnitName() {
		return juvLocUnitName;
	}
	/**
	 * @param juvLocUnitName The juvLocUnitName to set.
	 */
	public void setJuvLocUnitName(String juvLocUnitName) {
		this.juvLocUnitName = juvLocUnitName;
	}
}
