/*
 * Created on Nov 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import java.lang.reflect.InvocationTargetException;

import messaging.codetable.GetCodetableRegistrationAttributesAndTypesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.ResponseLocatorConstants;
import pd.codetable.ICodetableRegCreator;
import pd.common.ResponseContextFactory;
import pd.exception.CodetableException;
import pd.exception.ReflectionException;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCodetableRegistrationAttributesAndTypesCommand  implements ICommand {

	  /**
	   * @roseuid
	   */
	  public GetCodetableRegistrationAttributesAndTypesCommand() 
	  {
	  }
   
	 /**
	 * @param event
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws CodetableException
	 * @roseuid 
    */
    public void execute(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException 
    {
    	GetCodetableRegistrationAttributesAndTypesEvent gEvent = (GetCodetableRegistrationAttributesAndTypesEvent) event;
 	    ResponseContextFactory respFac = new ResponseContextFactory();
	    ICodetableRegCreator aCreator =  null;
	    try {        
	       	    aCreator = (ICodetableRegCreator) respFac.lookup(ResponseLocatorConstants.CODETABLEREG_SERVICE_LOCATOR);
	       	    aCreator.retrieveAttributeAndTypes(gEvent);	       
	    }catch (ReflectionException e) {
		    e.printStackTrace();
	    }	    
	}

/**
    * @param event
    * @roseuid 
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 
    */
   public void update(Object anObject) 
   {
    
   }
}


