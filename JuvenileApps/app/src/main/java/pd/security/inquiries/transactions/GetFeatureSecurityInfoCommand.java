//Source file: C:\\views\\Security\\app\\src\\pd\\security\\transactions\\GetFeatureSecurityInfoCommand.java

package pd.security.inquiries.transactions;

import naming.ResponseLocatorConstants;
import messaging.inquiries.GetFeatureSecurityInfoEvent;
import messaging.security.inquiries.reply.FeatureSecurityInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Feature;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;

/* 
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetFeatureSecurityInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 44E9D2D600F5
    */
   public GetFeatureSecurityInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E9C8340272
    */
   public void execute(IEvent event) 
   {/*
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   GetFeatureSecurityInfoEvent aEvent = (GetFeatureSecurityInfoEvent) event;
	   Feature feature = Feature.find(aEvent.getFeatureId());
  		
  	   if(feature != null){
  	   	   ResponseContextFactory respFac = new ResponseContextFactory();
	   	   ResponseCreator srCreator =  null;
	   	   try {
			  srCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.FEATURE_SECURITY_INFO_RESPONSE_LOCATOR);
		   } catch (ReflectionException e) {
				e.printStackTrace();
		   }
		   FeatureSecurityInfoResponseEvent resp = (FeatureSecurityInfoResponseEvent) srCreator.create(feature);
	       dispatch.postEvent(resp);
  	   }*/ //87191
   }
   
   /**
    * @param event
    * @roseuid 44E9C8340274
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E9C8340276
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44E9C8340278
    */
   public void update(Object anObject) 
   {
    
   }
   
   
}
