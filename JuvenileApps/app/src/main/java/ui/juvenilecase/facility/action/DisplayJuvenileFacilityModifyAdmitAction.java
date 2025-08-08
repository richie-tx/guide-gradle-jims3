package ui.juvenilecase.facility.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.contact.domintf.IName;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.security.SecurityUIHelper;
/**
 * Facility- Modify Admit action
 * @author sthyagarajan
 *
 */
public class DisplayJuvenileFacilityModifyAdmitAction extends LookupDispatchAction {
	
	/**
	 * Constructor
	 */
	public DisplayJuvenileFacilityModifyAdmitAction() {

	}

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
	public ActionForward modifyAdmit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.setAction("modifyAdmitView");
		form.setSaReasonOtherComments("");
		form.setAdmissionComments(""); //added for 53113
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("modifyAdmitView"); // added for US 14452
		
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		briefingDetailsForm.setHideFacilityTraitsLink(false); // added for US 14452
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
            	
        // after discussion with Regina, condition need not be checked and removed in JSP as well.
   		// reset the value admission information Change flag
		if(!form.getFacilityStatus().equals("T") && !form.getFacilityStatus().equals("E")){
	       	if(form.isLocationChangeFlag()){
	       		form.setChangeLabelCd("FRUC");
	       		form.setChangeLabel("Floor Room Unit Changed");
	       		form.setReasonForChange("Floor Room Unit and/or Admission Comments Changed.");
	       		form.setLocationChangeFlag(false);
	       	}else if(form.isAdmitReasonChangeFlag()){
	       		form.setChangeLabelCd("ADRC");
	       		form.setChangeLabel("Admit Reason Changed");
	       		form.setReasonForChange("Admit By, a Comments field, or Observation Status information has been changed.");
	       		form.setAdmitReasonChangeFlag(false);
	       	}else if(form.isSecureStatusChangeFlag()){
	       		form.setChangeLabelCd("SESC");
	       		form.setReasonForChange("Secure Status and/or Admission Comments Changed.");
	       		form.setSecureStatusChangeFlag(false);
	       	}else{
	       		if(form.isOtherChangeFlag()){
				    form.setChangeLabelCd("OTHR");
				    form.setChangeLabel("Other Changed");
				    form.setReasonForChange("Admit By, a Comments field, or Observation Status information has been changed.");
				    form.setOtherChangeFlag(false);
			    }else{ 
			    	// if no flag is true.Default it to other.
			      	form.setOtherChangeFlag(true);
			       	form.setChangeLabelCd("OTHR");
				    form.setChangeLabel("Other Changed");
				    form.setReasonForChange("Admit By, a Comments field, or Observation Status information has been changed.");
			     }
		    }
		}else{ //default it to other .U.S 27500
	      	form.setOtherChangeFlag(true);
	    	form.setLocationChangeFlag(false);
			form.setSecureStatusChangeFlag(false);
    		form.setAdmitReasonChangeFlag(false);
	       	form.setChangeLabelCd("OTHR");
		    form.setChangeLabel("Other Changed");
		    form.setReasonForChange("Admit By, a Comments field, or Observation Status information has been changed.");
		}
       	return aMapping.findForward("success");
   }
	
	/**
	 * On click of next
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		//escape Time
		if(form.getEscapeTime()!=null){
			form.setEscapeTime(JuvenileFacilityHelper.getDateWithColon(form.getEscapeTime()));
		}
		//Return Time
		if(form.getReturnTime()!=null){
			form.setReturnTime(JuvenileFacilityHelper.getDateWithColon(form.getReturnTime()));
		}
		form.setAction("summary");
		form.setHideFacilityTraitsLink(true);
		JuvenileAdmitReasonsResponseEvent admitReasonRespEvent =null;
		
		if(form.getSpecialAttentionCd()!=null && !form.getSpecialAttentionCd().isEmpty()){
			form.setSpecialAttentionDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SPECIAL_ATTENTION, form.getSpecialAttentionCd()));
		}
		
		if(form.getSpecialAttentionCd().equals("NO")){
			form.setSaReason("");
			form.setSaReasonStr("");
			//form.setSaReasonOtherComments("");   commented out for Bug #51515
			form.setSelectedSAReasons(null);
			form.setSelectedSA("NO");
		}
		
		String[] selectedSAReasonsCodes = form.getSelectedSAReasons();
		if(selectedSAReasonsCodes!=null&& selectedSAReasonsCodes.length!=0){
			form.setSelectedSAReasonList(SimpleCodeTableHelper.getDescsFromSelCodeIds(PDCodeTableConstants.SPECIAL_ATTENTION_REASON, selectedSAReasonsCodes));
			JuvenileFacilityHelper.setSAReasonDesc(form.getSelectedSAReasonList(),form);
			selectedSAReasonsCodes=null; // reset the array
			form.setSelectedSAReasons(new String[0]); // reset the array
		}
		form.setSelectedAdmitReason(form.getReasonCode());
		//Bug #51515
		/*if(form.getSaReason()!=null)
		{
			if(!form.getSaReason().contains("OT")){
				form.setSaReasonOtherComments("");
			}
		}else
		{
			form.setSaReasonOtherComments("");	
		}*/
		
		if(form.getReleasedTo()!=null && !form.getReleasedTo().isEmpty()){
			form.setReleasedToDesc(ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getReleasedFromDetentionCodes(), form.getReleasedTo()));
		}
		
		if(form.getReleaseAuthority()!=null && !form.getReleaseAuthority().isEmpty()){
			if(form.getReleaseAuthority().trim().length()<4){ //51735
				IName name =SecurityUIHelper.getUserName("UV"+form.getReleaseAuthority());
				if(name!=null){
					form.setReleaseAuthorityDesc(name.getFormattedName());
				}
			}
			form.setReleaseTime(JuvenileFacilityHelper.getDateWithColon(form.getReleaseTime()));
		} 
		
		if(form.getAdmitBy()!=null && !form.getAdmitBy().isEmpty()){
			if(form.getAdmitBy().trim().length()<4){
				IName name =SecurityUIHelper.getUserName("UV"+form.getAdmitBy());
				if(name!=null){
					form.setAdmitByDesc(name.getFormattedName());
				}	
			}else{
				if(form.getAdmitBy().trim().length()==5){
					IName name =SecurityUIHelper.getUserName(form.getAdmitBy());
					if(name!=null){
						form.setAdmitByDesc(name.getFormattedName());
					}	
				}
			}
		}

		if(form.getReasonCode()!=null && !form.getReasonCode().isEmpty()){
			form.setReasonCode(StringUtils.split(form.getReasonCode(), '|')[0]);
			admitReasonRespEvent = JuvenileFacilityHelper.getAdmitReasonByCode(form.getReasonCode());
			form.setAdmitReasonResp(admitReasonRespEvent);
			if(admitReasonRespEvent!=null){
				form.setAdmitReasonStr(admitReasonRespEvent.getDescription());
			}
			//bug #26805
			if(form.getReasonCode().equals("DIV")){
				form.setSecureStatus("N");
		//		form.setSecureStatusChangeFlag(true);
			}
			//bug #26805
		}
		
		if(form.getFacilityStatus()!=null && form.getFacilityStatus().isEmpty()){
			form.setLocationChangeFlag(false);
			form.setSecureStatusChangeFlag(false);
    		form.setAdmitReasonChangeFlag(false);
    		form.setOtherChangeFlag(false);
		}else{
			// admission information
	    	if(form.getChangeLabelCd().equals("FRUC")){
	    		form.setLocationChangeFlag(true);
	    		form.setChangeLabel("Floor Room Unit Changed");
	    		form.setReasonForChange("Location within the facility changed.");
	    		form.setReasonCode(form.getAdmitReasonCd()); // default value.
	    		admitReasonRespEvent = JuvenileFacilityHelper.getAdmitReasonByCode(form.getReasonCode());
				form.setAdmitReasonResp(admitReasonRespEvent);
				if(admitReasonRespEvent!=null){
					form.setAdmitReasonStr(admitReasonRespEvent.getDescription());
				}
	    	}else if(form.getChangeLabelCd().equals("SESC")){
	    		form.setSecureStatusChangeFlag(true);
	    		form.setReasonForChange("Secure Status within the facility changed.");
	    		form.setChangeLabel("Secure Status Changed");
	    		form.setReasonCode(form.getAdmitReasonCd()); // default value.
	    		admitReasonRespEvent = JuvenileFacilityHelper.getAdmitReasonByCode(form.getReasonCode());
				form.setAdmitReasonResp(admitReasonRespEvent);
				if(admitReasonRespEvent!=null){
					form.setAdmitReasonStr(admitReasonRespEvent.getDescription());
				}
	    	}else if(form.getChangeLabelCd().equals("ADRC")){
	    		form.setAdmitReasonChangeFlag(true);
	    		form.setReasonForChange("Admit Reason within the facility changed.");
	    		form.setChangeLabel("Admit Reason Changed");
	    	}else {
	    		if(form.getChangeLabelCd().equals("OTHR")){
		    		form.setOtherChangeFlag(true);
		    		form.setReasonForChange("Admit By, a Comments field, or Observation Status information has been changed.");
		    		form.setChangeLabel("Other Changed");
		    		form.setReasonCode(form.getAdmitReasonCd()); // default value.
		    		admitReasonRespEvent = JuvenileFacilityHelper.getAdmitReasonByCode(form.getReasonCode());
					form.setAdmitReasonResp(admitReasonRespEvent);
					if(admitReasonRespEvent!=null){
						form.setAdmitReasonStr(admitReasonRespEvent.getDescription());
					}
	    		}
	    	}
		}
		form.setEscapeFromDesc(JuvenileFacilityHelper.getServicesDelivered(form.getEscapeFrom()).getDescription());
		return aMapping.findForward("next");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse){
			return( aMapping.findForward(UIConstants.CANCEL ) ) ;
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
			HttpServletRequest aRequest, HttpServletResponse aResponse){
			return( aMapping.findForward(UIConstants.BACK )) ;
	}

	@Override
	protected Map<String,String> getKeyMethodMap() {
		Map<String,String> keyMap = new HashMap<String,String>();
		keyMap.put("button.modifyAdmit", "modifyAdmit");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

}
