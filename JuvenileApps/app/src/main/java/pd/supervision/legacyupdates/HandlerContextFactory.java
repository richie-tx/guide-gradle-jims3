/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.legacyupdates;

import java.util.Hashtable;
import java.util.Iterator;



/**
 * The <code>HandlerContextFactory</code> is a singleton implementation of 
 * the factory pattern wherein handlers(faccade implementations) to update 
 * assignment data based on the record type are retrieved and a signature of 
 * the class instance is persisted.  
 * 
 * @author mchowdhury, parumbak
 * 
 */
public class HandlerContextFactory {
    private static HandlerContextFactory fac = null; 
    private static Hashtable handlerMap = new Hashtable();
	
    /**
     * private constructor
     */
    private  HandlerContextFactory() {
	}
	
    
    /**
     * Caller to return a singleton instance and instantiate the 
     * Handler Map
     * 
     * @return pd.supervision.legacyupdates.HandlerContextFactory
     */
    public static HandlerContextFactory getInstance(){
    	
    	if(fac == null){
    		fac = new HandlerContextFactory();
    		
    		Iterator iter = HandlerConfig.findAll();
        
    		while (iter.hasNext()){
                HandlerConfig hConfig = (HandlerConfig) iter.next(); 
                
                // Debug
                System.out.println("hConfig.getHandlerLocatorKey() - "+hConfig.getHandlerLocatorKey());
                System.out.println("      hConfig.getHandlerName() - "+hConfig.getHandlerName());
                
                handlerMap.put(hConfig.getHandlerLocatorKey(), hConfig.getHandlerName());
            }    		
    	}
    	return fac;
    }
    
    /**
     * The <code> getHandlerName() </code> helper method allows a caller to 
     * pass a record type aka. the key for the handler, and obtain a signature of
     * the handler type.
     * 
     * @param recType - String
     * @return String - A object of type java.lang.String that returns the handler type
     */
    private String getHandlerName(String recType){
    	return (String) handlerMap.get(recType);
    }
    
    /**
     * The <code> lookup() </code> helper method allows a caller to pass
     * a record type as an argument and obtain a reference to an handler object 
     * that corresponds to the rec type.
     * 
     * @param recType - The record type
     * @return An object reference
     * @throws ReflectionException
     */
	public ILegacyUpdateHandler lookup(String recType) throws Exception{
		ILegacyUpdateHandler handler = null;		    
		try {
			Class c = Class.forName(getHandlerName(recType));
			handler = (ILegacyUpdateHandler)c.newInstance();			
		} finally{
			System.out.println((handler != null)?"Instantiated Handler Object":"Error");
		}
		return handler;
     }
}
