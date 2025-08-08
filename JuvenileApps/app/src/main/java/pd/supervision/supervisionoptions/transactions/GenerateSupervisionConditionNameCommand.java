//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\GenerateSupervisionConditionNameCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.Group;
import pd.supervision.supervisionoptions.Condition;
import pd.supervision.supervisionoptions.GroupSequenceGenerator;
import messaging.supervisionoptions.GenerateSupervisionConditionNameEvent;
import messaging.supervisionoptions.reply.SupervisionConditionNameResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GenerateSupervisionConditionNameCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C437037A
    */
   public GenerateSupervisionConditionNameCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3A0159
    */
   public void execute(IEvent event) 
   {
		GenerateSupervisionConditionNameEvent evt = (GenerateSupervisionConditionNameEvent)event;
		SupervisionConditionNameResponseEvent resp = new SupervisionConditionNameResponseEvent();
		//Condition con = new Condition();
	    //DuplicateRecordErrorResponseEvent respErr = new DuplicateRecordErrorResponseEvent();
		
		// From ManageSupervisionOptions.doc:
		// "The name will be the group 1 association followed by a unique 4 digit sequential number 
		// and a 3 digit abbreviation ( conditions = CON; Department Policy = DEP; Court Policy = CRT (CSCD); 
		// Court Policy = COM (Pretrial) ) on the end."

		GroupSequenceGenerator generator = GroupSequenceGenerator.find( evt );
		 		
		Group group = Group.find( evt.getGroupId() );
		StringBuffer newName = new StringBuffer (group.getGroupName().toUpperCase());
		newName.append(generator.nextSequenceAsString(4));
	    newName = newName.append(" - CON");
	    String finalName = newName.toString();

		// make sure that name retirned to ui is unique
		boolean cont = true;
  		while (cont ){	
			 Iterator iter = Condition.findAll("name", finalName);	
			 if(iter.hasNext()){
			    StringBuffer newName1 = new StringBuffer(group.getGroupName().toUpperCase());
				newName1.append(generator.nextSequenceAsString(4));
				newName = newName1.append(" - CON");
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
    * @roseuid 42F79A3A015B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A3A015D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A3A0167
    */
   public void update(Object anObject) 
   {
    
   }
   
}
