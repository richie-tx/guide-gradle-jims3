//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

public class ServiceInstructorAttribute implements ICalendarAttribute
{
	private int intstructorId;

	/**
	 * @roseuid 44805C940340
	 */
	public ServiceInstructorAttribute()
	{

	}
	
	
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName()
	{		
		return "JVSRVPRVPROF_ID";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue()
	{		
		return new Integer(intstructorId);
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue)
	{		
		
	}

	/**
	 * @return
	 */
	public int getIntstructorId()
	{
		return intstructorId;
	}

	/**
	 * @param i
	 */
	public void setIntstructorId(int i)
	{
		intstructorId = i;
	}

}