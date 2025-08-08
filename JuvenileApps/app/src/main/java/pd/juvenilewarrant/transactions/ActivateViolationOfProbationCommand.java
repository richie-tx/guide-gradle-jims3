//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\ActivateViolationOfProbationCommand.java

package pd.juvenilewarrant.transactions;

import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import messaging.juvenilewarrant.ActivateViolationOfProbationEvent;
import messaging.mojo.DataNotFoundEvent;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActivateViolationOfProbationCommand implements ICommand
{

	/**
	 * @roseuid 42110051000C
	 */
	public ActivateViolationOfProbationCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4210F85F03D7
	 */
	public void execute(IEvent anEvent)
	{
		ActivateViolationOfProbationEvent event = (ActivateViolationOfProbationEvent) anEvent;

		JuvenileWarrant warrant = JuvenileWarrant.find(event.getWarrantNum());

		if (warrant == null)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			DataNotFoundEvent replyEvent = new DataNotFoundEvent();
			replyEvent.setTopic(PDJuvenileWarrantConstants.JUVENILE_WARRANT_EVENT_TOPIC);
			dispatch.postEvent(replyEvent);
		}
		else
		{
			String status = event.getWarrantActivationStatus();

			JuvenileWarrantWorker worker = new JuvenileWarrantWorker();

			if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE.equals(status))
			{
				this.applyWarrantActive(warrant, event.getUnSendNotSignedReason());
				worker.sendNotification(warrant, PDNotificationConstants.VOP_ACTIVATION);
			}
			else if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_UNSEND.equals(status))
			{
				this.applyWarrantUnsend(warrant, event.getUnSendNotSignedReason());				
				worker.sendNotification(warrant, PDNotificationConstants.VOP_UNSEND);
			}
			else if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_REJECTED.equals(status))
			{
				this.applyWarrantRejected(warrant, event.getUnSendNotSignedReason());
				worker.sendNotification(warrant, PDNotificationConstants.VOP_REJECTION);
			}
		}
	}

	private void applyWarrantActive(JuvenileWarrant warrant, String reason)
	{
		warrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN);
		warrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
		warrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_SIGNED);
		warrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED);
		warrant.setWarrantActivationDate(new java.util.Date());
		warrant.setWarrantAcknowledgementDate(new java.util.Date());
		warrant.setUnsendNotSignedReason(reason);
	}

	private void applyWarrantUnsend(JuvenileWarrant warrant, String reason)
	{
		warrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_PENDING);
		warrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_UNSEND);
		warrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);
		warrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED);
		warrant.setUnsendNotSignedReason(reason);
	}

	private void applyWarrantRejected(JuvenileWarrant warrant, String reason)
	{
		warrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_RECALL);
		warrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE);
		warrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_NOT_SIGNED);
		warrant.setWarrantAcknowledgeStatusId(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_NOT_PRINTED);
		warrant.setUnsendNotSignedReason(reason);
	}

	/**
	 * @param event
	 * @roseuid 4210F85F03D9
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4210F85F03DB
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42110051001C
	 */
	public void update(Object updateObject)
	{

	}
}
