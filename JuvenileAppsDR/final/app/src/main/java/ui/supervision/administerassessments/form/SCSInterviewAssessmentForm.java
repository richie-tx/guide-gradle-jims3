package ui.supervision.administerassessments.form;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author cc_bjangay
 *
 */
public class SCSInterviewAssessmentForm extends AssessmentTypeForm
{
	private Collection scsIntrvScreenOneQuestionsList = new ArrayList(); // It is a collection of ui answers (converted)																	
	private Collection scsIntrvScreenTwoQuestionsList = new ArrayList(); // It is a collection of ui answers (converted)																	
	private Collection scsIntrvScreenThreeQuestionsList = new ArrayList(); // It is a collection  of ui answers(converted)																		
	private Collection scsIntrvScreenFourQuestionsList = new ArrayList(); // It is a collection of ui answers(converted)	
	private Collection scsIntrvScreenFiveQuestionsList = new ArrayList(); // It is a collection of ui answers(converted)	
	private Collection scsIntrvScreenSixQuestionsList = new ArrayList(); // It is a collection of ui answers(converted)	
																		
	private Collection scsSummaryQuestionsList = new ArrayList();

	private String forceFieldAssessmentStatus = "";
	private String forceFieldAssessmentTypeId = "";
	private String forceFieldMasterAssessId = null;
	private String forceFieldAssessmentId = null;
	private boolean forceFieldAssessmentIncomplete = false;
	
	private String scsScreenType = "";

	private String createUpdateSCSInterviewId = "";
	private String createUpdateSCSIntervwMasterAssessId = "";
	private String taskId = "";
	
	private boolean isForceFieldContinued = false;

	private String confirmationMsg = "";
	
	
	
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
		
		assessmentIncomplete = false;
		forceFieldAssessmentIncomplete = false;
		
		versionId = "";
		assessmentId = "";

		assessmentDate = null;
		assessmentDateStr = "";

		islatestVersionShown = false;

		versionDetailsList = new ArrayList(); // map of prior version with versionId - key, and a collection of transaction date, assessment date and
											 // assessmentId(OID) - maintain an Inner class for the collection
		questionAnswerList = new ArrayList();

		scsIntrvScreenOneQuestionsList = new ArrayList();
		scsIntrvScreenTwoQuestionsList = new ArrayList();
		scsIntrvScreenThreeQuestionsList = new ArrayList();
		scsIntrvScreenFourQuestionsList = new ArrayList();
		scsIntrvScreenFiveQuestionsList = new ArrayList();
		scsIntrvScreenSixQuestionsList = new ArrayList();
		
		scsSummaryQuestionsList = new ArrayList();

		forceFieldAssessmentStatus = "";
		forceFieldAssessmentTypeId = "";
		forceFieldMasterAssessId = "";
		forceFieldAssessmentId = "";

		questionAnswerList = new ArrayList();

		scsScreenType = "";

		createUpdateSCSInterviewId = "";
		createUpdateSCSIntervwMasterAssessId = "";
		taskId = "";
		
		isForceFieldContinued = false;

		confirmationMsg = "";

		sentToState = false;
	}

	
	public void clearSelected() 
	{
		selectedAssessmentBeanId = "";
		selectedAssessSupervisionPrd = "";
		selectedVersionNumber = "";
		selectedAssessmentBeanObj = null;
	}

	public void clearAll() {
		clear();
		clearSelected();
	}

	/**
	 * @return the scsIntrvScreenOneQuestionsList
	 */
	public Collection getScsIntrvScreenOneQuestionsList() {
		return scsIntrvScreenOneQuestionsList;
	}


	/**
	 * @param scsIntrvScreenOneQuestionsList the scsIntrvScreenOneQuestionsList to set
	 */
	public void setScsIntrvScreenOneQuestionsList(
			Collection scsIntrvScreenOneQuestionsList) {
		this.scsIntrvScreenOneQuestionsList = scsIntrvScreenOneQuestionsList;
	}


	/**
	 * @return the scsIntrvScreenTwoQuestionsList
	 */
	public Collection getScsIntrvScreenTwoQuestionsList() {
		return scsIntrvScreenTwoQuestionsList;
	}


	/**
	 * @param scsIntrvScreenTwoQuestionsList the scsIntrvScreenTwoQuestionsList to set
	 */
	public void setScsIntrvScreenTwoQuestionsList(
			Collection scsIntrvScreenTwoQuestionsList) {
		this.scsIntrvScreenTwoQuestionsList = scsIntrvScreenTwoQuestionsList;
	}


	/**
	 * @return the scsIntrvScreenThreeQuestionsList
	 */
	public Collection getScsIntrvScreenThreeQuestionsList() {
		return scsIntrvScreenThreeQuestionsList;
	}


	/**
	 * @param scsIntrvScreenThreeQuestionsList the scsIntrvScreenThreeQuestionsList to set
	 */
	public void setScsIntrvScreenThreeQuestionsList(
			Collection scsIntrvScreenThreeQuestionsList) {
		this.scsIntrvScreenThreeQuestionsList = scsIntrvScreenThreeQuestionsList;
	}


	/**
	 * @return the scsIntrvScreenFourQuestionsList
	 */
	public Collection getScsIntrvScreenFourQuestionsList() {
		return scsIntrvScreenFourQuestionsList;
	}


	/**
	 * @param scsIntrvScreenFourQuestionsList the scsIntrvScreenFourQuestionsList to set
	 */
	public void setScsIntrvScreenFourQuestionsList(
			Collection scsIntrvScreenFourQuestionsList) {
		this.scsIntrvScreenFourQuestionsList = scsIntrvScreenFourQuestionsList;
	}


	/**
	 * @return the scsIntrvScreenFiveQuestionsList
	 */
	public Collection getScsIntrvScreenFiveQuestionsList() {
		return scsIntrvScreenFiveQuestionsList;
	}


	/**
	 * @param scsIntrvScreenFiveQuestionsList the scsIntrvScreenFiveQuestionsList to set
	 */
	public void setScsIntrvScreenFiveQuestionsList(
			Collection scsIntrvScreenFiveQuestionsList) {
		this.scsIntrvScreenFiveQuestionsList = scsIntrvScreenFiveQuestionsList;
	}


	/**
	 * @return the scsIntrvScreenSixQuestionsList
	 */
	public Collection getScsIntrvScreenSixQuestionsList() {
		return scsIntrvScreenSixQuestionsList;
	}


	/**
	 * @param scsIntrvScreenSixQuestionsList the scsIntrvScreenSixQuestionsList to set
	 */
	public void setScsIntrvScreenSixQuestionsList(
			Collection scsIntrvScreenSixQuestionsList) {
		this.scsIntrvScreenSixQuestionsList = scsIntrvScreenSixQuestionsList;
	}


	/**
	 * @return the scsSummaryQuestionsList
	 */
	public Collection getScsSummaryQuestionsList() {
		return scsSummaryQuestionsList;
	}



	/**
	 * @param scsSummaryQuestionsList the scsSummaryQuestionsList to set
	 */
	public void setScsSummaryQuestionsList(Collection scsSummaryQuestionsList) {
		this.scsSummaryQuestionsList = scsSummaryQuestionsList;
	}



	/**
	 * @return the forceFieldAssessmentStatus
	 */
	public String getForceFieldAssessmentStatus() {
		return forceFieldAssessmentStatus;
	}



	/**
	 * @param forceFieldAssessmentStatus the forceFieldAssessmentStatus to set
	 */
	public void setForceFieldAssessmentStatus(String forceFieldAssessmentStatus) {
		this.forceFieldAssessmentStatus = forceFieldAssessmentStatus;
	}



	/**
	 * @return the forceFieldAssessmentTypeId
	 */
	public String getForceFieldAssessmentTypeId() {
		return forceFieldAssessmentTypeId;
	}



	/**
	 * @param forceFieldAssessmentTypeId the forceFieldAssessmentTypeId to set
	 */
	public void setForceFieldAssessmentTypeId(String forceFieldAssessmentTypeId) {
		this.forceFieldAssessmentTypeId = forceFieldAssessmentTypeId;
	}



	/**
	 * @return the forceFieldMasterAssessId
	 */
	public String getForceFieldMasterAssessId() {
		return forceFieldMasterAssessId;
	}



	/**
	 * @param forceFieldMasterAssessId the forceFieldMasterAssessId to set
	 */
	public void setForceFieldMasterAssessId(String forceFieldMasterAssessId) {
		this.forceFieldMasterAssessId = forceFieldMasterAssessId;
	}



	/**
	 * @return the forceFieldAssessmentId
	 */
	public String getForceFieldAssessmentId() {
		return forceFieldAssessmentId;
	}



	/**
	 * @param forceFieldAssessmentId the forceFieldAssessmentId to set
	 */
	public void setForceFieldAssessmentId(String forceFieldAssessmentId) {
		this.forceFieldAssessmentId = forceFieldAssessmentId;
	}



	/**
	 * @return the forceFieldAssessmentIncomplete
	 */
	public boolean isForceFieldAssessmentIncomplete() {
		return forceFieldAssessmentIncomplete;
	}



	/**
	 * @param forceFieldAssessmentIncomplete the forceFieldAssessmentIncomplete to set
	 */
	public void setForceFieldAssessmentIncomplete(
			boolean forceFieldAssessmentIncomplete) {
		this.forceFieldAssessmentIncomplete = forceFieldAssessmentIncomplete;
	}



	/**
	 * @return the scsScreenType
	 */
	public String getScsScreenType() {
		return scsScreenType;
	}



	/**
	 * @param scsScreenType the scsScreenType to set
	 */
	public void setScsScreenType(String scsScreenType) {
		this.scsScreenType = scsScreenType;
	}



	/**
	 * @return the createUpdateSCSInterviewId
	 */
	public String getCreateUpdateSCSInterviewId() {
		return createUpdateSCSInterviewId;
	}



	/**
	 * @param createUpdateSCSInterviewId the createUpdateSCSInterviewId to set
	 */
	public void setCreateUpdateSCSInterviewId(String createUpdateSCSInterviewId) {
		this.createUpdateSCSInterviewId = createUpdateSCSInterviewId;
	}



	/**
	 * @return the createUpdateSCSIntervwMasterAssessId
	 */
	public String getCreateUpdateSCSIntervwMasterAssessId() {
		return createUpdateSCSIntervwMasterAssessId;
	}



	/**
	 * @param createUpdateSCSIntervwMasterAssessId the createUpdateSCSIntervwMasterAssessId to set
	 */
	public void setCreateUpdateSCSIntervwMasterAssessId(
			String createUpdateSCSIntervwMasterAssessId) {
		this.createUpdateSCSIntervwMasterAssessId = createUpdateSCSIntervwMasterAssessId;
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


	/**
	 * @return the isForceFieldContinued
	 */
	public boolean isForceFieldContinued() {
		return isForceFieldContinued;
	}



	/**
	 * @param isForceFieldContinued the isForceFieldContinued to set
	 */
	public void setForceFieldContinued(boolean isForceFieldContinued) {
		this.isForceFieldContinued = isForceFieldContinued;
	}



	/**
	 * @return the confirmationMsg
	 */
	public String getConfirmationMsg() {
		return confirmationMsg;
	}



	/**
	 * @param confirmationMsg the confirmationMsg to set
	 */
	public void setConfirmationMsg(String confirmationMsg) {
		this.confirmationMsg = confirmationMsg;
	}
	
}
