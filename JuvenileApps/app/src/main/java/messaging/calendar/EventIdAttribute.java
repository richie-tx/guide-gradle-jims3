/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EventIdAttribute implements ICalendarAttribute {

	private int serviceEventId;

	/**
	 *  
	 */
	public EventIdAttribute() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {
		// TODO Auto-generated method stub
		return "SERVEVENT_ID";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {
		// TODO Auto-generated method stub
		return new Integer(serviceEventId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the serviceEventId.
	 */
	public int getServiceEventId() {
		return serviceEventId;
	}

	/**
	 * @param serviceEventId
	 *            The serviceEventId to set.
	 */
	public void setServiceEventId(int serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
}
