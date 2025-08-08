/*
 * Created on Jan 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.criminalcase;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCriminalCaseDb2Event extends RequestEvent
{
	private String caseNum;
	private String courtDivisionId;
	
	
	
	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String criminalCaseId) {
		this.caseNum = criminalCaseId;
	}

	public String getCourtDivisionId() {
		return courtDivisionId;
	}

	public void setCourtDivisionId(String cdi) {
		this.courtDivisionId = cdi;
	}	
}
