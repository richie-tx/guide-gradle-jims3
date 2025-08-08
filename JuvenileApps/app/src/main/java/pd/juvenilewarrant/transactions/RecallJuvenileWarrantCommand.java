package pd.juvenilewarrant.transactions;

import java.util.Calendar;

import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import pd.km.util.AuthHelper;
import messaging.juvenilewarrant.RecallJuvenileWarrantEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.IBuilder;

/**
 * @author asrvastava
 *
 */
public class RecallJuvenileWarrantCommand implements ICommand
{

	/**
	 * @roseuid 41F7C32002E5
	 */
	public RecallJuvenileWarrantCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 41F7B6A2019E
	 */
	public void execute(IEvent event)
	{
		RecallJuvenileWarrantEvent recallEvent = (RecallJuvenileWarrantEvent) event;
		JuvenileWarrant juvWarrant = JuvenileWarrant.find(recallEvent.getWarrantNum());
		if (juvWarrant != null)
		{
			juvWarrant.setRecallReasonId(recallEvent.getRecallReason());
			juvWarrant.setRecallUserId(AuthHelper.getCurrentUser());
			juvWarrant.setRecallDate(Calendar.getInstance().getTime());
			juvWarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE);
			juvWarrant.setWarrantStatusId(PDJuvenileWarrantConstants.WARRANT_STATUS_RECALL);

			//create response events
			IBuilder builder = new JuvenileWarrantBuilder(juvWarrant);
	        builder.build();
	        ResponseEvent response = (ResponseEvent) builder.getResult();
			IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
			MessageUtil.postReply(response);
			JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
			worker.sendNotificationWarrantRecalled(juvWarrant);
		}
	}

	/**
	 * @param event
	 * @roseuid 41F7B6A201A0
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 41F7B6A201A2
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param updateObject
	 * @roseuid 41F7C3200305
	 */
	public void update(Object updateObject)
	{
	}
}