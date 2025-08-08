// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\juvenilecase\\notification\\transactions\\SendCaseplanNotificationEventCommand.java

package pd.juvenilecase.notification.transactions;

import java.util.Iterator;

import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.notification.SendNonComplianceSanctionDateExpiredNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.UIConstants;
import pd.juvenilecase.casefile.CasefileNonComplianceNotice;

/**
 * @author cShimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SendNonComplianceSanctionDateExpiredNotificationCommand implements ICommand {

	/**
	 * @roseuid 46C1B3B50153
	 */
	public SendNonComplianceSanctionDateExpiredNotificationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 46C0BC620393
	 */
	public void execute(IEvent event) throws Exception {

		SendNonComplianceSanctionDateExpiredNotificationEvent nevt = (SendNonComplianceSanctionDateExpiredNotificationEvent) event;

		CasefileNonComplianceNoticeResponseEvent resp = new CasefileNonComplianceNoticeResponseEvent();
		Iterator notices = CasefileNonComplianceNotice.findAll("casefileId", nevt.getSupervisionNumber() );
		while (notices.hasNext())
		{
			CasefileNonComplianceNotice notice = (CasefileNonComplianceNotice) notices.next();
			resp = notice.getResponseEvent();
			if (nevt.getNoticeId().equalsIgnoreCase(resp.getCasefileNonComplianceNoticeId() ) )
			{
				if (resp.getCompletionStatusId() == null || "NOO".equalsIgnoreCase(resp.getCompletionStatusId() ) ) 
				{
					StringBuffer sb = new StringBuffer("The Non-compliance status is ");
					if (resp.getCompletionStatusId() == null){
						sb.append("PENDING");
					} else {
						sb.append("NO");
					}
					sb.append(" for Juvenile #");
					sb.append(nevt.getJuvenileNum());
					sb.append(". The sanctions were to be completed by today.");
					nevt.setNotificationMessage(sb.toString());
					CreateNotificationEvent notificationEvent = 
						(CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
					notificationEvent.setNotificationTopic(nevt.getNoticeTopic());
					notificationEvent.setSubject(nevt.getSubject());
					notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
					notificationEvent.addContentBean(nevt);
					MessageUtil.postRequest(notificationEvent);
				}
			}
		}
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
