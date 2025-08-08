package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportIViewDocEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.interviewinfo.InterviewDocument;

public class UpdateProductionSupportIViewDocCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportIViewDocCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportIViewDoc");
	   UpdateProductionSupportIViewDocEvent updateEvent = (UpdateProductionSupportIViewDocEvent) event;
	   Iterator<InterviewDocument> interviewDocumentIter = InterviewDocument.findAllForCasefile(updateEvent.getCasefileId());
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   while(interviewDocumentIter.hasNext()){
			   InterviewDocument interviewDocument = (InterviewDocument)interviewDocumentIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   interviewDocument.setCasefileId(updateEvent.getMergeToCasefileId());
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
