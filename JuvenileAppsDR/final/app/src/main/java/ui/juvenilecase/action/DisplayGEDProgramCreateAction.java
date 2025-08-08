//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\action\\DisplayGEDProgramCreateAction.java

package ui.juvenilecase.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileGEDProgramForm;


public class DisplayGEDProgramCreateAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "displayGEDCreate");
	}	
   
   public DisplayGEDProgramCreateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward displayGEDCreate(ActionMapping aMapping, ActionForm aForm, 
		   HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm;
		jgpForm.clear();
		String juvId = (String) aRequest.getAttribute("juvenileID");
		aRequest.setAttribute("juvenileID", null);
		jgpForm.setJuvenileId(juvId);
		jgpForm.setEnrollmentStatuses(CodeHelper.getCodes(PDCodeTableConstants.GED_ENROLLMENT_STATUS, true) );
		jgpForm.setParticipationLevels(CodeHelper.getParticipationCodes());
		jgpForm.setPrograms(CodeHelper.getCodes(PDCodeTableConstants.GEDPROGRAM, true));
		return aMapping.findForward(UIConstants.SUCCESS);   
   }   
}
