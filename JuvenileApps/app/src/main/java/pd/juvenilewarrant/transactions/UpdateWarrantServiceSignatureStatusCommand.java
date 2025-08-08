package pd.juvenilewarrant.transactions;

import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantServiceBuilder;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.pattern.IBuilder;
import messaging.juvenilewarrant.UpdateWarrantServiceSignatureStatusEvent;

public class UpdateWarrantServiceSignatureStatusCommand implements ICommand
{

	/**
	 * @roseuid 41FFDACD0251
	 */
	public UpdateWarrantServiceSignatureStatusCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 41F1732A02F0
	 */
	public void execute(IEvent event)
	{
		UpdateWarrantServiceSignatureStatusEvent updateWarrantEvent = (UpdateWarrantServiceSignatureStatusEvent) event;
		
		JuvenileWarrant warrant = JuvenileWarrant.find(updateWarrantEvent.getWarrantNum());

		// this command should always set this status to printed
		warrant.setServiceReturnSignatureStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_FILED);
		
		JuvenileWarrantWorker.unregisterEvents(warrant);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		IBuilder builder = new JuvenileWarrantServiceBuilder(warrant);
        builder.build();
        ResponseEvent response = (ResponseEvent) builder.getResult();
		dispatch.postEvent(response);
	}

	/**
	 * 
	 * @param warrant
	 */

	

	/**
	 * @param event
	 * @roseuid 41F1732A02F2
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41F1732A02F4
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 41FFDACD0261
	 */
	public void update(Object updateObject)
	{

	}
}
