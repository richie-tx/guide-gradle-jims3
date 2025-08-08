package ui.juvenilecase.mentalhealth.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.mentalhealth.CreateMentalHealthAdaptiveBehaviorEvent;
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

public class SubmitMentalHealthAdaptiveBehaviorAction extends JIMSBaseAction {

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C8A0230
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		CreateMentalHealthAdaptiveBehaviorEvent createEvent = (CreateMentalHealthAdaptiveBehaviorEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.CREATEMENTALHEALTHADAPTIVEBEHAVIOR);
		createEvent.setTestSessId(sessForm.getTestSessId());
		TestingSessionForm.AdaptiveBehaviorTest abRec = sessForm.getAbRec();
		createEvent.setTestDate(DateUtil.stringToDate(abRec.getTestDate(),"MM/dd/yyyy"));
		createEvent.setTestNameId("VIN");
		createEvent.setCommunicationScore(abRec.getCommunicationScore());
		createEvent.setCompositeScore(abRec.getCompositeScore());
		createEvent.setLivingScore(abRec.getLivingScore());
		createEvent.setSocialScore(abRec.getSocialScore());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(createEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		sessForm.setActionType("confirm");
		sessForm.setConfirmMessage("Adaptive Behavior Test Results Confirmed.");
		return forward;
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

	public ActionForward returnToTest(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setConfirmMessage("Test Session Results Confirmed");
		sessForm.setActionType("confirm");
		ActionForward forward = aMapping.findForward(UIConstants.RETURN_SUCCESS);
		return forward;
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.returnToTestSession", "returnToTest");
	}
}
