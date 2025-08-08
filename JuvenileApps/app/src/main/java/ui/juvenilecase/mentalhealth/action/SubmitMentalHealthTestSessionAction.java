//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\SubmitMentalHealthTestSessionAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Map;

import messaging.mentalhealth.CreateMentalHealthTestSessionEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
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

public class SubmitMentalHealthTestSessionAction extends JIMSBaseAction {

	/**
	 * @roseuid 45D4AEB20208
	 */
	public SubmitMentalHealthTestSessionAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C9003B7
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		CreateMentalHealthTestSessionEvent createEvent = (CreateMentalHealthTestSessionEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.CREATEMENTALHEALTHTESTSESSION);
		createEvent.setJuvenileNum(sessForm.getJuvNum());
		createEvent.setServiceEventId(sessForm.getEventId());
		createEvent.setSessionDate(DateUtil.stringToDate(sessForm.getSessionDate(), "MM/dd/yyyy"));
		createEvent.setTestType(sessForm.getTestTypeId());
		createEvent.setActualSessionLength(sessForm.getActualSessionLength());
		createEvent.setRecommendations(sessForm.getRecommendations());
		CompositeResponse resp = postRequestEvent(createEvent);

		// Perform Error handling
		MessageUtil.processReturnException(resp);
		if (resp == null)
			sendToErrorPage(aRequest, "Error creating Testing Session");
		else {
			TestingSessionResponseEvent test = (TestingSessionResponseEvent) MessageUtil
					.filterComposite(resp, TestingSessionResponseEvent.class);
			sessForm.setTestSessId(test.getTestSessID());
		}
		sessForm.setActionType("confirm");
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap) {
	}
}
