//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthDSMIVSummaryAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileDSM5CodesResponseEvent;
import messaging.mentalhealth.GetMentalHealthDSMTestDataEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;

public class DisplayMentalHealthDSMIVSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 45D4AEA602E3
    */
   public DisplayMentalHealthDSMIVSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45D49C8303B7
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	TestingSessionForm sessForm = (TestingSessionForm)aForm;
	/*
   	String comments = sessForm.getDsmRec().getAxisIVComments();
	if (!comments.equals("") && comments != null) {
		IUserInfo user = SecurityUIHelper.getUser();
		Name userName = new Name(user.getFirstName(),"",user.getLastName());
		sessForm.getDsmRec().setAxisIVComments(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
	}	  	
	*/
	if(!sessForm.getDsmRec().isMentalHealthNeeded())
	{
		sessForm.getDsmRec().setMentalHealthTreatment(false);
		sessForm.getDsmRec().setMentalHealthNeededStr("No");	
		sessForm.getDsmRec().setMentalHealthTreatmentStr("No");	
	}
	else
	{
		if(!sessForm.getDsmRec().isMentalHealthTreatment())
			sessForm.getDsmRec().setMentalHealthTreatmentStr("No");	
		else
			sessForm.getDsmRec().setMentalHealthTreatmentStr("Yes");	
	}
	sessForm.getDsmRec().setDsmivDesc(SimpleCodeTableHelper.getDescrByCode("TJPC_DSMIV_DIAGNOSIS",sessForm.getDsmRec().getDsmivId()));
	sessForm.setShowIV("N");
	sessForm.setActionType("summary");
    return forward;
   }
   
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {   
	TestingSessionForm sessForm = (TestingSessionForm)aForm;
	sessForm.setShowIV("Y");  // needs to be set to blank whenever DSM V becomes active
	//get the provider and instructor name
	Collection coll = sessForm.getDsmResultsList();
	Iterator iter = coll.iterator();
	while(iter.hasNext())
	{
		DSMIVTestResponseEvent resp = (DSMIVTestResponseEvent)iter.next();
		if(resp.getTestId().equals(sessForm.getSelectedValue()))
		{
			sessForm.setServiceProviderName(resp.getServiceProviderName());
			
			if(resp.getInstructorName() != null) {
			    String name = resp.getInstructorName();

			    // Remove anything after the parenthesis only if the field contains one
			    if (name != null && name.contains("(")) {
				name = name.replaceAll("\\s*\\(.*\\)", "");
			    }
			   
			    resp.setInstructorName(name);
			}
			
			sessForm.setInstructorName(resp.getInstructorName());
			sessForm.setProgramReferralNum(resp.getProgramReferralNum());
			break;
		}
	}
	GetMentalHealthDSMTestDataEvent dEvent = (GetMentalHealthDSMTestDataEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHDSMTESTDATA);
	dEvent.setTestId(sessForm.getSelectedValue());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(dEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	Object obj = MessageUtil.filterComposite(response, DSMIVTestResponseEvent.class);
	if(obj != null)
	{
		DSMIVTestResponseEvent resp = (DSMIVTestResponseEvent)obj;
		
		fillDSMRec(resp, sessForm.getDsmRec());
		if (resp.getCreateDate() != null){
			Date date1 = DateUtil.stringToDate( "02/04/2014", DateUtil.DATE_FMT_1);
			if ( resp.getCreateDate().before(date1))
			{
				sessForm.setShowIV("Y");
			}
			else
				sessForm.setShowIV("N");
		}
	}
	
	/**Retrieve TJPC DSM IV Diagnosis Codes**/
	sessForm.setDsmivDiagnosisList(
			SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.TJPC_DSMIV_DIAGNOSIS));
	
	sessForm.setActionType("view");
	sessForm.setConfirmMessage("");
	return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   private void fillDSMRec(DSMIVTestResponseEvent resp, TestingSessionForm.DSMIVTest dsmTest)
   {
	  Date dsmCreateDate =  resp.getCreateDate();
	  Date date1 = DateUtil.stringToDate( "02/04/2014", DateUtil.DATE_FMT_1);
	  //Bug 13660 Showing old codes from the existing DSM_CODES codetable
	  if ( dsmCreateDate.before(date1)){
		  dsmTest.setTestDate(DateUtil.dateToString(resp.getTestDate(),"MM/dd/yyyy"));
	   	  dsmTest.setAxisIFifth(resp.getAxis1FifthScore());
	   	  dsmTest.setAxisIFifthDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis1FifthScore()));//added all the descriptions for User story 11170
	   	  dsmTest.setAxisIFourth(resp.getAxis1FourthScore());
	   	  dsmTest.setAxisIFourthDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis1FourthScore()));
	   	  dsmTest.setAxisIIIPrimaryScore(resp.getAxis3PrimaryScore());
	   	  dsmTest.setAxisIIIPrimaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis3PrimaryScore()));
	   	  dsmTest.setAxisIIISecondaryScore(resp.getAxis3SecondaryScore());
	   	  dsmTest.setAxisIIISecondaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis3SecondaryScore()));
	   	  dsmTest.setAxisIIPrimaryScore(resp.getAxis2PrimaryScore());
	   	  dsmTest.setAxisIIPrimaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis2PrimaryScore()));
	   	  dsmTest.setAxisIISecondaryScore(resp.getAxis2SecondaryScore());
	   	  dsmTest.setAxisIISecondaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis2SecondaryScore()));
	   	  dsmTest.setAxisIPrimaryScore(resp.getAxis1PrimaryScore());
	   	  dsmTest.setAxisIPrimaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis1PrimaryScore()));
	   	  dsmTest.setAxisISecondaryScore(resp.getAxis1SecondaryScore());
	   	  dsmTest.setAxisISecondaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis1SecondaryScore()));
	   	  dsmTest.setAxisITertiaryScore(resp.getAxis1TertiaryScore());
	   	  dsmTest.setAxisITertiaryScoreDesc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getAxis1TertiaryScore()));
	   	  dsmTest.setAxisIVComments(resp.getAxis4Comments());
	   	  dsmTest.setAxisVScore(resp.getAxis5Score());
	   	  dsmTest.setEconomicProblems(resp.isAxis4EconomicProblems());
	   	  dsmTest.setHealthCareProblems(resp.isAxis4HealthProblems());
	   	  dsmTest.setHousingProblems(resp.isAxis4HousingProblems());
	   	  dsmTest.setIsEducationalProblems(resp.isAxis4EducationalProblems());
	   	  dsmTest.setLegalSystemProblems(resp.isAxis4LegalProblems());
	   	  dsmTest.setOccupationalProblems(resp.isAxis4OccupationalProblems());
	   	  dsmTest.setPsychoEnvProblems(resp.isAxis4PsychosocialProblems());
	   	  dsmTest.setSocioEnvProblems(resp.isAxis4SocialEnvironmentProblems());
	   	  dsmTest.setSuppGrpProblems(resp.isAxis4SupportGroupProblems());	  
	   	  dsmTest.setMentalHealthNeeded(resp.isAxis4MentalHealthNeeds());
	   	  dsmTest.setMentalHealthTreatment(resp.isAxis4MentalHealthTreatment());
	   	  dsmTest.setDsmivId(resp.getDsmivId());  
	   	  dsmTest.setDsmivDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TJPC_DSMIV_DIAGNOSIS, resp.getDsmivId()));
	   	  dsmTest.setMedicalDiagnosis(resp.getMedicalDiagnosis());
	   	  dsmTest.setCreateDate(resp.getCreateDate());
	   	  dsmTest.setDiagnosis10(resp.getDiagnosis10());
	   	  dsmTest.setDiagnosis10Desc(SimpleCodeTableHelper.getDescrByCode("DSM_CODES", resp.getDiagnosis10()));
	  }
	  else{		  
		  //Bug 13660 Showing new codes from the new spreadsheet (DSM_V_CODES codetable)
   	  dsmTest.setTestDate(DateUtil.dateToString(resp.getTestDate(),"MM/dd/yyyy"));
   	  dsmTest.setAxisIFifth(resp.getAxis1FifthScore());
   	  //dsmTest.setAxisIFifthDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis1FifthScore()));//added all the descriptions for User story 11170
   	  dsmTest.setAxisIFifthDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis1FifthScore()));//US #40636
   	  dsmTest.setAxisIFourth(resp.getAxis1FourthScore());
   	  //dsmTest.setAxisIFourthDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis1FourthScore()));
   	  dsmTest.setAxisIFourthDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis1FourthScore())); //US #40636
   	  dsmTest.setAxisIIIPrimaryScore(resp.getAxis3PrimaryScore());
   	  //dsmTest.setAxisIIIPrimaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis3PrimaryScore()));
   	  dsmTest.setAxisIIIPrimaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis3PrimaryScore()));//US #40636
   	  dsmTest.setAxisIIISecondaryScore(resp.getAxis3SecondaryScore());
   	  //dsmTest.setAxisIIISecondaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis3SecondaryScore()));
   	  dsmTest.setAxisIIISecondaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis3SecondaryScore()));//US #40636
   	  dsmTest.setAxisIIPrimaryScore(resp.getAxis2PrimaryScore());
   	 // dsmTest.setAxisIIPrimaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis2PrimaryScore()));
   	  dsmTest.setAxisIIPrimaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis2PrimaryScore()));//US #40636
   	  dsmTest.setAxisIISecondaryScore(resp.getAxis2SecondaryScore());
   	  //dsmTest.setAxisIISecondaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis2SecondaryScore()));
   	  dsmTest.setAxisIISecondaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis2SecondaryScore()));//US #40636
   	  dsmTest.setAxisIPrimaryScore(resp.getAxis1PrimaryScore());
   	  //dsmTest.setAxisIPrimaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis1PrimaryScore()));
   	  dsmTest.setAxisIPrimaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis1PrimaryScore()));//US #40636
   	  dsmTest.setAxisISecondaryScore(resp.getAxis1SecondaryScore());
   	  //dsmTest.setAxisISecondaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis1SecondaryScore()));
   	  dsmTest.setAxisISecondaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getAxis1SecondaryScore()));//US #40636
   	  dsmTest.setAxisITertiaryScore(resp.getAxis1TertiaryScore());
   	  //dsmTest.setAxisITertiaryScoreDesc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getAxis1TertiaryScore()));
   	  dsmTest.setAxisITertiaryScoreDesc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription( resp.getAxis1TertiaryScore()));//US #40636
   	  dsmTest.setAxisIVComments(resp.getAxis4Comments());
   	  dsmTest.setAxisVScore(resp.getAxis5Score());
   	  dsmTest.setEconomicProblems(resp.isAxis4EconomicProblems());
   	  dsmTest.setHealthCareProblems(resp.isAxis4HealthProblems());
   	  dsmTest.setHousingProblems(resp.isAxis4HousingProblems());
   	  dsmTest.setIsEducationalProblems(resp.isAxis4EducationalProblems());
   	  dsmTest.setLegalSystemProblems(resp.isAxis4LegalProblems());
   	  dsmTest.setOccupationalProblems(resp.isAxis4OccupationalProblems());
   	  dsmTest.setPsychoEnvProblems(resp.isAxis4PsychosocialProblems());
   	  dsmTest.setSocioEnvProblems(resp.isAxis4SocialEnvironmentProblems());
   	  dsmTest.setSuppGrpProblems(resp.isAxis4SupportGroupProblems());	  
   	  dsmTest.setMentalHealthNeeded(resp.isAxis4MentalHealthNeeds());
   	  dsmTest.setMentalHealthTreatment(resp.isAxis4MentalHealthTreatment());
   	  dsmTest.setDsmivId(resp.getDsmivId());  
   	  dsmTest.setDsmivDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TJPC_DSMIV_DIAGNOSIS, resp.getDsmivId()));
   	  dsmTest.setMedicalDiagnosis(resp.getMedicalDiagnosis());
   	  dsmTest.setCreateDate(resp.getCreateDate());
   	  dsmTest.setDiagnosis10(resp.getDiagnosis10());
   	  //dsmTest.setDiagnosis10Desc(ui.common.SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES", resp.getDiagnosis10()));
   	dsmTest.setDiagnosis10Desc(ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(resp.getDiagnosis10())); //US #40636
	  }
   	 
   	  
   }
  //added new button to validate code for user story 11170
   public ActionForward validateCode(ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	   {
	   TestingSessionForm sessForm = (TestingSessionForm)aForm;
	   String activeDiagnosisDescField = aRequest.getParameter("activeDiagnosisField");	
	  // Collection codes = CodeHelper.getCodes("DSM_V_CODES", true);
	   String scoreValue = "";
	   String dsmVDiagnosis = "";	
	   if(activeDiagnosisDescField.equals("axisIPrimaryScoreDesc")){		
		 scoreValue = sessForm.getDsmRec().getAxisIPrimaryScore();	
		 dsmVDiagnosis = getDSMVCodesById(scoreValue);
        }
	   else if(activeDiagnosisDescField.equals("axisISecondaryScoreDesc")){
		  scoreValue =  sessForm.getDsmRec().getAxisISecondaryScore();
		  dsmVDiagnosis = getDSMVCodesById(scoreValue);
	   }
	   else if(activeDiagnosisDescField.equals("axisITertiaryScoreDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisITertiaryScore();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("axisIFourthDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisIFourth();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("axisIFifthDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisIFifth();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("axisIIPrimaryScoreDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisIIPrimaryScore();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("axisIISecondaryScoreDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisIISecondaryScore();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("axisIIIPrimaryScoreDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisIIIPrimaryScore();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("axisIIISecondaryScoreDesc")){
			  scoreValue =  sessForm.getDsmRec().getAxisIIISecondaryScore();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }
	   else if(activeDiagnosisDescField.equals("diagnosis10Desc")){
			  scoreValue =  sessForm.getDsmRec().getDiagnosis10();
			  dsmVDiagnosis = getDSMVCodesById(scoreValue);
		   }   
	      	
	   	
	   	if( dsmVDiagnosis == null || dsmVDiagnosis.trim().length()==0 )
	   	{
	   		sessForm.setActionType("");	
	   		
	   		if(activeDiagnosisDescField.equals("axisIPrimaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisIPrimaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisISecondaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisISecondaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisITertiaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisITertiaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIFourthDesc")){
	   			sessForm.getDsmRec().setAxisIFourthDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIFifthDesc")){
	   			sessForm.getDsmRec().setAxisIFifthDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIIPrimaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisIIPrimaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIISecondaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisIISecondaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIIIPrimaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisIIIPrimaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIIISecondaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisIIISecondaryScoreDesc("");
	   		}
	   		if(activeDiagnosisDescField.equals("axisIIISecondaryScoreDesc")){
	   			sessForm.getDsmRec().setAxisIIISecondaryScoreDesc("");
	   		}
	   		sendToErrorPage(aRequest, "error.noRecords");
	   	}
	   	else
	   	{	
	   	    String codeStatus = ComplexCodeTableHelper.getJuvenileDSM5CodeStatus(scoreValue);
	   	 
	   	    if(activeDiagnosisDescField != null && !codeStatus.equals("INACTIVE"))
	   		{
		   		if(activeDiagnosisDescField.equals("axisIPrimaryScoreDesc")){ 
		   			sessForm.getDsmRec().setAxisIPrimaryScoreDesc(dsmVDiagnosis);			   					   		
		   		}
		   		else if(activeDiagnosisDescField.equals("axisISecondaryScoreDesc")){
		   			sessForm.getDsmRec().setAxisISecondaryScoreDesc(dsmVDiagnosis);
		   		}
		   		else if(activeDiagnosisDescField.equals("axisITertiaryScoreDesc")){		
		   			sessForm.getDsmRec().setAxisITertiaryScoreDesc(dsmVDiagnosis);
		   		}
		   		else if(activeDiagnosisDescField.equals("axisIFourthDesc")){
		   			sessForm.getDsmRec().setAxisIFourthDesc(dsmVDiagnosis);   		
		   		}
		   		else if(activeDiagnosisDescField.equals("axisIFifthDesc")){
		   			sessForm.getDsmRec().setAxisIFifthDesc(dsmVDiagnosis);   		
		   		}
		   		else if(activeDiagnosisDescField.equals("axisIIPrimaryScoreDesc")){	
		   			sessForm.getDsmRec().setAxisIIPrimaryScoreDesc(dsmVDiagnosis);
		   		}
		   		else if(activeDiagnosisDescField.equals("axisIISecondaryScoreDesc")){	
		   			sessForm.getDsmRec().setAxisIISecondaryScoreDesc(dsmVDiagnosis);
		   		}
		   		else if(activeDiagnosisDescField.equals("axisIIIPrimaryScoreDesc")){
		   			sessForm.getDsmRec().setAxisIIIPrimaryScoreDesc(dsmVDiagnosis);
		   		}	   	
		   		else if(activeDiagnosisDescField.equals("axisIIISecondaryScoreDesc")){	
		   			sessForm.getDsmRec().setAxisIIISecondaryScoreDesc(dsmVDiagnosis);
		   		}
		   		else if(activeDiagnosisDescField.equals("diagnosis10Desc")){	
		   			sessForm.getDsmRec().setDiagnosis10Desc(dsmVDiagnosis);   		
		   		}
		   		else{
		   			
		   		}
		   	} else {
		   	    
		   	    sendToErrorPage(aRequest, "error.inactiveRecord");
		   	    
		   	}    	   	    			   	
	   	}
	   	if(sessForm.getDsmRec().isMentalHealthNeeded())
	   		sessForm.getDsmRec().setMentalHealthNeededStr("Yes");
	 	if(sessForm.getDsmRec().isMentalHealthTreatment())
	 		sessForm.getDsmRec().setMentalHealthTreatmentStr("Yes");
	 	else
	 		sessForm.getDsmRec().setMentalHealthTreatmentStr("No");
	 
	 		
	   	ActionForward forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
	   	return forward;
	   	
	   }
   
	  public static String getDSMVCodesById(String dsmVId)
	  {
		 String dsmVDiagnosis = "";
		 if(dsmVId != null && dsmVId.length()>0){
			 //dsmVDiagnosis = CodeHelper.getCodeDescription("DSM_V_CODES", dsmVId);
			 dsmVDiagnosis=ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(dsmVId);		
		 }
	  	return dsmVDiagnosis;
	  }
	  
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{	    
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setSecondaryAction("update");
		sessForm.setActionType("update");
		sessForm.setConfirmMessage("");
		return aMapping.findForward(UIConstants.CANCEL);
	}

   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setActionType("");
		return aMapping.findForward(UIConstants.BACK);
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.view", "view");
		keyMap.put("button.validateDiagnosisCode", "validateCode");
	}
}
