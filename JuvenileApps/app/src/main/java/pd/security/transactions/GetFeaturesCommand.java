//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetFeaturesCommand.java

package pd.security.transactions;

import java.util.Iterator;

import messaging.info.reply.CountInfoMessage;
import messaging.security.GetFeaturesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.security.Feature;
import pd.security.PDSecurityHelper;

/**
 * 
 * 
 * @author mchowdhury
 * @description Get feature based on feature name and feature category  
 *
 */
public class GetFeaturesCommand implements ICommand 
{
   
   /**
	* @roseuid 4256F0A702CE
	*/
   public GetFeaturesCommand() 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F801B9
	*/
   public void execute(IEvent event) 
   {
   		GetFeaturesEvent featuresEvent = (GetFeaturesEvent) event;
   		/*	MetaDataResponseEvent metaData = (MetaDataResponseEvent) Feature.findMeta(featuresEvent);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        if (metaData.getCount() > 2000){
        	CountInfoMessage infoEvent = new CountInfoMessage();
//        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
          	infoEvent.setCount(metaData.getCount());  
        	dispatch.postEvent(infoEvent);
        }else{	  
        	Iterator features = Feature.findAll(featuresEvent);
        	PDSecurityHelper.sendFeaturesResponseEvent(features);
        }*/
   }
   
   /**
	* @param event
	* @roseuid 425551F801BB
	*/
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F801BD
	*/
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
	* @param anObject
	* @roseuid 425551F801BF
	*/
   public void update(Object anObject) 
   {
    
   }
}
