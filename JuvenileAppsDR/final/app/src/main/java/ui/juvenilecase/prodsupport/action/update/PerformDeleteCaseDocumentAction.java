package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.CasefileDocResponse;
import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCasefileNonComplianceNoticeEvent;
import messaging.productionsupport.DeleteProductionSupportCommonAppDocumentEvent;
import messaging.productionsupport.DeleteProductionSupportInterviewReportDocumentEvent;
import messaging.productionsupport.DeleteProductionSupportJournalCaseReviewDocumentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class PerformDeleteCaseDocumentAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteCaseDocumentAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		ActionErrors errors = new ActionErrors();

		String documentId = regform.getDocumentId();
		String docTable = regform.getDocumentType();

		if (documentId == null || documentId.equals("") || docTable == null
				|| docTable.equals("")) {
			regform.setMsg("PerformDeleteCaseDocumentAction.java - invalid DocumentID or DocTable selected.");
			return (mapping.findForward("error"));
		}
			
		if(regform.getCasefiledocs() != null && regform.getCasefiledocs().size() > 0 && performDelete(regform.getCasefiledocs(), documentId, docTable, errors) ) {
				regform.setMsg("");
				return mapping.findForward("success");
		}else{
			regform.setMsg("Error - The document was not deleted. PerformDeleteCaseDocumentAction.java: Table " +  docTable + " DocId: " + documentId);
			return mapping.findForward("error");
		}
	}

	/**
	 * delete record and mark success if no errors
	 * @param documentList
	 * @param documentId
	 * @param table
	 * @param errors
	 * @return
	 */
	private boolean performDelete(ArrayList<CasefileDocResponse > documentList,
			String documentId, String table, ActionErrors errors) {
		boolean success = true;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		Iterator iter = documentList.iterator();
		while (iter.hasNext()) {
			CasefileDocResponse  documentRecord = (CasefileDocResponse ) iter.next();
			if (documentId.equals(documentRecord.getDocumentId())) {
				/** Log the file to be deleted **/
				log.info("BEGIN DELETE - table: " + table + " DOC ID: " + documentId + " LogonID " + SecurityUIHelper.getLogonId());
				if(documentRecord.getTableName()!= null && documentRecord.getTableName().equalsIgnoreCase("JCCASENONCOMNOTE") ){
					
					/**
					 * Delete Non-Compliance Documents (JCCASENONCOMNOTE)
					 */				
					DeleteProductionSupportCasefileNonComplianceNoticeEvent nonComplianceNoticeEvent = new DeleteProductionSupportCasefileNonComplianceNoticeEvent();
					nonComplianceNoticeEvent.setDocumentId(documentId);
					dispatch.postEvent(nonComplianceNoticeEvent);
					CompositeResponse nonComplianceNoticeResponse = (CompositeResponse) dispatch.getReply();
					ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(nonComplianceNoticeResponse, ErrorResponseEvent.class);
					if(error != null){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
						success = false;
					}
				}
				
				if(documentRecord.getTableName()!= null && documentRecord.getTableName().equalsIgnoreCase("JCCOMMONAPPDOC") ){
					
					/**
					 * Delete CommonApp Documents (JCCOMMONAPPDOC)
					 */
					DeleteProductionSupportCommonAppDocumentEvent  commonAppDocEvent = new DeleteProductionSupportCommonAppDocumentEvent();
					commonAppDocEvent.setDocumentId(documentId);
					dispatch.postEvent(commonAppDocEvent);
					CompositeResponse commonAppDocResponse = (CompositeResponse) dispatch.getReply();
					ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(commonAppDocResponse, ErrorResponseEvent.class);
					if(error != null){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
						success = false;
					}
				}
				
				if(documentRecord.getTableName()!= null && documentRecord.getTableName().equalsIgnoreCase("JCIVIEWDOC") ){
					
					/**
					 * Delete Interview Documents (JCIVIEWDOC)
					 */
					DeleteProductionSupportInterviewReportDocumentEvent  interviewReportDocEvent = new DeleteProductionSupportInterviewReportDocumentEvent();
					interviewReportDocEvent.setDocumentId(documentId);
					dispatch.postEvent(interviewReportDocEvent);
					CompositeResponse interviewReportDocResponse = (CompositeResponse) dispatch.getReply();
					ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(interviewReportDocResponse, ErrorResponseEvent.class);					
					if(error != null){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
						success = false;
					}
				}
				
				if(documentRecord.getTableName()!= null && documentRecord.getTableName().equalsIgnoreCase("JCJOURNALRPRTDOC") ){
					
					/**
					 * Delete Journal case Review Documents (JCJOURNALRPRTDOC)
					 */
					DeleteProductionSupportJournalCaseReviewDocumentEvent  journalCaseReviewDocEvent = new DeleteProductionSupportJournalCaseReviewDocumentEvent();
					journalCaseReviewDocEvent.setDocumentId(documentId);
					dispatch.postEvent(journalCaseReviewDocEvent);
					CompositeResponse journalCaseReviewDocResponse = (CompositeResponse) dispatch.getReply();
					ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(journalCaseReviewDocResponse, ErrorResponseEvent.class);
					if(error != null){
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
						success = false;
					}
				}

				log.info("END - Successfully deleted table: " + table + " DOC ID: " + documentId + " LOGONID: " + SecurityUIHelper.getLogonId());
			}
		}

		return success;
	}
	
}
