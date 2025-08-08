package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetAssessmentReferralEvent;
import messaging.casefile.SaveAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasefileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.UIJuvenileAssessmentReferralHelper;
import ui.juvenilecase.casefile.form.AssessmentReferralForm;

public class HandleGangAssessmentReferralCreateAction extends JIMSBaseAction
{
	 /**
	   * Default constructor
	   */
	   public HandleGangAssessmentReferralCreateAction() 
	   {
		       
	   }
	   
	   @Override
		protected void addButtonMapping(Map keyMap) {
		   	keyMap.put("button.createGangAssessmentRef", "createGangAssessmentReferral");
		   	keyMap.put("button.updateAssessment", "updateGangAssessmentReferralDetails");
	        keyMap.put("button.next", "next");
	        keyMap.put("button.finish", "finish");
	        keyMap.put("button.returnToAssessmentRequestList", "gangAssessmentReferralDetails");
	        keyMap.put("button.back", "back");
	        keyMap.put("button.cancel", "cancel");
		}
	   
	   /**
	    * Executed when the gang is added to the list. 
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward createGangAssessmentReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   	AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm) aForm;
		   	gangAssessmentRefForm.clear();
			gangAssessmentRefForm.clearAssessmentReferralForm();
			
	    	//Prefill profile details
			UIJuvenileAssessmentReferralHelper.populateProfileDetails(gangAssessmentRefForm,aRequest);
			gangAssessmentRefForm.setAction(UIConstants.CREATE);
			gangAssessmentRefForm.setSecondaryAction(UIConstants.DETAILS);
	    	return aMapping.findForward(UIConstants.SUCCESS);
	   }
	   
	   /**
	    * Set all the selected values in the summary page. 
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return
	    */
	   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse){
		   
			AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm) aForm;
				//Gang
				if(gangAssessmentRefForm.getGangNameId()!=null&&!gangAssessmentRefForm.getGangNameId().equals(UIConstants.EMPTY_STRING)){
					if(!gangAssessmentRefForm.getGangNameId().equalsIgnoreCase(UIConstants.OTHER)){
						gangAssessmentRefForm.setGangName(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_NAME,gangAssessmentRefForm.getGangNameId()));
					}else{
						gangAssessmentRefForm.setGangName("OTHER - "+gangAssessmentRefForm.getOtherGangName().toUpperCase());
					}
				}else{
					if(gangAssessmentRefForm.getGangName().equalsIgnoreCase("OTHER")){
						gangAssessmentRefForm.setGangName("OTHER - "+gangAssessmentRefForm.getOtherGangName().toUpperCase());
					}else{//on click of please select. reset the values
						if(!gangAssessmentRefForm.getAction().equals("update")){
							gangAssessmentRefForm.setGangName("");
						}
					}
				}
				//Clique
				if(gangAssessmentRefForm.getCliqueSetId()!=null && !gangAssessmentRefForm.getCliqueSetId().equals(UIConstants.EMPTY_STRING)){
					if (!gangAssessmentRefForm.getCliqueSetId().equalsIgnoreCase(UIConstants.OTHER)){
						gangAssessmentRefForm.setCliqueSet(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_CLIQUE,gangAssessmentRefForm.getCliqueSetId()));
					}else{
						gangAssessmentRefForm.setCliqueSet("OTHER - "+gangAssessmentRefForm.getOtherCliqueSet().toUpperCase());
					}
				}else {
					if(gangAssessmentRefForm.getCliqueSet().equalsIgnoreCase("OTHER")){
						gangAssessmentRefForm.setCliqueSet("OTHER - "+gangAssessmentRefForm.getOtherCliqueSet().toUpperCase());
					}else{ //on click of please select. reset the values
						if(!gangAssessmentRefForm.getAction().equals("update")){
						gangAssessmentRefForm.setCliqueSet("");
						}
					}
				}
				
				if(gangAssessmentRefForm.getPlacementFacilityId()!=null&&!gangAssessmentRefForm.getPlacementFacilityId().equals(UIConstants.EMPTY_STRING)){
					gangAssessmentRefForm.setPlacementFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,gangAssessmentRefForm.getPlacementFacilityId()));
				}
				String[] reasonForReferralsCodes = gangAssessmentRefForm.getSelectedReasonForReferrals();
				
				//reset values on click of back only for check box clearance.
				if(!gangAssessmentRefForm.getAction().equalsIgnoreCase("update")){
					gangAssessmentRefForm.setReasonForReferralId("");
					gangAssessmentRefForm.setSelectedReasonForReferralsList(new ArrayList());
					if(gangAssessmentRefForm.getOtherReasonForReferralTxt()!=null && gangAssessmentRefForm.getOtherReasonForReferralTxt().equals("")){
						gangAssessmentRefForm.setOtherReasonForReferral("");
					}
				}
				if(reasonForReferralsCodes!=null&& reasonForReferralsCodes.length!=0){
					gangAssessmentRefForm.setSelectedReasonForReferralsList(SimpleCodeTableHelper.getDescsFromSelCodeIds(PDCodeTableConstants.GANG_ASSMNT_REASONFORREFERRAL, reasonForReferralsCodes));
					UIJuvenileAssessmentReferralHelper.setReasonForReferralId(reasonForReferralsCodes,gangAssessmentRefForm);
				}
				reasonForReferralsCodes=null; // reset the array
				gangAssessmentRefForm.setSelectedReasonForReferrals(new String[0]); // reset the array
				
				if(gangAssessmentRefForm.getLvlOfGangInvolvementId()!=null && !gangAssessmentRefForm.getLvlOfGangInvolvementId().equals(UIConstants.EMPTY_STRING)){
					gangAssessmentRefForm.setLvlOfGangInvolvement(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_ASSESSMENT_LEVELOFGANGINVOLVEMENT,gangAssessmentRefForm.getLvlOfGangInvolvementId()));
				}
				gangAssessmentRefForm.setRecommendations(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RECOMMENDATIONS,gangAssessmentRefForm.getRecommendationsId()));
				gangAssessmentRefForm.setSecondaryAction(UIConstants.DETAILS);
				
			return aMapping.findForward(UIConstants.NEXT);
	   }

	   /**
	    * Save the selected value in the JCAssessments table
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return
	    */
	   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse){

		   AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm) aForm;

		   SaveAssessmentReferralEvent requestEvent = (SaveAssessmentReferralEvent)EventFactory.
				   getInstance(JuvenileCasefileControllerServiceNames.SAVEASSESSMENTREFERRAL);
		   
		   //for update
		   if(gangAssessmentRefForm.getAssessmentIDNumber()!=null && !gangAssessmentRefForm.getAssessmentIDNumber().equals(UIConstants.EMPTY_STRING)){ 
			   requestEvent = UIJuvenileAssessmentReferralHelper.updateGangAssessmentReferral(gangAssessmentRefForm);
		   }else{ //for save.
			   requestEvent = UIJuvenileAssessmentReferralHelper.createGangAssessmentReferral(gangAssessmentRefForm);
		   }
		   
		   CompositeResponse response =  MessageUtil.postRequest(requestEvent);
		   AssessmentReferralResponseEvent event = (AssessmentReferralResponseEvent) MessageUtil.filterComposite(response,AssessmentReferralResponseEvent.class);
		   ReturnException returnException =(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

			if (returnException == null && event!=null) 
			{
				if(gangAssessmentRefForm.getAction().equalsIgnoreCase(UIConstants.CREATE)){
					//create activity
					String activityCode=ActivityConstants.ASSESSMENT_REFERRAL_CREATED;
					String comments="ASSESSMENT REFERRAL CREATED";
					UIJuvenileAssessmentReferralHelper.createActivityForAssessmentCreation(gangAssessmentRefForm,activityCode,comments);
					//Send notification and email.
					UIJuvenileAssessmentReferralHelper.sendGangAssessmentNotificationForGangUnitOfficer(event,gangAssessmentRefForm);
					
				}else{
					if(gangAssessmentRefForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)){
						//update activity.
						String activityCode=ActivityConstants.ASSESSMENT_REFERRAL_SUBMITTED;
						String comments="ASSESSMENT REFERRAL SUBMITTED";
						UIJuvenileAssessmentReferralHelper.createActivityForAssessmentCreation(gangAssessmentRefForm,activityCode,comments);
						//Send notification and email.
						UIJuvenileAssessmentReferralHelper.sendGangAssessmentNotificationForProbationOfficer(event,gangAssessmentRefForm);
					}
				}
			} 
			if(!gangAssessmentRefForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)){
				gangAssessmentRefForm.setAction(UIConstants.CREATE);
			}
			gangAssessmentRefForm.setSecondaryAction(UIConstants.CONFIRM);
		   return aMapping.findForward(UIConstants.FINISH);
	   }
	   
	   
	   /**
	    * View Gang Assessment Referral Details.
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return
	    */
	   public ActionForward gangAssessmentReferralDetails(ActionMapping aMapping, ActionForm aForm,
				HttpServletRequest aRequest, HttpServletResponse aResponse)
		{
		   AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm) aForm;
		   gangAssessmentRefForm.clearAssessmentReferralForm();
		   return(aMapping.findForward(UIConstants.VIEW_DETAIL));
		}
	   
	   /**
	    * Update Gang Assessment Referral Details.
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return
	    */
	   public ActionForward updateGangAssessmentReferralDetails(ActionMapping aMapping, ActionForm aForm,
				HttpServletRequest aRequest, HttpServletResponse aResponse)
		{
		   AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm) aForm;
		   gangAssessmentRefForm.clear();
		   UIJuvenileAssessmentReferralHelper.populateProfileDetails(gangAssessmentRefForm,aRequest);
		   //populate selected assessment details.
		   if(gangAssessmentRefForm.getUpdateAssessment()!=null){
			   String assessmentId=gangAssessmentRefForm.getUpdateAssessment();
			   //Call the get Service to do an update.
				GetAssessmentReferralEvent event = (GetAssessmentReferralEvent) 
				EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETASSESSMENTREFERRAL);
				event.setAssessmentId(assessmentId);
				AssessmentReferralResponseEvent gangAssessmentsResp= (AssessmentReferralResponseEvent) MessageUtil.postRequest(event, AssessmentReferralResponseEvent.class);
				if(gangAssessmentsResp!=null){
					gangAssessmentRefForm.setAssessmentIDNumber(assessmentId);
					gangAssessmentRefForm.setReferralDate(DateUtil.dateToString(gangAssessmentsResp.getReferralDate(),UIConstants.DATE_FMT_1));
					gangAssessmentRefForm.setGangName(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_NAME,gangAssessmentsResp.getGangNameId()));
					gangAssessmentRefForm.setDescHybrid(gangAssessmentsResp.getDescHybrid());
					gangAssessmentRefForm.setOtherGangName(gangAssessmentsResp.getOtherGangName());
					gangAssessmentRefForm.setOtherCliqueSet(gangAssessmentsResp.getOtherCliqueSet());
					gangAssessmentRefForm.setCliqueSet(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_CLIQUE,gangAssessmentsResp.getCliqueSetId()));
					String[] reasonForReferralsCodes = StringUtils.split(gangAssessmentsResp.getReasonForReferralId(), ",");
					gangAssessmentRefForm.setSelectedReasonForReferralsList(SimpleCodeTableHelper.getDescsFromSelCodeIds(PDCodeTableConstants.GANG_ASSMNT_REASONFORREFERRAL, reasonForReferralsCodes));
					for (int i = 0; i < reasonForReferralsCodes.length; i++) {
						if(reasonForReferralsCodes[i].equalsIgnoreCase(UIConstants.OTHER)){
							gangAssessmentRefForm.getSelectedReasonForReferralsList().add("OTHER - "+gangAssessmentsResp.getOtherReasonForReferral());
							//reset the array on update.
							gangAssessmentRefForm.setSelectedReasonForReferrals(null);
						}
					}	
					gangAssessmentRefForm.setSelectedReasonForReferralsList(gangAssessmentRefForm.getSelectedReasonForReferralsList());
					gangAssessmentRefForm.setPlacementFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,gangAssessmentsResp.getPlacementFacilityId()));
					gangAssessmentRefForm.setLvlOfGangInvolvement(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_ASSESSMENT_LEVELOFGANGINVOLVEMENT,gangAssessmentsResp.getLvlOfGangInvolvementId()));
					gangAssessmentRefForm.setComments(gangAssessmentsResp.getComments());
					
					if(gangAssessmentsResp.getParentNotified() != null && !gangAssessmentsResp.getParentNotified().equals("")){
					    
					    if(gangAssessmentsResp.getParentNotified().equalsIgnoreCase("1"))
						gangAssessmentRefForm.setParentNotified("Yes");
					    else if(gangAssessmentsResp.getParentNotified().equalsIgnoreCase("0"))
						gangAssessmentRefForm.setParentNotified("No");					    
					}
					
					if(gangAssessmentsResp.getParentNotifiedGangAssReq() != null && !gangAssessmentsResp.getParentNotifiedGangAssReq().equals("")){
					    
					    if(gangAssessmentsResp.getParentNotifiedGangAssReq().equalsIgnoreCase("1"))
						gangAssessmentRefForm.setParentNotifiedGangAssReq("Yes");
					    else if(gangAssessmentsResp.getParentNotifiedGangAssReq().equalsIgnoreCase("0"))
						gangAssessmentRefForm.setParentNotifiedGangAssReq("No");					    
					}
					
				}
		   }
		   gangAssessmentRefForm.setAction(UIConstants.UPDATE);
		   return aMapping.findForward(UIConstants.SUCCESS);
		}
	   
	   /**
	    * Cancel the assessment.
	    */
		public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse) {
			ActionForward forward = aMapping.findForward(UIConstants.VIEW_DETAIL);
			return forward;
		}
}
