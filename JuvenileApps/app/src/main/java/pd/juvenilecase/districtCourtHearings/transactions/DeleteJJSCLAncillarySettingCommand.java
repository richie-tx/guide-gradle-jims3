package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.DeleteJJSCLAncillarySettingEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;

/**
 * @author sthyagarajan
 */
public class DeleteJJSCLAncillarySettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	boolean isSettingDeleted = false;
	DocketEventResponseEvent docketResp = new DocketEventResponseEvent();

	//delete by docketevent id.
	DeleteJJSCLAncillarySettingEvent evt = (DeleteJJSCLAncillarySettingEvent) event;
	
	Iterator<JJSCLAncillary> ancillaryItr = new ArrayList<JJSCLAncillary>().iterator(); // empty   iterator
	ancillaryItr = JJSCLAncillary.findAll("OID",evt.getDocketEventId()); // regular find.);

	if (ancillaryItr != null)
	{
	    if(ancillaryItr.hasNext())
	    {
		try
		{
		    JJSCLAncillary ancillary = ancillaryItr.next();
		    docketResp.setCreateDate(new Date(ancillary.getCreateTimestamp().getTime()));
		    ancillary.delete();
		    new Home().bind(ancillary);
		    isSettingDeleted = true;
		}
		catch (Exception e)
		{
		    isSettingDeleted = false;
		    e.printStackTrace();
		}
	    }
	}
	docketResp.setDeleteFlag(isSettingDeleted ? "true" : "false");
	dispatch.postEvent(docketResp);

    }
}
