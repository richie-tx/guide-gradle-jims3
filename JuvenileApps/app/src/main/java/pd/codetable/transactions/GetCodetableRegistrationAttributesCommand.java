/*
 * Created on Sep 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import java.lang.reflect.InvocationTargetException;

import messaging.codetable.GetCodetableRegistrationAttributesEvent;
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
public class GetCodetableRegistrationAttributesCommand implements ICommand {

	  /**
	   * @roseuid
	   */
	  public GetCodetableRegistrationAttributesCommand() 
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
    	GetCodetableRegistrationAttributesEvent gEvent = (GetCodetableRegistrationAttributesEvent) event;
 	    ResponseContextFactory respFac = new ResponseContextFactory();
	    ICodetableRegCreator aCreator =  null;
	    String type = gEvent.getType();

	    try {        
	       	    aCreator = (ICodetableRegCreator) respFac.lookup(ResponseLocatorConstants.CODETABLEREG_SERVICE_LOCATOR);
	       	    aCreator.retrieve(gEvent);	       
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

