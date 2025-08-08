//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\SubmitUpdatePasswordAction.java

package ui.security.authentication.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.GetJIMS2AccountEvent;
import messaging.authentication.UpdateJIMS2AccountPasswordEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.PDSecurityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.form.LoginForm;
//import messaging.authentication.ManageSessionEvent;
//import messaging.security.authentication.reply.LoginErrorResponseEvent;

public class SubmitUpdatePasswordAction extends LookupDispatchAction
{

    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.submit", "submit");
	keyMap.put("button.back", "back");
	keyMap.put("button.reset", "reset");
	keyMap.put("button.cancel", "cancel");
	return keyMap;
    }

    /**
     * @roseuid 4399CE4403BE
     */
    public SubmitUpdatePasswordAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4398426801FB
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	LoginForm loginForm = (LoginForm) aForm;
	String password = loginForm.getJims2Password();
	String newPassword = loginForm.getNewPassword();
	//	String JIMSPassword = loginForm.getPassword();
	//newPassword = loginForm.getNewPassword();
	String userType = loginForm.getUserType();

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CompositeResponse compositeResponse;
	if ((userType.equals("nonGenericUser")) || (userType.equals(PDSecurityConstants.NON_GENERIC_USER)))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Non-Generic user cannot update the password from JIMS2 Application"));
	    saveErrors(aRequest, errors);
	    loginForm.setAction("updatePassword");
	    return aMapping.findForward(UIConstants.FAILURE);
	}
		
	if ((userType.equals("genericUser")) || userType.equals("genericSP") || userType.equals(PDSecurityConstants.SERVICE_PROVIDER) || userType.equals(PDSecurityConstants.OFFICER_PROFILE))
	{
	    if (!loginForm.getCurrentPassword().toUpperCase().equals(password.toUpperCase()))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
		saveErrors(aRequest, errors);
		loginForm.setAction("updatePassword");
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    UpdateJIMS2AccountPasswordEvent updateEvent = (UpdateJIMS2AccountPasswordEvent) EventFactory.getInstance(LogonControllerServiceNames.UPDATEJIMS2ACCOUNTPASSWORD);
	    updateEvent.setJIMS2LogonId(loginForm.getJims2UserId());
	    updateEvent.setCurrentPassword(loginForm.getCurrentPassword());
	    updateEvent.setNewPassword(newPassword);
	    updateEvent.setSecurityManagerUserId(loginForm.getSmUserId());
	    //updateEvent.setPasswordQuestionId(loginForm.getForgottenPasswdPhraseId());
	    //updateEvent.setAnswer(loginForm.getAnswer());
	    dispatch.postEvent(updateEvent);
	}
	else
	{
	    // this path should not occur but did during testing when user type value(s)were modified from original values 			
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Password not changed. Unable to find User information"));
	    saveErrors(aRequest, errors);
	    loginForm.clearJIMSMessages();
	    loginForm.setAction("updatePassword");
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	compositeResponse = (CompositeResponse) dispatch.getReply();
	ReturnException lResponse = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
	// will be true if error occured updating ACF2 or user profile				
	if (lResponse != null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", lResponse.getMessage()));
	    saveErrors(aRequest, errors);
	    loginForm.clearJIMSMessages();
	    //loginForm.setErrorMessage(lResponse.getMessage());
	    loginForm.setAction("updatePassword");
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	LoginErrorResponseEvent loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
	if (loginErr != null)
	{
	    ActionErrors errors = new ActionErrors();
	    ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
	    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	loginForm.clearJIMSMessages();
	loginForm.setConfirmMessage("Your password has been successfully changed");
	loginForm.setAction("updatePassword");
	loginForm.setJims2Password(newPassword);
	loginForm.setConfirmMessage("Your password has been successfully changed.");
	// Verify user has JIMS2Account
	compositeResponse = findJIMS2Account(loginForm.getJims2UserId()); //U.S #79250 Revisit later
	JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
	// User does not have JIMS2Account if this is true and should not continue to JIMS2 Main Menu 
	if (jims2User == null)
	{
	    loginForm.setConfirmMessage("Your password has been successfully changed. Log in with your new password.");
	    loginForm.setAction("");
	    return aMapping.findForward(UIConstants.BACK_MODIFY);
	}

	String forward = UIConstants.SUCCESS;
	loginForm.setAction("");
	// if user accessed page within JIMS2, send alert message and logoff user 
	if (loginForm.getFromPage().equalsIgnoreCase(UIConstants.MAIN_MENU))
	{
	    forward = UIConstants.UPDATE_SUCCESS;
	    loginForm.setAction("updatePassword");
	}
	return aMapping.findForward(forward);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	LoginForm loginForm = (LoginForm) aForm;
	loginForm.clearLoginDetails();
	loginForm.clearJIMSMessages();
	loginForm.setAction("updatePassword");
	ActionForward forward = aMapping.findForward(UIConstants.BACK);
	return forward;
    }

    public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	LoginForm loginForm = (LoginForm) aForm;
	loginForm.clearLoginDetails();
	loginForm.clearJIMSMessages();
	loginForm.setAction("updatePassword");
	ActionForward forward = aMapping.findForward("reset");
	return forward;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
	return forward;
    }

    public CompositeResponse findJIMS2Account(String jims2UserId)
    {
	GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
	accountEvent.setJIMS2LogonId(jims2UserId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(accountEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	return compositeResponse;

    }

}
