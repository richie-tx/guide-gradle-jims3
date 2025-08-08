package pd.juvenilecase.districtCourtHearings.transactions;
//not in use. Check JJSCLCOURTSETTING.

/*package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.UpdateJJSCLCourtEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import naming.JuvenileCaseControllerServiceNames;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;

*//**
 * UpdateJJSCLCourtCommand
 * 
 *
 *//*
public class UpdateJJSCLCourtCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJJSCLCourtEvent courtEvent = (UpdateJJSCLCourtEvent) event;
	DocketEventResponseEvent evt = new DocketEventResponseEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	String updateFlg="false";
	JJSCourt court = null;
	
	GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
	courtEvt.setJuvenileNumber(courtEvent.getJuvenileNumber());
	courtEvt.setReferralNumber(courtEvent.getReferralNumber());
	Iterator<JJSCourt> courtItr = JJSCourt.findAll("OID",courtEvent.getDocketEventId);
	
	if (courtItr.hasNext())
	{
	    court = courtItr.next();
	    if(!courtEvent.getCourtId().equals(court.getCourtId())){
		court.setCourtId(courtEvent.getCourtId());
		updateFlg="true";
	    }
	    if(!courtEvent.getCourtDate().equals(court.getCourtDate())){
		court.setCourtDate(courtEvent.getCourtDate());
		updateFlg="true";
	    }
	    if(!courtEvent.getCourtTime().equals(court.getCourtTime().substring(0,5))){
		court.setCourtTime(courtEvent.getCourtTime());
		updateFlg="true";
	    }
	    if(courtEvent.getAttorneyConnection()!=null && !courtEvent.getAttorneyConnection().equals(court.getAttorneyConnection())){
		court.setAttorneyConnection(courtEvent.getAttorneyConnection());
		updateFlg="true";
	    }
	    if(!courtEvent.getBarNumber().equals(court.getBarNumber())){
		if(courtEvent.getBarNumber().isEmpty()){
		    if(court.getBarNumber()!=null){
			court.setBarNumber(null);
			court.setAttorneyName(null);
			updateFlg="true";
		    }
		}
		else
		{
		    court.setBarNumber(courtEvent.getBarNumber());
		    if(!courtEvent.getAttorneyName().equals(court.getAttorneyName())){
			court.setAttorneyName(courtEvent.getAttorneyName());
		    }
		    updateFlg="true";
		}
	    }
	   
	    if(!courtEvent.getHearingType().equals(court.getHearingType())){
		court.setHearingType(courtEvent.getHearingType());
		updateFlg="true";
	    }
	    if(!courtEvent.getHearingCategory().equals(court.getHearingCategory())){
		court.setHearingCategory(courtEvent.getHearingCategory());
		updateFlg="true";
	    }
	    if(!courtEvent.getIssueFlag().equals(court.getIssueFlag())){
		court.setIssueFlag(courtEvent.getIssueFlag());
		updateFlg="true";
	    }
	    if(!courtEvent.getJuryFlag().equals(court.getJuryFlag())){
		court.setJuryFlag(courtEvent.getJuryFlag());
		updateFlg="true";
	    }
	    court.setLcDate(DateUtil.getCurrentDate());
	    court.setLcTime(Calendar.getInstance().getTime());
	    court.setLcUser(PDSecurityHelper.getLogonId());
	}
	
	if(updateFlg.equalsIgnoreCase("true")){
	    IHome home = new Home();
	    home.bind(court);
    	}
	evt.setUpdateFlag(updateFlg);
	dispatch.postEvent(evt);
    }
}*/