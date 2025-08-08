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
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileGEDProgramForm;


public class DisplayGEDProgramSummaryAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "nextPage");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}	
   
   public DisplayGEDProgramSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward nextPage(ActionMapping aMapping, ActionForm aForm, 
		   HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
		JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm;
		jgpForm.setEnrollmentStatusDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.EXIT_TYPE, jgpForm.getEnrollmentStatusId() ) );
		jgpForm.setParticipationLevelDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SCHOOL_PARTICIPATION, jgpForm.getParticipationLevelId() ) );
		jgpForm.setProgramDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.GEDPROGRAM, jgpForm.getProgramId() ));
		jgpForm.setAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);   
   } 
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
		   HttpServletRequest aRequest, HttpServletResponse aResponse)  
   {
		JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm; 
		jgpForm.clear();
		return aMapping.findForward(UIConstants.BACK); 
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
		   HttpServletRequest aRequest, HttpServletResponse aResponse)  
   {
		JuvenileGEDProgramForm jgpForm = (JuvenileGEDProgramForm) aForm; 
		jgpForm.clear();
		return aMapping.findForward(UIConstants.CANCEL); 
   }
}
