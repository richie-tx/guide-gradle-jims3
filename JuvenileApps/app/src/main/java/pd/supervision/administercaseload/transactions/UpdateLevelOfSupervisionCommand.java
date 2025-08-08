package pd.supervision.administercaseload.transactions;

import messaging.administercaseload.UpdateLevelOfSupervisionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administersupervisee.SuperviseeHelper;

public class UpdateLevelOfSupervisionCommand implements ICommand {

	/**
	 * @roseuid 464360400282
	 */
	public UpdateLevelOfSupervisionCommand() {
	}

	/**
	 * @param event
	 * @roseuid 46433E1A0259
	 */
	public void execute(IEvent anEvent) {
		UpdateLevelOfSupervisionEvent event = (UpdateLevelOfSupervisionEvent) anEvent;
		SuperviseeHelper.updateSupervisionLevel(event);
	}
	


	/**
	 * @param event
	 * @roseuid 46433E1A0266
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 46433E1A0268
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 46433E1A026A
	 */
	public void update(Object anObject) {

	}

}
