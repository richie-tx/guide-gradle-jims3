//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\HandleValidateUserAction.java
package ui.security.authentication.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.appshell.UserEvent;
import messaging.authentication.CreateJIMS2AccountEvent;
import messaging.authentication.GetJIMS2AccountEvent;
import messaging.authentication.ManageSessionEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.notification.GetUserNoticesEvent;
import messaging.notification.reply.NotificationResponseEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import messaging.security.authentication.reply.NoFeaturesErrorResponseEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.security.SecurityUser;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.securitytransactionsevents.LoginEvent;
import mojo.messaging.securitytransactionsevents.reply.AuthenticationFailedResponseEvent;
import mojo.messaging.securitytransactionsevents.reply.LoginResponseEvent;
import mojo.naming.CommonConstants;
import naming.LogonControllerServiceNames;
import naming.NotificationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDSecurityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import pd.security.PDSecurityHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.security.SecurityUIHelper;
import ui.security.authentication.AuthenticationHelper;
import ui.security.authentication.form.LoginForm;
import ui.security.authentication.jims2Account.UIJIMS2AccountHelper;
import ui.task.TaskHelper;

public class HandleValidateUserAction extends LookupDispatchAction
{

    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.submit", "submit");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.reset", "reset");
	return keyMap;
    }

    /**
     * @roseuid 4399CE430053
     */
    public HandleValidateUserAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 439711A70333
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	LoginForm loginForm = (LoginForm) aForm;

	String logonId = loginForm.getLogonId().toUpperCase();
	String password = loginForm.getPassword();
	loginForm.clear();

	if (logonId != null)
	{
	    if (logonId.indexOf("@") > -1)
	    {
		loginForm.setJims2UserId(logonId);
		loginForm.setJims2Password(password); //86322
	    }
	}

	if (loginForm.getAuthenticationMethod().equalsIgnoreCase("SP")) //service provider flow. generic users.
	{
	    /*  HttpSession session = aRequest.getSession();
	      GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
	      accountEvent.setJIMS2LogonId(loginForm.getJims2UserId());
	      IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	      dispatch.postEvent(accountEvent);
	      CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	      LoginErrorResponseEvent loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
	      if (loginErr != null)
	      {
	    ActionErrors errors = new ActionErrors();
	    ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
	    errors.add(ActionErrors.GLOBAL_ERROR, error);
	    saveErrors(aRequest, errors);
	    loginForm.setLogonId(logonId);
	    return aMapping.findForward(UIConstants.FAILURE);
	      }
	      JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
	      if (jims2User == null)
	      {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "LOGIN ERROR --- USER NOT FOUND!"));
	    loginForm.clearLoginDetails();
	    saveErrors(aRequest, errors);
	    loginForm.setLogonId(logonId);
	    return (aMapping.findForward(UIConstants.FAILURE));
	      }
	      if (jims2User.getStatus() != null && jims2User.getStatus().equals("I"))
	      {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "The JIMS2 Account is Inactive, please contact your Security Administrator."));
	    loginForm.clearLoginDetails();
	    saveErrors(aRequest, errors);
	    loginForm.setLogonId(logonId);
	    return (aMapping.findForward(UIConstants.FAILURE));
	      }
	      if (jims2User.getJIMS2AccountTypeId()!= null && jims2User.getJIMS2AccountTypeId().equals("N"))
	      {
	    ActionErrors errors = new ActionErrors();
	    
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "JIMS2 Generic Account not found for user " + loginForm.getJims2UserId()
	    	+ ", please contact your Security Administrator to create one or if you have an AD Account, please login with your AD account."));
	    loginForm.clearLoginDetails();
	    saveErrors(aRequest, errors);
	    loginForm.setLogonId(logonId);
	    return (aMapping.findForward(UIConstants.FAILURE));
	      }
	      String jimsPassword = jims2User.getJimsPassword();
	      //String jims2Password = jims2User.getJIMS2Password().toUpperCase();
	      String jimsLogonId = jims2User.getJimsLogonId();
	      loginForm.setJimsUserId(jimsLogonId);
	      String accountTypeId = jims2User.getJIMS2AccountTypeId();
	      loginForm.setLogonId(logonId);
	      loginForm.setUserType(accountTypeId);
	    
	      if (jimsPassword != null)
	      {
	    if (accountTypeId.equals(PDSecurityConstants.OFFICER_PROFILE) || accountTypeId.equals(PDSecurityConstants.SERVICE_PROVIDER))
	    {
	        if (!(jims2User.getJIMS2Password().equalsIgnoreCase(password)))
	        {
	    	ActionErrors errors = new ActionErrors();
	    	errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
	    	loginForm.clearLoginDetails();
	    	loginForm.setLogonId(logonId);
	    	saveErrors(aRequest, errors);
	    	return aMapping.findForward(UIConstants.FAILURE);
	        }
	    }
	    ValidateUserProfileEvent validateEvent = (ValidateUserProfileEvent) EventFactory.getInstance(LogonControllerServiceNames.VALIDATEUSERPROFILE);
	    validateEvent.setLogonId(jimsLogonId);
	    IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch1.postEvent(validateEvent);
	    compositeResponse = (CompositeResponse) dispatch1.getReply();
	    Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(validateDataMap);
	    LoginErrorResponseEvent loginError = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
	    if ((loginError != null) && (!loginError.getMessage().equals("The JIMS2 Account is Inactive, please contact your Security Administrator.")) && (!loginError.getMessage().equals("User already has a JIMS2 Account - Please Login using your JIMS2 User ID")))
	    {
	        ActionErrors errors = new ActionErrors();
	        ActionMessage error = new ActionMessage("error.authenticate.user", loginError.getMessage());
	        errors.add(ActionErrors.GLOBAL_ERROR, error);
	        saveErrors(aRequest, errors);
	        loginForm.setLogonId(logonId);
	        return aMapping.findForward(UIConstants.FAILURE);
	    }
	    LoginEvent loginEvent = new LoginEvent();
	    loginEvent.setUsername(jimsLogonId);
	    loginEvent.setPassword(jimsPassword);
	    dispatch.postEvent(loginEvent);
	    IEvent lReply = dispatch.getReply();
	    if (lReply instanceof mojo.km.messaging.noop.NoReply)
	    {
	        ActionErrors errors = new ActionErrors();
	        ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
	        errors.add(ActionErrors.GLOBAL_ERROR, error);
	        saveErrors(aRequest, errors);
	        loginForm.setLogonId(logonId);
	        return aMapping.findForward(UIConstants.FAILURE);
	    }
	    compositeResponse = (CompositeResponse) lReply;
	    AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
	    if (err != null)
	    {
	        String type = err.getErrorType();
	        loginForm.setLogonId(logonId);
	        loginForm.setJims2UserId(loginForm.getJims2UserId());
	        if (type.equals(CommonConstants.PROFILE_EXPIRED_ERROR) || type.equals(CommonConstants.PASSWORD_EXPIRED_ERROR))
	        {
	    	if (accountTypeId.equalsIgnoreCase("L") || accountTypeId.equalsIgnoreCase("S"))
	    	{
	    	    ActionErrors errors = new ActionErrors();
	    	    ActionMessage error = new ActionMessage("error.generic", "Password has expired. Please contact the ITC Help Desk at 713-755-6624 for assistance");
	    	    errors.add(ActionErrors.GLOBAL_ERROR, error);
	    	    saveErrors(aRequest, errors);
	    	    return aMapping.findForward(UIConstants.FAILURE);
	    	}
	    	populateLoginFormInfo(aForm, aRequest, aMapping, password);
	    	loginForm.setLogonId(logonId);
	    	UserEvent user = new UserEvent(logonId.toUpperCase(), loginForm.getUserName().toString(), "");
	    	session.setAttribute("userInfo", user);
	    	return aMapping.findForward(UIConstants.UPDATEPASSWORD);
	        }
	        if (type.equals(CommonConstants.PROFILE_SUSPENDED_ERROR))
	        {
	    	ActionErrors errors = new ActionErrors();
	    	ActionMessage error = new ActionMessage("error.suspended.password", getUserSupervisorInfo(jimsLogonId));
	    	errors.add(ActionErrors.GLOBAL_ERROR, error);
	    	saveErrors(aRequest, errors);
	    	return aMapping.findForward(UIConstants.FAILURE);
	        }
	        if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
	        {
	    	ActionErrors errors = new ActionErrors();
	    	ActionMessage error = new ActionMessage("error.suspended.user.profile", getUserSupervisorInfo(jimsLogonId));
	    	errors.add(ActionErrors.GLOBAL_ERROR, error);
	    	saveErrors(aRequest, errors);
	    	return aMapping.findForward(UIConstants.FAILURE);
	        }
	        ActionErrors errors = new ActionErrors();
	        ActionMessage error = new ActionMessage("error.authenticate.user", err.getErrorMessage());
	        errors.add(ActionErrors.GLOBAL_ERROR, error);
	        saveErrors(aRequest, errors);
	        loginForm.setLogonId(logonId);
	        return aMapping.findForward(UIConstants.FAILURE);
	    }
	    ReturnException ret = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
	    if (ret != null)
	    {

	        ActionErrors errors = new ActionErrors();
	        ActionMessage error = new ActionMessage("error.authenticate.user", ret.getMessage());
	        errors.add(ActionErrors.GLOBAL_ERROR, error);
	        saveErrors(aRequest, errors);
	        loginForm.setLogonId(logonId);
	        return aMapping.findForward(UIConstants.FAILURE);
	    }

	    ManageSessionEvent sessionEvent = (ManageSessionEvent) EventFactory.getInstance(LogonControllerServiceNames.MANAGESESSION);
	    sessionEvent.setJIMS2AccountTypeId(accountTypeId);
	    sessionEvent.setJIMS2AccountTypeOID(jims2User.getJIMS2AccountTypeOID());
	    sessionEvent.setFirstName(jims2User.getFirstName());
	    sessionEvent.setLastName(jims2User.getLastName());
	    sessionEvent.setMiddleName(jims2User.getMiddleName());
	    sessionEvent.setJIMS2LogonId(jims2User.getJIMS2LogonId());
	    sessionEvent.setJIMS2Password(password);
	    sessionEvent.setJIMSLogonId(jimsLogonId);
	    sessionEvent.setJIMSPassword(jimsPassword);
	    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch2.postEvent(sessionEvent);
	    compositeResponse = (CompositeResponse) dispatch2.getReply();
	    Map validateMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(validateMap);
	    loginError = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);

	    if (loginError != null)
	    {
	        session.setAttribute("USERLOGIN", "FAILURE");
	        session.removeAttribute("userInfo");
	        ActionErrors errors = new ActionErrors();
	        errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", loginError.getMessage()));
	        saveErrors(aRequest, errors);
	        loginForm.setLogonId(logonId);
	        return aMapping.findForward(UIConstants.FAILURE);
	    }

	    NoFeaturesErrorResponseEvent noFeatures = (NoFeaturesErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, NoFeaturesErrorResponseEvent.class);
	    if (noFeatures != null)
	    {
	        loginForm.setRolesMessage(noFeatures.getMessage());
	    }
	    loginForm.setAccountTypeId(accountTypeId);

	    if (accountTypeId.equals(PDSecurityConstants.OFFICER_PROFILE))
	    {
	        loginForm.setUserType("genericUser");
	    }
	    else if (accountTypeId.equals(PDSecurityConstants.SERVICE_PROVIDER))
	    {
	        loginForm.setUserType("genericSP");
	    }
	    loginForm.setAccountTypeOID(jims2User.getJIMS2AccountTypeOID());
	    loginForm.setJims2UserId(jims2User.getJIMS2LogonId());
	    loginForm.setJims2Password(jims2User.getJIMS2Password());
	    loginForm.setLogonId(jims2User.getJimsLogonId());
	    loginForm.setPassword(jimsPassword);
	    loginForm.setUserName(new Name(jims2User.getFirstName(), jims2User.getMiddleName(), jims2User.getLastName()));
	    loginForm.setUserWorkPhoneNumber(new PhoneNumber(jims2User.getPhoneNum()));
	    loginForm.getUserWorkPhoneNumber().setExt(jims2User.getPhoneExt());
	    loginForm.setUserEmail(jims2User.getEmail());
	    //loginForm.setForgottenPasswdPhrase(jims2User.getForgottenPasswdPhrase());
	    //loginForm.setForgottenPasswdPhraseId(jims2User.getForgottenPasswdPhraseId());
	    //loginForm.setAnswer(jims2User.getPasswordAnswer());
	    
	    session.setAttribute("USERLOGIN", "SUCCESS");
	    UserEvent user = new UserEvent(logonId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
	    session.setAttribute("userInfo", user);
	    loginForm.clearJIMSMessages();

	    this.callNotificationFramework(user, loginForm);
	    loginForm.setLogonId("");
	    System.err.println("HTTP Session Id: [" + session.getId() + "]" + " JIMS2 USER_ID: [" + jims2User.getJIMS2LogonId() + "]");
	    AuthenticationHelper.getOfficerCaseload(loginForm);

	    return aMapping.findForward("jims2Success");
	      }
	      else
	      {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
	    saveErrors(aRequest, errors);
	    loginForm.clearLoginDetails();
	    loginForm.setLogonId(logonId);
	    return (aMapping.findForward(UIConstants.FAILURE));
	      }*/

	    //service provider flow. Phase 2
	    HttpSession session = aRequest.getSession();

	    GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
	    accountEvent.setJIMS2LogonId(loginForm.getJims2UserId());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(accountEvent);
	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    LoginErrorResponseEvent loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
	    if (loginErr != null)
	    {
		ActionErrors errors = new ActionErrors();
		ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
		saveErrors(aRequest, errors);
		loginForm.setLogonId(loginForm.getJims2UserId());
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
	    if (jims2User == null) //non-existing users
	    {
		String noOfAttempts = (String)session.getAttribute(loginForm.getJims2UserId());
		if (noOfAttempts == null)
		{
		    noOfAttempts = "1";
		    session.setAttribute(loginForm.getJims2UserId(), noOfAttempts);
		}
		else
		{
		    int attempts = Integer.valueOf(noOfAttempts) + 1;
		    noOfAttempts = String.valueOf(attempts);
		    session.setAttribute(loginForm.getJims2UserId(), noOfAttempts);
		}
		//call security manager web-service EMAIL
		IEvent lReply = authenticateUser(dispatch, loginForm.getJims2UserId(), password, "EMAIL",noOfAttempts);
		if (lReply == null)
		{
		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + logonId + " could not be located");
		    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(logonId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}

		if (lReply instanceof mojo.km.messaging.noop.NoReply)
		{
		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
		    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(logonId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		compositeResponse = (CompositeResponse) lReply;
		AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
		if (err != null)
		{
		    String type = err.getErrorType();
		    loginForm.setLogonId(logonId);
		    loginForm.setJims2UserId(loginForm.getJims2UserId());
		    if (type.equals(CommonConstants.INCORRECT_PASSWORD_ERROR))
		    {

			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.generic", "Incorrect password supplied.  Supply the correct password or your account will be suspended");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);

		    }
		    if (type.equals(CommonConstants.PROFILE_SUSPENDED_ERROR))
		    {

			//ActionErrors errors = new ActionErrors();
			//ActionMessage error = new ActionMessage("error.suspended.password", logonId);//TODO revisit getUserSupervisorInfo(logonId));
			//errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			//saveErrors(aRequest, errors);
			loginForm.setErrorMessage("User profile is suspended or invalid.  Please contact Data.Corrections@hcjpd.hctx.net.");
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
		    {
			//ActionErrors errors = new ActionErrors();
			//ActionMessage error = new ActionMessage("error.suspended.user.profile", logonId);//TODO revisit getUserSupervisorInfo(logonId));
			//errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			//saveErrors(aRequest, errors);
			loginForm.setErrorMessage("User profile is suspended or invalid.  Please contact Data.Corrections@hcjpd.hctx.net.");
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		}
		LoginResponseEvent loginResponse = (LoginResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginResponseEvent.class);

		if (loginResponse != null && loginResponse.getSecUser().getJIMSLogonId() == null)
		{
		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", loginResponse.getMessage());
		    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(logonId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		String jimsLogonId = loginResponse.getSecUser().getJIMSLogonId(); //jucode
		if (jimsLogonId != null)
		{
		    loginForm.setSmUserId(loginResponse.getSecUser().getUserOID());
		    loginForm.setUserType("genericSP");
		    populateLoginFormInfo(loginForm, loginResponse);
		    /* 
		     GetUserProfileEvent requestEvent = (GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
		     requestEvent.setLogonId(jimsLogonId);
		     requestEvent.setSmUserId(loginForm.getSmUserId());
		     dispatch.postEvent(requestEvent);
		     compositeResponse = (CompositeResponse) dispatch.getReply();

		     Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		     MessageUtil.processReturnException(dataMap);
		     UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
		     if (userResponse != null)
		     {
		    loginForm.setJimsUserId(jimsLogonId);
		    loginForm.setSmUserId(loginResponse.getSecurityManagerUserId());
		    loginForm.setLogonId(logonId);
		    loginForm.setUserType(userResponse.getGenericUserType());
		    loginForm.setDepartmentName(userResponse.getDepartmentName());
		    loginForm.setDepartmentId(userResponse.getDepartmentId());
		    loginForm.setAccountTypeId(userResponse.getGenericUserType());
		    loginForm.setUserName(new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName()));
		    loginForm.setUserWorkPhoneNumber(new PhoneNumber(userResponse.getPhoneNum()));
		    // no longer in use. Migrated to SM. Refer US #87188.
		    //loginForm.getUserWorkPhoneNumber().setExt(userResponse.getPhoneExt());
		    //loginForm.setCreateOfficerProfileInd(userResponse.getCreateOfficerProfileInd());
		    //loginForm.setUserName(new Name(userResponse.getFirstName(),userResponse.getMiddleName(),userResponse.getLastName()));
		    
		     }
		     else
		     {
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "User Profile not found!!")); // no records on j2sec user. to be handled in phase II.
		    loginForm.setLogonId(loginForm.getJims2UserId());
		    loginForm.clearLoginDetails();
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		     }*/
		    err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);

		    ReturnException ret = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
		    if (ret != null)
		    {
			ret.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", ret.getMessage());
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(loginForm.getJims2UserId());
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		    CreateJIMS2AccountEvent cRequest = (CreateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
		    cRequest.setJIMS2LogonId(loginForm.getJims2UserId());
		    cRequest.setLogonId(jimsLogonId);
		//    cRequest.setCreate(true);
		    cRequest = UIJIMS2AccountHelper.prepareCreateRequestEvent(cRequest, loginForm);
		    dispatch.postEvent(cRequest);
		    compositeResponse = (CompositeResponse) dispatch.getReply();
		    jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
		    if (jims2User != null && !jims2User.isAccountCreated())
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", "LOGIN ERROR --- USER NOT FOUND!"));
			loginForm.clearLoginDetails();
			saveErrors(aRequest, errors);
			loginForm.setLogonId(logonId);
			return (aMapping.findForward(UIConstants.FAILURE));
		    }

		    //populate the other information.
		    accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
		    accountEvent.setJIMS2LogonId(jims2User.getJIMS2LogonId());
		    dispatch.postEvent(accountEvent);
		    compositeResponse = (CompositeResponse) dispatch.getReply();
		    loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
		    if (loginErr != null)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(jims2User.getJimsLogonId());
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
		    ActionForward forward = loginUser(jimsLogonId, loginForm, logonId, loginResponse, jims2User, aMapping, aForm, aRequest, aResponse);
		    return forward;
		}

	    }
	    else
	    //existing users flow.
	    {
		if (jims2User.getStatus() != null && jims2User.getStatus().equals("I"))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", "The JIMS2 Account is Inactive, please contact your Security Administrator."));
		    loginForm.clearLoginDetails();
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(loginForm.getJims2UserId());
		    return (aMapping.findForward(UIConstants.FAILURE));
		}
		String jimsLogonId = jims2User.getJimsLogonId();
		loginForm.setJimsUserId(jimsLogonId);
		String accountTypeId = jims2User.getJIMS2AccountTypeId();
		loginForm.setLogonId(loginForm.getJims2UserId());
		loginForm.setUserType(accountTypeId);

		if (password != null)
		{
		    //JUCODE Authentication
		    String noOfAttempts = (String) session.getAttribute(logonId);
		    if (noOfAttempts == null)
		    {
			noOfAttempts = "1";
			session.setAttribute(logonId, noOfAttempts);
		    }
		    else
		    {
			int attempts = Integer.valueOf(noOfAttempts) + 1;
			noOfAttempts = String.valueOf(attempts);
			session.setAttribute(logonId, noOfAttempts);
		    }
		    // Webservice- using EMAIl.
		    IEvent lReply = authenticateUser(dispatch, logonId, password, "EMAIL", noOfAttempts);
		    if (lReply == null)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + logonId + " could not be located");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(logonId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		    if (lReply instanceof mojo.km.messaging.noop.NoReply)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(logonId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    compositeResponse = (CompositeResponse) lReply;
		    AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
		    if (err != null)
		    {
			String type = err.getErrorType();
			loginForm.setLogonId(logonId);
			loginForm.setJims2UserId(loginForm.getJims2UserId());
			if (type.equals(CommonConstants.INCORRECT_PASSWORD_ERROR))
			{

			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.generic", "Incorrect password supplied.  Supply the correct password or your account will be suspended");
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);

			}
			if (type.equals(CommonConstants.PROFILE_SUSPENDED_ERROR))
			{

			   /* ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.suspended.password", getUserSupervisorInfo(logonId));
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);*/
			    loginForm.setErrorMessage("User profile is suspended or invalid.  Please contact Data.Corrections@hcjpd.hctx.net.");
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
			{
			    /*ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.suspended.user.profile", getUserSupervisorInfo(logonId));
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);*/
			    loginForm.setErrorMessage("User profile is suspended or invalid.  Please contact Data.Corrections@hcjpd.hctx.net.");
			    return aMapping.findForward(UIConstants.FAILURE);
			}
		    }

		    LoginResponseEvent loginResponse = (LoginResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginResponseEvent.class);

		    if (loginResponse != null)
		    {
			SecurityUser secUser = loginResponse.getSecUser();
			populateLoginFormInfo(loginForm, loginResponse);
			if (secUser.getJIMSLogonId() == null)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", loginResponse.getMessage());
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    loginForm.setLogonId(logonId);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			jimsLogonId = secUser.getJIMSLogonId();
			loginForm.setSmUserId(secUser.getUserOID());
			ActionForward forward = loginUser(jimsLogonId, loginForm, logonId, loginResponse, jims2User, aMapping, aForm, aRequest, aResponse);

			return forward;
		    }
		    return null;
		}
		else
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
		    saveErrors(aRequest, errors);
		    loginForm.clearLoginDetails();
		    loginForm.setLogonId(loginForm.getJims2UserId());
		    return (aMapping.findForward(UIConstants.FAILURE));
		}
	    }
	}
	else
	    if (loginForm.getAuthenticationMethod().equals("AD"))
	    //NON-Generic User
	    {
		//AD logon  Authentication.
		HttpSession session = aRequest.getSession();
		//call the AD logon webservice
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (password != null)
		{
		    //authenticate user
		    String noOfAttempts = (String) session.getAttribute(logonId);
		    if (noOfAttempts == null)
		    {
			noOfAttempts = "1";
			session.setAttribute(logonId, noOfAttempts);
		    }
		    else
		    {
			int attempts = Integer.valueOf(noOfAttempts) + 1;
			noOfAttempts = String.valueOf(attempts);
			session.setAttribute(logonId, noOfAttempts);
		    }
		    IEvent lReply = authenticateUser(dispatch, logonId, password, "AD", noOfAttempts);
		    if (lReply == null)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + logonId + " could not be located");
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(logonId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		    if (lReply instanceof mojo.km.messaging.noop.NoReply)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(logonId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    CompositeResponse compositeResponse = (CompositeResponse) lReply;
		    AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
		    if (err != null)
		    {
			String type = err.getErrorType();
			loginForm.setLogonId(logonId);
			loginForm.setJims2UserId(loginForm.getJims2UserId());
			if (type.equals(CommonConstants.INCORRECT_PASSWORD_ERROR))
			{

			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.generic", "Incorrect password supplied.  Supply the correct password or your account will be suspended");
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);

			}
			if (type.equals(CommonConstants.PROFILE_SUSPENDED_ERROR))
			{

			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.suspended.password", getUserSupervisorInfo(logonId));
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.suspended.user.profile", getUserSupervisorInfo(logonId));
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
		    }

		    LoginResponseEvent loginResponse = (LoginResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginResponseEvent.class);
		    if (loginResponse != null && loginResponse.getSecUser().getJIMSLogonId() == null)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", loginResponse.getMessage());
			errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(logonId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    if (loginResponse != null)
		    {
			String userId = loginResponse.getSecUser().getJIMSLogonId();
			if (userId != null)
			{
			    populateLoginFormInfo(loginForm, loginResponse);
			    /*GetUserProfileEvent requestEvent = (GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
			    requestEvent.setLogonId(userId);
			    requestEvent.setSmUserId(smUserId);
			    requestEvent.setGenericType("N");
			    dispatch.postEvent(requestEvent);
			    compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    MessageUtil.processReturnException(dataMap);
			    UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);*/
			    /*  if (userResponse != null)
			      {*/
			    /*		loginForm.setJimsUserId(userId);//uvcode
			    		loginForm.setLogonId(logonId); //credentials
			    		loginForm.setUserType("N");
			    		loginForm.setDepartmentName(userResponse.getDept().getDepartmentdescription());
			    		loginForm.setDepartmentId(userResponse.getDepartmentId());
			    		loginForm.setAccountTypeId("N");
			    		loginForm.setUserName(new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName()));
			    		loginForm.setUserWorkPhoneNumber(new PhoneNumber(userResponse.getPhone()));
			    		loginForm.setFirstName(userResponse.getFirstname());
			    		loginForm.setLastName(userResponse.getLastname());
			    		loginForm.setMiddleName("");
			    		loginForm.setAgencyName(userResponse.getAgency().getAgencyname());
			    		loginForm.setAgencyId(userResponse.getAgencyId());
			    		loginForm.setEmail(userResponse.getContactemail());
			    		 dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			    		ValidateOfficerProfileEvent officerProfile = (ValidateOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
			    		officerProfile.setLogonId(userId);
			    		dispatch.postEvent(officerProfile);
			    		compositeResponse = (CompositeResponse) dispatch.getReply();
			    		OfficerProfileResponseEvent officerResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
			    		if (officerResponse != null)
			    		{
			    		    loginForm.setBadgeNumber(officerResponse.getBadgeNum());
			    		    loginForm.setOtherIdNumber(officerResponse.getBadgeNum());
			    		}*/
			    //loginForm.getUserWorkPhoneNumber().setExt(userResponse.getPhoneExt());
			    //loginForm.setCreateOfficerProfileInd(userResponse.getCreateOfficerProfileInd());
			    /*   }*/
			    /*else
			    {
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "User Profile not found!!")); // no records on j2sec user. to be handled in phase II.
			    loginForm.setLogonId(userId);
			    loginForm.clearLoginDetails();
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			    }*/

			    ReturnException ret = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
			    if (ret != null)
			    {
				ret.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", ret.getMessage());
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				loginForm.setLogonId(userId);
				return aMapping.findForward(UIConstants.FAILURE);
			    }

			    GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
			    accountEvent.setLogonId(userId);
			    dispatch.postEvent(accountEvent);
			    compositeResponse = (CompositeResponse) dispatch.getReply();
			    LoginErrorResponseEvent loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
			    if (loginErr != null)
			    {
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				loginForm.setLogonId(userId);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
			    if (jims2User == null)
			    {
				CreateJIMS2AccountEvent cRequest = (CreateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
				cRequest = UIJIMS2AccountHelper.prepareCreateRequestEvent(cRequest, loginForm);
				dispatch.postEvent(cRequest);
				compositeResponse = (CompositeResponse) dispatch.getReply();
				jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
				if (jims2User != null && !jims2User.isAccountCreated())
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", "LOGIN ERROR --- USER NOT FOUND!"));
				    loginForm.clearLoginDetails();
				    saveErrors(aRequest, errors);
				    loginForm.setLogonId(logonId);
				    return (aMapping.findForward(UIConstants.FAILURE));
				}
				//bug 85297
				if (jims2User != null && jims2User.isAccountCreated())
				{
				    accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
				    accountEvent.setLogonId(jims2User.getJimsLogonId());
				    dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				    dispatch.postEvent(accountEvent);
				    compositeResponse = (CompositeResponse) dispatch.getReply();
				    loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
				    if (jims2User != null && !jims2User.isAccountCreated())
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", "LOGIN ERROR --- USER NOT FOUND!"));
					loginForm.clearLoginDetails();
					saveErrors(aRequest, errors);
					loginForm.setLogonId(logonId);
					return (aMapping.findForward(UIConstants.FAILURE));
				    }
				    //populate the user info.For the new users.
				    jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
				    //populate the other information.
				    accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
				    accountEvent.setLogonId(userId);
				    dispatch.postEvent(accountEvent);
				    compositeResponse = (CompositeResponse) dispatch.getReply();
				    loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
				    if (loginErr != null)
				    {
					ActionErrors errors = new ActionErrors();
					ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
					errors.add(ActionErrors.GLOBAL_MESSAGE, error);
					saveErrors(aRequest, errors);
					loginForm.setLogonId(userId);
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}
				//bug 85297
			    }
			    //loginUser //Ad logon Authentication
			    ActionForward forward = loginUser(userId, loginForm, logonId, loginResponse, jims2User, aMapping, aForm, aRequest, aResponse);
			    return forward;
			}
		    }
		}
		else
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
		    saveErrors(aRequest, errors);
		    loginForm.clearLoginDetails();
		    loginForm.setLogonId(logonId);
		    return (aMapping.findForward(UIConstants.FAILURE));
		}
	    }
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", "LOGIN ERROR --- USER NOT FOUND!"));
	loginForm.clearLoginDetails();
	saveErrors(aRequest, errors);
	loginForm.setLogonId(logonId);
	return (aMapping.findForward(UIConstants.FAILURE));
    }

    /**
     * loginUser
     * 
     * @param uvCode
     * @param loginForm
     * @param logonId
     * @param loginResponse
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    private ActionForward loginUser(String uvCode, LoginForm loginForm, String logonId, LoginResponseEvent loginResponse, JIMS2AccountResponseEvent jims2User, ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();

	String userId = uvCode;
	String accountTypeId = jims2User.getJIMS2AccountTypeId();

	ManageSessionEvent sessionEvent = (ManageSessionEvent) EventFactory.getInstance(LogonControllerServiceNames.MANAGESESSION);
	sessionEvent.setJIMS2AccountTypeId(accountTypeId);
	sessionEvent.setJIMS2AccountTypeOID(jims2User.getJIMS2AccountTypeOID());
	sessionEvent.setFirstName(jims2User.getFirstName());
	sessionEvent.setLastName(jims2User.getLastName());
	sessionEvent.setMiddleName(jims2User.getMiddleName());
	sessionEvent.setJIMS2LogonId(jims2User.getJIMS2LogonId());
	//sessionEvent.setJIMS2Password(loginForm.getJims2Password());//current password
	sessionEvent.setJIMSLogonId(userId);
	sessionEvent.setSecurityManagerUserId(loginForm.getSmUserId());
	//sessionEvent.setJIMSPassword(jimsPassword);
	sessionEvent.setLoginResponse(loginResponse);

	IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch2.postEvent(sessionEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch2.getReply();
	Map validateMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(validateMap);
	LoginErrorResponseEvent loginError = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);

	if (loginError != null)
	{
	    session.setAttribute("USERLOGIN", "FAILURE");
	    session.removeAttribute("userInfo");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authenticate.user", loginError.getMessage()));
	    saveErrors(aRequest, errors);
	    loginForm.setLogonId(userId);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	NoFeaturesErrorResponseEvent noFeatures = (NoFeaturesErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, NoFeaturesErrorResponseEvent.class);
	if (noFeatures != null)
	{
	    loginForm.setRolesMessage(noFeatures.getMessage());
	}
	loginForm.setAccountTypeId(accountTypeId);
	session.setAttribute("USERLOGIN", "SUCCESS");
	if (accountTypeId.equals(PDSecurityConstants.NON_GENERIC_USER))
	{
	    loginForm.setUserType("nonGenericUser");
	    UserEvent userevent = new UserEvent(userId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
	    session.setAttribute("userInfo", userevent);
	    loginForm.clearJIMSMessages();
	    this.callNotificationFramework(userevent, loginForm);
	}
	else if (accountTypeId.equals(PDSecurityConstants.OFFICER_PROFILE))
	{
	    loginForm.setUserType("genericUser");
	    loginForm.setUserType("nonGenericUser");
	    UserEvent userevent = new UserEvent(userId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
	    session.setAttribute("userInfo", userevent);
	    loginForm.clearJIMSMessages();
	    this.callNotificationFramework(userevent, loginForm);
	}
	else if (accountTypeId.equals(PDSecurityConstants.SERVICE_PROVIDER))
	{
	    loginForm.setUserType("genericSP");
	    UserEvent userevent = new UserEvent(logonId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
	    session.setAttribute("userInfo", userevent);
	    loginForm.clearJIMSMessages();
	    this.callNotificationFramework(userevent, loginForm);
	}
	/*loginForm.setAccountTypeOID(jims2User.getJIMS2AccountTypeOID());
	loginForm.setLogonId(jims2User.getJimsLogonId());
	loginForm.setUserName(new Name(jims2User.getFirstName(), jims2User.getMiddleName(), jims2User.getLastName()));
	loginForm.setUserWorkPhoneNumber(new PhoneNumber(jims2User.getPhoneNum()));
	loginForm.getUserWorkPhoneNumber().setExt(jims2User.getPhoneExt());
	loginForm.setUserEmail(jims2User.getEmail());*/

	//loginForm.setForgottenPasswdPhrase(jims2User.getForgottenPasswdPhrase());
	//loginForm.setForgottenPasswdPhraseId(jims2User.getForgottenPasswdPhraseId());
	//loginForm.setAnswer(jims2User.getPasswordAnswer());
	
	loginForm.setLogonId("");
	System.err.println("HTTP Session Id: [" + session.getId() + "]" + " JIMS2 USER_ID: [" + jims2User.getJIMS2LogonId() + "]");

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	ValidateOfficerProfileEvent officerProfile = (ValidateOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
	officerProfile.setLogonId(userId);
	officerProfile.setDepartmentId(loginForm.getDepartmentId());
	officerProfile.setDepartmentName(loginForm.getDepartmentName());
	officerProfile.setAgencyId(loginForm.getAgencyId());

	dispatch.postEvent(officerProfile);
	compositeResponse = (CompositeResponse) dispatch.getReply();
	OfficerProfileResponseEvent officerResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
	if (officerResponse != null)
	{
	    loginForm.setBadgeNumber(officerResponse.getBadgeNum());
	    loginForm.setOtherIdNumber(officerResponse.getBadgeNum());
	}
	AuthenticationHelper.getOfficerCaseload(loginForm);

	return aMapping.findForward("jims2Success");
    }

    /**
     * callNotificationFramework
     * 
     * @param user
     * @param loginForm
     */
    private void callNotificationFramework(UserEvent user, LoginForm loginForm)
    {
	// call Task Framework
	ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	IUserInfo userInfo = mgr.getIUserInfo();
	String agencyId = userInfo.getAgencyId();
	int submittedTaskCount = 0;
	StringBuffer taskSize = new StringBuffer("");
	if (!UIConstants.CSC.equalsIgnoreCase(agencyId))
	{

	    Collection tasks = TaskHelper.getNotClosedUserTasks(SecurityUIHelper.getLogonId(), UIConstants.CLOSED_STATUS_ID);
	    Iterator tasksIter = tasks.iterator();
	    SortedMap taskMap = new TreeMap();
	    while (tasksIter.hasNext())
	    {
		TaskResponseEvent resp = (TaskResponseEvent) tasksIter.next();
		String statusId = resp.getTask().getStatusCode();
		if (statusId != null && statusId.equalsIgnoreCase(UIConstants.SUBMITTED_STATUS_ID))
		{
		    submittedTaskCount++;
		}
		taskMap.put(resp.getTask().getSubmittedDate(), resp);
	    }

	    Collection sortedList = this.sortCollection(taskMap);

	    taskSize.append(submittedTaskCount);
	    taskSize.append(" / ");
	    if (tasks != null && !tasks.isEmpty())
	    {
		taskSize.append(tasks.size());
	    }
	    else
	    {
		taskSize.append("0");
	    }
	    loginForm.setTaskCount(taskSize.toString());
	    loginForm.setTaskList(new ArrayList(sortedList));
	}
	else
	{

	    taskSize.append("0");
	    loginForm.setTaskCount(taskSize.toString());
	}

	// call notice framework
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetUserNoticesEvent pEvent = (GetUserNoticesEvent) EventFactory.getInstance(NotificationControllerServiceNames.GETUSERNOTICES);
	pEvent.setDestinationIdentityId(SecurityUIHelper.getLogonId());
	dispatch.postEvent(pEvent);

	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	Collection notices = MessageUtil.compositeToCollection(response, NotificationResponseEvent.class);
	Iterator noticeIter = notices.iterator();
	SortedMap noticeMap = new TreeMap();
	while (noticeIter.hasNext())
	{
	    NotificationResponseEvent nEvent = (NotificationResponseEvent) noticeIter.next();
	    if(!nEvent.getNotification().getSubject().equalsIgnoreCase("SERVICE LOCATION REMOVAL")){ //US 174962
		noticeMap.put(nEvent.getNotification().getSentDate(), nEvent);
	    }
	    
	}
	Collection sortedNoticeList = this.sortNoticeCollection(noticeMap);
	loginForm.setNoticeList(sortedNoticeList);
	if (sortedNoticeList != null)
	{
	    loginForm.setNoticeListSize(new StringBuffer("").append(sortedNoticeList.size()).toString());
	}
	else
	{
	    loginForm.setNoticeListSize("0");
	}
    }

    /**
     * sortCollection
     * 
     * @param taskMap
     * @return Collection
     */
    private Collection sortCollection(SortedMap taskMap)
    {
	LinkedList<TaskResponseEvent> stack = new LinkedList<TaskResponseEvent>();
	Iterator<TaskResponseEvent> it = taskMap.values().iterator();
	while (it.hasNext())
	{
	    TaskResponseEvent resp = (TaskResponseEvent) it.next();
	    stack.addFirst(resp);
	}

	Collection list = new ArrayList();
	Object[] obj = stack.toArray();
	for (int i = 0; i < obj.length; i++)
	{
	    list.add(obj[i]);
	}
	return list;
    }

    /**
     * sortNoticeCollection
     * 
     * @param taskMap
     * @return Collection
     */
    private Collection sortNoticeCollection(SortedMap noticeMap)
    {
	LinkedList stack = new LinkedList();
	Iterator it = noticeMap.values().iterator();
	while (it.hasNext())
	{
	    NotificationResponseEvent nEvent = (NotificationResponseEvent) it.next();
	    stack.addFirst(nEvent);
	}

	Collection list = new ArrayList();
	Object[] obj = stack.toArray();
	for (int i = 0; i < obj.length; i++)
	{
	    list.add(obj[i]);
	}
	return list;
    }

    /**
     * cancel
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
	LoginForm loginForm = (LoginForm) aForm;
	loginForm.clearJIMSMessages();
	loginForm.clearLoginDetails();
	return forward;
    }

    /**
     * reset
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward reset(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	LoginForm loginForm = (LoginForm) aForm;
	loginForm.clearLoginDetails();
	loginForm.clearJIMSMessages();
	ActionForward forward = aMapping.findForward("reset");
	return forward;
    }

    /**
     * authenticateUser
     * 
     * @param dispatch
     * @param userID
     * @param password
     * @return IEvent
     */
    public IEvent authenticateUser(IDispatch dispatch, String adLogonId, String password, String credentialType, String noOfAttempts)
    {
	//String userId = null;

	LoginEvent loginEvent = new LoginEvent();
	loginEvent.setUsername(adLogonId);
	loginEvent.setPassword(password);
	loginEvent.setCredentialType(credentialType);
	loginEvent.setNoOfAttempts(noOfAttempts);
	dispatch.postEvent(loginEvent);

	IEvent lReply = EventManager.getSharedInstance(EventManager.REPLY).getReply();
	//TODO need to revist .
	///CompositeResponse compositeResponse = (CompositeResponse) lReply;
	//LoginResponseEvent loginResponse = (LoginResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginResponseEvent.class);

	/*if (loginResponse != null && loginResponse.getUvCode() != null)
	{
	    userId = loginResponse.getUvCode();

	    Iterator<User> users = User.findAll("JIMSLogonId", userId);
	    StringBuffer message = new StringBuffer();
	    User lUser = null;
	    if (users != null && users.hasNext())
	    {
		lUser = (User) users.next();
		String dept = lUser.getDepartmentId();
		PrimaryDeptContact deptContact = lUser.getPrimaryContact(dept);
		if (deptContact != null)
		{
		    String phone = deptContact.getPhoneNum();
		    String extension = deptContact.getPhoneExt();
		    String formattedPhone = deptContact.getFormattedPhoneNum();

		    if ((extension != null) && (!(extension.equals(""))))
		    {
			message = message.append(" ").append(deptContact.getFormattedName()).append(" at ").append(formattedPhone).append(" ext. ").append(extension);
		    }
		    else
		    {
			message = message.append(" ").append(deptContact.getFormattedName()).append(" at ").append(formattedPhone);
		    }
		}
	    }
	}*/

	/*if (userId == null || password == null) ..validated on the UI
	{
	    return null;
	}*/

	return lReply;
    }

    /**
     * getUserSupervisorInfo
     * 
     * @param userID
     * @return String
     */
    public String getUserSupervisorInfo(String userID)
    {
	StringBuffer message = new StringBuffer();
	String phone ="";
	String extension="";
	String departmentId = PDSecurityHelper.getUserDepartmentId();
	Department department = Department.find(departmentId);
	if(department!=null){
	    if(department.getContacts()!=null){
		List<DepartmentContact> departmentContacts = (List<DepartmentContact>) department.getContacts();
		Iterator<DepartmentContact> departmentContactsItr = departmentContacts.iterator();
		while(departmentContactsItr.hasNext()){
		    DepartmentContact contact = departmentContactsItr.next();
		    if(contact!=null && contact.getPrimaryContact()!=null){
			if(contact.getPrimaryContact().equalsIgnoreCase("Y")){
			    phone = contact.getPhoneNum();
			    extension = contact.getPhoneExt();
			    if ((extension != null) && (!(extension.equals(""))))
			    {
			        message = message.append(" ").append(contact.getFullNameWithLastNameFirst()).append(" at ").append(phone).append(" ext. ").append(extension);
			    }
			    else
			    {
			        message = message.append(" ").append(contact.getFullNameWithLastNameFirst()).append(" at ").append(phone);
			    }
			}
		    }
		}
	    }
	}
	
	//87191
/*	Iterator users = //User.findAll("JIMSLogonId", userID);
	StringBuffer message = new StringBuffer();
	User lUser = null;
	if (users != null && users.hasNext())
	{
	    lUser = (User) users.next();
	    String dept = lUser.getDepartmentId();
	     PrimaryDeptContact deptContact = lUser.getPrimaryContact(dept);//86318
	     if (deptContact != null)
	     {
	    String phone = deptContact.getPhoneNum();
	    String extension = deptContact.getPhoneExt();
	    String formattedPhone = deptContact.getFormattedPhoneNum();

	    if ((extension != null) && (!(extension.equals(""))))
	    {
	        message = message.append(" ").append(deptContact.getFormattedName()).append(" at ").append(formattedPhone).append(" ext. ").append(extension);
	    }
	    else
	    {
	        message = message.append(" ").append(deptContact.getFormattedName()).append(" at ").append(formattedPhone);
	    }
	     }
	}*/
	return message.toString();
    }

    /**
     * populateLoginFormInfo
     * 
     * @param aForm
     * @param aRequest
     * @param aMapping
     * @param password
     */
    public void populateLoginFormInfo(LoginForm loginForm, LoginResponseEvent loginResponse)
    {
	String userId = loginResponse.getSecUser().getJIMSLogonId();
	SecurityUser userResponse = loginResponse.getSecUser();
	if (userId != null)
	{

	    loginForm.setJimsUserId(userId);//uvcode
	    loginForm.setDepartmentName(userResponse.getDepartmentName());
	    loginForm.setDepartmentId(userResponse.getDepartmentId());
	    if(loginForm.getUserType()!=null && !loginForm.getUserType().isEmpty() && !loginForm.getUserType().equalsIgnoreCase("N") && loginForm.getAuthenticationMethod().equalsIgnoreCase("SP"))
	    {
		loginForm.setAccountTypeId("S");
	    }
	    else
	    {
		loginForm.setAccountTypeId("N");
	    }
	    loginForm.setUserName(new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName()));
	    loginForm.setUserWorkPhoneNumber(new PhoneNumber(userResponse.getWorkPhone()));
	    loginForm.setFirstName(userResponse.getFirstName());
	    loginForm.setLastName(userResponse.getLastName());
	    loginForm.setMiddleName("");
	    loginForm.setAgencyName(userResponse.getAgencyName());
	    loginForm.setAgencyId(userResponse.getAgencyId());
	    loginForm.setEmail(userResponse.getEmail());
	    loginForm.setSmUserId(userResponse.getUserOID());
	}
    }
}
//87191
/*    public void populateLoginFormInfo(ActionForm aForm, HttpServletRequest aRequest, ActionMapping aMapping, String password)
    {
	LoginForm loginForm = (LoginForm) aForm;
	String userId = loginForm.getLogonId();

	GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
	accountEvent.setLogonId(userId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(accountEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	LoginErrorResponseEvent loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
	if (loginErr != null)
	{
	    ActionErrors errors = new ActionErrors();
	    ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
	    errors.add(ActionErrors.GLOBAL_ERROR, error);
	    saveErrors(aRequest, errors);
	    //loginForm.setErrorMessage(loginError.getMessage());
	    loginForm.setLogonId(loginForm.getLogonId());
	    return;
	}

	JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
	if (jims2User != null)
	{
	    //  String jimsPassword = jims2User.getJimsPassword();
	    //String jims2Password = jims2User.getJIMS2Password().toUpperCase();
	    String jimsLogonId = jims2User.getJimsLogonId();
	    String accountTypeId = jims2User.getJIMS2AccountTypeId();

	    // if (accountTypeId.equals(PDSecurityConstants.NON_GENERIC_USER))
	    // {
	    //	jimsPassword = password;
	    //  }
	    loginForm.setAccountTypeId(accountTypeId);
	    loginForm.setAccountTypeOID(jims2User.getJIMS2AccountTypeOID());
	    loginForm.setJims2UserId(jims2User.getJIMS2LogonId());
	    //   loginForm.setJims2Password(jims2User.getJIMS2Password());
	    loginForm.setLogonId(jims2User.getJimsLogonId());
	    //   loginForm.setPassword(jimsPassword);
	    loginForm.setUserName(new Name(jims2User.getFirstName(), jims2User.getMiddleName(), jims2User.getLastName()));
	    loginForm.setUserWorkPhoneNumber(new PhoneNumber(jims2User.getPhoneNum()));
	    loginForm.getUserWorkPhoneNumber().setExt(jims2User.getPhoneExt());
	    loginForm.setUserEmail(jims2User.getEmail());
	    //   loginForm.setForgottenPasswdPhrase(jims2User.getForgottenPasswdPhrase());
	    //   loginForm.setForgottenPasswdPhraseId(jims2User.getForgottenPasswdPhraseId());
	    //   loginForm.setAnswer(jims2User.getPasswordAnswer());
	}
    }*/

/**
 * //U.S #79250
 * 
 * @param aMapping
 * @param aForm
 * @param aRequest
 * @param aResponse
 * @return ActionForward
 * @roseuid 439711A70333 ACF call Logic - old. one.
 */
/*    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	LoginForm loginForm = (LoginForm) aForm;
	String userId = loginForm.getLogonId().toUpperCase();
	String password = loginForm.getPassword();
	loginForm.clear();

	boolean found = false;
	if (userId != null)
	{
	    if (userId.indexOf("@") > -1)
	    {
		loginForm.setJims2UserId(userId);
		loginForm.setLogonId("");
		found = true;
	    }
	}
	if (!found)
	{
	    if (userId.length() <= 5)
	    {
		GetUserProfileEvent requestEvent = (GetUserProfileEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
		requestEvent.setLogonId(userId);
		//requestEvent.setThinResponseInd(true);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		UserResponseEvent userResponse = (UserResponseEvent) MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);
		if (userResponse != null)
		{
		    loginForm.setJimsUserId(userResponse.getLogonId());
		    loginForm.setLogonId(userResponse.getLogonId());
		    loginForm.setUserType(userResponse.getGenericUserType());
		    loginForm.setDepartmentName(userResponse.getDepartmentName());
		    loginForm.setDepartmentId(userResponse.getDepartmentId());
		    loginForm.setAccountTypeId(userResponse.getGenericUserType());
		    loginForm.setUserName(new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName()));
		    loginForm.setUserWorkPhoneNumber(new PhoneNumber(userResponse.getPhoneNum()));
		    loginForm.getUserWorkPhoneNumber().setExt(userResponse.getPhoneExt());
		    loginForm.setCreateOfficerProfileInd(userResponse.getCreateOfficerProfileInd());
		    //authenticate user
		    IEvent lReply = authenticateUser(dispatch, userId, password);
		    if (lReply == null)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + userId + " could not be located");
			errors.add(ActionErrors.GLOBAL_ERROR, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(userId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		    if (lReply instanceof mojo.km.messaging.noop.NoReply)
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
			errors.add(ActionErrors.GLOBAL_ERROR, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(userId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    compositeResponse = (CompositeResponse) lReply;
		    //					ReturnException ret = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
		    //					if (ret != null)
		    //					{
		    //						ret.printStackTrace();
		    //						ActionErrors errors = new ActionErrors();
		    //						ActionMessage error = new ActionMessage("error.authenticate.user", ret.getMessage());
		    //						errors.add(ActionErrors.GLOBAL_ERROR, error);
		    //						saveErrors(aRequest, errors);
		    //						loginForm.setLogonId(userId);
		    //						return aMapping.findForward(UIConstants.FAILURE);
		    //					}
		    AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
		    if (err != null)
		    {
			String type = err.getErrorType();
			loginForm.setLogonId(userId);
			loginForm.setJims2UserId(userId);
			String accountTypeId = "";
			if (loginForm.getAccountTypeId() != null)
			{
			    accountTypeId = loginForm.getAccountTypeId().toUpperCase();
			}
			if (type.equals(CommonConstants.PASSWORD_EXPIRED_ERROR))
			{
			    // this condition may need modification, it assumes generic logon Id has accountTypeId of L or S                       	
			    if (accountTypeId.equalsIgnoreCase("L") || accountTypeId.equalsIgnoreCase("S"))
			    {
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.generic", "Password has expired. Please contact the ITC Help Desk at 713-755-6624 for assistance");
				errors.add(ActionErrors.GLOBAL_ERROR, error);
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    populateLoginFormInfo(aForm, aRequest, aMapping, password);
			    //loginForm.setUserType("nonGenericUser");
			    //return aMapping.findForward("nonGenericSuccess");
			    return aMapping.findForward(UIConstants.UPDATEPASSWORD);
			}
			if (type.equals(CommonConstants.PROFILE_EXPIRED_ERROR))
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.generic", "User ID has expired.");
			    errors.add(ActionErrors.GLOBAL_ERROR, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

			if (type.equals(CommonConstants.PROFILE_SUSPENDED_ERROR))
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.suspended.password", getUserSupervisorInfo(userId));
			    errors.add(ActionErrors.GLOBAL_ERROR, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.suspended.user.profile", getUserSupervisorInfo(userId));
			    errors.add(ActionErrors.GLOBAL_ERROR, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			if (!type.equals(CommonConstants.PROFILE_EXPIRED_ERROR))
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", err.getErrorMessage());
			    errors.add(ActionErrors.GLOBAL_ERROR, error);
			    saveErrors(aRequest, errors);
			    loginForm.setLogonId(userId);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

		    }

		    ReturnException ret = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
		    if (ret != null)
		    {
			ret.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", ret.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, error);
			saveErrors(aRequest, errors);
			loginForm.setLogonId(userId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    LoginResponseEvent lResponse = (LoginResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginResponseEvent.class);
		    if (lResponse != null)
		    {

			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.authenticate.user", lResponse.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, error);
			saveErrors(aRequest, errors);
			//loginForm.setErrorMessage(lResponse.getMessage());
			loginForm.setLogonId(userId);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    else
		    {

			ValidateUserProfileEvent validateEvent = (ValidateUserProfileEvent) EventFactory.getInstance(LogonControllerServiceNames.VALIDATEUSERPROFILE);
			validateEvent.setLogonId(userId);
			IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch1.postEvent(validateEvent);
			compositeResponse = (CompositeResponse) dispatch1.getReply();
			Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(validateDataMap);
			LoginErrorResponseEvent loginError = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
			if (loginError != null)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", loginError.getMessage());
			    errors.add(ActionErrors.GLOBAL_ERROR, error);
			    saveErrors(aRequest, errors);
			    //loginForm.setErrorMessage(loginError.getMessage());
			    loginForm.setLogonId(userId);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			else
			{
			    loginForm.setLogonId(userId);
			    loginForm.setPassword(password);
			    loginForm.setDepartmentName(userResponse.getDepartmentName());
			    loginForm.setDepartmentId(userResponse.getDepartmentId());
			    loginForm.setAccountTypeId(userResponse.getGenericUserType());
			    loginForm.setUserName(new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName()));
			    loginForm.setUserWorkPhoneNumber(new PhoneNumber(userResponse.getPhoneNum()));
			    loginForm.getUserWorkPhoneNumber().setExt(userResponse.getPhoneExt());
			    loginForm.setCreateOfficerProfileInd(userResponse.getCreateOfficerProfileInd());
			    // get region value?
			    UserEvent user = new UserEvent(userId.toUpperCase(), loginForm.getUserName().toString(), userResponse.getServer());
			    HttpSession session = aRequest.getSession();
			    session.setAttribute("userInfo", user);

			    if (!userResponse.getEmail().equals(""))
			    {
				//loginForm.setJims2UserId("jsmith@yahoo.com");
				//else
				loginForm.setJims2UserId(userResponse.getEmail());
				loginForm.setConfirmJIMS2UserId(userResponse.getEmail());
			    }

			    loginForm.setUserEmail(userResponse.getEmail());
			    loginForm.setUserStatus(userResponse.getUserStatus());
			    loginForm.clearJIMSMessages();
			    String genericUserType = userResponse.getGenericUserType();
			    if (genericUserType.equals(PDSecurityConstants.OFFICER_PROFILE))
			    {
				loginForm.setAgencyName(userResponse.getAgencyName());
				loginForm.setUserType("genericUser");
				//RAC to make logon id not show when you go to login page 
				loginForm.setLogonId("");
				return aMapping.findForward("genericOfficerSuccess");
			    }
			    else
				if (genericUserType.equals(PDSecurityConstants.SERVICE_PROVIDER))
				{
				    loginForm.setUserType("genericSP");
				    //get the service provider name
				    GetServiceProviderFromDepartmentIdEvent serviceProviderEvent = (GetServiceProviderFromDepartmentIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERFROMDEPARTMENTID);
				    serviceProviderEvent.setDepartmentId(loginForm.getDepartmentId());
				    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
				    dispatch2.postEvent(serviceProviderEvent);
				    CompositeResponse compositeResponse1 = (CompositeResponse) dispatch2.getReply();
				    Collection serviceProviders = MessageUtil.compositeToCollection(compositeResponse1, JuvenileServiceProviderResponseEvent.class);
				    boolean spfound = false;
				    if (serviceProviders != null || serviceProviders.size() != 0)
				    {
					Iterator iter = serviceProviders.iterator();
					while (iter.hasNext())
					{
					    JuvenileServiceProviderResponseEvent juv = (JuvenileServiceProviderResponseEvent) iter.next();
					    if (loginForm.getLogonId().equalsIgnoreCase(juv.getAdminUserProfileId()) || loginForm.getLogonId().equalsIgnoreCase(juv.getContactUserProfileId()))
					    {
						loginForm.setServiceProviderName(juv.getServiceProviderName());
						loginForm.setServiceProviderId(juv.getServiceProviderId());
						spfound = true;
						break;
					    }
					}
					if (!spfound)
					{
					    String errorMessage = "";
					    if (userResponse.getDepartmentId().equalsIgnoreCase("JUV"))
						errorMessage = "error.juvSP.notFound";
					    else
						if (userResponse.getDepartmentId().equalsIgnoreCase("CSC"))
						    errorMessage = "error.cscSP.notFound";
						else
						    if (userResponse.getDepartmentId().equalsIgnoreCase("PTR"))
							errorMessage = "error.ptsSP.notFound";
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage(errorMessage, "provider error"));
					    saveErrors(aRequest, errors);
					    return aMapping.findForward("failure");
					}
				    }
				    // 08-24-2007 value needed for SP create flow
				    //								loginForm.setLogonId("");
				    loginForm.setEmployeeId("");
				    return aMapping.findForward("genericSPSuccess");
				}
				else
				{
				    loginForm.setUserType("nonGenericUser");
				    // 08-20-2007 Activity #44572
				    // blanking this field here causes problem with creating JIMS2Account 								
				    //								loginForm.setLogonId("");
				    return aMapping.findForward("nonGenericSuccess");
				}
			}
		    }
		}
		else
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.incorrect.user", "user not found"));
		    loginForm.clearLoginDetails();
		    loginForm.setLogonId(userId);
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}

	    }
	    else
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.invalid.userId", "user id not found"));
		loginForm.clearLoginDetails();
		loginForm.setLogonId(userId);
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	else
	{
	    HttpSession session = aRequest.getSession();
	    GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
	    accountEvent.setJIMS2LogonId(loginForm.getJims2UserId());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(accountEvent);
	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    LoginErrorResponseEvent loginErr = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
	    if (loginErr != null)
	    {
		ActionErrors errors = new ActionErrors();
		ActionMessage error = new ActionMessage("error.authenticate.user", loginErr.getMessage());
		errors.add(ActionErrors.GLOBAL_ERROR, error);
		saveErrors(aRequest, errors);
		//loginForm.setErrorMessage(loginError.getMessage());
		loginForm.setLogonId(userId);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
	    if (jims2User == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "LOGIN ERROR --- USER NOT FOUND!"));
		loginForm.clearLoginDetails();
		saveErrors(aRequest, errors);
		loginForm.setLogonId(userId);
		return (aMapping.findForward(UIConstants.FAILURE));
	    }
	    if (jims2User.getStatus() != null && jims2User.getStatus().equals("I"))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", "The JIMS2 Account is Inactive, please contact your Security Administrator."));
		loginForm.clearLoginDetails();
		saveErrors(aRequest, errors);
		loginForm.setLogonId(userId);
		return (aMapping.findForward(UIConstants.FAILURE));
	    }
	    String jimsPassword = jims2User.getJimsPassword();
	    //String jims2Password = jims2User.getJIMS2Password().toUpperCase();
	    String jimsLogonId = jims2User.getJimsLogonId();
	    loginForm.setJimsUserId(jimsLogonId);
	    String accountTypeId = jims2User.getJIMS2AccountTypeId();
	    loginForm.setLogonId(userId);
	    loginForm.setUserType(accountTypeId);
	    if (accountTypeId.equals(PDSecurityConstants.NON_GENERIC_USER))
	    {
		jimsPassword = password;
		if (jimsPassword.length() > 8)
		{
		    jimsPassword = password.substring(0, 8);
		    System.out.println("password too long truncated to 8 for non generic user");
		}
	    }

	    if (jimsPassword != null)
	    {

		if (accountTypeId.equals(PDSecurityConstants.OFFICER_PROFILE) || accountTypeId.equals(PDSecurityConstants.SERVICE_PROVIDER))
		{
		    if (!(jims2User.getJIMS2Password().equalsIgnoreCase(password)))
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
			loginForm.clearLoginDetails();
			loginForm.setLogonId(userId);
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		}
		ValidateUserProfileEvent validateEvent = (ValidateUserProfileEvent) EventFactory.getInstance(LogonControllerServiceNames.VALIDATEUSERPROFILE);
		validateEvent.setLogonId(jimsLogonId);
		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(validateEvent);
		compositeResponse = (CompositeResponse) dispatch1.getReply();
		Map validateDataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(validateDataMap);
		LoginErrorResponseEvent loginError = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
		if ((loginError != null) && (!loginError.getMessage().equals("The JIMS2 Account is Inactive, please contact your Security Administrator.")) && (!loginError.getMessage().equals("User already has a JIMS2 Account - Please Login using your JIMS2 User ID")))
		{
		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", loginError.getMessage());
		    errors.add(ActionErrors.GLOBAL_ERROR, error);
		    saveErrors(aRequest, errors);
		    //loginForm.setErrorMessage(loginError.getMessage());
		    loginForm.setLogonId(userId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		LoginEvent loginEvent = new LoginEvent();
		//loginEvent.setUserID(jimsLogonId);
		loginEvent.setUsername(jimsLogonId);
		loginEvent.setPassword(jimsPassword);
		dispatch.postEvent(loginEvent);
		IEvent lReply = dispatch.getReply();
		if (lReply instanceof mojo.km.messaging.noop.NoReply)
		{
		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
		    errors.add(ActionErrors.GLOBAL_ERROR, error);
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(userId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		compositeResponse = (CompositeResponse) lReply;
		//compositeResponse = (CompositeResponse) dispatch.getReply();
		AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
		if (err != null)
		{
		    String type = err.getErrorType();
		    loginForm.setLogonId(userId);
		    loginForm.setJims2UserId(loginForm.getJims2UserId());
		    if (type.equals(CommonConstants.PROFILE_EXPIRED_ERROR) || type.equals(CommonConstants.PASSWORD_EXPIRED_ERROR))
		    {
			if (accountTypeId.equalsIgnoreCase("L") || accountTypeId.equalsIgnoreCase("S"))
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.generic", "Password has expired. Please contact the ITC Help Desk at 713-755-6624 for assistance");
			    errors.add(ActionErrors.GLOBAL_ERROR, error);
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			populateLoginFormInfo(aForm, aRequest, aMapping, password);
			loginForm.setLogonId(userId);
			UserEvent user = new UserEvent(userId.toUpperCase(), loginForm.getUserName().toString(), "");
			session.setAttribute("userInfo", user);
			return aMapping.findForward(UIConstants.UPDATEPASSWORD);
		    }
		    if (type.equals(CommonConstants.PROFILE_SUSPENDED_ERROR))
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.suspended.password", getUserSupervisorInfo(jimsLogonId));
			errors.add(ActionErrors.GLOBAL_ERROR, error);
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
		    {
			ActionErrors errors = new ActionErrors();
			ActionMessage error = new ActionMessage("error.suspended.user.profile", getUserSupervisorInfo(jimsLogonId));
			errors.add(ActionErrors.GLOBAL_ERROR, error);
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", err.getErrorMessage());
		    errors.add(ActionErrors.GLOBAL_ERROR, error);
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(userId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		ReturnException ret = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
		if (ret != null)
		{

		    ActionErrors errors = new ActionErrors();
		    ActionMessage error = new ActionMessage("error.authenticate.user", ret.getMessage());
		    errors.add(ActionErrors.GLOBAL_ERROR, error);
		    saveErrors(aRequest, errors);
		    loginForm.setLogonId(userId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		//				

		ManageSessionEvent sessionEvent = (ManageSessionEvent) EventFactory.getInstance(LogonControllerServiceNames.MANAGESESSION);
		sessionEvent.setJIMS2AccountTypeId(accountTypeId);
		sessionEvent.setJIMS2AccountTypeOID(jims2User.getJIMS2AccountTypeOID());
		sessionEvent.setFirstName(jims2User.getFirstName());
		sessionEvent.setLastName(jims2User.getLastName());
		sessionEvent.setMiddleName(jims2User.getMiddleName());
		sessionEvent.setJIMS2LogonId(jims2User.getJIMS2LogonId());
		sessionEvent.setJIMS2Password(password);
		sessionEvent.setJIMSLogonId(jimsLogonId);
		sessionEvent.setJIMSPassword(jimsPassword);
		IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch2.postEvent(sessionEvent);
		compositeResponse = (CompositeResponse) dispatch2.getReply();
		Map validateMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(validateMap);
		//MessageUtil.processReturnException(compositeResponse);									
		loginError = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);

		if (loginError != null)
		{
		    session.setAttribute("USERLOGIN", "FAILURE");
		    session.removeAttribute("userInfo");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.authenticate.user", loginError.getMessage()));
		    saveErrors(aRequest, errors);
		    //loginForm.setErrorMessage(loginError.message);
		    loginForm.setLogonId(userId);
		    return aMapping.findForward(UIConstants.FAILURE);
		}

		NoFeaturesErrorResponseEvent noFeatures = (NoFeaturesErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, NoFeaturesErrorResponseEvent.class);
		if (noFeatures != null)
		{
		    loginForm.setRolesMessage(noFeatures.getMessage());
		}
		loginForm.setAccountTypeId(accountTypeId);

		if (accountTypeId.equals(PDSecurityConstants.NON_GENERIC_USER))
		{
		    loginForm.setUserType("nonGenericUser");
		}
		else
		    if (accountTypeId.equals(PDSecurityConstants.OFFICER_PROFILE))
		    {
			loginForm.setUserType("genericUser");
		    }
		    else
			if (accountTypeId.equals(PDSecurityConstants.SERVICE_PROVIDER))
			{
			    loginForm.setUserType("genericSP");
			}
		loginForm.setAccountTypeOID(jims2User.getJIMS2AccountTypeOID());
		loginForm.setJims2UserId(jims2User.getJIMS2LogonId());
		loginForm.setJims2Password(jims2User.getJIMS2Password());
		loginForm.setLogonId(jims2User.getJimsLogonId());
		loginForm.setPassword(jimsPassword);
		loginForm.setUserName(new Name(jims2User.getFirstName(), jims2User.getMiddleName(), jims2User.getLastName()));
		loginForm.setUserWorkPhoneNumber(new PhoneNumber(jims2User.getPhoneNum()));
		loginForm.getUserWorkPhoneNumber().setExt(jims2User.getPhoneExt());
		loginForm.setUserEmail(jims2User.getEmail());
		loginForm.setForgottenPasswdPhrase(jims2User.getForgottenPasswdPhrase());
		loginForm.setForgottenPasswdPhraseId(jims2User.getForgottenPasswdPhraseId());
		loginForm.setAnswer(jims2User.getPasswordAnswer());
		//				if (jims2User.getJIMS2AccountTypeId().equals("L"))
		//					loginForm.setUserType("genericUser");
		//				else
		//					loginForm.setUserType("nonGenericUser");
		session.setAttribute("USERLOGIN", "SUCCESS");
		UserEvent user = new UserEvent(userId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
		session.setAttribute("userInfo", user);
		loginForm.clearJIMSMessages();

		this.callNotificationFramework(user, loginForm);
		loginForm.setLogonId("");
		System.err.println("HTTP Session Id: [" + session.getId() + "]" + " JIMS2 USER_ID: [" + jims2User.getJIMS2LogonId() + "]");
		AuthenticationHelper.getOfficerCaseload(loginForm);

		loginForm.setActiveFileCount((activeFileCount));
		loginForm.setPendingFileCount(pendingFileCount);
		loginForm.setActiveJuveniles(activeJuveniles);
		loginForm.setPendingJuveniles(pendingJuveniles);
		return aMapping.findForward("jims2Success");
	    }
	    else
	    {
		//loginForm.setErrorMessage("Incorrect Password Supplied");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.incorrect.password", "Incorrect Password Supplied."));
		saveErrors(aRequest, errors);
		loginForm.clearLoginDetails();
		loginForm.setLogonId(userId);
		return (aMapping.findForward(UIConstants.FAILURE));
	    }
	}
    }*/

