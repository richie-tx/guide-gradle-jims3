package pd.juvenile.transactions;
import messaging.juvenile.SaveJuvenileDSPlacementsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileDualStatusPlacement;

public class SaveJuvenileDSPlacementsCommand implements ICommand {

	/**
	 * @roseuid 42BC4D3B003A
	 */
	public SaveJuvenileDSPlacementsCommand()
	{

	}
	
	@Override
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
	    SaveJuvenileDSPlacementsEvent saveEvent = (SaveJuvenileDSPlacementsEvent) event;
	    JuvenileDualStatusPlacement dualPlacement = new JuvenileDualStatusPlacement();
	    dualPlacement.hydrate(saveEvent);
	}

}
