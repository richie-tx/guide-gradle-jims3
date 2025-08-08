//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveCourtPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import pd.codetable.supervision.SupervisionCode;
import pd.supervision.supervisionoptions.CourtPolicy;
import pd.supervision.supervisionoptions.PolicySupervisionOption;
import pd.supervision.supervisionoptions.StringSet;
import pd.supervision.supervisionoptions.VariableElementHelper;
import messaging.supervisionoptions.SaveCourtPolicyEvent;
import messaging.supervisionoptions.reply.CourtPolicyDetailResponseEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class SaveCourtPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44D002E
    */
   public SaveCourtPolicyCommand()
   {
   }
   
   /**
	* @param event
	* @roseuid 42F7997D0011
	*/
   public void execute(IEvent event) 
   {
		SaveCourtPolicyEvent evt = (SaveCourtPolicyEvent)event;
		CourtPolicy policy;
	    	
		if ( evt.getCourtPolicyId() == null )
		{
			// new condition
			policy = new CourtPolicy();
		}
		else
		{
			// existing condition
			policy = CourtPolicy.find( evt.getCourtPolicyId() );
		}
	
		// check for duplicate name
		if(policy.isDuplicate(evt.getCourtPolicyId(), evt.getName(), evt.getAgencyId())){
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(evt.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
		}else{
			policy.setAgencyId( evt.getAgencyId() );
			policy.setDescription( evt.getDescription() );
			policy.setEffectiveDate( evt.getEffectiveDate() );
			policy.setInactiveDate( evt.getInactiveDate() );
			policy.setIsStandard( evt.isStandard() );
			policy.setName( evt.getName() );
			policy.setNotes( evt.getNotes() );
			String oldGroup = policy.getGroupId();
			policy.validateAssociationsForGroups(evt.getGroupId());
			policy.setGroupId( evt.getGroupId() );
			
            //--------------------------EventTypes--------------------------------//
            StringSet exEventTypes = createCondSet(policy.getSupervisionEventTypes());
            StringSet passedEventTypes = createCondSet(evt.getEventTypes());
            Set newEventTypes = passedEventTypes.complement(exEventTypes);
            Set removedEventTypes = exEventTypes.complement(passedEventTypes);
            //new EventTypes
            for(Iterator iter = newEventTypes.iterator(); iter.hasNext(); ) {
                String eventTypeId = (String)iter.next();
                if (!eventTypeId.equals("")) {
                    SupervisionCode code = SupervisionCode.find(eventTypeId);
                    if (code != null) {
                        policy.insertSupervisionEventTypes(code);
                    }
                }
            }
            // removed EventTypes
            for(Iterator iter = removedEventTypes.iterator(); iter.hasNext(); ) {
                String eventTypeId = (String)iter.next();
                if (!eventTypeId.equals("")) {
                    SupervisionCode code = SupervisionCode.find(eventTypeId);
                    policy.removeSupervisionEventTypes(code);
                }
            }
            
//			policy.clearSupervisionEventTypes();
//			Iterator eventTypes = evt.getEventTypes().iterator();
//			while ( eventTypes.hasNext() )
//			{
//				String eventTypeId = (String)eventTypes.next();
//				SupervisionCode eventType = SupervisionCode.find( eventTypeId );
//				policy.insertSupervisionEventTypes( eventType );
//			}
		
			policy.setEventCount( evt.getEventCountValue() );
			policy.setPeriodValue( evt.getEventPeriodValue() );
			policy.setPeriodId( evt.getEventPeriodTypeId() );

//			if ( VariableElementHelper.saveVariableElements( policy.getPolicySupervisionOptions(), evt.getVariableElements(), PolicySupervisionOption.class ) )
//			{
//				policy.validateAssociationsForCourts();
//			}
			
			if ( VariableElementHelper.saveCourtPolicyVariableElements( policy, evt.getVariableElements(), PolicySupervisionOption.class ) )
			{
				policy.validateAssociationsForCourts();
			}

			policy.setIsDepartmentPolicy(evt.isDepartmentPolicy());

			//TODO Out of scope 
			//   private Collection evt.taskRecipients;
			//   private String evt.taskSubject;
			//   private int evt.taskDueBy;
			//   private String evt.emailNotificationTo;

			// post resp event back
			CourtPolicyDetailResponseEvent reply = new CourtPolicyDetailResponseEvent(); 
			reply.setPolicyId( policy.getOID().toString() );
			reply.setName( policy.getName() );
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(reply);
		}
   }
   
   static private StringSet createCondSet(Collection objectIds) {
       StringSet set = new StringSet(objectIds, new StringSet.Stringizer() {
           public String toString(Object obj) {
               return obj.toString();
           }
       });
       return set;
   }

   /**
    * @param event
    * @roseuid 42F7997D001F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997D0021
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997D0023
    */
   public void update(Object anObject) 
   {
    
   }
   
}
