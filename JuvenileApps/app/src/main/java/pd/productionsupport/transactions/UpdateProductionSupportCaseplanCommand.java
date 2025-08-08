package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.productionsupport.UpdateProductionSupportCaseplanEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.caseplan.CasePlan;

public class UpdateProductionSupportCaseplanCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportCaseplanCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportCaseplan");
	   UpdateProductionSupportCaseplanEvent updateEvent = (UpdateProductionSupportCaseplanEvent) event;
	   GetCaseplanDetailsEvent specificSearchEvent = new GetCaseplanDetailsEvent();
	   specificSearchEvent.setSupervisionNumber(updateEvent.getCasefileId());
	   Iterator casePlanIter = CasePlan.findAll(specificSearchEvent);
	   while(casePlanIter.hasNext()){
		   CasePlan caseplan = (CasePlan)casePlanIter.next();
		   caseplan.setSupervisionNumber(updateEvent.getMergeToCasefileId());
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
