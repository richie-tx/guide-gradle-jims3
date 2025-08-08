package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.supervision.administerserviceprovider.administerlocation.Location;
import messaging.administerlocation.GetLocationByLocationIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetLocationByLocationIdCommand implements ICommand
{

	public void execute(IEvent event) 
	{
		GetLocationByLocationIdEvent reqEvent = (GetLocationByLocationIdEvent) event;
		List locList = Location.findAll(reqEvent);	
		Location location = null;
		LocationResponseEvent replyEvent = null;
		List aResponses = new ArrayList();
		
		for (int i = 0; i < locList.size(); i++)
		{
			location = (Location) locList.get(i);
			replyEvent = new LocationResponseEvent();
			replyEvent.setAgencyId(location.getAgencyId());
			replyEvent.setAptNumber(location.getAptNumber());
			replyEvent.setCity(location.getCity());
			replyEvent.setLocationId(location.getLocationId());
			replyEvent.setLocationName(location.getLocationName());
			replyEvent.setPhoneNumber(location.getPhoneNumber());
			replyEvent.setLocationFax(location.getLocationFax());
			replyEvent.setStateId(location.getStateId());
			replyEvent.setStreetName(location.getStreetName());
			replyEvent.setStreetNum(location.getStreetNum());
			replyEvent.setZipCode(location.getZipCode());
			aResponses.add(replyEvent);
		}
		MessageUtil.postReplies(aResponses);
	}

	public void onRegister(IEvent event)
	{
		

	}

	public void onUnregister(IEvent event)
	{
		

	}

	public void update(Object updateObject)
	{
		

	}

}
