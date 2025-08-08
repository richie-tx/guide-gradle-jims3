package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateDetentionCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateProductionSupportReferralSealEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class UpdateDetentionCourtRecordSealOnReferralSealCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateDetentionCourtRecordSealOnReferralSealEvent updateEvent = (UpdateDetentionCourtRecordSealOnReferralSealEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCLDetention> objIter = new ArrayList<JJSCLDetention>().iterator(); // empty   iterator
	objIter = JJSCLDetention.findAll("OID",updateEvent.getDocketId());
	
	
	       //Iterator objIter = JJSReferral.findAll(updateEvent);      
	while (objIter.hasNext())
	{

	    JJSCLDetention crt = (JJSCLDetention) objIter.next();
	    if (crt != null && (crt.getRecType().equalsIgnoreCase("DETENTION")||crt.getRecType().equalsIgnoreCase("I.DETENTION")||crt.getRecType().equalsIgnoreCase("S.DETENTION")))
	    {
		if(updateEvent.getAction().equalsIgnoreCase("seal"))
		{
		    crt.setRecType("S.DETENTION");	
		}
		else if(updateEvent.getAction().equalsIgnoreCase("purge"))
		    crt.setRecType("I.DETENTION");
		else
		    crt.setRecType("DETENTION"); //can use same for Unseal
		new Home().bind(crt);
	    }
	    else
	    {

		//Post error back	    
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		if(updateEvent.getAction().equalsIgnoreCase("seal"))
		{
		    error.setMessage("Detention COURT record not SEALED docketId: " + updateEvent.getDocketId());
		}
		else if(updateEvent.getAction().equalsIgnoreCase("purge"))
		    error.setMessage("Detention COURT record not PURGED docketId: " + updateEvent.getDocketId());
		else if(updateEvent.getAction().equalsIgnoreCase("unSealJuv"))
		    error.setMessage("Detention COURT record not UNSEALED docketId: " + updateEvent.getDocketId());
		else
		    error.setMessage("Detention COURT record not unpurged docketId: " + updateEvent.getDocketId());
		dispatch.postEvent(error);
	    }
	}

    }

}
