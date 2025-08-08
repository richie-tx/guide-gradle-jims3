//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\GetJuvenileMedicalHealthIssuesListCommand.java

package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileMedicalHealthIssuesListEvent;
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
public class GetJuvenileMedicalHealthIssuesListCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE22102ED
    */
   public GetJuvenileMedicalHealthIssuesListCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBF6B001D
    */
   public void execute(IEvent event) 
   {
   	GetJuvenileMedicalHealthIssuesListEvent reqEvent = (GetJuvenileMedicalHealthIssuesListEvent)event;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	Iterator issuesIterator =	JuvenileHealthIssue.findAll(reqEvent);
   	while (issuesIterator.hasNext()) {
   		JuvenileHealthIssue issueResults = (JuvenileHealthIssue) issuesIterator.next();
   		JuvenileHealthIssueResponseEvent issueRespEvent = getIssueResponseEvent(issueResults);
		dispatch.postEvent(issueRespEvent);
	}
   }
   
   /**
 * @param issueResults
 * @return
 */
private JuvenileHealthIssueResponseEvent getIssueResponseEvent(JuvenileHealthIssue issueResults) {
	// TODO Auto-generated method stub
	JuvenileHealthIssueResponseEvent issueRespEvent = new JuvenileHealthIssueResponseEvent();
	issueRespEvent.setHealthIssueId(issueResults.getHealthIssueId());
	issueRespEvent.setEntryDate(issueResults.getEntryDate());
	issueRespEvent.setIssueId(issueResults.getIssueId());
	issueRespEvent.setIssueStatusId(issueResults.getIssueStatusId());
	issueRespEvent.setHealthStatusId(issueResults.getHealthStatusId()); 
	issueRespEvent.setConditionLevelId(issueResults.getConditionLevelId());
	issueRespEvent.setConditionSeverityId(issueResults.getConditionSeverityId());
	issueRespEvent.setModificationReason(issueResults.getModificationReason());
	return issueRespEvent;
}

/**
    * @param event
    * @roseuid 462CBF6B002A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBF6B002C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBF6B002E
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
