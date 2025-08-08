//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\ActivateJuvenileArrestWarrantCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Calendar;

import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import pd.notification.juvenilewarrant.PDJuvenileWarrantNotificationHelper;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import messaging.juvenilewarrant.ActivateJuvenileArrestWarrantEvent;
import messaging.juvenilewarrant.SendJuvenileArrestWarrantNotificationEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.pattern.IBuilder;
import pd.exception.ActiveWarrantException;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActivateJuvenileArrestWarrantCommand implements ICommand
{

	/**
	 * @roseuid 41E693E10352
	 */
	public ActivateJuvenileArrestWarrantCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41E692CB0334
	 */
	public void execute(IEvent event) throws ActiveWarrantException
	{
		ActivateJuvenileArrestWarrantEvent updateARREvent = (ActivateJuvenileArrestWarrantEvent) event;
		JuvenileWarrant warrant = JuvenileWarrant.find(updateARREvent.getWarrantNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		// See if there is an active warrant for this Juvenile (Petition# or Juv# and Ref#)
		// If not found, can proceed, as there is no ACTIVE juvenile warrant
		// for this juvenile under this referral.  So nothing needs to be
		// caught or thrown.
		//		boolean activeWarrantExists =
		//			PDJuvenileWarrantHelper.checkForActiveWarrant(
		boolean activeWarrantExists =
			PDJuvenileWarrantHelper.checkForActiveWarrant(
				warrant.getJuvenileNum(),
				String.valueOf(warrant.getDaLogNumber()),
				warrant.getDateOfBirth(),
				warrant.getFirstName(),
				warrant.getLastName(),
				warrant.getReferralNum(),
				"");

		if (activeWarrantExists)
		{
			ActiveWarrantErrorEvent error = new ActiveWarrantErrorEvent();
			error.setMessage("There already exists an active Juvenile Warrant for this juvenile: " + warrant.getJuvenileNum());
			dispatch.postEvent(error);			
		}
		else
		{
			warrant.setWarrantActivationDate(Calendar.getInstance().getTime());
			warrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
			warrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_SIGNED);
			warrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN);

			//create response events
			IBuilder builder = new JuvenileWarrantBuilder(warrant);
	        builder.build();
	        JuvenileWarrantResponseEvent response = (JuvenileWarrantResponseEvent) builder.getResult();

			dispatch.postEvent(response);
			
			JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
			worker.sendNotification(warrant, PDNotificationConstants.ARR_WANTED);
		
			this.processARRActivationFailure(warrant);				
		}
	}
	
	/**
	 * @param notificationEvent
	 */
	private void processARRActivationFailure(JuvenileWarrant warrant)
	{
		//unregister activation failure that had been posted in the create of the ARR warrant
		IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		String taskName =
			PDJuvenileWarrantNotificationHelper.getTaskName(
				SendJuvenileArrestWarrantNotificationEvent.class.getName(),
				warrant.getWarrantNum(),
				PDNotificationConstants.ARR_ACTIVATION_FAILURE);

		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
		unRegTaskEvent.setTaskName(taskName);
		requestDispatch.postEvent(unRegTaskEvent);
	}

	/**
	 * @param event
	 * @roseuid 41E692CB0336
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41E692CB0338
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 */
	public void update(Object updateObject)
	{

	}
}
