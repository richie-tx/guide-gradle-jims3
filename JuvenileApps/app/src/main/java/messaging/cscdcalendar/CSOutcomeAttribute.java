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
public class CSOutcomeAttribute implements ICalendarAttribute {
	
	private String outcome;

	public CSOutcomeAttribute() {

	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {		
		return "OUTCOME";
	}
	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {		
		return outcome;
	}

	/**
	 * @return Returns the outcome.
	 */
	public String getOutcome() {
		return outcome;
	}
	/**
	 * @param outcome The outcome to set.
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/* (non-Javadoc)
	 * @see pd.common.calendar.ICalendarAttribute#setAttributeValue(java.lang.String)
	 */
	public void setAttributeValue(String attributeValue) {	
		
	}
}
