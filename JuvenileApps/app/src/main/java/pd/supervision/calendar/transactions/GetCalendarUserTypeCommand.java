//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetCalendarUserTypeCommand.java

package pd.supervision.calendar.transactions;

import java.util.Iterator;

import messaging.calendar.GetCalendarUserTypeEvent;
import messaging.calendar.reply.CalendarUserTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDOfficerProfileConstants;
import pd.contact.officer.OfficerProfile;

public class GetCalendarUserTypeCommand implements ICommand 
{
   
   /**
    * @roseuid 45F1B0E400AA
    */
   public GetCalendarUserTypeCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45F080F00340
    */
   public void execute(IEvent event)    
   {
   		GetCalendarUserTypeEvent utEvent = (GetCalendarUserTypeEvent)event;   		
   		Iterator iter = OfficerProfile.findAll("logonId",utEvent.getLogonId());
   		
 		while(iter.hasNext())
   		{		
   			OfficerProfile officer = (OfficerProfile)iter.next();
            if (officer.getStatusId().equals(PDCodeTableConstants.STATUS_ACTIVE)) {
            	CalendarUserTypeResponseEvent resp = new CalendarUserTypeResponseEvent();
            	if (officer.getOfficerTypeId().equals(PDOfficerProfileConstants.OFFICER_TYPE_JPO)){
            		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            		resp.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_JPO);
            		if (officer.getOfficerSubTypeId()!=null && officer.getOfficerSubTypeId().equals(PDOfficerProfileConstants.OFFICER_SUBTYPE_CLM)){
            			resp.setCalendarType(PDCalendarConstants.CALENDAR_TYPE_CLM);   					
            		}
            		resp.setOfficerId(officer.getOfficerProfileId());
            		resp.setOfficerLastName(officer.getLastName());
            		resp.setOfficerFirstName(officer.getFirstName());
            		resp.setOfficerMiddleName(officer.getMiddleName());
            		dispatch.postEvent(resp);
            	}  
			}
   		}
   		
   		
   		
   		
   	
   }
   
   /**
    * @param event
    * @roseuid 45F080F0034E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45F080F00350
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45F080F0035D
    */
   public void update(Object anObject) 
   {
    
   }
   

}
