/*
 * Created on Jan 2, 2008
 *
 */
package ui.supervision.administercompliance.administerviolationreport;

import java.io.OutputStream;
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

import javax.servlet.http.HttpServletResponse;

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
import messaging.administercompliance.ViolationReportPrintTemplateRequestEvent;
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
import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.posttrial.GetSuperviseesEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSCDStaffPositionControllerServiceNames;
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
import ui.supervision.administercompliance.administercasesummary.AdministerComplianceReportingBean;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.supervisionorder.SupervisionOrderListHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;

/**
 * @author jjose
 * 
 */
public class UIViolationReportHelper {
	 public static final String FILEEXT = ".pdf";
	 public static final String CONTEXTKEYPREFIX = "REPORTING::";

	/**
	 * @param violationReportsForm
	 */
	public static void getReasonForTransferCreateInfo(ViolationReportsForm vrForm){
		List theList = vrForm.getReasonForTransferList();
		if (vrForm.getCurrentReasonForTransferList() == null || vrForm.getCurrentReasonForTransferList().isEmpty()){
			vrForm.setCreate1Comments("");
			for (int x = 0; x<vrForm.getReasonForTransferList().size(); x++){
				CodeResponseEvent t3 = (CodeResponseEvent) vrForm.getReasonForTransferList().get(x);
				t3.setCodeId("");
			}
		}else {
			vrForm.setCreate1Comments(vrForm.getCurrentReasonForTransferComments());
			for (int x = 0; x<vrForm.getCurrentReasonForTransferList().size(); x++){
	        	CodeResponseEvent cre = (CodeResponseEvent) vrForm.getCurrentReasonForTransferList().get(x);
	        	for (int y=0; y < theList.size(); y++){
	        		CodeResponseEvent tl = (CodeResponseEvent) theList.get(y);
	        		if (cre.getCode().equals(tl.getCode())){
	        			tl.setVisible(true);
	        			break;
	        		}
	        	}
			}
		}
		String extValue = vrForm.getIsExtended();
		if ( "".equals(extValue) || extValue == null ){
			vrForm.setIsExtended("NO");
		}
		vrForm.setCreate1ElementsList(UIViolationReportHelper.sortReasonForTransfer(theList)); 
	}
	
	/**
	 * @param violationReportsForm
	 */
	public static void getReasonForTransferUpdateInfo(ViolationReportsForm vrForm){
		List theList = new ArrayList();
// reset codelist visible value in prep for display
		for (int x=0; x<vrForm.getReasonForTransferList().size(); x++){
			CodeResponseEvent cre = (CodeResponseEvent) vrForm.getReasonForTransferList().get(x);
			cre.setVisible(false);
		}
		String comments = "";
//		 add code for response event
		if ((vrForm.getCurrentReasonForTransferList() == null || vrForm.getCurrentReasonForTransferList().isEmpty())){
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
	        event.setRequestType(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER);
	        event.setNcResponseId(vrForm.getViolationReportId());	 
	        
	        CompositeResponse response = MessageUtil.postRequest(event);
	        
	        theList = MessageUtil.compositeToList(response, NCReasonForTransferResponseEvent.class);
	        Collection unFoundList = new ArrayList();
			for (int x = 0; x<theList.size(); x++){
				NCReasonForTransferResponseEvent nftre = (NCReasonForTransferResponseEvent) theList.get(x);
				boolean isFlag = false;
				for (int y=0; y < vrForm.getReasonForTransferList().size(); y++){
					CodeResponseEvent tl = (CodeResponseEvent) vrForm.getReasonForTransferList().get(y);
	        		if (nftre.getReasonForTransferCodeId().equalsIgnoreCase(tl.getCode())){
	        			tl.setVisible(true);
	        			isFlag = true;						
	        		}
	        	}
				if(!isFlag){
					CodeResponseEvent t2 = new CodeResponseEvent();
					t2.setCode(nftre.getReasonForTransferCodeId());
					t2.setDescription(nftre.getReasonForTransferCodeDesc());
					t2.setCodeId(nftre.getReasonForTransferId());
					t2.setVisible(true);
					unFoundList.add(t2);
				}
			}
			theList = vrForm.getReasonForTransferList();  //should contain selected values
			theList.addAll(unFoundList);
			
			for (int x = 0; x<vrForm.getReasonForTransferList().size(); x++){
				CodeResponseEvent t3 = (CodeResponseEvent) vrForm.getReasonForTransferList().get(x);
				if(!t3.isVisible()){
				    t3.setCodeId("");
				}
			}
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);        
	        if(resp != null){
	        	comments = resp.getComments();
	        } else {
	        	comments = vrForm.getCurrentReasonForTransferComments();
	        }
		} else {
			comments = vrForm.getCurrentReasonForTransferComments();
			if (vrForm.getCurrentReasonForTransferList().isEmpty()){
				theList = vrForm.getReasonForTransferList(); //should contain NO selected values
			} else {
				for (int c =0; c < vrForm.getCurrentReasonForTransferList().size(); c++){
					CodeResponseEvent ce = (CodeResponseEvent) vrForm.getCurrentReasonForTransferList().get(c);
					for (int r=0; r < vrForm.getReasonForTransferList().size(); r++){
						CodeResponseEvent re = (CodeResponseEvent) vrForm.getReasonForTransferList().get(r);
						if (ce.getCode().equalsIgnoreCase(re.getCode())){
							re.setVisible(true);
						}
					}
				}
				theList = vrForm.getReasonForTransferList();
			}
		}
		String extValue = vrForm.getIsExtended();
		if ( "".equals(extValue) || extValue == null ){
			vrForm.setIsExtended("NO");
		}
		vrForm.setCreate1ElementsList(theList);
		vrForm.setCreate1Comments(comments);
	}	

	/**
	 * @param violationReportsForm
	 */
	public static void getMentalHealthCreateInfo(ViolationReportsForm vrForm){
		String mhComments = "";
		String mhDiagnosis = "";
		vrForm.setCursorPosition("");
// add code for response event
		if (StringUtils.isNotEmpty(vrForm.getCurrentMentalHealthComments())){
	        mhComments = vrForm.getCurrentMentalHealthComments();
		}
		if (StringUtils.isNotEmpty(vrForm.getCurrentMentalHealthDiagnosis())){
	        mhDiagnosis = vrForm.getCurrentMentalHealthDiagnosis();
		}
        vrForm.setCreate1Comments(mhComments);
        vrForm.setCreate2Comments(mhDiagnosis);
	}
	
	/**
	 * @param violationReportsForm
	 */
	public static void getMentalHealthUpdateInfo(ViolationReportsForm vrForm){
		String mhComments = "";
		String mhDiagnosis = "";
		vrForm.setCursorPosition("");
				
		if (StringUtils.isNotEmpty(vrForm.getCurrentMentalHealthComments())){
			mhComments = vrForm.getCurrentMentalHealthComments();
		}
		if (StringUtils.isNotEmpty(vrForm.getCurrentMentalHealthDiagnosis())){
			mhDiagnosis = vrForm.getCurrentMentalHealthDiagnosis();
		}
        vrForm.setCreate1Comments(mhComments);
        vrForm.setCreate2Comments(mhDiagnosis);
	}
	
	/**
	 * @param violationReportsForm
	 */
	public static void getLawViolationCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearLawViolationsAdds();
		List theList = new ArrayList();	
		if ((vrForm.getCurrentLawViolationsList() == null || vrForm.getCurrentLawViolationsList().isEmpty())){
	        vrForm.setCreate1Comments("");
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_LAW_VIOLATION);
	        
	        GetNCLawViolationsEvent reqEvent = new GetNCLawViolationsEvent();
	        reqEvent.setDefendantId(vrForm.getSuperviseeId());
	        reqEvent.setActivationDate(vrForm.getOrderActivationDate());
	        
	        StringBuffer sb = new StringBuffer();
	        sb.append( vrForm.getCdi() ).append( vrForm.getCaseNum() );
	        reqEvent.setCaseNum( sb.toString() );
	        event.addRequest(reqEvent);
	    	theList = MessageUtil.postRequestListFilter(event, NCLawViolationResponseEvent.class);
		} else {
			theList = vrForm.getCurrentLawViolationsList();
			vrForm.setCreate1Comments(vrForm.getCurrentLawViolationsComments());
		}
        vrForm.setCreate1ElementsList(theList);
 	}

	/**
	 * @param violationReportsForm
	 */
	public static void getLawViolationUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearLawViolationsAdds();
		List theList = new ArrayList();	
		String comments = "";		
		if ((vrForm.getCurrentLawViolationsList() == null || vrForm.getCurrentLawViolationsList().isEmpty())){
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_LAW_VIOLATION);
	        event.setNcResponseId(vrForm.getViolationReportId());
	        event.setActivationDate(vrForm.getOrderActivationDate());
	        
	        GetNCLawViolationsEvent reqEvent = new GetNCLawViolationsEvent();
	        reqEvent.setDefendantId(vrForm.getSuperviseeId());
	        reqEvent.setActivationDate(vrForm.getOrderActivationDate());
	        StringBuffer sb = new StringBuffer();
			sb.append( vrForm.getCdi()).append( vrForm.getCaseNum() );
			
	        reqEvent.setCaseNum( sb.toString() );
	        event.addRequest(reqEvent);

	        CompositeResponse response = MessageUtil.postRequest(event);        
	        theList = MessageUtil.compositeToList(response, NCLawViolationResponseEvent.class);
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);        
	        if(resp != null){
	        	comments = resp.getComments();
	        } else {
	        	comments = vrForm.getCurrentLawViolationsComments();
	        }
		} else {
			theList = vrForm.getCurrentLawViolationsList();	
			comments = vrForm.getCurrentLawViolationsComments();	
		}
        vrForm.setCreate1ElementsList(theList);
       	vrForm.setCreate1Comments(comments);
  	}	
	/**
	 * @param violationReportsForm
	 */
	public static void getFeeHistoryCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearFeeHistoryAdds();
		List theList = new ArrayList();
		if ((vrForm.getCurrentFeeHistoryList() == null || vrForm.getCurrentFeeHistoryList().isEmpty())){
			vrForm.setCreate1Comments("");
	     	GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_DELINQUENT_FEE);
	       
	        GetNCFeesEvent ncReqEvent = new GetNCFeesEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        event.addRequest(ncReqEvent);
	        
	        GetNCFAS1FeesEvent ncFas1FeesEvent = new GetNCFAS1FeesEvent();
	        ncFas1FeesEvent.setSpn( vrForm.getSuperviseeId() );
	        ncFas1FeesEvent.setCaseId(vrForm.getCaseNum());
	        ncFas1FeesEvent.setCdi(vrForm.getCdi());
	        event.addRequest( ncFas1FeesEvent );
	        
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, NCFeeResponseEvent.class);
	        if (theList != null && !theList.isEmpty() ){
	        	for (int f = 0; f < theList.size(); f++){
	        		NCFeeResponseEvent fre = (NCFeeResponseEvent) theList.get(f);
	        		if ("0.00".equals(fre.getAmountOrdered() ) ) {
	        			theList.remove(f);
	        		}
	        	}
	        }
		}else {
			theList = vrForm.getCurrentFeeHistoryList();
			vrForm.setCreate1Comments(vrForm.getCurrentFeeHistoryComments());
		}
        vrForm.setCreate1ElementsList(theList);
	}

	/**
	 * @param violationReportsForm
	 */
	public static void getFeeHistoryUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearFeeHistoryAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((vrForm.getCurrentFeeHistoryList() == null || vrForm.getCurrentFeeHistoryList().isEmpty())){
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_DELINQUENT_FEE);
	        event.setNcResponseId(vrForm.getViolationReportId());
	        
	        GetNCFeesEvent ncReqEvent =  new GetNCFeesEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        event.addRequest(ncReqEvent);
	        
	        GetNCFAS1FeesEvent ncFas1FeesEvent = new GetNCFAS1FeesEvent();
	        ncFas1FeesEvent.setSpn( vrForm.getSuperviseeId() );
	        ncFas1FeesEvent.setCaseId(vrForm.getCaseNum());
	        ncFas1FeesEvent.setCdi(vrForm.getCdi());
	        event.addRequest( ncFas1FeesEvent );
	        
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, NCFeeResponseEvent.class);
	        if (theList != null && !theList.isEmpty() ){
	        	for (int f = 0; f < theList.size(); f++){
	        		NCFeeResponseEvent fre = (NCFeeResponseEvent) theList.get(f);
	        		if ("0.00".equals(fre.getAmountOrdered() ) ) {
	        			theList.remove(f);
	        		}
	        	}
	        }
	        vrForm.setCreate1ElementsList(theList);
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);        
	        if(resp != null){
	        	comments = resp.getComments();
	        } else {
	        	comments = vrForm.getCurrentFeeHistoryComments();
	        }
		} else {
			theList = vrForm.getCurrentFeeHistoryList();
			comments = vrForm.getCurrentFeeHistoryComments();			
		}
        vrForm.setCreate1ElementsList(theList);
		vrForm.setCreate1Comments(comments);
	}
	
	/**
	 * @param violationReportsForm
	 */
	public static void getReportingHistoryCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearReportingHistoryAdds();
		//vrForm.clearReportingHistoryAddressInfo();
		List theList = new ArrayList();	
		String comments = "";
		vrForm.setShowAddress(false);
		if ((vrForm.getCurrentReportingHistoryList() == null || vrForm.getCurrentReportingHistoryList().isEmpty())){
			comments = vrForm.getCurrentReportingHistoryComments();
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
	        GetNCReportingEvent ncReqEvent = new GetNCReportingEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        ncReqEvent.setDefendantId(vrForm.getSuperviseeId());
	        event.addRequest(ncReqEvent);
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class);        
	        NCLastKnownAddressResponseEvent addressResp =
	            (NCLastKnownAddressResponseEvent) MessageUtil.filterComposite(response, NCLastKnownAddressResponseEvent.class);
	        if (addressResp != null){
	        	vrForm.setLastContactDate(addressResp.getLastContactDate());
	        	vrForm.setAddressNumber(addressResp.getStreetNumber());
	        	vrForm.setAddressName(addressResp.getStreetName());
	        	vrForm.setAddressCity(addressResp.getCity());        	
	        	vrForm.setAddressState(addressResp.getState());
	        	vrForm.setAddressStateId(addressResp.getStateId());
	        	vrForm.setAddressZipCode(addressResp.getZip());
	        	vrForm.setAddressType(addressResp.getAddressType());
	        	vrForm.setAddressTypeId(addressResp.getAddressTypeId());
	        	vrForm.setShowAddress(true);
	        } 
		} else { 
			theList = vrForm.getCurrentReportingHistoryList();
			if(theList != null && !theList.isEmpty()){
				StringBuffer addressInfo = new StringBuffer();
				addressInfo.append((vrForm.getAddressNumber() == null)?"":vrForm.getAddressNumber());
				addressInfo.append((vrForm.getAddressName() == null)?"":vrForm.getAddressName());
				addressInfo.append((vrForm.getAddressCity() == null)?"":vrForm.getAddressCity());
				addressInfo.append((vrForm.getAddressState() == null)?"":vrForm.getAddressState());
				addressInfo.append((vrForm.getAddressZipCode() == null)?"":vrForm.getAddressZipCode());
				addressInfo.append((vrForm.getLastContactDate() == null)?"":vrForm.getLastContactDate());
				addressInfo.append((vrForm.getAddressType() == null)?"":vrForm.getAddressType());
				if(addressInfo.toString().length() > 0){
					vrForm.setShowAddress(true);
				}
			}
			comments = vrForm.getCurrentReportingHistoryComments();
	    }
		vrForm.setCreate1ElementsList(theList);
		vrForm.setCreate1Comments(comments);	
	}

	/**
	 * @param violationReportsForm
	 */
	public static void getReportingHistoryUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearReportingHistoryAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((vrForm.getCurrentReportingHistoryList() == null || vrForm.getCurrentReportingHistoryList().isEmpty())){
			vrForm.setShowAddress(false);
			vrForm.clearReportingHistoryAddressInfo();	
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
	        event.setNcResponseId(vrForm.getViolationReportId());
	        
	        GetNCReportingEvent ncReqEvent = new GetNCReportingEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        ncReqEvent.setDefendantId(vrForm.getSuperviseeId());
	        event.addRequest(ncReqEvent);
	        
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class);
	        vrForm.setCreate1ElementsList(theList);
	        NCLastKnownAddressResponseEvent addressResp =
	            (NCLastKnownAddressResponseEvent) MessageUtil.filterComposite(response, NCLastKnownAddressResponseEvent.class);
	        if (addressResp != null){
	        	vrForm.setLastContactDate(addressResp.getLastContactDate());
	        	vrForm.setAddressNumber(addressResp.getStreetNumber());
	        	vrForm.setAddressName(addressResp.getStreetName());
	        	vrForm.setAddressCity(addressResp.getCity());        	
	        	vrForm.setAddressState(addressResp.getState());
	        	vrForm.setAddressStateId(addressResp.getStateId());
	        	vrForm.setAddressZipCode(addressResp.getZip());
	        	vrForm.setAddressType(addressResp.getAddressType());
	        	vrForm.setAddressTypeId(addressResp.getAddressTypeId());
	        	vrForm.setShowAddress(true);
	        }
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);        
	        if(resp != null && resp.getComments() != null){
	        	comments = resp.getComments();
	        } else {
	        	comments = vrForm.getCurrentReportingHistoryComments();
	        }
		} else {
			theList = vrForm.getCurrentReportingHistoryList();
			if(theList != null && !theList.isEmpty()){
				StringBuffer addressInfo = new StringBuffer();
				addressInfo.append((vrForm.getAddressNumber() == null)?"":vrForm.getAddressNumber());
				addressInfo.append((vrForm.getAddressName() == null)?"":vrForm.getAddressName());
				addressInfo.append((vrForm.getAddressCity() == null)?"":vrForm.getAddressCity());
				addressInfo.append((vrForm.getAddressState() == null)?"":vrForm.getAddressState());
				addressInfo.append((vrForm.getAddressZipCode() == null)?"":vrForm.getAddressZipCode());
				addressInfo.append((vrForm.getLastContactDate() == null)?"":vrForm.getLastContactDate());
				addressInfo.append((vrForm.getAddressType() == null)?"":vrForm.getAddressType());
				if(addressInfo.toString().length() > 0){
					vrForm.setShowAddress(true);
				}
			}
			comments = vrForm.getCurrentReportingHistoryComments();
		}
		vrForm.setCreate1ElementsList(theList);
		vrForm.setCreate1Comments(comments);
	}
	
	/**
	 * @param violationReportsForm
	 */
	public static void getEmploymentHistoryCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearEmploymentHistoryAdds();
		List theList = new ArrayList();
		String theComments = "";
		if ((vrForm.getCurrentEmploymentHistoryList() == null || vrForm.getCurrentEmploymentHistoryList().isEmpty())){
	     	GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
	        event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
	        GetNCEmploymentsEvent ncReqEvent = new GetNCEmploymentsEvent();
	        ncReqEvent.setDefandantId(vrForm.getSuperviseeId());
	        event.addRequest(ncReqEvent);
	        CompositeResponse response = MessageUtil.postRequest(event);
	        Collection reports = MessageUtil.compositeToCollection(response, NCEmploymentResponseEvent.class);
	        theList = new ArrayList(reports);		        
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);        
	        if(resp != null && resp.getComments() != null){
	        	theComments = resp.getComments();
	        }
		} else {
			theList = vrForm.getCurrentEmploymentHistoryList();
			theComments = vrForm.getCurrentEmploymentHistoryComments();
		}
	    vrForm.setCreate1ElementsList(theList);
	    vrForm.setCreate1Comments(theComments);
	}

	/**
	 * @param violationReportsForm
	 */
	public static void getEmploymentHistoryUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearEmploymentHistoryAdds();
		List theList = new ArrayList();
		String comments = "";
		
		if ( (vrForm.getCurrentEmploymentHistoryList() == null || vrForm.getCurrentEmploymentHistoryList().isEmpty())){
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.UPDATE_MODE);
			event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
			event.setNcResponseId(vrForm.getViolationReportId());
			
			GetNCEmploymentsEvent ncReqEvent = new GetNCEmploymentsEvent();
	        ncReqEvent.setDefandantId(vrForm.getSuperviseeId());
	        event.addRequest(ncReqEvent);
        
			CompositeResponse response = MessageUtil.postRequest(event);
			theList = MessageUtil.compositeToList(response, NCEmploymentResponseEvent.class);
			NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);
			if(resp != null && resp.getComments() != null){
				comments = resp.getComments();
			}
			if (comments.equals("") && !vrForm.getCurrentEmploymentHistoryComments().equals("")){
				comments = vrForm.getCurrentEmploymentHistoryComments();
			}
		} else {
			theList = vrForm.getCurrentEmploymentHistoryList();
			comments = vrForm.getCurrentEmploymentHistoryComments();
		}
		if (!theList.isEmpty()){
			for (int t=0; t< theList.size(); t++){
				NCEmploymentResponseEvent ncre = (NCEmploymentResponseEvent) theList.get(t);
				if ((ncre.getStatusDesc() == null || ncre.getStatusDesc().equals("") && ncre.getStatusId() != null)){
					for (int x=0; x<vrForm.getEmploymentStatusList().size(); x++){
						CodeResponseEvent cre = (CodeResponseEvent) vrForm.getEmploymentStatusList().get(x);
						if (cre.getCodeId().equals(ncre.getStatusId())){
							ncre.setStatusDesc(cre.getDescription());
							break;	
						}
					}
				}
			}
		}
		vrForm.setCreate1ElementsList(theList);
		vrForm.setCreate1Comments(comments);
	}
	
	/**
	 * @param violationReportsForm
	 */
	public static void getPreviousCourtActivityCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearViolationReportsAdds();
		vrForm.clearMotionsAdds();
		vrForm.clearOthersAdds();		
		List theVList = new ArrayList();
		List theMList = new ArrayList();
		List theOList = new ArrayList();
		String vComments = "";
		String mComments = "";
		String oComments = "";
		vrForm.setShowVRAddFields(false);
		vrForm.setShowMotionAddFields(false);
		vrForm.setCursorPosition("");
// add code for response event
		if ((vrForm.getCurrentCourtActivityVRList() == null || vrForm.getCurrentCourtActivityVRList().isEmpty()) &&
			(vrForm.getCurrentMotionsList() == null || vrForm.getCurrentMotionsList().isEmpty()) &&
			(vrForm.getCurrentOthersList() == null || vrForm.getCurrentOthersList().isEmpty())){
				GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
		        event.setMode(ViolationReportConstants.CREATE_MODE);
		        event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
		        GetNCCourtActivitiesEvent ncReqEvent =  new GetNCCourtActivitiesEvent();
		        ncReqEvent.setCaseId(vrForm.getCaseNum());
		        ncReqEvent.setCdi(vrForm.getCdi());

		        event.addRequest(ncReqEvent);
		        CompositeResponse response = MessageUtil.postRequest(event);
		
		        Collection reports = MessageUtil.compositeToCollection(response, NCPreviousCourtActivityResponseEvent.class);
		        if (reports != null){
		        	Iterator rptIter = reports.iterator();
		        	while(rptIter.hasNext()){
		        		NCPreviousCourtActivityResponseEvent prare = (NCPreviousCourtActivityResponseEvent) rptIter.next();
		        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION.equalsIgnoreCase(prare.getSubType())){ 
		        			theVList.add(prare);
		        		}
		        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION.equalsIgnoreCase(prare.getSubType())){
		        			theMList.add(prare);
		        		}
		        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER.equalsIgnoreCase(prare.getSubType())){
		        			theOList.add(prare);
		        		}
		        	}
		        }
		} else {
			theVList = vrForm.getCurrentCourtActivityVRList();
			theMList = vrForm.getCurrentMotionsList();
			theOList = vrForm.getCurrentOthersList();
	        vComments = vrForm.getCurrentCourtActivityVRComments();
	        mComments = vrForm.getCurrentMotionsComments();
	        oComments = vrForm.getCurrentOthersComments();
		}
        vrForm.setCreate1ElementsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(theVList));
        vrForm.setCreate2ElementsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(theMList));
        vrForm.setCreate3ElementsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(theOList));
        vrForm.setCreate1Comments(vComments);
        vrForm.setCreate2Comments(mComments);
        vrForm.setCreate3Comments(oComments);
	}

	/**
	 * @param violationReportsForm
	 */
	public static void getPreviousCourtActivityUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearViolationReportsAdds();
		vrForm.clearMotionsAdds();
		vrForm.clearOthersAdds();
		List theVList = new ArrayList();
		List theMList = new ArrayList();
		List theOList = new ArrayList();
		String vComments = "";
		String mComments = "";
		String oComments = "";
		vrForm.setShowVRAddFields(false);
		vrForm.setShowMotionAddFields(false);
		vrForm.setCursorPosition("");
		if (vrForm.getCurrentCourtActivityVRList() == null || vrForm.getCurrentCourtActivityVRList().isEmpty() ||
			vrForm.getCurrentMotionsList() == null || vrForm.getCurrentMotionsList().isEmpty() ||
			vrForm.getCurrentOthersList() == null || vrForm.getCurrentOthersList().isEmpty()){
				GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
				event.setMode(ViolationReportConstants.CREATE_MODE);
				event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
		        event.setNcResponseId(vrForm.getViolationReportId());
		        
		        GetNCCourtActivitiesEvent ncReqEvent =  new GetNCCourtActivitiesEvent();
		        ncReqEvent.setCaseId(vrForm.getCaseNum());
		        ncReqEvent.setCdi(vrForm.getCdi());
		        event.addRequest(ncReqEvent);
		        
		        CompositeResponse response = MessageUtil.postRequest(event);
		        List list = MessageUtil.compositeToList(response, NCPreviousCourtActivityResponseEvent.class);
		        List commentList = MessageUtil.compositeToList(response, NCResponseResponseEvent.class);
		        int len = list.size();
		        for(int x=0; x <len; x++ ){
		        	NCPreviousCourtActivityResponseEvent ncpe = (NCPreviousCourtActivityResponseEvent) list.get(x);
	        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION.equalsIgnoreCase(ncpe.getSubType())){ 
	        			theVList.add(ncpe);
	        		}
	        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION.equalsIgnoreCase(ncpe.getSubType())){
	        			theMList.add(ncpe);
	        		}
	        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER.equalsIgnoreCase(ncpe.getSubType())){
	        			theOList.add(ncpe);
	        		}
		        }
		        len = commentList.size();
		        for(int x=0; x <len; x++ ){
		        	NCResponseResponseEvent ncpe = (NCResponseResponseEvent) commentList.get(x);
		        	if (ncpe.getCommentType().equalsIgnoreCase(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION )){
		        		vComments = ncpe.getComments();
		        	}else 
		        		if (ncpe.getCommentType().equalsIgnoreCase(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_MOTION )){
		        		mComments = ncpe.getComments();
		        		} else {
		        			oComments = ncpe.getComments();
		        			}
		        }
		}   
		if (vrForm.getCurrentCourtActivityVRList() != null && !vrForm.getCurrentCourtActivityVRList().isEmpty()){
			theVList = vrForm.getCurrentCourtActivityVRList();
		}
		if (vrForm.getCurrentMotionsList() != null && !vrForm.getCurrentMotionsList().isEmpty()){
			theMList = vrForm.getCurrentMotionsList();
		}
		if (vrForm.getCurrentOthersList() != null && !vrForm.getCurrentOthersList().isEmpty()){
			theOList = vrForm.getCurrentOthersList();
		}
		if (!vrForm.getCurrentCourtActivityVRComments().equals("")){
			vComments = vrForm.getCurrentCourtActivityVRComments();
		}
		if (!vrForm.getCurrentMotionsComments().equals("")){
			mComments = vrForm.getCurrentMotionsComments();
		}
		if (!vrForm.getCurrentOthersComments().equals("")){
			oComments = vrForm.getCurrentOthersComments();
		}
		vrForm.setCreate1ElementsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(theVList));
        vrForm.setCreate2ElementsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(theMList));
        vrForm.setCreate3ElementsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(theOList));
        vrForm.setCreate1Comments(vComments);
        vrForm.setCreate2Comments(mComments);
        vrForm.setCreate3Comments(oComments);
	}	
	/**
	 * @param violationReportsForm
	 */
	public static void getTreatmentIssuesCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearTreatmentIssuesAdds();
		List theList = new ArrayList();
		String comments = "";
		if ((vrForm.getCurrentTreatmentIssuesList() == null || vrForm.getCurrentTreatmentIssuesList().isEmpty())){
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
	        GetNCTreatmentIssuesEvent ncReqEvent = new GetNCTreatmentIssuesEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        event.addRequest(ncReqEvent);
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, CSProgramReferralResponseEvent.class);
		} else {
			theList = vrForm.getCurrentTreatmentIssuesList();
			comments = vrForm.getCurrentTreatmentIssuesComments();
		}
		UIViolationReportHelper.sortTreatmentIssuesList(theList);
	    vrForm.setCreate1ElementsList(loadDischargeReasonDescriptions(theList, vrForm.getDischargeReasons()));
        vrForm.setCreate1Comments(comments);
	}

	/**
	 * @param violationReportsForm
	 */
	public static void getTreatmentIssuesUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearTreatmentIssuesAdds();
		List theList = new ArrayList();
		if (vrForm.getCurrentTreatmentIssuesList() == null || vrForm.getCurrentTreatmentIssuesList().isEmpty()){
			GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
			event.setMode(ViolationReportConstants.UPDATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
	        event.setNcResponseId( vrForm.getViolationReportId() );
	        
	        GetNCTreatmentIssuesEvent ncReqEvent = new GetNCTreatmentIssuesEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        event.addRequest(ncReqEvent);
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, CSProgramReferralResponseEvent.class);
		} else {
			theList = vrForm.getCurrentTreatmentIssuesList();
		}
		UIViolationReportHelper.sortTreatmentIssuesList(theList);
		vrForm.setCreate1ElementsList(loadDischargeReasonDescriptions(theList, vrForm.getDischargeReasons()));
		vrForm.setCreate1Comments( vrForm.getCurrentTreatmentIssuesComments() );
	}	

	/**
	 * @param violationReportsForm
	 */	
	public static void getCommunityServiceCreateInfo(ViolationReportsForm vrForm){
		if ((vrForm.getCurrentHoursOrdered() != null && vrForm.getCurrentHoursOrdered().equals("") &&
			 vrForm.getCurrentHoursCompleted() != null && vrForm.getCurrentHoursCompleted().equals("") &&
			 vrForm.getCurrentCommunityServiceComments() != null && vrForm.getCurrentCommunityServiceComments().equals(""))){
				vrForm.setCreate1Comments("");
				GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
				event.setMode(ViolationReportConstants.CREATE_MODE);
		        event.setRequestType(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE);
		        
		        GetNCCommunityServicesEvent ncReqEvent = new GetNCCommunityServicesEvent();
		        ncReqEvent.setCdi(vrForm.getCdi());
		        ncReqEvent.setCaseId(vrForm.getCaseNum());
		        event.addRequest(ncReqEvent);
		        List theList = MessageUtil.postRequestListFilter(event, NCCommunityServiceResponseEvent.class);
		        for (int x =0; x < theList.size(); x++){
					NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent) theList.get(x);
					vrForm.setHoursOrdered(csre.getHoursOrdered());
					vrForm.setHoursCompleted(csre.getHoursCompleted());
					break;
				}
		} else {
			vrForm.setHoursOrdered(vrForm.getCurrentHoursOrdered());
			vrForm.setHoursCompleted(vrForm.getCurrentHoursCompleted());
			vrForm.setCreate1Comments(vrForm.getCurrentCommunityServiceComments());
		}
	}	

	/**
	 * @param violationReportsForm
	 */	
	public static void getCommunityServiceUpdateInfo(ViolationReportsForm vrForm){
		if ((vrForm.getCurrentHoursOrdered() != null && vrForm.getCurrentHoursOrdered().equals("") &&
			 vrForm.getCurrentHoursCompleted() != null && vrForm.getCurrentHoursCompleted().equals("") &&
			 vrForm.getCurrentCommunityServiceComments() != null && vrForm.getCurrentCommunityServiceComments().equals(""))){
				vrForm.setCreate1Comments("");
				GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
				event.setMode(ViolationReportConstants.UPDATE_MODE);
		        event.setRequestType(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE);
		        event.setNcResponseId( vrForm.getViolationReportId() );
		        
		        GetNCCommunityServicesEvent ncReqEvent = new GetNCCommunityServicesEvent();
		        ncReqEvent.setCdi(vrForm.getCdi());
		        ncReqEvent.setCaseId(vrForm.getCaseNum());
		        event.addRequest(ncReqEvent);
		        List theList = MessageUtil.postRequestListFilter(event, NCCommunityServiceResponseEvent.class);
		        for (int x =0; x < theList.size(); x++){
					NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent) theList.get(x);
					vrForm.setHoursOrdered(csre.getHoursOrdered());
					vrForm.setHoursCompleted(csre.getHoursCompleted());
					break;
				}
		} else {
			vrForm.setHoursOrdered(vrForm.getCurrentHoursOrdered());
			vrForm.setHoursCompleted(vrForm.getCurrentHoursCompleted());
			vrForm.setCreate1Comments(vrForm.getCurrentCommunityServiceComments());
		}
	}		

	/**
	 * @param violationReportsForm
	 */
	public static void getPositiveUrinalysisCreateInfo(ViolationReportsForm vrForm){
		vrForm.clearPositiveUrinalysisAdds();
		List theList = new ArrayList();
		String totalSA = "";
		String comments = "";
		if ((vrForm.getCurrentPositiveUrinalysisList() == null || vrForm.getCurrentPositiveUrinalysisList().isEmpty()) ){
			vrForm.setCreate1Comments("");
	        GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
	        event.setMode(ViolationReportConstants.CREATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_POSITIVE_UA);
	        GetNCPositiveUrinalysisEvent ncReqEvent = new GetNCPositiveUrinalysisEvent();
	
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        ncReqEvent.setDefendantId(vrForm.getSuperviseeId());
	        event.addRequest(ncReqEvent);
	
	        CompositeResponse response = MessageUtil.postRequest(event);
	        Collection reports = MessageUtil.compositeToCollection(response, NonComplianceEventResponseEvent.class);
	        theList = new ArrayList(reports);
	        if (theList == null || theList.isEmpty()){
	        	if (!vrForm.getCurrentPositiveUrinalysisComments().equals( null) && !vrForm.getCurrentPositiveUrinalysisComments().equals("")){
	        		comments = vrForm.getCurrentPositiveUrinalysisComments();
	        	}
	        }
	        // need to get this total value from response -- violationReport record 
	        totalSA = "";
		} else {
			theList = vrForm.getCurrentPositiveUrinalysisList();
			totalSA = vrForm.getCurrentTotalSpecimensAnalyzed();
			comments = vrForm.getCurrentPositiveUrinalysisComments();			
		}
		vrForm.setTotalSpecimensAnalyzed(totalSA);
        vrForm.setCreate1ElementsList(theList);
        vrForm.setCreate1Comments(comments);
	}

	/**
	 * @param violationReportsForm
	 */
	public static void getPositiveUrinalysisUpdateInfo(ViolationReportsForm vrForm){
		vrForm.clearPositiveUrinalysisAdds();
		List theList = new ArrayList();
		String comments = "";
		String curTotalSpecimens = vrForm.getCurrentTotalSpecimensAnalyzed();
		if ((vrForm.getCurrentPositiveUrinalysisList() == null || vrForm.getCurrentPositiveUrinalysisList().isEmpty()) ){		
	        GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
	        event.setMode(ViolationReportConstants.UPDATE_MODE);
	        event.setRequestType(ViolationReportConstants.REQUEST_POSITIVE_UA);
	        event.setNcResponseId(vrForm.getViolationReportId());

	        GetNCPositiveUrinalysisEvent ncReqEvent = new GetNCPositiveUrinalysisEvent();
	        ncReqEvent.setCaseId(vrForm.getCaseNum());
	        ncReqEvent.setCdi(vrForm.getCdi());
	        ncReqEvent.setDefendantId(vrForm.getSuperviseeId());
	        event.addRequest(ncReqEvent);
	        
	        CompositeResponse response = MessageUtil.postRequest(event);
	        theList = MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class);
// need to get this total value
	        vrForm.setTotalSpecimensAnalyzed("");        
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);
	        if(resp != null){
	        	comments = resp.getComments();
	        } else {
	        	comments = vrForm.getCurrentPositiveUrinalysisComments();
	        }
		} else {
			theList = vrForm.getCurrentPositiveUrinalysisList();
			comments = vrForm.getCurrentPositiveUrinalysisComments();
			//totalSA = vrForm.getCurrentTotalSpecimensAnalyzed();
		}
        vrForm.setCreate1ElementsList(theList);
        vrForm.setTotalSpecimensAnalyzed( curTotalSpecimens );
        vrForm.setCreate1Comments(comments);
	}	

	/**
	 * @param violationReportsForm
	 */
	public static void getRecommendationsCreateInfo(ViolationReportsForm vrForm){
		List theList = vrForm.getActiveSuggestedCourtActions();
		if (vrForm.getCurrentRecommendations() == null || vrForm.getCurrentRecommendations().equals("")){
			vrForm.setCreate1Comments("");
			for (int x = 0; x<vrForm.getSuggestedCourtActions().size(); x++){
				CodeResponseEvent t3 = (CodeResponseEvent) vrForm.getSuggestedCourtActions().get(x);
				t3.setCodeId("");
			}
		} else {
			vrForm.setCreate1Comments(vrForm.getCurrentRecommendations());
			int len = vrForm.getCurrentSuggestedCourtActionsList().size();
			int listSize = theList.size();
	        for(int x=0; x <len; x++ ){
	        	CodeResponseEvent csca = (CodeResponseEvent) vrForm.getCurrentSuggestedCourtActionsList().get(x);
	        	for (int y=0; y < listSize; y++){
	        		CodeResponseEvent tl = (CodeResponseEvent) theList.get(y);
	        		if (csca.getCode().equals(tl.getCode())){
	        			tl.setVisible(true);
	        			break;
	        		}
	        	}
	        }	
		}
		vrForm.setCreate1ElementsList(theList);
	}		

	/**
	 * @param violationReportsForm
	 */
	public static void getRecommendationsUpdateInfo(ViolationReportsForm vrForm){
		String recommendations = "";
		List theList = new ArrayList();
		List activeList = new ArrayList();
		List tempList = vrForm.getActiveSuggestedCourtActions();

		//reset codelist visible value in prep for display
		for ( int x=0; x < tempList.size(); x++ ){
			CodeResponseEvent cre = (CodeResponseEvent) tempList.get(x);
			cre.setVisible(false);
			activeList.add( x , cre);
		}
		vrForm.setActiveSuggestedCourtActions( new ArrayList( activeList) );
		
		if (vrForm.getCurrentRecommendations() == null || vrForm.getCurrentRecommendations().equals("")){
	        GetNCResponseComponentsEvent event = (GetNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSECOMPONENTS);
	        event.setRequestType(ViolationReportConstants.REQUEST_RECOMMENDATION);
	        event.setNcResponseId(vrForm.getViolationReportId());

	        CompositeResponse response = MessageUtil.postRequest(event);

	        theList = MessageUtil.compositeToList(response, CodeResponseEvent.class);
	        Collection unFoundList = new ArrayList();
			for (int x = 0; x<theList.size(); x++){
				CodeResponseEvent ncrre = (CodeResponseEvent) theList.get(x);
				boolean isFlag = false;
				for (int y=0; y < vrForm.getActiveSuggestedCourtActions().size(); y++){
					CodeResponseEvent tl = (CodeResponseEvent) vrForm.getActiveSuggestedCourtActions().get(y);
	        		if (ncrre.getCode().equalsIgnoreCase(tl.getCode()) && !ncrre.getTransaction().equalsIgnoreCase("FL")){
	        			tl.setVisible(true);
	        			isFlag = true;						
	        		}
	        	}
				if(!isFlag && !ncrre.getTransaction().equalsIgnoreCase("FL")){
					CodeResponseEvent t2 = new CodeResponseEvent();
					t2.setCode(ncrre.getCode());
					t2.setDescription(ncrre.getDescription());
					t2.setCodeId(ncrre.getCodeId());
					t2.setTransaction(ncrre.getTransaction());
					t2.setVisible(true);
					unFoundList.add(t2);
				}
			}
			theList = vrForm.getActiveSuggestedCourtActions();  //should contain selected values
			theList.addAll(unFoundList);
			
			for (int x = 0; x<vrForm.getActiveSuggestedCourtActions().size(); x++){
				CodeResponseEvent t3 = (CodeResponseEvent) vrForm.getActiveSuggestedCourtActions().get(x);
				if(!t3.isVisible()){
				    t3.setCodeId("");
				}
			}
	        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);        
	        if(resp != null){
	        	recommendations = resp.getComments();
	        } else {
	        	recommendations = vrForm.getCurrentRecommendations();
	        }
		} else {
			recommendations = vrForm.getCurrentRecommendations();
			if (vrForm.getCurrentSuggestedCourtActionsList().isEmpty()){
				theList = vrForm.getActiveSuggestedCourtActions(); //should contain NO selected values
			} else {
				int matchCount = 0;
				for (int c =0; c < vrForm.getCurrentSuggestedCourtActionsList().size(); c++){
					CodeResponseEvent ce = (CodeResponseEvent) vrForm.getCurrentSuggestedCourtActionsList().get(c);
					for (int r=0; r < vrForm.getActiveSuggestedCourtActions().size(); r++){
						CodeResponseEvent re = (CodeResponseEvent) vrForm.getActiveSuggestedCourtActions().get(r);
						if (ce.getCode().equalsIgnoreCase(re.getCode())){
							re.setVisible(true);
							ce.setVisible(true);
							matchCount++;
						}
					}
				}
				theList = vrForm.getActiveSuggestedCourtActions();
				if (matchCount != vrForm.getCurrentSuggestedCourtActionsList().size()){
					for (int c =0; c < vrForm.getCurrentSuggestedCourtActionsList().size(); c++){
						CodeResponseEvent ce = (CodeResponseEvent) vrForm.getCurrentSuggestedCourtActionsList().get(c);
						if (ce.isVisible() == false){
							for (int r=0; r < vrForm.getSuggestedCourtActions().size(); r++){
								CodeResponseEvent re = (CodeResponseEvent) vrForm.getSuggestedCourtActions().get(r);
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
		}
		vrForm.setCreate1Comments(recommendations);
		vrForm.setCreate1ElementsList(UIViolationReportHelper.sortCourtActionsList(theList));
	}		
	
	/**
	 * Retrieve the information for CourtActions for Presented Update of Court Action choices
	 * @param violationReportsForm
	 */
	public static void getUpdateCourtActionsUpdateInfo(ViolationReportsForm vrForm){
		List theList = new ArrayList();
		List activeList = new ArrayList();
		List tempList = vrForm.getActiveSuggestedCourtActions();

		// set the field courtActionfiledDate with the value for fileDateStr
		if(vrForm.getFileDateStr() != null){
			vrForm.setCourtActionfiledDate(vrForm.getFileDateStr());
		}
		//reset codelist visible value in prep for display
		for ( int x=0; x < tempList.size(); x++ ){
			CodeResponseEvent cre = (CodeResponseEvent) tempList.get(x);
			cre.setVisible(false);
			activeList.add( x , cre);
		}
		vrForm.setActiveSuggestedCourtActions( new ArrayList( activeList) );
		// new code


		if (vrForm.getCurrentCourtActionsList().isEmpty()){
			theList = vrForm.getActiveSuggestedCourtActions(); //should contain NO selected values
		} else {
			int matchCount = 0;
			for (int c =0; c < vrForm.getCurrentCourtActionsList().size(); c++){
				CodeResponseEvent ce = (CodeResponseEvent) vrForm.getCurrentCourtActionsList().get(c);
				for (int r=0; r < vrForm.getActiveSuggestedCourtActions().size(); r++){
					CodeResponseEvent re = (CodeResponseEvent) vrForm.getActiveSuggestedCourtActions().get(r);
					if (ce.getCode().equalsIgnoreCase(re.getCode())){
						re.setVisible(true);
						ce.setVisible(true);
						matchCount++;
					}
				}
			}
			theList = vrForm.getActiveSuggestedCourtActions();
			if (matchCount != vrForm.getCurrentCourtActionsList().size()){
				for (int c =0; c < vrForm.getCurrentCourtActionsList().size(); c++){
					CodeResponseEvent ce = (CodeResponseEvent) vrForm.getCurrentCourtActionsList().get(c);
					if (ce.isVisible() == false){
						for (int r=0; r < vrForm.getCourtActions().size(); r++){
							CodeResponseEvent re = (CodeResponseEvent) vrForm.getCourtActions().get(r);
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
	
		vrForm.setCreate1ElementsList(UIViolationReportHelper.sortCourtActionsList(theList));
		

	}		
	
	/**
	 * @param violationReportsForm
	 */
	public static void LoadCurrentVRInfo(ViolationReportsForm vrForm) {
		GetNCResponseDetailsEvent event = (GetNCResponseDetailsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.GETNCRESPONSEDETAILS);
        event.setNcResponseId(vrForm.getViolationReportId());
        CompositeResponse response = MessageUtil.postRequest(event);
        NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil.filterComposite(response, NCResponseResponseEvent.class);
        vrForm.setTaskId( resp.getTaskId() );
        // current report header info
        vrForm.setStatusDesc(resp.getStatus());
        vrForm.setStatusId(resp.getStatusId());
        vrForm.setStatusChangedDate(resp.getStatusChangedDate());
        if(resp.getCreatedBy() != null){
        	vrForm.setCreatedByName(SecurityUIHelper.getUserName(resp.getCreatedBy()).toString());
        }
        vrForm.setCreateDate(resp.getCreateDate());
        if (resp.getSubMgrAppDate() != null){
        	vrForm.setSubMgrAppDate(resp.getSubMgrAppDate());
        }
        vrForm.setTotalSpecimensAnalyzed(resp.getTotalSpecimenAnalyzed());
        // clear temporary work values        
        vrForm.setCreate1ElementsList(new ArrayList());
        vrForm.setCreate1Comments("");
        vrForm.setCreate2ElementsList(new ArrayList());
        vrForm.setCreate2Comments("");
        vrForm.setCreate3ElementsList(new ArrayList());
        vrForm.setCreate3Comments("");
        // filing info (only present when VR status is filed )
        if (resp.getFiledDate() != null){
        	vrForm.setFileDateStr(DateUtil.dateToString(new Date(resp.getFiledDate().getTime()), DateUtil.DATE_FMT_1));
        }
        vrForm.setWhoSignedName(resp.getSignedBy());
        vrForm.setWhoSignedId(resp.getPositionIdOfSignedBy());
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
			vrForm.setPresentedById(staffResponse.getAssignedLogonId());        
	     // current logged in user
		}else if(jimsId != null && !jimsId.equals("")){
			vrForm.setPresentedById(jimsId);	        
		}
        
        vrForm.setPresentedByName(presentedBy);
        // reason for transfer
        List rftre = MessageUtil.compositeToList(response,
                                        ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER);

        List comments = (List) MessageUtil.compositeToList( response, NCCommentResponseEvent.class );

        for ( int ctr =0; ctr <comments.size();ctr++){
        	NCCommentResponseEvent commentResp = (NCCommentResponseEvent) comments.get(ctr);
        	if ("RFT".equalsIgnoreCase( commentResp.getReportType() ) ){
        		vrForm.setIsExtended( commentResp.getComment() );
        		break;
	        }
        }
        if (rftre != null && !rftre.isEmpty()) {
                        vrForm.setCurrentReasonForTransferList(rftre);
        } else {
                        vrForm.setCurrentReasonForTransferList(new ArrayList());
        }
// law violatoins       
      List lvre = MessageUtil.compositeToList(response, NCLawViolationResponseEvent.class);
      if (lvre != null && !lvre.isEmpty()){
     		vrForm.setCurrentLawViolationsList(UIViolationReportHelper.sortLawViolationList(lvre));
      }
// fee history
        List fhre = MessageUtil.compositeToList(response, NCFeeResponseEvent.class); 
        if (fhre != null ){
        	vrForm.setCurrentFeeHistoryList(UIViolationReportHelper.sortFeeHistoryList(fhre));
        }
// reporting history and positive urinalysis
        List rptList = new ArrayList();
        List puList = new ArrayList();
        List ncere = MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class);
        if (ncere != null){
        	for (int x = 0; x <ncere.size(); x++){
        		NonComplianceEventResponseEvent event2 = (NonComplianceEventResponseEvent) ncere.get(x);
        		if (event2.getTopic().equalsIgnoreCase(ViolationReportConstants.REQUEST_REPORTING)){ 
        			rptList.add(event2);
        		}
        		if (event2.getTopic().equalsIgnoreCase(ViolationReportConstants.REQUEST_POSITIVE_UA)){
        			puList.add(event2);
        		}
        	}
        }	
        NCLastKnownAddressResponseEvent addressResp =
            (NCLastKnownAddressResponseEvent) MessageUtil.filterComposite(response, NCLastKnownAddressResponseEvent.class);
        if(addressResp != null){
        	vrForm.setLastContactDate(addressResp.getLastContactDate());
        	vrForm.setAddressNumber(addressResp.getStreetNumber());
        	vrForm.setAddressName(addressResp.getStreetName());
        	vrForm.setAddressCity(addressResp.getCity());        	
        	vrForm.setAddressState(addressResp.getState());
        	vrForm.setAddressStateId(addressResp.getStateId());
        	vrForm.setAddressZipCode(addressResp.getZip());
        	vrForm.setAddressType(addressResp.getAddressType());
        	vrForm.setAddressTypeId(addressResp.getAddressTypeId());
        }
// eventtypes may contain eventIds instead of event descriptions        
        if (rptList != null && rptList.size() > 0){
        	reloadEventTypeDescriptions(rptList, vrForm.getEventTypes());
		}
       	vrForm.setCurrentReportingHistoryList(UIViolationReportHelper.sortReportingHistoryList(rptList));
       	vrForm.setCurrentPositiveUrinalysisList(UIViolationReportHelper.sortPositiveUrinalysisList(puList));
       	vrForm.setCurrentTotalSpecimensAnalyzed(resp.getTotalSpecimenAnalyzed());
       	// employment history  
      List ehre = MessageUtil.compositeToList(response, NCEmploymentResponseEvent.class);
      if (ehre != null ){
    	  for (int t=0; t< ehre.size(); t++){
    		  NCEmploymentResponseEvent ncre = (NCEmploymentResponseEvent) ehre.get(t);
    		  if ((ncre.getStatusDesc() == null || ncre.getStatusDesc().equals("") && ncre.getStatusId() != null)){
    			  for (int x=0; x<vrForm.getEmploymentStatusList().size(); x++){
    				  CodeResponseEvent cre = (CodeResponseEvent) vrForm.getEmploymentStatusList().get(x);
    				  if (cre.getCodeId().equals(ncre.getStatusId())){
    					  ncre.setStatusDesc(cre.getDescription());
    					  break;	
    				  }
    			  }
    		  }
    	  }
    	  vrForm.setCurrentEmploymentHistoryList(UIViolationReportHelper.sortEmploymentHistoryListByCLS( ehre ));
      }
      // previous court activity  
      List vrList = new ArrayList();
      List motList = new ArrayList();
      List othList = new ArrayList();      
      List pcare = MessageUtil.compositeToList(response, NCPreviousCourtActivityResponseEvent.class);
      if (pcare != null){
      	for (int x = 0; x <pcare.size(); x++){
      		NCPreviousCourtActivityResponseEvent pcaEvent = (NCPreviousCourtActivityResponseEvent) pcare.get(x);      		
      		if(pcaEvent.getSummaryOfCourtAction() != null && pcaEvent.getSummaryOfCourtAction().length() > 250){
      			pcaEvent.setShortTruncatedSummaryOfCourtAction(truncateComments(pcaEvent.getSummaryOfCourtAction(), 250));
      		}
      		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION.equalsIgnoreCase(pcaEvent.getSubType())){ 
      			vrList.add(pcaEvent);
      		} else if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION.equalsIgnoreCase(pcaEvent.getSubType())){
      				motList.add(pcaEvent);
      		} else if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER.equalsIgnoreCase(pcaEvent.getSubType())){
      			othList.add(pcaEvent);
      		}
      	}
      }	
      // extract literal from code tables for each list      
      for (int m = 0; m < motList.size(); m++){
    	  NCPreviousCourtActivityResponseEvent moEvent = (NCPreviousCourtActivityResponseEvent) motList.get(m);
		  for (int x=0; x<vrForm.getMotionsDispositions().size(); x++){
			  CodeResponseEvent cre2 = (CodeResponseEvent) vrForm.getMotionsDispositions().get(x);
			  if (cre2.getCode().equals(moEvent.getDisposition())){
				  moEvent.setDisposition(cre2.getDescription());
				  break;	
			  }
		  }
      }
      vrForm.setCurrentCourtActivityVRList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(vrList));
      vrForm.setCurrentMotionsList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(motList));
      vrForm.setCurrentOthersList(UIViolationReportHelper.sortPreviousCourtActivityListDescending(othList));      
// treatment issues
        List tire = MessageUtil.compositeToList(response, CSProgramReferralResponseEvent.class);
        if (tire != null ){
        	for (int x = 0; x < tire.size(); x++){
        		CSProgramReferralResponseEvent newEvent = (CSProgramReferralResponseEvent) tire.get(x);
				for (int y=0; y<vrForm.getDischargeReasons().size(); y++){
					CodeResponseEvent cre2 = (CodeResponseEvent) vrForm.getDischargeReasons().get(y);
					if (cre2.getCode().equals(newEvent.getDischargeReason())){
						newEvent.setDischargeReason(cre2.getDescription());
						break;
					}
				}
			}
        	vrForm.setCurrentTreatmentIssuesList(UIViolationReportHelper.sortTreatmentIssuesList(tire));
        }   
// community service  
        NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent)MessageUtil.filterComposite(response, NCCommunityServiceResponseEvent.class);
        if (csre != null){
        	vrForm.setCurrentHoursOrdered(csre.getHoursOrdered());
        	vrForm.setCurrentHoursCompleted(csre.getHoursCompleted());
        }
// recommendations and court actions       
         List rre = MessageUtil.compositeToList(response, ViolationReportConstants.REQUEST_RECOMMENDATION);
         List caList = new ArrayList();
         List recList = new ArrayList();
         if (rre != null){
 			for (int x = 0; x<rre.size(); x++){
 				CodeResponseEvent crex = (CodeResponseEvent) rre.get(x);
				if (ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(crex.getTransaction())){
					caList.add(crex);
				} else {
					recList.add(crex);
				}
			}	
         }	
         if (recList != null){
        	 vrForm.setCurrentSuggestedCourtActionsList(UIViolationReportHelper.sortCourtActionsList(recList));
         }
         if (caList != null){
        	 vrForm.setCurrentCourtActionsList(UIViolationReportHelper.sortCourtActionsList(caList));
         }
// comments        
        List cre = MessageUtil.compositeToList(response, NCCommentResponseEvent.class);
        if (cre != null && cre.size() > 0){
        	for (int x =0; x < cre.size(); x++){
        		NCCommentResponseEvent nccre = (NCCommentResponseEvent) cre.get(x);
        		if (nccre.getReportType() != null){
        			if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER)){
        				vrForm.setCurrentReasonForTransferComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_MENTAL_HEALTH_COMMENTS)){
        				vrForm.setCurrentMentalHealthComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_MENTAL_HEALTH_DIAGNOSIS)){
        				vrForm.setCurrentMentalHealthDiagnosis(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_LAW_VIOLATION)){
        				vrForm.setCurrentLawViolationsComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_DELINQUENT_FEE)){
        				vrForm.setCurrentFeeHistoryComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_REPORTING)){
        				vrForm.setCurrentReportingHistoryComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_EMPLOYMENT)){
        				vrForm.setCurrentEmploymentHistoryComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_TREATMENT)){
        				vrForm.setCurrentTreatmentIssuesComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE)){
        				vrForm.setCurrentCommunityServiceComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_POSITIVE_UA)){
        				vrForm.setCurrentPositiveUrinalysisComments(nccre.getComment());
        			} else if (nccre.getReportType().equalsIgnoreCase("RCM")) {
       	       			if (ViolationReportConstants.SUGGESTED_COURT_ACTION.equalsIgnoreCase(nccre.getCommentType())){
        	    			vrForm.setSummaryOfCourtActions(nccre.getComment());
        	    		} else {        				
        	    			vrForm.setCurrentRecommendations(nccre.getComment());
        	    		}	
        		   	}
        		}	
     		} // end for loop        
     	}
        
        // court actions summary comments (only if the case has already been filed)
        if((resp.getStatusId().equals(ViolationReportConstants.STATUS_FILED)) && (resp.getCourtActionSummary()!=null && 
        		!resp.getCourtActionSummary().equalsIgnoreCase("") ) ){
        	vrForm.setSummaryOfCourtActions(resp.getCourtActionSummary());
        }
	}
	
	/**
	 * Convert the given referral type code to a meaningful description
	 * @param refTypeCode
	 * @return
	 */
	public static String getReferralTypeDesc(String refTypeCode)
	{
		int space_indx = refTypeCode.indexOf(' ');
		String program_group = refTypeCode.substring(0, space_indx);
		String program_type = refTypeCode.substring(space_indx + 1);
		
			//get description for the given program group and type codes
		String program_group_desc =
			SimpleCodeTableHelper.getDescrByCode(
				CSAdministerProgramReferralsConstants.PROGRAM_GROUP_CODE_TABLE, program_group);
		String program_type_desc =
			SimpleCodeTableHelper.getDescrByCode(
					CSAdministerProgramReferralsConstants.PROGRAM_TYPE_CODE_TABLE, program_type);
		
		return program_group_desc + " / " + program_type_desc;
	}//end of getReferralTypeResponse()
	
	/**
	 * 
	 * @param vrForm
	 */
	public static void loadDropDown(ViolationReportsForm vrForm){
		vrForm.setAllowMaintain("");
		if (SecurityUIHelper.isUserSA()){
			vrForm.setAllowMaintain(UIConstants.YES);
		}
// begin code table loading for drop down lists and other code tables
		GetReasonForTransferCodesEvent reasonForTransferEvent = new GetReasonForTransferCodesEvent();
		reasonForTransferEvent.setRequestType(ViolationReportConstants.REPORTTYPE_VIOLATION);
		
		List reasonForTransferList = MessageUtil.postRequestListFilter(reasonForTransferEvent, CodeResponseEvent.class);
		if(reasonForTransferList != null && !reasonForTransferList.isEmpty()){
			vrForm.setReasonForTransferList(reasonForTransferList);
		}else{
			vrForm.setReasonForTransferList(new ArrayList());
		}
		vrForm.setPayTypes(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.PAYTYPE));
// not sure if this is correct for event type drop down for reporting history		
        //List eventTypesList = ComplexCodeTableHelper.getEventTypesByContext("P", true);
        List eventTypesList = CodeHelper.getEventTypes("CSC");
        if (eventTypesList == null){
        	eventTypesList = new ArrayList();
        }
		vrForm.setEventTypes(eventTypesList);
		vrForm.setEmploymentStatusList(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.EMPLOYMENT_STATUS));
//		vrForm.setMotionsActivities(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.MOTION_ACTIVITY));
		List temp = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.MOTION_DISPOSITION);
		vrForm.setMotionsDispositions(loadActiveCodes(temp));
	//	vrForm.setOtherActivities(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.OTHER_ACTIVITY));
		vrForm.setDischargeReasons(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.JIMS2_DISCHARGE_REASON));
		vrForm.setSuggestedCourtActions(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SUGGESTEDCOURTACTION));
		temp = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.SUGGESTEDCOURTACTION);
		vrForm.setActiveSuggestedCourtActions(loadActiveCodes(temp));
// end code table loading	
	}
	
	/**
	 * @param list
	 * return list
	 */
	public static List loadActiveCodes(List theList){
		List rList = new ArrayList();
		for (int t= 0; t < theList.size(); t++){
       		CodeResponseEvent tl = (CodeResponseEvent) theList.get(t);
       		if (tl.getStatus().equalsIgnoreCase("active")){
       			rList.add(tl);
       		}
		}
		return rList;
	}

	/**
	 * Loads missing/blank discharge reasons into event using drop down code table values
	 * @param List of CSProgramReferralResponseEvent
	 * @param List of CodeResponseEvent
	 * @return
	 */
	public static List loadDischargeReasonDescriptions(List theList, List dischargeReasons){
		if (!theList.isEmpty()){
	        for (int x =0; x < theList.size(); x++){
	        	CSProgramReferralResponseEvent csre = (CSProgramReferralResponseEvent) theList.get(x);
	        	if (csre.getDischargeReasonCd() != null && !csre.getDischargeReasonCd().equals("")){
	        		for (int y=0; y<dischargeReasons.size(); y++){
    					CodeResponseEvent cre2 = (CodeResponseEvent) dischargeReasons.get(y);
    					if (csre.getDischargeReasonCd().equals(cre2.getCode()) ){
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
	 * @param violationReportsForm
	 */
	public static void prepareToFileReport(ViolationReportsForm vrForm, String courtNumber) {
		vrForm.setConfirmationMessage("");
		Date theDate = DateUtil.getCurrentDate();
		vrForm.setCourtActionfiledDate(DateUtil.dateToString(theDate,DateUtil.DATE_FMT_1));
		vrForm.setPresentedById("");
		vrForm.setPresentedByFirstName("");
		vrForm.setPresentedByLastName("");
		vrForm.setPresentedByName("");
		vrForm.setWhoSignedId("");
		vrForm.setWhoSignedFirstName("");
		vrForm.setWhoSignedLastName("");
		vrForm.setWhoSignedName("");
		vrForm.setSummaryOfCourtActions("");
// revise presentedby to use code so position displays next to name		
		vrForm.setPresentedByList(CodeHelper.getAllSupervisionStaff(true, vrForm.getCourtNum(),true));
		vrForm.setWhoSignedList(SupervisionOrderListHelper.getJudgeList());
		List theList = vrForm.getActiveSuggestedCourtActions();
		int matchCount = 0;
// flags court action as suggested so it will display blue in jsp, use "status" value in event
       	for (int y=0; y < theList.size(); y++){
       		CodeResponseEvent tl = (CodeResponseEvent) theList.get(y);
       		tl.setStatus("");
    		for (int x = 0; x <vrForm.getCurrentSuggestedCourtActionsList().size(); x++){
            	CodeResponseEvent csca = (CodeResponseEvent) vrForm.getCurrentSuggestedCourtActionsList().get(x);
            	if (csca.getCode().equals(tl.getCode())){
        			tl.setStatus("B");
        			matchCount++;
        			break;
        		}
        	}
		}
// check if a current SCA has become inactive and add to list
		if (matchCount != vrForm.getCurrentSuggestedCourtActionsList().size()){
			for (int c =0; c < vrForm.getCurrentSuggestedCourtActionsList().size(); c++){
				CodeResponseEvent ce = (CodeResponseEvent) vrForm.getCurrentSuggestedCourtActionsList().get(c);
				if (ce.isVisible() == false){
					for (int r=0; r < vrForm.getSuggestedCourtActions().size(); r++){
						CodeResponseEvent re = (CodeResponseEvent) vrForm.getSuggestedCourtActions().get(r);
						if (ce.getCode().equalsIgnoreCase(re.getCode())){
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
//sort in case inactive added
		vrForm.setCreate1ElementsList(UIViolationReportHelper.sortCourtActionsList(theList)); 
// remove leading CR, CC, etc from court number
       	if (courtNumber != null){
       		if (courtNumber.indexOf(" ") > -1){
       			courtNumber = courtNumber.substring(courtNumber.indexOf(" ") + 2, courtNumber.length());
       		}
       	}
        // determine the user to use for the presentedBy field.  Use existing chosen user.
        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        String jimsId = userInfo.getJIMSLogonId();   
		vrForm.setPresentedById(jimsId);	        
		// set the signed id to the court of the staff member
		vrForm.setWhoSignedId(courtNumber);
	}
	
	/**
	 * get the presented and signed information for top of court actions section
	 * @param violationReportsForm
	 */
	public static void getPresentedSignedInformation(ViolationReportsForm vrForm, String courtNumber) {
		// revise presentedby to use code so position displays next to name		
		vrForm.setPresentedByList(CodeHelper.getAllSupervisionStaff(true, vrForm.getCourtNum(),true));
		vrForm.setWhoSignedList(SupervisionOrderListHelper.getJudgeList());
		// remove leading CR, CC, etc from court number
       	if (courtNumber != null){
       		if (courtNumber.indexOf(" ") > -1){
       			courtNumber = courtNumber.substring(courtNumber.indexOf(" ") + 2, courtNumber.length());
       		}
       	}
       	// get the court number for the signedBy 
       	String currentSignedBy = vrForm.getWhoSignedName();
       	for(Object event:vrForm.getWhoSignedList()){
       		JudgeResponseEvent courtInfo = (JudgeResponseEvent)event;
       		if(currentSignedBy != null && !currentSignedBy.equals("") &&
       				currentSignedBy.contains(courtInfo.getFirstName()) && currentSignedBy.contains(courtInfo.getLastName())){
       			courtNumber = courtInfo.getCourtNumber();
       		}   		
       	}
       	
		vrForm.setWhoSignedId(courtNumber);
	}

	/**
	 * this method reloads event descriptions into eventtypes field which may contain eventIds if the reporting history
	 * record was created thru violation reporting.  Reporting history records created via Compliance will contain descriptions
	 * @param eventList
	 * @param eventTypes
	 * @return
	 */
	public static List reloadEventTypeDescriptions(List eventList, List eventTypes){
		for (int g = 0; g < eventList.size(); g++){
			NonComplianceEventResponseEvent ncre2 = (NonComplianceEventResponseEvent) eventList.get(g);
			if (ncre2.getEventTypesId() != null ) {
				String [] theIDs = ncre2.getEventTypesId().split(",");
				StringBuffer selectedEventTypes = new StringBuffer();
				boolean matchFound = false;
				for(int y=0; y< theIDs.length; y++){
					String compareId = theIDs[y].trim();
					if (!compareId.equals("")){
						for (int t = 0; t<eventTypes.size(); t++){
							CodeResponseEvent et = (CodeResponseEvent) eventTypes.get(t);
							if(et.getSupervisionCode().equals(compareId)){
								selectedEventTypes.append(et.getDescription());
								matchFound = true;
								if(y<theIDs.length - 1) {
									selectedEventTypes.append(", ");
									break;
								}	
							}	
						} // end t loop
						if (matchFound == false){
							selectedEventTypes.append(theIDs[y]);
							if(y<theIDs.length - 1) {
								selectedEventTypes.append(", ");
							}	
						}
					}
				}  // end y loop
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
	public static Timestamp convertDateToTimeStamp(String dateStr, String timeStr, String meridianStr){
		final Timestamp ts;
		String[] dateFld = dateStr.split("\\x2F");
		String[] timeFld = timeStr.split("\\x3A");
		int YYYY = Integer.parseInt(dateFld[2]);
		int MM = Integer.parseInt(dateFld[0]);
		int DD = Integer.parseInt(dateFld[1]);
		int HH = Integer.parseInt(timeFld[0]);
		if (meridianStr.equalsIgnoreCase("AM") && HH == 12){
			HH = HH - 12;
		}
		if (meridianStr.equalsIgnoreCase("PM") && HH < 12){
			HH = HH + 12;
		}
		int MIN = Integer.parseInt(timeFld[1]);
		int SS = 0;
		Calendar cal = Calendar.getInstance();
		cal.set(YYYY, MM - 1, DD, HH, MIN, SS);
		ts = new Timestamp( cal.getTime().getTime() );
		return ts;
	}	
	
	/**
	 * 
	 * @param addItemIndex
	 * @return
	 */
	public static String getAddIndex(String addItemIndex){
		String addIndex = "M1";
		String addIndexNumStr = "";
		int a = 0;
		if (addItemIndex != null && !addItemIndex.equalsIgnoreCase("")){
			addIndex = addItemIndex;
		 	addIndexNumStr = addIndex.substring(1);
		 	a = Integer.parseInt(addIndexNumStr);
		 	a++;
		 	addIndex = "M" + String.valueOf(a);
		}		
		return addIndex;
	}
// ************************************************************************************
//	NOT SURE IF THESE METHODS BELOW ARE NEEDED, LEFT FOR REFERENCE FOR THE TIME BEING
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
			//This exception is thrown if calendar was created with an invalid
			// date.
		}
		return date;
	}
/** temporary sort methods needed until 'sortResult' pagination issue resolved */

	/**
	 * @param reasonForTransferList
	 * @return List
	 */
	public static List sortReasonForTransfer(List rftList){	
		if (rftList.size() > 1){
			SortedMap map = new TreeMap();
			for (int x = 0; x < rftList.size(); x++){
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
	public static List sortLawViolationList(List lvList){
		if (lvList.size() > 1){
			SortedMap map = new TreeMap();
			for (int x = 0; x < lvList.size(); x++){
				NCLawViolationResponseEvent lvre = (NCLawViolationResponseEvent) lvList.get(x);	
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
	public static List sortFeeHistoryList(List fhList){	
		if (fhList.size() > 1){
			String payType = "";
			SortedMap map = new TreeMap();
			for (int x = 0; x < fhList.size(); x++){
				NCFeeResponseEvent fhre = (NCFeeResponseEvent) fhList.get(x);	
				payType = "";
				if (fhre.getPayType() != null){
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
	public static List sortReportingHistoryList(List rhList){	
		if (rhList.size() > 1){
			SortedMap map = new TreeMap();
// sort by date ascending order			
			for (int x = 0; x < rhList.size(); x++){	
				NonComplianceEventResponseEvent rhre = (NonComplianceEventResponseEvent) rhList.get(x);	
				map.put("" + rhre.getDateTime() + rhre.getNonComplianceEventId(), rhre);
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
	public static List sortEmploymentHistoryList(List ehList){
		if (ehList.size() > 1){
			String empName = "";
			SortedMap map = new TreeMap();
// sort by employer name			
			for (int x = 0; x < ehList.size(); x++){
				NCEmploymentResponseEvent ehre = (NCEmploymentResponseEvent) ehList.get(x);
				empName = "";
				if (ehre.getEmployerName()!= null){
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
	 * @param employmentHistoryList
	 * @return List
	 */
	public static List sortEmploymentHistoryListByCLS( List ehList ){
		
		if ( ehList.size() > 1){
			String empSeqNum = "";
			SortedMap map = new TreeMap();
			// sort by employer sequence Number			
			for (int z = 0; z < ehList.size(); z++){
				NCEmploymentResponseEvent ehre = (NCEmploymentResponseEvent) ehList.get( z );
				empSeqNum = "";
				if ( ehre.getSeqNum()!= null){
					empSeqNum = ehre.getSeqNum();
				}
				map.put( empSeqNum, ehre );
			}
			List temp = new ArrayList( map.values() );
			ehList = new ArrayList( temp );
		} 
		return ehList;
	} 	

	/**
	 * @param previousCourtActivityList
	 * @return List
	 */
	public static List sortPreviousCourtActivityList(List pcaList){
		if (pcaList.size() > 1){
			SortedMap map = new TreeMap();
			for (int x = 0; x < pcaList.size(); x++){
				NCPreviousCourtActivityResponseEvent pcare = (NCPreviousCourtActivityResponseEvent) pcaList.get(x);
				map.put(pcare.getOccurenceDate() + pcare.getPreviousCourtActivityId(), pcare);
			}
			pcaList = new ArrayList(map.values());
		} 
		return pcaList;
	} 

	/**
	 * @param previousCourtActivityList
	 * @return List
	 */
	public static List sortPreviousCourtActivityListDescending(List pcaList){
		if (pcaList.size() > 1){
			SortedMap map = new TreeMap();
			for (int x = 0; x < pcaList.size(); x++){
				NCPreviousCourtActivityResponseEvent pcare = (NCPreviousCourtActivityResponseEvent) pcaList.get(x);
				map.put(pcare.getOccurenceDate() + pcare.getPreviousCourtActivityId(), pcare);
			}
			List temp = new ArrayList(map.values());
			for (int y=0; y < temp.size(); y++){
				pcaList.set(y, temp.get(temp.size() - y - 1));
			}
		} 
		return pcaList;
	}

	/**
	 * @param treatmentIssuesList
	 * @return List
	 */
	public static List sortTreatmentIssuesList(List tiList){
		if (tiList.size() > 1){
			String refType = "";
			SortedMap map = new TreeMap();
			for (int x = 0; x < tiList.size(); x++){
				CSProgramReferralResponseEvent tire = (CSProgramReferralResponseEvent) tiList.get(x);
				refType = "";
				if (tire.getReferralTypeDesc() != null){
					refType = tire.getReferralTypeDesc();
				}else if (tire.getReferralTypeCode() != null) {
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
	 * @param reportingHistoryList
	 * @return List
	 */
	public static List sortPositiveUrinalysisList(List puList){
		if (puList.size() > 1){
			SortedMap map = new TreeMap();
			for (int x = 0; x < puList.size();  x++){
				NonComplianceEventResponseEvent rhre = (NonComplianceEventResponseEvent) puList.get(x);	
				map.put(rhre.getDateTime() + rhre.getNonComplianceEventId(), rhre);
			}
			// reverse sorted map 
			List temp = new ArrayList(map.values());
			Collections.reverse(temp);
			puList = new ArrayList(temp);
		}
		return puList;
	} 	

	/**
	 * @param suggestedcourtactionList
	 * @return List
	 */
	public static List sortCourtActionsList(List scaList){
		if (scaList.size() > 0){
			SortedMap map = new TreeMap();
			for (int x = 0; x < scaList.size(); x++){
				CodeResponseEvent rre = (CodeResponseEvent) scaList.get(x);	
				map.put(rre.getDescription(), rre);
			}
			scaList = new ArrayList(map.values());
		}
		return scaList;
	} 
	
	public static UpdateNCResponseStatusEvent prepareRequestEvent(ViolationReportsForm vrForm, String subject, String text, String topic, String statusId, boolean selfApprove) {
		String crtNum = vrForm.getCourtNum().trim();
		if (crtNum.indexOf(" ") > -1){
			crtNum = crtNum.substring(crtNum.length() - 3, crtNum.length());
		}
		// this tasks needs full court value to find CLO.
		if (ViolationReportConstants.CSTASK_SUBJECT_VIOLATION_SUBMISSION_REVIEW.equalsIgnoreCase(subject)||
				( ViolationReportConstants.CSTASK_SUBJECT_VIOLATION_SUBMISSION_APPROVAL.equalsIgnoreCase( subject ) )){
			String fd = crtNum.substring(0,1);
			if (fd.equals("0")){
				crtNum = "CC 0" + crtNum;
			}else {
				crtNum = "CR 0" + crtNum;
			}
		}
		UpdateNCResponseStatusEvent uEvent = (UpdateNCResponseStatusEvent) EventFactory.getInstance(ComplianceControllerServiceNames.UPDATENCRESPONSESTATUS);
		uEvent.setNcResponseId(vrForm.getViolationReportId());
		uEvent.setCourtNumber(crtNum);
		uEvent.setNtTaskId(vrForm.getTaskId());
		uEvent.setSubject(subject);
		uEvent.setSuperviseeName(vrForm.getSuperviseeName());
		uEvent.setOffense(vrForm.getOffense());
		uEvent.setOfficerName(vrForm.getOfficerName());
		uEvent.setProgramUnit(vrForm.getProgramUnit());
		uEvent.setSpn(vrForm.getSuperviseeId());
		uEvent.setText(text);
		uEvent.setTopic(topic);
		uEvent.setStatusId(statusId);
		uEvent.setSelfApprove(selfApprove);
		uEvent.setCdi(vrForm.getCdi());
		uEvent.setCaseNumber( vrForm.getCaseNum() );
		uEvent.setLos(vrForm.getLos());
		uEvent.setReportType(ViolationReportConstants.REPORTTYPE_VIOLATION);
		uEvent.setStaffPositionId( vrForm.getTaskToStaffId() );
		return uEvent;
	}

	public static String printSetup(ViolationReportsForm vrForm , CaseAssignmentForm caseAssignForm, SuperviseeHeaderForm myHeaderForm, SuperviseeForm superviseeForm, SuperviseeInfoHeaderForm mySupHeader, HttpServletResponse aResponse) throws Exception{
		String result = "";
// Get supervisee info not on form		
    	GetSuperviseesEvent sEvent = new GetSuperviseesEvent();
    	sEvent.setSpn(vrForm.getSuperviseeId());
	    CompositeResponse response = MessageUtil.postRequest(sEvent);
	    Collection col = MessageUtil.compositeToCollection(response, PartyListResponseEvent.class);
	    String Sex = "";
    	if (col != null && !col.isEmpty()) {
    		Iterator iterator = col.iterator();
    		while(iterator.hasNext()){
    			PartyListResponseEvent pResp = (PartyListResponseEvent) iterator.next();
    			if (pResp.getSexId() != null){
    				Sex = pResp.getSexId();
    			}
    		}	
    	}    
		if (Sex != null && !Sex.equals("")){
			if (Sex.equalsIgnoreCase("M")){
				Sex = "MALE";
			} else if (Sex.equalsIgnoreCase("F")){
				Sex = "FEMALE";
			} else if (Sex.equalsIgnoreCase("U")){
				Sex = "UNKNOWN";
			}
		}
		ReportRequestEvent violationReport =  new ViolationReportPrintTemplateRequestEvent();
		AdministerComplianceReportingBean violationReportBean = new AdministerComplianceReportingBean();	
		GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
			.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
		getEvent.setDefendantId(caseAssignForm.getDefendantId());
		SuperviseeInfoResponseEvent sprResponse =
			(SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent, SuperviseeInfoResponseEvent.class);
		if(sprResponse != null){
			violationReportBean.setLevelOfSupervision(sprResponse.getSupervisionLevel());
			violationReportBean.setProgramUnit(sprResponse.getProgramUnit());			 
		}
		if( mySupHeader.getLastContactDate() != null ){
			violationReportBean.setLastContactDate(mySupHeader.getLastContactDate());
		} else {
			violationReportBean.setLastContactDate(null);
		}
		if( mySupHeader.getNextContactDate() != null ){
			violationReportBean.setNextOfficeVisitDate(mySupHeader.getNextContactDate());
		} else {
			violationReportBean.setNextOfficeVisitDate(null);
		}
		if( mySupHeader.getNextContactTime() != null ){
			violationReportBean.setNextOfficeVisitTime(mySupHeader.getNextContactTime());
		}else {
			violationReportBean.setNextOfficeVisitTime(null);
		}
		violationReportBean.setSexCd(Sex);
		populateCLODetails(violationReportBean, caseAssignForm);
		populateCSODetails(violationReportBean, sprResponse);
		populateMgApproveName(violationReportBean, sprResponse);
		vrForm.setOrderId(findOrderId(vrForm.getSuperviseeId(), vrForm.getCdi(), vrForm.getCaseNum()));

		SupervisionOrderDetailResponseEvent orderDetailResponseEvent = null;
		
		orderDetailResponseEvent = UISupervisionOrderHelper.getSupervisionOrderDetailsForReporting(vrForm.getOrderId());

		violationReportBean.setSupervisionLengthNum(orderDetailResponseEvent.getSupervisionLengthNum());
		violationReportBean.setSupervisionBeginDate(orderDetailResponseEvent.getCaseSupervisionBeginDate());
		violationReportBean.setSupervisionEndDate(orderDetailResponseEvent.getCaseSupervisionEndDate());
		violationReportBean.setSuperviseeId(vrForm.getSuperviseeId());
		violationReportBean.setCause(vrForm.getCaseNum());
		violationReportBean.setSubMgrAppDate(vrForm.getSubMgrAppDate());
		violationReportBean.setOffenseLevel(setDegreeOfOffense(orderDetailResponseEvent.getOffenseId()));
		violationReportBean.setHoursOrdered(vrForm.getCurrentHoursOrdered());
		violationReportBean.setHoursCompleted(vrForm.getCurrentHoursCompleted());
		violationReportBean.setSpecimenTotal(vrForm.getCurrentTotalSpecimensAnalyzed());
		// Set array lists
		violationReportBean.setMentalHealthComments(vrForm.getCurrentMentalHealthComments());
		violationReportBean.setMentalHealthDiagnosis(vrForm.getCurrentMentalHealthDiagnosis());
		List lawViolationList = vrForm.getCurrentLawViolationsList();
		for ( int a=0; a < lawViolationList.size(); a ++ ){
			
			NCLawViolationResponseEvent NCResponse = (NCLawViolationResponseEvent) lawViolationList.get( a );
			NCResponse.setOffenseLitrel( NCResponse.getOffenseLitrel() );
		}
		violationReportBean.setLawViolations(lawViolationList);
		violationReportBean.setLawViolationComments( vrForm.getCurrentLawViolationsComments() );
		violationReportBean.setFeeHistoryComments( vrForm.getCurrentFeeHistoryComments() );
		violationReportBean.setFeeHistory(vrForm.getCurrentFeeHistoryList());
		getReportingHistoryCreateInfo(vrForm);
		violationReportBean.setReportingHistoryComments( vrForm.getCurrentReportingHistoryComments() );		
		violationReportBean.setReportingHistory(vrForm.getCurrentReportingHistoryList());
		violationReportBean.setEmploymentHistory(vrForm.getCurrentEmploymentHistoryList());
		violationReportBean.setEmploymentHistoryComments( vrForm.getCurrentEmploymentHistoryComments() );
//This is a temporary list that combines the CurrentCourtActivityVRList, CurrentMotionsList, and CurrentOthersList
		List newList = new ArrayList(); 
		if (vrForm.getCurrentCourtActivityVRList().size() > 0 ){
			newList.addAll(vrForm.getCurrentCourtActivityVRList());
		}
		if (vrForm.getCurrentMotionsList().size() > 0 ){
			newList.addAll(vrForm.getCurrentMotionsList());
		}
		if (vrForm.getCurrentOthersList().size() > 0 ){
			newList.addAll(vrForm.getCurrentOthersList());
		}
		if (newList.size() > 0) {
			Collections.sort(newList, NCPreviousCourtActivityResponseEvent.NCPReviousCourtActivityResponseEventSubTypeComparator);
			Iterator scrubList = newList.iterator();
			//This is the final list of Previous Court Activity with scrubbed comments			
			List preCrtActList = new ArrayList();
			while (scrubList.hasNext()) {
				NCPreviousCourtActivityResponseEvent scrubbed = (NCPreviousCourtActivityResponseEvent) scrubList.next();
				if (StringUtils.isNotEmpty(scrubbed.getTypeOfCourtActionComment())) {
					scrubbed.setTypeOfCourtActionComment( scrubbed.getTypeOfCourtActionComment() );
				}
				if (StringUtils.isNotEmpty(scrubbed.getSummaryOfCourtAction())) {
					scrubbed.setSummaryOfCourtAction( truncateComments(scrubbed.getSummaryOfCourtAction(),250) );
				}
				preCrtActList.add(scrubbed);	
			}
			violationReportBean.setPreCourtActivity(preCrtActList);
		}
		violationReportBean.setTreatmentIssues(vrForm.getCurrentTreatmentIssuesList());
		violationReportBean.setTreatmentIssuesComments( vrForm.getCurrentTreatmentIssuesComments());
		violationReportBean.setCommunityServiceComments( vrForm.getCurrentCommunityServiceComments() );
		violationReportBean.setPosUrinalysis(vrForm.getCurrentPositiveUrinalysisList());
		violationReportBean.setPosUrinalysisComments( vrForm.getCurrentPositiveUrinalysisComments() );
		violationReportBean.setTotalSpecimenAnalyzed(vrForm.getTotalSpecimensAnalyzed());
		violationReportBean.setRecommendations( vrForm.getCurrentRecommendations() );
		violationReportBean.setSugCourtActions(vrForm.getCurrentSuggestedCourtActionsList());
		violationReportBean.setReasonForTransfer(vrForm.getCurrentReasonForTransferList());
		violationReportBean.setIsExtended(vrForm.getIsExtended());
		violationReportBean.setCourtActions(vrForm.getCurrentCourtActionsList());
		if(StringUtils.isNotEmpty(vrForm.getSummaryOfCourtActions())){
			violationReportBean.setSummaryOfCourtActions(truncateComments(vrForm.getSummaryOfCourtActions(), 2000));
		}
		violationReportBean.setOffense( caseAssignForm.getOffenseDesc() );
		violationReportBean.setCourt(caseAssignForm.getCourtNumber());
		violationReportBean.setPartyName(orderDetailResponseEvent.getPrintedName());
		violationReportBean.setAddressZip(superviseeForm.getSuperviseeZipCode());
		violationReportBean.setDateOfBirth(superviseeForm.getDateOfBirth());
		violationReportBean.setTypeOfDisposition(orderDetailResponseEvent.getDispositionType());
		StringBuffer superviseeAddress = new StringBuffer();
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeStreetNumber())){
			superviseeAddress.append(superviseeForm.getSuperviseeStreetNumber());
			superviseeAddress.append(" ");
		}
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeStreetName())){
			superviseeAddress.append(superviseeForm.getSuperviseeStreetName());
			superviseeAddress.append(" ");
		}
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeStreetType())){
			superviseeAddress.append(superviseeForm.getSuperviseeStreetType());
			superviseeAddress.append(" ");
		}
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeApartmentNumber())){
			superviseeAddress.append("No. ");
			superviseeAddress.append(superviseeForm.getSuperviseeApartmentNumber());
			superviseeAddress.append(" ");
		}
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeCity())){
			superviseeAddress.append(superviseeForm.getSuperviseeCity());
			superviseeAddress.append(", ");
		}
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeState())){
			superviseeAddress.append(superviseeForm.getSuperviseeState());
			superviseeAddress.append(" ");
		}
		if(StringUtils.isNotEmpty(superviseeForm.getSuperviseeZipCode())){
			superviseeAddress.append(superviseeForm.getSuperviseeZipCode());
		}
		violationReportBean.setSuperviseeAddress(superviseeAddress.toString());	
		StringBuffer reportName = new StringBuffer();
		reportName.append(CONTEXTKEYPREFIX);
		reportName.append("VIOLATION REPORT");
		violationReport.setReportName( reportName.toString());		
		violationReport.addDataObject(violationReportBean);
		CompositeResponse resp = MessageUtil.postRequest(violationReport);
        ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(resp, ReportResponseEvent.class);
        if (aRespEvent == null) {
            ReturnException returnException = (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
            throw returnException;
        } else {
            String fileName = "CSVIOLATIONREPORT" ;
            aResponse.setContentType("application/x-file-download");
            aResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + FILEEXT);
            aResponse.setHeader("Cache-Control", "must-revalidate");
            aResponse.setContentLength(aRespEvent.getContent().length);
            aResponse.resetBuffer();
            OutputStream os = aResponse.getOutputStream();
            os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
            os.flush();
            os.close();
        }
	    
		return result;
	}

	/**
	 * 
	 * @param officerDataTo
	 * @param caseAssignForm
	 */
	public static void populateCLODetails(AdministerComplianceReportingBean officerDataTo, CaseAssignmentForm caseAssignForm)
	{
		GetCourtStaffPositionEvent event = (GetCourtStaffPositionEvent) EventFactory
        .getInstance(CSCDStaffPositionControllerServiceNames.GETCOURTSTAFFPOSITION);
		StringBuffer courtNumber = new StringBuffer();
		if ( StringUtils.isNotEmpty( caseAssignForm.getReassignedCourtId() ) ) {
			String fd = caseAssignForm.getReassignedCourtId().substring(0,1);
			if (fd.equals("0")){
				courtNumber.append("CC 0");
			    courtNumber.append(caseAssignForm.getReassignedCourtId());
			}else {
				courtNumber.append("CR 0");
			    courtNumber.append(caseAssignForm.getReassignedCourtId());
			}
		} else if ( StringUtils.isNotEmpty( caseAssignForm.getCourtNumber() ) ) {
			String fd = caseAssignForm.getCourtNumber().substring(0,1);
			if (fd.equals("0")){
				courtNumber.append("CC 0");
			    courtNumber.append(caseAssignForm.getCourtNumber());
			}else {
				courtNumber.append("CR 0");
			    courtNumber.append(caseAssignForm.getCourtNumber());
			}
		}
		event.setCourtId(courtNumber.toString());
		event.setJobTitleId(PDCodeTableConstants.STAFF_JOB_TITLE_CLO);
		List results = MessageUtil.postRequestListFilter(event, SupervisionStaffResponseEvent.class);
		
		if (results != null && results.size() > 0) {
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
            SupervisionStaffResponseEvent sre = (SupervisionStaffResponseEvent) iterator.next();
			StringBuffer officerName = new StringBuffer();
				if ( StringUtils.isNotEmpty( sre.getLastName() ) ) {
					officerName.append(sre.getLastName());
					officerName.append(", ");
				}
				if ( StringUtils.isNotEmpty( sre.getFirstName() ) ) {
					officerName.append(sre.getFirstName());
				}
				if ( StringUtils.isNotEmpty( sre.getMiddleName() ) ) {
					officerName.append(" ");
					officerName.append(sre.getMiddleName());
				}
				if ( StringUtils.isNotEmpty( officerName.toString() ) ) {
					officerDataTo.setCloName(officerName.toString());
				} else {
					officerDataTo.setCloName("NO OFFICER ASSIGNED");						
				}
				if ( StringUtils.isNotEmpty( sre.getSupervisionStaffId() ) ) {
					GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
					reqEvt.setStaffPositionId(sre.getSupervisionStaffId());
					LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);	
					if ( StringUtils.isNotEmpty( staffPosRespEvt.getStaffPositionName() ) ) {
						officerDataTo.setCloPOI(staffPosRespEvt.getDivisionName());
					}
				}
			}
		}
	}//end of populateCLODetails()
	
	/**
	 * 
	 * @param officerDataTo
	 * @param caseAssignForm
	 * @param vrForm
	 */
	public static void populateCSODetails(AdministerComplianceReportingBean officerDataTo, SuperviseeInfoResponseEvent sprResponse)
	{
		String staffPositionId = sprResponse.getPositionId();
		if ( StringUtils.isNotEmpty( staffPositionId ) ) {
			GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
			reqEvt.setStaffPositionId(staffPositionId);
			reqEvt.setOfficerNameNeeded(true);
			LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
			//add logic to find userprofile to get positions phone and email
			if(staffPosRespEvt!=null)
			{
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getOfficerName() ) ) {
					officerDataTo.setCsoName(staffPosRespEvt.getOfficerName());
				} else {
					officerDataTo.setCsoName("NO OFFICER ASSIGNED");						
				}
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getStaffPositionName() ) ) {
					officerDataTo.setCsoPOI(staffPosRespEvt.getStaffPositionName());
				}
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getDivisionName() ) ) {
					officerDataTo.setDivisionName( staffPosRespEvt.getDivisionName() );
				}
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getSPPhoneNumber() ) ) {
					PhoneNumber supervisionStaffPhone = new PhoneNumber(staffPosRespEvt.getSPPhoneNumber());
					officerDataTo.setCsoPhone(supervisionStaffPhone.toString());
					supervisionStaffPhone = null;
				}
			}
		}
		staffPositionId = "";
	}//end of populateCSODetails()
	
	/**
	 * 
	 * @param officerDataTo
	 * @param caseAssignForm
	 */
	public static void populateMgApproveName(AdministerComplianceReportingBean officerDataTo, SuperviseeInfoResponseEvent sprResponse)
	{
		String staffPositionId = sprResponse.getPositionId();
		if ( StringUtils.isNotEmpty( staffPositionId ) ) {
			GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
			reqEvt.setStaffPositionId(staffPositionId);
			reqEvt.setOfficerNameNeeded(true);
			LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
			//add logic to find userprofile to get positions phone and email
			if ( StringUtils.isNotEmpty( staffPosRespEvt.getSupervisorPositionId() ) ) {
				String supervisorPositionId = staffPosRespEvt.getSupervisorPositionId();
				GetLightCSCDStaffForUserEvent supervisorEvt = new GetLightCSCDStaffForUserEvent();
				supervisorEvt.setStaffPositionId(supervisorPositionId);
				supervisorEvt.setOfficerNameNeeded(true);
				LightCSCDStaffResponseEvent supervisorRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(supervisorEvt, LightCSCDStaffResponseEvent.class);
				if(supervisorRespEvt!=null)
				{
					if ( StringUtils.isNotEmpty( supervisorRespEvt.getOfficerName() ) ) {
						officerDataTo.setMgApproveName(supervisorRespEvt.getOfficerName());
					}
					if ( StringUtils.isNotEmpty( supervisorRespEvt.getStaffPositionName() ) ) {
						officerDataTo.setMgApprovePOI(supervisorRespEvt.getStaffPositionName());
					}
					if ( StringUtils.isNotEmpty( supervisorRespEvt.getSPPhoneNumber() ) ) {
						PhoneNumber supervisionStaffPhone = new PhoneNumber(supervisorRespEvt.getSPPhoneNumber());
						officerDataTo.setMgApprovePhone(supervisionStaffPhone.toString());
						supervisionStaffPhone = null;
					}
				}
				supervisorPositionId = "";
			}
			reqEvt = null;
		}
		staffPositionId = "";
	}//end of populateMgApproveName()
	
	public static String findOrderId(String superviseeId, String cdi, String caseNumber){
		String orderId = "";
    	String caseNum = cdi + caseNumber;
		GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
        event.setSuperviseeId(superviseeId);

        List cases = MessageUtil.postRequestListFilter(event, SuperviseeCaseOrderResponseEvent.class);
		if (cases != null && !cases.isEmpty()) {
			Iterator iter = cases.iterator();
			while(iter.hasNext()){
				SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter.next();
				if (score.getCaseNumber() != null && score.getCaseNumber().equalsIgnoreCase(caseNum)) {
					orderId = score.getSupervisionOrderId();
					break;
				}
			}
		}	
		return orderId;	
	}
	
	private static String setDegreeOfOffense(String offenseDegreeId) {
    	String level = "";
    	String degree = "";
    	if (offenseDegreeId != null && !"".equals( offenseDegreeId)){
    		OffenseCodeResponseEvent offenseCode = CodeHelper.getOffenseCode(offenseDegreeId);
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
