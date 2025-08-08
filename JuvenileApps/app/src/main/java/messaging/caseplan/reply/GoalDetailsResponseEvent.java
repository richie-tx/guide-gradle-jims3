/*
 * Created on Nov 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan.reply;

import java.util.ArrayList;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GoalDetailsResponseEvent extends ResponseEvent {
	private String goalID;
	private String domainTypeId;
	private String timeframeId;
	private String timeframeDesc;
	private String ExplainOtherTxt;
	private ArrayList namesOfPersResponsible;
	private String progressNotes;
	private String intervention;
	private String endRecommendations;
	private Date entryDate;
	private Date closedDate;
	private Date goalStatusChangeDate;
	private String statusId;
	private String goalDescription;
	
	/**
	 * @return Returns the closedDate.
	 */
	public Date getClosedDate() {
		return closedDate;
	}
	/**
	 * @param closedDate The closedDate to set.
	 */
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	/**
	 * @return the goalStatusChangeDate
	 */
	public Date getGoalStatusChangeDate() {
		return goalStatusChangeDate;
	}
	/**
	 * @param goalStatusChangeDate the goalStatusChangeDate to set
	 */
	public void setGoalStatusChangeDate(Date goalStatusChangeDate) {
		this.goalStatusChangeDate = goalStatusChangeDate;
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
	 * @return Returns the status.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatusId(String statusid) {
		this.statusId = statusid;
	}
	/**
	 * @return Returns the domainTypeId.
	 */
	public String getDomainTypeId() {
		return domainTypeId;
	}
	/**
	 * @param domainTypeId The domainTypeId to set.
	 */
	public void setDomainTypeId(String domainTypeId) {
		this.domainTypeId = domainTypeId;
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
	 * @return Returns the namesOfpersResponsible.
	 */
	public ArrayList getNamesOfPersResponsible() {
		return namesOfPersResponsible;
	}
	/**
	 * @param namesOfpersResponsible The namesOfpersResponsible to set.
	 */
	public void setNamesOfPersResponsible(ArrayList namesOfpersResponsible) {
		this.namesOfPersResponsible = namesOfpersResponsible;
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
	 * @return Returns the intervention.
	 */
	public String getIntervention() {
		return intervention;
	}
	/**
	 * @param intervention The intervention to set.
	 */
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}
	/**
	 * @return Returns the timeframeId.
	 */
	public String getTimeframeId() {
		return timeframeId;
	}
	/**
	 * @param timeframeId The timeframeId to set.
	 */
	public void setTimeframeId(String timeframeId) {
		this.timeframeId = timeframeId;
	}
	
	public String getTimeframeDescription()
	{
		return timeframeDesc;
	}
	
	public void setTimeframeDescription(String desc) {
		timeframeDesc = desc;
	}
	/**
	 * @return the explainOtherTxt
	 */
	public String getExplainOtherTxt() {
		return ExplainOtherTxt;
	}
	/**
	 * @param explainOtherTxt the explainOtherTxt to set
	 */
	public void setExplainOtherTxt(String explainOtherTxt) {
		ExplainOtherTxt = explainOtherTxt;
	}
}
