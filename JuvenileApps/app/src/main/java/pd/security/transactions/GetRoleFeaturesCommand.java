//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRoleFeaturesCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.security.GetRoleFeaturesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Feature;
import mojo.km.security.Role;
import pd.security.PDSecurityHelper;

/**
 * 
 * 
 * @author mchowdhury
 * @description get features of a role  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetRoleFeaturesCommand implements ICommand 
{
   
   /**
	* @roseuid 4256F0A7030D
	*/
   public GetRoleFeaturesCommand() 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F80188
	*/
   public void execute(IEvent event) 
   {
	  /* GetRoleFeaturesEvent roleFeaturesEvent = (GetRoleFeaturesEvent) event;
	   Role role = Role.find(roleFeaturesEvent.getRoleId());
	  	  
	   Collection features = role.getFeatures();
	   Iterator fIt = features.iterator();
	   PDSecurityHelper.sendFeaturesResponseEvent(fIt);*/ //87191
   }
 
   /**
	* @param event
	* @roseuid 425551F8018A
	*/
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 425551F8018C
	*/
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
	* @param anObject
	* @roseuid 425551F8018E
	*/
   public void update(Object anObject) 
   {
    
   }
}
