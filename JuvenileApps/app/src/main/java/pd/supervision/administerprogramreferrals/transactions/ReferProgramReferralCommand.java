package pd.supervision.administerprogramreferrals.transactions;

import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.ReferProgramReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class ReferProgramReferralCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		ReferProgramReferralEvent referralEvent = (ReferProgramReferralEvent)event; 
		
		if(referralEvent != null)
		{
			CSProgramReferralHelper.referProgramReferral(referralEvent);
		}
	}

}
