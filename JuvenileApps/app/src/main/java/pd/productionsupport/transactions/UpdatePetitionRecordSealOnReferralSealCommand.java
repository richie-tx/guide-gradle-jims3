package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.security.PDSecurityHelper;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.UpdateCourtRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdatePetitionRecordSealOnReferralSealEvent;
import messaging.productionsupport.UpdateProductionSupportReferralSealEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class UpdatePetitionRecordSealOnReferralSealCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdatePetitionRecordSealOnReferralSealEvent updateEvent = (UpdatePetitionRecordSealOnReferralSealEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSPetition> objIter = new ArrayList<JJSPetition>().iterator(); // empty   iterator
	objIter = JJSPetition.findAll("OID",updateEvent.getOID());
	
	
	       //Iterator objIter = JJSReferral.findAll(updateEvent);      
	while (objIter.hasNext())
	{

	    JJSPetition pet = (JJSPetition) objIter.next();
	    if (pet != null && (pet.getRecType().equalsIgnoreCase("PETITION")||pet.getRecType().equalsIgnoreCase("I.PETITION") ||pet.getRecType().equalsIgnoreCase("S.PETITION")))
	    {
		if(updateEvent.getAction().equalsIgnoreCase("seal"))
		{
		    pet.setRecType("S.PETITION");	
		}
		else if(updateEvent.getAction().equalsIgnoreCase("purge"))
		    pet.setRecType("I.PETITION");
		else
		    pet.setRecType("PETITION");
		new Home().bind(pet);
	    }
	    else
	    {

		//Post error back	    
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent error = new ErrorResponseEvent();
		if(updateEvent.getAction().equalsIgnoreCase("seal"))
		    error.setMessage("Petition record not SEALED ID: " + updateEvent.getOID());
		else if(updateEvent.getAction().equalsIgnoreCase("purge"))
		    error.setMessage("Petition record not PURGED ID: " + updateEvent.getOID());
		else if(updateEvent.getAction().equalsIgnoreCase("unSealJuv"))
		    error.setMessage("Petition record not PURGED ID: " + updateEvent.getOID());
		else
		    error.setMessage("Petition record not UNPURGED ID: " + updateEvent.getOID());
		dispatch.postEvent(error);
	    }
	}

    }

}
