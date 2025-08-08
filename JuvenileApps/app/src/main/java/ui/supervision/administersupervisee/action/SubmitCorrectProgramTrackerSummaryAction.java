//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisee\\action\\SubmitCorrectLOSSummaryAction.java

package ui.supervision.administersupervisee.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administersupervisee.UpdateProgramTrackerEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.SuperviseeControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.supervisee.form.SuperviseeForm; 

public class SubmitCorrectProgramTrackerSummaryAction extends JIMSBaseAction
{

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
	}
   
   /**
    * @roseuid 484E86E803DB
    */
   public SubmitCorrectProgramTrackerSummaryAction() 
   {
    
   }
   
   /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		SuperviseeForm superviseeForm = (SuperviseeForm)aForm;
	
		UpdateProgramTrackerEvent event = (UpdateProgramTrackerEvent) EventFactory
        .getInstance(SuperviseeControllerServiceNames.UPDATEPROGRAMTRACKER);
		
		event.setSuperviseeHistoryId(superviseeForm.getProgramTrackerHistoryId());
		event.setSpn(superviseeForm.getSuperviseeId());
        event.setEffectiveDate(DateUtil.stringToDate(superviseeForm.getProgramTrackerEffectiveDate(), UIConstants.DATE_FMT_1));
        event.setEndDate(DateUtil.stringToDate(superviseeForm.getProgramTrackerEndDate(), UIConstants.DATE_FMT_1));
        event.setProgramTrackerId(superviseeForm.getProgramTrackerId());
        event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
                
        event.setCorrect(true);

        CompositeResponse cr = MessageUtil.postRequest(event);
        ErrorResponseEvent er = (ErrorResponseEvent) MessageUtil.filterComposite(cr, ErrorResponseEvent.class);
        ActionForward forward = null;
        if (er != null){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, er.getMessage());
        	forward = aMapping.findForward(UIConstants.CANCEL);
        } else {		
        	forward = aMapping.findForward(UIConstants.FINISH_PROGRAM_TRACKER);
        	superviseeForm.setSecondaryAction(UIConstants.CONFIRM);
        	superviseeForm.setConfirmMessage("Program Tracker record successfully corrected.");
        }
        return forward;
	}
}
