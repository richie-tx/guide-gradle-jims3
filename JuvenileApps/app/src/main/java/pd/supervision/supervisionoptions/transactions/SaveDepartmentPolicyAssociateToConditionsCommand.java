//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveDepartmentPolicyAssociateToConditionsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.AgencyPolicy;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.StringSet;
import messaging.supervisionoptions.SaveDepartmentPolicyAssociateToConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveDepartmentPolicyAssociateToConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44D03B9
    */
   public SaveDepartmentPolicyAssociateToConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7500AF
    */
   public void execute(IEvent event) 
   {
		SaveDepartmentPolicyAssociateToConditionsEvent evt = (SaveDepartmentPolicyAssociateToConditionsEvent)event;
	    
		AgencyPolicy policy = AgencyPolicy.find(evt.getPolicyId());
			
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
    * @roseuid 42F79A7500BC
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7500BE
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A7500C0
    */
   public void update(Object anObject) 
   {
    
   }
   
}
