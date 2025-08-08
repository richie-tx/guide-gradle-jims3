//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\UpdateJIMSGenericAccountCommand.java

package pd.security.authentication.registergenericaccount.transactions;

import java.util.Iterator;

import messaging.authentication.registergenericaccount.reply.VerificationGenericAccountResponseEvent;
import messaging.registergenericaccount.VerifyJIMSGenericAccountEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.security.authentication.registergenericaccount.JIMSGenericAccount;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VerifyJIMSGenericAccountCommand implements ICommand 
{
   
   /**
    * @roseuid 456220A10082
    */
   public VerifyJIMSGenericAccountCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456208BC02D6
    */
   public void execute(IEvent event) 
   {
   	    VerifyJIMSGenericAccountEvent vEvent = (VerifyJIMSGenericAccountEvent) event;
   	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
   	    Iterator iter = JIMSGenericAccount.findAll("logonId",vEvent.getLogonId().toUpperCase());
   	    if(iter.hasNext()){
   	    	iter.next();
   	    	VerificationGenericAccountResponseEvent vResp = new VerificationGenericAccountResponseEvent();
   	    	vResp.setMessage("error.registergenericaccount.duplicateLogonId");
   	    	dispatch.postEvent(vResp);
   	    }   	    	
   }
   
   /**
    * @param event
    * @roseuid 456208BC02E5
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456208BC02E7
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456208BC02E9
    */
   public void update(Object anObject) 
   {
    
   }
}
