package pd.supervision.administerprogramreferrals.transactions;


import java.util.List;

import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.GetInitOpenReferralsForRefTypesEvent;
import messaging.administerprogramreferrals.InitNOpenRefsForRefTypesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;



public class GetInitOpenReferralsForRefTypesCommand implements ICommand
{
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetInitOpenReferralsForRefTypesEvent requestEvent = (GetInitOpenReferralsForRefTypesEvent)event;
		
		InitNOpenRefsForRefTypesEvent queryEvent = new InitNOpenRefsForRefTypesEvent();
		queryEvent.setDefendantId(requestEvent.getDefendantId());
		queryEvent.setRefTypesCdList(requestEvent.getRefTypesCdList());
		
		List progRefList = CSProgramReferralHelper.getReferralsForRefTypes(queryEvent);
		List responseList = CSProgramReferralHelper.getReferralsWithRefTypesResponseEvent(progRefList);
		
		MessageUtil.postReplies(responseList);
		
	}//end of execute()

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
