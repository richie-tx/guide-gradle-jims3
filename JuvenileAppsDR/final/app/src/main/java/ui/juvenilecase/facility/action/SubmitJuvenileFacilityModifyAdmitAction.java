package ui.juvenilecase.facility.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.UpdateJuvenileFacilityAdmitDetailsEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileFacilityControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;

public class SubmitJuvenileFacilityModifyAdmitAction  extends JIMSBaseAction{

	/**
	 * Modify admit record.Pre-condition Pre-condition: The
	 * JUVENILE_FACIILTY_ADMISSION_HEADER.FacilityStatus = D (detention), P
	 * (placement), V (diversion), R (returned), E (escaped), T (temporary
	 * release), or is null while ReleasedTo = TRN.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return success
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {

		AdmitReleaseForm form =(AdmitReleaseForm) aForm;
		
		StringBuffer comments =null;
		JuvenileDetentionFacilitiesResponseEvent detResp=(JuvenileDetentionFacilitiesResponseEvent) aRequest.getSession().getAttribute("FACILITY_DETENTION_RESP");//JuvenileFacilityHelper.getInFacilityDetails(form.getJuvenileNum(),aRequest);
	
		
		UpdateJuvenileFacilityAdmitDetailsEvent updateEvt = (UpdateJuvenileFacilityAdmitDetailsEvent)EventFactory.
				   getInstance(JuvenileFacilityControllerServiceNames.UPDATEJUVENILEFACILITYADMITDETAILS); 
		
			updateEvt.setJuvNum(form.getJuvenileNum());
			updateEvt.setLastSeqNum(form.getFacilitySeqNum());
			updateEvt.setFacilityStatus(form.getFacilityStatus());
			updateEvt.setOriginalAdmitDate(detResp.getOriginalAdmitDate());
			updateEvt.setAdmitReasonResp(form.getAdmitReasonResp());
			updateEvt.setDetentionStatus(form.getFacilityStatus());
			String juvenileName = form.getProfileDetail().getFirstName()+", "+form.getProfileDetail().getMiddleName()+", "+form.getProfileDetail().getLastName();
			updateEvt.setJuvenileName(juvenileName);
			//task 148846
			updateEvt.setPetitionNum(detResp.getPetitionNum());
			////task 148846
			JuvenileFacilityResponseEvent facilityRespEvt = JuvenileFacilityHelper.getFacility(form.getFacilities(), form.getDetainedFacility());
			if(facilityRespEvt != null){
				updateEvt.setFacRefereeCourt(facilityRespEvt.getRefereeCourt());
			}
			boolean isObservationStatusChanged = false;
			if( detResp.getSaReason()!=null &&!detResp.getSaReason().isEmpty() && !detResp.getSaReason().equals(form.getSaReason())){
				isObservationStatusChanged = true;
			}
			if( detResp.getSpecialAttention()!=null && !detResp.getSpecialAttention().isEmpty() && !detResp.getSpecialAttention().equals(form.getSpecialAttentionCd())){
				isObservationStatusChanged = true;
			}
			String splAttnOtherComments = JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(),detResp.getDetentionId());
			if(splAttnOtherComments!= null && !splAttnOtherComments.isEmpty() &&!splAttnOtherComments.equals(form.getSaReasonOtherComments().trim())){
				isObservationStatusChanged = true;
			}
			
			updateEvt.setObservationStatusChanged(isObservationStatusChanged);
			
			if(isObservationStatusChanged){
				updateEvt.setSaReason(form.getSaReason()); 
				updateEvt.setSpecialAttentionCd(form.getSpecialAttentionCd());
				if(form.getSaReasonOtherComments()!=null){
					updateEvt.setSaReasonOtherComments(form.getSaReasonOtherComments().trim());
				}
			}
			
			if(form.getAdmissionComments()!=null){
				updateEvt.setComments(form.getAdmissionComments().trim());
			}
			updateEvt.setChangeExplanation(form.getReasonForChange());
			updateEvt.setEscapeAttempts(form.getNumOfEscapeAttempts());
			if(form.getEscapeComments()!=null){
				updateEvt.setEscapeAttemptComments(form.getEscapeComments().trim());
			}
			updateEvt.setDetResp(detResp);
			//if secure status is modified.
			if(detResp.getSecureStatus().equals(form.getSecureStatus())){
				form.setSecureStatusChangeFlag(false);
			}
			if(detResp.getAdmitReason().equals(form.getReasonCode())){
				form.setAdmitReasonChangeFlag(false);
			}
			if(detResp.getFloorNum().equals(form.getFloorLocation())&& detResp.getRoomNum().equals(form.getRoomLocation()) && detResp.getUnit().equals(form.getUnitLocation()) && detResp.getMultipleOccupyUnit().equals(form.getMultipleOccupancyUnit()) && detResp.getReceiptNumber().equals(form.getValuablesReceipt())&& detResp.getLockerNumber().equals(form.getLockerLocation())){
				form.setLocationChangeFlag(false);
			}
			if(detResp.getAdmittedByJPO().equals(form.getAdmitBy())&& detResp.getComments().equals(form.getAdmissionComments().trim())){
				form.setOtherChangeFlag(false);
			}
			if(form.isSecureStatusChangeFlag()){
				updateEvt.setSecureStatus(form.getSecureStatus());
				updateEvt.setSecureIndicatorChangeFlag(form.isSecureStatusChangeFlag());
				updateEvt.setReasonChangeFlag(false);
				updateEvt.setLocationChangeFlag(false);
				updateEvt.setOtherChangeFlag(false);
			}else if(form.isAdmitReasonChangeFlag()){
				updateEvt.setAdmitReason(form.getReasonCode());
				updateEvt.setReasonChangeFlag(form.isAdmitReasonChangeFlag());
				updateEvt.setLocationChangeFlag(false);
				updateEvt.setOtherChangeFlag(false);
				updateEvt.setSecureIndicatorChangeFlag(false);
				updateEvt.setSaReasonOtherComments(form.getSaReasonOtherComments().trim());
			}else if(form.isLocationChangeFlag()){
				updateEvt.setFloorNum(form.getFloorLocation());
				updateEvt.setRoomNum(form.getRoomLocation());
				updateEvt.setUnit(form.getUnitLocation());
				updateEvt.setMultipleOccupyUnit(form.getMultipleOccupancyUnit());
				updateEvt.setLockerNumber(form.getLockerLocation());
				updateEvt.setReceiptNumber(form.getValuablesReceipt());
				updateEvt.setLocationChangeFlag(form.isLocationChangeFlag());
				updateEvt.setReasonChangeFlag(false);
				updateEvt.setOtherChangeFlag(false);
				updateEvt.setSecureIndicatorChangeFlag(false);
			}else if(form.isOtherChangeFlag()){
				updateEvt.setAdmittedBy(form.getAdmitBy());
				updateEvt.setOtherChangeFlag(form.isOtherChangeFlag());
				updateEvt.setReasonChangeFlag(false);
				updateEvt.setLocationChangeFlag(false);
				updateEvt.setSecureIndicatorChangeFlag(false);
			}
			//Release Update.
			if(form.getFacilityStatus()!=null && (form.getFacilityStatus().equals("N")|| form.getFacilityStatus().equals("T") || (form.getFacilityStatus().isEmpty() && !form.getReleasedTo().isEmpty()))){
				updateEvt.setReleaseDate(DateUtil.stringToDate(form.getReleaseDate(),DateUtil.DATE_FMT_1));
				updateEvt.setReleaseTime(form.getReleaseTime());
				if(form.getReleaseReason()!=null && !form.getReleaseReason().isEmpty()){
					updateEvt.setReleaseReason(form.getReleaseReason());
					updateEvt.setReleaseBy(form.getReleasedBy());
					updateEvt.setReleaseTo(form.getReleasedTo());
					updateEvt.setReleaseAuthorizedBy(form.getReleaseAuthority());
				}
			}
			
			//return update.
			if(form.getFacilityStatus()!=null && form.getFacilityStatus().equals("R") &&form.getReturnStatus()!=null && form.getReturnStatus().equals("YES")){
				updateEvt.setReturnDate(DateUtil.stringToDate(form.getReturnDate(),DateUtil.DATE_FMT_1));
				updateEvt.setReturnTime(form.getReturnTime());
			}
				
			
			//Escape Update.
			if(form.getFacilityStatus()!=null && form.getFacilityStatus().equals("E")){
				updateEvt.setRelocationDate(DateUtil.stringToDate(form.getEscapeDate(),DateUtil.DATE_FMT_1));
				updateEvt.setRelocationTime(form.getEscapeTime());
				updateEvt.setEscapeCode(form.getEscapeFrom());
			}
			
			JuvenileFacilityResponseEvent facilityResp = JuvenileFacilityHelper.getFacility(form.getFacilities(), form.getDetainedFacility());
			if( facilityResp != null ){
			    updateEvt.setTjjdFacilityId(facilityResp.getTjjdFacilityId());
			    updateEvt.setTjjdFundingSrc(facilityResp.getTjjdFundingSrc());
			    updateEvt.setAvgCostPerDay(facilityResp.getAvgCostPerDay());
			}
			CompositeResponse compositeResponse = MessageUtil.postRequest(updateEvt);
			Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	        if (errorResponse != null)
	        {
	            ErrorResponseEvent errEvt = (ErrorResponseEvent) errorResponse;
	            try {
					handleFatalUnexpectedException(errEvt.getMessage());
				} catch (GeneralFeedbackMessageException e) {
					e.printStackTrace();
				}
	        }
	        
	        //If current supervision is null or empty.
	      if(form.getCurrentSupervisionNum()==null || (form.getCurrentSupervisionNum()!=null && form.getCurrentSupervisionNum().isEmpty())){
	        	form.setCurrentSupervisionNum(JuvenileFacilityHelper.getMostRecentActiveCasefile(form.getReferrals(),form.getCasefiles(),form.getCurrentReferral()));
	      }
	        
		//Update Detention Record Information.
		if(form.isLocationChangeFlag()){
			String title= "Facility location modification.";
			comments = new StringBuffer();
			comments.append(form.getReasonForChange())
					.append("New facility location is, FLOOR: ")
					.append(form.getFloorLocation())
					.append(", UNIT: ")
					.append(form.getUnitLocation())
					.append(", ROOM: ")
					.append(form.getRoomLocation());
				if(form.getMultipleOccupancyUnit()!=null && !form.getMultipleOccupancyUnit().isEmpty()){
					comments.append(" - "+ form.getMultipleOccupancyUnit());					
				}
				// 104028
				if(!detResp.getReceiptNumber().equals(form.getValuablesReceipt()))
				{
				    comments.append(", Valuables: ");
				    comments.append(form.getValuablesReceipt());
				}
				if(!detResp.getLockerNumber().equals(form.getLockerLocation()))
				{
				    comments.append(", Locker: ");
				    comments.append(form.getLockerLocation());
				}
				//
			comments.append(", Admission Comments: ")
					.append(form.getAdmissionComments()).append(", SupervisionNum: ")
					.append(form.getCurrentSupervisionNum());
			JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT,
					 ActivityConstants.FACILITY_ADMIT_LOCATION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			
		} else if(form.isSecureStatusChangeFlag()){ // activity if the secure status Indicator is changed.
			String title= "Facility secure status change.";
			comments = new StringBuffer();
			comments.append(form.getReasonForChange())
					.append("New facility secure status is, Secure Status: ")
					.append(form.getSecureStatusDesc())
					.append(", Admission Comments: ")
					.append(form.getAdmissionComments() + ", SupervisionNum: ")
					.append(form.getCurrentSupervisionNum());
			JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
					 ActivityConstants.FACILITY_SECURE_STATUS, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			
		}else if(form.isAdmitReasonChangeFlag()){ //activity if the admit Reason Indicator is changed. 
			String title= "Facility admit reason modification.";
			comments = new StringBuffer();
			comments.append(form.getReasonForChange())
					.append("New Reason for admission is, Admit Reason: ")
					.append(form.getAdmitReasonStr())
					.append(",Admission Comments: ")
					.append(form.getAdmissionComments()).append(", SupervisionNum")
					.append(form.getCurrentSupervisionNum());
			JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),  ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT,
					 ActivityConstants.FACILITY_ADMIT_REASON, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			
		}else{ //other Indicator flag changed
			if(form.isOtherChangeFlag()){
				if(form.getAdmitBy()!=null && !form.getAdmitBy().isEmpty()&&!form.getAdmitBy().equals(detResp.getAdmittedByJPO())){ //If the AdmitBy value was changed.
					String title= "Facility Admission Detail modification.";
					comments = new StringBuffer();
					comments.append(form.getReasonForChange())
							.append("Admitting Officer changed to: ")
							.append(form.getAdmitBy());
					JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
							ActivityConstants.FACILITY_ADMIT_BY, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				}
			}
		}
		if(isObservationStatusChanged){
			//spl attention changed
			if(detResp.getSpecialAttention()!=null && !form.getSpecialAttentionCd().isEmpty()&&(!form.getSpecialAttentionCd().equals(detResp.getSpecialAttention()) || !form.getSaReason().equals(detResp.getSaReason()))){ 
				String title= "Facility Observation Status modification.";
				comments = new StringBuffer();
				comments.append("Observation Status was changed to  ").append(form.getSpecialAttentionDesc());
				if(form.getSaReason().contains("OT")){
					comments.append(", Observation Comments: ").append(form.getSaReasonOtherComments());
				}
				JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL,  ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT ,
						 ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			}
			// SA comments changed
			if(form.getSaReasonOtherComments()!=null && !form.getSaReasonOtherComments().isEmpty()&&(!form.getSaReasonOtherComments().equals(detResp.getSaReasonOtherComments()))){ //sa reasons
				String title= "Facility Observation modification.";
				comments = new StringBuffer();
				comments.append("Observation Comments: ").append(form.getSaReasonOtherComments());
				JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(),ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL ,ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT, 
						ActivityConstants.FACILITY_SPECIAL_ATTENTION, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
			}
		}
		//admission comments changed
		String facAdmissionComments = JuvenileFacilityHelper.getFacilityAdmissionComments(form.getJuvenileNum(),form.getDetentionId()); //51737
		if(form.getAdmissionComments()!=null && !form.getAdmissionComments().isEmpty()&& !facAdmissionComments.isEmpty() && !form.getAdmissionComments().equals(facAdmissionComments)){
			String title= "Facility admission comments modification";
			comments = new StringBuffer();
			comments.append("Admission Comments: ").append(form.getAdmissionComments());
			JuvenileFacilityHelper.createActivity(form.getCurrentSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT,
					 ActivityConstants.FACILITY_ADMISSION_COMMENTS, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
		}

	   form.setAction("confirm");
	   return aMapping.findForward("success");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		
		if(form.getSpecialAttentionCd().equals("NO")){
			form.setSelectedSAReasons(null);
			form.setSelectedSA("NO");
		}else{
			form.setSelectedSA(form.getSpecialAttentionCd());
			form.setSelectedSAReasons(form.getSaReason().split(","));
			form.setSaReasonStr(form.getSaReason());
		}
		if(form.getEscapeTime().contains(":")){
			form.setEscapeTime(JuvenileFacilityHelper.stripColonInDate(form.getEscapeTime()));
		}
		if(form.getReturnTime().contains(":")){
			form.setReturnTime(JuvenileFacilityHelper.stripColonInDate(form.getReturnTime()));
		}
		if(form.getReleaseTime().contains(":")){
			form.setReleaseTime(JuvenileFacilityHelper.stripColonInDate(form.getReleaseTime()));
		}
		
		form.setReasonCode(form.getSelectedAdmitReason());
		return( aMapping.findForward( UIConstants.BACK ) ) ;

	}

	@Override
	protected Map<String,String> getKeyMethodMap() {
		Map<String,String> keyMap = new HashMap<String,String>();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	@Override
	protected void addButtonMapping(Map keyMap) {
		 keyMap.put("button.back", "back");
         keyMap.put("button.cancel", "cancel");
         keyMap.put("button.finish", "finish");
		
	}


}
