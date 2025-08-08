package messaging.administercaseload.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class CaseAssignInOutResponseEvent extends ResponseEvent 
{
	private String caseAssignIoId;
	private String defendantId;
	private String defendantName;
	private String criminalCaseId;
	private Date beginDate;
	private Date endDate;
	private String assignStaffPositionId;
	private String inOut;
	private String supervisionOrderId;
	private String courtId;

	public String getCaseAssignIoId() {
		return caseAssignIoId;
	}
	public void setCaseAssignIoId(String caseAssignIoId) {
		this.caseAssignIoId = caseAssignIoId;
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
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
	public String getDefendantName() {
		return defendantName;
	}
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
}
