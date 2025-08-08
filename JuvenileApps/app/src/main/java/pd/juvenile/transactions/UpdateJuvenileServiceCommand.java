package pd.juvenile.transactions;

import messaging.juvenile.UpdateJuvenileServiceEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.helper.WebServiceHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class UpdateJuvenileServiceCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJuvenileServiceEvent evt = (UpdateJuvenileServiceEvent) event;
	//Home home= new Home();
	
	/*JuvenileService juvenileService = new JuvenileService();
	juvenileService.setCaseNum(evt.getCaseNum());
	juvenileService.setAppearanceDate(evt.getAppearanceDate());
	juvenileService.setRequestDate(evt.getRequestDate());
	juvenileService.setServiceType(evt.getServiceType());
	juvenileService.setTrackingNum(evt.getTrackingNum());
	juvenileService.setServiceStatus(evt.getServiceStatus());
	juvenileService.setIssueClerk(evt.getIssueClerk());
	juvenileService.setIssueDate(evt.getIssueDate());
	juvenileService.setLcDate(evt.getLcDate());
	juvenileService.setLcUser(evt.getLcUser());
	
	home.bind(juvenileService);*/
	WebServiceHelper helper = new WebServiceHelper();
	helper.updateJUVService( event );
    }
}
