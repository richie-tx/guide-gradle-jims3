/*
 * Created on Apr 7, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.cscdcalendar.reply;

import java.util.Hashtable;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CSSpnViewConditionsResponseEvent extends ResponseEvent {

	private Hashtable caseConditions = new Hashtable();

	
	/**
	 * @return Returns the caseConditions.
	 */
	public Hashtable getCaseConditions() {
		return caseConditions;
	}

	/**
	 * @param caseConditions
	 *            The caseConditions to set.
	 */
	public void setCaseConditions(Hashtable caseConditions) {
		this.caseConditions = caseConditions;
	}
}
