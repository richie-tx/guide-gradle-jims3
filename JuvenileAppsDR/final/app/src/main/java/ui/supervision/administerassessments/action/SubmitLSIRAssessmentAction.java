/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.DeleteAssessmentEvent;
import messaging.administerassessments.UpdateLSIRAssessmentEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.form.LSIRAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitLSIRAssessmentAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish","finish");	
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::SubmitLSIRAssessmentAction::finish()::Method Begin");
		
		LSIRAssessmentForm lsirForm = (LSIRAssessmentForm) aForm;
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		if(lsirForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteAssessmentEvent deleteRequestEvent = (DeleteAssessmentEvent)AdminAssessmentsHelper.getDeleteAssessmentEvent(lsirForm);
			
	        CompositeResponse response = this.postRequestEvent(deleteRequestEvent);   
			MessageUtil.processReturnException(response);
		}
		else 
		if((lsirForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (lsirForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			ArrayList questionAnswerList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(lsirForm.getLsirAssessmentQuestionsList());
			lsirForm.setQuestionAnswerList(questionAnswerList);
			UpdateLSIRAssessmentEvent lsirUpdateRequestEvt = (UpdateLSIRAssessmentEvent)AdminAssessmentsHelper.getUpdateLSIRAssessmentEvent(lsirForm);
		
	        CompositeResponse response = this.postRequestEvent(lsirUpdateRequestEvt);   
			MessageUtil.processReturnException(response);
	        ReturnException exception = (ReturnException)MessageUtil.filterComposite(response,ReturnException.class);
		}
		
    	if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
        {       	
        	if(lsirForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
        	{
        		if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
        		{
        			aRequest.setAttribute("confirmMessage","New LSI-R Assessment successfully created.");
        		}
        		else
        		{
        			aRequest.setAttribute("confirmMessage","New LSI-R Reassessment successfully created.");
        		}
        	}
        	
        	else if(lsirForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
        	{
        		if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
        		{
        			aRequest.setAttribute("confirmMessage","LSI-R Assessment successfully updated.");
        		}
        		else
        		{
        			aRequest.setAttribute("confirmMessage","LSI-R Reassessment successfully updated.");
        		}
        	}
        	
        	else if(lsirForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
        	{
        		if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
        		{
        			aRequest.setAttribute("confirmMessage","LSI-R Assessment successfully deleted.");
        		}
        		else
        		{
        			aRequest.setAttribute("confirmMessage","LSI-R Reassessment successfully deleted.");
        		}  
        	}
        } 
        lsirForm.setSecondaryAction(UIConstants.CONFIRM);
        
        return aMapping.findForward(forward);
	}//end of finish()

}//end of Class

