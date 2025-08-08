package ui.juvenilecase.facility.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByHearingDateEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumAndHearingDateEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.GetJuvenileFacilityHeaderEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.context.multidatasource.Home;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.ActivityConstants;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSHeader;
import pd.security.PDSecurityHelper;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UINotificationHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.security.SecurityUIHelper;

public class DisplayJuvenileFacilityReleaseAction  extends JIMSBaseAction {

	public DisplayJuvenileFacilityReleaseAction(){
		
	}
	
	/**
	 * Escape 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return escape
	 */
	public ActionForward escape(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("escapeView");
		form.setEscapeAction("escapeView");
		
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //bug #50640
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
    	
		//reset the out come to B for escape information
		form.setOutcome("B");
		form.setOutcomeDesc("ABSENT WITHOUT PERMISSION");
		form.setEscapeStatus("E");
		form.setReleaseReason("E");
		form.setReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASE_REASON, "E"));
		return aMapping.findForward("success");
	}
	
	/**
	 * Return Escape 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward returnEscapee(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("returnEscapeView");
		
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //bug #50640
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
    	
		//reset the out come to B for escape information
		form.setEscapeStatus("R");
		form.setReturnStatus("YES");
		form.setReturnReason("RE");
		return aMapping.findForward("success");
	}
	
	/**
	 * Release
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward release(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("releaseView");
		
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //bug #50640
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
    	
		//reset the out come to B for escape information
		form.setReleaseReason("R");
		form.setReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASE_REASON, "R"));
		return aMapping.findForward("success");
	}
	
	/**
	 * Temp Release
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return 
	 */
	public ActionForward tempRelease(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		//add if condition for temp release request if today detention calendar record present.
		//
	    boolean tempRelease=true;
	    String forward=null;
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();
	    GetJJSCLDetentionByHearingDateEvent jjsdetnCrtEvent = (GetJJSCLDetentionByHearingDateEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYHEARINGDATE);
	  //get the form from the briefing details page.
	    JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	    jjsdetnCrtEvent.setJuvNumber(briefingDetailsForm.getJuvenileNum());
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date currDate = new Date();
	    String today = sdf.format(currDate);
	    jjsdetnCrtEvent.setCourtDate(today);
	    dispatch.postEvent(jjsdetnCrtEvent);
	    CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);
	    if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
		while (clDetnDktRespItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDetnDktRespItr.next();
		    if (docRespEvent != null)
		    {
			//set Court Date and time
			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			if (docRespEvent.getJuvenileNumber() != null)
			{
			    docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
			}
			dockets.add(docRespEvent);
		    }
		}
	    }
	    if(dockets.size()>0)//and the flag has to be null
	    {
		//check for the flag: if pending then display appropriate message and set the boolean to false
		//do a case for the flag
		JuvenileFacilityHeaderResponseEvent detentionRespEvent = JuvenileFacilityHelper.getJuvFacilityHeaderDetailsforTempRelease(briefingDetailsForm.getJuvenileNum(),"detention");
		char status;
		if(detentionRespEvent.getTempReleaseStatus()!=null)
		    status = detentionRespEvent.getTempReleaseStatus().trim().toUpperCase().charAt(0);
		else
		    status=' ';
		switch(status) {
        		case ' ': 
        		{
        		    	tempRelease=false;
        		    	briefingDetailsForm.setTempReleaseRequestDecision("N");
        		    	forward="TempReleaseRequest";
        			break;
        		} 
        		case 'P': 
        		{
        		    //	disable temp release button or show appropriate message
        		    	briefingDetailsForm.setTempReleaseRequestDecision("P");
        		    	tempRelease=false;
        		    	forward="TempReleaseRequest";
        			break;
        		}
        		case 'R': 
        		{
        		    //	disable temp release button or show appropriate message
        		    	briefingDetailsForm.setTempReleaseRequestDecision("R");
        		    	tempRelease=false;
        		    	forward="TempReleaseRequest";
        			break;
        		}
        		case 'A': 
        		{
        		    	tempRelease=true;
        			break;
        		}
		}		
		
	    }
	    else
	    {
		//dettempRelease=true;
		briefingDetailsForm.setTempReleaseRequestDecision("i");
	    }
	  //check if kid has court record for today task 150243
	    //boolean tempRelease=true;
	    //String forward=null;
	    IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	    List<DocketEventResponseEvent> distDockets = new ArrayList<DocketEventResponseEvent>();
	    GetJJSCLCourtByJuvNumAndHearingDateEvent jjsdistCrtEvent = (GetJJSCLCourtByJuvNumAndHearingDateEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYJUVNUMANDHEARINGDATE);
	  //get the form from the briefing details page.
	    //JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	    jjsdistCrtEvent.setJuvenileNumber(briefingDetailsForm.getJuvenileNum());
	    /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date currDate = new Date();
	    String today = sdf.format(currDate);*/
	    jjsdistCrtEvent.setCourtDate(today);
	    dispatch1.postEvent(jjsdistCrtEvent);
	    CompositeResponse clDistResp = (CompositeResponse) dispatch1.getReply();
	    List<DocketEventResponseEvent> clDistDktRespEvts = MessageUtil.compositeToList(clDistResp, DocketEventResponseEvent.class);
	    if (clDistDktRespEvts != null && !clDistDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> clDistDktRespItr = clDistDktRespEvts.iterator();
		while (clDistDktRespItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDistDktRespItr.next();
		    if (docRespEvent != null)
		    {			
			distDockets.add(docRespEvent);
		    }
		}
	    }
	    if(distDockets.size()>0)//and the flag has to be null
	    {
		//check for the flag: if pending then display appropriate message and set the boolean to false
		//do a case for the flag
		JuvenileFacilityHeaderResponseEvent detentionRespEvent = JuvenileFacilityHelper.getJuvFacilityHeaderDetailsforTempRelease(briefingDetailsForm.getJuvenileNum(),"district");
		char status;
		if(detentionRespEvent.getTempReleaseStatus()!=null)
		    status = detentionRespEvent.getTempReleaseStatus().trim().toUpperCase().charAt(0);
		else
		    status=' ';
		switch(status) {
        		case ' ': 
        		{
        		    	tempRelease=false;
        		    	//change this flag and show accordigly in jsp too 
        		    	briefingDetailsForm.setTempDistReleaseRequestDecision("C");
        		    	forward="TempReleaseRequest";
        			break;
        		} 
        		case 'P': 
        		{
        		    //	disable temp release button or show appropriate message
        		    	briefingDetailsForm.setTempDistReleaseRequestDecision("P");
        		    	tempRelease=false;
        		    	forward="TempReleaseRequest";
        			break;
        		}
        		case 'R': 
        		{
        		    //	disable temp release button or show appropriate message
        		    	briefingDetailsForm.setTempDistReleaseRequestDecision("R");
        		    	tempRelease=false;
        		    	forward="TempReleaseRequest";
        			break;
        		}
        		case 'A': 
        		{
        		    	tempRelease=true;
        			break;
        		}
		}		
		
	    }
	    else
	    {
		//tempRelease=true;
		briefingDetailsForm.setTempDistReleaseRequestDecision("i");
	    }
	  //
	    //below common for both district and detention
	    if(tempRelease)
	    {	    	
	    	AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("tempReleaseView");
		
		//get the form from the briefing details page.
		//JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //bug #50640
		
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
    	
		//reset the out come to B for escape information
		form.setReleaseReason("T");
		form.setReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASE_REASON, "T"));
		forward="success";
	    }
	    return aMapping.findForward(forward);
	}
	
	public static JuvenileFacilityHeaderResponseEvent getJuvFacilityHeaderDetails(String juvNum){
		//Service to call JJS_HEADER
		GetJuvenileFacilityHeaderEvent headerEvent =
			(GetJuvenileFacilityHeaderEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYHEADER);
		
		headerEvent.setJuvenileNum(juvNum);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(headerEvent);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse response = (CompositeResponse)replyEvent;

		JuvenileFacilityHeaderResponseEvent juvFacHeaderRespEvent =(JuvenileFacilityHeaderResponseEvent)MessageUtil.filterComposite(response,JuvenileFacilityHeaderResponseEvent.class);
		return juvFacHeaderRespEvent;
	}
	
	public ActionForward tempReleaseDecision(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse) {
	
	AdmitReleaseForm form = (AdmitReleaseForm) aForm;
	form.clear();
	
	form.setAction("tempReleaseDecisionView");
	
	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	
	briefingDetailsForm.setAction("tempReleaseDecisionView");
	
	//POPULATE THE ADMIT RELEASE FORM
			JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
			JuvenileFacilityHelper.populateProfileReferralDetails(form);
			form.setAuthorizingOfficer(SecurityUIHelper.getLogonId());
			IName name=SecurityUIHelper.getUserName(form.getAuthorizingOfficer());
			if(name!=null)
			{
			    StringBuffer fullName = new StringBuffer();
			    fullName.append(form.getAuthorizingOfficer().toUpperCase());
			    fullName.append(" - ");
			    fullName.append(name.getFormattedName());
			    form.setAuthorizingOfficerName(fullName.toString());
			}
			
	return aMapping.findForward("tmpRelDecision");
}

	
	/**
	 * Return Release
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward retTempRelease(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("retTempReleaseView");
		
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //bug #50640
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
		
		form.setReturnStatus("YES");
		return aMapping.findForward("success");
	}
	
	/**
	 * In Transfer
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward inTransfer(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("inTransferView");
		
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //added for traits to differentiate admit, modify admit and the rest of the release process.
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
		
		form.setReleaseReason("N");
		form.setReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELEASE_REASON, "N"));
		form.setReleasedTo("NTS");
		form.setOutcome("C");
		return aMapping.findForward("success");
	}
	
	
	/**
	 * Referral Transfer
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward referralTransfer(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		form.clear();
		
		form.setAction("referralTransferView");
				
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		briefingDetailsForm.setAction("releaseView"); //bug #50640
		//facility traits should be shown in the header as the traits needs to be added.
		form.setHideFacilityTraitsLink(false);
		
		
		//POPULATE THE ADMIT RELEASE FORM
		JuvenileFacilityHelper.populateAdmitReleaseForm(form,briefingDetailsForm);	
		JuvenileFacilityHelper.populateProfileReferralDetails(form);
		
	
		return aMapping.findForward("refTransferSuccess");
	}
	
	
	
	
	/**
	 * Next 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return next
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		AdmitReleaseForm form = (AdmitReleaseForm) aForm;
		
		//get the form from the briefing details page.
		JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");

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
		//Bug #51515
		/*if(form.getSaReason()!=null){
			if(!form.getSaReason().contains("OT")){
				form.setSaReasonOtherComments("");
			}
		}else{
			form.setSaReasonOtherComments("");	
		}*/
		
		if(form.getReasonCode()!=null){
			form.setAdmitReasonStr(JuvenileFacilityHelper.getAdmitReasonByCode(form.getAdmitReasonCd()).getDescription());
		}
		
		
		//return related attributes
		if(form.getFacilityStatus().equals("E")){
			form.setReturnReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RETURN_REASON, form.getReturnReason()));
			form.setReturnTime(JuvenileFacilityHelper.getDateWithColon(form.getReturnTime()));
			if(form.getEscapeFrom()!=null){
				form.setEscapeFromDesc(JuvenileFacilityHelper.getServicesDelivered(form.getEscapeFrom()).getDescription());
			}
			form.setReturnAction("returnView");
			form.setReturnReason("RE"); //52422
		}
		
		//Set form values related to releaseView.
		if(form.getAction().equals("escapeView")){
			if(form.getEscapeFrom()!=null){
				form.setEscapeFromDesc(JuvenileFacilityHelper.getServicesDelivered(form.getEscapeFrom()).getDescription());
			}
			
			if(form.getEscapeTime()!=null){
				form.setEscapeTime(JuvenileFacilityHelper.getDateWithColon(form.getEscapeTime()));
			}	
		}
		
		
		if(form.getAction().equals("releaseView")){
			form.setReleasedToDesc(ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getReleasedFromDetentionCodes(), form.getReleasedTo()));
			if(form.getReleaseAuthority()!=null&&!form.getReleaseAuthority().isEmpty()){ //#51735 changes.
				if(form.getReleaseAuthority().trim().length()<4){
					IName name =SecurityUIHelper.getUserName("UV"+form.getReleaseAuthority());
					if(name!=null){
						form.setReleaseAuthorityDesc(name.getFormattedName());
					}
				}
			}
			form.setOutcomeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_OUTCOME, form.getOutcome()));
			form.setReleaseTime(JuvenileFacilityHelper.getDateWithColon(form.getReleaseTime()));
		}
		
		//Set form values related to tempRelease.
		if(form.getAction().equals("tempReleaseView")){
			form.setReleasedToDesc(ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getReleasedFromDetentionCodes(), form.getReleasedTo()));
			if(form.getReleaseAuthority()!=null&&!form.getReleaseAuthority().isEmpty()){ //#51735 changes.
				if(form.getReleaseAuthority().trim().length()<4){
					IName name =SecurityUIHelper.getUserName("UV"+form.getReleaseAuthority());
					if(name!=null){
						form.setReleaseAuthorityDesc(name.getFormattedName());
					}
				}
			}
			form.setTempReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TEMP_RELEASE_REASON, form.getTempReleaseReason()));
			form.setReleaseTime(JuvenileFacilityHelper.getDateWithColon(form.getReleaseTime()));
		}
		if(form.getAction().equals("retTempReleaseView")){
			form.setReturnReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RETURN_REASON, form.getReturnReason()));
			form.setReturnTime(JuvenileFacilityHelper.getDateWithColon(form.getReturnTime()));
		}
		//Set form values related to inTransfer.
		if(form.getAction().equals("inTransferView")){
			form.setReleasedToDesc(ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getReleasedFromDetentionCodes(), form.getReleasedTo()));
			if(form.getReleaseAuthority()!=null&&!form.getReleaseAuthority().isEmpty()){ //#51735 changes.
				if(form.getReleaseAuthority().trim().length()<4){
					IName name =SecurityUIHelper.getUserName("UV"+form.getReleaseAuthority());
					if(name!=null){
						form.setReleaseAuthorityDesc(name.getFormattedName());
					}
				}
			}
			form.setTempReleaseReasonDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.TEMP_RELEASE_REASON, form.getTempReleaseReason()));
			form.setOutcomeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_OUTCOME, form.getOutcome()));
			form.setTransferToFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, form.getTransferToFacility()));
			form.setReleaseTime(JuvenileFacilityHelper.getDateWithColon(form.getReleaseTime()));
		}
		
		// referral Transfer view.
		if(form.getAction().equals("referralTransferView"))
		{
			String transferToRef[]= StringUtils.split(form.getTransferToReferral(), '-');
			int transferToRefLength = StringUtils.countMatches(form.getTransferToReferral(), "-");
			if(transferToRef!=null){
				if(transferToRefLength == 2)
				{
					form.setBookingReferral(transferToRef[0]);
					form.setBookingSupervisionNum(transferToRef[1]);
				}
				else
				{
					form.setBookingReferral(transferToRef[0]);
				}
			}
			
			//offense Record set,booking referral, booking supervision, offense Level
			if(form.getReferrals()!=null && !form.getReferrals().isEmpty())
			{
				List<JuvenileProfileReferralListResponseEvent>  profileRespListDetails =(List<JuvenileProfileReferralListResponseEvent>)form.getReferrals();
				if(profileRespListDetails!=null)
				{
					Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = profileRespListDetails.iterator();
					while (juvProfResItr.hasNext())
					{
						JuvenileProfileReferralListResponseEvent profileRespEvent = juvProfResItr.next();
						if(form.getBookingReferral().equals(profileRespEvent.getReferralNumber()))
						{
							form.setBookingPetitionNum(profileRespEvent.getPetitionNumber());
					  	 	form.setBookingOffense(profileRespEvent.getMostSevereOffense().getOffenseDescription());
					  	    form.setBookingOffenseCd(profileRespEvent.getMostSevereOffense().getOffenseCodeId());
					  	    form.setBookingOffenseLevel(profileRespEvent.getMostSevereOffense().getOffenseLevelId());
					  	    form.setSeveritySubType(profileRespEvent.getMostSevereOffense().getSeveritySubtype());
					  	   
							if(profileRespEvent.getCasefiles()!=null)
						  	{
						  		Iterator<JuvenileCasefileResponseEvent> casefilesIter = profileRespEvent.getCasefiles().iterator();
								while(casefilesIter.hasNext())
								{
									JuvenileCasefileResponseEvent casefileResp = (JuvenileCasefileResponseEvent)casefilesIter.next();
								  	if(casefileResp!=null && casefileResp.getSupervisionNum()!=null)
								  	{ 
								  	  if(form.getBookingSupervisionNum().equals(casefileResp.getSupervisionNum()))
								  	  {
								  	 	  form.setBookingPetitionNum(profileRespEvent.getPetitionNumber());
								  	 	  form.setBookingOffense(profileRespEvent.getMostSevereOffense().getOffenseDescription());
								  	  	  form.setBookingOffenseCd(profileRespEvent.getMostSevereOffense().getOffenseCodeId());
								  	   	  form.setBookingOffenseLevel(profileRespEvent.getMostSevereOffense().getOffenseLevelId());
								  	   	  form.setSeveritySubType(profileRespEvent.getMostSevereOffense().getSeveritySubtype());
								  	   	  break;
									   }
								  	}
								}
						  	}
						}
					}
				}
			}
			
			List<JuvenileProfileReferralListResponseEvent> referralList =  JuvenileFacilityHelper.getCurrentReferrals(briefingDetailsForm,form);
			form.setReferrals(referralList);
			
			return aMapping.findForward("refTransferNext");
		} 
		
		if(form.getAction().equals("tempReleaseDecisionView"))
		{
		    return aMapping.findForward("tempReleaseDecisionNext");
		}
		
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
	public ActionForward courtAdmin(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse) 
        {
        	
	    JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    String juvnum = aRequest.getParameter("juvnum");
	    JJSHeader header = JuvenileFacilityHelper.getJJSHeader(juvnum);
        	//add the code for email and activity.
	    //email
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date currDate = new Date();
	    String today = sdf.format(currDate);
	    SimpleDateFormat time = new SimpleDateFormat("HH:MM");
	    String timeNow = time.format(currDate);
	    Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("TEMP REL CONTACT GROUP");
	    /*if (briefingDetailsForm.getTempReleaseRequestDecision().equals("N"))	    
		securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("TEMP REL CONTACT GROUP");	    
	    else
	    {
		if (briefingDetailsForm.getTempReleaseRequestDecision().equals("C"))
		    securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("TEMP REL CONTACT GROUP");//rename with new group
		    
	    }*/
		
		if(securityRespEvent!=null){
			 Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
		
		    while(securityRespIter.hasNext())
		    {
			OfficerProfileResponseEvent	securityResEvent =	securityRespIter.next();
						
			if(securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals(""))
		    	{
    			//send email
    			SendEmailEvent sendEmailEvent = new SendEmailEvent();
    			StringBuffer message = new StringBuffer(100);
    			sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
		    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
    			sendEmailEvent.setSubject(" Request for Temporary Release: " + briefingDetailsForm.getProfileDetail().getJuvenileNum() + "," + briefingDetailsForm.getProfileDetail().getFormattedName());
    			message.append("Court Administration acceptance or rejection for temporary release is required for the following: "); 
    			message.append(System.getProperty("line.separator"));
    			message.append("Temporary Release is requested by ");
    			message.append(PDSecurityHelper.getLogonId()+", "+today+", "+timeNow+" ");
    			message.append(System.getProperty("line.separator"));
    			message.append(briefingDetailsForm.getProfileDetail().getFormattedName()+" is currently admitted in "+header.getFacilityCode());
    			message.append(", Referral# ");
    			message.append(briefingDetailsForm.getAdmissionInfo().getReferralNumber());
    			message.append(" on ");
    			message.append(DateUtil.dateToString(briefingDetailsForm.getAdmissionInfo().getAdmitDate(), DateUtil.DATE_FMT_1)+" .");
    			message.append(System.getProperty("line.separator"));
    			if (briefingDetailsForm.getTempReleaseRequestDecision().equals("N")&&briefingDetailsForm.getTempDistReleaseRequestDecision().equals("C"))
    			    message.append("Juvenile has a scheduled district and detention court hearing for today, "+today+" .");
    			else
    			{
        			if (briefingDetailsForm.getTempReleaseRequestDecision().equals("N"))
        			    message.append("Juvenile has a scheduled detention hearing for today, "+today+" .");
        			else
        			{
            			if (briefingDetailsForm.getTempDistReleaseRequestDecision().equals("C"))
            			    message.append("Juvenile has a scheduled district court hearing for today, "+today+" .");  
        			}
    			}
    			
    			sendEmailEvent.setMessage(message.toString());
    		    	dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    		dispatch.postEvent(sendEmailEvent);
		    	}//end if officer email
		    }//end while
		}
		//email end
		//code to update the flg to P
		    
		    IHome home= new Home();
		    try{
        			//add if condition to update other flag
        			if (briefingDetailsForm.getTempReleaseRequestDecision().equals("N"))
        			{
        			    header.setReleaseDecisionStatus("P");
        			    briefingDetailsForm.setTempReleaseRequestDecision("S");//setting it to S not to show the P,R,A messages but success message
        			}
        			/*else
        			{*/
        			    if (briefingDetailsForm.getTempDistReleaseRequestDecision().equals("C"))
        			    {
        				header.setDistReleaseDecisionStatus("P");
        				briefingDetailsForm.setTempDistReleaseRequestDecision("S");
        			    }
        			    
        			//}
        	 		home.bind(header);
			}
		    catch(Exception ex){
				ErrorResponseEvent evt = new ErrorResponseEvent();
				evt.setException(ex);
				evt.setMessage("**** Exception while updating JJS HEADER****");
				dispatch.postEvent(evt);
			}
		//Generate Temporary Release activity.
	            String title= "Temporary Release Request - " +header.getFacilityCode();
	            StringBuffer comments = new StringBuffer();
	            comments.append("Comments: ")
	                        .append("A temporary release request was submitted to Court Administration by "+PDSecurityHelper.getLogonId());
	            JuvenileFacilityHelper.createActivity(briefingDetailsForm.getSupervisionNum(), ActivityConstants.ACTIVITY_CATEGORY_RESIDENTIAL, ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT,
	        	    ActivityConstants.TEMPORARY_RELEASE_REQUEST, title, comments.toString(),null,DateUtil.getCurrentDate(),null);//ActivityConstants.TEMPORARY_RELEASE_DECISION
		//
		
		
		//message Temporary Release request is sent to Court Administration		 

        	return aMapping.findForward("TempReleaseRequest");
	}
	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction #addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.escape","escape");
		keyMap.put("button.returnEscapee","returnEscapee");
		keyMap.put("button.release","release");
		keyMap.put("button.tempRelease","tempRelease");
		keyMap.put("button.tempReleaseDecision","tempReleaseDecision");
		keyMap.put("button.returnTempRelease","retTempRelease");
		keyMap.put("button.facilityTransfer","inTransfer");
		keyMap.put("button.refTransfer","referralTransfer");
		keyMap.put("button.next","next");
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.courtAdmin","courtAdmin");
	}
	
	

	
}