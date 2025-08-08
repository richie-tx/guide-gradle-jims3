//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\SubmitUserProfileDeleteAction.java

package ui.contact.user.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.user.DeleteUserProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.user.form.UserProfileForm;

public class SubmitUserProfileDeleteAction extends LookupDispatchAction
{
	
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.returnProfile","returnProfile");
		return buttonMap;
	}
	/**
	 * @roseuid 43F4FC420033
	 */
	public SubmitUserProfileDeleteAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 43F4EE51033E
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		Collection coll = userProfileForm.getUserProfileDetails();
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			UserProfileForm.UserProfileDetail profileDetail = (UserProfileForm.UserProfileDetail)iter.next();
			userProfileForm.setLogonId(profileDetail.getLogonId());
		}

		DeleteUserProfileEvent deleteEvent =
			(DeleteUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.DELETEUSERPROFILE);
			
		deleteEvent.setLogonId(userProfileForm.getLogonId());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deleteEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}
	
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserProfileForm userProfileForm = (UserProfileForm) aForm;
		userProfileForm.clear();
		userProfileForm.clearUserProfiles();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	public ActionForward returnProfile(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	
		return aMapping.findForward("searchResults");
	}
}
