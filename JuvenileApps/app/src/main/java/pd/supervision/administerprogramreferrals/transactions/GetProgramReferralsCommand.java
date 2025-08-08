/*
 * Created on Mar 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pd.common.util.StringUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import messaging.administerprogramreferrals.GetProgramReferralsEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.csserviceprovider.GetSPProgramLocationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramReferralsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
			//retrieve all referrals for specified supervisee 
		GetProgramReferralsEvent get_referrals_event = (GetProgramReferralsEvent)event;		
		List supervisee_referrals = 
				CSProgramReferralHelper.getSuperviseeReferrals(get_referrals_event.getDefendantId());
		
			//create list of response events
		int num_referrals = supervisee_referrals.size();
		ArrayList referral_response_events = new ArrayList(num_referrals);
		
		for (int i=0;i<num_referrals;i++)
		{
			CSProgramReferral csProgramReferral = (CSProgramReferral)supervisee_referrals.get(i);	
			CSProgramReferralResponseEvent responseEvent = CSProgramReferralHelper.getProgramReferralResponse(csProgramReferral, false);
			
			referral_response_events.add(responseEvent);
		}
		
			//post reply of referral response events
		MessageUtil.postReplies(referral_response_events);
	}//end of execute()

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
