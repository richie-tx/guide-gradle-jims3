/**
 * 
 */
package pd.supervision.administersupervisee.transactions;

import pd.supervision.administercaseload.SuperviseeHistory;
import messaging.administersupervisee.UpdateSuperviseeHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author cc_cwalters
 *
 */
public class UpdateSuperviseeHistoryCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		UpdateSuperviseeHistoryEvent 
			hist_event =  (UpdateSuperviseeHistoryEvent)event;
		
			//retrieve supervisee history record and update
		SuperviseeHistory 
			this_history = SuperviseeHistory.find(hist_event.getSuperviseeHistoryId());
		
		if (this_history != null)
		{
				//update specified history record
			if (hist_event.getUpdateAction().equals("Update"))
			{
				this_history.setCaseloadCreditStaffPositionId(
						hist_event.getCaseloadCreditStaffPositionId());
				this_history.setAssignedProgramUnitId(
						hist_event.getAssignedProgramUnitId());	
				this_history.setProgramUnitAssignmentDate(
						DateUtil.stringToDate(hist_event.getProgramUnitAssignmentDate(),
								DateUtil.DATE_FMT_1));					
			}
			else	//delete specified history record
				if (hist_event.getUpdateAction().equals("Delete"))
				{
					this_history.delete();
				}		
		}

	}//end of execute()

}
