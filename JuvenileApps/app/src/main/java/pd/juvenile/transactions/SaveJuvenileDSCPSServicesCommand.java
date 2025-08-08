package pd.juvenile.transactions;
import messaging.juvenile.SaveJuvenileDSCPSServicesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileDualStatusCPSServices;

public class SaveJuvenileDSCPSServicesCommand implements ICommand {

	/**
	 * @roseuid 42BC4D3B003A
	 */
	public SaveJuvenileDSCPSServicesCommand()
	{

	}
	
	@Override
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
	    SaveJuvenileDSCPSServicesEvent saveEvent = (SaveJuvenileDSCPSServicesEvent) event;
	    JuvenileDualStatusCPSServices dualcpsService = new JuvenileDualStatusCPSServices();
	    dualcpsService.hydrate(saveEvent);
	}

}
