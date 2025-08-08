// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\ValidateLocationUnitDetailsCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import java.util.Iterator;

import messaging.administerlocation.ValidateLocationUnitDetailsEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateLocationUnitDetailsCommand implements ICommand {

	/**
	 * @roseuid 4664666000C7
	 */
	public ValidateLocationUnitDetailsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4664621D002F
	 */
	public void execute(IEvent event) {
		ValidateLocationUnitDetailsEvent validateEvent = (ValidateLocationUnitDetailsEvent) event;
		if (validateEvent.isUpdate()) {
			String locationUnitId = validateEvent.getLocationUnitId();
			JuvLocationUnit locationUnit = JuvLocationUnit.find(locationUnitId);
			String oldlocUnitName = locationUnit.getLocationUnitName();
			String newlocUnitName = validateEvent.getLocationUnitName();
			if(oldlocUnitName.equalsIgnoreCase(newlocUnitName)){
				//Since LocationUnitName has not been changed there is no need for validating.
			}
			else{
				Iterator iter = JuvLocationUnit.findAll("locationId", validateEvent.getLocationId());
				while (iter.hasNext()) {
					JuvLocationUnit juvLocationUnit = (JuvLocationUnit) iter.next();
					String existingLocUnitName = juvLocationUnit.getLocationUnitName().trim();
					if (existingLocUnitName.equalsIgnoreCase(newlocUnitName)) {
						this.sendLocUnitDetailsErrorResponseEvent("error.duplicate.locationUnitName");
						break;
					}
				}
			}
			
		}
		else {
			String newlocUnitCd = validateEvent.getJuvUnitCd();
			String newlocUnitName = validateEvent.getLocationUnitName();
			Iterator iter = JuvLocationUnit.findAll("locationId", validateEvent.getLocationId());
			while (iter.hasNext()) {
				JuvLocationUnit juvLocationUnit = (JuvLocationUnit) iter.next();
				String existingLocUnitName = juvLocationUnit.getLocationUnitName().trim();
				String existingLocUnitCd = juvLocationUnit.getJuvUnitCd().trim();
				if (existingLocUnitCd.equalsIgnoreCase(newlocUnitCd)) {
					this.sendLocUnitDetailsErrorResponseEvent("error.duplicate.locationUnitCd");
					break;
				}
				if (existingLocUnitName.equalsIgnoreCase(newlocUnitName)) {
					this.sendLocUnitDetailsErrorResponseEvent("error.duplicate.locationUnitName");
					break;
				}
			}
		}
	}

	/**
	 * @param errorKey
	 */
	private void sendLocUnitDetailsErrorResponseEvent(String errorKey) {
		ErrorResponseEvent errorEvent = new ErrorResponseEvent();
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}

	/**
	 * @param event
	 * @roseuid 4664621D0031
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4664621D003F
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4664621D0041
	 */
	public void update(Object anObject) {

	}

}
