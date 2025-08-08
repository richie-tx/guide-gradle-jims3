package pd.supervision.administerprogramreferrals.transactions;

import java.util.Iterator;

import messaging.administerprogramreferrals.GetReferralFormsEvent;
import messaging.administerprogramreferrals.reply.ReferralFormResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerprogramreferrals.CSReferralForm;

public class GetReferralFormsCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		GetReferralFormsEvent requestEvent = (GetReferralFormsEvent)event;
		
		if(requestEvent != null)
		{
			Iterator iter = CSProgramReferralHelper.getReferralForms(requestEvent.getReferralTypeCd());
			if(iter != null)
			{
				while(iter.hasNext())
				{
					CSReferralForm referralForm = (CSReferralForm)iter.next();
					
					ReferralFormResponseEvent responseEvent = CSProgramReferralHelper.convertToReferralResponseEvent(referralForm);
					MessageUtil.postReply(responseEvent);
				}
			}
			
		}
	}
	
}
