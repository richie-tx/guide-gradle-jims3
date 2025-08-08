//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenile\\transactions\\CreateMedicalHealthIssueCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.CreateMedicalHealthIssueEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileHealthIssue;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateMedicalHealthIssueCommand implements ICommand 
{
   
   /**
    * @roseuid 462CE210000E
    */
   public CreateMedicalHealthIssueCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCA6038D
    */
   public void execute(IEvent event) 
   {
   	CreateMedicalHealthIssueEvent issueEvent = (CreateMedicalHealthIssueEvent)event;  
   	JuvenileHealthIssue healthIssue = new JuvenileHealthIssue();
   	if(issueEvent.getHealthIssuesListId()!=null && !issueEvent.getHealthIssuesListId().equals("")){
   		healthIssue=JuvenileHealthIssue.find(issueEvent.getHealthIssuesListId());
   		if(healthIssue != null){
   			healthIssue.setJuvenileNum(issueEvent.getJuvenileNum());
   		   	healthIssue.setEntryDate(issueEvent.getEntryDate());
   		   	healthIssue.setIssueId(issueEvent.getIssueId());
   		   	healthIssue.setIssueStatusId(issueEvent.getIssueStatusId());
   		   	healthIssue.setHealthStatusId(issueEvent.getHealthStatusId());
   		   	healthIssue.setConditionSeverityId(issueEvent.getConditionSeverityId());
   		   	healthIssue.setConditionLevelId(issueEvent.getConditionLevelId());
   		   	healthIssue.setModificationReason(issueEvent.getModificationReason());
   		}
   	  }
   	else{	
		   	healthIssue.setJuvenileNum(issueEvent.getJuvenileNum());
		   	healthIssue.setEntryDate(issueEvent.getEntryDate());
		   	healthIssue.setIssueId(issueEvent.getIssueId());
		   	healthIssue.setIssueStatusId(issueEvent.getIssueStatusId());
		   	healthIssue.setHealthStatusId(issueEvent.getHealthStatusId());
		   	healthIssue.setConditionSeverityId(issueEvent.getConditionSeverityId());
		   	healthIssue.setConditionLevelId(issueEvent.getConditionLevelId());
   		}
   }
   
   /**
    * @param event
    * @roseuid 462CBCA6039C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 462CBCA6039E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 462CBCA603A0
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}
