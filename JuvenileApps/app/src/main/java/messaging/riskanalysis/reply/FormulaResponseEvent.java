package messaging.riskanalysis.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class FormulaResponseEvent extends ResponseEvent {
	private String status;
	private String statusDesc;
	private Date entryDate;
	private Date activateDate;
	private String assessmentTypeCd;
	private String assessmentTypeDesc;
	private String version;
	private String modificationReason;
	private Collection categories;
	private String formulaId;
	private boolean updatable;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}
	public String getAssessmentTypeCd() {
		return assessmentTypeCd;
	}
	public void setAssessmentTypeCd(String assessmentTypeCd) {
		this.assessmentTypeCd = assessmentTypeCd;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getModificationReason() {
		return modificationReason;
	}
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}
	public void setAssessmentTypeDesc(String assessmentTypeDesc) {
		this.assessmentTypeDesc = assessmentTypeDesc;
	}
	public String getAssessmentTypeDesc() {
		return assessmentTypeDesc;
	}
	public void setCategories(Collection categories) {
		this.categories = categories;
	}
	public Collection getCategories() {
		return categories;
	}
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	public String getFormulaId() {
		return formulaId;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}
	public boolean isUpdatable() {
		return updatable;
	}
}
