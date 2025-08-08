//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\GetJIMSGenericAccountsCommand.java

package pd.security.authentication.registergenericaccount.transactions;

import java.util.HashMap;
import java.util.Iterator;

import messaging.authentication.registergenericaccount.reply.JIMSGenericAccountResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.registergenericaccount.GetJIMSGenericAccountsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.security.authentication.registergenericaccount.JIMSGenericAccountAccttypeView;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetJIMSGenericAccountsCommand extends ResponseCommonUtil implements ICommand 
{
   
   /**
    * @roseuid 4562209A01BA
    */
   public GetJIMSGenericAccountsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456208BD00F2
    */
   public void execute(IEvent event) 
   {
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetJIMSGenericAccountsEvent gEvent = (GetJIMSGenericAccountsEvent) event;
		
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator gCreator =  null;
   		try {
			gCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.GENERICACCOUNT_RESPONSE_LOCATOR);
		} catch (ReflectionException e) {
			e.printStackTrace();
		}

		MetaDataResponseEvent metaData = (MetaDataResponseEvent) JIMSGenericAccountAccttypeView.findMeta(gEvent);
        if (metaData.getCount() > 2000){
        	CountInfoMessage infoEvent = new CountInfoMessage();
//        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
          	infoEvent.setCount(metaData.getCount());  
        	dispatch.postEvent(infoEvent);
        }else{		
        	Iterator iterator = JIMSGenericAccountAccttypeView.findAll(gEvent);
        	HashMap map = new HashMap();
        	while(iterator.hasNext()){
        		JIMSGenericAccountAccttypeView view = (JIMSGenericAccountAccttypeView) iterator.next();
        		String genericAccountId = view.getJimsGenericId();
        		if(view != null && !map.containsKey(genericAccountId)){
        			JIMSGenericAccountResponseEvent resp = (JIMSGenericAccountResponseEvent) gCreator.create(view);
        			dispatch.postEvent(resp);
        			map.put(genericAccountId,genericAccountId);
        		}
        	}	
   	    }
   }
   
   /**
    * @param event
    * @roseuid 456208BD00F4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456208BD00F6
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456208BD0101
    */
   public void update(Object anObject) 
   {
    
   }
}
