// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendMentalHealthNotificationCommand.java

package pd.juvenilecase.notification.transactions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendMentalHealthNotificationEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.TaskControllerServiceNames;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;
import pd.task.helper.TaskHelper;

public class SendMentalHealthNotificationCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B5022E
	 */
	public SendMentalHealthNotificationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC620307
	 */
	public void execute(IEvent event) throws Exception {
		SendMentalHealthNotificationEvent nevt = (SendMentalHealthNotificationEvent) event;

		// Added condition to differentiate Task and Notice.
		if (nevt.getAlertType().equalsIgnoreCase("MaysiReqTask")) {
			GetAllMAYSIAssessmentsEvent requestEvent = (GetAllMAYSIAssessmentsEvent) EventFactory
					.getInstance(JuvenileMentalHealthControllerServiceNames.GETALLMAYSIASSESSMENTS);
			requestEvent.setJuvenileNumber(nevt.getJuvenileNum());
			requestEvent.setReferralNumber("%");
			Iterator juvMAYSIItertor = JuvenileMAYSIComplete.findAll(requestEvent);
			while (juvMAYSIItertor.hasNext())
			{
				int checkTask = 0; // Not to send duplicate alerts.
				JuvenileMAYSIComplete juvMAYSI = (JuvenileMAYSIComplete) juvMAYSIItertor.next();
				if (juvMAYSI.getAssessmentOptionId().equals(PDJuvenileCaseConstants.NEW_MAYSI_ASSESSMENT_OPTION)) {
					MAYSISearchResultResponseEvent maysiEvent = juvMAYSI.getSearchResponseEvent();
					String strMaysiId = maysiEvent.getMaysiFullAssessId();
					if (strMaysiId.equals(nevt.getMaysiId())) {
						int i = strMaysiId.lastIndexOf("-");
						strMaysiId = strMaysiId.substring(i + 1);
						if ((checkTask == 0) && ("0".equals(strMaysiId))) {
							//Create Task
							CreateTaskEvent createTask = (CreateTaskEvent) EventFactory.getInstance(TaskControllerServiceNames.CREATETASK);
							createTask.setCreatorId(nevt.getUserID());
							createTask.setOwnerId(nevt.getIdentity());
							createTask.addTaskStateItem("maysiId", nevt.getMaysiId());
							createTask.addTaskStateItem("juvenileNum", nevt.getJuvenileNum());
							// createTask.addTaskStateItem("submitAction","New Assessment");
							createTask.addTaskStateItem("action", "update");
							// Added as it is throwing an exception.
							createTask.addTaskStateItem("locationUnitId", nevt.getLocationUnitId());
							createTask.addTaskStateItem("facilityTypeId", nevt.getFacilityTypeId());
							createTask.addTaskStateItem("lengthOfStayId", nevt.getLengthOfStayId());
							createTask.addTaskStateItem("submitAction", "Link");
							// End
							// createTask.setTaskTopic("MJCW.MAYSIREQUESTOR.ACTION.ALERT");
							createTask.setTaskTopic(nevt.getTaskTopic());
							createTask.setTaskSubject(nevt.getSubject());
							checkTask++;
							//<KISHORE>JIMS200057619 : MJCW - Get Rid of Command Chaining for Tasks
							//IDispatch dispatch1 = EventManager.getSharedInstance( EventManager.REQUEST ) ;
							//dispatch1.postEvent( createTask ) ;
							try {
								@SuppressWarnings("unused")
								String taskId = TaskHelper.createTask(createTask);
							} catch (IOException e) {
								throw e;
							}
						}
					}
				}

			}
		}
		// 20Days Trigger fired for CLM
		if (nevt.getAlertType().equalsIgnoreCase("Notice")) {
			JuvenileMAYSIMetadata maysiDetails = JuvenileMAYSIMetadata
					.find(nevt.getMaysiAssessId());

			String casefileId = nevt.getSupervisionNumber();
			if (casefileId != null && !(casefileId.equals(""))) {
				JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
				if ((caseFile != null)
						&& (!(caseFile.getCaseStatusId().equals("")))
						&& (caseFile.getCaseStatusId() != null)
						&& (caseFile.getCaseStatusId()
								.equalsIgnoreCase(PDJuvenileCaseConstants.CASESTATUS_ACTIVE))) {
					// If subsequent needed then send the notice.
					if (maysiDetails != null
							&& maysiDetails.getAssessmentOptionId()
									.equalsIgnoreCase("B")) {
						CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
								.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
						notificationEvent.setNotificationTopic(nevt
								.getNoticeTopic());
						notificationEvent.setSubject(nevt.getSubject());
						notificationEvent.addIdentity(
								UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT,
								nevt);
						notificationEvent.addContentBean(nevt);
						MessageUtil.postRequest(notificationEvent);
						// IDispatch dispatch =
						// EventManager.getSharedInstance(EventManager.REQUEST);
						// dispatch.postEvent(notificationEvent);
					}
				}
			}
		}

		// 30 Days Trigger fired for JPO
		if (nevt.getAlertType().equalsIgnoreCase("Task")) {
			JuvenileMAYSIMetadata maysiDetails = JuvenileMAYSIMetadata
					.find(nevt.getMaysiAssessId());
			// If subsequent needed then send the Task.
			// need to complete
			String casefileId = nevt.getSupervisionNumber();
			if (casefileId != null && !(casefileId.equals(""))) {
				JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
				if ((caseFile != null)
						&& (!(caseFile.getCaseStatusId().equals("")))
						&& (caseFile.getCaseStatusId() != null)
						&& (caseFile.getCaseStatusId()
								.equalsIgnoreCase(PDJuvenileCaseConstants.CASESTATUS_ACTIVE))) {
					if (maysiDetails != null
							&& maysiDetails.getAssessmentOptionId()
									.equalsIgnoreCase("B")) {
						CreateTaskEvent createTask = (CreateTaskEvent) EventFactory
								.getInstance(TaskControllerServiceNames.CREATETASK);
						createTask.setCreatorId(nevt.getUserID());
						createTask.setOwnerId(nevt.getIdentity()); // same as
																	// ownerId
						createTask.addTaskStateItem("maysiId", nevt
								.getMaysiAssessId());
						createTask.addTaskStateItem("juvenileNum", nevt
								.getJuvenileNum());
						createTask.addTaskStateItem("officerId", nevt
								.getOfficerId());
						createTask.addTaskStateItem("caseFileId", nevt
								.getSupervisionNumber());
						createTask.addTaskStateItem("submitAction", "Link");
						createTask.addTaskStateItem("assessmentOption",
								maysiDetails.getAssessmentOption()
										.getDescription());
						createTask.addTaskStateItem("referralNumber",
								maysiDetails.getReferralNumber());
						createTask.addTaskStateItem("locationUnitId", nevt
								.getLocationUnitId());
						createTask.addTaskStateItem("facilityTypeId", nevt
								.getFacilityTypeId());
						createTask.addTaskStateItem("lengthOfStayId", nevt
								.getLengthOfStayId());
						createTask.setTaskTopic(nevt.getTaskTopic());
						createTask.setTaskSubject(nevt.getTaskSubject());
						//<KISHORE>JIMS200057619 : MJCW - Get Rid of Command Chaining for Tasks
						//IDispatch dispatch1 = EventManager.getSharedInstance( EventManager.REQUEST ) ;
						//dispatch1.postEvent( createTask ) ;
			            try {
			                @SuppressWarnings("unused")
							String taskId = TaskHelper.createTask(createTask);
			            } catch (IOException e) {
			                throw e;
			            }
					}
				}
			}
		}
		// 30 Days Trigger fired for CLM
		if (nevt.getAlertType().equalsIgnoreCase("Email")) {
			
			JuvenileMAYSIMetadata maysiDetails = JuvenileMAYSIMetadata
			.find(nevt.getMaysiAssessId());
			// If subsequent needed then send the Email			
			String casefileId = nevt.getSupervisionNumber();
			if (casefileId != null && !(casefileId.equals(""))) {
				JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
				if ((caseFile != null)
						&& (!(caseFile.getCaseStatusId().equals("")))
						&& (caseFile.getCaseStatusId() != null)
						&& (caseFile.getCaseStatusId()
								.equalsIgnoreCase(PDJuvenileCaseConstants.CASESTATUS_ACTIVE))) {
					if (maysiDetails != null
							&& maysiDetails.getAssessmentOptionId()
									.equalsIgnoreCase("B")) {
					
						
						 SendEmailEvent sendEmailEvent = new SendEmailEvent();								
						 sendEmailEvent.setFromAddress("JIMS2Notifications@hctx.net");
						 sendEmailEvent.setSubject(nevt.getSubject());
						 sendEmailEvent.setMessage(nevt.getNotificationMessage());
						//get the email id 
						 Iterator<OfficerProfile> officerprofiles = OfficerProfile.findAll("logonId",nevt.getIdentity());
						 while(officerprofiles.hasNext())
						 {
							 OfficerProfile officer = (OfficerProfile)officerprofiles.next();
							 sendEmailEvent.addToAddress(officer.getEmail());
						 }
						// UserProfile userProfile = UserProfile.find(nevt.getIdentity());
						// sendEmailEvent.addToAddress(userProfile.getEmail());
						 IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					        dispatch.postEvent(sendEmailEvent);
						
					}
				}
			}
					
		}
	}

	/**
	 * @param event
	 * @roseuid 46C0BC620309
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0BC62030B
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
