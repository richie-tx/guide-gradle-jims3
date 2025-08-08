/*
 * Created on Feb 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionMapping;

import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;
import ui.common.SimpleCodeTableHelper;

/**
 * @author cc_bjangay
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SCSAssessmentForm extends AssessmentTypeForm {
	private Collection scsScreenOneQuestionsList = new ArrayList(); // It is a collection of ui answers (converted)																	
	private Collection scsScreenTwoQuestionsList = new ArrayList(); // It is a collection of ui answers (converted)																	
	private Collection scsScreenThreeQuestionsList = new ArrayList(); // It is a collection  of ui answers(converted)																		
	private Collection scsScreenFourQuestionsList = new ArrayList(); // It is a collection of ui answers(converted)	
																		
	private Collection assessmentClassificationCodes;

	private Collection scsSummaryQuestionsList = new ArrayList();

	private String comments = "";

	// map of classification type(key- String) and total (value - Integer)
	private Map classificationTypeTotalsMap = new HashMap();

	private String totalSI = "";
	private String totalCC = "";
	private String totalES = "";
	private String totalLS = "";

	private String highestSI = "";

	private int[] scsTotals = null;

	private Collection clasificationTypeList = new ArrayList();

	private String primaryClassificationTypeId = "";
	private String primaryClassificationTypeDesc = "";

	private String secondaryClassificationTypeId = "";
	private String secondaryClassificationTypeDesc = "";

	private String forceFieldAssessmentStatus = "";
	private String forceFieldAssessmentTypeId = "";
	private String forceFieldMasterAssessId = null;
	private String forceFieldAssessmentId = null;
	private boolean forceFieldAssessmentIncomplete = false;
	
	private String scsScreenType = "";

	private String createUpdateSCSId = "";
	private String createUpdateSCSMasterAssessId = "";
	private String taskId = "";
	
	private boolean isForceFieldContinued = false;

	private String confirmationMsg = "";

	public void clear() {
		action = "";
		secondaryAction = "";

		defendantId = "";
		supervisionBeginDate = null;
		supervisionEndDate = null;

		supervisionPeriod = "";

		assessmentTypeId = "";
		masterAssessmentId = "";
		migrated ="";
		
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

		scsScreenOneQuestionsList = new ArrayList();
		scsScreenTwoQuestionsList = new ArrayList();
		scsScreenThreeQuestionsList = new ArrayList();
		scsScreenFourQuestionsList = new ArrayList();

		scsSummaryQuestionsList = new ArrayList();

		comments = "";

		classificationTypeTotalsMap = new HashMap();

		totalSI = "";
		totalCC = "";
		totalES = "";
		totalLS = "";

		scsTotals = null;

		clasificationTypeList = new ArrayList();

		primaryClassificationTypeId = "";
		primaryClassificationTypeDesc = "";

		secondaryClassificationTypeId = "";
		secondaryClassificationTypeDesc = "";

		forceFieldAssessmentStatus = "";
		forceFieldAssessmentTypeId = "";
		forceFieldMasterAssessId = "";
		forceFieldAssessmentId = "";

		questionAnswerList = new ArrayList();

		scsScreenType = "";

		createUpdateSCSId = "";
		createUpdateSCSMasterAssessId = "";
		taskId = "";
		
		isForceFieldContinued = false;

		confirmationMsg = "";

		sentToState = false;
	}

	public void clearSelected() {
		selectedAssessmentBeanId = "";
		selectedAssessSupervisionPrd = "";
		selectedVersionNumber = "";
		selectedAssessmentBeanObj = null;
	}

	public void clearAll() {
		clear();
		clearSelected();
	}

	public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
		String resetCheckBoxes = (String) aRequest
				.getParameter("clearSelectedCheckBoxes");

		if ((resetCheckBoxes != null)
				&& (resetCheckBoxes.equalsIgnoreCase("true"))) {
			ArrayList questionGrpList = (ArrayList) this
					.getScsScreenThreeQuestionsList();

			if ((questionGrpList != null) && (questionGrpList.size() > 0)) {
				Iterator grpItr = questionGrpList.iterator();

				while (grpItr.hasNext()) {
					CSCQuestionGroup riskGrp = (CSCQuestionGroup) grpItr.next();

					ArrayList questionsList = (ArrayList) riskGrp
							.getQuestions();

					if ((questionsList != null) && (questionsList.size() > 0)) {
						Iterator questItr = questionsList.iterator();

						while (questItr.hasNext()) {
							CSCQuestion question = (CSCQuestion) questItr
									.next();
							if (question.getUiControlType().equalsIgnoreCase(
									CSCQuestion.UI_CNTRL_TYPE_CHECKBOX)) {
								question
										.setSelectedResponseIdsArr(new String[0]);
							}
						}
					}
				}
			}
		}
	}

	public String getHighestSI() {
		return highestSI;
	}

	public void setHighestSI(String highestSI) {
		this.highestSI = highestSI;
	}

	public Collection getAssessmentClassificationCodes() {
		return assessmentClassificationCodes;
	}

	public void setAssessmentClassificationCodes(Collection assessmentClassificationCodes) {
		this.assessmentClassificationCodes = assessmentClassificationCodes;
	}

	/**
	 * @return Returns the confirmationMsg.
	 */
	public String getConfirmationMsg() {
		return confirmationMsg;
	}

	/**
	 * @param confirmationMsg
	 *            The confirmationMsg to set.
	 */
	public void setConfirmationMsg(String confirmationMsg) {
		this.confirmationMsg = confirmationMsg;
	}

	/**
	 * @return Returns the isForceFieldContinued.
	 */
	public boolean isForceFieldContinued() {
		return isForceFieldContinued;
	}

	/**
	 * @param isForceFieldContinued
	 *            The isForceFieldContinued to set.
	 */
	public void setForceFieldContinued(boolean isForceFieldContinued) {
		this.isForceFieldContinued = isForceFieldContinued;
	}

	/**
	 * @return Returns the createUpdateSCSId.
	 */
	public String getCreateUpdateSCSId() {
		return createUpdateSCSId;
	}

	/**
	 * @param createUpdateSCSId
	 *            The createUpdateSCSId to set.
	 */
	public void setCreateUpdateSCSId(String createUpdateSCSId) {
		this.createUpdateSCSId = createUpdateSCSId;
	}

	/**
	 * @return Returns the clasificationTypeList.
	 */
	public Collection getClasificationTypeList() {
		return clasificationTypeList;
	}

	/**
	 * @param clasificationTypeList
	 *            The clasificationTypeList to set.
	 */
	public void setClasificationTypeList(Collection clasificationTypeList) {
		this.clasificationTypeList = clasificationTypeList;
	}

	/**
	 * @return Returns the classificationTypeTotalsMap.
	 */
	public Map getClassificationTypeTotalsMap() {
		return classificationTypeTotalsMap;
	}

	/**
	 * @param classificationTypeTotalsMap
	 *            The classificationTypeTotalsMap to set.
	 */
	public void setClassificationTypeTotalsMap(Map classificationTypeTotalsMap) {
		this.classificationTypeTotalsMap = classificationTypeTotalsMap;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the forceFieldAssessmentStatus.
	 */
	public String getForceFieldAssessmentStatus() {
		return forceFieldAssessmentStatus;
	}

	/**
	 * @param forceFieldAssessmentStatus
	 *            The forceFieldAssessmentStatus to set.
	 */
	public void setForceFieldAssessmentStatus(String forceFieldAssessmentStatus) {
		this.forceFieldAssessmentStatus = forceFieldAssessmentStatus;
	}

	/**
	 * @return Returns the primaryClassificationTypeDesc.
	 */
	public String getPrimaryClassificationTypeDesc() {
		return primaryClassificationTypeDesc;
	}

	/**
	 * @param primaryClassificationTypeDesc
	 *            The primaryClassificationTypeDesc to set.
	 */
	public void setPrimaryClassificationTypeDesc(
			String primaryClassificationTypeDesc) {
		this.primaryClassificationTypeDesc = primaryClassificationTypeDesc;
	}

	/**
	 * @return Returns the primaryClassificationTypeId.
	 */
	public String getPrimaryClassificationTypeId() {
		return primaryClassificationTypeId;
	}

	/**
	 * @param primaryClassificationTypeId
	 *            The primaryClassificationTypeId to set.
	 */
	public void setPrimaryClassificationTypeId(
			String primaryClassificationTypeId) {
		this.primaryClassificationTypeId = primaryClassificationTypeId;
		this.primaryClassificationTypeDesc = SimpleCodeTableHelper
				.getDescrByCode(
						PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION,
						primaryClassificationTypeId);
	}

	/**
	 * @return Returns the scsScreenType.
	 */
	public String getScsScreenType() {
		return scsScreenType;
	}

	/**
	 * @param scsScreenType
	 *            The scsScreenType to set.
	 */
	public void setScsScreenType(String scsScreenType) {
		this.scsScreenType = scsScreenType;
	}

	/**
	 * @return Returns the scsScreenFourQuestionsList.
	 */
	public Collection getScsScreenFourQuestionsList() {
		return scsScreenFourQuestionsList;
	}

	/**
	 * @param scsScreenFourQuestionsList
	 *            The scsScreenFourQuestionsList to set.
	 */
	public void setScsScreenFourQuestionsList(
			Collection scsScreenFourQuestionsList) {
		this.scsScreenFourQuestionsList = scsScreenFourQuestionsList;
	}

	/**
	 * @return Returns the scsScreenOneQuestionsList.
	 */
	public Collection getScsScreenOneQuestionsList() {
		return scsScreenOneQuestionsList;
	}

	/**
	 * @param scsScreenOneQuestionsList
	 *            The scsScreenOneQuestionsList to set.
	 */
	public void setScsScreenOneQuestionsList(
			Collection scsScreenOneQuestionsList) {
		this.scsScreenOneQuestionsList = scsScreenOneQuestionsList;
	}

	/**
	 * @return Returns the scsScreenThreeQuestionsList.
	 */
	public Collection getScsScreenThreeQuestionsList() {
		return scsScreenThreeQuestionsList;
	}

	/**
	 * @param scsScreenThreeQuestionsList
	 *            The scsScreenThreeQuestionsList to set.
	 */
	public void setScsScreenThreeQuestionsList(
			Collection scsScreenThreeQuestionsList) {
		this.scsScreenThreeQuestionsList = scsScreenThreeQuestionsList;
	}

	/**
	 * @return Returns the scsScreenTwoQuestionsList.
	 */
	public Collection getScsScreenTwoQuestionsList() {
		return scsScreenTwoQuestionsList;
	}

	/**
	 * @param scsScreenTwoQuestionsList
	 *            The scsScreenTwoQuestionsList to set.
	 */
	public void setScsScreenTwoQuestionsList(
			Collection scsScreenTwoQuestionsList) {
		this.scsScreenTwoQuestionsList = scsScreenTwoQuestionsList;
	}

	/**
	 * @return Returns the scsSummaryQuestionsList.
	 */
	public Collection getScsSummaryQuestionsList() {
		return scsSummaryQuestionsList;
	}

	/**
	 * @param scsSummaryQuestionsList
	 *            The scsSummaryQuestionsList to set.
	 */
	public void setScsSummaryQuestionsList(Collection scsSummaryQuestionsList) {
		this.scsSummaryQuestionsList = scsSummaryQuestionsList;
	}

	/**
	 * @return Returns the scsTotals.
	 */
	public int[] getScsTotals() {
		return scsTotals;
	}

	/**
	 * @param scsTotals
	 *            The scsTotals to set.
	 */
	public void setScsTotals(int[] scsTotals) {
		this.scsTotals = scsTotals;
	}

	/**
	 * @return Returns the secondaryClassificationTypeDesc.
	 */
	public String getSecondaryClassificationTypeDesc() {
		return secondaryClassificationTypeDesc;
	}

	/**
	 * @param secondaryClassificationTypeDesc
	 *            The secondaryClassificationTypeDesc to set.
	 */
	public void setSecondaryClassificationTypeDesc(
			String secondaryClassificationTypeDesc) {
		this.secondaryClassificationTypeDesc = secondaryClassificationTypeDesc;
	}

	/**
	 * @return Returns the secondaryClassificationTypeId.
	 */
	public String getSecondaryClassificationTypeId() {
		return secondaryClassificationTypeId;
	}

	/**
	 * @param secondaryClassificationTypeId
	 *            The secondaryClassificationTypeId to set.
	 */
	public void setSecondaryClassificationTypeId(
			String secondaryClassificationTypeId) {
		this.secondaryClassificationTypeId = secondaryClassificationTypeId;
		this.secondaryClassificationTypeDesc = SimpleCodeTableHelper
				.getDescrByCode(
						PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION,
						secondaryClassificationTypeId);
	}

	/**
	 * @return Returns the totalCC.
	 */
	public String getTotalCC() {
		return totalCC;
	}

	/**
	 * @param totalCC
	 *            The totalCC to set.
	 */
	public void setTotalCC(String totalCC) {
		this.totalCC = totalCC;
	}

	/**
	 * @return Returns the totalES.
	 */
	public String getTotalES() {
		return totalES;
	}

	/**
	 * @param totalES
	 *            The totalES to set.
	 */
	public void setTotalES(String totalES) {
		this.totalES = totalES;
	}

	/**
	 * @return Returns the totalLS.
	 */
	public String getTotalLS() {
		return totalLS;
	}

	/**
	 * @param totalLS
	 *            The totalLS to set.
	 */
	public void setTotalLS(String totalLS) {
		this.totalLS = totalLS;
	}

	/**
	 * @return Returns the totalSI.
	 */
	public String getTotalSI() {
		return totalSI;
	}

	/**
	 * @param totalSI
	 *            The totalSI to set.
	 */
	public void setTotalSI(String totalSI) {
		this.totalSI = totalSI;
	}

	/**
	 * @return Returns the forceFieldAssessmentId.
	 */
	public String getForceFieldAssessmentId() {
		return forceFieldAssessmentId;
	}

	/**
	 * @param forceFieldAssessmentId
	 *            The forceFieldAssessmentId to set.
	 */
	public void setForceFieldAssessmentId(String forceFieldAssessmentId) {
		this.forceFieldAssessmentId = forceFieldAssessmentId;
	}

	/**
	 * @return Returns the forceFieldAssessmentTypeId.
	 */
	public String getForceFieldAssessmentTypeId() {
		return forceFieldAssessmentTypeId;
	}

	/**
	 * @param forceFieldAssessmentTypeId
	 *            The forceFieldAssessmentTypeId to set.
	 */
	public void setForceFieldAssessmentTypeId(String forceFieldAssessmentTypeId) {
		this.forceFieldAssessmentTypeId = forceFieldAssessmentTypeId;
	}

	/**
	 * @return Returns the forceFieldMasterAssessId.
	 */
	public String getForceFieldMasterAssessId() {
		return forceFieldMasterAssessId;
	}

	/**
	 * @param forceFieldMasterAssessId
	 *            The forceFieldMasterAssessId to set.
	 */
	public void setForceFieldMasterAssessId(String forceFieldMasterAssessId) {
		this.forceFieldMasterAssessId = forceFieldMasterAssessId;
	}

	/**
	 * @return the createUpdateSCSMasterAssessId
	 */
	public String getCreateUpdateSCSMasterAssessId() {
		return createUpdateSCSMasterAssessId;
	}

	/**
	 * @param createUpdateSCSMasterAssessId the createUpdateSCSMasterAssessId to set
	 */
	public void setCreateUpdateSCSMasterAssessId(
			String createUpdateSCSMasterAssessId) {
		this.createUpdateSCSMasterAssessId = createUpdateSCSMasterAssessId;
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
	
}
