package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetJournalDocsEvent;
import messaging.casefile.reply.CasefileDocResponse;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeProbationViolationResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
import messaging.interviewinfo.GetInterviewReportsEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceDocumentEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceNoticeEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceNoticeProbationViolationEvent;
import messaging.productionsupport.GetProductionSupportCasefileNonComplianceNoticeSanctionEvent;
import messaging.productionsupport.GetProductionSupportCommonAppDocumentEvent;
import messaging.productionsupport.reply.ProductionSupportNonComplianceNoticeSanctionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class DeleteCaseDocumentQueryAction extends Action {

	private Logger log = Logger.getLogger("DeleteCaseDocumentQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;		

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setCasefileId("");
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String editFlag = request.getParameter("edit");
		String documentId = regform.getDocumentId();
		String documentType = regform.getDocumentType();
		int searchDocCount = 0;

		if (editFlag != null && editFlag.equalsIgnoreCase("Y")) {
		    
		        if(regform.getCasefiledocs() != null && regform.getCasefiledocs().size() > 0)
		        {
		            searchDocCount = regform.getCasefiledocs().size();
		        }

			ArrayList document = getSingleRow(regform, documentId, documentType);

			if (document == null || document.size()<1)
			{
				regform.setMsg("Error retrieving document.");
				return mapping.findForward("error");
			}
			else
			{
				//regform.clearAllResults();				
				regform.setCasefiledocs(document);
				regform.setCasefiledocCount(document.size());
                regform.setSearchCaseDocumentCount(searchDocCount);
				if (documentType.equals("JCCASENONCOMNOTE"))
				{
					ArrayList children = getNonCompChildren(regform.getCasenoncomnoteId());
					regform.setCasedocchildren(children);
					regform.setCasedocchildrenCount(children.size());
				}	
				
				return mapping.findForward("continue");
			}

		}

		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals(" ")) {
			regform.setMsg("CasefileId was null.");
			return mapping.findForward("error");
		}

		regform.clearAllResults();

		log.info("Case Document Query CASEFILE_ID: " + casefileId + " LogonId: " +
				SecurityUIHelper.getLogonId());

		// Search and populate the casefile records
		regform = retrieveCasefileRecords(regform, casefileId);

		if (regform.getCasefiledocCount() == 0) {
			regform.setMsg("No casefile document records returned for casefileID "
					+ casefileId);
			return mapping.findForward("error");
		}

		regform.setMsg("");
		return mapping.findForward("success");

	}

	/**
	 * retrieve the main 3 types of parent casefile document types
	 * @param regform
	 * @param casefileId
	 * @return
	 */
	public static ProdSupportForm retrieveCasefileRecords(
			ProdSupportForm regform, String casefileId) {
		ArrayList allDocuments = new ArrayList();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		/**
		 * Search for Non-Compliance Documents
		 */
		GetProductionSupportCasefileNonComplianceNoticeEvent getCompliantNoticeDocs = 
			(GetProductionSupportCasefileNonComplianceNoticeEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILENONCOMPLIANCENOTICE);
		getCompliantNoticeDocs.setCasefileId(casefileId);
		dispatch.postEvent(getCompliantNoticeDocs);
		CompositeResponse compliantNoticeDocsResponse = (CompositeResponse) dispatch.getReply();
		ArrayList nonCompDocsList = (ArrayList) MessageUtil.compositeToCollection(compliantNoticeDocsResponse, CasefileNonComplianceNoticeResponseEvent.class);
		if (nonCompDocsList != null) {
			Iterator iter = nonCompDocsList.iterator();
			while (iter.hasNext()) {
				CasefileNonComplianceNoticeResponseEvent nonComplanceNotice = (CasefileNonComplianceNoticeResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();
				casefileDoc.setTableName("JCCASENONCOMNOTE");
				casefileDoc.setDocumentId(nonComplanceNotice.getDocumentId());
				casefileDoc.setCaseNonConNoteId(nonComplanceNotice.getCasefileNonComplianceNoticeId());
				casefileDoc.setCreateDate(nonComplanceNotice.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(nonComplanceNotice.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(nonComplanceNotice.getCreateUserID());
				casefileDoc.setUpdateDate(nonComplanceNotice.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(nonComplanceNotice.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(nonComplanceNotice.getUpdateUser());
				
				allDocuments.add(casefileDoc);
			}
		}

		/**
		 * Search for CommonApp Documents
		 */
		GetProductionSupportCommonAppDocumentEvent getCommonAppDocumentEvent = (GetProductionSupportCommonAppDocumentEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOMMONAPPDOCUMENT);
		getCommonAppDocumentEvent.setCasefileId(casefileId);
		dispatch.postEvent(getCommonAppDocumentEvent);
		CompositeResponse commonAppDocumentEventResponse = (CompositeResponse) dispatch.getReply();
		ArrayList commonAppDocumentList = (ArrayList) MessageUtil.compositeToCollection(commonAppDocumentEventResponse, CommonAppDocResponseEvent.class);	
		if (commonAppDocumentList != null) {
			Iterator iter = commonAppDocumentList.iterator();
			while (iter.hasNext()) {
				CommonAppDocResponseEvent commonAppResponse = (CommonAppDocResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();
				casefileDoc.setTableName("JCCOMMONAPPDOC");
				casefileDoc.setDocTypeCd(commonAppResponse.getDocTypeCd());
				casefileDoc.setDocumentId(commonAppResponse.getCommonAppDocId());
				casefileDoc.setCreateDate(commonAppResponse.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(commonAppResponse.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(commonAppResponse.getCreateUserID());
				casefileDoc.setUpdateDate(commonAppResponse.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(commonAppResponse.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(commonAppResponse.getUpdateUser());
				allDocuments.add(casefileDoc);
			}
		}

		/**
		 * Search for Interview Documents
		 */
		GetInterviewReportsEvent getInterviewDocs = (GetInterviewReportsEvent)
			EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETINTERVIEWREPORTS);
		getInterviewDocs.setCasefileId(casefileId);
		dispatch.postEvent(getInterviewDocs);
		CompositeResponse interviewDocsResponse = (CompositeResponse) dispatch.getReply();
		ArrayList interviewsDocsList = (ArrayList) MessageUtil.compositeToCollection(interviewDocsResponse, InterviewReportHeaderResponseEvent.class);
		if (interviewsDocsList != null) {
			Iterator iter = interviewsDocsList.iterator();

			while (iter.hasNext()) {
				InterviewReportHeaderResponseEvent interviewDoc = (InterviewReportHeaderResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();
				casefileDoc.setTableName("JCIVIEWDOC");
				casefileDoc.setDocumentId(interviewDoc.getReportId());
				casefileDoc.setDocTypeCd(interviewDoc.getReportType());
				casefileDoc.setCreateDate(interviewDoc.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(interviewDoc.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(interviewDoc.getCreateUserID());
				casefileDoc.setUpdateDate(interviewDoc.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(interviewDoc.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(interviewDoc.getUpdateUser());
				allDocuments.add(casefileDoc);
			}
		}
		
		/**
		 * Search for Journal case Review Documents
		 */
		GetJournalDocsEvent getJournalDocs = (GetJournalDocsEvent)
			EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETJOURNALDOCS);
		getJournalDocs.setCasefileId(casefileId);
		dispatch.postEvent(getJournalDocs);
		CompositeResponse journalDocsResponse = (CompositeResponse) dispatch.getReply();
		ArrayList journalDocsList = (ArrayList) MessageUtil.compositeToCollection(journalDocsResponse, CasefileDocumentsResponseEvent.class);
		if (journalDocsList != null) {
			Iterator iter = journalDocsList.iterator();

			while (iter.hasNext()) {
				CasefileDocumentsResponseEvent journalDoc = (CasefileDocumentsResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();
				casefileDoc.setTableName("JCJOURNALRPRTDOC");
				casefileDoc.setDocumentId(journalDoc.getReportId());
				casefileDoc.setDocTypeCd(journalDoc.getDocumentTypeCd());
				casefileDoc.setCreateDate(journalDoc.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(journalDoc.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(journalDoc.getCreateUserID());
				casefileDoc.setUpdateDate(journalDoc.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(journalDoc.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(journalDoc.getUpdateUser());
				allDocuments.add(casefileDoc);
			}
		}
		
		Collections.sort(allDocuments, CasefileDocResponse.CasefileDocResponseDocumentIdComparator);
		regform.setCasefiledocs(allDocuments);
		regform.setCasefiledocCount(allDocuments.size());

		if (allDocuments.size() < 1) {
			regform.setCasefiledocCount(0);
			regform.setCasefiledocs(null);
		}

		return regform;
	}

	/**
	 * 
	 * get detailed record entered from screen (or chosen)
	 * @param regform
	 * @param oid
	 * @param documentType
	 * @return
	 */
	private ArrayList getSingleRow(ProdSupportForm regform, String oid,
			String documentType) {
		ArrayList row = new ArrayList();

		ArrayList documents = regform.getCasefiledocs();

		if (documents != null) {
			Iterator iter = documents.iterator();

			while (iter.hasNext()) {
				CasefileDocResponse docResponse = (CasefileDocResponse) iter.next();
				if (docResponse.getTableName().equals(documentType)&& docResponse.getDocumentId().equals(oid)) 
				{
					row = new ArrayList<CasefileDocResponse>();
					row.add(docResponse);
					regform.setCasenoncomnoteId(docResponse.getCaseNonConNoteId());
					break;
				}
			}
		}

		return row;

	}
	
	/**
	 * determine child table records for JCCASENONCOMPDOC records
	 * @param noncomnoteId
	 * @return
	 */
	private ArrayList getNonCompChildren(String noncomnoteId)
	{
		ArrayList children = new ArrayList();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		/**
		 * Search JCCASENONCOMPDOC 
		 */		
		GetProductionSupportCasefileNonComplianceDocumentEvent getNonComplianceDocumentEvent = 
			(GetProductionSupportCasefileNonComplianceDocumentEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILENONCOMPLIANCEDOCUMENT);
		getNonComplianceDocumentEvent.setNoncomnoteId(noncomnoteId);
		dispatch.postEvent(getNonComplianceDocumentEvent);
		CompositeResponse getNonComplianceDocumentResponse = (CompositeResponse) dispatch.getReply();
		ArrayList getNonComplianceDocumentResponseList = (ArrayList) MessageUtil.compositeToCollection(getNonComplianceDocumentResponse, CasefileDocumentsResponseEvent.class);
		if (getNonComplianceDocumentResponseList != null) {
			Iterator iter = getNonComplianceDocumentResponseList.iterator();
			while (iter.hasNext()) {
				CasefileDocumentsResponseEvent nonComplianceDocumentResponse = (CasefileDocumentsResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();				
				casefileDoc.setTableName("JCCASENONCOMPDOC");
				casefileDoc.setDocumentId(nonComplianceDocumentResponse.getReportId());
				casefileDoc.setDocTypeCd(nonComplianceDocumentResponse.getDocumentTypeCd());
				casefileDoc.setCaseNonConNoteId(nonComplianceDocumentResponse.getCasefileNonComplianceNoticeId());
				casefileDoc.setCreateDate(nonComplianceDocumentResponse.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(nonComplianceDocumentResponse.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(nonComplianceDocumentResponse.getCreateUserID());
				casefileDoc.setUpdateDate(nonComplianceDocumentResponse.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(nonComplianceDocumentResponse.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(nonComplianceDocumentResponse.getUpdateUser());
				children.add(casefileDoc);
			}
		}
		
		/**
		 * Search JCCASENONCOMNSNC 
		 */
		GetProductionSupportCasefileNonComplianceNoticeSanctionEvent getNonComplianceSanctionEvent = 
			(GetProductionSupportCasefileNonComplianceNoticeSanctionEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILENONCOMPLIANCENOTICESANCTION);
		getNonComplianceSanctionEvent.setNoncomnoteId(noncomnoteId);
		dispatch.postEvent(getNonComplianceSanctionEvent);
		CompositeResponse getNonComplianceSanctionResponse = (CompositeResponse) dispatch.getReply();
		ArrayList getNonComplianceSanctionResponseList = (ArrayList) MessageUtil.compositeToCollection(getNonComplianceSanctionResponse, ProductionSupportNonComplianceNoticeSanctionResponseEvent.class);
		if (getNonComplianceSanctionResponseList != null) {
			Iterator iter = getNonComplianceSanctionResponseList.iterator();
			while (iter.hasNext()) {
				ProductionSupportNonComplianceNoticeSanctionResponseEvent sactionResponseEvent = (ProductionSupportNonComplianceNoticeSanctionResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();
				casefileDoc.setTableName("JCCASENONCOMNSNC");
				casefileDoc.setDocumentId(sactionResponseEvent.getReportId());
				casefileDoc.setCaseNonConNoteId(sactionResponseEvent.getCasefileNonComplianceNoticeId());
				casefileDoc.setCreateDate(sactionResponseEvent.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(sactionResponseEvent.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(sactionResponseEvent.getCreateUserID());
				casefileDoc.setUpdateDate(sactionResponseEvent.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(sactionResponseEvent.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(sactionResponseEvent.getUpdateUser());
				children.add(casefileDoc);
			}
		}
			
		/**
		 * Search JCCASENONCOMNPRV 
		 */
		GetProductionSupportCasefileNonComplianceNoticeProbationViolationEvent getNonComplianceProbationViolationEvent = 
			(GetProductionSupportCasefileNonComplianceNoticeProbationViolationEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILENONCOMPLIANCENOTICEPROBATIONVIOLATION);
		getNonComplianceProbationViolationEvent.setNoncomnoteId(noncomnoteId);
		dispatch.postEvent(getNonComplianceProbationViolationEvent);
		CompositeResponse getNonComplianceProbationViolationResponse = (CompositeResponse) dispatch.getReply();
		ArrayList getNonComplianceProbationViolationResponseList = (ArrayList) MessageUtil.compositeToCollection(getNonComplianceProbationViolationResponse, CasefileNonComplianceNoticeProbationViolationResponseEvent.class);
		
		if (getNonComplianceProbationViolationResponseList != null) {
			Iterator iter = getNonComplianceProbationViolationResponseList.iterator();
			while (iter.hasNext()) {			
				CasefileNonComplianceNoticeProbationViolationResponseEvent probationViolationResponseEvent = 
					(CasefileNonComplianceNoticeProbationViolationResponseEvent) iter.next();
				CasefileDocResponse casefileDoc = new CasefileDocResponse();
				casefileDoc.setTableName("JCCASENONCOMNPRV");
				casefileDoc.setDocumentId(probationViolationResponseEvent.getCasefileNonComplianceNoticeId());
				casefileDoc.setCaseNonConNoteId(probationViolationResponseEvent.getCasefileNonComplianceNoticeId());
				casefileDoc.setCreateDate(probationViolationResponseEvent.getCreateDate());
				casefileDoc.setCreateJIMS2UserID(probationViolationResponseEvent.getCreateJIMS2UserID());
				casefileDoc.setCreateUserID(probationViolationResponseEvent.getCreateUserID());
				casefileDoc.setUpdateDate(probationViolationResponseEvent.getUpdateDate());
				casefileDoc.setUpdateJIMS2UserID(probationViolationResponseEvent.getUpdateJIMS2UserID());
				casefileDoc.setUpdateUser(probationViolationResponseEvent.getUpdateUser());
				children.add(casefileDoc);
			}
		}
		
		return children;
	}
}
