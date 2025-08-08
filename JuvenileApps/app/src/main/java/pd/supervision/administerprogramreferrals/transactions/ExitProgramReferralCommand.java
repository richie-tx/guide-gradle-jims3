package pd.supervision.administerprogramreferrals.transactions;

import messaging.administerprogramreferrals.ExitProgramReferralEvent;
import messaging.administerprogramreferrals.reply.SubmitExitProgRefResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;

public class ExitProgramReferralCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		ExitProgramReferralEvent exitRefEvent = (ExitProgramReferralEvent)event;
		
		if(exitRefEvent != null)
		{
			SubmitExitProgRefResponseEvent responseEvent = CSProgramReferralHelper.exitProgramReferral(exitRefEvent);
			MessageUtil.postReply(responseEvent);
		}
	}

}
