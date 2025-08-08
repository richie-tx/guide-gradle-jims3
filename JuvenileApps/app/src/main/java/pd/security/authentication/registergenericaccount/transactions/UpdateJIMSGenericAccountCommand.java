//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\UpdateJIMSGenericAccountCommand.java

package pd.security.authentication.registergenericaccount.transactions;

import naming.PDConstants;
import naming.PDSecurityConstants;
import messaging.registergenericaccount.UpdateJIMSGenericAccountEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.security.authentication.registergenericaccount.JIMSGenericAccount;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateJIMSGenericAccountCommand implements ICommand 
{
   
   /**
    * @roseuid 456220A10082
    */
   public UpdateJIMSGenericAccountCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456208BC02D6
    */
   public void execute(IEvent event) 
   {
   	    UpdateJIMSGenericAccountEvent uEvent = (UpdateJIMSGenericAccountEvent) event;
   	    String genericAccountId = uEvent.getGenericAccountId();
   	    JIMSGenericAccount acct = null;
   	    if(genericAccountId != null && !genericAccountId.equals("")){
   	    	acct = JIMSGenericAccount.findByOid(genericAccountId);
   	    	if(acct != null){
   	    		acct.setJimsGenericAccount(uEvent);
   	    	}
   	    }else{
   	    	acct = new JIMSGenericAccount();
   	    	acct.setJimsGenericAccount(uEvent);
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
