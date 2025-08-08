//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetVendorContactEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceLocationEvent extends RequestEvent 
{
   public int locationId;
   public int juvLocUnitId;
   private int serviceId;
   private String locationName;
   private String serviceName;
   
   /**
    * @roseuid 447458710020
    */
   public GetServiceLocationEvent() 
   {
    
   }
	
	/**
	 * @return
	 */
	public int getLocationId()
	{
		return locationId;
	}
	
	/**
	 * @param i
	 */
	public void setLocationId(int i)
	{
		locationId = i;
	}

	/**
	 * @return
	 */
	public int getServiceId()
	{
		return serviceId;
	}
	
	/**
	 * @param i
	 */
	public void setServiceId(int i)
	{
		serviceId = i;
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
	public void setLocationName(String string)
	{
		locationName = string;
	}

	/**
	 * @return
	 */
	public String getServiceName()
	{
		return serviceName;
	}
	
	/**
	 * @param string
	 */
	public void setServiceName(String string)
	{
		serviceName = string;
	}

/**
 * @return Returns the juvLocUnitId.
 */
public int getJuvLocUnitId() {
	return juvLocUnitId;
}
/**
 * @param juvLocUnitId The juvLocUnitId to set.
 */
public void setJuvLocUnitId(int juvLocUnitId) {
	this.juvLocUnitId = juvLocUnitId;
}
}
