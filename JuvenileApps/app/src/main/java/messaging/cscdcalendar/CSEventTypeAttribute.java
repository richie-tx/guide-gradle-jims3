/*
 * Created on Feb 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.cscdcalendar;

import messaging.calendar.ICalendarAttribute;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSEventTypeAttribute implements ICalendarAttribute {
	
	private String eventTypeId;

	public CSEventTypeAttribute() {

	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {		
		return "CSEVENTTYPE";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {		
		return eventTypeId;
	}

	/**
	 * @return Returns the eventTypeId.
	 */
	public String getEventTypeId() {
		return eventTypeId;
	}
	/**
	 * @param eventTypeId The eventTypeId to set.
	 */
	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {	
		
	}
}

