package pd.juvenilecase.transactions;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import naming.JuvenileCaseControllerServiceNames;

import messaging.districtCourtHearings.SaveJJSCLCourtEvent;
import messaging.juvenilecase.SaveJJSLastAttorneyEvent;
import messaging.juvenilecase.SaveProdSupportLastAttorneyEvent;
import messaging.juvenilecase.UpdateJJSLastAttorneyGALEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.UpdateJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;

public class UpdateJJSLastAttorneyGALCommand implements ICommand{

    @Override
    public void execute(IEvent event) throws Exception
    {
	
	UpdateJJSLastAttorneyGALEvent evt = (UpdateJJSLastAttorneyGALEvent)event;
	    // update last attorney
	//SaveJJSLastAttorneyEvent jjsEvt = new SaveJJSLastAttorneyEvent();
	    Iterator refIter = JJSLastAttorney.findAll("juvenileNum",evt.getJuvenileNumber());
	    Date currDate = new Date();
	    //add code to save id differently from court and detention
	    if (refIter.hasNext())
	    {
		JJSLastAttorney ref = (JJSLastAttorney) refIter.next();
		if (ref != null)
		{  	
		    /*if(evt.getGalName()!=null&&!evt.getGalName().isEmpty())
		    {*/
			if(!evt.getGalbarNumber().equalsIgnoreCase(ref.getGalBarNum()))
			{
			    	if(evt.getGalName()!=null && evt.getGalName().isEmpty())
			    	    ref.setGalName(null);
			    	else
			    	    ref.setGalName(evt.getGalName());
        			if(evt.getGalbarNumber()!=null && evt.getGalbarNumber().isEmpty())
        			    ref.setGalBarNum(null);
        			else
        			    ref.setGalBarNum(evt.getGalbarNumber());
        			ref.setGaladddate(currDate);        			
        			/*if(evt.getRecType().equalsIgnoreCase("COURT"))
        				ref.setJjclcourtId(evt.getJjclcourtId());
        			if(evt.getRecType().equalsIgnoreCase("DETENTION"))
        				ref.setJjcldetentionId(evt.getJjclcourtId());*/
        			
			}
		    //}
		    IHome home = new Home();
		    home.bind(ref);
		}
	    }
	    else
	    {
		JJSLastAttorney ref = new JJSLastAttorney();
		ref.setJuvenileNum(evt.getJuvenileNumber());		
		ref.setCreateUserID(PDSecurityHelper.getLogonId()); 
		 if(evt.getGalName()!=null&&!evt.getGalName().isEmpty())
		 {
		     ref.setGalName(evt.getGalName());
		     ref.setGalBarNum(evt.getGalbarNumber());
		     //add galdate as todays date
		     ref.setGaladddate(currDate);
		 }
		IHome home = new Home();
		home.bind(ref);
	    }
	    
    }
}
