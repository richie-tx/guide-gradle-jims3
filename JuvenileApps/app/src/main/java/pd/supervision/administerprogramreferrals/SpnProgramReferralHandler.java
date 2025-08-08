/*
 * Created on July 6, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals;


import java.util.Iterator;

import naming.SupervisionConstants;

import mojo.km.messaging.IEvent;
import pd.common.util.StringUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;

/**
 * @author ryoung
 *
 */
public class SpnProgramReferralHandler implements ISpnHandler {


		
	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#consolidate(java.lang.String, java.lang.String)
	 */
	public void consolidate(String aliasSpn, String baseSpn) throws Exception 
	{
			//retrieve alias and base SPNs
    	String alias_defendantId = StringUtil.padSpn(aliasSpn);
        String base_defendantId = StringUtil.padSpn(baseSpn);

               
    	//transfer case Assignments from alias to base SPN
        transferProgramReferrals(alias_defendantId, base_defendantId);

	}
		
	/**
     * Transfer Supervision Plans from a given SPN to another
     * @param fromSpn
     * @param toSpn
     * @throws Exception 
     */
    private void transferProgramReferrals(String fromSpn, String toSpn) throws Exception
    {    	
    	Iterator pReferralIter = CSProgramReferral.findAll(SupervisionConstants.DEFENDANT_ID, fromSpn);
        
    	while(pReferralIter.hasNext()){
        	CSProgramReferral cReferral = (CSProgramReferral) pReferralIter.next();
        	cReferral.setDefendantId(toSpn);
        	cReferral.bind();      	
        }
        
    }

    /**
     * 
     */
	public void split(IEvent processSpnSplitEvent) throws Exception {
		// TODO Auto-generated method stub
		
	}

   
}