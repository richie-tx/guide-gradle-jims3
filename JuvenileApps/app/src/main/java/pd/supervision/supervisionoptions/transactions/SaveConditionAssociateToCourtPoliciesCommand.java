//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveConditionAssociateToCourtPoliciesCommand.java

package pd.supervision.supervisionoptions.transactions;

import pd.supervision.supervisionoptions.Condition;
import messaging.supervisionoptions.SaveConditionAssociateToCourtPoliciesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * Associate a single Condition to multiple Court Policies.
 *  
 * @author bschwartz
 */
public class SaveConditionAssociateToCourtPoliciesCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44A0203
    */
   public SaveConditionAssociateToCourtPoliciesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A760023
    */
   public void execute(IEvent event) 
   {
		SaveConditionAssociateToCourtPoliciesEvent evt = (SaveConditionAssociateToCourtPoliciesEvent)event; 
		
		Condition cond = Condition.find( evt.getConditionId() );
		// update associations
		cond.updateCourtPolicyAssociations(evt.getPolicyIds());
				
//		StringSet changedPolicyIds = new StringSet( evt.getPolicyIds() );
//		
//		StringSet currPolicyIds = new StringSet( cond.getCourtPolicies(), new StringSet.Stringizer() {
//			public String toString( Object obj )
//			{
//				return ((CourtPolicy)obj).getOID().toString();
//			}
//		});
//		
//
//		// new associations
//		StringSet newAssociations = changedPolicyIds.complement(currPolicyIds);
//		Iterator policyIds = newAssociations.iterator();
//		while ( policyIds.hasNext() )
//		{
//			String policyId = (String)policyIds.next();
//			CourtPolicy policy = CourtPolicy.find( policyId );
//			cond.insertCourtPolicies(policy);
//		}
//		
//		// removed associations 
//		StringSet removedAssociations = currPolicyIds.complement(changedPolicyIds);
//		policyIds = removedAssociations.iterator();
//		while ( policyIds.hasNext() )
//		{
//			String policyId = (String)policyIds.next();
//			CourtPolicy policy = CourtPolicy.find( policyId );
//			cond.removeCourtPolicies(policy);
//		}

   }
   
   /**
    * @param event
    * @roseuid 42F79A76002F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A760031
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A760033
    */
   public void update(Object anObject) 
   {
    
   }
   
}
