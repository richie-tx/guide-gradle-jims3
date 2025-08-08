/**
 * 
 */
package ui.supervision.administersupervisee.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administersupervisee.UpdateSuperviseeDetailsEvent;
import messaging.administersupervisee.reply.DNAResponseEvent;
import messaging.administersupervisee.reply.SupervisionLevelResponseEvent;
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
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author dwilliamson
 *
 */
public class HandleDNASelectionAction extends JIMSBaseAction{
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.correct", "correct");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.add", "addDNA");
	}

	/**
	 * 
	 */
	public HandleDNASelectionAction() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String HISTORY_ID_MISSING = "HttpRequest info missing after CANCEL.  Please try again.";
	
	public ActionForward add(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		String superviseeId=superviseeForm.getSuperviseeId();
		superviseeForm.setAction("create");
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		
		superviseeForm.setDnaCollectedDate("");
		superviseeForm.setDnaFlagInd(false);
		
		ActionForward forward = null;
		forward = aMapping.findForward(UIConstants.ADD_DNA);
	
	    return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward correct(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		String superviseeId=superviseeForm.getSuperviseeId();
		superviseeForm.setAction("update");
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		
		ActionForward forward = null;
		if (superviseeId == null ||
				superviseeId.equals("")){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, HISTORY_ID_MISSING);
			forward = aMapping.findForward(UIConstants.CANCEL);
		} else {
			superviseeForm.setSelectedValue("");			
			superviseeForm.setDnaCollectedDate(superviseeForm.getDnaCollectedDate());
			forward = aMapping.findForward(UIConstants.DNA_CORRECT);
		}
	
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward delete(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		String superviseeId=superviseeForm.getSuperviseeId();
		UpdateSuperviseeDetailsEvent event = (UpdateSuperviseeDetailsEvent) EventFactory
        .getInstance(SuperviseeControllerServiceNames.UPDATESUPERVISEEDETAILS);
		event.setSpn(superviseeForm.getSuperviseeId());
        event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());  
        event.setDelete(true);
        CompositeResponse cr = MessageUtil.postRequest(event);
        ErrorResponseEvent er = (ErrorResponseEvent) MessageUtil.filterComposite(cr, ErrorResponseEvent.class);
      
		superviseeForm.setAction("delete");
		ActionForward forward = null;
        if (er != null){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, er.getMessage());
        	forward = aMapping.findForward(UIConstants.CANCEL);
        } else {
        	superviseeForm.setSecondaryAction(UIConstants.DELETE);
        	aRequest.setAttribute("confirmMessage", "DNA Information successfully deleted.");
        	SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
        	myHeaderForm.setSuperviseeId(superviseeId);
        	UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
        	UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
        	forward = aMapping.findForward(UIConstants.FINISH_DNA);
        }
		return forward;

	}

	@Override
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		
		// cancel button - put the original DNA Date and DNA flag back onto the form
		superviseeForm.setDnaCollectedDate(superviseeForm.getDnaCurrentRecordDate());
		superviseeForm.setDnaFlagInd(superviseeForm.isDnaCurrentRecordFlagInd());
		
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	
	

}
