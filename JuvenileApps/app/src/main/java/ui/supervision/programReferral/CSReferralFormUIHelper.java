/*
 * Created on Jul 2, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.GetRecentDisposedCasesEvent;
import messaging.administercaseload.reply.DisposedCaseResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administerlocation.GetLocationByLocationIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerprogramreferrals.to.CSCDDwiReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.CSCDEduReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.CSCDTaipReferralFormDataTO;
import messaging.administerprogramreferrals.to.NewStartReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.PolygraphReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.ReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.TAIPReferralFormReportDataTO;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.csserviceprovider.GetLocationByCSServiceProviderEvent;
import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseEvent;
import messaging.supervisionorder.GetCurrentSupervisionOrderStartEndDatesEvent;
import messaging.supervisionorder.GetSupervisionOrdersEvent;
import messaging.supervisionorder.reply.SupervisionOrderStartEndDatesResponseEvent;
import messaging.user.GetUserProfilesEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.CSReferralFormConstants;
import naming.CaseloadControllerServiceNames;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UserControllerServiceNames;
import ui.common.SimpleCodeTableHelper;
import ui.common.StringUtil;
import ui.common.PhoneNumber;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;
import ui.supervision.administerserviceprovider.CSC.ServiceProviderLightContactBean;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.supervisee.form.SuperviseeForm;

/**
 * @author cc_bjangay
 *
 */
public class CSReferralFormUIHelper 
{
	/**
	 * 
	 * @param superviseeDataTo
	 * @param superviseeForm
	 * @param caseAssignForm
	 */
	public static void populateSuperviseeDetails(ReferralFormReportDataTO superviseeDataTo, SuperviseeForm superviseeForm, CaseAssignmentForm caseAssignForm)
	{
		superviseeDataTo.setSuperviseeId(superviseeForm.getSuperviseeId());
		superviseeDataTo.setSuperviseeName(superviseeForm.getSuperviseeName());
		if( StringUtils.isNotEmpty( superviseeForm.getSuperviseePhoneNumber()))
		{
			PhoneNumber superviseePhone = new PhoneNumber(superviseeForm.getSuperviseePhoneNumber());
			superviseeDataTo.setSuperviseePhone(superviseePhone);
		}
		if( StringUtils.isNotEmpty( superviseeForm.getEmployePhoneNum()))
		{
			PhoneNumber employePhoneNum = new PhoneNumber(superviseeForm.getEmployePhoneNum());
			superviseeDataTo.setSuperviseeEmployPhone(employePhoneNum);
		}
		superviseeDataTo.setSuperviseeDOB(superviseeForm.getDateOfBirth());
		String court = caseAssignForm.getCourtNumber();
		if ( StringUtils.isNotEmpty(court) ) {
			String trimmedCourt = "";
			for (int i = 0; i < court.length(); i++) {
				String test = court.substring(i,i+1);
				if (test.equals("I") || test.equals("O")) {
					superviseeDataTo.setCourt(court);
					break;
				}else if ( !test.equals("0") ) {
		            trimmedCourt = court.substring(i, court.length());
		            superviseeDataTo.setCourt(trimmedCourt);
		            break;
		    	} 
			}
		}
		superviseeDataTo.setCurrentOffense(caseAssignForm.getOffenseDesc());
		superviseeDataTo.setCause(caseAssignForm.getCaseNum());
		superviseeDataTo.setRace(superviseeForm.getRace());
		superviseeDataTo.setSex(superviseeForm.getSex());
		superviseeDataTo.setDriverLicenseNumber(superviseeForm.getDriverLicenseNumber());
		superviseeDataTo.setSid(superviseeForm.getStateIdNumber());
		if(!StringUtil.isEmpty(superviseeForm.getSsn().toString()))
		{
			superviseeDataTo.setSsn(superviseeForm.getSsn());
		}
		
	}//end of populateSuperviseeDetails()
	
	/**
	 * 
	 * @param superviseeAddressDataTo
	 * @param superviseeForm
	 */
	public static void populateSuperviseeAddress(ReferralFormReportDataTO superviseeAddressDataTo, SuperviseeForm superviseeForm)
	{
		String superviseeAddress = (superviseeForm.getSuperviseeStreetName() == null)?"":superviseeForm.getSuperviseeStreetName();
		if(superviseeAddress != null && !superviseeAddress.equals(""))
   		{
			StringBuffer addressInfo = new StringBuffer();
			addressInfo.append((superviseeForm.getSuperviseeStreetNumber() == null)?"":superviseeForm.getSuperviseeStreetNumber());
			addressInfo.append(" ");
			addressInfo.append((superviseeForm.getSuperviseeStreetName() == null)?"":superviseeForm.getSuperviseeStreetName());
			addressInfo.append(" ");
			addressInfo.append((superviseeForm.getSuperviseeCity() == null)?"":superviseeForm.getSuperviseeCity());
			addressInfo.append(", ");
			addressInfo.append((superviseeForm.getSuperviseeState() == null)?"":superviseeForm.getSuperviseeState());
			addressInfo.append(" ");
			addressInfo.append((superviseeForm.getSuperviseeZipCode() == null)?"":superviseeForm.getSuperviseeZipCode());
			superviseeAddress  = addressInfo.toString().trim();
			superviseeAddressDataTo.setSuperviseeAddress(superviseeAddress);
   		}
	}//end of populateSuperviseeAddress()
	
	/**
	 * 
	 * @param officerDataTo
	 * @param caseAssignForm
	 * @param progRefForm
	 */
	public static void populateOfficerDetails(ReferralFormReportDataTO officerDataTo, CaseAssignmentForm caseAssignForm, CSCProgRefForm progRefForm)
	{
		officerDataTo.setSupervisionStaffName(caseAssignForm.getOfficerNameStr());
		officerDataTo.setSuperviseeProgramUnit(caseAssignForm.getProgramUnitName());
		officerDataTo.setReferralDateAsStr(progRefForm.getReferralDateAsStr());
		GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
		reqEvt.setStaffPositionId(caseAssignForm.getOfficerPositionId());
		reqEvt.setOfficerNameNeeded(true);
		LightCSCDStaffResponseEvent staffPosRespEvt = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(reqEvt, LightCSCDStaffResponseEvent.class);
		//add logic to find userprofile to get positions phone and email
		if(staffPosRespEvt!=null)
		{
			if (staffPosRespEvt.getOfficerLogonId() != null)
			{
				officerDataTo.setPoi(staffPosRespEvt.getProbationOfficerInd());
				officerDataTo.setStaffPositionName(staffPosRespEvt.getStaffPositionName());
				GetUserProfilesEvent userProfilesEvt = (GetUserProfilesEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILES);				
				userProfilesEvt.setLogonId(staffPosRespEvt.getOfficerLogonId());
				if( StringUtils.isNotEmpty(staffPosRespEvt.getSPPhoneNumber()))
				{
					PhoneNumber supervisionStaffPhone = new PhoneNumber(staffPosRespEvt.getSPPhoneNumber());
					officerDataTo.setSupervisionStaffPhone(supervisionStaffPhone);
				}
				Collection profiles = MessageUtil.postRequestListFilter(userProfilesEvt, UserResponseEvent.class);
				if (profiles != null && !profiles.isEmpty())
				{
					Iterator userIter = profiles.iterator();
					while (userIter.hasNext())
					{
						UserResponseEvent urEvent = (UserResponseEvent) userIter.next();
						officerDataTo.setSuperviseeStaffEmail(urEvent.getEmail());
					} 
				}
				String staffLogonId = staffPosRespEvt.getOfficerLogonId();
				populateProgramUnitAddress(officerDataTo, staffLogonId);
			}	
		}
	}//end of populateOfficerDetails()
	
	/**
	 * 
	 * @param officerDataTo
	 * @param staffLogonId
	 */
	public static void populateProgramUnitAddress(ReferralFormReportDataTO officerDataTo, String staffLogonId)
	{
		
		GetCSCDSupervisionStaffEvent getByUserId = new GetCSCDSupervisionStaffEvent();
        getByUserId.setAgencyId("CSC");
        getByUserId.setStaffLogonId(staffLogonId);
        CSCDSupervisionStaffResponseEvent staffEvent = (CSCDSupervisionStaffResponseEvent) 
        						MessageUtil.postRequest(getByUserId, CSCDSupervisionStaffResponseEvent.class);    
        if ( staffEvent != null  ) {
        	
        	if ( StringUtils.isNotEmpty( staffEvent.getLocationId() ) ) {
        		String locationId = staffEvent.getLocationId();
        		GetLocationByLocationIdEvent location = new GetLocationByLocationIdEvent();
        		location.setLocationId(locationId);
        		location.setAgencyId(SecurityUIHelper.getUserAgencyId());
        		location.setStatusId("A");
        		LocationResponseEvent locationResponse = (LocationResponseEvent) MessageUtil.postRequest(location, LocationResponseEvent.class);
        		if ( locationResponse != null ) {
        			StringBuffer programUnitAddress = new StringBuffer();
        			if ( StringUtils.isNotEmpty( locationResponse.getStreetNum() ) ) {
        				programUnitAddress.append(locationResponse.getStreetNum());
        				programUnitAddress.append( " " );
        			}
        			if ( StringUtils.isNotEmpty( locationResponse.getStreetName() ) ) {
        				programUnitAddress.append(locationResponse.getStreetName());
        				programUnitAddress.append( " " );
        			}
        			if ( StringUtils.isNotEmpty( locationResponse.getAptNumber() ) ) {
        				programUnitAddress.append( "Apartment " );
        				programUnitAddress.append(locationResponse.getAptNumber());
        				programUnitAddress.append( " " );
        			}
        			if ( StringUtils.isNotEmpty( locationResponse.getCity() ) ) {
        				programUnitAddress.append(locationResponse.getCity());
        				programUnitAddress.append( ", " );
        			}
        			if ( StringUtils.isNotEmpty( locationResponse.getStateId() ) ) {
        				programUnitAddress.append(locationResponse.getStateId());
        				programUnitAddress.append( " " );
        			}
        			if ( StringUtils.isNotEmpty( locationResponse.getZipCode() ) ) {
        				programUnitAddress.append(locationResponse.getZipCode());
        			}
        			officerDataTo.setProgramUnitAddress(programUnitAddress.toString());
        			if ( StringUtils.isNotEmpty( locationResponse.getLocationId() ) ) {
        				GetLocationByCSServiceProviderEvent locationEvent =
    						new GetLocationByCSServiceProviderEvent();
		    			locationEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());// changed vj
		    			locationEvent.setLocationStatusCode("A");				
		    			List<LocationResponseEvent> location_responses = 
		    					MessageUtil.postRequestListFilter(locationEvent, LocationResponseEvent.class);
		    			
		    			if (location_responses != null)
		    			{
		    				if ( StringUtils.isNotEmpty( locationResponse.getPhoneNumber() ) ) {
		    					IPhoneNumber phoneNumber = new PhoneNumber(locationResponse.getPhoneNumber());
		    					officerDataTo.setProgramUnitPhone( phoneNumber );
		    				}	
		    				if ( StringUtils.isNotEmpty( locationResponse.getLocationFax() ) ) {
		    					IPhoneNumber locationFax = new PhoneNumber(locationResponse.getLocationFax());
		    					officerDataTo.setSupervisionStaffFax( locationFax );
		    					officerDataTo.setProgramUnitFax( locationFax );
		    				}	
		    			}
        			}
        		}
        	}	
        }
	}//end of populateSuperviseeAddress()
	
	/**
	 * 
	 * @param OOCDataTo
	 * @param caseAssignForm
	 */
	public static void populateOOCDetails(ReferralFormReportDataTO OOCDataTo, CaseAssignmentForm caseAssignForm)
	{
		if ( StringUtils.isNotEmpty( caseAssignForm.getCdi() ) && caseAssignForm.getCdi().equals("010") ) {
			GetOutOfCountyCaseEvent request =
				(GetOutOfCountyCaseEvent) EventFactory.getInstance(
					OutOfCountyCaseControllerServiceNames.GETOUTOFCOUNTYCASE);
			// set the criteria from the form
			request.setCaseNum( caseAssignForm.getCaseNum() );
			request.setCourtDivisionId( caseAssignForm.getCdi() );
			// indicate whether the case is being read for reactivation
			request.setReactivate(false);
			List cases = MessageUtil.postRequestListFilter( request, OutOfCountyCaseEvent.class);
			for ( int x =0; x < cases.size(); x++ ){
				OutOfCountyCaseEvent response = (OutOfCountyCaseEvent) cases.get( x );
				OOCDataTo.setOocOriginatingCauseNumber( response.getOriginalCaseNum() );
				if( StringUtils.isNotEmpty(response.getOriginalCountyId() ) ) {
					String countyCode = response.getOriginalCountyId();
					List countyList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.COUNTY);
					Iterator countyIter = countyList.iterator();
					while(countyIter.hasNext())
					{
						CodeResponseEvent county =  (CodeResponseEvent) countyIter.next();
						if ( StringUtils.isNotEmpty( county.getCode() ) && county.getCode().equals( countyCode ) ) {
							
							OOCDataTo.setOocOriginatingCounty( county.getDescription() );
							break;
						}
					}		
				}else if( StringUtils.isNotEmpty( response.getStateId() ) ) {
					String stateCode = response.getStateId();
					List stateList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.STATE_ABBR);
					Iterator stateIter = stateList.iterator();
					while(stateIter.hasNext())
					{
						CodeResponseEvent state =  (CodeResponseEvent) stateIter.next();
						if ( StringUtils.isNotEmpty( state.getCode() ) && state.getCode().equals( stateCode ) ) {
							
							OOCDataTo.setOocOriginatingState( state.getDescription() );
							break;
						}
					}		
				}
			}
		}
	}//end of populateOOCDetails();
	
	/**
	 * 
	 * @param programDataTo
	 * @param progRefForm
	 */
	public static void populateProgramDetails(ReferralFormReportDataTO programDataTo, CSCProgRefForm progRefForm)
	{
		if( StringUtils.isNotEmpty(progRefForm.getTracerNum()))
		{
			programDataTo.setContractTracerNumber(progRefForm.getTracerNum());
		}
		programDataTo.setReferralDateAsStr(progRefForm.getReferralDateAsStr());
		if (progRefForm.getReferralProgramLocBean() != null)
		{
			if( StringUtils.isNotEmpty( progRefForm.getReferralProgramLocBean().getReferralTypeDesc() ) ) {
				programDataTo.setReferralType(progRefForm.getReferralProgramLocBean().getReferralTypeDesc());
			} else if( StringUtils.isNotEmpty( progRefForm.getReferralTypeDesc() ) ) {
				programDataTo.setReferralType(progRefForm.getReferralTypeDesc());
			}
			if( StringUtils.isNotEmpty( progRefForm.getReferralProgramLocBean().getProgramName() ) ) {
				programDataTo.setProgramName(progRefForm.getReferralProgramLocBean().getProgramName() );
			}
			CSCLocationInfoBean locInfo = progRefForm.getReferralProgramLocBean().getProgramLocationBean();      		
    	   	IPhoneNumber locationPhone = locInfo.getLocationPhone();
    	   	if ( locationPhone.getLast4Digit() != "" )
       		{
	            String pgmPhoneNum = "";
	   			StringBuffer phoneInfo = new StringBuffer();
	   			phoneInfo.append((locInfo.getLocationPhone().getAreaCode() == null)?"":locInfo.getLocationPhone().getAreaCode());
	   			phoneInfo.append("-");
	   			phoneInfo.append((locInfo.getLocationPhone().getPrefix() == null)?"":locInfo.getLocationPhone().getPrefix());
	   			phoneInfo.append("-");
	   			phoneInfo.append((locInfo.getLocationPhone().getLast4Digit() == null)?"":locInfo.getLocationPhone().getLast4Digit());
	   			pgmPhoneNum = phoneInfo.toString().trim();
	   			if (pgmPhoneNum.length() < 5 ){
	   				pgmPhoneNum = "";
	   			}else if (StringUtils.isNotEmpty(pgmPhoneNum)){
	   				PhoneNumber programPhoneNum = new PhoneNumber(pgmPhoneNum);
	   				programDataTo.setReferralProgramPhone(programPhoneNum);
	   			}
       		}       
    	   	IPhoneNumber locationFax = locInfo.getLocationFax();
       		if (locationFax.getLast4Digit() != "")
       		{
       			String pgmFaxNum ="";
   	   			StringBuffer phoneInfo = new StringBuffer();
   	   			phoneInfo.append((locInfo.getLocationFax().getAreaCode() == null)?"":locInfo.getLocationFax().getAreaCode());
   	   			phoneInfo.append("-");
   	   			phoneInfo.append((locInfo.getLocationFax().getPrefix() == null)?"":locInfo.getLocationFax().getPrefix());
   	   			phoneInfo.append("-");
   	   			phoneInfo.append((locInfo.getLocationFax().getLast4Digit() == null)?"":locInfo.getLocationFax().getLast4Digit());
   	   			pgmFaxNum = phoneInfo.toString().trim();
   	   			if (pgmFaxNum.length() < 5 ){
   	   				pgmFaxNum = "";
   	   			}else if (StringUtils.isNotEmpty(pgmFaxNum)){
	   				PhoneNumber referralProgramFax = new PhoneNumber(pgmFaxNum);
	   				programDataTo.setReferralProgramFax(referralProgramFax);
	   			}
   	       	}
       		String referralProgramLocation = (locInfo.getStreetName() == null)?"":locInfo.getStreetName();
       		if(StringUtils.isNotEmpty(referralProgramLocation))
       		{
	       		StringBuffer addressInfo = new StringBuffer();
				addressInfo.append((locInfo.getStreetNumber() == null)?"":locInfo.getStreetNumber());
				addressInfo.append(" ");
				addressInfo.append((locInfo.getStreetName() == null)?"":locInfo.getStreetName());
				if(StringUtils.isNotEmpty(locInfo.getAptNum())) {
					addressInfo.append(" Apt/Suite ");
					addressInfo.append(locInfo.getAptNum());						
				}
				addressInfo.append(" ");
				addressInfo.append((locInfo.getCity() == null)?"":locInfo.getCity());
				addressInfo.append(" ");
				addressInfo.append((locInfo.getState() == null)?"":locInfo.getState());
				addressInfo.append(" ");
				addressInfo.append((locInfo.getZipCode() == null)?"":locInfo.getZipCode());
				referralProgramLocation = addressInfo.toString().trim();
				programDataTo.setReferralProgramLocation(referralProgramLocation);
       		}
       }
	}//end of populateProgramDetails()
	
	/**
	 * 
	 * @param serviceProviderDataTo
	 * serviceProviderForm
	 * @param progRefForm
	 */
	public static void populateServiceProviderDetails(ReferralFormReportDataTO serviceProviderDataTo, ServiceProviderCSCDForm serviceProviderForm, CSCProgRefForm progRefForm)
	{
		if( StringUtils.isNotEmpty(progRefForm.getUserEnteredServiceProviderName() ))
		{
			serviceProviderDataTo.setServiceProviderName(progRefForm.getUserEnteredServiceProviderName());
			if (progRefForm.getReferralProgramLocBean().getProgramLocationBean() != null)
			{
	    	   	IPhoneNumber enteredSPPhone = progRefForm.getUserEnteredSPPhone();
	    	   	if (enteredSPPhone.getLast4Digit() != "")
	       		{
		            String pgmPhoneNum = enteredSPPhone.getFormattedPhoneNumber();
		   			if (pgmPhoneNum.length() < 5 ){
		   				pgmPhoneNum = "";
		   			}else if (StringUtils.isNotEmpty(pgmPhoneNum)){
		   				PhoneNumber programPhoneNum = new PhoneNumber(pgmPhoneNum);
		   				serviceProviderDataTo.setServiceProviderPhone(programPhoneNum);
		   			}
	       		}       
	    	   	IPhoneNumber enteredSPFax = progRefForm.getUserEnteredSPFax();
	       		if (enteredSPFax.getLast4Digit() != "")
	       		{
	       			String pgmFaxNum = enteredSPFax.getFormattedPhoneNumber();
	   	   			if (pgmFaxNum.length() < 5 ){
	   	   				pgmFaxNum = "";
	   	   			}else if (StringUtils.isNotEmpty(pgmFaxNum)){
		   				PhoneNumber programFaxNum = new PhoneNumber(pgmFaxNum);
		   				serviceProviderDataTo.setServiceProviderFax(programFaxNum);
		   			}
	   	       	} 
			}
		}
		else
		{
			if (StringUtils.isNotEmpty(serviceProviderForm.getName()))
			{
				serviceProviderDataTo.setServiceProviderName(serviceProviderForm.getName());
				List contacts = (List) serviceProviderForm.getContacts();
				if(contacts != null)
				{
					for( int i = 0; i < contacts.size(); i++ )
					{
						ServiceProviderLightContactBean contactName = (ServiceProviderLightContactBean) contacts.get(i);
						if(!contactName.equals(""))
						{
							serviceProviderDataTo.setServiceProviderContactName(contactName.getContactName());
							serviceProviderDataTo.setServiceProviderContactPhone(contactName.getContactOfficePhoneNumber());
							break;
						}
					}
				}
				if (serviceProviderForm.getPhoneNumber() != null)
	       		{
	       			if (StringUtils.isNotEmpty(serviceProviderForm.getPhoneNumber().getLast4Digit())){
		       			String serviceProvPhone = "";
			   			StringBuffer phoneInfo = new StringBuffer();
			   			phoneInfo.append((serviceProviderForm.getPhoneNumber().getAreaCode() == null)?"":serviceProviderForm.getPhoneNumber().getAreaCode());
			   			phoneInfo.append("-");
			   			phoneInfo.append((serviceProviderForm.getPhoneNumber().getPrefix() == null)?"":serviceProviderForm.getPhoneNumber().getPrefix());
			   			phoneInfo.append("-");
			   			phoneInfo.append((serviceProviderForm.getPhoneNumber().getLast4Digit() == null)?"":serviceProviderForm.getPhoneNumber().getLast4Digit());
			   			serviceProvPhone = phoneInfo.toString().trim();
			   			if (serviceProvPhone.length() < 5 ){
			   				serviceProvPhone = "";
			   			}else if (StringUtils.isNotEmpty(serviceProvPhone)){
			   				PhoneNumber serviceProviderPhone = new PhoneNumber(serviceProvPhone);
			   				serviceProviderDataTo.setServiceProviderPhone(serviceProviderPhone);
			   			}
	       			}
	       		}
	    	   	if ( serviceProviderForm.getFaxNumber() != null)
	       		{
	       			String serviceProvFax ="";
	   	   			StringBuffer phoneInfo = new StringBuffer();
	   	   			phoneInfo.append((serviceProviderForm.getFaxNumber().getAreaCode() == null)?"":serviceProviderForm.getFaxNumber().getAreaCode());
	   	   			phoneInfo.append("-");
	   	   			phoneInfo.append((serviceProviderForm.getFaxNumber().getPrefix() == null)?"":serviceProviderForm.getFaxNumber().getPrefix());
	   	   			phoneInfo.append("-");
	   	   			phoneInfo.append((serviceProviderForm.getFaxNumber().getLast4Digit() == null)?"":serviceProviderForm.getFaxNumber().getLast4Digit());
	   	   			serviceProvFax = phoneInfo.toString().trim();
	   	   			if (serviceProvFax.length() < 5 ){
	   	   				serviceProvFax = "";
	   	   			}else if (StringUtils.isNotEmpty(serviceProvFax)){
		   				PhoneNumber serviceProviderFax = new PhoneNumber(serviceProvFax);
		   				serviceProviderDataTo.setServiceProviderFax(serviceProviderFax);
		   			}
	   	       	}
	    	   	String serviceProviderLocation = (serviceProviderForm.getMailingAddress().getStreetName() == null)?"":serviceProviderForm.getMailingAddress().getStreetName();
	       		if(StringUtils.isNotEmpty(serviceProviderLocation))
	       		{
		       		StringBuffer addressInfo = new StringBuffer();
					addressInfo.append((serviceProviderForm.getMailingAddress().getStreetNum() == null)?"":serviceProviderForm.getMailingAddress().getStreetNum());
					addressInfo.append(" ");
					addressInfo.append((serviceProviderForm.getMailingAddress().getStreetName() == null)?"":serviceProviderForm.getMailingAddress().getStreetName());
					if(StringUtils.isNotEmpty(serviceProviderForm.getMailingAddress().getAptNum())) {
						addressInfo.append(" Apt/Suite ");
						addressInfo.append(serviceProviderForm.getMailingAddress().getAptNum());						
					}
					addressInfo.append(" ");
					addressInfo.append((serviceProviderForm.getMailingAddress().getCity() == null)?"":serviceProviderForm.getMailingAddress().getCity());
					addressInfo.append(" ");
					addressInfo.append((serviceProviderForm.getMailingAddress().getStateCode() == null)?"":serviceProviderForm.getMailingAddress().getStateCode());
					addressInfo.append(" ");
					addressInfo.append((serviceProviderForm.getMailingAddress().getZipCode() == null)?"":serviceProviderForm.getMailingAddress().getZipCode());
					serviceProviderLocation = addressInfo.toString().trim();
					serviceProviderDataTo.setServiceProviderLocation(serviceProviderLocation);
	       		}
			}
		}
	}//end of populateServiceProviderDetails()
	
	/**
	 * 
	 * @param cscdDwiRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateCscdDwiFields(CSCDDwiReferralFormReportDataTO cscdDwiRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.ENROLLMENT_NUM).equalsIgnoreCase(formField.getFieldLabel()))
					{
	   					for (int x= 0; x <formField.getResponseChoices().size(); x++)
	   					{
	   						RefFormFieldOption opts =  (RefFormFieldOption) formField.getResponseChoices().get(x);
	   						if (opts.getOptionId().equals(formField.getResponseId()))
	   						{
	   							cscdDwiRefFormDataTo.setEnrollmentNumber(opts.getOptionDisplayText());
	   							break;
	   						}
	   					}
	   				}else			
					if ((CSReferralFormConstants.NUM_ACC_TWO_YRS).equalsIgnoreCase(formField.getFieldLabel()))
					{
						cscdDwiRefFormDataTo.setNumOfAccidentsInTwoYears(formField.getResponseText());	
					} else
					if ((CSReferralFormConstants.NUM_ALC_DRG_ARRESTS).equalsIgnoreCase(formField.getFieldLabel()))
					{
						cscdDwiRefFormDataTo.setNumOfAlcDrugArrests(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.NUM_ALC_DRG_TRTMNT).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdDwiRefFormDataTo.setNumOfAlcDrugTreatments(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.RECENT_BAC).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdDwiRefFormDataTo.setRecentBAC(formField.getResponseText());
					}else 
					if ((CSReferralFormConstants.NUM_FELONY_ARRESTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdDwiRefFormDataTo.setNumOfFelonyArrests(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.NUM_MISC_NON_ALC_DRG_ARREST).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdDwiRefFormDataTo.setNumOfMisdNonAlcDrugArrests(formField.getResponseText());	
					} else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdDwiRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateCscdDwiFields()
	
	/**
	 * 
	 * @param cscdEduRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateCscdEduFields(CSCDEduReferralFormReportDataTO cscdEduRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.PROGRAM_NAME).equalsIgnoreCase(formField.getFieldLabel()))
					{
	   					cscdEduRefFormDataTo.setProgramName(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.ENROLLMENT_NUM).equalsIgnoreCase(formField.getFieldLabel()))
					{
						for (int x= 0; x <formField.getResponseChoices().size(); x++)
	   					{
	   						RefFormFieldOption opts =  (RefFormFieldOption) formField.getResponseChoices().get(x);
	   						if (opts.getOptionId().equals(formField.getResponseId()))
	   						{
	   							if (formField.getResponseId().equals("5") ) {
	   								cscdEduRefFormDataTo.setEnrollmentNumber("");
		   							break;
	   							} else {
	   								cscdEduRefFormDataTo.setEnrollmentNumber(opts.getOptionDisplayText());
	   								break;
	   							}
	   						}
	   					}	
					} else
					if ((CSReferralFormConstants.ORDER_VOLUNTEER).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						for (int x= 0; x <formField.getResponseChoices().size(); x++)
	   					{
	   						RefFormFieldOption opts =  (RefFormFieldOption) formField.getResponseChoices().get(x);
	   						if (opts.getOptionId().equals(formField.getResponseId()))
	   						{
	   							if (formField.getResponseId().equals("9") ) {
	   								cscdEduRefFormDataTo.setAdultEducationOrderVolunteer("");
		   							break;
	   							} else {
		   							cscdEduRefFormDataTo.setAdultEducationOrderVolunteer(opts.getOptionDisplayText());
		   							break;
	   							}
	   						}
	   					}	
					}else 
					if ((CSReferralFormConstants.API_DATE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdEduRefFormDataTo.setApiDate(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.API_COMPREHENSIVE_SCORE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdEduRefFormDataTo.setApiComprehensiveScore(formField.getResponseText());	
					}else
					if ((CSReferralFormConstants.API_VOCABULARY_SCORE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdEduRefFormDataTo.setApiVocabularyScore(formField.getResponseText());	
					}else
					if ((CSReferralFormConstants.API_AVERAGE_SCORE).equalsIgnoreCase(formField.getFieldLabel()))
					{
						cscdEduRefFormDataTo.setApiAverageScore(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.TABE_DATE).equalsIgnoreCase(formField.getFieldLabel()))
					{
						cscdEduRefFormDataTo.setTabeDate(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.TABE_COMPREHENSIVE_SCORE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdEduRefFormDataTo.setTabeComprehensiveScore(formField.getResponseText());	
					} else
					if ((CSReferralFormConstants.TABE_MATHEMATIC_SCORE).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						cscdEduRefFormDataTo.setTabeMathematicsScore(formField.getResponseText());		
					}else
					if ((CSReferralFormConstants.TABE_AVERAGE_SCORE).equalsIgnoreCase(formField.getFieldLabel()))
					{			
						cscdEduRefFormDataTo.setTabeAverageScore(formField.getResponseText());		
					}else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdEduRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateCscdEduFields()
	
	/**
	 * 
	 * @param cscdGeneralRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateCscdGeneralFields(ReferralFormReportDataTO cscdGeneralRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.PROGRAM_NAME).equalsIgnoreCase(formField.getFieldLabel()))
					{
	   					cscdGeneralRefFormDataTo.setProgramName(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.ENROLLMENT_NUM).equalsIgnoreCase(formField.getFieldLabel()))
					{
						for (int x= 0; x <formField.getResponseChoices().size(); x++)
	   					{
	   						RefFormFieldOption opts =  (RefFormFieldOption) formField.getResponseChoices().get(x);
	   						if (opts.getOptionId().equals(formField.getResponseId()))
	   						{
	   							if (formField.getResponseId().equals("12") ) {
	   								cscdGeneralRefFormDataTo.setEnrollmentNumber("");
		   							break;
	   							} else {
	   								cscdGeneralRefFormDataTo.setEnrollmentNumber(opts.getOptionDisplayText());
	   								break;
	   							}
	   						}
	   					}
					} else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdGeneralRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateCscdGeneralFields()
	
	/**
	 * 
	 * @param cscdTaipRefFormDataTo
	 * @param fieldsList
	 */	
	public static void populateCscdTaipFields(CSCDTaipReferralFormDataTO  cscdTaipRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			List substanceAbuseDates = new ArrayList();
			List substanceAbused     = new ArrayList();
			List prevTreatmentDates  = new ArrayList();
			List prevTreatmentPrograms = new ArrayList();
			boolean isScreeningSource = true;
			boolean isScreeningInstrument = true;
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.COVERED_BY_INSURANCE).equalsIgnoreCase(formField.getFieldLabel()))
					{
						cscdTaipRefFormDataTo.setMedicalInsurance(formField.getResponseId());
	   				}else			
					if ((CSReferralFormConstants.PUBLIC_ASSISTANCE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setPublicAssistance(formField.getResponseId());
						
					} else
					if ((CSReferralFormConstants.MONTHLY_INCOME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setMonthlyIncome(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.OCCUPATION).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setOccupation(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.NUM_OF_DEPENDANTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setNumOfDependants(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.MENTAL_HLTH_DIAG_DT).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setMentalHealthPgmDt1(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.MENTAL_HLTH_PGM_DT).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setMentalHealthPgmDt2(formField.getResponseText());	
					}else 
					if ((CSReferralFormConstants.MENTAL_HLTH_DIAG).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						cscdTaipRefFormDataTo.setMentalHealthPgmDiag1(formField.getResponseText());		
					}else 
					if ((CSReferralFormConstants.MENTAL_HLTH_PGM).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						cscdTaipRefFormDataTo.setMentalHealthPgmDiag2(formField.getResponseText());			
					}else 					
					if ((CSReferralFormConstants.CURRENT_MEDICATIONS).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						cscdTaipRefFormDataTo.setMedicationComments(formField.getResponseText());					
					}else 
					if ((CSReferralFormConstants.CURRENT_MEDICAL_PROBLEMS).equalsIgnoreCase(formField.getFieldLabel()))
					{		
					    cscdTaipRefFormDataTo.setMedicalProblemsComments(formField.getResponseText());						
					}else 
					if ((CSReferralFormConstants.DATE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						if(formField.getResponseText() != null)
						{
							substanceAbuseDates.add(formField.getResponseText());
						}					
					}else
					if ((CSReferralFormConstants.SUBSTANCE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						if(formField.getResponseText() != null)
						{
							substanceAbused.add(formField.getResponseText());
						}
					}else
					if ((CSReferralFormConstants.PRV_TRTMNT_DT).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						if(formField.getResponseText() != null)
						{
							prevTreatmentDates.add(formField.getResponseText());
						}							
					}else
					if ((CSReferralFormConstants.PRV_TRTMNT_PGM).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						if(formField.getResponseText() != null)
						{
							prevTreatmentPrograms.add(formField.getResponseText());
						}								
					}else
					if ((CSReferralFormConstants.ASSESS_SCREEN_SOURCE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						if(isScreeningSource)
						{
							cscdTaipRefFormDataTo.setAssessScreenSource1(formField.getResponseText());
							isScreeningSource = false;
						}else{
							cscdTaipRefFormDataTo.setAssessScreenSource2(formField.getResponseText());							
						}					
				   }else
					if ((CSReferralFormConstants.ASSESS_SCREEN_INSTRMNT).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						if(isScreeningInstrument)
						{
							cscdTaipRefFormDataTo.setAssessScreenInstrument1(formField.getResponseText());
							isScreeningInstrument = false;
						}else{
							cscdTaipRefFormDataTo.setAssessScreenInstrument2(formField.getResponseText());							
						}							
				   }else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTaipRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
			if ( substanceAbuseDates.size() > 0 ) {
			//	Collections.reverse(substanceAbuseDates);
				cscdTaipRefFormDataTo.setSubstanceAbuseDatesList(substanceAbuseDates);
			}
			if ( substanceAbused.size() > 0 ) {					
			//	Collections.reverse(substanceAbused);
				cscdTaipRefFormDataTo.setSubstanceAbusedList(substanceAbused);
			}
			if ( prevTreatmentDates.size() > 0 ) {					
			//	Collections.reverse(prevTreatmentDates);
				cscdTaipRefFormDataTo.setPrevTreatmentDatesList(prevTreatmentDates);
			}
			if ( prevTreatmentPrograms.size() > 0 ) {					
			//	Collections.reverse(prevTreatmentPrograms);							
				cscdTaipRefFormDataTo.setPrevTreatmentProgramsList(prevTreatmentPrograms);
			}
		}
	}//end of populateCscdTaipFields()
	
	/**
	 * 
	 * @param cscdTaipRefFormDataTo
	 * @param assessForm
	 */	
	public static void populateCscdTaipReportDetails(CSCDTaipReferralFormDataTO cscdTaipRefFormDataTo, AssessmentForm assessForm){
		  String defendant_id = cscdTaipRefFormDataTo.getSuperviseeId();
			if (!StringUtil.isEmpty(defendant_id)) { //For Assessment scores and DisposedCases Info
				while (defendant_id.length() < 8) {
					defendant_id = "0" + defendant_id;
				}
				assessForm.clearSuperviseeDetails();
				assessForm.clear();
				assessForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
				assessForm.setDefendantId(defendant_id);
				RequestEvent reqEvent = AdminAssessmentsHelper.getAssessmentsSummaryEvent(assessForm);
				Collection assessmentsSummaryList = MessageUtil.postRequestListFilter(reqEvent, AssessmentSummaryResponseEvent.class);
				if (assessmentsSummaryList != null && !assessmentsSummaryList.isEmpty()) 
				{
					// populate the AssessmentForm with AssessmentSummaryResponseEvent
					AdminAssessmentsHelper.setAssessmentSummaryResponseEventCollection(assessForm, (ArrayList) assessmentsSummaryList);
					List reassessmentsList = new ArrayList();						
					boolean reAssessmentExist = false;
					//for Risk and NeedScores
					if (assessForm.getReassessmentsList() != null && !assessForm.getReassessmentsList().isEmpty())
					{
						Iterator iterator = assessForm.getReassessmentsList().iterator();
						while (iterator.hasNext()) 
						{
							AssessmentLightBean assessmentBean = (AssessmentLightBean) iterator.next();
							if ((assessmentBean.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN) || assessmentBean.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR)) && assessmentBean.isStatusComplete() && assessmentBean.getAssessmentStatus().equalsIgnoreCase("EXIST")) 
							{
								reassessmentsList.add(assessmentBean);
							}
						}
						if (reassessmentsList != null && reassessmentsList.size() > 0) 
						{
							reAssessmentExist = true;
							Collections.sort(reassessmentsList, Collections.reverseOrder());
							AssessmentLightBean assessmentBean = (AssessmentLightBean) reassessmentsList.get(0);
							cscdTaipRefFormDataTo.setRiskScore(assessmentBean.getRiskScore());										
							cscdTaipRefFormDataTo.setNeedsScore(assessmentBean.getNeedsScore());
						}
					}
					if (assessForm.getInitialAssessmentsList() != null && !assessForm.getInitialAssessmentsList().isEmpty() && !reAssessmentExist) 
					{
						Iterator iterator = assessForm.getInitialAssessmentsList().iterator();
						while (iterator.hasNext()) 
						{
							AssessmentLightBean assessmentBean = (AssessmentLightBean) iterator.next();
							if ((assessmentBean.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN) || assessmentBean.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR))&& assessmentBean.isStatusComplete()) 
							{
								cscdTaipRefFormDataTo.setRiskScore(assessmentBean.getRiskScore());
								cscdTaipRefFormDataTo.setNeedsScore(assessmentBean.getNeedsScore());
								break;
							}
						}
					}
					//for SCS Category
					if (assessForm.getScsAssessmentsList() != null && !assessForm.getScsAssessmentsList().isEmpty()) 
					{	
						Iterator iterator = assessForm.getScsAssessmentsList().iterator();
						while (iterator.hasNext()) 
						{
							AssessmentLightBean assessmentBean = (AssessmentLightBean) iterator.next();
							if (assessmentBean.getAssessmentTypeId().equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS) && assessmentBean.isStatusComplete()) 
							{
								cscdTaipRefFormDataTo.setScsScore(assessmentBean.getPrimaryClassificationStr());
							   	break;									
							}
						}
					}
				}
			}
	}//end of populateCscdTaipReportDetails()
	
	/**
	 * 
	 * @param cscdTDOEPRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateCscdTDOEPFields(ReferralFormReportDataTO cscdTDOEPRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.ENROLLMENT_NUM).equalsIgnoreCase(formField.getFieldLabel()))
					{
	   					for (int x= 0; x <formField.getResponseChoices().size(); x++)
	   					{
	   						RefFormFieldOption opts =  (RefFormFieldOption) formField.getResponseChoices().get(x);
	   						if (opts.getOptionId().equals(formField.getResponseId()))
	   						{
	   							if (formField.getResponseId().equals("16") ) {
	   								cscdTDOEPRefFormDataTo.setEnrollmentNumber("");
		   							break;
	   							} else {
	   								cscdTDOEPRefFormDataTo.setEnrollmentNumber(opts.getOptionDisplayText());
	   								break;
	   							}
	   						}
	   					}
	   				}else			
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						cscdTDOEPRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateCscdTDOEPFields()
	
	/**
	 * 
	 * @param cscdVipRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateCscdVIPDates(ReferralFormReportDataTO cscdVipRefFormDataTo)
	{
		//get description for the given program group and type codes
		List vipDatesList =
			SimpleCodeTableHelper.getCodesSortedByCodeId("VIP_DATES");
		List englishList = new ArrayList();
		List spanishList = new ArrayList();
		if(vipDatesList != null)
		{
			Iterator vipDatesIter = vipDatesList.iterator();
			for (int x= 0; x <vipDatesList.size(); x++)
			{
				CodeResponseEvent vipDate = (CodeResponseEvent)vipDatesIter.next();
				if (x < 12)
				{
					englishList.add(vipDate);
				} else {
					spanishList.add(vipDate);
				}
				
			}
			cscdVipRefFormDataTo.setVIPEnglishList(englishList);
			cscdVipRefFormDataTo.setVIPSpanishList(spanishList);
		}
	}//end of populateCscdVIPDates()
	
	/**
	 * 
	 * @param generalRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateGeneralFields(ReferralFormReportDataTO generalRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.PROGRAM_NAME).equalsIgnoreCase(formField.getFieldLabel()))
					{
	   					generalRefFormDataTo.setProgramName(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						generalRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateGeneralFields()
	
	/**
	 * 
	 * @param newStartRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateNewStartFields(NewStartReferralFormReportDataTO newStartRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{  			
					if ((CSReferralFormConstants.CONTACT_METHOD).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setSuperviseeContMethDesc(formField.getResponseId());	
					}else
					if ((CSReferralFormConstants.TDC_NUMBER).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setSuperviseeTDCNumber(formField.getResponseText());	
					} else 
					if ((CSReferralFormConstants.SUBST_ABS_HISTORY).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setSubsAbuseHistIndicator(formField.getResponseId());	
					}else 
					if ((CSReferralFormConstants.CURR_SUBST_ABUSE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setSubsAbuseCurrUsrIndicator(formField.getResponseId());	
					}else 
					if ((CSReferralFormConstants.COURT_ORDER_MAND).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setNewStartCrtOrderIndicator(formField.getResponseId());	
					}else 
					if ((CSReferralFormConstants.REVOCATION_RISK).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setRevcRiskIndicator(formField.getResponseId());	
					}else 
					if ((CSReferralFormConstants.REVOCATION_RISK_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						newStartRefFormDataTo.setRevcRiskComments(formField.getResponseText());		
					}else
					if ((CSReferralFormConstants.CURRENT_MEDICATIONS).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						newStartRefFormDataTo.setMedicationComments(formField.getResponseText());			
					}else
					if ((CSReferralFormConstants.DIAG_PRESENT_PSYCH_PROBLEMS).equalsIgnoreCase(formField.getFieldLabel()))
					{		
						newStartRefFormDataTo.setMentalHealthComments( formField.getResponseText() );			
					}else
					if ((CSReferralFormConstants.CURRENT_NEEDS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						if (formField.getResponseChoices() != null && formField.getResponseChoices().size() > 0 ) 
						{
							List currentNeedsList = new ArrayList();
							List selectedIds = new ArrayList();
							String[] formIds;
							formIds = formField.getSelectedResponseIdsArr();
							currentNeedsList = formField.getResponseChoices();
							if ( formIds != null ){
								selectedIds = Arrays.asList(formIds); 
							}
							
							if(currentNeedsList != null)
							{
								Iterator currentNeedsIter = currentNeedsList.iterator();
								while(currentNeedsIter.hasNext())
								{
									RefFormFieldOption formFieldOption = (RefFormFieldOption)currentNeedsIter.next();
									if (formFieldOption.getOptionDisplayText() != null)
									{			
										if ((CSReferralFormConstants.PSYCHIATRIC).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setPsychIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setPsychIndicator(CSReferralFormConstants.ZERO);
											}	
										
										}else
										if ((CSReferralFormConstants.MEDICAL).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{										
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setMedicalIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setMedicalIndicator(CSReferralFormConstants.ZERO);
											}	
									
										}else 
										if ((CSReferralFormConstants.DRUG_ALCH_TREATMENT).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setDrugAlcoholIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setDrugAlcoholIndicator(CSReferralFormConstants.ZERO);
											}
										}else 
										if ((CSReferralFormConstants.HOUSING).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setHousingIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setHousingIndicator(CSReferralFormConstants.ZERO);
											}
									
										}else 
										if ((CSReferralFormConstants.EDUCATION).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setEducationIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setEducationIndicator(CSReferralFormConstants.ZERO);
											}
										}else 
										if ((CSReferralFormConstants.GRP_PGM).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setGroupProgramIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setGroupProgramIndicator(CSReferralFormConstants.ZERO);
											}
									
										}else
										if ((CSReferralFormConstants.INDV_COUNSELING).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setIndvCounselingIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setIndvCounselingIndicator(CSReferralFormConstants.ZERO);
											}
										}else
										if ((CSReferralFormConstants.BENEFITS_APPL).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{	
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setBenefitsAppIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setBenefitsAppIndicator(CSReferralFormConstants.ZERO);
											}
										}else
										if ((CSReferralFormConstants.EMPL_TRAINING).equalsIgnoreCase(formFieldOption.getOptionDisplayText()))
										{
											if(formFieldOption.getOptionId() != null && selectedIds.contains(formFieldOption.getOptionId()))
											{
												newStartRefFormDataTo.setEmpTrainingIndicator(CSReferralFormConstants.ONE);
											}
											else{
												newStartRefFormDataTo.setEmpTrainingIndicator(CSReferralFormConstants.ZERO);
											}
										}else
										if ((CSReferralFormConstants.OTHER).equalsIgnoreCase(formFieldOption.getOptionDisplayText()) && formFieldOption.getUserEnteredOptionName() != null )
										{
											newStartRefFormDataTo.setOtherComments(formFieldOption.getUserEnteredOptionName());
										}
									}	
								}	
							}
						}			
				    }else					
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						newStartRefFormDataTo.setReferralNeeds( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateNewStartFields()
	
	/**
	 * 
	 * @param nonTaipTreatmentDataTo
	 * @param fieldsList	
	 */
	public static void populateNonTaipTreatmentFields(TAIPReferralFormReportDataTO nonTaipTreatmentDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.REFERRAL_OFFICER).equalsIgnoreCase(formField.getFieldLabel()))
					{
						nonTaipTreatmentDataTo.setReferralOfficer(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.INTENSIVE_OUTPATIENT_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						nonTaipTreatmentDataTo.setIntensiveOutpatientTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.SUPPORTIVE_OUTPATIENT_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						nonTaipTreatmentDataTo.setSupportiveOutpatientTime(formField.getResponseText());
					}else			
					if ((CSReferralFormConstants.INTENSIVE_RESIDENTIAL_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						nonTaipTreatmentDataTo.setIntensiveResidentialTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.RESIDENTIAL_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						nonTaipTreatmentDataTo.setResidentialTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						nonTaipTreatmentDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateNonTiapTreatmentFields()
	
	/**
	 * 
	 * @param polyRefFormDataTo
	 * @param fieldsList
	 */
	public static void populatePolygraphFields(PolygraphReferralFormReportDataTO polyRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{		
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.SERVICE_PROVIDER_FAX).equalsIgnoreCase(formField.getFieldLabel()))
					{
						polyRefFormDataTo.setSpFax(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.FAX_DATE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setSpFaxDate(formField.getResponseText());
					}else 
					if ((CSReferralFormConstants.EXAM_DATE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setPolygrphExamDate(formField.getResponseText());
					}else 
					if ((CSReferralFormConstants.EXAM_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setPolygrphExamTime(formField.getResponseText());
					} else 
					if ((CSReferralFormConstants.EXAMINER_NAME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setPolygrphExaminerName(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.EXAMINER_PHONE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setPolygrphExaminerPhone(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.EXAMINER_FAX).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setPolygrphExaminerFax(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.SELF_PAY).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setSelfPay(formField.getResponseId());
					} else
					if ((CSReferralFormConstants.EXAMINATION_TYPE).equalsIgnoreCase(formField.getFieldLabel()))
					{
						for (int x= 0; x <formField.getResponseChoices().size(); x++)
	   					{
	   						RefFormFieldOption opts =  (RefFormFieldOption) formField.getResponseChoices().get(x);
	   						if (opts.getOptionId().equals(formField.getResponseId()))
	   						{
	   							polyRefFormDataTo.setPolygrphExamType(opts.getOptionId());
	   							break;
	   						}
	   					}
					} else
					if	((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						polyRefFormDataTo.setReferralReasonComments( formField.getResponseText() );
					}
				}
			}	
		}
	}//end of populatePolygraphFields()
	
	/**
	 * 
	 * @param priorOffenseDataTo
	 * @param caseAssignForm
	 */
	public static void populatePriorOffenseDetails(ReferralFormReportDataTO priorOffenseDataTo, CaseAssignmentForm  caseAssignForm){
		String defendant_id = priorOffenseDataTo.getSuperviseeId();
		if (StringUtils.isNotEmpty(defendant_id)) // For Current Offense, Current Offense Date and Disposed cases Info 
		{
			while (defendant_id.length() < 8) 
			{
				defendant_id = "0" + defendant_id;
			}
			GetSupervisionOrdersEvent requestEvent = 
				(GetSupervisionOrdersEvent) EventFactory.getInstance(
					SupervisionOrderControllerServiceNames.GETSUPERVISIONORDERS);

			requestEvent.setCaseNum(caseAssignForm.getCaseNum());
			requestEvent.setCourtDivision(caseAssignForm.getCdi());
			requestEvent.setSpn(defendant_id);
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
			GetRecentDisposedCasesEvent getDisposedCasesEvent = (GetRecentDisposedCasesEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETRECENTDISPOSEDCASES);
			getDisposedCasesEvent.setDefendantId(defendant_id); 
			List disposedCases = MessageUtil.postRequestListFilter(getDisposedCasesEvent, DisposedCaseResponseEvent.class);
			if( disposedCases != null && disposedCases.size() > 0)
			{	
				DisposedCaseResponseEvent currentOffense = null;
				String caseCDI = "";
				String caseDisposition = "";
				String caseNumber = "";
				for(int i=0; i < disposedCases.size(); i++)
				{
					currentOffense = (DisposedCaseResponseEvent) disposedCases.get(i);
					caseCDI = currentOffense.getCdi();
					caseDisposition = currentOffense.getDispositionCd();
					caseNumber = currentOffense.getCriminalCaseId();
					if( !StringUtils.isNotEmpty(currentOffense.getOffenseDate() ) )
					{
						disposedCases.remove(i);
						i--;
					}
					else if(caseAssignForm.getCaseNum().trim().equals(caseNumber))
					{
						//set Instant Offense Date and remove Instant Offense from Other Offenses 
						String tempDate = currentOffense.getOffenseDate();
						Date currentOffenseDate = DateUtil.convertMMDDYY(tempDate);
						priorOffenseDataTo.setCurrentOffenseDate(currentOffenseDate);
						disposedCases.remove(i);
						i--;
					}
					else if(!StringUtil.isEmpty(caseCDI) && caseCDI.trim().equals("001"))
					{
						//remove CDI = 001 cases from Other Offenses
						disposedCases.remove(i);
						i--;
					}
					else if(!StringUtil.isEmpty(caseDisposition) && caseDisposition.trim().equals("DISM"))
					{
						//remove Dismissed cases from Other Offenses
						disposedCases.remove(i);
						i--;
					}
					else if(!StringUtil.isEmpty(caseDisposition) && caseDisposition.trim().equals("NOB"))
					{
						//remove No Billed cases from Other Offenses
						disposedCases.remove(i);
						i--;
					}else if(StringUtil.isEmpty(currentOffense.getOffenseDate()) && StringUtil.isEmpty(currentOffense.getOffenseDesc()) && StringUtil.isEmpty(caseDisposition))
					{
						//remove cases without Date and Offense and Disposition  from Other Offenses
						disposedCases.remove(i);
						i--;
					}
				}
				//sort Prior Offenses by date ascending order
				Collections.sort((List)disposedCases,DisposedCaseResponseEvent.PriorOffenseDateComparator);
				//reverse sort Prior Offenses by date descending order
				Collections.reverse(disposedCases);	
				if( disposedCases.size() > 0 )
				{
					List tempList = new ArrayList();
					if ( disposedCases.size() < 5 ) {
						tempList.addAll( disposedCases );
					}else{						
						for(int x=0; x < 5; x++ )
						{
							tempList.add(disposedCases.get(x));
						}
					}
					priorOffenseDataTo.setPriorOffenseRecordList(tempList);
				}
	        }
		}// Current Offense, Current Offense Date and Disposed cases Info
	}//end of populatePriorOffenseDetails()
	
	/**
	 * 
	 * @param sexOffenderTreatmentRefFormDataTo
	 * @param fieldsList
	 */
	public static void populateSexOffenderTreatmentFields(PolygraphReferralFormReportDataTO sexOffenderTreatmentRefFormDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.SERVICE_PROVIDER_FAX).equalsIgnoreCase(formField.getFieldLabel()))
					{
						sexOffenderTreatmentRefFormDataTo.setSpFax(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.FAX_DATE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						sexOffenderTreatmentRefFormDataTo.setSpFaxDate(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.EXAMINER_NAME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						sexOffenderTreatmentRefFormDataTo.setPolygrphExaminerName(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.EXAMINER_PHONE).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						sexOffenderTreatmentRefFormDataTo.setPolygrphExaminerPhone(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.EXAMINER_FAX).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						sexOffenderTreatmentRefFormDataTo.setPolygrphExaminerFax(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.SELF_PAY).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						sexOffenderTreatmentRefFormDataTo.setSelfPay(formField.getResponseId());
					} else
					if ((CSReferralFormConstants.SEX_OFFENDER_REFERRAL_REASON).equalsIgnoreCase(formField.getFieldLabel()))
					{
						
	   					sexOffenderTreatmentRefFormDataTo.setPolygrphExamType(formField.getResponseId());
	   					List responseChoices = formField.getResponseChoices();
	   					if ( responseChoices != null )
	   					{
	   						Iterator choicesIter = responseChoices.iterator();
	   						while(choicesIter.hasNext())
	   						{
	   							RefFormFieldOption choice = (RefFormFieldOption)choicesIter.next();
	   							if ( choice.getOptionId().equals(formField.getResponseId()) ) {
	   								sexOffenderTreatmentRefFormDataTo.setSexOffenRefOtherReasDesc(choice.getUserEnteredOptionName());
	   							}
	   						}
	   					}
	   					
					} else
					if	((CSReferralFormConstants.SEX_OFFENDER_REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						sexOffenderTreatmentRefFormDataTo.setSexOffenRefReason( formField.getResponseText() );
					}
				}
			}	
		}
	}//end of populateSexOffenderTreatmentFields()
	
	/**
	 * 
	 * @param supervisionDatesTo
	 * @param caseAssignForm
	 */
	public static void populateSupervisionDates(ReferralFormReportDataTO supervisionDatesTo, CaseAssignmentForm  caseAssignForm){
		String caseIdStr = null;
		if(StringUtils.isNotEmpty(caseAssignForm.getCdi()) && StringUtils.isNotEmpty(caseAssignForm.getCaseNum()))
		{
	        StringBuffer caseNumber = new StringBuffer();
	        caseNumber.append( caseAssignForm.getCdi() );
	        caseNumber.append( caseAssignForm.getCaseNum() ); 
			caseIdStr = caseNumber.toString().trim();
		}
		if (StringUtils.isNotEmpty(caseIdStr))	// For Parole/Probation Date
		{
			GetCurrentSupervisionOrderStartEndDatesEvent orderEvent = (GetCurrentSupervisionOrderStartEndDatesEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETCURRENTSUPERVISIONORDERSTARTENDDATES);
			orderEvent.setCaseId(caseIdStr); 
			orderEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());		
			Collection supervisionOrder = MessageUtil.postRequestListFilter(orderEvent, SupervisionOrderStartEndDatesResponseEvent.class);
			if (supervisionOrder != null && !supervisionOrder.isEmpty()) 
			{
				Iterator iterator = supervisionOrder.iterator();
				while (iterator.hasNext()) 
				{
					SupervisionOrderStartEndDatesResponseEvent resp = (SupervisionOrderStartEndDatesResponseEvent) iterator.next();
					supervisionDatesTo.setDocs(resp.getCaseSupervisionBeginDate());
					supervisionDatesTo.setDischargeDate(resp.getCaseSupervisionEndDate());
					break;
				}
			}
		}
	}//end of populateSupervisionDates()
	
	/**
	 * 
	 * @param taipOutPatntDataTo
	 * @param fieldsList
	 */
	public static void populateTaipOutPatientFields(TAIPReferralFormReportDataTO taipOutPatntDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.REFERRAL_OFFICER).equalsIgnoreCase(formField.getFieldLabel()))
					{
						taipOutPatntDataTo.setReferralOfficer(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.INTENSIVE_OUTPATIENT_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						taipOutPatntDataTo.setIntensiveOutpatientTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.SUPPORTIVE_OUTPATIENT_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						taipOutPatntDataTo.setSupportiveOutpatientTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						taipOutPatntDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateTiapOutPatientFields()
	
	/**
	 * 
	 * @param taipResidentialDataTo
	 * @param fieldsList
	 */
	public static void populateTaipResidentialFields(TAIPReferralFormReportDataTO taipResidentialDataTo, List fieldsList)
	{
		if(fieldsList != null)
		{
			Iterator fieldIter = fieldsList.iterator();
			while(fieldIter.hasNext())
			{
				ReferralFormField formField = (ReferralFormField)fieldIter.next();
				if (formField.getFieldLabel() != null)
				{
					if ((CSReferralFormConstants.REFERRAL_OFFICER).equalsIgnoreCase(formField.getFieldLabel()))
					{
						taipResidentialDataTo.setReferralOfficer(formField.getResponseText());
	   				}else			
					if ((CSReferralFormConstants.INTENSIVE_RESIDENTIAL_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						taipResidentialDataTo.setIntensiveResidentialTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.SUPPORTIVE_RESIDENTIAL_TIME).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						taipResidentialDataTo.setResidentialTime(formField.getResponseText());
					} else
					if ((CSReferralFormConstants.REFERRAL_REASON_COMMENTS).equalsIgnoreCase(formField.getFieldLabel()))
					{	
						taipResidentialDataTo.setReferralReasonComments( formField.getResponseText() );
					} 
				}
			}	
		}
	}//end of populateTiapResidentialFields()
	
}

