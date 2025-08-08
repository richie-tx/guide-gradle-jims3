/*
 * Created on July 19, 2016
 *
 */
package messaging.casefile;

import mojo.km.messaging.RequestEvent;


/**
 * @author ugopinath
 *
 */
public class SaveFacilityDocEvent extends RequestEvent{
	String casefileId;
	private Object document;
	String docTypeCd;
	
	public String getDocTypeCd() {
		return docTypeCd;
	}
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	public SaveFacilityDocEvent() 
	{
    
	}
	
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
}
