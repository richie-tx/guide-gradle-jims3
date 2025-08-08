//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GenerateDepartmentPolicyNameCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.Condition;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.GroupSequenceGenerator;
import messaging.supervisionoptions.GenerateDepartmentPolicyNameEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyNameResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GenerateDepartmentPolicyNameCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43603A9
    */
   public GenerateDepartmentPolicyNameCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A080218
    */
   public void execute(IEvent event) 
   {
		GenerateDepartmentPolicyNameEvent evt = (GenerateDepartmentPolicyNameEvent)event;
			
		DepartmentPolicyNameResponseEvent resp = new DepartmentPolicyNameResponseEvent();
			
		// From ManageSupervisionOptions.doc:
		// "The name will be the group 1 association followed by a unique 4 digit sequential number 
		// and a 3 digit abbreviation ( conditions = CON; Department Policy = DEP; Court Policy = CRT (CSCD); 
		// Court Policy = COM (Pretrial) ) on the end."
		
		GroupSequenceGenerator generator = GroupSequenceGenerator.find( evt );
	    Group group = Group.find( evt.getGroupId() );
	    StringBuffer newName = new StringBuffer (group.getGroupName().toUpperCase());
	    newName.append(generator.nextSequenceAsString(4));
		newName = newName.append(" - DEP");
		String finalName = newName.toString();

			// make sure that name retirned to ui is unique
			boolean cont = true;
			while (cont ){	
				 Iterator iter = Condition.findAll("name", finalName);	
				 if(iter.hasNext()){
					StringBuffer newName1 = new StringBuffer(group.getGroupName().toUpperCase());
					newName1.append(generator.nextSequenceAsString(4));
					newName = newName1.append(" - DEP");
					finalName = newName.toString();
				 }else{
					cont = false; 
				 }
			 }
			resp.setName( finalName );
			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent( resp );
   }
   
   /**
    * @param event
    * @roseuid 42F79A080222
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A080224
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A080226
    */
   public void update(Object anObject) 
   {
    
   }
   
}
