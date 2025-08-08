//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

public class ServiceEventTypeAttribute implements ICalendarAttribute
{
	private String serviceEventTypeId;

	/**
	 * @roseuid 44805C940340
	 */
	public ServiceEventTypeAttribute()
	{

	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName()
	{		
		return "EVENTTYPECD";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue()
	{		
		return serviceEventTypeId;
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue)
	{		
		
	}

	/**
	 * @return Returns the serviceEventTypeId.
	 */
	public String getServiceEventTypeId() {
		return serviceEventTypeId;
	}
	/**
	 * @param serviceEventTypeId The serviceEventTypeId to set.
	 */
	public void setServiceEventTypeId(String serviceEventTypeId) {
		this.serviceEventTypeId = serviceEventTypeId;
	}
}
