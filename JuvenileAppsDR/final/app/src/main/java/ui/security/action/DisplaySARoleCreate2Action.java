//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleCreate2Action.java

package ui.security.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.agency.reply.AgencyResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.form.RoleSAForm;

public class DisplaySARoleCreate2Action extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB6E501F4
	 */
	public DisplaySARoleCreate2Action()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.next", "next");
		buttonMap.put("button.back", "back");

		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D631031E
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;

		String agencyId = saRoleForm.getAgencyId();
		Collection originalAgencyCollection = saRoleForm.getAgencies();
		Iterator itOriginalAgencies = originalAgencyCollection.iterator();
		while (itOriginalAgencies.hasNext())
		{
			AgencyResponseEvent aEvent = (AgencyResponseEvent) itOriginalAgencies.next();
			if (agencyId.equalsIgnoreCase(aEvent.getAgencyId()))
			{
				saRoleForm.setAgencyName(aEvent.getAgencyName());
				break;
			}
		}

		saRoleForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}
}