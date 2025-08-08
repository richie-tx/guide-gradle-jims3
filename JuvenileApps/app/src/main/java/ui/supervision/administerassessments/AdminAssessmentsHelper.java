/*
 * Created on Jan 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.struts.action.ActionForm;

import naming.AssessmentControllerServiceNames;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;
import ui.common.CSCQuestionResponse;
import ui.common.SimpleCodeTableHelper;
import ui.common.StringUtil;
import ui.common.UIUtil;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.AssessmentTypeForm;
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.administerassessments.form.LSIRAssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;
import messaging.administerassessments.CalculateSCSTotalsEvent;
import messaging.administerassessments.DeleteAssessmentEvent;
import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.GetAssessmentsSummaryEvent;
import messaging.administerassessments.UpdateForceFieldAssessmentEvent;
import messaging.administerassessments.UpdateLSIRAssessmentEvent;
import messaging.administerassessments.UpdateSCSAssessmentEvent;
import messaging.administerassessments.UpdateSCSInterviewAssessmentEvent;
import messaging.administerassessments.UpdateWisconsinAssessmentEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administerassessments.reply.PriorAssessmentVersionResponseEvent;
import messaging.administerassessments.reply.SCSTotalsResponseEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.DateUtil;



/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminAssessmentsHelper
{
	
	/**
	 * 
	 * @param aAssessmentForm
	 * @return
	 */
	public static RequestEvent getAssessmentsSummaryEvent(AssessmentForm aAssessmentForm)
	{
		GetAssessmentsSummaryEvent assessmentsSummaryEvent = (GetAssessmentsSummaryEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTSSUMMARY);
		
		assessmentsSummaryEvent.setDefendantId(aAssessmentForm.getDefendantId());
		if(aAssessmentForm.getSupervisionPeriod()== CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV)
		{
			assessmentsSummaryEvent.setSearchOnActiveSupervisionPeriod(true);
		}
		else
		{
			assessmentsSummaryEvent.setSearchOnActiveSupervisionPeriod(false);
		}
		return assessmentsSummaryEvent;
	}//end of getAssessmentsSummaryEvent()
	
	
	
	/**
	 * 
	 * @param aAssessmentForm
	 * @param assessmentsSummaryList
	 */
	public static void setAssessmentSummaryResponseEventCollection(AssessmentForm aAssessmentForm, Collection assessmentsSummaryList)
	{
//		separate to initial assessments and reassessmewnts and populate the form
		ArrayList initialAssessmentsList = new ArrayList();
		ArrayList reassessmentsList = new ArrayList();
		ArrayList scsAssessmentsList = new ArrayList();
		
		aAssessmentForm.setInitialAssessmentExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		aAssessmentForm.setIncompleteWisconsinInitialExist(false);
		aAssessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		aAssessmentForm.setScsInventoryExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		aAssessmentForm.setScsInterviewExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		
		aAssessmentForm.setIncompleteWisconsinReassessExist(false);
		
		if((assessmentsSummaryList != null) && (assessmentsSummaryList.size() > 0))
		{
			assessmentsSummaryList = (ArrayList)assessmentsSummaryList;
			
			Iterator iterator = assessmentsSummaryList.iterator();
			while(iterator.hasNext())
			{
				AssessmentSummaryResponseEvent assessmentSummaryRespEvt = (AssessmentSummaryResponseEvent)iterator.next();

				AssessmentLightBean assessmentBean = new AssessmentLightBean();
				
				String assessmentTypeId = assessmentSummaryRespEvt.getAssessmentTypeId();
				assessmentBean.setAssessmentTypeId(assessmentTypeId);
				String assessmentTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
						assessmentTypeId);
				assessmentBean.setAssessmentTypeName(assessmentTypeName);
				
				String masterAssessId = assessmentSummaryRespEvt.getMasterAssessmentId();
				assessmentBean.setMasterAssessmentId(masterAssessId);
				
				String assessmentId = assessmentSummaryRespEvt.getAssessmentId();
				assessmentBean.setAssessmentId(assessmentId);
				
				if(!StringUtil.isEmpty(assessmentSummaryRespEvt.getAssessmentStatusCd()))
				{
					if(assessmentSummaryRespEvt.getAssessmentStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
					{
						assessmentBean.setStatusComplete(true);
						assessmentBean.setStatusDesc(CSCAssessmentConstants.ASSESSMENT_COMPLETE_DESC);
					}
					else
					{
						assessmentBean.setStatusComplete(false);
						assessmentBean.setStatusDesc(CSCAssessmentConstants.ASSESSMENT_INCOMPLETE_DESC);
					}
				}
				
				if((assessmentTypeId!=null) && (masterAssessId != null) && (assessmentId != null))
				{
					String assessmentBeanId = assessmentTypeId+masterAssessId+assessmentId;
					assessmentBean.setAssessmentBeanId(assessmentBeanId.trim());
				}
				else
				{
					assessmentBean.setAssessmentBeanId(assessmentTypeId);
				}
				
				if(!StringUtil.isEmpty(assessmentBean.getAssessmentBeanId()))
				{
					Date assessDate = assessmentSummaryRespEvt.getAssessmentDate();
					Date migratedDate = assessmentSummaryRespEvt.getMigratedAssessmentDate();
					
					assessmentBean.setMigrated(UIConstants.NO);
					if(assessDate != null && migratedDate != null){
						int result = DateUtil.compare(assessDate, migratedDate, DateUtil.DATE_FMT_1);
						if(result != 0){
							assessmentBean.setMigrated(UIConstants.YES);
						}
					}
					
//					For Wisconsin Rescheduled Reassessment
					if((assessDate==null) && (assessmentBean.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN)))
					{
						assessmentBean.setAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
						assessmentBean.setInitial(false);
						assessmentBean.setAssessmentDueDate(assessmentSummaryRespEvt.getDueDate());
						assessmentBean.setSortOrder("3");
						reassessmentsList.add(assessmentBean);
					}
//				    Except Wisconsin Rescheduled Reassessment
					else
					{
						assessmentBean.setAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
						assessmentBean.setAssessmentDate(assessDate);
						assessmentBean.setActualAssessmentDate(migratedDate);
						assessmentBean.setAssessmentDateStr(DateUtil.dateToString(assessDate, DateUtil.DATE_FMT_1));
						assessmentBean.setInitial(assessmentSummaryRespEvt.isInitial());						
						assessmentBean.setRiskScore(assessmentSummaryRespEvt.getRiskScore());
						assessmentBean.setNeedsScore(assessmentSummaryRespEvt.getNeedsScore());
						
			//			If its an SCS Assessment, assign to AssessmentBean and add the AssessmentBean to SCSAssessment list
						if(assessmentSummaryRespEvt.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS))
						{
							/*String forceFieldMasterAssessId = assessmentSummaryRespEvt.getForceFieldMasterAssessmentId();
							
							if((forceFieldMasterAssessId == null) || (forceFieldMasterAssessId.equalsIgnoreCase("")))
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
								assessmentBean.setForceFieldAssessmentId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
								String forceFieldassessTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
										assessmentBean.getForceFieldAssessmentId());
								assessmentBean.setForceFieldAssessmentTypeName("NEW " + forceFieldassessTypeName);			
								assessmentBean.setForceFieldMasterAssessId(null);
								assessmentBean.setForceFieldAssessmentId(null);
							}
							else
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
								assessmentBean.setForceFieldAssessmentId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
								String forceFieldassessTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
																												assessmentBean.getForceFieldAssessmentId());
								assessmentBean.setForceFieldAssessmentTypeName(forceFieldassessTypeName);	
								assessmentBean.setForceFieldMasterAssessId(assessmentSummaryRespEvt.getForceFieldMasterAssessmentId());
								assessmentBean.setForceFieldAssessmentId(assessmentSummaryRespEvt.getForceFieldAssessmentId());
								
								if(!StringUtil.isEmpty(assessmentSummaryRespEvt.getForceFieldAssessStatusCd()))
								{
									if(assessmentSummaryRespEvt.getForceFieldAssessStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
									{
										assessmentBean.setForceFieldStatusComplete(true);
										assessmentBean.setForceFieldStatusDesc(CSCAssessmentConstants.ASSESSMENT_COMPLETE_DESC);
									}
									else
									{
										assessmentBean.setForceFieldStatusComplete(false);
										assessmentBean.setForceFieldStatusDesc(CSCAssessmentConstants.ASSESSMENT_INCOMPLETE_DESC);
									}
								}
							}*/
						
							String primaryId = assessmentSummaryRespEvt.getPrimaryClassificationTypeId();
							assessmentBean.setPrimaryClassificationId(primaryId);
							String classificationType = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION,
									assessmentBean.getPrimaryClassificationId());
							assessmentBean.setPrimaryClassificationStr(classificationType);
							
							String secondaryId = assessmentSummaryRespEvt.getSecondaryClassificationTypeId();
							assessmentBean.setSecondaryClassificationId(secondaryId);
							classificationType = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION,
									assessmentBean.getSecondaryClassificationId());
							assessmentBean.setSecondaryClassificationStr(classificationType);			
							
							assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
							if(assessmentSummaryRespEvt.isForceFieldFound())
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
							}
							assessmentBean.setSortOrder("1"); // SCS inventory assessments are always first for sort
							scsAssessmentsList.add(assessmentBean);
							aAssessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
							aAssessmentForm.setScsInventoryExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
						}
//						If its an SCS Interview, assign to AssessmentBean and add the AssessmentBean to SCSAssessment list
						else
						if(assessmentSummaryRespEvt.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW))
						{
							/*String forceFieldMasterAssessId = assessmentSummaryRespEvt.getForceFieldMasterAssessmentId();
							
							if((forceFieldMasterAssessId == null) || (forceFieldMasterAssessId.equalsIgnoreCase("")))
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
								assessmentBean.setForceFieldAssessmentId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
								String forceFieldassessTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
										assessmentBean.getForceFieldAssessmentId());
								assessmentBean.setForceFieldAssessmentTypeName("NEW " + forceFieldassessTypeName);			
								assessmentBean.setForceFieldMasterAssessId(null);
								assessmentBean.setForceFieldAssessmentId(null);
							}
							else
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
								assessmentBean.setForceFieldAssessmentId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
								String forceFieldassessTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
																												assessmentBean.getForceFieldAssessmentId());
								assessmentBean.setForceFieldAssessmentTypeName(forceFieldassessTypeName);	
								assessmentBean.setForceFieldMasterAssessId(assessmentSummaryRespEvt.getForceFieldMasterAssessmentId());
								assessmentBean.setForceFieldAssessmentId(assessmentSummaryRespEvt.getForceFieldAssessmentId());
								
								if(!StringUtil.isEmpty(assessmentSummaryRespEvt.getForceFieldAssessStatusCd()))
								{
									if(assessmentSummaryRespEvt.getForceFieldAssessStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
									{
										assessmentBean.setForceFieldStatusComplete(true);
										assessmentBean.setForceFieldStatusDesc(CSCAssessmentConstants.ASSESSMENT_COMPLETE_DESC);
									}
									else
									{
										assessmentBean.setForceFieldStatusComplete(false);
										assessmentBean.setForceFieldStatusDesc(CSCAssessmentConstants.ASSESSMENT_INCOMPLETE_DESC);
									}
								}
							}*/
						
							assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
							if(assessmentSummaryRespEvt.isForceFieldFound())
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
							}
							assessmentBean.setSortOrder("2"); // SCS interview assessments are always second for sort
							scsAssessmentsList.add(assessmentBean);
							aAssessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
							aAssessmentForm.setScsInterviewExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
						}	
						else
						if(assessmentSummaryRespEvt.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD))
						{
							String forceFieldMasterAssessId = assessmentSummaryRespEvt.getMasterAssessmentId();
							
							if((forceFieldMasterAssessId == null) || (forceFieldMasterAssessId.equalsIgnoreCase("")))
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
								assessmentBean.setForceFieldAssessmentId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
								String forceFieldassessTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
										assessmentBean.getForceFieldAssessmentId());
								assessmentBean.setForceFieldAssessmentTypeName("NEW " + forceFieldassessTypeName);			
								assessmentBean.setForceFieldMasterAssessId(null);
								assessmentBean.setForceFieldAssessmentId(null);
								assessmentBean.setForceFieldAssessmentDate(assessmentSummaryRespEvt.getAssessmentDate());
							}
							else
							{
								assessmentBean.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
								assessmentBean.setForceFieldAssessmentId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
								String forceFieldassessTypeName = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_TYPE,
																												assessmentBean.getForceFieldAssessmentId());
								assessmentBean.setForceFieldAssessmentTypeName(forceFieldassessTypeName);	
								assessmentBean.setForceFieldMasterAssessId(assessmentSummaryRespEvt.getMasterAssessmentId());
								assessmentBean.setForceFieldAssessmentId(assessmentSummaryRespEvt.getAssessmentId());
								assessmentBean.setForceFieldAssessmentDate(assessmentSummaryRespEvt.getAssessmentDate());
								
								if(!StringUtil.isEmpty(assessmentSummaryRespEvt.getAssessmentStatusCd()))
								{
									if(assessmentSummaryRespEvt.getAssessmentStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
									{
										assessmentBean.setForceFieldStatusComplete(true);
										assessmentBean.setForceFieldStatusDesc(CSCAssessmentConstants.ASSESSMENT_COMPLETE_DESC);
									}
									else
									{
										assessmentBean.setForceFieldStatusComplete(false);
										assessmentBean.setForceFieldStatusDesc(CSCAssessmentConstants.ASSESSMENT_INCOMPLETE_DESC);
									}
								}
							}
							assessmentBean.setSortOrder("3"); // SCS Force Field Analyses are always third for sort
							scsAssessmentsList.add(assessmentBean);
						}	
			//			If its WISCONSIN or LSIR
						else
						{
							// set sort order
							if(assessmentSummaryRespEvt.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN)){
								assessmentBean.setSortOrder("2");
							}else{
								assessmentBean.setSortOrder("1");
							}
							if(assessmentSummaryRespEvt.isInitial())
							{
								initialAssessmentsList.add(assessmentBean);
								aAssessmentForm.setInitialAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
								
								if(!assessmentBean.isStatusComplete())
								{
									aAssessmentForm.setIncompleteWisconsinInitialExist(true);
								}
							}
							else
							{
								assessmentBean.setAssessmentDueDate(assessmentSummaryRespEvt.getDueDate());								

								reassessmentsList.add(assessmentBean);
								
								if(((assessmentSummaryRespEvt.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN))) && (!assessmentBean.isStatusComplete()))
								{
									aAssessmentForm.setIncompleteWisconsinReassessExist(true);
								}
							}
						}		
					}
				}
			}		
		}
		Collections.sort(initialAssessmentsList, AssessmentLightBean.AssessmentOrderComparator);
		Collections.sort(reassessmentsList, AssessmentLightBean.AssessmentOrderComparator);
		Collections.sort(scsAssessmentsList, AssessmentLightBean.AssessmentOrderComparator);

		aAssessmentForm.setInitialAssessmentsList(initialAssessmentsList);
		aAssessmentForm.setReassessmentsList(reassessmentsList);
		aAssessmentForm.setScsAssessmentsList(scsAssessmentsList);
		
		if(aAssessmentForm.getSupervisionPeriod().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV))
		{
			aAssessmentForm.setAllHistoricalInitialAssessmentsList(initialAssessmentsList);
			aAssessmentForm.setAllHistoricalReassessmentsList(reassessmentsList);
			aAssessmentForm.setAllHistoricalScsAssessmentsList(scsAssessmentsList);
			
		}
	}//end of setAssessmentSummaryResponseEventCollection()
	
	
	
	
	/**
	 * 
	 * @param aAssessmentForm
	 * @param responseEvt
	 */
	public static void setSupervisionPeriodResponseEvent(AssessmentForm aAssessmentForm, SupervisionPeriodResponseEvent responseEvt)
	{
		aAssessmentForm.setSupervisionBeginDate(responseEvt.getSupervisionBeginDate());
		aAssessmentForm.setSupervisionEndDate(responseEvt.getSupervisionEndDate());
	}//end of setSupervisionPeriodResponseEventI()
	
	
	
	/**
	 * 
	 * @param aActionForm
	 * @return
	 */
	public static RequestEvent getAssessmentDetailsEvent(ActionForm aActionForm)
	{
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTDETAILS);
		
		AssessmentTypeForm assessmentTypeForm = (AssessmentTypeForm)aActionForm;
		
		assessmentDetailsEvent.setDefendantId(assessmentTypeForm.getDefendantId());
		assessmentDetailsEvent.setAssessmentTypeId(assessmentTypeForm.getAssessmentTypeId());
		
		assessmentDetailsEvent.setMasterAssessmentId(assessmentTypeForm.getMasterAssessmentId());
		assessmentDetailsEvent.setAssessmentId(assessmentTypeForm.getAssessmentId());
		
		if(assessmentTypeForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
		{
			assessmentDetailsEvent.setAssessmentCreate(true);
			assessmentDetailsEvent.setAssessmentUpdate(false);
			assessmentDetailsEvent.setAssessmentDetails(false);
			assessmentDetailsEvent.setRetrievePriorVersion(false);
		}
		else 
		if(assessmentTypeForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
		{
			assessmentDetailsEvent.setAssessmentCreate(false);
			assessmentDetailsEvent.setAssessmentUpdate(true);
			assessmentDetailsEvent.setAssessmentDetails(false);
			assessmentDetailsEvent.setRetrievePriorVersion(false);
		}
		else
		if(assessmentTypeForm.getAction().equalsIgnoreCase(UIConstants.VIEW_DETAIL))
		{
			assessmentDetailsEvent.setAssessmentCreate(false);
			assessmentDetailsEvent.setAssessmentUpdate(false);
			assessmentDetailsEvent.setAssessmentDetails(true);
			assessmentDetailsEvent.setRetrievePriorVersion(true);
		}
		else
		if(assessmentTypeForm.getAction().equalsIgnoreCase(UIConstants.ASSESSMENT_VERSION_DETAILS))
		{
			assessmentDetailsEvent.setAssessmentCreate(false);
			assessmentDetailsEvent.setAssessmentUpdate(false);
			assessmentDetailsEvent.setAssessmentDetails(true);
			assessmentDetailsEvent.setRetrievePriorVersion(false);
		}
		
		if(assessmentTypeForm instanceof WisconsinAssessmentForm)
		{
			WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aActionForm;
			if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
			{
				assessmentDetailsEvent.setInitial(true);
			}
			else
			{
				assessmentDetailsEvent.setInitial(false);
			}
		}
		else
		if(aActionForm instanceof LSIRAssessmentForm)
		{
			LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)aActionForm;
			if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
			{
				assessmentDetailsEvent.setInitial(true);
			}
			else
			{
				assessmentDetailsEvent.setInitial(false);
			}
		}
		
		return assessmentDetailsEvent;
	}//end of getAssessmentDetailsEvent()
	
	
	
	
	/**
	 * 
	 * @param wisconsinForm
	 * @return
	 */
	public static RequestEvent getUpdateWisconsinAssessmentEvent(WisconsinAssessmentForm wisconsinForm)
	{
		UpdateWisconsinAssessmentEvent wisconsinRequestEvt = (UpdateWisconsinAssessmentEvent) EventFactory.getInstance(AssessmentControllerServiceNames.UPDATEWISCONSINASSESSMENT);
		
		wisconsinRequestEvt.setDefendantId(wisconsinForm.getDefendantId());
		wisconsinRequestEvt.setAssessmentTypeCd(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
		
		if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
		{
			wisconsinRequestEvt.setIsInitial(true);
		}
		else
		{
			wisconsinRequestEvt.setIsInitial(false);
		}
		
		wisconsinRequestEvt.setAssessmentDate(wisconsinForm.getAssessmentDate());
		wisconsinRequestEvt.setMasterAssessmentId(wisconsinForm.getMasterAssessmentId());
		
		wisconsinRequestEvt.setAssessmentIncomplete(wisconsinForm.isAssessmentIncomplete());
		 
		wisconsinRequestEvt.setQuestionAnswers(wisconsinForm.getQuestionAnswerList());
		try {
			wisconsinRequestEvt.setRiskScore(0);
			wisconsinRequestEvt.setRiskLevel(0);
			wisconsinRequestEvt.setNeedsScore(0);
			wisconsinRequestEvt.setNeedsLevel(0);
			
			if((wisconsinForm.getTotalRiskScore() != null) && (!wisconsinForm.getTotalRiskScore().equalsIgnoreCase("")))
			{
				wisconsinRequestEvt.setRiskScore(Integer.parseInt(wisconsinForm.getTotalRiskScore()));
			}
			if((wisconsinForm.getRiskLevel() != null) && (!wisconsinForm.getRiskLevel().equalsIgnoreCase("")))
			{
				wisconsinRequestEvt.setRiskLevel(Integer.parseInt(wisconsinForm.getRiskLevel()));
			}
			if((wisconsinForm.getTotalNeedsScore() != null) && (!wisconsinForm.getTotalNeedsScore().equalsIgnoreCase("")))
			{
				wisconsinRequestEvt.setNeedsScore(Integer.parseInt(wisconsinForm.getTotalNeedsScore()));
			}
			if((wisconsinForm.getNeedsLevel() != null) && (!wisconsinForm.getNeedsLevel().equalsIgnoreCase("")))
			{
				wisconsinRequestEvt.setNeedsLevel(Integer.parseInt(wisconsinForm.getNeedsLevel()));
			}			
		} catch (Exception ex)
		{
			//exception occured while parsing the String value
		}
		wisconsinRequestEvt.setLevelOfSupervision(wisconsinForm.getLevelOfSupervisionCd());
		
		return wisconsinRequestEvt;
	}//end of getUpdateWisconsinAssessmentEvent()
	
	
	
	
	/**
	 * 
	 * @param lsirForm
	 * @return
	 */
	public static RequestEvent getUpdateLSIRAssessmentEvent(LSIRAssessmentForm lsirForm)
	{
		UpdateLSIRAssessmentEvent lsirRequestEvt = (UpdateLSIRAssessmentEvent) EventFactory.getInstance(AssessmentControllerServiceNames.UPDATELSIRASSESSMENT);
		
		lsirRequestEvt.setDefendantId(lsirForm.getDefendantId());
		lsirRequestEvt.setAssessmentTypeCd(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
		
		lsirRequestEvt.setAssessmentDate(lsirForm.getAssessmentDate());
		lsirRequestEvt.setMasterAssessmentId(lsirForm.getMasterAssessmentId());
		
		if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
		{
			lsirRequestEvt.setIsReassessment(false);
		}
		else
		{
			lsirRequestEvt.setIsReassessment(true);
		}
		
		lsirRequestEvt.setQuestionAnswers(lsirForm.getQuestionAnswerList());
		lsirRequestEvt.setComments(lsirForm.getComments());
		
		return lsirRequestEvt;
	}//end of getUpdateLSIRAssessmentEvent()
	
	
	/**
	 * 
	 * @param scsForm
	 * @return
	 */
	public static RequestEvent getUpdateSCSAssessmentEvent(SCSAssessmentForm scsForm) throws Exception 
	{
		UpdateSCSAssessmentEvent scsRequestEvt = (UpdateSCSAssessmentEvent) EventFactory.getInstance(AssessmentControllerServiceNames.UPDATESCSASSESSMENT);
		
		scsRequestEvt.setDefendantId(scsForm.getDefendantId());
		scsRequestEvt.setAssessmentTypeCd(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
		
		scsRequestEvt.setAssessmentDate(scsForm.getAssessmentDate());
		scsRequestEvt.setMasterAssessmentId(scsForm.getMasterAssessmentId());
		
		scsRequestEvt.setAssessmentIncomplete(scsForm.isAssessmentIncomplete());
		
		scsRequestEvt.setQuestionAnswers(scsForm.getQuestionAnswerList());
		scsRequestEvt.setComments(scsForm.getComments());
		
		scsRequestEvt.setPrimaryClassCd(scsForm.getPrimaryClassificationTypeId());
		scsRequestEvt.setSecondaryClassCd(scsForm.getSecondaryClassificationTypeId());
		scsRequestEvt.setScsId(scsForm.getAssessmentId());
		
		try
		{
			scsRequestEvt.setSiTotal(Integer.parseInt(scsForm.getTotalSI()));
			scsRequestEvt.setCcTotal(Integer.parseInt(scsForm.getTotalCC()));
			scsRequestEvt.setEsTotal(Integer.parseInt(scsForm.getTotalES()));
			scsRequestEvt.setLsTotal(Integer.parseInt(scsForm.getTotalLS()));
		}
		catch(Exception ex)
		{
			System.out.println("********************* Exception:" + ex.toString());
			System.out.println("********************* Total SI Score = " + scsForm.getTotalSI());
			System.out.println("********************* Total CC Score = " + scsForm.getTotalCC());
			System.out.println("********************* Total ES Score = " + scsForm.getTotalES());
			System.out.println("********************* Total LS Score = " + scsForm.getTotalLS());
			
			throw ex;
		}
		return scsRequestEvt;
	}//end of getUpdateSCSAssessmentEvent()
	
	
	
	/**
	 * 
	 * @param scsInterviewForm
	 * @return
	 */
	public static RequestEvent getUpdateSCSInterviewAssessmentEvent(SCSInterviewAssessmentForm scsInterviewForm)
	{
		UpdateSCSInterviewAssessmentEvent scsInterviewRequestEvt = (UpdateSCSInterviewAssessmentEvent) EventFactory.getInstance(AssessmentControllerServiceNames.UPDATESCSINTERVIEWASSESSMENT);
		
		scsInterviewRequestEvt.setDefendantId(scsInterviewForm.getDefendantId());
		scsInterviewRequestEvt.setAssessmentTypeCd(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW);
		
		scsInterviewRequestEvt.setAssessmentDate(scsInterviewForm.getAssessmentDate());
		scsInterviewRequestEvt.setMasterAssessmentId(scsInterviewForm.getMasterAssessmentId());
		
		scsInterviewRequestEvt.setAssessmentIncomplete(scsInterviewForm.isAssessmentIncomplete());
		
		scsInterviewRequestEvt.setQuestionAnswers(scsInterviewForm.getQuestionAnswerList());
		
		scsInterviewRequestEvt.setScsInterviewId(scsInterviewForm.getAssessmentId());
		
		return scsInterviewRequestEvt;
	}//end of getUpdateSCSInterviewAssessmentEvent()
	
	
	
	/**
	 * 
	 * @param forceFieldForm
	 * @return
	 */
	public static RequestEvent getUpdateForceFieldAssessmentEvent(ForceFieldAssessmentForm forceFieldForm)
	{
		UpdateForceFieldAssessmentEvent forceFieldRequestEvt = (UpdateForceFieldAssessmentEvent) EventFactory.getInstance(AssessmentControllerServiceNames.UPDATEFORCEFIELDASSESSMENT);
		
		forceFieldRequestEvt.setDefendantId(forceFieldForm.getDefendantId());
		forceFieldRequestEvt.setAssessmentTypeCd(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
		
		forceFieldRequestEvt.setAssessmentDate(forceFieldForm.getAssessmentDate());
		forceFieldRequestEvt.setMasterAssessmentId(forceFieldForm.getMasterAssessmentId());
		
		forceFieldRequestEvt.setAssessmentIncomplete(forceFieldForm.isAssessmentIncomplete());
		
		forceFieldRequestEvt.setScsAssessId(null);
		String createUpdateSCSId = forceFieldForm.getCreateUpdateSCSId();
		if((createUpdateSCSId != null) && (!createUpdateSCSId.equalsIgnoreCase("")))
		{
			forceFieldRequestEvt.setScsAssessId(createUpdateSCSId);
		}
		
		forceFieldRequestEvt.setQuestionAnswers(forceFieldForm.getQuestionAnswerList());
		
		return forceFieldRequestEvt;
	}//end of getUpdateForceFieldAssessmentEvent()
	
	
	
	/**
	 * 
	 * @param assessmentTypeForm
	 * @return
	 */
	public static RequestEvent getDeleteAssessmentEvent(AssessmentTypeForm assessmentTypeForm)
	{
		DeleteAssessmentEvent deleteRequestEvt = (DeleteAssessmentEvent) EventFactory.getInstance(AssessmentControllerServiceNames.DELETEASSESSMENT);
		deleteRequestEvt.setMasterAssessmentId(assessmentTypeForm.getMasterAssessmentId());	
		
		return deleteRequestEvt;
	}//end of getDeleteAssessmentEvent()
	
	
	/**
	 * 
	 * @param scsForm
	 * @return
	 */
	public static RequestEvent getCalculateSCSTotalsEvent(SCSAssessmentForm scsForm)
	{
		CalculateSCSTotalsEvent scsTotalRequestEvent = (CalculateSCSTotalsEvent)EventFactory.getInstance(AssessmentControllerServiceNames.CALCULATESCSTOTALS);
		scsTotalRequestEvent.setQuestionAnswers(scsForm.getQuestionAnswerList());
		
		return scsTotalRequestEvent;
	}//end of getCalculateSCSTotalsEvent()
	
	
	
	/**
	 * 
	 * @param scsForm
	 * @param scsTotalsRespEvt
	 */
	public static String setSCSTotalsResponseEvent(SCSAssessmentForm scsForm, SCSTotalsResponseEvent scsTotalsRespEvt) throws Exception
	{
		HashMap classificationIdTotalMap =  (HashMap)scsTotalsRespEvt.getClassificationTypeIdTotalMap();
		scsForm.setClassificationTypeTotalsMap(classificationIdTotalMap);
		
		int[] scsTotals = new int[4];
		
		if((classificationIdTotalMap!=null) && (classificationIdTotalMap.size() > 0))
		{
				Set classificationIdSet = classificationIdTotalMap.keySet();
			
				Iterator it = classificationIdSet.iterator();
				while (it.hasNext())
				{
					String classificationId = (String) it.next();
					if ((classificationId != null)
							&& (classificationId
									.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_CC)))
					{
						try
						{
							Integer i = (Integer)classificationIdTotalMap.get(classificationId);
							scsForm.setTotalCC(i.toString());
							scsTotals[CSCAssessmentConstants.ASSESSMENT_SCS_CLASSIFICATION_INDEX_CC]=Integer.parseInt(scsForm.getTotalCC());
						}
						catch(Exception ex)
						{
							System.out.println("Exception in SCS CC Score: " +  scsForm.getTotalCC() + " : " + ex.toString());
							return "Failed to calculate SCS CC score";
						}
						
					} 
					else if ((classificationId != null)
							&& (classificationId
									.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_ES)))
					{
						try
						{
							Integer i = (Integer)classificationIdTotalMap.get(classificationId);
							scsForm.setTotalES(i.toString());
							scsTotals[CSCAssessmentConstants.ASSESSMENT_SCS_CLASSIFICATION_INDEX_ES]=Integer.parseInt(scsForm.getTotalES());
						}
						catch(Exception ex)
						{
							System.out.println("Exception in SCS ES Score: " +  scsForm.getTotalES() + " : " + ex.toString());
							return "Failed to calculate SCS ES score";
						}
					} 
					else if ((classificationId != null)
							&& (classificationId
									.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_LS)))
					{
						try
						{
							Integer i = (Integer)classificationIdTotalMap.get(classificationId);
							scsForm.setTotalLS(i.toString());
							scsTotals[CSCAssessmentConstants.ASSESSMENT_SCS_CLASSIFICATION_INDEX_LS]=Integer.parseInt(scsForm.getTotalLS());
						}
						catch(Exception ex)
						{
							System.out.println("Exception in SCS LS Score: " +  scsForm.getTotalLS() + " : " + ex.toString());
							return "Failed to calculate SCS LS score";
						}
					}
					else if ((classificationId != null)
							&& (classificationId
									.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI)))
					{
						try
						{
							Integer i = (Integer)classificationIdTotalMap.get(classificationId);
							scsForm.setTotalSI(i.toString());
							scsTotals[CSCAssessmentConstants.ASSESSMENT_SCS_CLASSIFICATION_INDEX_SI]=Integer.parseInt(scsForm.getTotalSI());
						}
						catch(Exception ex)
						{
							System.out.println("Exception in SCS SI Score: " +  scsForm.getTotalSI() + " : " + ex.toString());
							return "Failed to calculate SCS SI score";
						}
					}
				}
			}
		
		scsForm.setScsTotals(scsTotals);
		
		scsForm.setPrimaryClassificationTypeId("");
		scsForm.setPrimaryClassificationTypeDesc("");
		scsForm.setSecondaryClassificationTypeId("");
		scsForm.setSecondaryClassificationTypeDesc("");
		
		return null;
	}//end of setSCSTotalsResponseEvent()
	
	
	/**
	 * 
	 * @param assessmentForm
	 * @param selectedAssessmentBeanId
	 * @param assessmentListType
	 * @return
	 */
	public static AssessmentLightBean getAssessmentBean(AssessmentForm assessmentForm, String selectedAssessmentBeanId, int assessmentListType)
	{
		ArrayList assessmentList=null;
		
		
			if(assessmentListType==CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT_LIST)
			{
				assessmentList = (ArrayList)assessmentForm.getInitialAssessmentsList();
			}
			else
				if(assessmentListType==CSCAssessmentConstants.ASSESSMENT_REASSESSMENT_LIST)
				{
					assessmentList = (ArrayList)assessmentForm.getReassessmentsList();
				}
				else
					if(assessmentListType==CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST)
					{
						assessmentList = (ArrayList)assessmentForm.getScsAssessmentsList();
					}
			
			if((assessmentList != null) && (assessmentList.size() > 0))
			{
				Iterator iterator = assessmentList.iterator();
				while(iterator.hasNext())
				{
					AssessmentLightBean assessmentLightBean = (AssessmentLightBean) iterator.next();
					if(assessmentLightBean.getAssessmentBeanId().equalsIgnoreCase(selectedAssessmentBeanId))
					{
						return assessmentLightBean;
					}
				}
			}
		
		return null;
	}//end of getAssessmentBean()
	
	
	/**
	 * 
	 * @param actionForm
	 * @param assessmentDetailsRespEvt
	 */
	public static void populateAssessmentDetailsResponseEventForWisconsin(WisconsinAssessmentForm wisconsinForm, AssessmentDetailsResponseEvent assessmentDetailsRespEvt)
	{
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();			
//			convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);			
//			separate the questions into Risk and Needs QuestionList and assign then to WisconsinForm
		if(uiQuestionGroupsList.size() > 0)
		{
			Iterator iterator = uiQuestionGroupsList.iterator();
			
			while(iterator.hasNext())
			{
				CSCQuestionGroup quesGroup = (CSCQuestionGroup)iterator.next();
				
				if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
				{
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITAIL_RISK_QUEST_GRP_ID))
					{
						ArrayList riskList = new ArrayList();
						riskList.add(quesGroup);
						wisconsinForm.setRiskAssessmentQuestionsList(riskList);
					}
					else
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITIAL_NEEDS_QUEST_GRP_ID))
					{
						ArrayList needsList = new ArrayList();
						needsList.add(quesGroup);
						wisconsinForm.setNeedsAssessmentQuestionsList(needsList);
					}
				}
				else
				{
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_REASSESS_RISK_QUEST_GRP_ID))
					{
						ArrayList riskList = new ArrayList();
						riskList.add(quesGroup);
						wisconsinForm.setRiskAssessmentQuestionsList(riskList);
					}
					else
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_REASSESS_NEEDS_QUEST_GRP_ID))
					{
						ArrayList needsList = new ArrayList();
						needsList.add(quesGroup);
						wisconsinForm.setNeedsAssessmentQuestionsList(needsList);
					}
				}
			}
		}
		
		wisconsinForm.setTotalRiskScore(assessmentDetailsRespEvt.getRiskScore());
		wisconsinForm.setRiskLevel(assessmentDetailsRespEvt.getRiskLevel());
		wisconsinForm.setTotalNeedsScore(assessmentDetailsRespEvt.getNeedsScore());
		wisconsinForm.setNeedsLevel(assessmentDetailsRespEvt.getNeedsLevel());
		int levelOfSupervisionCd = 4;
		try
		{
			levelOfSupervisionCd = Integer.parseInt(assessmentDetailsRespEvt.getLevelOfSupervision());
		}
		catch(Exception ex)
		{
		}
		wisconsinForm.setLevelOfSupervisionCd(assessmentDetailsRespEvt.getLevelOfSupervision());
		wisconsinForm.setLevelOfSupervision(getLevelOfSupervisionDesc(levelOfSupervisionCd));
		wisconsinForm.setSentToState(assessmentDetailsRespEvt.isSentToState());
	}//end of populateAssessmentDetailsResponseEventForWisconsin()
	
	
	/**
	 * 
	 * @param actionForm
	 * @param assessmentDetailsRespEvt
	 */
	public static void populateAssessmentDetailsResponseEventForLSIR(LSIRAssessmentForm lsirForm, AssessmentDetailsResponseEvent assessmentDetailsRespEvt)
	{
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();			
//			convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);			
		
		lsirForm.setLsirAssessmentQuestionsList(uiQuestionGroupsList);
		lsirForm.setComments(assessmentDetailsRespEvt.getComments());
		lsirForm.setSentToState(assessmentDetailsRespEvt.isSentToState());
	}//end of populateAssessmentDetailsResponseEventForLSIR()
	
	
	/**
	 * 
	 * @param actionForm
	 * @param assessmentDetailsRespEvt
	 */
	public static void populateAssessmentDetailsResponseEventForForceField(ForceFieldAssessmentForm forceFieldAssessmentForm, AssessmentDetailsResponseEvent assessmentDetailsRespEvt)
	{
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();			
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		
		forceFieldAssessmentForm.setForceFieldQuestionsList(uiQuestionGroupsList);
		forceFieldAssessmentForm.setSentToState(assessmentDetailsRespEvt.isSentToState());
	}//end of populateAssessmentDetailsResponseEventForForceField()
	
	
	/**
	 * 
	 * @param actionForm
	 * @param assessmentDetailsRespEvt
	 */
	public static void populateAssessmentDetailsResponseEventForSCS(SCSAssessmentForm scsForm, AssessmentDetailsResponseEvent assessmentDetailsRespEvt)
	{
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();			
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		
//		separate the questions into different QuestionGroups and assign then to SCSForm
		if((uiQuestionGroupsList != null) && (uiQuestionGroupsList.size() == 4))
		{
			Iterator questionGrpIterator = uiQuestionGroupsList.iterator();
			while(questionGrpIterator.hasNext())
			{
				CSCQuestionGroup questionGrp = (CSCQuestionGroup) questionGrpIterator.next();
				ArrayList questionGrpList = new ArrayList();
				questionGrpList.add(questionGrp);
				
				if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE_QUEST_GRP_ID))
				{
					scsForm.setScsScreenOneQuestionsList(questionGrpList);
				}
				else
					if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO_QUEST_GRP_ID))
					{
						scsForm.setScsScreenTwoQuestionsList(questionGrpList);
					}
					else
						if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE_QUEST_GRP_ID))
						{
							scsForm.setScsScreenThreeQuestionsList(questionGrpList);
						}
						else
						{
							scsForm.setScsScreenFourQuestionsList(questionGrpList);
						}
			}
		}
		
		if((scsForm.getAction().equalsIgnoreCase(UIConstants.VIEW_DETAIL)) ||
			(scsForm.getAction().equalsIgnoreCase(UIConstants.ASSESSMENT_VERSION_DETAILS)))
		{
//		set the summary question list
			ArrayList summaryQuestionsList = new ArrayList();
			
			CSCQuestionGroup screenOneQuesGrp = (CSCQuestionGroup)((ArrayList)scsForm.getScsScreenOneQuestionsList()).get(0);
			CSCQuestionGroup questGrp = new CSCQuestionGroup();
			questGrp.setGroupId(screenOneQuesGrp.getGroupId());
			questGrp.setGroupSequence(screenOneQuesGrp.getGroupSequence());
			questGrp.setGroupText(screenOneQuesGrp.getGroupText());		
			ArrayList questionList = new ArrayList();
			questGrp.setQuestions(questionList);
			
			ArrayList screenOneQuesLst = (ArrayList)screenOneQuesGrp.getQuestions();
			questionList.addAll(screenOneQuesLst);
			
			ArrayList screenTwoQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsForm.getScsScreenTwoQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenTwoQuesLst);
			
			ArrayList screenThreeQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsForm.getScsScreenThreeQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenThreeQuesLst);
			
			ArrayList screenFourQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsForm.getScsScreenFourQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenFourQuesLst);
			
			summaryQuestionsList.add(questGrp);
			scsForm.setScsSummaryQuestionsList(summaryQuestionsList);
			
			scsForm.setSentToState(assessmentDetailsRespEvt.isSentToState());
		}
		
		if(scsForm.getAction().equalsIgnoreCase(UIConstants.ASSESSMENT_VERSION_DETAILS))
		{
//	    	set force field variables
	    	scsForm.setForceFieldAssessmentId(assessmentDetailsRespEvt.getForceFieldId());
	    	
	    	if((scsForm.getForceFieldAssessmentId() != null) && (!scsForm.getForceFieldAssessmentId().equalsIgnoreCase("")))
	    	{
	    		scsForm.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
	    	}
	    	else
	    	{
	    		scsForm.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
	    		scsForm.setForceFieldAssessmentId(null);
	    	}
		}
		
		scsForm.setComments(assessmentDetailsRespEvt.getComments());
		scsForm.setTotalCC(assessmentDetailsRespEvt.getTotalCC());
		scsForm.setTotalES(assessmentDetailsRespEvt.getTotalES());
		scsForm.setTotalLS(assessmentDetailsRespEvt.getTotalLS());
		scsForm.setTotalSI(assessmentDetailsRespEvt.getTotalSI());
		
		scsForm.setPrimaryClassificationTypeId(assessmentDetailsRespEvt.getPrimaryClassification());
		scsForm.setSecondaryClassificationTypeId(assessmentDetailsRespEvt.getSecondaryClassification());
	}//end of populateAssessmentDetailsResponseEventForSCS()
	
	
	
	
	/**
	 * 
	 * @param actionForm
	 * @param assessmentDetailsRespEvt
	 */
	public static void populateAssessmentDetailsResponseEventForSCSInterview(SCSInterviewAssessmentForm scsInterviewForm, AssessmentDetailsResponseEvent assessmentDetailsRespEvt)
	{
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();			
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		
//		separate the questions into different QuestionGroups and assign then to SCSInterviewForm
		if((uiQuestionGroupsList != null) && (uiQuestionGroupsList.size() == 6))
		{
			Iterator questionGrpIterator = uiQuestionGroupsList.iterator();
			while(questionGrpIterator.hasNext())
			{
				CSCQuestionGroup questionGrp = (CSCQuestionGroup) questionGrpIterator.next();
				ArrayList questionGrpList = new ArrayList();
				questionGrpList.add(questionGrp);
				
				if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_ONE_QUEST_GRP_ID))
				{
					scsInterviewForm.setScsIntrvScreenOneQuestionsList(questionGrpList);
				}
				else
				if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_TWO_QUEST_GRP_ID))
				{
					scsInterviewForm.setScsIntrvScreenTwoQuestionsList(questionGrpList);
				}
				else
				if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_THREE_QUEST_GRP_ID))
				{
					scsInterviewForm.setScsIntrvScreenThreeQuestionsList(questionGrpList);
				}
				else
				if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_FOUR_QUEST_GRP_ID))
				{
					scsInterviewForm.setScsIntrvScreenFourQuestionsList(questionGrpList);
				}
				else
				if(questionGrp.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_FIVE_QUEST_GRP_ID))
				{
					scsInterviewForm.setScsIntrvScreenFiveQuestionsList(questionGrpList);
				}
				else
				{
					scsInterviewForm.setScsIntrvScreenSixQuestionsList(questionGrpList);
				}
			}
		}
		
		if((scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.VIEW_DETAIL)) ||
			(scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.ASSESSMENT_VERSION_DETAILS)))
		{
//		set the summary question list
			ArrayList summaryQuestionsList = new ArrayList();
			
			CSCQuestionGroup screenOneQuesGrp = (CSCQuestionGroup)((ArrayList)scsInterviewForm.getScsIntrvScreenOneQuestionsList()).get(0);
			CSCQuestionGroup questGrp = new CSCQuestionGroup();
			questGrp.setGroupId(screenOneQuesGrp.getGroupId());
			questGrp.setGroupSequence(screenOneQuesGrp.getGroupSequence());
			questGrp.setGroupText(screenOneQuesGrp.getGroupText());		
			ArrayList questionList = new ArrayList();
			questGrp.setQuestions(questionList);
			
			ArrayList screenOneQuesLst = (ArrayList)screenOneQuesGrp.getQuestions();
			questionList.addAll(screenOneQuesLst);
			
			ArrayList screenTwoQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsInterviewForm.getScsIntrvScreenTwoQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenTwoQuesLst);
			
			ArrayList screenThreeQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsInterviewForm.getScsIntrvScreenThreeQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenThreeQuesLst);
			
			ArrayList screenFourQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsInterviewForm.getScsIntrvScreenFourQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenFourQuesLst);
			
			ArrayList screenFiveQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsInterviewForm.getScsIntrvScreenFiveQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenFiveQuesLst);
			
			ArrayList screenSixQuesLst = (ArrayList)((CSCQuestionGroup)((ArrayList)scsInterviewForm.getScsIntrvScreenSixQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenSixQuesLst);
			
			summaryQuestionsList.add(questGrp);
			scsInterviewForm.setScsSummaryQuestionsList(summaryQuestionsList);
			
			scsInterviewForm.setSentToState(assessmentDetailsRespEvt.isSentToState());
		}
		
		if(scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.ASSESSMENT_VERSION_DETAILS))
		{
//	    	set force field variables
			scsInterviewForm.setForceFieldAssessmentId(assessmentDetailsRespEvt.getForceFieldId());
	    	
	    	if((scsInterviewForm.getForceFieldAssessmentId() != null) && (!scsInterviewForm.getForceFieldAssessmentId().equalsIgnoreCase("")))
	    	{
	    		scsInterviewForm.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
	    	}
	    	else
	    	{
	    		scsInterviewForm.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
	    		scsInterviewForm.setForceFieldAssessmentId(null);
	    	}
		}
		
	}//end of populateAssessmentDetailsResponseEventForSCS()
	
	
	
	/**
	 * 
	 * @param actionForm
	 * @param priorVersionList
	 */
	public static void populatePriorAssessmentVersionResponseEvent(ActionForm actionForm, Collection priorVersionList)
	{
		if((priorVersionList != null) && (priorVersionList.size() > 0))
		{
			AssessmentTypeForm assessmentTypeForm = (AssessmentTypeForm)actionForm;
			
			ArrayList versionList = new ArrayList();
			
			Iterator it = priorVersionList.iterator();
			while(it.hasNext())
			{
				PriorAssessmentVersionResponseEvent versionRespEvt =  (PriorAssessmentVersionResponseEvent)it.next();
				
				AssessmentVersion assessmentVersion = new AssessmentVersion();
				
				assessmentVersion.setAssessmentDate(versionRespEvt.getAssessmentDate());
				String dateString = DateUtil.dateToString(versionRespEvt.getAssessmentDate(), DateUtil.DATE_FMT_1);
				assessmentVersion.setAssessmentDateStr(dateString);
				
				assessmentVersion.setTransactionDate(versionRespEvt.getTransactionDate());
				dateString = DateUtil.dateToString(versionRespEvt.getTransactionDate(), DateUtil.DATE_FMT_1);
				assessmentVersion.setTransactionDateStr(dateString);
				
				assessmentVersion.setAssessmentId(versionRespEvt.getAssessmentId());
				assessmentVersion.setVersionNumber(versionRespEvt.getVersionNumber());
				
				versionList.add(assessmentVersion);
			}
			Collections.sort(versionList);
			assessmentTypeForm.setVersionDetailsList(versionList);
			
			AssessmentVersion latestAssessmentVersion = (AssessmentVersion)versionList.get(0);
			assessmentTypeForm.setAssessmentDate(latestAssessmentVersion.getAssessmentDate());
			assessmentTypeForm.setAssessmentDateStr(latestAssessmentVersion.getAssessmentDateStr());
			assessmentTypeForm.setAssessmentId(latestAssessmentVersion.getAssessmentId());
			assessmentTypeForm.setVersionId(latestAssessmentVersion.getVersionNumber());
			assessmentTypeForm.setIslatestVersionShown(true);
		}
	}//end of populatePriorAssessmentVersionResponseEvent()
	
	
	/**
	 * 
	 * @param enteredAssessDate
	 * @param assessmentTypeForm
	 * @return
	 */
	public static boolean isAssessmentDateInSupervisionRange(Date enteredAssessDate, AssessmentTypeForm assessmentTypeForm)
   {
	   	Date supervisionBeginDate = assessmentTypeForm.getSupervisionBeginDate();
		Date supervisionEndDate = assessmentTypeForm.getSupervisionEndDate();
		boolean isAssessmentDateInRange = true;
		
	   	if((supervisionBeginDate != null) && (supervisionEndDate != null))
		{
			isAssessmentDateInRange = false;
			int result=0;
	   		
	   		if(enteredAssessDate != null)
	   		{
	   			result = DateUtil.compare(enteredAssessDate,supervisionBeginDate,DateUtil.DATE_FMT_1);
	   			if(result >= 0)
	   			{
	   				result = DateUtil.compare(enteredAssessDate,supervisionEndDate,DateUtil.DATE_FMT_1);
	   				if(result<=0)
	   				{
	   					isAssessmentDateInRange = true;
	   				}
	   			}
	   		}
		}
	   	return isAssessmentDateInRange;
   }//end of isAssessmentDateInSupervisionRange()
	
	
	
	/**
	 * 
	 * @param levelOfSupervisionCd
	 * @return
	 */
	public static String getLevelOfSupervisionDesc(int levelOfSupervisionCd)
	{
		String levelOfSupervisionDesc = CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM;
		
		switch(levelOfSupervisionCd)
		{
			case CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM_CD: 
			{
				levelOfSupervisionDesc = CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM;
				break;
			}
			case CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD:
			{
				levelOfSupervisionDesc = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM;
				break;
			}
			case CSCAssessmentConstants.ASSESSMENT_LOS_MAXIMUM_CD:
			{
				levelOfSupervisionDesc = CSCAssessmentConstants.ASSESSMENT_LOS_MAXIMUM;
				break;
			}
		}
		return levelOfSupervisionDesc;
	}//end of getLevelOfSupervisionDesc()
	
	
	
	public static ArrayList getReassessListWithoutPendingWisc(ArrayList reassessmentList)
	{
		ArrayList filteredReassessList = new ArrayList();
		
		if((reassessmentList != null) && (reassessmentList.size() > 0))
		{
			Iterator it = reassessmentList.iterator();
			while(it.hasNext())
			{
				AssessmentLightBean assessBeanObj = (AssessmentLightBean)it.next();
				if((assessBeanObj.getAssessmentStatus().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST)) && 
   						(assessBeanObj.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN)))
   				{
					continue;
   				}
				else
				{
					filteredReassessList.add(assessBeanObj);
				}
			}
		}
		return filteredReassessList;
	}
	
	
	
	/**
	    * 
	    * @param questionGroupList
	    * @return
	    */
	   public static boolean validateWisconsinFields(ArrayList questionGroupList)
	   {
	   	if((questionGroupList != null) && (questionGroupList.size()> 0))
		{
			Iterator questionGroupIterator = questionGroupList.iterator();
			while(questionGroupIterator.hasNext())
			{
				CSCQuestionGroup questionGroup = (CSCQuestionGroup)questionGroupIterator.next();
				
				ArrayList questionsList = (ArrayList)questionGroup.getQuestions();
				Iterator questionsIterator = questionsList.iterator();
				
				while(questionsIterator.hasNext())
				{
					CSCQuestion question = (CSCQuestion)questionsIterator.next();
					if(question.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_SELECT))
					{
						if((question.getResponseId()==null) || ((question.getResponseId().equalsIgnoreCase(""))))
						{
							return false;
						}
						ArrayList possibleResponsesList = (ArrayList)question.getResponseChoices();
						Iterator possibleRespItr = possibleResponsesList.iterator();
						while(possibleRespItr.hasNext())
						{
							CSCQuestionResponse possibleResp = (CSCQuestionResponse)possibleRespItr.next();
							if(question.getResponseId().equalsIgnoreCase(possibleResp.getResponseId()))
							{
								if(possibleResp.getResponseValue().equalsIgnoreCase(""))
								{
									return false;
								}
							}
						}					
					}
					else
					if(question.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_RADIO))
					{
						if((question.getResponseId()==null) || ((question.getResponseId().equalsIgnoreCase(""))))
						{
							return false;
						}
					}
				}
			}			
		}
	   	return true;
	   }//end of validateFields()
	   

}
