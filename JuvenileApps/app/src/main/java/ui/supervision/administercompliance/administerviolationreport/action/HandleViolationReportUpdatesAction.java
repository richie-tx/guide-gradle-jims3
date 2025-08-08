//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\HandleViolationReportUpdatesAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import java.util.Iterator;
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
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.exception.ReturnException;

import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
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
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderSearchForm;
import ui.supervision.administerserviceprovider.programreferral.action.UIProgramHierarchyBean;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleViolationReportUpdatesAction extends JIMSBaseAction {

	private static final String Service_Provider_Search_Form = "cscServiceProviderSearchForm";

	/**
	 * @roseuid 47DA9D400110
	 */
	public HandleViolationReportUpdatesAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.submitForApproval", "submitForApproval");
		keyMap.put("button.violationReportsList", "vrList");
		keyMap.put("button.maintain", "maintainReport");
		keyMap.put("button.update", "updateReport");
		keyMap.put("button.delete", "deleteReport");
		keyMap.put("button.file/CourtActions", "fileReport");
		keyMap.put("button.print", "printReport");	
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.UPDATE_FAILURE;
		String forwardType = aRequest.getParameter("type").trim();
		vrForm.setShowAddFields(false);
		vrForm.setAddItemIndex("");
		vrForm.setConfirmationMessage("");
		if (vrForm.getSecondaryAction().equals(UIConstants.CREATE)){
			if (forwardType != null && !forwardType.equals("")) {
				if (forwardType.equalsIgnoreCase("reasonForTransfer")){
					UIViolationReportHelper.getReasonForTransferCreateInfo(vrForm);
					forwardStr = "transferSuccess";
				}else 
					if (forwardType.equalsIgnoreCase("mentalHealth")){
						UIViolationReportHelper.getMentalHealthCreateInfo( vrForm );
						forwardStr = "mentalHealthSuccess";
					}else 
						if (forwardType.equalsIgnoreCase("lawViolation")){
							UIViolationReportHelper.getLawViolationCreateInfo(vrForm);
							forwardStr = "lawViolationSuccess";
						}else 
							if (forwardType.equalsIgnoreCase("feeHistory")){
								UIViolationReportHelper.getFeeHistoryCreateInfo(vrForm);
								forwardStr = "feeHistorySuccess";
								}else 
									if (forwardType.equalsIgnoreCase("reportingHistory")){
										UIViolationReportHelper.getReportingHistoryCreateInfo(vrForm);
										forwardStr = "reportingHistorySuccess";
										}else 
											if (forwardType.equalsIgnoreCase("employmentHistory")){
												UIViolationReportHelper.getEmploymentHistoryCreateInfo(vrForm);
										        forwardStr = "employmentHistorySuccess";
												}else 
													if (forwardType.equalsIgnoreCase("previousCourtActivity")){
														UIViolationReportHelper.getPreviousCourtActivityCreateInfo(vrForm);
														forwardStr = "previousCourtActivitySuccess";
														}else 
															if (forwardType.equalsIgnoreCase("treatmentIssues")){
																UIViolationReportHelper.getTreatmentIssuesCreateInfo(vrForm);
																//load cscServiceProviderSearchForm form update treatment displayed in JSP
																ServiceProviderSearchForm cscServiceProviderSearchForm = (ServiceProviderSearchForm)getSessionForm(aMapping, aRequest, Service_Provider_Search_Form,true);
																//clear old cscServiceProviderSearchForm form
																cscServiceProviderSearchForm.clear();
																loadProgramHierarchy( cscServiceProviderSearchForm );
																forwardStr = "teatmentIssuesSuccess";
																}else 
																	if (forwardType.equalsIgnoreCase("communityService")){
																		UIViolationReportHelper.getCommunityServiceCreateInfo(vrForm);
																		forwardStr = "communityServiceSuccess";
																		}else 
																			if (forwardType.equalsIgnoreCase("positiveUrinalysis")){
																				UIViolationReportHelper.getPositiveUrinalysisCreateInfo(vrForm);
																				forwardStr = "positveUrinalysisSuccess";
																				}else 
																					if (forwardType.equalsIgnoreCase("recommendations")){
																						UIViolationReportHelper.getRecommendationsCreateInfo(vrForm);
																						forwardStr = "recommendationsSuccess";
																						}
			}
		}	
		if (vrForm.getSecondaryAction().equals(UIConstants.UPDATE) || vrForm.getSecondaryAction().equals(UIConstants.MAINTAIN)){
			if (forwardType != null && !forwardType.equals("")) {
				if (forwardType.equalsIgnoreCase("reasonForTransfer")){
					UIViolationReportHelper.getReasonForTransferUpdateInfo(vrForm);
					forwardStr = "transferSuccess";
				}else 
					if (forwardType.equalsIgnoreCase("mentalHealth")){
						UIViolationReportHelper.getMentalHealthCreateInfo( vrForm );
						forwardStr = "mentalHealthSuccess";
					}else 
						if (forwardType.equalsIgnoreCase("lawViolation")){
							UIViolationReportHelper.getLawViolationUpdateInfo(vrForm);
							forwardStr = "lawViolationSuccess";
						}else 
							if (forwardType.equalsIgnoreCase("feeHistory")){
								UIViolationReportHelper.getFeeHistoryUpdateInfo(vrForm);
								forwardStr = "feeHistorySuccess";
								}else 
									if (forwardType.equalsIgnoreCase("reportingHistory")){
										UIViolationReportHelper.getReportingHistoryUpdateInfo(vrForm);
										forwardStr = "reportingHistorySuccess";
										}else 
											if (forwardType.equalsIgnoreCase("employmentHistory")){
												UIViolationReportHelper.getEmploymentHistoryUpdateInfo(vrForm);
										        forwardStr = "employmentHistorySuccess";
												}else 
													if (forwardType.equalsIgnoreCase("previousCourtActivity")){
														UIViolationReportHelper.getPreviousCourtActivityUpdateInfo(vrForm);
														forwardStr = "previousCourtActivitySuccess";
														}else 
															if (forwardType.equalsIgnoreCase("treatmentIssues")){
																UIViolationReportHelper.getTreatmentIssuesUpdateInfo(vrForm);
																//load cscServiceProviderSearchForm form update treatment displayed in JSP
																ServiceProviderSearchForm cscServiceProviderSearchForm = (ServiceProviderSearchForm)getSessionForm(aMapping, aRequest, Service_Provider_Search_Form,true);
																//clear old cscServiceProviderSearchForm form
																cscServiceProviderSearchForm.clear();
																loadProgramHierarchy( cscServiceProviderSearchForm );
																forwardStr = "teatmentIssuesSuccess";
																}else 
																	if (forwardType.equalsIgnoreCase("communityService")){
																		UIViolationReportHelper.getCommunityServiceUpdateInfo(vrForm);
																		forwardStr = "communityServiceSuccess";
																		}else 
																			if (forwardType.equalsIgnoreCase("positiveUrinalysis")){
																				UIViolationReportHelper.getPositiveUrinalysisUpdateInfo(vrForm);
																				forwardStr = "positveUrinalysisSuccess";
																				}else 
																					if (forwardType.equalsIgnoreCase("recommendations")){
																						UIViolationReportHelper.getRecommendationsUpdateInfo(vrForm);
																						forwardStr = "recommendationsSuccess";
																						}else 
																							if (forwardType.equalsIgnoreCase("updateCourtActions")){
																								// set up the presented and signed info
																								// retrieve court number from header form to use to set default who signed selection	
																								CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);
																								String courtNumber = null;
																								if (caForm != null){
																									courtNumber = caForm.getCourtNumber();
																								}
																								UIViolationReportHelper.getPresentedSignedInformation(vrForm,courtNumber);
																								UIViolationReportHelper.getUpdateCourtActionsUpdateInfo(vrForm);
																								forwardStr = "updateCourtActionsSuccess";
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forward = UIConstants.APPROVAL_SUCCESS;

		vrForm.setConfirmationMessage("Violation Report successfully submitted for approval.");
		if (vrForm.getViolationReportId() == null || vrForm.getViolationReportId().equals("")){
			forward = UIConstants.UPDATE_FAILURE; 
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"No information entered to submit for approval"));
			saveErrors(aRequest, errors);
			vrForm.setConfirmationMessage("");
		}
		String statusId = ViolationReportConstants.STATUS_PENDING_MANAGER_APPROVAL;
		String subject = ViolationReportConstants.CSTASK_SUBJECT_NEW_VIOLATION_FOR_APPROVAL;
		String topic = ViolationReportConstants.CSTASK_TOPIC_NEW_VIOLATION_FOR_APPROVAL;
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		String officerId = "";
		UICaseHistoryHelper chHelper = new UICaseHistoryHelper();
		
		Map officersMap = chHelper.buildOfficerHiearchyMap( caForm );
		
		if ( "DR".equals(vrForm.getStatusId() )){
			
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
			vrForm.setConfirmationMessage("");
		}
		
		vrForm.setTaskToStaffId( officerId );
		vrForm.setSuperviseeName(caForm.getSuperviseeNameStr());
		vrForm.setSuperviseeId(caForm.getDefendantId());
		vrForm.setOfficerName(caForm.getOfficerNameStr());
		vrForm.setOffense(caForm.getOffenseDesc());
		vrForm.setProgramUnit(caForm.getProgramUnitName());
		vrForm.setLos(caForm.getLevelOfSupervision());
		vrForm.setCdi(caForm.getCdi());
		vrForm.setCaseNum( caForm.getCaseNum() );
		vrForm.setCourtNum(caForm.getCourtNumber());
		vrForm.setAction("submitForApproval");
		StringBuffer taskText = new StringBuffer();
		taskText.append(ViolationReportConstants.CSTASK_SUBJECT_NEW_VIOLATION_FOR_APPROVAL);
		taskText.append(" ");
		if (vrForm.getSuperviseeName() != null){
			taskText.append(vrForm.getSuperviseeName());
		}
		taskText.append(", ");
		if (vrForm.getSuperviseeId() != null){
			taskText.append(vrForm.getSuperviseeId());
		}	
		taskText.append(", ");
		if (vrForm.getCaseNum() != null){
			taskText.append(vrForm.getCaseNum());
		}	
		taskText.append(", ");
		if (vrForm.getCourtNum() != null){
			taskText.append(vrForm.getCourtNum());
		}
		UpdateNCResponseStatusEvent uEvent = UIViolationReportHelper.prepareRequestEvent(vrForm, subject, taskText.toString(), topic, statusId,false);
		uEvent.setAssignedOfficerId( caForm.getOfficerPositionId() );
		uEvent.setAllocSupervisorId( caForm.getSupervisorPositionId() );
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.setConfirmationMessage("");
		vrForm.setAction("");
		vrForm.setSecondaryAction(UIConstants.MAINTAIN);
		vrForm.setAllowUpdate("Y");
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
	public ActionForward updateReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.setConfirmationMessage("");
		vrForm.setAction("");
		vrForm.setSecondaryAction(UIConstants.UPDATE);
		vrForm.setAllowUpdate("Y");
		return aMapping.findForward(UIConstants.MAINTAIN_SUCCESS);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		vrForm.setConfirmationMessage("");
		vrForm.setAction("");
		String forward = UIConstants.UPDATE_FAILURE;
    	DeleteNCResponseEvent event = (DeleteNCResponseEvent) EventFactory.getInstance(ComplianceControllerServiceNames.DELETENCRESPONSE);
    	event.setNcResponseId(vrForm.getViolationReportId());    	
    	MessageUtil.postRequest(event);
		ReturnException returnException = (ReturnException) MessageUtil.postRequest(event, ReturnException.class);
		if (returnException == null){
			vrForm.setConfirmationMessage("Violation Report successfully deleted.");
			forward = UIConstants.DELETE_SUCCESS;
			if (vrForm.getTaskId() != null && !vrForm.getTaskId().equals(0)){
				
				UICaseHistoryHelper helper = new UICaseHistoryHelper();
				helper.closeCsTaskById( vrForm.getTaskId() );
			}
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured deleting Law Violation information"));
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
	public ActionForward fileReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
// retrieve court number from header form to use to set default who signed selection	
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);
		String courtNumber = null;
		if (caForm != null){
			courtNumber = caForm.getCourtNumber();
		}
		UIViolationReportHelper.prepareToFileReport(vrForm, courtNumber);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.BACK;
		if ( vrForm.isTaskflowInd() ){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}else if (vrForm.getViolationReportsDisplayList() == null || vrForm.getViolationReportsDisplayList().isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No reports found for this supervisee on this case"));
			saveErrors(aRequest, errors);
			vrForm.setViolationReportsDisplayList(new ArrayList());
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
	public ActionForward vrList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forward = UIConstants.VIOLATION_REPORT_LIST_SUCCESS;
		vrForm.setConfirmationMessage("Violation Report successfully saved as Draft.");
		if (vrForm.getViolationReportId() == null || vrForm.getViolationReportId().equals("")){
			forward = UIConstants.UPDATE_FAILURE; 
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"No information entered to create Law Violation"));
			saveErrors(aRequest, errors);
		} else {
			List reports = UICaseHistoryHelper.getViolationReports(new StringBuffer(vrForm.getCdi()).append(vrForm.getCaseNum()).toString());
			if (reports == null || reports.isEmpty()) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No reports found for this supervisee"));
				saveErrors(aRequest, errors);
				vrForm.setViolationReportsDisplayList(new ArrayList());
			} else {
				vrForm.setViolationReportsDisplayList(reports);
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
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.CANCEL;
		if ( vrForm.isTaskflowInd() ){
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
	public ActionForward printReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws  Exception {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,
				"caseAssignmentForm", true);
		caseAssignmentForm.setDefendantId(vrForm.getSuperviseeId());
		SuperviseeForm superviseeForm = (SuperviseeForm) getSessionForm(aMapping, aRequest,
				"superviseeForm", true);
		superviseeForm.setSuperviseeId(vrForm.getSuperviseeId());
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
		Collections.sort((List)events,CSEventsReportReponseEvent.CSEventsReportComparator);
		Collections.reverse(events);
		UIViolationReportHelper.sortTreatmentIssuesList(vrForm.getCurrentTreatmentIssuesList());
		if( events !=null && events.size() > 0 ){
			Iterator < CSEventsReportReponseEvent > calendarEvents = events.iterator();
			while( calendarEvents.hasNext() ){		
				CSEventsReportReponseEvent event = (CSEventsReportReponseEvent) calendarEvents.next();
				if ( StringUtils.isNotEmpty(event.getStatus()) && event.getStatus().equals("O") ){
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
		UIViolationReportHelper.printSetup(vrForm, caseAssignmentForm, myHeaderForm, superviseeForm, mySupHeader, aResponse);
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