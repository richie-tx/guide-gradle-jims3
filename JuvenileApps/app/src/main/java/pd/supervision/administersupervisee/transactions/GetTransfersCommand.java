//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisee\\transactions\\GetSuperviseeDataCommand.java

package pd.supervision.administersupervisee.transactions;

import java.util.List;

import messaging.administersupervisee.GetTransfersEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administersupervisee.SuperviseeHelper;

public class GetTransfersCommand implements ICommand {
   
   /**
    * @roseuid 484E81420180
    */
   public GetTransfersCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4849421B00AA
    */
   public void execute(IEvent anEvent) {
	   GetTransfersEvent event = (GetTransfersEvent) anEvent;
	   List transfers = SuperviseeHelper.getTransfers(event);
	   MessageUtil.postReplies(transfers);
   }
   
/**
    * @param event
    * @roseuid 4849421B0202
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4849421B02BD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4849421B033B
    */
   public void update(Object anObject) 
   {
    
   }
}
