//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\DisplayJuvenileCasefileDispositionListAction.java

package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJuvenileCasefileDispositionListEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingDispositionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.PetitionDetailsForm;


public class DisplayJuvenileCasefileDispositionListAction extends Action
{
   
   /**
    * @roseuid 467FB5A80265
    */
   public DisplayJuvenileCasefileDispositionListAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 467AD3480011
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		PetitionDetailsForm form = (PetitionDetailsForm) aForm;
   		GetJuvenileCasefileDispositionListEvent list = (GetJuvenileCasefileDispositionListEvent)EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILECASEFILEDISPOSITIONLIST);
   		list.setPetitionNum(form.getPetitionNum());
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(list);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response, JuvenileOffenderTrackingDispositionResponseEvent.class);
		Collections.sort((List)coll);
		form.setDispositions(coll);
		form.setNotDetailed(true);
		return aMapping.findForward("success");
   }
}
