/*
 * Created on Mar 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.List;

import pd.supervision.administerprogramreferrals.CSCaseHelper;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.GetReferralTypeSelectionEvent;
import messaging.administerprogramreferrals.reply.ReferralTypeSelectionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetReferralTypeSelectionCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetReferralTypeSelectionEvent this_event = (GetReferralTypeSelectionEvent) event;
		
			//retrieve all referral types
		List referral_types = CSProgramReferralHelper.getReferralTypeResponses();
 
			//retrieve all order conditions
		List order_conditions = 
			CSCaseHelper.getOrderConditions(this_event.getCriminalCaseId());
		
			//create response event
		ReferralTypeSelectionResponseEvent response_event = 
							new ReferralTypeSelectionResponseEvent();

			//add order conditions and referral types to response event
		response_event.setReferralTypes(referral_types);
		response_event.setOrderConditions(order_conditions);
		
			//post response
		MessageUtil.postReply(response_event);
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
