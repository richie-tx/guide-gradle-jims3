package ui.supervision.administercaseload;

import java.util.Date;

public class InOutActivityBean 
{
	private String caseAssignIoId;
	private String defendantId;
	private String defendantName;
	private String criminalCaseId;
	private String beginDate;
	private String endDate;
	private String assignStaffPositionId;
	private String inOut;
	private String court;
	private String supervisionOrderId;

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
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
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
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
}
