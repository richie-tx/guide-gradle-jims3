// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendCasefileClosingNotificationCommand.java

package pd.juvenilecase.notification.transactions;

import java.util.Date;

import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendCasefileClosingNotificationEvent;
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
public class SendCasefileClosingNotificationCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B50098
	 */
	public SendCasefileClosingNotificationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC62028B
	 */
	public void execute(IEvent event) {

		SendCasefileClosingNotificationEvent nevt = (SendCasefileClosingNotificationEvent) event;
		String casefileId = nevt.getSupervisionNumber();

		JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
		if ((caseFile != null) && (caseFile.getCaseStatusId() != null)
				&& (!caseFile.getCaseStatusId().equals(""))) {
			Date expectedEndDate = caseFile.getSupervisionEndDate();
			if (nevt.getIdentityType() != null
					&& nevt.getIdentityType().equals("jpo")) {
				String newIdentity = getOfficerLogonId(caseFile);
				if ((newIdentity != null) && (!newIdentity.equals(""))) {
					nevt.setIdentity(newIdentity);
				}
			}
			if (nevt.getIdentityType() != null
					&& nevt.getIdentityType().equals("clm")) {
				String newIdentity = getManagerId(caseFile);
				if ((newIdentity != null) && (!newIdentity.equals(""))) {
					nevt.setIdentity(newIdentity);
				}
			}

			// check whether the notices are required
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.JPO.SUPERVISION.DUE30.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					//check if end date has changed since notice was created
					if (nevt.getSupervisionExpectedEndDate() != null && expectedEndDate!=null
						&& expectedEndDate.equals(nevt.getSupervisionExpectedEndDate())) {
						this.sendNotice(nevt);
					}
				}
			}
			
			if (nevt.getNoticeTopic().equalsIgnoreCase(
				"MJCW.JPO.SUPERVISION.DUE60.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					//check if end date has changed since notice was created
					if (nevt.getSupervisionExpectedEndDate() != null && expectedEndDate!=null
						&& expectedEndDate.equals(nevt.getSupervisionExpectedEndDate())) {
						this.sendNotice(nevt);
					}
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
				"MJCW.JPO.SUPERVISION.OVERDUE.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					//check if end date has changed since notice was created
					if (nevt.getSupervisionExpectedEndDate() != null && expectedEndDate!=null
						&& expectedEndDate.equals(nevt.getSupervisionExpectedEndDate())) {
						this.sendNotice(nevt);
					}
				}
			}			
			
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.CLM.SUPERVISION.OVERDUE.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					//check if end date has changed since notice was created
					if (nevt.getSupervisionExpectedEndDate() != null && expectedEndDate!=null
						&& expectedEndDate.equals(nevt.getSupervisionExpectedEndDate())) {
						this.sendNotice(nevt);
					}
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.CASEFILE.ASSIGNMENT.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.JPO.CASEFILE.CLOSING.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.CLM.CASEFILE.CLOSING.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.JPO.CASEFILE.CLOSING.7DAYS.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.CLM.CASEFILE.CLOSING.7DAYS.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}

			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.CASEFILE.CLOSING.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}
			// Added
			// Release Cancelled Notification #JIMS200046868
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.JPO.RELEASECANCELLED.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}
			if (nevt.getNoticeTopic().equalsIgnoreCase(
					"MJCW.M204.CLM.RELEASECANCELLED.NOTIFICATION")) {
				if (caseFile.getCaseStatusId().equalsIgnoreCase("P")
						|| caseFile.getCaseStatusId().equalsIgnoreCase("A")) {
					this.sendNotice(nevt);
				}
			}

		}
	}

	// Send Notice
	private void sendNotice(SendCasefileClosingNotificationEvent nevt) {
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
	 * @roseuid 46C0BC62028D
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0BC620299
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
