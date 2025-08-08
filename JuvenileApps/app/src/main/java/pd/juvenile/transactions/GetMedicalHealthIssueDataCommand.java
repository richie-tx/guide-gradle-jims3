//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\GetMedicalHealthIssueDataCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.GetMedicalHealthIssueDataEvent;
import messaging.juvenile.reply.JuvenileHealthIssueResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileHealthIssue;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMedicalHealthIssueDataCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE21D00CA
    */
   public GetMedicalHealthIssueDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC035F
    */
   public void execute(IEvent event) 
   {
   	GetMedicalHealthIssueDataEvent reqEvent = (GetMedicalHealthIssueDataEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	JuvenileHealthIssue healthIssueData = JuvenileHealthIssue.find(reqEvent.getHealthIssueId());
	JuvenileHealthIssueResponseEvent issueRespEvent = getHealthIssueResponse(healthIssueData);
	dispatch.postEvent(issueRespEvent);
   }
   
   /**
 * @param healthIssueData
 * @return
 */
private JuvenileHealthIssueResponseEvent getHealthIssueResponse(JuvenileHealthIssue healthIssueData) {
	JuvenileHealthIssueResponseEvent issueRespEvent = new JuvenileHealthIssueResponseEvent();
	issueRespEvent.setHealthIssueId(healthIssueData.getHealthIssueId());
	issueRespEvent.setEntryDate(healthIssueData.getEntryDate());
	issueRespEvent.setIssueId(healthIssueData.getIssueId());
	issueRespEvent.setIssueStatusId(healthIssueData.getIssueStatusId());
	issueRespEvent.setHealthStatusId(healthIssueData.getHealthStatusId()); 
	issueRespEvent.setConditionSeverityId(healthIssueData.getConditionSeverityId());
	issueRespEvent.setConditionLevelId(healthIssueData.getConditionLevelId());
	issueRespEvent.setModificationReason(healthIssueData.getModificationReason());
	return issueRespEvent;
}

/**
    * @param event
    * @roseuid 462CBCCC0361
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCCC036C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCCC036E
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
