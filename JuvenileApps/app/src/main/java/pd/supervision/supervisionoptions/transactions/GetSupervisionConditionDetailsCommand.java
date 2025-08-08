//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetSupervisionConditionDetailsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.supervisionoptions.GetSupervisionConditionDetailsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;
import pd.codetable.supervision.SupervisionCode;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.VariableElementHelper;
import pd.supervision.supervisionorder.SupervisionOrderCondition;

public class GetSupervisionConditionDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44603B9
    */
   public GetSupervisionConditionDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A39033E
    */
   public void execute(IEvent event) 
   {
    	GetSupervisionConditionDetailsEvent evt = (GetSupervisionConditionDetailsEvent)event;
    	
    	String conditionId = evt.getConditionId();
    	
		ConditionDetailResponseEvent reply = new ConditionDetailResponseEvent(); 
    	
    	Condition cond = Condition.find( conditionId );
    	
    	reply.setConditionId( cond.getOID().toString() );
		reply.setName( cond.getName() );

		Group group = cond.getGroup();
		if(group != null){			
			Group[] groups = group.getGroupList();
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
		}
		    
		if(cond.getIsSpecialCondition()){
			reply.setSpecialCondition(true);
			SupervisionOrderCondition orderCond = SupervisionOrderCondition.findBySpecialCondition(cond.getOID().toString());
			if(orderCond != null){
				reply.setResolvedDescription(orderCond.getResolvedDescription());
			}
		}

		reply.setDescription( cond.getDescription() );
		reply.setInactiveReason(cond.getReasonToInactivate());
        reply.setSpanishDescription(cond.getSpanishDescription());
        
		reply.setEffectiveDate( cond.getEffectiveDate() );
		reply.setInactiveDate( cond.getInactiveDate() );
		reply.setStandard( cond.getIsStandard() );
		reply.setNotes( cond.getNotes() );
		reply.setSeverityLevelId( cond.getSeverityLevelId() );
		
		Iterator docs = cond.getDocuments().iterator();
		while ( docs.hasNext() )
		{
			SupervisionCode codeAssoc = (SupervisionCode)docs.next();
			reply.addDocument( codeAssoc.getOID().toString() );
		}
    
		reply.setJurisdictionId( cond.getJurisdictionId() );
	
		Iterator evtTypes = cond.getSupervisionEventTypes().iterator();
		while ( evtTypes.hasNext() )
		{
			SupervisionCode eventType = (SupervisionCode)evtTypes.next();
			reply.addEventType( eventType.getOID().toString() );
		}

		Iterator supTypes = cond.getSupervisionTypes().iterator();
		while ( supTypes.hasNext() )
		{
			Code supTypeCode = (Code)supTypes.next();
			reply.addSupervisionType(supTypeCode.getCode());
		}
    
		reply.setEventCount( cond.getEventCount() );
		reply.setEventPeriodValue( cond.getPeriodValue() );
		reply.setEventPeriodTypeId( cond.getPeriodId() );
		
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

//		VariableElementHelper.createVariableElementResponses( cond.getConditionSupervisionOptions(), reply.getVariableElements() );
		List veres = VariableElementHelper.createConditionVariableElementResponses(cond);
        reply.setVariableElements(veres);
    
        // associated court policies
		CommonSupervisionHelper.postAssociatedCourtPolicies(conditionId);

        // associated department policies
        CommonSupervisionHelper.postAssociatedAgencyPolicies(conditionId);
        
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
   }
   
   /**
    * @param event
    * @roseuid 42F79A390340
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A39034B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A39034D
    */
   public void update(Object anObject) 
   {
    
   }
   
}
