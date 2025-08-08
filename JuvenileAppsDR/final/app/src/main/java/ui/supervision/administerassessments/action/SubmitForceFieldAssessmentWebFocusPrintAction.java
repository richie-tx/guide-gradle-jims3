/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package ui.supervision.administerassessments.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.webfocus.GetWebFocusReportEvent;
import messaging.webfocus.reply.WebFocusReportCatalogResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.WebFocusReportCatalogControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author dgibler
 *  
 */
public class SubmitForceFieldAssessmentWebFocusPrintAction extends JIMSBaseAction
{
	  public static final String COMMA = ",";

	    public static final String ZERO = "0";
	    
	    public static final String CSFORCEASSESS_ID  = "&CSFORCEASSESS_ID=";
	    
	    public static final String AMPERNAME = "&partyName=";

	    /**
	     * @roseuid 438F240E01BC
	     */
	    public SubmitForceFieldAssessmentWebFocusPrintAction()
	    {

	    }

	    /**
	     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	     * @return Map
	     */
	    /* (non-Javadoc)
		 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
		 */
		protected void addButtonMapping(Map keyMap)
		{
			keyMap.put("button.print", "printOrder");
	        keyMap.put("button.printDraft", "printDraftOrder");
	        keyMap.put("button.printSpanish", "printSpanishOrder");
	        keyMap.put("button.printSpanishVersion", "printSpanishVersion");
	        keyMap.put("button.back", "back");
	        keyMap.put("button.cancel", "cancel");
		}
	   

	    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil
	                .getCurrentUserAgencyID()));
	    }

	    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(
	                UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
	        return forward;
	    }


	    public ActionForward printOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        String path = this.processReport(aMapping, aForm, aRequest, aResponse);
	        
	        if(path.length() < 50){
	            
	            throw new GeneralFeedbackMessageException("The Print Template " + path + 
	                      " for Process Supervision Order cannot be found. Call System Admin to correct this problem");
	        }
	         ActionForward af = new ActionForward(path, true);

	        return af;
	    }

	    public ActionForward printDraftOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        processReport(aMapping, aForm, aRequest, aResponse);
	        return (null);
	    }

	    public ActionForward printSpanishOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        processReport(aMapping, aForm, aRequest, aResponse);
	        return (null);
	    }

	    public ActionForward printSpanishVersion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse) throws Exception
	    {
	        processReport(aMapping, aForm, aRequest, aResponse);
	        return (null);
	    }

	    private String processReport(ActionMapping aMapping, ActionForm form, HttpServletRequest aRequest, HttpServletResponse response)
	            throws Exception
	    {
	        ActionForward forward = new ActionForward();
	        ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm) form;
	        
//	        to obtain the Supervisee Details
	        SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
	        String partyName = superviseeHeaderForm.getSuperviseeNameDesc();
			
	        ReportResponseEvent aRespEvent = null;
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	        
	        //Get user logged On
			ISecurityManager mgr = (ISecurityManager) ContextManager.getSession()
			.get("ISecurityManager");
			IUserInfo userInfo = mgr.getIUserInfo();

			String userId = userInfo.getJIMSLogonId();
			//String passWord = userInfo.getJIMS2Password();
	        
	        String reportName = "FORCEFIELD";
	        String assessmentId = forceFieldForm.getAssessmentId();
	        
	        GetWebFocusReportEvent requestEvent = (GetWebFocusReportEvent) EventFactory
				.getInstance(WebFocusReportCatalogControllerServiceNames.GETWEBFOCUSREPORT);

	     	     	
	     	String report = reportName.toString().replaceAll(" ","");

	     	requestEvent.setWebFocusName(report);
			
	     	WebFocusReportCatalogResponseEvent reportResp = (WebFocusReportCatalogResponseEvent) 
	     					MessageUtil.postRequest(requestEvent, WebFocusReportCatalogResponseEvent.class);
	        
	     	
	     	// Builds the Web Focus url
	     	StringBuffer reportSb = new StringBuffer();
	     	if(reportResp != null){
	     	    
	     	    reportSb.append(reportResp.getUrl());
	     	    reportSb.append(report.toLowerCase());
	     	    reportSb.append(reportResp.getUserParm());
	     	    reportSb.append(userId);
	     	   // reportSb.append(reportResp.getPasswordParm());
	     	 //   reportSb.append(passWord);
	     	    reportSb.append(CSFORCEASSESS_ID);
	     	    reportSb.append(assessmentId);
	     	    reportSb.append(AMPERNAME);
	     	    reportSb.append(partyName);
	     	}else{
	     	    
	     	   sendToErrorPage(aRequest,"error.no.search.results.found");
	     	//   reportSb.append(report.toLowerCase());
	     	   return reportSb.toString();
	     	}
	      
	        return reportSb.toString();

	    }

 
}
