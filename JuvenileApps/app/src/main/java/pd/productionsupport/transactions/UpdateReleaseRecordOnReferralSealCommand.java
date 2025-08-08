package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.GetJuvenileDetentionFacilitiesEvent;
import messaging.productionsupport.UpdateReleaseRecordOnReferralSealEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.JJSFacility;

public class UpdateReleaseRecordOnReferralSealCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateReleaseRecordOnReferralSealEvent updateEvent = (UpdateReleaseRecordOnReferralSealEvent) event;
	//UpdateReleaseRecordOnReferralSealEvent releaseEvent = (UpdateReleaseRecordOnReferralSealEvent) event;
	//GetJuvenileDetentionFacilitiesEvent evt=(GetJuvenileDetentionFacilitiesEvent) event;
	//evt.setJuvenileNum(updateEvent.getJuvNum());
	//evt.setReferralNum(updateEvent.getRefNum());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//updateEvent.setJuvNum(juvNum);
	Iterator<JJSFacility> objIter = new ArrayList<JJSFacility>().iterator(); // empty   iterator
	//objIter = JJSFacility.findAll("OID",updateEvent.getOID());
	objIter = JJSFacility.findAll(updateEvent);
	
	
	       //Iterator objIter = JJSReferral.findAll(updateEvent);      
	while (objIter.hasNext())
	{

	    JJSFacility det = (JJSFacility) objIter.next();
	    if (det != null && det.getReleaseDate()!=null && det.getReleaseTo().equalsIgnoreCase("NTS"))
	    {
		det.setDetentionStatus("N");		
		new Home().bind(det);
	    }
	    else if (det != null && det.getReleaseDate()!=null && !det.getReleaseTo().equalsIgnoreCase("NTS"))
	    {
		det.setDetentionStatus("");		
		new Home().bind(det);
	    }
	    else
	    {

		//Post error back	    
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		error.setMessage("Release setails not updated: " + updateEvent.getOID());
		dispatch.postEvent(error);
	    }
	}

    }

}
