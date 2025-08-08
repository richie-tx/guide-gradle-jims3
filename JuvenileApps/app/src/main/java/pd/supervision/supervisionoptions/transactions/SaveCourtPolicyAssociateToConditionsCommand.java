//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveCourtPolicyAssociateToConditionsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.CourtPolicy;
import pd.supervision.supervisionoptions.StringSet;
import messaging.supervisionoptions.SaveCourtPolicyAssociateToConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveCourtPolicyAssociateToConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44C00AB
    */
   public SaveCourtPolicyAssociateToConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A750050
    */
   public void execute(IEvent event) 
   {
		SaveCourtPolicyAssociateToConditionsEvent evt = (SaveCourtPolicyAssociateToConditionsEvent)event;
    
		CourtPolicy policy = CourtPolicy.find( evt.getPolicyId() );
		
		StringSet changedConditionIds = new StringSet( evt.getConditionIds() );

		StringSet currConditionIds = new StringSet( policy.getConditions(), new StringSet.Stringizer() {
			public String toString( Object obj )
			{
				return ((Condition)obj).getOID().toString();
			}
		});
			
		// new associations
		StringSet newAssociations = changedConditionIds.complement(currConditionIds);
		Iterator conditionIds = newAssociations.iterator();
		while ( conditionIds.hasNext() )
		{
			String conditionId = (String)conditionIds.next();
			Condition cond = Condition.find( conditionId );
			policy.insertConditions(cond);
		}
			
		// removed associations
		StringSet removedAssociations = currConditionIds.complement(changedConditionIds);
		conditionIds = removedAssociations.iterator();
		while ( conditionIds.hasNext() )
		{
			String conditionId = (String)conditionIds.next();
			Condition cond = Condition.find( conditionId );
			policy.removeConditions(cond);
		}

   }
   
   /**
    * @param event
    * @roseuid 42F79A750052
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A75005E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A750060
    */
   public void update(Object anObject) 
   {
    
   }
   
}
