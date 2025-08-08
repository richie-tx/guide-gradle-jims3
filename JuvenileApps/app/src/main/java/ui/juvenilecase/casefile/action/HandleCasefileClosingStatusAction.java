package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.address.reply.AddressResponseEvent;
import messaging.casefile.CreateActivityEvent;
import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.GetCasefileClosingExceptionsEvent;
import messaging.casefile.GetCasefileNonComplianceNoticesEvent;
import messaging.casefile.SaveCasefileClosingProbDatesFlagEvent;
import messaging.casefile.UpdateJuvenileCasefileEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CasefileExceptionResponseEvent;
import messaging.casefile.reply.CasefileNonComplianceNoticeResponseEvent;
import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileDSPlacementResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileReferralProbationDatesResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.GetSupervisionTypeTJJDMapEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.OverrideOptionsResponseEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.referral.GetCasefileOffensesEvent;
import messaging.referral.UpdateJuvenileReferralEvent;
import messaging.referral.UpdateJuvenileReferralProbDatesEvent;
import messaging.riskanalysis.GetRiskAssessmentsByCasefileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.CasePlanConstants;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileCasefileNonComplianceControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.PDJuvenileFamilyConstants;
import naming.PDJuvenileWarrantConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.config.FormBeanConfig;

import pd.juvenilecase.JJSFacility;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CasefileClosingForm.CasefileException;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

public class HandleCasefileClosingStatusAction extends LookupDispatchAction {
	/**
	 * @roseuid 439608B20389
	 */
	public HandleCasefileClosingStatusAction() {
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward checkCasefileClosingStatus(ActionMapping aMapping,	ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
		String casefileID = aRequest.getParameter("casefileId");
		
		myClosingForm.clearAll();
		myClosingForm.setClosingInfoFound(true);
		myClosingForm.setCaseLoadManager(false);
		myClosingForm.setHasNonComplianceIncompleteStatus(false);
		myClosingForm.setHasGuardianException(false);
		myClosingForm.setHasPendingTJJDException(false);
		myClosingForm.setHasActiveProgramReferral(false);
		myClosingForm.setHasGoalsException(false);
		myClosingForm.setHasRulesException(false);
		myClosingForm.setCasePlanNotAcknowledgedException(false);
		myClosingForm.setHasJuvenileInFacility(false);

		boolean exceptionFound = false;

		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		if (casefileForm == null) {
			casefileForm = new JuvenileCasefileForm();
			UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(casefileForm,	casefileID);
		} 
		if (casefileID == null && casefileForm.getSupervisionNum() != null){
			casefileID = casefileForm.getSupervisionNum(); 
		}
		
		//remove risk assesment requirement for supervision category type AR(Post Adjudicated Residential) - US 106778
		if(casefileForm.getSupervisionCategoryId() != null && "AR".equalsIgnoreCase(casefileForm.getSupervisionCategoryId())){		    
		    casefileForm.setRiskAssessmentNeeded(false);
		    casefileForm.setProgressAssessmentNeeded(false);
		    casefileForm.setResidentialAssessmentNeeded(false);
		    casefileForm.setResidentialProgressAssessNeeded(false);
		    casefileForm.setCommunityAssessmentNeeded(false);
		    casefileForm.setReferralAssessmentNeeded(false);
		}

		// check if CommonApp exists
		String currentUserId = UIUtil.getCurrentUserID();
		if (currentUserId != null) {
			ValidateOfficerProfileEvent event = (ValidateOfficerProfileEvent) EventFactory
					.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
			event.setLogonId(currentUserId);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			Object obj = MessageUtil.filterComposite(compositeResponse,	OfficerProfileResponseEvent.class);
			if (obj != null) {
				OfficerProfileResponseEvent resp = (OfficerProfileResponseEvent) obj;
				if (notNullNotEmptyString(resp.getOfficerSubType())	&& resp.getOfficerSubType().equalsIgnoreCase("CASELOAD MANAGER")) {
					myClosingForm.setCaseLoadManager(true);
					if (casefileID != null) {
						UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(casefileForm, casefileID);
					}
				}
			}
		}
		if (notNullNotEmptyString(casefileID)) {
			if (casefileForm != null && casefileForm.getSupervisionNum() != null
					&& !casefileForm.getSupervisionNum().equals(casefileID)) {
				UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(casefileForm, casefileID);
			}
		}

		aRequest.getSession(true).setAttribute("juvenileCasefileForm", casefileForm);

		myClosingForm.setSupervisionNumber(casefileForm.getSupervisionNum());
		myClosingForm.setJuvenileNum(casefileForm.getJuvenileNum());
		myClosingForm.setActivationDateStr(casefileForm.getActivationDateStr());		
			
		UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, casefileForm.getJuvenileNum());		
		UIJuvenileCasefileClosingHelper.getProfileReferrals(myClosingForm, casefileForm.getJuvenileNum());		
		String supervisionCat = casefileForm.getSupervisionCategoryId();
		JuvenileProfileDetailResponseEvent profileResponse = (JuvenileProfileDetailResponseEvent) aRequest.getSession(true).getAttribute("JuvenileDetailForm");
		if (profileResponse != null ) {
		    myClosingForm.setYouthDeathReason(profileResponse.getYouthDeathReason());
		    myClosingForm.setYouthDeathReasonDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.CAUSE_OF_DEATH, myClosingForm.getYouthDeathReason()));
		    myClosingForm.setYouthDeathVerification(profileResponse.getYouthDeathVerification());
		    myClosingForm.setYouthDeathVerificationDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.DEATH_VERIFICATION, myClosingForm.getYouthDeathVerification()));
		    if ( profileResponse.getDeathDate() != null ) {
			myClosingForm.setDeathDate(DateUtil.dateToString(profileResponse.getDeathDate(), DateUtil.DATE_FMT_1));
		    }
		    myClosingForm.setDeathAge(profileResponse.getDeathAge());
		}
		if (!supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) &&
			!supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ) &&
			!supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES))  {
			
			UIJuvenileCasefileClosingHelper.getReferrals(myClosingForm,	casefileForm.getJuvenileNum());
			if (myClosingForm.getReferrals() != null && casefileForm.getJuvenileNum() != null) {
				getMaxSeverityOffense(myClosingForm.getReferrals(), casefileForm.getJuvenileNum());
				getFinalDisposition(myClosingForm.getReferrals(), casefileForm.getJuvenileNum());
			}
		}
		
		String refNumber = ""; 
		if (casefileForm.getControllingReferralNumber() != null) {
			refNumber = casefileForm.getControllingReferralNumber();
		} else {
			casefileForm.setControllingReferralNumber(UIConstants.EMPTY_STRING);
		}
		if (supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) ||
			supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ) ||
			supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES) ||
			refNumber.equals("") )  {
				UIJuvenileCasefileClosingHelper.getReferrals(myClosingForm,	casefileForm.getJuvenileNum());
				if (myClosingForm.getReferrals() != null && casefileForm.getJuvenileNum() != null) {
					getMaxSeverityOffense(myClosingForm.getReferrals(), casefileForm.getJuvenileNum());
					getFinalDisposition(myClosingForm.getReferrals(), casefileForm.getJuvenileNum());
				}
		}

		String caseStatus = casefileForm.getCaseStatusId();
		if (caseStatus != null && caseStatus.equals(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)) {
			Collection<CasefileExceptionResponseEvent> myExceptions = getCasefileException(casefileForm.getSupervisionNum());
			/*if ( profileResponse != null
				&& ( profileResponse.getHispanic() == null
						|| profileResponse.getHispanic() .length() == 0
						|| profileResponse.getIsUSCitizen() == null
						||  profileResponse.getIsUSCitizen().length() == 0)
					){
			    CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			    casefileExceptionResponseEvent.setExceptionId(profileResponse.getJuvenileNum());
			    casefileExceptionResponseEvent.setExceptionType(PDJuvenileConstants.HISPANIC_ENTRY_REQUIRED);
			    casefileExceptionResponseEvent.setExceptionMessage("Hispanic data entry/verification required. Please update the Juvenile's Profile for entry/verification.");
			    if ( myExceptions == null ){
				myExceptions = new ArrayList<CasefileExceptionResponseEvent>();
				myExceptions.add(casefileExceptionResponseEvent);
			    } else {
				myExceptions.add(casefileExceptionResponseEvent);
			    }
			}*/
                	    // Adding Hispanic Exception code below US 186994 April 2025
                	    if ((casefileForm.getHispanicIndicatorNeedsUpdate()))
                	    {
                		CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
                		casefileExceptionResponseEvent.setExceptionId(profileResponse.getJuvenileNum());
                		casefileExceptionResponseEvent.setExceptionType(PDJuvenileConstants.HISPANIC_ENTRY_REQUIRED);
                		casefileExceptionResponseEvent.setExceptionMessage("Hispanic data entry/verification required. Please update the Juvenile's Profile for entry/verification.");
                		if (myExceptions == null)
                		{
                		    myExceptions = new ArrayList<CasefileExceptionResponseEvent>();
                		    myExceptions.add(casefileExceptionResponseEvent);
                		}
                		else
                		{
                		    myExceptions.add(casefileExceptionResponseEvent);
                		}
                	    }// Adding Hispanic Exception code ABOVE for US 186994 April 2025
			// Adding VOP Exception code here for US 186994 April 2025
			if("Yes".equalsIgnoreCase(casefileForm.getIsVOPEntryNeeded())){
			    CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			    //casefileExceptionResponseEvent.setExceptionId(profileResponse.getJuvenileNum());
			    casefileExceptionResponseEvent.setExceptionType(PDJuvenileConstants.VOP_DETAILS_REQUIRED);
			    casefileExceptionResponseEvent.setExceptionMessage("VOP Details must be provided.");
			    if ( myExceptions == null ){
				myExceptions = new ArrayList<CasefileExceptionResponseEvent>();
				myExceptions.add(casefileExceptionResponseEvent);
			    } else {
				myExceptions.add(casefileExceptionResponseEvent);
			    }
			}// Adding VOP Exception code here for US 186994 April 2025
			
			if ( casefileForm.getSchoolHistoryNeedsUpdate() ) {
			    CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			    casefileExceptionResponseEvent.setExceptionId( casefileForm.getJuvenileNum() );
			    casefileExceptionResponseEvent.setExceptionType(PDJuvenileConstants.SCHOOL_HISTORY_UPDATE_ONCE_YEAR_REQUIRED);
			    casefileExceptionResponseEvent.setExceptionMessage("School history update is required once a year .");
			    
			    if ( myExceptions == null ){
				myExceptions = new ArrayList<CasefileExceptionResponseEvent>();
				myExceptions.add(casefileExceptionResponseEvent);
			    } else {
				myExceptions.add(casefileExceptionResponseEvent);
			    }
			}
			
			if ( casefileForm.getIsSubstanceAbuseTjjdRequired() ){
			    CasefileExceptionResponseEvent casefileExceptionResponseEvent = new CasefileExceptionResponseEvent();
			    casefileExceptionResponseEvent.setExceptionId( casefileForm.getJuvenileNum() );
			    casefileExceptionResponseEvent.setExceptionType(PDJuvenileConstants.SUBSTANCE_ABUSE_TJJD_REQUIRED);
			    casefileExceptionResponseEvent.setExceptionMessage("Substance abuse TJJD is required.");
			    
			    if ( myExceptions == null ){
				myExceptions = new ArrayList<CasefileExceptionResponseEvent>();
				myExceptions.add(casefileExceptionResponseEvent);
			    } else {
				myExceptions.add(casefileExceptionResponseEvent);
			    }
			}
			if (nullOrEmptyCollection(myExceptions)) {
				// check for Program Referrals
				ArrayList<CasefileException> allExceptions = new ArrayList<CasefileException>();
				UIJuvenileCasefileClosingHelper.getProgramReferralExceptions(myClosingForm, allExceptions);
				if (nullOrEmptyCollection(allExceptions)) {
					myClosingForm.setAction(UIConstants.UPDATE_SUMMARY);
					myClosingForm.setSecondaryAction(UIConstants.EMPTY_STRING);
					myClosingForm.setSelectedValue(UIConstants.EMPTY_STRING);
					myClosingForm.setControllingReferral(UIConstants.EMPTY_STRING);
				  // RRY Removed CLE Activity check on 9/19/13
				} else {
					myClosingForm.setCasefileExceptions( allExceptions );
					myClosingForm.setHasActiveProgramReferral( true );
				}
			} else if (!cleActivityExist(casefileForm)) { 
				ArrayList<CasefileException> allExceptions = new ArrayList<CasefileException>();
				if (myExceptions != null) {
					for (CasefileExceptionResponseEvent exceptionEvent : myExceptions) {
						if (exceptionEvent != null) {
						    	if ( myClosingForm.getOverrideOptionCodes().size() == 0 
						    		&& ( "MAYSI".equals( exceptionEvent.getExceptionType() )
						    			|| "PACT".equals( exceptionEvent.getExceptionType() ) ) ){
						    	    List<OverrideOptionsResponseEvent> overrideOptionCoddes = ComplexCodeTableHelper.getOverrideOptionCodes();
						    	    if ( overrideOptionCoddes != null
						    		    && overrideOptionCoddes.size() > 0 ){
						    		myClosingForm.getOverrideOptionCodes().addAll(overrideOptionCoddes);
						    	    }
						    	}
							CasefileException casefileException = UIJuvenileCasefileClosingHelper.getExceptionFROMExceptionRespEvt(exceptionEvent);
							allExceptions.add(casefileException);
						}
					}
				}
				
				UIJuvenileCasefileClosingHelper.getProgramReferralExceptions(myClosingForm, allExceptions);
				myClosingForm.setCasefileExceptions(allExceptions);
				if (allExceptions != null && allExceptions.size() > 0)
				{
					exceptionFound = true;
					for (int x=0; x<allExceptions.size(); x++)
					{	
						CasefileException cfe = (CasefileException) allExceptions.get(x);
						if (!PDJuvenileCaseConstants.MAYSI_NOT_DONE.equals(cfe.getExceptionType() ) &&
							!PDJuvenileCaseConstants.BENEFITS_NOT_DONE.equals(cfe.getExceptionType()) &&
							!PDJuvenileCaseConstants.RISK_NOT_DONE.equals(cfe.getExceptionType()) )
							{
								myClosingForm.setHasActiveProgramReferral(true);
								break;
						}	
					}
				}	
			}
		}
		
		// CHECK FOR GUARDIAN ADDRESS
		boolean guardianExceptionFound = true;
		boolean guardianInHomeExceptionFound = true;
		int guardianCount = 0;
		String juvenileNum = casefileForm.getJuvenileNum();

		GetFamilyConstellationsEvent getFamilyEvent = 
			(GetFamilyConstellationsEvent)EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
		getFamilyEvent.setJuvenileNum(juvenileNum);

		CompositeResponse response = MessageUtil.postRequest(getFamilyEvent);
		Collection<FamilyConstellationMemberListResponseEvent> guardians = 
			MessageUtil.compositeToCollection(response,	FamilyConstellationMemberListResponseEvent.class);

		if( guardians != null  &&  !guardians.isEmpty() )
		{
			Iterator<FamilyConstellationMemberListResponseEvent> iter = guardians.iterator();
			while( iter.hasNext() )
			{
				FamilyConstellationMemberListResponseEvent fme = iter.next();
				if( fme.isGuardian() )
				{
					if (fme.isInHomeStatus()) {
						guardianInHomeExceptionFound = false;						
					}

					guardianCount++;
// verify current address type Residence and is complete -- ER 74082 08/16/2012	
					List<AddressResponseEvent> address = getCurrentGuardianAddress(fme.getMemberNum());
					if (address.size() > 0) 
					{	
						AddressResponseEvent cgAddress = (AddressResponseEvent) address.get(0);
						if ("RES".equalsIgnoreCase(cgAddress.getAddressTypeId())  && // bug fix 22327 starts
						     (cgAddress.getCity() != null && !cgAddress.getCity().isEmpty() &&
							 cgAddress.getStreetNum() != null && !cgAddress.getStreetNum().isEmpty() &&
							 cgAddress.getStreetName() != null && !cgAddress.getStreetName().isEmpty() &&
							 cgAddress.getZipCode() != null && !cgAddress.getZipCode().isEmpty() &&
							 cgAddress.getStateId() != null && !cgAddress.getStateId().isEmpty())) // bug fix 22327 ends
							{
								guardianExceptionFound = false;
							}
					} else {
						break;
					}
				}
			}
		}else {
			guardians = new ArrayList<FamilyConstellationMemberListResponseEvent>();
		}
		
		if (guardianExceptionFound || guardianInHomeExceptionFound) {
// get instance of juvenileFamilyForm to prevent java:servlet error in linked to jsp
			JuvenileFamilyForm juvFamilyForm = (JuvenileFamilyForm) getSessionForm(aMapping, aRequest, "juvenileFamilyForm", true);
			juvFamilyForm.clear();
			juvFamilyForm.setAction("");
// get instance of juvenileMemberForm to load values needed for jsp display			
			JuvenileMemberForm juvMemberForm =  (JuvenileMemberForm) getSessionForm(aMapping, aRequest, "juvenileMemberForm", true);
			juvMemberForm.clearAll();
			List<FamilyConstellationMemberListResponseEvent> guardiansList = (List<FamilyConstellationMemberListResponseEvent>)guardians;
			for (int x =0; x<guardiansList.size(); x++){
				FamilyConstellationMemberListResponseEvent resp = guardiansList.get(x); 
				if( resp.isGuardian() )
				{
					if (resp.getMemberNum()!= null && !"".equals(resp.getMemberNum()))
					{
						juvMemberForm.setMemberNumber(resp.getMemberNum());
						Name memberName = new Name(resp.getFirstName(), resp.getMiddleName(), resp.getLastName());
						juvMemberForm.setName(memberName);
						// load constellation on family form			
						juvFamilyForm.setJuvenileNumber(juvenileNum);
						loadConstellationInfo(juvFamilyForm);
						break;
					}	
				}	
			}
			ArrayList<CasefileException> allExceptions = new ArrayList<CasefileException>();
			if (myClosingForm.getCasefileExceptions() != null) {
				allExceptions = (ArrayList) myClosingForm.getCasefileExceptions();
			}
			if (guardianExceptionFound) {
				
				CasefileException caseFileException = new CasefileException();
				caseFileException.setExceptionType(PDJuvenileCaseConstants.GUARDIAN_IN_HOME_ADDRESS_NOT_FOUND);
				if (guardianCount > 1){
					caseFileException.setExceptionType(PDJuvenileCaseConstants.GUARDIANS_IN_HOME_ADDRESS_NOT_FOUND); 
				}
				caseFileException.setExceptionMessage(UIConstants.EMPTY_STRING );
				allExceptions.add( caseFileException );	
				myClosingForm.setHasGuardianException(true);
			}
			if (guardianInHomeExceptionFound) {
				
				CasefileException cfException = new CasefileException();
				cfException.setExceptionType(PDJuvenileCaseConstants.GUARDIAN_IN_HOME_STATUS);
				cfException.setExceptionMessage(UIConstants.EMPTY_STRING );
				allExceptions.add( cfException );
				myClosingForm.setHasGuardianException(true);
			}
			
			myClosingForm.setCasefileExceptions(allExceptions);
			return aMapping.findForward(UIConstants.EXCEPTIONS); //bug fix 22327
		}
//		Added 5/22/2013  ER 74940		
//		Check for Non Compliance Completion Status not equal to YES
//		Added 8/13/2013  defect 75939		
//		Check for Non Compliance Completion Status equal to Pending -- blank/empty status value		
		GetCasefileNonComplianceNoticesEvent ncEvent = (GetCasefileNonComplianceNoticesEvent) EventFactory.getInstance(JuvenileCasefileNonComplianceControllerServiceNames.GETCASEFILENONCOMPLIANCENOTICES);
        ncEvent.setCasefileId(casefileID);
        List notices = MessageUtil.postRequestListFilter(ncEvent, CasefileNonComplianceNoticeResponseEvent.class);
        if (notices != null && !notices.isEmpty()) {
        	for (int n=0; n<notices.size(); n++) {
        		CasefileNonComplianceNoticeResponseEvent cncEvent = (CasefileNonComplianceNoticeResponseEvent) notices.get(n);
        		if (cncEvent.getCompletionStatusId() == null || "".equalsIgnoreCase(cncEvent.getCompletionStatusId() ) )
        		{
        			ArrayList<CasefileException> allExceptions = new ArrayList<CasefileException>();
            		if (myClosingForm.getCasefileExceptions() != null) {
            			allExceptions = (ArrayList) myClosingForm.getCasefileExceptions();
            		}
        			CasefileException caseFileException = new CasefileException();
        			caseFileException.setExceptionType(PDJuvenileCaseConstants.NON_COMPLIANCE_COMPLETION_NO_STATUS_FOUND);
        			caseFileException.setExceptionMessage(UIConstants.EMPTY_STRING );
        			allExceptions.add( caseFileException );
        			myClosingForm.setCasefileExceptions(allExceptions);
        			myClosingForm.setHasNonComplianceIncompleteStatus(true);
        			break;
        		}
        	}
        }
		//added for the caseplan not acknowlwdged exception ER  JIMS200076788
		//to get the caseplan details
		  GetCaseplanDetailsEvent det = (GetCaseplanDetailsEvent) EventFactory
          .getInstance(JuvenileCasePlanControllerServiceNames.GETCASEPLANDETAILS);
		  det.setSupervisionNumber(casefileID);	
		  
		  CompositeResponse compositeResponsecaseplan = MessageUtil.postRequest(det);		  
		  CaseplanDetailsResponseEvent cpEvt = (CaseplanDetailsResponseEvent) MessageUtil.filterComposite(
				  compositeResponsecaseplan, CaseplanDetailsResponseEvent.class);		 
		  if (cpEvt.getCaseplanID() != null)
		  {
			  String casePlanStatusId = (String)cpEvt.getStatusId();			  
			  if (!casePlanStatusId.equals(CasePlanConstants.CP_STATUS_SIGNED) ) {	
					ArrayList<CasefileException> allExceptions = new ArrayList<CasefileException>();
            		if (myClosingForm.getCasefileExceptions() != null) {
            			allExceptions = (ArrayList) myClosingForm.getCasefileExceptions();
            		}
        			CasefileException caseFileException = new CasefileException();
        			caseFileException.setExceptionType(PDJuvenileCaseConstants.CASEPLAN_STATUS_NOT_ACKNOWLEDGED);
        			caseFileException.setExceptionMessage(UIConstants.EMPTY_STRING );
        			allExceptions.add( caseFileException );
        			myClosingForm.setCasefileExceptions(allExceptions);
        			myClosingForm.setCasePlanNotAcknowledgedException(true);
				}
		  }	
		  
			//added for U.S #11382
		    ArrayList<CasefileException> allExceptions = new ArrayList<CasefileException>();
	  		if (myClosingForm.getCasefileExceptions() != null) {
	  			allExceptions = (ArrayList) myClosingForm.getCasefileExceptions();
	  		}
			allExceptions = UIJuvenileCasefileClosingHelper.getFacilityClosingExceptions(myClosingForm, allExceptions);
			if(allExceptions!=null){
				myClosingForm.setCasefileExceptions(allExceptions);
			}
	        //added for U.S #11382
		//----ended ----
        if (myClosingForm.isHasActiveProgramReferral() || myClosingForm.isHasJuvenileInFacility() || myClosingForm.isHasNonComplianceIncompleteStatus() || exceptionFound ) {
        	myClosingForm.setHasActiveProgramReferral(false);  // if true, includes TJJD, Program Referrals, Goals and/or Rules
	        if ( myClosingForm.getCasefileExceptions() != null ){
	        	
	        	List<CasefileException> temp = new ArrayList( myClosingForm.getCasefileExceptions() );
	        	for ( int x=0; x<temp.size(); x++ )
				{	
					CasefileException cfe = (CasefileException) temp.get(x);
					/*if (PDJuvenileCaseConstants.TJJD_NOT_COMPLETED.equals(cfe.getExceptionType() ) ) {
						myClosingForm.setHasPendingTJJDException(true);
					} removed for task 41376*/
					if (PDJuvenileCaseConstants.PROG_REFERRALS_NOT_DONE.equals(cfe.getExceptionType() ) ) {
						myClosingForm.setHasActiveProgramReferral(true);
					}
					if (PDJuvenileCaseConstants.GOALS_NOT_DONE.equals(cfe.getExceptionType() ) ) {
						myClosingForm.setHasGoalsException(true);
					}
					if (PDJuvenileCaseConstants.RULES_NOT_DONE.equals(cfe.getExceptionType() ) ) {
						myClosingForm.setHasRulesException(true);
					}
					if(PDJuvenileCaseConstants.JUVENILE_IN_FACILITY.equals(cfe.getExceptionType())||PDJuvenileCaseConstants.JUVENILE_REFFERAL_NOT_ASSOCIATED.equals(cfe.getExceptionType())){
						myClosingForm.setHasJuvenileInFacility(true);
					}
				}	
				return aMapping.findForward(UIConstants.EXCEPTIONS);
	        }
		}
        
        // User-story 11079 starts
        //Get all the offenses associated to the casefile.
    	checkForTransferredOffenses(myClosingForm);
 	   // User-story 11079 ends
        
        
		// RETRIEVE CLOSING INFORMATION
		if (caseStatus != null) {
			GetCasefileClosingDetailsEvent casefileClosingDetailEvent = (GetCasefileClosingDetailsEvent) EventFactory
					.getInstance(JuvenileCasefileControllerServiceNames.GETCASEFILECLOSINGDETAILS);
			casefileClosingDetailEvent.setSupervisionNumber(myClosingForm.getSupervisionNumber());

			CompositeResponse compositeResponse = MessageUtil.postRequest(casefileClosingDetailEvent);
			CasefileClosingResponseEvent casefileClosingResponseEvent = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse,
							CasefileClosingResponseEvent.class);

			if (casefileClosingResponseEvent == null && !caseStatus.equals(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE,
						new ActionMessage("error.generic","No Casefile Closing Info found for this Supervision Number"));
				saveErrors(aRequest, errors);
				myClosingForm.setClosingInfoFound(false);
			}

			UIJuvenileCasefileClosingHelper.setClosingInfoFROMClosingRespEvt(myClosingForm, casefileClosingResponseEvent);
			if (caseStatus.equals(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)) {
				myClosingForm.setSupervisionEndDate(UIConstants.EMPTY_STRING);
			}
		}
		
		
         //getting all other active and pending casefiles having same supervision category id and getting the earliest begin date for ER JIMS200076597
		
        String closingDate = myClosingForm.getSupervisionEndDate();
        String closingCasefileId = myClosingForm.getSupervisionNumber();
        Date dClosingdate = DateUtil.stringToDate(closingDate, "MM/dd/yyyy");
		if(myClosingForm != null)
		{
			//Calling the event to get the assignment date of all the casefiles
			SearchJuvenileProfileCasefileListEvent search = (SearchJuvenileProfileCasefileListEvent)			
			EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);
			search.setJuvenileId(myClosingForm.getJuvenileNum());
			List casefiles = MessageUtil.postRequestListFilter(search,
					JuvenileProfileCasefileListResponseEvent.class);
						
			List activePendingCaseFilesList = new ArrayList(); 
			Date earliestAssignmentDate = null;
			String earliestDateCaseFile = "";
			if(casefiles != null)
			{
				Iterator iter = casefiles.iterator();	
				//getting the supervision category from supervision type id of this casefile
	        	String supCategoryClosingCasefile = UIJuvenileCaseworkHelper.getSupCatFromType(casefileForm.getSupervisionTypeId());
				while (iter.hasNext()){					
					JuvenileProfileCasefileListResponseEvent respEvt = (JuvenileProfileCasefileListResponseEvent) iter.next();
					//get only Active or pending case file with similar supervision category 
					String supCategory= respEvt.getSupervisionCategory();
					String currSuperNum = respEvt.getSupervisionNum();
					//removing the closing casefile details from the iteration
					if(closingCasefileId.equals(currSuperNum)){
						continue;
					}
					String supervisionTypeDesc = respEvt.getSupervisionType(); 
					//when the supervision category is null then get the code from the codetable 
					if(supCategory == null){
						//getting the corresponding supervision type code of supervision type description
						Collection codes = CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE);
						Iterator codeIter = codes.iterator();
                        String supTypeId = "";
                        while(codeIter.hasNext()){
                        	CodeResponseEvent respEvent = (CodeResponseEvent)codeIter.next();
                                String currentCodeDesc = respEvent.getDescription();
                                if(currentCodeDesc.equalsIgnoreCase(supervisionTypeDesc)){
                                	supTypeId = respEvent.getCode();
                                }
                        }
                        //getting the supervision category of corresponding supervision type code
                        supCategory = UIJuvenileCaseworkHelper.getSupCatFromType(supTypeId);						 
					}
					boolean isClosingPostAdjCommOrRes = false;
					boolean includeCaseFile = false;
					if(supCategoryClosingCasefile != null && (supCategoryClosingCasefile.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM)||supCategoryClosingCasefile.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES))) {
						 isClosingPostAdjCommOrRes = true;
					}
					//checking whether the casefile must be active or pending
					if(respEvt.getCaseStatus().equals("ACTIVE")||respEvt.getCaseStatus().equals("PENDING")){						
						if(isClosingPostAdjCommOrRes){
							//if closing case file is of category Residential or Community, include all residential and community cateory casefiles for checking overlapping
							if(supCategory != null && (supCategory.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM) || supCategory.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES))){
								includeCaseFile = true;					
							}
						}/*else{
							//if closing case file is NOT Residential or Community category, only include matching catefory casefiles for checking overlapping
							if(supCategory != null && supCategory.equals(supCategoryClosingCasefile)){
								includeCaseFile = true;
							}
						}*/
					}

					if(includeCaseFile){					
						Date casefileAssignAddDate = DateUtil.stringToDate(respEvt.getAssignmentAddDate(), "MM/dd/yyyy");
						
						activePendingCaseFilesList.add(respEvt);
						//earliest activation date of all the casefiles
						if(earliestAssignmentDate == null){
							earliestAssignmentDate = casefileAssignAddDate;
							earliestDateCaseFile = currSuperNum;
						}else{
							if(casefileAssignAddDate.before(earliestAssignmentDate)){
								earliestAssignmentDate = casefileAssignAddDate;
								earliestDateCaseFile = currSuperNum;
							}
						}
					}					
				}
			}
			
			String earliestAsgnDateStr = "";
			if(earliestAssignmentDate != null){
				earliestAsgnDateStr = DateUtil.dateToString(earliestAssignmentDate, "MM/dd/yyyy");				
			}
			myClosingForm.setEarliestAssignAddDate(earliestAsgnDateStr);
			myClosingForm.setEarliestDateCasefile(earliestDateCaseFile);
					
		}
       
	//ended ER JIMS200076597
		
		UIJuvenileCasefileClosingHelper.getInterview(myClosingForm,	casefileForm.getJuvenileNum(), myClosingForm.getSupervisionNumber());
		if (myClosingForm.getSupervisionNumber() == null || myClosingForm.getSupervisionNumber().equals("") ||
			myClosingForm.getJuvenileNum() == null || myClosingForm.getJuvenileNum().equals("")) {
			ActionMessage myError=new ActionMessage("error.casefileUnavailable",myClosingForm.getSupervisionNumber());
			ArrayList coll=new ArrayList();
			coll.add(myError);
			sendToErrorPage(aRequest,coll);
			return aMapping.findForward(UIConstants.CANCEL);
		} 
		
		UIJuvenileCasefileClosingHelper.getProgramReferralExceptions(myClosingForm, new ArrayList());
		
		
		//---------------added for new exception by Sruti--------------
		
		//ended
		
		//do the transfer here,added for 11382
		String probationOfficerLogonId = null;
		String juvenileName=null;		
		if(myClosingForm.isCasefileAssociated()){
			
			String transferCasefileId = myClosingForm.getTransferCasefileId();
			if(transferCasefileId!=null){
				//update detention Record.
				JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvenileNum);
				if(facility!=null){
					facility.setCurrentSupervisionNum(transferCasefileId);
				}
				IHome home= new Home();
				home.bind(facility);
			}
			
			myClosingForm.setClosingInfoAvail(true);
			
			GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
			getCasefileEvent.setSupervisionNumber( transferCasefileId ) ;				
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( getCasefileEvent ) ;
			CompositeResponse compResponse = (CompositeResponse)dispatch.getReply( ) ;		
			JuvenileCasefileResponseEvent casefile = 
						(JuvenileCasefileResponseEvent)MessageUtil.filterComposite( compResponse, JuvenileCasefileResponseEvent.class );
			
			if(casefile != null && casefile.getSupervisionNum() != null){
				probationOfficerLogonId = casefile.getProbationOfficerLogonId();
				juvenileName= casefile.getJuvenileLastName() +", "+casefile.getJuvenileFirstName();
				//send notification.
				 JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
				 respEvt.setSubject("Due to casefile closure, facility admit records have been assigned to new supervision number.");
				 respEvt.setIdentity(probationOfficerLogonId);
				 respEvt.setJuvenileNum(casefileForm.getJuvenileNum());
				 String notifMessage = "Facility admit records exist for juvenile "+juvenileName+", Juvenile#: "+casefileForm.getJuvenileNum()+". Due to casefile closure, facility admit records have been assigned to "+transferCasefileId+".";
					 respEvt.setNotificationMessage(notifMessage);
						    	
				 CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
				 notificationEvent.setNotificationTopic("JC.FACILITY.CASEFILE.CLOSURE.NOTIFICATION");
				 notificationEvent.setSubject(respEvt.getSubject());
				 notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
				 notificationEvent.addContentBean(respEvt);
				 CompositeResponse compResp = MessageUtil.postRequest(notificationEvent);
			     MessageUtil.processReturnException( compResp ) ;
			     myClosingForm.setCasefileAssociated(false);
			}
			
		}
		
		if (caseStatus != null && caseStatus.equals(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)) {
		    String splcategoryId=null;
		    String supTJJDTypeId=null;
		    String tjjdindicator=null;
		    boolean probationFlag=false;
		    
		    GetJuvenileCasefileEvent getCasefileEvt = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);

		    	getCasefileEvt.setSupervisionNumber(myClosingForm.getSupervisionNumber());

			CompositeResponse res = MessageUtil.postRequest(getCasefileEvt);
			JuvenileCasefileResponseEvent casef = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(res, JuvenileCasefileResponseEvent.class);

			// check  referraldatesstatus==null
			if (casef != null)
			{
			    if(casef.getSupervisionTypeId() != null)
				{
				    GetSupervisionTypeTJJDMapEvent request = new GetSupervisionTypeTJJDMapEvent();
				    request.setSupervisionTypeId(casef.getSupervisionTypeId());
				    
				    CompositeResponse replyEvent = MessageUtil.postRequest(request);
				    SupervisionTypeMapResponseEvent tjjdMap = (SupervisionTypeMapResponseEvent) MessageUtil.filterComposite(replyEvent,SupervisionTypeMapResponseEvent.class);
				    
				    if( tjjdMap != null ){
					
					splcategoryId= tjjdMap.getSplCategoryId();
					supTJJDTypeId= tjjdMap.getSupTJJDTypeId();
					tjjdindicator = tjjdMap.getTjjdIndicator();
				    }
				
				}
			    if(casef.getProbationFlag()==null)//checking if the flag is already set means the dates are already given
			    	probationFlag=true;
			}
		
	            myClosingForm.setSkipSubOutCome(false);
		    boolean isNewSubOutComeSkipped = true;
		    
        	    if (supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_INDIRECT))
        	    {
        		List<CodeResponseEvent> filteredList = new ArrayList<>();
        		List<CodeResponseEvent> activeSupervisionOutcomesList=CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUPERVISIONOUTCOME,true);
        		
        		for (CodeResponseEvent event : activeSupervisionOutcomesList)
        		{
        		    if (event.getCode().equals("D")
        			    || event.getCode().equals("S")
        			    || event.getCode().equals("X"))
        		    {
        			filteredList.add(event);
        		    }
        		}
        
        		if (filteredList.size() > 0)
        		{
        		    myClosingForm.setActiveSupervisionOutcomesList(filteredList);
        		    myClosingForm.setSkipSubOutCome(true);
        		}
        		
        		isNewSubOutComeSkipped = false;
        	    }        
        	   if (supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ)) {
        	       
        	       if(splcategoryId == null ) { splcategoryId = ""; }
        	       
        		    List<CodeResponseEvent> filteredList = new ArrayList<>();
        		    List<CodeResponseEvent> activeSupervisionOutcomesList=CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUPERVISIONOUTCOME,true);

        		    for (CodeResponseEvent event : activeSupervisionOutcomesList) {
        		        if (splcategoryId.equalsIgnoreCase("DP")) {
        		            if (event.getCode().equals("D") || event.getCode().equals("S") || event.getCode().equals("X")) {
        		                filteredList.add(event);
        		            }
        		        } else {
        		            if (event.getCode().equals("D") || event.getCode().equals("S")) {
        		                filteredList.add(event);
        		            }
        		        }
        		    }

        		    if (!filteredList.isEmpty()) {
        		        myClosingForm.setActiveSupervisionOutcomesList(filteredList);
        		        if (!splcategoryId.equalsIgnoreCase("DP")) {
        		            myClosingForm.setSkipSubOutCome(true);
        		        }
        		    }

        		    if (splcategoryId.equalsIgnoreCase("DP")) {
        		        List<JuvenileCodeTableChildCodesResponseEvent> temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
        		        List<JuvenileCodeTableChildCodesResponseEvent> temp2 = new ArrayList<>();
        		        List<JuvenileCodeTableChildCodesResponseEvent> temp3 = new ArrayList<>();

        		        for (JuvenileCodeTableChildCodesResponseEvent joscre : temp1) {
        		            if (UIConstants.OUTCOME_COMPLETE.equalsIgnoreCase(joscre.getParentId()) && joscre.getCode().equalsIgnoreCase("SET")) {
        		                temp2.add(joscre);
        		            }
        		            if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(joscre.getParentId()) &&
        		                (joscre.getCode().equalsIgnoreCase("DAF") || joscre.getCode().equalsIgnoreCase("UNS"))) {
        		                temp3.add(joscre);
        		            }
        		        }

        		        myClosingForm.setOptionalSupervisionOutcomeDescList(temp2);
        		        myClosingForm.setRequiredSupervisionOutcomeDescList(temp3);
        		    }
        		    
        		    isNewSubOutComeSkipped = false;
        		}
        	      
        	       if (supervisionCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_PRE_PETITION)) {        		   
        		   if(tjjdindicator == null ) { tjjdindicator = ""; }        		   
        		    if (tjjdindicator != null) {
        		        List<CodeResponseEvent> filteredList = new ArrayList<>();
        		        List<CodeResponseEvent> activeSupervisionOutcomesList=CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUPERVISIONOUTCOME,true);
        		        for (CodeResponseEvent event : activeSupervisionOutcomesList) {
        		            if (tjjdindicator.equalsIgnoreCase("SC")) {
        		                if (event.getCode().equals("D") || event.getCode().equals("S") || event.getCode().equals("X")) {
        		                    filteredList.add(event);
        		                }
        		            } else {
        		                if (event.getCode().equals("D") || event.getCode().equals("S")) {
        		                    filteredList.add(event);
        		                }
        		            }
        		        }

        		        if (!filteredList.isEmpty()) {
        		            myClosingForm.setActiveSupervisionOutcomesList(filteredList);
        		            if (!tjjdindicator.equalsIgnoreCase("SC")) {
        		                myClosingForm.setSkipSubOutCome(true);
        		            }
        		        }

        		        if (tjjdindicator.equalsIgnoreCase("SC")) {
        		            List<JuvenileCodeTableChildCodesResponseEvent> temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
        		            List<JuvenileCodeTableChildCodesResponseEvent> temp2 = new ArrayList<>();
        		            List<JuvenileCodeTableChildCodesResponseEvent> temp3 = new ArrayList<>();

        		            for (JuvenileCodeTableChildCodesResponseEvent joscre : temp1) {
        		                if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(joscre.getParentId()) &&
        		                    (joscre.getCode().equalsIgnoreCase("DAF") || joscre.getCode().equalsIgnoreCase("UCJ") ||
        		                     joscre.getCode().equalsIgnoreCase("PHO") || joscre.getCode().equalsIgnoreCase("ODP") ||
        		                     joscre.getCode().equalsIgnoreCase("REF"))) {
        		                    temp3.add(joscre);
        		                }
        		            }

        		            myClosingForm.setOptionalSupervisionOutcomeDescList(temp2);
        		            myClosingForm.setRequiredSupervisionOutcomeDescList(temp3);
        		        }
        		        
        		        isNewSubOutComeSkipped = false;
        		    }
        		}
        	       
            	    if (isNewSubOutComeSkipped)
            	    {
            		List<CodeResponseEvent> activeSupervisionOutcomesList=CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUPERVISIONOUTCOME,true);
            		myClosingForm.setActiveSupervisionOutcomesList(activeSupervisionOutcomesList);
            		
            		List<JuvenileCodeTableChildCodesResponseEvent> temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
            		List<JuvenileCodeTableChildCodesResponseEvent> temp2 = new ArrayList<>();
            		List<JuvenileCodeTableChildCodesResponseEvent> temp3 = new ArrayList<>();
            
            		for (JuvenileCodeTableChildCodesResponseEvent joscre : temp1)
            		{
            		    if (UIConstants.OUTCOME_COMPLETE.equalsIgnoreCase(joscre.getParentId())
            			    && !joscre.getCode().equalsIgnoreCase("SET"))
            		    {
            			temp2.add(joscre);
            		    }
            		    if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(joscre.getParentId())
            			    && (!joscre.getCode().equalsIgnoreCase("DAF") && !joscre.getCode().equalsIgnoreCase("UNS") && !joscre.getCode().equalsIgnoreCase("UCJ") &&
       		                     !joscre.getCode().equalsIgnoreCase("PHO") && !joscre.getCode().equalsIgnoreCase("ODP") &&
       		                     !joscre.getCode().equalsIgnoreCase("REF")))
            		    {
            			temp3.add(joscre);
            		    }
            		}
            
            		myClosingForm.setOptionalSupervisionOutcomeDescList(temp2);
            		myClosingForm.setRequiredSupervisionOutcomeDescList(temp3);
            	    }
        	       

        	    if( splcategoryId!= null
			    && (splcategoryId.equalsIgnoreCase("CT")||splcategoryId.equalsIgnoreCase("SC"))
			    && probationFlag&&myClosingForm.getActivereferralList().size()>0
			    && !"PROB".equalsIgnoreCase(supTJJDTypeId) )//check flag too
			{
        			myClosingForm.setAction("addproDates");
        		    	    return aMapping.findForward(UIConstants.PROBATIONDATESSUCCESS);			    
			}
		    	else
		    	    return aMapping.findForward(UIConstants.SUCCESS);
		}

		return aMapping.findForward(UIConstants.SUCCESS_HOME);
	}

	/*
	 * Get the max severity level record for each Referral
	 * @param referralList
	 * @param juvenileNum
	 */
	private void getMaxSeverityOffense(Collection<CasefileClosingForm.Refferal> referralList,
			String juvenileNum) {
		// get petition with max severity level for each referral
		if (referralList != null) {
			for (CasefileClosingForm.Refferal referral : referralList) {
				// get petitions
				GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent) EventFactory
						.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);

				pet.setJuvenileNum(juvenileNum);
				pet.setReferralNum(referral.getReferralNumber());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(pet);

				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				Map map = MessageUtil.groupByTopic(response);

				Collection<JJSChargeResponseEvent> petitions = (Collection) map.get(PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC);
				int count = 0;
				if (petitions != null) {
					HashMap petitionsMap = new HashMap();
					Iterator<JJSChargeResponseEvent> petIter = petitions.iterator();
					while (petIter.hasNext()) {
						JJSChargeResponseEvent chgResp = petIter.next();
						if (count == 0 && !petIter.hasNext()) { 
							// if this is the while's first iteration and
							// there is *not* another entry in the HashMap
							String referralNum = referral.getReferralNumber();
							if (notNullNotEmptyString(chgResp.getLevelDegree())) {
								referral.setReferralNumber(referralNum + " - " + chgResp.getLevelDegree());
							}
							break;
						}

						if (petitionsMap == null || petitionsMap.isEmpty() || !petitionsMap.containsKey(chgResp.getLevelDegree())) {
							petitionsMap.put(chgResp.getLevelDegree(), chgResp);
						} else {
							JJSChargeResponseEvent tempResp = (JJSChargeResponseEvent) petitionsMap.get(chgResp.getLevelDegree());
							if (chgResp.getPetitionDate().compareTo(tempResp.getPetitionDate()) > 0) {
								petitionsMap.remove(tempResp.getLevelDegree());
								petitionsMap.put(chgResp.getLevelDegree(), chgResp);
							}
						}
						++count;
					} // while

					if (count != 0) { 
						// we're in here because level degree was never
						// appended to the referral number in the previous loop
						String ref = getMaxSeverity(petitionsMap, referral.getReferralNumber());
						referral.setReferralNumber(ref);
					}
				} else { // else petitions collection is null - get the offenses
					count = 0;
					Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses(juvenileNum, referral.getReferralNumber());
					HashMap offenseMap = new HashMap();
					if (offenses != null) {
						Iterator<JJSOffenseResponseEvent> offIter = offenses.iterator();
						while (offIter.hasNext()) {
							JJSOffenseResponseEvent offResp = offIter.next();
							if (count == 0 && !offIter.hasNext()) { 
								// if this is the while's first iteration and
								// there is *not* another entry in the offenses
								// Collection
								String referralNum = referral.getReferralNumber();
								referral.setReferralNumber(referralNum + " - " + offResp.getCatagory());
								break;
							}

							if (offenseMap == null || offenseMap.isEmpty() || !offenseMap.containsKey(offResp.getCatagory())) {
								offenseMap.put(offResp.getCatagory(), offResp);
							} else {
								JJSOffenseResponseEvent tempResp = (JJSOffenseResponseEvent) offenseMap.get(offResp.getCatagory());
								if (offResp.getOffenseDate().compareTo(tempResp.getOffenseDate()) > 0) {
									offenseMap.remove(tempResp.getCatagory());
									offenseMap.put(offResp.getCatagory(),offResp);
								}
							}
							++count;
						} // while
					}

					if (count != 0) { 
						// we're in here because level degree was never
						// appended to the referral number in the previous loop
						String ref = getMaxSeverity(offenseMap, referral.getReferralNumber());
						referral.setReferralNumber(ref);
					}
				}

				checkReferralNumber(referral);
			} // for

			if (referralList.size() > 1) {
				Collections.sort((List) referralList);
			}
		}
	}

	/*
	 * Get the Risk Assessments
	 * @param juvenileNum
	 * @param casefileId
	 */
	private String getRiskAssessments(String juvenileNum, String casefileId) {
		
		GetRiskAssessmentsByCasefileEvent risk = (GetRiskAssessmentsByCasefileEvent)EventFactory.
									getInstance( JuvenileRiskAnalysisControllerServiceNames.GETRISKASSESSMENTSBYCASEFILE );
		risk.setCasefileId( casefileId );
		CompositeResponse resp = MessageUtil.postRequest(risk);
		Collection riskAssessments = MessageUtil.compositeToCollection(resp, RiskAssessmentListResponseEvent.class);
		for(Iterator iter=riskAssessments.iterator();iter.hasNext();)
		{
			RiskAssessmentListResponseEvent riskAssessment= (RiskAssessmentListResponseEvent)iter.next();
			if(riskAssessment!=null && !riskAssessment.isCompleted() &&(riskAssessment.getAssessmentTypeDesc()!=null && riskAssessment.getAssessmentTypeDesc().equals(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE)
					||riskAssessment.getAssessmentTypeDesc()!=null && riskAssessment.getAssessmentTypeDesc().equals(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE)))
				return RiskAnalysisConstants.RISK_TYPE_NON_COURT_REFERRAL_USE_NAME;
		}
		return "";
	}
	/*
	 * @param offenseMap
	 * @param referralNum
	 * @return
	 */
	public String getMaxSeverity(HashMap offenseMap, String referralNum) {
		String severity = UIConstants.EMPTY_STRING;

		if (offenseMap != null && offenseMap.size() != 0) {
			if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CF) != null) {
				severity = referralNum + " - CF";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F1) != null) {
				severity = referralNum + " - F1";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F2) != null) {
				severity = referralNum + " - F2";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F3) != null) {
				severity = referralNum + " - F3";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_JF) != null) {
				severity = referralNum + " - JF";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MA) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MB) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MC) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CO) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_SO) != null) {
				severity = referralNum + " - FC";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_AC) != null) {
				severity = referralNum + " - AC";
			}
		}

		return severity;
	}

	/*
	 * given a referral number, ensure it's well-formed
	 */
	private void checkReferralNumber(CasefileClosingForm.Refferal referral) {
		String str = referral.getReferralNumber();
		int firstIndex = str.indexOf("-"), lastIndex = str.lastIndexOf("-");

		if (firstIndex != (-1)) { // found a first occurrence
			if (lastIndex != (-1) && (firstIndex != lastIndex)) { 
				// last occurrence found and both char offsets differ,
				// which means the number looks like, "1010 - JF - JF"
				String newNum = str.substring(0, (lastIndex - 1));
				referral.setReferralNumber(newNum);
			}
		}
	}
	
	private void getFinalDisposition(Collection<CasefileClosingForm.Refferal> referrals,
			String juvenileNum) {
		
		if (referrals != null) {
			for (CasefileClosingForm.Refferal referral : referrals) {
		
		      GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory
		      .getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);

		      if (referral.getFinalDisposition() != null && !referral.getFinalDisposition().equals("")) {
		    	  courtDisp.setCode(referral.getFinalDisposition());
		    	  CompositeResponse resp = postRequestEvent(courtDisp);
               
		    	  int count=0;
		    	  Collection juvDisp = MessageUtil.compositeToCollection(resp, JuvenileDispositionCodeResponseEvent.class  );
		    	  if (juvDisp != null){
		    		  Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
		    		  while (juvCodeIter.hasNext()) {
		    			  JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
		    			  String referralNum = referral.getReferralNumber();
		    			  String dispDesc = dispResp.getShortDesc();
		    			  referral.setReferralNumber(referralNum + " - " + dispDesc);            			   
            			  ++count;
		    		  }
            	   }
               }
			}
		}
	}
	

	/*
	 * Get the case file exceptions
	 * @param aSupervisionNumber
	 * @return
	 */
	public Collection getCasefileException(String aSupervisionNumber) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		GetCasefileClosingExceptionsEvent casefileClosingExceptionsEvent = new GetCasefileClosingExceptionsEvent();
		casefileClosingExceptionsEvent.setSupervisionNumber(aSupervisionNumber);
		dispatch.postEvent(casefileClosingExceptionsEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		if (dataMap != null) {
			Collection exceptions = (Collection) dataMap.get(PDJuvenileCaseConstants.NO_CASEFILE_EXCEPTION_TOPIC);
			return exceptions;
		}
		return null;
	}
	public ActionForward addprobDates( ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse )
        {
	    
	    	CasefileClosingForm form = (CasefileClosingForm)aForm ;
	    	String selectedReferrals = (String) aRequest.getParameter("selectedReferrals");
	    	String refnum="";
	    	List<String>  refNums = Arrays.asList(selectedReferrals.split(","));
	    	for (int i = 0; i < refNums.size(); i++)
		{   
        	    	refnum = refNums.get(i);
        	    	form.setRefNo(refnum);
        	    	JuvenileReferralProbationDatesResponseEvent prob = this.createreferralprobDates( form) ;
                        form.getNewDates().add( prob ) ;
		}
	    	//form.setCartRefnums(selectedReferrals);
	    	Collection<JuvenileReferralProbationDatesResponseEvent> shoppingCart= form.getNewDates();
        	/*List<String>  refNums=new ArrayList<String>();*/
        	String cartNums="";
        	if( shoppingCart != null  &&  !shoppingCart.isEmpty() )
		{
			Iterator<JuvenileReferralProbationDatesResponseEvent> iter = shoppingCart.iterator();
			while( iter.hasNext() )
			{
			    JuvenileReferralProbationDatesResponseEvent cart = iter.next();
			    if(cart.getReferralNumber()!=null && !cart.getReferralNumber().isEmpty())
				cartNums=cartNums+cart.getReferralNumber()+',';
				//refNums.add(cart.getReferralNumber());
			}
		}
        	form.setCartRefnums(cartNums);                
                form.setProbationStartDate(null);
                form.setProbationEndDate(null);
                form.setRefCloseDate(null);
                form.setRefNo(null);
                
                return aMapping.findForward( "addprobDates" ) ;
        }
	public ActionForward removeDates( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	//ActionForward forward = aMapping.findForward("removePlacements");

	    CasefileClosingForm form = (CasefileClosingForm) aForm;
        	if(	  form.getSelectedValue()!= null && 
        			!(form.getSelectedValue().equals("")) && 
        			  form.getNewDates()!= null )
        	{
        		int offset = Integer.parseInt(form.getSelectedValue() ) ;
        		if( form.getNewDates().size() > offset )
        		{
        			((ArrayList)form.getNewDates()).remove( offset );							
        		}
        	}
        	Collection<JuvenileReferralProbationDatesResponseEvent> shoppingCart= form.getNewDates();
        	/*List<String>  refNums=new ArrayList<String>();*/
        	String refNums="";
        	if( shoppingCart != null  &&  !shoppingCart.isEmpty() )
		{
			Iterator<JuvenileReferralProbationDatesResponseEvent> iter = shoppingCart.iterator();
			while( iter.hasNext() )
			{
			    JuvenileReferralProbationDatesResponseEvent cart = iter.next();
			    if(cart.getReferralNumber()!=null && !cart.getReferralNumber().isEmpty())
				refNums=refNums+cart.getReferralNumber()+',';
				//refNums.add(cart.getReferralNumber());
			}
		}
        	form.setCartRefnums(refNums);
        	return aMapping.findForward( "removeDates" ) ;
	}
	private JuvenileReferralProbationDatesResponseEvent createreferralprobDates( CasefileClosingForm form )
	{
	    //need to create a response event just for placements
	    JuvenileReferralProbationDatesResponseEvent prob = new JuvenileReferralProbationDatesResponseEvent( ) ;	    
	    //prob.setReferralNumber(form.getRefNo());
	    prob.setReferralNumber(form.getRefNo());
	    prob.setProbationStartDate(form.getProbationStartDate());
	    prob.setProbationEndDate(form.getProbationEndDate());
	    prob.setRefCloseDate(form.getRefCloseDate());
	    return prob ;
	}
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse )
        {
	    
	    	CasefileClosingForm form = (CasefileClosingForm)aForm ;
	    	Collection newDates=form.getNewDates();
		
		if( newDates != null && !newDates.isEmpty( ) )
		{
			
			Iterator i = newDates.iterator( ) ;

			while( i.hasNext() )
			{
			    JuvenileReferralProbationDatesResponseEvent juvProbresp = (JuvenileReferralProbationDatesResponseEvent)i.next( );
			    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			    UpdateJuvenileReferralProbDatesEvent updateEvent = (UpdateJuvenileReferralProbDatesEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.UPDATEJUVENILEREFERRALPROBDATES);
			    //updateEvent = setUpdateDetails(updateEvent, form);
			    updateEvent.setJuvenileNum(form.getJuvenileNum());
			    updateEvent.setReferralNum(juvProbresp.getReferralNumber());
			    updateEvent.setProbationStartDate(DateUtil.stringToDate(juvProbresp.getProbationStartDate(), DateUtil.DATE_FMT_1));
			    updateEvent.setProbationEndDate(DateUtil.stringToDate(juvProbresp.getProbationEndDate(), DateUtil.DATE_FMT_1));
			    updateEvent.setClosedDate(DateUtil.stringToDate(juvProbresp.getRefCloseDate(), DateUtil.DATE_FMT_1));
			    updateEvent.setCasefileId( form.getSupervisionNumber() );
			    dispatch.postEvent(updateEvent);
			    CompositeResponse response = (CompositeResponse) dispatch.getReply();
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
			    if (error != null)
			    {
				ActionErrors errors = new ActionErrors();
				if (error.getErrorLogId() != null && error.getErrorLogId().equalsIgnoreCase("notificationError"))
				{
					//do nothing
				}
				else
				{
				    saveErrors(aRequest, errors);
				    return aMapping.findForward("probdatesFailure");
				}
				    // return aMapping.findForward(UIConstants.FAILURE);// as per Cassandra any failure in sending notifications or MAYSI should still continue with assignment creation / update
			    }
			    
			}
		}
		//save flag as complete modify this to JCCASEFILE
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    SaveCasefileClosingProbDatesFlagEvent casefileClosingSaveEvent = (SaveCasefileClosingProbDatesFlagEvent) EventFactory
				.getInstance(JuvenileCasefileControllerServiceNames.SAVECASEFILECLOSINGPROBDATESFLAG);
		    casefileClosingSaveEvent.setSupervisionNumber(form.getSupervisionNumber());
		    dispatch.postEvent(casefileClosingSaveEvent);
		    CompositeResponse resp = (CompositeResponse) dispatch.getReply();
			
                return aMapping.findForward( "success" ) ;
        }
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}
	public ActionForward Refresh(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse) {
	return aMapping.findForward("refresh");
}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward exceptionsSummary(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		
		if (casefileForm == null) {
			String casefileID = aRequest.getParameter("casefileId");
			casefileForm = new JuvenileCasefileForm();
			UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(casefileForm,casefileID);
		}

		Collection<CasefileExceptionResponseEvent> myExceptions = getCasefileException(casefileForm.getSupervisionNum());

		ArrayList<CasefileException > allExceptions = new ArrayList<CasefileException >();
		if (myExceptions != null) {
			for (CasefileExceptionResponseEvent exceptionEvent : myExceptions) {
				CasefileException caseFileException = UIJuvenileCasefileClosingHelper.getExceptionFROMExceptionRespEvt(exceptionEvent);
				allExceptions.add(caseFileException);
			}
		}
		UIJuvenileCasefileClosingHelper.getProgramReferralExceptions(myClosingForm, allExceptions);
		myClosingForm.setCasefileExceptions(allExceptions);
		myClosingForm.setAction(UIConstants.SUMMARY);

		return aMapping.findForward(PDJuvenileCaseConstants.EXCEPTIONSSUMMARY);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward overrideClosingExeceptions(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		if (casefileForm == null) {
			String casefileID = aRequest.getParameter("casefileId");
			casefileForm = new JuvenileCasefileForm();
			UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(casefileForm,casefileID);
		}

		ArrayList<CasefileException > allExceptions = new ArrayList<CasefileException >();
		Collection<CasefileExceptionResponseEvent> myExceptions = getCasefileException(casefileForm.getSupervisionNum());
		if (myExceptions != null) {
			for (CasefileExceptionResponseEvent casefileExceptionResponseEvent : myExceptions) {
				CasefileException caseFileException = UIJuvenileCasefileClosingHelper
						.getExceptionFROMExceptionRespEvt(casefileExceptionResponseEvent);
				allExceptions.add(caseFileException);
			}
		}

		UIJuvenileCasefileClosingHelper.getProgramReferralExceptions(myClosingForm, allExceptions);
		myClosingForm.setCasefileExceptions(allExceptions);

		UpdateJuvenileCasefileEvent updateEvent = (UpdateJuvenileCasefileEvent) EventFactory
				.getInstance(JuvenileCasefileControllerServiceNames.UPDATEJUVENILECASEFILE);
		updateEvent.setSupervisionNumber(casefileForm.getSupervisionNum());
		updateEvent.setSupervisionEndDate( casefileForm.getExpectedSupervisionEndDate() );
		if ("MAYSI".equals( myClosingForm.getOverrideSelection()  )) {
		    updateEvent.setMAYSINeeded(false);
		} 
		
		if ("PACT".equals( myClosingForm.getOverrideSelection()) ){
		    updateEvent.setReferralRiskNeeded(false);
		} 
		
		
		//commented out for US 14459
		//updateEvent.setInterviewRiskNeeded(false);
		MessageUtil.postRequest(updateEvent);

		CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);

		reqEvent.setSupervisionNumber(casefileForm.getSupervisionNum());
		reqEvent.setActivityDate(DateUtil.getCurrentDate());
		reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_CATEGORY_ADMINISTRATIVE);
		reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_CASE_MANAGEMENT);
		reqEvent.setActivityCodeId(ActivityConstants.CASEFILE_CLOSING_OVERRIDE_EXCEPTIONS);
		reqEvent.setComments(myClosingForm.getExceptionOverrideComments());
		MessageUtil.postRequest(reqEvent);

		myClosingForm.setAction(UIConstants.CONFIRM);

		return aMapping.findForward(PDJuvenileCaseConstants.EXCEPTIONSSUMMARY);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward continueClosing(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		if (casefileForm == null) {
			String casefileID = aRequest.getParameter("casefileId");
			casefileForm = new JuvenileCasefileForm();
			UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(casefileForm,casefileID);
		}
		
		
        // User-story 11079 starts
        //Get all the offenses associated to the casefile.
		checkForTransferredOffenses(myClosingForm);
 	   // User-story 11079 ends

		//do the transfer here,added for 11382 #55211
		String probationOfficerLogonId = null;
		String juvenileName=null;
		if(myClosingForm.isCasefileAssociated()){
			
			String transferCasefileId = myClosingForm.getTransferCasefileId();
			if(transferCasefileId!=null){
				//update detention Record.
				JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(myClosingForm.getJuvenileNum());
				if(facility!=null){
					facility.setCurrentSupervisionNum(transferCasefileId);
				}
				IHome home= new Home();
				home.bind(facility);
			}
			
			myClosingForm.setClosingInfoAvail(true);
			
			GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
			getCasefileEvent.setSupervisionNumber( transferCasefileId ) ;				
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( getCasefileEvent ) ;
			CompositeResponse compResponse = (CompositeResponse)dispatch.getReply( ) ;		
			JuvenileCasefileResponseEvent casefile = 
						(JuvenileCasefileResponseEvent)MessageUtil.filterComposite( compResponse, JuvenileCasefileResponseEvent.class );
			
			if(casefile != null && casefile.getSupervisionNum() != null){
				probationOfficerLogonId = casefile.getProbationOfficerLogonId();
				juvenileName= casefile.getJuvenileLastName() +", "+casefile.getJuvenileFirstName();
				//send notification.
				 JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
				 respEvt.setSubject("Due to casefile closure, facility admit records have been assigned to new supervision number.");
				 respEvt.setIdentity(probationOfficerLogonId);
				 respEvt.setJuvenileNum(casefileForm.getJuvenileNum());
				 String notifMessage = "Facility admit records exist for juvenile "+juvenileName+", Juvenile#: "+casefileForm.getJuvenileNum()+". Due to casefile closure, facility admit records have been assigned to "+transferCasefileId+".";
					 respEvt.setNotificationMessage(notifMessage);
						    	
				 CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
				 notificationEvent.setNotificationTopic("JC.FACILITY.CASEFILE.CLOSURE.NOTIFICATION");
				 notificationEvent.setSubject(respEvt.getSubject());
				 notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
				 notificationEvent.addContentBean(respEvt);
				 CompositeResponse compResp = MessageUtil.postRequest(notificationEvent);
			     MessageUtil.processReturnException( compResp ) ;
			     myClosingForm.setCasefileAssociated(false);
			}
		}
		
		String caseStatus = casefileForm.getCaseStatusId();
		if (caseStatus.equals(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)) {
		    //add the prob section bug 128392
		    String splcategoryId=null;
		    String supTJJDTypeId=null;
		    boolean probationFlag=false;
		    
		    GetJuvenileCasefileEvent getCasefileEvt = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);

		    	getCasefileEvt.setSupervisionNumber(myClosingForm.getSupervisionNumber());
		    	

			CompositeResponse res = MessageUtil.postRequest(getCasefileEvt);
			JuvenileCasefileResponseEvent casef = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(res, JuvenileCasefileResponseEvent.class);

			// check  referraldatesstatus==null
			if (casef != null)
			{
			    if(casef.getSupervisionTypeId() != null)
				{
				    GetSupervisionTypeTJJDMapEvent request = new GetSupervisionTypeTJJDMapEvent();
				    request.setSupervisionTypeId(casef.getSupervisionTypeId());
				    
				    CompositeResponse replyEvent = MessageUtil.postRequest(request);
				    SupervisionTypeMapResponseEvent tjjdMap = (SupervisionTypeMapResponseEvent) MessageUtil.filterComposite(replyEvent,SupervisionTypeMapResponseEvent.class);
				    
				    if( tjjdMap != null ){
					
					splcategoryId= tjjdMap.getSplCategoryId();
					supTJJDTypeId= tjjdMap.getSupTJJDTypeId();
				    }
				
				}
			    if(casef.getProbationFlag()==null)//checking if the flag is already set means the dates are already given
			    	probationFlag=true;
			}
			
		    if( splcategoryId!= null
			    &&(splcategoryId.equalsIgnoreCase("CT")||splcategoryId.equalsIgnoreCase("SC"))
			    &&probationFlag
			    &&myClosingForm.getActivereferralList().size()>0
			    &&!"PROB".equalsIgnoreCase( supTJJDTypeId ) )//check flag too
			{
        			myClosingForm.setAction("addproDates");
        		    	    return aMapping.findForward(UIConstants.PROBATIONDATESSUCCESS);			    
			}
		    	else
		    	    return aMapping.findForward(UIConstants.SUCCESS);		    
			
		}

		return aMapping.findForward(UIConstants.SUCCESS_HOME);
	}

	/*
	 * 
	 * @param casefileForm
	 * 
	 * @return
	 */
	private boolean cleActivityExist(JuvenileCasefileForm casefileForm) {
		if ((casefileForm.getSupervisionNum() != null) && (!"".equals(casefileForm.getSupervisionNum()))){
			List activityResults = UIJuvenileCaseworkHelper.fetchCleActivities(casefileForm.getSupervisionNum());

			if (activityResults != null && (activityResults.size() > 0)) {
				return (true);
			}
		}
		return false;
	}

	/*
	 * given a String, return true if it's not null and not empty
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString(String str) {
		return (str != null && (str.trim().length() > 0));
	}

	/*
	 * given a Collection, return true if it's null or empty
	 * @param coll
	 * @return
	 */
	private boolean nullOrEmptyCollection(Collection coll) {
		return (coll == null || (coll.size() < 1));
	}

	private List getCurrentGuardianAddress(String memberNumber)
	{
		List addr = new ArrayList();
		GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent)
				EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
		memberAddressEvent.setMemberNum(memberNumber);

		CompositeResponse response = MessageUtil.postRequest(memberAddressEvent);
		Collection addressResponses = MessageUtil.compositeToCollection(response,AddressResponseEvent.class);
		if (addressResponses.size() > 0)
		{
// retrieve latest address based on create date -- same as Juvenile Briefing Details
			Collections.sort((List) addressResponses);
			AddressResponseEvent arEvent = (AddressResponseEvent)((List) addressResponses).get(0);
			addr.add(arEvent);
		}
		
		return addr;
	}	

	private void loadConstellationInfo(JuvenileFamilyForm myFamForm)
	{
		myFamForm.clear();
		myFamForm.setCurrentActiveConstellation(new JuvenileFamilyForm.Constellation());
		myFamForm.setAction("");

//		if( ! myFamForm.isListsSet() )
//		{
//			UIJuvenileLoadCodeTables.getInstance().setJuvenileFamilyForm(myFamForm);
//		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Send PD Request Event
		GetFamilyConstellationsEvent event = (GetFamilyConstellationsEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
		event.setJuvenileNum(myFamForm.getJuvenileNumber());
		dispatch.postEvent(event);

		// Get PD Response Event	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		// Perform Error handling
		MessageUtil.processReturnException(response); 
		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{
			Collection families  = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
			UIJuvenileHelper.setJuvFamilyFormConstListFROMConstListRespEvt( myFamForm, families);

			families = myFamForm.getConstellationList();
			if(families != null && families.size() > 0) 
			{
				Iterator<JuvenileFamilyForm.ConstellationList> myIter = families.iterator();
				while( myIter.hasNext() )
				{
					JuvenileFamilyForm.ConstellationList myFamily = myIter.next();
					if( myFamily.isActive() )
					{
						JuvenileFamilyForm.Constellation newFamily = new JuvenileFamilyForm.Constellation();

						myFamForm.setHasActiveConstellation(true);
						newFamily.setFamilyNumber(myFamily.getFamilyNumber());
						newFamily.setActive(myFamily.isActive());
						myFamForm.setCurrentConstellation(newFamily);
						myFamForm.setCurrentActiveConstellation(newFamily);
						Collection currentFamMembers = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
						UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,currentFamMembers);
						break ;
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Added for 11079
	 * @param myClosingForm
	 */
	private void checkForTransferredOffenses(CasefileClosingForm myClosingForm){
		  List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseRespList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(myClosingForm.getJuvenileNum());
	        boolean isTransferredOffense = false;
	        int offenseCount=0;
	        int transferredOffenseCount =0;
	        
	        IDispatch dispatch =EventManager.getSharedInstance(EventManager.REQUEST);
	    	GetCasefileOffensesEvent event =(GetCasefileOffensesEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETCASEFILEOFFENSES);
	    	event.setCasefileId(myClosingForm.getSupervisionNumber());
	        event.setJuvenileNum(myClosingForm.getJuvenileNum());
	 		dispatch.postEvent(event);
	 	    List<JJSOffenseResponseEvent> offenseResps = MessageUtil.postRequestListFilter(event, JJSOffenseResponseEvent.class);
	 	     	
	 	   Iterator<JJSOffenseResponseEvent> OffenseIter = offenseResps.iterator();
	 	   
	 	    while(OffenseIter.hasNext()){
	 	    	JJSOffenseResponseEvent offense =  OffenseIter.next();
	 	    	if(offense!=null){
	 	    		if(offense.getSeveritySubtype()!=null && offense.getSeveritySubtype().equals("T")){
	 	    			 offenseCount++;
	 	    			 if(transferredOffenseRespList!=null){
	 	    				Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseRespList.iterator();
	 	    				while (transferredOffenseIter.hasNext())
	 						{
	 							JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
	 							if(transferredOffense!=null){
	 								if(transferredOffense.getReferralNum().equals(offense.getReferralNum())){
	 									transferredOffenseCount++;
	 								}
	 							}
	 						}
	 	    			 }
	 	    		}
	 	    	}
	 	    }
	 	    // all referral nos with transferred offenses must be added to the transferred offenses.
	 	    if(offenseCount!=0 && offenseCount!=transferredOffenseCount){
	 	    	isTransferredOffense=false;
				myClosingForm.setHasTransferredOffense(isTransferredOffense);
	 	    }else{ // not a transferred offense.
	 	    	isTransferredOffense=true;
	 	    	myClosingForm.setHasTransferredOffense(isTransferredOffense);
	 	    }
	}
	
	
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
   {
		ActionErrors errors = new ActionErrors();
		if(aActionErrors!=null && aActionErrors.size()>0){
			Iterator i=aActionErrors.iterator();
			while(i.hasNext()){
				ActionMessage error=(ActionMessage)i.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE,error);
			}
		   saveErrors(aRequest, errors);
		}
   }
	protected ActionForm getSessionForm(ActionMapping aMapping, HttpServletRequest aRequest, String aFormName, boolean aCreateIfNeeded)  throws GeneralFeedbackMessageException{
		Object myObj =aMapping.getModuleConfig().findFormBeanConfig(aFormName);
		if(myObj!=null && myObj instanceof FormBeanConfig){  // couldn't find the object
			FormBeanConfig myFormConfig=(FormBeanConfig)myObj;
			String myFormName=myFormConfig.getName();
			String myFormClassName=myFormConfig.getType();
			try{
				Class myClass=Class.forName(myFormClassName);
				return getOurForm(aRequest,myFormName,myClass,aCreateIfNeeded);
			}
			catch(Exception e){
				throw new GeneralFeedbackMessageException("The requested  form name: " + aFormName + " could not be found while validating against the struts-config file. " + e.getMessage());
			}

		}
		throw new GeneralFeedbackMessageException("The requested  form name: " + aFormName + " could not be found while validating against the struts-config file. ");
	}
	
	private ActionForm getOurForm(HttpServletRequest aRequest, String aFormName, Class aClass, boolean isCreate) throws Exception
	{
		ActionForm myForm = getOurForm(aFormName,aRequest);
		if (myForm == null)
		{
			HttpSession session = aRequest.getSession();
			myForm = (ActionForm)aClass.newInstance();
			session.setAttribute(aFormName, myForm);
		}
		return myForm;
	}
	
	private ActionForm getOurForm(String aFormName, HttpServletRequest aRequest)
	{
		HttpSession session = aRequest.getSession();
		Object aObj= session.getAttribute(aFormName);
		if(aObj!=null)
			return (ActionForm)aObj;
		return null;
	}
	
	/**
	 * Utility method to help post method to the PD. Takes in a completed set event, posts it to the PD and runs it through MessageUtil.processReturnException
	 * before returning the composite response to the user
	 * @param event -- the RequestEvent to post to the PD
	 * @throws -- throws null pointer exception if incoming event is null
	 * @return -- the Composite Resposnse
	 */
	protected CompositeResponse postRequestEvent(RequestEvent event) {
		return MessageUtil.postRequest(event);
	}

	/**
	 * Utility method to help post method to the PD. Takes in a completed set event, posts it to the PD and runs it through MessageUtil.processReturnException
	 * and filters for the response event class.
	 * @param event -- the RequestEvent to post to the PD
	 * @param classToFilter -- the class type to filter the response on
	 * @throws -- throws null pointer exception if incoming event is null
	 * @return -- the Single ResposnseEvent, returns null if not found
	 */
	protected IEvent postRequestEvent(RequestEvent event,Class classToFilter) {
		return MessageUtil.postRequest(event, classToFilter);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map buttonMap = new HashMap();
		buttonMap.put("button.link", "checkCasefileClosingStatus");
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		buttonMap.put("button.back", UIConstants.BACK);
		buttonMap.put("button.submit", PDJuvenileCaseConstants.EXCEPTIONSSUMMARY);
		buttonMap.put("button.finish", PDJuvenileCaseConstants.OVERRIDECLOSINGEXCEPTIONS);
		buttonMap.put("button.continueToClosing", PDJuvenileCaseConstants.CONTINUECLOSING);
		buttonMap.put("button.update", "addprobDates");
		buttonMap.put( "button.remove", "removeDates" ) ;
		buttonMap.put( "button.next", "next" ) ;
		buttonMap.put( "button.refresh", "Refresh" ) ;
		return buttonMap;
	}
}