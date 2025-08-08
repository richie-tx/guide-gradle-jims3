/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.FilterProgramReferralsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FilterProgramReferralsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{

			//filter list of referrals based on specified event info 
		FilterProgramReferralsEvent filter_referrals_event = (FilterProgramReferralsEvent)event;		
		List filtered_referrals = 
				CSProgramReferralHelper.filterProgramReferrals(filter_referrals_event);
		
			//create list of response events
		ArrayList referral_response_events = new ArrayList(filtered_referrals.size());
		for (int i=0;i<referral_response_events.size();i++)
		{
				//add response event for each referral
			referral_response_events.add(
					CSProgramReferralHelper.getProgramReferralResponse(
							(CSProgramReferral)filtered_referrals.get(i), false));
		}
		
			//post reply of referral response events
		MessageUtil.postReplies(referral_response_events);
		
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
