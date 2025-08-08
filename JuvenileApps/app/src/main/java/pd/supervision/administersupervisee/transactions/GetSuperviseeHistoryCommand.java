/**
 * 
 */
package pd.supervision.administersupervisee.transactions;

import pd.supervision.administercaseload.SuperviseeHistory;
import messaging.administersupervisee.GetSuperviseeHistoryEvent;
import messaging.administersupervisee.reply.SuperviseeHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;


/**
 * @author cc_cwalters
 *
 */
public class GetSuperviseeHistoryCommand implements ICommand 
{

	/**
	 * Convert supervisee history entity to response event
	 */
	private SuperviseeHistoryResponseEvent 
				convertHistory2Response(SuperviseeHistory history)
	{
			//create response object and populate with history data
		SuperviseeHistoryResponseEvent 
			hist_response = new SuperviseeHistoryResponseEvent();
		hist_response.setAssignedProgramUnitId(history.getAssignedProgramUnitId());
		hist_response.setAssignedStaffPositionId(history.getAssignedStaffPositionId());
		hist_response.setCaseloadCreditStaffPositionId(history.getCaseloadCreditStaffPositionId());
		hist_response.setComments(history.getComments());
		hist_response.setCreateDate(
				DateUtil.dateToString(history.getCreateTimestamp(), 
						DateUtil.DATE_FMT_1));
		hist_response.setCurrentlySupervised(history.isCurrentlySupervised());
		hist_response.setLosEffectiveDate(history.getLosEffectiveDate());
		hist_response.setProgramUnitAssignmentDate(
				DateUtil.dateToString(history.getProgramUnitAssignmentDate(), 
						DateUtil.DATE_FMT_1));
		hist_response.setSuperviseeHistoryId(history.getOID());
		hist_response.setSuperviseeId(history.getSuperviseeId());
		hist_response.setSupervisionLevelId(history.getSupervisionLevelId());
		hist_response.setType(history.getType());
		
		return hist_response;
	}//end of convertHistory2Response()
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
			//retrieve given supervisee history record
		GetSuperviseeHistoryEvent 
			get_history_event = (GetSuperviseeHistoryEvent) event;		
		SuperviseeHistory this_history = 
				SuperviseeHistory.find(get_history_event.getSuperviseeHistoryId());
		
			//convert history to response event and post reply			
		SuperviseeHistoryResponseEvent 
			hist_response = convertHistory2Response(this_history);		
		MessageUtil.postReply(hist_response);
		
	}//end of execute()

}
