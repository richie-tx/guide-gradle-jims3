/*
 * Created on Oct 17, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.commonfunctionality.spnconsolidation;

import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ISpnHandler {
	
	
	public void consolidate(String aliasSpn, String baseSpn ) throws Exception ;
    
    
    /**
     * This method implements the Split Spn functionality.
     * @param erroneousSpn
     * @param validSpn
     * @throws Exception 
     */
    public void split(IEvent processSpnSplitEvent) throws Exception;

}
