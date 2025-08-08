package pd.supervision.administerprogramreferrals.transactions;

import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.SubmitProgramReferralEvent;
import messaging.administerprogramreferrals.reply.SubmitExitProgRefResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class SubmitProgramReferralCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		SubmitProgramReferralEvent submitEvent = (SubmitProgramReferralEvent)event;
		
		if(submitEvent != null)
		{
			 SubmitExitProgRefResponseEvent responseEvent = CSProgramReferralHelper.submitProgramReferral(submitEvent);
			 MessageUtil.postReply(responseEvent);
		}
	}

}
