//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable.transactions;

import java.lang.reflect.InvocationTargetException;

import messaging.codetable.GetCodetableRecordEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
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

public class GetCodetableRecordCommand implements ICommand 
{
   
	  /**
	   * @roseuid 45B9521B01BF
	   */
	  public GetCodetableRecordCommand() 
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
	 * @roseuid 45B94F5000C2
    */
    public void execute(IEvent event) throws SecurityException, IllegalArgumentException, InstantiationException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CodetableException 
    {
   	    GetCodetableRecordEvent gEvent = (GetCodetableRecordEvent) event;
 	    ResponseContextFactory respFac = new ResponseContextFactory();
	    ICodetableCreator aCreator =  null;
	    String type = gEvent.getType();
	    try {
	        if(PDCodeTableConstants.SIMPLE_CODE.equalsIgnoreCase(type)){
	       	    aCreator = (ICodetableCreator) respFac.lookup(ResponseLocatorConstants.SIMPLECODETABLE_SERVICE_LOCATOR);
	        }else if(PDCodeTableConstants.COMPLEX_CODE.equalsIgnoreCase(type)){
	       	    aCreator = (ICodetableCreator) respFac.lookup(ResponseLocatorConstants.COMPLEXCODETABLE_SERVICE_LOCATOR);
	        }
	        else if(PDCodeTableConstants.COMPOUND_CODE.equalsIgnoreCase(type)){
	       	    aCreator = (ICodetableCreator) respFac.lookup(ResponseLocatorConstants.COMPOUNDCODETABLE_SERVICE_LOCATOR);
	        }else{
	       	    aCreator = (ICodetableCreator) respFac.lookup(ResponseLocatorConstants.SIMPLECODETABLE_SERVICE_LOCATOR);
	        }
	    }catch (ReflectionException e) {
		    e.printStackTrace();
	    }
	    aCreator.retrieve(gEvent);
	}

/**
    * @param event
    * @roseuid 45B94F5000C4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45B94F5000D1
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45B94F5000D3
    */
   public void update(Object anObject) 
   {
    
   }
}
