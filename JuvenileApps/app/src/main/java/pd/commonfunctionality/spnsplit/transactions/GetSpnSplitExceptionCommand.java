//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\commonfunctionality\\spnsplit\\transactions\\ProcessSpnSplitCommand.java

package pd.commonfunctionality.spnsplit.transactions;

import java.util.Iterator;

import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.SupervisionConstants;
import pd.common.CommandUtil;
import pd.commonfunctionality.spnsplit.SpnSplitException;

public class GetSpnSplitExceptionCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 4561E29E01D9
    */
   public GetSpnSplitExceptionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 455E28BC0164
    */
   public void execute(IEvent event) 
   {
   	    Iterator iter = SpnSplitException.findAll();
   	    SpnSplitException spe = null;
   	    while(iter.hasNext()){
   	    	spe = (SpnSplitException) iter.next();
   	    	if(spe != null && SupervisionConstants.ACTIVE_STATUS.equalsIgnoreCase(spe.getStatusId())){
	   	    	SpnSplitErrorResponseEvent resp = spe.getResponseEvent();
	   	    	MessageUtil.postReply(resp);
   	    	}
   	    }
   }
   
   /**
    * @param event
    * @roseuid 455E28BC0166
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 455E28BC0172
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 455E28BC0174
    */
   public void update(Object anObject) 
   {
    
   }
}
