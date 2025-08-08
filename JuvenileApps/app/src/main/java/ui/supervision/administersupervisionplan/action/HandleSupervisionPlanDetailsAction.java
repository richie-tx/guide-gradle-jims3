//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisionplan\\action\\DisplaySupervisionPlanDetailsAction.java

package ui.supervision.administersupervisionplan.action;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administersupervisionplan.GetSupervisionPlanDetailsEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.report.SupervisionPlanPrintTemplateRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administersupervisionplan.AdminSupervisionPlanHelper;
import ui.supervision.administersupervisionplan.SupervisionPlanReportingBean;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleSupervisionPlanDetailsAction extends JIMSBaseAction
{
	public static final String SPANISH = "_Spanish";
	public static final String FILEEXT = ".pdf";
	public static final String CONTEXTKEYPREFIX = "REPORTING::";
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.updateLink","updateSupervisionPlanLink");
		keyMap.put("button.copyLink","copySupervisionPlanLink");
		keyMap.put("button.view","viewDetails");
		keyMap.put("button.update","update");
		keyMap.put("button.copy","copy");
		keyMap.put("button.delete","delete");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.saveAsDraft", "saveAsDraft");
		keyMap.put("button.print", "printSupervisionPlan");
	}
   
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		supervisionPlanForm.setAction(UIConstants.UPDATE);
		supervisionPlanForm.setSecondaryAction("");
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward copy(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		supervisionPlanForm.setAction(UIConstants.COPY);
		supervisionPlanForm.setSecondaryAction("");
		supervisionPlanForm.setSupervisionPlanDate(null);
		supervisionPlanForm.setSupervisionPlanDateStr("");
		return aMapping.findForward(UIConstants.COPY_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		supervisionPlanForm.setAction(UIConstants.DELETE);
		supervisionPlanForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
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
	public ActionForward updateSupervisionPlanLink(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		String selectedSupervisionPlanId = supervisionPlanForm.getSelectedSupervisionPlanId();
		String taskId = supervisionPlanForm.getTaskId();
		supervisionPlanForm.clearAll();
		AdminSupervisionPlanHelper.initializeSupervisionPlanForm(assessmentForm, supervisionPlanForm);
		supervisionPlanForm.setAction(UIConstants.UPDATE);
		supervisionPlanForm.setSecondaryAction("");
		supervisionPlanForm.getAgencyId();
		supervisionPlanForm.setSupervisionPlanId(selectedSupervisionPlanId);
		supervisionPlanForm.setTaskId(taskId);
		GetSupervisionPlanDetailsEvent supPlanDetailsEvent = (GetSupervisionPlanDetailsEvent)AdminSupervisionPlanHelper.getSupervisionPlanDetailsEvent(supervisionPlanForm);
		CompositeResponse compositeResponse = this.postRequestEvent(supPlanDetailsEvent);
		SupervisionPlanDetailsResponseEvent supervisionPlanDetailsRespEvent = (SupervisionPlanDetailsResponseEvent)MessageUtil.filterComposite(compositeResponse, SupervisionPlanDetailsResponseEvent.class);
		if(supervisionPlanDetailsRespEvent == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve selected Supervision Plan Details.");
			aMapping.findForward(UIConstants.VIEW_FAILURE);
		}
		
		AdminSupervisionPlanHelper.populateSupervisionPlanDetailsResponseEvent(supervisionPlanForm, supervisionPlanDetailsRespEvent);
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
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
	public ActionForward copySupervisionPlanLink(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		String selectedSupervisionPlanId = supervisionPlanForm.getSelectedSupervisionPlanId();	
		supervisionPlanForm.clearAll();
		AdminSupervisionPlanHelper.initializeSupervisionPlanForm(assessmentForm, supervisionPlanForm);
		supervisionPlanForm.setAction(UIConstants.COPY);
		supervisionPlanForm.setSecondaryAction("");
		supervisionPlanForm.getAgencyId();
		supervisionPlanForm.setSupervisionPlanId(selectedSupervisionPlanId);
		GetSupervisionPlanDetailsEvent supPlanDetailsEvent = (GetSupervisionPlanDetailsEvent)AdminSupervisionPlanHelper.getSupervisionPlanDetailsEvent(supervisionPlanForm);
		CompositeResponse compositeResponse = this.postRequestEvent(supPlanDetailsEvent);
		SupervisionPlanDetailsResponseEvent supervisionPlanDetailsRespEvent = (SupervisionPlanDetailsResponseEvent)MessageUtil.filterComposite(compositeResponse, SupervisionPlanDetailsResponseEvent.class);
		if(supervisionPlanDetailsRespEvent == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve selected Supervision Plan details");
			aMapping.findForward(UIConstants.VIEW_FAILURE);
		}
		AdminSupervisionPlanHelper.populateSupervisionPlanDetailsResponseEvent(supervisionPlanForm, supervisionPlanDetailsRespEvent);
		supervisionPlanForm.setSupervisionPlanId("");
		return aMapping.findForward(UIConstants.COPY_SUCCESS);
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
	public ActionForward viewDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		String selectedSupPlanId = supervisionPlanForm.getSelectedSupervisionPlanId();	
		supervisionPlanForm.clearAll();
		AdminSupervisionPlanHelper.initializeSupervisionPlanForm(assessmentForm, supervisionPlanForm);
		supervisionPlanForm.setAction(UIConstants.VIEW);
		supervisionPlanForm.setSecondaryAction("");
		supervisionPlanForm.getAgencyId();
		supervisionPlanForm.setSupervisionPlanId(selectedSupPlanId);
		supervisionPlanForm.setDraftSupervisionPlanExists(assessmentForm.isDraftSupPlanExists());
		GetSupervisionPlanDetailsEvent supPlanDetailsEvent = (GetSupervisionPlanDetailsEvent)AdminSupervisionPlanHelper.getSupervisionPlanDetailsEvent(supervisionPlanForm);
		CompositeResponse compositeResponse = this.postRequestEvent(supPlanDetailsEvent);
		SupervisionPlanDetailsResponseEvent supervisionPlanDetailsRespEvent = (SupervisionPlanDetailsResponseEvent)MessageUtil.filterComposite(compositeResponse, SupervisionPlanDetailsResponseEvent.class);
		if(supervisionPlanDetailsRespEvent == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve selected Supervision Plan Details.");
			aMapping.findForward(UIConstants.VIEW_FAILURE);
		}
		AdminSupervisionPlanHelper.populateSupervisionPlanDetailsResponseEvent(supervisionPlanForm, supervisionPlanDetailsRespEvent);		
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.FINISH);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.SAVE_AS_DRAFT_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws Exception
	 */
	public ActionForward printSupervisionPlan(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {	 
		SupervisionPlanReportingBean spRb = new SupervisionPlanReportingBean();
		SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
        //set supervisee information
		spRb.setSpn(superviseeHeaderForm.getSuperviseeId());
		spRb.setDefendantName(superviseeHeaderForm.getSuperviseeNameDesc());
        spRb.setLos(superviseeHeaderForm.getLOSDesc());
        //set assigned CSO information
        spRb.setAssignedCSO(superviseeHeaderForm.getOfficerNameDesc());
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setStaffPositionId(superviseeHeaderForm.getOfficerPositionId());		
		LightCSCDStaffResponseEvent staffPositionResponse = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
        if(staffPositionResponse != null){
        	spRb.setCsoPoi(staffPositionResponse.getProbationOfficerInd());
        	spRb.setStaffPositionName(staffPositionResponse.getStaffPositionName());
        } else {
        	spRb.setCsoPoi("");
        	spRb.setStaffPositionName("");
        }
        //set supervision plan information
        SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		supervisionPlanForm.setSecondaryAction("");
		spRb.setPlanDate(supervisionPlanForm.getSupervisionPlanDateStr());
		spRb.setProblemStmt(escapeXMLChars(supervisionPlanForm.getProblem()));
        spRb.setBehavioralObj(escapeXMLChars(supervisionPlanForm.getBehaviorObjective()));
        spRb.setOffenderActionPlan(escapeXMLChars(supervisionPlanForm.getOffenderActionPlan()));
        spRb.setCsoActionPlan(escapeXMLChars(supervisionPlanForm.getCsoActionPlan()));	  
        
        ReportRequestEvent supOrderEvent = new SupervisionPlanPrintTemplateRequestEvent();	
        supOrderEvent.addDataObject(spRb);
        StringBuffer reportName = new StringBuffer( CONTEXTKEYPREFIX )
                                .append("SUPERVISION PLAN");
        if(spRb.isPrintSpanish()){
        	reportName.append(SPANISH);
        }
        supOrderEvent.setReportName(reportName.toString());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(supOrderEvent);
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        ReportResponseEvent aRespEvent = null;
        aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(response, ReportResponseEvent.class);
        if (aRespEvent == null) {
            ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
            throw returnException;
        } else {
            String fileName = "CSCD-Supervision Plan" ;
            aResponse.setContentType("application/x-file-download");
            aResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + FILEEXT);
            aResponse.setHeader("Cache-Control", "must-revalidate");
            aResponse.setContentLength(aRespEvent.getContent().length);
            aResponse.resetBuffer();
            OutputStream os = aResponse.getOutputStream();
            os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
            os.flush();
            os.close();
        }
     return (null);
    }
	
	 /**
     * Escapes the special XML characters like &,  ',",< and >  in the string passed.
     * @param string The string in which the special chars  needs to be escaped.
     * @return The string with escaped XML chars.
     */
    private String escapeXMLChars(String string)
    {
        if(string != null && !"".equals( string))
        {
        	string = string.replaceAll("<br />",  "<br/>").replaceAll("> <", "><space/><").replaceAll("&nbsp;", "<space/>").replaceAll("\\\\+", "&#92;&#92;");
        }
        return string;
    }
}
