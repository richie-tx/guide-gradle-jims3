//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplayRoleCreateAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetCodesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class DisplayRoleCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB7E3004E
	 */
	public DisplayRoleCreateAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 425551F9006F
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.SA_CREATE_SUCCESS;
		RoleForm roleForm = (RoleForm) aForm;
		//	Retrieve SETCIC Access and JMC Rep drop code table if user is MasterAdministrator
		//	Agency types should already be loaded from search page. 
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		roleForm.setAgencyId("");
		roleForm.setAgencyName("");
		roleForm.setFeatureName("");
		roleForm.setAvailableAgencies(emptyColl);
		roleForm.setSubSystemName("");
		roleForm.setFeatureCategoryId("");
		roleForm.clearStringArray(roleForm.getSelectedFeatures());
		roleForm.clearStringArray(roleForm.getSelectedAgencies());
		roleForm.setFeatures(emptyColl);
		roleForm.setAvailableFeatures(emptyColl);
		if (SecurityUIHelper.isUserMA() && roleForm.getAction().equals(UIConstants.CREATE))
		{
			roleForm.setCurrentAgencies(emptyColl);
			roleForm.setCurrentFeatures(emptyColl);
		}
		else
			if (SecurityUIHelper.isUserSA() && roleForm.getAction().equals(UIConstants.CREATE))
			{
				roleForm.setCurrentFeatures(emptyColl);
			}
		if (SecurityUIHelper.isUserMA())
		{
			Collection codeTable = fetchDropDownCodes(PDCodeTableConstants.AGENCY_TYPE);
			roleForm.setAgencyTypes(codeTable);
			codeTable = SecurityUIHelper.setJMCREPDropDownCodes();
			roleForm.setJmcReps(codeTable);
			forward = UIConstants.MA_CREATE_SUCCESS;
		}
		if (roleForm.getAction().equalsIgnoreCase(UIConstants.COPY))
		{
			String roleName = roleForm.getRoleName();
			if (roleName != null && !(roleName.equals("")))
			{
				roleName = roleName.trim();
				String originalRoleName = roleForm.getOriginalRoleName().trim();
				if (roleName.equalsIgnoreCase(originalRoleName))
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.copy.duplicate.roleName"));
					saveErrors(aRequest, errors);
					return aMapping.findForward(UIConstants.COPY_FAILURE);
				}
			}
			if (SecurityUIHelper.isUserMA())
			{
				forward = UIConstants.MA_COPY_SUCCESS;
			}
			else
				if (SecurityUIHelper.isUserSA())
				{
					forward = UIConstants.SA_COPY_SUCCESS;
				}
				else
				{
					forward = UIConstants.FAILURE;
				}
		}
		return aMapping.findForward(forward);
	}

	/**
	* 
	* @param CodeTableName
	* @return codes
	*/
	public Collection fetchDropDownCodes(String codeTableName)
	{
		GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODES);
		codesRequestEvent.setCodeTableName(codeTableName);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(codesRequestEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection codes = (Collection) dataMap.get(PDCodeTableConstants.getCodeTableTopic(codeTableName));
		codes = MessageUtil.processEmptyCollection(codes);
		Collections.sort((ArrayList) codes);
		return codes;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String masterAdmin = roleForm.getMasterAdmin();
		roleForm.clear();
		roleForm.setMasterAdmin(masterAdmin);		
		return aMapping.findForward(UIConstants.CANCEL);	
	}		
}