//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GetDepartmentPolicyDetailsCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import messaging.supervisionoptions.GetDepartmentPolicyDetailsEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.Court;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.AgencyPolicy;
import pd.supervision.supervisionoptions.CommonSupervisionHelper;

public class GetDepartmentPolicyDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C4430196
    */
   public GetDepartmentPolicyDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A0800DC
    */
   public void execute(IEvent event) 
   {
		GetDepartmentPolicyDetailsEvent evt = (GetDepartmentPolicyDetailsEvent)event;
		    	
		String departmentPolicyId = evt.getDepartmentPolicyId();
		    	
		DepartmentPolicyDetailResponseEvent reply = new DepartmentPolicyDetailResponseEvent(); 
		    	
		AgencyPolicy policy = AgencyPolicy.find( departmentPolicyId );
		    	
		reply.setAgencyId( policy.getAgencyId() );
		reply.setDepartmentId( policy.getOID().toString() );
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
		reply.setNotes( policy.getNotes() );
				
		Iterator courts = policy.getCourts().iterator();
		while ( courts.hasNext() )
		{
			Court court = (Court)courts.next(); 
			//TODO What is the court ID?
			reply.addCourtId( court.getOID().toString() );
		}
		    
        // associated conditions
		CommonSupervisionHelper.postAssociatedConditionsForDepartmentPolicy(departmentPolicyId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);
   }
   
   /**
    * @param event
    * @roseuid 42F79A0800DE
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A0800EA
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A0800EC
    */
   public void update(Object anObject) 
   {
    
   }
   
}
