package messaging.administersupervisee.reply;

import java.util.Comparator;
import java.util.Date;

import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.ResponseEvent;

public class SuperviseeHistoryResponseEvent extends ResponseEvent 
{
	private String superviseeHistoryId;
	private String superviseeId;
	private String type;
	private Date losEffectiveDate;
	private String supervisionLevelId;
	private String caseloadCreditStaffPositionId;
	private String caseloadCreditStaffPositionName;
	private String assignedProgramUnitId;
	private String assignedProgramUnitName;
	private String assignedStaffPositionId;	
	private boolean currentlySupervised;	
	private String comments;	
	private String programUnitAssignmentDate;
	private String createDate;
	private Date dnaCollectedDate;
	private boolean dnaFlagInd;

	public static Comparator superviseeHistoryComparator = new Comparator() 
	{
		public int compare(Object history1, Object history2) 
		{			
		  int history_id1 = 
			  Integer.valueOf(
					  ((SuperviseeHistoryResponseEvent)history1).
					  			getSuperviseeHistoryId());

		  int history_id2 = 
			  Integer.valueOf(
					  ((SuperviseeHistoryResponseEvent)history2).
					  			getSuperviseeHistoryId());		  
		  if (history_id1 < history_id2)
			  return -1;
		  else
			  if (history_id1 > history_id2) 
				  	return 1;
			  else
					  	return 0;
		}	
	};	
	
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}
	
	public String getSuperviseeId() {
		return superviseeId;
	}
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLosEffectiveDate() {
		return losEffectiveDate;
	}
	public void setLosEffectiveDate(Date losEffectiveDate) {
		this.losEffectiveDate = losEffectiveDate;
	}
	public String getSupervisionLevelId() {
		return supervisionLevelId;
	}
	public void setSupervisionLevelId(String supervisionLevelId) {
		this.supervisionLevelId = supervisionLevelId;
	}
	public String getCaseloadCreditStaffPositionId() {
		return caseloadCreditStaffPositionId;
	}
	public void setCaseloadCreditStaffPositionId(
			String caseloadCreditStaffPositionId) {
		this.caseloadCreditStaffPositionId = caseloadCreditStaffPositionId;
	}
	public String getAssignedProgramUnitId() {
		return assignedProgramUnitId;
	}
	public void setAssignedProgramUnitId(String assignedProgramUnitId) {
		this.assignedProgramUnitId = assignedProgramUnitId;
	}
	public String getAssignedStaffPositionId() {
		return assignedStaffPositionId;
	}
	public void setAssignedStaffPositionId(String assignedStaffPositionId) {
		this.assignedStaffPositionId = assignedStaffPositionId;
	}
	public boolean isCurrentlySupervised() {
		return currentlySupervised;
	}
	public void setCurrentlySupervised(boolean currentlySupervised) {
		this.currentlySupervised = currentlySupervised;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getProgramUnitAssignmentDate() {
		return programUnitAssignmentDate;
	}
	public void setProgramUnitAssignmentDate(String programUnitAssignmentDate) {
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}
	public String getCaseloadCreditStaffPositionName() {
		return caseloadCreditStaffPositionName;
	}
	public void setCaseloadCreditStaffPositionName(
			String caseloadCreditStaffPositionName) {
		this.caseloadCreditStaffPositionName = caseloadCreditStaffPositionName;
	}
	public String getAssignedProgramUnitName() {
		return assignedProgramUnitName;
	}
	public void setAssignedProgramUnitName(String assignedProgramUnitName) {
		this.assignedProgramUnitName = assignedProgramUnitName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Date getDnaCollectedDate() {
		return dnaCollectedDate;
	}
	public void setDnaCollectedDate(Date dnaCollectedDate) {
		this.dnaCollectedDate = dnaCollectedDate;
	}
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}
	
	
	
	
}
