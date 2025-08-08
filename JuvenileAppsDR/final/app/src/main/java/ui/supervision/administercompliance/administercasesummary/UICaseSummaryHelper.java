/*
 * Created on Jan 2, 2008
 *
 */
package ui.supervision.administercompliance.administercasesummary;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.administercompliance.GetNCCommunityServicesEvent;
import messaging.administercompliance.GetNCCourtActivitiesEvent;
import messaging.administercompliance.GetNCEmploymentsEvent;
import messaging.administercompliance.GetNCFAS1FeesEvent;
import messaging.administercompliance.GetNCFeesEvent;
import messaging.administercompliance.GetNCLawViolationsEvent;
import messaging.administercompliance.GetNCPositiveUrinalysisEvent;
import messaging.administercompliance.GetNCReportingEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.GetNCResponseDetailsEvent;
import messaging.administercompliance.GetNCTreatmentIssuesEvent;
import messaging.administercompliance.GetReasonForTransferCodesEvent;
import messaging.administercompliance.UpdateNCResponseStatusEvent;
import messaging.administercompliance.reply.NCCommentResponseEvent;
import messaging.administercompliance.reply.NCCommunityServiceResponseEvent;
import messaging.administercompliance.reply.NCEmploymentResponseEvent;
import messaging.administercompliance.reply.NCFeeResponseEvent;
import messaging.administercompliance.reply.NCLastKnownAddressResponseEvent;
import messaging.administercompliance.reply.NCLawViolationResponseEvent;
import messaging.administercompliance.reply.NCPreviousCourtActivityResponseEvent;
import messaging.administercompliance.reply.NCReasonForTransferResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CaseloadConstants;
import naming.CaseloadControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;

import ui.common.CodeHelper;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;

/**
 * @author jjose
 * 
 */
public class UICaseSummaryHelper {
	public static final String FILEEXT = ".pdf";

	/**
	 * @param CaseSummaryForm
	 */
	public static void getReasonForTransferCreateInfo(CaseSummaryForm csForm){
		List theList = csForm.getReasonForTransferList();
		if (csForm.getCurrentReasonForTransferList() != null && csForm.getCurrentReasonForTransferList().isEmpty()){
			csForm.setCreate1Comments("");
			for (int x = 0; x<csForm.getReasonForTransferList().size(); x++){
				CodeResponseEvent t3 = (CodeResponseEvent) csForm.getReasonForTransferList().get(x);
				t3.setCodeId("");
			}
		}else {
			csForm.setCreate1Comments(csForm.getCurrentReasonForTransferComments());
			for (int x = 0; x<csForm.getCurrentReasonForTransferList().size(); x++){
	        	CodeResponseEvent cre = (CodeResponseEvent) csForm.getCurrentReasonForTransferList().get(x);
	        	for (int y=0; y < theList.size(); y++){
	        		CodeResponseEvent tl = (CodeResponseEvent) theList.get(y);
	        		if (cre.getCode().equals(tl.getCode())){
	        			tl.setVisible(true);
	        			break;
	        		}
	        	}
			}
		}
		String extValue = csForm.getIsExtended();
		if ( "".equals(extValue) || extValue == null ){
			csForm.setIsExtended("NO");
		}
		csForm.setCreate1ElementsList(UICaseSummaryHelper.sortReasonForTransfer(theList)); 
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getReasonForTransferUpdateInfo(CaseSummaryForm csForm) {
		List theList = new ArrayList();
		// reset codelist visible value in prep for display
		for (int x = 0; x < csForm.getReasonForTransferList().size(); x++) {
			CodeResponseEvent cre = (CodeResponseEvent) csForm
					.getReasonForTransferList().get(x);
			cre.setVisible(false);
		}
		String comments = "";
		// add code for response event
		if ((csForm.getCurrentReasonForTransferList() == null || csForm
				.getCurrentReasonForTransferList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setRequestType(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER);
			event.setNcResponseId(csForm.getViolationReportId());

			CompositeResponse response = MessageUtil.postRequest(event);

			theList = MessageUtil.compositeToList(response,
					NCReasonForTransferResponseEvent.class);
			Collection unFoundList = new ArrayList();
			for (int x = 0; x < theList.size(); x++) {
				NCReasonForTransferResponseEvent nftre = (NCReasonForTransferResponseEvent) theList
						.get(x);
				boolean isFlag = false;
				for (int y = 0; y < csForm.getReasonForTransferList().size(); y++) {
					CodeResponseEvent tl = (CodeResponseEvent) csForm
							.getReasonForTransferList().get(y);
					if (nftre.getReasonForTransferCodeId().equalsIgnoreCase(
							tl.getCode())) {
						tl.setVisible(true);
						isFlag = true;
					}
				}
				if (!isFlag) {
					CodeResponseEvent t2 = new CodeResponseEvent();
					t2.setCode(nftre.getReasonForTransferCodeId());
					t2.setDescription(nftre.getReasonForTransferCodeDesc());
					t2.setCodeId(nftre.getReasonForTransferId());
					t2.setVisible(true);
					unFoundList.add(t2);
				}
			}
			theList = csForm.getReasonForTransferList(); // should contain
															// selected values
			theList.addAll(unFoundList);

			for (int x = 0; x < csForm.getReasonForTransferList().size(); x++) {
				CodeResponseEvent t3 = (CodeResponseEvent) csForm
						.getReasonForTransferList().get(x);
				if (!t3.isVisible()) {
					t3.setCodeId("");
				}
			}
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null) {
				comments = resp.getComments();
			} else {
				comments = csForm.getCurrentReasonForTransferComments();
			}
		} else {
			comments = csForm.getCurrentReasonForTransferComments();
			if (csForm.getCurrentReasonForTransferList().isEmpty()) {
				theList = csForm.getReasonForTransferList(); // should contain
																// NO selected
																// values
			} else {
				for (int c = 0; c < csForm.getCurrentReasonForTransferList()
						.size(); c++) {
					CodeResponseEvent ce = (CodeResponseEvent) csForm
							.getCurrentReasonForTransferList().get(c);
					for (int r = 0; r < csForm.getReasonForTransferList()
							.size(); r++) {
						CodeResponseEvent re = (CodeResponseEvent) csForm
								.getReasonForTransferList().get(r);
						if (ce.getCode().equalsIgnoreCase(re.getCode())) {
							re.setVisible(true);
						}
					}
				}
				theList = csForm.getReasonForTransferList();
			}
		}
		String extValue = csForm.getIsExtended();
		if ( "".equals(extValue) || extValue == null ){
			csForm.setIsExtended("NO");
		}
		
		csForm.setCreate1ElementsList(UICaseSummaryHelper
				.sortReasonForTransfer(theList));
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getLawViolationCreateInfo(CaseSummaryForm csForm) {
		csForm.clearLawViolationsAdds();
		List theList = new ArrayList();
		if ((csForm.getCurrentLawViolationsList() == null || csForm
				.getCurrentLawViolationsList().isEmpty())) {
			csForm.setCreate1Comments("");
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_LAW_VIOLATION);

			GetNCLawViolationsEvent reqEvent = new GetNCLawViolationsEvent();
			reqEvent.setActivationDate(csForm.getActivationDate());
			reqEvent.setDefendantId(csForm.getSuperviseeId());

			event.addRequest(reqEvent);
			theList = MessageUtil.postRequestListFilter(event,
					NCLawViolationResponseEvent.class);
		} else {
			theList = csForm.getCurrentLawViolationsList();
			csForm.setCreate1Comments(csForm.getCurrentLawViolationsComments());
		}
		csForm.setCreate1ElementsList(theList);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getLawViolationUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearLawViolationsAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((csForm.getCurrentLawViolationsList() != null && csForm
				.getCurrentLawViolationsList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_LAW_VIOLATION);
			event.setNcResponseId(csForm.getViolationReportId());
			event.setActivationDate(csForm.getActivationDate());

			GetNCLawViolationsEvent reqEvent = new GetNCLawViolationsEvent();
			reqEvent.setDefendantId(csForm.getSuperviseeId());
			reqEvent.setActivationDate(csForm.getActivationDate());

			event.addRequest(reqEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					NCLawViolationResponseEvent.class);
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null) {
				comments = resp.getComments();
			} else {
				comments = csForm.getCurrentLawViolationsComments();
			}
		} else {
			theList = csForm.getCurrentLawViolationsList();
			comments = csForm.getCurrentLawViolationsComments();
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getFeeHistoryCreateInfo(CaseSummaryForm csForm) {
		csForm.clearFeeHistoryAdds();
		List theList = new ArrayList();
		if ((csForm.getCurrentFeeHistoryList() == null || csForm
				.getCurrentFeeHistoryList().isEmpty())) {
			csForm.setCreate1Comments("");
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_DELINQUENT_FEE);

			GetNCFeesEvent ncReqEvent = new GetNCFeesEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			event.addRequest(ncReqEvent);

			GetNCFAS1FeesEvent ncFas1FeesEvent = new GetNCFAS1FeesEvent();
			ncFas1FeesEvent.setSpn(csForm.getSuperviseeId());
			ncFas1FeesEvent.setCaseId(csForm.getCaseNum());
			ncFas1FeesEvent.setCdi(csForm.getCdi());
			event.addRequest(ncFas1FeesEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					NCFeeResponseEvent.class);
			if (theList != null && !theList.isEmpty()) {
				for (int f = 0; f < theList.size(); f++) {
					NCFeeResponseEvent fre = (NCFeeResponseEvent) theList
							.get(f);
					if ("0.00".equals(fre.getAmountOrdered())) {
						theList.remove(f);
					}
				}
			}
		} else {
			theList = csForm.getCurrentFeeHistoryList();
			csForm.setCreate1Comments(csForm.getCurrentFeeHistoryComments());
		}
		csForm.setCreate1ElementsList(theList);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getFeeHistoryUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearFeeHistoryAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((csForm.getCurrentFeeHistoryList() == null || csForm
				.getCurrentFeeHistoryList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_DELINQUENT_FEE);
			event.setNcResponseId(csForm.getViolationReportId());

			GetNCFeesEvent ncReqEvent = new GetNCFeesEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			event.addRequest(ncReqEvent);

			GetNCFAS1FeesEvent ncFas1FeesEvent = new GetNCFAS1FeesEvent();
			ncFas1FeesEvent.setSpn(csForm.getSuperviseeId());
			ncFas1FeesEvent.setCaseId(csForm.getCaseNum());
			ncFas1FeesEvent.setCdi(csForm.getCdi());
			event.addRequest(ncFas1FeesEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					NCFeeResponseEvent.class);
			if (theList != null && !theList.isEmpty()) {
				for (int f = 0; f < theList.size(); f++) {
					NCFeeResponseEvent fre = (NCFeeResponseEvent) theList
							.get(f);
					if ("0.00".equals(fre.getAmountOrdered())) {
						theList.remove(f);
					}
				}
			}
			csForm.setCreate1ElementsList(theList);
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null) {
				comments = resp.getComments();
			} else {
				comments = csForm.getCurrentFeeHistoryComments();
			}
		} else {
			theList = csForm.getCurrentFeeHistoryList();
			comments = csForm.getCurrentFeeHistoryComments();
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getReportingHistoryCreateInfo(CaseSummaryForm csForm) {
		csForm.clearReportingHistoryAdds();
		// csForm.clearReportingHistoryAddressInfo();
		List theList = new ArrayList();
		String comments = "";
		csForm.setShowAddress(false);
		if ((csForm.getCurrentReportingHistoryList() != null && csForm
				.getCurrentReportingHistoryList().isEmpty())) {
			comments = csForm.getCurrentReportingHistoryComments();
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
			GetNCReportingEvent ncReqEvent = new GetNCReportingEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			ncReqEvent.setDefendantId(csForm.getSuperviseeId());
			event.addRequest(ncReqEvent);
			CompositeResponse response = MessageUtil.postRequest(event);
			Collection reports = MessageUtil.compositeToCollection(response,
					NonComplianceEventResponseEvent.class);
			theList = new ArrayList(reports);
			if (theList != null && !theList.isEmpty()) {
				NCLastKnownAddressResponseEvent addressResp = (NCLastKnownAddressResponseEvent) MessageUtil
						.filterComposite(response,
								NCLastKnownAddressResponseEvent.class);
				if (addressResp != null) {
					csForm.setLastContactDate(addressResp.getLastContactDate());
					csForm.setAddressNumber(addressResp.getStreetNumber());
					csForm.setAddressName(addressResp.getStreetName());
					csForm.setAddressCity(addressResp.getCity());
					csForm.setAddressState(addressResp.getState());
					csForm.setAddressStateId(addressResp.getStateId());
					csForm.setAddressZipCode(addressResp.getZip());
					csForm.setAddressType(addressResp.getAddressType());
					csForm.setAddressTypeId(addressResp.getAddressTypeId());
					csForm.setShowAddress(true);
				}
			}
		} else {
			theList = csForm.getCurrentReportingHistoryList();
			if (theList != null && !theList.isEmpty()) {
				StringBuffer addressInfo = new StringBuffer();
				addressInfo.append((csForm.getAddressNumber() == null) ? ""
						: csForm.getAddressNumber());
				addressInfo.append((csForm.getAddressName() == null) ? ""
						: csForm.getAddressName());
				addressInfo.append((csForm.getAddressCity() == null) ? ""
						: csForm.getAddressCity());
				addressInfo.append((csForm.getAddressState() == null) ? ""
						: csForm.getAddressState());
				addressInfo.append((csForm.getAddressZipCode() == null) ? ""
						: csForm.getAddressZipCode());
				addressInfo.append((csForm.getLastContactDate() == null) ? ""
						: csForm.getLastContactDate());
				addressInfo.append((csForm.getAddressType() == null) ? ""
						: csForm.getAddressType());
				if (addressInfo.toString().length() > 0) {
					csForm.setShowAddress(true);
				}
			}
			comments = csForm.getCurrentReportingHistoryComments();
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getReportingHistoryUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearReportingHistoryAdds();
		// csForm.clearReportingHistoryAddressInfo();
		List theList = new ArrayList();
		String comments = "";
		if ((csForm.getCurrentReportingHistoryList() == null || csForm
				.getCurrentReportingHistoryList().isEmpty())) {
			csForm.setShowAddress(false);
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
			event.setNcResponseId(csForm.getViolationReportId());

			GetNCReportingEvent ncReqEvent = new GetNCReportingEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			ncReqEvent.setDefendantId(csForm.getSuperviseeId());
			event.addRequest(ncReqEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					NonComplianceEventResponseEvent.class);
			csForm.setCreate1ElementsList(theList);
			if (theList != null && !theList.isEmpty()) {
				NCLastKnownAddressResponseEvent addressResp = (NCLastKnownAddressResponseEvent) MessageUtil
						.filterComposite(response,
								NCLastKnownAddressResponseEvent.class);
				if (addressResp != null) {
					csForm.setLastContactDate(addressResp.getLastContactDate());
					csForm.setAddressNumber(addressResp.getStreetNumber());
					csForm.setAddressName(addressResp.getStreetName());
					csForm.setAddressCity(addressResp.getCity());
					csForm.setAddressState(addressResp.getState());
					csForm.setAddressStateId(addressResp.getStateId());
					csForm.setAddressZipCode(addressResp.getZip());
					csForm.setAddressType(addressResp.getAddressType());
					csForm.setAddressTypeId(addressResp.getAddressTypeId());
					csForm.setShowAddress(true);
				}
			}
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null && resp.getComments() != null) {
				comments = resp.getComments();
			} else {
				comments = csForm.getCurrentReportingHistoryComments();
			}
		} else {
			theList = csForm.getCurrentReportingHistoryList();
			if (theList != null && !theList.isEmpty()) {
				StringBuffer addressInfo = new StringBuffer();
				addressInfo.append((csForm.getAddressNumber() == null) ? ""
						: csForm.getAddressNumber());
				addressInfo.append((csForm.getAddressName() == null) ? ""
						: csForm.getAddressName());
				addressInfo.append((csForm.getAddressCity() == null) ? ""
						: csForm.getAddressCity());
				addressInfo.append((csForm.getAddressState() == null) ? ""
						: csForm.getAddressState());
				addressInfo.append((csForm.getAddressZipCode() == null) ? ""
						: csForm.getAddressZipCode());
				addressInfo.append((csForm.getLastContactDate() == null) ? ""
						: csForm.getLastContactDate());
				addressInfo.append((csForm.getAddressType() == null) ? ""
						: csForm.getAddressType());
				if (addressInfo.toString().length() > 0) {
					csForm.setShowAddress(true);
				}
			}
			comments = csForm.getCurrentReportingHistoryComments();
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getEmploymentHistoryCreateInfo(CaseSummaryForm csForm) {
		csForm.clearEmploymentHistoryAdds();
		List theList = new ArrayList();
		String theComments = "";
		if ((csForm.getCurrentEmploymentHistoryList() == null || csForm
				.getCurrentEmploymentHistoryList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
			GetNCEmploymentsEvent ncReqEvent = new GetNCEmploymentsEvent();
			ncReqEvent.setDefandantId(csForm.getSuperviseeId());
			event.addRequest(ncReqEvent);
			CompositeResponse response = MessageUtil.postRequest(event);
			Collection reports = MessageUtil.compositeToCollection(response,
					NCEmploymentResponseEvent.class);
			theList = new ArrayList(reports);
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null && resp.getComments() != null) {
				theComments = resp.getComments();
			}
		} else {
			theList = csForm.getCurrentEmploymentHistoryList();
			theComments = csForm.getCurrentEmploymentHistoryComments();
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(theComments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getEmploymentHistoryUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearEmploymentHistoryAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((csForm.getCurrentEmploymentHistoryList() == null || csForm
				.getCurrentEmploymentHistoryList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
			event.setNcResponseId(csForm.getViolationReportId());

			GetNCEmploymentsEvent ncReqEvent = new GetNCEmploymentsEvent();
			ncReqEvent.setDefandantId(csForm.getSuperviseeId());
			event.addRequest(ncReqEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					NCEmploymentResponseEvent.class);
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null && resp.getComments() != null) {
				comments = resp.getComments();
			}
			if (comments.equals("")
					&& !csForm.getCurrentEmploymentHistoryComments().equals("")) {
				comments = csForm.getCurrentEmploymentHistoryComments();
			}
		} else {
			theList = csForm.getCurrentEmploymentHistoryList();
			comments = csForm.getCurrentEmploymentHistoryComments();
		}
		if (!theList.isEmpty()) {
			for (int t = 0; t < theList.size(); t++) {
				NCEmploymentResponseEvent ncre = (NCEmploymentResponseEvent) theList
						.get(t);
				if ((ncre.getStatusDesc() == null || ncre.getStatusDesc()
						.equals("") && ncre.getStatusId() != null)) {
					for (int x = 0; x < csForm.getEmploymentStatusList().size(); x++) {
						CodeResponseEvent cre = (CodeResponseEvent) csForm
								.getEmploymentStatusList().get(x);
						if (cre.getCodeId().equals(ncre.getStatusId())) {
							ncre.setStatusDesc(cre.getDescription());
							break;
						}
					}
				}
			}
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getPreviousCourtActivityCreateInfo(CaseSummaryForm csForm) {
		csForm.clearViolationReportsAdds();
		csForm.clearMotionsAdds();
		csForm.clearOthersAdds();
		List theVList = new ArrayList();
		List theMList = new ArrayList();
		List theOList = new ArrayList();
		String vComments = "";
		String mComments = "";
		String oComments = "";
		csForm.setShowVRAddFields(false);
		csForm.setShowMotionAddFields(false);
		csForm.setCursorPosition("");
		// add code for response event
		if ((csForm.getCurrentCourtActivityVRList() == null || csForm
				.getCurrentCourtActivityVRList().isEmpty())
				&& (csForm.getCurrentMotionsList() == null || csForm
						.getCurrentMotionsList().isEmpty())
				&& (csForm.getCurrentOthersList() == null || csForm
						.getCurrentOthersList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
			GetNCCourtActivitiesEvent ncReqEvent = new GetNCCourtActivitiesEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());


			event.addRequest(ncReqEvent);
			CompositeResponse response = MessageUtil.postRequest(event);

			Collection reports = MessageUtil.compositeToCollection(response,
					NCPreviousCourtActivityResponseEvent.class);
			if (reports != null) {
				Iterator rptIter = reports.iterator();
				while (rptIter.hasNext()) {
					NCPreviousCourtActivityResponseEvent prare = (NCPreviousCourtActivityResponseEvent) rptIter
							.next();
					if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION
							.equalsIgnoreCase(prare.getSubType())) {
						theVList.add(prare);
					}
					if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION
							.equalsIgnoreCase(prare.getSubType())) {
						theMList.add(prare);
					}
					if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER
							.equalsIgnoreCase(prare.getSubType())) {
						theOList.add(prare);
					}
				}
			}
		} else {
			theVList = csForm.getCurrentCourtActivityVRList();
			theMList = csForm.getCurrentMotionsList();
			theOList = csForm.getCurrentOthersList();
			vComments = csForm.getCurrentCourtActivityVRComments();
			mComments = csForm.getCurrentMotionsComments();
			oComments = csForm.getCurrentOthersComments();
		}
		csForm.setCreate1ElementsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(theVList));
		csForm.setCreate2ElementsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(theMList));
		csForm.setCreate3ElementsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(theOList));
		csForm.setCreate1Comments(vComments);
		csForm.setCreate2Comments(mComments);
		csForm.setCreate3Comments(oComments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getPreviousCourtActivityUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearViolationReportsAdds();
		csForm.clearMotionsAdds();
		csForm.clearOthersAdds();
		List theVList = new ArrayList();
		List theMList = new ArrayList();
		List theOList = new ArrayList();
		String vComments = "";
		String mComments = "";
		String oComments = "";
		csForm.setShowVRAddFields(false);
		csForm.setShowMotionAddFields(false);
		csForm.setCursorPosition("");
		if (csForm.getCurrentCourtActivityVRList() == null
				|| csForm.getCurrentCourtActivityVRList().isEmpty()
				|| csForm.getCurrentMotionsList() == null
				|| csForm.getCurrentMotionsList().isEmpty()
				|| csForm.getCurrentOthersList() == null
				|| csForm.getCurrentOthersList().isEmpty()) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
			event.setNcResponseId(csForm.getViolationReportId());

			GetNCCourtActivitiesEvent ncReqEvent = new GetNCCourtActivitiesEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			event.addRequest(ncReqEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			List list = MessageUtil.compositeToList(response,
					NCPreviousCourtActivityResponseEvent.class);
			List commentList = MessageUtil.compositeToList(response,
					NCResponseResponseEvent.class);
			int len = list.size();
			for (int x = 0; x < len; x++) {
				NCPreviousCourtActivityResponseEvent ncpe = (NCPreviousCourtActivityResponseEvent) list
						.get(x);
				if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION
						.equalsIgnoreCase(ncpe.getSubType())) {
					theVList.add(ncpe);
				}
				if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION
						.equalsIgnoreCase(ncpe.getSubType())) {
					theMList.add(ncpe);
				}
				if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER
						.equalsIgnoreCase(ncpe.getSubType())) {
					theOList.add(ncpe);
				}
			}
			len = commentList.size();
			for (int x = 0; x < len; x++) {
				NCResponseResponseEvent ncpe = (NCResponseResponseEvent) commentList
						.get(x);
				if (ncpe.getCommentType()
						.equalsIgnoreCase(
								ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION)) {
					vComments = ncpe.getComments();
				} else if (ncpe
						.getCommentType()
						.equalsIgnoreCase(
								ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_MOTION)) {
					mComments = ncpe.getComments();
				} else {
					oComments = ncpe.getComments();
				}
			}
		}
		if (csForm.getCurrentCourtActivityVRList() != null
				&& !csForm.getCurrentCourtActivityVRList().isEmpty()) {
			theVList = csForm.getCurrentCourtActivityVRList();
		}
		if (csForm.getCurrentMotionsList() != null
				&& !csForm.getCurrentMotionsList().isEmpty()) {
			theMList = csForm.getCurrentMotionsList();
		}
		if (csForm.getCurrentOthersList() != null
				&& !csForm.getCurrentOthersList().isEmpty()) {
			theOList = csForm.getCurrentOthersList();
		}
		if (!csForm.getCurrentCourtActivityVRComments().equals("")) {
			vComments = csForm.getCurrentCourtActivityVRComments();
		}
		if (!csForm.getCurrentMotionsComments().equals("")) {
			mComments = csForm.getCurrentMotionsComments();
		}
		if (!csForm.getCurrentOthersComments().equals("")) {
			oComments = csForm.getCurrentOthersComments();
		}
		csForm.setCreate1ElementsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(theVList));
		csForm.setCreate2ElementsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(theMList));
		csForm.setCreate3ElementsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(theOList));
		csForm.setCreate1Comments(vComments);
		csForm.setCreate2Comments(mComments);
		csForm.setCreate3Comments(oComments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getTreatmentIssuesCreateInfo(CaseSummaryForm csForm) {
		csForm.clearTreatmentIssuesAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((csForm.getCurrentTreatmentIssuesList() == null || csForm
				.getCurrentTreatmentIssuesList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
			GetNCTreatmentIssuesEvent ncReqEvent = new GetNCTreatmentIssuesEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			event.addRequest(ncReqEvent);
			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					CSProgramReferralResponseEvent.class);
		} else {
			theList = csForm.getCurrentTreatmentIssuesList();
			comments = csForm.getCurrentTreatmentIssuesComments();
		}
		csForm.setCreate1ElementsList(loadDischargeReasonDescriptions(theList,
				csForm.getDischargeReasons()));
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getTreatmentIssuesUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearTreatmentIssuesAdds();
		List theList = new ArrayList();
		String comments = "";
		if (csForm.getCurrentTreatmentIssuesList() == null
				|| csForm.getCurrentTreatmentIssuesList().isEmpty()) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
			GetNCTreatmentIssuesEvent ncReqEvent = new GetNCTreatmentIssuesEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			event.addRequest(ncReqEvent);
			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					CSProgramReferralResponseEvent.class);
		} else {
			theList = csForm.getCurrentTreatmentIssuesList();
			comments = csForm.getCurrentTreatmentIssuesComments();
		}
		csForm.setCreate1ElementsList(loadDischargeReasonDescriptions(theList,
				csForm.getDischargeReasons()));
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getCommunityServiceCreateInfo(CaseSummaryForm csForm) {
		if ((csForm.getCurrentHoursOrdered() != null
				&& csForm.getCurrentHoursOrdered().equals("")
				&& csForm.getCurrentHoursCompleted() != null
				&& csForm.getCurrentHoursCompleted().equals("")
				&& csForm.getCurrentCommunityServiceComments() != null && csForm
				.getCurrentCommunityServiceComments().equals(""))) {
			csForm.setCreate1Comments("");
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE);
			GetNCCommunityServicesEvent ncReqEvent = new GetNCCommunityServicesEvent();
			ncReqEvent.setCdi(csForm.getCdi());
			ncReqEvent.setCaseId(csForm.getCaseNum());
			event.addRequest(ncReqEvent);
			List theList = MessageUtil.postRequestListFilter(event,
					NCCommunityServiceResponseEvent.class);
			for (int x = 0; x < theList.size(); x++) {
				NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent) theList
						.get(x);
				csForm.setHoursOrdered(csre.getHoursOrdered());
				csForm.setHoursCompleted(csre.getHoursCompleted());
				break;
			}
		} else {
			csForm.setHoursOrdered(csForm.getCurrentHoursOrdered());
			csForm.setHoursCompleted(csForm.getCurrentHoursCompleted());
			csForm.setCreate1Comments(csForm
					.getCurrentCommunityServiceComments());
		}
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getCommunityServiceUpdateInfo(CaseSummaryForm csForm) {
		if ((csForm.getCurrentHoursOrdered() != null
				&& csForm.getCurrentHoursOrdered().equals("")
				&& csForm.getCurrentHoursCompleted() != null
				&& csForm.getCurrentHoursCompleted().equals("")
				&& csForm.getCurrentCommunityServiceComments() != null && csForm
				.getCurrentCommunityServiceComments().equals(""))) {
			csForm.setCreate1Comments("");
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE);
			GetNCCommunityServicesEvent ncReqEvent = new GetNCCommunityServicesEvent();
			ncReqEvent.setCdi(csForm.getCdi());
			ncReqEvent.setCaseId(csForm.getCaseNum());
			event.addRequest(ncReqEvent);
			List theList = MessageUtil.postRequestListFilter(event,
					NCCommunityServiceResponseEvent.class);
			for (int x = 0; x < theList.size(); x++) {
				NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent) theList
						.get(x);
				csForm.setHoursOrdered(csre.getHoursOrdered());
				csForm.setHoursCompleted(csre.getHoursCompleted());
				break;
			}
		} else {
			csForm.setHoursOrdered(csForm.getCurrentHoursOrdered());
			csForm.setHoursCompleted(csForm.getCurrentHoursCompleted());
			csForm.setCreate1Comments(csForm
					.getCurrentCommunityServiceComments());
		}
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getPositiveUrinalysisCreateInfo(CaseSummaryForm csForm) {
		csForm.clearPositiveUrinalysisAdds();
		List theList = new ArrayList();
		String totalSA = "";
		String comments = "";
		if ((csForm.getCurrentPositiveUrinalysisList() == null || csForm
				.getCurrentPositiveUrinalysisList().isEmpty())) {
			csForm.setCreate1Comments("");
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_POSITIVE_UA);
			GetNCPositiveUrinalysisEvent ncReqEvent = new GetNCPositiveUrinalysisEvent();

			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			ncReqEvent.setDefendantId(csForm.getSuperviseeId());
			event.addRequest(ncReqEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			Collection reports = MessageUtil.compositeToCollection(response,
					NonComplianceEventResponseEvent.class);
			theList = new ArrayList(reports);
			if (theList == null || theList.isEmpty()) {
				if (!csForm.getCurrentPositiveUrinalysisComments().equals(null)
						&& !csForm.getCurrentPositiveUrinalysisComments()
								.equals("")) {
					comments = csForm.getCurrentPositiveUrinalysisComments();
				}
			}
			// need to get this total value from response -- violationReport
			// record
			totalSA = "";
		} else {
			theList = csForm.getCurrentPositiveUrinalysisList();
			totalSA = csForm.getCurrentTotalSpecimensAnalyzed();
			comments = csForm.getCurrentPositiveUrinalysisComments();
		}
		csForm.setTotalSpecimensAnalyzed(totalSA);
		csForm.setCreate1ElementsList(theList);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getPositiveUrinalysisUpdateInfo(CaseSummaryForm csForm) {
		csForm.clearPositiveUrinalysisAdds();
		List theList = new ArrayList();
		String totalSA = "";
		String comments = "";
		if ((csForm.getCurrentPositiveUrinalysisList() == null || csForm
				.getCurrentPositiveUrinalysisList().isEmpty())) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_POSITIVE_UA);
			event.setNcResponseId(csForm.getViolationReportId());

			GetNCPositiveUrinalysisEvent ncReqEvent = new GetNCPositiveUrinalysisEvent();
			ncReqEvent.setCaseId(csForm.getCaseNum());
			ncReqEvent.setCdi(csForm.getCdi());
			ncReqEvent.setDefendantId(csForm.getSuperviseeId());
			event.addRequest(ncReqEvent);

			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response,
					NonComplianceEventResponseEvent.class);
			// need to get this total value
			csForm.setTotalSpecimensAnalyzed("");
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null) {
				comments = resp.getComments();
			} else {
				comments = csForm.getCurrentPositiveUrinalysisComments();
			}
		} else {
			theList = csForm.getCurrentPositiveUrinalysisList();
			comments = csForm.getCurrentPositiveUrinalysisComments();
			totalSA = csForm.getCurrentTotalSpecimensAnalyzed();
		}
		csForm.setCreate1ElementsList(theList);
		csForm.setTotalSpecimensAnalyzed(totalSA);
		csForm.setCreate1Comments(comments);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getRecommendationsCreateInfo(CaseSummaryForm csForm) {
		List theList = csForm.getActiveSuggestedCourtActions();
		if (csForm.getCurrentRecommendations() == null
				|| csForm.getCurrentRecommendations().equals("")) {
			csForm.setCreate1Comments("");
			for (int x = 0; x < csForm.getSuggestedCourtActions().size(); x++) {
				CodeResponseEvent t3 = (CodeResponseEvent) csForm
						.getSuggestedCourtActions().get(x);
				t3.setCodeId("");
			}
		} else {
			csForm.setCreate1Comments(csForm.getCurrentRecommendations());
			int len = csForm.getCurrentSuggestedCourtActionsList().size();
			int listSize = theList.size();
			for (int x = 0; x < len; x++) {
				CodeResponseEvent csca = (CodeResponseEvent) csForm
						.getCurrentSuggestedCourtActionsList().get(x);
				for (int y = 0; y < listSize; y++) {
					CodeResponseEvent tl = (CodeResponseEvent) theList.get(y);
					if (csca.getCode().equals(tl.getCode())) {
						tl.setVisible(true);
						break;
					}
				}
			}
		}
		csForm.setCreate1ElementsList(theList);
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void getRecommendationsUpdateInfo(CaseSummaryForm csForm) {
		String recommendations = "";
		List theList = new ArrayList();
		List activeList = new ArrayList();
		List tempList = csForm.getActiveSuggestedCourtActions();

		// reset codelist visible value in prep for display
		for (int x = 0; x < tempList.size(); x++) {
			CodeResponseEvent cre = (CodeResponseEvent) tempList.get(x);
			cre.setVisible(false);
			activeList.add(x, cre);
		}
		csForm.setActiveSuggestedCourtActions(new ArrayList(activeList));

		if (csForm.getCurrentRecommendations() == null
				|| csForm.getCurrentRecommendations().equals("")) {
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory
					.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setRequestType(ViolationReportConstants.REQUEST_RECOMMENDATION);
			event.setNcResponseId(csForm.getViolationReportId());

			CompositeResponse response = MessageUtil.postRequest(event);

			theList = MessageUtil.compositeToList(response,CodeResponseEvent.class);
			Collection unFoundList = new ArrayList();
			for (int x = 0; x < theList.size(); x++) {
				CodeResponseEvent ncrre = (CodeResponseEvent) theList.get(x);
				boolean isFlag = false;
				for (int y = 0; y < csForm.getActiveSuggestedCourtActions().size(); y++) {
					CodeResponseEvent tl = (CodeResponseEvent) csForm.getActiveSuggestedCourtActions().get(y);
					if (ncrre.getCode().equalsIgnoreCase(tl.getCode()) && !ncrre.getTransaction().equalsIgnoreCase("FL")) {
						tl.setVisible(true);
						isFlag = true;
					}
				}
				if (!isFlag && !ncrre.getTransaction().equalsIgnoreCase("FL")) {
					CodeResponseEvent t2 = new CodeResponseEvent();
					t2.setCode(ncrre.getCode());
					t2.setDescription(ncrre.getDescription());
					t2.setCodeId(ncrre.getCodeId());
					t2.setTransaction(ncrre.getTransaction());
					t2.setVisible(true);
					unFoundList.add(t2);
				}
			}
			theList = csForm.getActiveSuggestedCourtActions(); // should contain selected values
			theList.addAll(unFoundList);

			for (int x = 0; x < csForm.getActiveSuggestedCourtActions().size(); x++) {
				CodeResponseEvent t3 = (CodeResponseEvent) csForm
						.getActiveSuggestedCourtActions().get(x);
				if (!t3.isVisible()) {
					t3.setCodeId("");
				}
			}
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
					.filterComposite(response, NCResponseResponseEvent.class);
			if (resp != null) {
				recommendations = resp.getComments();
			} else {
				recommendations = csForm.getCurrentRecommendations();
			}
		} else {
			recommendations = csForm.getCurrentRecommendations();
			if (csForm.getCurrentSuggestedCourtActionsList().isEmpty()) {
				theList = csForm.getActiveSuggestedCourtActions(); // should contain NO selected values
			} else {
				int matchCount = 0;
				for (int c = 0; c < csForm.getCurrentSuggestedCourtActionsList().size(); c++) {
					CodeResponseEvent ce = (CodeResponseEvent) csForm
							.getCurrentSuggestedCourtActionsList().get(c);
					for (int r = 0; r < csForm.getActiveSuggestedCourtActions().size(); r++) {
						CodeResponseEvent re = (CodeResponseEvent) csForm.getActiveSuggestedCourtActions().get(r);
						if (ce.getCode().equalsIgnoreCase(re.getCode())) {
							re.setVisible(true);
							ce.setVisible(true);
							matchCount++;
						}
					}
				}
				theList = csForm.getActiveSuggestedCourtActions();
				if (matchCount != csForm.getCurrentSuggestedCourtActionsList()
						.size()) {
					for (int c = 0; c < csForm
							.getCurrentSuggestedCourtActionsList().size(); c++) {
						CodeResponseEvent ce = (CodeResponseEvent) csForm
								.getCurrentSuggestedCourtActionsList().get(c);
						if (ce.isVisible() == false) {
							for (int r = 0; r < csForm
									.getSuggestedCourtActions().size(); r++) {
								CodeResponseEvent re = (CodeResponseEvent) csForm
										.getSuggestedCourtActions().get(r);
								if (ce.getCode().equalsIgnoreCase(re.getCode())) {
									CodeResponseEvent t2 = new CodeResponseEvent();
									t2.setCode(re.getCode());
									t2.setDescription(re.getDescription());
									t2.setCodeId(re.getCodeId());
									t2.setVisible(true);
									theList.add(t2);
									break;
								}
							}
						}
					}
				}
			}
		}
		csForm.setCreate1Comments(recommendations);
		csForm.setCreate1ElementsList(UIViolationReportHelper.sortCourtActionsList(theList));
	}
	
	/**
	 * Retrieve the information for CourtActions for Presented Update of Court Action choices
	 * @param caseSummaryForm
	 */
	public static void getUpdateCourtActionsUpdateInfo(CaseSummaryForm csForm){
		List theList = new ArrayList();
		List activeList = new ArrayList();
		List tempList = csForm.getActiveSuggestedCourtActions();

		// set the field courtActionfiledDate with the value for fileDateStr
		if(csForm.getFileDateStr() != null){
			csForm.setCourtActionfiledDate(csForm.getFileDateStr());
		}
		
		//reset codelist visible value in prep for display
		for ( int x=0; x < tempList.size(); x++ ){
			CodeResponseEvent cre = (CodeResponseEvent) tempList.get(x);
			cre.setVisible(false);
			activeList.add( x , cre);
		}
		csForm.setActiveSuggestedCourtActions( new ArrayList( activeList) );
		// new code


		if (csForm.getCurrentCourtActionsList().isEmpty()){
			theList = csForm.getActiveSuggestedCourtActions(); //should contain NO selected values
		} else {
			int matchCount = 0;
			for (int c =0; c < csForm.getCurrentCourtActionsList().size(); c++){
				CodeResponseEvent ce = (CodeResponseEvent) csForm.getCurrentCourtActionsList().get(c);
				for (int r=0; r < csForm.getActiveSuggestedCourtActions().size(); r++){
					CodeResponseEvent re = (CodeResponseEvent) csForm.getActiveSuggestedCourtActions().get(r);
					if (ce.getCode().equalsIgnoreCase(re.getCode())){
						re.setVisible(true);
						ce.setVisible(true);
						matchCount++;
					}
				}
			}
			theList = csForm.getActiveSuggestedCourtActions();
			if (matchCount != csForm.getCurrentCourtActionsList().size()){
				for (int c =0; c < csForm.getCurrentCourtActionsList().size(); c++){
					CodeResponseEvent ce = (CodeResponseEvent) csForm.getCurrentCourtActionsList().get(c);
					if (ce.isVisible() == false){
						for (int r=0; r < csForm.getCourtActions().size(); r++){
							CodeResponseEvent re = (CodeResponseEvent) csForm.getCourtActions().get(r);
							if (ce.getCode().equalsIgnoreCase(re.getCode())){
								CodeResponseEvent t2 = new CodeResponseEvent();
								t2.setCode(re.getCode());
								t2.setDescription(re.getDescription());
								t2.setCodeId(re.getCodeId());
								t2.setVisible(true);
								theList.add(t2);
								break;
							}
						}	
					}
				}
			}	
		}
	
		csForm.setCreate1ElementsList(UIViolationReportHelper.sortCourtActionsList(theList));
		

	}		

	/**
	 * @param CaseSummaryForm
	 */
	public static void LoadCurrentCSInfo(CaseSummaryForm csForm) {
		GetNCResponseDetailsEvent event = (GetNCResponseDetailsEvent) EventFactory
				.getInstance(ComplianceControllerServiceNames.GETNCRESPONSEDETAILS);
		event.setNcResponseId(csForm.getViolationReportId());

		CompositeResponse response = MessageUtil.postRequest(event);
		NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
				.filterComposite(response, NCResponseResponseEvent.class);
		csForm.setTaskId(resp.getTaskId());
		// current report header info
		csForm.setStatusDesc(resp.getStatus());
		csForm.setStatusId(resp.getStatusId());
		csForm.setStatusChangedDate(resp.getStatusChangedDate());
		if (resp.getCreatedBy() != null) {
			csForm.setCreatedByName(SecurityUIHelper.getUserName(
					resp.getCreatedBy()).toString());
		}
		csForm.setCreateDate(resp.getCreateDate());
		if (resp.getSubMgrAppDate() != null) {
			csForm.setSubMgrAppDate(resp.getSubMgrAppDate());
		}
		if (resp.getManagerApprovalDate() != null) {
			csForm.setManagerApprovalDate(resp.getManagerApprovalDate());
		}
		csForm.setTotalSpecimensAnalyzed(resp.getTotalSpecimenAnalyzed());
		// clear temporary work values
		csForm.setCreate1ElementsList(new ArrayList());
		csForm.setCreate1Comments("");
		csForm.setCreate2ElementsList(new ArrayList());
		csForm.setCreate2Comments("");
		csForm.setCreate3ElementsList(new ArrayList());
		csForm.setCreate3Comments("");
		// filing info (only present when VR status is filed )
		if (resp.getFiledDate() != null) {
			csForm.setFileDateStr(DateUtil.dateToString(new Date(resp
					.getFiledDate().getTime()), DateUtil.DATE_FMT_1));
		}
		csForm.setWhoSignedName(resp.getSignedBy());
		csForm.setWhoSignedId(resp.getPositionIdOfSignedBy());
		String presentedBy = resp.getPresentedBy() + "|" + resp.getPositionIdOfPresentedBy();
        // determine the user to use for the presentedBy field.  Use existing chosen user unless not present, then use current logged in user
        // current user from database
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String jimsId = userInfo.getJIMSLogonId();   
		if(resp.getPositionIdOfPresentedBy() != null && !resp.getPositionIdOfPresentedBy().equals("")){
			GetCSCDSupervisionStaffEvent staffEvent = (GetCSCDSupervisionStaffEvent) EventFactory.getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);
			staffEvent.setAgencyId("CSC");
			staffEvent.setStaffPositionId(resp.getPositionIdOfPresentedBy());
			CSCDSupervisionStaffResponseEvent staffResponse = (CSCDSupervisionStaffResponseEvent) MessageUtil.postRequest(staffEvent,
					CSCDSupervisionStaffResponseEvent.class);
			csForm.setPresentedById(staffResponse.getAssignedLogonId());
	     // current logged in user
		}else if(jimsId != null && !jimsId.equals("")){
			csForm.setPresentedById(jimsId);	        
		}
		csForm.setPresentedByName(presentedBy);
        // reason for transfer
        List rftre = MessageUtil.compositeToList(response,
                                        ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER);

        List comments = (List) MessageUtil.compositeToList( response, NCCommentResponseEvent.class );

        for ( int ctr =0; ctr <comments.size();ctr++){
        	NCCommentResponseEvent commentResp = (NCCommentResponseEvent) comments.get(ctr);
        	if ("RFT".equalsIgnoreCase( commentResp.getReportType() ) ){
        		csForm.setIsExtended( commentResp.getComment() );
        		break;
	        }
        }
        if (rftre != null && !rftre.isEmpty()) {
                        csForm.setCurrentReasonForTransferList(rftre);
        } else {
                        csForm.setCurrentReasonForTransferList(new ArrayList());
        }
		// law violations
		List lvre = MessageUtil.compositeToList(response,
				NCLawViolationResponseEvent.class);
		if (lvre != null && !lvre.isEmpty()) {
			csForm.setCurrentLawViolationsList(UICaseSummaryHelper
					.sortLawViolationList(lvre));
		}
		// fee history
		List fhre = MessageUtil.compositeToList(response,
				NCFeeResponseEvent.class);
		if (fhre != null) {
			csForm.setCurrentFeeHistoryList(UICaseSummaryHelper
					.sortFeeHistoryList(fhre));
		}
		// reporting history and positive urinalysis
		List rptList = new ArrayList();
		List puList = new ArrayList();
		List ncere = MessageUtil.compositeToList(response,
				NonComplianceEventResponseEvent.class);
		if (ncere != null) {
			for (int x = 0; x < ncere.size(); x++) {
				NonComplianceEventResponseEvent event2 = (NonComplianceEventResponseEvent) ncere
						.get(x);
				if (event2.getTopic().equalsIgnoreCase(
						ViolationReportConstants.REQUEST_REPORTING)) {
					rptList.add(event2);
				}
				if (event2.getTopic().equalsIgnoreCase(
						ViolationReportConstants.REQUEST_POSITIVE_UA)) {
					puList.add(event2);
				}
			}
		}
		NCLastKnownAddressResponseEvent addressResp = (NCLastKnownAddressResponseEvent) MessageUtil
				.filterComposite(response,
						NCLastKnownAddressResponseEvent.class);
		if (addressResp != null) {
			csForm.setLastContactDate(addressResp.getLastContactDate());
			csForm.setAddressNumber(addressResp.getStreetNumber());
			csForm.setAddressName(addressResp.getStreetName());
			csForm.setAddressCity(addressResp.getCity());
			csForm.setAddressState(addressResp.getState());
			csForm.setAddressStateId(addressResp.getStateId());
			csForm.setAddressZipCode(addressResp.getZip());
			csForm.setAddressType(addressResp.getAddressType());
			csForm.setAddressTypeId(addressResp.getAddressTypeId());
		}
		// eventtypes may contain eventIds instead of event descriptions
		if (rptList != null && rptList.size() > 0) {
			reloadEventTypeDescriptions(rptList, csForm.getEventTypes());
		}
		csForm.setCurrentReportingHistoryList(UICaseSummaryHelper
				.sortReportingHistoryList(rptList));
		csForm.setCurrentPositiveUrinalysisList(UICaseSummaryHelper
				.sortPositiveUrinalysisList(puList));
		csForm.setCurrentTotalSpecimensAnalyzed(resp.getTotalSpecimenAnalyzed());
		// employment history
		List ehre = MessageUtil.compositeToList(response,
				NCEmploymentResponseEvent.class);
		if (ehre != null) {
			for (int t = 0; t < ehre.size(); t++) {
				NCEmploymentResponseEvent ncre = (NCEmploymentResponseEvent) ehre
						.get(t);
				if ((ncre.getStatusDesc() == null || ncre.getStatusDesc()
						.equals("") && ncre.getStatusId() != null)) {
					for (int x = 0; x < csForm.getEmploymentStatusList().size(); x++) {
						CodeResponseEvent cre = (CodeResponseEvent) csForm
								.getEmploymentStatusList().get(x);
						if (cre.getCodeId().equals(ncre.getStatusId())) {
							ncre.setStatusDesc(cre.getDescription());
							break;
						}
					}
				}
			}
			csForm.setCurrentEmploymentHistoryList(UIViolationReportHelper
					.sortEmploymentHistoryListByCLS(ehre));
		}
		// previous court activity
		List vrList = new ArrayList();
		List motList = new ArrayList();
		List othList = new ArrayList();
		List pcare = MessageUtil.compositeToList(response,
				NCPreviousCourtActivityResponseEvent.class);
		if (pcare != null) {
			for (int x = 0; x < pcare.size(); x++) {
				NCPreviousCourtActivityResponseEvent pcaEvent = (NCPreviousCourtActivityResponseEvent) pcare
						.get(x);
				if(pcaEvent.getSummaryOfCourtAction() != null && pcaEvent.getSummaryOfCourtAction().length() > 250){
	      			pcaEvent.setShortTruncatedSummaryOfCourtAction(truncateComments(pcaEvent.getSummaryOfCourtAction(), 250));
	      		}
				if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION
						.equalsIgnoreCase(pcaEvent.getSubType())) {
					vrList.add(pcaEvent);
				} else if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION
						.equalsIgnoreCase(pcaEvent.getSubType())) {
					motList.add(pcaEvent);
				} else if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER
						.equalsIgnoreCase(pcaEvent.getSubType())) {
					othList.add(pcaEvent);
				}
			}
		}
		// extract literal from code tables for each list
		for (int m = 0; m < motList.size(); m++) {
			NCPreviousCourtActivityResponseEvent moEvent = (NCPreviousCourtActivityResponseEvent) motList
					.get(m);
			for (int x = 0; x < csForm.getMotionsDispositions().size(); x++) {
				CodeResponseEvent cre2 = (CodeResponseEvent) csForm
						.getMotionsDispositions().get(x);
				if (cre2.getCode().equals(moEvent.getDisposition())) {
					moEvent.setDisposition(cre2.getDescription());
					break;
				}
			}
		}
		csForm.setCurrentCourtActivityVRList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(vrList));
		csForm.setCurrentMotionsList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(motList));
		csForm.setCurrentOthersList(UICaseSummaryHelper
				.sortPreviousCourtActivityListDescending(othList));
		// treatment issues
		List tire = MessageUtil.compositeToList(response,
				CSProgramReferralResponseEvent.class);
		if (tire != null) {
			for (int x = 0; x < tire.size(); x++) {
				CSProgramReferralResponseEvent newEvent = (CSProgramReferralResponseEvent) tire
						.get(x);
				for (int y = 0; y < csForm.getDischargeReasons().size(); y++) {
					CodeResponseEvent cre2 = (CodeResponseEvent) csForm
							.getDischargeReasons().get(y);
					if (cre2.getCode().equals(newEvent.getDischargeReason())) {
						newEvent.setDischargeReason(cre2.getDescription());
						break;
					}
				}
			}
			csForm.setCurrentTreatmentIssuesList(UICaseSummaryHelper
					.sortTreatmentIssuesList(tire));
		}
		// community service
		NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent) MessageUtil
				.filterComposite(response,
						NCCommunityServiceResponseEvent.class);
		if (csre != null) {
			csForm.setCurrentHoursOrdered(csre.getHoursOrdered());
			csForm.setCurrentHoursCompleted(csre.getHoursCompleted());
		}
		// recommendations and court actions
		List rre = MessageUtil.compositeToList(response,
				ViolationReportConstants.REQUEST_RECOMMENDATION);
		List caList = new ArrayList();
		List recList = new ArrayList();
		if (rre != null) {
			for (int x = 0; x < rre.size(); x++) {
				CodeResponseEvent crex = (CodeResponseEvent) rre.get(x);
				if (ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(crex
						.getTransaction())) {
					caList.add(crex);
				} else {
					recList.add(crex);
				}
			}
		}
		csForm.setCurrentSuggestedCourtActionsList(UICaseSummaryHelper
				.sortCourtActionsList(recList));
		csForm.setCurrentCourtActionsList(UICaseSummaryHelper
				.sortCourtActionsList(caList));
		// comments
		List cre = MessageUtil.compositeToList(response,
				NCCommentResponseEvent.class);
		if (cre != null && cre.size() > 0) {
			for (int x = 0; x < cre.size(); x++) {
				NCCommentResponseEvent nccre = (NCCommentResponseEvent) cre
						.get(x);
				if (nccre.getReportType() != null) {
					if (nccre
							.getReportType()
							.equalsIgnoreCase(
									ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER)) {
						csForm.setCurrentReasonForTransferComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_MENTAL_HEALTH_DIAGNOSIS)) {
						csForm.setCurrentMentalHealthDiagnosis( nccre.getComment() );
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_MENTAL_HEALTH_COMMENTS)) {
						csForm.setCurrentMentalHealthComments( nccre.getComment() );
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_LAW_VIOLATION)) {
						csForm.setCurrentLawViolationsComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_DELINQUENT_FEE)) {
						csForm.setCurrentFeeHistoryComments(nccre.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_REPORTING)) {
						csForm.setCurrentReportingHistoryComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_EMPLOYMENT)) {
						csForm.setCurrentEmploymentHistoryComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_TREATMENT)) {
						csForm.setCurrentTreatmentIssuesComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_COMMUNITY_SERVICE)) {
						csForm.setCurrentCommunityServiceComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase(
							ViolationReportConstants.REQUEST_POSITIVE_UA)) {
						csForm.setCurrentPositiveUrinalysisComments(nccre
								.getComment());
					} else if (nccre.getReportType().equalsIgnoreCase("RCM")) {
						if (ViolationReportConstants.SUGGESTED_COURT_ACTION
								.equalsIgnoreCase(nccre.getCommentType())) {
							csForm.setSummaryOfCourtActions(nccre.getComment());
						} else {
							csForm.setCurrentRecommendations(nccre.getComment());
						}
					}
				}
			} // end for loop
		}
        // court actions summary comments (only if the case has already been filed)
        if((resp.getStatusId().equals(ViolationReportConstants.STATUS_FILED)) && (resp.getCourtActionSummary()!= null && 
        		!resp.getCourtActionSummary().equalsIgnoreCase("") ) ){
        	csForm.setSummaryOfCourtActions(resp.getCourtActionSummary());
        }
	}

	/**
	 * Convert the given referral type code to a meaningful description
	 * 
	 * @param refTypeCode
	 * @return
	 */
	public static String getReferralTypeDesc(String refTypeCode) {
		int space_indx = refTypeCode.indexOf(' ');
		String program_group = refTypeCode.substring(0, space_indx);
		String program_type = refTypeCode.substring(space_indx + 1);

		// get description for the given program group and type codes
		String program_group_desc = SimpleCodeTableHelper.getDescrByCode(
				CSAdministerProgramReferralsConstants.PROGRAM_GROUP_CODE_TABLE,
				program_group);
		String program_type_desc = SimpleCodeTableHelper.getDescrByCode(
				CSAdministerProgramReferralsConstants.PROGRAM_TYPE_CODE_TABLE,
				program_type);

		return program_group_desc + " / " + program_type_desc;
	}// end of getReferralTypeResponse()

	/**
	 * 
	 * @param csForm
	 */
	public static void loadDropDown(CaseSummaryForm csForm) {
		csForm.setAllowMaintain("");
		if (SecurityUIHelper.isUserSA()) {
			csForm.setAllowMaintain(UIConstants.YES);
		}
		// begin code table loading for drop down lists and other code tables
		GetReasonForTransferCodesEvent reasonForTransferEvent = new GetReasonForTransferCodesEvent();
		reasonForTransferEvent
				.setRequestType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);
		List reasonForTransferList = MessageUtil.postRequestListFilter(
				reasonForTransferEvent, CodeResponseEvent.class);
		if (reasonForTransferList != null && !reasonForTransferList.isEmpty()) {
			csForm.setReasonForTransferList(reasonForTransferList);
		} else {
			csForm.setReasonForTransferList(new ArrayList());
		}
		csForm.setPayTypes(SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.PAYTYPE));
		// not sure if this is correct for event type drop down for reporting
		// history
		List eventTypesList = CodeHelper
				.getEventTypes(CaseloadConstants.CSCD_AGENCY_ID);
		if (eventTypesList == null) {
			eventTypesList = new ArrayList();
		}
		csForm.setEventTypes(eventTypesList);
		csForm.setEmploymentStatusList(SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.EMPLOYMENT_STATUS));
		List temp = SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.MOTION_DISPOSITION);
		csForm.setMotionsDispositions(loadActiveCodes(temp));
		csForm.setDischargeReasons(SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.JIMS2_DISCHARGE_REASON));
		csForm.setSuggestedCourtActions(SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.SUGGESTEDCOURTACTION));
		temp = SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.SUGGESTEDCOURTACTION);
		csForm.setActiveSuggestedCourtActions(loadActiveCodes(temp));
		// end code table loading
	}

	/**
	 * @param list
	 *            return list
	 */
	public static List loadActiveCodes(List theList) {
		List rList = new ArrayList();
		for (int t = 0; t < theList.size(); t++) {
			CodeResponseEvent tl = (CodeResponseEvent) theList.get(t);
			if (tl.getStatus().equalsIgnoreCase("active")) {
				rList.add(tl);
			}
		}
		return rList;
	}

	/**
	 * Loads missing/blank discharge reasons into event using drop down code
	 * table values
	 * 
	 * @param List
	 *            of CSProgramReferralResponseEvent
	 * @param List
	 *            of CodeResponseEvent
	 * @return
	 */
	public static List loadDischargeReasonDescriptions(List theList,
			List dischargeReasons) {
		if (!theList.isEmpty()) {
			for (int x = 0; x < theList.size(); x++) {
				CSProgramReferralResponseEvent csre = (CSProgramReferralResponseEvent) theList
						.get(x);
				if (csre.getDischargeReasonCd() != null
						&& !csre.getDischargeReasonCd().equals("")) {
					for (int y = 0; y < dischargeReasons.size(); y++) {
						CodeResponseEvent cre2 = (CodeResponseEvent) dischargeReasons
								.get(y);
						if (csre.getDischargeReasonCd().equals(cre2.getCode())) {
							csre.setDischargeReason(cre2.getDescription());
							break;
						}
					}
				}
			}
		}
		return theList;
	}

	/**
	 * @param CaseSummaryForm
	 */
	public static void prepareToFileReport(CaseSummaryForm csForm,
			String courtNumber) {
		csForm.setConfirmationMessage("");
		Date theDate = DateUtil.getCurrentDate();
		csForm.setCourtActionfiledDate(DateUtil.dateToString(theDate,
				DateUtil.DATE_FMT_1));
		csForm.setPresentedById("");
		csForm.setPresentedByFirstName("");
		csForm.setPresentedByLastName("");
		csForm.setPresentedByName("");
		csForm.setWhoSignedId("");
		csForm.setWhoSignedFirstName("");
		csForm.setWhoSignedLastName("");
		csForm.setWhoSignedName("");
		csForm.setSummaryOfCourtActions("");
		// revise presentedby to use code so position displays next to name
		csForm.setPresentedByList(CodeHelper.getAllSupervisionStaff(true,
				csForm.getCourtNum(), true));
		csForm.setWhoSignedList(SupervisionOrderListHelper.getJudgeList());
		List theList = csForm.getActiveSuggestedCourtActions();
		int matchCount = 0;
		// flags court action as suggested so it will display blue in jsp, use
		// "status" value in event
		for (int y = 0; y < theList.size(); y++) {
			CodeResponseEvent tl = (CodeResponseEvent) theList.get(y);
			tl.setStatus("");
			for (int x = 0; x < csForm.getCurrentSuggestedCourtActionsList()
					.size(); x++) {
				CodeResponseEvent csca = (CodeResponseEvent) csForm
						.getCurrentSuggestedCourtActionsList().get(x);
				if (csca.getCode().equals(tl.getCode())) {
					tl.setStatus("B");
					matchCount++;
					break;
				}
			}
		}
		// check if a current SCA has become inactive and add to list
		if (matchCount != csForm.getCurrentSuggestedCourtActionsList().size()) {
			for (int c = 0; c < csForm.getCurrentSuggestedCourtActionsList()
					.size(); c++) {
				CodeResponseEvent ce = (CodeResponseEvent) csForm
						.getCurrentSuggestedCourtActionsList().get(c);
				if (ce.isVisible() == false) {
					for (int r = 0; r < csForm.getSuggestedCourtActions()
							.size(); r++) {
						CodeResponseEvent re = (CodeResponseEvent) csForm
								.getSuggestedCourtActions().get(r);
						if (ce.getCode().equalsIgnoreCase(re.getCode())) {
							CodeResponseEvent t2 = new CodeResponseEvent();
							t2.setCode(re.getCode());
							t2.setDescription(re.getDescription());
							t2.setCodeId(re.getCodeId());
							t2.setVisible(true);
							t2.setStatus("B");
							theList.add(t2);
							break;
						}
					}
				}
			}
		}
		csForm.setCreate1ElementsList(UIViolationReportHelper
				.sortCourtActionsList(theList)); // sort in case inactive added
		// remove leading CR, CC, etc from court number
		if (courtNumber != null) {
			if (courtNumber.indexOf(" ") > -1) {
				courtNumber = courtNumber.substring(
						courtNumber.indexOf(" ") + 2, courtNumber.length());
			}
		}
        // determine the user to use for the presentedBy field.  Use existing chosen user.
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String jimsId = userInfo.getJIMSLogonId();   
		csForm.setPresentedById(jimsId);	        
		// set the signed id to the court of the staff member
		csForm.setWhoSignedId(courtNumber);
	}

	/**
	 * get the presented and signed information for top of court actions section
	 * @param violationReportsForm
	 */
	public static void getPresentedSignedInformation(CaseSummaryForm csForm, String courtNumber) {
		// revise presentedby to use code so position displays next to name		
		csForm.setPresentedByList(CodeHelper.getAllSupervisionStaff(true, csForm.getCourtNum(),true));
		csForm.setWhoSignedList(SupervisionOrderListHelper.getJudgeList());
		// remove leading CR, CC, etc from court number
       	if (courtNumber != null){
       		if (courtNumber.indexOf(" ") > -1){
       			courtNumber = courtNumber.substring(courtNumber.indexOf(" ") + 2, courtNumber.length());
       		}
       	}
       	// get the court number for the signedBy 
       	String currentSignedBy = csForm.getWhoSignedName();
       	for(Object event:csForm.getWhoSignedList()){
       		JudgeResponseEvent courtInfo = (JudgeResponseEvent)event;
       		if(currentSignedBy != null && !currentSignedBy.equals("") &&
       				currentSignedBy.contains(courtInfo.getFirstName()) && currentSignedBy.contains(courtInfo.getLastName())){
       			courtNumber = courtInfo.getCourtNumber();
       		}   		
       	}
       	csForm.setWhoSignedId(courtNumber);
	}
	
	/**
	 * this method reloads event descriptions into eventtypes field which may
	 * contain eventIds if the reporting history record was created thru
	 * violation reporting. Reporting history records created via Compliance
	 * will contain descriptions
	 * 
	 * @param eventList
	 * @param eventTypes
	 * @return
	 */
	public static List reloadEventTypeDescriptions(List eventList,
			List eventTypes) {
		for (int g = 0; g < eventList.size(); g++) {
			NonComplianceEventResponseEvent ncre2 = (NonComplianceEventResponseEvent) eventList
					.get(g);
			if (ncre2.getEventTypesId() != null) {
				String[] theIDs = ncre2.getEventTypesId().split(",");
				StringBuffer selectedEventTypes = new StringBuffer();
				boolean matchFound = false;
				for (int y = 0; y < theIDs.length; y++) {
					String compareId = theIDs[y].trim();
					if (!compareId.equals("")) {
						for (int t = 0; t < eventTypes.size(); t++) {
							CodeResponseEvent et = (CodeResponseEvent) eventTypes
									.get(t);
							if (et.getSupervisionCode().equals(compareId)) {
								selectedEventTypes.append(et.getDescription());
								matchFound = true;
								if (y < theIDs.length - 1) {
									selectedEventTypes.append(", ");
									break;
								}
							}
						} // end t loop
						if (matchFound == false) {
							selectedEventTypes.append(theIDs[y]);
							if (y < theIDs.length - 1) {
								selectedEventTypes.append(", ");
							}
						}
					}
				} // end y loop
				ncre2.setEventTypes(selectedEventTypes.toString());
			}
		} // end g loop
		return eventList;
	}

	/**
	 * 
	 * @param dateStr
	 * @param timeStr
	 * @param meridianStr
	 * @return
	 */
	public static Timestamp convertDateToTimeStamp(String dateStr,
			String timeStr, String meridianStr) {
		final Timestamp ts;
		String[] dateFld = dateStr.split("\\x2F");
		String[] timeFld = timeStr.split("\\x3A");
		int YYYY = Integer.parseInt(dateFld[2]);
		int MM = Integer.parseInt(dateFld[0]);
		int DD = Integer.parseInt(dateFld[1]);
		int HH = Integer.parseInt(timeFld[0]);
		if (meridianStr.equalsIgnoreCase("AM") && HH == 12) {
			HH = HH - 12;
		}
		if (meridianStr.equalsIgnoreCase("PM") && HH < 12) {
			HH = HH + 12;
		}
		int MIN = Integer.parseInt(timeFld[1]);
		int SS = 0;
		Calendar cal = Calendar.getInstance();
		cal.set(YYYY, MM - 1, DD, HH, MIN, SS);
		ts = new Timestamp(cal.getTime().getTime());
		return ts;
	}

	/**
	 * 
	 * @param addItemIndex
	 * @return
	 */
	public static String getAddIndex(String addItemIndex) {
		String addIndex = "M1";
		String addIndexNumStr = "";
		int a = 0;
		if (addItemIndex != null && !addItemIndex.equalsIgnoreCase("")) {
			addIndex = addItemIndex;
			addIndexNumStr = addIndex.substring(1);
			a = Integer.parseInt(addIndexNumStr);
			a++;
			addIndex = "M" + String.valueOf(a);
		}
		return addIndex;
	}

	// ************************************************************************************
	// NOT SURE IF THESE METHODS BELOW ARE NEEDED, LEFT FOR REFERENCE FOR THE
	// TIME BEING
	// ************************************************************************************
	/**
	 * Create Date from date and time Strings
	 * 
	 * @return Date
	 * @pre strDate != null
	 * @pre strTime != null
	 * @pre strDate = mm/dd/yyyy format
	 * @pre strDate.length() == 10
	 * @pre strTime.length() <= 5 (HH:MM)
	 */
	public static Date convertToDateTime(String strDate, String strTime) {

		Date date = null;
		try {
			int year = new Integer(strDate.substring(6, 10)).intValue();
			int month = new Integer(strDate.substring(0, 2)).intValue();
			int day = new Integer(strDate.substring(3, 5)).intValue();
			int hours = new Integer(strTime.substring(0, 2)).intValue();
			int minutes = new Integer(strTime.substring(3, 5)).intValue();
			int seconds = 0;
			Calendar calendar = Calendar.getInstance();
			calendar.setLenient(false);
			calendar.set(year, month - 1, day, hours, minutes, seconds);
			date = calendar.getTime();
		} catch (NumberFormatException e) {
		} catch (IndexOutOfBoundsException e) {
		} catch (IllegalArgumentException e) {
			// This exception is thrown if calendar was created with an invalid
			// date.
		}
		return date;
	}

	/**
	 * temporary sort methods needed until 'sortResult' pagination issue
	 * resolved
	 */

	/**
	 * @param reasonForTransferList
	 * @return List
	 */
	public static List sortReasonForTransfer(List rftList) {
		if (rftList.size() > 1) {
			SortedMap map = new TreeMap();
			for (int x = 0; x < rftList.size(); x++) {
				CodeResponseEvent cre = (CodeResponseEvent) rftList.get(x);
				map.put(cre.getDescription(), cre);
			}
			rftList = new ArrayList(map.values());
		}
		return rftList;
	}

	/**
	 * @param lawViolaitonList
	 * @return List
	 */
	public static List sortLawViolationList(List lvList) {
		if (lvList.size() > 1) {
			SortedMap map = new TreeMap();
			for (int x = 0; x < lvList.size(); x++) {
				NCLawViolationResponseEvent lvre = (NCLawViolationResponseEvent) lvList
						.get(x);
				map.put(lvre.getCaseId() + lvre.getLawViolationId(), lvre);
			}
			lvList = new ArrayList(map.values());
		}
		return lvList;
	}

	/**
	 * @param feeHistoryList
	 * @return List
	 */
	public static List sortFeeHistoryList(List fhList) {
		if (fhList.size() > 1) {
			String payType = "";
			SortedMap map = new TreeMap();
			for (int x = 0; x < fhList.size(); x++) {
				NCFeeResponseEvent fhre = (NCFeeResponseEvent) fhList.get(x);
				payType = "";
				if (fhre.getPayType() != null) {
					payType = fhre.getPayType();
				}
				map.put(payType + fhre.getFeeId(), fhre);
			}
			fhList = new ArrayList(map.values());
		}
		return fhList;
	}

	/**
	 * @param reportingHistoryList
	 * @return List
	 */
	public static List sortReportingHistoryList(List rhList) {
		if (rhList.size() > 1) {
			SortedMap map = new TreeMap();
			// sort by date ascending order
			for (int x = 0; x < rhList.size(); x++) {
				NonComplianceEventResponseEvent rhre = (NonComplianceEventResponseEvent) rhList
						.get(x);
				map.put("" + rhre.getDateTime()
						+ rhre.getNonComplianceEventId(), rhre);
			}
			// reverse sorted map
			List temp = new ArrayList(map.values());
			Collections.reverse(temp);
			rhList = new ArrayList(temp);
		}
		return rhList;

	}

	/**
	 * @param employmentHistoryList
	 * @return List
	 */
	public static List sortEmploymentHistoryList(List ehList) {
		if (ehList.size() > 1) {
			String empName = "";
			SortedMap map = new TreeMap();
			// sort by employer name
			for (int x = 0; x < ehList.size(); x++) {
				NCEmploymentResponseEvent ehre = (NCEmploymentResponseEvent) ehList
						.get(x);
				empName = "";
				if (ehre.getEmployerName() != null) {
					empName = ehre.getEmployerName();
				}
				map.put(empName + " " + ehre.getEmploymentId(), ehre);
			}

			List temp = new ArrayList(map.values());
			ehList = new ArrayList(temp);
		}
		return ehList;
	}

	/**
	 * @param previousCourtActivityList
	 * @return List
	 */
	public static List sortPreviousCourtActivityList(List pcaList) {
		if (pcaList.size() > 1) {
			SortedMap map = new TreeMap();
			for (int x = 0; x < pcaList.size(); x++) {
				NCPreviousCourtActivityResponseEvent pcare = (NCPreviousCourtActivityResponseEvent) pcaList
						.get(x);
				map.put(pcare.getOccurenceDate()
						+ pcare.getPreviousCourtActivityId(), pcare);
			}
			pcaList = new ArrayList(map.values());
		}
		return pcaList;
	}

	/**
	 * @param previousCourtActivityList
	 * @return List
	 */
	public static List sortPreviousCourtActivityListDescending(List pcaList) {
		if (pcaList.size() > 1) {
			SortedMap map = new TreeMap();
			for (int x = 0; x < pcaList.size(); x++) {
				NCPreviousCourtActivityResponseEvent pcare = (NCPreviousCourtActivityResponseEvent) pcaList
						.get(x);
				map.put(pcare.getOccurenceDate()
						+ pcare.getPreviousCourtActivityId(), pcare);
			}
			List temp = new ArrayList(map.values());
			for (int y = 0; y < temp.size(); y++) {
				pcaList.set(y, temp.get(temp.size() - y - 1));
			}
		}
		return pcaList;
	}

	/**
	 * @param treatmentIssuesList
	 * @return List
	 */
	public static List sortTreatmentIssuesList(List tiList) {
		if (tiList.size() > 1) {
			String refType = "";
			SortedMap map = new TreeMap();
			for (int x = 0; x < tiList.size(); x++) {
				CSProgramReferralResponseEvent tire = (CSProgramReferralResponseEvent) tiList
						.get(x);
				refType = "";
				if (tire.getReferralTypeDesc() != null) {
					refType = tire.getReferralTypeDesc();
				} else if (tire.getReferralTypeCode() != null) {
					refType = getReferralTypeDesc(tire.getReferralTypeCode());
					tire.setReferralTypeDesc(refType);
				}
				map.put(tire.getReferralTypeDesc(), tire);
			}
			tiList = new ArrayList(map.values());
		}
		return tiList;
	}

	/**
	 * @param positiveUrinalysisList
	 * @return List
	 */
	public static List sortPositiveUrinalysisList(List puList) {
		if (puList.size() > 1) {
			SortedMap map = new TreeMap();
			for (int x = 0; x < puList.size(); x++) {
				NonComplianceEventResponseEvent rhre = (NonComplianceEventResponseEvent) puList
						.get(x);
				map.put(rhre.getDateTime() + rhre.getNonComplianceEventId(),
						rhre);
			}
			// reverse sorted map
			List temp = new ArrayList(map.values());
			Collections.reverse(temp);
			puList = new ArrayList(temp);
		}
		return puList;
	}

	/**
	 * @param suggestedCourtActionList
	 * @return List
	 */
	public static List sortCourtActionsList(List scaList) {
		if (scaList.size() > 0) {
			SortedMap map = new TreeMap();
			for (int x = 0; x < scaList.size(); x++) {
				CodeResponseEvent rre = (CodeResponseEvent) scaList.get(x);
				map.put(rre.getDescription(), rre);
			}
			scaList = new ArrayList(map.values());
		}
		return scaList;
	}

	public static UpdateNCResponseStatusEvent prepareRequestEvent(
			CaseSummaryForm csForm, String subject, String text, String topic,
			String statusId, boolean selfApprove) {
		String crtNum = csForm.getCourtNum().trim();
		if (crtNum.indexOf(" ") > -1) {
			crtNum = crtNum.substring(crtNum.length() - 3, crtNum.length());
		}
		// this tasks needs full court value to find CLO.
		if (ViolationReportConstants.CSTASK_SUBJECT_CASESUMMARY_SUBMISSION_FILING
				.equalsIgnoreCase(subject)) {
			String fd = crtNum.substring(0, 1);
			if (fd.equals("0")) {
				crtNum = "CC 0" + crtNum;
			} else {
				crtNum = "CR 0" + crtNum;
			}
		}
		UpdateNCResponseStatusEvent uEvent = (UpdateNCResponseStatusEvent) EventFactory
				.getInstance(ComplianceControllerServiceNames.UPDATENCRESPONSESTATUS);
		uEvent.setNcResponseId(csForm.getViolationReportId());
		uEvent.setCaseNumber(csForm.getCdi() + csForm.getCaseNum());
		if (uEvent.getCaseNumber().length() > 15) {
			uEvent.setCaseNumber(csForm.getCaseNum());
		}
		uEvent.setCourtNumber(crtNum);
		uEvent.setNtTaskId(csForm.getTaskId());
		uEvent.setSubject(subject);
		uEvent.setSuperviseeName(csForm.getSuperviseeName());
		uEvent.setOffense(csForm.getOffense());
		uEvent.setOfficerName(csForm.getOfficerName());
		uEvent.setProgramUnit(csForm.getProgramUnit());
		uEvent.setSpn(csForm.getSuperviseeId());
		uEvent.setText(text);
		uEvent.setTopic(topic);
		uEvent.setStatusId(statusId);
		uEvent.setSelfApprove(selfApprove);
		uEvent.setCdi(csForm.getCdi());
		uEvent.setLos(csForm.getLos());
		uEvent.setReportType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);
		uEvent.setStaffPositionId(csForm.getTaskToStaffId());
		return uEvent;
	}

	/**
	 * 
	 * @param csForm
	 * @param caseAssignForm
	 * @param myHeaderForm
	 * @param superviseeForm
	 * @return
	 */
	public AdministerComplianceReportingBean buildReportingBean(
			CaseSummaryForm csForm, CaseAssignmentForm caseAssignForm,
			SuperviseeHeaderForm myHeaderForm,
			SuperviseeInfoHeaderForm mySupHeader, SuperviseeForm superviseeForm) {
		AdministerComplianceReportingBean reportBean = new AdministerComplianceReportingBean();
		GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
		getEvent.setDefendantId(caseAssignForm.getDefendantId());
		SuperviseeInfoResponseEvent sprResponse = (SuperviseeInfoResponseEvent) MessageUtil
				.postRequest(getEvent, SuperviseeInfoResponseEvent.class);
		if (sprResponse != null) {
			reportBean.setLevelOfSupervision(sprResponse.getSupervisionLevel());
			reportBean.setProgramUnit(sprResponse.getProgramUnit());
		}
		// sets data fields
		SupervisionOrderDetailResponseEvent orderDetailResponseEvent = null;
		if (csForm.getOrderId() == null) {
			GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory
					.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
			event.setSuperviseeId(csForm.getSuperviseeId());

			List cases = MessageUtil.postRequestListFilter(event,
					SuperviseeCaseOrderResponseEvent.class);
			if (cases != null && !cases.isEmpty()) {
				String caseNum = csForm.getCdi() + csForm.getCaseNum();
				Iterator iter = cases.iterator();
				while (iter.hasNext()) {
					SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter
							.next();
					if (score.getCaseNumber() != null
							&& score.getCaseNumber().equalsIgnoreCase(caseNum)) {
						csForm.setOrderId(score.getSupervisionOrderId());
						break;
					}
				}
			}
		}
		orderDetailResponseEvent = UISupervisionOrderHelper
				.getSupervisionOrderDetailsForReporting(csForm.getOrderId());
		reportBean.setSupervisionLengthNum(orderDetailResponseEvent
				.getSupervisionLengthNum());
		reportBean.setSupervisionBeginDate(orderDetailResponseEvent
				.getCaseSupervisionBeginDate());
		reportBean.setSupervisionEndDate(orderDetailResponseEvent
				.getCaseSupervisionEndDate());
		reportBean.setCourt(caseAssignForm.getCourtNumber());
		populateCLODetails(reportBean, caseAssignForm);
		populateCSODetails(reportBean, sprResponse);
		populateMgApproveName(reportBean, sprResponse);
		String offenseDesc = offenseDesc(caseAssignForm.getOffenseDesc());
		reportBean.setOffense(offenseDesc);
		reportBean.setPartyName(orderDetailResponseEvent.getPrintedName());
		if (mySupHeader.getLastContactDate() != null) {
			reportBean.setLastContactDate(mySupHeader.getLastContactDate());
		} else {
			reportBean.setLastContactDate(null);
		}
		if (mySupHeader.getNextContactDate() != null) {
			reportBean.setNextOfficeVisitDate(mySupHeader.getNextContactDate());
		} else {
			reportBean.setNextOfficeVisitDate(null);
		}
		if (mySupHeader.getNextContactTime() != null) {
			reportBean.setNextOfficeVisitTime(mySupHeader.getNextContactTime());
		} else {
			reportBean.setNextOfficeVisitTime(null);
		}
		reportBean.setTypeOfDisposition(orderDetailResponseEvent
				.getDispositionType());
		reportBean.setAddressZip(superviseeForm.getSuperviseeZipCode());
		reportBean.setDateOfBirth(superviseeForm.getDateOfBirth());
		reportBean.setSuperviseeId(superviseeForm.getSuperviseeId());
		reportBean.setSexCd(superviseeForm.getSex());
		StringBuffer superviseeAddress = new StringBuffer();
		if (StringUtils.isNotEmpty(superviseeForm.getSuperviseeStreetNumber())) {
			superviseeAddress
					.append(superviseeForm.getSuperviseeStreetNumber());
			superviseeAddress.append(" ");
		}
		if (StringUtils.isNotEmpty(superviseeForm.getSuperviseeStreetName())) {
			superviseeAddress.append(superviseeForm.getSuperviseeStreetName());
			superviseeAddress.append(" ");
		}
		if (StringUtils.isNotEmpty(superviseeForm.getSuperviseeStreetType())) {
			superviseeAddress.append(superviseeForm.getSuperviseeStreetType());
			superviseeAddress.append(" ");
		}
		if (StringUtils.isNotEmpty(superviseeForm
				.getSuperviseeApartmentNumber())) {
			superviseeAddress.append("No. ");
			superviseeAddress.append(superviseeForm
					.getSuperviseeApartmentNumber());
			superviseeAddress.append(" ");
		}
		if (StringUtils.isNotEmpty(superviseeForm.getSuperviseeCity())) {
			superviseeAddress.append(superviseeForm.getSuperviseeCity());
			superviseeAddress.append(", ");
		}
		if (StringUtils.isNotEmpty(superviseeForm.getSuperviseeState())) {
			superviseeAddress.append(superviseeForm.getSuperviseeState());
			superviseeAddress.append(" ");
		}
		if (StringUtils.isNotEmpty(superviseeForm.getSuperviseeZipCode())) {
			superviseeAddress.append(superviseeForm.getSuperviseeZipCode());
		}
		reportBean.setSuperviseeAddress(superviseeAddress.toString());
		if (csForm.getManagerApprovalDate() != null) {
			reportBean.setSubMgrAppDate(csForm.getSubMgrAppDate());
		}
		if (csForm.getFileDateStr() != null) {
			reportBean.setFileDateStr(csForm.getFileDateStr());
		}
		reportBean.setCause(csForm.getCaseNum());
		reportBean.setSuperviseeId(csForm.getSuperviseeId());
		reportBean.setOffenseLevel(setDegreeOfOffense(orderDetailResponseEvent
				.getOffenseId()));
		reportBean.setHoursOrdered(csForm.getCurrentHoursOrdered());
		reportBean.setHoursCompleted(csForm.getCurrentHoursCompleted());
		reportBean.setSpecimenTotal(csForm.getCurrentTotalSpecimensAnalyzed());
		// Set array lists
		List lawViolationList = csForm.getCurrentLawViolationsList();
		for (int a = 0; a < lawViolationList.size(); a++) {
			NCLawViolationResponseEvent NCResponse = (NCLawViolationResponseEvent) lawViolationList
					.get(a);
			String offenseLit = offenseDesc(NCResponse.getOffenseLitrel());
			NCResponse.setOffenseLitrel(offenseLit);
		}
		reportBean.setLawViolations(lawViolationList);
		reportBean.setFeeHistory(csForm.getCurrentFeeHistoryList());
		reportBean.setTreatmentIssues(csForm.getCurrentTreatmentIssuesList());
		// RCK - Scrub Details of Reporting History
		reportBean.setReportingHistory(csForm.getCurrentReportingHistoryList());
		reportBean.setSugCourtActions(csForm
				.getCurrentSuggestedCourtActionsList());
		reportBean.setRecommendations(csForm.getCurrentRecommendations());
		reportBean.setReasonForTransfer(csForm
				.getCurrentReasonForTransferList());
		reportBean.setIsExtended(csForm.getIsExtended());
		reportBean.setEmploymentHistory(csForm
				.getCurrentEmploymentHistoryList());
		reportBean.setPosUrinalysis(csForm.getCurrentPositiveUrinalysisList());
		// This is a temporary list that combines the
		// CurrentCourtActivityVRList, CurrentMotionsList, and CurrentOthersList
		List newList = new ArrayList();
		if (csForm.getCurrentCourtActivityVRList().size() > 0) {
			newList.addAll(csForm.getCurrentCourtActivityVRList());
		}
		if (csForm.getCurrentMotionsList().size() > 0) {
			newList.addAll(csForm.getCurrentMotionsList());
		}
		if (csForm.getCurrentOthersList().size() > 0) {
			newList.addAll(csForm.getCurrentOthersList());
		}
		if (newList.size() > 0) {
			Collections.sort(newList, NCPreviousCourtActivityResponseEvent.NCPReviousCourtActivityResponseEventSubTypeComparator);
			Iterator scrubList = newList.iterator();
			// This is the final list of Previous Court Activity with scrubbed
			// comments
			List preCrtActList = new ArrayList();
			while (scrubList.hasNext()) {
				NCPreviousCourtActivityResponseEvent scrubbed = (NCPreviousCourtActivityResponseEvent) scrubList
						.next();
				if (StringUtils.isNotEmpty(scrubbed
						.getTypeOfCourtActionComment())) {
					scrubbed.setTypeOfCourtActionComment(scrubbed
							.getTypeOfCourtActionComment());
				}
				if (StringUtils.isNotEmpty(scrubbed.getSummaryOfCourtAction())) {
					scrubbed.setSummaryOfCourtAction(truncateComments(scrubbed
							.getSummaryOfCourtAction(),250));
				}
				preCrtActList.add(scrubbed);
			}
			reportBean.setPreCourtActivity(preCrtActList);
		}
		reportBean.setFeeHistoryComments(csForm.getCurrentFeeHistoryComments());
		reportBean.setTreatmentIssuesComments(csForm
				.getCurrentTreatmentIssuesComments());
		reportBean.setReportingHistoryComments(csForm
				.getCurrentReportingHistoryComments());
		reportBean.setCommunityServiceComments(csForm
				.getCurrentCommunityServiceComments());
		reportBean.setMentalHealthComments(csForm
				.getCurrentMentalHealthComments());
		reportBean.setMentalHealthDiagnosis(csForm
				.getCurrentMentalHealthDiagnosis());
		reportBean.setLawViolationComments(csForm
				.getCurrentLawViolationsComments());
		reportBean.setEmploymentHistoryComments(csForm
				.getCurrentEmploymentHistoryComments());
		reportBean.setPosUrinalysisComments(csForm
				.getCurrentPositiveUrinalysisComments());
		reportBean.setCourtActions(csForm.getCurrentCourtActionsList());
		if (StringUtils.isNotEmpty(csForm.getSummaryOfCourtActions())) {
			reportBean.setSummaryOfCourtActions(truncateComments(csForm.getSummaryOfCourtActions(),2000));
		}
		return reportBean;
	}

	/**
	 * 
	 * @param officerDataTo
	 * @param caseAssignForm
	 */
	public static void populateCLODetails(
			AdministerComplianceReportingBean officerDataTo,
			CaseAssignmentForm caseAssignForm) {
		GetCourtStaffPositionEvent event = (GetCourtStaffPositionEvent) EventFactory
				.getInstance(CSCDStaffPositionControllerServiceNames.GETCOURTSTAFFPOSITION);
		StringBuffer courtNumber = new StringBuffer();
		if (StringUtils.isNotEmpty(caseAssignForm.getReassignedCourtId())) {
			String fd = caseAssignForm.getReassignedCourtId().substring(0, 1);
			if (fd.equals("0")) {
				courtNumber.append("CC 0");
				courtNumber.append(caseAssignForm.getReassignedCourtId());
			} else {
				courtNumber.append("CR 0");
				courtNumber.append(caseAssignForm.getReassignedCourtId());
			}
		} else if (StringUtils.isNotEmpty(caseAssignForm.getCourtNumber())) {
			String fd = caseAssignForm.getCourtNumber().substring(0, 1);
			if (fd.equals("0")) {
				courtNumber.append("CC 0");
				courtNumber.append(caseAssignForm.getCourtNumber());
			} else {
				courtNumber.append("CR 0");
				courtNumber.append(caseAssignForm.getCourtNumber());
			}
		}
		event.setCourtId(courtNumber.toString());
		event.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		List results = MessageUtil.postRequestListFilter(event,
				SupervisionStaffResponseEvent.class);
		if (results != null && results.size() > 0) {
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				SupervisionStaffResponseEvent sre = (SupervisionStaffResponseEvent) iterator
						.next();
				StringBuffer officerName = new StringBuffer();
				if (StringUtils.isNotEmpty(sre.getLastName())) {
					officerName.append(sre.getLastName());
					officerName.append(", ");
				}
				if (StringUtils.isNotEmpty(sre.getFirstName())) {
					officerName.append(sre.getFirstName());
				}
				if (StringUtils.isNotEmpty(sre.getMiddleName())) {
					officerName.append(" ");
					officerName.append(sre.getMiddleName());
				}
				if (StringUtils.isNotEmpty(officerName.toString())) {
					officerDataTo.setCloName(officerName.toString());
				} else {
					officerDataTo.setCloName("NO OFFICER ASSIGNED");
				}
				if (StringUtils.isNotEmpty(sre.getSupervisionStaffId())) {
					GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
					reqEvt.setStaffPositionId(sre.getSupervisionStaffId());
					LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil
							.postRequest(reqEvt,
									LightCSCDStaffResponseEvent.class);
					if ( StringUtils.isNotEmpty( staffPosRespEvt.getStaffPositionName() ) ) {
						officerDataTo.setCloPOI(staffPosRespEvt.getDivisionName());
					}
				}
			}
		}
	}// end of populateCLODetails()

	/**
	 * 
	 * @param officerDataTo
	 * @param sprResponse
	 */
	public static void populateCSODetails(
			AdministerComplianceReportingBean officerDataTo,
			SuperviseeInfoResponseEvent sprResponse) {
		String staffPositionId = sprResponse.getPositionId();
		if (StringUtils.isNotEmpty(staffPositionId)) {
			GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
			reqEvt.setStaffPositionId(staffPositionId);
			reqEvt.setOfficerNameNeeded(true);
			LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil
					.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
			// add logic to find userprofile to get positions phone and email
			if (staffPosRespEvt != null) {
				if (StringUtils.isNotEmpty(staffPosRespEvt.getOfficerName())) {
					officerDataTo.setCsoName(staffPosRespEvt.getOfficerName());
				} else {
					officerDataTo.setCsoName("NO OFFICER ASSIGNED");
				}
				if (StringUtils.isNotEmpty(staffPosRespEvt
						.getStaffPositionName())) {
					officerDataTo.setCsoPOI(staffPosRespEvt
							.getStaffPositionName());
				}
				if (StringUtils.isNotEmpty(staffPosRespEvt.getDivisionName())) {
					officerDataTo.setDivisionName(staffPosRespEvt
							.getDivisionName());
				}
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getSPPhoneNumber() ) ) {
					PhoneNumber supervisionStaffPhone = new PhoneNumber(staffPosRespEvt.getSPPhoneNumber());
					officerDataTo.setCsoPhone(supervisionStaffPhone.toString());
					supervisionStaffPhone = null;
				}
			}
		}
	}// end of populateCSODetails()

	/**
	 * 
	 * @param officerDataTo
	 * @param caseAssignForm
	 */
	public static void populateMgApproveName(
			AdministerComplianceReportingBean officerDataTo,
			SuperviseeInfoResponseEvent sprResponse) {
		String staffPositionId = sprResponse.getPositionId();
		if (StringUtils.isNotEmpty(staffPositionId)) {
			GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
			reqEvt.setStaffPositionId(staffPositionId);
			reqEvt.setOfficerNameNeeded(true);
			LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil
					.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
			// add logic to find userprofile to get positions phone and email
			if (StringUtils.isNotEmpty(staffPosRespEvt
					.getSupervisorPositionId())) {
				String supervisorPositionId = staffPosRespEvt
						.getSupervisorPositionId();
				GetLightCSCDStaffForUserEvent supervisorEvt = new GetLightCSCDStaffForUserEvent();
				supervisorEvt.setStaffPositionId(supervisorPositionId);
				supervisorEvt.setOfficerNameNeeded(true);
				LightCSCDStaffResponseEvent supervisorRespEvt = (LightCSCDStaffResponseEvent) MessageUtil
						.postRequest(supervisorEvt,
								LightCSCDStaffResponseEvent.class);
				if (supervisorRespEvt != null) {
					if (StringUtils.isNotEmpty(supervisorRespEvt
							.getOfficerName())) {
						officerDataTo.setMgApproveName(supervisorRespEvt
								.getOfficerName());
					}
					if (StringUtils.isNotEmpty(supervisorRespEvt
							.getStaffPositionName())) {
						officerDataTo.setMgApprovePOI(supervisorRespEvt
								.getStaffPositionName());
					}
					if ( StringUtils.isNotEmpty( supervisorRespEvt.getSPPhoneNumber() ) ) {
						PhoneNumber supervisionStaffPhone = new PhoneNumber(supervisorRespEvt.getSPPhoneNumber());
						officerDataTo.setMgApprovePhone(supervisionStaffPhone.toString());
						supervisionStaffPhone = null;
					}
				}
			}
		}
	}// end of populateMgApproveName()

	private static String setDegreeOfOffense(String offenseDegreeId) {
		String level = "";
		String degree = "";
		if (offenseDegreeId != null && !"".equals(offenseDegreeId)) {
			OffenseCodeResponseEvent offenseCode = CodeHelper
					.getOffenseCode(offenseDegreeId);
			if (offenseCode != null) {
				if (offenseCode.getLevel() != null) {
					level = offenseCode.getLevel();
				}
				if (offenseCode.getDegree() != null) {
					degree = offenseCode.getDegree();
				}
			}
		}
		StringBuffer degreeOfOffense = new StringBuffer();
		degreeOfOffense.append(level);
		degreeOfOffense.append(degree);
		return degreeOfOffense.toString();
	}

	/**
	 * Escapes the special XML characters like < and > in the string passed.
	 * 
	 * @param string
	 *            The string in which the special chars needs to be escaped.
	 * @return The string with escaped XML chars.
	 */
	private static String offenseDesc(String string) {
		if (string != null && !"".equals(string)) {
			string = string.replaceAll("<", "&#60;").replaceAll(">", "&#62;");
		}
		return string;
	}

	/**
	 * 
	 * @param csForm
	 */
	public static void getMentalHealthCreateInfo(CaseSummaryForm csForm) {
		String mhComments = "";
		String mhDiagnosis = "";
		csForm.setCursorPosition("");
		// add code for response event
		if (StringUtils.isNotEmpty(csForm.getCurrentMentalHealthComments())){
	        mhComments = csForm.getCurrentMentalHealthComments();
		}
		if (StringUtils.isNotEmpty(csForm.getCurrentMentalHealthDiagnosis())){
	        mhDiagnosis = csForm.getCurrentMentalHealthDiagnosis();
		}
        csForm.setCreate1Comments(mhComments);
        csForm.setCreate2Comments(mhDiagnosis);
	}
	
	/**
	 * @param CaseSummaryForm
	 */
	public static void getMentalHealthUpdateInfo( CaseSummaryForm csForm ){
		String mhComments = "";
		String mhDiagnosis = "";
		csForm.setCursorPosition("");
				
		if (StringUtils.isNotEmpty(csForm.getCurrentMentalHealthComments())){
			mhComments = csForm.getCurrentMentalHealthComments();
		}
		if (StringUtils.isNotEmpty(csForm.getCurrentMentalHealthDiagnosis())){
			mhDiagnosis = csForm.getCurrentMentalHealthDiagnosis();
		}
		csForm.setCreate1Comments( mhDiagnosis );
		csForm.setCreate2Comments( mhComments );
	}
	
	/**
	 * truncate a comment or string based in a desired length
	 * @param existingComment
	 * @param truncateLength
	 * @return
	 */
	private static String truncateComments(String existingComment, int truncateLength){
		String truncatedComment = "";
		if(existingComment != null){
			if(existingComment.length() > truncateLength){
				truncatedComment = existingComment.substring(0, truncateLength);
			}else{
				truncatedComment = existingComment;
			}
		}
		return truncatedComment;
	}

}// END CLASS
