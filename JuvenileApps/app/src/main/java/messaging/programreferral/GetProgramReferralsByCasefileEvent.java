package messaging.programreferral;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetProgramReferralsByCasefileEvent extends RequestEvent {
	private String casefileId;
	private String provProgramId;
	private Date beginDate;
	private boolean courtOrdered;
	private String assignedHours;

	public String getAssignedHours() {
		return assignedHours;
	}

	public void setAssignedHours(String assignedHours) {
		this.assignedHours = assignedHours;
	}

	public String getCasefileId() {
		return casefileId;
	}

	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	public String getProvProgramId() {
		return provProgramId;
	}

	public void setProvProgramId(String provProgramId) {
		this.provProgramId = provProgramId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public boolean isCourtOrdered() {
		return courtOrdered;
	}

	public void setCourtOrdered(boolean isCourtOrdered) {
		this.courtOrdered = isCourtOrdered;
	}
}
