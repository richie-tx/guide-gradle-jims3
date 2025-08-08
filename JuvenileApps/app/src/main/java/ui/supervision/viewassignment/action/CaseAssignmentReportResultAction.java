/*
 * Created on Jan 22, 2008
 *
 */
package ui.supervision.viewassignment.action;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.domintf.contact.party.ISupervisee;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import messaging.viewassignment.CaseAssignmentReportEvent;
import messaging.viewassignment.CaseAssignmentReportResponseEvent;
import messaging.viewassignment.SuperviseeTO;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.viewassignment.form.BaseCaseAssignmentReport;
import ui.supervision.viewassignment.form.CaseAssignmentReportForm;
import ui.supervision.viewassignment.form.ProgramUnitReport;
import ui.supervision.viewassignment.form.SingleCaseReport;
import ui.supervision.viewassignment.form.SuperviseeReport;
import ui.supervision.viewassignment.form.UserReport;
import ui.supervision.viewassignment.form.WorkgroupReport;

/**
 * @author cc_rbhat
 *  
 */
public class CaseAssignmentReportResultAction extends JIMSBaseAction {
	private static String FWD_SETUP = "displayResults";

	private static String FWD_SEARCH = "search";
	
	private static String FWD_VIEW_CASENOTES = "viewSuperviseeCaseNotes";
	
	private static String FWD_NORESULTS = "noResults";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "setup"); 
		keyMap.put("button.link", "viewSuperviseeCasenotes");
	}

	public ActionForward setup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String forwardStr = FWD_SETUP;
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;
		caseAssignmentReportForm.setErrorMsg("");
		StringBuffer name = new StringBuffer();
		IUserInfo user = SecurityUIHelper.getUser();
		name.append(user.getFirstName()).append(" ").append(user.getMiddleName()).append(" ").append(user.getLastName());
		caseAssignmentReportForm.setLogonUserName(name.toString());
		try {
			String searchBy = caseAssignmentReportForm.getSearchBy();
			if (searchBy.equalsIgnoreCase("individualCase")) {
				searchByCase(caseAssignmentReportForm);
			} else if (searchBy.equalsIgnoreCase("programunit")) {
				searchByProgramUnit(caseAssignmentReportForm);
			} else if (searchBy.equalsIgnoreCase("supervisee")) {
				searchBySupervisee(caseAssignmentReportForm);
			} else if (searchBy.equalsIgnoreCase("user")) {
				searchByUser(caseAssignmentReportForm);
			} else if (searchBy.equalsIgnoreCase("workgroup")) {
				searchByWorkgroup(caseAssignmentReportForm);
			}
		} catch (IllegalArgumentException e) {
        	sendToErrorPage(request, "error.max.limit.exceeded");		
		}
		if (caseAssignmentReportForm.getSearchResults() == null || caseAssignmentReportForm.getSearchResults().isEmpty()){
//			ActionErrors errors = new ActionErrors();
//			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("No Search Results found"));
//			saveErrors(request, errors);
			caseAssignmentReportForm.setErrorMsg("No Search Results found");
			forwardStr = FWD_NORESULTS;
		}	
		return mapping.findForward(forwardStr);
	}

	public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;

		return mapping.findForward(FWD_SEARCH);
	}

	public ActionForward viewSuperviseeCasenotes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CaseAssignmentReportForm caseAssignmentReportForm = (CaseAssignmentReportForm) form;
		String defendantId = caseAssignmentReportForm.getSuperviseeReport().getDefendantId();
		String supervisionPeriodId = getActiveSupervisionPeriodId(defendantId);
		if (supervisionPeriodId == null) {
			sendToErrorPage(request, "error.invalidSupPeriod");
		}		
		ActionForward fwd = mapping.findForward(FWD_VIEW_CASENOTES);		
		StringBuffer path = new StringBuffer();
		path.append(fwd.getPath());
		path.append("&selectedValue=");
		path.append(defendantId);
		path.append("&supervisionPeriodId=");
		path.append(supervisionPeriodId);
		path.append("&supervisionPeriod=");
		path.append("A");		
		return new ActionForward(path.toString());
	}
	
	private void searchByCase(CaseAssignmentReportForm form) {
		SingleCaseReport singleCaseReport = form.getSingleCaseReport();
		CaseAssignmentReportEvent requestEvent = new CaseAssignmentReportEvent();
		requestEvent.setSearchType(CaseloadConstants.FILTER_BY_CASE);
		requestEvent.setCriminalCaseId(singleCaseReport.getCriminalCaseId());
		requestEvent.setCourtDivisionIndicator(singleCaseReport.getCourtDivisionIndicator());

		getSearchResults(singleCaseReport, requestEvent);
	}

	private void searchByProgramUnit(CaseAssignmentReportForm form) {
		ProgramUnitReport programUnitReport = form.getProgramUnitReport();
		CaseAssignmentReportEvent requestEvent = new CaseAssignmentReportEvent();
		requestEvent.setSearchType(CaseloadConstants.FILTER_BY_PROGRAMUNIT);
		requestEvent.setAssignmentStartDate(DateUtil.stringToDate(programUnitReport.getAssignmentStartDate(),
				"MM/dd/yyyy"));
		requestEvent
				.setAssignmentEndDate(DateUtil.stringToDate(programUnitReport.getAssignmentEndDate(), "MM/dd/yyyy"));
		requestEvent.setProgramUnitIds(Arrays.asList(programUnitReport.getSelectedProgramUnits()));

		getSearchResults(programUnitReport, requestEvent);
	}

	private void searchBySupervisee(CaseAssignmentReportForm form) {
		SuperviseeReport superviseeReport = form.getSuperviseeReport();
		CaseAssignmentReportEvent requestEvent = new CaseAssignmentReportEvent();
		requestEvent.setSearchType(CaseloadConstants.FILTER_BY_DEFENDANT);
		requestEvent.setDefendantId(superviseeReport.getDefendantId());
		requestEvent.setAssignmentStartDate(DateUtil.stringToDate(superviseeReport.getAssignmentStartDate(), "MM/dd/yyyy"));
		requestEvent.setAssignmentEndDate(DateUtil.stringToDate(superviseeReport.getAssignmentEndDate(), "MM/dd/yyyy"));

		getSearchResults(superviseeReport, requestEvent);
	}

	private void searchByUser(CaseAssignmentReportForm form) {
		UserReport userReport = form.getUserReport();
		CaseAssignmentReportEvent requestEvent = new CaseAssignmentReportEvent();
		requestEvent.setSearchType(CaseloadConstants.FILTER_BY_USER);
		requestEvent.setAcknowledgeUserId(userReport.getUserId());
		requestEvent.setAssignmentStartDate(DateUtil.stringToDate(userReport.getAssignmentStartDate(), "MM/dd/yyyy"));
		requestEvent.setAssignmentEndDate(DateUtil.stringToDate(userReport.getAssignmentEndDate(), "MM/dd/yyyy"));
		List selectedProgramUnitsNames = userReport.getSelectedProgramUnitsNames();
		if (selectedProgramUnitsNames != null && selectedProgramUnitsNames.size() > 0) {
		    requestEvent.setProgramUnitIds(Arrays.asList(userReport.getSelectedProgramUnits()));
		} 
		getSearchResults(userReport, requestEvent);
	}

	private void searchByWorkgroup(CaseAssignmentReportForm form) {
		WorkgroupReport workgroupReport = form.getWorkgroupReport();
		CaseAssignmentReportEvent requestEvent = new CaseAssignmentReportEvent();
		requestEvent.setSearchType(CaseloadConstants.FILTER_BY_WORKGROUP);
		requestEvent.setWorkgroupId(workgroupReport.getSelectedWorkgroupId());
		requestEvent.setAssignmentStartDate(DateUtil.stringToDate(workgroupReport.getAssignmentStartDate(),
				"MM/dd/yyyy"));
		requestEvent.setAssignmentEndDate(DateUtil.stringToDate(workgroupReport.getAssignmentEndDate(), "MM/dd/yyyy"));
		List selectedProgramUnitsNames = workgroupReport.getSelectedProgramUnitsNames();
		if (selectedProgramUnitsNames != null && selectedProgramUnitsNames.size() > 0) {
		    requestEvent.setProgramUnitIds(Arrays.asList(workgroupReport.getSelectedProgramUnits()));
		} 
		getSearchResults(workgroupReport, requestEvent);

	}

	private void getSearchResults(BaseCaseAssignmentReport report, CaseAssignmentReportEvent requestEvent) {
		CaseAssignmentReportResponseEvent responseEvent = (CaseAssignmentReportResponseEvent) MessageUtil.postRequest(
				requestEvent, CaseAssignmentReportResponseEvent.class);
		if (responseEvent.isResultSetExceedMaxLimit()) {
			//The inputs from user are illegal in the sense that they correspond
			//to more than 2000 records.
			throw new IllegalArgumentException();
		} else {
			List searchResults = responseEvent.getResults();
			int noOfCases = 0;
			int len = 0;
			String courtNum = "";
			int courtNumInt = 0;
			for (Iterator iterator = searchResults.iterator(); iterator.hasNext();) {
				SuperviseeTO supervisee = (SuperviseeTO) iterator.next();
				noOfCases += supervisee.getActiveCases().size();
				for (int x=0; x <supervisee.getActiveCases().size(); x++){
					CaseAssignmentTO cato = (CaseAssignmentTO) supervisee.getActiveCases().get(x);
					if (cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equalsIgnoreCase("")) {
						cato.setDisplayCaseNum(cato.getCriminalCaseId().substring(3, cato.getCriminalCaseId().length()));
					}
					if (cato.getCourtId() != null && !cato.getCourtId().equalsIgnoreCase("")) {
						courtNum = cato.getCourtId();
						if(courtNum.length() > 3 ) {
							String crtIds [] = courtNum.split(" ");
							if(crtIds != null && crtIds.length > 1){
								courtNum = crtIds[1];
							}
							try {
								courtNumInt = new Integer(courtNum);
								courtNum = "" + courtNumInt;
								while(courtNum.length() < 3){
									courtNum = "0" + courtNum;
								}
							}catch(NumberFormatException e){
		//do nothing, court number is not integer
							}	
						}
						cato.setDisplayCourtId(courtNum);
					}					
					
				}
				supervisee.sortCasesByProgramUnitAssignmentDate();
			}
			Collections.sort(searchResults);
			report.setSearchResults(searchResults);
			report.setNoOfCases(String.valueOf(noOfCases));
		}
	}

	private String getActiveSupervisionPeriodId(String defendantId) {
		boolean notesForActiveSupervisionPeriod = true;
		String supervisionPeriodId = null;
		CompositeResponse response = UICommonSupervisionHelper.getSuperviseeInfo(SecurityUIHelper.getUserAgencyId(), defendantId, 
				notesForActiveSupervisionPeriod, supervisionPeriodId);
		
		Collection results = MessageUtil.compositeToCollection(response,
				SuperviseeResponseEvent.class);
		ISupervisee superviseeInfo = null;
		if (results != null && !results.isEmpty()) {
			superviseeInfo = (ISupervisee) results.iterator().next();
			Collection cases = superviseeInfo.getCases();
			if (cases != null && !cases.isEmpty()) {
				CasenoteCaseTO casenoteCaseTO = (CasenoteCaseTO) cases.iterator().next();
				supervisionPeriodId = casenoteCaseTO.getSupervisionPeriodId();
			}
		}
		return supervisionPeriodId;
	}

}