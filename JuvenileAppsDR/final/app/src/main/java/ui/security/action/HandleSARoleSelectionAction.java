//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleSARoleSelectionAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.agency.GetAgenciesByJmcRepEvent;
import messaging.security.GetRoleDetailsEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleSAForm;

public class HandleSARoleSelectionAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleSARoleSelectionAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		String forward = UIConstants.FAILURE;
		String action = aRequest.getParameter("action");
		String roleId = aRequest.getParameter("roleId");
		saRoleForm.setAction(action);
		saRoleForm.setRoleId(roleId);

		/** use roleId to find role information from form Collection roleNames for display */
		GetRoleDetailsEvent requestEvent = new GetRoleDetailsEvent();
		requestEvent.setRoleId(roleId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		RoleResponseEvent responseEvent = (RoleResponseEvent) MessageUtil.filterComposite(compositeResponse, RoleResponseEvent.class);

		saRoleForm.setRoleName(responseEvent.getRoleName());
		saRoleForm.setRoleDescription(responseEvent.getRoleDescription());
		saRoleForm.setAgencies(responseEvent.getAgencies());
		saRoleForm.setFeatures(responseEvent.getFeatures());
		Collection features = responseEvent.getFeatures();
		features = MessageUtil.processEmptyCollection(features);
		saRoleForm.setCurrentFeatures(SecurityUIHelper.sortFeatures(features));
		Collection originalAgencyCollection = saRoleForm.getAgencies();
		Iterator itOriginalAgencies = originalAgencyCollection.iterator();
		while (itOriginalAgencies.hasNext())
		{
			AgencyResponseEvent aEvent = (AgencyResponseEvent) itOriginalAgencies.next();
			saRoleForm.setAgencyName(aEvent.getAgencyName());
			saRoleForm.setAgencyId(aEvent.getAgencyId());
			break;
		}
		saRoleForm.setAllSecAdminNameInd("");
		if (action.equalsIgnoreCase(UIConstants.VIEW))
		{
			forward = UIConstants.VIEW_SUCCESS;
			if (saRoleForm.getRoleName().equalsIgnoreCase(UIConstants.ALL_SECURITIES_ADMINISTRATORS))
			{
				saRoleForm.setAllSecAdminNameInd("Y");
			}
		}
		if (action.equalsIgnoreCase(UIConstants.DELETE))
		{
			forward = UIConstants.DELETE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.UPDATE))
		{
			Collection emptyColl = new ArrayList();
			emptyColl = MessageUtil.processEmptyCollection(emptyColl);
			saRoleForm.setFeatures(emptyColl);
			saRoleForm.setAvailableFeatures(emptyColl);
			saRoleForm.setFeatureName("");
			saRoleForm.setSubSystemName("");
			saRoleForm.setFeatureCategoryId("");

			GetAgenciesByJmcRepEvent event = new GetAgenciesByJmcRepEvent();
			event.setJmcRepId("Y");
			dispatch.postEvent(event);

			compositeResponse = (CompositeResponse) dispatch.getReply();
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
			agencies = SecurityUIHelper.getAgenciesWhichDoesNotHaveSARole(agencies);
			Collections.sort((List) agencies );
			saRoleForm.setJmcAgencies(agencies);
			if (saRoleForm.getRoleName().equalsIgnoreCase(UIConstants.ALL_SECURITIES_ADMINISTRATORS))
			{
				saRoleForm.setAllSecAdminNameInd("Y");
			}

			forward = UIConstants.UPDATE_SUCCESS;
		}
		return aMapping.findForward(forward);
	}

}
