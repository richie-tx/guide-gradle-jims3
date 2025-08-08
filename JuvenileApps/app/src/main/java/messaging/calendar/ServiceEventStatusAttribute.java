//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

public class ServiceEventStatusAttribute implements ICalendarAttribute
{
	private String serviceEventStatusCd;

	/**
	 * @roseuid 44805C940340
	 */
	public ServiceEventStatusAttribute()
	{

	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName()
	{		
		return "EVENTSTATUSCD";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue()
	{		
		return serviceEventStatusCd;
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue)
	{		
		
	}

	/**
	 * @return Returns the serviceEventStatusCd.
	 */
	public String getServiceEventStatusCd() {
		return serviceEventStatusCd;
	}
	/**
	 * @param serviceEventStatusCd The serviceEventStatusCd to set.
	 */
	public void setServiceEventStatusCd(String serviceEventStatusCd) {
		this.serviceEventStatusCd = serviceEventStatusCd;
	}
}