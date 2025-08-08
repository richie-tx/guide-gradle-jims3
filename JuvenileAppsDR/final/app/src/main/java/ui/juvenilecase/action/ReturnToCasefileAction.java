//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayMAYSIDetailsAction.java

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.MentalHealthForm;


/**
 * @author dgibler
 *
 */
public class ReturnToCasefileAction extends Action {
   
   /**
    * @roseuid 42791FCA0232
    */
   public ReturnToCasefileAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42791D57017E
    */
   public ActionForward execute(ActionMapping aMapping, 
   								ActionForm aForm, HttpServletRequest aRequest, 
   								HttpServletResponse aResponse) {
    
    	MentalHealthForm mhForm = (MentalHealthForm)aForm;
		
		ActionForward oldForward = aMapping.findForward(UIConstants.SUCCESS);
		ActionForward newForward = new ActionForward(oldForward.getPath() + "?" + PDJuvenileCaseConstants.CASEFILEID_PARAM + "=" + mhForm.getCasefileId());
		newForward.setRedirect(true);
		return newForward;
   }
}
