package pd.juvenilecase.districtCourtHearings.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.DeleteJJSCLCourtSettingEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumAndHearingDateEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumResetDateChainNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCourtHearingControllerServiceNames;
import pd.juvenilecase.JJSCourt;

/**
 * 
 * @author sthyagarajan
 *
 */
public class DeleteJJSCLCourtSettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	boolean isSettingDeleted = false;
	DeleteJJSCLCourtSettingEvent evt = (DeleteJJSCLCourtSettingEvent) event;

	DocketEventResponseEvent docketResp = new DocketEventResponseEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	
	Iterator<JJSCourt> courtItr = new ArrayList<JJSCourt>().iterator(); // empty   iterator
	courtItr = JJSCourt.findAll("OID",evt.getDocketEventId());

	//delete by docketevent id.
	if (courtItr != null)
	{
	    if (courtItr.hasNext())
	    {
		JJSCourt court = courtItr.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String courtDate="";
		int seqotoFind=0;
		if(court.getCourtDate()!=null)
		  courtDate = sdf.format(court.getCourtDate());			
		String juvNo=court.getJuvenileNumber();
		String chainNum=court.getChainNumber();
		String transferTo=court.getCourtId();
		if(court.getSeqNumber()!=null)
		    seqotoFind=Integer.parseInt(court.getSeqNumber())-10; 
		docketResp.setCreateDate(new Date(court.getCreateTimestamp().getTime()));
		try
		{
		    court.delete();
		    new Home().bind(court);
		    isSettingDeleted = true;
		    //fetch record with juvnum,chainnum and resetdate as courtDate if found and transferto <> null null it
		    GetJJSCLCourtByJuvNumResetDateChainNumEvent jjsdistCrtEvent =new GetJJSCLCourtByJuvNumResetDateChainNumEvent();
		    jjsdistCrtEvent.setJuvenileNumber(juvNo);	    
		    jjsdistCrtEvent.setResetDate(courtDate);
		    jjsdistCrtEvent.setChainNumber(chainNum);
		    jjsdistCrtEvent.setSeqNumber(String.valueOf(seqotoFind));
		    Iterator<JJSCourt> crtItr = JJSCourt.findAll(jjsdistCrtEvent);
		    while (crtItr.hasNext())
		    {
			JJSCourt crt = (JJSCourt) crtItr.next();
			if (crt.getTransferTo()!=null && crt.getTransferTo().equalsIgnoreCase(transferTo))
			{
			    crt.setTransferTo(null);			    
			}
			crt.setResetDate(null);			
			crt.setResetHearingType(crt.getHearingType());
			new Home().bind(crt);
		    }		    
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		    isSettingDeleted = false;
		}
	    }
	}
	docketResp.setDeleteFlag(isSettingDeleted ? "true" : "false");
	dispatch.postEvent(docketResp);
    }
}