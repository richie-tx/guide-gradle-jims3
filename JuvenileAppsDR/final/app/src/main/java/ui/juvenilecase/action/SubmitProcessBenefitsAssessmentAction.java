package ui.juvenilecase.action;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveBenefitsAssessmentEvent;
import messaging.juvenilecase.reply.IndividualIncomeDeterminationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;

public class SubmitProcessBenefitsAssessmentAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.mainPage", "mainPage");
		keyMap.put("button.back", "back");
		keyMap.put("button.backToBenefitsAssessment", "backToBenefitsAss");
		keyMap.put("button.returnToCasefile", "returnToCasefile");
		return keyMap;
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward backToBenefitsAss(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm) aForm;
			form.setReview(false);
			return aMapping.findForward("titleIV");
		}
		
	public ActionForward returnToCasefile(ActionMapping aMapping, 
									ActionForm aForm, HttpServletRequest aRequest, 
									HttpServletResponse aResponse) {
    
			return aMapping.findForward("returnToCasefile");
	   }

	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm) aForm;
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm) aForm;
		ProcessBenefitsAssessmentForm.BenefitAssessment asmnt = form.getCurrentAssessment();
		ProcessBenefitsAssessmentForm.BenefitAssessment.TitleIVEQuestion titleIVe = asmnt.getQuestion();

		SaveBenefitsAssessmentEvent evt = new SaveBenefitsAssessmentEvent();
		evt.setCasefileId(asmnt.getCasefileId());
		evt.setAssessmentId(asmnt.getAssessmentId());
		evt.setGuardianId(asmnt.getGuardian().getConstellationMemberId());

		evt.setEligibleForMedicaid(asmnt.isEligibleForMedicaid());
		evt.setReceivingMedicaid(asmnt.isReceivingMedicaid());
		evt.setEligibleForTitleIVe(asmnt.isEligibleForTitleIVe());
		evt.setReceivingTitleIVe(asmnt.isReceivingTitleIVe());

		//Question 1
		evt.setLegalResident(titleIVe.isLegalResident());

		//Question 2
		evt.setOneParentIsStepparent(titleIVe.isOneParentIsStepparent());
		evt.setDeathOrAbsence(titleIVe.isDeathOrAbsence());
		evt.setIncapacityOrDisabilityOfParent(titleIVe.isIncapacityOrDisabilityOfParent());
		evt.setPrimaryWageEarnerUnderemployment(titleIVe.isPrimaryWageEarnerUnderemployment());

		if (titleIVe.isPrimaryWageEarnerUnderemployment())
		{
			evt.setPweRelationshipToJuvenile(titleIVe.getPweRelationshipToJuvenile());
			evt.setPweRelationshipToJuvenileId(titleIVe.getPweRelationshipToJuvenileId());
			evt.setPweWorkedLessThen100Hours(titleIVe.isPweWorkedLessThen100Hours());
			evt.setPweIncomeLessThanUnderemployedLimit(titleIVe.isPweIncomeLessThanUnderemployedLimit());
		}

		//	Underemployment Parent Checklist
		evt.setPweWorkedSteadyLessThan100Hours(titleIVe.isPweWorkedSteadyLessThan100Hours());
		evt.setPweWorkedIrregularLessThan100HoursAvg(titleIVe.isPweWorkedIrregularLessThan100HoursAvg());
		evt.setPweGrossMonthlyIncomeForOver100Hours((int)titleIVe.getPweGrossMonthlyIncomeForOver100Hours() * 100);

		evt.setAfdcIncomeCertifiedGroupLimit((int) (titleIVe.getAfdcIncomeCertifiedGroupLimit() * 100));
		evt.setAfdcIncomeCertifiedGroupParentsSize(titleIVe.getAfdcIncomeCertifiedGroupParentsSize());
		evt.setAfdcIncomeCertifiedGroupSize(titleIVe.getAfdcIncomeCertifiedGroupSize());

		//Question 3
		evt.setWasChildLivingWithParent(titleIVe.isWasChildLivingWithParent());
		//if(titleIVe.isLivingWithSpecifiedGuardian())???

		//AFDC Income Determination Worksheet
		evt.setAfdcIncomeLimitsMet(titleIVe.isAfdcIncomeLimitsMet());
		evt.setAfdcIncomeStepparentsMonthlyGross((int) (titleIVe.getAfdcIncomeStepparentsMonthlyGross() * 100));
		evt.setAfdcIncomeStepparentWorkRelatedExpenses((int) (titleIVe.getAfdcIncomeStepparentWorkRelatedExpenses() * 100));
		evt.setAfdcIncomeStepparentOtherMonthlyIncome((int) (titleIVe.getAfdcIncomeStepparentOtherMonthlyIncome() * 100));
		evt.setAfdcIncomeStepparentMonthyPaymentsToDependent(
			(int) (titleIVe.getAfdcIncomeStepparentMonthyPaymentsToDependent() * 100));
		evt.setAfdcIncomeStepparentMonthyAlimonyChildSupport(
			(int) (titleIVe.getAfdcIncomeStepparentMonthyAlimonyChildSupport() * 100));

		evt.setAfdcIncomeStepparentNoncertifiedCount(titleIVe.getAfdcIncomeStepparentNoncertifiedCount());

		//calculated fields
		evt.setAfdcIncomeStepparentCountableEarnedMonthy((int) (titleIVe.getAfdcIncomeStepparentCountableEarnedMonthy() * 100));
		evt.setAfdcIncomeStepparentTotalCountableMonthy((int) (titleIVe.getAfdcIncomeStepparentTotalCountableMonthy() * 100));
		evt.setAfdcIncomeStepparentAllowanceAmount((int) (titleIVe.getAfdcIncomeStepparentAllowanceAmount() * 100));
		evt.setAfdcIncomeStepparentAppliedIncome((int) (titleIVe.getAfdcIncomeStepparentAppliedIncome() * 100));

		evt.setAfdcIncomeTotalMonthy(((int) titleIVe.getAfdcIncomeTotalMonthy() * 100));
		evt.setAfdcIncomeTotalCountable((int) (titleIVe.getAfdcIncomeTotalCountable() * 100));

		//titleIVe.isGrossIncomeBelowAFDCLimit()

		//Question 5
		evt.setUnder10KLimit(titleIVe.isUnder10KLimit());

		//Question 6
		evt.setChildMeetsEligibilityCriteria(asmnt.isChildMeetAFDCRequirement());

		if (titleIVe.getSelectedInfoSourceId() != null && titleIVe.getSelectedInfoSourceId().length > 0)
		{
			Collection sourcesForAFDCInformation = Arrays.asList(titleIVe.getSelectedInfoSourceId());
			evt.setSourcesForAFDCInformation(sourcesForAFDCInformation);
		}

		//IV-e Eligibility - to be filled by JPD Staff only
		evt.setOrderContainsBestInterestLanguage(asmnt.isInitialOrderRemovalContainBestInterest());
		evt.setResonableEffortsMadeWithin60Days(asmnt.isReasonableEffortsMadeWithin60DaysOfChildRemoval());
		evt.setOrdersIncludeResponsibilityForCareAndPlacement(asmnt.isCourtOrderIncludeFindingChildCareAndPlacement());
		evt.setChildMeetsAFDCAndOrderRequirements(asmnt.isChildMeetAFDCRequirement());
		evt.setTitleIVeOfficerId(form.getTitleIVeOfficerId());
		evt.setTitleIVeOfficerName(form.getTitleIVeOfficerName().getFormattedName());

		Iterator iter = titleIVe.getAfdcIncomeWorksheetItems().iterator();
		while (iter.hasNext())
		{
			ProcessBenefitsAssessmentForm.IndividualIncomeDetermination incDeter =
				(ProcessBenefitsAssessmentForm.IndividualIncomeDetermination) iter.next();

			IndividualIncomeDeterminationResponseEvent incEvt = new IndividualIncomeDeterminationResponseEvent();

			incEvt.setMemberId(incDeter.getMemberId());
			incEvt.setName(incDeter.getName());
			incEvt.setAge(incDeter.getAge());
			incEvt.setRelationshipToJuvenileId(incDeter.getRelationshipToJuvenileId());
			incEvt.setComments(incDeter.getComments());
			incEvt.setIncomeSourceId(incDeter.getIncomeSourceId());
			//incEvt.setGrossMonthyIncome(incDeter.getGrossMonthyIncome());
			evt.getAfdcIncomeWorksheetItems().add(incEvt);
		}
		
		// Adding record in activity table
		JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute("juvenileCasefileForm");
		
		UIJuvenileHelper.createActivity(juvenileCasefileForm.getSupervisionNum(), ActivityConstants.TITLE_IV_E_ASSESSMENT_COMPLETED, "");		

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);

		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		ReturnException returnException =
			(ReturnException) MessageUtil.filterComposite(replyEvent, ReturnException.class);
		if (returnException != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.common"));
			saveErrors(aRequest, errors);
			aRequest.setAttribute("pageType", UIConstants.SUMMARY);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		aRequest.setAttribute("pageType", UIConstants.CONFIRM);

		return aMapping.findForward(UIConstants.FINISH);
	}

}