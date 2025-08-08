/*
 * Created on Mar 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administerprogramreferrals.GetSuperviseeActiveCasesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administerprogramreferrals.CSCaseHelper;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSuperviseeActiveCasesCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
			//retrieve active cases for given supervisee
		GetSuperviseeActiveCasesEvent active_cases_event = 
										(GetSuperviseeActiveCasesEvent)event;
		
		GetActiveCasesEvent queryEvent = new GetActiveCasesEvent();
		queryEvent.setDefendantId(active_cases_event.getDefendantId());
		
        // search for active cases based on defendantId (i.e. defendantId)
		ArrayList case_response_events = new ArrayList();
        Iterator iter = CaseAssignmentOrder.findAllByEvent(queryEvent);
        while(iter.hasNext())
        {
        	//add response event for each case
        	case_response_events.add(CSCaseHelper.getSuperviseeCaseResponseEvent(
					(CaseAssignmentOrder)iter.next()));
        }
		
			//post response events
		MessageUtil.postReplies(case_response_events);
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
