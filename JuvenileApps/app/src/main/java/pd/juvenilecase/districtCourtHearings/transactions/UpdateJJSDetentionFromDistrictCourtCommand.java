package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Calendar;

import messaging.districtCourtHearings.UpdateJJSDetentionFromDistrictCourtEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.JJSFacility;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class UpdateJJSDetentionFromDistrictCourtCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	String updateFlg = "false";
	JuvenileDetentionFacilitiesResponseEvent facilityResp = new JuvenileDetentionFacilitiesResponseEvent();
	UpdateJJSDetentionFromDistrictCourtEvent reqEvent = (UpdateJJSDetentionFromDistrictCourtEvent) event;

	//Service call to header.
	JJSFacility detention = JuvenileFacilityHelper.getCurrentFacilityRecord(reqEvent.getJuvenileNumber());

	
	if (detention != null)
	{
	    IHome home = new Home();
	    if (detention.getAdmitDate()!=null &&reqEvent.getAdmitDate()!=null && (detention.getAdmitDate().equals(reqEvent.getAdmitDate()) ||  detention.getAdmitDate().before(reqEvent.getAdmitDate())))
	    {
		 detention.setAdmitReason(reqEvent.getAdmitReason());
		 detention.setAdmitDate(reqEvent.getAdmitDate());
	    }
	    detention.setDetainedDate(reqEvent.getDetainedDate());
	    detention.setDetentionStatus(reqEvent.getDetentionStatus());
	    detention.setLcTime(String.valueOf(Calendar.getInstance().getTime()));
	    detention.setLastChangeDate(DateUtil.getCurrentDate());
	    home.bind(detention);
	    facilityResp.setUpdateFlag(updateFlg);
	}
	facilityResp.setUpdateFlag(updateFlg);
	dispatch.postEvent(facilityResp);
    }
}
