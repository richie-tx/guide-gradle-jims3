package pd.juvenilewarrant.transactions;

import java.util.List;

import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import messaging.juvenilewarrant.JuvenileAssociateAddressRequestEvent;
import messaging.juvenilewarrant.UpdateJuvenileAssociateEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeRequest;

import mojo.km.utilities.MessageUtil;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateJuvenileAssociateCommand implements ICommand
{

	/**
	 * @roseuid 41E6C8D3008C
	 */
	public UpdateJuvenileAssociateCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 41E59AED030F
	 */
	public void execute(IEvent event)
	{
		UpdateJuvenileAssociateEvent updateEvent = (UpdateJuvenileAssociateEvent) event;

		JuvenileAssociate juvenileAssociate = JuvenileAssociate.find(updateEvent.getAssociateNumber());

		if (juvenileAssociate != null)
		{
			this.setJuvenileAssociateFields(updateEvent, juvenileAssociate);
			this.updateJuvenileAssociateAddress(updateEvent, juvenileAssociate);
		}
	}

	private void setJuvenileAssociateFields(UpdateJuvenileAssociateEvent updateEvent, JuvenileAssociate juvAssociate)
	{
		juvAssociate.setLastName(updateEvent.getAssociateLastName());
		juvAssociate.setFirstName(updateEvent.getAssociateFirstName());
		juvAssociate.setMiddleName(updateEvent.getAssociateMiddleName());
		juvAssociate.setCellPhone(updateEvent.getCellPhone());
		juvAssociate.setSsn(updateEvent.getAssociateSsn());
		juvAssociate.setDateOfBirth(updateEvent.getAssociateDateOfBirth());
		juvAssociate.setHomePhone(updateEvent.getHomePhone());
		juvAssociate.setWorkPhone(updateEvent.getWorkPhone());
		juvAssociate.setFaxLocation(updateEvent.getFaxLocation());
		juvAssociate.setFaxNumber(updateEvent.getFax());
		juvAssociate.setPager(updateEvent.getPager());
		juvAssociate.setEmail1(updateEvent.getEmail1());
		juvAssociate.setEmail2(updateEvent.getEmail2());
		juvAssociate.setEmail3(updateEvent.getEmail3());
		juvAssociate.setDlNumber(updateEvent.getDlNumber());
		//Set codes for associate
		juvAssociate.setRaceId(updateEvent.getAssociateRace());
		juvAssociate.setRelationshipToJuvenileId(updateEvent.getRelationshipToJuvenile());
		juvAssociate.setSexId(updateEvent.getAssociateSex());
		juvAssociate.setDlStateId(updateEvent.getDlState());
	}

	private void updateJuvenileAssociateAddress(CompositeRequest updateEvent, JuvenileAssociate juvenileAssociate)
	{
		List addresses =
			MessageUtil.compositeToList(updateEvent, JuvenileAssociateAddressRequestEvent.class);

		if (addresses != null)
		{
			int len = addresses.size();
			for(int i=0;i<len;i++)
			{
				JuvenileAssociateAddressRequestEvent thisEvent = (JuvenileAssociateAddressRequestEvent) addresses.get(i);

				String addressNum = thisEvent.getAddressId();

				//get associate number from juvenileAssociate
				JuvenileAssociateAddress address = null;

				if (addressNum != null)
				{
					address = JuvenileAssociateAddress.find(addressNum);
				}

				if (address != null)
				{
					address.setAssociateNum(thisEvent.getAssociateNum());
					address.setAddressTypeId(thisEvent.getAddressTypeId());
					address.setAptNum(thisEvent.getAptNum());
					address.setCity(thisEvent.getCity());
					address.setCountryId(thisEvent.getCountry());
					address.setCountyId(thisEvent.getCountyId());
					address.setKeymap(thisEvent.getKeymap());

					if (thisEvent.getLatitude() != null && !thisEvent.getLatitude().equals(""))
					{
						address.setLatitude(Double.parseDouble(thisEvent.getLatitude()));
					}

					if (thisEvent.getLongitude() != null && !thisEvent.getLongitude().equals(""))
					{
						address.setLongitude(Double.parseDouble(thisEvent.getLongitude()));
					}

					address.setStateId(thisEvent.getStateId());
					address.setStreetName(thisEvent.getStreetName());
					address.setStreetNum(thisEvent.getStreetNum());
					address.setStreetTypeId(thisEvent.getStreetTypeId());
					address.setZipCode(thisEvent.getZipCode());
					address.setAptNum(thisEvent.getAptNum());
					address.setAdditionalZipCode(thisEvent.getAdditionalZipCode());
					address.setAddressStatus(thisEvent.getAddressStatus());
				}
				else
				{
					// if adding a new address 
					address = new JuvenileAssociateAddress();
					address.setAssociateNum(thisEvent.getAssociateNum());
					address.setAddressTypeId(thisEvent.getAddressTypeId());
					address.setCity(thisEvent.getCity());
					address.setCountryId(thisEvent.getCountry());
					address.setCountyId(thisEvent.getCountyId());
					address.setKeymap(thisEvent.getKeymap());

					if (thisEvent.getLatitude() != null && !thisEvent.getLatitude().equals(""))
					{
						address.setLatitude(Double.parseDouble(thisEvent.getLatitude()));
					}

					if (thisEvent.getLongitude() != null && !thisEvent.getLongitude().equals(""))
					{
						address.setLongitude(Double.parseDouble(thisEvent.getLongitude()));
					}

					address.setStateId(thisEvent.getStateId());
					address.setStreetName(thisEvent.getStreetName());
					address.setStreetNum(thisEvent.getStreetNum());
					address.setStreetTypeId(thisEvent.getStreetTypeId());
					address.setZipCode(thisEvent.getZipCode());
					address.setAptNum(thisEvent.getAptNum());
					address.setAdditionalZipCode(thisEvent.getAdditionalZipCode());

					juvenileAssociate.insertAddresses(address);

				}
			}

		}
	}

	/**
		 * @param event
		 * @roseuid 41E59AED0311
		 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41E59AED031C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 41E6C8D3009C
	 */
	public void update(Object updateObject)
	{

	}
}
