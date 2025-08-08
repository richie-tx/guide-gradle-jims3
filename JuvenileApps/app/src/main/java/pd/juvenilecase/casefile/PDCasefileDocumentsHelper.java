package pd.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.GetCommonAppDocsEvent;
import messaging.casefile.GetJournalDocsEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.caseplan.GetJPOReviewReportsEvent;
import messaging.caseplan.GetPreviousCaseplansEvent;
import naming.PDConstants;
import pd.codetable.PDCodeHelper;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.JPOReviewDocMetadata;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import ui.common.CodeHelper;

public class PDCasefileDocumentsHelper {

	/**
	* @param String casefileId
	* Interview-related Forms � Parental Written request, Attorney Appointment, and 
	                            the Social History Reports (Universal and Generic Court Report)
	*/
	
	public static ArrayList getInterviewRelatedReports(String casefileId)
	{
		ArrayList reports = new ArrayList();
		Iterator iter = null;
		iter = InterviewDocument.findAllForCasefile( casefileId );
		
		while ( iter.hasNext() )
		{
			InterviewDocument doc = (InterviewDocument)iter.next();
			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
			document.setEntryDate(doc.getCreationDate());
			document.setReportType(doc.getDocumentTypeCode().getDescription());
			document.setReportId(doc.getOID().toString());
			document.setReport(doc.getDocument());
			reports.add(document);
		}
		
		return reports;
	}

	/**
	* @param String casefileId
	* CommonApp exit plans list
	*/
	public static ArrayList getCommonAppReports(String casefileId)
	{
		ArrayList exitPlanList = new ArrayList();
		GetCommonAppDocsEvent evt = new GetCommonAppDocsEvent();
		evt.setCasefileId(casefileId);
		Iterator commonAppDocs = CommonAppDocMetadata.findAll(evt);
   		if(commonAppDocs!=null){
	   		while(commonAppDocs.hasNext()) {
	   			CommonAppDocMetadata report = (CommonAppDocMetadata)commonAppDocs.next();
	   			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
	   			document.setEntryDate(report.getCreationDate());
	   			//String reportType = PDCodeHelper.getCodeDescription("DOCUMENT_TYPE", report.getDocTypeCd());
	   			String reportType = CodeHelper.getCodeDescription("DOCUMENT_TYPE", report.getDocTypeCd());
	   			document.setReportType(reportType);
	   			document.setReportId(report.getOID());
	   			document.setReport(report.getDocument());
	   			exitPlanList.add(document);
	   		}
   		}
		
		return exitPlanList;
	}
	
	/**
	* @param String casefileId
	* Facility reports
	*/
	public static ArrayList getFacilityReports(String casefileId)
	{
		ArrayList reports = new ArrayList();
		Iterator iter = null;
		iter = FacilityDocMetadata.findAllForCasefile( casefileId );
		
		while ( iter.hasNext() )
		{
			FacilityDocMetadata doc = (FacilityDocMetadata)iter.next();
			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
			document.setEntryDate(doc.getCreationDate());
			String reportType = PDCodeHelper.getCodeDescription("DOCUMENT_TYPE", doc.getDocTypeCd());
			document.setReportType(reportType);
			document.setReportId(doc.getOID().toString());
			document.setReport(doc.getDocument());
			reports.add(document);
		}
		
		return reports;
	}

	/**
	* @param String casefileId
	* Caseplan list
	*/
	public static ArrayList getCaseplanReports(String casefileId)
	{
		ArrayList prevCaseplans = new ArrayList();
		GetPreviousCaseplansEvent evt = new GetPreviousCaseplansEvent();
		evt.setCasefileId(casefileId);
   		Iterator cps = CasePlan.findAll(evt);
   		if(cps!=null){
	   		while(cps.hasNext()) {
	   			CasePlan report = (CasePlan)cps.next();
	   			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
				document.setEntryDate(report.getCreateTimestamp());
				document.setReportType(PDConstants.REPORT_TYPE_CASEPLAN);
				document.setReportId(report.getCaseplanID());
				document.setReport(report.getReport());
				prevCaseplans.add(document);
	   		}
   		}
		Collections.sort((List)prevCaseplans);
		
		return prevCaseplans;
	}

	/**
	* @param String casefileId
	* Caseplan JPO review reports list
	*/
	public static ArrayList getCaseplanJPOReviewReports(String casefileId)
	{
		ArrayList reviews = new ArrayList();
		GetJPOReviewReportsEvent reportsEvent = new GetJPOReviewReportsEvent();
        reportsEvent.setCasefileId(casefileId);        
		Iterator jpoReviews = JPOReviewDocMetadata.findAll(reportsEvent);
   		if(jpoReviews!=null){
	   		while(jpoReviews.hasNext()) {
	   			JPOReviewDocMetadata report = (JPOReviewDocMetadata)jpoReviews.next();
				CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
				document.setEntryDate(report.getReviewDate());
				document.setReportType(PDConstants.REPORT_TYPE_CASEPLAN_JPO_REVIEW);
				document.setReportId(report.getOID());
				document.setReport(report.getReport());
				reviews.add(document);
	   		}
   		}
   		return reviews; 
	}
   		
	/**
	* @param String casefileId
	* Journal reports list
	*/
	public static ArrayList getJournalReports(String casefileId)
	{
		ArrayList journalReportsList = new ArrayList();
		GetJournalDocsEvent journalReportsEvent = new GetJournalDocsEvent();
		journalReportsEvent.setCasefileId(casefileId);        
		Iterator journalReports = JournalDocMetadata.findAll(journalReportsEvent);
   		if(journalReports!=null){
	   		while(journalReports.hasNext()) {
	   			JournalDocMetadata report = (JournalDocMetadata)journalReports.next();
				CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
				document.setEntryDate(report.getCreationDate());
				document.setReportType(PDConstants.REPORT_TYPE_JOURNAL_CASE_REVIEW);
				document.setReportId(report.getOID());
				document.setDocumentTypeCd(report.getDocTypeCd());
				document.setReport(report.getDocument());
				if(report.getCreateUserID() != null){
					document.setCreateUserID(report.getCreateUserID());
				}
				if(report.getCreateTimestamp() != null){
					document.setCreateDate(new Date(report.getCreateTimestamp().getTime()));
				}
				if(report.getUpdateUserID() != null){
					document.setUpdateUser(report.getUpdateUserID());
				}
				if(report.getUpdateTimestamp() != null){
					document.setUpdateDate(new Date(report.getUpdateTimestamp().getTime()));
				}
				if(report.getCreateJIMS2UserID() != null){
					document.setCreateJIMS2UserID(report.getCreateJIMS2UserID());
				}
				if(report.getUpdateJIMS2UserID() != null){
					document.setUpdateJIMS2UserID(report.getUpdateJIMS2UserID());
				}
				journalReportsList.add(document);
	   		}
   		}	
		return journalReportsList;
	}
}
