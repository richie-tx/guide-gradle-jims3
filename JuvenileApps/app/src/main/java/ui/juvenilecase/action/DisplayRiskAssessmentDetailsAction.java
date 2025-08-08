//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayRiskAssessmentDetailsAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.SaveJuvenileProfileDocumentEvent;
import messaging.juvenilecase.reply.CourtReferralAssessmentEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskSuggestedCasePlanDomainResponseEvent;
import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import messaging.riskanalysis.reply.RiskAssessmentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayRiskAssessmentDetailsAction extends Action
{
	/**
	 * @roseuid 433D8A0701B5
	 */
	public DisplayRiskAssessmentDetailsAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433C3D3E00CA
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm)aForm;
		
		//Takes user back a page
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.BACK)) )
		{
			riskForm.setAction(UIConstants.EMPTY_STRING);
			return back(aMapping);
		}
		//Cancels and goes back to a preset page 
		else if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CANCEL)) )
		{
			riskForm.setAction(UIConstants.EMPTY_STRING);
			return cancel(aMapping);
		}

		ActionForward forward = null;
		CompositeResponse composite = null;
		
		if (riskForm.getAction() == null 
				|| riskForm.getAction().equals(UIConstants.EMPTY_STRING)){
			
			GetRiskAssessmentDetailsEvent event = (GetRiskAssessmentDetailsEvent)
				EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETRISKASSESSMENTDETAILS);
			String assessmentId = aRequest.getParameter("assessmentId");
			String assessmentType = aRequest.getParameter("assessmentType");
			String action =aRequest.getParameter("action");
			riskForm.setAction(action);

			if( assessmentId == null )
			{
				assessmentId = riskForm.getAssessmentId();
			}
			if( assessmentType == null )
			{
				assessmentType = riskForm.getRiskAssessmentType();
			}
		
			event.setAssessmentID(assessmentId);
			event.setAssessmentType(assessmentType);

			composite = MessageUtil.postRequest(event);
			//Gets the actual reponses to questions from the composite response and places them in a collection for the UI to display
			Collection riskAssessmentDetails = MessageUtil.compositeToCollection(composite, RiskAnswerResponseEvent.class);
			riskForm.setAssessmentDetailsResponseList(riskAssessmentDetails);
		
			RiskAssessmentResponseEvent re = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);
			riskForm.setRiskFormulaId(String.valueOf(re.getRiskFormulaId()));
			riskForm.setRiskAssessmentDate(re.getEnteredDate());
			riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(re.getEnteredDate()));
			riskForm.setModReason(re.getModReason());
			riskForm.setCasefileID(re.getCasefileId());
			
			if (re.getRecommendations() != null && re.getRecommendations().size() > 1){
				List sortedList = new ArrayList(re.getRecommendations());
				ArrayList sortFields = new ArrayList();
				sortFields.add(new ComparatorChain(new BeanComparator("resultGroupDisplayDesc")));
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort(sortedList, multiSort);
				riskForm.setRecommendations(sortedList);
			} else {
				riskForm.setRecommendations(re.getRecommendations());
			}
			riskForm.setRiskAssessmentType(re.getAssessmentType());
			riskForm.setRiskAssessmentTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE, re.getAssessmentType()));
		
			//Three UI list for three different types of override reasons are created
			List releaseOverrideReasons = new ArrayList();
			List detentionOverrideReasons = new ArrayList();
			List overrideOtherReasons = new ArrayList();

			//Get the overridden reasons from the code table to place in the UI List
			List overRiddenReasons = CodeHelper.getCodes(PDCodeTableConstants.OVERRIDDENREASON, true);
		
			//Sort Overridden Reasons
			ArrayList sortFields = new ArrayList();
			sortFields.add(new BeanComparator("code"));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(overRiddenReasons, multiSort);
		
			//Places the overRiddenReasons from the code table in the UI list
			//Iterator<CodeResponseEvent> overRiddenReasonsIter = overRiddenReasons.iterator();
			List <CodeResponseEvent> overRiddenReasonList = CollectionUtil.iteratorToList(overRiddenReasons.iterator());
			
			//while( overRiddenReasonsIter.hasNext() )
			for (int i = 0; i < overRiddenReasonList.size(); i++) 
			{
				//CodeResponseEvent overRiddenReasonResponse = overRiddenReasonsIter.next();
				CodeResponseEvent overRiddenReasonResponse = overRiddenReasonList.get(i);
				char overRiddenReasonFirstChar = overRiddenReasonResponse.getCode().charAt(0);
			
			
				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					releaseOverrideReasons.add(overRiddenReasonResponse);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					detentionOverrideReasons.add(overRiddenReasonResponse);
				}
			
			}

			//Set the UI overidden list
			riskForm.setReleaseOverrideReasons(releaseOverrideReasons);
			riskForm.setDetentionOverrideReasons(detentionOverrideReasons);
			riskForm.setOverrideOtherReasons(overrideOtherReasons);
		}
		
		this.setOverrideInfo(composite, riskForm);

		riskForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
		
		if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL) )
		{
			forward = this.custodyNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL) )
		{
			forward = this.nonCustodyNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_PREA_FOLLOW_UP ) || riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_INTERVIEW ))
		{
			forward = this.interviewNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
			}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL) )
		{
			forward = this.residentialNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_COMMUNITY)
			|| riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_OLD_COMMUNITY))
		{
			forward = this.communityNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_PROGRESS) )
		{
			forward = this.progressNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS) 
				|| riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_PROGRESS_COPY))
		{
			forward = this.progressNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
					"Residential Progress", composite);	
			
		
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN) )
		{
			forward = this.gangNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
					riskForm.getRiskAssessmentType(), composite);	
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_PREA_FOLLOW_UP) )
		{
			forward = this.gangNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
					riskForm.getRiskAssessmentType(), composite);	
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_GANG)
				|| riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_GANG_COPY)) 
		{
				forward = this.gangNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
						riskForm.getRiskAssessmentType(), composite);	
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_TESTING) )
		{
			forward = this.testingNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		else if( riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE) 
			|| riskForm.getRiskAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE) )
		{
			forward = this.courtReferralNavigateSetInfoAndCheckOverride(aMapping, aForm, aRequest, aResponse, riskForm,
				riskForm.getRiskAssessmentType(), composite);
		}
		
		return forward;
	}
	
	/**
	 * @param composite
	 * @param riskForm
	 */
	private void setOverrideInfo(CompositeResponse composite, RiskAnalysisForm riskForm){
		RiskAssessmentResponseEvent rare = null;
		if (composite != null){
			rare = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);
		}

		if (composite != null && rare != null){
			if( notNullNotEmptyString( rare.getOverRiddenReasonCd() )  || 
				notNullNotEmptyString( rare.getOverRiddenReasonOther()) )
			{

			//if( commForm.getAction().equals( UIConstants.EDIT) )
//				if( riskForm.getSecondaryAction() != null 
//						&& riskForm.getSecondaryAction().equals( UIConstants.EDIT) )
//				{
					riskForm.setRecommendationOverridden(rare.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(rare.getOverRiddenReasonCd());
				
					String overiddenReasonFirstTwo = rare.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(rare.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(rare.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					} else {
						riskForm.setOverRiddenReasonDetentionOther(UIConstants.EMPTY_STRING);
						riskForm.setOverRiddenReasonOther(UIConstants.EMPTY_STRING);
					}

			} else {
				riskForm.setOverRiddenReasonDetentionOther(UIConstants.EMPTY_STRING);
				riskForm.setOverRiddenReasonOther(UIConstants.EMPTY_STRING);
				riskForm.setRecommendationOverridden(false);
				riskForm.setOverRiddenReasonCd(UIConstants.EMPTY_STRING);
				riskForm.setOverRiddenReasonDesc(UIConstants.EMPTY_STRING);
				riskForm.setOverRideType(UIConstants.EMPTY_STRING);
			}
		}

		String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
		riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

		String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

		if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
		{
			char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);
				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
			{
				riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
			}
			else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
			{
				riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
			}
		}
	}
	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward communityNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		//RiskAssessmentCommunityForm commForm = new RiskAssessmentCommunityForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			//needs fixing!!!
			SubmitCommunityAssessmentAction submitCommunityAssessmentAction = new SubmitCommunityAssessmentAction();
			submitCommunityAssessmentAction.updateCommunityRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);
			//above needs fixing!!
			//commForm = (RiskAssessmentCommunityForm)aRequest.getSession().getAttribute("riskCommunityForm");
			//commForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			//aRequest.getSession().setAttribute("riskCommunityForm", commForm);
			return aMapping.findForward("communitySuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//commForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//commForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}
		
		//Sets the Assessment Type
		//commForm.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_COMMUNITY);
		//Gets the Referral Assement Details from composite
		//CommunityAssessmentEvent progAssessEvent = (CommunityAssessmentEvent)MessageUtil.filterComposite(composite, CommunityAssessmentEvent.class);

		if( composite != null)
		{
			//riskForm.setCommunityAssessEvent(progAssessEvent);
			//riskForm.setRiskAssessmentType(assessmentType);
			//riskForm.setRiskAssessmentDate(progAssessEvent.getEnteredDate());
			//riskForm.setFinalScore(String.valueOf(progAssessEvent.getFinalScore()));
			//riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(progAssessEvent.getEnteredDate()));
			//For Updating, currently only specific to Custody Refferal but will have to added to all types in the future
			//refForm.setModReason(refAssessEvent.getModReason());
			
			//commForm.setRecommendation(progAssessEvent.getRecommendation());
			//riskForm.setRecommendations(progAssessEvent.getRecommendations());

			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( progAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( progAssessEvent.getOverRiddenReasonOther()) )
			{
				//if( commForm.getAction().equals( UIConstants.EDIT) )
				if( riskForm.getSecondaryAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(progAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(progAssessEvent.getOverRiddenReasonCd());
					
					String overiddenReasonFirstTwo = progAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(progAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(progAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
				
			}*/
		}
		
		//aRequest.getSession().setAttribute("riskCommunityForm", commForm);

		forward = aMapping.findForward("communitySuccess");
		return forward;
		
	}
	
	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward progressNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		//RiskAssessmentProgressForm progForm = new RiskAssessmentProgressForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			//needs fixing!!!
			SubmitProgressAssessmentAction submitProgressAssessmentAction = new SubmitProgressAssessmentAction();
			submitProgressAssessmentAction.updateProgressRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);
			//above needs fixing!!
			//progForm = (RiskAssessmentProgressForm)aRequest.getSession().getAttribute("riskProgressForm");
			//progForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			//aRequest.getSession().setAttribute("riskProgressForm", progForm);
			return aMapping.findForward("progressSuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//progForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//progForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}
		
		//Sets the Assessment Type
		if(riskForm.getRiskAssessmentType()==null || riskForm.getRiskAssessmentType().equals(""))
			riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_PROGRESS);
		//Gets the Referral Assement Details from composite
		//RiskAssessmentResponseEvent progAssessEvent = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);

		if( composite != null )
		{
			//riskForm.setProgressAssessEvent(progAssessEvent);
//			riskForm.setRiskAssessmentType(assessmentType);
//			riskForm.setRiskAssessmentDate(progAssessEvent.getEnteredDate());
//			//riskForm.setFinalScore(String.valueOf(progAssessEvent.getFinalScore()));
//			riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(progAssessEvent.getEnteredDate()));
//			riskForm.setModReason(progAssessEvent.getModReason());
//			riskForm.setRecommendations(progAssessEvent.getRecommendations());

			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( progAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( progAssessEvent.getOverRiddenReasonOther()) )
			{
				//if( progForm.getAction().equals( UIConstants.EDIT) )
				if( riskForm.getSecondaryAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(progAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(progAssessEvent.getOverRiddenReasonCd());

					String overiddenReasonFirstTwo = progAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(progAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(progAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_OTHER);
				}
			}*/
		}
		
		//aRequest.getSession().setAttribute("riskProgressForm", progForm);
		forward = aMapping.findForward("progressSuccess");
		return forward;
		
	}
	
	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward gangNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			SubmitGangRiskAssessmentAction submitGangRiskAssessment = new SubmitGangRiskAssessmentAction();
			submitGangRiskAssessment.updateGangRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);		
			return aMapping.findForward("gangSuccess");
		}
		
		if((riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.PRINT)) )
		{
			return aMapping.findForward(UIConstants.PRINT_SUCCESS);
		}
		
		if((riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.PRINT_REPORT)))
		{
			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			//let the pdfManager know that the report should be saved in the request during report creation
			if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase("MH SCREEN"))
				aRequest.setAttribute("isPdfSaveNeeded", "false");
			else
				aRequest.setAttribute("isPdfSaveNeeded", "true");
			if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase("PREA FOLLOW-UP"))
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PREA_FOLLOW_UP_RISK_ASSESSMENT_REPORT);
			if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase("MH SCREEN"))
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.MHSCREEN_RISK_ASSESSMENT_REPORT);
			else
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.GANG_RISK_ASSESSMENT_REPORT);
			byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
			if(pdfDocument!=null){
				// persist a copy of the BFO pdf document
				SaveJuvenileProfileDocumentEvent saveEvent = 
					(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
				saveEvent.setDocument(pdfDocument);
				saveEvent.setDocumentTypeCodeId(UIConstants.GANG_RISK_DOCTYPE_CODE);
				saveEvent.setJuvenileNum(riskForm.getJuvenileNum());
				saveEvent.setEntryDate(DateUtil.getCurrentDate());
				CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
				ReturnException returnException =
					   (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
				if (returnException != null) {
					return null; 
				}
				aRequest.removeAttribute("pdfSavedReport");
				aRequest.removeAttribute("isPdfSaveNeeded");
			}
			riskForm.setAction("");
			return null;
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{	
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			riskForm.setSecondaryAction(UIConstants.EDIT);
		
		}
		
		forward = aMapping.findForward("gangSuccess");
		return forward;
		
	}
	
	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward testingNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		//RiskAssessmentTestingForm testForm = new RiskAssessmentTestingForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			//needs fixing!!!
			SubmitTestingAssessmentAction submitTestingAssessmentAction = new SubmitTestingAssessmentAction();
			submitTestingAssessmentAction.updateTestingRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);
			//above needs fixing!!
			//testForm = (RiskAssessmentTestingForm)aRequest.getSession().getAttribute("riskTestingForm");
			//testForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			//aRequest.getSession().setAttribute("riskTestingForm", testForm);
			return aMapping.findForward("testingSuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{			
			riskForm.setSecondaryAction(UIConstants.EDIT);		
		}
		
		//Sets the Assessment Type
		riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
		

		if( composite != null )
		{
			//riskForm.setTestingAssessEvent(testAssessEvent);
//			riskForm.setRiskAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
//			//riskForm.setFinalScore(String.valueOf(testAssessEvent.getFinalScore()));
//			//riskForm.setRiskAssessmentDate(testAssessEvent.getEnteredDate());
//			riskForm.setRecommendations(testAssessEvent.getRecommendations());
//			riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(testAssessEvent.getEnteredDate()));
//			riskForm.setModReason(testAssessEvent.getModReason());


			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( testAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( testAssessEvent.getOverRiddenReasonOther()) )
			{
				//if( testForm.getAction().equals( UIConstants.EDIT) )
				if( riskForm.getSecondaryAction().equals( UIConstants.EDIT) )	
				{
					riskForm.setRecommendationOverridden(testAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(testAssessEvent.getOverRiddenReasonCd());

					String overiddenReasonFirstTwo = testAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(testAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(testAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
			}*/
		}
		
		//aRequest.getSession().setAttribute("riskTestingForm", testForm);
		forward = aMapping.findForward("testingSuccess");
		return forward;
		
	}

	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward residentialNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		//RiskAssessmentResidentialForm resForm = new RiskAssessmentResidentialForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			//needs fixing!!!
			SubmitResidentialAssessmentAction submitResidentialAssessmentAction = new SubmitResidentialAssessmentAction();
			submitResidentialAssessmentAction.updateResidentialRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);
			//above needs fixing!!
			//resForm = (RiskAssessmentResidentialForm)aRequest.getSession().getAttribute("riskResidentialForm");
			//resForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			//aRequest.getSession().setAttribute("riskResidentialForm", resForm);
			return aMapping.findForward("residentialSuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//resForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//resForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}
		//RiskAssessmentResponseEvent re = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);
		if( composite != null )
		{
			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( re.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( re.getOverRiddenReasonOther()) )
			{
				if( resForm.getAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(re.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(re.getOverRiddenReasonCd());

					String overiddenReasonFirstTwo = re.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(re.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(re.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

					
				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
			}*/
			
		}
		
		//aRequest.getSession().setAttribute("riskResidentialForm", resForm);
		forward = aMapping.findForward("residentialSuccess");
		return forward;
		
	}
	
	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward interviewNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		//RiskAssessmentInterviewForm intForm = new RiskAssessmentInterviewForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			//needs fixing!!!
			SubmitInterviewAssessmentAction submitInterviewAssessmentAction = new SubmitInterviewAssessmentAction();
			submitInterviewAssessmentAction.updateInterviewRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);
			//above needs fixing!!
			//intForm = (RiskAssessmentInterviewForm)aRequest.getSession().getAttribute("riskInterviewForm");
			//intForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			//aRequest.getSession().setAttribute("riskInterviewForm", intForm);
			return aMapping.findForward("interviewSuccess");
		}
		if((riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.PRINT)) )
		{
			return aMapping.findForward(UIConstants.PRINT_PREA_SUCCESS);
		}
		
		if((riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.PRINT_REPORT)))
		{
			BFOPdfManager pdfManager = BFOPdfManager.getInstance();
			//let the pdfManager know that the report should be saved in the request during report creation
			/*if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase("MH SCREEN"))
				aRequest.setAttribute("isPdfSaveNeeded", "false");
			else
				aRequest.setAttribute("isPdfSaveNeeded", "true");*/
			aRequest.setAttribute("isPdfSaveNeeded", "true");
			if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase("PREA FOLLOW-UP"))
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PREA_FOLLOW_UP_RISK_ASSESSMENT_REPORT);
			
			byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
			if(pdfDocument!=null){
				// persist a copy of the BFO pdf document
				SaveJuvenileProfileDocumentEvent saveEvent = 
					(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
				saveEvent.setDocument(pdfDocument);
				saveEvent.setDocumentTypeCodeId(UIConstants.GANG_RISK_DOCTYPE_CODE);
				saveEvent.setJuvenileNum(riskForm.getJuvenileNum());
				saveEvent.setEntryDate(DateUtil.getCurrentDate());
				CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
				ReturnException returnException =
					   (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
				if (returnException != null) {
					return null; 
				}
				aRequest.removeAttribute("pdfSavedReport");
				aRequest.removeAttribute("isPdfSaveNeeded");
			}
			riskForm.setAction("");
			return null;
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//intForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//intForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}
		
		//Sets the Assessment Type
		//intForm.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_INTERVIEW);
		//Gets the Referral Assement Details from composite
		//InterviewAssessmentEvent intAssessEvent = (InterviewAssessmentEvent)MessageUtil.filterComposite(composite, InterviewAssessmentEvent.class);
		//RiskAssessmentResponseEvent intAssessEvent = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);

		if( composite != null )
		{
			//riskForm.setInterviewAssessEvent(intAssessEvent);
			//riskForm.setRiskAssessmentType(assessmentType);
			//riskForm.setRiskAssessmentDate(intAssessEvent.getEnteredDate());
			//riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(intAssessEvent.getEnteredDate()));
			//riskForm.setFinalScore(String.valueOf(intAssessEvent.getFinalScore()));

			//riskForm.setModReason(intAssessEvent.getModReason());
			
			//intForm.setRecommendation(intAssessEvent.getRecommendation());
			//riskForm.setRecommendations(intAssessEvent.getRecommendations());

			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( intAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( intAssessEvent.getOverRiddenReasonOther()) )
			{
				if( intForm.getAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(intAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(intAssessEvent.getOverRiddenReasonCd());
					
					String overiddenReasonFirstTwo = intAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(intAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(intAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
			}*/
			
		}

		//aRequest.getSession().setAttribute("riskInterviewForm", intForm);
		forward = aMapping.findForward("interviewSuccess");
		return forward;
		
	}

	/**
	 * @param aMapping
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward nonCustodyNavigateSetInfoAndCheckOverride(
			ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		RiskAssessmentReferralForm refForm = new RiskAssessmentReferralForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			SubmitReferralAssessmentAction submitReferralAssessmentAction = new SubmitReferralAssessmentAction();
			submitReferralAssessmentAction.updateReferralRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);

			refForm = (RiskAssessmentReferralForm)aRequest.getSession().getAttribute("riskReferralForm");
			//refForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			aRequest.getSession().setAttribute("riskReferralForm", refForm);
			return aMapping.findForward("referralSuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//refForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//refForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}
		
		//Sets the Assessment Type
		//refForm.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL);
		//Gets the Referral Assement Details from composite
		//ReferralAssessmentEvent refAssessEvent = (ReferralAssessmentEvent)MessageUtil.filterComposite(composite, ReferralAssessmentEvent.class);

		if( composite != null )
		{
//			riskForm.setReferralAssessEvent(refAssessEvent);
//			riskForm.setRiskAssessmentType(assessmentType);
			//riskForm.setRiskAssessmentDate(refAssessEvent.getEnteredDate());
			//riskForm.setFinalScore(String.valueOf(refAssessEvent.getFinalScore()));
			//For Updating, currently only specific to Custody Refferal but will have to added to all types in the future
			//refForm.setModReason(refAssessEvent.getModReason());
			
			//refForm.setRecommendation(refAssessEvent.getRecommendation());
//			riskForm.setRecommendations(refAssessEvent.getRecommendations());

			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( refAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( refAssessEvent.getOverRiddenReasonOther()) )
			{
				if( refForm.getAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(refAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(refAssessEvent.getOverRiddenReasonCd());

					String overiddenReasonFirstTwo = refAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(refAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(refAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
				
			}*/
			
		}
		
		//riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(refAssessEvent.getEnteredDate()));
		
		aRequest.getSession().setAttribute("riskReferralForm", refForm);
		forward = aMapping.findForward("referralSuccess");
		return forward;
		
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward custodyNavigateSetInfoAndCheckOverride(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		RiskAssessmentReferralForm refForm = new RiskAssessmentReferralForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			SubmitReferralAssessmentAction submitReferralAssessmentAction = new SubmitReferralAssessmentAction();
			submitReferralAssessmentAction.updateReferralRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);

			refForm = (RiskAssessmentReferralForm)aRequest.getSession().getAttribute("riskReferralForm");
			//refForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			aRequest.getSession().setAttribute("riskReferralForm", refForm);
			return aMapping.findForward("newReferralSuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//refForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//refForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}

		//Gets the Referral Assement Details from composite
		/* ReferralAssessmentEvent refAssessEvent = (ReferralAssessmentEvent)MessageUtil.filterComposite(composite, ReferralAssessmentEvent.class);

		if( refAssessEvent != null )
		{
		    /* //The following if is specific 
			if (refAssessEvent.isMoreThanOneFailure()) {
				refForm.setMoreThanOneFailureString(UIConstants.YES_FULL_TEXT);
			} else {
				refForm.setMoreThanOneFailureString(UIConstants.NO_FULL_TEXT);
			}

			{ String riskMandatoryDetentionDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.RISK_MANDATORY_DETENTION, refAssessEvent.getRiskMandatoryDetentionCd());
			  refForm.setRiskMandatoryDetentionDesc(riskMandatoryDetentionDesc);
			}
			refForm.setIsNewReferral(String.valueOf(Boolean.valueOf(refAssessEvent.isNewReferral())));
//			riskForm.setRecommendations(refAssessEvent.getRecommendations());

			 if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 
			if( notNullNotEmptyString( refAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( refAssessEvent.getOverRiddenReasonOther()) )
			{
				if( refForm.getAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(refAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(refAssessEvent.getOverRiddenReasonCd());

					String overiddenReasonFirstTwo = refAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(refAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(refAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
				
			}
			
		}
		}*/
		aRequest.getSession().setAttribute("riskReferralForm", refForm);
		forward = aMapping.findForward("newReferralSuccess");
		return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @param riskForm
	 * @param assessmentType
	 * @param composite
	 * @return
	 */
	private ActionForward courtReferralNavigateSetInfoAndCheckOverride(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse,
			RiskAnalysisForm riskForm, String assessmentType,
			CompositeResponse composite) {
		
		ActionForward forward;
		//Creates new form for the risk type
		RiskAssessmentCourtReferralForm refForm = new RiskAssessmentCourtReferralForm();
		
		//Sends user to success page after over ridding a recommendation
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.CONFIRM)) )
		{
			riskForm.setAction(UIConstants.CONFIRM);
			SubmitReferralAssessmentAction submitReferralAssessmentAction = new SubmitReferralAssessmentAction();
			submitReferralAssessmentAction.updateReferralRiskAnalysisOverrideStatus(aMapping, aForm, aRequest, aResponse);

			refForm = (RiskAssessmentCourtReferralForm)aRequest.getSession().getAttribute("riskCourtReferralForm");
			//refForm.setAction(UIConstants.SUCCESS);
			riskForm.setSecondaryAction(UIConstants.SUCCESS);
			riskForm.setAction(UIConstants.SUCCESS);
			aRequest.getSession().setAttribute("riskCourtReferralForm", refForm);
			return aMapping.findForward("courtReferralSuccess");
		}

		//Shows user summary info before confirmation of over riding
		boolean showSummary = false;
		if( (riskForm.getAction() != null) && (riskForm.getAction().equals(UIConstants.SUMMARY)) )
		{
			showSummary = true;
		}

		if( showSummary )
		{
			//refForm.setAction(UIConstants.SUMMARY);
			riskForm.setSecondaryAction(UIConstants.SUMMARY);
			riskForm.setAction(UIConstants.EMPTY_STRING);
		}
		else
		{
			//refForm.setAction(UIConstants.EDIT);
			riskForm.setSecondaryAction(UIConstants.EDIT);
			//riskForm.clear() ;
		}
		
		//Sets the Assessment Type
		
		//refForm.setAssessmentType(assessmentType); // RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE or RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE
		
		//Gets the Referral Assement Details from composite
		//CourtReferralAssessmentEvent refAssessEvent = (CourtReferralAssessmentEvent)MessageUtil.filterComposite(composite, CourtReferralAssessmentEvent.class);

		if( composite != null )
		{
//			riskForm.setCourtReferralAssessmentEvent(refAssessEvent);
//			riskForm.setRiskAssessmentType(assessmentType);
//			riskForm.setRiskAssessmentDate(refAssessEvent.getEnteredDate());
//			riskForm.setModReason(refAssessEvent.getModReason());
			
			//Check to see if entered date is passed 90 days, if it is, an update will not be allowed
			/*
			Date now = new Date();
			Date enteredDate = refAssessEvent.getEnteredDate();
			
			Calendar c1 = Calendar.getInstance(); 
			c1.setTime(enteredDate);
			
			Calendar c2 = Calendar.getInstance(); 
			c2.setTime(now);
			c2.add(Calendar.DATE, -90);
			Date threeMonthsAgo = c2.getTime();
		    c2.setTime(threeMonthsAgo);
		    
		    if(c1.after(c2)) {
				riskForm.setOverNinetyDays(UIConstants.YES_FULL_TEXT); 
			} else {
				riskForm.setOverNinetyDays(UIConstants.NO_FULL_TEXT);
			}
			//End date over 90 days check
			*/
		    //riskForm.setOverNinetyDays(UIRiskAnalysisHelper.ninetyDayCheck(refAssessEvent.getEnteredDate()));
			//refForm.setRecommendations(refAssessEvent.getRecommendations());

			/* if we already have one of the two reason types stored,
			 * then obviously we have already overridden the recommendation,
			 * so set the form values appropriately
			 */
			/*if( notNullNotEmptyString( refAssessEvent.getOverRiddenReasonCd() )  || 
					notNullNotEmptyString( refAssessEvent.getOverRiddenReasonOther()) )
			{
				if( refForm.getAction().equals( UIConstants.EDIT) )
				{
					riskForm.setRecommendationOverridden(refAssessEvent.isRecommendationOverridden());
					riskForm.setOverRiddenReasonCd(refAssessEvent.getOverRiddenReasonCd());

					String overiddenReasonFirstTwo = refAssessEvent.getOverRiddenReasonCd().substring(0, 2);
					if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
					{
						riskForm.setOverRiddenReasonOther(refAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonDetentionOther(null);
					} 
					else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
					{
						riskForm.setOverRiddenReasonDetentionOther(refAssessEvent.getOverRiddenReasonOther());
						riskForm.setOverRiddenReasonOther(null);
					}

				}
			}

			String overRiddenReasonDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.OVERRIDDENREASON, riskForm.getOverRiddenReasonCd());
			riskForm.setOverRiddenReasonDesc(overRiddenReasonDesc);

			String overRiddenReasonCd = riskForm.getOverRiddenReasonCd();

			if( overRiddenReasonCd != null && overRiddenReasonCd.length() > 0 )
			{
				char overRiddenReasonFirstChar = overRiddenReasonCd.charAt(0);

				if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_RELEASE);
				}
				else if( overRiddenReasonFirstChar == RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION_CODE_FIRST_CHAR )
				{
					riskForm.setOverRideType(RiskAnalysisConstants.OVERRIDE_TYPE_DETENTION);
				}
				
			}*/
			
			RiskSuggestedCasePlanDomainResponseEvent riskSuggestedCasePlanDomainResponseEvent 
    			= (RiskSuggestedCasePlanDomainResponseEvent) MessageUtil.filterComposite(composite, RiskSuggestedCasePlanDomainResponseEvent.class);
    
		    if (riskSuggestedCasePlanDomainResponseEvent != null) {
		    	refForm.setSuggestedCasePlanDomains(riskSuggestedCasePlanDomainResponseEvent.getSuggestedCasePlanDomainNames());
		    }
		    CourtReferralAssessmentEvent refAssessEvent = (CourtReferralAssessmentEvent)MessageUtil.filterComposite(composite, CourtReferralAssessmentEvent.class);
			//Completed Information
		    if (refAssessEvent.isCompleted() == true)
		    {
		    	refForm.setCompleted(true);
				refForm.setCollateralVisits(refAssessEvent.getCollateralVisits());
				refForm.setFace2FaceVisits(refAssessEvent.getFace2FaceVisits());
				refForm.setCourtDispositionTJPC(refAssessEvent.getCourtDispositionTJPC());
				refForm.setJjsCourtDecision(refAssessEvent.getJjsCourtDecision()); 
				refForm.setJjsCourtDisposition(refAssessEvent.getJjsCourtDisposition());
		    }
		    else
		    {
		    	refForm.setCompleted(false);
		    }
			
		}
		
		aRequest.getSession().setAttribute("riskCourtReferralForm", refForm);
		forward = aMapping.findForward("courtReferralSuccess");
		return forward;
	}

	/* given a String, return true if it's not null and not empty
	 * 
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}
	
	/*
	 * 
	 */
	private ActionForward back(ActionMapping aMapping)
	{
		return aMapping.findForward("back");
	}

	/*
	 * 
	 */
	private ActionForward cancel(ActionMapping aMapping)
	{
		return aMapping.findForward("cancel");
	}
}
