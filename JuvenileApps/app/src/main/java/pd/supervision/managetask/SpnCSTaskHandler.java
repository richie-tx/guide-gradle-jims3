/*
 * Created on Oct 17, 2006
 *
 */
package pd.supervision.managetask;

import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;


/**
 * @author ryoung
 * 
 */
public class SpnCSTaskHandler implements ISpnHandler {
    final String ZERO = "0";
   
	
    /**
     * Initiate the process of a SPN Consolidation transaction
     * @throws Exception 
     */
    public void consolidate( String aliasSpn, String baseSpn ) throws Exception 
    {    	
    	//retrieve alias and base SPNs
    	String alias_defendantId = padSpn( aliasSpn );
        String base_defendantId = padSpn( baseSpn );

      
        //Consolidate tasks from alias to base SPN
        consolidateCSTasks( base_defendantId, alias_defendantId );
        
     }//end of consolidate()
    
 
    
    private String padSpn(String spn){
        if (spn != null && spn.length() < 8) {
            StringBuffer sb = new StringBuffer(spn);
            for (int i = 0; i < 8 - spn.length(); i++) {
                sb.insert(0, ZERO);
            }
            spn = sb.toString();
        }
        return spn;
    }
    
    /**
     * 
     * @param toSpn
     * @param fromSpn
     */
    private void consolidateCSTasks( String toSpn, String fromSpn ) {
       
    	CSTask task = null;
    	 CSTask csTask = new CSTask();
    	 
        List< CSTask > alias_tasks = 
        	CollectionUtil.iteratorToList(
        			csTask.findAll( "defendantId" , fromSpn ));
        
	     for ( int x =0; x < alias_tasks.size(); x ++ ){
	    	 
	    	 task = (CSTask) alias_tasks.get( x );
	    	 task.setDefendantId( toSpn );
	     }
	     alias_tasks.clear();
	     alias_tasks = null;
	     task = null;
	     csTask = null;
	     
    }

    /**
     * Initiate the process of a SPN split transaction
     * @throws Exception 
     */
    public void split(IEvent processSpnSplitEvent) throws Exception
    {
        
    }//end of split()
    
 
    
 
    
 
}
