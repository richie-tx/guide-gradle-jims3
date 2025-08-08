//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\RetrieveCasePlanDocumentDataCommand.java

package pd.juvenilecase.caseplan.transactions;

import pd.juvenilecase.caseplan.CaseplanHelper;
import messaging.caseplan.RetrieveCasePlanDocumentDataEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class RetrieveCasePlanDocumentDataCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B83100E9
    */
   public RetrieveCasePlanDocumentDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6303A9
    */
   public void execute(IEvent event) 
   {
   		RetrieveCasePlanDocumentDataEvent evt = (RetrieveCasePlanDocumentDataEvent)event;
   		if(evt.isResidential()) {
   			CaseplanHelper.retrieveResidentialCaseplanData(evt);
   		} else {
   			CaseplanHelper.retrieveOtherCaseplanData(evt);
   		}
   		

   		
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6303B2
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6303B4
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45119A6303BC
    */
   public void update(Object anObject) 
   {
    
   }
   
}
