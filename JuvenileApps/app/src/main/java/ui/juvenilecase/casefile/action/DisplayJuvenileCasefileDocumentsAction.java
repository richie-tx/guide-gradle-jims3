package ui.juvenilecase.casefile.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetSchoolAdjudicationNotificationEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.casefile.GetCasefileDocumentsEvent;
import messaging.casefile.GetCasefileNonComplianceNoticesEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileNonComplianceControllerServiceNames;
import naming.PDConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.MAYSIInformationReportingBean;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.form.CasefileDocumentsForm;
import ui.juvenilecase.form.JuvenileCasefileForm;

public class DisplayJuvenileCasefileDocumentsAction extends JIMSBaseAction{

	public DisplayJuvenileCasefileDocumentsAction( )
	{
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CasefileDocumentsForm cdf = (CasefileDocumentsForm)aForm ;
		cdf.clearAll( ) ;
		JuvenileCasefileForm casefile = UIJuvenileCaseworkHelper.getHeaderForm( aRequest ) ;
		cdf.setJuvenileId( casefile.getJuvenileNum() ) ;
		cdf.setCasefileId( casefile.getSupervisionNum() );
		
		GetCasefileDocumentsEvent reqEvent = new GetCasefileDocumentsEvent();
		reqEvent.setCasefileId( casefile.getSupervisionNum() );
		reqEvent.setJuvenileId(casefile.getJuvenileNum());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);
        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();

		Collection reportHistory = MessageUtil.compositeToCollection(compResponse, CasefileDocumentsResponseEvent.class);
		ArrayList documents = new ArrayList<CasefileDocumentsResponseEvent>();
		for(Object obj:reportHistory){
			CasefileDocumentsResponseEvent response = (CasefileDocumentsResponseEvent)obj;
			documents.add(response);
			
		}
		
        GetSchoolAdjudicationNotificationEvent sanEvent = new GetSchoolAdjudicationNotificationEvent();
		sanEvent.setDocTypeCd("SAN");
		sanEvent.setJuvenileId(casefile.getJuvenileNum());
		
		IDispatch dispatchSAN = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchSAN.postEvent(sanEvent);
        CompositeResponse sanResponse = (CompositeResponse) dispatchSAN.getReply();
        
        Collection schoolAdjudication = MessageUtil.compositeToCollection( sanResponse, ServiceEventAttendanceResponseEvent.class );
		for( Object obj:schoolAdjudication ){
			ServiceEventAttendanceResponseEvent adjResponse = ( ServiceEventAttendanceResponseEvent )obj;
			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
			document.setEntryDate(adjResponse.getStartDate());
			document.setReportId(adjResponse.getServiceEventId());
			document.setReportType("SCHOOL ADJUDICATION NOTIFICATION");
			document.setReport(adjResponse.getDocument());			
			documents.add( document );
		}
		
		
		//Facility Parent Orientation 12253.
		GetSchoolAdjudicationNotificationEvent fpoEvent = new GetSchoolAdjudicationNotificationEvent();
		fpoEvent.setDocTypeCd("FPO");
		fpoEvent.setJuvenileId(casefile.getJuvenileNum());
		dispatch.postEvent(fpoEvent);
		 CompositeResponse fpoResponse = (CompositeResponse) dispatch.getReply();    
	    Collection facilityParentOrientation = MessageUtil.compositeToCollection( fpoResponse, ServiceEventAttendanceResponseEvent.class );
		for( Object obj:facilityParentOrientation ){
			ServiceEventAttendanceResponseEvent adjResponse = ( ServiceEventAttendanceResponseEvent )obj;
			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
			document.setEntryDate(adjResponse.getStartDate());
			document.setReportId(adjResponse.getServiceEventId());
			document.setReportType(PDConstants.REPORT_TYPE_FACILITY_PARENT_ORIENTATION_LETTER);
			document.setReport(adjResponse.getDocument());			
			documents.add( document );
		}
		
		//Court Appointment Letter US 11109.
		GetSchoolAdjudicationNotificationEvent aplEvent = new GetSchoolAdjudicationNotificationEvent();
		aplEvent.setDocTypeCd("APL");
		aplEvent.setJuvenileId(casefile.getJuvenileNum());
		dispatch.postEvent(aplEvent);
		CompositeResponse aplResponse = (CompositeResponse) dispatch.getReply();    
	    Collection apptLetters = MessageUtil.compositeToCollection( aplResponse, ServiceEventAttendanceResponseEvent.class );
		for( Object obj:apptLetters ){
			ServiceEventAttendanceResponseEvent apletterResponse = ( ServiceEventAttendanceResponseEvent )obj;
			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
			document.setEntryDate(apletterResponse.getStartDate());
			document.setReportId(apletterResponse.getServiceEventId());
			document.setReportType(PDConstants.REPORT_TYPE_COURT_APPOINTMENT_LETTER);
			document.setReport(apletterResponse.getDocument());			
			documents.add( document );
		}
				
		
		GetCasefileNonComplianceNoticesEvent event = (GetCasefileNonComplianceNoticesEvent) EventFactory.getInstance(JuvenileCasefileNonComplianceControllerServiceNames.GETCASEFILENONCOMPLIANCENOTICES);
        event.setCasefileId( casefile.getSupervisionNum());
        List vopdocs = MessageUtil.postRequestListFilter(event, CasefileDocumentsResponseEvent.class);
        for(int x=0; x<vopdocs.size(); x++){
			CasefileDocumentsResponseEvent response = (CasefileDocumentsResponseEvent) vopdocs.get(x);
			documents.add(response);
        }
        
        //add code to retrieve Maysi assessment records
        Collection assessments = UIJuvenileCaseworkHelper.fetchPreviousMAYSIAssessments(casefile.getJuvenileNum());
        if(assessments != null) {
        	for( Object obj:assessments ){
    			MAYSISearchResultResponseEvent msrResponse = ( MAYSISearchResultResponseEvent )obj;
    			CasefileDocumentsResponseEvent document = new CasefileDocumentsResponseEvent();
    			document.setEntryDate(msrResponse.getAssessDate() );
    			document.setReportId( msrResponse.getMaysiFullAssessId() );
    			document.setReportType(PDConstants.REPORT_TYPE_MAYSI_ASSESSMENT);
    			document.setReport(UIConstants.EMPTY_STRING);			
    			documents.add( document );
    		}
        }
		
        cdf.setDocuments(documents);
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward returnToCasefile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{

		return aMapping.findForward("returnToCasefile");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewReportDetails(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		CasefileDocumentsForm cdf = (CasefileDocumentsForm)aForm ;
		String reportId = aRequest.getParameter("reportId");
		String reportType = aRequest.getParameter("reportType");
		ArrayList reports = cdf.getDocuments();

		if(StringUtils.isNotEmpty(reportId) && StringUtils.isNotEmpty(reportType) && reports != null && !reports.isEmpty())
		{
			if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_REQUEST_APPOINTED_ATTORNEY, reportType) || 
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_PARENTAL_WRITTEN_STMT, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_RIGHTS_OF_PARENTS_WORKSHEET, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_SCHOOL_ADJUDICATION_NOTIFICATION, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_COURT_APPOINTMENT_LETTER, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_FACILITY_PARENT_ORIENTATION_LETTER, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_SOCIAL_HISTORY, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_COURT_REPORT_INFO_SUMMARY, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_PCS, reportType) ||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_REPORT_TO_REFEREE, reportType)||
				StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_EXH, reportType)){
				viewInterviewRelatedReports(reportId,reports,aRequest,aResponse);
			}else if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_COMMUNITY_EXIT_PLAN, reportType) ||
					 StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_COMMON_APP_REPORT, reportType) ||    // ER changes 11069 change court exit plan to common app report
					 StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_RESIDENTAIL_EXIT_PLAN, reportType)){
				viewExitReports(reportId,reports,aRequest,aResponse);
			}else if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_CASEPLAN, reportType)){
				viewCasePlanReports(reportId,reports,aRequest,aResponse);
			}else if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_CASEPLAN_JPO_REVIEW, reportType)){
				viewJpoCaseplanReviewReports(reportId,reports,aRequest,aResponse);
			}else if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_NON_COMPLIANCE_NOTICE, reportType)){
				viewNonComplianceNotice(reportId,reports,aRequest,aResponse);
			}else if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_MAYSI_ASSESSMENT, reportType)){
				viewMAYSIAssessmentDocument(reportId,reports,aRequest,aResponse);
			}else if(StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_JOURNAL_CASE_REVIEW, reportType) || StringUtils.equalsIgnoreCase(PDConstants.REPORT_TYPE_FACILITY, reportType)){
				viewJournalCaseReviewReport(reportId,reports,aRequest,aResponse);
			}	
			
		}

		return null;
	}
	
	private void viewInterviewRelatedReports(String reportId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(reportId))
			{
				if(resp.getReport()!=null){
					String fileName = resp.getReportType().replace(' ', '_');
					byte[] content = (byte[])resp.getReport();
					try{
						aResponse.setContentType("application/x-file-download");
						aResponse.setHeader("Content-disposition", "attachment; filename=" + fileName.substring(fileName.lastIndexOf("/")+1) + ".pdf");
						aResponse.setHeader("Cache-Control", "max-age=" + 1200);
						aResponse.setContentLength(content.length);
						aResponse.resetBuffer();
						OutputStream os = aResponse.getOutputStream();
						os.write(content, 0, content.length);
						os.flush();
						os.close();

					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void viewExitReports(String selectedExitPlanId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(selectedExitPlanId))
			{
				if(resp.getReport()!=null){
			    	//Append document type + create date to be the file name, for easier identification
			    	StringBuffer documentName = new StringBuffer();
			    	documentName.append(resp.getReportType());
			    	documentName.append("_");    	
			    	String documentCreateDate = DateUtil.dateToString(resp.getEntryDate(), UIConstants.DATE_FMT_1);
			    	documentName.append(documentCreateDate.replaceAll("/", ""));
			    	
			    	try {
						setPrintContentResp(aResponse, (byte[])resp.getReport(), documentName.toString(), UIConstants.PRINT_AS_PDF_DOC);
					}
					catch(Exception e) {
						sendToErrorPage(aRequest, "");
					}
				}
			}
		}

	}
	
	private void viewCasePlanReports(String caseplanId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(caseplanId))
			{
				if(resp.getReport()!=null){
					String fileName="PreviousCaseplan_" + caseplanId;
					try{
						setPrintContentResp(aResponse,(byte[])resp.getReport(),fileName,UIConstants.PRINT_AS_PDF_DOC);
					}
					catch(Exception e){
						sendToErrorPage(aRequest, "");
					}
				}
			}
		}
	}
	
	private void viewJpoCaseplanReviewReports(String reviewId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(reviewId))
			{
				if (resp.getReport() != null)
				{
					String fileName = "JPOReview_" + reviewId;
					try
					{
						setPrintContentResp(aResponse, (byte[]) resp.getReport(), fileName,
								UIConstants.PRINT_AS_PDF_DOC);
					}
					catch (Exception e)
					{
						sendToErrorPage(aRequest, "");
					}
				}
			}
		}
    }

	private void viewNonComplianceNotice(String reviewId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(reviewId))
			{
				if (resp.getReport() != null)
				{
					StringBuffer documentName = new StringBuffer();
			    	documentName.append(resp.getReportType());
			    	documentName.append("_");    	
			    	String documentEntryDate = DateUtil.dateToString(resp.getEntryDate(), UIConstants.DATE_FMT_1);
			    	documentName.append(documentEntryDate.replaceAll("/", ""));
			    	
			    	try {
						setPrintContentResp(aResponse, (byte[])resp.getReport(), documentName.toString(), UIConstants.PRINT_AS_PDF_DOC);
					}
					catch(Exception e) {
						sendToErrorPage(aRequest, "");
					}
					break;
				}
			}
		}
	}
	// 09-20-2013 - as of this date, no document is being saved to print.  Able to use value from assessment record to
	//              load same values used for printing on Create Mental Health Assessment Confirmation page.
	private void viewMAYSIAssessmentDocument(String reviewId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(reviewId) && PDConstants.REPORT_TYPE_MAYSI_ASSESSMENT.equals(resp.getReportType() ) )
			{
				String sequenceNum = resp.getReportId();
				int indexOfFirstDash = -1 ;
				int indexOfSecondDash = -1 ;

				for( int i = 0; i < sequenceNum.length(); i++ )
				{
					if( sequenceNum.charAt(i) == '-' )
					{
						if( indexOfFirstDash == -1 )
						{
							indexOfFirstDash = i;
						}
						if( indexOfFirstDash != -1 )
						{
							indexOfSecondDash = i;
						}
					}
				}
					
					
				String assessmentId = sequenceNum.substring(0, indexOfFirstDash);
				String subAssessId = sequenceNum.substring((indexOfFirstDash + 1), (indexOfSecondDash));
				String maysiDetailId = sequenceNum.substring((indexOfSecondDash + 1), sequenceNum.length());

				if( subAssessId.equals("0") )
				{
					subAssessId = "";
				}

				if( maysiDetailId.equals("0") )
				{
					maysiDetailId = "";
				}
				
				MAYSIDetailsResponseEvent detail = UIJuvenileCaseworkHelper.fetchMAYSIDetails(assessmentId, subAssessId, maysiDetailId);
				if (detail != null && !"".equals(detail))
				{
				
				    MAYSIInformationReportingBean reportInfo = new MAYSIInformationReportingBean();
			        reportInfo.setJuvenileNum(detail.getJuvenileNum());
			        reportInfo.setPlacementType(detail.getFacilityType());
			        reportInfo.setPreviousMAYSI(detail.isHasPreviousMAYSI() ? "YES" : "NO");
			        reportInfo.setLength(detail.getLengthOfStay());
			        reportInfo.setLocation(detail.getLocationUnit());
			        reportInfo.setReferralNum(String.valueOf(detail.getReferralNumber()));
			        reportInfo.setGender(detail.getSex());
			        reportInfo.setRace(detail.getRace());
			        reportInfo.setCurrentAge(detail.getTestAge());
			        CompositeResponse myResp = this.sendPrintRequest("REPORTING::MAYSI_REQUEST_INFO", reportInfo, null);
			        try
			        {
			            this.setPrintContentResp(aResponse, myResp, "maysiInfo.pdf", UIConstants.PRINT_AS_PDF_DOC);
			        }
			        catch (Exception e)
			        {
			            sendToErrorPage(aRequest, "error.generic",
			                    "A problem with generating the MAYSI print document has been encountered");
			        }
				} 
				break;
			}
		}
	}
	
	/**
	 * view the individual historical journal case review report
	 * @param reviewId
	 * @param reports
	 * @param aRequest
	 * @param aResponse
	 */
	private void viewJournalCaseReviewReport(String reviewId,ArrayList reports,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		Iterator iter = reports.iterator();
		while (iter.hasNext())
		{
			CasefileDocumentsResponseEvent resp = (CasefileDocumentsResponseEvent)iter.next();
			if (resp.getReportId().equals(reviewId))
			{
				if (resp.getReport() != null)
				{
					StringBuffer documentName = new StringBuffer();
			    	documentName.append(resp.getReportType());
			    	documentName.append("_");    	
			    	String documentEntryDate = DateUtil.dateToString(resp.getEntryDate(), UIConstants.DATE_FMT_1);
			    	documentName.append(documentEntryDate.replaceAll("/", ""));
			    	
			    	try {
						setPrintContentResp(aResponse, (byte[])resp.getReport(), documentName.toString(), UIConstants.PRINT_AS_PDF_DOC);
					}
					catch(Exception e) {
						sendToErrorPage(aRequest, "");
					}
					break;
				}
			}
		}
	}

	@Override
	protected void addButtonMapping(Map keyMap) {
		keyMap.put( "button.link", "link" ) ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.returnToCasefile", "returnToCasefile" ) ;
		keyMap.put( "prompt.other", "viewReportDetails" ) ;
	}

}
