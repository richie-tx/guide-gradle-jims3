/*
 * Created on Jan 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments;

import java.util.Comparator;
import java.util.Date;
import mojo.km.utilities.DateUtil;


/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssessmentLightBean implements Comparable<AssessmentLightBean>
{	
	public String sortOrder= ""; // use string starting from 1 upward. Will be ignored if blank
//	to distinguish between each assessment displayed on Assessment Summary Page
	private String assessmentBeanId = "";
	
	private String assessmentTypeId = "";	
	private String assessmentTypeName = "";
	
	private String masterAssessmentId = ""; // same for all versions of an assessment	
	private String assessmentId = ""; // OID of an assessment 	
	
//	used for Wisconsin and LSIR only
	private boolean isInitial = false;
	
//	used to hold - "ASSESSMENT_EXIST" or "ASSESSMENT_NOT_EXIST"
	private String assessmentStatus = "";
	
	private String forceFieldAssessmentTypeId = "";
	private String forceFieldAssessmentTypeName = "";
	private String forceFieldAssessmentStatus = "";
	private Date forceFieldAssessmentDate = null;
	private String forceFieldAssessmentDateStr = "";
	private String forceFieldMasterAssessId = null;
	private String forceFieldAssessmentId = null;
	
	private Date assessmentDate = null; 
	private Date actualAssessmentDate = null; //Migrated Assessment Date
	private String assessmentDateStr = "";	
	private Date assessmentDueDate = null;	
	private String assessmentDueDateStr = null;
	
	private String riskScore = "";	
	private String needsScore = "";
	
	private String primaryClassificationId = "";	
	private String primaryClassificationStr = "";
	private String secondaryClassificationId = "";
	private String secondaryClassificationStr = "";
	
//	used to hold - assessment 'COMPLETE' or 'INCOMPLETE'
	private boolean statusComplete = false;
	private String migrated = "";
	private String statusDesc = "";

	private boolean forceFieldStatusComplete = false;
	private String forceFieldStatusDesc = "";
	
	/**
	 * sort based on the assessment order, then status, and then assessment date status
	 */
	public static Comparator AssessmentOrderComparator = new Comparator() {
		public int compare(Object assessmentVersion, Object previousAssessmentVersion){
		
		if(assessmentVersion == null || previousAssessmentVersion == null || !(assessmentVersion instanceof AssessmentLightBean) 
				||  !(previousAssessmentVersion instanceof AssessmentLightBean))
		{
			return 1;
		}
		AssessmentLightBean assessment = (AssessmentLightBean)assessmentVersion;
		AssessmentLightBean previousAssessment = (AssessmentLightBean)previousAssessmentVersion;
		Date date1 = assessment.assessmentDate;
		Date date2 = previousAssessment.assessmentDate;
		
		//first check if both the sortOrder fields on the bean are set. If they are use these to sort first, 
		// if not just use assess date and status desc
		if(assessment.getSortOrder() != null && assessment.getSortOrder().length() > 0 
				&& previousAssessment.getSortOrder()!= null && previousAssessment.getSortOrder().length() > 0){
			if(assessment.getSortOrder().compareTo(previousAssessment.getSortOrder())!=0){	// sort on sortOrder field
				return assessment.getSortOrder().compareTo(previousAssessment.getSortOrder());
			}else if(previousAssessment.getStatusDesc().compareTo(assessment.getStatusDesc())!= 0){
				return previousAssessment.getStatusDesc().compareTo(assessment.getStatusDesc());
			}else if(date1 != null && date2 != null){
				return DateUtil.compare(date2, date1, DateUtil.DATE_FMT_1);
			}else{
				return 0;
			}
		}else if(assessment.getStatusDesc() != null && previousAssessment.getStatusDesc() != null) { // one of beans has no sort order so ignore sort Order
			if(previousAssessment.getStatusDesc().compareTo(assessment.getStatusDesc())!= 0){
				return previousAssessment.getStatusDesc().compareTo(assessment.getStatusDesc());
			}else if(date1 != null && date2 != null){
				return DateUtil.compare(date2, date1, DateUtil.DATE_FMT_1);
			}else{
				return 0;
			}
		}else{	// one of the sortOrder and one of the status desc were not populated so use date
			if(date1 != null && date2 != null){
				return DateUtil.compare(date2, date1, DateUtil.DATE_FMT_1);
			}else{
				return 0;
			}
		}
		}
	};
	
	/**
	 * comparable method to sort only based on the date
	 */
	public int compareTo(AssessmentLightBean o) 
	{
		if((o == null) || !(o instanceof AssessmentLightBean))
		{
			return 1;
		}
		AssessmentLightBean beanObj2 = (AssessmentLightBean)o;
		
		Date date1 = this.assessmentDate;
		Date date2 = beanObj2.assessmentDate;
		
		if(date1!= null && date2 != null)
		{
			return DateUtil.compare(date1, date2, DateUtil.DATE_FMT_1);
		}
		else
		{
			return 1;
		}
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getMigrated() {
		return migrated;
	}

	public void setMigrated(String migrated) {
		this.migrated = migrated;
	}

	/**
	 * @return Returns the assessmentStatus.
	 */
	public String getAssessmentStatus() {
		return assessmentStatus;
	}
	/**
	 * @param assessmentStatus The assessmentStatus to set.
	 */
	public void setAssessmentStatus(String assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
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
	 * @return Returns the assessmentDateStr.
	 */
	public String getAssessmentDateStr() {
		return assessmentDateStr;
	}
	/**
	 * @param assessmentDateStr The assessmentDateStr to set.
	 */
	public void setAssessmentDateStr(String assessmentDateStr) {
		this.assessmentDateStr = assessmentDateStr;
	}
	/**
	 * @return Returns the assessmentDueDate.
	 */
	public Date getAssessmentDueDate() {
		return assessmentDueDate;
	}
	/**
	 * @param assessmentDueDate The assessmentDueDate to set.
	 */
	public void setAssessmentDueDate(Date assessmentDueDate) {
		this.assessmentDueDate = assessmentDueDate;
	}	
	/**
	 * @return Returns the assessmentDueDateStr.
	 */
	public String getAssessmentDueDateStr() {
		return assessmentDueDateStr;
	}
	/**
	 * @param assessmentDueDateStr The assessmentDueDateStr to set.
	 */
	public void setAssessmentDueDateStr(String assessmentDueDateStr) {
		this.assessmentDueDateStr = assessmentDueDateStr;
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
	 * @return Returns the primaryClassificationId.
	 */
	public String getPrimaryClassificationId() {
		return primaryClassificationId;
	}
	/**
	 * @param primaryClassificationId The primaryClassificationId to set.
	 */
	public void setPrimaryClassificationId(String primaryClassificationId) {
		this.primaryClassificationId = primaryClassificationId;
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
	 * @return Returns the secondaryClassificationId.
	 */
	public String getSecondaryClassificationId() {
		return secondaryClassificationId;
	}
	/**
	 * @param secondaryClassificationId The secondaryClassificationId to set.
	 */
	public void setSecondaryClassificationId(String secondaryClassificationId) {
		this.secondaryClassificationId = secondaryClassificationId;
	}	
	/**
	 * @return Returns the primaryClassificationStr.
	 */
	public String getPrimaryClassificationStr() {
		return primaryClassificationStr;
	}
	/**
	 * @param primaryClassificationStr The primaryClassificationStr to set.
	 */
	public void setPrimaryClassificationStr(String primaryClassificationStr) {
		this.primaryClassificationStr = primaryClassificationStr;
	}
	
	public Date getActualAssessmentDate() {
		return actualAssessmentDate;
	}
	
	public void setActualAssessmentDate(Date actualAssessmentDate) {
		this.actualAssessmentDate = actualAssessmentDate;
	}
	/**
	 * @return Returns the secondaryClassificationStr.
	 */
	public String getSecondaryClassificationStr() {
		return secondaryClassificationStr;
	}
	/**
	 * @param secondaryClassificationStr The secondaryClassificationStr to set.
	 */
	public void setSecondaryClassificationStr(String secondaryClassificationStr) {
		this.secondaryClassificationStr = secondaryClassificationStr;
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
	 * @return Returns the forceFieldMasterAssessId.
	 */
	public String getForceFieldMasterAssessId() {
		return forceFieldMasterAssessId;
	}
	/**
	 * @param forceFieldMasterAssessId The forceFieldMasterAssessId to set.
	 */
	public void setForceFieldMasterAssessId(String forceFieldMasterAssessId) {
		this.forceFieldMasterAssessId = forceFieldMasterAssessId;
	}		
	/**
	 * @return Returns the forceFieldAssessmentTypeName.
	 */
	public String getForceFieldAssessmentTypeName() {
		return forceFieldAssessmentTypeName;
	}
	/**
	 * @param forceFieldAssessmentTypeName The forceFieldAssessmentTypeName to set.
	 */
	public void setForceFieldAssessmentTypeName(String forceFieldAssessmentTypeName) {
		this.forceFieldAssessmentTypeName = forceFieldAssessmentTypeName;
	}	
	/**
	 * @return Returns the forceFieldAssessmentTypeId.
	 */
	public String getForceFieldAssessmentTypeId() {
		return forceFieldAssessmentTypeId;
	}
	/**
	 * @param forceFieldAssessmentTypeId The forceFieldAssessmentTypeId to set.
	 */
	public void setForceFieldAssessmentTypeId(String forceFieldAssessmentTypeId) {
		this.forceFieldAssessmentTypeId = forceFieldAssessmentTypeId;
	}		
	
	/**
	 * @return Returns the forceFieldAssessmentStatus.
	 */
	public String getForceFieldAssessmentStatus() {
		return forceFieldAssessmentStatus;
	}
	/**
	 * @param forceFieldAssessmentStatus The forceFieldAssessmentStatus to set.
	 */
	public void setForceFieldAssessmentStatus(String forceFieldAssessmentStatus) {
		this.forceFieldAssessmentStatus = forceFieldAssessmentStatus;
	}
	/**
	 * @return Returns the assessmentBeanId.
	 */
	public String getAssessmentBeanId() {
		return assessmentBeanId;
	}
	/**
	 * @param assessmentBeanId The assessmentBeanId to set.
	 */
	public void setAssessmentBeanId(String assessmentBeanId) {
		this.assessmentBeanId = assessmentBeanId;
	}
	
	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}
	
	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	/**
	 * @return the statusComplete
	 */
	public boolean isStatusComplete() {
		return statusComplete;
	}
	
	/**
	 * @param statusComplete the statusComplete to set
	 */
	public void setStatusComplete(boolean statusComplete) {
		this.statusComplete = statusComplete;
	}


	/**
	 * @return the forceFieldStatusComplete
	 */
	public boolean isForceFieldStatusComplete() {
		return forceFieldStatusComplete;
	}


	/**
	 * @param forceFieldStatusComplete the forceFieldStatusComplete to set
	 */
	public void setForceFieldStatusComplete(boolean forceFieldStatusComplete) {
		this.forceFieldStatusComplete = forceFieldStatusComplete;
	}


	/**
	 * @return the forceFieldStatusDesc
	 */
	public String getForceFieldStatusDesc() {
		return forceFieldStatusDesc;
	}


	/**
	 * @param forceFieldStatusDesc the forceFieldStatusDesc to set
	 */
	public void setForceFieldStatusDesc(String forceFieldStatusDesc) {
		this.forceFieldStatusDesc = forceFieldStatusDesc;
	}

	/**
	 * @return the forceFieldAssessmentDate
	 */
	public Date getForceFieldAssessmentDate() {
		return forceFieldAssessmentDate;
	}

	/**
	 * @param forceFieldAssessmentDate the forceFieldAssessmentDate to set
	 */
	public void setForceFieldAssessmentDate(Date forceFieldAssessmentDate) {
		if(forceFieldAssessmentDate != null)
		{
			try
			{
				this.forceFieldAssessmentDateStr = DateUtil.dateToString(forceFieldAssessmentDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.forceFieldAssessmentDateStr = "";
			}
		}
		this.forceFieldAssessmentDate = forceFieldAssessmentDate;
	}

	/**
	 * @return the forceFieldAssessmentDateStr
	 */
	public String getForceFieldAssessmentDateStr() {
		return forceFieldAssessmentDateStr;
	}
	
}
