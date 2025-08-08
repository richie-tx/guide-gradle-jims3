/*
 * Created on Aug 2, 2006
 *
 */
package ui.supervision.administercasenotes.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CasenoteControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteForm;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;

/**
 * @author jjose
 *  
 */
public class SubmitAdministerCasenotesJournalAction extends
		JIMSBaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap) {
		
		keyMap.put("button.assessments", "assessments");
		keyMap.put("button.saveAsDraft", "saveAsDraft");
		keyMap.put("button.finish", "finish");
	
	}

	 public ActionForward assessments(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)throws GeneralFeedbackMessageException {
    	CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		String myForward="toAssessmetns";
		String spn=myForm.getSuperviseeId();
		if(spn==null || spn.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"A Supervisee must be available");
			myForward=UIConstants.FAILURE;
		}
		else{
			SuperviseeInfoHeaderForm supInfoHeaderForm = (SuperviseeInfoHeaderForm)getSessionForm(aMapping, aRequest, "superviseeInfoHeaderForm", true);	    
			supInfoHeaderForm.setSpn(spn);
		}
		return aMapping.findForward(myForward);
	}
	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		myForm.setSecondaryAction("");
		if (saveCasenoteData(aForm, false, aRequest)) {
			myForm.setAction(UIConstants.VIEW);
			myForm.setSecondaryAction(UIConstants.UPDATE_SUCCESS);
			return aMapping.findForward(UIConstants.SUCCESS);
		} else {
			return aMapping.findForward(UIConstants.FAILURE);
		}
	}

	public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		
		SuperviseeInfoHeaderForm supInfoHeaderForm = (SuperviseeInfoHeaderForm)getSessionForm(aMapping, aRequest, "superviseeInfoHeaderForm", true);
		myForm.setSecondaryAction("");
		if ( saveCasenoteData( aForm, true, aRequest ) ) {
			myForm.setAction(UIConstants.VIEW);
			myForm.setSecondaryAction(UIConstants.UPDATE_SUCCESS);
			// Create nttask
			//this.createNtTaskForPosition(supInfoHeaderForm);// Stop creating tasks for Draft Casenotes for the time being
			
			return aMapping.findForward(UIConstants.SUCCESS);
		} else {
			return aMapping.findForward(UIConstants.FAILURE);
		}
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		myForm.setSecondaryAction("");
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		myForm.setSecondaryAction("");
		myForm.getCurrentCasenote().clearAll();
		myForm.setAction(UIConstants.VIEW);
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		myForm.clearCurrentCasenoteAction();
		return forward;
	}

	
	// we could return something (boolean or String) here indicating success or
	// failure
	private boolean saveCasenoteData(ActionForm aForm, boolean saveAsDraft,
			HttpServletRequest aRequest) {
		CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		CasenoteForm casenoteForm = myForm.getCurrentCasenote();

		UpdateCasenoteEvent updateEvent = (UpdateCasenoteEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.UPDATECASENOTE);

		// there should probably be an operation on the CasenoteForm. ie -
		// fillCasenoteData(ICasenote casenote)
		// then we would call casenoteForm.fillCasenoteData(updateEvent);

		// if this has a value it will indicate Update to the command
		updateEvent.setCasenoteId(casenoteForm.getCasenoteId());

		// base attributes
		updateEvent.setCollateralId(casenoteForm.getCollateralId());
		updateEvent.setContactMethodId(casenoteForm.getContactMethodId());
		updateEvent.setHowGeneratedId(casenoteForm.getGeneratedById());
		// Set the subjectId(s) to the code
		String subjectIds[] = casenoteForm.getSubjectIds();
		Collection subjects = new ArrayList();
		for (int i = 0; i < subjectIds.length; i++) {
			subjects.add(subjectIds[i]);
		}
		updateEvent.setSubjects(subjects);
		// This must be set in order to create the correct TYPE of casenote.
		//updateEvent.setContextType(CasenoteContext.SUPERVISEE);
		updateEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
		updateEvent.setSuperviseeId(myForm.getSuperviseeId());
		updateEvent.setSupervisionPeriodId(myForm.getSupervisionPeriodId());

		updateEvent.setNotes(casenoteForm.getCasenoteText());
		//updateEvent.setEntryDate(UICasenoteHelper.convertToDateTime(DateUtil.convertDateToYYYYMMDD(casenoteForm.getCasenoteDate()),
		// casenoteForm.getCasenoteTime()));
		String casenoteTime = casenoteForm.getCasenoteTime();
		if (casenoteForm.getAMPMId().equalsIgnoreCase("PM")){
			String[] theTime = casenoteForm.getCasenoteTime().split(":");
			int iHr = Integer.parseInt(theTime[0]);
			if (iHr < 12){
				iHr = iHr + 12;
			}	
			String sHr = Integer.toString(iHr);
			casenoteTime = sHr + ":" + theTime[1];
		}		
		updateEvent.setEntryDate(UICasenoteHelper.convertToDateTime(DateUtil.dateToString(casenoteForm.getCasenoteDate(), "yyyyMMdd"),	casenoteTime));
		String associateIds[] = casenoteForm.getAssociateIds();
		Collection associates = new ArrayList();
		if (associateIds != null){
			for (int i = 0; i < associateIds.length; i++) {
				if(associateIds[i]!=null && !associateIds[i].equals("") )
					associates.add(associateIds[i]);
			}
		}
		updateEvent.setAssociates(associates);
		updateEvent
				.setHowGeneratedId(SupervisionConstants.HOW_GENERATED_CREATED_BY);
		updateEvent.setAgencyId(myForm.getUserAgency());

		// end fillCasenoteData(updateEvent)

		// This allows to save a casenote as 'Draft' so that it can be
		// edited within a 24-hour period.
		// Setting this to false saves casenote as 'Complete'.
		updateEvent.setSaveAsDraft(saveAsDraft);

		CasenoteResponseEvent response = (CasenoteResponseEvent) MessageUtil.postRequest( updateEvent , CasenoteResponseEvent.class );
		
		if (response != null) {
			casenoteForm.clearAll();
			// TODO: reload casenotes
			System.out.println("Case note successfully created or updated.");
			return true;
		} else {
			sendToErrorPage(aRequest, "error.generic", "Casenote save failed.");
			System.out.println("Case note save failed.");
			return false;
		}
	}
	
	/**
	 * 	
	 * @param supInfoHeaderForm
	 */
//	private void createNtTaskForPosition(SuperviseeInfoHeaderForm supInfoHeaderForm) {
//		
//        //Get users position
//		ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
//        IUserInfo userInfo = mgr.getIUserInfo();
//        String userId = userInfo.getJIMSLogonId();
//        LightCSCDStaffResponseEvent respEvent = UIManagetasksHelper.fetchCSStaffposition(userId);
//		
//		String staffPositionId = "";
//		if(respEvent !=null){
//			staffPositionId = respEvent.getStaffPositionId();
//		}
//		
//		//due date 24 hours from today's date
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.HOUR, 24);
//
//		// create nttask with 
//		CreateTaskEvent createTaskEvent = new CreateTaskEvent();
//		createTaskEvent.setTaskTopic( "CS.DRAFT.CASENOTE" );
//		createTaskEvent.setTaskSubject( "DRAFT CASENOTE WILL BE AUTOSAVED IN 24 HOURS" );
//		createTaskEvent.setOwnerId( CaseloadConstants.POSITION_OID_PREPEND_STRING + staffPositionId );
//		createTaskEvent.setDueDate( calendar.getTime() );
//		createTaskEvent.setSeverityLevel( new Integer(1) );
//
//		
//		StringBuffer taskText = new StringBuffer();
//		taskText.append("Draft casenote created on ").append(DateUtil.dateToString( new Date(),DateUtil.DATE_FMT_1 ) + " for ")
//		.append(supInfoHeaderForm.getSuperviseeName() + " ")
//		.append("for SPN: " + supInfoHeaderForm.getSpn() + ". ")
//		.append("If 24hrs has passed you will need to create a new casenote to add additional information.");
//
//		ITaskState taskState = createTaskEvent.getTaskState();
//		taskState.addItem(CaseloadConstants.DEFENDANT_ID, supInfoHeaderForm.getSpn());
//		taskState.addItem(CaseloadConstants.TASK_TEXT, taskText.toString());
//		// Builds next action
//		String pageFlowScenario = "displayAdministerCasenotesSearch";
//		taskState.addItem( CaseloadConstants.SCENARIO, pageFlowScenario );
//
//		TaskResponseEvent taskResponseEvent = (TaskResponseEvent) MessageUtil.postRequest(createTaskEvent,
//				TaskResponseEvent.class);
//				
//		if (taskResponseEvent != null)
//        {
//            String nttaskId = taskResponseEvent.getTaskId();
//
//            if (nttaskId != null && !nttaskId.equals(""))
//            {
//                String subject2 = "Draft Casenote";
//
//                CreateCSTaskEvent createCSTask = (CreateCSTaskEvent) EventFactory
//                        .getInstance(CSTaskControllerServiceNames.CREATECSTASK);
//
//                createCSTask.setSubject2(subject2);
//                createCSTask.setDefendantId(supInfoHeaderForm.getSpn());
//                createCSTask.setNtTaskId(nttaskId);
//
//                MessageUtil.postRequest(createCSTask);
//            }
//        }
//	}


}// END CLASS
