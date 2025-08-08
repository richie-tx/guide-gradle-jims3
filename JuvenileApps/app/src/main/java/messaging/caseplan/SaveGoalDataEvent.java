//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\SaveGoalDataEvent.java

package messaging.caseplan;

import mojo.km.messaging.Composite.CompositeRequest;

public class SaveGoalDataEvent extends CompositeRequest 
{
	private String caseplanID;
	private String caseplanStatus; //added to get the existing caseplan status
	private String goalID;
	private String supervisionNumber;
	private String domainTypeID;
	private String timeframeID;
	private String explainOtherTxt;
	private String goalDescription;
	private String progressNotes;
	private String endRecommendations;
	private boolean goalEnded;
	private boolean pending;
	private String goalIntervention; //added Intervention field for ER JIMS200075816 
	
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
	 * @return the caseplanStatus
	 */
	public String getCaseplanStatus() {
		return caseplanStatus;
	}
	/**
	 * @param caseplanStatus the caseplanStatus to set
	 */
	public void setCaseplanStatus(String caseplanStatus) {
		this.caseplanStatus = caseplanStatus;
	}
	/**
	 * @return Returns the domainTypeID.
	 */
	public String getDomainTypeID() {
		return domainTypeID;
	}
	/**
	 * @param domainTypeID The domainTypeID to set.
	 */
	public void setDomainTypeID(String domainTypeID) {
		this.domainTypeID = domainTypeID;
	}
	/**
	 * @return Returns the goalDescription.
	 */
	public String getGoalDescription() {
		return goalDescription;
	}
	/**
	 * @param goalDescription The goalDescription to set.
	 */
	public void setGoalDescription(String goalDescription) {
		this.goalDescription = goalDescription;
	}
	/**
	 * @return Returns the goalIntervention.
	 */
	public String getGoalIntervention() {
		return goalIntervention;
	}
	/**
	 * @param goalIntervention The goalIntervention to set.
	 */
	public void setGoalIntervention(String goalIntervention) {
		this.goalIntervention = goalIntervention;
	}
	/**
	 * @return Returns the progressNotes.
	 */
	public String getProgressNotes() {
		return progressNotes;
	}
	/**
	 * @param progressNotes The progressNotes to set.
	 */
	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}
	/**
	 * @return Returns the supervisionNumber.
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}
	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 */
	public void setSupervisionNumber(String supervisionNumber) {
		this.supervisionNumber = supervisionNumber;
	}
	/**
	 * @return Returns the timeframeID.
	 */
	public String getTimeframeID() {
		return timeframeID;
	}
	/**
	 * @param timeframeID The timeframeID to set.
	 */
	public void setTimeframeID(String timeframeID) {
		this.timeframeID = timeframeID;
	}
	/**
	 * @return the explainOtherTxt
	 */
	public String getExplainOtherTxt() {
		return explainOtherTxt;
	}
	/**
	 * @param explainOtherTxt the explainOtherTxt to set
	 */
	public void setExplainOtherTxt(String explainOtherTxt) {
		this.explainOtherTxt = explainOtherTxt;
	}
	
   /**
    * @roseuid 4533BD1C0310
    */
   public SaveGoalDataEvent() 
   {
    
   }
	/**
	 * @return Returns the goalID.
	 */
	public String getGoalID() {
		return goalID;
	}
	
	/**
	 * @param goalID The goalID to set.
	 */
	public void setGoalID(String goalID) {
		this.goalID = goalID;
	}
	/**
	 * @return Returns the endRecommendations.
	 */
	public String getEndRecommendations() {
		return endRecommendations;
	}
	/**
	 * @param endRecommendations The endRecommendations to set.
	 */
	public void setEndRecommendations(String endRecommendations) {
		this.endRecommendations = endRecommendations;
	}
	/**
	 * @return Returns the goalEnded.
	 */
	public boolean isGoalEnded() {
		return goalEnded;
	}
	/**
	 * @param goalEnded The goalEnded to set.
	 */
	public void setGoalEnded(boolean goalEnded) {
		this.goalEnded = goalEnded;
	}
	/**
	 * @return Returns the pending.
	 */
	public boolean isPending() {
		return pending;
	}
	/**
	 * @param pending The pending to set.
	 */
	public void setPending(boolean pending) {
		this.pending = pending;
	}
}
