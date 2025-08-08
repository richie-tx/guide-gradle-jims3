//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileNonComplianceNoticeHistoryAction.java

package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.JuvenileNonComplianceForm;
//import ui.juvenilecase.form.JuvenileNonComplianceForm;


public class DisplayJuvenileCasefileNonComplianceNoticeHistoryAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "link");
	}	
   
 
   public DisplayJuvenileCasefileNonComplianceNoticeHistoryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   	jncForm.setConfirmationMsg("");
		return (aMapping.findForward(UIConstants.SUCCESS));
   }

}
