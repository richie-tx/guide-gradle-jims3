//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\csserviceprovider\\transactions\\GetSPProgramCommand.java

package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import pd.supervision.administerserviceprovider.csserviceprovider.CSProgram;
import pd.supervision.administerserviceprovider.csserviceprovider.CSProgramHelper;
import messaging.csserviceprovider.GetSPProgramEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.context.ICommand;

public class GetSPProgramCommand implements ICommand 
{
   
   /**
    * @roseuid 4786797F00CE
    */
   public GetSPProgramCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C13021B
    */
   public void execute(IEvent event) 
   {
  			//retrieve the specified service provider program
       GetSPProgramEvent get_prog_event = (GetSPProgramEvent)event;
       CSProgram program = 
           CSProgramHelper.getProgram(get_prog_event.getProgramId());
       
       		//post response with program details
       CSProgramResponseEvent prog_response =
           CSProgramHelper.getProgramResponseEvent(program);
       MessageUtil.postReply(prog_response);    
   }//end of execute()
   
   /**
    * @param event
    * @roseuid 47856C13021D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47856C13022B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47856C13023A
    */
   public void update(Object anObject) 
   {
    
   }
   
}
