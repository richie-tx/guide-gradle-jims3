//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicyCreateAction.java

package ui.supervision.SupervisionOptions.action;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class DisplayCourtPolicyCreateAction extends Action
{
   
   /**
    * @roseuid 42F7C47B032C
    */
   public DisplayCourtPolicyCreateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997C01B7
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		//	clean up the form upon at begining of scenario
		CourtPolicyForm form = (CourtPolicyForm)aForm;
		form.clear();
		form.setPolicyId(null);	
		
		//	default selection of radio button to non-standard			
		form.setStandard(false); 
		
		Date today = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		form.setEffectiveDate(dateFormatter.format(new Date()));
		
		// set default jurisdiction to Harris County
		form.setJurisdictionId(UIConstants.HCOUNTY);
				 
		// TODO get agency from the user
		String agencyId = form.getAgencyId();
			  	
		if(form.getGroups() == null || form.getGroups().size() == 0)
		{
			// get groups	
			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
			form.setGroups(groups);
		}
		
		if(form.getDetailDictionary() == null || form.getDetailDictionary().size() == 0)
		{
			// get detail dictionary
			Collection varElementTypes = UISupervisionOptionHelper.fetchDetailDictionary(agencyId);
			form.setDetailDictionary(varElementTypes); 
		}		
		form.setFilteredDetailDictionary(form.getDetailDictionary());
		
		form.setAction(UIConstants.CREATE);
	SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
