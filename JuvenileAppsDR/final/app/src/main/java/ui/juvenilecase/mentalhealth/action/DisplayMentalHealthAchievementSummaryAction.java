//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthAchievementSummaryAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import messaging.mentalhealth.GetMentalHealthATTestDataEvent;
import messaging.mentalhealth.reply.ATTestResponseEvent;
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

public class DisplayMentalHealthAchievementSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 45D4AEA200DF
    */
   public DisplayMentalHealthAchievementSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45D49C7D0116
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	TestingSessionForm sessForm = (TestingSessionForm)aForm;
/*	
   	String comments = sessForm.getAtRec().getRecommendations();
	if (!comments.equals("") && comments != null) {
		IUserInfo user = SecurityUIHelper.getUser();
		Name userName = new Name(user.getFirstName(),"",user.getLastName());
		sessForm.getAtRec().setRecommendations(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
	}	  	
*/	
	sessForm.setActionType("summary");
    return forward;
   }
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {   
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		//get the provider and instructor name
		Collection coll = sessForm.getAchievementResultsList();
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			ATTestResponseEvent resp = (ATTestResponseEvent)iter.next();
			if(resp.getTestId().equals(sessForm.getSelectedValue()))
			{
				sessForm.setServiceProviderName(resp.getServiceProviderName());
				sessForm.setInstructorName(resp.getInstructorName());
			}
		}
		GetMentalHealthATTestDataEvent atEvent = (GetMentalHealthATTestDataEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHATTESTDATA);
		atEvent.setTestId(sessForm.getSelectedValue());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(atEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Object obj = MessageUtil.filterComposite(response, ATTestResponseEvent.class);
		if(obj != null)
		{
			ATTestResponseEvent resp = (ATTestResponseEvent)obj;
			fillATRec(resp, sessForm.getAtRec());
		}
		sessForm.setActionType("view");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	    return forward;
   }
   
   private void fillATRec(ATTestResponseEvent resp, TestingSessionForm.AchievementTest atTest)
   {
	   	atTest.setTestDate(DateUtil.dateToString(resp.getTestDate(),"MM/dd/yyyy"));
	   	atTest.setTestNameId(resp.getTestname());
	   	atTest.setArithmeticGradeLevel(resp.getArithmeticGradeLevel());
	   	atTest.setArithmeticScore(resp.getArithmeticScore());
	   	atTest.setReadingGradeLevel(resp.getReadingGradeLevel());
	   	atTest.setReadingScore(resp.getReadingScore());
	   	atTest.setSpellingGradeLevel(resp.getSpellingGradeLevel());
	   	atTest.setSpellingScore(resp.getSpellingScore());
	   	atTest.setSentenceCompletionLevel(resp.getSentenceCompletionLevel());
	   	atTest.setSentenceCompletionScore(resp.getSentenceCompletionScore());
	   	atTest.setReadingCompositeLevel(resp.getReadingCompositeLevel());
	   	atTest.setReadingCompositeScore(resp.getReadingCompositeScore());
	   	atTest.setRecommendations(resp.getRecommendations());
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
		return forward;
	}
   
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.view", "view");
	}
}
