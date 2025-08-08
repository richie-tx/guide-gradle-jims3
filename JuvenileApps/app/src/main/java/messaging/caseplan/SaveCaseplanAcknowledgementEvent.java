//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\SaveGoalDataEvent.java

package messaging.caseplan;

import mojo.km.messaging.Composite.CompositeRequest;
import java.util.Date;

public class SaveCaseplanAcknowledgementEvent extends CompositeRequest 
{
	private String caseplanID;	
	private String explanation;
	private Date entryDate;
	private String signatureStatus; 
	
  /**
    * @roseuid 4533BD1C0310
    */
   public SaveCaseplanAcknowledgementEvent() 
   {
    
   }
	/**
	 * @return Returns the caseplanID.
	 */
	public String getCaseplanID() {
		return caseplanID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setCaseplanID(String caseplanID) {
		this.caseplanID = caseplanID;
	}

	/**
	 * @return Returns the explanation.
	 */
	public String getExplanation() {
		return explanation;
	}
	/**
	 * @param explanation The explanation to set.
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
 
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
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
	 * @param signatureStatus The signatureStatus to set.
	 */
	public void setSignatureStatus(String signatureStatus) {
		this.signatureStatus = signatureStatus;
	}

}
