package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.family.GetBenefitsAssessmentsEvent;
import messaging.family.GetFamilyConstellationMemberLatestFinancialEvent;
import messaging.family.GetRequestedBenefitsAssessmentDetailEvent;
import messaging.interviewinfo.GetJuvenileBenefitsEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentDetailResponseEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.IndividualIncomeDeterminationResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;

public class DisplayBenefitsAssessmentFormAction extends LookupDispatchAction
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{

		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm) aForm;
		form.setView(false);
		form.setReview(false);
		String selGuardianId = form.getCurrentAssessment().getGuardian().getMemberNumber();

		if (selGuardianId == null || selGuardianId.trim().length() < 1)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE,
					new ActionMessage("error.pleaseSelectOneToProceed", "Please select one"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// grab the selected guardian object to be displayed later
		Collection juvGuardians = form.getCurrentAssessment().getListOfGuardians();
		if (juvGuardians != null && !juvGuardians.isEmpty())
		{
			for (Iterator iter = juvGuardians.iterator(); iter.hasNext();)
			{
				ProcessBenefitsAssessmentForm.Guardian theGuardian = (ProcessBenefitsAssessmentForm.Guardian) iter
						.next();
				if (theGuardian.getMemberNumber().equals(selGuardianId))
				{

					form.getCurrentAssessment().setGuardian(theGuardian);
				}
			}
		}

		// get guardian financial info
		GetFamilyConstellationMemberLatestFinancialEvent request = (GetFamilyConstellationMemberLatestFinancialEvent) EventFactory
				.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONMEMBERLATESTFINANCIAL);
		request.setConstelltionMemberId(form.getCurrentAssessment().getGuardian().getConstellationMemberId());

		CompositeResponse response = MessageUtil.postRequest(request);

		Map dataMap = MessageUtil.groupByTopic(response);
		// Assert.assertTrue(message.toString(), obj != null);
		form.setEmploymentInfoList(null);
		if (selGuardianId != null && !selGuardianId.equals(""))
		{
			JuvenileFamilyForm.Guardian guard = new JuvenileFamilyForm.Guardian();
			UIJuvenileFamilyHelper.getEmploymentMemberInfo(selGuardianId, guard);
			form.setEmploymentInfoList(guard.getEmploymentInfoList());
		}
		if (dataMap != null)
		{
			String topic = request.getConstelltionMemberId() + "_"
					+ PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC;
			Collection members = (Collection) dataMap.get(topic);

			if (members != null && members.size() > 0)
			{
				Iterator iter = members.iterator();
				while (iter.hasNext())
				{
					FamilyConstellationMemberFinancialResponseEvent reply = (FamilyConstellationMemberFinancialResponseEvent) iter
							.next();
					form.setGuardianFinancialInfo(reply);
				}
			}
		}

		// get successful previous benefits assessment as well
		String juvNumber = form.getCurrentAssessment().getJuvNumber();
		if (juvNumber != null)
		{
			GetBenefitsAssessmentsEvent event = new GetBenefitsAssessmentsEvent();
			event.setJuvenileNum(juvNumber);

			List benes = MessageUtil.postRequestListFilter(event, BenefitsAssessmentResponseEvent.class);
			Collections.sort(benes);
			form.setPreviousBenefitsAssessments(benes);
		}

		// Get Juvenile's Previous Benefits
		if (juvNumber != null)
		{
			GetJuvenileBenefitsEvent event = new GetJuvenileBenefitsEvent();
			event.setJuvenileNum(juvNumber);
			event.setTitle4eAndMedicaidOnly(true);

			List benefitsResponseEvents = MessageUtil.postRequestListFilter(event, JuvenileBenefitResponseEvent.class);
			ArrayList benefits = new ArrayList();
			Iterator benefitsIter = benefitsResponseEvents.iterator();

			while (benefitsIter.hasNext())
			{
				JuvenileMemberForm.MemberBenefit ben = new JuvenileMemberForm.MemberBenefit();
				JuvenileBenefitResponseEvent jbre = (JuvenileBenefitResponseEvent) benefitsIter.next();
				ben.setEligibilityTypeId(jbre.getEligibilityTypeId());
				ben.setEligibleForBenefits(jbre.isEligibleForBenefits());
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

				Date entryDate = jbre.getEntryDate();
				if (entryDate != null)
				{
					ben.setEntryDate(format.format(entryDate));
				}
				ben.setReceivingBenefits(jbre.isReceivingBenefits());
				benefits.add(ben);
			}

			if (benefits.size() > 0)
			{
				Collections.sort(benefits);
				form.setPreviousJuvenileBenefits(benefits);
			}
		}

		// get benefits assessment details, for ADFC income determination worksheet

		GetRequestedBenefitsAssessmentDetailEvent event = new GetRequestedBenefitsAssessmentDetailEvent();
		event.setAssessmentId(form.getCurrentAssessment().getAssessmentId());
		event.setGuardianId(form.getCurrentAssessment().getGuardian().getConstellationMemberId()); // this

		// must be stored in current assessment

		List benes = MessageUtil.postRequestListFilter(event, BenefitsAssessmentDetailResponseEvent.class);

		// AssessmentId is unique, therefore, the size of the collection has to
		// be 1
		if (benes != null && benes.size() == 1)
		{
			Iterator beneIter = benes.iterator();
			BenefitsAssessmentDetailResponseEvent beneDetail = (BenefitsAssessmentDetailResponseEvent) beneIter.next();

			// Set data from Response Event to Custom UI Object
			Collection incomeWorksheetItemsRE = beneDetail.getAfdcIncomeWorksheetItems();
			List incomeWorksheetItems = new ArrayList();

			if (incomeWorksheetItemsRE != null && incomeWorksheetItemsRE.size() > 0)
			{

				for (Iterator iter = incomeWorksheetItemsRE.iterator(); iter.hasNext();)
				{
					IndividualIncomeDeterminationResponseEvent iidRE = (IndividualIncomeDeterminationResponseEvent) iter
							.next();
					ProcessBenefitsAssessmentForm.IndividualIncomeDetermination iid = new ProcessBenefitsAssessmentForm.IndividualIncomeDetermination();
					iid.setJuvenileNumber(beneDetail.getJuvenileId());
					iid.setMemberId(iidRE.getMemberId());
					iid.setName(iidRE.getName());
					iid.setRelationshipToJuvenileId(iidRE.getRelationshipToJuvenileId());
					iid.setGrossMonthyIncome(iidRE.getGrossMonthyIncome());
					iid.setIncomeSourceId(iidRE.getIncomeSourceId());
					iid.setComments(iidRE.getComments());
					iid.setAge(iidRE.getAge());
					incomeWorksheetItems.add(iid);
				}
				Collections.sort(incomeWorksheetItems);
			}

			incomeWorksheetItems.add(new ProcessBenefitsAssessmentForm.IndividualIncomeDetermination());
			incomeWorksheetItems.add(new ProcessBenefitsAssessmentForm.IndividualIncomeDetermination());

			form.getCurrentAssessment().getQuestion().setAfdcIncomeWorksheetItems(incomeWorksheetItems);
		}
		// else, error

		return aMapping.findForward(UIConstants.NEXT);
	}
}