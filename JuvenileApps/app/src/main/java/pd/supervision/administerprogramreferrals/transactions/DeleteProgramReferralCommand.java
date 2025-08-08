/*
 * Created on Apr 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;


import java.util.Iterator;

import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.DeleteProgramReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteProgramReferralCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
			//delete specified program referral
		DeleteProgramReferralEvent delete_referral_event = (DeleteProgramReferralEvent)event;		
		CSProgramReferralHelper.deleteProgramReferral(delete_referral_event);
		
//		remove the referral reference on the casenotes, if exist
		Iterator iter = Casenote.findAll("programReferralId", delete_referral_event.getProgramReferralId());
		if(iter!=null)
		{
			while(iter.hasNext())
			{
				Casenote casenote = (Casenote)iter.next();
				casenote.setProgramReferralId(null);
			}
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
