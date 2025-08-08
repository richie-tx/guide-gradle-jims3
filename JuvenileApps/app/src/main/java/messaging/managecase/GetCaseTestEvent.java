/*
 * Created on Jun 22, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.managecase;

import mojo.km.messaging.RequestEvent;

/**
 * @author mpatino
 *
 * Test query event for VSAM mapping - do not delete.
 */
public class GetCaseTestEvent extends RequestEvent {
	
	String courtDivision;
	String caseNumber;

	/**
	 * @return
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @return
	 */
	public String getCourtDivision() {
		return courtDivision;
	}

	/**
	 * @param string
	 */
	public void setCaseNumber(String cas) {
		caseNumber = cas;
	}

	/**
	 * @param string
	 */
	public void setCourtDivision(String cdi) {
		courtDivision = cdi;
	}

}
