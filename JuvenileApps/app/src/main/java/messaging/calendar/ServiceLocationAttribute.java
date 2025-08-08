//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

public class ServiceLocationAttribute implements ICalendarAttribute
{
	private int serviceLocationId;

	/**
	 * @roseuid 44805C940340
	 */
	public ServiceLocationAttribute()
	{

	}
	/**
	 * @return
	 */
	public int getServiceLocationId()
	{
		return serviceLocationId;
	}

	/**
	 * @param i
	 */
	public void setServiceLocationId(int i)
	{
		serviceLocationId = i;
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName()
	{		
		return "JUVLOCUNIT_ID";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue()
	{		
		return new Integer(serviceLocationId);
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue)
	{		
		
	}

}
