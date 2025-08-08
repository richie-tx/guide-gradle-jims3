package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.CreateJuvenileAssociateEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseToPersonInfoEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.authentication.form.LoginForm;

/**
 * @author ryoung
 *
 */
public class SubmitReleaseToPersonAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 41FFE1B7003E
    */
   public SubmitReleaseToPersonAction() 
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
    * @roseuid 41FFC64D0224
    */
   public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	jwForm.setAction("confirm");
	
	// fill associate info
	CreateJuvenileAssociateEvent createAssEvent =
		(CreateJuvenileAssociateEvent) EventFactory.getInstance(
			JuvenileWarrantControllerServiceNames.CREATEJUVENILEASSOCIATE);
	
	// get bean from form
	JuvenileAssociateBean associateBean = jwForm.getNewReleaseToAssociate();		
	associateBean.populateCreateJuvenileAssociateCompositeEvent(createAssEvent);
	
	UpdateJuvenileReleaseToPersonInfoEvent requestEvent = (UpdateJuvenileReleaseToPersonInfoEvent)EventFactory.getInstance(JuvenileWarrantControllerServiceNames.UPDATEJUVENILERELEASETOPERSONINFO);
	requestEvent.setWarrantNum(jwForm.getWarrantNum());
	requestEvent.setAssociateId(jwForm.getReleaseAssociateNum());
	requestEvent.setAssociateEvent(createAssEvent);

	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
		
	jwForm.setBackToWarrantUrl(aMapping.findForward(UIConstants.SUCCESS).getPath());
	
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
