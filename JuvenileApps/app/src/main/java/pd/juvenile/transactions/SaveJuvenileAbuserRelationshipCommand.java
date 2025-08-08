package pd.juvenile.transactions;
import pd.juvenile.JuvenileAbusePerpatrator;
import pd.juvenile.JuvenileAbuserRelationship;
import messaging.juvenile.SaveJuvenileAbusePerpEvent;
import messaging.juvenile.SaveJuvenileAbuserRelationshipEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenileAbuserRelationshipCommand implements ICommand {

	/**
	 * @roseuid 42BC4D3B003A
	 */
	public SaveJuvenileAbuserRelationshipCommand()
	{

	}
	
	@Override
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
	    SaveJuvenileAbuserRelationshipEvent saveEvent = (SaveJuvenileAbuserRelationshipEvent) event;
	    JuvenileAbuserRelationship abuseReln = new JuvenileAbuserRelationship();
	    abuseReln.hydrate(saveEvent);
	}

}
