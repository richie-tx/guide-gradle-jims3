package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileDeliquencyHistoryEvent;
import messaging.juvenilecase.reply.ReferralPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.ReferralPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskWeightedResponseEvent;
import messaging.riskanalysis.CheckReferralPreconditionsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm;

public class DisplayNewReferralAssessmentAction extends Action
{

    /**
     * @roseuid 433D89CB021D
     */
    public DisplayNewReferralAssessmentAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        
//        RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
//        RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aForm;
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
    	RiskAssessmentReferralForm refForm = new RiskAssessmentReferralForm();
    	aRequest.getSession().setAttribute("riskReferralForm", refForm);
        JuvenileCasefileForm casefileForm =
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        // original casefileID may have changed if user has selected entry date hyperlink
        // modified 08/21/12 as part of changes for defect 74100
        riskForm.setCasefileID(casefileForm.getSupervisionNum()); 
    	
    	refForm.setIsNewReferral(aRequest.getParameter("isNewReferral"));
    	refForm.setMoreThanOneFailure(false);
    	
        refForm.setRiskMandatoryDetentionCd(UIConstants.EMPTY_STRING);
        riskForm.setMode(UIConstants.EMPTY_STRING);
        riskForm.setModReason(UIConstants.EMPTY_STRING);
        riskForm.setRiskAssessmentDate(new Date());
        
        String casefileID = riskForm.getCasefileID();
        String juvenileNum = riskForm.getJuvenileNum();
        
//        refForm.setCasefileID(casefileID);
//        refForm.setJuvenileNum(juvenileNum);
//
/** Start - Check if Preconditions are met, return reponse events with Questions & Answers 
 *          and JuvenileDeliquencyHistoryEvent 
 **/
        //CompositeResponse response = UIRiskAnalysisHelper.checkReferralPreCondition(casefileID);
        CheckReferralPreconditionsEvent event = (CheckReferralPreconditionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKREFERRALPRECONDITIONS);
        event.setCasefileID(casefileID);
        event.setJuvenileNum(juvenileNum);
        
        String assessmentTypeName = UIConstants.EMPTY_STRING;

        if (refForm.getIsNewReferral().equalsIgnoreCase("true")) {
        	event.setNewReferral(true);
        	assessmentTypeName = RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL_USE_NAME;
        } else {
        	event.setNewReferral(false);
        	assessmentTypeName = RiskAnalysisConstants.RISK_TYPE_PRE_COURT_STAFFING_REFERRAL_USE_NAME;
        }
        
        CompositeResponse response = MessageUtil.postRequest(event);
        ReferralPreConditionFailedResponseEvent errorEvt = (ReferralPreConditionFailedResponseEvent) MessageUtil
                .filterComposite(response, ReferralPreConditionFailedResponseEvent.class);

//        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute(
//                "juvenileCasefileForm");

        //If Preconditions fail, forward elsewhere
        if (errorEvt != null) {
        	
        	// pre conditions not met
            ActionErrors errors = new ActionErrors();
        	if (errorEvt.getMessage() != null && errorEvt.getMessage().length() > 0) {
        		
        		if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE)) {
        			
        			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        					assessmentTypeName, casefileForm.getSupervisionNum()));
        			
        		} else if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NEW_REFERAL_NEEDED)) {
        			
        			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonenewreferralneeded", 
        					RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_AND_CUSTODY_REFERRAL_USE_NAME));
        			
        		} else if (errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.NO_ACTIVE_FORMULA)) {
        			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noActiveRiskFormula",
        					assessmentTypeName, UIConstants.EMPTY_STRING, UIConstants.EMPTY_STRING));

    			}else {
            		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedone", 
            				assessmentTypeName, casefileForm.getSupervisionType(), casefileForm.getCaseStatus()));
            	}
        		
        	} else {
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedone", assessmentTypeName
        				, casefileForm.getSupervisionType(), casefileForm.getCaseStatus()));
        	}
        	
            saveErrors(aRequest, errors);
            return aMapping.findForward("preconditionFailed");
        }
/** End - Check if Preconditions are met, return reponse events with Questions & Answers 
 *          and JuvenileDeliquencyHistoryEvent 
**/   
        
/** Start - Set Static Juvenile Delinquency History Answers on form - Still used for Custody Referrals ("New Referral") **/
        JuvenileDeliquencyHistoryEvent jdhEvt = (JuvenileDeliquencyHistoryEvent) MessageUtil.filterComposite(response,
                JuvenileDeliquencyHistoryEvent.class);
        refForm.setCapitalFelonyTotal(jdhEvt.getTotalCapitalFelony());
        refForm.setMisdClassABTotal(jdhEvt.getTotalClassAB());
        refForm.setMisdClassCTotal(jdhEvt.getTotalClassC());
        refForm.setFelony1Total(jdhEvt.getTotalFelony1());
        refForm.setFelony2Total(jdhEvt.getTotalFelony2());
        refForm.setFelony3Total(jdhEvt.getTotalFelony3());
        refForm.setLevelTotal(jdhEvt.getTotalLevel());
        refForm.setOffensesTotal(jdhEvt.getTotalOffenses());
        refForm.setReferralHistoryTotal(jdhEvt.getTotalReferralsHistory());
        refForm.setStatusCityOrdOffensesTotal(jdhEvt.getTotalStatusCO());
        refForm.setStateJailFelonyTotal(jdhEvt.getTotalStateJailFelony());
        refForm.setAgeFirstReferred(jdhEvt.getAgeFirstReferred());
        refForm.setSeriousnessIndex(jdhEvt.getSeriousnessIndex());
/** End - Set Static Juvenile Delinquency History Answers on form**/

        
/** Start - Set Other Static Answers on Form - Still used for Custody Referrals ("New Referral") **/ 
        // Get Static Refferral Information Answers
        ReferralPrefillResponseEvent prefillEvent = (ReferralPrefillResponseEvent) MessageUtil.filterComposite(
                response, ReferralPrefillResponseEvent.class);
        
        refForm.setPendingCourt(prefillEvent.getCourtStatus());
        refForm.setCurrentlyOnProbation(prefillEvent.getProbationStatus());
        refForm.setPendingCourtVOP(prefillEvent.getPetitionAllegation());
        refForm.setNumberOfCharges(String.valueOf(prefillEvent.getNumofCharges()));   
        
/*        List<JuvenileDetentionFacilitiesResponseEvent> detentionRecs = MessageUtil.compositeToList(response, JuvenileDetentionFacilitiesResponseEvent.class);

        if (detentionRecs.size() > 1){
	        List sortedList = new ArrayList(detentionRecs);
	        ArrayList sortFields = new ArrayList();
	        sortFields.add(new ComparatorChain(new BeanComparator("admitDate")));
	        ComparatorChain multiSort = new ComparatorChain(sortFields);
	        Collections.sort(sortedList, multiSort);
	        detentionRecs = sortedList;
	        sortedList = null;
	        sortFields = null;
	        multiSort = null;
        }
*/        
/** End - Set Other Static Answers on Form **/

/** Start - Get Dynamic questions & answners **/
        List <RiskQuestionResponseEvent> qaGroup = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
        
        if (qaGroup.size() > 0){
        	RiskQuestionResponseEvent qre = qaGroup.get(0);
        	riskForm.setRiskFormulaId(qre.getRiskFormulaId());
        } else {
        	riskForm.setRiskFormulaId(UIConstants.EMPTY_STRING);
        }
//        Iterator qaGroupIter = qaGroup.iterator();
/** End - Get Dynamic questions & answners **/
        
 /** Start - Set Dynamic List of Refferal Information Question & Answers **/
        List myList = new ArrayList(); //Master List of Questions & Answers
//        qaGroupIter = null;
//        qaGroupIter = qaGroup.iterator();
        RiskQuestionResponseEvent question = null;
        
        for (int i = 0; i < qaGroup.size(); i++) 
        {
            //RiskQuestionResponseEvent question = (RiskQuestionResponseEvent) qaGroupIter.next();
        	question = qaGroup.get(i);
            
            /**
             * 
            **/
            if (question.getUiControlType().equalsIgnoreCase("QUESTIONHEADER")) {
            	preSetOnSelectedAnswerId("", question);
            }
            
            /** The application keeps track of certain questions via a control code placed in the question
             *  There are certain questions that must remain dynamic but also be preset. These questions
             *  can still be modified and removed. However if a question that must be preset with data 
             *  needs to be added. It must have a control code and be added to this list of if/else.
             * 
            **/
            if (question.getControlCode() != null && question.getControlCode().length() > 0) {
    			question.setUseAnswerText(true);
        		if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_CFT)) {
        			preSetOnSelectedAnswerId(jdhEvt.getTotalCapitalFelony(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_MAB)) {
        			preSetOnSelectedAnswerId(jdhEvt.getTotalClassAB(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_MCC)) {
           			preSetOnSelectedAnswerId(jdhEvt.getTotalClassC(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_FT1)) {
          			preSetOnSelectedAnswerId(jdhEvt.getTotalFelony1(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_FT2)) {
         			preSetOnSelectedAnswerId(jdhEvt.getTotalFelony2(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_FT3)) {
         			preSetOnSelectedAnswerId(jdhEvt.getTotalFelony3(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_LVT)) {
        			//preSetOnSelectedAnswerId(jdhEvt.getTotalLevel(), question);
        			question.setSelectedAnswerID(new Integer(jdhEvt.getTotalLevel()).toString());
        			question.setUseAnswerText(false);
        		}else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.SCHOOL)) {
    				question.setSelectedAnswerID(prefillEvent.getSchool());
    				question.setUseAnswerText(false);
        		}else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.GRADE_LEVEL)) {
    				question.setSelectedAnswerID(prefillEvent.getGrade());
    				question.setUseAnswerText(false);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_RHT)) {
           			//preSetOnSelectedAnswerId(jdhEvt.getTotalReferralsHistory(), question);
        			question.setSelectedAnswerID(new Integer(jdhEvt.getTotalReferralsHistory()).toString());
        			question.setUseAnswerText(false);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_TOF)) {
         			preSetOnSelectedAnswerId(jdhEvt.getTotalOffenses(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_COT)) {
         			preSetOnSelectedAnswerId(jdhEvt.getTotalStatusCO(), question);
         		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_SJF)) {
         			preSetOnSelectedAnswerId(jdhEvt.getTotalStateJailFelony(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_YCP)) {
        			preSetOnSelectedAnswerId(prefillEvent.getCourtStatus(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_COP)) {
        			preSetOnSelectedAnswerId(prefillEvent.getProbationStatus(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_CCS)) {
        			preSetOnSelectedAnswerId(prefillEvent.getPetitionAllegation(), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_NOC)) {
        			preSetOnSelectedAnswerId(String.valueOf(prefillEvent.getNumofCharges()), question);
        		} else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.REFERRAL_CONTROL_CODE_TOA)) {
        			preSetOnSelectedAnswerId(RiskAnalysisConstants.NON_CUSTODY, question);
        		}else if (question.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_REFERRALS_INFORMATION)) {
        			question.setUseAnswerText(false);
        			question.setSelectedAnswerID(prefillEvent.getReferrals());
/*        	        JuvenileDetentionFacilitiesResponseEvent jdfre = null;
        	        StringBuffer sb = new StringBuffer();
        	        for (int j = 0; j < detentionRecs.size(); j++) {
        				jdfre = detentionRecs.get(j);
        				sb.append(jdfre.getReferralNumber());
        				sb.append(" ");
        				sb.append(jdfre.getFacilityCode());
        				sb.append(" ");
        				sb.append(jdfre.getAdmitReason());
        				sb.append(" ");
        				sb.append(DateUtil.dateToString(jdfre.getAdmitDate(), DateUtil.DATE_FMT_1));
        				if (j > 0){
        					sb.append(", ");
        				}
        			}
        	        if (sb.length() > 0){        	        
        	        	question.setSelectedAnswerID(sb.toString());
        	        } else {
        	        	question.setSelectedAnswerID("NO DETENTION FACILITIES WITHIN THE LAST THREE DAYS");
        	        }
        	        detentionRecs = null;
        	        sb = null;

        	        */
        	       
        		}
            }
            
            myList.add(question);
        }
/** End - Set Dynamic List of Refferal Information Question & Answers **/

/** Start - Set master list questions & answers (Static & Dynamic) in refForm **/
        riskForm.setQuestionAnswers(myList);
/** End - Set master list questions & answers (Static & Dynamic) in refForm **/
        riskForm.setAction("newPCSRisk");
        
        if (refForm.getIsNewReferral().equalsIgnoreCase("true")) {
			return aMapping.findForward("successNew");
		} else {
			return aMapping.findForward("successOld");
		}
    }

	/**
	 * Sets the selectedAnwserId for information that has to be set before display on the screen
	 * i.e. Juvenile History and Prefill Information
	 * @param prefillEvent
	 * @param question
	 */
	private void preSetOnSelectedAnswerId(
			String stringToCompare,
			RiskQuestionResponseEvent question) {
		Iterator<RiskWeightedResponseEvent> iter = question.getAnswers().iterator();
		if( iter != null && iter.hasNext() ) {
			while(iter.hasNext()) {
				RiskWeightedResponseEvent riskWeightedResponseEvent = iter.next();
				
				if (stringToCompare.equalsIgnoreCase(riskWeightedResponseEvent.getAnswerText())) {
					question.setSelectedAnswerID(String.valueOf(riskWeightedResponseEvent.getWeightedResponseID()));
					break;
				}
				
			}
		}
	}

}
