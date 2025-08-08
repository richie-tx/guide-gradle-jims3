package ui.security.authentication.action;//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\DisplayLoginAction.java

/*package ui.security.authentication.action;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

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
//import mojo.mfa.authentication.AuthHelper;
import naming.LogonControllerServiceNames;
import naming.NotificationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDSecurityConstants;
import naming.UIConstants;
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
*/
/*public class HandleValidateMFAAction extends Action
{

    private static final String STATE = "state";
    private static final String IDTOKEN = "id_token";
    private static final String FAILED_TO_VALIDATE_MESSAGE = "Failed to validate data received from Authorization service - ";

    *//**
        * @param aMapping
        * @param aForm
        * @param aRequest
        * @param aResponse
        * @return ActionForward
        * @roseuid 439711A70311
        */
/*
public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
{
if (aRequest instanceof HttpServletRequest)
{
    HttpServletRequest httpRequest = (HttpServletRequest) aRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) aResponse;

    String currentUri = aRequest.getRequestURL().toString();
    String path = aRequest.getServletPath();
    String queryStr = aRequest.getQueryString();
    String fullUrl = currentUri + (queryStr != null ? "?" + queryStr : "");
    LoginForm loginForm = (LoginForm) aForm;
    try
    {

	Map<String, List<String>> params = new HashMap<>();

	Map<String, String[]> map = aRequest.getParameterMap();
	Set set = map.entrySet();
	Iterator it = set.iterator();
	while (it.hasNext())
	{
	    Map.Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
	    String paramName = entry.getKey();
	    String[] paramValues = entry.getValue();
	    //System.out.println(paramName + "" + paramValues);
	    params.put(paramName, Collections.singletonList(paramValues[0]));
	}

	AuthenticationResponse authResponse = AuthenticationResponseParser.parse(new URI(fullUrl), params);
	if (AuthHelper.isAuthenticationSuccessful(authResponse))
	{
	    AuthenticationSuccessResponse oidcResponse = (AuthenticationSuccessResponse) authResponse;
	    if (oidcResponse.getIDToken() != null)
	    {

		String authsource = (String) oidcResponse.getIDToken().getJWTClaimsSet().getClaim("authenticationSource");

		if (authsource.equalsIgnoreCase("localAccountAuthentication")) //service provider flow. generic users.
		{
		    String spUserame = ((String) oidcResponse.getIDToken().getJWTClaimsSet().getClaim("email")).toUpperCase();
		    //service provider flow. Phase 2
		    String SPlogon = (String) oidcResponse.getIDToken().getJWTClaimsSet().getClaim("sub");
		    HttpSession session = aRequest.getSession();

		    GetJIMS2AccountEvent accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
		    accountEvent.setJIMS2LogonId(spUserame);//(loginForm.getJims2UserId());
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
			//loginForm.setLogonId(loginForm.getJims2UserId());
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    JIMS2AccountResponseEvent jims2User = (JIMS2AccountResponseEvent) MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
		    if (jims2User == null) //non-existing users
		    {
			String noOfAttempts = (String) session.getAttribute(spUserame);//(loginForm.getJims2UserId());
			if (noOfAttempts == null)
			{
			    noOfAttempts = "1";
			    session.setAttribute(spUserame, noOfAttempts);
			}
			else
			{
			    int attempts = Integer.valueOf(noOfAttempts) + 1;
			    noOfAttempts = String.valueOf(attempts);
			    session.setAttribute(spUserame, noOfAttempts);
			}
			//call security manager web-service EMAIL
			IEvent lReply = authenticateUser(dispatch, SPlogon, "EMAIL", noOfAttempts);//replace with new logic
			if (lReply == null)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + spUserame + " could not be located");
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    loginForm.setLogonId(SPlogon);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

			if (lReply instanceof mojo.km.messaging.noop.NoReply)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    //loginForm.setLogonId(logonId);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			compositeResponse = (CompositeResponse) lReply;
			AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
			ReturnException ex = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
			    if (ex != null)
			    {
				ex.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", ex.getMessage());
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);					
				return aMapping.findForward(UIConstants.FAILURE);
			    }				
			    LoginResponseEvent loginResponse = (LoginResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginResponseEvent.class);

			if (loginResponse != null && loginResponse.getSecUser().getJIMSLogonId() == null)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", loginResponse.getMessage());
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    loginForm.setLogonId(spUserame);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			String jimsLogonId = loginResponse.getSecUser().getJIMSLogonId(); //jucode
			if (jimsLogonId != null)
			{
			    loginForm.setSmUserId(loginResponse.getSecUser().getUserOID());
			    loginForm.setUserType("genericSP");
			    populateLoginFormInfo(loginForm, loginResponse);
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
			    cRequest.setJIMS2LogonId(spUserame);
			    cRequest.setLogonId(jimsLogonId);
			    //cRequest.setCreate(true);
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
				loginForm.setLogonId(spUserame);
				return (aMapping.findForward(UIConstants.FAILURE));
			    }

			    //populate the other information.
			    accountEvent = (GetJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNT);
			    //accountEvent.setJIMS2LogonId(jims2User.getJIMS2LogonId());
			    accountEvent.setJIMS2LogonId(spUserame);
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
			    ActionForward forward = loginUser(jimsLogonId, loginForm, spUserame, loginResponse, jims2User, aMapping, aForm, aRequest, aResponse);
			    return forward;
			    //return aMapping.findForward("");
			}
			return null;
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
			    loginForm.setLogonId(spUserame);
			    return (aMapping.findForward(UIConstants.FAILURE));
			}
			String jimsLogonId = jims2User.getJimsLogonId();
			loginForm.setJimsUserId(jimsLogonId);
			String accountTypeId = jims2User.getJIMS2AccountTypeId();
			loginForm.setLogonId(loginForm.getJims2UserId());
			loginForm.setUserType(accountTypeId);

			//JUCODE Authentication
			String noOfAttempts = (String) session.getAttribute(spUserame);
			if (noOfAttempts == null)
			{
			    noOfAttempts = "1";
			    session.setAttribute(spUserame, noOfAttempts);
			}
			else
			{
			    int attempts = Integer.valueOf(noOfAttempts) + 1;
			    noOfAttempts = String.valueOf(attempts);
			    session.setAttribute(spUserame, noOfAttempts);
			}
			// Webservice- using EMAIl.
			IEvent lReply = authenticateUser(dispatch, SPlogon, "EMAIL", noOfAttempts);
			if (lReply == null)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + spUserame + " could not be located");
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    loginForm.setLogonId(spUserame);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

			if (lReply instanceof mojo.km.messaging.noop.NoReply)
			{
			    ActionErrors errors = new ActionErrors();
			    ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
			    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			    saveErrors(aRequest, errors);
			    loginForm.setLogonId(spUserame);
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			compositeResponse = (CompositeResponse) lReply;
			//AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
			ReturnException ex = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
			    if (ex != null)
			    {
				ex.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", ex.getMessage());
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);					
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			if (err != null)
			{
			    String type = err.getErrorType();				    
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
				ActionMessage error = new ActionMessage("error.generic", "User profile is suspended or invalid.  Please contact Data.Corrections@hcjpd.hctx.net.");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
				
			    }
			    if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
			    {
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.generic", "User profile is suspended or invalid.  Please contact Data.Corrections@hcjpd.hctx.net.");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
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
				loginForm.setLogonId(spUserame);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    jimsLogonId = secUser.getJIMSLogonId();
			    loginForm.setSmUserId(secUser.getUserOID());
			    ActionForward forward = loginUser(jimsLogonId, loginForm, spUserame, loginResponse, jims2User, aMapping, aForm, aRequest, aResponse);
			    return forward;
			}
			else
			{
			    	ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", "User not in Security Manager or is invalid/suspended.");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				//loginForm.setLogonId(spUserame);
				return aMapping.findForward(UIConstants.FAILURE);
			}
			    

		    }
		    
		}
		else
		    if (authsource.equalsIgnoreCase("socialIdpAuthentication")) //normal login
		    {
			//AD logon  Authentication.
			String username = (String) oidcResponse.getIDToken().getJWTClaimsSet().getClaim("winaccountname");
			HttpSession session = aRequest.getSession();
			//call the AD logon webservice
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			if (username != null)
			{
			    String noOfAttempts = (String) session.getAttribute(username);
			    if (noOfAttempts == null)
			    {
				noOfAttempts = "1";
				session.setAttribute(username, noOfAttempts);
			    }
			    else
			    {
				int attempts = Integer.valueOf(noOfAttempts) + 1;
				noOfAttempts = String.valueOf(attempts);
				session.setAttribute(username, noOfAttempts);
			    }
			    IEvent lReply = authenticateUser(dispatch, username, "AD", noOfAttempts);
			    if (lReply == null)
			    {
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", "User with user login name " + username + " could not be located in Security Manager");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				//loginForm.setLogonId(logonId);
				return aMapping.findForward(UIConstants.FAILURE);
			    }

			    if (lReply instanceof mojo.km.messaging.noop.NoReply)
			    {
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", lReply.toString());
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				//loginForm.setLogonId(logonId);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    CompositeResponse compositeResponse = (CompositeResponse) lReply;
			    
			   // AuthenticationFailedResponseEvent err = (AuthenticationFailedResponseEvent) MessageUtil.filterComposite(compositeResponse, AuthenticationFailedResponseEvent.class);
			    ReturnException ex = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
			    if (ex != null)
			    {
				ex.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.authenticate.user", ex.getMessage());
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);					
				return aMapping.findForward(UIConstants.FAILURE);
			    }

			    if (err != null)
			    {
				String type = err.getErrorType();					
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
				    ActionMessage error = new ActionMessage("error.suspended.password", getUserSupervisorInfo(username));
				    errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				    saveErrors(aRequest, errors);
				    return aMapping.findForward(UIConstants.FAILURE);
				}
				if (type.equals(CommonConstants.INCORRECT_USER_ERROR))
				{
				    ActionErrors errors = new ActionErrors();
				    ActionMessage error = new ActionMessage("error.suspended.user.profile", getUserSupervisorInfo(username));
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
				//loginForm.setLogonId(logonId);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    if (loginResponse != null)
			    {
				String userId = loginResponse.getSecUser().getJIMSLogonId();
				if (userId != null)
				{
				    populateLoginFormInfo(loginForm, loginResponse);

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
					    //loginForm.setLogonId(logonId);
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
						//loginForm.setLogonId(logonId);
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
				    ActionForward forward = loginUser(userId, loginForm, username, loginResponse, jims2User, aMapping, aForm, aRequest, aResponse);
				    return forward;
				}
			    }

			}

		    }
	    }
	}
	else
	{
	    AuthenticationErrorResponse oidcResponse = (AuthenticationErrorResponse) authResponse;
	    throw new Exception(String.format("Request for auth code failed: %s - %s", oidcResponse.getErrorObject().getCode(), oidcResponse.getErrorObject().getDescription()));
	}
    }
    catch (Throwable exc)
    {
	aResponse.setStatus(500);
	aRequest.setAttribute("error", exc.getMessage());		
	//action errors
	ActionErrors errors = new ActionErrors();
	ActionMessage error = new ActionMessage("error.authenticate.user", exc.getMessage());
	errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	saveErrors(aRequest, errors);		
	return aMapping.findForward(UIConstants.FAILURE);
    }
}
return aMapping.findForward(UIConstants.REDIRECT);
//return null;
}

*//**
    * populateLoginFormInfo
    * 
    * @param aForm
    * @param aRequest
    * @param aMapping
    * @param password
    */
/*
public void populateLoginFormInfo(LoginForm loginForm, LoginResponseEvent loginResponse)
{
String userId = loginResponse.getSecUser().getJIMSLogonId();
SecurityUser userResponse = loginResponse.getSecUser();
if (userId != null)
{

    loginForm.setJimsUserId(userId);//uvcode
    loginForm.setDepartmentName(userResponse.getDepartmentName());
    loginForm.setDepartmentId(userResponse.getDepartmentId());
    if (loginForm.getUserType() != null && !loginForm.getUserType().isEmpty() && !loginForm.getUserType().equalsIgnoreCase("N"))//&& loginForm.getAuthenticationMethod().equalsIgnoreCase("SP") add genericSP check
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

private void validateState(String cookieValue, String state) throws Exception
{
if (StringUtils.isEmpty(state) || !state.equals(cookieValue))
{
    throw new Exception(FAILED_TO_VALIDATE_MESSAGE + "could not validate state");
}
}

private void validateNonce(String cookieValue, String nonce) throws Exception
{
if (StringUtils.isEmpty(nonce) || !nonce.equals(cookieValue))
{
    throw new Exception(FAILED_TO_VALIDATE_MESSAGE + "could not validate nonce");
}
}

private String getNonceClaimValueFromIdToken(String idToken) throws ParseException
{
return (String) JWTParser.parse(idToken).getJWTClaimsSet().getClaim("nonce");
}

*//**
    * getUserSupervisorInfo
    * 
    * @param userID
    * @return String
    */
/*
public String getUserSupervisorInfo(String userID)
{
StringBuffer message = new StringBuffer();
String phone = "";
String extension = "";
String departmentId = PDSecurityHelper.getUserDepartmentId();
Department department = Department.find(departmentId);
if (department != null)
{
    if (department.getContacts() != null)
    {
	List<DepartmentContact> departmentContacts = (List<DepartmentContact>) department.getContacts();
	Iterator<DepartmentContact> departmentContactsItr = departmentContacts.iterator();
	while (departmentContactsItr.hasNext())
	{
	    DepartmentContact contact = departmentContactsItr.next();
	    if (contact != null && contact.getPrimaryContact() != null)
	    {
		if (contact.getPrimaryContact().equalsIgnoreCase("Y"))
		{
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
return message.toString();
}

*//**
    * authenticateUser
    * 
    * @param dispatch
    * @param userID
    * @param password
    * @return IEvent
    */
/*
public IEvent authenticateUser(IDispatch dispatch, String adLogonId, String credentialType, String noOfAttempts)
{
//String userId = null;

LoginEvent loginEvent = new LoginEvent();
loginEvent.setUsername(adLogonId);
//loginEvent.setPassword(password);
loginEvent.setCredentialType(credentialType);
loginEvent.setNoOfAttempts(noOfAttempts);
dispatch.postEvent(loginEvent);

IEvent lReply = EventManager.getSharedInstance(EventManager.REPLY).getReply();

return lReply;
}

*//**
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
/*
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
sessionEvent.setJIMSLogonId(userId);
sessionEvent.setSecurityManagerUserId(loginForm.getSmUserId());
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
else
    if (accountTypeId.equals(PDSecurityConstants.OFFICER_PROFILE))
    {
	loginForm.setUserType("genericUser");
	loginForm.setUserType("nonGenericUser");
	UserEvent userevent = new UserEvent(userId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
	session.setAttribute("userInfo", userevent);
	loginForm.clearJIMSMessages();
	this.callNotificationFramework(userevent, loginForm);
    }
    else
	if (accountTypeId.equals(PDSecurityConstants.SERVICE_PROVIDER))
	{
	    loginForm.setUserType("genericSP");
	    UserEvent userevent = new UserEvent(logonId.toUpperCase(), loginForm.getUserName().toString(), jims2User.getServer());
	    session.setAttribute("userInfo", userevent);
	    loginForm.clearJIMSMessages();
	    this.callNotificationFramework(userevent, loginForm);
	}
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

*//**
    * sortCollection
    * 
    * @param taskMap
    * @return Collection
    */
/*
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

*//**
    * sortNoticeCollection
    * 
    * @param taskMap
    * @return Collection
    */
/*
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

*//**
    * callNotificationFramework
    * 
    * @param user
    * @param loginForm
    *//*
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
          noticeMap.put(nEvent.getNotification().getSentDate(), nEvent);
      }
      Collection sortedNoticeList = this.sortNoticeCollection(noticeMap);
      loginForm.setNoticeList(sortedNoticeList);
      if (notices != null)
      {
          loginForm.setNoticeListSize(new StringBuffer("").append(notices.size()).toString());
      }
      else
      {
          loginForm.setNoticeListSize("0");
      }
      }
      }*/
