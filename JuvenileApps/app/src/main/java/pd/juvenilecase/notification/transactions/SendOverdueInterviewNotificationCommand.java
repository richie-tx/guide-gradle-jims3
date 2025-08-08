// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendOverdueInterviewNotificationCommand.java

package pd.juvenilecase.notification.transactions;

import java.util.Iterator;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendOverdueInterviewNotificationEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.PDTaskConstants;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.Interview;
import pd.supervision.calendar.ServiceEventContext;
import pd.task.Task;
import pd.task.TaskDef;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SendOverdueInterviewNotificationCommand implements ICommand {
	private static String POST_COURT_INTERVIEW = "Post Court Interview has not been attended.";
	private static String RISK_ANALYSIS_14 = "Risk Analysis is 14 days overdue.";
	private static String RISK_ANALYSIS_29  = "Risk Analysis is 29 days overdue.";
	/**
	 * @roseuid 46C1B3B503A5
	 */
	public SendOverdueInterviewNotificationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC620142
	 */
	public void execute(IEvent event) throws Exception {
		SendOverdueInterviewNotificationEvent nevt = (SendOverdueInterviewNotificationEvent) event;
		// Sending Notice
		if (nevt.getAlertType().equals("Notice")) {
			CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
					.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
			String casefileId = nevt.getSupervisionNumber();
			if (casefileId != null && !casefileId.equals("")){
				JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					if (nevt.getIdentityType() != null && nevt.getIdentityType().equals("jpo")) {
						String newIdentity = getOfficerLogonId(casefileId);
						if ((newIdentity != null) && (!newIdentity.equals(""))) {
							nevt.setIdentity(newIdentity);
						}
					}
					if (nevt.getSubject().equals(POST_COURT_INTERVIEW)) {
						boolean interviewExists = checkForInterview(casefileId);
						if (interviewExists) {
							return;
						}
					}
					if (nevt.getSubject().equals(RISK_ANALYSIS_14) || 
						nevt.getSubject().equals(RISK_ANALYSIS_29) )  {
						boolean riskExists = checkForRisk(casefileId);
						if (riskExists) {
							return;
						}
					}
					notificationEvent.setNotificationTopic(nevt.getNoticeTopic());
					notificationEvent.setSubject(nevt.getSubject());
					notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
					notificationEvent.addContentBean(nevt);
					// EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
					MessageUtil.postRequest(notificationEvent);
				}
			}
		}

		// Sending Task
		if (nevt.getAlertType().equals("Task")) {
			nevt.addTaskStateItem("casefileID", nevt.getCasefileId());
			nevt.addTaskStateItem("submitAction", nevt.getSubmitAction());
			nevt.addTaskStateItem("juvenileID", nevt.getJuvenileNum());
			nevt.addTaskStateItem("officerID", nevt.getOfficerId());
			TaskDef taskDef = TaskDef.find(nevt.getTaskTopic());

			if (taskDef == null) {
				TaskDef.throwTaskDefinitionNotFound(nevt.getTaskTopic());
			} else if (nevt.getTaskId() == null || nevt.getTaskId().equals("")) {
				if ((nevt.getCasefileId() != null)
						&& (!nevt.getCasefileId().equals(""))) {
					// For retrieving the current JPO Id.
					if (nevt.getIdentityType() != null
							&& nevt.getIdentityType().equals("jpo")) {
						String newIdentity = getOfficerLogonId(nevt
								.getCasefileId());
						if (!newIdentity.equals("")) {
							nevt.setOwnerId(newIdentity);
						}
					}
					if (nevt.getIdentityType() != null
							&& nevt.getIdentityType().equals("clm")) {
						String newIdentity = getManagerId(nevt.getCasefileId());
						if (!newIdentity.equals("")) {
							nevt.setOwnerId(newIdentity);
						}
					}

					// Check whether the Task is required or not.
					// JPO
					if (nevt.getTaskTopic().equalsIgnoreCase(PDTaskConstants.MJCW_JPO_OVERDUE_INTERVIEWNOTIFICATION)) {

						Iterator interviews = 
							Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());

						int interviewFound = 0;
						if (!interviews.hasNext()) {
							// Task Created as there are no interviews.
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
								|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						} else {
							while (interviews.hasNext()) {
								Interview interview = (Interview) interviews.next();
								if (interview.getInterviewTypeId().equalsIgnoreCase("HD")) {
									// Interview Found.
									interviewFound++;
								}
							}
							
							if (interviewFound == 0) {
								//<KISHORE>JIMS200058764 : Calendar: Home Diagnostic Visit (PD) - KK
								int calendarEventFound = 0;
								Iterator iter = ServiceEventContext.findAll("juvenileId", nevt.getJuvenileNum());
								while(iter.hasNext()){
									ServiceEventContext calEvent = (ServiceEventContext)iter.next();
									if (UIConstants.HOME_DIAGNOSTIC_VISIT.equalsIgnoreCase(calEvent.getEventTypeId())){
										// HOME_DIAGNOSTIC_EVENT Calendar Event Found.
										calendarEventFound++;
									}
								}
								if(calendarEventFound == 0){
									// Send the Task.
									if (cf.getCaseStatusId().equalsIgnoreCase("P")
											|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
										this.createTask(nevt, taskDef);
									}
								}
							}
						}

					}
					// CLM
					if (nevt.getTaskTopic().equalsIgnoreCase(PDTaskConstants.MJCW_CLM_OVERDUE_INTERVIEWNOTIFICATION)) {
						Iterator interviews = Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());

						int interviewFound = 0;
						if (!interviews.hasNext()) {
							// Task Created as there are no interviews.
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						} else {
							while (interviews.hasNext()) {
								Interview interview = (Interview) interviews.next();
								if (interview.getInterviewTypeId().equalsIgnoreCase("HD")) {
									// Interview Found.
									interviewFound++;
								}
							}
							if (interviewFound == 0) {
								//<KISHORE>JIMS200058764 : Calendar: Home Diagnostic Visit (PD) - KK
								int calendarEventFound = 0;
								Iterator iter = ServiceEventContext.findAll("juvenileId", nevt.getJuvenileNum());
								while(iter.hasNext()){
									ServiceEventContext calEvent = (ServiceEventContext)iter.next();
									if (UIConstants.HOME_DIAGNOSTIC_VISIT.equalsIgnoreCase(calEvent.getEventTypeId())){
										// HOME_DIAGNOSTIC_EVENT Calendar Event Found.
										calendarEventFound++;
									}
								}
								if(calendarEventFound == 0){
									// Send the Task.
									if (cf.getCaseStatusId().equalsIgnoreCase("P")
											|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
										this.createTask(nevt, taskDef);
									}
								}
							}
						}
					}

					// JPO

					if (nevt.getTaskTopic().equalsIgnoreCase("MJCW.JPO.PRECOURT.INTERVIEWNOTIFICATION")) {
						Iterator interviews = Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());
						int interviewFound = 0;
						if (!interviews.hasNext()) {
							// Task Created as there are no interviews.
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						} else {
							while (interviews.hasNext()) {
								Interview interview = (Interview) interviews.next();
								if (interview.getInterviewTypeId().equalsIgnoreCase("PR")) {
									// Interview Found.
									interviewFound++;
								}
							}
							if (interviewFound == 0) {
								// Send the Task.
								if (cf.getCaseStatusId().equalsIgnoreCase("P")
										|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
									this.createTask(nevt, taskDef);
								}
							}
						}
					}

					// JPO
					if (nevt.getTaskTopic().equalsIgnoreCase(PDTaskConstants.MJCW_JPO_OVERDUE_COMMUNITYSUPERVISION)) {
						Iterator interviews = Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());
						if (!interviews.hasNext()) {
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						}

					}
					// CLM
					if (nevt.getTaskTopic().equalsIgnoreCase(PDTaskConstants.MJCW_CLM_OVERDUE_COMMUNITYSUPERVISION)) {
						Iterator interviews = Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());
						if (!interviews.hasNext()) {
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						}
					}

					// JPO
					if (nevt.getTaskTopic().equalsIgnoreCase(PDTaskConstants.MJCW_JPO_OVERDUE_DEFERREDADJUDICATION)) {
						Iterator interviews = Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());
						if (!interviews.hasNext()) {
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						}
					}

					// JPO
					if (nevt.getTaskTopic().equalsIgnoreCase("MJCW.JPO.POSTCOURT.INTERVIEWNOTIFICATION")) {
						Iterator interviews = Interview.findAllForCasefile(nevt.getCasefileId());
						JuvenileCasefile cf = JuvenileCasefile.find(nevt.getCasefileId());
						if (!interviews.hasNext()) {
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
								this.createTask(nevt, taskDef);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param nevt
	 * @param taskDef
	 * @throws Exception
	 */
	private void createTask(SendOverdueInterviewNotificationEvent nevt,
			TaskDef taskDef) throws Exception {
		Task taskNew = taskDef.createTask(nevt);
		if (taskNew != null) {
			TaskResponseEvent taskEvent = new TaskResponseEvent();
			taskEvent.setTaskId(taskNew.getOID().toString());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(taskEvent);
		}
	}

	/**
	 * @param casefileId
	 * @returns ProbationOfficer LogonId
	 */
	private String getOfficerLogonId(String casefileId) {

		String officerProfileId = "";
		String officerLogonId = "";
		JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
		officerProfileId = (caseFile != null && caseFile.getProbationOfficerId() != null) ? caseFile.getProbationOfficerId() : "";
		OfficerProfile officerProfile = 
			(!officerProfileId.equals("")) ? OfficerProfile	.find(officerProfileId): null;
		officerLogonId = officerProfile != null ? officerProfile.getLogonId(): "";
		return officerLogonId;

	}

	/**
	 * @param casefileId
	 * @returns ProbationOfficer ManagerId
	 */
	private String getManagerId(String casefileId) {
		String officerProfileId = "";
		String managerId = "";
		JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
		officerProfileId = (caseFile != null)?caseFile.getProbationOfficerId() : "";
		OfficerProfile officerProfile =(officerProfileId != null)?OfficerProfile.find(officerProfileId):null;
		managerId = (officerProfile != null) ? officerProfile.getManagerId(): "";

		return managerId;
	}
	
	/**
	 * @param nevt
	 * @param taskDef
	 * @throws Exception
	 */
	private boolean checkForInterview(String casefileId) {
		boolean interviewFound = false;
		Iterator interviews = Interview.findAllForCasefile(casefileId); 
		if(interviews!= null){
			while (interviews.hasNext())
			{
				Interview interview = (Interview)interviews.next();	
				if (interview.getInterviewTypeId().equals("PO")) {
					interviewFound = true;
					break;
				}
			}
		}
		return interviewFound;
	}
	
	/**
	 * @param nevt
	 * @param taskDef
	 * @throws Exception
	 */
	private boolean checkForRisk(String casefileId) {
		boolean riskNeeded = false;
		JuvenileCasefile casefile = JuvenileCasefile.find(casefileId); 
		if(casefile != null){
			//changed for US 14459
			/*if (casefile.getIsCommunityRiskNeeded() || casefile.getIsInterviewRiskNeeded() ||
				casefile.getIsProgressRiskNeeded() || casefile.getIsReferralRiskNeeded() ||
				casefile.getIsResidentialRiskNeeded() || casefile.getIsTestingRiskNeeded()){*/
				if (casefile.getIsCommunityRiskNeeded() || casefile.getIsProgressRiskNeeded() 
						|| casefile.getIsReferralRiskNeeded() || casefile.getIsResidentialRiskNeeded()){
					riskNeeded = true;
			}
		}
		return riskNeeded;
	}
	
	/**
	 * @param event
	 * @roseuid 46C0BC620144
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0BC620150
	 */
	public void onUnregister(IEvent event) {
	}

	/**
	 * @param anObject
	 * @roseuid 46C0BC620152
	 */
	public void update(Object anObject) {
	}
}
