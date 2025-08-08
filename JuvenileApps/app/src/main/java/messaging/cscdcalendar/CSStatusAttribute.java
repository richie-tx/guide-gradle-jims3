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
public class CSStatusAttribute implements ICalendarAttribute {
	
	private String statusId;

	public CSStatusAttribute() {

	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {		
		return "STATUS";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {		
		return statusId;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {	
		
	}
}
