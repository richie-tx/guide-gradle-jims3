/*
 * Created on Jul 22, 2005
 *
 */
package mojo.km.addressValidation.transactions;

import java.util.Iterator;

import messaging.addressValidation.AddressValidationEvent;
import messaging.addressValidation.ValidateBlockRangeEvent;
import messaging.addressValidation.ValidateStreetZipEvent;
import messaging.addressValidation.reply.ValidateAddressResponseEvent;
import mojo.km.addressValidation.Address;
import mojo.km.addressValidation.AddressValidationHelper;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author Rcooper
 *
 */
public class AddressValidationCommand implements ICommand
{
	private void setBlockRange(AddressValidationEvent addEvent, ValidateBlockRangeEvent event)
	{
		if(addEvent.getStreetNum() > 99)
		{
			String beginBlockString = String.valueOf(addEvent.getStreetNum());
			beginBlockString = beginBlockString.substring(0,beginBlockString.length() - 2);
			String endBlockString = beginBlockString + "99";
			beginBlockString += "00";
			event.setBeginingStreetNum(Integer.parseInt(beginBlockString));
			event.setEndingStreetNum(Integer.parseInt(endBlockString));			
		}
		else
		{
			event.setBeginingStreetNum(0);
			event.setBeginingStreetNum(99);
		}

		event.setStreetName(addEvent.getStreetName());
		event.setZipCode(addEvent.getZipCode());
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		AddressValidationEvent addEvent = (AddressValidationEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator addresses = Address.findAll(addEvent);
		String continueSearch = "y";
		while (addresses.hasNext())
		{
			continueSearch = "N";
			Address address = (Address) addresses.next();
			ValidateAddressResponseEvent addressResponse =
				AddressValidationHelper.getValidateAddressResponseEvent(address);
			addressResponse.setTopic("VALIDATE_ADDRESS");
			addressResponse.setReturnMessage("Exact address was found - Search was successful ");

			dispatch.postEvent(addressResponse);
		}
		if (continueSearch.equals("y"))
		{
			ValidateBlockRangeEvent vbre = new ValidateBlockRangeEvent();

			this.setBlockRange(addEvent, vbre);

			Iterator addresses2 = Address.findAll(vbre);

			while (addresses2.hasNext())
			{
				continueSearch = "N";
				Address address = (Address) addresses2.next();
				ValidateAddressResponseEvent addressResponse =
					AddressValidationHelper.getValidateAddressResponseEvent(address);
				addressResponse.setTopic("VALIDATE_ADDRESS");
				addressResponse.setReturnMessage("Exact address was not found - Block range Search was successful ");
				dispatch.postEvent(addressResponse);
				return;
			}
		}
		if (continueSearch.equals("y"))
		{
			ValidateStreetZipEvent vsze = new ValidateStreetZipEvent();

			vsze.setStreetName(addEvent.getStreetName());
			vsze.setZipCode(addEvent.getZipCode());
			Iterator addresses3 = Address.findAll(vsze);
			
			while (addresses3.hasNext())
			{
				continueSearch = "N";
				Address address = (Address) addresses3.next();
				ValidateAddressResponseEvent addressResponse =
					AddressValidationHelper.getValidateAddressResponseEvent(address);
				addressResponse.setTopic("VALIDATE_ADDRESS");
				addressResponse.setReturnMessage(
					"Invalid Street Number.  However, Street Name / Zip Code Combination is Valid ");
				addressResponse.setValidAddressInd("N");
				dispatch.postEvent(addressResponse);
				return;
			}
		}
		if (continueSearch.equals("y"))
		{
			ValidateAddressResponseEvent addressResponse = new ValidateAddressResponseEvent();
			addressResponse.setReturnMessage("Invalid Address");
			addressResponse.setValidAddressInd("N");
			addressResponse.setStreetName(addEvent.getStreetName());
			addressResponse.setStreetNum(new Integer(addEvent.getStreetNum()).toString());
			addressResponse.setZipCode(new Integer(addEvent.getZipCode()).toString());

			dispatch.postEvent(addressResponse);
		}
		{
		}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}
