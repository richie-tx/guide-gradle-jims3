/*
 * Created on Dec 29, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.List;

import messaging.administerprogramreferrals.GetServiceProvidersByReferralTypesEvent;
import messaging.administerprogramreferrals.reply.GetServiceProvidersByReferralTypesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;

/**
 * @author c_shimek
 *
 * this is a modified version of InitiateReferralsCommand with update coding removed
 * and was created for Activity #63239 as SP data is loaded within update command
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceProvidersByReferralTypesCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetServiceProvidersByReferralTypesEvent this_event = (GetServiceProvidersByReferralTypesEvent)event;
		
			//retrieve elements from initiate referrals event
		List referral_types = this_event.getReferralTypes();
		
			//retrieve service providers matching specified referral types
		List matching_sps = 
			CSServiceProviderHelper.getServiceProvidersForReferralTypes(referral_types);
		
			//convert matching service providers to necessary response events
		List sp_reftype_responses = 
				CSServiceProviderHelper.getServiceProviderReftypeResponses(matching_sps);
		
			//set properties of response event
		GetServiceProvidersByReferralTypesResponseEvent initiate_referrals_response = new GetServiceProvidersByReferralTypesResponseEvent();
		initiate_referrals_response.setServiceProviderRefTypeResponses(sp_reftype_responses);
		
			//post response
		MessageUtil.postReply(initiate_referrals_response);
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
