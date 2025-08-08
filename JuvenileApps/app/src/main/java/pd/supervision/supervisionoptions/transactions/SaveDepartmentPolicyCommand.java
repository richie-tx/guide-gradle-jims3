//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveDepartmentPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.Court;
import pd.supervision.supervisionoptions.AgencyPolicy;
import pd.supervision.supervisionoptions.StringSet;
import messaging.supervisionoptions.SaveDepartmentPolicyEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyDetailResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class SaveDepartmentPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44E030D
    */
   public SaveDepartmentPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A08031E
    */
   public void execute(IEvent event) 
   {
		SaveDepartmentPolicyEvent evt = (SaveDepartmentPolicyEvent)event;
		AgencyPolicy policy;
		    	
		if ( evt.getDepartmentId() == null )
		{
			// new condition
			policy = new AgencyPolicy();
		}
		else
		{
			// existing condition
			policy = AgencyPolicy.find( evt.getDepartmentId() );
		}
		
		// check for duplicate name
		if(policy.isDuplicate(evt.getDepartmentId(), evt.getName(), evt.getAgencyId())){
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(evt.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
		}else{
			policy.setDescription( evt.getDescription() );
					
			policy.setEffectiveDate( evt.getEffectiveDate() );
			policy.setInactiveDate( evt.getInactiveDate() );
			policy.setName( evt.getName() );
			policy.setNotes( evt.getNotes() );
			policy.validateAssociationsForGroups(evt.getGroupId());
			policy.setGroupId( evt.getGroupId() );
			policy.setAgencyId(evt.getAgencyId());
			
			StringSet currentCourtIds = policy.getAllCourtIds();
			StringSet changedCourtIds = new StringSet(evt.getCourtIds());
			StringSet addedCourtIds = changedCourtIds.complement(currentCourtIds);
			StringSet removedCourtIds = currentCourtIds.complement(changedCourtIds);
			
			Iterator courtIds = removedCourtIds.iterator();
			while ( courtIds.hasNext() )
			{
				String courtId = (String)courtIds.next();
				Court court = Court.find( courtId );
				policy.removeCourts( court );
			}
			
			courtIds = addedCourtIds.iterator();
			while ( courtIds.hasNext() )
			{
				String courtId = (String)courtIds.next();
				Court court = Court.find( courtId );
				policy.insertCourts( court );
			}
			
			//policy.validateAssociationsForCourts();
			
			// post resp event back
			DepartmentPolicyDetailResponseEvent reply = new DepartmentPolicyDetailResponseEvent(); 
			reply.setDepartmentId( policy.getOID().toString() );
			reply.setName( policy.getName() );
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(reply);
			
		}
   }
   
   /**
    * @param event
    * @roseuid 42F79A080320
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A08032C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A08032E
    */
   public void update(Object anObject) 
   {
    
   }
   
}
