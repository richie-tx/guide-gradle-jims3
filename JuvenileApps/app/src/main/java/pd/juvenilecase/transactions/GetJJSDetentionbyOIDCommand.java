package pd.juvenilecase.transactions;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.GetJJSDetentionbyOIDEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

/**
 * 
 * GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
 *
 */
public class GetJJSDetentionbyOIDCommand implements ICommand{

    @Override
    public void execute(IEvent event) throws Exception {
	GetJJSDetentionbyOIDEvent crtevt =(GetJJSDetentionbyOIDEvent)event;
	JJSCLDetention court = JJSCLDetention.find(crtevt.getId());
	DocketEventResponseEvent courtResponseEvent = new DocketEventResponseEvent();//PDOfficerProfileHelper.getOfficerProfileResponseEvent(court);
	courtResponseEvent.setAttorneyName(court.getAttorneyName());
	courtResponseEvent.setAttorneyConnection(court.getAttorneyConnection());
	courtResponseEvent.setBarNum(court.getBarNumber());
	//add other attributes to response as and when required
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	dispatch.postEvent(courtResponseEvent);

    }
}
