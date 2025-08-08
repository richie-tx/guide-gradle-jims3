/*
 * Created on Feb 25, 2005
 */
package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import messaging.juvenilewarrant.GetVOPJuvenileWarrantForActivateEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantTypeErrorEvent;
import naming.PDJuvenileWarrantConstants;

/**
 * @author dgibler
 *  
 */
public class GetVOPJuvenileWarrantForActivateCommand implements ICommand {
	/**
	 * @param event
	 */
	public void execute(IEvent event) throws Exception {
		GetVOPJuvenileWarrantForActivateEvent warEvent = (GetVOPJuvenileWarrantForActivateEvent) event;

		List warrants = new ArrayList();
		String warrantNum = warEvent.getWarrantNum();
		if (warrantNum != null && !warrantNum.equals(""))
		{
			if (warrantNum.length() < 10)
			{ 
				if (warEvent.getWarrantNum() != null) 
				{
					JuvenileWarrant warrant = JuvenileWarrant.find(warEvent.getWarrantNum());
					if (warrant != null) 
					{
						warrants.add(warrant);
					}
				}
			}
		}
		else 
		{
			Iterator i = JuvenileWarrant.findAll(warEvent);

			while (i.hasNext()) 
			{
				JuvenileWarrant warrant = (JuvenileWarrant) i.next();
				
				if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(warrant
						.getWarrantActivationStatusId())) 
				{
					warrants.add(warrant);
				}

			}
		}

		if (warrants.size() == 1) 
		{
			this.processSingleWarrant((JuvenileWarrant) warrants.get(0));
		}
		
		if (warrants.size() > 1) 
		{
			this.processMultipleWarrants(warrants);
		}
	}

	/**
	 * @param dispatch
	 * @param warrants
	 */
	private void processMultipleWarrants(List warrants) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		for (int i =0; i < warrants.size(); i++)
		{
		    JuvenileWarrant aWarrant = (JuvenileWarrant) warrants.get(i);
			if (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(aWarrant.getWarrantTypeId()) == true) 
			{
			    if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(aWarrant
					.getWarrantActivationStatusId()) == true) 
			    {
			        dispatch.postEvent(aWarrant.valueObject());
			    }
			}
		}
	}
	
	/**
	 * @param dispatch
	 * @param warrants
	 */
	private void processSingleWarrant(JuvenileWarrant aWarrant) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(aWarrant.getWarrantTypeId()) == false) {
			InvalidWarrantTypeErrorEvent error = new InvalidWarrantTypeErrorEvent();
			error.setTopic(PDJuvenileWarrantConstants.ERROR_INVALID_WARRANT_TYPE_TOPIC);
			dispatch.postEvent(error);
		} else if (PDJuvenileWarrantConstants.WARRANT_ACTIVATION_NOT_ACTIVE.equals(aWarrant
				.getWarrantActivationStatusId()) == false) {
			ActiveWarrantErrorEvent awError = new ActiveWarrantErrorEvent();
			awError.setTopic(PDJuvenileWarrantConstants.ERROR_ACTIVE_WARRANT_EXISTS_TOPIC);
			dispatch.postEvent(awError);
		} else {
			dispatch.postEvent(aWarrant.valueObject());
			this.sendChildValues(dispatch, aWarrant);
		}

	}

	/**
	 * @param warrant
	 */
	private void sendChildValues(IDispatch dispatch, JuvenileWarrant warrant) {
		PDJuvenileWarrantHelper.sendAssociatesFields(dispatch, warrant);

		PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());
	}

	/**
	 * @param event
	 */
	public void onRegister(IEvent event) {
	}

	/**
	 * @param event
	 */
	public void onUnregister(IEvent event) {
	}

	/**
	 * @param event
	 */
	public void update(Object updateObject) {
	}

}