package pd.juvenilewarrant.transactions;

import java.util.Calendar;
import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.km.util.AuthHelper;
import messaging.juvenilewarrant.InactivateJuvenileWarrantEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.IBuilder;

/**
 * @author asrvastava
 *
 */
public class InactivateJuvenileWarrantCommand implements ICommand
{

	/**
	 * @roseuid 41F7C3320362
	 */
	public InactivateJuvenileWarrantCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41F7B68A0335
	 */
	public void execute(IEvent event)
	{
		InactivateJuvenileWarrantEvent recallEvent = (InactivateJuvenileWarrantEvent) event;
		JuvenileWarrant juvWarrant = JuvenileWarrant.find(recallEvent.getWarrantNum());
		if (juvWarrant != null)
		{
			juvWarrant.setRecallReasonId(recallEvent.getRecallReason());
			juvWarrant.setRecallUserId(AuthHelper.getCurrentUser());
			juvWarrant.setRecallDate(Calendar.getInstance().getTime());
			juvWarrant.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_INACTIVE);
			
			//create response events
			IBuilder builder = new JuvenileWarrantBuilder(juvWarrant);
	        builder.build();
	        ResponseEvent response = (ResponseEvent) builder.getResult();

			MessageUtil.postReply(response);
		}
	}

	/**
	 * @param event
	 * @roseuid 41F7B68A0337
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41F7B68A0339
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 41F7C3320382
	 */
	public void update(Object updateObject)
	{

	}
}