package pd.juvenile.transactions;

import messaging.juvenile.SaveJuvenilePurgeEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenile.JuvenileCore;

/**
 * 
 * @author ryoung
 *
 */
public class SaveJuvenilePurgeCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	SaveJuvenilePurgeEvent evt = (SaveJuvenilePurgeEvent) event;
	JuvenileCore coreObj = JuvenileCore.findCore( evt.getJuvenileNum() );

	if ( coreObj != null )
	{
	    coreObj.setSealedDate(null);
	    coreObj.setRecType(evt.getRecType());
	    new Home().bind( coreObj );
	}
    }
}
