//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplayRoleSearchAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgencyEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.LoadSecurityCodeTables;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class DisplayRoleSearchAction extends Action
{

	/**
	 * @roseuid 425695E6033C
	 */
	public DisplayRoleSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 425551F8006E
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.clear();
		roleForm.setMasterAdmin("");

		LoadSecurityCodeTables load = LoadSecurityCodeTables.getInstance();
	   	load.setRoleForm(roleForm);
	   	   
		if (SecurityUIHelper.isUserMA())
		{
			roleForm.setMasterAdmin("Y");
			Collection codeTable = SecurityUIHelper.setJMCREPDropDownCodes();
			roleForm.setJmcReps(codeTable);			
		}
		else
			if (SecurityUIHelper.isUserSA())
			{
				String agencyId = SecurityUIHelper.getUserAgencyId();
				if (agencyId == null || agencyId.equals(""))
				{
					return aMapping.findForward(UIConstants.FAILURE);
				}
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				GetAgencyEvent event = (GetAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCY);
				event.setAgencyId(agencyId);
				dispatch.postEvent(event);

				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);				

				AgencyResponseEvent agency = (AgencyResponseEvent) MessageUtil.filterComposite(compositeResponse, AgencyResponseEvent.class);
				if (agency != null)
				{
					Collection myAgency = new ArrayList();
					myAgency.add(agency);
					roleForm.setCurrentAgencies(myAgency);
					roleForm.setAgencyName(agency.getAgencyName());
					roleForm.setAgencyId(agency.getAgencyId());
				}
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			}
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}