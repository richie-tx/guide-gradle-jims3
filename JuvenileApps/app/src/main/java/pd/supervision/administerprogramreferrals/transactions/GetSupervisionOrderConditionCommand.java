package pd.supervision.administerprogramreferrals.transactions;

import java.util.List;

import pd.supervision.administerprogramreferrals.CSCaseHelper;
import messaging.administerprogramreferrals.GetSupervisionOrderConditionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetSupervisionOrderConditionCommand implements ICommand
{

	public void execute(IEvent event) throws Exception
	{
		GetSupervisionOrderConditionEvent reqEvent = (GetSupervisionOrderConditionEvent)event;
		
		//retrieve all order conditions
		List order_conditions = 
			CSCaseHelper.getOrderConditions(reqEvent.getCriminalCaseId());
		
		MessageUtil.postReplies(order_conditions);
	}

}
