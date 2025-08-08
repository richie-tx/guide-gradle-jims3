//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilewarrant\\action\\SubmitWarrantReleaseDecisionConfirmAction.java

package ui.juvenilewarrant.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.UpdateJuvenileReleaseInfoEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.authentication.form.LoginForm;

/**
 * @author hrodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitWarrantReleaseDecisionAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 41FFE13C0109
    */
   public SubmitWarrantReleaseDecisionAction() 
   {
    
   }
	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();

		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.mainPage", "mainPage");

		return buttonMap;
	}  
	
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 41FFC64E0168
    */
   public ActionForward finish(
        ActionMapping aMapping,
        ActionForm aForm,
        HttpServletRequest aRequest,
        HttpServletResponse aResponse) 
   {
	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	jwForm.setAction("confirm");
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	UpdateJuvenileReleaseInfoEvent requestEvent =
		(UpdateJuvenileReleaseInfoEvent) EventFactory.getInstance(
			JuvenileWarrantControllerServiceNames.UPDATEJUVENILERELEASEINFO);

	requestEvent.setReleaseDecision(jwForm.getReleaseDecisionId());
	
	Date releaseDate = new Date();
	requestEvent.setReleaseDecisionTimeStamp(releaseDate);
	requestEvent.setLogonId(UIUtil.getCurrentUserID());
	requestEvent.setWarrantNum(jwForm.getWarrantNum());
	
	jwForm.setReleaseDecisionDate(releaseDate);
	
	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
	
	return aMapping.findForward(UIConstants.SUCCESS);
   }
	/**
	 * 
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

	return aMapping.findForward(UIConstants.CANCEL);
   }
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
   public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {

	return aMapping.findForward(UIConstants.MAIN_PAGE);
   }
   

}
