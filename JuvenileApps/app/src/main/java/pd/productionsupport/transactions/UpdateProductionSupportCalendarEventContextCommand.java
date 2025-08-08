package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportCalendarEventContextEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.common.calendar.CalendarEventContext;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;

public class UpdateProductionSupportCalendarEventContextCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportCalendarEventContextCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	   System.out.println("updateProductionSupportCalendarEventContext");
	   UpdateProductionSupportCalendarEventContextEvent updateEvent = (UpdateProductionSupportCalendarEventContextEvent) event;
	   Iterator<CalendarEventContext> calendarContextIter = CalendarEventContext.findAll("caseFileId", updateEvent.getCasefileId());
	   if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
				updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
		   while(calendarContextIter.hasNext()){
			   CalendarEventContext calendarContext = (CalendarEventContext)calendarContextIter.next();
			   if(updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0){
				   calendarContext.setCaseFileId(updateEvent.getMergeToCasefileId());
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
