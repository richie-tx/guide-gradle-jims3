/*
 * Created on Jul 26, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
import ui.juvenilecase.form.JuvenileProfileForm;
/**
 * @author CShimek
 */
public class HandleFamilyMemberLeftNavSelectAction extends JIMSBaseAction
{

	public HandleFamilyMemberLeftNavSelectAction() {
	}
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.details", "getMemberDetails");
		keyMap.put("button.cancel", "cancel");
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMemberSearchForm jmSearchForm = (JuvenileMemberSearchForm) aForm;
		jmSearchForm.clear();
		jmSearchForm.clearSearchResults();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward getMemberDetails(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
//			JuvenileMemberSearchForm jmSearchForm = (JuvenileMemberSearchForm) aForm;
			JuvenileProfileForm jpForm = (JuvenileProfileForm) getSessionForm(aMapping, aRequest, "juvenileProfileHeader", true);
			jpForm.clear();
			JuvenileMemberForm jmForm = (JuvenileMemberForm) getSessionForm(aMapping, aRequest, "juvenileMemberForm", true);;
			jmForm.setSecondaryAction("popup");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
}