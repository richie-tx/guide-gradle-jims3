//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisee\\action\\SubmitDeleteLOSSummaryAction.java

package ui.supervision.administersupervisee.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administercaseload.UpdateLevelOfSupervisionEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.supervisee.form.SuperviseeForm; 
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class SubmitDeleteLOSSummaryAction extends JIMSBaseAction
{
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
	}
   
   /**
    * @roseuid 484E86E803DB
    */
   public SubmitDeleteLOSSummaryAction() 
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
		String superviseeId = superviseeForm.getSuperviseeId();
		
		UpdateLevelOfSupervisionEvent event = (UpdateLevelOfSupervisionEvent) EventFactory
        .getInstance(CaseloadControllerServiceNames.UPDATELEVELOFSUPERVISION);
		
		event.setSuperviseeHistoryId(superviseeForm.getSupervisionLevelHistoryId());
		event.setDefendantId(superviseeForm.getSuperviseeId());
        event.setLosEffectiveDate(DateUtil.stringToDate(superviseeForm.getEffectiveDate(), UIConstants.DATE_FMT_1));
        event.setLevelOfSupervisionId(superviseeForm.getSupervisionLevel());
        event.setComments(superviseeForm.getLosComments());
        event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
                
        event.setDelete(true);

        CompositeResponse cr = MessageUtil.postRequest(event);
        ErrorResponseEvent er = (ErrorResponseEvent) MessageUtil.filterComposite(cr, ErrorResponseEvent.class);
        ActionForward forward = null;
        if (er != null){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, er.getMessage());
        	forward = aMapping.findForward(UIConstants.CANCEL);
        } else {		
        	SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
        	myHeaderForm.setSuperviseeId(superviseeId);
        	UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
        	UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
        	if (superviseeForm.getLosHistories() != null) {
        		UIAdministerSuperviseeHelper.populateCurrentLOSInformationFromHistory(superviseeForm);
        	} else {
        		UIAdministerSuperviseeHelper.blankCurrentLOSInformation(superviseeForm);
        	}
        	forward = aMapping.findForward(UIConstants.FINISH_LOS);
        }
        return forward;
	}
	
	/**
	 * Override Cancel method implementation
	 * @param aMapping  - the struts mapping
	 * @param aForm -- the struts form
	 * @param aRequest -- the request object
	 * @param aResponse -- the response object
	 * @return -- an action forward for "Cancel"
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		// cancel button - put the original LOS Effective Date and Supervision Level back onto the form
		superviseeForm.setEffectiveDate(superviseeForm.getEffectiveLosDateCurrentRecord());
		superviseeForm.setSupervisionLevel(superviseeForm.getSupervisionLevelCurrentRecord());
		superviseeForm.setLosComments(superviseeForm.getLosCommentsCurrentRecord());
		
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
}
