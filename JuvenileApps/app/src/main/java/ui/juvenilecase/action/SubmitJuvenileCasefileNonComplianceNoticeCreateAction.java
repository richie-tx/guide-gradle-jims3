//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\casefile\\action\\HandleJuvenileCasefileVOPSelectionAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetCasefileNonComplianceNoticesEvent;
import messaging.casefile.SaveCasefileNonComplianceNoticeEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.notification.SendNonComplianceSanctionDateExpiredNotificationEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.CalendarConstants;
import naming.ActivityConstants;
import naming.JuvenileCasefileNonComplianceControllerServiceNames;
import naming.JuvenileCasefileNotificationControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileNonComplianceForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;


public class SubmitJuvenileCasefileNonComplianceNoticeCreateAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.generateNotice", "generateNotice");
		keyMap.put("button.noticeList", "returnToSelection");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}	
 
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;
   /**
    * 
    */
   public SubmitJuvenileCasefileNonComplianceNoticeCreateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward generateNotice(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   throws GeneralFeedbackMessageException
   {
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   if ("Y".equals(jncForm.getNoiticeGeneratedInd()) )
	   {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
				"Notice already generated. If printed copy needed,\n click Notice List button then select 'View Notice History' link under Non-Compliance Notice Tab"));
			saveErrors(aRequest, errors);
			jncForm.setConfirmationMsg("");
		    return aMapping.findForward("finish");
	   }
	   jncForm.setCurrentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	   jncForm.setNonComplianceDateStr(DateUtil.dateToString(jncForm.getNonComplianceDate(), "MMMM d, yyyy"));
	   SaveCasefileNonComplianceNoticeEvent requestEvent = (SaveCasefileNonComplianceNoticeEvent) EventFactory
       .getInstance(JuvenileCasefileNonComplianceControllerServiceNames.SAVECASEFILENONCOMPLIANCENOTICE);
	   
	   UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(),
               ActivityConstants.VOP_SANCTIONS_DOCUMENTED, UIConstants.EMPTY_STRING);

	   requestEvent.setCasefileId(jncForm.getSupervisionNum());
	   requestEvent.setNonComplianceDate(jncForm.getNonComplianceDate());
	   requestEvent.setSanctionAssignedDate(jncForm.getSanctionAssignedDate());
	   requestEvent.setCompleteSanctionByDate(jncForm.getSanctionCompleteByDate());
// Parent informed moved to Juvenile Completion Status in SubmitJuvenileCasefileNonComplianceNoticeUpdateAction
//	   requestEvent.setParentInformed(false); 
//	   if ("true".equalsIgnoreCase(jncForm.getParentalNotified()))
//	   {
//		   requestEvent.setParentInformed(true); 
//	   } 
	   requestEvent.setViolationLevelId(jncForm.getViolationLevelId());
	   requestEvent.setViolations(jncForm.getProbationViolationList());
	   requestEvent.setSanctionLevelId(jncForm.getSanctionLevelId());
	   requestEvent.setSanctions(jncForm.getSelectedSanctionsList());
	   
	   aRequest.getSession().setAttribute("reportInfo", jncForm);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.NONCOMPLIANCE_NOTICE);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");

		if( pdfDocument == null || pdfDocument.length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}
		requestEvent.setDocument(pdfDocument);
	   
	   CompositeResponse response =  MessageUtil.postRequest(requestEvent);
	   ReturnException returnException =
		   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			jncForm.setConfirmationMsg("Non-Compliance Notice has been generated.");
			jncForm.setNoiticeGeneratedInd("Y");
		} 
	   UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(),
               ActivityConstants.VOP_SANCTIONS_NOTICE_GENERATED, UIConstants.EMPTY_STRING);

	   if (returnException == null) {
		   	CasefileNonComplianceNoticeResponseEvent cnRespEvent =
			   (CasefileNonComplianceNoticeResponseEvent) MessageUtil.filterComposite(response, CasefileNonComplianceNoticeResponseEvent.class);
		   	String noticeOID = cnRespEvent.getCasefileNonComplianceNoticeId();
		   	JuvenileCasefileForm jcfForm = (JuvenileCasefileForm) getSessionForm(aMapping, aRequest, "juvenileCasefileForm", true);
//begin create eventTask record add 		  
	        SendNonComplianceSanctionDateExpiredNotificationEvent sendNotificationEvent =
				(SendNonComplianceSanctionDateExpiredNotificationEvent) EventFactory.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDNONCOMPLIANCESANCTIONDATEEXPIREDNOTIFICATION);
			sendNotificationEvent.setJuvenileNum(jncForm.getJuvenileNum());
			sendNotificationEvent.setSupervisionNumber(jncForm.getSupervisionNum());
			sendNotificationEvent.setNoticeId(noticeOID);
			sendNotificationEvent.setIdentityType( "jpo" );
			sendNotificationEvent.setIdentity( jcfForm.getProbationOfficerLogonId() );
			sendNotificationEvent.setNoticeTopic("MJCW.JPO.OVERDUE.NONCOMPLIANCE.SANCTION.COMPLETE.NOTIFICATION");
//build subject
			sendNotificationEvent.setSubject("Non-Compliance Sanctions have not been completed.");
//message built in command because current status value is need message text			
			sendNotificationEvent.setNotificationMessage("");
//Create the task name
			StringBuffer sb = new StringBuffer();
			sb.append( sendNotificationEvent.getClass().getName() );
			sb.append( "-" );
			sb.append( Math.random() );
//Registering the task with the scheduler
			RegisterTaskEvent rtEvent = new RegisterTaskEvent();
			rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
			rtEvent.setFirstNotificationDate(jncForm.getSanctionCompleteByDate());
			rtEvent.setNextNotificationDate(jncForm.getSanctionCompleteByDate()); 
			rtEvent.setTaskName(sb.toString());
			rtEvent.setNotificationEvent(sendNotificationEvent);  // create new event for NonCompliance
			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
			sb = null;
	   }
	   return aMapping.findForward("finish");
   }
  
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward generateNoticeUJAC(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   throws GeneralFeedbackMessageException
   {
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   if ("Y".equals(jncForm.getNoiticeGeneratedInd()) )
	   {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
				"Notice already generated. If printed copy needed,\n click Notice List button then select 'View Notice History' link under Non-Compliance Notice Tab"));
			saveErrors(aRequest, errors);
			jncForm.setConfirmationMsg("");
		    return aMapping.findForward("finish");
	   } 
	   jncForm.setCurrentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	   SaveCasefileNonComplianceNoticeEvent requestEvent = (SaveCasefileNonComplianceNoticeEvent) EventFactory
       .getInstance(JuvenileCasefileNonComplianceControllerServiceNames.SAVECASEFILENONCOMPLIANCENOTICE);
	   
	   UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(),
               ActivityConstants.VOP_SANCTIONS_DOCUMENTED, UIConstants.EMPTY_STRING);

	   requestEvent.setCasefileId(jncForm.getSupervisionNum());
	   requestEvent.setNonComplianceDate(jncForm.getNonComplianceDate());
	   requestEvent.setSanctionAssignedDate(jncForm.getSanctionAssignedDate());
	   requestEvent.setCompleteSanctionByDate(jncForm.getSanctionCompleteByDate());
// Parent informed moved to Juvenile Completion Status in SubmitJuvenileCasefileNonComplianceNoticeUpdateAction
//	   requestEvent.setParentInformed(false); 
//	   if ("true".equalsIgnoreCase(jncForm.getParentalNotified()))
//	   {
//		   requestEvent.setParentInformed(true); 
//	   } 
	   requestEvent.setViolationLevelId(jncForm.getViolationLevelId());
	   requestEvent.setViolations(jncForm.getProbationViolationList());
	   requestEvent.setSanctionLevelId(jncForm.getSanctionLevelId());
	   requestEvent.setSanctions(jncForm.getSelectedSanctionsList());

/** begin pdf coding */	   
		CompositeResponse compResp = sendPrintRequest("REPORTING::NONCOMPLIANCE_NOTICE", jncForm, null);
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
		MessageUtil.filterComposite(compResp, ReportResponseEvent.class);

		if( aReportRespEvt == null || aReportRespEvt.getContent() == null || aReportRespEvt.getContent().length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}
		try {
			setPrintContentResp(aResponse, compResp, "NONCOMPLIANCE_NOTICE", UIConstants.PRINT_AS_PDF_DOC);
	
		} catch (GeneralFeedbackMessageException e) {
			e.printStackTrace();
		}
		requestEvent.setDocument(aReportRespEvt.getContent());
/** end pdf coding */  
	   
	   CompositeResponse response =  MessageUtil.postRequest(requestEvent);
	   ReturnException returnException =
		   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			jncForm.setConfirmationMsg("Non-Compliance Notice has been generated.");
			jncForm.setNoiticeGeneratedInd("Y");
		} 
	   UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(),
               ActivityConstants.VOP_SANCTIONS_NOTICE_GENERATED, UIConstants.EMPTY_STRING);

	   if (returnException == null) {
		   	CasefileNonComplianceNoticeResponseEvent cnRespEvent =
			   (CasefileNonComplianceNoticeResponseEvent) MessageUtil.filterComposite(response, CasefileNonComplianceNoticeResponseEvent.class);
		   	String noticeOID = cnRespEvent.getCasefileNonComplianceNoticeId();
		   	JuvenileCasefileForm jcfForm = (JuvenileCasefileForm) getSessionForm(aMapping, aRequest, "juvenileCasefileForm", true);
// begin create eventTask record add 		  
	        SendNonComplianceSanctionDateExpiredNotificationEvent sendNotificationEvent =
				(SendNonComplianceSanctionDateExpiredNotificationEvent) EventFactory.getInstance(JuvenileCasefileNotificationControllerServiceNames.SENDNONCOMPLIANCESANCTIONDATEEXPIREDNOTIFICATION);
			sendNotificationEvent.setJuvenileNum(jncForm.getJuvenileNum());
			sendNotificationEvent.setSupervisionNumber(jncForm.getSupervisionNum());
			sendNotificationEvent.setNoticeId(noticeOID);
			sendNotificationEvent.setIdentityType( "jpo" );
			sendNotificationEvent.setIdentity( jcfForm.getProbationOfficerLogonId() );
			sendNotificationEvent.setNoticeTopic("MJCW.JPO.OVERDUE.NONCOMPLIANCE.SANCTION.COMPLETE.NOTIFICATION");
// build subject
			sendNotificationEvent.setSubject("Non-Compliance Sanctions have not been completed.");
// message built in command because current status value is need message text			
			sendNotificationEvent.setNotificationMessage("");
//Create the task name
			StringBuffer sb = new StringBuffer();
			sb.append( sendNotificationEvent.getClass().getName() );
			sb.append( "-" );
			sb.append( Math.random() );
//Registering the task with the scheduler
			RegisterTaskEvent rtEvent = new RegisterTaskEvent();
			rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
			rtEvent.setFirstNotificationDate(jncForm.getSanctionCompleteByDate());
			rtEvent.setNextNotificationDate(jncForm.getSanctionCompleteByDate()); 
			rtEvent.setTaskName(sb.toString());
			rtEvent.setNotificationEvent(sendNotificationEvent);  // create new event for NonCompliance
			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
			sb = null;
	   }
	   return aMapping.findForward("finish");
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward returnToSelection(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   	JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   	LoadNonComplianceList(jncForm);
		jncForm.setConfirmationMsg("");
		jncForm.setAction("");
		return aMapping.findForward("backToList");
   } 

  	public void LoadNonComplianceList(JuvenileNonComplianceForm jncForm )
  	{
	   	GetCasefileNonComplianceNoticesEvent event = (GetCasefileNonComplianceNoticesEvent) EventFactory.getInstance(JuvenileCasefileNonComplianceControllerServiceNames.GETCASEFILENONCOMPLIANCENOTICES);
	   	event.setCasefileId(jncForm.getSupervisionNum());
		List notices = MessageUtil.postRequestListFilter(event, CasefileNonComplianceNoticeResponseEvent.class);
		if (notices == null) {
			notices = new ArrayList();
		} 
		for (int n=0; n<notices.size(); n++)
		{
			CasefileNonComplianceNoticeResponseEvent cncEvent = (CasefileNonComplianceNoticeResponseEvent) notices.get(n);
			cncEvent.setNoticeSignatureStatusLit("");
			cncEvent.setJuvenileCompletedLit("");
			cncEvent.setViolationLevelLit("");
// set default literal
			cncEvent.setNoticeSignatureStatusLit("UNSIGNED");
			cncEvent.setJuvenileCompletedLit("PENDING");
			cncEvent.setViolationLevelLit("MINOR");
			if (cncEvent.getSignatureStatusId() != null)
			{
				cncEvent.setNoticeSignatureStatusLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSIGNATURE_STATUS, cncEvent.getSignatureStatusId()));
			}
			if (cncEvent.getCompletionStatusId() != null)
			{
				cncEvent.setJuvenileCompletedLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCCOMPLETION_STATUS, cncEvent.getCompletionStatusId()));
			}
			if (cncEvent.getViolationLevelId() != null)
			{
				cncEvent.setViolationLevelLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCVIOLATION_LEVEL, cncEvent.getViolationLevelId()));
			}
		}
		jncForm.setExistingNoticesList(notices);
        List documents = MessageUtil.postRequestListFilter(event, CasefileDocumentsResponseEvent.class);
        jncForm.setDocuments(documents);
  		   
  	}
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;

	String forward = UIConstants.BACK;
	if ("Y".equals(jncForm.getNoiticeGeneratedInd()) ) 
	{
	   	LoadNonComplianceList(jncForm);
		forward = "backToList";
		jncForm.setNoiticeGeneratedInd("");
	}
	jncForm.setConfirmationMsg("");
	jncForm.setAction("");
	return aMapping.findForward(forward);
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;	
	   jncForm.setConfirmationMsg("");
	   jncForm.setAction("");	   
	   return aMapping.findForward("cancel");
   } 
   
}