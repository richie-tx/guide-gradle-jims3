package pd.juvenilecase.transactions;


import java.util.Iterator;

import messaging.juvenilecase.GetCustomFacilityAdmitReasonPopEvent;
import messaging.juvenilecase.reply.FacilityAdmitReasonResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSFacilityReport;

public class GetCustomFacilityAdmitReasonPopCommand implements ICommand 

{

 /**
  * @roseuid 45702FB40066
  */
 public GetCustomFacilityAdmitReasonPopCommand() 
 {
 }

 /**
  * @param event
  * @roseuid 456F2D850264
  */


 
	public void execute(IEvent event) 
	{
		GetCustomFacilityAdmitReasonPopEvent evt = (GetCustomFacilityAdmitReasonPopEvent) event;
		Iterator facilityItr = JJSFacilityReport.findAll(evt);
		
		FacilityAdmitReasonResponseEvent respEvent = new FacilityAdmitReasonResponseEvent();		
		JJSFacilityReport facility = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		while (facilityItr.hasNext()) 
		{
			//iterate and get values for each last sequence number value
			facility = (JJSFacilityReport) facilityItr.next();

			respEvent = facility.valueObject2();				
			
			dispatch.postEvent(respEvent); 
			
		}//end of facility iteration
	}

 
 
 /**
  * @param event
  * @roseuid 456F2D850272
  */
 public void onRegister(IEvent event)
 { 

 }

 
 /**
  * @param event 
  * @roseuid 456F2D850274
  */

 public void onUnregister(IEvent event) 
 {

 }

 /**
  * @param anObject
  * @roseuid 456F2D850276 
  */

 public void update(Object anObject) 
 { 

 }

 
}