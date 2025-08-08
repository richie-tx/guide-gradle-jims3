package pd.supervision.administerprogramreferrals.transactions;

import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.ChangeReferralStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class ChangeReferralStatusCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		ChangeReferralStatusEvent changeStatusEvt = (ChangeReferralStatusEvent)event;
		
		if(changeStatusEvt != null)
		{
			CSProgramReferralHelper.changeProgramReferralStatus(changeStatusEvt);
		}
	}
}
