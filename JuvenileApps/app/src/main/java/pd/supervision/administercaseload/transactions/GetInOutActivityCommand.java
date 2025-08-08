/**
 * 
 */
package pd.supervision.administercaseload.transactions;

import java.util.List;

import pd.supervision.administercaseload.CaseAssignmentHelper;
import pd.supervision.administercaseload.CaseloadInOutActivity;
import messaging.administercaseload.GetInOutActivityEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 */
public class GetInOutActivityCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{

		GetInOutActivityEvent in_out_activity_event = 
			(GetInOutActivityEvent)event;
		
			//retrieve in/out activity
		List<CaseloadInOutActivity> in_out_activity = 
			CaseAssignmentHelper.getInOutActivity(in_out_activity_event);
		
			//return in/out activity responses
		MessageUtil.postReplies(
				CaseAssignmentHelper.getInOutActivityResponses(in_out_activity));

	}

}
