//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthHospitalizationListAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.List;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.mentalhealth.GetMentalHealthHospitalizationListEvent;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;

import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.mentalhealth.form.HospitalizationForm;

public class DisplayMentalHealthHospitalizationListAction extends Action
{
   
   /**
    * @roseuid 45B0DED602E5
    */
   public DisplayMentalHealthHospitalizationListAction() 
   {
   	
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45B0CB76012B
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	HospitalizationForm hpf = (HospitalizationForm)aForm;	
	   	HttpSession session = aRequest.getSession();
		JuvenileProfileForm headerForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
		hpf.setJuvNum(headerForm.getJuvenileNum());
	   	GetMentalHealthHospitalizationListEvent listEvent = (GetMentalHealthHospitalizationListEvent)EventFactory.getInstance("GetMentalHealthHospitalizationList");
	   	listEvent.setJuvenileNum(hpf.getJuvNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(listEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(compositeResponse, HospitalizationResponseEvent.class);
		Collections.sort((List)coll);
		hpf.setHospitalizationList(coll);		
		return aMapping.findForward(UIConstants.SUCCESS);
   }
}
