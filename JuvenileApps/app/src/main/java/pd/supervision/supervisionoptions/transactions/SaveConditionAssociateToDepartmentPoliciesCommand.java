//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\SaveConditionAssociateToDepartmentPoliciesCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.AgencyPolicy;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.StringSet;
import messaging.supervisionoptions.SaveConditionAssociateToDepartmentPoliciesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveConditionAssociateToDepartmentPoliciesCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C44B0157
    */
   public SaveConditionAssociateToDepartmentPoliciesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A750292
    */
   public void execute(IEvent event) 
   {
		SaveConditionAssociateToDepartmentPoliciesEvent evt = (SaveConditionAssociateToDepartmentPoliciesEvent)event; 
		Condition cond = Condition.find( evt.getConditionId() );
			
		StringSet changedPolicyIds = new StringSet( evt.getPolicyIds() );
			
		StringSet currPolicyIds = new StringSet( cond.getAgencyPolicies(), new StringSet.Stringizer() {
			public String toString( Object obj )
			{
				return ((AgencyPolicy)obj).getOID().toString();
			}
		});
			
	
		// new associations
		StringSet newAssociations = changedPolicyIds.complement(currPolicyIds);
		Iterator policyIds = newAssociations.iterator();
		while ( policyIds.hasNext() )
		{
			String policyId = (String)policyIds.next();
			AgencyPolicy policy = AgencyPolicy.find( policyId );
			cond.insertAgencyPolicies(policy);
			// TESTING to avoid deadlock issue
//			new Home().bind(policy);
		}
			
		// removed associations 
		StringSet removedAssociations = currPolicyIds.complement(changedPolicyIds);
		policyIds = removedAssociations.iterator();
		while ( policyIds.hasNext() )
		{
			String policyId = (String)policyIds.next();
			AgencyPolicy policy = AgencyPolicy.find( policyId );
			cond.removeAgencyPolicies(policy);
		}
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A75029F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A7502A1
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A7502A3
    */
   public void update(Object anObject) 
   {
    
   }
   
}
