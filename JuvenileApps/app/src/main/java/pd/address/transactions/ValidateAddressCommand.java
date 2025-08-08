//Source file: C:\\views\\dev\\app\\src\\pd\\address\\transactions\\ValidateAddressCommand.java

package pd.address.transactions;

import messaging.address.AddressRequestEvent;
import messaging.address.ValidateAddressEvent;
import messaging.addressValidation.AddressValidationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author unknown
 * This command has not yet been implemented.
 */
public class ValidateAddressCommand implements ICommand
{

	/**
	@roseuid 4107C378003D
	 */
	public ValidateAddressCommand()
	{

	}

	/**
	@param event
	@roseuid 40E54CEB00ED
	 */
	public void execute(IEvent event)
	{
		ValidateAddressEvent rEvent = (ValidateAddressEvent)event; 
	
		AddressValidationEvent requestEvent = new AddressValidationEvent();
		requestEvent.setStreetNum(new Integer(rEvent.getStreetNum()).intValue());
		requestEvent.setStreetName(rEvent.getStreetName());
		requestEvent.setZipCode(new Integer(rEvent.getZipCode()).intValue());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
	}

	/**
	@param event
	@roseuid 40E54CEB00F6
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 40E54CEB00F8
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 4107C378005B
	 */
	public void update(Object updateObject)
	{

	}
}
