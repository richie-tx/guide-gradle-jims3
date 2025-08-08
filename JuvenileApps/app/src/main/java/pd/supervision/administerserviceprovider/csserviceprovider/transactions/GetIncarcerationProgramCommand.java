/**
 * 
 */
package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import pd.supervision.administerserviceprovider.csserviceprovider.CSProgram;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramHelper;
import messaging.csserviceprovider.GetIncarcerationProgramEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 */
public class GetIncarcerationProgramCommand implements ICommand 
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetIncarcerationProgramEvent incarceration_prog_event = 
			(GetIncarcerationProgramEvent)event;
		
			//attempt to retrieve any programs with the specified incarceration condition
		CSProgram incarceration_program = 
			CSProgramHelper.getIncarcerationProgram(
				incarceration_prog_event.getIncarcerationConditionId());
		
		MessageUtil.postReply(
				CSProgramHelper.getProgramResponseEvent(incarceration_program));
	}
}
