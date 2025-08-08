/**
 * 
 */
package ui.supervision.administersupervisee.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administersupervisee.UpdateSuperviseeDetailsEvent;
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
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author dwilliamson
 *
 */
public class SubmitAddDNASummaryAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
	}

	/**
	 * 
	 */
	public SubmitAddDNASummaryAction() {
		// TODO Auto-generated constructor stub
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
	    ActionForward forward = null;
    
	
		UpdateSuperviseeDetailsEvent event = (UpdateSuperviseeDetailsEvent) EventFactory
        .getInstance(SuperviseeControllerServiceNames.UPDATESUPERVISEEDETAILS);
		
		event.setSpn(superviseeForm.getSuperviseeId());
		Date dnaCollectedDate = DateUtil.stringToDate(superviseeForm.getDnaCollectedDate(), UIConstants.DATE_FMT_1);
        event.setDnaCollectedDate(dnaCollectedDate);
        if(dnaCollectedDate != null){
            event.setDnaFlagInd(true);
        }

        event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
                
        event.setAdd(true);

        CompositeResponse cr = MessageUtil.postRequest(event);
        ErrorResponseEvent er = (ErrorResponseEvent) MessageUtil.filterComposite(cr, ErrorResponseEvent.class);

        if (er != null){
            this.sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, er.getMessage());
        	forward = aMapping.findForward(UIConstants.CANCEL);
        } else {		
        	SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
        	myHeaderForm.setSuperviseeId(superviseeId);
        	superviseeForm.setSelectedValue("");
        	aRequest.setAttribute("confirmMessage", "DNA Information successfully added.");
        	forward =  aMapping.findForward(UIConstants.FINISH_DNA);
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
