package messaging.administercaseload;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetInOutActivityViewEvent extends RequestEvent 
{
	private String assignStaffPositionId;
	private Date beginDate;
	private String defendantId;
	private String criminalCaseId;
	private String inOut;
	
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public String getAssignStaffPositionId() {
		return assignStaffPositionId;
	}

	public void setAssignStaffPositionId(String assignStaffPositionId) {
		this.assignStaffPositionId = assignStaffPositionId;
	}

	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	public String getInOut() {
		return inOut;
	}

	public void setInOut(String inOut) {
		this.inOut = inOut;
	}	
	
}
