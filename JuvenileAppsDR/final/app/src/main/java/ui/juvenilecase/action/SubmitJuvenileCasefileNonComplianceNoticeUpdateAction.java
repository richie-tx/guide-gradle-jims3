//Source file: C:\\views\\juvenileCasework\\app\\src\\ui\\juvenilecase\\casefile\\action\\HandleJuvenileCasefileVOPSelectionAction.java

package ui.juvenilecase.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetCasefileNonComplianceNoticesEvent;
import messaging.casefile.UpdateCasefileNonComplianceCompletionEvent;
import messaging.casefile.UpdateCasefileNonComplianceSignatureEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasefileNonComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileNonComplianceForm;


public class SubmitJuvenileCasefileNonComplianceNoticeUpdateAction extends JIMSBaseAction
{
	 /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.printNotice", "printNotice");
		keyMap.put("button.submitSignatureStatus", "submitSignature"); 
		keyMap.put("button.submitCompletionStatus", "submitCompletion");
		keyMap.put("button.noticeList", "returnToSelection");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}	
   
   /**
    * 
    */
   public SubmitJuvenileCasefileNonComplianceNoticeUpdateAction () 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward printNotice(ActionMapping aMapping, ActionForm aForm,
		   HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception 
   {
// any change made to this method should also be made in create action
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
	   jncForm.setConfirmationMsg("");
	   jncForm.setCurrentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	   jncForm.setNonComplianceDateStr(DateUtil.dateToString(jncForm.getNonComplianceDate(), DateUtil.DATE_FMT_1));
	   jncForm.setSanctionCompleteByDateStr(DateUtil.dateToString(jncForm.getSanctionCompleteByDate(), DateUtil.DATE_FMT_1));
	   ReportRequestEvent nonCompliancePrintEvent = new ReportRequestEvent();
	   nonCompliancePrintEvent.addDataObject(jncForm);   
	   nonCompliancePrintEvent.setReportName("REPORTING::NONCOMPLIANCE_NOTICE");
	    	    
   	   ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.postRequest(nonCompliancePrintEvent, ReportResponseEvent.class );
   	    
   	   if (aRespEvent == null) {
   	    	return aMapping.findForward("casenotePrintException");
	   } 
   	   aResponse.setContentType("application/x-file-download");
   	   aResponse.setHeader("Content-disposition", "attachment; filename="
   	   							+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");   
   	   aResponse.setHeader("Cache-Control", "must-revalidate");   
   	   aResponse.setContentLength(aRespEvent.getContent().length);   
   	   aResponse.resetBuffer();   
   	   OutputStream os;
   	   os = aResponse.getOutputStream();
   	   os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);   
   	   os.flush();   
   	   os.close(); 
       return null;
   }  
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward submitSignature(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
// add code to update signature status	
	   UpdateCasefileNonComplianceSignatureEvent updateEvent = new  UpdateCasefileNonComplianceSignatureEvent();
	   updateEvent.setCasefileNonComplianceId(jncForm.getSelectedNoticeId());
	   updateEvent.setSignatureStatusId(jncForm.getSignatureStatus());
//	   if (jncForm.getSignatureSignedDateStr() != "") 
//	   {
		   updateEvent.setSignedDate(DateUtil.stringToDate(jncForm.getSignatureSignedDateStr(), DateUtil.DATE_FMT_1));
//	   } else {
//		   updateEvent.setSignedDate(DateUtil.getCurrentDate());  // defaults to current date
//		   jncForm.setSignatureSignedDateStr(DateUtil.dateToString(updateEvent.getSignedDate(),DateUtil.DATE_FMT_1));
//	   }
	   updateEvent.setParentInformed(false); 
	   if ("true".equalsIgnoreCase(jncForm.getParentalNotified()))
	   {
		   updateEvent.setParentInformed(true); 
	   }
	 
	   CompositeResponse response =  MessageUtil.postRequest(updateEvent);
	   ReturnException returnException =
		   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Error occured updating Signature Status"));
			saveErrors(aRequest, errors);	
		} else {
			jncForm.setSignatureStatusLiteral(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCSIGNATURE_STATUS,jncForm.getSignatureStatus() ) );	
			String msg = "Signature Status successfully updated to " + jncForm.getSignatureStatusLiteral() + ".";
			jncForm.setConfirmationMsg(msg);
			jncForm.setAction("confirm");
			   
			if (!"".equals(jncForm.getSignatureSignedDateStr() ) )
			{
			jncForm.setSignatureSignedDate(DateUtil.stringToDate(jncForm.getSignatureSignedDateStr(), DateUtil.DATE_FMT_1) );
			}
		} 
		if("SIG".equals(jncForm.getSignatureStatus() ) ) {
			UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(),
				ActivityConstants.VOP_SANCTIONS_NOTICE_SIGNED, UIConstants.EMPTY_STRING);
		} else {
			UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(),
				ActivityConstants.VOP_SANCTIONS_NOTICE_SIGNATURE_REFUSAL, UIConstants.EMPTY_STRING);			
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
   public ActionForward submitCompletion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   JuvenileNonComplianceForm jncForm = (JuvenileNonComplianceForm) aForm;
// add code to update juvenile completed information	
	   UpdateCasefileNonComplianceCompletionEvent updateEvent = new UpdateCasefileNonComplianceCompletionEvent();
	   updateEvent.setCasefileNonComplianceId(jncForm.getSelectedNoticeId());
	   updateEvent.setCompletionStatusId(jncForm.getJuvenileCompletedStatus());
	   updateEvent.setCompletionComments(jncForm.getJuvenileCompletedComments());
	   updateEvent.setActionTakenId(jncForm.getActionTakenId());
	   if ("YES".equals(jncForm.getJuvenileCompletedStatus() ) )
	   {
		   updateEvent.setActionTakenId(jncForm.getOriginalActionTakenId());
		   updateEvent.setActionTakenComments(jncForm.getOriginalOtherActionTakenComment()); 
		   jncForm.setActionTakenId(jncForm.getOriginalActionTakenId());
		   jncForm.setOtherActionTakenComment(jncForm.getOriginalOtherActionTakenComment());
	   } else {
//		   updateEvent.setActionTakenOtherText(jncForm.getActionTakenDesc());
		   updateEvent.setActionTakenComments(jncForm.getOtherActionTakenComment());
	   }
	   if (!"".equals(jncForm.getCompletionDateStr()))
	   {	   
		   updateEvent.setCompletionDate(DateUtil.stringToDate(jncForm.getCompletionDateStr(), DateUtil.DATE_FMT_1) );
	   }
	   CompositeResponse response =  MessageUtil.postRequest(updateEvent);
	   ReturnException returnException =
		   (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);

		if (returnException != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Error occured updating Completion Status"));
			saveErrors(aRequest, errors);	
		} else {
//	   jncForm.setJuvenileCompletedStatusLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCCOMPLETION_STATUS,jncForm.getJuvenileCompletedStatus() ) );	   
		   StringBuffer activityComments = new StringBuffer("Completion Status changed to  ");
		   
		   if ("YES".equalsIgnoreCase(jncForm.getJuvenileCompletedStatus() ) ) 
		   {
			   jncForm.setJuvenileCompletedStatusLit("Yes");
		   }
		   if ("NOO".equalsIgnoreCase(jncForm.getJuvenileCompletedStatus() ) ) 
		   {
			   jncForm.setJuvenileCompletedStatusLit("No");
		   }
		   if ("PAR".equalsIgnoreCase(jncForm.getJuvenileCompletedStatus() ) ) 
		   {
			   jncForm.setJuvenileCompletedStatusLit("Partial");
		   }
		   jncForm.setActionTakenDesc("");
		   if (!"".equalsIgnoreCase(jncForm.getActionTakenId() ))
		   {
			   if ("OTH".equalsIgnoreCase(jncForm.getActionTakenId()) ) 
			   {
				   jncForm.setActionTakenDesc("Other"); 
			   } else {
				   jncForm.setActionTakenDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.NCACTION_TAKEN, jncForm.getActionTakenId() ) );
			   }	   
		   }
		   activityComments.append(jncForm.getJuvenileCompletedStatusLit());
		   activityComments.append(", ");
		   boolean completionInfoEntered = false;
		   
		   if (updateEvent.getCompletionComments() != null && !updateEvent.getCompletionComments().equals(UIConstants.EMPTY_STRING)){
			   activityComments.append(" Completion Comments: ");
			   activityComments.append(updateEvent.getCompletionComments());
			   completionInfoEntered = true;
		   }
		   if (updateEvent.getActionTakenComments() != null && !updateEvent.getActionTakenComments().equals(UIConstants.EMPTY_STRING)){
			   if (!jncForm.getJuvenileCompletedStatus().equals("YES")){
				   if (completionInfoEntered){
					   activityComments.append(", ");
				   }
				   activityComments.append("Action taken Comments: ");
				   activityComments.append(updateEvent.getActionTakenComments());
			   }
		   }
		   UIJuvenileHelper.createActivity(jncForm.getSupervisionNum(), ActivityConstants.VOP_SANCTIONS_COMPLETION_STATUS_UPDATE, activityComments.toString());
		   
		   String msg = "Juvenile Completion Status successfully updated to " + jncForm.getJuvenileCompletedStatusLit() + ".";
		   jncForm.setConfirmationMsg(msg);	
		   jncForm.setAction("confirm");
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
		jncForm.setConfirmationMsg("");
		jncForm.setAction("");
		return aMapping.findForward("returnToSelection");
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
	jncForm.setConfirmationMsg("");
	jncForm.setAction("");
	return aMapping.findForward(UIConstants.BACK);
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
