//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\UpdateOICSignatureStatusCommand.java

package pd.juvenilewarrant.transactions;

import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import pd.codetable.Code;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import pd.km.util.AuthHelper;
import messaging.juvenilewarrant.UpdateOICSignatureStatusEvent;
import messaging.juvenilewarrant.reply.NoWarrantStatusErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author ryoung
 *
 */
public class UpdateOICSignatureStatusCommand implements ICommand
{

	private static final String NO_REASON = "No reason specified.";

	/**
	 * @roseuid 41AF69950067
	 */
	public UpdateOICSignatureStatusCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41AF5ED4031B
	 */
	public void execute(IEvent event)
	{
		UpdateOICSignatureStatusEvent statusEvent = (UpdateOICSignatureStatusEvent) event;
		JuvenileWarrant jwarrant = JuvenileWarrant.find(statusEvent.getWarrantNum());

		if (jwarrant != null)
		{
			// confirm that the warrant status is PENDING (PreCondition of the UseCase).

			Code warrantStatus = jwarrant.getWarrantStatus();

			if (warrantStatus == null)
			{
				// Handle error condition for no warrantStatus
				this.sendNoWarrantStatusError(jwarrant.getWarrantNum());
			}
			else
			{
				String warrantStatusCode = warrantStatus.getCode();

				if (PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING.equals(warrantStatusCode))
				{
					String signatureOption = statusEvent.getSignatureOption();
					if (PDJuvenileWarrantConstants.WARRANT_UNSEND.equals(signatureOption))
					{
						this.updateUnsend(statusEvent, jwarrant);
					}
					else if (PDJuvenileWarrantConstants.WARRANT_RETURN.equals(signatureOption))
					{
						this.updateReturn(statusEvent, jwarrant);
					}
					else if (PDJuvenileWarrantConstants.WARRANT_SIGNED.equals(signatureOption))
					{
						this.updateSign(statusEvent, jwarrant);
					}
				}
			}
		}
	}

	private void updateUnsend(UpdateOICSignatureStatusEvent statusEvent, JuvenileWarrant jwarrant)
	{
		jwarrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_RECALL);
		jwarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_UNSEND);
		jwarrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);
		jwarrant.setUnsendNotSignedReason(statusEvent.getUnSendNotSignedReason());

		if (statusEvent.getUnSendNotSignedReason() == null)
		{
			jwarrant.setUnsendNotSignedReason(NO_REASON);
		}
		else
		{
			jwarrant.setUnsendNotSignedReason(statusEvent.getUnSendNotSignedReason());
		}

		setFileStamp(jwarrant);

		JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
		worker.sendNotification(jwarrant, PDNotificationConstants.OIC_DO_NOT_ISSUE);
	}

	private void updateReturn(UpdateOICSignatureStatusEvent statusEvent, JuvenileWarrant jwarrant)
	{
		jwarrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING);
		jwarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE);
		jwarrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);

		if (statusEvent.getUnSendNotSignedReason() == null)
		{
			jwarrant.setUnsendNotSignedReason(NO_REASON);
		}
		else
		{
			jwarrant.setUnsendNotSignedReason(statusEvent.getUnSendNotSignedReason());
		}

		setFileStamp(jwarrant);

		JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
		worker.sendNotification(jwarrant, PDNotificationConstants.OIC_UPDATES_REQUIRED);
	}

	private void updateSign(UpdateOICSignatureStatusEvent statusEvent, JuvenileWarrant jwarrant)
	{
		jwarrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN);
		jwarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
		jwarrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_SIGNED);
		jwarrant.setWarrantActivationDate(new java.util.Date());
		setFileStamp(jwarrant);
		jwarrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED);

		if (statusEvent.getUnSendNotSignedReason() == null)
		{
			jwarrant.setUnsendNotSignedReason(NO_REASON);
		}
		else
		{
			jwarrant.setUnsendNotSignedReason(statusEvent.getUnSendNotSignedReason());
		}

		JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
		worker.sendNotification(jwarrant, PDNotificationConstants.OIC_JUVENILE_IS_WANTED);
	}

	private void sendNoWarrantStatusError(String warrantNum)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		NoWarrantStatusErrorEvent event = new NoWarrantStatusErrorEvent();
		event.setWarrantNum(warrantNum);
		dispatch.postEvent(event);
	}

	private void setFileStamp(JuvenileWarrant jwarrant)
	{
		jwarrant.setFileStampDate(DateUtil.getCurrentDate());
		jwarrant.setFileStampUserId(AuthHelper.getCurrentUser());
	}

	/**
	 * @param event
	 * @roseuid 41AF5ED40327
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 41AF5ED40329
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 41AF5ED4032B
	 */
	public void update(Object anObject)
	{
	}
}