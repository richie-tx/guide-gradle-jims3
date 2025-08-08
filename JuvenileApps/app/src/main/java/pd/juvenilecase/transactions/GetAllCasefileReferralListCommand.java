package pd.juvenilecase.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.GetAllCasefileReferralListEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefileReferral;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;

/**
 * 
 * 
 * 
 */
public class GetAllCasefileReferralListCommand implements ICommand
{

    /**
     * @roseuid 42791F9003A9
     */
    public GetAllCasefileReferralListCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42791D5702FF
     */
    public void execute(IEvent event)
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetAllCasefileReferralListEvent searchEvent = (GetAllCasefileReferralListEvent) event;
		
	Iterator<JuvenileCasefileReferral> iter = JuvenileCasefileReferral.findAll(searchEvent);
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(searchEvent.getJuvenileNum());
	while (iter.hasNext())
	{
	    JuvenileCasefileReferral caseRef = (JuvenileCasefileReferral) iter.next();
	    JuvenileProfileReferralListResponseEvent resp = caseRef.getValueObjectExt();
	    if (transferredOffenses !=null
			   && transferredOffenses.size() > 0 ) {
			   if ( "TRNDSP".equals(resp.getOffense())
				   || "TRNSIN".equals(resp.getOffense())
				   || "REGION".equals(resp.getOffense())
			           || "ISCOIN".equals(resp.getOffense())
			       ){
			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
				   if ( resp.getReferralNumber().equals(transferredOffense.getReferralNum()) ) {
				       resp.setOffenseDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
				   }
			       }
			  
			   }
	    }
	    dispatch.postEvent(resp);
	}
    }

    /**
     * @param event
     * @roseuid 42791D570301
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42791D570303
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42791D57030E
     */
    public void update(Object anObject)
    {

    }

}
