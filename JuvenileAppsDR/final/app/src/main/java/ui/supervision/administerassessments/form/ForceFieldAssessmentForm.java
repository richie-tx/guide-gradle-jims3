/*
 * Created on Feb 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.form;

import java.util.ArrayList;
import java.util.Collection;



/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ForceFieldAssessmentForm extends AssessmentTypeForm
{
	private String scsMasterAssessmentId = "";
	private boolean afterSCSOperation = false;
	private String createUpdateSCSId = "";
	
	private String scsIntrvwMasterAssessmentId = "";
	private boolean afterSCSIntrvwOperation = false;
	private String createUpdateSCSIntrvwId = "";
	
	private Collection forceFieldQuestionsList = new ArrayList();
	private String forceFieldScreenType = "";
	private String taskId = "";
	
	
	
	public void clear()
	{
		action = "";
		secondaryAction = "";
		
		defendantId = "";
		supervisionBeginDate = null;
		supervisionEndDate = null;
		
		supervisionPeriod = "";
		
		assessmentTypeId = "";
		masterAssessmentId = "";
		taskId = "";
		
		assessmentIncomplete = false;
		
		versionId = "";
		assessmentId = "";
		
		assessmentDate = null;
		assessmentDateStr = "";
		
		islatestVersionShown = false;
		
		versionDetailsList = new ArrayList(); // map of prior version with versionId - key, and a collection of transaction date, assessment date and assessmentId(OID) - maintain an Inner class for the collection
		questionAnswerList = new ArrayList();
		
		scsMasterAssessmentId = "";
		afterSCSOperation = false;
		createUpdateSCSId = "";
		
		scsIntrvwMasterAssessmentId = "";
		afterSCSIntrvwOperation = false;
		createUpdateSCSIntrvwId = "";
		
		forceFieldQuestionsList = new ArrayList();
		forceFieldScreenType= "";
		
		sentToState = false;
		migrated ="";
	}
	
	public void clearSelected()
	{
		selectedAssessmentBeanId = "";
		selectedAssessSupervisionPrd = "";
		selectedVersionNumber = "";
		selectedAssessmentBeanObj = null;
	}
	

	public void clearAll()
	{
		clear();
		clearSelected();
	}
	
	
	
	/**
	 * @return Returns the createUpdateSCSId.
	 */
	public String getCreateUpdateSCSId() {
		return createUpdateSCSId;
	}
	/**
	 * @param createUpdateSCSId The createUpdateSCSId to set.
	 */
	public void setCreateUpdateSCSId(String createUpdateSCSId) {
		this.createUpdateSCSId = createUpdateSCSId;
	}
	/**
	 * @return Returns the afterSCSOperation.
	 */
	public boolean isAfterSCSOperation() {
		return afterSCSOperation;
	}
	/**
	 * @param afterSCSOperation The afterSCSOperation to set.
	 */
	public void setAfterSCSOperation(boolean afterSCSOperation) {
		this.afterSCSOperation = afterSCSOperation;
	}
	/**
	 * @return Returns the forceFieldScreenType.
	 */
	public String getForceFieldScreenType() {
		return forceFieldScreenType;
	}
	/**
	 * @param forceFieldScreenType The forceFieldScreenType to set.
	 */
	public void setForceFieldScreenType(String forceFieldScreenType) {
		this.forceFieldScreenType = forceFieldScreenType;
	}
	/**
	 * @return Returns the forceFieldQuestionsList.
	 */
	public Collection getForceFieldQuestionsList() {
		return forceFieldQuestionsList;
	}
	/**
	 * @param forceFieldQuestionsList The forceFieldQuestionsList to set.
	 */
	public void setForceFieldQuestionsList(Collection forceFieldQuestionsList) {
		this.forceFieldQuestionsList = forceFieldQuestionsList;
	}
	/**
	 * @return Returns the scsMasterAssessmentId.
	 */
	public String getScsMasterAssessmentId() {
		return scsMasterAssessmentId;
	}
	/**
	 * @param scsMasterAssessmentId The scsMasterAssessmentId to set.
	 */
	public void setScsMasterAssessmentId(String scsMasterAssessmentId) {
		this.scsMasterAssessmentId = scsMasterAssessmentId;
	}

	/**
	 * @return the scsIntrvwMasterAssessmentId
	 */
	public String getScsIntrvwMasterAssessmentId() {
		return scsIntrvwMasterAssessmentId;
	}

	/**
	 * @param scsIntrvwMasterAssessmentId the scsIntrvwMasterAssessmentId to set
	 */
	public void setScsIntrvwMasterAssessmentId(String scsIntrvwMasterAssessmentId) {
		this.scsIntrvwMasterAssessmentId = scsIntrvwMasterAssessmentId;
	}

	/**
	 * @return the afterSCSIntrvwOperation
	 */
	public boolean isAfterSCSIntrvwOperation() {
		return afterSCSIntrvwOperation;
	}

	/**
	 * @param afterSCSIntrvwOperation the afterSCSIntrvwOperation to set
	 */
	public void setAfterSCSIntrvwOperation(boolean afterSCSIntrvwOperation) {
		this.afterSCSIntrvwOperation = afterSCSIntrvwOperation;
	}

	/**
	 * @return the createUpdateSCSIntrvwId
	 */
	public String getCreateUpdateSCSIntrvwId() {
		return createUpdateSCSIntrvwId;
	}

	/**
	 * @param createUpdateSCSIntrvwId the createUpdateSCSIntrvwId to set
	 */
	public void setCreateUpdateSCSIntrvwId(String createUpdateSCSIntrvwId) {
		this.createUpdateSCSIntrvwId = createUpdateSCSIntrvwId;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}
