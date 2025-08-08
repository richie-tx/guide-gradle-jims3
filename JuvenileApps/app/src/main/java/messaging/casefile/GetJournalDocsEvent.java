/*
 * Created on Oct 15, 2007
 *
 */
package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author rcarter
 *
 */
public class GetJournalDocsEvent extends RequestEvent {
	private String casefileId;
	
	
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}
