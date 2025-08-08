/*
 * Created on May 29, 2008
 *
 */
package messaging.posttrial;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetAssignmentAndHistoryEvent extends RequestEvent
{
	private String caseNumber;
	private String cdi;
	private String criminalCaseId;
	
	public String getCriminalCaseId() {
		return new StringBuffer(this.cdi).append(this.caseNumber).toString();
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCdi() {
		return cdi;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
}
