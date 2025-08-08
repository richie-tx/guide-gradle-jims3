/*
 * Created on Oct 17, 2006
 *
 */
package pd.supervision.manageassociate;

import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;


/**
 * @author ryoung
 * 
 */
public class SpnAssociatesHandler implements ISpnHandler {
    final String ZERO = "0";

  
    /**
     * Redesigned consolidate method using different means of supervision period calculation
     * @throws Exception 
     */
    public void consolidate( String aliasSpn, String baseSpn ) throws Exception 
    {    	
    		//retrieve alias and base SPNs
    	String alias_defendantId =  stripLeadingZeroes( aliasSpn );
        String base_defendantId = stripLeadingZeroes( baseSpn ) ;
        
        //transfer tasks from alias to base SPN
        consolidateAssociates( base_defendantId, alias_defendantId );
        
     }//end of consolidate()
    
    /**
     * 
     * @param baseSpn
     * @param aliasSpn
     */
    private void consolidateAssociates( String baseSpn, String aliasSpn ) {
       
		//retrieve Associates for alias SPN
        List< SuperviseeAssociate > alias_Associates = 	CollectionUtil.iteratorToList(
        			SuperviseeAssociate.findAll( aliasSpn ));
        
	     for ( int x =0; x < alias_Associates.size(); x ++ ){
	    	 
	    	 SuperviseeAssociate associate = (SuperviseeAssociate) alias_Associates.get( x );
	    	 associate.setSpn( baseSpn );
	     }
    }

    /**
     * Initiate the process of a SPN split transaction
     * @throws Exception 
     */
    public void split(IEvent processSpnSplitEvent) throws Exception
    {
        
    }//end of split()
    
    private String stripLeadingZeroes(String aString) {
		   StringBuffer sb = new StringBuffer(aString);
		   StringBuffer newSb = new StringBuffer();
		   for (int i = 0; i < aString.length(); i++) {
			   if (sb.charAt(i) != '0'){
				   newSb.append(sb.substring(i));
				   break;
			   }
		   }
		   return newSb.toString();
	   }

}//End of Class
