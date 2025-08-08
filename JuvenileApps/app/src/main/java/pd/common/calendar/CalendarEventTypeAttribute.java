/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.io.Serializable;
import messaging.calendar.ICalendarAttribute;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventTypeAttribute implements ICalendarAttribute, Serializable
{
	private String attributeValue;
	
	public CalendarEventTypeAttribute(String value) 
	{
		this.attributeValue = value;
	}
	
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName()
	{
		return "CALEVENTTYPE";
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue()
	{
		return this.attributeValue;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attribute)
	{
		this.attributeValue = attribute;

	}

}
