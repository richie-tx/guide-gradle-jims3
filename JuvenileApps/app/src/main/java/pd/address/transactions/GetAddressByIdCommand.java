/*
 * Created on Oct 5, 2006
 *
 */
package pd.address.transactions;

import pd.address.Address;
import messaging.address.GetAddressByIdEvent;
import messaging.address.reply.AddressResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 */
public class GetAddressByIdCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetAddressByIdEvent reqEvent = (GetAddressByIdEvent)event;
		Address address = Address.find(new Integer(reqEvent.getAddressId()).intValue());
		AddressResponseEvent respEvent = new AddressResponseEvent(); 
		respEvent.setAddressTypeId(address.getAddressTypeId());
		respEvent.setStreetNum(address.getStreetNum());
		respEvent.setStreetNumSuffixId(address.getStreetNumSuffixId());
		respEvent.setStreetName(address.getStreetName());
		respEvent.setStreetTypeId(address.getStreetTypeId());
		respEvent.setAptNum(address.getAptNum());
		respEvent.setCity(address.getCity());
		respEvent.setStateId(address.getStateId());
		respEvent.setZipCode(address.getZipCode());
		respEvent.setAdditionalZipCode(address.getAdditionalZipCode());
		respEvent.setValidated(address.getValidated());
		respEvent.setAddressStatus(address.getValidated());
		respEvent.setCountyId(address.getCountyId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(respEvent);	 
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}
}
