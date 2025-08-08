//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

public class ServiceAttribute implements ICalendarAttribute
{
	private Integer serviceId;

	/**
	 * @roseuid 44805C940340
	 */
	public ServiceAttribute()
	{

	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName()
	{		
		return "SERVICE_ID";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue()
	{		
		return serviceId;
	}

	/**
	 * @return Returns the serviceId.
	 */
	public Integer getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {	
		
	}
}
