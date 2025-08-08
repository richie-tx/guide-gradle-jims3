/*
 * Created on September 07, 2010
 *
 */
package pd.supervision.administersupervisee;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;

/**
 * @author ryoung
 * 
 */
public class SpnSuperviseeHandler implements ISpnHandler {
    final String ZERO = "0";
	
  
    
    /**
     * Redesigned consolidate method using different means of supervision period calculation
     * @throws Exception 
     */
    public void consolidate( String aliasSpn, String baseSpn ) throws Exception 
    {    	
    		//retrieve alias and base SPNs
    	String alias_defendantId = padSpn(aliasSpn);
        String base_defendantId = padSpn(baseSpn);

        
        //look for supervisee exist or not
        Supervisee aliasDefendant = Supervisee.findByDefendantId(alias_defendantId);        
        Supervisee baseDefendant = Supervisee.findByDefendantId(base_defendantId);
        
        if( aliasDefendant != null && baseDefendant != null ){
        	
        	Iterator shIter = SuperviseeHistory.findAll( "superviseeId", aliasDefendant.getOID() );
        	while( shIter.hasNext() ){
        		SuperviseeHistory sh = (SuperviseeHistory) shIter.next();
        		sh.setSuperviseeId( baseDefendant.getOID() );
        		
        			//commit each change to database
        			//don't know of a way via framework to issue a single COMMIT statement
        		( new mojo.km.persistence.Home()).bind(sh);
        	}
        	
        	//remove supervisee record after moving history records over to base defendant
        	aliasDefendant.delete();
        
        }else if( aliasDefendant != null && baseDefendant == null ){
        	        	
        	aliasDefendant.setDefendantId( base_defendantId );
        	
        }else if( aliasDefendant == null && baseDefendant == null ){
        	
        	Supervisee s = new Supervisee();
        	s.setDefendantId( base_defendantId );
        	
        	SuperviseeHistory sh = new SuperviseeHistory();
        	sh.setSuperviseeId( s.getOID() );
        }   
    }//end of consolidate()
    
  
  
   /**
    *  
    * @param spn
    * @return
    */
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

public void split(IEvent processSpnSplitEvent) throws Exception {
	// TODO Auto-generated method stub
	
}

    
     

}
