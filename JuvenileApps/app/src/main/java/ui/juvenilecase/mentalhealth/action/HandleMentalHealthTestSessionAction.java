//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\HandleMentalHealthTestSessionAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.mentalhealth.CreateMentalHealthTestSessionEvent;
import messaging.mentalhealth.GetDSMIVRecordEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;

public class HandleMentalHealthTestSessionAction extends JIMSBaseAction {

	/**
	 * @roseuid 45D4AEAD0237
	 */
	public HandleMentalHealthTestSessionAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C9003B7
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		CreateMentalHealthTestSessionEvent createEvent = (CreateMentalHealthTestSessionEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.CREATEMENTALHEALTHTESTSESSION);
		createEvent.setJuvenileNum(sessForm.getJuvNum());
		createEvent.setServiceEventId(sessForm.getEventId());
		createEvent.setSessionDate(DateUtil.stringToDate(sessForm.getSessionDate(), "MM/dd/yyyy"));
		createEvent.setTestType(sessForm.getTestTypeId());
		createEvent.setActualSessionLength(sessForm.getActualSessionLength());
		createEvent.setPsychologicalAssessment(sessForm.getPsychoAssessment());
		createEvent.setPsychiatricAssessment(sessForm.getPsychiatricAssessment());
		createEvent.setMentalRetardationDiagnosis(sessForm.getMentalRetardationDiagnosis());
		createEvent.setMentalIllnessDiagnosis(sessForm.getMentalIllnessDiagnosis());
		createEvent.setRecommendations(sessForm.getRecommendations());
		CompositeResponse resp = postRequestEvent(createEvent);

		// Perform Error handling
		MessageUtil.processReturnException(resp);
		if (resp == null)
			sendToErrorPage(aRequest, "Error creating Testing Session");
		else {
			TestingSessionResponseEvent test = (TestingSessionResponseEvent) MessageUtil.filterComposite(resp, TestingSessionResponseEvent.class);
			sessForm.setTestSessId(test.getTestSessID());
		}
		sessForm.setActionType("confirm");
		sessForm.setConfirmMessage("Testing Session Results Confirmed.");
		// check if DSM test data already exists for this testSession
		Collection coll = getDSMTestList(sessForm);
		sessForm.setDsmResultsList(coll);
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	private Collection getDSMTestList(TestingSessionForm sessForm) {
		GetDSMIVRecordEvent dsmEvent = (GetDSMIVRecordEvent) EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETDSMIVRECORD);
		dsmEvent.setTestSessID(sessForm.getTestSessId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(dsmEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response,DSMIVTestResponseEvent.class);
		return coll;
	}

	public ActionForward addDSM(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setDsmRec(new TestingSessionForm.DSMIVTest());
		sessForm.setDsmivDiagnosisList(ui.common.SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.TJPC_DSMIV_DIAGNOSIS));
		sessForm.getDsmRec().setTestDate(sessForm.getSessionDate());
		sessForm.setActionType("");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward("addDSMSuccess");
		return forward;
	}

	public ActionForward addIQ(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setIqRec(new TestingSessionForm.IQTest());
		sessForm.getIqRec().setTestDate(sessForm.getSessionDate());
		sessForm.setActionType("");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward("addIQSuccess");
		return forward;
	}

	public ActionForward addAT(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setAtRec(new TestingSessionForm.AchievementTest());
		sessForm.getAtRec().setTestDate(sessForm.getSessionDate());
		sessForm.setActionType("");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward("addATSuccess");
		return forward;
	}

	public ActionForward addAF(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setAfRec(new TestingSessionForm.AdaptiveFunctioningTest());
		sessForm.getAfRec().setTestDate(sessForm.getSessionDate());
		sessForm.setActionType("");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward("addAFSuccess");
		return forward;
	}
	
	public ActionForward addAB(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setAbRec(new TestingSessionForm.AdaptiveBehaviorTest());
		sessForm.getAbRec().setTestDate(sessForm.getSessionDate());
		sessForm.getAbRec().setTestNameId("VINELAND II");
		sessForm.setActionType("");
		sessForm.setConfirmMessage("");
		ActionForward forward = aMapping.findForward("addABSuccess");
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

	public ActionForward previousResults(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setActionType("confirm");
		ActionForward forward = aMapping.findForward("resultsSuccess");
		return forward;
	}

	public ActionForward updateResults(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setActionType("confirm");
		sessForm.setSecondaryAction("update");
		sessForm.setConfirmMessage("");
		// check if DSM test data already exists for this testSession
		Collection coll = getDSMTestList(sessForm);
		sessForm.setDsmResultsList(coll);
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.addDSMV", "addDSM");
		keyMap.put("button.addIQTest", "addIQ");
		keyMap.put("button.addAchievement", "addAT");
		keyMap.put("button.addAdaptiveFunctioning", "addAF");
		keyMap.put("button.addAdaptiveBehavior", "addAB");
		keyMap.put("button.previousResults", "previousResults");
		keyMap.put("button.addTestingSessionResults", "updateResults");
	}
}
