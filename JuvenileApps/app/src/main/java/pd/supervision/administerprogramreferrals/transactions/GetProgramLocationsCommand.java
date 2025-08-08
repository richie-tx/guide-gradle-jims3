/*
 * Created on Apr 9, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.List;

import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramHelper;
import messaging.administerprogramreferrals.GetProgramLocationsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramLocationsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetProgramLocationsEvent this_event = (GetProgramLocationsEvent)event;
		
		if(this_event.isOrderByLocation())
		{
			//retrieve list of program locations
			List program_locations = 
				CSProgramHelper.getProgramLocationsBySerProvNRefTypes(
												this_event.getServiceProviderIds(), this_event.getReferralTypesCodeList(), true);
			
				//post replies of program locations 
			MessageUtil.postReplies(CSProgramHelper.getProgramLocationResponseEventsOrderByLoc(program_locations));
		}
		else
		{
				//retrieve list of program locations
			List program_locations = 
				CSProgramHelper.getProgramLocationsBySerProvNRefTypes(
												this_event.getServiceProviderIds(), this_event.getReferralTypesCodeList(), false);
			
				//post replies of program locations 
			MessageUtil.postReplies(CSProgramHelper.getProgramLocationResponseEvents(program_locations));
		}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
