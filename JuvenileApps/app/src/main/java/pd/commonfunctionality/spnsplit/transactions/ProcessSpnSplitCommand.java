//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\commonfunctionality\\spnsplit\\transactions\\ProcessSpnSplitCommand.java

package pd.commonfunctionality.spnsplit.transactions;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import naming.SupervisionConstants;

import org.apache.commons.collections.FastArrayList;

import messaging.spnsplit.ProcessSpnSplitEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.CollectionUtil;
import pd.common.CommandUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.commonfunctionality.spnconsolidation.SpnConsolidationConfig;
import pd.supervision.legacyupdates.HandlerConfig;
import pd.supervision.supervisionorder.SpnOrderHandler;

public class ProcessSpnSplitCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 4561E29E01D9
    */
   public ProcessSpnSplitCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 455E28BC0164
    */
   public void execute(IEvent event) 
   {
 	    ProcessSpnSplitEvent requestEvent = (ProcessSpnSplitEvent) event;
        try
        {
        	Iterator iter = HandlerConfig.findAll();
        	ISpnHandler iSpnHandler = null;
        	SortedMap map = new TreeMap();
        	while(iter.hasNext()){
        		HandlerConfig hc = (HandlerConfig) iter.next();
        		if(SupervisionConstants.LOCATORKEY_SPNORDHL.equalsIgnoreCase(hc.getHandlerLocatorKey())){
        			iSpnHandler = getHandlerObject(hc.getHandlerName());
        			map.put("1", iSpnHandler);
        		}else if(SupervisionConstants.LOCATORKEY_SPNCNTHL.equalsIgnoreCase(hc.getHandlerLocatorKey())){
        			iSpnHandler = getHandlerObject(hc.getHandlerName());
        			map.put("2", iSpnHandler);
        		}
        	}
        	
			Iterator iterator = map.values().iterator();
			while(iterator.hasNext()){
				iSpnHandler = (ISpnHandler) iterator.next();
				iSpnHandler.split(requestEvent);
			}
        }
        catch (Exception e)
        {
            ReturnException returnException = new ReturnException(e.getMessage(), e);
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(returnException);
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
