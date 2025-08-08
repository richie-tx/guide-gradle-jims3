package pd.juvenilecase.facility.transactions;

import java.util.Iterator;

import messaging.facility.GetJuvenileFacilityHeaderEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import pd.juvenilecase.JJSHeader;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJuvenileFacilityHeaderCommand implements ICommand{

	
	/**
	 * Execute method
	 */
	public void execute(IEvent event)
	{
		GetJuvenileFacilityHeaderEvent facilityEvent = (GetJuvenileFacilityHeaderEvent) event; 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator<JJSHeader> jjsHeaderItr = JJSHeader.findAll(facilityEvent);
		while (jjsHeaderItr.hasNext()) {
			JJSHeader header =  jjsHeaderItr.next();
			JuvenileFacilityHeaderResponseEvent resp = new JuvenileFacilityHeaderResponseEvent();
			resp.setHeaderId(header.getOID());
			resp.setFacilityCode(header.getFacilityCode());
			resp.setFacilityStatus(header.getFacilityStatus());
			resp.setJuvenileNumber(header.getJuvenileNumber());
			resp.setLastSqlNum(header.getLastSequenceNumber());
			resp.setNextHearingDate(DateUtil.dateToString(header.getNextHearingDate(), DateUtil.DATE_FMT_1));
			resp.setProbableCauseDate(header.getProbableCauseDate());
			resp.setReferralNo(header.getReferralNumber());
			resp.setRelocationDate(header.getRelocationDate());
			resp.setBookingSupervision(header.getBookingSupervisionNum());
			
			if(header.getRelocationTime()!=null && !header.getRelocationTime().isEmpty()){
				resp.setRelocationTime(JuvenileFacilityHelper.getHoursMinsFromTime(header.getRelocationTime()));
			}
			
			resp.setTopic(PDJuvenileConstants.JUVENILE_FACILITY_TOPIC);
			//add code to distinguish district and detention
			if(facilityEvent.getCourtType()!=null)
			{
        			if(facilityEvent.getCourtType().equals("district"))
        			    resp.setTempReleaseStatus(header.getDistReleaseDecisionStatus());
        			if(facilityEvent.getCourtType().equals("detention"))
        			    resp.setTempReleaseStatus(header.getReleaseDecisionStatus());
			}
			dispatch.postEvent(resp);
		}
	}
}