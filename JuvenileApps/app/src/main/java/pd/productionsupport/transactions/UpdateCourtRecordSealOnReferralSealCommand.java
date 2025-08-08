package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateProductionSupportReferralSealEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class UpdateCourtRecordSealOnReferralSealCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateCourtRecordSealOnReferralSealEvent updateEvent = (UpdateCourtRecordSealOnReferralSealEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCourt> objIter = new ArrayList<JJSCourt>().iterator(); // empty   iterator
	objIter = JJSCourt.findAll("OID",updateEvent.getDocketId());
	
	
	       //Iterator objIter = JJSReferral.findAll(updateEvent);      
	while (objIter.hasNext())
	{

	    JJSCourt crt = (JJSCourt) objIter.next();
	    if (crt != null && (crt.getRectype().equalsIgnoreCase("COURT")||crt.getRectype().equalsIgnoreCase("I.COURT") ||crt.getRectype().equalsIgnoreCase("S.COURT")))
	    {
		if(updateEvent.getAction().equalsIgnoreCase("seal"))
		{
		    crt.setRectype("S.COURT");	
		}
		else if(updateEvent.getAction().equalsIgnoreCase("purge")){
		    crt.setRectype("I.COURT"); 
		} else
		    crt.setRectype("COURT");
		
		new Home().bind(crt);
	    }
	    else
	    {

		//Post error back	    
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		if(updateEvent.getAction().equalsIgnoreCase("seal"))
		{
		 error.setMessage("COURT record not SEALED docketId: " + updateEvent.getDocketId());
		}
		else if(updateEvent.getAction().equalsIgnoreCase("purge"))
		    error.setMessage("COURT record not PURGED docketId: " + updateEvent.getDocketId());
		else
		    error.setMessage("COURT record not unpurged docketId: " + updateEvent.getDocketId());
		dispatch.postEvent(error);
	    }
	}

    }

}
