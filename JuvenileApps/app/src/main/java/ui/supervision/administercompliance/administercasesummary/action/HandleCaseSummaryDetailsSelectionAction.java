//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\HandleCaseSummaryDetailsSelectionAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerassessments.GetAssessmentsSummaryEvent;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.administercasenotes.GetCasenoteByOIDEvent;
import messaging.administercasenotes.GetSuperviseeInSupervisionPeriodEvent;
import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.administercompliance.UpdateNCResponseStatusEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdcalendar.GetMonthlyCSCalendarEvent;
import messaging.cscdcalendar.reply.MonthlyCSCalendarResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.report.GenericPrintRequestEvent;
import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.AssessmentControllerServiceNames;
import naming.CSEventControllerServiceNames;
import naming.CaseloadControllerServiceNames;
import naming.CasenoteControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.CaseInfo;
import ui.supervision.Casenote;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercasenotes.CasenotesJournalBean;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.administercompliance.administercasehistory.UICaseHistoryHelper;
import ui.supervision.administercompliance.administercasehistory.form.CaseHistoryForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleCaseSummaryDetailsSelectionAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D400110
	 */
	public HandleCaseSummaryDetailsSelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.approve", "approveSummary");
		keyMap.put("button.returnForResponse", "changeRequest");
		keyMap.put("button.changeRequest", "changeRequest");		
		keyMap.put("button.file", "fileReport");
		keyMap.put("button.maintain", "maintainReport");
		keyMap.put("button.print", "printReport");	
		keyMap.put("button.tasks", "tasks");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.caseHistory", "caseHistory");
		keyMap.put("button.link","printCasenote");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward approveSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;	
		
		String status = "";
		String subject = "";
		String topic = "";

   	   	List reasonForTransferList = csForm.getCurrentReasonForTransferList();
   	   	if (reasonForTransferList != null && reasonForTransferList.size() > 0) {
	   	   	for (int x = 0; x < reasonForTransferList.size(); x++ ) {
	   	   		CodeResponseEvent reasonForTransfer = (CodeResponseEvent) reasonForTransferList.get(x);
	   	   		if (!reasonForTransfer.getCode().equals("RNTR")) {
	   	   			status = ViolationReportConstants.STATUS_MANAGER_APPROVED;
	   	   			subject = ViolationReportConstants.CSTASK_SUBJECT_CASESUMMARY_SUBMISSION_FILING;
	   	   			topic = ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_FILING_REQUIRED;
	   	   			break;
	   	   		} else {
	   	   			status = ViolationReportConstants.STATUS_COMPLETED;
	   	   		}
	   	   	}
   	   	}
					
		StringBuffer taskText = new StringBuffer();
		if (status.equals("MA")) {
			taskText.append(ViolationReportConstants.CSTASK_SUBJECT_CASESUMMARY_SUBMISSION_FILING);
			taskText.append(" ");
			if (csForm.getSuperviseeName() != null){
				taskText.append(csForm.getSuperviseeName());
			}
			taskText.append(", ");
			if (csForm.getSuperviseeId() != null){
				taskText.append(csForm.getSuperviseeId());
			}	
			taskText.append(", ");
			if (csForm.getCaseNum() != null){
				taskText.append(csForm.getCaseNum());
			}	
			taskText.append(", ");
			if (csForm.getCourtNum() != null){
				taskText.append(csForm.getCourtNum());
			}
		}
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		
		UICaseHistoryHelper chHelper = new UICaseHistoryHelper();
		
		Map officersMap = chHelper.buildOfficerHiearchyMap( caForm );
		csForm.setTaskToStaffId((String) officersMap.get("cloPosition"));
		
		UpdateNCResponseStatusEvent uEvent = UICaseSummaryHelper.prepareRequestEvent(csForm, subject, taskText.toString(), topic, status,false);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(uEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				
		ErrorResponseEvent er = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if(er != null){
			sendToErrorPage(aRequest, er.getMessage(), er.getUserId());
			csForm.setConfirmationMessage("");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		csForm.setConfirmationMessage("Case Summary successfully approved.");
		return aMapping.findForward(UIConstants.APPROVE_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward changeRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setConfirmationMessage("");
		csForm.setTaskSubject(ViolationReportConstants.CSTASK_SUBJECT_CASESUMMARY_UPDATES_REQUIRED);
		csForm.setTaskNextAction("");
		String forward = UIConstants.CHANGE_SUCCESS;
		
		StringBuffer taskText = new StringBuffer("Case Summary updates required for ");
		taskText.append(csForm.getSuperviseeName());
		taskText.append(", ");
		taskText.append(csForm.getSuperviseeId());
		taskText.append(", ");
		taskText.append(csForm.getCaseNum());
		taskText.append(", ");
		taskText.append(csForm.getCourtNum());
		
		csForm.setTaskText(taskText.toString());
		
		String creatorId = "";
		// RRY added this for the return logic
		CaseAssignmentForm caForm = (CaseAssignmentForm) 
									getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);
		
		UICaseHistoryHelper chHelper = new UICaseHistoryHelper();
		
		Map offList = chHelper.buildOfficerHiearchyMap( caForm );
		
		if( ViolationReportConstants.STATUS_MANAGER_APPROVED.equalsIgnoreCase( csForm.getStatusId() )){
			creatorId = (String) offList.get( "csoSupervisor" );
	    }else if(ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL.equalsIgnoreCase( csForm.getStatusId() )){
	    	creatorId = (String) offList.get( "csoPosition" );
	    }
		
		if ( creatorId == null ){
			
			forward = UIConstants.APPROVAL_FAILURE; 
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
															"Case does not have CLO/CSO assigned properly"));
			saveErrors( aRequest, errors );
			csForm.setConfirmationMessage("");
		}
		
		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
		gEvent.setStaffPositionId( creatorId );
		LightCSCDStaffResponseEvent userStaffPosition = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
		if(userStaffPosition != null){
			csForm.setTaskTo(userStaffPosition.getStaffPositionName());	
			csForm.setTaskToStaffId( userStaffPosition.getStaffPositionId() );
		}
		return aMapping.findForward( forward );

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward submitForApproval(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setConfirmationMessage("Case Summary successfully submitted for approval.");
// add code to set report status		
		ActionForward forward = aMapping.findForward(UIConstants.APPROVAL_SUCCESS);
		return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward fileReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
// retrieve court number from header form to use to set default who signed selection	
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);
		String courtNumber = null;
		if (caForm != null){
			courtNumber = caForm.getCourtNumber();
		}
		UICaseSummaryHelper.prepareToFileReport(csForm, courtNumber);
		return aMapping.findForward(UIConstants.FILE_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward maintainReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setConfirmationMessage("");
		csForm.setSecondaryAction("maintain");
		csForm.setAllowUpdate("Y");
		return aMapping.findForward(UIConstants.MAINTAIN_SUCCESS);
	}	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward caseHistory(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		aRequest.setAttribute("superviseeId", csForm.getSuperviseeId());
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		myHeaderForm.setSuperviseeId(csForm.getSuperviseeId());
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);

		CaseHistoryForm chForm = (CaseHistoryForm) getSessionForm(aMapping, aRequest, "caseHistoryForm", true);
		chForm.setAllowUpdates(false);
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		Set userFeatures = securityManager.getFeatures(); 
		if (userFeatures.contains("CSCD-CASE-SUM-CREATE") || userFeatures.contains("CSCD-VIOL-RPT-CREATE") ) {
			chForm.setAllowUpdates(true);
		}
		
		GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
        event.setSuperviseeId(csForm.getSuperviseeId());

        List cases = MessageUtil.postRequestListFilter(event, SuperviseeCaseOrderResponseEvent.class);
		if (cases == null || cases.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No cases found for this supervisee"));
			saveErrors(aRequest, errors);
		} else {
			// remove CDI value from case number and court type from case number for correct display value	
			chForm.setCaseHistoryList(UICaseHistoryHelper.formatEventInfo(cases));
		} 
		
		return aMapping.findForward(UIConstants.CASE_HISTORY_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward tasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		return aMapping.findForward(UIConstants.TASK_SUCCESS);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.BACK;
		if (csForm.getTaskId() != null && !csForm.getTaskId().equals("")){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.CANCEL;
		if ( csForm.isTaskflowInd() ){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws IOException
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward printCasenote(ActionMapping aMapping,	ActionForm aForm,HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws IOException, GeneralFeedbackMessageException
	{
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		
		SuperviseeInfoHeaderForm mySupHeader = UICommonSupervisionHelper.getSuperviseeInfoHeaderForm(aRequest, true);
		mySupHeader.clearAll();
		
		GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
									.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
		getEvent.setDefendantId( csForm.getSuperviseeId());
		SuperviseeInfoResponseEvent sprResponse =
						(SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent, SuperviseeInfoResponseEvent.class);
	
		String spn = sprResponse.getDefendantId();
		mySupHeader.setSpn( spn );
		mySupHeader.setSuperviseeName( new Name( "", "",sprResponse.getDefendantName()));
		mySupHeader.setOfficerName( new Name("","",sprResponse.getOfficerName() ));
		mySupHeader.setUnit( sprResponse.getProgramUnit() );
		mySupHeader.setLos( sprResponse.getSupervisionLevel() );
		this.buildNextContact( mySupHeader );
		this.getSuperviseeInfo( mySupHeader );
		
		GetAssessmentsSummaryEvent assessmentsSummaryEvent = (GetAssessmentsSummaryEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTSSUMMARY);
	    assessmentsSummaryEvent.setDefendantId( spn );
		assessmentsSummaryEvent.setSearchOnActiveSupervisionPeriod(true);
		List assessmentList = MessageUtil.postRequestListFilter( assessmentsSummaryEvent, AssessmentSummaryResponseEvent.class);
		
		int listSize = assessmentList.size();
		ArrayList newList = new ArrayList();
		if (assessmentList != null && listSize > 0) {
			for (int s=0; s<listSize; s++){
				AssessmentSummaryResponseEvent assessment = (AssessmentSummaryResponseEvent) assessmentList.get(s);
				String assessmentTypeId = assessment.getAssessmentTypeId();
				String assessmentStatusCd = assessment.getAssessmentStatusCd();
				Date assessmentDate = assessment.getAssessmentDate();
				if( StringUtils.isNotEmpty(assessmentStatusCd) && assessmentStatusCd.equals("C") 
						&& assessmentDate != null && StringUtils.isNotEmpty(assessmentTypeId)) {
					if( assessmentTypeId.equals("W") || assessmentTypeId.equals("L") ) {
						newList.add(assessment);
					}
				}
			}
		}
		listSize = newList.size();
		if(newList != null && listSize > 0){
			//sort assessments by date ascending order
			Collections.sort(newList, UICommonSupervisionHelper.assessmentComparator );
			
			AssessmentSummaryResponseEvent lastAssessment = (AssessmentSummaryResponseEvent) newList.get(listSize - 1);
			mySupHeader.setLastAssessmentDate(lastAssessment.getAssessmentDate());
		}
		
		List casenoteResp = new ArrayList();
		
		GetCasenoteByOIDEvent reqEvent = new GetCasenoteByOIDEvent();
		reqEvent.setCasenoteId( csForm.getCasenoteId() );
		
		casenoteResp = MessageUtil.postRequestListFilter(reqEvent, CasenoteResponseEvent.class);
		Casenote newCasenote = new Casenote();
		List prCasenotesList = new ArrayList();
		
		HttpSession session = aRequest.getSession(); 
		CasenoteJournalForm casenoteJournalForm = new CasenoteJournalForm();
							session.setAttribute("casenoteJournalForm", casenoteJournalForm);
		
		if (!casenoteResp.isEmpty())
		{
			CasenoteSearchForm searchForm = new CasenoteSearchForm();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();

			Iterator iter = casenoteResp.iterator();
			while (iter.hasNext())
			{
				ICasenote casenote = (CasenoteResponseEvent)iter.next();
				newCasenote = UICasenoteHelper.getCasenote(casenote, UIUtil.getCurrentUserID() );
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				prCasenotesList.add(newCasenote);
			}
			UICasenoteHelper.resolveCreatorNames(prCasenotesList);
			casenoteJournalForm.getSearchCasenote().setCasenoteResults( prCasenotesList );
		}
		
		GenericPrintRequestEvent casenotesPrintEvent = new GenericPrintRequestEvent();
	    casenotesPrintEvent.addDataObject(prepareBean( mySupHeader, casenoteJournalForm));   
	    casenotesPrintEvent.setReportName("REPORTING::CASENOTE");
	    	    
   	    ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.postRequest(casenotesPrintEvent, ReportResponseEvent.class );
   	    
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
	 * 
	 * @param mySupHeader
	 */
	private void buildNextContact( SuperviseeInfoHeaderForm mySupHeader ){
		
		GetMonthlyCSCalendarEvent getMonthlyEvents = 
			(GetMonthlyCSCalendarEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETMONTHLYCSCALENDAR);
		getMonthlyEvents.setCurrentContext("S");		
		getMonthlyEvents.setSuperviseeId( mySupHeader.getSpn() );	
		getMonthlyEvents.setStartDatetime(Calendar.getInstance().getTime());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getMonthlyEvents);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection events = MessageUtil.compositeToCollection(compositeResponse, MonthlyCSCalendarResponseEvent.class);
		List eventsList = (List) events;
		if(eventsList != null && !eventsList.isEmpty()){
			// Sort list if has data
			Collections.sort(eventsList, MonthlyCSCalendarResponseEvent.CSCalendarDateComparator);
			Collections.reverse(eventsList);
			Iterator<MonthlyCSCalendarResponseEvent> calendarEvents = eventsList.iterator();
			while( calendarEvents.hasNext() ){
				MonthlyCSCalendarResponseEvent event = (MonthlyCSCalendarResponseEvent) calendarEvents.next();
				//defect 65723 extra edit
				if (event.getStartDatetime().after(getMonthlyEvents.getStartDatetime()) ||
					( event.getStartTime() != null && !event.getStartTime().equals("") &&
					  event.getStartTime().after(getMonthlyEvents.getStartDatetime()) ) ) {
					if(event.getStatus().equals("O")) {
						if( mySupHeader.getNextContactDate() == null || mySupHeader.getNextContactDate().after(event.getStartDatetime()) ){
						
							mySupHeader.setNextContactDate(event.getStartDatetime());
						// if starttime present use it, as it contains date and time value.	
							if (event.getStartTime() != null && !event.getStartTime().equals("")){
								mySupHeader.setNextContactDate(event.getStartTime());
							}
							mySupHeader.setNextContactTime(event.getStartTime());
							mySupHeader.setContactMethodId(event.getCategoryCd());
							mySupHeader.setContactMethod(event.getCategoryDesc());
						}
					}
				} 
			}
		}
	}
	
	/**
	 * 
	 * @param headerForm
	 */
	private void getSuperviseeInfo(	SuperviseeInfoHeaderForm headerForm )
	{
		
		GetSuperviseeInSupervisionPeriodEvent mySuperviseeEvent = (GetSuperviseeInSupervisionPeriodEvent) EventFactory
											.getInstance(CasenoteControllerServiceNames.GETSUPERVISEEINSUPERVISIONPERIOD);
			mySuperviseeEvent.setSpn( headerForm.getSpn() );
			mySuperviseeEvent.setUserAgencyId( UIUtil.getCurrentUserAgencyID() );
			mySuperviseeEvent.setActiveSupervisionPeriod( true );
			
			SuperviseeResponseEvent supResp = (SuperviseeResponseEvent) 
									MessageUtil.postRequest( mySuperviseeEvent, SuperviseeResponseEvent.class );

		if ( supResp != null ){
			
			List myCasesList = new ArrayList();
			List caseList = (List) supResp.getCases();
			CaseInfo myCase = new CaseInfo();
			
			for ( int x=0; x< caseList.size(); x++ ){
				
				CasenoteCaseTO caseTO = (CasenoteCaseTO) caseList.get(x);
				
				myCase.setCaseNum(caseTO.getCaseNum());
				myCase.setCdi(caseTO.getCdi());
				myCase.setCourt( caseTO.getCourtNum( ));
				myCase.setCaseSupPeriodBeginDate(caseTO.getCaseSupervisionPeriodBeginDate());
				myCase.setCaseSupPeriodEndDate(caseTO.getCaseSupervisionPeriodEndDate() );
				myCase.setSupPeriodId(caseTO.getSupervisionPeriodId());
				myCase.setSuperviseeName( caseTO.getSuperviseeName());
			    if (StringUtils.isNotEmpty( caseTO.getOffenseCodeId() )){
			    	String descr = CodeHelper.getOffenseCodeDescription( caseTO.getOffenseCodeId() );
					myCase.setOffense( descr );
				}
				myCasesList.add( myCase );
				myCase = new CaseInfo();
				
			}
			
			headerForm.setSuperviseeName( new Name( supResp.getFirstName(), supResp.getMiddleName(), supResp.getLastName() ));
			headerForm.setCases( myCasesList );


		}
	}
	
	 private CasenotesJournalBean prepareBean(SuperviseeInfoHeaderForm sForm, CasenoteJournalForm cjForm){
	    	CasenotesJournalBean printDataBean = new CasenotesJournalBean();
	    	
	    	printDataBean.setSupInfoHeaderForm(sForm);
	    	printDataBean.setCasenoteJournalForm(cjForm);
	    	printDataBean.setSuperviseeName(sForm.getSuperviseeName().getFormattedName());   
	    	printDataBean.setOfficerName(sForm.getOfficerName().getFormattedName());
	    	return printDataBean;
	}
}
