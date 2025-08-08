//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\HandleSupervisionOrderSelectionAction.java

package ui.supervision.administerassessments.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.report.AssessmentsPrintTemplateRequestEvent;
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
import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;
import ui.common.CSCQuestionResponse;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;
import ui.supervision.administersupervisionplan.AssessmentsPlanReportingBean;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author dgibler
 *  
 */
public class SubmitWisconsinAssessmentWebFocusPrintAction extends JIMSBaseAction
{
	  public static final String COMMA = ",";

	    public static final String ZERO = "0";
	    
	    public static final String ASSESSWIS_ID = "&ASSESSWIS_ID=";
	    
	    public static final String AMPERNAME = "&partyName=";

	    public static final String CONTEXTKEYPREFIX = "REPORTING::";
	    
	    public static final String FILEEXT = ".pdf";

	    /**
	     * @roseuid 438F240E01BC
	     */
	    public SubmitWisconsinAssessmentWebFocusPrintAction()
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
	        this.processReport(aMapping, aForm, aRequest, aResponse);
	        return null;
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
//	        ActionForward forward = new ActionForward();
	        WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm) form;
//	        to obtain the Supervisee Details
	        SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
	        AssessmentsPlanReportingBean reportBean = new AssessmentsPlanReportingBean();
	        reportBean.setAssessementDate(wisconsinForm.getAssessmentDateStr());
	        reportBean.setNeedAssessmentQuestionList(wisconsinForm.getNeedsAssessmentQuestionsList());
	        reportBean.setNeedsLevel(wisconsinForm.getNeedsLevel());
	        reportBean.setRiskLevel(wisconsinForm.getRiskLevel());
	        Object[] riskAssessments = wisconsinForm.getRiskAssessmentQuestionsList().toArray();	
	        reportBean.setReAssessment(false);
			reportBean.setPageHeading("RISK ASSESSMENT");
	         
	         for(int i=riskAssessments.length-1; i >= 0; i--){
	        	 CSCQuestionGroup group = (CSCQuestionGroup) riskAssessments[i];
	        	 Object[] questions = group.getQuestions().toArray();
	        	   for(int j = questions.length-1; j>=0;j--){
	        		   CSCQuestion quest = (CSCQuestion) questions[j];
	        		   if("RATE THE FOLLOWING BASED ON PERIOD SINCE LAST CLASSIFICATION:".equalsIgnoreCase(quest.getQuestionText())){
	        			   reportBean.setReAssessment(true);
	        			   reportBean.setPageHeading("RISK REASSESSMENT");       			   
	        		   }
	        		   if(quest.getQuestionText().indexOf((j+1) + ". ") == -1 && !"RATE THE FOLLOWING BASED ON PERIOD SINCE LAST CLASSIFICATION:".equalsIgnoreCase(quest.getQuestionText()) ){
	        			   quest.setQuestionText((j+1) + ". " + quest.getQuestionText());
	        		   }
	        		   String responseStr = quest.getResponseId();
	        		   if(responseStr != null){
	        		   Object[] choices = quest.getResponseChoices().toArray();
	        		   for(int k=choices.length-1;k>=0;k-- ){
	        			   CSCQuestionResponse cscQuestionResp = (CSCQuestionResponse)   choices[k];
	        			   if(responseStr.equals(cscQuestionResp.getResponseId())){
	        				   quest.setResponseText(cscQuestionResp.getResponseValue());
	        				   break;
	        			   }
	        		   }	      
	        		   }
	        		   String[] qText = quest.getQuestionText().split("<br>");
	        		   quest.setFormattedQText(qText);
	        	   }
	         }
	         
	         Object[] needsAssessments = wisconsinForm.getNeedsAssessmentQuestionsList().toArray();
	         for(int i=needsAssessments.length-1; i >= 0; i--){
	        	 CSCQuestionGroup group = (CSCQuestionGroup) needsAssessments[i];
	        	 Object[] questions = group.getQuestions().toArray();	        	 
	        	   for(int j = questions.length-1; j>=0;j--){	        		  
	        		   CSCQuestion quest = (CSCQuestion) questions[j];
	        		   String responseStr = quest.getResponseId();	        		   
	        		   if(quest.getQuestionText().indexOf( (j+1) + ". ") == -1){
	        			   quest.setQuestionText((j+1) + ". " + quest.getQuestionText());
	        		   }
	        		   Object[] choices = quest.getResponseChoices().toArray();
	        		   List newChoices = new ArrayList();	
	        		   if(responseStr != null){
	        		   for(int k=0;k<=choices.length-1;k++ ){
	        			   CSCQuestionResponse cscQuestionResp = (CSCQuestionResponse)   choices[k];	        			   
	        			   if(responseStr.equals(cscQuestionResp.getResponseId())){
	        				   quest.setResponseText(new String(cscQuestionResp.getResponseValue()));	        				   
	        			   }
	        			   if(cscQuestionResp.getResponseValue().equals("")){
	        				   quest.getResponseChoices().remove(cscQuestionResp);
	        			   } else if(!cscQuestionResp.getResponseValue().equals("0") && !cscQuestionResp.getResponseValue().equals(" ") && 
	        					   cscQuestionResp.getResponseValue().indexOf("-") == -1 && 
	        					   cscQuestionResp.getResponseValue().indexOf("+") == -1 ){
	        				   cscQuestionResp.setResponseValue("+" + cscQuestionResp.getResponseValue());
	        			   }
	        				   
	        		   }	
	        		   }
	        		   if( quest.getResponseChoices().size() < 4){
	        			   int numberOfObj = 4 - quest.getResponseChoices().size();
	        			   for(int n=1;n<=numberOfObj;n++){
	        				   CSCQuestionResponse newResp = new CSCQuestionResponse();
	        				   newResp.setResponseValue(" ");
	        				   newChoices.add(newResp);
	        			   }
	        			   newChoices.addAll(quest.getResponseChoices());
	        			   quest.setResponseChoices(newChoices);
	        		   } 
	        	   }
	         }
	        
	        reportBean.setRiskAssessmentQuestionList(wisconsinForm.getRiskAssessmentQuestionsList());
	        reportBean.setSpn(wisconsinForm.getDefendantId());
	        reportBean.setDefendantName(superviseeHeaderForm.getSuperviseeNameDesc());
	        reportBean.setTotalRiskScore(wisconsinForm.getTotalRiskScore());
	        reportBean.setTotalNeedsScore(wisconsinForm.getTotalNeedsScore());
	        	        
	        ReportRequestEvent assessmentsReportEvent = new AssessmentsPrintTemplateRequestEvent();
	        assessmentsReportEvent.setReportName( new StringBuffer( CONTEXTKEYPREFIX )
            .append("ASSESSMENTS REPORT").toString());
	        assessmentsReportEvent.addDataObject(reportBean);
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	        dispatch.postEvent(assessmentsReportEvent);
	        CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	        ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(resp, ReportResponseEvent.class);
	        if (aRespEvent == null) {
	            ReturnException returnException = (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
	            throw returnException;
	        } else {
	            String fileName = "CSCD-Supervision Plan" ;
	            response.setContentType("application/x-file-download");
	            response.setHeader("Content-disposition", "attachment; filename=" + fileName + FILEEXT);
	            response.setHeader("Cache-Control", "must-revalidate");
	            response.setContentLength(aRespEvent.getContent().length);
	            response.resetBuffer();
	            OutputStream os = response.getOutputStream();
	            os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
	            os.flush();
	            os.close();
	        }
	        
	        //Get user logged On
//			ISecurityManager mgr = (ISecurityManager) ContextManager.getSession()
//			.get("ISecurityManager");
//			IUserInfo userInfo = mgr.getIUserInfo();
//
//			String userId = userInfo.getJIMSLogonId();
//			String passWord = userInfo.getJIMS2Password();
//	        
//	        String reportName = null;
//			
//	        String wisconsinAssessType = wisconsinForm.getWisconsinAssessmentType(); 
//	        if(wisconsinAssessType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
//	        {
//	        	reportName = "ASSESSMENTS";
//	        }
//	        else if(wisconsinAssessType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT))
//	        {
//	        	reportName = "REASSESSMENTS";
//	        }
//	        
//	        String assessmentId = wisconsinForm.getAssessmentId();
//	        
//	        GetWebFocusReportEvent requestEvent = (GetWebFocusReportEvent) EventFactory
//				.getInstance(WebFocusReportCatalogControllerServiceNames.GETWEBFOCUSREPORT);
//
//	     	     	
//	     	String report = reportName.toString().replaceAll(" ","");
//
//	     	requestEvent.setWebFocusName(report);
//			
//	     	WebFocusReportCatalogResponseEvent reportResp = (WebFocusReportCatalogResponseEvent) 
//	     					MessageUtil.postRequest(requestEvent, WebFocusReportCatalogResponseEvent.class);
//	        
//	     	
//	     	// Builds the Web Focus url
//	     	StringBuffer reportSb = new StringBuffer();
//	     	if(reportResp != null){
//	     	    
//	     	    reportSb.append(reportResp.getUrl());
//	     	    reportSb.append(report.toLowerCase());
//	     	    reportSb.append(reportResp.getUserParm());
//	     	    reportSb.append(userId);
//	     	    reportSb.append(reportResp.getPasswordParm());
//	     	    reportSb.append(passWord);
//	     	    reportSb.append(ASSESSWIS_ID);
//	     	    reportSb.append(assessmentId);
//	     	    reportSb.append(AMPERNAME);
//	     	    reportSb.append(partyName);
//	     	    reportSb.append(AMPERDEFENDANT_ID);
//	     	    reportSb.append(defendant_id);
//
//	     	}else{
//	     	    
//	     	   sendToErrorPage(aRequest,"error.no.search.results.found");
//	     	//   reportSb.append(report.toLowerCase());
//	     	   return reportSb.toString();
//	     	}
//	      
	        return null;

	    }
}