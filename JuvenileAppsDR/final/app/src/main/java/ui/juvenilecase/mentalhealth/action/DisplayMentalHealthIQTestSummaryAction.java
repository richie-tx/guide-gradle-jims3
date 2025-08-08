//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthIQTestSummaryAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import messaging.mentalhealth.GetMentalHealthIQTestDataEvent;
import messaging.mentalhealth.reply.IQTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayMentalHealthIQTestSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 45D4AEA80312
    */
   public DisplayMentalHealthIQTestSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45D49C8A01F0
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	TestingSessionForm sessForm = (TestingSessionForm)aForm;
/*	
   	String comments = sessForm.getIqRec().getRecommendations();
	if (!comments.equals("") && comments != null) {
		IUserInfo user = SecurityUIHelper.getUser();
		Name userName = new Name(user.getFirstName(),"",user.getLastName());
		sessForm.getIqRec().setRecommendations(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
	}
	
*/	sessForm.setActionType("summary");
    return forward;
   }
   
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {   
	TestingSessionForm sessForm = (TestingSessionForm)aForm;
	//get the provider and instructor name
	Collection coll = sessForm.getIqResultsList();
	Iterator iter = coll.iterator();
	while(iter.hasNext())
	{
		IQTestResponseEvent resp = (IQTestResponseEvent)iter.next();
		if(resp.getTestId().equals(sessForm.getSelectedValue()))
		{
			sessForm.setServiceProviderName(resp.getServiceProviderName());
			
			if(resp.getInstructorName() != null) {
			    String name = resp.getInstructorName();

			    // Remove anything after the parenthesis only if the field contains one
			    if (name != null && name.contains("(")) {
				name = name.replaceAll("\\s*\\(.*\\)", "");
			    }
			   
			    resp.setInstructorName(name);
			}
			
			
			sessForm.setInstructorName(resp.getInstructorName());
			break;
		}
	}
	GetMentalHealthIQTestDataEvent iqEvent = (GetMentalHealthIQTestDataEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHIQTESTDATA);
	iqEvent.setTestId(sessForm.getSelectedValue());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(iqEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	Object obj = MessageUtil.filterComposite(response, IQTestResponseEvent.class);
	if(obj != null)
	{
		IQTestResponseEvent resp = (IQTestResponseEvent)obj;
		fillIQRec(resp, sessForm.getIqRec());
	}
	
	sessForm.setActionType("view");
	sessForm.setConfirmMessage("");
	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
    return forward;
   }
   private void fillIQRec(IQTestResponseEvent resp, TestingSessionForm.IQTest iqTest)
   {
   	 	iqTest.setTestDate(DateUtil.dateToString(resp.getTestDate(),"MM/dd/yyyy"));
   	 	iqTest.setTestNameId(resp.getTestName());
	   	iqTest.setFullScore(resp.getFullScore());
	   	iqTest.setPerformanceScore(resp.getPerformanceScore());
	   	iqTest.setVerbalScore(resp.getVerbalScore());
	   	iqTest.setVerbalComprehension(resp.getVerbalComprehension());
	   	iqTest.setPerceptualReasoning(resp.getPerceptualReasoning());
	   	iqTest.setNonVerbalIQ(resp.getNonVerbalIQ());
	   	iqTest.setProcessingSpeed(resp.getProcessingSpeed());
	   	iqTest.setWorkingMemory(resp.getWorkingMemory());
	   	iqTest.setPictorialIQ(resp.getPictorialIQ());
	   	iqTest.setGeometricIQ(resp.getGeometricIQ());
	   	iqTest.setRecommendations(resp.getRecommendation());
   }
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setActionType("");
		return forward;
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");	
		keyMap.put("button.view", "view");
	}
}
