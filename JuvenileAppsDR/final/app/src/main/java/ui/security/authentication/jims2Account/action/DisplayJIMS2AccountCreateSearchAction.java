//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\jims2Account\\DisplayJIMS2CreateSearchAction.java

package ui.security.authentication.jims2Account.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayJIMS2AccountCreateSearchAction extends LookupDispatchAction
{

    /**
     * @roseuid 4562205E0208
     */
    public DisplayJIMS2AccountCreateSearchAction()
    {

    }

    protected Map getKeyMethodMap()
    {
	Map buttonMap = new HashMap();
	buttonMap.put("button.next", "next");
	buttonMap.put("button.reset", "reset");
	buttonMap.put("button.cancel", "cancel");
	return buttonMap;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
	String forward = UIConstants.FAILURE;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetUserProfilesEvent uEvent = (GetUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILES);
	uEvent.setLogonId(jaForm.getSearchLogonId());
	dispatch.postEvent(uEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);

	UserResponseEvent uResp = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
	if (uResp == null)
	{
	    this.errorSave(aRequest, jaForm, "error.no.userProfile.found");
	    return aMapping.findForward(UIConstants.SEARCH_FAILURE);
	}

	String userType = uResp.getGenericUserType();
	if (userType == null)
	{
	    this.errorSave(aRequest, jaForm, "error.invalid.userId");
	    forward = UIConstants.SEARCH_FAILURE;
	}
	else
	{
	    jaForm.setUserType(userType);
	    jaForm.setAction(UIConstants.CREATE);
	    jaForm.setPageType(UIConstants.SUMMARY);
	    jaForm.setJimsLogonId(uResp.getLogonId());
	    if (userType.equalsIgnoreCase("N"))
	    {
		this.setForm(jaForm, uResp.getFirstName(), uResp.getMiddleName(), uResp.getLastName(), uResp.getDepartmentId(), uResp.getDepartmentName(), uResp.getEmail(), uResp.getEmail());
		forward = UIConstants.SUCCESS;
	    }
	    else
		if (userType.equalsIgnoreCase("L"))
		{
		    jaForm.setSearchBadgeNumber("");
		    jaForm.setSearchOtherIdNumber("");
		    jaForm.setDepartmentId(uResp.getDepartmentId());
		    forward = UIConstants.OFFICER_PROFILE_SUCCESS;
		}
		else
		    if (userType.equalsIgnoreCase("S"))
		    {
			jaForm.setSearchEmployeeId("");
			jaForm.setDepartmentId(uResp.getDepartmentId());
			forward = UIConstants.SERVICE_PROVIDER_SUCCESS;
		    }
		    else
		    {
			this.errorSave(aRequest, jaForm, "error.invalid.userId");
			forward = UIConstants.SEARCH_FAILURE;
		    }
	}
	return aMapping.findForward(forward);
    }

    /**
     * @param request
     * @param jaForm
     * @param errorKey
     */
    private void errorSave(HttpServletRequest request, JIMS2AccountForm jaForm, String errorKey)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
	saveErrors(request, errors);
	this.setForm(jaForm, "", "", "", "", "", "", "");
    }

    /**
     * @param jaForm
     * @param firstName
     * @param middleName
     * @param lastName
     * @param departmentId
     * @param departmentName
     * @param jims2LogonId
     * @param reenterJims2LogonId
     */
    private void setForm(JIMS2AccountForm jaForm, String firstName, String middleName, String lastName, String departmentId, String departmentName, String jims2LogonId, String reenterJims2LogonId)
    {
	jaForm.setFirstName(firstName);
	jaForm.setMiddleName(middleName);
	jaForm.setLastName(lastName);
	jaForm.setDepartmentId(departmentId);
	jaForm.setDepartmentName(departmentName);
	jaForm.setJims2LogonId(jims2LogonId);
	jaForm.setReenterJIMS2LogonId(reenterJims2LogonId);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
	jaForm.clear();
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
	jaForm.setSearchLogonId("");
	return aMapping.findForward(UIConstants.RESET_SUCCESS);
    }
}
