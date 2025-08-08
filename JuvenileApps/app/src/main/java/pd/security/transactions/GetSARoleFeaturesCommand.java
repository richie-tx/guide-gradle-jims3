//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetFeaturesCommand.java

package pd.security.transactions;

import java.util.Iterator;
import pd.security.PDSecurityHelper;
import messaging.security.GetSARoleFeaturesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Feature;

/*
 * 
 * 
 * @author mchowdhury
 * @description Get SARole features Constraints are: feature name and feature category 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
public class GetSARoleFeaturesCommand implements ICommand 
{
   
   /**
	* @roseuid 4256F0A702CE
	*/
   public GetSARoleFeaturesCommand() 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F801B9
	*/
   public void execute(IEvent event) 
   {
	 /* GetSARoleFeaturesEvent featuresEvent = (GetSARoleFeaturesEvent) event;
	  Iterator features = Feature.findAll(featuresEvent);
	  PDSecurityHelper.sendFeaturesResponseEvent(features); */ //87191
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
