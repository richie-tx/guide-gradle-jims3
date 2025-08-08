package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportInterviewEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JPOAssignmentHistory;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.Interview;

public class UpdateProductionSupportInterviewCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportInterviewCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportInterview");
	   UpdateProductionSupportInterviewEvent updateEvent = (UpdateProductionSupportInterviewEvent) event;
	   Iterator<Interview> interviewsIter = Interview.findAll("casefileId", updateEvent.getCasefileId());
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   while(interviewsIter.hasNext()){
			   Interview interview = (Interview)interviewsIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   interview.setCasefileId(updateEvent.getMergeToCasefileId());
			   }
		   }
	   }
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02F0
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44C8E0DB02FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44C8E0DB02FF
    */
   public void update(Object anObject) 
   {
    
   } 
}
