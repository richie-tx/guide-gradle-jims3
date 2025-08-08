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
public class CSResultPositionAttribute implements ICalendarAttribute {
	
	private Integer resultPositionId;

	public CSResultPositionAttribute() {

	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {		
		return "REPOSITION_ID";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {		
		return resultPositionId;
	}

	/**
	 * @return Returns the resultPositionId.
	 */
	public Integer getResultPositionId() {
		return resultPositionId;
	}
	/**
	 * @param resultPositionId The resultPositionId to set.
	 */
	public void setResultPositionId(Integer resultPositionId) {
		this.resultPositionId = resultPositionId;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {	
		
	}
}

