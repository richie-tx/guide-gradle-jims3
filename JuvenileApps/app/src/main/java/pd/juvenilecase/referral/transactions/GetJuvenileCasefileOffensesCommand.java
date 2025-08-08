//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileOffensesCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.referral.JJSOffense;

/**
 * @author glyons To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileCasefileOffensesCommand implements ICommand
{

    /**
     * @roseuid 42A9A3020387
     */
    public GetJuvenileCasefileOffensesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42A99B980159
     */
    public void execute(IEvent event)
    {
	GetJuvenileCasefileOffensesEvent off = (GetJuvenileCasefileOffensesEvent) event;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator i = JJSOffense.findAll(off);
	
	

	while (i.hasNext())
	{
	    JJSOffense o = (JJSOffense) i.next();
	    
	    JJSOffenseResponseEvent resp = o.valueObject();
	    //ncic code
	    JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",o.getOffenseCodeId());
	    if (offenseCode != null) 
	    {
			if (offenseCode.getNcicCode() != null) 
			    resp.setNcicCode(offenseCode.getNcicCode());
			else
			    resp.setNcicCode(null);
	    }
	  
	    dispatch.postEvent(resp);
	}
    }

    /**
     * @param event
     * @roseuid 42A99B980162
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42A99B98016B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42A99B98016D
     */
    public void update(Object anObject)
    {

    }
}
