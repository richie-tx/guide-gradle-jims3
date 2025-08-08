package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.interviewinfo.UpdateJuvenileBenefitsEvent;
import messaging.interviewinfo.reply.BenefitsReceivedByResponseEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.juvenilecase.UIJuvenileInterviewInfoHelper;
import ui.juvenilecase.form.JuvenileBenefitsInsuranceForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;


public class SubmitJuvenileBenefitsCreateAction extends LookupDispatchAction
{
	/* (non-Javadoc)
		 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
		 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addToList", "addToList");
		keyMap.put("button.back", "back");
		keyMap.put("button.saveAndAddInsurance", "saveAndAddInsurance");
		keyMap.put("button.next", "next");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.remove", "remove");
		keyMap.put("button.update", "update");
		keyMap.put("button.finishUpdate", "finishUpdate");
		return keyMap;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		if(form.getSelectedValue()!=null && !form.getSelectedValue().equalsIgnoreCase(""))
		{
			Iterator benefitsIter = form.getJuvBenefits().iterator();
			while(benefitsIter.hasNext())
			{
				JuvenileMemberForm.MemberBenefit memBenefit = (	JuvenileMemberForm.MemberBenefit) benefitsIter.next();
				if(memBenefit.getMemberBenefitId().equals(form.getSelectedValue()))
				{
					JuvenileMemberForm.MemberBenefit currentBenefit = new JuvenileMemberForm.MemberBenefit();
					currentBenefit.setBenefitStatus(memBenefit.getBenefitStatus());
					currentBenefit.setEligibilityId(memBenefit.getEligibilityId());
					currentBenefit.setEligibilityType(memBenefit.getEligibilityType());
					currentBenefit.setEligibilityTypeId(memBenefit.getEligibilityTypeId());
					currentBenefit.setEntryDate(memBenefit.getEntryDate());
					currentBenefit.setIdNumber(memBenefit.getIdNumber());
					currentBenefit.setMemberBenefitId(memBenefit.getMemberBenefitId());
					currentBenefit.setReceivedAmt(memBenefit.getReceivedAmt());
					currentBenefit.setReceivedBy(memBenefit.getFormattedName());
					currentBenefit.setReceivingBenefits(memBenefit.isReceivingBenefits());
					currentBenefit.setEligibleForBenefits(memBenefit.isEligibleForBenefits());
					
					form.setCurrentBenefit(currentBenefit);
					break;
				}
			}
		}
		//set the benefit status dropdown values
		List<CodeResponseEvent> statusCodes = UIJuvenileInterviewInfoHelper.loadBenefitStatuses();
		if(statusCodes != null)
		{
		    form.setBenefitStatuses(statusCodes);
		}			
				
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
			
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward addToList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = UIConstants.SUCCESS; //happy path scenario
		
		JuvenileMemberForm.MemberBenefit currentBenefit = form.getCurrentBenefit();
		JuvenileMemberForm.MemberBenefit tmpBenefit = new JuvenileMemberForm.MemberBenefit();
		tmpBenefit.setEntryDate(currentBenefit.getEntryDate());
		tmpBenefit.setEligibilityTypeId(currentBenefit.getEligibilityTypeId());
		tmpBenefit.setEligibleForBenefits(currentBenefit.isEligibleForBenefits());
		tmpBenefit.setReceivingBenefits(currentBenefit.isReceivingBenefits());
		if(currentBenefit.isReceivingBenefits())
		{
			Iterator iter = form.getBenefitsReceivers().iterator();
			while(iter.hasNext())
			{
				BenefitsReceivedByResponseEvent response = (BenefitsReceivedByResponseEvent) iter.next();
				if(response.getFormattedName().equalsIgnoreCase(currentBenefit.getReceivedBy()))
				{
					tmpBenefit.setReceivedByFirstName(response.getFirstName());
					tmpBenefit.setReceivedByMiddleName(response.getMiddleName());
					tmpBenefit.setReceivedByLastName(response.getLastName());
				}
			}
			tmpBenefit.setReceivedBy(currentBenefit.getReceivedBy());
			tmpBenefit.setReceivedAmt(currentBenefit.getReceivedAmt());
			tmpBenefit.setIdNumber(currentBenefit.getIdNumber());
			tmpBenefit.setBenefitStatus("CURRENT");
		}
		
		form.getNewJuvBenefits().add(tmpBenefit);
		
		//clear any user input
		form.setCurrentBenefit(new JuvenileMemberForm.MemberBenefit());
		
		return aMapping.findForward(UIConstants.ADD_TO_LIST_SUCCESS);
		
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward remove(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = "removeSuccess"; //happy path scenario
		
		String myBenefitPos = form.getSelectedValue();
		
		if(myBenefitPos!=null && !(myBenefitPos.equals(""))){
				if(form.getNewJuvBenefits()!=null & form.getNewJuvBenefits().size()>0)
					 ((List)form.getNewJuvBenefits()).remove((Integer.valueOf(myBenefitPos)).intValue());
		}
	
		return aMapping.findForward(forward);
	
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.NEXT);
	}
	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = UIConstants.SUCCESS; //happy path scenario
		
		if(!saveBenefit(form))
			return aMapping.findForward("handleException");
		else	
			aRequest.setAttribute("confirm", "true");
			return aMapping.findForward("saveAndContinue");
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward saveAndAddInsurance(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = UIConstants.SUCCESS; //happy path scenario
		if(!saveBenefit(form))
			return aMapping.findForward("handleException");
		else
			return aMapping.findForward("saveAndAddInsurance");

	}
	
	private boolean saveBenefit(JuvenileBenefitsInsuranceForm form)
	{
		//translate data from JuvenileMemberForm.MemberBenefits -> UpdateJuvenileBenefitsEvent

		UpdateJuvenileBenefitsEvent event = new UpdateJuvenileBenefitsEvent();
		event.setJuvenileNum(form.getJuvNumber());

		Iterator newBenefitsIter = form.getNewJuvBenefits().iterator();

		while(newBenefitsIter.hasNext())
		{
			JuvenileMemberForm.MemberBenefit newBenefit = (JuvenileMemberForm.MemberBenefit)newBenefitsIter.next();

			JuvenileBenefitResponseEvent re = new JuvenileBenefitResponseEvent();
			re.setEligibilityTypeId(newBenefit.getEligibilityTypeId());
			re.setEligibleForBenefits(newBenefit.isEligibleForBenefits());
			re.setReceivingBenefits(newBenefit.isReceivingBenefits());
			re.setReceivedBy(new Name(newBenefit.getReceivedByFirstName(), newBenefit.getReceivedByMiddleName(), newBenefit.getReceivedByLastName()));
			re.setReceivedAmt(newBenefit.getReceivedAmt());
			re.setIdNumber(newBenefit.getIdNumber());
			re.setBenefitStatus("CU");
			//re.setr
			event.addUpdateEvents(re);
			
			//added for User Story 27022
			event.setAction("create");
		}
		
		CompositeResponse replyEvent = MessageUtil.postRequest(event);
				
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward finishUpdate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		
		if(!updateBenefit(form))
			return aMapping.findForward("handleException");
		else
			form.setJuvBenefits(UIJuvenileInterviewInfoHelper.getBenefitsInfoList(form.getJuvNumber()));
		
		form.setCurrentBenefit(new JuvenileMemberForm.MemberBenefit());
		aRequest.setAttribute("confirm", "true");
		return aMapping.findForward("saveAndContinue");

	}
	
	private boolean updateBenefit(JuvenileBenefitsInsuranceForm form)
	{
		UpdateJuvenileBenefitsEvent event = new UpdateJuvenileBenefitsEvent();
		event.setJuvenileNum(form.getJuvNumber());
		
		JuvenileMemberForm.MemberBenefit newBenefit = form.getCurrentBenefit();
		
		JuvenileBenefitResponseEvent re = new JuvenileBenefitResponseEvent();
		re.setEligibilityTypeId(newBenefit.getEligibilityTypeId());
		re.setEligibleForBenefits(newBenefit.isEligibleForBenefits());
		re.setReceivingBenefits(newBenefit.isReceivingBenefits());
		re.setReceivedBy(new Name(newBenefit.getReceivedByFirstName(), newBenefit.getReceivedByMiddleName(), newBenefit.getReceivedByLastName()));
		re.setReceivedAmt(newBenefit.getReceivedAmt());
		re.setIdNumber(newBenefit.getIdNumber());
		re.setBenefitStatus(newBenefit.getBenefitStatus());
		re.setBenefitId(newBenefit.getMemberBenefitId());
		
		event.setAction("update");
		event.addUpdateEvents(re);	
		CompositeResponse replyEvent = MessageUtil.postRequest(event);
		return true;
	}
	

}