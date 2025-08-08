//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayUserGroupSearchAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.SecurityUIHelper;
import ui.security.form.UserGroupForm;

public class DisplayUserGroupSearchAction extends Action
{

	/**
	 * @roseuid 42971FCD00BB
	 */
	public DisplayUserGroupSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BB030F
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		userGroupForm.clear();

		String userType = SecurityUIHelper.getUserTypeId();
		if (userType == null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		{
			userGroupForm.setUserType(userType);
		}

		if (!userType.equals(UIConstants.MA_ROLETYPE))
		{
			userGroupForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
		}

		Collection statuses = fetchDropDownCodes(PDCodeTableConstants.USER_GROUP_STATUS);
		userGroupForm.setStatuses(statuses);

		return aMapping.findForward(UIConstants.SUCCESS);
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
}