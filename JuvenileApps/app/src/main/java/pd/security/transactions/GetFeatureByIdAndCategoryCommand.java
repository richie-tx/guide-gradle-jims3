//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetFeaturesCommand.java

package pd.security.transactions;

import java.util.Iterator;

import messaging.security.GetFeatureByIdAndCategoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Feature;
import pd.security.PDSecurityHelper;

/**
 * 
 * 
 * @author mchowdhury
 * @description Get feature based on feature name and feature category  
 *
 */
public class GetFeatureByIdAndCategoryCommand implements ICommand 
{
   
   /**
	* @roseuid 4256F0A702CE
	*/
   public GetFeatureByIdAndCategoryCommand() 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F801B9
	*/
   public void execute(IEvent event) 
   {
   	/*  GetFeatureByIdAndCategoryEvent featuresEvent = (GetFeatureByIdAndCategoryEvent) event;
	  Iterator features = Feature.findAll(featuresEvent);
	  PDSecurityHelper.sendFeaturesResponseEvent(features);*/ //87191
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
