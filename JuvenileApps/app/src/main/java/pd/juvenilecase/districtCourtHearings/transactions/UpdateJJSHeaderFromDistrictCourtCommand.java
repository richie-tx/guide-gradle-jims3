package pd.juvenilecase.districtCourtHearings.transactions;

import messaging.districtCourtHearings.UpdateJJSHeaderFromDistrictCourtEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.JJSHeader;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * Used in court action.Not created for facility.
 * 
 * @author sthyagarajan
 */
public class UpdateJJSHeaderFromDistrictCourtCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	String updateFlg = "false";
	JuvenileFacilityHeaderResponseEvent headerResp = new JuvenileFacilityHeaderResponseEvent();
	UpdateJJSHeaderFromDistrictCourtEvent reqEvent = (UpdateJJSHeaderFromDistrictCourtEvent) event;

	//Service call to header.
	JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNumber());

	if (header != null)
	{
	    IHome home = new Home();
	    header.setNextHearingDate(reqEvent.getNextHearingDate());
	    header.setProbableCauseDate(reqEvent.getProbableCauseDate());
	    home.bind(header);
	    headerResp.setUpdateFlag(updateFlg);
	}
	headerResp.setUpdateFlag(updateFlg);
	dispatch.postEvent(headerResp);
    }
}