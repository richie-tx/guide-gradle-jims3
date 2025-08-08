/*
 * Created on Jun 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.calendar;

import messaging.calendar.ICalendarAttribute;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvLocUnitIdAttribute implements ICalendarAttribute {

	private int juvLocUnitId;

	/**
	 *  
	 */
	public GetJuvLocUnitIdAttribute() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeName()
	 */
	public String getAttributeName() {
		// TODO Auto-generated method stub
		return "JUVLOCUNIT_ID";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pd.common.calendar.ICalendarAttribute#getAttributeValue()
	 */
	public Object getAttributeValue() {
		// TODO Auto-generated method stub
		return new Integer(juvLocUnitId);
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
	 * @return Returns the juvLocUnitId.
	 */
	public int getJuvLocUnitId() {
		return juvLocUnitId;
	}

	/**
	 * @param juvLocUnitId
	 *            The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(int juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}
}
