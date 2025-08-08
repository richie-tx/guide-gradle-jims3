//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;


import java.sql.Timestamp;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCCourtActivityEvent extends RequestEvent 
{
    private Timestamp occurenceDate;
    private String typeOfCourtActionComment;
    private String disposition;
    private String type;
    private String subType;
    private String courtActivityId;
    private String summaryOfCourtActivity;
    private boolean manualAdded;
	
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	
    public String getSummaryOfCourtActivity() {
		return summaryOfCourtActivity;
	}
	public void setSummaryOfCourtActivity(String summaryOfCourtActivity) {
		this.summaryOfCourtActivity = summaryOfCourtActivity;
	}

	/**
	 * @return the typeOfCourtActionComment
	 */
	public String getTypeOfCourtActionComment() {
		return typeOfCourtActionComment;
	}
	/**
	 * @param typeOfCourtActionComment the typeOfCourtActionComment to set
	 */
	public void setTypeOfCourtActionComment(String typeOfCourtActionComment) {
		this.typeOfCourtActionComment = typeOfCourtActionComment;
	}
	/**
	 * @return Returns the courtActivityId.
	*/
	public String getCourtActivityId() {
		return courtActivityId;
	}
	/**
	 * @param courtActivityId The courtActivityId to set.
	*/ 
	public void setCourtActivityId(String courtActivityId) {
		this.courtActivityId = courtActivityId;
	}
	/**
	 * @return Returns the disposition.
	 */
	public String getDisposition() {
		return disposition;
	}
	/**
	 * @param disposition The disposition to set.
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	/**
	 * @return Returns the occurenceDate.
	 */
	public Timestamp getOccurenceDate() {
		return occurenceDate;
	}
	/**
	 * @param occurenceDate The occurenceDate to set.
	 */
	public void setOccurenceDate(Timestamp occurenceDate) {
		this.occurenceDate = occurenceDate;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
}
