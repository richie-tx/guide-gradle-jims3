package pd.juvenile.transactions;
import pd.juvenile.JuvenileAbusePerpatrator;
import messaging.juvenile.SaveJuvenileAbusePerpEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenileAbusePerpCommand implements ICommand {

	/**
	 * @roseuid 42BC4D3B003A
	 */
	public SaveJuvenileAbusePerpCommand()
	{

	}
	
	@Override
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
		SaveJuvenileAbusePerpEvent saveEvent = (SaveJuvenileAbusePerpEvent) event;
		JuvenileAbusePerpatrator abusePrep = new JuvenileAbusePerpatrator();
		abusePrep.hydrate(saveEvent);
	}

}
