/**
 * 
 */
package pd.supervision.administersupervisionplan;

import java.util.List;

import mojo.km.messaging.IEvent;
import pd.common.util.StringUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.supervision.administerassessments.Assessment;

/**
 * @author cc_cwalters
 *
 */
public class SpnSupervisionPlanHandler implements ISpnHandler {


    /**
     * Transfer Supervision Plans from a given SPN to another
     * @param fromSpn
     * @param toSpn
     * @throws Exception 
     */
    private void transferSupervisionPlans(List<SupervisionPlan> fromSupervisionPlans, 
    		String toSpn) throws Exception
    {    	
    		//loop through all from supervision plans and reset defendant ID to toSpn 
        int num_from_supervision_plans = fromSupervisionPlans.size();
        for (int i=0;i<num_from_supervision_plans;i++)
        {
    			//reset defendant ID of supervision plan to toSpn
        	SupervisionPlan from_supervision_plan = fromSupervisionPlans.get(i);
        	from_supervision_plan.setDefendantId(toSpn);
        }
    }//end of transferSupervisionPlans()
    
	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#consolidate(java.lang.String, java.lang.String)
	 */
	public void consolidate(String aliasSpn, String baseSpn) throws Exception 
	{
			//retrieve alias and base SPNs
    	String alias_defendantId = StringUtil.padSpn(aliasSpn);
        String base_defendantId = StringUtil.padSpn(baseSpn);

        	//retrieve supervision plans  for alias SPN
        List<SupervisionPlan> alias_supervision_plans = 
        	SupervisionPlan.findAll("defendantId", alias_defendantId);
        
    		//transfer supervision plans from alias to base SPN
        transferSupervisionPlans(alias_supervision_plans, base_defendantId);

	}

	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#split(mojo.km.messaging.IEvent)
	 */
	public void split(IEvent processSpnSplitEvent) throws Exception {
		// TODO Auto-generated method stub

	}

}
