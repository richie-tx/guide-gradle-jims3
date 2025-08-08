//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\transactions\\ActivateInactivateLocationUnitCommand.java

package pd.supervision.administerserviceprovider.administerlocation.transactions;

import messaging.administerlocation.ActivateInactivateLocationUnitEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ActivateInactivateLocationUnitCommand implements ICommand {

	/**
	 * @roseuid 4664663D020E
	 */
	public ActivateInactivateLocationUnitCommand() {

	}

	/**
	 * If isInactivate field of Event is true then status will be set to
	 * Inactive or else it will be set to Active.
	 * 
	 * @param event
	 * @roseuid 46646205031D
	 */
	public void execute(IEvent event) {
		ActivateInactivateLocationUnitEvent locationUnitEvent = (ActivateInactivateLocationUnitEvent) event;
		String locationUnitId = locationUnitEvent.getJuvLocUnitId();
		JuvLocationUnit locationUnit = JuvLocationUnit.find(locationUnitId);
		if (locationUnitEvent.isInactivate()) {
			locationUnit.setUnitStatusId("I");
		} else {
			locationUnit.setUnitStatusId("A");
		}
	}

	/**
	 * @param event
	 * @roseuid 46646205032B
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 46646205032D
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 46646205033B
	 */
	public void update(Object anObject) {

	}

}
