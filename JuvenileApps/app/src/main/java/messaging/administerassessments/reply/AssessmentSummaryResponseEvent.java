/*
 * Created on Jan 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_bjangay
 */
public class AssessmentSummaryResponseEvent extends ResponseEvent
{	
	private String assessmentTypeId; //'W', 'L'...
	private String assessmentTypeName;
//	same for all versions of the assessment (used to differentiate between create (null) and update also).
	private String masterAssessmentId; 
	private String assessmentId; //OID
	
//	used to check if Assessment is COMPLETE or INCOMPLETE
	private String assessmentStatusCd;
	
//	only for SCS - holds masterAssessmentId of its corresponding Force Field Analysis
	private String forceFieldMasterAssessmentId;
//	only for SCS � holds the AssessmentId (OID) of its corresponding Force Field Analysis
	private String forceFieldAssessmentId;
//	used to check if Force Field Assessment is COMPLETE or INCOMPLETE
	private String forceFieldAssessStatusCd;
	
	private Date assessmentDate;
	private Date migratedAssessmentDate;

	//	for Wisconsin and LSI-R only
	private boolean isInitial;
//	for Wisconsin and LSI-R Reassessments only
	private Date dueDate;
	
//	for Wisconsin and LSI-R
	private String riskScore;
	private String needsScore;
	
//	for SCS only
	private String primaryClassificationTypeId;
	private String secondaryClassificationTypeId;	
	
	private String specificTypeAssessmentId;

//	for SCS and Force Field only
	private boolean forceFieldFound;
	private boolean scsInventoryForceFld;
	private boolean scsInterviewForceFld;
	
	
	
	public String getSpecificTypeAssessmentId() {
		return specificTypeAssessmentId;
	}
	public void setSpecificTypeAssessmentId(String specificTypeAssessmentId) {
		this.specificTypeAssessmentId = specificTypeAssessmentId;
	}
	/**
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate() {
		return assessmentDate;
	}
	/**
	 * @param assessmentDate The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	/**
	 * @return Returns the assessmentId.
	 */
	public String getAssessmentId() {
		return assessmentId;
	}
	/**
	 * @param assessmentId The assessmentId to set.
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	/**
	 * @return Returns the assessmentTypeId.
	 */
	public String getAssessmentTypeId() {
		return assessmentTypeId;
	}
	/**
	 * @param assessmentTypeId The assessmentTypeId to set.
	 */
	public void setAssessmentTypeId(String assessmentTypeId) {
		this.assessmentTypeId = assessmentTypeId;
	}
	/**
	 * @return Returns the assessmentTypeName.
	 */
	public String getAssessmentTypeName() {
		return assessmentTypeName;
	}
	/**
	 * @param assessmentTypeName The assessmentTypeName to set.
	 */
	public void setAssessmentTypeName(String assessmentTypeName) {
		this.assessmentTypeName = assessmentTypeName;
	}

/**
 * @return Returns the dueDate.
 */
public Date getDueDate() {
	return dueDate;
}
/**
 * @param dueDate The dueDate to set.
 */
public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
}
/**
 * @return Returns the forceFieldAssessmentId.
 */
public String getForceFieldAssessmentId() {
	return forceFieldAssessmentId;
}
/**
 * @param forceFieldAssessmentId The forceFieldAssessmentId to set.
 */
public void setForceFieldAssessmentId(String forceFieldAssessmentId) {
	this.forceFieldAssessmentId = forceFieldAssessmentId;
}
/**
 * @return Returns the forceFieldMasterAssessmentId.
 */
public String getForceFieldMasterAssessmentId() {
	return forceFieldMasterAssessmentId;
}
/**
 * @param forceFieldMasterAssessmentId The forceFieldMasterAssessmentId to set.
 */
public void setForceFieldMasterAssessmentId(String forceFieldMasterAssessmentId) {
	this.forceFieldMasterAssessmentId = forceFieldMasterAssessmentId;
}
/**
 * @return Returns the isInitial.
 */
public boolean isInitial() {
	return isInitial;
}
/**
 * @param isInitial The isInitial to set.
 */
public void setInitial(boolean isInitial) {
	this.isInitial = isInitial;
}
/**
 * @return Returns the masterAssessmentId.
 */
public String getMasterAssessmentId() {
	return masterAssessmentId;
}
/**
 * @param masterAssessmentId The masterAssessmentId to set.
 */
public void setMasterAssessmentId(String masterAssessmentId) {
	this.masterAssessmentId = masterAssessmentId;
}
	/**
	 * @return Returns the needsScore.
	 */
	public String getNeedsScore() {
		return needsScore;
	}
	/**
	 * @param needsScore The needsScore to set.
	 */
	public void setNeedsScore(String needsScore) {
		this.needsScore = needsScore;
	}
/**
 * @return Returns the primaryClassificationTypeId.
 */
public String getPrimaryClassificationTypeId() {
	return primaryClassificationTypeId;
}
/**
 * @param primaryClassificationTypeId The primaryClassificationTypeId to set.
 */
public void setPrimaryClassificationTypeId(String primaryClassificationTypeId) {
	this.primaryClassificationTypeId = primaryClassificationTypeId;
}
/**
 * @return Returns the riskScore.
 */
public String getRiskScore() {
	return riskScore;
}
/**
 * @param riskScore The riskScore to set.
 */
public void setRiskScore(String riskScore) {
	this.riskScore = riskScore;
}
	/**
	 * @return Returns the secondaryClassificationTypeId.
	 */
	public String getSecondaryClassificationTypeId() {
		return secondaryClassificationTypeId;
	}
	/**
	 * @param secondaryClassificationTypeId The secondaryClassificationTypeId to set.
	 */
	public void setSecondaryClassificationTypeId(String secondaryClassificationTypeId) {
		this.secondaryClassificationTypeId = secondaryClassificationTypeId;
	}
	/**
	 * @return the assessmentStatusCd
	 */
	public String getAssessmentStatusCd() {
		return assessmentStatusCd;
	}
	/**
	 * @param assessmentStatusCd the assessmentStatusCd to set
	 */
	public void setAssessmentStatusCd(String assessmentStatusCd) {
		this.assessmentStatusCd = assessmentStatusCd;
	}
	/**
	 * @return the forceFieldAssessStatusCd
	 */
	public String getForceFieldAssessStatusCd() {
		return forceFieldAssessStatusCd;
	}
	/**
	 * @param forceFieldAssessStatusCd the forceFieldAssessStatusCd to set
	 */
	public void setForceFieldAssessStatusCd(String forceFieldAssessStatusCd) {
		this.forceFieldAssessStatusCd = forceFieldAssessStatusCd;
	}	
	
	public Date getMigratedAssessmentDate() {
			return migratedAssessmentDate;
		}
	
	public void setMigratedAssessmentDate(Date migratedAssessmentDate) {
			this.migratedAssessmentDate = migratedAssessmentDate;
		}
	/**
	 * @return the forceFieldFound
	 */
	public boolean isForceFieldFound() {
		return forceFieldFound;
	}
	/**
	 * @param forceFieldFound the forceFieldFound to set
	 */
	public void setForceFieldFound(boolean forceFieldFound) {
		this.forceFieldFound = forceFieldFound;
	}
	/**
	 * @return the scsInventoryForceFld
	 */
	public boolean isScsInventoryForceFld() {
		return scsInventoryForceFld;
	}
	/**
	 * @param scsInventoryForceFld the scsInventoryForceFld to set
	 */
	public void setScsInventoryForceFld(boolean scsInventoryForceFld) {
		this.scsInventoryForceFld = scsInventoryForceFld;
	}
	/**
	 * @return the scsInterviewForceFld
	 */
	public boolean isScsInterviewForceFld() {
		return scsInterviewForceFld;
	}
	/**
	 * @param scsInterviewForceFld the scsInterviewForceFld to set
	 */
	public void setScsInterviewForceFld(boolean scsInterviewForceFld) {
		this.scsInterviewForceFld = scsInterviewForceFld;
	}
	
}
