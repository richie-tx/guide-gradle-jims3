/*
 * Create on May 24, 2006
 */

package messaging.administerserviceprovider.reply;

import java.util.Collection;
import java.util.Comparator;

import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.messaging.ResponseEvent;


public class ServiceLocationResponseEvent extends ResponseEvent implements Comparable
{	
	private String locationId;
	private String locationName;
	private Collection serviceTypeResponseEvents;
	private String juvLocUnitId;
	private String locationUnitName;


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
	public String getLocationName()
	{
		return locationName;
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
	public void setLocationName(String string)
	{
		locationName = string;
	}

	/**
	 * @return
	 */
	public Collection getServiceTypeResponseEvents()
	{
		return serviceTypeResponseEvents;
	}

	/**
	 * @param collection
	 */
	public void setServiceTypeResponseEvents(Collection collection)
	{
		serviceTypeResponseEvents = collection;
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
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}
	/**
	 * @param locationUnitName The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	public int compareTo(Object o) throws ClassCastException {
		if(o==null)
			return -1;
		if(this.locationUnitName==null)
			return 1;
		ServiceLocationResponseEvent l = (ServiceLocationResponseEvent)o;		
		if(l.getLocationUnitName() == null)
			return -1;
		return this.locationUnitName.compareToIgnoreCase(l.getLocationUnitName());	
		
	}
	
}