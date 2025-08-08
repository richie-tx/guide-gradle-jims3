//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionCreateAction.java

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
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionCreateAction extends Action
{
   
   /**
    * @roseuid 42F7C48E01B5
    */
   public DisplaySupervisionConditionCreateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A39007F
    */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		//	clean up the form upon at begining of scenario
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
	  	
	  	form.clear();
	  	form.setConditionId(null);	
		form.setAction(UIConstants.CREATE);
		
		// non-standard need to be default value
		form.setStandard(false);
		
		// set default jurisdiction to Harris County
		form.setJurisdictionId(UIConstants.HCOUNTY);  
		
		Date today = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		form.setEffectiveDate(dateFormatter.format(new Date()));
		
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
		SupervisionOptionsHelper.getTaskConfiguration(form,aRequest,false);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
