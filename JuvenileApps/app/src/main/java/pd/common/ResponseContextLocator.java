/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common;

import java.util.HashMap;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeHelper;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResponseContextLocator {
     
	static private ResponseContextLocator instance = null;
    static private HashMap map = new HashMap();
		
	/**
	 * <p>name: getInstance description: Other classes call this method to get the ContextLocator
	 * @return Instance
	 */
	public synchronized static ResponseContextLocator getInstance(){
	   if (instance == null) {
		  instance = new ResponseContextLocator();
	   }
	   return instance;
	}


	/** <p>name: ContextLocator description: constructor 
	 */
	private ResponseContextLocator(){
	   this.load();
	}

	/** 
	 * @description: loads the parameters
	 */
	private void load(){
		Iterator contexts = PDCodeHelper.getCodes(PDCodeTableConstants.RESPONSE_CONTEXT_LOCATOR,false).iterator();
		while(contexts.hasNext()){
			Code context = (Code) contexts.next();
			map.put(context.getCode(),context.getDescription());
		}
	}
	
	/** 
	 * @description: return the map containing context
	 */
	public HashMap getPropertyMap(){
		return map;
	}
	
	/** 
	 * @description: return the context name
	 */
	public String getContextName(String contextId){
		return (String) map.get(contextId);
	}
}
