//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\DisplayJuvenileCasefileCourtOrderListAction.java

package ui.juvenilecase.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.referral.GetJuvenileCasefileCourtOrdersEvent;
import messaging.referral.reply.JuvenileCourtOrderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileReferralControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.PetitionDetailsForm;


public class DisplayJuvenileCasefileCourtOrderListAction extends Action
{
   
   /**
    * @roseuid 46AF85FE0066
    */
   public DisplayJuvenileCasefileCourtOrderListAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 46AF8048011E
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		PetitionDetailsForm form = (PetitionDetailsForm) aForm;
   		GetJuvenileCasefileCourtOrdersEvent evt = (GetJuvenileCasefileCourtOrdersEvent) EventFactory.getInstance(
				JuvenileReferralControllerServiceNames.GETJUVENILECASEFILECOURTORDERS);
   		evt.setPetitionNum(form.getPetitionNum());
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(evt);
   		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		Collection responses = MessageUtil.compositeToCollection(replyEvent,JuvenileCourtOrderResponseEvent.class);
		form.setCourtOrders(responses);
   		return aMapping.findForward("success");
   }
}
