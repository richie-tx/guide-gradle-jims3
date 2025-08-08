/**
 * 
 */
package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import pd.supervision.administerserviceprovider.csserviceprovider.CSProgram;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramHelper;
import messaging.csserviceprovider.GetIncarcerationProgramEvent;
import messaging.csserviceprovider.GetProgramUnitProgramEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 */
public class GetProgramUnitProgramCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetProgramUnitProgramEvent pup_event = 
			(GetProgramUnitProgramEvent)event;
		
			//attempt to retrieve any programs with the specified program unit
		CSProgram incarceration_program = 
			CSProgramHelper.getNonInactiveProgramUnitProgram(
					pup_event.getProgramId(), pup_event.getProgramUnitId());
		
		MessageUtil.postReply(
				CSProgramHelper.getProgramResponseEvent(incarceration_program));

	}

}
