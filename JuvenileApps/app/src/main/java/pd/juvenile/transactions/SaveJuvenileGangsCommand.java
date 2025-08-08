package pd.juvenile.transactions;

import java.util.Enumeration;

import messaging.juvenile.JuvenileGangRequestEvent;
import messaging.juvenile.SaveJuvenileGangsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileGangs;
/**
 * 
 * @author sthyagarajan
 * SaveJuvenileGangsCommand
 */
public class SaveJuvenileGangsCommand implements ICommand {

	/**
	 * Default constructor.
	 */
	public SaveJuvenileGangsCommand() {

	}

	public void execute(IEvent event) {
		SaveJuvenileGangsEvent reqEvent = (SaveJuvenileGangsEvent) event;
		// this is a composite event
		Enumeration enumeration = reqEvent.getRequests();
		if (enumeration != null) {
			while (enumeration.hasMoreElements()) {
				JuvenileGangRequestEvent gangEvent = (JuvenileGangRequestEvent) enumeration
						.nextElement();
				JuvenileGangs.create(gangEvent);
			}
		}

	}
}
