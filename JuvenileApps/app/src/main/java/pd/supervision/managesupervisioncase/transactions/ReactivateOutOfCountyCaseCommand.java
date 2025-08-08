//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\ReactivateOutOfCountyCaseCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.managesupervisioncase.ReactivateOutOfCountyCaseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.CollectionUtil;
import pd.supervision.managesupervisioncase.OutOfCountyProbationReactivateCase;
import pd.task.helper.TaskHelper;

public class ReactivateOutOfCountyCaseCommand implements ICommand 
{
   
   /**
    * @roseuid 444525DD0125
    */
   public ReactivateOutOfCountyCaseCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 443D14B203A5
    */
   public void execute(IEvent event) 
   {
		ReactivateOutOfCountyCaseEvent reactivateEvent = (ReactivateOutOfCountyCaseEvent)event;
			
		GetOutOfCountyCaseEvent findEvent = new GetOutOfCountyCaseEvent();
		findEvent.setCaseNum(reactivateEvent.getCaseNum());
		findEvent.setCourtDivisionId(reactivateEvent.getCdi());
		
		Iterator iter = new Home().findAll(findEvent, OutOfCountyProbationReactivateCase.class);
		List cases = CollectionUtil.iteratorToList(iter);
		if (cases.size() > 0){
			OutOfCountyProbationReactivateCase theCase = (OutOfCountyProbationReactivateCase) cases.get(0);
			theCase.reactivate(reactivateEvent);
			try {
				TransactionManager.getInstance().commitClear();
			} catch (Exception e) {
				TaskHelper.createCsTaskToJIMSWorkgroup("REACTIVATE OOC CASE", theCase.getSpn(), theCase.getCriminalCaseId(), e);
			}
		
			// post a return
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(new ResponseEvent());	
		}
    }
   
   
   /**
	 * 
	 * @param outOfCountyCase
	 * @return
	 */
	/* private boolean isT30RecordToBeProcessed(OutOfCountyProbationCase outOfCountyCase, String dispositionTypeId)
	{
		if((dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.DEFERRED_ADJUDICATION)) ||
				(dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.STRAIGHT_PROBATION)))
		{
			String caseTypeId = outOfCountyCase.getOutOfCountyCaseTypeId();
			
			if((caseTypeId.equalsIgnoreCase(PDCodeTableConstants.IN_STATE_FELONY)) || (caseTypeId.equalsIgnoreCase(PDCodeTableConstants.IN_STATE_MISD)) || 
					(caseTypeId.equalsIgnoreCase(PDCodeTableConstants.OUT_OF_STATE_FELONY)) || (caseTypeId.equalsIgnoreCase(PDCodeTableConstants.OUT_OF_STATE_MISD)))
			{
				return true;
			}
		}
		return false;
	}*/
	
   
   /**
    * @param event
    * @roseuid 443D14B203AD
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 443D14B203AF
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 444525DD0139
    */
   public void update(Object updateObject) 
   {
    
   }
}
