/*
 * Created on Feb 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;

import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administerassessments.reply.CSCPossibleResponseEvent;
import messaging.administerassessments.reply.CSCQuestionGroupResponseEvent;
import messaging.administerassessments.reply.CSCQuestionResponseEvent;
import messaging.administerassessments.reply.PriorAssessmentVersionResponseEvent;
import messaging.administerassessments.reply.SCSTotalsResponseEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import ui.common.CSCQuestion;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestExecution	
{
	
	public static SupervisionPeriodResponseEvent fakeSupervisionPeriodResponseEventExecute()
	{
		SupervisionPeriodResponseEvent responseEvtObj = new SupervisionPeriodResponseEvent();
		Date date = new Date(System.currentTimeMillis()-999999999);
		responseEvtObj.setSupervisionBeginDate(date);
		System.out.println("Begin Date*******************=" + date.toString());
		date = new Date(System.currentTimeMillis());
		responseEvtObj.setSupervisionEndDate(date);
		System.out.println("End Date*******************=" + date.toString());
		return responseEvtObj;
	}
	
	public static ArrayList fakeDisplayAssessmentSummaryExectue(AssessmentForm assessmentForm)
	{
		ArrayList assessmentSummaryResponseEventList = new ArrayList();
		
		/**		**/	
//		create the Wisconsin Initial Assessment
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj1 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj1.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
		assessmentSummaryResponseEventObj1.setMasterAssessmentId("1");
		assessmentSummaryResponseEventObj1.setAssessmentId("11");
		assessmentSummaryResponseEventObj1.setInitial(true);
		assessmentSummaryResponseEventObj1.setAssessmentDate(new Date(System.currentTimeMillis() - 310000)); 
		assessmentSummaryResponseEventObj1.setRiskScore("11");
		assessmentSummaryResponseEventObj1.setNeedsScore("11");
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj1);
		
		
//		create the LSI-R Initial Assessment		
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj2 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj2.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
		assessmentSummaryResponseEventObj2.setMasterAssessmentId("2");
		assessmentSummaryResponseEventObj2.setAssessmentId("12");
		assessmentSummaryResponseEventObj2.setInitial(true);
		assessmentSummaryResponseEventObj2.setAssessmentDate(new Date(System.currentTimeMillis() - 320000)); 
		assessmentSummaryResponseEventObj2.setRiskScore("12");
		assessmentSummaryResponseEventObj2.setNeedsScore("12");
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj2);
		

//		create the SCS Assessment with Force Field			
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj3 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj3.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
		assessmentSummaryResponseEventObj3.setMasterAssessmentId("3");
		assessmentSummaryResponseEventObj3.setAssessmentId("13");			
		assessmentSummaryResponseEventObj3.setForceFieldMasterAssessmentId("31");
		assessmentSummaryResponseEventObj3.setForceFieldAssessmentId("131");	
		assessmentSummaryResponseEventObj3.setAssessmentDate(new Date(System.currentTimeMillis() - 3630000)); 		
		assessmentSummaryResponseEventObj3.setPrimaryClassificationTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI);
		assessmentSummaryResponseEventObj3.setSecondaryClassificationTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_CC);
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj3);

		
//		create the SCS Assessment with new Force Field		
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj6 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj6.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
		assessmentSummaryResponseEventObj6.setMasterAssessmentId("6");
		assessmentSummaryResponseEventObj6.setAssessmentId("16");			
		assessmentSummaryResponseEventObj6.setForceFieldMasterAssessmentId(null);
		assessmentSummaryResponseEventObj6.setForceFieldAssessmentId(null);	
		assessmentSummaryResponseEventObj6.setAssessmentDate(new Date(System.currentTimeMillis() - 360000)); 		
		assessmentSummaryResponseEventObj6.setPrimaryClassificationTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_CC);
		assessmentSummaryResponseEventObj6.setSecondaryClassificationTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI);
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj6);
		
		
	
//		create the Wisconsin Reassessment
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj4 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj4.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
		assessmentSummaryResponseEventObj4.setMasterAssessmentId("4");
		assessmentSummaryResponseEventObj4.setAssessmentId("41");
		assessmentSummaryResponseEventObj4.setInitial(false);
		assessmentSummaryResponseEventObj4.setAssessmentDate(new Date(System.currentTimeMillis())); 
		assessmentSummaryResponseEventObj4.setDueDate(new Date(System.currentTimeMillis() - 999999990)); 
		assessmentSummaryResponseEventObj4.setRiskScore("14");
		assessmentSummaryResponseEventObj4.setNeedsScore("14");
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj4);
		
//		create the LSIR Reassessment	
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj5 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj5.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
		assessmentSummaryResponseEventObj5.setMasterAssessmentId("5");
		assessmentSummaryResponseEventObj5.setAssessmentId("15");
		assessmentSummaryResponseEventObj5.setInitial(false);
		assessmentSummaryResponseEventObj5.setAssessmentDate(new Date(System.currentTimeMillis())); 
		assessmentSummaryResponseEventObj5.setDueDate(new Date(System.currentTimeMillis() - 3610000)); 
		assessmentSummaryResponseEventObj5.setRiskScore("15");
		assessmentSummaryResponseEventObj5.setNeedsScore("15");
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj5);
		
//		create the Rescheduled Wisconsin Reassessment
		AssessmentSummaryResponseEvent assessmentSummaryResponseEventObj8 = new AssessmentSummaryResponseEvent();
		assessmentSummaryResponseEventObj8.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
		assessmentSummaryResponseEventObj8.setInitial(false);
		assessmentSummaryResponseEventObj8.setDueDate(new Date(System.currentTimeMillis() - 3610000)); 
		
		assessmentSummaryResponseEventList.add(assessmentSummaryResponseEventObj8);
		
		return assessmentSummaryResponseEventList;
		
	}
	
	public static Collection fakeLSIRCreateExecute()
	{
		/****************************** start 1 QuestionGroup  ******************************************/
	 	
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt1 = new CSCQuestionGroupResponseEvent();	   		
	   		
	   		
	   		questionGrpRespEvt1.setId("LSIR1");
	   		questionGrpRespEvt1.setDisplayText(null);
	   		questionGrpRespEvt1.setDisplayTextDetailHeader(false);
	   		questionGrpRespEvt1.setDisplayTextAlign(null);
	   		questionGrpRespEvt1.setSequence(1);	   		
	   	
	   		ArrayList questionRespList1 = new ArrayList();	
	   		questionGrpRespEvt1.setQuestionResponseEvents(questionRespList1);
	   		
	   		/******* Start 1 Question ******************/	   		
	   		CSCQuestionResponseEvent questionRespEvt11 = new CSCQuestionResponseEvent();
	   		
	   		questionRespEvt11.setId("11");
	   		questionRespEvt11.setText("LSIR-Risk Score");
	   		questionRespEvt11.setRowSequence(1);
	   		questionRespEvt11.setColumnSequence(1);
	   		questionRespEvt11.setRequired(true);
	   		questionRespEvt11.setRequiredImageShown(true);
	   		questionRespEvt11.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_TEXT);	   		
	   		questionRespEvt11.setResponseNewLine(false);
	   		questionRespEvt11.setSelectedResponseId(null);
	   		questionRespEvt11.setSelectedResponseText(null);
	   		questionRespEvt11.setResponseDataType(CSCQuestion.RESPONSE_VAR_TYPE_INTEGER);
	   		questionRespEvt11.setUiControlSize("2");
	   		questionRespEvt11.setMinValue("0");
	   		questionRespEvt11.setMaxValue("43");
	   		questionRespEvt11.setColumnWidth("1%");
	   		questionRespEvt11.setPossibleResponseEvents(null);		   			   		
	   		/******* End 1 Question **************************/
	   		
	   		
	   		/******* Start 2 Question ******************/	   		
	   		CSCQuestionResponseEvent questionRespEvt12 = new CSCQuestionResponseEvent();
	   		
	   		questionRespEvt12.setId("11");
	   		questionRespEvt12.setText("LSIR-Needs Score");
	   		questionRespEvt12.setRowSequence(2);
	   		questionRespEvt12.setColumnSequence(1);
	   		questionRespEvt12.setRequired(true);
	   		questionRespEvt12.setRequiredImageShown(true);
	   		questionRespEvt12.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_TEXT);	   		
	   		questionRespEvt12.setResponseNewLine(false);
	   		questionRespEvt12.setSelectedResponseId(null);
	   		questionRespEvt12.setSelectedResponseText(null);
	   		questionRespEvt12.setResponseDataType(CSCQuestion.RESPONSE_VAR_TYPE_INTEGER);
	   		questionRespEvt12.setUiControlSize("2");
	   		questionRespEvt12.setMinValue("-8");
	   		questionRespEvt12.setMaxValue("60");
	   		questionRespEvt12.setColumnWidth("1%");
	   		questionRespEvt12.setPossibleResponseEvents(null);	
	   		
	   		/******* End 2 Question ******************/
	   		
	   		questionRespList1.add(questionRespEvt11);
	   		questionRespList1.add(questionRespEvt12);
	   		
	   		ArrayList questionGrpList = new ArrayList();
	   		questionGrpList.add(questionGrpRespEvt1);
	   		
	   		return questionGrpList;
	}
	
	/**
	 * WISCONSIN INITIAL ASSESSMENT - RISK
	 * @return
	 */
	public static Collection fakeInitialWisconsinCreateExecute()
	   {
		
		/******************************************* Question Group 1 (Risk) Start  ********************************/
		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt1 = new CSCQuestionGroupResponseEvent();
	   		
	   		/******* Start First Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt11 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt110 = new CSCPossibleResponseEvent();
	   		possibleRespEvt110.setId("110");
	   		possibleRespEvt110.setDisplayText("Please Select");
	   		possibleRespEvt110.setResponseValue("");
	   		possibleRespEvt110.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt111 = new CSCPossibleResponseEvent();
	   		possibleRespEvt111.setId("111");
	   		possibleRespEvt111.setDisplayText("None");
	   		possibleRespEvt111.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt112 = new CSCPossibleResponseEvent();
	   		possibleRespEvt112.setId("112");
	   		possibleRespEvt112.setDisplayText("One");
	   		possibleRespEvt112.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt113 = new CSCPossibleResponseEvent();
	   		possibleRespEvt113.setId("113");
	   		possibleRespEvt113.setDisplayText("Two or More");
	   		possibleRespEvt113.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList11 = new ArrayList();   		
	   		possibleAnswersList11.add(possibleRespEvt110);
	   		possibleAnswersList11.add(possibleRespEvt111);
	   		possibleAnswersList11.add(possibleRespEvt112);
	   		possibleAnswersList11.add(possibleRespEvt113);
	   		
			
	  		
	   		questionRespEvt11.setId("11");
	   		questionRespEvt11.setText("Number of Address changes in the Last 12 months");
	   		questionRespEvt11.setRowSequence(1);
	   		questionRespEvt11.setColumnSequence(1);
	   		questionRespEvt11.setRequired(true);
	   		questionRespEvt11.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt11.setResponseNewLine(false);
	   		questionRespEvt11.setEachResponseNewLine(false);	   		
	   		questionRespEvt11.setSelectedResponseId(null);
	   		questionRespEvt11.setSelectedResponseText(null);
	   		questionRespEvt11.setPossibleResponseEvents(possibleAnswersList11);
	   		
	   		/******* End First Question ****************/
	   		
	   		
	   		
	   		/******* Start Second Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt12 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt120 = new CSCPossibleResponseEvent();
	   		possibleRespEvt120.setId("120");
	   		possibleRespEvt120.setDisplayText("Please Select");
	   		possibleRespEvt120.setResponseValue("");
	   		possibleRespEvt120.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt121 = new CSCPossibleResponseEvent();
	   		possibleRespEvt121.setId("121");
	   		possibleRespEvt121.setDisplayText("60% or More");
	   		possibleRespEvt121.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt122 = new CSCPossibleResponseEvent();
	   		possibleRespEvt122.setId("122");
	   		possibleRespEvt122.setDisplayText("40%-59%");
	   		possibleRespEvt122.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt123 = new CSCPossibleResponseEvent();
	   		possibleRespEvt123.setId("123");
	   		possibleRespEvt123.setDisplayText("Under 40%");
	   		possibleRespEvt123.setResponseValue("3");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt124 = new CSCPossibleResponseEvent();
	   		possibleRespEvt124.setId("124");
	   		possibleRespEvt124.setDisplayText("Not Applicable");
	   		possibleRespEvt124.setResponseValue("4");
	   		
	   		ArrayList possibleAnswersList12 = new ArrayList();
	   		possibleAnswersList12.add(possibleRespEvt120);
	   		possibleAnswersList12.add(possibleRespEvt121);
	   		possibleAnswersList12.add(possibleRespEvt122);
	   		possibleAnswersList12.add(possibleRespEvt123);
	   		possibleAnswersList12.add(possibleRespEvt124);
			
	  		
	   		questionRespEvt12.setId("12");
	   		questionRespEvt12.setText("Percentage of Time Employed in the Last 12 months");
	   		questionRespEvt12.setRowSequence(2);
	   		questionRespEvt12.setColumnSequence(1);
	   		questionRespEvt12.setRequired(true);
	   		questionRespEvt12.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt12.setResponseNewLine(false);
	   		questionRespEvt12.setEachResponseNewLine(false);
	   		questionRespEvt12.setSelectedResponseId(null);
	   		questionRespEvt12.setSelectedResponseText(null);
	   		questionRespEvt12.setPossibleResponseEvents(possibleAnswersList12);
	   		
	   		/******* End Second Question ****************/
	   		
	   		
	   		/******* Start Third Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt13 = new CSCQuestionResponseEvent();
	   		

	   		CSCPossibleResponseEvent possibleRespEvt130 = new CSCPossibleResponseEvent();
	   		possibleRespEvt130.setId("130");
	   		possibleRespEvt130.setDisplayText("Alcohol use unrelated to criminal activity; ex., no alcohol-related arrests, no evidence of use during the offense");
	   		possibleRespEvt130.setResponseValue("0");	   		
	   		
	   		CSCPossibleResponseEvent possibleRespEvt131 = new CSCPossibleResponseEvent();
	   		possibleRespEvt131.setId("131");
	   		possibleRespEvt131.setDisplayText("Probable relationship between alcohol use and criminal activity");
	   		possibleRespEvt131.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt132 = new CSCPossibleResponseEvent();
	   		possibleRespEvt132.setId("132");
	   		possibleRespEvt132.setDisplayText("Definite relationship between alcohol use and criminal activity; ex., pattern of committing offenses while using alcohol");
	   		possibleRespEvt132.setResponseValue("2");
	   		
	   		
	   		ArrayList possibleAnswersList13 = new ArrayList();
	   		possibleAnswersList13.add(possibleRespEvt130);
	   		possibleAnswersList13.add(possibleRespEvt131);
	   		possibleAnswersList13.add(possibleRespEvt132);		
	  		
	   		questionRespEvt13.setId("13");
	   		questionRespEvt13.setText("Alcohol Usage");
	   		questionRespEvt13.setRowSequence(3);
	   		questionRespEvt13.setColumnSequence(1);
	   		questionRespEvt13.setRequired(true);
	   		questionRespEvt13.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt13.setResponseNewLine(true);
	   		questionRespEvt13.setEachResponseNewLine(true);
	   		questionRespEvt13.setSelectedResponseId(null);
	   		questionRespEvt13.setSelectedResponseText(null);
	   		questionRespEvt13.setPossibleResponseEvents(possibleAnswersList13);
	   		
	   		/******* End Third Question ****************/
	   		
	   		
	   		/******* Start Fourth Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt14 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt140 = new CSCPossibleResponseEvent();
	   		possibleRespEvt140.setId("140");
	   		possibleRespEvt140.setDisplayText("No abuse of legal drug; no indicators of illegal drug involvement, ie., use, possession or abuse");
	   		possibleRespEvt140.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt141 = new CSCPossibleResponseEvent();
	   		possibleRespEvt141.setId("141");
	   		possibleRespEvt141.setDisplayText("Probable relationship between drug involvement and criminal activity");
	   		possibleRespEvt141.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt142 = new CSCPossibleResponseEvent();
	   		possibleRespEvt142.setId("142");
	   		possibleRespEvt142.setDisplayText("Definite relationship between drug involvement and criminal activity; ex., pattern of committing offenses while using drugs, sale or manufacture of illegal drugs");
	   		possibleRespEvt142.setResponseValue("2");
	   			   		  		
	   		ArrayList possibleAnswersList14 = new ArrayList();
	   		possibleAnswersList14.add(possibleRespEvt140);
	   		possibleAnswersList14.add(possibleRespEvt141);
	   		possibleAnswersList14.add(possibleRespEvt142);		
	   			  		
	   		questionRespEvt14.setId("14");
	   		questionRespEvt14.setText("Other Drug Usage");
	   		questionRespEvt14.setRowSequence(4);
	   		questionRespEvt14.setColumnSequence(1);
	   		questionRespEvt14.setRequired(true);
	   		questionRespEvt14.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt14.setResponseNewLine(true);
	   		questionRespEvt14.setEachResponseNewLine(true);
	   		questionRespEvt14.setSelectedResponseId(null);
	   		questionRespEvt14.setSelectedResponseText(null);
	   		questionRespEvt14.setPossibleResponseEvents(possibleAnswersList14);
	   		
	   		/******* End Fourth Question ****************/
	   		
	   		
	   		
	   		/******* Start Fifth Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt15 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt151 = new CSCPossibleResponseEvent();
	   		possibleRespEvt151.setId("151");
	   		possibleRespEvt151.setDisplayText("Motivated to change; receptive to assistance");
	   		possibleRespEvt151.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt152 = new CSCPossibleResponseEvent();
	   		possibleRespEvt152.setId("152");
	   		possibleRespEvt152.setDisplayText("Somewhat motivated but dependent or unwilling to accept responsibility");
	   		possibleRespEvt152.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt153 = new CSCPossibleResponseEvent();
	   		possibleRespEvt153.setId("153");
	   		possibleRespEvt153.setDisplayText("Rationalizes behavior; negative; not motivated to change");
	   		possibleRespEvt153.setResponseValue("1");
	   		
	   		ArrayList possibleAnswersList15 = new ArrayList();
	   		possibleAnswersList15.add(possibleRespEvt151);
	   		possibleAnswersList15.add(possibleRespEvt152);		
	   		possibleAnswersList15.add(possibleRespEvt153);
	   		
	   		questionRespEvt15.setId("15");
	   		questionRespEvt15.setText("Attitude");
	   		questionRespEvt15.setRowSequence(5);
	   		questionRespEvt15.setColumnSequence(1);
	   		questionRespEvt15.setRequired(true);
	   		questionRespEvt15.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt15.setResponseNewLine(true);
	   		questionRespEvt15.setEachResponseNewLine(true);
	   		questionRespEvt15.setSelectedResponseId(null);
	   		questionRespEvt15.setSelectedResponseText(null);
	   		questionRespEvt15.setPossibleResponseEvents(possibleAnswersList15);
	   		
	   		/******* End Fifth Question ****************/
	   		
	   		
	   		
	   		/******* Start Six Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt16 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt161 = new CSCPossibleResponseEvent();
	   		possibleRespEvt161.setId("161");
	   		possibleRespEvt161.setDisplayText("Please Select");
	   		possibleRespEvt161.setResponseValue("");
	   		possibleRespEvt161.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt162 = new CSCPossibleResponseEvent();
	   		possibleRespEvt162.setId("162");
	   		possibleRespEvt162.setDisplayText("24 or older");
	   		possibleRespEvt162.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt163 = new CSCPossibleResponseEvent();
	   		possibleRespEvt163.setId("163");
	   		possibleRespEvt163.setDisplayText("20-23");
	   		possibleRespEvt163.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt164 = new CSCPossibleResponseEvent();
	   		possibleRespEvt164.setId("164");
	   		possibleRespEvt164.setDisplayText("19 or younger");
	   		possibleRespEvt164.setResponseValue("3");
	   		
	   		ArrayList possibleAnswersList16 = new ArrayList();
	   		possibleAnswersList16.add(possibleRespEvt161);
	   		possibleAnswersList16.add(possibleRespEvt162);		
	   		possibleAnswersList16.add(possibleRespEvt163);		
	   		possibleAnswersList16.add(possibleRespEvt164);		
	  		
	   		questionRespEvt16.setId("16");
	   		questionRespEvt16.setText("Adult or Juvenile Adjudication for wothless checks or forgery");
	   		questionRespEvt16.setRowSequence(6);
	   		questionRespEvt16.setColumnSequence(1);
	   		questionRespEvt16.setRequired(true);
	   		questionRespEvt16.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt16.setResponseNewLine(false);
	   		questionRespEvt16.setSelectedResponseId(null);
	   		questionRespEvt16.setSelectedResponseText(null);
	   		questionRespEvt16.setPossibleResponseEvents(possibleAnswersList16);
	   		
	   		/******* End Six Question ****************/
	   		
	   		
	   		
	   		
	   		/******* Start 7 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt17 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt171 = new CSCPossibleResponseEvent();
	   		possibleRespEvt171.setId("171");
	   		possibleRespEvt171.setDisplayText("Please Select");
	   		possibleRespEvt171.setResponseValue("");
	   		possibleRespEvt171.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt172 = new CSCPossibleResponseEvent();
	   		possibleRespEvt172.setId("172");
	   		possibleRespEvt172.setDisplayText("None");
	   		possibleRespEvt172.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt173 = new CSCPossibleResponseEvent();
	   		possibleRespEvt173.setId("163");
	   		possibleRespEvt173.setDisplayText("One or More");
	   		possibleRespEvt173.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList17 = new ArrayList();
	   		possibleAnswersList17.add(possibleRespEvt171);
	   		possibleAnswersList17.add(possibleRespEvt172);		
	   		possibleAnswersList17.add(possibleRespEvt173);		
	   		  		
	   		questionRespEvt17.setId("16");
	   		questionRespEvt17.setText("Number of Prior Periods of Community Supervision/ Parole Supervision (Adult or Juvenile)");
	   		questionRespEvt17.setRowSequence(7);
	   		questionRespEvt17.setColumnSequence(1);
	   		questionRespEvt17.setRequired(true);
	   		questionRespEvt17.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt17.setResponseNewLine(false);
	   		questionRespEvt17.setSelectedResponseId(null);
	   		questionRespEvt17.setSelectedResponseText(null);
	   		questionRespEvt17.setPossibleResponseEvents(possibleAnswersList17);
	   		
	   		/******* End 7 Question ****************/
	   		
	   		
	   		
	   		/******* Start 8 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt18 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt180 = new CSCPossibleResponseEvent();
	   		possibleRespEvt180.setId("180");
	   		possibleRespEvt180.setDisplayText("Please Select");
	   		possibleRespEvt180.setResponseValue("");
	   		possibleRespEvt180.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt181 = new CSCPossibleResponseEvent();
	   		possibleRespEvt181.setId("181");
	   		possibleRespEvt181.setDisplayText("None");
	   		possibleRespEvt181.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt182 = new CSCPossibleResponseEvent();
	   		possibleRespEvt182.setId("182");
	   		possibleRespEvt182.setDisplayText("One or More");
	   		possibleRespEvt182.setResponseValue("1");	   		
	   		   		
	   		ArrayList possibleAnswersList18 = new ArrayList();
	   		possibleAnswersList18.add(possibleRespEvt180);
	   		possibleAnswersList18.add(possibleRespEvt181);
	   		possibleAnswersList18.add(possibleRespEvt182);		
	   		
	   		questionRespEvt18.setId("18");
	   		questionRespEvt18.setText("Number of Prior Community Supervision/Parole Revocations(Adult or Juvenile)");
	   		questionRespEvt18.setRowSequence(8);
	   		questionRespEvt18.setColumnSequence(1);
	   		questionRespEvt18.setRequired(true);
	   		questionRespEvt18.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt18.setResponseNewLine(false);
	   		questionRespEvt18.setSelectedResponseId(null);
	   		questionRespEvt18.setSelectedResponseText(null);
	   		questionRespEvt18.setPossibleResponseEvents(possibleAnswersList18);
	   		
	   		/******* End 8 Question ****************/
	   		
	   		
	   		/******* Start 9 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt19 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt190 = new CSCPossibleResponseEvent();
	   		possibleRespEvt190.setId("190");
	   		possibleRespEvt190.setDisplayText("Please Select");
	   		possibleRespEvt190.setResponseValue("");
	   		possibleRespEvt190.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt191 = new CSCPossibleResponseEvent();
	   		possibleRespEvt191.setId("191");
	   		possibleRespEvt191.setDisplayText("None");
	   		possibleRespEvt191.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt192 = new CSCPossibleResponseEvent();
	   		possibleRespEvt192.setId("192");
	   		possibleRespEvt192.setDisplayText("One or More");
	   		possibleRespEvt192.setResponseValue("1");	   		
	   		
	   		CSCPossibleResponseEvent possibleRespEvt193 = new CSCPossibleResponseEvent();
	   		possibleRespEvt193.setId("193");
	   		possibleRespEvt193.setDisplayText("Two or More");
	   		possibleRespEvt193.setResponseValue("2");	  
	   		
	   		ArrayList possibleAnswersList19 = new ArrayList();
	   		possibleAnswersList19.add(possibleRespEvt190);
	   		possibleAnswersList19.add(possibleRespEvt191);
	   		possibleAnswersList19.add(possibleRespEvt192);		
	   		possibleAnswersList19.add(possibleRespEvt193);	
	   		
	   		questionRespEvt19.setId("19");
	   		questionRespEvt19.setText("Number of Prior Felony Adjudications of Guilt (or Juvenile Commitments - include deferred)");
	   		questionRespEvt19.setRowSequence(9);
	   		questionRespEvt19.setColumnSequence(1);
	   		questionRespEvt19.setRequired(true);
	   		questionRespEvt19.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt19.setResponseNewLine(false);
	   		questionRespEvt19.setSelectedResponseId(null);
	   		questionRespEvt19.setSelectedResponseText(null);
	   		questionRespEvt19.setPossibleResponseEvents(possibleAnswersList19);
	   		
	   		/******* End 9 Question ****************/
	   		
	   		
	   		/******* Start 10 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt110 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt1100 = new CSCPossibleResponseEvent();
	   		possibleRespEvt1100.setId("1100");
	   		possibleRespEvt1100.setDisplayText("Yes");
	   		possibleRespEvt1100.setResponseValue("1");
	 	   		
	   		CSCPossibleResponseEvent possibleRespEvt1101 = new CSCPossibleResponseEvent();
	   		possibleRespEvt1101.setId("1101");
	   		possibleRespEvt1101.setDisplayText("No");
	   		possibleRespEvt1101.setResponseValue("0");
	   		
	   		ArrayList possibleAnswersList110 = new ArrayList();
	   		possibleAnswersList110.add(possibleRespEvt1100);
	   		possibleAnswersList110.add(possibleRespEvt1101);
	   		
	   		questionRespEvt110.setId("110");
	   		questionRespEvt110.setText("Adult or Juvenile Adjudication for burglary, theft, auto theft, or robbery");
	   		questionRespEvt110.setRowSequence(10);
	   		questionRespEvt110.setColumnSequence(1);
	   		questionRespEvt110.setRequired(true);
	   		questionRespEvt110.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt110.setResponseNewLine(false);	   		
	   		questionRespEvt110.setSelectedResponseId(null);
	   		questionRespEvt110.setSelectedResponseText(null);
	   		questionRespEvt110.setPossibleResponseEvents(possibleAnswersList110);
	   		
	   		/******* End 10 Question ****************/
	   		
	   		
	   		/******* Start 11 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt111 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt1110 = new CSCPossibleResponseEvent();
	   		possibleRespEvt1110.setId("1110");
	   		possibleRespEvt1110.setDisplayText("Yes");
	   		possibleRespEvt1110.setResponseValue("1");
	 	   		
	   		CSCPossibleResponseEvent possibleRespEvt1111 = new CSCPossibleResponseEvent();
	   		possibleRespEvt1111.setId("1111");
	   		possibleRespEvt1111.setDisplayText("No");
	   		possibleRespEvt1111.setResponseValue("0");
	   		
	   		ArrayList possibleAnswersList111 = new ArrayList();
	   		possibleAnswersList111.add(possibleRespEvt1110);
	   		possibleAnswersList111.add(possibleRespEvt1111);
	   		
	   		questionRespEvt111.setId("111");
	   		questionRespEvt111.setText("Adult or Juvenile Adjudication for worthless checks or forgery");
	   		questionRespEvt111.setRowSequence(11);
	   		questionRespEvt111.setColumnSequence(1);
	   		questionRespEvt111.setRequired(true);
	   		questionRespEvt111.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt111.setResponseNewLine(false);	   		
	   		questionRespEvt111.setSelectedResponseId(null);
	   		questionRespEvt111.setSelectedResponseText(null);
	   		questionRespEvt111.setPossibleResponseEvents(possibleAnswersList111);
	   		
	   		/******* End 11 Question ****************/
	   		
	   		
	   		/******* Start 12 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt112 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt1120 = new CSCPossibleResponseEvent();
	   		possibleRespEvt1120.setId("1120");
	   		possibleRespEvt1120.setDisplayText("Yes");
	   		possibleRespEvt1120.setResponseValue("1");
	 	   		
	   		CSCPossibleResponseEvent possibleRespEvt1121 = new CSCPossibleResponseEvent();
	   		possibleRespEvt1121.setId("1121");
	   		possibleRespEvt1121.setDisplayText("No");
	   		possibleRespEvt1121.setResponseValue("0");
	   		
	   		ArrayList possibleAnswersList112 = new ArrayList();
	   		possibleAnswersList112.add(possibleRespEvt1120);
	   		possibleAnswersList112.add(possibleRespEvt1121);
	   		
	   		questionRespEvt112.setId("112");
	   		questionRespEvt112.setText("Adult or Juvenile Adjudications for Assaultive Offense within the Last FIVE Years (An offense which is defined as Assaultive, or one which involves the use of a weapon, physical force, or the threat offorce)");
	   		questionRespEvt112.setRowSequence(12);
	   		questionRespEvt112.setColumnSequence(1);
	   		questionRespEvt112.setRequired(true);
	   		questionRespEvt112.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt112.setResponseNewLine(false);	   		
	   		questionRespEvt112.setSelectedResponseId(null);
	   		questionRespEvt112.setSelectedResponseText(null);
	   		questionRespEvt112.setPossibleResponseEvents(possibleAnswersList112);
	   		
	   		/******* End 12 Question ****************/
	   		
	   		
	   		ArrayList questionRespList1 = new ArrayList();
	   		
	   		questionRespList1.add(questionRespEvt11);
	   		questionRespList1.add(questionRespEvt12);
	   		questionRespList1.add(questionRespEvt13);
	   		questionRespList1.add(questionRespEvt14);
	   		questionRespList1.add(questionRespEvt15);
	   		questionRespList1.add(questionRespEvt16);
	   		questionRespList1.add(questionRespEvt17);
	   		questionRespList1.add(questionRespEvt18);
	   		questionRespList1.add(questionRespEvt19);
	   		questionRespList1.add(questionRespEvt110);
	   		questionRespList1.add(questionRespEvt111);
	   		questionRespList1.add(questionRespEvt112);
			
	   		questionGrpRespEvt1.setId(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITAIL_RISK_QUEST_GRP_ID);
	   		questionGrpRespEvt1.setDisplayText("Risk Assessment");
	   		questionGrpRespEvt1.setDisplayTextDetailHeader(false);	   		
	   		questionGrpRespEvt1.setSequence(1);
	   		questionGrpRespEvt1.setQuestionResponseEvents(questionRespList1);
	   		
	   		/******************************************* Question Group 1 (Risk) End  ********************************/
	   		
	   		
	   		/******************************************* Question Group 2 (Needs) Start  ********************************/
	        
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt2 = new CSCQuestionGroupResponseEvent();
	   		
	   		/******* Start First Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt21 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt210 = new CSCPossibleResponseEvent();
	   		possibleRespEvt210.setId("210");
	   		possibleRespEvt210.setDisplayText("High School or above skill level");
	   		possibleRespEvt210.setResponseValue("0");	   		
	   		
	   		CSCPossibleResponseEvent possibleRespEvt211 = new CSCPossibleResponseEvent();
	   		possibleRespEvt211.setId("211");
	   		possibleRespEvt211.setDisplayText("Adequate skills, able to handle everyday requirements");
	   		possibleRespEvt211.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt212 = new CSCPossibleResponseEvent();
	   		possibleRespEvt212.setId("212");
	   		possibleRespEvt212.setDisplayText("Low skill level causing minor adjustment problems");
	   		possibleRespEvt212.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt213 = new CSCPossibleResponseEvent();
	   		possibleRespEvt213.setId("213");
	   		possibleRespEvt213.setDisplayText("Minimal skill level causing serious adjustment problems");
	   		possibleRespEvt213.setResponseValue("3");
	   		
	   		ArrayList possibleAnswersList21 = new ArrayList();   		
	   		possibleAnswersList21.add(possibleRespEvt210);
	   		possibleAnswersList21.add(possibleRespEvt211);
	   		possibleAnswersList21.add(possibleRespEvt212);
	   		possibleAnswersList21.add(possibleRespEvt213);
	   		
			
	  		
	   		questionRespEvt21.setId("21");
	   		questionRespEvt21.setText("ACADEMIC/VOCATIONAL SKILLS");
	   		questionRespEvt21.setRowSequence(1);
	   		questionRespEvt21.setColumnSequence(1);
	   		questionRespEvt21.setRequired(true);
	   		questionRespEvt21.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt21.setResponseNewLine(true);
	   		questionRespEvt21.setEachResponseNewLine(true);
	   		questionRespEvt21.setSelectedResponseId(null);
	   		questionRespEvt21.setSelectedResponseText(null);
	   		questionRespEvt21.setPossibleResponseEvents(possibleAnswersList21);
	   		
	   		/******* End First Question ****************/
	   		
	   		
	   		
	   		/******* Start Second Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt22 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt220 = new CSCPossibleResponseEvent();
	   		possibleRespEvt220.setId("220");
	   		possibleRespEvt220.setDisplayText("Satisfactory employment for one year or longer");
	   		possibleRespEvt220.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt221 = new CSCPossibleResponseEvent();
	   		possibleRespEvt221.setId("221");
	   		possibleRespEvt221.setDisplayText("Secure employment; no difficulties reported; or homemaker, student, or retired");
	   		possibleRespEvt221.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt222 = new CSCPossibleResponseEvent();
	   		possibleRespEvt222.setId("222");
	   		possibleRespEvt222.setDisplayText("Unsatisfactory employment or  unemployed but has adequate job skills");
	   		possibleRespEvt222.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt223 = new CSCPossibleResponseEvent();
	   		possibleRespEvt223.setId("223");
	   		possibleRespEvt223.setDisplayText("Unemployed and virtually unemployable; needs training");
	   		possibleRespEvt223.setResponseValue("3");
   		
	   		ArrayList possibleAnswersList22 = new ArrayList();
	   		possibleAnswersList22.add(possibleRespEvt220);
	   		possibleAnswersList22.add(possibleRespEvt221);
	   		possibleAnswersList22.add(possibleRespEvt222);
	   		possibleAnswersList22.add(possibleRespEvt223);
			
	  		
	   		questionRespEvt22.setId("22");
	   		questionRespEvt22.setText("EMPLOYMENT");
	   		questionRespEvt22.setRowSequence(2);
	   		questionRespEvt22.setColumnSequence(1);
	   		questionRespEvt22.setRequired(true);
	   		questionRespEvt22.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt22.setResponseNewLine(true);
	   		questionRespEvt22.setEachResponseNewLine(true);
	   		questionRespEvt22.setSelectedResponseId(null);
	   		questionRespEvt22.setSelectedResponseText(null);
	   		questionRespEvt22.setPossibleResponseEvents(possibleAnswersList22);
	   		
	   		/******* End Second Question ****************/
	   		
	   		
	   		/******* Start Third Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt23 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt230 = new CSCPossibleResponseEvent();
	   		possibleRespEvt230.setId("230");
	   		possibleRespEvt230.setDisplayText("Long-standing pattern of selfsufficiency;e.g., good credit");
	   		possibleRespEvt230.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt231 = new CSCPossibleResponseEvent();
	   		possibleRespEvt231.setId("231");
	   		possibleRespEvt231.setDisplayText("No current difficulties");
	   		possibleRespEvt231.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt232 = new CSCPossibleResponseEvent();
	   		possibleRespEvt232.setId("232");
	   		possibleRespEvt232.setDisplayText("Situational or minor difficulties");
	   		possibleRespEvt232.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt233 = new CSCPossibleResponseEvent();
	   		possibleRespEvt233.setId("233");
	   		possibleRespEvt233.setDisplayText("Severe difficulties; may include overdrafts, bad checks or bankruptcy");
	   		possibleRespEvt233.setResponseValue("3");
   		
	   		ArrayList possibleAnswersList23 = new ArrayList();
	   		possibleAnswersList23.add(possibleRespEvt230);
	   		possibleAnswersList23.add(possibleRespEvt231);
	   		possibleAnswersList23.add(possibleRespEvt232);
	   		possibleAnswersList23.add(possibleRespEvt233);
			
	  		
	   		questionRespEvt23.setId("23");
	   		questionRespEvt23.setText("FINANCIAL MANAGEMENT");
	   		questionRespEvt23.setRowSequence(3);
	   		questionRespEvt23.setColumnSequence(1);
	   		questionRespEvt23.setRequired(true);
	   		questionRespEvt23.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt23.setResponseNewLine(true);
	   		questionRespEvt23.setEachResponseNewLine(true);
	   		questionRespEvt23.setSelectedResponseId(null);
	   		questionRespEvt23.setSelectedResponseText(null);
	   		questionRespEvt23.setPossibleResponseEvents(possibleAnswersList23);
	   		
	   		/******* End Third Question ****************/
	   		
	   		
	   		/******* Start Fourth Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt24 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt240 = new CSCPossibleResponseEvent();
	   		possibleRespEvt240.setId("240");
	   		possibleRespEvt240.setDisplayText("Relationships and support exceptionally strong");
	   		possibleRespEvt240.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt241 = new CSCPossibleResponseEvent();
	   		possibleRespEvt241.setId("241");
	   		possibleRespEvt241.setDisplayText("Relatively stable relationships");
	   		possibleRespEvt241.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt242 = new CSCPossibleResponseEvent();
	   		possibleRespEvt242.setId("242");
	   		possibleRespEvt242.setDisplayText("Some disorganization or stress but potential for improvement");
	   		possibleRespEvt242.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt243 = new CSCPossibleResponseEvent();
	   		possibleRespEvt243.setId("243");
	   		possibleRespEvt243.setDisplayText("Major disorganization or stress");
	   		possibleRespEvt243.setResponseValue("3");
   		
	   		ArrayList possibleAnswersList24 = new ArrayList();
	   		possibleAnswersList24.add(possibleRespEvt240);
	   		possibleAnswersList24.add(possibleRespEvt241);
	   		possibleAnswersList24.add(possibleRespEvt242);
	   		possibleAnswersList24.add(possibleRespEvt243);
			
	  		
	   		questionRespEvt24.setId("24");
	   		questionRespEvt24.setText("MARITAL/FAMILY RELATIONSHIPS");
	   		questionRespEvt24.setRowSequence(4);
	   		questionRespEvt24.setColumnSequence(1);
	   		questionRespEvt24.setRequired(true);
	   		questionRespEvt24.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt24.setResponseNewLine(true);
	   		questionRespEvt24.setEachResponseNewLine(true);
	   		questionRespEvt24.setSelectedResponseId(null);
	   		questionRespEvt24.setSelectedResponseText(null);
	   		questionRespEvt24.setPossibleResponseEvents(possibleAnswersList24);
	   		
	   		/******* End Fourth Question ****************/
	   		
	   		
	   		
	   		/******* Start Fifth Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt25 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt251 = new CSCPossibleResponseEvent();
	   		possibleRespEvt251.setId("251");
	   		possibleRespEvt251.setDisplayText("Please Select");
	   		possibleRespEvt251.setResponseValue("");
	   		possibleRespEvt251.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt253 = new CSCPossibleResponseEvent();
	   		possibleRespEvt253.setId("253");
	   		possibleRespEvt253.setDisplayText("Good support and influence");
	   		possibleRespEvt253.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt254 = new CSCPossibleResponseEvent();
	   		possibleRespEvt254.setId("254");
	   		possibleRespEvt254.setDisplayText("No adverse relationships");
	   		possibleRespEvt254.setResponseValue("3");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt255 = new CSCPossibleResponseEvent();
	   		possibleRespEvt255.setId("255");
	   		possibleRespEvt255.setDisplayText("Associations with occasional negative results");
	   		possibleRespEvt255.setResponseValue("4");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt256 = new CSCPossibleResponseEvent();
	   		possibleRespEvt256.setId("256");
	   		possibleRespEvt256.setDisplayText("Associations almost completely negative");
	   		possibleRespEvt256.setResponseValue("5");
	   		
	   		ArrayList possibleAnswersList25 = new ArrayList();
	   		possibleAnswersList25.add(possibleRespEvt251);	   	
	   		possibleAnswersList25.add(possibleRespEvt253);
	   		possibleAnswersList25.add(possibleRespEvt254);
	   		possibleAnswersList25.add(possibleRespEvt255);
	   		possibleAnswersList25.add(possibleRespEvt256);
	   		
	   		questionRespEvt25.setId("25");
	   		questionRespEvt25.setText("COMPANIONS");
	   		questionRespEvt25.setRowSequence(5);
	   		questionRespEvt25.setColumnSequence(1);
	   		questionRespEvt25.setRequired(true);
	   		questionRespEvt25.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt25.setResponseNewLine(true);	   		
	   		questionRespEvt25.setSelectedResponseId(null);
	   		questionRespEvt25.setSelectedResponseText(null);
	   		questionRespEvt25.setPossibleResponseEvents(possibleAnswersList25);
	   		
	   		/******* End Fifth Question ****************/
	   		
	   		
	   		
	   		/******* Start Six Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt26 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt260 = new CSCPossibleResponseEvent();
	   		possibleRespEvt260.setId("260");
	   		possibleRespEvt260.setDisplayText("Exceptionally well adjusted; accepts responsibility for actions");
	   		possibleRespEvt260.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt261 = new CSCPossibleResponseEvent();
	   		possibleRespEvt261.setId("261");
	   		possibleRespEvt261.setDisplayText("No symptoms of emotional instability; appropriate emotional responses");
	   		possibleRespEvt261.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt262 = new CSCPossibleResponseEvent();
	   		possibleRespEvt262.setId("262");
	   		possibleRespEvt262.setDisplayText("Symptoms limit but do not prohibit adequate functioning;e.g., excessive anxiety");
	   		possibleRespEvt262.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt263 = new CSCPossibleResponseEvent();
	   		possibleRespEvt263.setId("263");
	   		possibleRespEvt263.setDisplayText("Symptoms prohibit adequate functioning; e.g., lashes out or retreats into self");
	   		possibleRespEvt263.setResponseValue("3");
   		
	   		ArrayList possibleAnswersList26 = new ArrayList();
	   		possibleAnswersList26.add(possibleRespEvt260);
	   		possibleAnswersList26.add(possibleRespEvt261);
	   		possibleAnswersList26.add(possibleRespEvt262);
	   		possibleAnswersList26.add(possibleRespEvt263);
			
	  		
	   		questionRespEvt26.setId("26");
	   		questionRespEvt26.setText("EMOTIONAL STABILITY");
	   		questionRespEvt26.setRowSequence(6);
	   		questionRespEvt26.setColumnSequence(1);
	   		questionRespEvt26.setRequired(true);
	   		questionRespEvt26.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt26.setResponseNewLine(true);
	   		questionRespEvt26.setEachResponseNewLine(true);
	   		questionRespEvt26.setSelectedResponseId(null);
	   		questionRespEvt26.setSelectedResponseText(null);
	   		questionRespEvt26.setPossibleResponseEvents(possibleAnswersList26);
	   		
	   		/******* End Six Question ****************/
	   		
	   		
	   		
	   		
	   		/******* Start 7 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt27 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt270 = new CSCPossibleResponseEvent();
	   		possibleRespEvt270.setId("270");
	   		possibleRespEvt270.setDisplayText("No use; use with no abuse; no disruption of functioning");
	   		possibleRespEvt270.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt271 = new CSCPossibleResponseEvent();
	   		possibleRespEvt271.setId("271");
	   		possibleRespEvt271.setDisplayText("Occasional abuse; some disruption of functioning");
	   		possibleRespEvt271.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt272 = new CSCPossibleResponseEvent();
	   		possibleRespEvt272.setId("272");
	   		possibleRespEvt272.setDisplayText("Frequent abuse; serious disruption of functioning");
	   		possibleRespEvt272.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList27 = new ArrayList();
	   		possibleAnswersList27.add(possibleRespEvt260);
	   		possibleAnswersList27.add(possibleRespEvt261);
	   		possibleAnswersList27.add(possibleRespEvt262);
	   			  		
	   		questionRespEvt27.setId("27");
	   		questionRespEvt27.setText("ALCOHOL USAGE PROBLEMS");
	   		questionRespEvt27.setRowSequence(7);
	   		questionRespEvt27.setColumnSequence(1);
	   		questionRespEvt27.setRequired(true);
	   		questionRespEvt27.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt27.setResponseNewLine(true);
	   		questionRespEvt27.setEachResponseNewLine(true);
	   		questionRespEvt27.setSelectedResponseId(null);
	   		questionRespEvt27.setSelectedResponseText(null);
	   		questionRespEvt27.setPossibleResponseEvents(possibleAnswersList27);
	   		
	   		/******* End 7 Question ****************/
	   		
	   		
	   		
	   		/******* Start 8 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt28 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt280 = new CSCPossibleResponseEvent();
	   		possibleRespEvt280.setId("280");
	   		possibleRespEvt280.setDisplayText("Please Select");
	   		possibleRespEvt280.setResponseValue("");
	   		possibleRespEvt280.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt281 = new CSCPossibleResponseEvent();
	   		possibleRespEvt281.setId("281");
	   		possibleRespEvt281.setDisplayText("No disruption of functioning");
	   		possibleRespEvt281.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt282 = new CSCPossibleResponseEvent();
	   		possibleRespEvt282.setId("282");
	   		possibleRespEvt282.setDisplayText("Occasional abuse; some disruption of functioning");
	   		possibleRespEvt282.setResponseValue("1");	   		
	   		
	   		CSCPossibleResponseEvent possibleRespEvt283 = new CSCPossibleResponseEvent();
	   		possibleRespEvt283.setId("283");
	   		possibleRespEvt283.setDisplayText("Frequent abuse;  Serious disruption of functioning");
	   		possibleRespEvt283.setResponseValue("2");	   		
	   		
	   		ArrayList possibleAnswersList28 = new ArrayList();
	   		possibleAnswersList28.add(possibleRespEvt280);
	   		possibleAnswersList28.add(possibleRespEvt281);
	   		possibleAnswersList28.add(possibleRespEvt282);		
	   		possibleAnswersList28.add(possibleRespEvt283);	
	   		
	   		questionRespEvt28.setId("28");
	   		questionRespEvt28.setText("OTHER DRUG USAGE PROBLEMS");
	   		questionRespEvt28.setRowSequence(8);
	   		questionRespEvt28.setColumnSequence(1);
	   		questionRespEvt28.setRequired(true);
	   		questionRespEvt28.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt28.setResponseNewLine(true);
	   		questionRespEvt28.setSelectedResponseId(null);
	   		questionRespEvt28.setSelectedResponseText(null);
	   		questionRespEvt28.setPossibleResponseEvents(possibleAnswersList28);
	   		
	   		/******* End 8 Question ****************/
	   		
	   		
	   		/******* Start 9 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt29 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt290 = new CSCPossibleResponseEvent();
	   		possibleRespEvt290.setId("290");
	   		possibleRespEvt290.setDisplayText("Able to function independently");
	   		possibleRespEvt290.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt291 = new CSCPossibleResponseEvent();
	   		possibleRespEvt291.setId("291");
	   		possibleRespEvt291.setDisplayText("Some need for assistance; potential for adequate adjustment; possible retardation");
	   		possibleRespEvt291.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt292 = new CSCPossibleResponseEvent();
	   		possibleRespEvt292.setId("292");
	   		possibleRespEvt292.setDisplayText("Deficiencies severely limit independent functioning; possible retardation");
	   		possibleRespEvt292.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList29 = new ArrayList();
	   		possibleAnswersList29.add(possibleRespEvt290);
	   		possibleAnswersList29.add(possibleRespEvt291);
	   		possibleAnswersList29.add(possibleRespEvt292);
	   			  		
	   		questionRespEvt29.setId("29");
	   		questionRespEvt29.setText("MENTAL ABILITY");
	   		questionRespEvt29.setRowSequence(9);
	   		questionRespEvt29.setColumnSequence(1);
	   		questionRespEvt29.setRequired(true);
	   		questionRespEvt29.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt29.setResponseNewLine(true);
	   		questionRespEvt29.setEachResponseNewLine(true);
	   		questionRespEvt29.setSelectedResponseId(null);
	   		questionRespEvt29.setSelectedResponseText(null);
	   		questionRespEvt29.setPossibleResponseEvents(possibleAnswersList29);
	   		
	   		/******* End 9 Question ****************/
	   		
	   		
	   		/******* Start 10 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt210 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2100 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2100.setId("2100");
	   		possibleRespEvt2100.setDisplayText("Sound physical health; seldom ill");
	   		possibleRespEvt2100.setResponseValue("0");
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt2101 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2101.setId("2101");
	   		possibleRespEvt2101.setDisplayText("Handicap or illness interferes with functioning on a recurring basis");
	   		possibleRespEvt2101.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2102 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2102.setId("2102");
	   		possibleRespEvt2102.setDisplayText("Deficiencies severely limit independent functioning; possible retardation");
	   		possibleRespEvt2102.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList210 = new ArrayList();
	   		possibleAnswersList210.add(possibleRespEvt2100);
	   		possibleAnswersList210.add(possibleRespEvt2101);
	   		possibleAnswersList210.add(possibleRespEvt2102);
	   			  		
	   		questionRespEvt210.setId("210");
	   		questionRespEvt210.setText("HEALTH");
	   		questionRespEvt210.setRowSequence(10);
	   		questionRespEvt210.setColumnSequence(1);
	   		questionRespEvt210.setRequired(true);
	   		questionRespEvt210.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt210.setResponseNewLine(true);
	   		questionRespEvt210.setEachResponseNewLine(true);
	   		questionRespEvt210.setSelectedResponseId(null);
	   		questionRespEvt210.setSelectedResponseText(null);
	   		questionRespEvt210.setPossibleResponseEvents(possibleAnswersList210);
	   		
	   		/******* End 10 Question ****************/
	   		
	   		
	   		/******* Start 11 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt211 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2110 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2110.setId("2110");
	   		possibleRespEvt2110.setDisplayText("Please Select");
	   		possibleRespEvt2110.setResponseValue("");
	   		possibleRespEvt2110.setDefault(true);
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt2111 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2111.setId("2111");
	   		possibleRespEvt2111.setDisplayText("No apparent dysfunction");
	   		possibleRespEvt2111.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2112 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2112.setId("2112");
	   		possibleRespEvt2112.setDisplayText("Real or perceived situational or minor problems");
	   		possibleRespEvt2112.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2113 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2113.setId("2113");
	   		possibleRespEvt2113.setDisplayText("Real or perceived chronic or severe problems");
	   		possibleRespEvt2113.setResponseValue("3");
	   		
	   		ArrayList possibleAnswersList211 = new ArrayList();
	   		possibleAnswersList211.add(possibleRespEvt2110);
	   		possibleAnswersList211.add(possibleRespEvt2111);
	   		possibleAnswersList211.add(possibleRespEvt2112);
	   		possibleAnswersList211.add(possibleRespEvt2113);
	   		
	   		questionRespEvt211.setId("211");
	   		questionRespEvt211.setText("SEXUAL BEHAVIOR");
	   		questionRespEvt211.setRowSequence(11);
	   		questionRespEvt211.setColumnSequence(1);
	   		questionRespEvt211.setRequired(true);
	   		questionRespEvt211.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt211.setResponseNewLine(true);	   		
	   		questionRespEvt211.setSelectedResponseId(null);
	   		questionRespEvt211.setSelectedResponseText(null);
	   		questionRespEvt211.setPossibleResponseEvents(possibleAnswersList211);
	   		
	   		/******* End 11 Question ****************/
	   		
	   		
	   		/******* Start 12 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt212 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2120 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2120.setId("2120");
	   		possibleRespEvt2120.setDisplayText("Please Select");
	   		possibleRespEvt2120.setResponseValue("");
	   		possibleRespEvt2120.setDefault(true);
	   			   		
	   		CSCPossibleResponseEvent possibleRespEvt2121 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2121.setId("2121");
	   		possibleRespEvt2121.setDisplayText("Well Adjusted");
	   		possibleRespEvt2121.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2122 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2122.setId("2122");
	   		possibleRespEvt2122.setDisplayText("No Needs");
	   		possibleRespEvt2122.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2123 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2123.setId("2123");
	   		possibleRespEvt2123.setDisplayText("Moderate Needs");
	   		possibleRespEvt2123.setResponseValue("3");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt2124 = new CSCPossibleResponseEvent();
	   		possibleRespEvt2124.setId("2124");
	   		possibleRespEvt2124.setDisplayText("High Needs");
	   		possibleRespEvt2124.setResponseValue("4");
	   		
	   		ArrayList possibleAnswersList212 = new ArrayList();
	   		possibleAnswersList212.add(possibleRespEvt2120);
	   		possibleAnswersList212.add(possibleRespEvt2121);
	   		possibleAnswersList212.add(possibleRespEvt2122);
	   		possibleAnswersList212.add(possibleRespEvt2123);
	   		possibleAnswersList212.add(possibleRespEvt2124);
	   			  		
	   		questionRespEvt212.setId("212");
	   		questionRespEvt212.setText("C.S.O.'S IMPRESSIONS OF DEFENDANT'S NEEDS");
	   		questionRespEvt212.setRowSequence(12);
	   		questionRespEvt212.setColumnSequence(1);
	   		questionRespEvt212.setRequired(true);
	   		questionRespEvt212.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt212.setResponseNewLine(false);
	   		questionRespEvt212.setSelectedResponseId(null);
	   		questionRespEvt212.setSelectedResponseText(null);
	   		questionRespEvt212.setPossibleResponseEvents(possibleAnswersList212);
	   		
	   		/******* End 12 Question ****************/
	   		
	   		
	   		ArrayList questionRespList2 = new ArrayList();
	   		
	   		questionRespList2.add(questionRespEvt21);
	   		questionRespList2.add(questionRespEvt22);
	   		questionRespList2.add(questionRespEvt23);
	   		questionRespList2.add(questionRespEvt24);
	   		questionRespList2.add(questionRespEvt25);
	   		questionRespList2.add(questionRespEvt26);
	   		questionRespList2.add(questionRespEvt27);
	   		questionRespList2.add(questionRespEvt28);
	   		questionRespList2.add(questionRespEvt29);
	   		questionRespList2.add(questionRespEvt210);
	   		questionRespList2.add(questionRespEvt211);
	   		questionRespList2.add(questionRespEvt212);
			
	   		questionGrpRespEvt2.setId(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITIAL_NEEDS_QUEST_GRP_ID);
	   		questionGrpRespEvt2.setDisplayText("Needs Assessment");
	   		questionGrpRespEvt2.setDisplayTextDetailHeader(false);	   		
	   		questionGrpRespEvt2.setSequence(2);
	   		questionGrpRespEvt2.setQuestionResponseEvents(questionRespList2);
	   		
	   		/******************************************* Question Group 2 (Needs) End  ********************************/
	   		
	   		ArrayList questionGrpList = new ArrayList();
	   		questionGrpList.add(questionGrpRespEvt1);
	   		questionGrpList.add(questionGrpRespEvt2);
	   		
	   		return questionGrpList;
	   }
	
	
	
	
	 public static Collection fakeNewWisconsinRiskReassessCreateExecute()
	   {
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt1 = new CSCQuestionGroupResponseEvent();
	   		
	   		/******* Start First Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt11 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt110 = new CSCPossibleResponseEvent();
	   		possibleRespEvt110.setId("110");
	   		possibleRespEvt110.setDisplayText("Please Select");
	   		possibleRespEvt110.setResponseValue("");
	   		possibleRespEvt110.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt111 = new CSCPossibleResponseEvent();
	   		possibleRespEvt111.setId("111");
	   		possibleRespEvt111.setDisplayText("None");
	   		possibleRespEvt111.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt112 = new CSCPossibleResponseEvent();
	   		possibleRespEvt112.setId("112");
	   		possibleRespEvt112.setDisplayText("One");
	   		possibleRespEvt112.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt113 = new CSCPossibleResponseEvent();
	   		possibleRespEvt113.setId("113");
	   		possibleRespEvt113.setDisplayText("Two or More");
	   		possibleRespEvt113.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList11 = new ArrayList();   		
	   		possibleAnswersList11.add(possibleRespEvt110);
	   		possibleAnswersList11.add(possibleRespEvt111);
	   		possibleAnswersList11.add(possibleRespEvt112);
	   		possibleAnswersList11.add(possibleRespEvt113);
	   		
			
	  		
	   		questionRespEvt11.setId("11");
	   		questionRespEvt11.setText("Number of Address changes in the Last 12 months");
	   		questionRespEvt11.setRowSequence(1);
	   		questionRespEvt11.setColumnSequence(1);
	   		questionRespEvt11.setRequired(true);
	   		questionRespEvt11.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt11.setResponseNewLine(false);
	   		questionRespEvt11.setSelectedResponseId(null);
	   		questionRespEvt11.setSelectedResponseText(null);
	   		questionRespEvt11.setPossibleResponseEvents(possibleAnswersList11);
	   		
	   		/******* End First Question ****************/
	   		
	   		
	   		
	   		/******* Start Second Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt12 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt120 = new CSCPossibleResponseEvent();
	   		possibleRespEvt120.setId("120");
	   		possibleRespEvt120.setDisplayText("Please Select");
	   		possibleRespEvt120.setResponseValue("");
	   		possibleRespEvt120.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt121 = new CSCPossibleResponseEvent();
	   		possibleRespEvt121.setId("121");
	   		possibleRespEvt121.setDisplayText("24 or older");
	   		possibleRespEvt121.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt122 = new CSCPossibleResponseEvent();
	   		possibleRespEvt122.setId("122");
	   		possibleRespEvt122.setDisplayText("20-23");
	   		possibleRespEvt122.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt123 = new CSCPossibleResponseEvent();
	   		possibleRespEvt123.setId("123");
	   		possibleRespEvt123.setDisplayText("19 or younger");
	   		possibleRespEvt123.setResponseValue("2");
	   		
	   		ArrayList possibleAnswersList12 = new ArrayList();
	   		possibleAnswersList12.add(possibleRespEvt120);
	   		possibleAnswersList12.add(possibleRespEvt121);
	   		possibleAnswersList12.add(possibleRespEvt122);
	   		possibleAnswersList12.add(possibleRespEvt123);
			
	  		
	   		questionRespEvt12.setId("12");
	   		questionRespEvt12.setText("Age at First Adjudication of Guilt <br> (Adult or Juvenile - include deferred)");
	   		questionRespEvt12.setRowSequence(2);
	   		questionRespEvt12.setColumnSequence(1);
	   		questionRespEvt12.setRequired(true);
	   		questionRespEvt12.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt12.setResponseNewLine(false);
	   		questionRespEvt12.setSelectedResponseId(null);
	   		questionRespEvt12.setSelectedResponseText(null);
	   		questionRespEvt12.setPossibleResponseEvents(possibleAnswersList12);
	   		
	   		/******* End Second Question ****************/
	   		
	   		
	   		/******* Start Third Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt13 = new CSCQuestionResponseEvent();
	   		

	   		CSCPossibleResponseEvent possibleRespEvt130 = new CSCPossibleResponseEvent();
	   		possibleRespEvt130.setId("130");
	   		possibleRespEvt130.setDisplayText("Please Select");
	   		possibleRespEvt130.setResponseValue("");
	   		possibleRespEvt130.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt131 = new CSCPossibleResponseEvent();
	   		possibleRespEvt131.setId("131");
	   		possibleRespEvt131.setDisplayText("None");
	   		possibleRespEvt131.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt132 = new CSCPossibleResponseEvent();
	   		possibleRespEvt132.setId("132");
	   		possibleRespEvt132.setDisplayText("One or More");
	   		possibleRespEvt132.setResponseValue("1");
	   		
	   		
	   		ArrayList possibleAnswersList13 = new ArrayList();
	   		possibleAnswersList13.add(possibleRespEvt130);
	   		possibleAnswersList13.add(possibleRespEvt131);
	   		possibleAnswersList13.add(possibleRespEvt132);		
	  		
	   		questionRespEvt13.setId("13");
	   		questionRespEvt13.setText("Number of Prior Probation/Parole Revocations (Adult or Juvenile)");
	   		questionRespEvt13.setRowSequence(3);
	   		questionRespEvt13.setColumnSequence(1);
	   		questionRespEvt13.setRequired(true);
	   		questionRespEvt13.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt13.setResponseNewLine(false);
	   		questionRespEvt13.setSelectedResponseId(null);
	   		questionRespEvt13.setSelectedResponseText(null);
	   		questionRespEvt13.setPossibleResponseEvents(possibleAnswersList13);
	   		
	   		/******* End Third Question ****************/
	   		
	   		
	   		/******* Start Fourth Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt14 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt140 = new CSCPossibleResponseEvent();
	   		possibleRespEvt140.setId("140");
	   		possibleRespEvt140.setDisplayText("Please Select");
	   		possibleRespEvt140.setResponseValue("");
	   		possibleRespEvt140.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt141 = new CSCPossibleResponseEvent();
	   		possibleRespEvt141.setId("141");
	   		possibleRespEvt141.setDisplayText("None");
	   		possibleRespEvt141.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt142 = new CSCPossibleResponseEvent();
	   		possibleRespEvt142.setId("142");
	   		possibleRespEvt142.setDisplayText("One");
	   		possibleRespEvt142.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt143 = new CSCPossibleResponseEvent();
	   		possibleRespEvt143.setId("143");
	   		possibleRespEvt143.setDisplayText("Two or more");
	   		possibleRespEvt143.setResponseValue("2");
	   		
	   		
	   		ArrayList possibleAnswersList14 = new ArrayList();
	   		possibleAnswersList14.add(possibleRespEvt140);
	   		possibleAnswersList14.add(possibleRespEvt141);
	   		possibleAnswersList14.add(possibleRespEvt142);		
	   		possibleAnswersList14.add(possibleRespEvt143);	
	  		
	   		questionRespEvt14.setId("14");
	   		questionRespEvt14.setText("Number of Prior Felony Adjudications of Guilt (or Juvenile Commitments - include deferred)");
	   		questionRespEvt14.setRowSequence(4);
	   		questionRespEvt14.setColumnSequence(1);
	   		questionRespEvt14.setRequired(true);
	   		questionRespEvt14.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt14.setResponseNewLine(false);
	   		questionRespEvt14.setSelectedResponseId(null);
	   		questionRespEvt14.setSelectedResponseText(null);
	   		questionRespEvt14.setPossibleResponseEvents(possibleAnswersList13);
	   		
	   		/******* End Fourth Question ****************/
	   		
	   		
	   		
	   		/******* Start Fifth Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt15 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt151 = new CSCPossibleResponseEvent();
	   		possibleRespEvt151.setId("151");
	   		possibleRespEvt151.setDisplayText("Yes");
	   		possibleRespEvt151.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt152 = new CSCPossibleResponseEvent();
	   		possibleRespEvt152.setId("152");
	   		possibleRespEvt152.setDisplayText("No");
	   		possibleRespEvt152.setResponseValue("1");
	   		
	   		
	   		ArrayList possibleAnswersList15 = new ArrayList();
	   		possibleAnswersList15.add(possibleRespEvt151);
	   		possibleAnswersList15.add(possibleRespEvt152);		
	  		
	   		questionRespEvt15.setId("15");
	   		questionRespEvt15.setText("Adult or Juvenile Adjudication for burglary, theft,auto theft, or robbery");
	   		questionRespEvt15.setRowSequence(5);
	   		questionRespEvt15.setColumnSequence(1);
	   		questionRespEvt15.setRequired(true);
	   		questionRespEvt15.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt15.setResponseNewLine(false);
	   		questionRespEvt15.setSelectedResponseId(null);
	   		questionRespEvt15.setSelectedResponseText(null);
	   		questionRespEvt15.setPossibleResponseEvents(possibleAnswersList15);
	   		
	   		/******* End Fifth Question ****************/
	   		
	   		
	   		
	   		/******* Start Six Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt16 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt161 = new CSCPossibleResponseEvent();
	   		possibleRespEvt161.setId("161");
	   		possibleRespEvt161.setDisplayText("Yes");
	   		possibleRespEvt161.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt162 = new CSCPossibleResponseEvent();
	   		possibleRespEvt162.setId("162");
	   		possibleRespEvt162.setDisplayText("No");
	   		possibleRespEvt162.setResponseValue("1");
	   		
	   		
	   		ArrayList possibleAnswersList16 = new ArrayList();
	   		possibleAnswersList16.add(possibleRespEvt161);
	   		possibleAnswersList16.add(possibleRespEvt162);		
	  		
	   		questionRespEvt16.setId("16");
	   		questionRespEvt16.setText("Adult or Juvenile Adjudication for wothless checks or forgery");
	   		questionRespEvt16.setRowSequence(6);
	   		questionRespEvt16.setColumnSequence(1);
	   		questionRespEvt16.setRequired(true);
	   		questionRespEvt16.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
	   		questionRespEvt16.setResponseNewLine(false);
	   		questionRespEvt16.setSelectedResponseId(null);
	   		questionRespEvt16.setSelectedResponseText(null);
	   		questionRespEvt16.setPossibleResponseEvents(possibleAnswersList16);
	   		
	   		/******* End Six Question ****************/
	   		
	   		
	   		
	   		
	   		/******* Start 7 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt17 = new CSCQuestionResponseEvent();
	   		
	   		questionRespEvt17.setId("17");
	   		questionRespEvt17.setText("RATE THE FOLLOWING BASED ON PERIOD SINCE LAST CLASSIFICATION:");
	   		questionRespEvt17.setRowSequence(7);
	   		questionRespEvt17.setColumnSequence(1);
	   		questionRespEvt17.setRequired(false);
	   		questionRespEvt17.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_HIDDEN);
	   		questionRespEvt17.setQuestionAlignment("center");
	   		questionRespEvt17.setResponseNewLine(false);
	   		questionRespEvt17.setSelectedResponseId(null);
	   		questionRespEvt17.setSelectedResponseText(null);
	   		questionRespEvt17.setPossibleResponseEvents(null);
	   		
	   		/******* End 7 Question ****************/
	   		
	   		
	   		
	   		/******* Start 8 Question ******************/
	   		
	   		CSCQuestionResponseEvent questionRespEvt18 = new CSCQuestionResponseEvent();
	   		
	   		CSCPossibleResponseEvent possibleRespEvt180 = new CSCPossibleResponseEvent();
	   		possibleRespEvt180.setId("180");
	   		possibleRespEvt180.setDisplayText("Please Select");
	   		possibleRespEvt180.setResponseValue("");
	   		possibleRespEvt180.setDefault(true);
	   		
	   		CSCPossibleResponseEvent possibleRespEvt181 = new CSCPossibleResponseEvent();
	   		possibleRespEvt181.setId("181");
	   		possibleRespEvt181.setDisplayText("60% or more");
	   		possibleRespEvt181.setResponseValue("0");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt182 = new CSCPossibleResponseEvent();
	   		possibleRespEvt182.setId("182");
	   		possibleRespEvt182.setDisplayText("40%-59%");
	   		possibleRespEvt182.setResponseValue("1");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt183 = new CSCPossibleResponseEvent();
	   		possibleRespEvt183.setId("183");
	   		possibleRespEvt183.setDisplayText("Under 40%");
	   		possibleRespEvt183.setResponseValue("2");
	   		
	   		CSCPossibleResponseEvent possibleRespEvt184 = new CSCPossibleResponseEvent();
	   		possibleRespEvt184.setId("184");
	   		possibleRespEvt184.setDisplayText("Not Applicable");
	   		possibleRespEvt184.setResponseValue("3");
	   		
	   		
	   		ArrayList possibleAnswersList18 = new ArrayList();
	   		possibleAnswersList18.add(possibleRespEvt180);
	   		possibleAnswersList18.add(possibleRespEvt181);
	   		possibleAnswersList18.add(possibleRespEvt182);		
	   		possibleAnswersList18.add(possibleRespEvt183);		
	   		possibleAnswersList18.add(possibleRespEvt184);		
	   		
	   		questionRespEvt18.setId("18");
	   		questionRespEvt18.setText("Percentage of Time Employed in the Last 12 months");
	   		questionRespEvt18.setRowSequence(8);
	   		questionRespEvt18.setColumnSequence(1);
	   		questionRespEvt18.setRequired(true);
	   		questionRespEvt18.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   		questionRespEvt18.setResponseNewLine(false);
	   		questionRespEvt18.setSelectedResponseId(null);
	   		questionRespEvt18.setSelectedResponseText(null);
	   		questionRespEvt18.setPossibleResponseEvents(possibleAnswersList18);
	   		
	   		/******* End 8 Question ****************/
	   		
	   		
	   		
	   		ArrayList questionRespList1 = new ArrayList();
	   		questionRespList1.add(questionRespEvt11);
	   		questionRespList1.add(questionRespEvt12);
	   		questionRespList1.add(questionRespEvt13);
	   		questionRespList1.add(questionRespEvt14);
	   		questionRespList1.add(questionRespEvt15);
	   		questionRespList1.add(questionRespEvt16);
	   		questionRespList1.add(questionRespEvt17);
	   		questionRespList1.add(questionRespEvt18);
			
	   		questionGrpRespEvt1.setId(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITAIL_RISK_QUEST_GRP_ID);
	   		questionGrpRespEvt1.setDisplayText("Risk Assessment");
	   		questionGrpRespEvt1.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt1.setDisplayTextAlign("left");
	   		questionGrpRespEvt1.setSequence(1);
	   		questionGrpRespEvt1.setQuestionResponseEvents(questionRespList1);
	   		
	        
	   		ArrayList questionGrpList = new ArrayList();
	   		questionGrpList.add(questionGrpRespEvt1);
	   		
	   		return questionGrpList;
	   }
	 	
	 
	 
	 
	 /**
	  * SCS ASSESSMENT 
	  * @return
	  */
	 public static Collection fakeNewSCSCreateExecute()
	   {
	 		/********************** Start Question Group 1 *************************/
	 	
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt1 = new CSCQuestionGroupResponseEvent();
	   		
	   		ArrayList questionRespList = new ArrayList();
  		
	   		questionGrpRespEvt1.setId(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE_QUEST_GRP_ID);
	   		questionGrpRespEvt1.setDisplayText("SCS Score Entry");
	   		questionGrpRespEvt1.setDisplayTextDetailHeader(false);
	   		questionGrpRespEvt1.setDisplayTextAlign(null);
	   		questionGrpRespEvt1.setSequence(1);
	   		questionGrpRespEvt1.setQuestionResponseEvents(questionRespList);
	   		
	   		/******* Start 1 - 10 Question ******************/
	   		
	   		int rowCounter = 1;
	   		int summaryRowCounter = 1;
	   		
	   		for(int i=1; i <= 10; i++)
	   		{	
	   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
	   			ArrayList possibleAnswersList = new ArrayList();   	
	   			
	   			char ch = 65;
	   			
	   			for(int j=0; j <=4; j++)
	   			{
	   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
	   				
	   				if(j == 0)
	   				{
	   					possibleRespEvt.setId("0");
		   				possibleRespEvt.setDisplayText("Please Select");
		   				possibleRespEvt.setResponseValue("");
		   				possibleRespEvt.setDefault(true);
		   				
		   				possibleAnswersList.add(possibleRespEvt);
	   				}
	   				else
	   				{		   				
		   				String ans= String.valueOf(ch);
		   				
		   				possibleRespEvt.setId(ans+j);
		   				possibleRespEvt.setDisplayText(ans);
		   				possibleRespEvt.setResponseValue(ans+j); 				
		   				possibleRespEvt.setDefault(false);
		   				
		   				possibleAnswersList.add(possibleRespEvt);
		   				ch++;
	   				}
	   			}
	   			
	   			questionRespEvt.setId(Integer.toString(i));
	   			questionRespEvt.setText(Integer.toString(i));
	   			questionRespEvt.setRowSequence(rowCounter++);
	   			questionRespEvt.setColumnSequence(1);
	   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
	   			questionRespEvt.setSummaryColSeq(1);
	   			questionRespEvt.setRequired(false);
	   			questionRespEvt.setColumnWidth("1%");
	   			questionRespEvt.setSummaryRespColumnWidth("24%");
	   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   			questionRespEvt.setResponseNewLine(false);
	   			questionRespEvt.setSelectedResponseId(null);
	   			questionRespEvt.setSelectedResponseText(null);
	   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
	   			
	   			
		   		questionRespList.add(questionRespEvt);
	   		}
	   		
	   		/******* End 1 - 10 Question ******************/
	   		
	   		
	   		
	   		/******* Start 11 - 20 Question ******************/
	   		
	   		rowCounter = 1;
	   		
	   		for(int i=11; i <= 20; i++)
	   		{	
	   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
	   			ArrayList possibleAnswersList = new ArrayList();   	
	   			
	   			char ch = 65;
	   			
	   			for(int j=0; j <=4; j++)
	   			{
	   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
	   				
	   				if(j == 0)
	   				{
	   					possibleRespEvt.setId("0");
		   				possibleRespEvt.setDisplayText("Please Select");
		   				possibleRespEvt.setResponseValue("");
		   				possibleRespEvt.setDefault(true);
		   				
		   				possibleAnswersList.add(possibleRespEvt);
	   				}
	   				else
	   				{		   				
		   				String ans= String.valueOf(ch);
		   				
		   				possibleRespEvt.setId(ans+j);
		   				possibleRespEvt.setDisplayText(ans);
		   				possibleRespEvt.setResponseValue(ans+j); 				
		   				possibleRespEvt.setDefault(false);
		   				
		   				possibleAnswersList.add(possibleRespEvt);
		   				ch++;
	   				}
	   			}
	   			
	   			questionRespEvt.setId(Integer.toString(i));
	   			questionRespEvt.setText(Integer.toString(i));
	   			questionRespEvt.setRowSequence(rowCounter++);
	   			questionRespEvt.setColumnSequence(2);
	   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
	   			questionRespEvt.setSummaryColSeq(1);
	   			questionRespEvt.setRequired(false);
	   			questionRespEvt.setColumnWidth("1%");
	   			questionRespEvt.setSummaryRespColumnWidth("24%");
	   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
	   			questionRespEvt.setResponseNewLine(false);
	   			questionRespEvt.setSelectedResponseId(null);
	   			questionRespEvt.setSelectedResponseText(null);
	   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
	   			
	   			
		   		questionRespList.add(questionRespEvt);
	   		}
	   		
	   		/******* End 11 - 20 Question ******************/
	   		
	   		/********************** End Question Group 1 *************************/
	   		
	   		
	   		/********************** Start Question Group 2 *************************/
		 	
		   		CSCQuestionGroupResponseEvent questionGrpRespEvt2 = new CSCQuestionGroupResponseEvent();
		   		
		   		ArrayList questionRespList2 = new ArrayList();
		   		
		   		questionGrpRespEvt2.setId(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO_QUEST_GRP_ID);
		   		questionGrpRespEvt2.setDisplayText("SCS Score Entry");
		   		questionGrpRespEvt2.setDisplayTextDetailHeader(false);
		   		questionGrpRespEvt2.setDisplayTextAlign(null);
		   		questionGrpRespEvt2.setSequence(2);
		   		questionGrpRespEvt2.setQuestionResponseEvents(questionRespList2);
		   	
		   		rowCounter = 1;
		   		summaryRowCounter = 1;
				
		   		/******* Start 21 - 30 Question ******************/
		   		
		   		for(int i=21; i <= 30; i++)
		   		{	
		   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j); 				
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(1);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(2);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList2.add(questionRespEvt);
		   		}
		   		
		   		/******* End 21 - 30 Question ******************/
		   		
		   		
		   		
		   		/******* Start 31 - 40 Question ******************/
		   		
		   		rowCounter = 1;
		   		
		   		for(int i=31; i <= 40; i++)
		   		{	
		   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j); 				
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(2);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(2);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList2.add(questionRespEvt);
		   		}
		   		
		   		/******* End 31 - 40 Question ******************/
		   		
		   		/********************** End Question Group 2 *************************/
	   		
	   		
	   		
		   		/**************************** Start Question Group 3 ********************/
		   		
		   		CSCQuestionGroupResponseEvent questionGrpRespEvt3 = new CSCQuestionGroupResponseEvent();
		   		
		   		ArrayList questionRespList3 = new ArrayList();
		   		
		   		questionGrpRespEvt3.setId(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE_QUEST_GRP_ID);
		   		questionGrpRespEvt3.setDisplayText("SCS Score Entry");
		   		questionGrpRespEvt3.setDisplayTextDetailHeader(false);
		   		questionGrpRespEvt3.setDisplayTextAlign(null);
		   		questionGrpRespEvt3.setSequence(3);
		   		questionGrpRespEvt3.setQuestionResponseEvents(questionRespList3);
		   	
		   		rowCounter = 1;
		   		summaryRowCounter = 1;
				
		   		/******* Start 41 - 49 Question ******************/
		   		
		   		for(int i=41; i <= 49; i++)
		   		{	
		   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j); 				
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(1);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(3);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList3.add(questionRespEvt);
		   		}
		   		
		   		/******* End 41 - 49 Question ******************/
		   		
		   	
		
		/******* Start 50 Question ******************/
   		
   			CSCQuestionResponseEvent questionRespEvt50 = new CSCQuestionResponseEvent();
   			ArrayList possibleAnswersList50 = new ArrayList();   	
   			
   			
   				CSCPossibleResponseEvent possibleRespEvt501 = new CSCPossibleResponseEvent();
   				
   				possibleRespEvt501.setId("2");
   				possibleRespEvt501.setDisplayText("A");
   				possibleRespEvt501.setResponseValue("2");
   				possibleRespEvt501.setDefault(false);
	   				
   				possibleAnswersList50.add(possibleRespEvt501);
   				
   			
   				questionRespEvt50.setId("50A");
   				questionRespEvt50.setText("50(A)");
   				questionRespEvt50.setRowSequence(10);
   				questionRespEvt50.setColumnSequence(1);
   				questionRespEvt50.setSummaryRowSeq(10);
   				questionRespEvt50.setSummaryColSeq(3);
   				questionRespEvt50.setRequired(false);
   				questionRespEvt50.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX);
   				questionRespEvt50.setColumnWidth("1%");
   				questionRespEvt50.setSummaryRespColumnWidth("24%");
   				questionRespEvt50.setResponseNewLine(false);
   				questionRespEvt50.setSelectedResponseId(null);
   				questionRespEvt50.setSelectedResponseText(null);
   				questionRespEvt50.setPossibleResponseEvents(possibleAnswersList50);
   			
   			
	   		questionRespList3.add(questionRespEvt50);
   		
   		/******* End 50 Question ******************/
		
		
		
		
		
		   		/******* Start 50 Question ******************/
		   		
		   		/**
		   		CSCQuestionResponseEvent questionRespEvt50 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt500 = new CSCPossibleResponseEvent();
		   		possibleRespEvt500.setId("500");
		   		possibleRespEvt500.setDisplayText("A");
		   		possibleRespEvt500.setResponseValue("1");
		   		possibleRespEvt500.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt502 = new CSCPossibleResponseEvent();
		   		possibleRespEvt502.setId("502");
		   		possibleRespEvt502.setDisplayText("B");
		   		possibleRespEvt502.setResponseValue("2");
		   		possibleRespEvt502.setDefault(false);	   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt503 = new CSCPossibleResponseEvent();
		   		possibleRespEvt503.setId("503");
		   		possibleRespEvt503.setDisplayText("C");
		   		possibleRespEvt503.setResponseValue("3");
		   		possibleRespEvt503.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt504 = new CSCPossibleResponseEvent();
		   		possibleRespEvt504.setId("504");
		   		possibleRespEvt504.setDisplayText("D");
		   		possibleRespEvt504.setResponseValue("4");
		   		possibleRespEvt504.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt505 = new CSCPossibleResponseEvent();
		   		possibleRespEvt505.setId("505");
		   		possibleRespEvt505.setDisplayText("E");
		   		possibleRespEvt505.setResponseValue("5");
		   		possibleRespEvt505.setDefault(false);
		   		
		   		ArrayList possibleAnswersList50 = new ArrayList();   		
		   		possibleAnswersList50.add(possibleRespEvt500);
		   		possibleAnswersList50.add(possibleRespEvt502);
		   		possibleAnswersList50.add(possibleRespEvt503);
		   		possibleAnswersList50.add(possibleRespEvt504);
		   		possibleAnswersList50.add(possibleRespEvt505);
		   		
		   		
		   		questionRespEvt50.setId("50");
		   		questionRespEvt50.setText("50");
		   		questionRespEvt50.setRowSequence(10);
		   		questionRespEvt50.setColumnSequence(1);
		   		questionRespEvt50.setSummaryRowSeq(10);
		   		questionRespEvt50.setSummaryColSeq(3);
		   		questionRespEvt50.setRequired(false);
		   		questionRespEvt50.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX);
		   		questionRespEvt50.setColumnWidth("1%");
		   		questionRespEvt50.setSummaryRespColumnWidth("24%");
		   		questionRespEvt50.setResponseNewLine(false);
		   		questionRespEvt50.setSelectedResponseId(null);
		   		questionRespEvt50.setSelectedResponseText(null);
//		   		questionRespEvt50.setSelectedResponsesId(null);
//		   		questionRespEvt50.setSelectedResponsesStr(null);
		   		ArrayList selectedResponsesList = new ArrayList();
		   		selectedResponsesList.add("500");
		   		selectedResponsesList.add("502");
		   		questionRespEvt50.setSelectedResponses(selectedResponsesList);	   		
		   		questionRespEvt50.setPossibleResponseEvents(possibleAnswersList50);
		   		
		   		questionRespList3.add(questionRespEvt50);
		   		*/
		   		/*************** End 50 Question ******************/
		   		
		   		
		   		/******* Start 51 - 53 Question ******************/
		   		
		   		rowCounter = 1;
		   		summaryRowCounter=11;
		   		
		   		for(int i=51; i <= 53; i++)
		   		{	
	   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j);
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(2);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(3);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList3.add(questionRespEvt);
		   		}
		   		
		   		/******* End 51 - 53 Question ******************/
		   		
		   		
				/******* Start 54 Question ******************/
		   		
		   			CSCQuestionResponseEvent questionRespEvt54 = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList54 = new ArrayList();   	
		   			
		   			
		   				CSCPossibleResponseEvent possibleRespEvt541 = new CSCPossibleResponseEvent();
		   				
		   				possibleRespEvt541.setId("3");
		   				possibleRespEvt541.setDisplayText("B");
		   				possibleRespEvt541.setResponseValue("3");
		   				possibleRespEvt541.setDefault(true);
			   				
		   				possibleAnswersList54.add(possibleRespEvt541);
		   				
		   			
		   				questionRespEvt54.setId("54B");
		   				questionRespEvt54.setText("54(B)");
		   				questionRespEvt54.setRowSequence(4);
		   				questionRespEvt54.setColumnSequence(1);
		   				questionRespEvt54.setSummaryRowSeq(14);
		   				questionRespEvt54.setSummaryColSeq(3);
		   				questionRespEvt54.setRequired(false);
		   				questionRespEvt54.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX);
		   				questionRespEvt54.setColumnWidth("1%");
		   				questionRespEvt54.setSummaryRespColumnWidth("24%");
		   				questionRespEvt54.setResponseNewLine(false);
		   				questionRespEvt54.setSelectedResponseId(null);
		   				questionRespEvt54.setSelectedResponseText(null);
		   				questionRespEvt54.setPossibleResponseEvents(possibleAnswersList54);
		   			
		   			
			   		questionRespList3.add(questionRespEvt54);
		   		
		   		/******* End 54 Question ******************/
		   		
		   		
		   		
		   		
		   		
		   		
		   		
		   		/******* Start 54 Question ******************/
		   		/**
		   		CSCQuestionResponseEvent questionRespEvt54 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt540 = new CSCPossibleResponseEvent();
		   		possibleRespEvt540.setId("540");
		   		possibleRespEvt540.setDisplayText("A");
		   		possibleRespEvt540.setResponseValue("1");
		   		possibleRespEvt540.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt542 = new CSCPossibleResponseEvent();
		   		possibleRespEvt542.setId("542");
		   		possibleRespEvt542.setDisplayText("B");
		   		possibleRespEvt542.setResponseValue("2");
		   		possibleRespEvt542.setDefault(false);	   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt543 = new CSCPossibleResponseEvent();
		   		possibleRespEvt543.setId("543");
		   		possibleRespEvt543.setDisplayText("C");
		   		possibleRespEvt543.setResponseValue("3");
		   		possibleRespEvt543.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt544 = new CSCPossibleResponseEvent();
		   		possibleRespEvt544.setId("544");
		   		possibleRespEvt544.setDisplayText("D");
		   		possibleRespEvt544.setResponseValue("4");
		   		possibleRespEvt544.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt545 = new CSCPossibleResponseEvent();
		   		possibleRespEvt545.setId("545");
		   		possibleRespEvt545.setDisplayText("E");
		   		possibleRespEvt545.setResponseValue("5");
		   		possibleRespEvt545.setDefault(false);
		   		
		   		CSCPossibleResponseEvent possibleRespEvt546 = new CSCPossibleResponseEvent();
		   		possibleRespEvt546.setId("546");
		   		possibleRespEvt546.setDisplayText("F");
		   		possibleRespEvt546.setResponseValue("6");
		   		possibleRespEvt546.setDefault(false);
		   		
		   		ArrayList possibleAnswersList54 = new ArrayList();   		
		   		possibleAnswersList54.add(possibleRespEvt540);
		   		possibleAnswersList54.add(possibleRespEvt542);
		   		possibleAnswersList54.add(possibleRespEvt543);
		   		possibleAnswersList54.add(possibleRespEvt544);
		   		possibleAnswersList54.add(possibleRespEvt545);
		   		possibleAnswersList54.add(possibleRespEvt546);
		   		
		   		
		   		questionRespEvt54.setId("54");
		   		questionRespEvt54.setText("54");
		   		questionRespEvt54.setRowSequence(4);
		   		questionRespEvt54.setColumnSequence(2);
		   		questionRespEvt54.setSummaryRowSeq(14);
		   		questionRespEvt54.setSummaryColSeq(3);
		   		questionRespEvt54.setRequired(false);
		   		questionRespEvt54.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_CHECKBOX);
		   		questionRespEvt54.setColumnWidth("1%");
		   		questionRespEvt54.setSummaryRespColumnWidth("24%");
		   		questionRespEvt54.setResponseNewLine(false);
		   		questionRespEvt54.setSelectedResponseId(null);
		   		questionRespEvt54.setSelectedResponseText(null);
//		   		questionRespEvt54.setSelectedResponsesId(null);
		   		questionRespEvt54.setSelectedResponses(null);	   		
		   		questionRespEvt54.setPossibleResponseEvents(possibleAnswersList50);
		   		
		   		questionRespList3.add(questionRespEvt54);
		   		*/
		   		/*************** End 54 Question ******************/
		   		
		   		
		   		/******* Start 55 - 60 Question ******************/
		   		
		   		rowCounter = 5;
		   		summaryRowCounter=15;
		   		
		   		for(int i=55; i <= 60; i++)
		   		{	
		   			
		   			
		   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j);
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(2);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(3);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList3.add(questionRespEvt);
		   		}
		   		
		   		/******* End 55 - 60 Question ******************/
		   		
		   		/**************************** End Question Group 3 **********************/
	   		
		   		
		   		/*********************** Start Question Group 4 ***************************/
		   		
		   		CSCQuestionGroupResponseEvent questionGrpRespEvt4 = new CSCQuestionGroupResponseEvent();
		   		
		   		ArrayList questionRespList4 = new ArrayList();
		   		
		   		questionGrpRespEvt4.setId(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_FOUR_QUEST_GRP_ID);
		   		questionGrpRespEvt4.setDisplayText("SCS Score Entry");
		   		questionGrpRespEvt4.setDisplayTextDetailHeader(false);
		   		questionGrpRespEvt4.setDisplayTextAlign(null);
		   		questionGrpRespEvt4.setSequence(4);
		   		questionGrpRespEvt4.setQuestionResponseEvents(questionRespList4);
		   	
		   		
		   		/******* Start 61 - 66 Question ******************/
		   		
		   		rowCounter = 1;
		   		summaryRowCounter = 1;
		   		
		   		for(int i=61; i <= 66; i++)
		   		{	
		   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j); 				
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(1);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(4);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList4.add(questionRespEvt);
		   		}
		   		
		   		/******* End 61 - 66 Question ******************/
		   		
		   		
		   		
		   		/******* Start 67 - 71 Question ******************/
		   		
		   		rowCounter = 1;
		   		
		   		for(int i=67; i <= 71; i++)
		   		{	
		   			CSCQuestionResponseEvent questionRespEvt = new CSCQuestionResponseEvent();
		   			ArrayList possibleAnswersList = new ArrayList();   	
		   			
		   			char ch = 65;
		   			
		   			for(int j=0; j <=4; j++)
		   			{
		   				CSCPossibleResponseEvent possibleRespEvt = new CSCPossibleResponseEvent();
		   				
		   				if(j == 0)
		   				{
		   					possibleRespEvt.setId("0");
			   				possibleRespEvt.setDisplayText("Please Select");
			   				possibleRespEvt.setResponseValue("");
			   				possibleRespEvt.setDefault(true);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
		   				}
		   				else
		   				{		   				
			   				String ans= String.valueOf(ch);
			   				
			   				possibleRespEvt.setId(ans+j);
			   				possibleRespEvt.setDisplayText(ans);
			   				possibleRespEvt.setResponseValue(ans+j); 				
			   				possibleRespEvt.setDefault(false);
			   				
			   				possibleAnswersList.add(possibleRespEvt);
			   				ch++;
		   				}
		   			}
		   			
		   			questionRespEvt.setId(Integer.toString(i));
		   			questionRespEvt.setText(Integer.toString(i));
		   			questionRespEvt.setRowSequence(rowCounter++);
		   			questionRespEvt.setColumnSequence(2);
		   			questionRespEvt.setSummaryRowSeq(summaryRowCounter++);
		   			questionRespEvt.setSummaryColSeq(4);
		   			questionRespEvt.setRequired(false);
		   			questionRespEvt.setColumnWidth("1%");
		   			questionRespEvt.setSummaryRespColumnWidth("24%");
		   			questionRespEvt.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   			questionRespEvt.setResponseNewLine(false);
		   			questionRespEvt.setSelectedResponseId(null);
		   			questionRespEvt.setSelectedResponseText(null);
		   			questionRespEvt.setPossibleResponseEvents(possibleAnswersList);
		   			
		   			
			   		questionRespList4.add(questionRespEvt);
		   		}
		   		
		   		/******* End 67 - 71 Question ******************/
		   		
		   		/*********************** End Question Group 4  ****************************/
	   		
	   		ArrayList questionGrpList = new ArrayList();
	   		questionGrpList.add(questionGrpRespEvt1);
	   		questionGrpList.add(questionGrpRespEvt2);
	   		questionGrpList.add(questionGrpRespEvt3);
	   		questionGrpList.add(questionGrpRespEvt4);
	   		
	   		
	   		return questionGrpList;
	   }
	 
	 
	 
	 
	 private static void setForceFieldRankQuestion(CSCQuestionResponseEvent question, String id, int rowSeq)
	 {
	 	question.setId(id);
	 	question.setText("Rank");
	 	question.setRowSequence(rowSeq);
	 	question.setColumnSequence(1);
	 	question.setRequired(false);
	 	question.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_TEXT);	   		
	 	question.setResponseNewLine(false);
	 	question.setSelectedResponseId(null);
	 	question.setSelectedResponseText(null);
	 	question.setResponseDataType(CSCQuestion.RESPONSE_VAR_TYPE_NUMERIC);
	 	question.setUiControlSize("2");
	 	question.setMinValue("1");
	 	question.setMaxValue("16");
	 	question.setColumnWidth("1%");
	 	question.setPossibleResponseEvents(null);	 	
	 }
	 
	 
	 
	 private static void setForceFieldStrengthQuestion(CSCQuestionResponseEvent question, String id, int rowSeq)
	 {
	 	question.setId(id);
	 	question.setText("Strength/Resource");
	 	question.setRowSequence(rowSeq);
	 	question.setColumnSequence(1);
	 	question.setRequired(false);
	 	question.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_TEXTBOX);	   		
	 	question.setResponseNewLine(false);
	 	question.setSelectedResponseId(null);
	 	question.setSelectedResponseText(null);
	 	question.setResponseDataType(CSCQuestion.RESPONSE_VAR_TYPE_ALPHA_NUMERIC);
	 	question.setUiControlSize("2");
	 	question.setTextLength("200");
	 	question.setColumnWidth("1%");
	 	question.setPossibleResponseEvents(null);
	 }
	 
	 
	 private static void setForceFieldWeaknessQuestion(CSCQuestionResponseEvent question, String id, int rowSeq)
	 {
	 	question.setId(id);
	 	question.setText("Problems/Weakness");
	 	question.setRowSequence(rowSeq);
	 	question.setColumnSequence(1);
	 	question.setRequired(false);
	 	question.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_TEXTBOX);	   		
	 	question.setResponseNewLine(false);
	 	question.setSelectedResponseId(null);
	 	question.setSelectedResponseText(null);
	 	question.setResponseDataType(CSCQuestion.RESPONSE_VAR_TYPE_ALPHA_NUMERIC);
	 	question.setUiControlSize("2");
	 	question.setTextLength("200");
	 	question.setColumnWidth("1%");
	 	question.setPossibleResponseEvents(null);
	 }
	 
	 
	 /**
	  * FORCE FIELD ASSESSMENT 
	  * @return
	  */
	 public static Collection fakeNewForceFieldCreateExecute()
	   {
	 		/****************************** start 1 QuestionGroup  ******************************************/
	 	
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt1 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		
	   		questionGrpRespEvt1.setId("FORCEFIELDGP1");
	   		questionGrpRespEvt1.setDisplayText("Present Offense");
	   		questionGrpRespEvt1.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt1.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt1.setSequence(1);	   		
	   	
	   		ArrayList questionRespList1 = new ArrayList();
	   		questionGrpRespEvt1.setQuestionResponseEvents(questionRespList1);
	   		
	   		/******* Start 1 Question ******************/	   		
	   		CSCQuestionResponseEvent questionRespEvt11 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt11, "11", 1);	   		
	   		/******* End 1 Question ******************/
	   		
	   		
	   		/******* Start 2 Question ******************/	   		
	   		CSCQuestionResponseEvent questionRespEvt12 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt12, "12", 2); 		
	   		/******* End 2 Question ******************/
	   		
	   		
	   		/******* Start 3 Question ******************/	   		
	   		CSCQuestionResponseEvent questionRespEvt13 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt13, "13", 3);	   		
	   		/******* End 3 Question ******************/
	   		
	   		questionRespList1.add(questionRespEvt11);
	   		questionRespList1.add(questionRespEvt12);
	   		questionRespList1.add(questionRespEvt13);
			
	   		/****************************** End 1 QuestionGroup  ******************************************/
	   		
	   		
	   		/****************************** Start 2 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt2 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt2.setId("FORCEFIELDGP2");
	   		questionGrpRespEvt2.setDisplayText("Prior Record");
	   		questionGrpRespEvt2.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt2.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt2.setSequence(2);
	   		
	   		ArrayList questionRespList2 = new ArrayList();	   		
	   		questionGrpRespEvt2.setQuestionResponseEvents(questionRespList2);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt21 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt21, "21", 4);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt22 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt22, "22", 5);
	   		
	   		CSCQuestionResponseEvent questionRespEvt23 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt23, "23", 6);
	   		
	   		questionRespList2.add(questionRespEvt21);
	   		questionRespList2.add(questionRespEvt22);
	   		questionRespList2.add(questionRespEvt23);
	   		
	   		/****************************** End 2 QuestionGroup  ******************************************/
	   		
	   		/****************************** Start 3 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt3 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt3.setId("FORCEFIELDGP3");
	   		questionGrpRespEvt3.setDisplayText("Adjustment to Supervision/Incarceration");
	   		questionGrpRespEvt3.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt3.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt3.setSequence(3);
	   		
	   		ArrayList questionRespList3 = new ArrayList();	   		
	   		questionGrpRespEvt3.setQuestionResponseEvents(questionRespList3);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt31 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt31, "31", 7);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt32 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt32, "32", 8);
	   		
	   		CSCQuestionResponseEvent questionRespEvt33 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt33, "33", 9);
	   		
	   		questionRespList3.add(questionRespEvt31);
	   		questionRespList3.add(questionRespEvt32);
	   		questionRespList3.add(questionRespEvt33);
	   		
	   		/****************************** End 3 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 4 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt4 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt4.setId("FORCEFIELDGP4");
	   		questionGrpRespEvt4.setDisplayText("Academic/Vocational Skills");
	   		questionGrpRespEvt4.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt4.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt4.setSequence(4);
	   		
	   		ArrayList questionRespList4 = new ArrayList();	   		
	   		questionGrpRespEvt4.setQuestionResponseEvents(questionRespList4);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt41 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt41, "41", 10);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt42 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt42, "22", 11);
	   		
	   		CSCQuestionResponseEvent questionRespEvt43 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt43, "23", 12);
	   		
	   		questionRespList4.add(questionRespEvt41);
	   		questionRespList4.add(questionRespEvt42);
	   		questionRespList4.add(questionRespEvt43);
	   		
	   		/****************************** End 4 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 5 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt5 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt5.setId("FORCEFIELDGP5");
	   		questionGrpRespEvt5.setDisplayText("Employment Pattern");
	   		questionGrpRespEvt5.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt5.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt5.setSequence(5);
	   		
	   		ArrayList questionRespList5 = new ArrayList();	   		
	   		questionGrpRespEvt5.setQuestionResponseEvents(questionRespList5);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt51 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt51, "51", 13);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt52 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt52, "52", 14);
	   		
	   		CSCQuestionResponseEvent questionRespEvt53 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt53, "53", 15);
	   		
	   		questionRespList5.add(questionRespEvt51);
	   		questionRespList5.add(questionRespEvt52);
	   		questionRespList5.add(questionRespEvt53);
	   		
	   		/****************************** End 5 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 6 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt6 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt6.setId("FORCEFIELDGP6");
	   		questionGrpRespEvt6.setDisplayText("Financial Management");
	   		questionGrpRespEvt6.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt6.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt6.setSequence(6);
	   		
	   		ArrayList questionRespList6 = new ArrayList();	   		
	   		questionGrpRespEvt6.setQuestionResponseEvents(questionRespList6);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt61 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt61, "61", 16);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt62 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt62, "62", 17);
	   		
	   		CSCQuestionResponseEvent questionRespEvt63 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt63, "63", 18);
	   		
	   		questionRespList6.add(questionRespEvt61);
	   		questionRespList6.add(questionRespEvt62);
	   		questionRespList6.add(questionRespEvt63);
	   		
	   		/****************************** End 6 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 7 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt7 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt7.setId("FORCEFIELDGP7");
	   		questionGrpRespEvt7.setDisplayText("Family (of origin) Relationships");
	   		questionGrpRespEvt7.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt7.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt7.setSequence(7);
	   		
	   		ArrayList questionRespList7 = new ArrayList();	   		
	   		questionGrpRespEvt7.setQuestionResponseEvents(questionRespList7);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt71 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt71, "71", 19);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt72 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt72, "72", 20);
	   		
	   		CSCQuestionResponseEvent questionRespEvt73 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt73, "73", 21);
	   		
	   		questionRespList7.add(questionRespEvt71);
	   		questionRespList7.add(questionRespEvt72);
	   		questionRespList7.add(questionRespEvt73);
	   		
	   		/****************************** End 7 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 8 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt8 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt8.setId("FORCEFIELDGP8");
	   		questionGrpRespEvt8.setDisplayText("Social Skills");
	   		questionGrpRespEvt8.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt8.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt8.setSequence(8);
	   		
	   		ArrayList questionRespList8 = new ArrayList();	   		
	   		questionGrpRespEvt8.setQuestionResponseEvents(questionRespList8);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt81 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt81, "81", 22);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt82 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt82, "82", 23);
	   		
	   		CSCQuestionResponseEvent questionRespEvt83 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt83, "83", 24);
	   		
	   		questionRespList8.add(questionRespEvt81);
	   		questionRespList8.add(questionRespEvt82);
	   		questionRespList8.add(questionRespEvt83);
	   		
	   		/****************************** End 8 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 9 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt9 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt9.setId("FORCEFIELDGP9");
	   		questionGrpRespEvt9.setDisplayText("Companions");
	   		questionGrpRespEvt9.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt9.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt9.setSequence(9);
	   		
	   		ArrayList questionRespList9 = new ArrayList();	   		
	   		questionGrpRespEvt9.setQuestionResponseEvents(questionRespList9);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt91 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt91, "91", 25);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt92 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt92, "92", 26);
	   		
	   		CSCQuestionResponseEvent questionRespEvt93 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt93, "93", 27);
	   		
	   		questionRespList9.add(questionRespEvt91);
	   		questionRespList9.add(questionRespEvt92);
	   		questionRespList9.add(questionRespEvt93);
	   		
	   		/****************************** End 9 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		/****************************** Start 10 QuestionGroup  ******************************************/
	   		
	   		CSCQuestionGroupResponseEvent questionGrpRespEvt10 = new CSCQuestionGroupResponseEvent();
	   		
	   		
	   		questionGrpRespEvt10.setId("FORCEFIELDGP10");
	   		questionGrpRespEvt10.setDisplayText("Marital or Opposite Sex Relationships");
	   		questionGrpRespEvt10.setDisplayTextDetailHeader(true);
	   		questionGrpRespEvt10.setDisplayTextAlign(CSCQuestion.QUESTION_ALIGN_CENTER);
	   		questionGrpRespEvt10.setSequence(10);
	   		
	   		ArrayList questionRespList10 = new ArrayList();	   		
	   		questionGrpRespEvt10.setQuestionResponseEvents(questionRespList10);
	   	
	   		
	   		CSCQuestionResponseEvent questionRespEvt101 = new CSCQuestionResponseEvent();
	   		setForceFieldRankQuestion(questionRespEvt101, "101", 28);	   		
	   		
	   		CSCQuestionResponseEvent questionRespEvt102 = new CSCQuestionResponseEvent();
	   		setForceFieldStrengthQuestion(questionRespEvt102, "102", 29);
	   		
	   		CSCQuestionResponseEvent questionRespEvt103 = new CSCQuestionResponseEvent();
	   		setForceFieldWeaknessQuestion(questionRespEvt103, "103", 30);
	   		
	   		questionRespList10.add(questionRespEvt101);
	   		questionRespList10.add(questionRespEvt102);
	   		questionRespList10.add(questionRespEvt103);
	   		
	   		/****************************** End 10 QuestionGroup  ******************************************/
	   		
	   		
	   		
	   		ArrayList questionGrpList = new ArrayList();
	   		questionGrpList.add(questionGrpRespEvt1);
	   		questionGrpList.add(questionGrpRespEvt2);
	   		questionGrpList.add(questionGrpRespEvt3);
	   		questionGrpList.add(questionGrpRespEvt4);
	   		questionGrpList.add(questionGrpRespEvt5);
	   		questionGrpList.add(questionGrpRespEvt6);
	   		questionGrpList.add(questionGrpRespEvt7);
	   		questionGrpList.add(questionGrpRespEvt8);
	   		questionGrpList.add(questionGrpRespEvt9);
	   		questionGrpList.add(questionGrpRespEvt10);
	   		
	   		return questionGrpList;
	   }	
	 
	   public static SCSTotalsResponseEvent fakeSCSTotalResponseEvent()
	   {
	   		SCSTotalsResponseEvent responseEvt = new SCSTotalsResponseEvent();
	   		
	   		HashMap classificationTypeTotalMap = new HashMap(4);
	   		
	   		classificationTypeTotalMap.put(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_CC, "21");
	   		classificationTypeTotalMap.put(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_ES, "22");
	   		classificationTypeTotalMap.put(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_LS, "22");
	   		classificationTypeTotalMap.put(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI, "24");
	   		
	   		responseEvt.setClassificationTypeIdTotalMap(classificationTypeTotalMap);
	   		return responseEvt;
	   	
	   }
	   
	   
	   public static AssessmentDetailsResponseEvent fakeAssessmentDetailsWisconsinResponseEvent()
	   {
	   		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = new AssessmentDetailsResponseEvent();
	   		assessmentDetailsRespEvt.setRiskScore("21");
	   		assessmentDetailsRespEvt.setRiskLevel("2");
	   		assessmentDetailsRespEvt.setNeedsScore("-6");
	   		assessmentDetailsRespEvt.setNeedsLevel("4");
	   		assessmentDetailsRespEvt.setLevelOfSupervision("Medium");
	   		
	   		assessmentDetailsRespEvt.setQuestionAnswerList(fakeInitialWisconsinQuestionAnswers());
	   		
	   		return assessmentDetailsRespEvt;
	   }

	   
	   public static AssessmentDetailsResponseEvent fakeAssessmentDetailsLSIRResponseEvent()
	   {
	   		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = new AssessmentDetailsResponseEvent();
	   		
	   		assessmentDetailsRespEvt.setComments("My Name is Bhavani...");
	   		assessmentDetailsRespEvt.setQuestionAnswerList(fakeLSIRCreateExecute());
	   		
	   		return assessmentDetailsRespEvt;
	   }
	   
	   
	   public static AssessmentDetailsResponseEvent fakeAssessmentDetailsSCSResponseEvent()
	   {
	   		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = new AssessmentDetailsResponseEvent();
	   		
	   		assessmentDetailsRespEvt.setComments("My Name is Bhavani...");
	   		
	   		assessmentDetailsRespEvt.setTotalSI("68");
	   		assessmentDetailsRespEvt.setTotalCC("68");
	   		assessmentDetailsRespEvt.setTotalLS("29");
	   		assessmentDetailsRespEvt.setTotalES("42");
	   		
	   		assessmentDetailsRespEvt.setPrimaryClassification(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI);
	   		assessmentDetailsRespEvt.setSecondaryClassification(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_CC);

	   		assessmentDetailsRespEvt.setQuestionAnswerList(fakeNewSCSCreateExecute());
	   		
	   		return assessmentDetailsRespEvt;
	   }
	   
	   public static AssessmentDetailsResponseEvent fakeAssessmentDetailsForceFieldResponseEvent()
	   {
	   		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = new AssessmentDetailsResponseEvent();
	   		
	   		assessmentDetailsRespEvt.setQuestionAnswerList(fakeNewForceFieldCreateExecute());
	   		
	   		return assessmentDetailsRespEvt;
	   }
	   
	   public static Collection fakePriorAssessmentVersionResponseEvent()
	   {
	   		ArrayList priorVersionList = new ArrayList();
	   		
	   		PriorAssessmentVersionResponseEvent priorVersionRespEvt1 = new PriorAssessmentVersionResponseEvent();
	   		priorVersionRespEvt1.setTransactionDate(new Date(36200000));
	   		priorVersionRespEvt1.setAssessmentDate(new Date(36100000));
	   		priorVersionRespEvt1.setAssessmentId("200");
	   		priorVersionRespEvt1.setVersionNumber("1");
	   		
	   		priorVersionList.add(priorVersionRespEvt1);
	   		
	   		PriorAssessmentVersionResponseEvent priorVersionRespEvt2 = new PriorAssessmentVersionResponseEvent();
	   		priorVersionRespEvt2.setTransactionDate(new Date(36400000));
	   		priorVersionRespEvt2.setAssessmentDate(new Date(3630000));
	   		priorVersionRespEvt2.setAssessmentId("201");
	   		priorVersionRespEvt2.setVersionNumber("2");
	   		
	   		priorVersionList.add(priorVersionRespEvt2);
	   		
	   		PriorAssessmentVersionResponseEvent priorVersionRespEvt3 = new PriorAssessmentVersionResponseEvent();
	   		priorVersionRespEvt3.setTransactionDate(new Date(35000000));
	   		priorVersionRespEvt3.setAssessmentDate(new Date(36300000));
	   		priorVersionRespEvt3.setAssessmentId("202");
	   		priorVersionRespEvt3.setVersionNumber("3");
	   		
	   		priorVersionList.add(priorVersionRespEvt3);
	   		
	   		return priorVersionList;
	   }
	   
	   /**
		 * WISCONSIN INITIAL ASSESSMENT - RISK
		 * @return
		 */
		public static Collection fakeInitialWisconsinQuestionAnswers()
		   {
			
			/******************************************* Question Group 1 (Risk) Start  ********************************/
			
		   		CSCQuestionGroupResponseEvent questionGrpRespEvt1 = new CSCQuestionGroupResponseEvent();
		   		
		   		/******* Start First Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt11 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt110 = new CSCPossibleResponseEvent();
		   		possibleRespEvt110.setId("110");
		   		possibleRespEvt110.setDisplayText("Please Select");
		   		possibleRespEvt110.setResponseValue("");
		   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt111 = new CSCPossibleResponseEvent();
		   		possibleRespEvt111.setId("111");
		   		possibleRespEvt111.setDisplayText("None");
		   		possibleRespEvt111.setResponseValue("0");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt112 = new CSCPossibleResponseEvent();
		   		possibleRespEvt112.setId("112");
		   		possibleRespEvt112.setDisplayText("One");
		   		possibleRespEvt112.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt113 = new CSCPossibleResponseEvent();
		   		possibleRespEvt113.setId("113");
		   		possibleRespEvt113.setDisplayText("Two or More");
		   		possibleRespEvt113.setResponseValue("2");
		   		
		   		ArrayList possibleAnswersList11 = new ArrayList();   		
		   		possibleAnswersList11.add(possibleRespEvt110);
		   		possibleAnswersList11.add(possibleRespEvt111);
		   		possibleAnswersList11.add(possibleRespEvt112);
		   		possibleAnswersList11.add(possibleRespEvt113);
		   		
				
		  		
		   		questionRespEvt11.setId("11");
		   		questionRespEvt11.setText("Number of Address changes in the Last 12 months");
		   		questionRespEvt11.setRowSequence(1);
		   		questionRespEvt11.setColumnSequence(1);
		   		questionRespEvt11.setRequired(true);
		   		questionRespEvt11.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt11.setResponseNewLine(false);
		   		questionRespEvt11.setEachResponseNewLine(false);	   		
		   		questionRespEvt11.setSelectedResponseId("112");
		   		questionRespEvt11.setSelectedResponseText(null);
		   		questionRespEvt11.setPossibleResponseEvents(possibleAnswersList11);
		   		
		   		/******* End First Question ****************/
		   		
		   		
		   		
		   		/******* Start Second Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt12 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt120 = new CSCPossibleResponseEvent();
		   		possibleRespEvt120.setId("120");
		   		possibleRespEvt120.setDisplayText("Please Select");
		   		possibleRespEvt120.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt121 = new CSCPossibleResponseEvent();
		   		possibleRespEvt121.setId("121");
		   		possibleRespEvt121.setDisplayText("60% or More");
		   		possibleRespEvt121.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt122 = new CSCPossibleResponseEvent();
		   		possibleRespEvt122.setId("122");
		   		possibleRespEvt122.setDisplayText("40%-59%");
		   		possibleRespEvt122.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt123 = new CSCPossibleResponseEvent();
		   		possibleRespEvt123.setId("123");
		   		possibleRespEvt123.setDisplayText("Under 40%");
		   		possibleRespEvt123.setResponseValue("3");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt124 = new CSCPossibleResponseEvent();
		   		possibleRespEvt124.setId("124");
		   		possibleRespEvt124.setDisplayText("Not Applicable");
		   		possibleRespEvt124.setResponseValue("4");
		   		
		   		ArrayList possibleAnswersList12 = new ArrayList();
		   		possibleAnswersList12.add(possibleRespEvt120);
		   		possibleAnswersList12.add(possibleRespEvt121);
		   		possibleAnswersList12.add(possibleRespEvt122);
		   		possibleAnswersList12.add(possibleRespEvt123);
		   		possibleAnswersList12.add(possibleRespEvt124);
				
		  		
		   		questionRespEvt12.setId("12");
		   		questionRespEvt12.setText("Percentage of Time Employed in the Last 12 months");
		   		questionRespEvt12.setRowSequence(2);
		   		questionRespEvt12.setColumnSequence(1);
		   		questionRespEvt12.setRequired(true);
		   		questionRespEvt12.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt12.setResponseNewLine(false);
		   		questionRespEvt12.setEachResponseNewLine(false);
		   		questionRespEvt12.setSelectedResponseId("124");
		   		questionRespEvt12.setSelectedResponseText(null);
		   		questionRespEvt12.setPossibleResponseEvents(possibleAnswersList12);
		   		
		   		/******* End Second Question ****************/
		   		
		   		
		   		/******* Start Third Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt13 = new CSCQuestionResponseEvent();
		   		

		   		CSCPossibleResponseEvent possibleRespEvt130 = new CSCPossibleResponseEvent();
		   		possibleRespEvt130.setId("130");
		   		possibleRespEvt130.setDisplayText("Alcohol use unrelated to criminal activity; ex., no alcohol-related arrests, no evidence of use during the offense");
		   		possibleRespEvt130.setResponseValue("0");	   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt131 = new CSCPossibleResponseEvent();
		   		possibleRespEvt131.setId("131");
		   		possibleRespEvt131.setDisplayText("Probable relationship between alcohol use and criminal activity");
		   		possibleRespEvt131.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt132 = new CSCPossibleResponseEvent();
		   		possibleRespEvt132.setId("132");
		   		possibleRespEvt132.setDisplayText("Definite relationship between alcohol use and criminal activity; ex., pattern of committing offenses while using alcohol");
		   		possibleRespEvt132.setResponseValue("2");
		   		
		   		
		   		ArrayList possibleAnswersList13 = new ArrayList();
		   		possibleAnswersList13.add(possibleRespEvt130);
		   		possibleAnswersList13.add(possibleRespEvt131);
		   		possibleAnswersList13.add(possibleRespEvt132);		
		  		
		   		questionRespEvt13.setId("13");
		   		questionRespEvt13.setText("Alcohol Usage");
		   		questionRespEvt13.setRowSequence(3);
		   		questionRespEvt13.setColumnSequence(1);
		   		questionRespEvt13.setRequired(true);
		   		questionRespEvt13.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt13.setResponseNewLine(true);
		   		questionRespEvt13.setEachResponseNewLine(true);
		   		questionRespEvt13.setSelectedResponseId("130");
		   		questionRespEvt13.setSelectedResponseText(null);
		   		questionRespEvt13.setPossibleResponseEvents(possibleAnswersList13);
		   		
		   		/******* End Third Question ****************/
		   		
		   		
		   		/******* Start Fourth Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt14 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt140 = new CSCPossibleResponseEvent();
		   		possibleRespEvt140.setId("140");
		   		possibleRespEvt140.setDisplayText("No abuse of legal drug; no indicators of illegal drug involvement, ie., use, possession or abuse");
		   		possibleRespEvt140.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt141 = new CSCPossibleResponseEvent();
		   		possibleRespEvt141.setId("141");
		   		possibleRespEvt141.setDisplayText("Probable relationship between drug involvement and criminal activity");
		   		possibleRespEvt141.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt142 = new CSCPossibleResponseEvent();
		   		possibleRespEvt142.setId("142");
		   		possibleRespEvt142.setDisplayText("Definite relationship between drug involvement and criminal activity; ex., pattern of committing offenses while using drugs, sale or manufacture of illegal drugs");
		   		possibleRespEvt142.setResponseValue("2");
		   			   		  		
		   		ArrayList possibleAnswersList14 = new ArrayList();
		   		possibleAnswersList14.add(possibleRespEvt140);
		   		possibleAnswersList14.add(possibleRespEvt141);
		   		possibleAnswersList14.add(possibleRespEvt142);		
		   			  		
		   		questionRespEvt14.setId("14");
		   		questionRespEvt14.setText("Other Drug Usage");
		   		questionRespEvt14.setRowSequence(4);
		   		questionRespEvt14.setColumnSequence(1);
		   		questionRespEvt14.setRequired(true);
		   		questionRespEvt14.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt14.setResponseNewLine(true);
		   		questionRespEvt14.setEachResponseNewLine(true);
		   		questionRespEvt14.setSelectedResponseId("141");
		   		questionRespEvt14.setSelectedResponseText(null);
		   		questionRespEvt14.setPossibleResponseEvents(possibleAnswersList14);
		   		
		   		/******* End Fourth Question ****************/
		   		
		   		
		   		
		   		/******* Start Fifth Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt15 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt151 = new CSCPossibleResponseEvent();
		   		possibleRespEvt151.setId("151");
		   		possibleRespEvt151.setDisplayText("Motivated to change; receptive to assistance");
		   		possibleRespEvt151.setResponseValue("0");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt152 = new CSCPossibleResponseEvent();
		   		possibleRespEvt152.setId("152");
		   		possibleRespEvt152.setDisplayText("Somewhat motivated but dependent or unwilling to accept responsibility");
		   		possibleRespEvt152.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt153 = new CSCPossibleResponseEvent();
		   		possibleRespEvt153.setId("153");
		   		possibleRespEvt153.setDisplayText("Rationalizes behavior; negative; not motivated to change");
		   		possibleRespEvt153.setResponseValue("1");
		   		
		   		ArrayList possibleAnswersList15 = new ArrayList();
		   		possibleAnswersList15.add(possibleRespEvt151);
		   		possibleAnswersList15.add(possibleRespEvt152);		
		   		possibleAnswersList15.add(possibleRespEvt153);
		   		
		   		questionRespEvt15.setId("15");
		   		questionRespEvt15.setText("Attitude");
		   		questionRespEvt15.setRowSequence(5);
		   		questionRespEvt15.setColumnSequence(1);
		   		questionRespEvt15.setRequired(true);
		   		questionRespEvt15.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt15.setResponseNewLine(true);
		   		questionRespEvt15.setEachResponseNewLine(true);
		   		questionRespEvt15.setSelectedResponseId("152");
		   		questionRespEvt15.setSelectedResponseText(null);
		   		questionRespEvt15.setPossibleResponseEvents(possibleAnswersList15);
		   		
		   		/******* End Fifth Question ****************/
		   		
		   		
		   		
		   		/******* Start Six Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt16 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt161 = new CSCPossibleResponseEvent();
		   		possibleRespEvt161.setId("161");
		   		possibleRespEvt161.setDisplayText("Please Select");
		   		possibleRespEvt161.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt162 = new CSCPossibleResponseEvent();
		   		possibleRespEvt162.setId("162");
		   		possibleRespEvt162.setDisplayText("24 or older");
		   		possibleRespEvt162.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt163 = new CSCPossibleResponseEvent();
		   		possibleRespEvt163.setId("163");
		   		possibleRespEvt163.setDisplayText("20-23");
		   		possibleRespEvt163.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt164 = new CSCPossibleResponseEvent();
		   		possibleRespEvt164.setId("164");
		   		possibleRespEvt164.setDisplayText("19 or younger");
		   		possibleRespEvt164.setResponseValue("3");
		   		
		   		ArrayList possibleAnswersList16 = new ArrayList();
		   		possibleAnswersList16.add(possibleRespEvt161);
		   		possibleAnswersList16.add(possibleRespEvt162);		
		   		possibleAnswersList16.add(possibleRespEvt163);		
		   		possibleAnswersList16.add(possibleRespEvt164);		
		  		
		   		questionRespEvt16.setId("16");
		   		questionRespEvt16.setText("Adult or Juvenile Adjudication for wothless checks or forgery");
		   		questionRespEvt16.setRowSequence(6);
		   		questionRespEvt16.setColumnSequence(1);
		   		questionRespEvt16.setRequired(true);
		   		questionRespEvt16.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt16.setResponseNewLine(false);
		   		questionRespEvt16.setSelectedResponseId("163");
		   		questionRespEvt16.setSelectedResponseText(null);
		   		questionRespEvt16.setPossibleResponseEvents(possibleAnswersList16);
		   		
		   		/******* End Six Question ****************/
		   		
		   		
		   		
		   		
		   		/******* Start 7 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt17 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt171 = new CSCPossibleResponseEvent();
		   		possibleRespEvt171.setId("171");
		   		possibleRespEvt171.setDisplayText("Please Select");
		   		possibleRespEvt171.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt172 = new CSCPossibleResponseEvent();
		   		possibleRespEvt172.setId("172");
		   		possibleRespEvt172.setDisplayText("None");
		   		possibleRespEvt172.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt173 = new CSCPossibleResponseEvent();
		   		possibleRespEvt173.setId("173");
		   		possibleRespEvt173.setDisplayText("One or More");
		   		possibleRespEvt173.setResponseValue("2");
		   		
		   		ArrayList possibleAnswersList17 = new ArrayList();
		   		possibleAnswersList17.add(possibleRespEvt171);
		   		possibleAnswersList17.add(possibleRespEvt172);		
		   		possibleAnswersList17.add(possibleRespEvt173);		
		   		  		
		   		questionRespEvt17.setId("16");
		   		questionRespEvt17.setText("Number of Prior Periods of Community Supervision/ Parole Supervision (Adult or Juvenile)");
		   		questionRespEvt17.setRowSequence(7);
		   		questionRespEvt17.setColumnSequence(1);
		   		questionRespEvt17.setRequired(true);
		   		questionRespEvt17.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt17.setResponseNewLine(false);
		   		questionRespEvt17.setSelectedResponseId("173");
		   		questionRespEvt17.setSelectedResponseText(null);
		   		questionRespEvt17.setPossibleResponseEvents(possibleAnswersList17);
		   		
		   		/******* End 7 Question ****************/
		   		
		   		
		   		
		   		/******* Start 8 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt18 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt180 = new CSCPossibleResponseEvent();
		   		possibleRespEvt180.setId("180");
		   		possibleRespEvt180.setDisplayText("Please Select");
		   		possibleRespEvt180.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt181 = new CSCPossibleResponseEvent();
		   		possibleRespEvt181.setId("181");
		   		possibleRespEvt181.setDisplayText("None");
		   		possibleRespEvt181.setResponseValue("0");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt182 = new CSCPossibleResponseEvent();
		   		possibleRespEvt182.setId("182");
		   		possibleRespEvt182.setDisplayText("One or More");
		   		possibleRespEvt182.setResponseValue("1");	   		
		   		   		
		   		ArrayList possibleAnswersList18 = new ArrayList();
		   		possibleAnswersList18.add(possibleRespEvt180);
		   		possibleAnswersList18.add(possibleRespEvt181);
		   		possibleAnswersList18.add(possibleRespEvt182);		
		   		
		   		questionRespEvt18.setId("18");
		   		questionRespEvt18.setText("Number of Prior Community Supervision/Parole Revocations(Adult or Juvenile)");
		   		questionRespEvt18.setRowSequence(8);
		   		questionRespEvt18.setColumnSequence(1);
		   		questionRespEvt18.setRequired(true);
		   		questionRespEvt18.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt18.setResponseNewLine(false);
		   		questionRespEvt18.setSelectedResponseId("182");
		   		questionRespEvt18.setSelectedResponseText(null);
		   		questionRespEvt18.setPossibleResponseEvents(possibleAnswersList18);
		   		
		   		/******* End 8 Question ****************/
		   		
		   		
		   		/******* Start 9 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt19 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt190 = new CSCPossibleResponseEvent();
		   		possibleRespEvt190.setId("190");
		   		possibleRespEvt190.setDisplayText("Please Select");
		   		possibleRespEvt190.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt191 = new CSCPossibleResponseEvent();
		   		possibleRespEvt191.setId("191");
		   		possibleRespEvt191.setDisplayText("None");
		   		possibleRespEvt191.setResponseValue("0");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt192 = new CSCPossibleResponseEvent();
		   		possibleRespEvt192.setId("192");
		   		possibleRespEvt192.setDisplayText("One or More");
		   		possibleRespEvt192.setResponseValue("1");	   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt193 = new CSCPossibleResponseEvent();
		   		possibleRespEvt193.setId("193");
		   		possibleRespEvt193.setDisplayText("Two or More");
		   		possibleRespEvt193.setResponseValue("2");	  
		   		
		   		ArrayList possibleAnswersList19 = new ArrayList();
		   		possibleAnswersList19.add(possibleRespEvt190);
		   		possibleAnswersList19.add(possibleRespEvt191);
		   		possibleAnswersList19.add(possibleRespEvt192);		
		   		possibleAnswersList19.add(possibleRespEvt193);	
		   		
		   		questionRespEvt19.setId("19");
		   		questionRespEvt19.setText("Number of Prior Felony Adjudications of Guilt (or Juvenile Commitments - include deferred)");
		   		questionRespEvt19.setRowSequence(9);
		   		questionRespEvt19.setColumnSequence(1);
		   		questionRespEvt19.setRequired(true);
		   		questionRespEvt19.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt19.setResponseNewLine(false);
		   		questionRespEvt19.setSelectedResponseId("193");
		   		questionRespEvt19.setSelectedResponseText(null);
		   		questionRespEvt19.setPossibleResponseEvents(possibleAnswersList19);
		   		
		   		/******* End 9 Question ****************/
		   		
		   		
		   		/******* Start 10 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt110 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt1100 = new CSCPossibleResponseEvent();
		   		possibleRespEvt1100.setId("1100");
		   		possibleRespEvt1100.setDisplayText("Yes");
		   		possibleRespEvt1100.setResponseValue("1");
		 	   		
		   		CSCPossibleResponseEvent possibleRespEvt1101 = new CSCPossibleResponseEvent();
		   		possibleRespEvt1101.setId("1101");
		   		possibleRespEvt1101.setDisplayText("No");
		   		possibleRespEvt1101.setResponseValue("0");
		   		
		   		ArrayList possibleAnswersList110 = new ArrayList();
		   		possibleAnswersList110.add(possibleRespEvt1100);
		   		possibleAnswersList110.add(possibleRespEvt1101);
		   		
		   		questionRespEvt110.setId("110");
		   		questionRespEvt110.setText("Adult or Juvenile Adjudication for burglary, theft, auto theft, or robbery");
		   		questionRespEvt110.setRowSequence(10);
		   		questionRespEvt110.setColumnSequence(1);
		   		questionRespEvt110.setRequired(true);
		   		questionRespEvt110.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt110.setResponseNewLine(false);	   		
		   		questionRespEvt110.setSelectedResponseId("1101");
		   		questionRespEvt110.setSelectedResponseText(null);
		   		questionRespEvt110.setPossibleResponseEvents(possibleAnswersList110);
		   		
		   		/******* End 10 Question ****************/
		   		
		   		
		   		/******* Start 11 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt111 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt1110 = new CSCPossibleResponseEvent();
		   		possibleRespEvt1110.setId("1110");
		   		possibleRespEvt1110.setDisplayText("Yes");
		   		possibleRespEvt1110.setResponseValue("1");
		 	   		
		   		CSCPossibleResponseEvent possibleRespEvt1111 = new CSCPossibleResponseEvent();
		   		possibleRespEvt1111.setId("1111");
		   		possibleRespEvt1111.setDisplayText("No");
		   		possibleRespEvt1111.setResponseValue("0");
		   		
		   		ArrayList possibleAnswersList111 = new ArrayList();
		   		possibleAnswersList111.add(possibleRespEvt1110);
		   		possibleAnswersList111.add(possibleRespEvt1111);
		   		
		   		questionRespEvt111.setId("111");
		   		questionRespEvt111.setText("Adult or Juvenile Adjudication for worthless checks or forgery");
		   		questionRespEvt111.setRowSequence(11);
		   		questionRespEvt111.setColumnSequence(1);
		   		questionRespEvt111.setRequired(true);
		   		questionRespEvt111.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt111.setResponseNewLine(false);	   		
		   		questionRespEvt111.setSelectedResponseId("1111");
		   		questionRespEvt111.setSelectedResponseText(null);
		   		questionRespEvt111.setPossibleResponseEvents(possibleAnswersList111);
		   		
		   		/******* End 11 Question ****************/
		   		
		   		
		   		/******* Start 12 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt112 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt1120 = new CSCPossibleResponseEvent();
		   		possibleRespEvt1120.setId("1120");
		   		possibleRespEvt1120.setDisplayText("Yes");
		   		possibleRespEvt1120.setResponseValue("1");
		 	   		
		   		CSCPossibleResponseEvent possibleRespEvt1121 = new CSCPossibleResponseEvent();
		   		possibleRespEvt1121.setId("1121");
		   		possibleRespEvt1121.setDisplayText("No");
		   		possibleRespEvt1121.setResponseValue("0");
		   		
		   		ArrayList possibleAnswersList112 = new ArrayList();
		   		possibleAnswersList112.add(possibleRespEvt1120);
		   		possibleAnswersList112.add(possibleRespEvt1121);
		   		
		   		questionRespEvt112.setId("112");
		   		questionRespEvt112.setText("Adult or Juvenile Adjudications for Assaultive Offense within the Last FIVE Years (An offense which is defined as Assaultive, or one which involves the use of a weapon, physical force, or the threat offorce)");
		   		questionRespEvt112.setRowSequence(12);
		   		questionRespEvt112.setColumnSequence(1);
		   		questionRespEvt112.setRequired(true);
		   		questionRespEvt112.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt112.setResponseNewLine(false);	   		
		   		questionRespEvt112.setSelectedResponseId("1120");
		   		questionRespEvt112.setSelectedResponseText(null);
		   		questionRespEvt112.setPossibleResponseEvents(possibleAnswersList112);
		   		
		   		/******* End 12 Question ****************/
		   		
		   		
		   		ArrayList questionRespList1 = new ArrayList();
		   		
		   		questionRespList1.add(questionRespEvt11);
		   		questionRespList1.add(questionRespEvt12);
		   		questionRespList1.add(questionRespEvt13);
		   		questionRespList1.add(questionRespEvt14);
		   		questionRespList1.add(questionRespEvt15);
		   		questionRespList1.add(questionRespEvt16);
		   		questionRespList1.add(questionRespEvt17);
		   		questionRespList1.add(questionRespEvt18);
		   		questionRespList1.add(questionRespEvt19);
		   		questionRespList1.add(questionRespEvt110);
		   		questionRespList1.add(questionRespEvt111);
		   		questionRespList1.add(questionRespEvt112);
				
		   		questionGrpRespEvt1.setId(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITAIL_RISK_QUEST_GRP_ID);
		   		questionGrpRespEvt1.setDisplayText("Risk Assessment");
		   		questionGrpRespEvt1.setDisplayTextDetailHeader(false);	   		
		   		questionGrpRespEvt1.setSequence(1);
		   		questionGrpRespEvt1.setQuestionResponseEvents(questionRespList1);
		   		
		   		/******************************************* Question Group 1 (Risk) End  ********************************/
		   		
		   		
		   		/******************************************* Question Group 2 (Needs) Start  ********************************/
		        
		   		CSCQuestionGroupResponseEvent questionGrpRespEvt2 = new CSCQuestionGroupResponseEvent();
		   		
		   		/******* Start First Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt21 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt210 = new CSCPossibleResponseEvent();
		   		possibleRespEvt210.setId("210");
		   		possibleRespEvt210.setDisplayText("High School or above skill level");
		   		possibleRespEvt210.setResponseValue("0");	   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt211 = new CSCPossibleResponseEvent();
		   		possibleRespEvt211.setId("211");
		   		possibleRespEvt211.setDisplayText("Adequate skills, able to handle everyday requirements");
		   		possibleRespEvt211.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt212 = new CSCPossibleResponseEvent();
		   		possibleRespEvt212.setId("212");
		   		possibleRespEvt212.setDisplayText("Low skill level causing minor adjustment problems");
		   		possibleRespEvt212.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt213 = new CSCPossibleResponseEvent();
		   		possibleRespEvt213.setId("213");
		   		possibleRespEvt213.setDisplayText("Minimal skill level causing serious adjustment problems");
		   		possibleRespEvt213.setResponseValue("3");
		   		
		   		ArrayList possibleAnswersList21 = new ArrayList();   		
		   		possibleAnswersList21.add(possibleRespEvt210);
		   		possibleAnswersList21.add(possibleRespEvt211);
		   		possibleAnswersList21.add(possibleRespEvt212);
		   		possibleAnswersList21.add(possibleRespEvt213);
		   		
				
		  		
		   		questionRespEvt21.setId("21");
		   		questionRespEvt21.setText("ACADEMIC/VOCATIONAL SKILLS");
		   		questionRespEvt21.setRowSequence(1);
		   		questionRespEvt21.setColumnSequence(1);
		   		questionRespEvt21.setRequired(true);
		   		questionRespEvt21.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt21.setResponseNewLine(true);
		   		questionRespEvt21.setEachResponseNewLine(true);
		   		questionRespEvt21.setSelectedResponseId("213");
		   		questionRespEvt21.setSelectedResponseText(null);
		   		questionRespEvt21.setPossibleResponseEvents(possibleAnswersList21);
		   		
		   		/******* End First Question ****************/
		   		
		   		
		   		
		   		/******* Start Second Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt22 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt220 = new CSCPossibleResponseEvent();
		   		possibleRespEvt220.setId("220");
		   		possibleRespEvt220.setDisplayText("Satisfactory employment for one year or longer");
		   		possibleRespEvt220.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt221 = new CSCPossibleResponseEvent();
		   		possibleRespEvt221.setId("221");
		   		possibleRespEvt221.setDisplayText("Secure employment; no difficulties reported; or homemaker, student, or retired");
		   		possibleRespEvt221.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt222 = new CSCPossibleResponseEvent();
		   		possibleRespEvt222.setId("222");
		   		possibleRespEvt222.setDisplayText("Unsatisfactory employment or  unemployed but has adequate job skills");
		   		possibleRespEvt222.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt223 = new CSCPossibleResponseEvent();
		   		possibleRespEvt223.setId("223");
		   		possibleRespEvt223.setDisplayText("Unemployed and virtually unemployable; needs training");
		   		possibleRespEvt223.setResponseValue("3");
	   		
		   		ArrayList possibleAnswersList22 = new ArrayList();
		   		possibleAnswersList22.add(possibleRespEvt220);
		   		possibleAnswersList22.add(possibleRespEvt221);
		   		possibleAnswersList22.add(possibleRespEvt222);
		   		possibleAnswersList22.add(possibleRespEvt223);
				
		  		
		   		questionRespEvt22.setId("22");
		   		questionRespEvt22.setText("EMPLOYMENT");
		   		questionRespEvt22.setRowSequence(2);
		   		questionRespEvt22.setColumnSequence(1);
		   		questionRespEvt22.setRequired(true);
		   		questionRespEvt22.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt22.setResponseNewLine(true);
		   		questionRespEvt22.setEachResponseNewLine(true);
		   		questionRespEvt22.setSelectedResponseId("222");
		   		questionRespEvt22.setSelectedResponseText(null);
		   		questionRespEvt22.setPossibleResponseEvents(possibleAnswersList22);
		   		
		   		/******* End Second Question ****************/
		   		
		   		
		   		/******* Start Third Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt23 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt230 = new CSCPossibleResponseEvent();
		   		possibleRespEvt230.setId("230");
		   		possibleRespEvt230.setDisplayText("Long-standing pattern of selfsufficiency;e.g., good credit");
		   		possibleRespEvt230.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt231 = new CSCPossibleResponseEvent();
		   		possibleRespEvt231.setId("231");
		   		possibleRespEvt231.setDisplayText("No current difficulties");
		   		possibleRespEvt231.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt232 = new CSCPossibleResponseEvent();
		   		possibleRespEvt232.setId("232");
		   		possibleRespEvt232.setDisplayText("Situational or minor difficulties");
		   		possibleRespEvt232.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt233 = new CSCPossibleResponseEvent();
		   		possibleRespEvt233.setId("233");
		   		possibleRespEvt233.setDisplayText("Severe difficulties; may include overdrafts, bad checks or bankruptcy");
		   		possibleRespEvt233.setResponseValue("3");
	   		
		   		ArrayList possibleAnswersList23 = new ArrayList();
		   		possibleAnswersList23.add(possibleRespEvt230);
		   		possibleAnswersList23.add(possibleRespEvt231);
		   		possibleAnswersList23.add(possibleRespEvt232);
		   		possibleAnswersList23.add(possibleRespEvt233);
				
		  		
		   		questionRespEvt23.setId("23");
		   		questionRespEvt23.setText("FINANCIAL MANAGEMENT");
		   		questionRespEvt23.setRowSequence(3);
		   		questionRespEvt23.setColumnSequence(1);
		   		questionRespEvt23.setRequired(true);
		   		questionRespEvt23.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt23.setResponseNewLine(true);
		   		questionRespEvt23.setEachResponseNewLine(true);
		   		questionRespEvt23.setSelectedResponseId("231");
		   		questionRespEvt23.setSelectedResponseText(null);
		   		questionRespEvt23.setPossibleResponseEvents(possibleAnswersList23);
		   		
		   		/******* End Third Question ****************/
		   		
		   		
		   		/******* Start Fourth Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt24 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt240 = new CSCPossibleResponseEvent();
		   		possibleRespEvt240.setId("240");
		   		possibleRespEvt240.setDisplayText("Relationships and support exceptionally strong");
		   		possibleRespEvt240.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt241 = new CSCPossibleResponseEvent();
		   		possibleRespEvt241.setId("241");
		   		possibleRespEvt241.setDisplayText("Relatively stable relationships");
		   		possibleRespEvt241.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt242 = new CSCPossibleResponseEvent();
		   		possibleRespEvt242.setId("242");
		   		possibleRespEvt242.setDisplayText("Some disorganization or stress but potential for improvement");
		   		possibleRespEvt242.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt243 = new CSCPossibleResponseEvent();
		   		possibleRespEvt243.setId("243");
		   		possibleRespEvt243.setDisplayText("Major disorganization or stress");
		   		possibleRespEvt243.setResponseValue("3");
	   		
		   		ArrayList possibleAnswersList24 = new ArrayList();
		   		possibleAnswersList24.add(possibleRespEvt240);
		   		possibleAnswersList24.add(possibleRespEvt241);
		   		possibleAnswersList24.add(possibleRespEvt242);
		   		possibleAnswersList24.add(possibleRespEvt243);
				
		  		
		   		questionRespEvt24.setId("24");
		   		questionRespEvt24.setText("MARITAL/FAMILY RELATIONSHIPS");
		   		questionRespEvt24.setRowSequence(4);
		   		questionRespEvt24.setColumnSequence(1);
		   		questionRespEvt24.setRequired(true);
		   		questionRespEvt24.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt24.setResponseNewLine(true);
		   		questionRespEvt24.setEachResponseNewLine(true);
		   		questionRespEvt24.setSelectedResponseId("240");
		   		questionRespEvt24.setSelectedResponseText(null);
		   		questionRespEvt24.setPossibleResponseEvents(possibleAnswersList24);
		   		
		   		/******* End Fourth Question ****************/
		   		
		   		
		   		
		   		/******* Start Fifth Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt25 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt251 = new CSCPossibleResponseEvent();
		   		possibleRespEvt251.setId("251");
		   		possibleRespEvt251.setDisplayText("Please Select");
		   		possibleRespEvt251.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt253 = new CSCPossibleResponseEvent();
		   		possibleRespEvt253.setId("253");
		   		possibleRespEvt253.setDisplayText("Good support and influence");
		   		possibleRespEvt253.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt254 = new CSCPossibleResponseEvent();
		   		possibleRespEvt254.setId("254");
		   		possibleRespEvt254.setDisplayText("No adverse relationships");
		   		possibleRespEvt254.setResponseValue("3");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt255 = new CSCPossibleResponseEvent();
		   		possibleRespEvt255.setId("255");
		   		possibleRespEvt255.setDisplayText("Associations with occasional negative results");
		   		possibleRespEvt255.setResponseValue("4");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt256 = new CSCPossibleResponseEvent();
		   		possibleRespEvt256.setId("256");
		   		possibleRespEvt256.setDisplayText("Associations almost completely negative");
		   		possibleRespEvt256.setResponseValue("5");
		   		
		   		ArrayList possibleAnswersList25 = new ArrayList();
		   		possibleAnswersList25.add(possibleRespEvt251);	   	
		   		possibleAnswersList25.add(possibleRespEvt253);
		   		possibleAnswersList25.add(possibleRespEvt254);
		   		possibleAnswersList25.add(possibleRespEvt255);
		   		possibleAnswersList25.add(possibleRespEvt256);
		   		
		   		questionRespEvt25.setId("25");
		   		questionRespEvt25.setText("COMPANIONS");
		   		questionRespEvt25.setRowSequence(5);
		   		questionRespEvt25.setColumnSequence(1);
		   		questionRespEvt25.setRequired(true);
		   		questionRespEvt25.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt25.setResponseNewLine(true);	   		
		   		questionRespEvt25.setSelectedResponseId("256");
		   		questionRespEvt25.setSelectedResponseText(null);
		   		questionRespEvt25.setPossibleResponseEvents(possibleAnswersList25);
		   		
		   		/******* End Fifth Question ****************/
		   		
		   		
		   		
		   		/******* Start Six Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt26 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt260 = new CSCPossibleResponseEvent();
		   		possibleRespEvt260.setId("260");
		   		possibleRespEvt260.setDisplayText("Exceptionally well adjusted; accepts responsibility for actions");
		   		possibleRespEvt260.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt261 = new CSCPossibleResponseEvent();
		   		possibleRespEvt261.setId("261");
		   		possibleRespEvt261.setDisplayText("No symptoms of emotional instability; appropriate emotional responses");
		   		possibleRespEvt261.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt262 = new CSCPossibleResponseEvent();
		   		possibleRespEvt262.setId("262");
		   		possibleRespEvt262.setDisplayText("Symptoms limit but do not prohibit adequate functioning;e.g., excessive anxiety");
		   		possibleRespEvt262.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt263 = new CSCPossibleResponseEvent();
		   		possibleRespEvt263.setId("263");
		   		possibleRespEvt263.setDisplayText("Symptoms prohibit adequate functioning; e.g., lashes out or retreats into self");
		   		possibleRespEvt263.setResponseValue("3");
	   		
		   		ArrayList possibleAnswersList26 = new ArrayList();
		   		possibleAnswersList26.add(possibleRespEvt260);
		   		possibleAnswersList26.add(possibleRespEvt261);
		   		possibleAnswersList26.add(possibleRespEvt262);
		   		possibleAnswersList26.add(possibleRespEvt263);
				
		  		
		   		questionRespEvt26.setId("26");
		   		questionRespEvt26.setText("EMOTIONAL STABILITY");
		   		questionRespEvt26.setRowSequence(6);
		   		questionRespEvt26.setColumnSequence(1);
		   		questionRespEvt26.setRequired(true);
		   		questionRespEvt26.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt26.setResponseNewLine(true);
		   		questionRespEvt26.setEachResponseNewLine(true);
		   		questionRespEvt26.setSelectedResponseId("262");
		   		questionRespEvt26.setSelectedResponseText(null);
		   		questionRespEvt26.setPossibleResponseEvents(possibleAnswersList26);
		   		
		   		/******* End Six Question ****************/
		   		
		   		
		   		
		   		
		   		/******* Start 7 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt27 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt270 = new CSCPossibleResponseEvent();
		   		possibleRespEvt270.setId("270");
		   		possibleRespEvt270.setDisplayText("No use; use with no abuse; no disruption of functioning");
		   		possibleRespEvt270.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt271 = new CSCPossibleResponseEvent();
		   		possibleRespEvt271.setId("271");
		   		possibleRespEvt271.setDisplayText("Occasional abuse; some disruption of functioning");
		   		possibleRespEvt271.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt272 = new CSCPossibleResponseEvent();
		   		possibleRespEvt272.setId("272");
		   		possibleRespEvt272.setDisplayText("Frequent abuse; serious disruption of functioning");
		   		possibleRespEvt272.setResponseValue("2");
		   		
		   		ArrayList possibleAnswersList27 = new ArrayList();
		   		possibleAnswersList27.add(possibleRespEvt270);
		   		possibleAnswersList27.add(possibleRespEvt271);
		   		possibleAnswersList27.add(possibleRespEvt272);
		   			  		
		   		questionRespEvt27.setId("27");
		   		questionRespEvt27.setText("ALCOHOL USAGE PROBLEMS");
		   		questionRespEvt27.setRowSequence(7);
		   		questionRespEvt27.setColumnSequence(1);
		   		questionRespEvt27.setRequired(true);
		   		questionRespEvt27.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt27.setResponseNewLine(true);
		   		questionRespEvt27.setEachResponseNewLine(true);
		   		questionRespEvt27.setSelectedResponseId("271");
		   		questionRespEvt27.setSelectedResponseText(null);
		   		questionRespEvt27.setPossibleResponseEvents(possibleAnswersList27);
		   		
		   		/******* End 7 Question ****************/
		   		
		   		
		   		
		   		/******* Start 8 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt28 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt280 = new CSCPossibleResponseEvent();
		   		possibleRespEvt280.setId("280");
		   		possibleRespEvt280.setDisplayText("Please Select");
		   		possibleRespEvt280.setResponseValue("");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt281 = new CSCPossibleResponseEvent();
		   		possibleRespEvt281.setId("281");
		   		possibleRespEvt281.setDisplayText("No disruption of functioning");
		   		possibleRespEvt281.setResponseValue("0");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt282 = new CSCPossibleResponseEvent();
		   		possibleRespEvt282.setId("282");
		   		possibleRespEvt282.setDisplayText("Occasional abuse; some disruption of functioning");
		   		possibleRespEvt282.setResponseValue("1");	   		
		   		
		   		CSCPossibleResponseEvent possibleRespEvt283 = new CSCPossibleResponseEvent();
		   		possibleRespEvt283.setId("283");
		   		possibleRespEvt283.setDisplayText("Frequent abuse;  Serious disruption of functioning");
		   		possibleRespEvt283.setResponseValue("2");	   		
		   		
		   		ArrayList possibleAnswersList28 = new ArrayList();
		   		possibleAnswersList28.add(possibleRespEvt280);
		   		possibleAnswersList28.add(possibleRespEvt281);
		   		possibleAnswersList28.add(possibleRespEvt282);		
		   		possibleAnswersList28.add(possibleRespEvt283);	
		   		
		   		questionRespEvt28.setId("28");
		   		questionRespEvt28.setText("OTHER DRUG USAGE PROBLEMS");
		   		questionRespEvt28.setRowSequence(8);
		   		questionRespEvt28.setColumnSequence(1);
		   		questionRespEvt28.setRequired(true);
		   		questionRespEvt28.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt28.setResponseNewLine(true);
		   		questionRespEvt28.setSelectedResponseId("282");
		   		questionRespEvt28.setSelectedResponseText(null);
		   		questionRespEvt28.setPossibleResponseEvents(possibleAnswersList28);
		   		
		   		/******* End 8 Question ****************/
		   		
		   		
		   		/******* Start 9 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt29 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt290 = new CSCPossibleResponseEvent();
		   		possibleRespEvt290.setId("290");
		   		possibleRespEvt290.setDisplayText("Able to function independently");
		   		possibleRespEvt290.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt291 = new CSCPossibleResponseEvent();
		   		possibleRespEvt291.setId("291");
		   		possibleRespEvt291.setDisplayText("Some need for assistance; potential for adequate adjustment; possible retardation");
		   		possibleRespEvt291.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt292 = new CSCPossibleResponseEvent();
		   		possibleRespEvt292.setId("292");
		   		possibleRespEvt292.setDisplayText("Deficiencies severely limit independent functioning; possible retardation");
		   		possibleRespEvt292.setResponseValue("2");
		   		
		   		ArrayList possibleAnswersList29 = new ArrayList();
		   		possibleAnswersList29.add(possibleRespEvt290);
		   		possibleAnswersList29.add(possibleRespEvt291);
		   		possibleAnswersList29.add(possibleRespEvt292);
		   			  		
		   		questionRespEvt29.setId("29");
		   		questionRespEvt29.setText("MENTAL ABILITY");
		   		questionRespEvt29.setRowSequence(9);
		   		questionRespEvt29.setColumnSequence(1);
		   		questionRespEvt29.setRequired(true);
		   		questionRespEvt29.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt29.setResponseNewLine(true);
		   		questionRespEvt29.setEachResponseNewLine(true);
		   		questionRespEvt29.setSelectedResponseId("291");
		   		questionRespEvt29.setSelectedResponseText(null);
		   		questionRespEvt29.setPossibleResponseEvents(possibleAnswersList29);
		   		
		   		/******* End 9 Question ****************/
		   		
		   		
		   		/******* Start 10 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt210 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2100 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2100.setId("2100");
		   		possibleRespEvt2100.setDisplayText("Sound physical health; seldom ill");
		   		possibleRespEvt2100.setResponseValue("0");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt2101 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2101.setId("2101");
		   		possibleRespEvt2101.setDisplayText("Handicap or illness interferes with functioning on a recurring basis");
		   		possibleRespEvt2101.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2102 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2102.setId("2102");
		   		possibleRespEvt2102.setDisplayText("Deficiencies severely limit independent functioning; possible retardation");
		   		possibleRespEvt2102.setResponseValue("2");
		   		
		   		ArrayList possibleAnswersList210 = new ArrayList();
		   		possibleAnswersList210.add(possibleRespEvt2100);
		   		possibleAnswersList210.add(possibleRespEvt2101);
		   		possibleAnswersList210.add(possibleRespEvt2102);
		   			  		
		   		questionRespEvt210.setId("210");
		   		questionRespEvt210.setText("HEALTH");
		   		questionRespEvt210.setRowSequence(10);
		   		questionRespEvt210.setColumnSequence(1);
		   		questionRespEvt210.setRequired(true);
		   		questionRespEvt210.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_RADIO);
		   		questionRespEvt210.setResponseNewLine(true);
		   		questionRespEvt210.setEachResponseNewLine(true);
		   		questionRespEvt210.setSelectedResponseId("2101");
		   		questionRespEvt210.setSelectedResponseText(null);
		   		questionRespEvt210.setPossibleResponseEvents(possibleAnswersList210);
		   		
		   		/******* End 10 Question ****************/
		   		
		   		
		   		/******* Start 11 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt211 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2110 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2110.setId("2110");
		   		possibleRespEvt2110.setDisplayText("Please Select");
		   		possibleRespEvt2110.setResponseValue("");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt2111 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2111.setId("2111");
		   		possibleRespEvt2111.setDisplayText("No apparent dysfunction");
		   		possibleRespEvt2111.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2112 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2112.setId("2112");
		   		possibleRespEvt2112.setDisplayText("Real or perceived situational or minor problems");
		   		possibleRespEvt2112.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2113 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2113.setId("2113");
		   		possibleRespEvt2113.setDisplayText("Real or perceived chronic or severe problems");
		   		possibleRespEvt2113.setResponseValue("3");
		   		
		   		ArrayList possibleAnswersList211 = new ArrayList();
		   		possibleAnswersList211.add(possibleRespEvt2110);
		   		possibleAnswersList211.add(possibleRespEvt2111);
		   		possibleAnswersList211.add(possibleRespEvt2112);
		   		possibleAnswersList211.add(possibleRespEvt2113);
		   		
		   		questionRespEvt211.setId("211");
		   		questionRespEvt211.setText("SEXUAL BEHAVIOR");
		   		questionRespEvt211.setRowSequence(11);
		   		questionRespEvt211.setColumnSequence(1);
		   		questionRespEvt211.setRequired(true);
		   		questionRespEvt211.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt211.setResponseNewLine(true);	   		
		   		questionRespEvt211.setSelectedResponseId("2111");
		   		questionRespEvt211.setSelectedResponseText(null);
		   		questionRespEvt211.setPossibleResponseEvents(possibleAnswersList211);
		   		
		   		/******* End 11 Question ****************/
		   		
		   		
		   		/******* Start 12 Question ******************/
		   		
		   		CSCQuestionResponseEvent questionRespEvt212 = new CSCQuestionResponseEvent();
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2120 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2120.setId("2120");
		   		possibleRespEvt2120.setDisplayText("Please Select");
		   		possibleRespEvt2120.setResponseValue("");
		   			   		
		   		CSCPossibleResponseEvent possibleRespEvt2121 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2121.setId("2121");
		   		possibleRespEvt2121.setDisplayText("Well Adjusted");
		   		possibleRespEvt2121.setResponseValue("1");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2122 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2122.setId("2122");
		   		possibleRespEvt2122.setDisplayText("No Needs");
		   		possibleRespEvt2122.setResponseValue("2");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2123 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2123.setId("2123");
		   		possibleRespEvt2123.setDisplayText("Moderate Needs");
		   		possibleRespEvt2123.setResponseValue("3");
		   		
		   		CSCPossibleResponseEvent possibleRespEvt2124 = new CSCPossibleResponseEvent();
		   		possibleRespEvt2124.setId("2124");
		   		possibleRespEvt2124.setDisplayText("High Needs");
		   		possibleRespEvt2124.setResponseValue("4");
		   		
		   		ArrayList possibleAnswersList212 = new ArrayList();
		   		possibleAnswersList212.add(possibleRespEvt2120);
		   		possibleAnswersList212.add(possibleRespEvt2121);
		   		possibleAnswersList212.add(possibleRespEvt2122);
		   		possibleAnswersList212.add(possibleRespEvt2123);
		   		possibleAnswersList212.add(possibleRespEvt2124);
		   			  		
		   		questionRespEvt212.setId("212");
		   		questionRespEvt212.setText("C.S.O.'S IMPRESSIONS OF DEFENDANT'S NEEDS");
		   		questionRespEvt212.setRowSequence(12);
		   		questionRespEvt212.setColumnSequence(1);
		   		questionRespEvt212.setRequired(true);
		   		questionRespEvt212.setResponseUIControlType(CSCQuestion.UI_CNTRL_TYPE_SELECT);
		   		questionRespEvt212.setResponseNewLine(false);
		   		questionRespEvt212.setSelectedResponseId("2124");
		   		questionRespEvt212.setSelectedResponseText(null);
		   		questionRespEvt212.setPossibleResponseEvents(possibleAnswersList212);
		   		
		   		/******* End 12 Question ****************/
		   		
		   		
		   		ArrayList questionRespList2 = new ArrayList();
		   		
		   		questionRespList2.add(questionRespEvt21);
		   		questionRespList2.add(questionRespEvt22);
		   		questionRespList2.add(questionRespEvt23);
		   		questionRespList2.add(questionRespEvt24);
		   		questionRespList2.add(questionRespEvt25);
		   		questionRespList2.add(questionRespEvt26);
		   		questionRespList2.add(questionRespEvt27);
		   		questionRespList2.add(questionRespEvt28);
		   		questionRespList2.add(questionRespEvt29);
		   		questionRespList2.add(questionRespEvt210);
		   		questionRespList2.add(questionRespEvt211);
		   		questionRespList2.add(questionRespEvt212);
				
		   		questionGrpRespEvt2.setId(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITIAL_NEEDS_QUEST_GRP_ID);
		   		questionGrpRespEvt2.setDisplayText("Needs Assessment");
		   		questionGrpRespEvt2.setDisplayTextDetailHeader(false);	   		
		   		questionGrpRespEvt2.setSequence(2);
		   		questionGrpRespEvt2.setQuestionResponseEvents(questionRespList2);
		   		
		   		/******************************************* Question Group 2 (Needs) End  ********************************/
		   		
		   		ArrayList questionGrpList = new ArrayList();
		   		questionGrpList.add(questionGrpRespEvt1);
		   		questionGrpList.add(questionGrpRespEvt2);
		   		
		   		return questionGrpList;
		   }
		

}
