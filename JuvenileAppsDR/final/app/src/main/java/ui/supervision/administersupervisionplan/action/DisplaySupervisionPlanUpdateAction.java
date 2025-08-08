//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisionplan\\action\\DisplaySupervisionPlanUpdateAction.java

package ui.supervision.administersupervisionplan.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administersupervisionplan.AdminSupervisionPlanHelper;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;



public class DisplaySupervisionPlanUpdateAction extends JIMSBaseAction
{
   public static final String SUMMARY_PAGE="SUMMARY";
   
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.newSupervisionPlan", "createSupervisionPlan");		
		keyMap.put("button.submit", "submit");		
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
	public ActionForward createSupervisionPlan(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm) aForm;		
		AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_ASSESSMENT_FORM,true);
		
		String forward = UIConstants.CREATE_SUCCESS;
		
		String pageType = supervisionPlanForm.getPageType();
		if((pageType != null) && (pageType.equalsIgnoreCase(SUMMARY_PAGE)))
		{
			pageType = "";
			return aMapping.findForward(forward);
		}
		supervisionPlanForm.clearAll();
		
		AdminSupervisionPlanHelper.initializeSupervisionPlanForm(assessmentForm, supervisionPlanForm);
		supervisionPlanForm.setAction(UIConstants.CREATE);
		supervisionPlanForm.setSecondaryAction("");
		supervisionPlanForm.getAgencyId();
		
		return aMapping.findForward(forward);
	}//end of createSupervisionPlan()
	
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm) aForm;	
		supervisionPlanForm.setSecondaryAction(UIConstants.SUMMARY);

		Date supervisionPlanDate = supervisionPlanForm.getSupervisionPlanDate();
		
//   	validate if Supervision Plan date not greater than the present date
		Date presentDate = new Date();
		int result = DateUtil.compare(supervisionPlanDate,presentDate,DateUtil.DATE_FMT_1);
   		if(result > 0)
   		{
   			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Supervision Plan Date cannot be future dated");
   			return aMapping.findForward(UIConstants.FAILURE);
   		}
		 
//   	validate the Supervision Plan date if entered within the Supervision Period	
   		boolean isSupPlanDateInRange = AdminSupervisionPlanHelper.isSupPlanDateInSupervisionRange(supervisionPlanDate,supervisionPlanForm);
   		if(!isSupPlanDateInRange)
   		{
   			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Supervision Plan Date should be within the Supervision Period");
   			return aMapping.findForward(UIConstants.FAILURE);
   		}
		
		String lastChangeDateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FMT_1);
		supervisionPlanForm.setLastChageDateStr(lastChangeDateStr);
		
		supervisionPlanForm.setLastChangeUserId(SecurityUIHelper.getLogonId());
		String userName = AdminSupervisionPlanHelper.getUserNameFromUserId(supervisionPlanForm.getLastChangeUserId());
		supervisionPlanForm.setLastChangeUserName(userName);
		
		return aMapping.findForward(UIConstants.SUBMIT);
	}
	
}
