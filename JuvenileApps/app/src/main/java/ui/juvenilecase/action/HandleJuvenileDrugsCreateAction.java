package ui.juvenilecase.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.JuvenileDrugRequestEvent;
import messaging.juvenile.SaveJuvenileDrugsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileDrugForm;
import ui.juvenilecase.form.TraitsForm;

public class HandleJuvenileDrugsCreateAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public HandleJuvenileDrugsCreateAction()
	{
	}

	/*
	 * 
	 */
	public ActionForward addNewDrugInfo(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileDrugForm form = (JuvenileDrugForm)aForm;
		JuvenileDrugRequestEvent evt = new JuvenileDrugRequestEvent();
		UIJuvenileHelper.populateJuvenileDrugRequestEventFromForm(evt, form);
		form.addNewDrugInfo(evt);
		form.clearDrugCreateInfo();
		
		return aMapping.findForward("addSuccess");
	}

	/*
	 * 
	 */
	public ActionForward removeDrugInfo(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileDrugForm form = (JuvenileDrugForm)aForm;
		if( form.getRemoveIndices() == null || form.getRemoveIndices().length == 0 )
		{
			return aMapping.findForward("failure");
		}
		form.removeSelectedDrugs();

		return aMapping.findForward("removeSuccess");
	}

	/*
	 * 
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException
	{
		JuvenileDrugForm drugForm = (JuvenileDrugForm)aForm;
		SaveJuvenileDrugsEvent saveEvt = (SaveJuvenileDrugsEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEDRUGS);
		saveEvt.setJuvenileNum(drugForm.getJuvenileNum());

		Iterator ite = drugForm.getNewDrugInfoList();
		if( !ite.hasNext() )
		{
			return aMapping.findForward("failure");
		}

		while( ite.hasNext() )
		{
			saveEvt.addRequest((RequestEvent)ite.next());
		}

		MessageUtil.postRequest(saveEvt);

		drugForm.setAction(UIConstants.CONFIRM);
		TraitsForm traitsForm;

		traitsForm = (TraitsForm)getSessionForm(aMapping, aRequest,
				UIConstants.JUVENILE_TRAITS_FORM, true);
		traitsForm.clear();
		traitsForm.setAction(UIConstants.VIEW);

		return aMapping.findForward("finishSuccess");
	}

	/*
	 * 
	 */
	public ActionForward backToList(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileDrugForm drugForm = (JuvenileDrugForm)aForm;
		drugForm.setAction(UIConstants.VIEW);
		
		return aMapping.findForward("backToList");
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.add", "addNewDrugInfo");
		buttonMap.put("button.removeSelected", "removeDrugInfo");
		buttonMap.put("button.finish", "submit");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.backToList", "backToList");
		buttonMap.put("button.cancel", "backToList");

		return;
	}
}
