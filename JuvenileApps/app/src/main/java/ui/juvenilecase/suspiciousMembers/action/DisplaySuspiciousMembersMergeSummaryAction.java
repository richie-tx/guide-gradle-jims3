//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.notification.to.EmailAttachmentBean;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;
import ui.juvenilecase.suspiciousMembers.UISuspiciousMemberDetailsHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/*
 * 
 * @author cShimek
 * 
 */

public class DisplaySuspiciousMembersMergeSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySuspiciousMembersMergeSummaryAction() {

	}
	private final String contactAdmin = "Please contact your System Administrator with a description of this problem." ;
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.printMerger", "printMerger");
		keyMap.put("button.printMergerBFO", "printMergerBFO");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward printMerger(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
/** selections are disabled in jsp, struts does not return selectedFromId and selectedToId value */
		smForm.setSelectedFromId(smForm.getSelFromId());
		smForm.setSelectedToId(smForm.getSelToId());
		
		UISuspiciousMemberDetailsHelper.loadMemberDetails(smForm.getSelectedFromId(), smForm);
		smForm.setMemberNumber(smForm.getSelectedFromId());
/** begin pdf coding */	   
		CompositeResponse compResp = sendPrintRequest("REPORTING::SUSPICIOUSMEMBERMERGE_FORM", smForm, null);
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
		MessageUtil.filterComposite(compResp, ReportResponseEvent.class);

		if( aReportRespEvt == null || aReportRespEvt.getContent() == null || aReportRespEvt.getContent().length < 1 )
		{
			sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		}
		try {
			setPrintContentResp(aResponse, compResp, "SUSPICIOUSMEMBERMERGE_FORM", UIConstants.PRINT_AS_PDF_DOC);
	
		} catch (GeneralFeedbackMessageException e) {
			e.printStackTrace();
		}
		List filteredList = UISuspiciousMemberDetailsHelper.filterJuvenilesByMemberNumber(smForm.getSelectedFromId(), smForm.getSelectedToId(), smForm.getAllAssociatedJuvenilesList());
		filteredList = UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(filteredList);
		smForm.setAssociatedJuvenilesListOrig(filteredList);
		smForm.setAssociatedJuvenilesList(filteredList);

		compResp = null;
		aReportRespEvt = null;
		

/** end pdf coding */ 
		return aMapping.findForward(UIConstants.PRINT_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward printMergerBFO(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	
	{
	 	SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		/** selections are disabled in jsp, struts does not return selectedFromId and selectedToId value */
		smForm.setSelectedFromId(smForm.getSelFromId());
		smForm.setSelectedToId(smForm.getSelToId());
		
		UISuspiciousMemberDetailsHelper.loadMemberDetails(smForm.getSelectedFromId(), smForm);
		smForm.setMemberNumber(smForm.getSelectedFromId());
		
		int len = smForm.getMergeMembersList().size();
		for (int x=0; x< len; x++ )
		{
			FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getMergeMembersList().get(x);
			if (smForm.getSelToId().equals(fmre.getMemberNum()) )
			{
			    smForm.setMemToName(fmre.getFirstName()+ " " + fmre.getLastName());
  			    break;
			}
		}
		
		
		aRequest.getSession().setAttribute("smForm", smForm);
		// generate report
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		byte[] pdf = pdfManager.createPDFByteReport(aRequest, aResponse, PDFReport.SUSPICIOUS_MEMBER_MERGE_FORM, smForm );
		
		SendEmailEvent sendEmailEvent = new SendEmailEvent();		
	    	sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");        		        	
	    	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");               		        	 
	        sendEmailEvent.setSubject("Family Member Details for merge " + smForm.getMemberFirstName() + " " + smForm.getMemberLastName() + " " + smForm.getSelectedFromId() + " to " + smForm.getMemToName() + " " + smForm.getSelectedToId());
	        sendEmailEvent.setMessage("PFA Family Member merge document.");
	        	
	        EmailAttachmentBean attachment = new EmailAttachmentBean();
		attachment.setContent(pdf);
		attachment.setName( PDFReport.SUSPICIOUS_MEMBER_MERGE_FORM.getReportName() + "from member " + smForm.getSelectedFromId() + " to member "  + smForm.getSelectedToId() + ".pdf" );
		attachment.setContentType( "application/pdf" );    
	    	
	    	sendEmailEvent.setAttachment(attachment);
	    	
	    	CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
	    	MessageUtil.processReturnException( res ) ;
		
		
		List filteredList = UISuspiciousMemberDetailsHelper.filterJuvenilesByMemberNumber(smForm.getSelectedFromId(), smForm.getSelectedToId(), smForm.getAllAssociatedJuvenilesList());
		filteredList = UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(filteredList);
		smForm.setAssociatedJuvenilesListOrig(filteredList);
		smForm.setAssociatedJuvenilesList(filteredList);
		
		return null;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		int len = smForm.getMergeMembersList().size();
		List matchList = new ArrayList();
		for (int x=0; x< len; x++ )
		{
			FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getMergeMembersList().get(x);
			if (smForm.getSelectedToId().equals(fmre.getMemberNum() ) )
			{
				matchList.add(fmre);
				break;
			}
		}
		smForm.setMergeMemberToList(matchList);
		
		List filteredList = UISuspiciousMemberDetailsHelper.filterJuvenilesByMemberNumber(smForm.getSelectedFromId(), smForm.getSelectedToId(), smForm.getAllAssociatedJuvenilesList());
		filteredList = UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(filteredList);
		smForm.setAssociatedJuvenilesListOrig(filteredList);
		smForm.setAssociatedJuvenilesList(filteredList);
		matchList = null;
		smForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
//		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
//		smForm.clear();
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}