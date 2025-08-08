package pd.supervision.administerprogramreferrals.transactions;

import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.GetAppointmentDetailsEvent;
import messaging.administerprogramreferrals.reply.AppointmentDetailsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetAppointmentDetailsCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		GetAppointmentDetailsEvent reqEvent = (GetAppointmentDetailsEvent)event;
		String programRefId = reqEvent.getProgramReferralId();
		
		if(programRefId != null)
		{
			AppointmentDetailsResponseEvent responseEvt = CSProgramReferralHelper.getAppointmentDetailsResponseEvent(programRefId);
			MessageUtil.postReply(responseEvt);
		}
	}

}
