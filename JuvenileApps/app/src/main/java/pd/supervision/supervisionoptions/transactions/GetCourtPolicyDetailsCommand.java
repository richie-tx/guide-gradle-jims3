//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetCourtPolicyDetailsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.supervisionoptions.GetCourtPolicyDetailsEvent;
import messaging.supervisionoptions.reply.CourtPolicyDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.CourtPolicy;
import pd.supervision.supervisionoptions.VariableElementHelper;

public class GetCourtPolicyDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4410261
    */
   public GetCourtPolicyDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B01C9
    */
   public void execute(IEvent event) 
   {
		GetCourtPolicyDetailsEvent evt = (GetCourtPolicyDetailsEvent)event;
	    	
		String courtPolicyId = evt.getCourtPolicyId();
	    	
		CourtPolicyDetailResponseEvent reply = new CourtPolicyDetailResponseEvent(); 
	    	
		CourtPolicy policy = CourtPolicy.find( courtPolicyId );
	    	
		reply.setPolicyId( policy.getOID().toString() );
		reply.setName( policy.getName() );
	    	
		Group[] groups = policy.getGroup().getGroupList();
		if ( groups[0] != null )
		{
			reply.setGroup1Id(groups[0].getOID().toString());
			reply.setGroup1Name(groups[0].getGroupName());
		}
		if ( groups[1] != null )
		{
			reply.setGroup2Id(groups[1].getOID().toString());
			reply.setGroup2Name(groups[1].getGroupName());
		}
		if ( groups[2] != null )
		{
			reply.setGroup3Id(groups[2].getOID().toString());
			reply.setGroup3Name(groups[2].getGroupName());
		}
	    
		reply.setDescription( policy.getDescription() );
		reply.setEffectiveDate( policy.getEffectiveDate() );
		reply.setInactiveDate( policy.getInactiveDate() );
		reply.setStandard( policy.getIsStandard() );
		reply.setNotes( policy.getNotes() );
			
		Iterator evtTypes = policy.getSupervisionEventTypes().iterator();
		while ( evtTypes.hasNext() )
		{
			SupervisionCode eventType = (SupervisionCode)evtTypes.next();
			reply.addEventType( eventType.getOID().toString() );
		}
	    
		reply.setEventCountValue( policy.getEventCount() );
		reply.setEventPeriodValue( policy.getPeriodValue() );
		reply.setEventPeriodTypeId( policy.getPeriodId() );
		reply.setDepartmentPolicy(policy.isDepartmentPolicy());
		//TODO JBS - Finish Task Recipient
		//Iterator evtTypes = cond.getDocu??ments().iterator();
		//while ( evtTypes.hasNext() )
		//{
		//	reply.addTaskRecipient( (String)evtTypes.next() );
		//}
	
		//TODO JBS - Finish
		//reply.setTaskSubject( cond.?? );
		//reply.setTaskDueBy( cond.?? );
		//reply.setEmailNotificationTo( cond.?? );
	
//		VariableElementHelper.createVariableElementResponses( policy.getPolicySupervisionOptions(), reply.getVariableElements() );
		List veres = VariableElementHelper.createCourtPolicyVariableElementResponses(policy);
        reply.setVariableElements(veres);
        // associated conditions
		CommonSupervisionHelper.postAssociatedConditionsForCourtPolicy(courtPolicyId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
	}
   
   /**
    * @param event
    * @roseuid 42F7997B01CB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B01D4
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997B01D6
    */
   public void update(Object anObject) 
   {
    
   }
   
}
