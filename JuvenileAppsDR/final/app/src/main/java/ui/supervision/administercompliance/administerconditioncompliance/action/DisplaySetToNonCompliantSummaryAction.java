// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\DisplaySetToNonCompliantSummaryAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceCasenoteHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

public class DisplaySetToNonCompliantSummaryAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 473B8C20003B
    */
   public DisplaySetToNonCompliantSummaryAction() 
   {
    
   }
   protected void addButtonMapping(Map keyMap)
   {
		keyMap.put("button.search", "search");   	
   		keyMap.put("button.viewAll", "viewAll");
        keyMap.put("button.advancedSearch", "advancedSearch");
        keyMap.put("button.basicSearch", "basicSearch");         		
   		keyMap.put("button.next", "next");  
   		keyMap.put("button.reset", "reset"); 
   }   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 473B7557006E
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm,
   		HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		ComplianceForm compForm = (ComplianceForm) aForm;
		if (compForm.getCollateralId().equalsIgnoreCase("AS")){
			UIAdministerComplianceCasenoteHelper.associateNameRelationshipFormatter(compForm);
		}
	   	return aMapping.findForward(UIConstants.SUCCESS);   	
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */   
   public ActionForward search(ActionMapping aMapping, ActionForm aForm,
   		HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		ComplianceForm compForm = (ComplianceForm) aForm;
		UIAdministerComplianceCasenoteHelper.casenoteSearch(compForm);	
	   	return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
   }

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */   
   public ActionForward viewAll(ActionMapping aMapping, ActionForm aForm,
   		HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.clearCasenoteSearch();
		UIAdministerComplianceCasenoteHelper.casenoteSearch(compForm);		
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
   }   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward advancedSearch(ActionMapping aMapping,
			ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException    
   {
	    ComplianceForm compForm = (ComplianceForm) aForm;
		UIAdministerComplianceCasenoteHelper.advancedSearch(compForm);
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
   } 
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward basicSearch(ActionMapping aMapping,
			ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException    
   {
	    ComplianceForm compForm = (ComplianceForm) aForm;
		UIAdministerComplianceCasenoteHelper.basicSearch(compForm);
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
   } 

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward reset(ActionMapping aMapping,
			ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException    
   {
	    ComplianceForm compForm = (ComplianceForm) aForm;
	    UIAdministerComplianceCasenoteHelper.resetInputs(compForm);
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
   }
}
