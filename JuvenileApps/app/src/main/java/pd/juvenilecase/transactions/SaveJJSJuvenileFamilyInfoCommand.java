package pd.juvenilecase.transactions;


import java.util.Iterator;

import messaging.juvenilecase.SaveJJSJuvenileFamilyInfoEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSJuvenile;


/**
 * 
 * @author ryoung
 *
 */
public class SaveJJSJuvenileFamilyInfoCommand implements ICommand {

    @Override
    public void execute(IEvent event) throws Exception
    {
	SaveJJSJuvenileFamilyInfoEvent evt = (SaveJJSJuvenileFamilyInfoEvent)event;
	    // update Referral
	
	    Iterator<JJSJuvenile> iter = JJSJuvenile.findAll("juvenileNum", evt.getJuvenileNum());

	    if ( iter.hasNext() )
	    {
		JJSJuvenile juv = iter.next();	
		juv.setLiveWithTjjd( evt.getYouthLivesWithId());
		
	    }
        
    }

}
