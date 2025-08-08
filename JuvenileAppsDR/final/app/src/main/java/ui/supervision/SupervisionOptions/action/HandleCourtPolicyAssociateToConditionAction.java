//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\HandleCourtPolicyAssociateToConditionAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class HandleCourtPolicyAssociateToConditionAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 42F7C49A033C
    */
   public HandleCourtPolicyAssociateToConditionAction() 
   {
    
   }
   
   public Map getKeyMethodMap(){
	Map buttonMap = new HashMap();
	buttonMap.put("button.filter", "filter");
	buttonMap.put("button.addSelected", "addSelected");
	buttonMap.put("button.next", "next");
	return buttonMap;
   }

   /**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 42F79A75038C
	*/
   public ActionForward filter(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
   	CourtPolicyForm form=(CourtPolicyForm)aForm;
	   AssociationHelper.filterCourtPolicyCondition( (CourtPolicyForm)aForm );
		
	   return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("filter",form.getAgencyId()));
   }

   /**
	   * @param aMapping
	   * @param aForm
	   * @param aRequest
	   * @param aResponse
	   * @return ActionForward
	   * @roseuid 42F79A75038C
	   */
   public ActionForward next(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {

		CourtPolicyForm form = (CourtPolicyForm)aForm;
		
	   form.setPageType( "summary" );
		
	   return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("next",form.getAgencyId()));
   }

   /**
	   * @param aMapping
	   * @param aForm
	   * @param aRequest
	   * @param aResponse
	   * @return ActionForward
	   * @roseuid 42F79A75038C
	   */
   public ActionForward addSelected(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
   	CourtPolicyForm form=(CourtPolicyForm)aForm;
	   AssociationHelper.addSelectedCourtPolicy( (CourtPolicyForm)aForm );
		
	   return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("addSelected",form.getAgencyId()));
   }
	
}
