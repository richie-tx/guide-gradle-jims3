/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileProfileSearchAction
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDJuvenileCaseConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.JuvenileProfileSearchForm;
import ui.juvenilecase.form.ProdSupportForm;

/**
 * Class DisplayJuvenileReferralSealingSearchAction.
 * 
 * @author Anju Pillai
 */
public class DisplayJuvenileReferralSealingSearchAction extends org.apache.struts.action.Action
//public class DisplayJuvenileReferralSealingSearchAction extends JIMSBaseAction
{   
   /* protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "submitSearch");	
    }*/
    public DisplayJuvenileReferralSealingSearchAction()
    {

    }
    
    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A46D8E0234
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
        // Clear form and set default search to "Juvenile Name"
        form.clear();
        //form.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);
        
      /*  String isFacility= aRequest.getParameter("isFacility");
        if(isFacility!=null){
        	 form.setFacilityAction("facility");
        	 return aMapping.findForward(naming.UIConstants.FACILITY_SUCCESS);
        }
        
        String referrals= aRequest.getParameter("isReferral");
        if(referrals!=null){
        	 form.setReferralAction("referral");
        	 return aMapping.findForward(naming.UIConstants.REFERRAL_SUCCESS);
        }*/
        
        return aMapping.findForward(naming.UIConstants.SUCCESS);
    }
  
    

}
