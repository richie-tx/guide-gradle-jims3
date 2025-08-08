/*
 * Created on Apr 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;


import java.util.List;

import messaging.administerprogramreferrals.UpdateProgramReferralsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateProgramReferralsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
			//update specified set of referrals
		UpdateProgramReferralsEvent updateReferralsEvt = (UpdateProgramReferralsEvent)event;
		
		if(updateReferralsEvt != null)
		{
			List referralsList = updateReferralsEvt.getReferralsList();
			
			if(referralsList != null)
			{
				CSProgramReferralHelper.updateProgramReferrals(referralsList);
			}
			
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
