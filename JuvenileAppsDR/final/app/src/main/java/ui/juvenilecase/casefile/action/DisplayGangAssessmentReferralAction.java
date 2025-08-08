package ui.juvenilecase.casefile.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetAssessmentReferralEvent;
import messaging.casefile.reply.AssessmentReferralResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvenileProfileDocumentEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.GangAssessmentReferralPrintReportBean;
import ui.juvenilecase.casefile.form.AssessmentReferralForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * 
 * @author sthyagarajan
 * 
 */
public class DisplayGangAssessmentReferralAction extends JIMSBaseAction {

	public DisplayGangAssessmentReferralAction() {

	}

	/**
     * List of Gang Assessment.
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward listGangAssessments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)throws GeneralFeedbackMessageException {
    	AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm)aForm;
    	gangAssessmentRefForm.clear();
    	String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
    	
    	GetAssessmentReferralEvent event = (GetAssessmentReferralEvent) 
    					EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETASSESSMENTREFERRAL);
    	event.setJuvenileId(juvenileNum);
    	CompositeResponse composite = MessageUtil.postRequest(event);
    	List<AssessmentReferralResponseEvent> gangAssessmentsResp = MessageUtil.compositeToList(composite, AssessmentReferralResponseEvent.class);
    	if(gangAssessmentsResp!=null && gangAssessmentsResp.iterator().hasNext()){
    		Collections.sort(gangAssessmentsResp);
    		gangAssessmentRefForm.setGangAssessmentRefList(gangAssessmentsResp);
    		gangAssessmentRefForm.setStatus(gangAssessmentsResp.get(0).getStatus());
    		gangAssessmentRefForm.setCreateUserId(gangAssessmentsResp.get(0).getUserId());
    	}
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }
    
    /**
     * Print Gang Assessment Referral Base Report.
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return 
	 */
	public ActionForward printGangAssessmentReferralReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse){	
		
		AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm)aForm;
		String assessmentId = (String)aRequest.getParameter(UIConstants.ASSESSMENT_ID) ;
		if(assessmentId!=null)
		{
			//Call the get Service to do an update.
			GetAssessmentReferralEvent event = (GetAssessmentReferralEvent) 
			EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETASSESSMENTREFERRAL);
			event.setAssessmentId(assessmentId);
			AssessmentReferralResponseEvent gangAssessmentsResp= (AssessmentReferralResponseEvent) MessageUtil.postRequest(event, AssessmentReferralResponseEvent.class);
			if(gangAssessmentsResp!=null)
			{
				// get data to load report heading - juvenile profile
			    GetJuvenileProfileMainEvent requestEvent = 
			    	(GetJuvenileProfileMainEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
			    requestEvent.setJuvenileNum(gangAssessmentsResp.getJuvenileNum());

			    CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
			    JuvenileProfileDetailResponseEvent detail =
					    	(JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);
					    
			    StringBuffer tempStr = new StringBuffer();
			    if (StringUtils.isNotEmpty(detail.getLastName())) {
			    	tempStr.append(detail.getLastName());
				}
			    if (StringUtils.isNotEmpty(detail.getFirstName())) {
			    	tempStr.append(", ");
			    	tempStr.append(detail.getFirstName());
				}
			    if (StringUtils.isNotEmpty(detail.getMiddleName())){
			    	tempStr.append(" ");
			    	tempStr.append(detail.getMiddleName());
			    }
			    if (StringUtils.isNotEmpty(detail.getNameSuffix())){
			    	tempStr.append(" ");
			    	tempStr.append(detail.getNameSuffix());
			    }
			    gangAssessmentRefForm.setJuvenileName(new Name(detail.getFirstName(), detail.getMiddleName(),detail.getLastName()));
				gangAssessmentRefForm.setJuvenileNum(detail.getJuvenileNum());
				gangAssessmentRefForm.setDateOfBirth(UIConstants.EMPTY_STRING);
				if(detail.getDateOfBirth()!= null){
					gangAssessmentRefForm.setDateOfBirth(DateUtil.dateToString(detail.getDateOfBirth(),UIConstants.DATE_FMT_1));
				}
				gangAssessmentRefForm.setGender(detail.getSex());
				String raceOrEthnicity = detail.getRace();
				if(detail.getEthnicity()!=null && !detail.getEthnicity().equalsIgnoreCase(UIConstants.EMPTY_STRING)){
			   		raceOrEthnicity+="-"+CodeHelper.getFastCodeDescription(
			   				PDCodeTableConstants.JUVENILE_ETHNICITY,detail.getEthnicity());
			   	}
				gangAssessmentRefForm.setRaceOrEthinicity(raceOrEthnicity);
				gangAssessmentRefForm.setLanguage(CodeHelper.getFastCodeDescription(
						PDCodeTableConstants.LANGUAGE,detail.getPrimaryLanguage()));
						
				gangAssessmentRefForm.setPersonMakingRef(gangAssessmentsResp.getPersonMakingReferral());
				gangAssessmentRefForm.setReferralDate(DateUtil.dateToString(gangAssessmentsResp.getReferralDate(),UIConstants.DATE_FMT_1));
				String[] reasonForReferralsCodes = StringUtils.split(gangAssessmentsResp.getReasonForReferralId(),",");
				gangAssessmentRefForm.setSelectedReasonForReferralsList(SimpleCodeTableHelper.getDescsFromSelCodeIds(PDCodeTableConstants.GANG_ASSMNT_REASONFORREFERRAL, reasonForReferralsCodes));
				for (int i = 0; i < reasonForReferralsCodes.length; i++){
					if(reasonForReferralsCodes[i].equalsIgnoreCase(UIConstants.OTHER)){
						gangAssessmentRefForm.getSelectedReasonForReferralsList().add("OTHER - "+gangAssessmentsResp.getOtherReasonForReferral());
						//reset the array on update.
						gangAssessmentRefForm.setSelectedReasonForReferrals(null);
					}
				}
				gangAssessmentRefForm.setGangName(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_NAME,gangAssessmentsResp.getGangNameId()));
				gangAssessmentRefForm.setCliqueSet(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_CLIQUE,gangAssessmentsResp.getCliqueSetId()));
				gangAssessmentRefForm.setOtherGangName(gangAssessmentsResp.getOtherGangName());
				gangAssessmentRefForm.setDescHybrid(gangAssessmentsResp.getDescHybrid());
				gangAssessmentRefForm.setOtherCliqueSet(gangAssessmentsResp.getOtherCliqueSet());
				gangAssessmentRefForm.setPlacementFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,gangAssessmentsResp.getPlacementFacilityId()));
				gangAssessmentRefForm.setLvlOfGangInvolvement(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GANG_ASSESSMENT_LEVELOFGANGINVOLVEMENT,gangAssessmentsResp.getLvlOfGangInvolvementId()));
				gangAssessmentRefForm.setComments(gangAssessmentsResp.getComments());

				//update
				//bug 172247
				if(gangAssessmentsResp.getAcceptedStatus()!=null)
				{
				    if(gangAssessmentsResp.getAcceptedStatus().equalsIgnoreCase("UNABLE"))
					gangAssessmentRefForm.setAcceptedStatus("Unable to Assess Youth");
				    else
					gangAssessmentRefForm.setAcceptedStatus(gangAssessmentsResp.getAcceptedStatus());				
				}
				gangAssessmentRefForm.setRejectionReason(gangAssessmentsResp.getRejectionReason());
				gangAssessmentRefForm.setRecommendations(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RECOMMENDATIONS,gangAssessmentsResp.getRecommendationsId()));
				gangAssessmentRefForm.setConclusion(gangAssessmentsResp.getConclusion());
				gangAssessmentRefForm.setAssessmentIDNumber(assessmentId);
				//add parent notified
				if(gangAssessmentsResp.getParentNotified()!=null)
				{
        				if(gangAssessmentsResp.getParentNotified().equalsIgnoreCase("1"))
        				    gangAssessmentRefForm.setParentNotified("Yes");
        				else if(gangAssessmentsResp.getParentNotified().equalsIgnoreCase("0"))
        				    gangAssessmentRefForm.setParentNotified("No");  
				}

				//add parent notified gang assessment request
				if(gangAssessmentsResp.getParentNotifiedGangAssReq() !=null)
				{
        				if(gangAssessmentsResp.getParentNotifiedGangAssReq().equalsIgnoreCase("1"))
        				    gangAssessmentRefForm.setParentNotifiedGangAssReq("Yes");
        				else if(gangAssessmentsResp.getParentNotifiedGangAssReq().equalsIgnoreCase("0"))
        				    gangAssessmentRefForm.setParentNotifiedGangAssReq("No");  
				}
			}
			ActionForward forward = aMapping.findForward(UIConstants.DETAIL_SUCCESS);
			return forward;
		}
		return null;
	}
	
	/**
	 * Print Gang Assessment BFO Report.
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printGangAssessmentReferralBFOReport(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) {	
		
			AssessmentReferralForm gangAssessmentRefForm = (AssessmentReferralForm)aForm;
			GangAssessmentReferralPrintReportBean reportBean = new GangAssessmentReferralPrintReportBean();
			// get data to load report heading - juvenile profile
		    reportBean.setJuvenileName(gangAssessmentRefForm.getJuvenileName().toString());
		    reportBean.setJuvenileNumber(gangAssessmentRefForm.getJuvenileNum());
			reportBean.setDateOfBirth(gangAssessmentRefForm.getDateOfBirth());
			reportBean.setGender(gangAssessmentRefForm.getGender());
			reportBean.setRaceOrEthinicity(gangAssessmentRefForm.getRaceOrEthinicity());
			if(gangAssessmentRefForm.getLanguage()==null){
				reportBean.setLanguage(UIConstants.EMPTY_STRING);	
			}else{
				reportBean.setLanguage(gangAssessmentRefForm.getLanguage());
			}
			reportBean.setPersonMakingReferral(gangAssessmentRefForm.getPersonMakingRef());
			reportBean.setReferralDate(gangAssessmentRefForm.getReferralDate());
			reportBean.setSelectedReasonForReferralsList(gangAssessmentRefForm.getSelectedReasonForReferralsList());
			reportBean.setGangName(gangAssessmentRefForm.getGangName());
			reportBean.setCliqueSet(gangAssessmentRefForm.getCliqueSet());
			reportBean.setPlacementFacility(gangAssessmentRefForm.getPlacementFacility());
			reportBean.setLvlOfGangInvolvement(gangAssessmentRefForm.getLvlOfGangInvolvement());
			reportBean.setComments(gangAssessmentRefForm.getComments());
			reportBean.setOtherGangName(gangAssessmentRefForm.getOtherGangName());
			reportBean.setOtherCliqueSet(gangAssessmentRefForm.getOtherCliqueSet());

			//update
			//bug 172247
			if(gangAssessmentRefForm.getAcceptedStatus()!=null)
			{
			    if(gangAssessmentRefForm.getAcceptedStatus().equalsIgnoreCase("UNABLE"))
				reportBean.setAcceptedStatus("Unable to Assess Youth");
			    else
				reportBean.setAcceptedStatus(gangAssessmentRefForm.getAcceptedStatus());				
			}
			//reportBean.setAcceptedStatus(gangAssessmentRefForm.getAcceptedStatus());
			reportBean.setRejectionReason(gangAssessmentRefForm.getRejectionReason());
			reportBean.setRecommendations(gangAssessmentRefForm.getRecommendations());
			reportBean.setConclusion(gangAssessmentRefForm.getConclusion());
			reportBean.setStatus(gangAssessmentRefForm.getStatus());
			reportBean.setAssessmentIDNumber(gangAssessmentRefForm.getAssessmentIDNumber());
			reportBean.setParentNotified(gangAssessmentRefForm.getParentNotified());
			
			aRequest.getSession().setAttribute("reportInfo", reportBean);
			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			//let the pdfManager know that the report should be saved in the request during report creation
			aRequest.setAttribute("isPdfSaveNeeded", "true");
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.GANG_ASSESSMENT_REFERRAL_REPORT);
			
			String action=null;
			if(gangAssessmentRefForm.getAction()!=null && !gangAssessmentRefForm.getAction().isEmpty()){
				action=gangAssessmentRefForm.getAction();
			}
			if(action!=null && action.equalsIgnoreCase("update")&& !gangAssessmentRefForm.getStatus().isEmpty())
			{
				byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
				if(pdfDocument!=null){
					// persist a copy of the BFO pdf document
					SaveJuvenileProfileDocumentEvent saveEvent = 
						(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
					saveEvent.setDocument(pdfDocument);
					saveEvent.setDocumentTypeCodeId(UIConstants.GANG_DOCTYPE_CODE);
					saveEvent.setJuvenileNum(gangAssessmentRefForm.getJuvenileNum());
					saveEvent.setEntryDate(DateUtil.getCurrentDate());
					CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
					ReturnException returnException =
						   (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
		
					if (returnException != null) {
						gangAssessmentRefForm.setErrorMsg("Error occured saving GANG REFERRAL Report.");
						gangAssessmentRefForm.setReportGenerated("N");
					}
					aRequest.removeAttribute("pdfSavedReport");
				}
			}
			// remove the pdf report attributes from session when finished saving to database
			aRequest.removeAttribute("isPdfSaveNeeded");
		return null;
	}

	/**
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	

	@Override
	protected void addButtonMapping(Map keyMap) {
        keyMap.put("button.link", "listGangAssessments");
        keyMap.put("button.returnToAssessmentRequest", "listGangAssessments");
        keyMap.put("button.updateAssessmentReferral","updateGangAssessmentReferralDetails");
        keyMap.put("button.view","printGangAssessmentReferralReport");
        keyMap.put("button.print","printGangAssessmentReferralBFOReport");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
	}
	
	
}
