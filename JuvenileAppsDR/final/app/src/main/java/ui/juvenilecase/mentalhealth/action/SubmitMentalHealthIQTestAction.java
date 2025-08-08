//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\SubmitMentalHealthIQTestAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Map;
import messaging.mentalhealth.CreateMentalHealthIQTestEvent;
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

public class SubmitMentalHealthIQTestAction extends JIMSBaseAction {

	/**
	 * @roseuid 45D4AEB10247
	 */
	public SubmitMentalHealthIQTestAction() {

	}

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
		CreateMentalHealthIQTestEvent createEvent = (CreateMentalHealthIQTestEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.CREATEMENTALHEALTHIQTEST);
		createEvent.setTestSessId(sessForm.getTestSessId());
		TestingSessionForm.IQTest iqTest = sessForm.getIqRec();
		createEvent.setTestName(iqTest.getTestNameId());
		createEvent.setTestDate(DateUtil.stringToDate(iqTest.getTestDate(),"MM/dd/yyyy"));
		createEvent.setFullScore(iqTest.getFullScore());
		createEvent.setPerformanceScore(iqTest.getPerformanceScore());
		createEvent.setVerbalScore(iqTest.getVerbalScore());
		createEvent.setRecommendations(iqTest.getRecommendations());
		createEvent.setVerbalComprehension(iqTest.getVerbalComprehension());
		createEvent.setPerceptualReasoning(iqTest.getPerceptualReasoning());
		createEvent.setNonVerbalIQ(iqTest.getNonVerbalIQ());
		createEvent.setProcessingSpeed(iqTest.getProcessingSpeed());
		createEvent.setWorkingMemory(iqTest.getWorkingMemory());
		createEvent.setPictorialIQ(iqTest.getPictorialIQ());
		createEvent.setGeometricIQ(iqTest.getGeometricIQ());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(createEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		sessForm.setActionType("confirm");
		sessForm.setConfirmMessage("IQ Test Results Confirmed.");
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
		ActionForward forward = aMapping.findForward(UIConstants.RETURN_SUCCESS);
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setConfirmMessage("Test Session Results Confirmed");
		sessForm.setActionType("confirm");
		return forward;
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.returnToTestSession", "returnToTest");
	}
}
