package pd.productionsupport.transactions;


import java.util.Iterator;

import messaging.productionsupport.UpdateProductionSupportSupervisionNumEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JuvenileTrait;

public class UpdateProductionSupportSupervisionNumCommand implements ICommand 
{
   
   /**
    * constructor
    */
   public UpdateProductionSupportSupervisionNumCommand()   
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
       UpdateProductionSupportSupervisionNumEvent updateEvent = (UpdateProductionSupportSupervisionNumEvent) event;
       IHome ihome = new Home();
       if(updateEvent.getCasefileId() != null && !(updateEvent.getCasefileId().equals("")) && 
		updateEvent.getMergeToCasefileId() != null && !(updateEvent.getMergeToCasefileId().equals(""))) {
	  // Iterator iter = JJSFacility.findAll("supervisionNum", updateEvent.getCasefileId());
	   Iterator <JJSFacility> iter = JJSFacility.findAll("bookingSupervisionNum", String.valueOf( updateEvent.getCasefileId() ));
	    while (iter.hasNext())
	    {
		JJSFacility facility = (JJSFacility) iter.next();
		if (updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0)
		{
		    facility.setBookingSupervisionNum(updateEvent.getMergeToCasefileId());
		    ihome.bind(facility);

		}
	    }
	   Iterator <JJSFacility> iter2 = JJSFacility.findAll("currentSupervisionNum", String.valueOf( updateEvent.getCasefileId() ));
	    while (iter2.hasNext())
	    {
		JJSFacility facility2 = (JJSFacility) iter2.next();
		if (updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0)
		{
		    facility2.setCurrentSupervisionNum(updateEvent.getMergeToCasefileId());
		    ihome.bind(facility2);
		}
	    }
	   
	    Iterator<JJSHeader> facilityHeaderIter = JJSHeader.findAll("bookingSupervisionNum", updateEvent.getCasefileId());
	    if (facilityHeaderIter != null)
	    {
		while (facilityHeaderIter.hasNext())
		{
		    JJSHeader facilityHeader = facilityHeaderIter.next();
		    if (facilityHeader != null)
		    {
			if (updateEvent.getMergeToCasefileId() != null && updateEvent.getMergeToCasefileId().length() > 0)
			{
			    facilityHeader.setBookingSupervisionNum(updateEvent.getMergeToCasefileId());
			    break;
			}
		    }
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
