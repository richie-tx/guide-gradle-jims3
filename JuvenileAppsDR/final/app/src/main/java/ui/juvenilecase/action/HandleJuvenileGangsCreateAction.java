package ui.juvenilecase.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.JuvenileGangRequestEvent;
import messaging.juvenile.SaveJuvenileGangsEvent;
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
import ui.juvenilecase.form.JuvenileGangsForm;
import ui.juvenilecase.form.TraitsForm;

public class HandleJuvenileGangsCreateAction extends JIMSBaseAction {

	/**
	 * Default constructor.
	 */
	public HandleJuvenileGangsCreateAction() {
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap) {
		
		buttonMap.put("button.add", "addNewGangInfo");
		buttonMap.put("button.removeSelected", "removeGangInfo");
		buttonMap.put("button.finish", "saveGangInfo");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");

		return;
	}

	/*
	 * Adds the new gang information to the list.
	 */
	public ActionForward addNewGangInfo(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		
		JuvenileGangsForm form = (JuvenileGangsForm) aForm;
		JuvenileGangRequestEvent requestAddEvt = new JuvenileGangRequestEvent();
		UIJuvenileHelper.populateJuvenileGangRequestEventFromForm(
				requestAddEvt, form);
		form.addNewGangInfo(requestAddEvt);
		form.clearGangCreateInfo();
		return aMapping.findForward(UIConstants.ADD_TO_LIST_SUCCESS);
	}

	/*
	 * Removes the gang information from the list
	 */
	public ActionForward removeGangInfo(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {

		JuvenileGangsForm form = (JuvenileGangsForm) aForm;
		String forward = UIConstants.REMOVE_SUCCESS; // happy path scenario

		String gangItemTobeRemoved = form.getSelectedValue();

		if (gangItemTobeRemoved != null && !(gangItemTobeRemoved.equals(""))) {
			if (form.getNewGangsInfoList() != null
					& form.getNewGangsInfoList().size() > 0)
				((List) form.getNewGangsInfoList()).remove((Integer
						.valueOf(gangItemTobeRemoved)).intValue());
		}

		return aMapping.findForward(forward);

	}

	/*
	 * Saves the gang information on click of finish.
	 */
	public ActionForward saveGangInfo(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		JuvenileGangsForm gangsForm = (JuvenileGangsForm) aForm;

		// Calls the save event service to persist data on JCGANG table.
		SaveJuvenileGangsEvent saveEvent = (SaveJuvenileGangsEvent) EventFactory
				.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEGANGS);

		saveEvent.setJuvenileNum(gangsForm.getJuvenileNum());

		Iterator<JuvenileGangRequestEvent> gngFormListItr = gangsForm.getNewGangsInfoList().iterator();

		if (!gngFormListItr.hasNext()) {
			return aMapping.findForward(UIConstants.FAILURE);
		}

		while (gngFormListItr.hasNext()) {
			saveEvent.addRequest((RequestEvent) gngFormListItr.next());
		}

		MessageUtil.postRequest(saveEvent);
		gangsForm.setAction(UIConstants.CONFIRM);

		TraitsForm traitsForm;

		traitsForm = (TraitsForm) getSessionForm(aMapping, aRequest,
				UIConstants.JUVENILE_TRAITS_FORM, true);
		traitsForm.clear();
		traitsForm.setAction(UIConstants.VIEW);

		return aMapping.findForward(UIConstants.FINISH_SUCCESS);
	}
	
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

}
