// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendPendingAppointmentNotificationCommand.java

package pd.juvenilecase.notification.transactions;

import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendPendingAppointmentNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendPendingAppointmentNotificationCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B60105
	 */
	public SendPendingAppointmentNotificationCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0BC62024A
	 */
	public void execute(IEvent event) throws Exception{

		SendPendingAppointmentNotificationEvent nevt = (SendPendingAppointmentNotificationEvent) event;
		String casefileId = nevt.getSupervisionNumber();
		JuvenileCasefile cf = JuvenileCasefile.find(casefileId);
		if(casefileId != null && !casefileId.equals("")) {
			String newIdentity = getOfficerLogonId(casefileId);
			if((newIdentity!= null) && (!newIdentity.equals(""))){
				nevt.setIdentity(newIdentity);
			}
		}
		
		if(cf.getCaseStatusId().equalsIgnoreCase("P") || cf.getCaseStatusId().equalsIgnoreCase("A")) {
		
			CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
			notificationEvent.setNotificationTopic("MJCW.JPO.PENDING.APPOINTMENT.NOTIFICATION");
			notificationEvent.setSubject(nevt.getSubject());
			notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
			notificationEvent.addContentBean(nevt);
			MessageUtil.postRequest(notificationEvent);
		}
	}

	/**
	 * @param casefileId
	 * @returns ProbationOfficer LogonId
	 */
	private String getOfficerLogonId(String casefileId){
		
		String officerProfileId = "";
		String officerLogonId = "";
		JuvenileCasefile caseFile = JuvenileCasefile.find(casefileId);
		officerProfileId = (caseFile!=null && caseFile.getProbationOfficerId()!=null) ? caseFile.getProbationOfficerId(): "" ;
		OfficerProfile officerProfile = (!officerProfileId.equals(""))? OfficerProfile.find(officerProfileId):null ;
		officerLogonId = officerProfile!=null ? officerProfile.getLogonId() : "";
		return officerLogonId;
	}
	
	/**
	 * @param event
	 * @roseuid 46C0BC62024C
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0BC62024E
	 */
	public void onUnregister(IEvent event) {
	}

	/**
	 * @param anObject
	 * @roseuid 46C0BC62025B
	 */
	public void update(Object anObject) {
	}
}