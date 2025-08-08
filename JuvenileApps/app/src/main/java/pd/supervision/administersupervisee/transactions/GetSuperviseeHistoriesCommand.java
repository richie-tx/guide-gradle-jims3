//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisee\\transactions\\GetSuperviseeDataCommand.java

package pd.supervision.administersupervisee.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.administersupervisee.GetSuperviseeHistoriesEvent;
import messaging.administersupervisee.reply.SuperviseeHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.common.util.StringUtil;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;


public class GetSuperviseeHistoriesCommand implements ICommand 
{
   
   /**
    * @roseuid 484E81420180
    */
   public GetSuperviseeHistoriesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4849421B00AA
    */
   public void execute(IEvent anEvent) {
	   GetSuperviseeHistoriesEvent event = ( GetSuperviseeHistoriesEvent ) anEvent;
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Supervisee sup = Supervisee.findByDefendantId(event.getDefendant_id());
		String supId = "";
		if(sup != null) 
		{
			//set supervisee response properties
			supId = sup.getOID();
		}
		
		if (!StringUtil.isEmpty( supId )) {
				Iterator iter = SuperviseeHistory.findAll("superviseeId", supId);
			  
				//create history response event
				SuperviseeHistoryResponseEvent history_response = null;
				List supervisee_history_responses = new ArrayList();
				while (iter.hasNext()) 
				{
					history_response = 	new SuperviseeHistoryResponseEvent();
					SuperviseeHistory hist = (SuperviseeHistory)iter.next();
					
					history_response.setSuperviseeHistoryId(hist.getOID());
					history_response.setSuperviseeId(hist.getSuperviseeId());
					history_response.setType(hist.getType());
					history_response.setLosEffectiveDate(hist.getLosEffectiveDate());
					history_response.setSupervisionLevelId(hist.getSupervisionLevelId());					
					history_response.setAssignedProgramUnitId(hist.getAssignedProgramUnitId());
					
						//retrieve workload credit position
					history_response.setCaseloadCreditStaffPositionId(hist.getCaseloadCreditStaffPositionId());
										
					history_response.setAssignedStaffPositionId(hist.getAssignedStaffPositionId());
					history_response.setCurrentlySupervised(hist.isCurrentlySupervised());
					history_response.setComments(hist.getComments());
					history_response.setProgramUnitAssignmentDate(
							DateUtil.dateToString(hist.getProgramUnitAssignmentDate(), 
									DateUtil.DATE_FMT_1));
					history_response.setCreateDate(
							DateUtil.dateToString(hist.getCreateTimestamp(), 
									DateUtil.DATE_FMT_1));
					supervisee_history_responses.add( history_response);
					dispatch.postEvent( history_response );
			}

		} 	
   }
  
   /**
    * @param event
    * @roseuid 4849421B0202
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4849421B02BD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4849421B033B
    */
   public void update(Object anObject) 
   {
    
   }
   
}
