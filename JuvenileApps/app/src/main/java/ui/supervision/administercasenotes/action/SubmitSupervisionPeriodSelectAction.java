/*
 * Created on Feb 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administercasenotes.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitSupervisionPeriodSelectAction extends LookupDispatchAction {

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		String supPeriodId=myForm.getSelectedValue();
		myForm.setSupervisionPeriodId(supPeriodId);
		SuperviseeInfoHeaderForm mySupInfoHeaderForm = UICommonSupervisionHelper
		.getSuperviseeInfoHeaderForm(aRequest, true);
		mySupInfoHeaderForm.setSupervisionPeriodId(supPeriodId);
		CasenoteJournalForm myJournalForm = UICommonSupervisionHelper
		.getCasenoteJournalForm(aRequest, true);
		myJournalForm.setSuperviseeId(myForm.getSpn());
		myJournalForm.setSupervisionPeriod(myForm.getSupervisionPeriod());
		myJournalForm.setSupervisionPeriodId(myForm.getSupervisionPeriodId());
		return aMapping.findForward("adminJournalView");
	}
	
	public ActionForward link(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		return null;
	}
	
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		String tempAction = myForm.getAction();
		myForm.clearAll();
		// set defaults up
		myForm.setAction(tempAction);
		if (tempAction.equalsIgnoreCase(UIConstants.ADVANCED))
			return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);

		return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
