package messaging.casefile;

import java.util.Collection;
import java.util.Date;
import mojo.km.messaging.RequestEvent;
public class SaveCasefileNonComplianceNoticeEvent extends RequestEvent
{
	private String casefileId;
	private Date nonComplianceDate;
	private Date sanctionAssignedDate;
	private Date completeSanctionByDate;
//	private boolean parentInformed;
	private String violationLevelId;
	private String sanctionLevelId;
	private Collection violations;
	private Collection sanctions;
	private Object document;
	
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public Date getNonComplianceDate() {
		return nonComplianceDate;
	}
	public void setNonComplianceDate(Date nonComplianceDate) {
		this.nonComplianceDate = nonComplianceDate;
	}
	public Date getSanctionAssignedDate() {
		return sanctionAssignedDate;
	}
	public void setSanctionAssignedDate(Date sanctionAssignedDate) {
		this.sanctionAssignedDate = sanctionAssignedDate;
	}
	public Date getCompleteSanctionByDate() {
		return completeSanctionByDate;
	}
	public void setCompleteSanctionByDate(Date completeSanctionByDate) {
		this.completeSanctionByDate = completeSanctionByDate;
	}
//	public boolean isParentInformed() {
//		return parentInformed;
//	}
//	public void setParentInformed(boolean parentInformed) {
//		this.parentInformed = parentInformed;
//	}
	public String getViolationLevelId() {
		return violationLevelId;
	}
	public void setViolationLevelId(String violationLevelId) {
		this.violationLevelId = violationLevelId;
	}
	public Collection getViolations() {
		return violations;
	}
	public void setViolations(Collection violations) {
		this.violations = violations;
	}
	public Collection getSanctions() {
		return sanctions;
	}
	public void setSanctions(Collection sanctions) {
		this.sanctions = sanctions;
	}
	public Object getDocument() {
		return document;
	}
	public void setDocument(Object document) {
		this.document = document;
	}
	public String getSanctionLevelId() {
		return sanctionLevelId;
	}
	public void setSanctionLevelId(String sanctionLevelId) {
		this.sanctionLevelId = sanctionLevelId;
	}

}
