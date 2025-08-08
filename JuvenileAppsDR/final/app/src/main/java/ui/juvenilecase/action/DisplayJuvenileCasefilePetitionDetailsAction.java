//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefilePetitionDetailsAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilecase.GetJuvenileCasefilePetitionEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingCoActorResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingComplainantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.SummaryOfFactsResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.AssignedReferralsForm;
import ui.juvenilecase.form.PetitionDetailsForm;

public class DisplayJuvenileCasefilePetitionDetailsAction extends JIMSBaseAction
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.view", "view");
	}

	/**
	 * @roseuid 42A9A1D4006B
	 */
	public DisplayJuvenileCasefilePetitionDetailsAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42A99B990041
	 */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		PetitionDetailsForm form = (PetitionDetailsForm)aForm;
		String petitionNum = "";
		String juvenileNum = "";
		
		HttpSession session = aRequest.getSession();
		AssignedReferralsForm referralsForm = (AssignedReferralsForm)
				session.getAttribute("assignedReferralsForm");
		
		juvenileNum  = referralsForm.getJuvenileNum();
		
		String page = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE);
		//<KISHORE>JIMS200059455 : Prog. Referral: Do not display canc. events(UI)-KK
		// There can be multiple charges per daLogNum where as only one charge per petition num
		if(form.isJotChargesDisplayOnly()){
			// Get the JOT Charges for a daLogNum
			form.setJotChargesDisplayOnly(true);
			form.setNotDetailed(false);
			if(referralsForm != null && StringUtils.isNotEmpty(referralsForm.getDaLogNum())){
				UIJuvenileCaseworkHelper.getJOTChargesForReferral(null,referralsForm.getDaLogNum(),form);
				aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY,
						PDJuvenileCaseConstants.JUVENILE_JOT);
				if(PDJuvenileCaseConstants.JUVENILE_PROFILE.equalsIgnoreCase(page)){
					aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
						PDJuvenileCaseConstants.JUVENILE_PROFILE);
				}	
				else if(PDJuvenileCaseConstants.JUVENILE_CASEFILE.equalsIgnoreCase(page)){
					aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
							PDJuvenileCaseConstants.JUVENILE_CASEFILE);
				}
				return aMapping.findForward("success");
			}
		}else{
			boolean notDetailed = form.isNotDetailed();
			if( form.isNotDetailed() )
			{
				petitionNum = form.getPetitionNum();
			}
			else
			{
				petitionNum = aRequest.getParameter(PDJuvenileCaseConstants.PETITIONNUM_PARAM);
				if( petitionNum == null )
				{
					petitionNum = form.getPetitionNum();
				}
			}
			form.clear();
			form.setJuvenileNum(juvenileNum);
			form.setPetitionNum(petitionNum);
			form.setJotChargesDisplayOnly(false);
			form.setNotDetailed(notDetailed);
			aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY,
					PDJuvenileCaseConstants.JUVENILE_REFERRAL);
			if(PDJuvenileCaseConstants.JUVENILE_PROFILE.equalsIgnoreCase(page)){
				aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
					PDJuvenileCaseConstants.JUVENILE_PROFILE);
			}	
			else if(PDJuvenileCaseConstants.JUVENILE_CASEFILE.equalsIgnoreCase(page)){
				aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
						PDJuvenileCaseConstants.JUVENILE_CASEFILE);
			}
			// Set the petition details
			if( referralsForm != null )
			{
				Collection<JJSChargeResponseEvent> petitions = referralsForm.getPetitions();
				if( petitions != null )
				{
					for( JJSChargeResponseEvent chargeResp: petitions )
					{
						if( petitionNum.equals(chargeResp.getPetitionNum()) )
						{
							form.setPetitionFiledDate(chargeResp.getPetitionDate());
							form.setPetitionStatus(chargeResp.getPetitionStatus());
							form.setPenalCategory(chargeResp.getPenalCategory());
							form.setLevelDegree(chargeResp.getLevelDegree());
							form.setPetitionAmendment(chargeResp.getAmend());
							form.setPetitionAmendmentDate(chargeResp.getPetitionDate());
							form.setPetitionAllegation(chargeResp.getOffense());
							form.setDpsCode(chargeResp.getDpsCode()); //added DPS code
						}
					}
				}
			}
			if( form.isNotDetailed() )
			{
				return aMapping.findForward("success");
			}
			// Get the JOT Charges for a petition
			if( petitionNum != null && !form.isNotDetailed() )
			{	
				UIJuvenileCaseworkHelper.getJOTChargesForReferral(petitionNum,null,form); //bug #67708
				return aMapping.findForward("success");
			} 
		}
		


		return aMapping.findForward("invalid");
	}
	
	/*
	 *  call to this function is commented out, 
	 *  so commenting fucntion out on 11/18/2008 - mjt
	 *  **************************************************************************
	private void getDispositions(PetitionDetailsForm form, String petitionNum)
	{
		GetJuvenileCasefileDispositionsEvent disp = new GetJuvenileCasefileDispositionsEvent();
		disp.setPetitionNum(petitionNum);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(disp);

		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		Map map = MessageUtil.groupByTopic(response);
		Collection dispositions = (Collection)map.get(PDJuvenileCaseConstants.JUVENILE_DISPOSITION_TOPIC);
		form.setDispositions(dispositions);
	}
**********************************************************	 */

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, 
	 * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, 
	 * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
}
