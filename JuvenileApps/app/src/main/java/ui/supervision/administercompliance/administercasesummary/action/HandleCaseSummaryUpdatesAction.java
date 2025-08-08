//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\HandleCaseSummaryUpdatesAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.DeleteNCResponseEvent;
import messaging.administercompliance.UpdateNCResponseStatusEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdcalendar.GetCalendarEventsByDefendantEvent;
import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import messaging.cscdstaffposition.GetStaffByUserIdEvent;
import messaging.managetask.UpdateCSTaskEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.CSTaskControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.security.PDSecurityHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDOrganizationStaffPosition;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.administercompliance.administercasehistory.UICaseHistoryHelper;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderSearchForm;
import ui.supervision.administerserviceprovider.programreferral.action.UIProgramHierarchyBean;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleCaseSummaryUpdatesAction extends JIMSBaseAction {
	
	private static final String Service_Provider_Search_Form = "cscServiceProviderSearchForm";

	/**
	 * @roseuid 47DA9D400110
	 */
	public HandleCaseSummaryUpdatesAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.submitForApproval", "submitForApproval");
		keyMap.put("button.maintain", "maintainReport");
		keyMap.put("button.caseSummaryList", "caseSummaryList");
		keyMap.put("button.delete", "deleteReport");
		keyMap.put("button.file/CourtActions", "fileReport");
		keyMap.put("button.print", "printUJACReport");	
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
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.UPDATE_FAILURE;
		String forwardType = aRequest.getParameter("type").trim();
		csForm.setShowAddFields(false);
		csForm.setAddItemIndex("");
		csForm.setConfirmationMessage("");
		if (csForm.getSecondaryAction().equals(UIConstants.CREATE)){
			if (forwardType != null && !forwardType.equals("")) {
				if (forwardType.equalsIgnoreCase("reasonForTransfer")){
					UICaseSummaryHelper.getReasonForTransferCreateInfo(csForm);
					forwardStr = "transferSuccess";
				}else 
					if (forwardType.equalsIgnoreCase("mentalHealth")){
						UICaseSummaryHelper.getMentalHealthCreateInfo( csForm );
						forwardStr = "mentalHealthSuccess";
					}else 
						if (forwardType.equalsIgnoreCase("lawViolation")){
							UICaseSummaryHelper.getLawViolationCreateInfo(csForm);
							forwardStr = "lawViolationSuccess";
						}else 
							if (forwardType.equalsIgnoreCase("feeHistory")){
								UICaseSummaryHelper.getFeeHistoryCreateInfo(csForm);
								forwardStr = "feeHistorySuccess";
								}else 
									if (forwardType.equalsIgnoreCase("reportingHistory")){
										UICaseSummaryHelper.getReportingHistoryCreateInfo(csForm);
										forwardStr = "reportingHistorySuccess";
										}else 
											if (forwardType.equalsIgnoreCase("employmentHistory")){
												UICaseSummaryHelper.getEmploymentHistoryCreateInfo(csForm);
										        forwardStr = "employmentHistorySuccess";
												}else 
													if (forwardType.equalsIgnoreCase("previousCourtActivity")){
														UICaseSummaryHelper.getPreviousCourtActivityCreateInfo(csForm);
														forwardStr = "previousCourtActivitySuccess";
														}else 
															if (forwardType.equalsIgnoreCase("treatmentIssues")){
																UICaseSummaryHelper.getTreatmentIssuesCreateInfo(csForm);
																//load cscServiceProviderSearchForm form update treatment displayed in JSP
																ServiceProviderSearchForm cscServiceProviderSearchForm = (ServiceProviderSearchForm)getSessionForm(aMapping, aRequest, Service_Provider_Search_Form,true);
																//clear old cscServiceProviderSearchForm form
																cscServiceProviderSearchForm.clear();
																loadProgramHierarchy( cscServiceProviderSearchForm );
																forwardStr = "teatmentIssuesSuccess";
																}else 
																	if (forwardType.equalsIgnoreCase("communityService")){
																		UICaseSummaryHelper.getCommunityServiceCreateInfo(csForm);
																		forwardStr = "communityServiceSuccess";
																		}else 
																			if (forwardType.equalsIgnoreCase("positiveUrinalysis")){
																				UICaseSummaryHelper.getPositiveUrinalysisCreateInfo(csForm);
																				forwardStr = "positveUrinalysisSuccess";
																				}else 
																					if (forwardType.equalsIgnoreCase("recommendations")){
																						UICaseSummaryHelper.getRecommendationsCreateInfo(csForm);
																						forwardStr = "recommendationsSuccess";
																						}
				
			}
		}	
		if (csForm.getSecondaryAction().equals(UIConstants.UPDATE) || csForm.getSecondaryAction().equals(UIConstants.MAINTAIN)){
			if (forwardType != null && !forwardType.equals("")) {
				if (forwardType.equalsIgnoreCase("reasonForTransfer")){
					UICaseSummaryHelper.getReasonForTransferUpdateInfo(csForm);
					forwardStr = "transferSuccess";
				}else 
				  if (forwardType.equalsIgnoreCase("mentalHealth")){
					 UICaseSummaryHelper.getMentalHealthUpdateInfo( csForm );
					 forwardStr = "mentalHealthSuccess";
					}else 
						if (forwardType.equalsIgnoreCase("lawViolation")){
							UICaseSummaryHelper.getLawViolationUpdateInfo(csForm);
							forwardStr = "lawViolationSuccess";
						}else 
							if (forwardType.equalsIgnoreCase("feeHistory")){
								UICaseSummaryHelper.getFeeHistoryUpdateInfo(csForm);
								forwardStr = "feeHistorySuccess";
								}else 
									if (forwardType.equalsIgnoreCase("reportingHistory")){
										UICaseSummaryHelper.getReportingHistoryUpdateInfo(csForm);
										forwardStr = "reportingHistorySuccess";
										}else 
											if (forwardType.equalsIgnoreCase("employmentHistory")){
												UICaseSummaryHelper.getEmploymentHistoryUpdateInfo(csForm);
										        forwardStr = "employmentHistorySuccess";
												}else 
													if (forwardType.equalsIgnoreCase("previousCourtActivity")){
														UICaseSummaryHelper.getPreviousCourtActivityUpdateInfo(csForm);
														forwardStr = "previousCourtActivitySuccess";
														}else 
															if (forwardType.equalsIgnoreCase("treatmentIssues")){
																UICaseSummaryHelper.getTreatmentIssuesUpdateInfo(csForm);
																//load cscServiceProviderSearchForm form update treatment displayed in JSP
																ServiceProviderSearchForm cscServiceProviderSearchForm = (ServiceProviderSearchForm)getSessionForm(aMapping, aRequest, Service_Provider_Search_Form,true);
																//clear old cscServiceProviderSearchForm form
																cscServiceProviderSearchForm.clear();
																loadProgramHierarchy( cscServiceProviderSearchForm );
																forwardStr = "teatmentIssuesSuccess";
																}else 
																	if (forwardType.equalsIgnoreCase("communityService")){
																		UICaseSummaryHelper.getCommunityServiceUpdateInfo(csForm);
																		forwardStr = "communityServiceSuccess";
																		}else 
																			if (forwardType.equalsIgnoreCase("positiveUrinalysis")){
																				UICaseSummaryHelper.getPositiveUrinalysisUpdateInfo(csForm);
																				forwardStr = "positveUrinalysisSuccess";
																				}else 
																					if (forwardType.equalsIgnoreCase("recommendations")){
																						UICaseSummaryHelper.getRecommendationsUpdateInfo(csForm);
																						forwardStr = "recommendationsSuccess";
																						}if (forwardType.equalsIgnoreCase("updateCourtActions")){
																							// set up the presented and signed info
																							// retrieve court number from header form to use to set default who signed selection	
																							CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);
																							String courtNumber = null;
																							if (caForm != null){
																								courtNumber = caForm.getCourtNumber();
																							}
																							UICaseSummaryHelper.getPresentedSignedInformation(csForm,courtNumber);
																							UICaseSummaryHelper.getUpdateCourtActionsUpdateInfo(csForm);
																							forwardStr = "updateCaseSumCourtActionsSuccess";
																							}
			}
		}	

		if (forwardStr.equalsIgnoreCase(UIConstants.UPDATE_FAILURE)){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
				"Error occured attempting to link to update page"));
			saveErrors(aRequest, errors);
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
	public ActionForward submitForApproval(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forward = UIConstants.APPROVAL_SUCCESS;
		
		csForm.setConfirmationMessage("Case Summary successfully submitted for approval.");
		if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){
			forward = UIConstants.UPDATE_FAILURE; 
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"No information entered to submit for approval"));
			saveErrors(aRequest, errors);
			csForm.setConfirmationMessage("");
		}
		// VERIFY THESE CONSTANT VALUES 		
		String statusId = ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL;
		String subject = ViolationReportConstants.CSTASK_SUBJECT_NEW_CASESUMMARY_FOR_APPROVAL;
		String topic = ViolationReportConstants.CSTASK_TOPIC_CASESUMMARY_APPROVAL;
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		csForm.setSuperviseeName(caForm.getSuperviseeNameStr());
		csForm.setSuperviseeId(caForm.getDefendantId());
		csForm.setOfficerName(caForm.getOfficerNameStr());
		csForm.setOffense(caForm.getOffenseDesc());
		csForm.setProgramUnit(caForm.getProgramUnitName());
		csForm.setLos(caForm.getLevelOfSupervision());
		csForm.setCdi(caForm.getCdi());
		csForm.setCaseNum(caForm.getCaseNum());
		csForm.setCourtNum(caForm.getCourtNumber());
		StringBuffer taskText = new StringBuffer();
		// VERIFY THIS CONSTANT VALUE		
		taskText.append(ViolationReportConstants.CSTASK_SUBJECT_NEW_CASESUMMARY_FOR_APPROVAL);
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
		
		String officerId = "";
		UICaseHistoryHelper chHelper = new UICaseHistoryHelper();
		
		Map officersMap = chHelper.buildOfficerHiearchyMap( caForm );
		
		if ( "DR".equals(csForm.getStatusId() )){
			
			if(isUserSupervisorType(SecurityUIHelper.getJIMSLogonId())){
				officerId = (String) officersMap.get("currentUserPosition");
			}else{
				officerId = (String) officersMap.get("csoSupervisor");
			}
		}
			
		if ( officerId == null ){
			
			forward = UIConstants.UPDATE_FAILURE; 
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Case does not have CLO/CSO assigned properly"));
			saveErrors( aRequest, errors );
			csForm.setConfirmationMessage("");
		}
		csForm.setTaskToStaffId( officerId );
		UpdateNCResponseStatusEvent uEvent = UICaseSummaryHelper.prepareRequestEvent(csForm, subject, taskText.toString(), topic, statusId,false);
		MessageUtil.postRequest(uEvent);
		return aMapping.findForward(forward);
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
	 * 
	 * @param csSpForm
	 */
	private void loadProgramHierarchy( ServiceProviderSearchForm csSpForm ){
		
		List hierarchyBeans = new ArrayList();
		UIProgramHierarchyBean hierarchyBean = new UIProgramHierarchyBean();

		List progGroups = SimpleCodeTableHelper.getCodesSortedByCode("CS_PROGRAM_GROUP");
		List progTypes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("CS_PROGRAM_TYPE");
		
		String parentCode = "";
		List children = new ArrayList();
		
		for ( int ctr = 0; ctr< progGroups.size();ctr++){
			
			CodeResponseEvent groupCode = (CodeResponseEvent) progGroups.get(ctr);
			if ( "ACTIVE".equalsIgnoreCase( groupCode.getStatus() )){

				parentCode = groupCode.getCode();	
				hierarchyBean = new UIProgramHierarchyBean();
				hierarchyBean.setParentCd( parentCode );
				hierarchyBean.setParentDesc(groupCode.getDescription());
				
				for (int cntr =0; cntr< progTypes.size(); cntr++)
				{
					JuvenileCodeTableChildCodesResponseEvent joscre = (JuvenileCodeTableChildCodesResponseEvent) progTypes.get( cntr );
	
					if ( "ACTIVE".equalsIgnoreCase(joscre.getStatus()) && parentCode.equalsIgnoreCase( joscre.getParentId() ) 
							&& verifyChildProgramTypeAllowedinProgramGroup(joscre.getParentId(),joscre.getCode()) ) {
						
						children.add( joscre );
	
					}
				}	
				hierarchyBean.setChildEvents( children );
				hierarchyBeans.add( hierarchyBean );
				children = new ArrayList();
				csSpForm.setProgramHeirarchyList(hierarchyBeans);
			}
		}
		csSpForm.setProgramTypes( new ArrayList() );
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward deleteReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setConfirmationMessage("");
		String forward = UIConstants.UPDATE_FAILURE;
    	DeleteNCResponseEvent event = (DeleteNCResponseEvent) EventFactory.getInstance(ComplianceControllerServiceNames.DELETENCRESPONSE);
    	event.setNcResponseId(csForm.getViolationReportId());    	
    	MessageUtil.postRequest(event);
		ReturnException returnException = (ReturnException) MessageUtil.postRequest(event, ReturnException.class);
		if (returnException == null){
			csForm.setConfirmationMessage("Case Summary successfully deleted.");
			forward = UIConstants.DELETE_SUCCESS;
			if (csForm.getTaskId() != null && !csForm.getTaskId().equals(0)){
				UpdateCSTaskEvent updateTaskEvent = 
					(UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
				updateTaskEvent.setCsTaskId( csForm.getTaskId());
				updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
				updateTaskEvent.setCloseTask( true );
				MessageUtil.postRequest(updateTaskEvent);
			}
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured deleting Case Summary information"));
			saveErrors(aRequest, errors);
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward caseSummaryList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forward = UIConstants.CASE_SUMMARY_SUCCESS;
		csForm.setConfirmationMessage("Violation Report successfully saved as Draft.");
		if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){
			forward = UIConstants.UPDATE_FAILURE; 
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"No information entered to create Law Violation"));
			saveErrors(aRequest, errors);
		} else {
			String cdiCaseNum = "";  // some flows case number has cdi and case number and cdi on form is null
			if (csForm.getCaseNum().length() == 15) {
				cdiCaseNum = csForm.getCaseNum();
			} else {
				cdiCaseNum = new StringBuffer(csForm.getCdi()).append(csForm.getCaseNum()).toString();
			}
			List reports = UICaseHistoryHelper.getCaseSummaries(cdiCaseNum);
			if (reports == null || reports.isEmpty()) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No reports found for this supervisee"));
				saveErrors(aRequest, errors);
				csForm.setCaseSummaryDisplayList(new ArrayList());
			} else { 
				csForm.setCaseSummaryDisplayList(reports);
			} 
		} 
		return aMapping.findForward(forward);
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
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.BACK;
		if (csForm.getTaskId() != null && !csForm.getTaskId().equals("")){
			forwardStr = UIConstants.TASKFLOW_BACK;
		} else if (csForm.getCaseSummaryDisplayList() == null || csForm.getCaseSummaryDisplayList().isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No reports found for this supervisee on this case"));
			saveErrors(aRequest, errors);
			csForm.setCaseSummaryDisplayList(new ArrayList());
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
	 * @throws Exception
	 */
	public ActionForward printUJACReport(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
    	
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
    	
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,
				"caseAssignmentForm", true);
		
		caseAssignmentForm.setDefendantId(csForm.getSuperviseeId());
		
		SuperviseeForm superviseeForm = (SuperviseeForm) getSessionForm(aMapping, aRequest,
				"superviseeForm", true);
		
		superviseeForm.setSuperviseeId(csForm.getSuperviseeId());
		
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm)getSessionForm(aMapping, aRequest,
				"superviseeHeaderForm", true);
		
		SuperviseeInfoHeaderForm mySupHeader = UICommonSupervisionHelper.getSuperviseeInfoHeaderForm(aRequest, true);
		mySupHeader.clearAll();
		//Query the CS Calendar Events
		
		GetCalendarEventsByDefendantEvent requestEvent = (GetCalendarEventsByDefendantEvent)
						EventFactory.getInstance(CSEventControllerServiceNames.GETCALENDAREVENTSBYDEFENDANT);
		
		requestEvent.setDefendantId( myHeaderForm.getSuperviseeId());
		
		List events = MessageUtil.postRequestListFilter(requestEvent, CSEventsReportReponseEvent.class);
		//sort monthly events in chronological order ascending 
		Collections.sort((List)events,CSEventsReportReponseEvent.CSEventsReportComparator);
		//reverse order of monthly events
		Collections.reverse(events);
		if( events !=null && events.size() > 0 ){
			Iterator < CSEventsReportReponseEvent > calendarEvents = events.iterator();
			while( calendarEvents.hasNext() ){		
				CSEventsReportReponseEvent event = (CSEventsReportReponseEvent) calendarEvents.next();
				if ( StringUtils.isNotEmpty(event.getStatus()) && event.getStatus().equals("O") ){
					//csEvenType of "OV" for Next Contact Date
					if ( StringUtils.isNotEmpty(event.getCsEventTypeId()) && event.getCsEventTypeId().equals("OV")) {
						if( event.getCsEventDate()!= null ){
							if( event.getCsEventDate().after( Calendar.getInstance().getTime() ) ){
								mySupHeader.setNextContactDate(event.getCsEventDate());
								if( event.getStartTime() != null ){
									mySupHeader.setNextContactTime(event.getStartTime());
								}
							} 
						}
					}
				} else if ( StringUtils.isNotEmpty(event.getStatus()) && event.getStatus().equals("C") ){
					//Outcome of "AT" for Office Visits is ATTENDED
					if (  StringUtils.isNotEmpty(event.getOutcomeCd()) && event.getOutcomeCd().equals("AT")){
						mySupHeader.setLastContactDate(event.getCsEventDate());
						mySupHeader.setLastContactTime(event.getStartTime());
					//Outcome of "CO" for Field Visits is COMPLETE
					} else if (  StringUtils.isNotEmpty(event.getOutcomeCd()) && event.getOutcomeCd().equals("CO")){
					//Contact Method of "DI" for Field Visits is DIRECT CONTACT
						if (  StringUtils.isNotEmpty(event.getContactMethod()) && event.getContactMethod().equals("DI")){
							mySupHeader.setLastContactDate(event.getCsEventDate());
							mySupHeader.setLastContactTime(event.getStartTime());
						}
					}
					if( mySupHeader.getLastContactDate() != null ) {
						break;
					}
				} 
			}
		}
    	
		UICaseSummaryHelper helper = new UICaseSummaryHelper();
    	 			
		GenericPrintRequestEvent caseSummaryEvent = new GenericPrintRequestEvent();
	    caseSummaryEvent.addDataObject(helper.buildReportingBean(csForm, caseAssignmentForm, myHeaderForm,
	    		mySupHeader, superviseeForm));   
	    caseSummaryEvent.setReportName("REPORTING::CASE SUMMARY REPORT");
	 
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);   
	    dispatch.postEvent(caseSummaryEvent);
	    
	    CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
   	 
   	    MessageUtil.processReturnException(compResponse);
   	   
   	    ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);
   	    if (aRespEvent != null)
   	    {
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
   	    }
        return null;
    }
	
	/**
	 * filter out child program types from being added to program groups from the complete set of program group/program type
	 * relationships.  Only certain parent types will be filtered on, and in those cases, only those listed will be confirmed as valid
	 * @param parentGroupId
	 * @param childTypeCode
	 * @return
	 */
	private boolean verifyChildProgramTypeAllowedinProgramGroup(String parentGroupId, String childTypeCode){
		
		final String RESIDENTIAL_GROUP = "RESFAC";
		Map<String, List> allowedRelationships = new LinkedHashMap<String, List>();
		// Treatment "TRTMT" Program Types that are allowed in TRTMT Program Group (all others in this group are filtered out).
		// Add others in the future as needed
		List<String> resValues = new ArrayList<String>();
		resValues.add("SUBABRES");
		resValues.add("SUBABAFT");
		resValues.add("INPAT");
		allowedRelationships.put(RESIDENTIAL_GROUP, resValues);
		boolean isAllowed = true;
		// check for allowed types in group and only include these
		if(parentGroupId != null && parentGroupId.equalsIgnoreCase(RESIDENTIAL_GROUP)){
			if(!allowedRelationships.get(RESIDENTIAL_GROUP).contains(childTypeCode)){
				isAllowed = false;
			}
		}
		
		return isAllowed;
	}
	
	/**
	 * determine if the given user with login id and agency has a job title of SUPERVISOR or ASSISTANT SUPERVISOR
	 * @param staffLoginId
	 * @return
	 */
	private boolean isUserSupervisorType(String staffLoginId){
		final String SUPERVISOR_TITLE_CODE = "SSP";	// SUPERVISOR JOB TITLE
		final String ASSISTANT_SUPERVISOR_TITLE_CODE = "AS"; //ASSISTANT SUPERVISOR JOB TITLE
		boolean isUserInRole = false;
	    GetStaffByUserIdEvent getByUserId = new GetStaffByUserIdEvent();
	    getByUserId.setAgencyId(PDSecurityHelper.getUserAgencyId());
	    getByUserId.setStaffLogonId(staffLoginId);
	    Iterator iter = CSCDOrganizationStaffPosition.findAll(getByUserId);
	    CSCDOrganizationStaffPosition currentStaffPosition = null;
	    while(iter.hasNext()){
	    	currentStaffPosition = (CSCDOrganizationStaffPosition)iter.next();
	    }
	    if(currentStaffPosition.getJobTitleCode().equalsIgnoreCase(SUPERVISOR_TITLE_CODE) 
	    		|| currentStaffPosition.getJobTitleCode().equalsIgnoreCase(ASSISTANT_SUPERVISOR_TITLE_CODE)){
	    	isUserInRole = true;
	    }
	    return isUserInRole;
	}
}