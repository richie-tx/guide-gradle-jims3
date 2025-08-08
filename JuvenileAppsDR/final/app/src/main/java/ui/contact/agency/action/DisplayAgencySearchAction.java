//Source file: C:\\views\\archproduction\\app\\src\\ui\\contact\\agency\\action\\DisplayAgencySearchAction.java

package ui.contact.agency.action;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.contact.agency.form.AgencyForm;
import ui.security.SecurityUIHelper;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayAgencySearchAction extends Action
{

	/**
	 * @roseuid 4113F60D0206
	 */
	public DisplayAgencySearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4107F6F0019A
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		if (!SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}

		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.clear();

		Collection agencyTypeCodes = CodeHelper.getCodes(naming.PDCodeTableConstants.AGENCY_TYPE, true);
		Collection jmcReps = SecurityUIHelper.setJMCREPDropDownCodes();
		agencyForm.setAgencyTypes(agencyTypeCodes);
		agencyForm.setJmcReps(jmcReps);

		return aMapping.findForward(UIConstants.SUCCESS);
	}

}
