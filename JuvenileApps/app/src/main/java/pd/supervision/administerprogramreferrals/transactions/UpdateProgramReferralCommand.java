/**
 * 
 */
package pd.supervision.administerprogramreferrals.transactions;

import messaging.administerprogramreferrals.UpdateProgramReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;

/**
 * @author cc_cwalters
 *
 */
public class UpdateProgramReferralCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		UpdateProgramReferralEvent updateEvent = (UpdateProgramReferralEvent)event;
		
		if(updateEvent != null)
		{
			CSProgramReferralHelper.updateProgramReferral(updateEvent);
		}
	}

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
