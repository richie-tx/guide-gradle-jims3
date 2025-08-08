// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendOverdueAssessmentNotificationCommand.java

package pd.juvenilecase.notification.transactions;

import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendOverdueAssessmentNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SendOverdueAssessmentNotificationCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B502E9
	 */
	public SendOverdueAssessmentNotificationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC6201CF
	 */
	public void execute(IEvent event) {

		SendOverdueAssessmentNotificationEvent nevt = (SendOverdueAssessmentNotificationEvent) event;
		// notificationEvent.setNotificationTopic("MJCW.JPO.OVERDUE.ASSESSMENT.NOTIFICATION");

		String casefileId = nevt.getSupervisionNumber();
		if (casefileId != null && !casefileId.equals("")) {
			JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);

			if (nevt.getIdentityType() != null
					&& nevt.getIdentityType().equals("jpo")) {
				String newIdentity = getOfficerLogonId(caseFile);
				if ((newIdentity != null) && (!newIdentity.equals(""))) {
					nevt.setIdentity(newIdentity);
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.JPO.MAYSI14DAYS.OVERDUE.NOTIFICATION")) {
				if (caseFile.getIsMAYSINeeded()) {
					if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
							|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
						this.sendNotice(nevt);
					}
				}

			}
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.JPO.MAYSI29DAYS.OVERDUE.NOTIFICATION")) {
				if (caseFile.getIsMAYSINeeded()) {
					if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
							|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
						this.sendNotice(nevt);
					}
				}

			}
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.JPO.RISK14DAYS.OVERDUE.NOTIFICATION")) {
				if (caseFile.getIsRiskAssessmentNeeded()) {
					if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
							|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
						this.sendNotice(nevt);
					}
				}
			}
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.JPO.RISK29DAYS.OVERDUE.NOTIFICATION")) {
				if (caseFile.getIsRiskAssessmentNeeded()) {
					if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
							|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
						this.sendNotice(nevt);
					}
				}
			}
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.JPO.TITLEIVEASSESS.OVERDUE.NOTIFICATION")) {
				if (caseFile.getIsBenefitsAssessmentNeeded()) {
					if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
							|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
						this.sendNotice(nevt);
					}
				}
			}
		}
	}

	// Send Notice
	private void sendNotice(SendOverdueAssessmentNotificationEvent nevt) {
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory
				.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic(nevt.getNoticeTopic());
		notificationEvent.setSubject(nevt.getSubject());
		notificationEvent.addIdentity(
				UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
		notificationEvent.addContentBean(nevt);
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(notificationEvent);
	}

	/**
	 * @param casefileId
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
	 * @param event
	 * @roseuid 46C0BC6201DD
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC6201DF
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 46C0BC6201E1
	 */
	public void update(Object anObject) {

	}

}
