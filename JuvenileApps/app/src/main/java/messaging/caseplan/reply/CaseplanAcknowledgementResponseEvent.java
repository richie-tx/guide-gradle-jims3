/*
 * Created on Sep 26 2013
 */
package messaging.caseplan.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 * 
 */
public class CaseplanAcknowledgementResponseEvent extends ResponseEvent
	implements Comparable{
	private String caseplanId;
	private String acknowledgementId;
	private Date entryDate;
	private Date createDate;
	private String signatureStatus;
	private String explanation;

	

	public CaseplanAcknowledgementResponseEvent() {
	}

	/**
	 * @return Returns the acknowledgementId.
	 */
	public String getAcknowledgementId() {
		return acknowledgementId;
	}

	/**
	 * @param acknowledgementId
	 *            The acknowledgementId to set.
	 */
	public void setAcknowledgementId(String acknowledgementId) {
		this.acknowledgementId = acknowledgementId;
	}

	/**
	 * @return Returns the caseplanId.
	 */
	public String getCaseplanId() {
		return caseplanId;
	}

	/**
	 * @param caseplanId
	 *            The caseplanId to set.
	 */
	public void setCaseplanId(String caseplanId) {
		this.caseplanId = caseplanId;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the signatureStatus.
	 */
	public String getSignatureStatus() {
		return signatureStatus;
	}

	/**
	 * @param signatureStatus
	 *            The signatureStatus to set.
	 */
	public void setSignatureStatus(String signatureStatus) {
		this.signatureStatus = signatureStatus;
	}

	/**
	 * @return Returns the explanation.
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation
	 *            The explanation to set.
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public int compareTo(Object obj)
	{
		if(obj==null)
			return 1;
		if(this.entryDate==null)
			return -1;
		CaseplanAcknowledgementResponseEvent resp = (CaseplanAcknowledgementResponseEvent)obj;
		return(resp.getEntryDate().compareTo(this.entryDate));
		
	
	}
}
