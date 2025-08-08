package pd.productionsupport.transactions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import naming.PDJuvenileCaseConstants;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.productionsupport.GetJuvenileReferralOffensesQueryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSOffense;

public class GetJuvenileReferralOffensesQueryCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJuvenileReferralOffensesQueryEvent evt = (GetJuvenileReferralOffensesQueryEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//search by juvenile 
	if (evt.getJuvenileNum()!= null && !evt.getJuvenileNum().isEmpty())
	{
	    Iterator<JJSOffense> offItr = JJSOffense.findAll(evt);
	    while (offItr.hasNext())
	    {
		JJSOffense o = (JJSOffense) offItr.next();
		JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();
    		resp.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
    		resp.setJuvenileNum(o.getJuvenileNum());    		
    		resp.setOffDate(DateUtil.dateToString(o.getOffenseDate(),DateUtil.DATE_FMT_1));
    		resp.setOffenseDescription(o.getOffenseDescription());
    		resp.setReferralNum(o.getReferralNum());
    		resp.setSequenceNum(o.getSequenceNum());
    		resp.setCatagory(o.getCatagory());
    		resp.setCitationCode(o.getCitationCode());
    		resp.setCitationSource(o.getCitationSource());
    		resp.setSequenceNum(o.getSequenceNum());
    		resp.setChargeSequenceNum(o.getChargeSequenceNum());
    		resp.setInvestigationNum(o.getInvestigationNumber());
    		resp.setOffenseReportGroup(o.getOffenseReportGroup());
    		resp.setOffenseCodeId(o.getOffenseCodeId());
    		resp.setOffenseCode(o.getOffenseCode().getOffenseCode());
    		resp.setOldoffenseCode(o.getOffenseCode().getOffenseCode());
    		//resp.setSeveritySubtype(o.getOffenseCode().getSeveritySubtype()); 
    		resp.setOffenseSeverity(o.getSeverity());
    		resp.setKeyMapLocation(o.getKeyMapLocation());
    		resp.setOffenseStreetNum(o.getOffenseStreetNum());
    		resp.setOffenseStreetName(o.getOffenseStreetName());
    		resp.setOffenseAptNum(o.getOffenseAptNum());
    		resp.setOffenseCity(o.getOffenseCity());
    		resp.setOffenseState(o.getOffenseState());
    		resp.setOffenseZip(o.getOffenseZip());
    		resp.setWeaponType(o.getWeaponType());
    		resp.setCjisNum(o.getCjisNum());
    		resp.setArrestDate(DateUtil.dateToString(o.getArrestDate(),DateUtil.DATE_FMT_1));    		
    		resp.setArrestTime(o.getArrestTime());
    		resp.setLcUser(o.getLcUser());
    		resp.setLcDate(o.getLcDate());
    		resp.setOID(o.getOID());
    		if (o.getLcTime() != null)
    		{
    		    	Calendar cal=Calendar.getInstance();
    	        	cal.setTime(o.getLcTime());
    	        	SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
    	        	String time = localDateFormat.format(cal.getTime());
    	        	resp.setLcTime(time);
    		}
    		resp.setRecType(o.getRecType());
    		//US 171045
    		resp.setOnCampOffense(o.getOnCampOffense());
    		resp.setOnCampDistrict(o.getOnCampDistrict());
    		resp.setOnCampSchool(o.getOnCampSchool());
    		
    		dispatch.postEvent(resp);;
	    }
	    
	}
    }
}
