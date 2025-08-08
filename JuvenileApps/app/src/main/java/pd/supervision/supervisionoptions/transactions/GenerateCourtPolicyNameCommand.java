//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GenerateCourtPolicyNameCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.Group;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.GroupSequenceGenerator;
import messaging.supervisionoptions.GenerateCourtPolicyNameEvent;
import messaging.supervisionoptions.reply.CourtPolicyNameResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GenerateCourtPolicyNameCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C43503C8
    */
   public GenerateCourtPolicyNameCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C0217
    */
   public void execute(IEvent event) 
   {
		GenerateCourtPolicyNameEvent evt = (GenerateCourtPolicyNameEvent)event;
			
		CourtPolicyNameResponseEvent resp = new CourtPolicyNameResponseEvent();
			
		// From ManageSupervisionOptions.doc:
		// "The name will be the group 1 association followed by a unique 4 digit sequential number 
		// and a 3 digit abbreviation ( conditions = CON; Department Policy = DEP; Court Policy = CRT (CSCD); 
		// Court Policy = COM (Pretrial) ) on the end."
		
		GroupSequenceGenerator generator = GroupSequenceGenerator.find( evt );
	    Group group = Group.find( evt.getGroupId() );
		
	    //modified the String to StringBuffer
	    StringBuffer newName = new StringBuffer (group.getGroupName().toUpperCase());
		newName.append(generator.nextSequenceAsString(4));
		if(evt.getAgencyId()!=null && (evt.getAgencyId().equalsIgnoreCase("PTS") ||  evt.getAgencyId().equalsIgnoreCase("PTR"))){
			newName = newName.append(" - COM");
		}
		else
			newName = newName.append(" - CRT");
		String finalName = newName.toString();

			// make sure that name retirned to ui is unique
			boolean cont = true;
			while (cont ){	
				 Iterator iter = Condition.findAll("name", finalName);	
				 if(iter.hasNext()){
					StringBuffer newName1 = new StringBuffer(group.getGroupName().toUpperCase());
					newName1.append(generator.nextSequenceAsString(4));
					if(evt.getAgencyId()!=null && evt.getAgencyId().equalsIgnoreCase("PTS")){
						newName = newName.append(" - COM");
					}
					else
						newName = newName.append(" - CRT");
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
    * @roseuid 42F7997C0222
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997C0224
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997C0226
    */
   public void update(Object anObject) 
   {
    
   }
   
}
