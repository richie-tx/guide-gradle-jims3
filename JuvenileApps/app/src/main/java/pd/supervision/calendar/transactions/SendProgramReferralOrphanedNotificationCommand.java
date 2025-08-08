/*
 * Created on Jan 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.calendar.transactions;

import pd.contact.officer.OfficerProfile;
import pd.supervision.programreferral.JuvenileProgramReferral;
import messaging.calendar.SendProgramReferralOrphanedNotificationEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.NotificationMessage;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.naming.NotificationControllerSerivceNames;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendProgramReferralOrphanedNotificationCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		SendProgramReferralOrphanedNotificationEvent reqEvent = (SendProgramReferralOrphanedNotificationEvent)event;
		String progRefId = reqEvent.getProgramReferralId();
		JuvenileProgramReferral progRefDetails = JuvenileProgramReferral.find(progRefId);
		String jpoId = progRefDetails.getOfficerId();
		if (!jpoId.equals("") && jpoId != null) {
			OfficerProfile officerProfile = OfficerProfile.find(jpoId);
			NotificationMessage nevt = new NotificationMessage();
			nevt.setSubject("Program Referral has no events");
			nevt.setIdentity(officerProfile.getLogonId());
			String message = "All events associated to Program Referral " + progRefId +
							" for " + progRefDetails.getJuvenile().getLastName() + ", " +
							progRefDetails.getJuvenile().getFirstName() + 
							" have been removed.";					
			nevt.setNotificationMessage(message);
			sendNotification(nevt,"MJCW.JPO.ALL.FUTURE.EVENTS.CANCELLATION.FOR.A.PROGRAMREFERRAL.NOTIFICATION");
		}
	}
	public static void sendNotification(NotificationMessage nevt, String topic){
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
					 EventFactory.getInstance(
					 NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic(topic);			
		notificationEvent.setSubject(nevt.getSubject());
		notificationEvent.addIdentity("respEvent", nevt);
		notificationEvent.addContentBean(nevt);
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
	}


	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

}
