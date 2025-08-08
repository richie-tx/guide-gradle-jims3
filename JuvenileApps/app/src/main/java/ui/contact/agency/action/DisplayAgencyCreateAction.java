//Source file: C:\\views\\archproduction\\app\\src\\ui\\contact\\agency\\action\\DisplayAgencyCreateAction.java

package ui.contact.agency.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.agency.form.AgencyForm;

/**
 * 
 */
public class DisplayAgencyCreateAction extends Action
{

	/**
	 * @roseuid 4113F60D00DA
	 */
	public DisplayAgencyCreateAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4107F6EF012A
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		if (!ui.security.SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.clear(); //clear the form in case user has tried to edit and press back button
		agencyForm.setJmcRep(UIConstants.NON_JMCREP_CODE); //set JMC Rep Code to no as the default
		agencyForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}

}
