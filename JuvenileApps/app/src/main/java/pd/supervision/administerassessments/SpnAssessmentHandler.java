/**
 * 
 */
package pd.supervision.administerassessments;

import java.util.List;

import mojo.km.messaging.IEvent;
import pd.common.util.StringUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;

/**
 * @author cc_cwalters
 *
 */
public class SpnAssessmentHandler implements ISpnHandler 
{

    /**
     * Transfer Assessments from a given SPN to another
     * @param fromSpn
     * @param toSpn
     * @throws Exception 
     */
    private void transferAssessments(List<Assessment> fromAssessments, 
    		String toSpn) throws Exception
    {    	
    		//loop through all from assessments and reset defendant ID to toSpn 
        int num_from_assessments = fromAssessments.size();
        for (int i=0;i<num_from_assessments;i++)
        {
    			//reset defendant ID of assessment to toSpn
        	Assessment from_assessment = fromAssessments.get(i);
        	from_assessment.setDefendantId(toSpn);
        }
    }//end of transferAssessments()
    
	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#consolidate(java.lang.String, java.lang.String)
	 */
	public void consolidate(String aliasSpn, String baseSpn) throws Exception 
	{
			//retrieve alias and base SPNs
    	String alias_defendantId = StringUtil.padSpn(aliasSpn);
        String base_defendantId = StringUtil.padSpn(baseSpn);

        	//retrieve assessments for alias SPN
        List<Assessment> alias_assessments = 
        		Assessment.findAll("defendantId", alias_defendantId);
        
    		//transfer assessments from alias to base SPN
        transferAssessments(alias_assessments, base_defendantId);
               
	}//end of consolidate

	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#split(mojo.km.messaging.IEvent)
	 */
	public void split(IEvent processSpnSplitEvent) throws Exception 
	{
		// TODO Auto-generated method stub

	}//end of split()

}
