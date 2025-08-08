/*
 * Created on Mar 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.cscdcalendar;

import messaging.calendar.ICalendarAttribute;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CSFVIteneraryAttribute implements ICalendarAttribute {

	private Integer fvIteneraryId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {
		// TODO Auto-generated method stub
		return "FVITINERARY_ID";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {
		// TODO Auto-generated method stub
		return fvIteneraryId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {
		// TODO Auto-generated method stub

	}

	public Integer getFvIteneraryId() {
		return fvIteneraryId;
	}

	public void setFvIteneraryId(Integer fvIteneraryId) {
		this.fvIteneraryId = fvIteneraryId;
	}
}
