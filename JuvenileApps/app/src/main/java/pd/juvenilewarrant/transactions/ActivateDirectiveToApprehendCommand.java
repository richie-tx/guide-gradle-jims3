//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\ActivateDirectiveToApprehendCommand.java

package pd.juvenilewarrant.transactions;

import java.util.Calendar;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import pd.exception.ActiveWarrantException;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import messaging.juvenilewarrant.ActivateDirectiveToApprehendEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.pattern.IBuilder;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActivateDirectiveToApprehendCommand implements ICommand
{

	/**
	 * @roseuid 4187CC000326
	 */
	public ActivateDirectiveToApprehendCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4187BDE70097
	 */
	public void execute(IEvent event) throws ActiveWarrantException
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ActivateDirectiveToApprehendEvent updateDTAEvent = (ActivateDirectiveToApprehendEvent) event;
		JuvenileWarrant warrant = JuvenileWarrant.find(updateDTAEvent.getWarrantNum());

		/*
		 * See if there is an active warrant for this Juvenile (Petition# or Juv# and Ref#)
		 * If not found, can proceed, as there is no ACTIVE juvenile warrant
		 * for this juvenile under this referral.  So nothing needs to be
		 * caught or thrown.
		 */
		boolean activeWarrantExists =
			PDJuvenileWarrantHelper.checkForActiveWarrant(
				warrant.getJuvenileNum(),
				String.valueOf(warrant.getDaLogNumber()),
				warrant.getDateOfBirth(),
				warrant.getFirstName(),
				warrant.getLastName(),
				null,
				"");

		if (activeWarrantExists)
		{
			ActiveWarrantErrorEvent error = new ActiveWarrantErrorEvent();
			error.setMessage("There already exists an active Juvenile Warrant for this juvenile: " + warrant.getJuvenileNum());
			dispatch.postEvent(error);
			return;
		}

		if (warrant != null)
		{
			// Send DTA notification that Juvenile is wanted.
			JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
			worker.sendNotification(warrant, PDNotificationConstants.DTA_WANTED);
			
			warrant.setWarrantActivationDate(Calendar.getInstance().getTime());
			warrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
			warrant.setWarrantSignedStatusId(PDJuvenileWarrantConstants.WARRANT_SIGNED);
			warrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN);

			//create response events
			IBuilder builder = new JuvenileWarrantBuilder(warrant);
	        builder.build();
	        JuvenileWarrantResponseEvent response = (JuvenileWarrantResponseEvent) builder.getResult();

			dispatch.postEvent(response);
		}
	}

	/**
	 * @param event
	 * @roseuid 4187BDE70099
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 4187BDE7009B
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param updateObject
	 * @roseuid 4187CC000336
	 */
	public void update(Object updateObject)
	{
	}
}