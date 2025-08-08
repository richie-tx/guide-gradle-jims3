package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.GetMentalHealthABTestDataEvent;
import messaging.mentalhealth.reply.ABTestResponseEvent;
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
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;

public class DisplayMentalHealthABSummaryAction extends JIMSBaseAction {

	
	 public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
	     	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	     	TestingSessionForm sessForm = (TestingSessionForm) aForm;
		TestingSessionForm.AdaptiveBehaviorTest abRec = sessForm.getAbRec();
		Integer compositeScore = 0;
		
		try
		{
		    compositeScore += Integer.parseInt( abRec.getCommunicationScore() );
		    compositeScore += Integer.parseInt( abRec.getLivingScore() );
		    compositeScore += Integer.parseInt( abRec.getSocialScore() );
		}
		catch (NumberFormatException e)
		{
		    // TODO Auto-generated catch block
		    compositeScore +=0;
		}
		abRec.setCompositeScore(compositeScore.toString());
		sessForm.setAbRec(abRec);
		sessForm.setActionType("summary");
		return forward;
	   }

	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		// get the provider and instructor name
		Collection coll = sessForm.getAbResultsList();
		Iterator iter = coll.iterator();
		while (iter.hasNext()) {
			ABTestResponseEvent resp = (ABTestResponseEvent) iter.next();
			if (resp.getTestId().equals(sessForm.getSelectedValue())) {
				sessForm.setServiceProviderName(resp.getServiceProviderName());
				sessForm.setInstructorName(resp.getInstructorName());
				sessForm.setProgramReferralNum(resp.getProgramReferralNum());
				break;
			}
		}
		GetMentalHealthABTestDataEvent abEvent = (GetMentalHealthABTestDataEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHABTESTDATA);
		abEvent.setTestId(sessForm.getSelectedValue());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(abEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Object obj = MessageUtil.filterComposite(response,ABTestResponseEvent.class);
		if (obj != null) {
			ABTestResponseEvent resp = (ABTestResponseEvent) obj;
			fillABRec(resp, sessForm.getAbRec());
		}
		sessForm.setActionType("view");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	private void fillABRec(ABTestResponseEvent resp, TestingSessionForm.AdaptiveBehaviorTest abTest) {
		abTest.setTestDate(DateUtil.dateToString(resp.getTestDate(),"MM/dd/yyyy"));
		String testNameId = resp.getTestName();
		abTest.setTestName(SimpleCodeTableHelper.getDescrByCode("AB_TEST_NAME", testNameId));
		abTest.setCommunicationScore(resp.getCommunicationScore());
		abTest.setCompositeScore(resp.getCompositeScore());
		abTest.setLivingScore(resp.getLivingScore());
		abTest.setSocialScore(resp.getSocialScore());
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.view", "view");
	}

}
