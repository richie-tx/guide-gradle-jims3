// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendCaseplanNotificationEventCommand.java

package pd.juvenilecase.notification.transactions;

import java.util.Iterator;
import messaging.casefile.GetActivitiesEvent;
import messaging.caseplan.GetJPOReviewReportsEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendCaseplanNotificationEventEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.JPOReviewDocMetadata;
import pd.task.Task;
import pd.task.TaskDef;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SendCaseplanNotificationEventCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B50153
	 */
	public SendCaseplanNotificationEventCommand() {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC620393
	 */
	public void execute(IEvent event) throws Exception {

		SendCaseplanNotificationEventEvent nevt = (SendCaseplanNotificationEventEvent) event;

		// Sending Notice
		if (nevt.getAlertType().equals("Notice")) {
			if (nevt.getCasefileId() != null
					&& !(nevt.getCasefileId().equals(""))) {
				JuvenileCasefile cf = JuvenileCasefile.find(nevt
						.getCasefileId());
				// Set the new JPOID If it has been changed.
				if (nevt.getIdentityType() != null
						&& nevt.getIdentityType().equals("jpo")) {
					String newIdentity = getOfficerLogonId(cf);
					if ((newIdentity != null) && (!newIdentity.equals(""))) {
						nevt.setIdentity(newIdentity);
					}
				}
				// Set the new CLMID If it has been changed.
				if (nevt.getIdentityType() != null
						&& nevt.getIdentityType().equals("clm")) {
					String newIdentity = getManagerId(cf);
					if ((newIdentity != null) && (!newIdentity.equals(""))) {
						nevt.setIdentity(newIdentity);
					}
				}

				if (nevt.getNoticeTopic().equalsIgnoreCase(
						"MJCW.JPO.PENDING.CASEPLAN.NOTIFICATION")) {

					CasePlan cp = null;
					if (cf == null || cf.getCaseplanId() == null
							|| cf.getCaseplanId().equals("")) {
					} else {
						cp = CasePlan.find(cf.getCaseplanId());
						if (cp != null
								&& (!(cp.getStatusId().equalsIgnoreCase("S")))) {
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase(
											"A")) {
								this.sendNotice(nevt);
							}
						}
					}
				}

				if (nevt.getNoticeTopic().equalsIgnoreCase(
						"MJCW.JPO.OVERDUE20DAYS.CASEPLAN.NOTIFICATION")) {
					CasePlan cp = null;
					if (cf == null || cf.getCaseplanId() == null
							|| cf.getCaseplanId().equals("")) {
					} else {
						cp = CasePlan.find(cf.getCaseplanId());
						if (cp != null
								&& (cp.getStatusId().equalsIgnoreCase("P"))) {
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase(
											"A")) {
								this.sendNotice(nevt);
							}
						}
					}
				}
			}
		}

		// Sending Task
		if (nevt.getAlertType().equals("Task")) {
			nevt.addTaskStateItem("casefileID", nevt.getCasefileId());
			nevt.addTaskStateItem("caseplanID", nevt.getCaseplanId());
			nevt.addTaskStateItem("submitAction", nevt.getSubmitAction());
			nevt.addTaskStateItem("juvenileID", nevt.getJuvenileNum());
			TaskDef taskDef = TaskDef.find(nevt.getTaskTopic());

			if (taskDef == null) {
				TaskDef.throwTaskDefinitionNotFound(nevt.getTaskTopic());
			} else if (nevt.getTaskId() == null || nevt.getTaskId().equals("")) {

				// Set the new JPOID If it has been changed.
				JuvenileCasefile cf = JuvenileCasefile.find(nevt
						.getCasefileId());

				if (nevt.getIdentityType() != null
						&& nevt.getIdentityType().equals("jpo")) {
					String newIdentity = getOfficerLogonId(cf);
					if (!newIdentity.equals("")) {
						nevt.setOwnerId(newIdentity);
					}
				}

				// check whether the task is required or not.
				if (nevt.getTaskTopic().equalsIgnoreCase(
						"MJCW.JPO.NONRESIDENTIAL.CASEPLAN.NOTIFICATION")
						|| nevt.getTaskTopic().equalsIgnoreCase(
								"MJCW.JPO.RESIDENTIAL.CASEPLAN.NOTIFICATION")) {
					GetJPOReviewReportsEvent evt = new GetJPOReviewReportsEvent();
					evt.setCasefileId(nevt.getCasefileId());
					evt.setCaseplanId(nevt.getCaseplanId());
					Iterator jpoReviews = JPOReviewDocMetadata.findAll(evt);
					// sendTask
					if (jpoReviews == null || !(jpoReviews.hasNext())) {
						if (cf.getCaseStatusId().equalsIgnoreCase("P")
								|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
							this.createTask(nevt, taskDef);
						}
					}

				}

				if (nevt.getTaskTopic().equalsIgnoreCase(
						"MJCW.CLM.SUPERVISION.REVIEW.NOTIFICATION")) {
					GetActivitiesEvent evt = new GetActivitiesEvent();
					evt
							.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_CASEPLAN);
					evt.setCasefileID(nevt.getCasefileId());
					evt
							.setCategoryId(ActivityConstants.ACTIVITY_CATEGORY_REPORTING);
					Iterator actIte = Activity.findAll(evt);
					int clmReviewsFound = 0;
					if (!actIte.hasNext()) {
						// sendTask as no clm reviews found.
						if (cf.getCaseStatusId().equalsIgnoreCase("P")
								|| cf.getCaseStatusId().equalsIgnoreCase("A")) {
							this.createTask(nevt, taskDef);
						}
					} else {
						while (actIte.hasNext()) {
							Activity activity = (Activity) actIte.next();
							String codeId = activity.getActivityCodeId();
							if (codeId.equalsIgnoreCase("CLM")
									|| codeId.equalsIgnoreCase("REJ")) {
								// CLM either accepted or rejected the caseplan.
								clmReviewsFound++;
							}
						}
						if (clmReviewsFound == 0) {
							// sendTask as no clm reviews found.
							if (cf.getCaseStatusId().equalsIgnoreCase("P")
									|| cf.getCaseStatusId().equalsIgnoreCase(
											"A")) {
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
	private void createTask(SendCaseplanNotificationEventEvent nevt,
			TaskDef taskDef) throws Exception {
		Task taskNew = taskDef.createTask(nevt);
		if (taskNew != null) {
			TaskResponseEvent taskEvent = new TaskResponseEvent();
			taskEvent.setTaskId(taskNew.getOID().toString());
			IDispatch dispatch = EventManager
					.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(taskEvent);
		}
	}

	/**
	 * @param nevt
	 */
	private void sendNotice(SendCaseplanNotificationEventEvent nevt) {
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
				.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic(nevt.getNoticeTopic());
		notificationEvent.setSubject(nevt.getSubject());
		notificationEvent.addIdentity(
				UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
		notificationEvent.addContentBean(nevt);
		MessageUtil.postRequest(notificationEvent);
		// IDispatch dispatch =
		// EventManager.getSharedInstance(EventManager.REQUEST);
		// dispatch.postEvent(notificationEvent);
	}

	/**
	 * @param casefile
	 * @returns ProbationOfficer LogonId
	 */
	private String getOfficerLogonId(JuvenileCasefile caseFile) {

		String officerProfileId = "";
		String officerLogonId = "";
		officerProfileId = (caseFile != null && caseFile
				.getProbationOfficerId() != null) ? caseFile
				.getProbationOfficerId() : "";
		OfficerProfile officerProfile = (!officerProfileId.equals("")) ? OfficerProfile
				.find(officerProfileId)
				: null;
		officerLogonId = officerProfile != null ? officerProfile.getLogonId()
				: "";
		return officerLogonId;
	}
	
	/**
	 * @param casefileId
	 * @returns ProbationOfficer ManagerId
	 */
	private String getManagerId(JuvenileCasefile caseFile) {
		String officerProfileId = "";
		String managerId = "";
		officerProfileId = (caseFile != null)?caseFile.getProbationOfficerId() : "";
		OfficerProfile officerProfile =(officerProfileId != null)?OfficerProfile.find(officerProfileId):null;
		managerId = (officerProfile != null) ? officerProfile.getManagerId(): "";

		return managerId;
	}

	/**
	 * @param event
	 * @roseuid 46C0BC620395
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0BC620397
	 */
	public void onUnregister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}
}
