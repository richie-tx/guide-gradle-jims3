//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\ValidateServiceProviderCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.ValidateLocationEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.Location;

public class ValidateLocationCommand implements ICommand {

	/**
	 * @roseuid 4473538E0385
	 */
	public ValidateLocationCommand() {

	}

	/**
	 * @param event
	 * @roseuid 446A2E440110
	 */
	public void execute(IEvent event) {
		ValidateLocationEvent validateEvent = (ValidateLocationEvent) event;
		String agencyId = validateEvent.getAgencyId();
		if (agencyId != null && !agencyId.equals("")) {
			Iterator iter = Location.findAll("agencyId", validateEvent.getAgencyId());
			String newLocationName = validateEvent.getLocationName().trim();
			String newLocationCd = validateEvent.getLocationCd().trim();
			while (iter.hasNext()) {
				Location location = (Location) iter.next();
				String existingLocationName = location.getLocationName().trim();
				String existingLocationCd = location.getLocationCd().trim();
				if (existingLocationName.equalsIgnoreCase(newLocationName)) {
					this.sendLocationErrorResponseEvent("error.duplicate.locationName");
					//return;
				}
				if (existingLocationCd.equalsIgnoreCase(newLocationCd)) {
					this.sendLocationErrorResponseEvent("error.duplicate.locationCd");
					//return;
				}
			}

		}

	}

	/**
	 * @param errorKey
	 */
	private void sendLocationErrorResponseEvent(String errorKey) {
		//		DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
		ErrorResponseEvent errorEvent = new ErrorResponseEvent();
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}

	/**
	 * @param event
	 * @roseuid 446A2E440112
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 446A2E44011F
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 446A2E440121
	 */
	public void update(Object anObject) {

	}
}