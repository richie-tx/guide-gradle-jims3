//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\DisplayJuvenileCasefileFeeListAction.java

package ui.juvenilecase.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.referral.GetJuvenileCasefileFeeListEvent;
import messaging.referral.reply.JuvenileFeeResponseEvent;
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


public class DisplayJuvenileCasefileFeeListAction extends Action
{
   
   /**
    * @roseuid 467FB5B201E8
    */
   public DisplayJuvenileCasefileFeeListAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 467AD34A01E2
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		PetitionDetailsForm form = (PetitionDetailsForm) aForm;
   		GetJuvenileCasefileFeeListEvent disp =
			(GetJuvenileCasefileFeeListEvent) EventFactory.getInstance(
				JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEFEELIST);
   		disp.setPetitionNum(form.getPetitionNum());
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(disp);
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		Collection responses = MessageUtil.compositeToCollection(replyEvent,JuvenileFeeResponseEvent.class);
		form.setFeePayments(responses);
   		return aMapping.findForward("success");
   }
}
