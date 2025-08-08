//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisee\\transactions\\GetSuperviseeDataCommand.java

package pd.supervision.administersupervisee.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administersupervisee.GetSuperviseeDataEvent;
import messaging.administersupervisee.reply.DNAResponseEvent;
import messaging.administersupervisee.reply.ProgramTrackerResponseEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import messaging.administersupervisee.reply.SuperviseeHistoryResponseEvent;
import messaging.administersupervisee.reply.SupervisionLevelResponseEvent;
import messaging.contact.reply.EmployerResponseEvent;
import messaging.contact.to.Address;
import messaging.domintf.contact.party.IParty;
import messaging.party.GetPartyDataEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.Name;
import naming.CaseloadConstants;
import naming.PDConstants;
import pd.common.util.StringUtil;
import pd.contact.party.IDNumber;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import pd.supervision.administersupervisee.EducationAssessment;
import pd.supervision.administersupervisee.ReportedEducation;
import pd.supervision.csts.CSTSHelper;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;


public class GetSuperviseeDataCommand implements ICommand 
{
   
   /**
    * @roseuid 484E81420180
    */
   public GetSuperviseeDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4849421B00AA
    */
   public void execute(IEvent anEvent) {
	   GetSuperviseeDataEvent event = (GetSuperviseeDataEvent) anEvent;
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetPartyDataEvent getPartyData = new GetPartyDataEvent();
		getPartyData.setSpn(event.getSuperviseeId());
		//off on
		getPartyData.setCurrentNameInd("Y");
		Party party = Party.find(getPartyData);
				
		EducationAssessment assessment = EducationAssessment.find(event.getSuperviseeId());
		ReportedEducation reportedEducation = ReportedEducation.find(event.getSuperviseeId());	
		
		if(party != null){
			IParty partyInfo = PDPartyHelper.getPartyResponseEvent(party);
			this.processIDNumbers(partyInfo);			
			SuperviseeDetailResponseEvent resp = new SuperviseeDetailResponseEvent();
			setResp(partyInfo, resp, assessment, reportedEducation);
			
				//create history collections
			Collection losHistories = new ArrayList();
			List supervisee_history_responses = new ArrayList();
			Collection programTrackerHistories = new ArrayList();
			Collection dnaHistories = new ArrayList();
			
			String supId = "";
			Supervisee sup = Supervisee.findByDefendantId(event.getSuperviseeId());
			if(sup != null) 
			{
					//set supervisee response properties
				supId = sup.getOID();
				
				resp.setProgramUnitAssignmentDate(sup.getProgramUnitAssignmentDate());
				resp.setCaseloadCreditStaffPositionId(sup.getCaseloadCreditStaffPositionId());				
				resp.setAssignedStaffPositionId(sup.getAssignedStaffPositionId());
				resp.setLosEffectiveDate(sup.getLosEffectiveDate());
				resp.setCurrentlySupervised(sup.isCurrentlySupervised());
				
				resp.setDnaCollectedDate(sup.getDnaCollectedDate());
				resp.setDnaFlagInd(sup.isDnaFlagInd());
				
				resp.setCreateDate(sup.getCreateTimestamp());
				resp.setAssignedProgramUnitId(sup.getAssignedProgramUnitId());
				
					//retrieve program unit name
				if (!StringUtil.isEmpty(sup.getAssignedProgramUnitId()))
				{									
					Organization org = Organization.find(sup.getAssignedProgramUnitId());
					if ( org != null ){
						
						resp.setAssignedProgramUnitName( org.getDescription() );
					}
				}
				//retrieve workload credit position
				if (!StringUtil.isEmpty(sup.getCaseloadCreditStaffPositionId()))
				{
		    		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
					ev.setStaffPositionId(sup.getCaseloadCreditStaffPositionId());	
					ev.setOfficerNameNeeded(true);
//					02/25/10 - Dawn removed command chaining.
					LightCSCDStaffResponseEvent staffResponseEvent = CSCDStaffPositionHelper.getLightCSCDStaffForUser(ev);
		    		if (staffResponseEvent != null){
			    		resp.setCaseloadCreditStaffPositionName(staffResponseEvent.getOfficerNameQualifiedByPosition());		    			
		    		}
				}
				
			}
			SupervisionLevelResponseEvent currentLOSResp = null;
			if (!StringUtil.isEmpty(supId)) {
				if (!StringUtil.isEmpty(sup.getSupervisionLevelId())){
					SupervisionLevelOfServiceCode currentLOS = sup.getSupervisionLevel();
					if (currentLOS != null){
						SupervisionLevelResponseEvent losResp = new SupervisionLevelResponseEvent();
						losResp.setLosEffectiveDate(sup.getLosEffectiveDate());
						losResp.setSupervisionLevelId(sup.getSupervisionLevelId());
						resp.setCurrentLevelOfSupervision(losResp);
						currentLOSResp = losResp;
					}
					resp.setProgramTrackerId(sup.getProgramTrackerId());
					resp.setProgramTrackerEffectiveDate(sup.getProgramTrackerEffectiveDate());
					resp.setProgramTrackerEndDate(sup.getProgramTrackerEndDate());
				}
				Iterator iter = SuperviseeHistory.findAll("superviseeId", supId);
				boolean currentLOSHistHasBeenSet = false;
			  
				Map userMap = new HashMap();
				while (iter.hasNext()) 
				{
					SuperviseeHistory hist = (SuperviseeHistory)iter.next();
					//create history response event
					SuperviseeHistoryResponseEvent history_response = 
						new SuperviseeHistoryResponseEvent();

					history_response.setSuperviseeHistoryId(hist.getOID());
					history_response.setSuperviseeId(hist.getSuperviseeId());
					history_response.setType(hist.getType());
					history_response.setLosEffectiveDate(hist.getLosEffectiveDate());
					history_response.setSupervisionLevelId(hist.getSupervisionLevelId());					
					history_response.setAssignedProgramUnitId(hist.getAssignedProgramUnitId());
					
						//retrieve workload credit position
					history_response.setCaseloadCreditStaffPositionId(hist.getCaseloadCreditStaffPositionId());
										
					history_response.setAssignedStaffPositionId(hist.getAssignedStaffPositionId());
					history_response.setCurrentlySupervised(hist.isCurrentlySupervised());
					history_response.setComments(hist.getComments());
					history_response.setDnaCollectedDate(hist.getDnaCollectedDate());
					history_response.setDnaFlagInd(hist.isDnaFlagInd());
					history_response.setProgramUnitAssignmentDate(
							DateUtil.dateToString(hist.getProgramUnitAssignmentDate(), 
									DateUtil.DATE_FMT_1));
					history_response.setCreateDate(
							DateUtil.dateToString(hist.getCreateTimestamp(), 
									DateUtil.DATE_FMT_1));
					
					supervisee_history_responses.add(history_response);					
					
						//create level of supervision responses
					if (hist.getType().equalsIgnoreCase(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_LOS))
					{
						SupervisionLevelResponseEvent losResp = new SupervisionLevelResponseEvent();
						if (currentLOSResp != null && !currentLOSHistHasBeenSet){
							String histEffDate = DateUtil.dateToString(hist.getLosEffectiveDate(), DateUtil.DATE_FMT_1);
							String currEffDate = DateUtil.dateToString(currentLOSResp.getLosEffectiveDate(), DateUtil.DATE_FMT_1);
							if (histEffDate != null && histEffDate.equals(currEffDate)
									&& hist.getSupervisionLevelId().equals(currentLOSResp.getSupervisionLevelId())){
								losResp.setCurrentLOS(true);
								currentLOSHistHasBeenSet = true;
							}
						}

						losResp.setSupervisionLevelHistoryId(hist.getOID());
						losResp.setLosEffectiveDate(hist.getLosEffectiveDate());
						losResp.setSupervisionLevelId(hist.getSupervisionLevelId());
						losResp.setComments(hist.getComments());
						
						/*
						 * Business Rule: LOS "View History" Entry Date should represent the (CREATEDATE) else the (UPDATEDATE)
						 * 
						 */
						
						if(hist.getUpdateTimestamp() != null && !hist.getUpdateTimestamp().equals(PDConstants.BLANK)){
							
							losResp.setEntryDate(hist.getUpdateTimestamp());
							
							}else {
								
								losResp.setEntryDate(hist.getEntryDate());
								
							}
						
						String userId = null;
						if (hist.getUpdateUserID() != null && !hist.getUpdateUserID().equals(PDConstants.BLANK)){
							userId = hist.getUpdateUserID();
						} else {
							userId = hist.getCreateUserID();
						}
						if (userMap.get(userId) == null){
							UserProfile up = UserProfile.find(userId);
							if (up != null){
								Name name = new Name();
								name.setFirstName(up.getFirstName());
								name.setLastName(up.getLastName());
								name.setMiddleName(up.getMiddleName());
								userMap.put(userId, name.getCompleteFullNameFirst());
							}
						}
						losResp.setUserName((String) userMap.get(userId));
						boolean sentToState = CSTSHelper.hasCSTSRecordBeenSentToState(sup.getDefendantId(), hist);
						losResp.setSentToState(sentToState);
						losHistories.add(losResp);
					} 
					
					if (hist.getType().equalsIgnoreCase(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_PROGRAM_TRACKER))
					{
						ProgramTrackerResponseEvent ptResp = new ProgramTrackerResponseEvent();
						ptResp.setSuperviseeHistoryId(hist.getOID());
						ptResp.setProgramTrackerEffectiveDate(hist.getProgramTrackerEffectiveDate());
						ptResp.setProgramTrackerEndDate(hist.getProgramTrackerEndDate());
						ptResp.setProgramTrackerId(hist.getProgramTrackerId());
						ptResp.setEntryDate(hist.getEntryDate());
						String userId = null;
						if (hist.getUpdateUserID() != null && !hist.getUpdateUserID().equals(PDConstants.BLANK)){
							userId = hist.getUpdateUserID();
						} else {
							userId = hist.getCreateUserID();
						}
						if (userMap.get(userId) == null){
							UserProfile up = UserProfile.find(userId);
							if (up != null){
								Name name = new Name();
								name.setFirstName(up.getFirstName());
								name.setLastName(up.getLastName());
								name.setMiddleName(up.getMiddleName());
								userMap.put(userId, name.getCompleteFullNameFirst());
							}
						}
						ptResp.setUserName((String) userMap.get(userId));

						programTrackerHistories.add(ptResp);
					}
					
					if (hist.getType().equalsIgnoreCase(CaseloadConstants.SUPERVISEE_HISTORY_UPDATE_DNA))
					{
						DNAResponseEvent dnaResp = new DNAResponseEvent();
						dnaResp.setSuperviseeHistoryId(hist.getOID());
						dnaResp.setDnaCollectedDate(hist.getDnaCollectedDate());
						dnaResp.setDnaFlagInd(hist.isDnaFlagInd());
						dnaResp.setEntryDate(hist.getEntryDate());
						String userId = null;
						if (hist.getUpdateUserID() != null && !hist.getUpdateUserID().equals(PDConstants.BLANK)){
							userId = hist.getUpdateUserID();
						} else {
							userId = hist.getCreateUserID();
						}
						if (userMap.get(userId) == null){
							UserProfile up = UserProfile.find(userId);
							if (up != null){
								Name name = new Name();
								name.setFirstName(up.getFirstName());
								name.setLastName(up.getLastName());
								name.setMiddleName(up.getMiddleName());
								userMap.put(userId, name.getCompleteFullNameFirst());
							}
						}
						dnaResp.setUserName((String) userMap.get(userId));

						dnaHistories.add(dnaResp);
					}
				}
			}
			
				//add history records to response
			resp.setSuperviseeHistory(supervisee_history_responses);
			
			if (losHistories != null && losHistories.size() > 0) {
				resp.setLevelOfSupervisionHistories((List) losHistories);
			}
			if (programTrackerHistories != null && programTrackerHistories.size() > 0) {
				List sortedList = new ArrayList(programTrackerHistories);
				List sortFields = new ArrayList();
				//Sort by OID desc 
				sortFields.add(new ReverseComparator(new BeanComparator("superviseeHistoryId")));
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort(sortedList, multiSort);

				resp.setProgramTrackerHistories(sortedList);
			}
			if (dnaHistories != null && dnaHistories.size() > 0) {
				resp.setDnaHistories((List) dnaHistories);
			}
			
			dispatch.postEvent(resp);

		} 	
   }
  
   /**
    * @param event
    * @roseuid 4849421B0202
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4849421B02BD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4849421B033B
    */
   public void update(Object anObject) 
   {
    
   }
   private void setResp(IParty party, SuperviseeDetailResponseEvent pre, 
		   EducationAssessment assessment, ReportedEducation reportedEducation) {
	    if (party.getScarsMarks() != null)
	    	pre.setScars((List) party.getScarsMarks());
	    if (party.getTattoos() != null)
	    	pre.setTattoos((List) party.getTattoos());
		pre.setFirstName(party.getFirstName());
		pre.setMiddleName(party.getMiddleName());	
		pre.setLastName(party.getLastName());
	    pre.setDescriptionSourceId(party.getNameSourceId());
		pre.setDateOfBirth(party.getDateOfBirth());
		pre.setBuildId(party.getBuildId());
		pre.setRaceId(party.getRaceId());
		pre.setSexId(party.getSexId());
		pre.setHeight(party.getHeight());
		pre.setWeight(party.getWeight());
		pre.setComplexionId(party.getSkinToneId());		
		pre.setEyeColorId(party.getEyeColorId());
		pre.setHairColorId(party.getHairColorId());
		pre.setUsCitizenId(party.getCitizenshipId());
		if ( party.getFingerPrintedInd() == "" || "N".equals( party.getFingerPrintedInd()) ) {
			pre.setFingerprinted(false);	
		}
		else {
			pre.setFingerprinted(true);
		}
		pre.setEthnicityId(party.getEthnicityId());
		pre.setMaritalStatusId(party.getMaritalStatusId());
		pre.setBirthPlaceId(party.getPlaceOfBirth());
		pre.setBirthStateCountryId(party.getPlaceOfBirthStateId());

		// Address information
	    Address adr = new Address();
	    adr.setStreetNum(party.getCurrentAddressStreetNum());
		adr.setStreetName(party.getCurrentAddressStreetName());
		adr.setStreetTypeId(party.getCurrentAddressStreetName2());
		adr.setAptNum(party.getCurrentAddressAptNum());
		adr.setCity(party.getCurrentAddressCity());
		adr.setStateId(party.getCurrentAddressStateId());
		adr.setZipCode(party.getCurrentAddressZipCode());
		adr.setAddressTypeCode(party.getCurrentAddressTypeId());
		pre.setCurrentAddress(adr);
		pre.setHomePhoneNum(party.getHomePhoneNum());

		// Identification Numbers information		
		pre.setSsn(party.getSsn());
		pre.setDriversLicenseNum(party.getDriversLicenseNum());
		pre.setDriversLicenseStateId(party.getDriversLicenseStateId());
		
		if ( party.getIdCardStateId() != null ){
			
			pre.setSidNum(party.getIdCardStateId());
		}else {
			
			pre.setSidNum(party.getSidNum());
		}
		
		pre.setFbiNum(party.getFbiNum());

		// Employer information		
		EmployerResponseEvent emp = new EmployerResponseEvent(); 
		emp.setEmployerName(party.getEmployerName());
		emp.setOccupation(party.getOccupation());
		emp.setPhoneNum(party.getEmployerPhoneNum());
		emp.setEmploymentStatusId(party.getEmploymentStatusId());
		emp.setStreetNum(party.getEmployerAddressStreetNum());
		emp.setStreetName(party.getEmployerAddressStreetName());
		emp.setStreetType(party.getEmployerAddressStreetName2());
		emp.setCity(party.getEmployerAddressCity());
		emp.setState(party.getEmployerAddressStateId());
		emp.setZipCode(party.getEmployerAddressZipCode());
		pre.setCurrentEmployer(emp);
		
		if (assessment != null) {
			pre.setHasAssementData(true);
			
			if (assessment.isGedVerifiedInd().equalsIgnoreCase("Y")) {
				pre.setGedVerifiedInd(true);
			} else {
				pre.setGedVerifiedInd(false);
			}
			
			pre.setGedDate(assessment.getGedDate());
			
			if (assessment.isHighSchoolDiplomaVerifiedInd().equalsIgnoreCase("Y")) {
				pre.setHighSchoolDiplomaVerifiedInd(true);
			} else {
				pre.setHighSchoolDiplomaVerifiedInd(false);
			}
			
			pre.setHighSchoolDiplomaDate(assessment.getHighSchoolDiplomaDate());
			pre.setAssessmentDate(assessment.getAssessmentDate());
			pre.setAssessedLevel(assessment.getAssessedLevel());
			pre.setReportedLevelId(assessment.getReportedLevelId());
			pre.setAssessmentMethod(assessment.getAssessmentMethod());
		} else {
			pre.setHasAssementData(false);
		}
		
		if (reportedEducation != null) {
			pre.setHasReportedEducationData(true);
			pre.setIntakeDate(reportedEducation.getIntakeDate());
			pre.setEducationLevel(reportedEducation.getEducationLevel());
			
			if (reportedEducation.isHighSchoolDiplomaInd().equalsIgnoreCase("Y")) {
				pre.setHighSchoolDiplomaInd(true);
			} else {
				pre.setHighSchoolDiplomaInd(false);
			}
			
			if (reportedEducation.isGedInd().equalsIgnoreCase("Y")) {
				pre.setGedInd(true);
			} else {
				pre.setGedInd(false);
			}
			
			char advancedDegree = reportedEducation.getAdvancedDegree().charAt(0);
			
			switch(advancedDegree)
			{
				case 'S': 
				{
					pre.setAdvancedDegree("SOME COLLEGE/NO DEGREE");
					break;
				}
				case 'A': 
				{
					pre.setAdvancedDegree("ASSOCIATE");
					break;
				}
				case 'B': 
				{
					pre.setAdvancedDegree("BACHELOR");
					break;
				}
				case 'M': 
				{
					pre.setAdvancedDegree("MASTER");
					break;
				}
				case 'D': 
				{
					pre.setAdvancedDegree("DOCTORATE");
					break;
				}
			}

		} else {
			pre.setHasReportedEducationData(false);
		}
		
//		pre.setAfisNum(party.getAfisNum());
//		pre.setAge(party.getAge());
//		pre.setAlienRegistrationNum(party.getAlienRegistrationNum());
//		pre.setBarNum(party.getBarNum());
//		pre.setCellPhone(party.getCellPhone());
//		pre.setCurrentNameInd(party.getCurrentNameInd());
//		pre.setDescriptorSourceId(party.getDescriptorSourceId());
//		pre.setDriversLicenseClassId(party.getDriversLicenseClassId());
//		pre.setEmail(party.getEmail());
//		pre.setDriversLicenseExpirationYear(party.getDriversLicenseExpirationYear());
	    
//		pre.setFaxLocation(party.getFaxLocation());
//		pre.setFaxNum(party.getFaxNum());
		
//		pre.setIdCardNum(party.getIdCardNum());
//		pre.setIdCardStateId(party.getIdCardStateId());
//		pre.setLanguageId(party.getLanguageId());

//		pre.setLogonId();


//		pre.setNamePtr(party.getNamePtr());
//		pre.setNameTypeId(party.getNameTypeId());
//		pre.setNextOfKinFirstName(party.getNextOfKinFirstName());
//		pre.setNextOfKinLastName(party.getNextOfKinLastName());
//		pre.setNextOfKinMiddleName(party.getNextOfKinMiddleName());
//		pre.setNextOfKinRelationshipId(party.getNextOfKinRelationshipId());
//		pre.setOID(party.getOID().toString());
//		pre.setPager(party.getPager());
//		pre.setPartyId(party.getOID().toString());
//		pre.setPhoneExt(party.getPhoneExt());
//		pre.setPhoneNum(party.getPhoneNum());
//		pre.setSheriffOfficeNum(party.getSheriffOfficeNum());
//		pre.setSidNum(party.getSidNum());
//		pre.setSpn(party.getSpn());
//		pre.setSsn(party.getSsn());
//		pre.setTitle(party.getTitle());
//		pre.setWorkPhoneNum(party.getWorkPhoneNum());
//		pre.setTopic(PDPartyConstants.PARTY_EVENT_TOPIC);

   }
    
   private void processIDNumbers(IParty partyInfo) {
	   
	   class DriversLicense {
		   private String dlNum;
		   private String dlState;
		   private String dlClass;
		   private String dlExpYear;
		   
		private DriversLicense() {
			super();
			this.dlNum = PDConstants.BLANK;
			this.dlState = PDConstants.BLANK;
			this.dlClass = PDConstants.BLANK;
			this.dlExpYear = PDConstants.BLANK;
		}
		   
	   }
	   
	   List <IDNumber> idNums = IDNumber.findAll("spn", partyInfo.getSpn());
	
	   if (idNums != null && idNums.size() > 1){
		   ArrayList sortedList = new ArrayList(idNums);
		   ArrayList sortFields = new ArrayList();
		   sortFields.add(new ReverseComparator(new BeanComparator("lcDate")));
		   sortFields.add(new ReverseComparator(new BeanComparator("lcTime")));
		   ComparatorChain multiSort = new ComparatorChain(sortFields);
		   Collections.sort(sortedList, multiSort);
		   idNums = sortedList;
	   }
	   
	   IDNumber idNum = null;
	   DriversLicense driversLicense = new DriversLicense();
	   String newestSSN = PDConstants.BLANK;
	   
	   for (int i = 0; i < idNums.size(); i++) {
		   idNum = idNums.get(i);
			if (driversLicense.dlNum.equals(PDConstants.BLANK)
					&& idNum.getDriversLicenseNum() != null){
				driversLicense.dlNum = idNum.getDriversLicenseNum();
				driversLicense.dlState = idNum.getDriversLicenseStateId();
				driversLicense.dlClass = idNum.getDriversLicenseClassId();
				driversLicense.dlExpYear = idNum.getDriversLicenseExpirationYear();
			}
			if (newestSSN.equals(PDConstants.BLANK)
					&& idNum.getSsn() != null){
				newestSSN = idNum.getSsn();
			}
			if (!driversLicense.dlNum.equals(PDConstants.BLANK)
					&& !newestSSN.equals(PDConstants.BLANK)){
				break;
			}
	   }
	   
	   if (!newestSSN.equals(PDConstants.BLANK)){
		   partyInfo.setSsn(newestSSN);
	   }
	   if (!driversLicense.dlNum.equals(PDConstants.BLANK)){
		   partyInfo.setDriversLicenseNum(driversLicense.dlNum);
		   partyInfo.setDriversLicenseStateId(driversLicense.dlState);
		   partyInfo.setDriversLicenseExpirationYear(driversLicense.dlExpYear);
		   partyInfo.setDriversLicenseClassId(driversLicense.dlClass);
	   }
   }
}
