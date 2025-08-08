package pd.supervision.administerprogramreferrals.transactions;

import java.util.Iterator;
import java.util.List;

import pd.supervision.administerprogramreferrals.CSProgramReferralByCaseload;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.GetProgRefByCaseloadEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetProgRefByCaseloadCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		GetProgRefByCaseloadEvent requestEvt = (GetProgRefByCaseloadEvent)event;
		
		Iterator iter = CSProgramReferralByCaseload.findAll(requestEvt);
		if(iter!=null)
		{
			List responseEvtsList = CSProgramReferralHelper.getProgRefByCaseloadRespEvts(iter);
			MessageUtil.postReplies(responseEvtsList);
		}
	}

}
