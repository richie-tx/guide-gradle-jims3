//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthIQResultsAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import messaging.mentalhealth.GetMentalHealthIQResultsEvent;
import messaging.mentalhealth.reply.IQTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayMentalHealthIQResultsAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 45D4AEA702B4
    */
   public DisplayMentalHealthIQResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45D36FDB0129
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		HttpSession session = aRequest.getSession();
		JuvenileProfileForm headerForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
		sessForm.setJuvNum(headerForm.getJuvenileNum());
		GetMentalHealthIQResultsEvent iqEvent = (GetMentalHealthIQResultsEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHIQRESULTS);
		iqEvent.setJuvenileNum(headerForm.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(iqEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response, IQTestResponseEvent.class);
		Collections.sort((List)coll);
		sessForm.setIqResultsList(coll);
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward; 
   }

   protected void addButtonMapping(Map keyMap) {
   }
}
