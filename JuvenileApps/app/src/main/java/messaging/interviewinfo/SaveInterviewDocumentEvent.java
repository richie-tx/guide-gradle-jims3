/*
 * Created on Oct 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;


/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveInterviewDocumentEvent extends RequestEvent {
	private String casefileId;
	private Object document;
	private String documentTypeCodeId;
	
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public Object getDocument() {
		return document;
	}
	public void setDocument(Object document) {
		this.document = document;
	}
	
	public String getDocumentTypeCodeId() {
		return documentTypeCodeId;
	}
	public void setDocumentTypeCodeId(String documentTypeCodeId) {
		this.documentTypeCodeId = documentTypeCodeId;
	}
}
