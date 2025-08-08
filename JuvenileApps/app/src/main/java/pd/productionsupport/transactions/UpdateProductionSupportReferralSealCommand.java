package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateProductionSupportReferralSealEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class UpdateProductionSupportReferralSealCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateProductionSupportReferralSealEvent updateEvent = (UpdateProductionSupportReferralSealEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSReferral> objIter = new ArrayList<JJSReferral>().iterator(); // empty   iterator
	objIter = JJSReferral.findAll("OID",updateEvent.getOID());
	
	
	       //Iterator objIter = JJSReferral.findAll(updateEvent);      
	while (objIter.hasNext())
	{

	    JJSReferral referral = (JJSReferral) objIter.next();
	    if (referral != null && (referral.getRecType().equalsIgnoreCase("REFERRAL")||(referral.getRecType().equalsIgnoreCase("I.REFERRAL") && updateEvent.getAction()!= null && !updateEvent.getAction().equals("unSealJuv")) ))
	    {
		referral.setRecType("S.REFERRAL");
		referral.setSealedComments(updateEvent.getSealedComments());
		referral.setSealedDate(DateUtil.stringToDate( updateEvent.getSealedDate(), DateUtil.DATE_FMT_1 ));
		referral.setLcDate(DateUtil.getCurrentDate());
		referral.setLcTime(Calendar.getInstance().getTime());
		referral.setLcUser(PDSecurityHelper.getLogonId());
		new Home().bind(referral);
	    }
	    else
		if (updateEvent.getAction()!= null && updateEvent.getAction().equals("unSealJuv") || updateEvent.getAction().equals("unSealRef"))
		{ //please add to the request that if the identified sealed youth has both I. and S. rectypes, it is okay to remove the I. on an unseal process. 
		    if (referral.getRecType().equalsIgnoreCase("S.REFERRAL") || referral.getRecType().equalsIgnoreCase("I.REFERRAL"))
		    {
			referral.setRecType("REFERRAL");
			referral.setLcDate(DateUtil.getCurrentDate());
			referral.setLcTime(Calendar.getInstance().getTime());
			referral.setLcUser(PDSecurityHelper.getLogonId());
			new Home().bind(referral);
		    }
		}
	    else
	    {
		//Post error back	    
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		error.setMessage("REFERRAL record not SEALED (JUVENILE NUM) with juvenileId: " + updateEvent.getJuvenileId());
		dispatch.postEvent(error);
	    }
	}

    }

}
