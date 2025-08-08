//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\UpdateCodetableDataCommand.java

package pd.codetable.transactions;

import java.lang.reflect.InvocationTargetException;

import messaging.codetable.UpdateCodetableDataEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDConstants;
import naming.ResponseLocatorConstants;
import pd.codetable.ICodetableCreator;
import pd.common.ResponseContextFactory;
import pd.exception.CodetableException;
import pd.exception.ReflectionException;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class UpdateCodetableDataCommand implements ICommand 
{
   
   /**
    * @roseuid 45B9540F0387
    */
   public UpdateCodetableDataCommand() 
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
    * @roseuid 45B95351005B
    */
   public void execute(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException 
   {
       UpdateCodetableDataEvent uEvent = (UpdateCodetableDataEvent) event; 
	   ResponseContextFactory respFac = new ResponseContextFactory();
	   ICodetableCreator aCreator =  null;
	   String type = uEvent.getCodetableType();
	   try {
	       if(PDConstants.SIMPLE.equalsIgnoreCase(type)){
	           aCreator = (ICodetableCreator) respFac.lookup(ResponseLocatorConstants.SIMPLECODETABLE_SERVICE_LOCATOR);
	       }else{
	           aCreator = (ICodetableCreator) respFac.lookup(ResponseLocatorConstants.COMPLEXCODETABLE_SERVICE_LOCATOR);
	       }
	   }catch (ReflectionException e) {
		   e.printStackTrace();
	   }
	   aCreator.save(uEvent);
   }

  /**
    * @param event
    * @roseuid 45B95351005D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45B953510069
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45B95351006B
    */
   public void update(Object anObject) 
   {
    
   }
}
