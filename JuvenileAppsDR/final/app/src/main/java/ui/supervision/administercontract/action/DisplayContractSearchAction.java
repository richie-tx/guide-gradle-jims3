//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\DisplayContractSearchAction.java

package ui.supervision.administercontract.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.supervision.administercontract.LoadSupervisionCodeTables;
import ui.supervision.administercontract.form.ContractForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayContractSearchAction extends Action
{
   
   /**
    * @roseuid 451C4F0D0142
    */
   public DisplayContractSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A6601AA
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
   	   contractForm.clear();
   	   contractForm.setShowServiceProviderInfo("");
   	   contractForm.setDepartmentId(SecurityUIHelper.getUserDepartmentId());
   	   LoadSupervisionCodeTables load = LoadSupervisionCodeTables.getInstance();
	   load.setContractForm(contractForm);
	   return aMapping.findForward(UIConstants.SUCCESS);
   }
}
