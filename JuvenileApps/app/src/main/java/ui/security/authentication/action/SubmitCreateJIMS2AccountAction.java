//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\authentication\\action\\SubmitCreateJIMS2AccountAction.java

package ui.security.authentication.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.CreateJIMS2AccountEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.form.LoginForm;

public class SubmitCreateJIMS2AccountAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4399CE4302C4
	 */

	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.backToLogin", "backToLogin");

		return keyMap;
	}
	public SubmitCreateJIMS2AccountAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 439711A9013D
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm) aForm;
		String action=loginForm.getAction();
		loginForm.clearJIMSMessages();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		CreateJIMS2AccountEvent createJIMS2 =
			(CreateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
		createJIMS2.setLogonId(loginForm.getLogonId());
		createJIMS2.setPassword(loginForm.getPassword());
		createJIMS2.setJIMS2AccountTypeId(loginForm.getAccountTypeId());
		// no longer in use. Migrated to SM. Refer US #87188.
		/*if(loginForm.getUserType().equals("genericSP"))
			createJIMS2.setJIMS2AccountTypeOID(loginForm.getEmployeeId());
		else*/
			createJIMS2.setJIMS2AccountTypeOID(loginForm.getAccountTypeOID());
		createJIMS2.setJIMS2LogonId(loginForm.getJims2UserId());
		
		if(loginForm.getUserType().equals("genericUser") || loginForm.getUserType().equals("genericSP"))
			createJIMS2.setJIMS2Password(loginForm.getJims2Password());			
		/*else
			createJIMS2.setJIMS2Password(loginForm.getPassword());*/
			
		
		//createJIMS2.setPasswordQuestionId(loginForm.getForgottenPasswdPhraseId());
		//createJIMS2.setAnswer(loginForm.getAnswer()); //86322
		createJIMS2.setFirstName(loginForm.getUserName().getFirstName());
		createJIMS2.setLastName(loginForm.getUserName().getLastName());
		createJIMS2.setMiddleName(loginForm.getUserName().getMiddleName());
		createJIMS2.setDepartmentId(loginForm.getDepartmentId());
		createJIMS2.setCreate(true);
		createJIMS2.setStatus("A");
		dispatch.postEvent(createJIMS2);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
//		LoginErrorResponseEvent loginErr =
//						(LoginErrorResponseEvent) MessageUtil.filterComposite(
//		response, LoginErrorResponseEvent.class);
//		if(loginErr!=null)
//		{
//			ActionErrors errors = new ActionErrors();
//			ActionError error = new ActionError("error.authenticate.user", loginErr.getMessage());
//			errors.add(ActionErrors.GLOBAL_ERROR, error);
//			saveErrors(aRequest, errors);
//			//loginForm.setErrorMessage(loginError.getMessage());
//			//loginForm.setLogonId(userId);
//			loginForm.clearJIMSMessages();		
//			loginForm.setAction("confirm");	
//			return aMapping.findForward(UIConstants.FAILURE);
//		}
		loginForm.clearJIMSMessages();		
		loginForm.setAction("confirm");	
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		LoginForm loginForm = (LoginForm) aForm;
		loginForm.clearJIMSMessages();	
		return forward;
	}

	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		LoginForm loginForm = (LoginForm) aForm;
		loginForm.clearJIMSMessages();
		loginForm.setAction("summary");
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	public ActionForward backToLogin(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		ActionForward forward = aMapping.findForward("backtologin");
		LoginForm loginForm = (LoginForm) aForm;
		loginForm.setLogonId(loginForm.getJims2UserId());
		loginForm.clearJIMSMessages();
		return forward;
	}
}
