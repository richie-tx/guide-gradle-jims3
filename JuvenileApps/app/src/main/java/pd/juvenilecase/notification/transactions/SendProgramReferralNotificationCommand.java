// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendProgramReferralNotificationCommand.java

package pd.juvenilecase.notification.transactions;

import pd.juvenilecase.JuvenileCasefile;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendProgramReferralNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.UIConstants;

public class SendProgramReferralNotificationCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B601D0
	 */
	public SendProgramReferralNotificationCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0009
	 */
	public void execute(IEvent event) throws Exception{
		SendProgramReferralNotificationEvent nevt = (SendProgramReferralNotificationEvent) event;
		
		JuvenileCasefile cf = JuvenileCasefile.find(nevt.getSupervisionNumber());
		if(cf.getCaseStatusId().equalsIgnoreCase("P") || cf.getCaseStatusId().equalsIgnoreCase("A")){
			CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
			notificationEvent.setNotificationTopic("MJCW.SP.PROGRAM.REFERRAL.ACTION.ALERT");			
			notificationEvent.setSubject(nevt.getSubject());
			notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
			notificationEvent.addContentBean(nevt);
			MessageUtil.postRequest(notificationEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE000B
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 * @roseuid 46C0C0DE0018
	 */
	public void onUnregister(IEvent event) {
	}

	/**
	 * @param anObject
	 * @roseuid 46C0C0DE001A
	 */
	public void update(Object anObject) {
	}
}
