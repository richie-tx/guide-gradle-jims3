/*
 * Created on Oct 17, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administercaseload;


import java.util.List;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import naming.SupervisionConstants;
import pd.common.util.StringUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.supervision.administercaseload.CaseAssignment;

/**
 * @author ryoung
 *
 */
public class SpnCaseloadHandler implements ISpnHandler {


		
	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#consolidate(java.lang.String, java.lang.String)
	 */
	public void consolidate(String aliasSpn, String baseSpn) throws Exception 
	{
			//retrieve alias and base SPNs
    	String alias_defendantId = StringUtil.padSpn(aliasSpn);
        String base_defendantId = StringUtil.padSpn(baseSpn);

               
    	//Consolidate case Assignments from alias to base SPN
        consolidateAssignments(alias_defendantId, base_defendantId);

	}
		
	public static String padSpn(String aSpn)
	{
		String spn = aSpn;
		if (spn != null && !spn.trim().equals(""))
		{
			while (spn.length() < 8)
			{
				spn = "0" + spn;
			}
		}
		return spn;		
	}
	
	/**
     * Transfer Case Assignments from a alias to base
     * @param fromSpn
     * @param toSpn
     * @throws Exception 
     */
    private void consolidateAssignments( String fromSpn, String toSpn ) throws Exception
    {    	
    	
        /**
         * Not allowing for Indirect and Multiple cases assign in another region
         *  retrieve caseAssignments for Alias SPN
         */ 
         List< CaseAssignment > alias_assignments = 
     			CollectionUtil.iteratorToList( CaseAssignment.findAll(SupervisionConstants.DEFENDANT_ID, fromSpn ));
         
         for ( int x =0; x < alias_assignments.size(); x++ ){
         	
        	 CaseAssignment alias_assignment = alias_assignments.get( x );
        	 alias_assignment.setDefendantId( toSpn );
         }

    }

    /**
     * 
     */
	public void split(IEvent processSpnSplitEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

   
}