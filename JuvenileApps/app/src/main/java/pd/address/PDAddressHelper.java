/*
 * Created on Oct 21, 2004
 */
package pd.address;

import java.util.Collection;
import java.util.Iterator;
import pd.codetable.Code;
import naming.PDAddressConstants;
import messaging.address.AddressRequestEvent;
import messaging.address.reply.AddressResponseEvent;
import messaging.contact.domintf.IAddress;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;

/**
 * @author dgibler
 */
public final class PDAddressHelper
{
	/**
	 *
	 */
	private PDAddressHelper()
	{
	}

	/**
	 * 
	 * @param address
	 * @return AdcdressResponseEvent
	 */
	public static AddressResponseEvent getAddressResponseEvent(Address address)
	{
		AddressResponseEvent are = null;
		if (address != null)
		{
			are = new AddressResponseEvent();
			
			are.setTopic(PDAddressConstants.ADDRESS_EVENT_TOPIC);
			
			address.fillAddress(are);
			Code code = (Code) address.getAddressType();
			if (code != null)
			{
				are.setAddressType(code.getDescription());
			}

			code = (Code) address.getState();
			if (code != null)
			{
				are.setState(code.getDescription());
			}

			//<KISHORE> JIMS200057156 : MJCW-Briefing, County does not display.
			code = (Code) address.getCounty();
			if (code != null)
			{
				are.setCounty(code.getDescription());
			}

			code = (Code) address.getStreetType();
			if (code != null)
			{
				are.setStreetType(code.getDescription());
			}
			
			code = (Code) address.getStreetNumSuffix();
			if (code != null)
			{
				are.setStreetNumSuffix(code.getDescription());
			}
			
			if (address.getAddressStatus() == null)
			{
				// TODO Refactor with Address (U)NPROCESSED constant
				are.setAddressStatus("U");
			}
			else
			{
				are.setAddressStatus(address.getAddressStatus());
			}
			
			are.setValidated(address.getValidated());
		}		

		return are;
	}
	
	/**
		 * 
		 * @param address
		 * @return AdcdressResponseEvent
		 */
		public static AddressResponseEvent getDepartmentAddressResponseEvent(Address address)
		{
			AddressResponseEvent are = null;
			if (address != null)
			{
				are = new AddressResponseEvent();
			
				are.setTopic(PDAddressConstants.ADDRESS_EVENT_TOPIC);
			
				address.fillAddress(are);
				Code code = (Code) address.getDepartmentAddressType();
				if (code != null)
				{
					are.setAddressType(code.getDescription());
				}

				code = (Code) address.getState();
				if (code != null)
				{
					are.setState(code.getDescription());
				}

				code = (Code) address.getStreetType();
				if (code != null)
				{
					are.setStreetType(code.getDescription());
				}

				if (address.getAddressStatus() == null)
				{
					// TODO Refactor with Address (U)NPROCESSED constant
					are.setAddressStatus("U");
				}
				else
				{
					are.setAddressStatus(address.getAddressStatus());
				}
			}		
 
			return are;
		}

	/**
	 * 
	 * @param col
	 */
	public static void postAddresses(Collection col)
	{
		if (col != null)
		{
			Iterator addresses = col.iterator();
			while (addresses.hasNext())
			{
				Address address = (Address) addresses.next();
				postAddress(address);
			}
		}
	}

	/**
	 * 
	 * @param address
	 */
	public static void postAddress(Address address)
	{
		AddressResponseEvent event = PDAddressHelper.getDepartmentAddressResponseEvent(address);
		if (event != null)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(event);
		}
	}

	public static void postAddress(Address address, String topic)
	{
		if (address != null)
		{
			String addressType = address.getAddressTypeId();
			AddressResponseEvent event = null;
			if(addressType.length()==1){
				event = PDAddressHelper.getDepartmentAddressResponseEvent(address);
			}else{
				event = PDAddressHelper.getAddressResponseEvent(address);
			}
			
			event.setTopic(topic);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(event);
		}
	}

	/**
	 * @param addressRequestEvent
	 * @param address
	 * @return Address
	 */
	public static Address getAddress(AddressRequestEvent addressRequestEvent, Address address)
	{
		if (addressRequestEvent == null)
		{
			return null;
		}
		address.setStateId(addressRequestEvent.getStateId());
		address.setStreetNum(addressRequestEvent.getStreetNum());
		address.setStreetName(addressRequestEvent.getStreetName());
		address.setCity(addressRequestEvent.getCity());
		address.setAddress2(addressRequestEvent.getAddress2());
		address.setAddressTypeId(addressRequestEvent.getAddressTypeId());
		address.setZipCode(addressRequestEvent.getZipCode());
		address.setAdditionalZipCode(addressRequestEvent.getAdditionalZipCode());
		address.setAddressId(addressRequestEvent.getAddressId());
		address.setAptNum(addressRequestEvent.getAptNum());
		address.setStreetTypeId(addressRequestEvent.getStreetTypeId());
		address.setValidated(addressRequestEvent.getValidated());
		return address;
	}

	/**
	 * Convert object implementing IAddress to an Address object
	 * @param addressInfo
	 * @return
	 */
	public static Address getAddress(IAddress addressInfo)
	{
	    Address address = new Address();
	    
		address.setStreetNum(addressInfo.getStreetNum());
		address.setStreetName(addressInfo.getStreetName());
		address.setStreetTypeId(addressInfo.getStreetTypeCode());
		address.setAptNum(addressInfo.getAptNum());
		address.setCity(addressInfo.getCity());
		address.setStateId(addressInfo.getStateCode());
		address.setZipCode(addressInfo.getZipCode());
		address.setAdditionalZipCode(addressInfo.getAdditionalZipCode());
		address.setAddressTypeId(addressInfo.getAddressTypeCode());
		address.setCountyId(addressInfo.getCountyCode());
		
		address.setAddress2(addressInfo.getAddress2());
		address.setAddressStatus(addressInfo.getAddressStatus());
		address.setCountryId(addressInfo.getCountryCode());
		address.setValidated(addressInfo.getValidated());

		return address;
	}//end of getAddress()

	/**
	 * Convert PD Address object to object implementing IAddress
	 * @param addressInfo
	 * @return
	 */	
	public static IAddress getAddress(Address pdAddress)
	{
	    messaging.contact.to.Address address = new messaging.contact.to.Address();
	    
	    if (pdAddress != null)
	    {
			address.setStreetNum(pdAddress.getStreetNum());
			address.setStreetName(pdAddress.getStreetName());
			address.setStreetTypeCode(pdAddress.getStreetTypeId());
			address.setAptNum(pdAddress.getAptNum());
			address.setCity(pdAddress.getCity());
			address.setStateCode(pdAddress.getStateId());
			address.setZipCode(pdAddress.getZipCode());
			address.setAdditionalZipCode(pdAddress.getAdditionalZipCode());
			address.setAddressTypeCode(pdAddress.getAddressTypeId());
			address.setCountyCode(pdAddress.getCountyId());
			
			address.setAddress2(pdAddress.getAddress2());
			address.setAddressStatus(pdAddress.getAddressStatus());
			address.setCountryCode(pdAddress.getCountryId());
			address.setValidated(pdAddress.getValidated());
	    }
		return address;
	}//end of getAddress()

}