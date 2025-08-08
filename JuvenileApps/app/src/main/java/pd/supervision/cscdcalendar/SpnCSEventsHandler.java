/*
 * Created on Oct 17, 2006
 *
 */
package pd.supervision.cscdcalendar;

import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;


/**
 * @author ryoung
 * 
 */
public class SpnCSEventsHandler implements ISpnHandler {
    final String ZERO = "0";
   

    /**
     * Redesigned consolidate method using different means of supervision period calculation
     * @throws Exception 
     */
    public void consolidate( String aliasSpn, String baseSpn ) throws Exception 
    {    	
    	//retrieve alias and base SPNs
    	String alias_defendantId = padSpn( aliasSpn );
        String base_defendantId = padSpn( baseSpn );

		//retrieve CSEvents for alias SPN
        List< CSEvent > alias_events = 
        	CollectionUtil.iteratorToList(
        			CSEvent.findAll( "partyId" , alias_defendantId ));
        
        //consolidate CSEvents from alias to base SPN
        consolidateEvents( alias_events, base_defendantId );
        
     }//end of consolidate()
    
 
    /**
     * 
     * @param spn
     * @return
     */
    private String padSpn( String spn ){
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
     * @param csevents
     * @param base_defendantId
     */
    private void consolidateEvents(List csevents , String base_defendantId ) {
       
	     for ( int x =0; x < csevents.size(); x ++ ){
	    	 
	    	 CSEvent event = (CSEvent) csevents.get(x);
	    	 event.setPartyId( base_defendantId );
	     }
    }

    /**
     * Initiate the process of a SPN split transaction
     * @throws Exception 
     */
    public void split(IEvent processSpnSplitEvent) throws Exception
    {
        
    }//end of split()
    
 
    
 
    
 
}
