package pd.juvenilecase.districtCourtHearings.transactions;

import messaging.districtCourtHearings.UpdateJJSHeaderFromDetentionCourtEvent;
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
 * @author anpillai
 */
public class UpdateJJSHeaderFromDetentionCourtCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	String updateFlg = "false";
	JuvenileFacilityHeaderResponseEvent headerResp = new JuvenileFacilityHeaderResponseEvent();
	UpdateJJSHeaderFromDetentionCourtEvent reqEvent = (UpdateJJSHeaderFromDetentionCourtEvent) event;

	//Service call to header.
	JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNumber());

	if (header != null)
	{
	    IHome home = new Home();
	    /*if(reqEvent.getProbableCauseDate()!=null)
		header.setProbableCauseDate(reqEvent.getProbableCauseDate());*/
	    if(reqEvent.isReleasedecisionStatus())
		header.setReleaseDecisionStatus(null);
	    if(reqEvent.isDistreleasedecisionStatus())
		header.setDistReleaseDecisionStatus(null);
	    home.bind(header);
	    headerResp.setUpdateFlag(updateFlg);
	}
	headerResp.setUpdateFlag(updateFlg);
	dispatch.postEvent(headerResp);
    }
}