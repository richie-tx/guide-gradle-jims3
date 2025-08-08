package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.DeleteJJSCLDetentionSettingEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class DeleteJJSCLDetentionSettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	boolean isSettingDeleted = false;
	DocketEventResponseEvent docketResp = new DocketEventResponseEvent();

	//delete by docketevent id.
	DeleteJJSCLDetentionSettingEvent evt = (DeleteJJSCLDetentionSettingEvent) event;

	Iterator<JJSCLDetention> detentionItr = new ArrayList<JJSCLDetention>().iterator(); // empty   iterator
	// by OID
	if (evt.getDocketEventId() != null && !evt.getDocketEventId().isEmpty() && evt.getChainNumber() == null)
	{
	    detentionItr = JJSCLDetention.findAll("OID", evt.getDocketEventId()); // regular find.);
	    if (detentionItr != null)
	    {
		if (detentionItr.hasNext())
		{
		    JJSCLDetention det = detentionItr.next();
		    docketResp.setCreateDate(new Date(det.getCreateTimestamp().getTime()));
		    try
		    {
			det.delete();
			new Home().bind(det);
			isSettingDeleted = true;
		    }
		    catch (Exception e)
		    {
			isSettingDeleted = false;
			e.printStackTrace();
		    }
		}
	    }
	}
	else if (evt.getChainNumber() != null && !evt.getChainNumber().isEmpty())
	{
	    /**
	     * 4.9.14 Activity: Delete Released juvenile�s future settings If
	     * the associated *Action = Released for a Result, delete all future
	     * Detention setting records in the chain of the setting. See
	     * ....\Detention Court Decisions CORRECTED09_28_18.docx
	     */
	    detentionItr = JJSCLDetention.findAll("chainNumber", evt.getChainNumber()); // by chainnumber
	    if (detentionItr != null)
	    {
		while (detentionItr.hasNext())
		{
		    try
		    {
			JJSCLDetention det = detentionItr.next();
			//delete all future Detention setting records 
			if (det.getCourtDate().after(DateUtil.getCurrentDate()))
			{
			    det.delete();
			    new Home().bind(det);
			    isSettingDeleted = true;
			}
		    }
		    catch (Exception e)
		    {
			isSettingDeleted = false;
			e.printStackTrace();
		    }
		}
	    }
	}
	
	docketResp.setDeleteFlag(isSettingDeleted ? "true" : "false");
	dispatch.postEvent(docketResp);
    }
}
