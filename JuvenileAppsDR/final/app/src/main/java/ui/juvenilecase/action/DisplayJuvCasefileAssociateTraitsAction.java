package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.TraitsForm;

public class DisplayJuvCasefileAssociateTraitsAction extends LookupDispatchAction {

	private final String JUVENILE_CASEFILE_FORM = "juvenileCasefileForm";

	/**
	 * @roseuid 42A7348001AE
	 */
	public DisplayJuvCasefileAssociateTraitsAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42A731DC028B
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = null;

		TraitsForm form = (TraitsForm) aForm;

		/*  
		 * Code to display error message if the supervision number is not selected.
		 * */
		if (form.getSupervisionNum() == null || form.getSupervisionNum().equals("")) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.supervisionNumber"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		if (form.getCategoryName() != null
				&& (form.getCategoryName().equalsIgnoreCase(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_ABUSE)||form.getCategoryName().equalsIgnoreCase(PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_DUALSTATUS))) {
			String myForward = setForwardMapping(UIConstants.SUCCESS, form.getCategoryName());
			JuvenileAbuseForm abuseform=new JuvenileAbuseForm();
			abuseform.clear();
			forward = aMapping.findForward(myForward);
		} else {
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}

		return forward;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		TraitsForm form = (TraitsForm) aForm;
		String myForward = setForwardMapping(UIConstants.CANCEL, form.getCategoryName());
		ActionForward forward = aMapping.findForward(myForward);
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}

	private String setForwardMapping(String aForward, String aCategoryForward) {
		if (aCategoryForward != null && aCategoryForward.length() > 0)
			return aForward + aCategoryForward;
		else
			return aForward;
	}
}
