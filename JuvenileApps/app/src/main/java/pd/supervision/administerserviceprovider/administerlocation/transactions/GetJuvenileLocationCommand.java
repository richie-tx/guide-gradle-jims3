package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.address.PDAddressHelper;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class GetJuvenileLocationCommand implements ICommand {

	/**
	 * @roseuid 447357A50143
	 */
	public GetJuvenileLocationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 44734FE30388
	 */
	public void execute(IEvent event) {
		GetJuvenileLocationEvent locationEvent = (GetJuvenileLocationEvent) event;
		String locationId = locationEvent.getLocationId();
		if(locationId != null && !locationId.equals("")){
			Location location  = Location.find(locationEvent.getLocationId());		
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			LocationResponseEvent replyEvent = location.getValueObject();
			if (location.getAddress()!=null){
				AddressResponseEvent are = PDAddressHelper.getAddressResponseEvent(location.getAddress());
				replyEvent.setLocationAddress(are);
			}			
				Iterator juvLocUnitsIter = JuvLocationUnit.findAll("locationId", location.getLocationId());
				ArrayList juvLocUnits = new ArrayList();
				while (juvLocUnitsIter.hasNext()) {
					JuvLocationUnit juvLoc = (JuvLocationUnit) juvLocUnitsIter.next();
					juvLocUnits.add(juvLoc.getValueObject());
					replyEvent.setLocationUnits(juvLocUnits);
				}
			dispatch.postEvent(replyEvent);
		}
	}

	/**
	 * @param event
	 * @roseuid 44734FE30393
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 44734FE30395
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 44734FE30397
	 */
	public void update(Object anObject) {

	}
}