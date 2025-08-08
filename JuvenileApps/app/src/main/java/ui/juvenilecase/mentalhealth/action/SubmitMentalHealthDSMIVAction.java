//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\SubmitMentalHealthDSMIVAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import messaging.mentalhealth.CreateMentalHealthDSMIVEvent;
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

public class SubmitMentalHealthDSMIVAction extends JIMSBaseAction {

	/**
	 * @roseuid 45D4AEB00295
	 */
	public SubmitMentalHealthDSMIVAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C84000D
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		TestingSessionForm.DSMIVTest dsmTest = sessForm.getDsmRec();
		CreateMentalHealthDSMIVEvent createEvent = (CreateMentalHealthDSMIVEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.CREATEMENTALHEALTHDSMIV);
		createEvent.setTestSessId(sessForm.getTestSessId());
		createEvent.setTestDate(DateUtil.stringToDate(dsmTest.getTestDate(),"MM/dd/yyyy"));
/** **************************************************
 *	ER 75795 Axis prompts to Diagnosis prompts 		
 * 		old value					new value
 *		Axis I Primary Score		Diagnosis 1
 *		Axis I Secondary Score		Diagnosis 2
 *		Axis I Tertiary Score		Diagnosis 3
 *		Axis I Fourth				Diagnosis 4
 *		Axis I Fifth				Diagnosis 5
 *		Axis II Primary Score		Diagnosis 6
 *		Axis II Secondary Score		Diagnosis 7
 *		Axis III Primary Score		Diagnosis 8
 *		Axis III Secondary Score	Diagnosis 9
 *		Axis V						Diagnosis 10
 *      Axis IV Comments            Comments
 */
		createEvent.setAxisIPrimaryScore(dsmTest.getAxisIPrimaryScore());
		createEvent.setAxisISecondaryScore(dsmTest.getAxisISecondaryScore());
		createEvent.setAxisITertiaryScore(dsmTest.getAxisITertiaryScore());
		createEvent.setAxisIFourth(dsmTest.getAxisIFourth());
		createEvent.setAxisIFifth(dsmTest.getAxisIFifth());
		createEvent.setAxisIIPrimaryScore(dsmTest.getAxisIIPrimaryScore());
		createEvent.setAxisIISecondaryScore(dsmTest.getAxisIISecondaryScore());
		createEvent.setAxisIIIPrimaryScore(dsmTest.getAxisIIIPrimaryScore());
		createEvent.setAxisIIISecondaryScore(dsmTest.getAxisIIISecondaryScore());
		createEvent.setAxisIVComments(dsmTest.getAxisIVComments());
		createEvent.setAxisVScore(dsmTest.getAxisVScore());
		createEvent.setEducationalProblems(dsmTest.getIsEducationalProblems());
		createEvent.setHousingProblems(dsmTest.getHousingProblems());
		createEvent.setHealthCareAccessProblems(dsmTest.getHealthCareProblems());
		createEvent.setOccupationalProblems(dsmTest.getOccupationalProblems());
		createEvent.setLegalSystemProblems(dsmTest.getLegalSystemProblems());
		createEvent.setOtherPsychoEnvProblems(dsmTest.getPsychoEnvProblems());
		createEvent.setSocialEnvProblems(dsmTest.getSocioEnvProblems());
		createEvent.setPrimarySuppGrpProblems(dsmTest.getSuppGrpProblems());
		createEvent.setEconomicProblems(dsmTest.getEconomicProblems());
		createEvent.setAxisIVComments(dsmTest.getAxisIVComments());
		createEvent.setMedicalDiagnosis(dsmTest.getMedicalDiagnosis());
		createEvent.setDsmivId(dsmTest.getDsmivId());
		createEvent.setMentalHealthNeeds(dsmTest.isMentalHealthNeeded());
		createEvent.setMentalHealthTreatment(dsmTest.isMentalHealthTreatment());
		createEvent.setJuvenileNum(sessForm.getJuvNum());
		createEvent.setDiagnosis10(dsmTest.getDiagnosis10());
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(createEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);
		sessForm.setActionType("confirm");
		sessForm.setConfirmMessage("DSMIV Results Confirmed.");
		Collection coll = new ArrayList();
		coll.add(dsmTest);
		sessForm.setDsmResultsList(coll);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setSecondaryAction("update");
		sessForm.setActionType("update");
		sessForm.setConfirmMessage("");
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		if(sessForm.getActionType().equals("summary"))
			return aMapping.findForward(UIConstants.BACK);
		else
			return aMapping.findForward(UIConstants.BACK_VIEW);
	}

	public ActionForward returnTest(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		sessForm.setConfirmMessage("Test Session Results Confirmed");
		sessForm.setActionType("confirm");
		return aMapping.findForward(UIConstants.RETURN_SUCCESS);
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.returnToTestSession", "returnTest");
	}
}