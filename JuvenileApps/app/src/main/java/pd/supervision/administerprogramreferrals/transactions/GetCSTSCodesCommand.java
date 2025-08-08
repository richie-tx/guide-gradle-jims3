package pd.supervision.administerprogramreferrals.transactions;

import java.util.HashSet;
import java.util.List;

import pd.codetable.supervision.CSProgramReferralType;
import pd.common.util.StringUtil;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.reply.CSTSCodesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetCSTSCodesCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		List referralTypes = CSProgramReferralHelper.getReferralTypes();
		
		HashSet cstsCodesSet = new HashSet();
		if(referralTypes!=null)
		{
			for(int index=0; index < referralTypes.size(); index++)
			{
				CSProgramReferralType referralType = (CSProgramReferralType)referralTypes.get(index);
				String cstsCode = referralType.getCtsCode();
				if(!StringUtil.isEmpty(cstsCode))
				{
					cstsCodesSet.add(cstsCode);
				}
			}
		}
		
		CSTSCodesResponseEvent responseEvent = new CSTSCodesResponseEvent();
		responseEvent.setCstsCodesSet(cstsCodesSet);
		MessageUtil.postReply(responseEvent);
	}

}
