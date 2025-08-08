//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\DeleteSupervisionConditionCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Calendar;
import java.util.Date;

import pd.supervision.supervisionoptions.Condition;
import messaging.supervisionoptions.DeleteSupervisionConditionEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class DeleteSupervisionConditionCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C435001F
    */
   public DeleteSupervisionConditionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B0247
    */
   public void execute(IEvent event) 
   {
		DeleteSupervisionConditionEvent evt = (DeleteSupervisionConditionEvent)event;
		Condition condition = Condition.find( evt.getConditionId() );
		if ( condition != null ){
/*			if(evt.isTrueDelete()){ //  actual delete
//				// we are not deleting condition physically from database to avoid having to through all the SuggOrders
//				// that are using it and delete this OID from there
//				condition.setIsDeleted(true);
			    //We are now physically deleting
			    try
			    {
				condition.delete();
			    }
			    catch (Exception e)
			    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			}else{*/ // inactivate Order
				Calendar cal = Calendar.getInstance();
				cal.setTime( new Date() );
				cal.set( Calendar.HOUR_OF_DAY, 0 );
				cal.set( Calendar.MINUTE, 0 );
				cal.set( Calendar.SECOND, 0 );
				cal.set( Calendar.MILLISECOND, 0);
				condition.setInactiveDate(cal.getTime());
				condition.setReasonToInactivate(evt.getReasonToInactivate());
			}
		//}
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B0252
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3B0254
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A3B0256
    */
   public void update(Object anObject) 
   {
    
   }
   
}
