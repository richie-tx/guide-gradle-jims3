package messaging.administercaseload;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetInOutActivityEvent extends RequestEvent 
{
	private Date beginDate;
	private Date endDate;
	private String assignStaffPositionId;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAssignStaffPositionId() {
		return assignStaffPositionId;
	}

	public void setAssignStaffPositionId(String assignStaffPositionId) {
		this.assignStaffPositionId = assignStaffPositionId;
	}	
	
}
