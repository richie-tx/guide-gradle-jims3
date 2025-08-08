/*
 * Created on Nov 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan.reply;

import java.util.Date;

import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GoalListResponseEvent extends ResponseEvent implements Comparable{
	private String goalID;
	private Date entryDate;
	private Date closedDate;
	private Date goalStatusChangeDate; //Added newly statuschange date
	private String statusId;
	private String goalDescription;
	private String goalIntervention; //added for ER JIMS200075816 
	
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
	 * @param statusChangeDate the statusChangeDate to set
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
	 * @return Returns the goalDescription.
	 */
	public String getGoalIntervention() {
		return goalIntervention;
	}
	/**
	 * @param goalDescription The goalDescription to set.
	 */
	public void setGoalIntervention(String goalIntervention) {
		this.goalIntervention = goalIntervention;
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
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		GoalListResponseEvent evt = (GoalListResponseEvent) obj;
		return goalID.compareToIgnoreCase(evt.getGoalID());		
	}	
}
