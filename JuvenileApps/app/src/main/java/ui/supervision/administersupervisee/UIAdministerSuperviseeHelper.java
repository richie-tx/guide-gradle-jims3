package ui.supervision.administersupervisee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.administersupervisee.GetSuperviseeDataEvent;
import messaging.administersupervisee.GetTransfersEvent;
import messaging.administersupervisee.reply.DNAResponseEvent;
import messaging.administersupervisee.reply.ProgramTrackerResponseEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import messaging.administersupervisee.reply.SupervisionLevelResponseEvent;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SuperviseeControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;

import ui.common.CodeHelper;
import ui.common.PhoneNumber;
import ui.supervision.supervisee.form.SuperviseeForm;

public class UIAdministerSuperviseeHelper {
	
//	static private Collection counties;
	
	/**
	 * Populates a supervisee forms based on supervisee id
	 * @param superviseeForm
	 */
	public static void populateSuperviseeForm(SuperviseeForm superviseeForm) {
		
		String superviseeId = superviseeForm.getSuperviseeId();
		GetSuperviseeDataEvent anEvent = new GetSuperviseeDataEvent();
		anEvent.setSuperviseeId(superviseeId);
		CompositeResponse response = MessageUtil.postRequest(anEvent);
		
		SuperviseeDetailResponseEvent superviseeDetailResponseEvent = (SuperviseeDetailResponseEvent) MessageUtil.filterComposite(response,
				SuperviseeDetailResponseEvent.class);
		if(superviseeDetailResponseEvent != null){
				
			PhoneNumber phoneNumber = new PhoneNumber("");
			String phoneNum = "";
			/* SUPERVISEE INFORMATION */
			//superviseeForm.setSuperviseeId(superviseeId);
			StringBuffer name = new StringBuffer();
			name.append(superviseeDetailResponseEvent.getLastName());
			name.append(", ");
			name.append(superviseeDetailResponseEvent.getFirstName());
			if(superviseeDetailResponseEvent.getMiddleName() != null && !"".equals(superviseeDetailResponseEvent.getMiddleName())){
				name.append(" ");
				name.append(superviseeDetailResponseEvent.getMiddleName());			
			}
			superviseeForm.setSuperviseeName(name.toString());
			//superviseeForm.setSuperviseeNameSource(superviseeDetailResponseEvent.getDescriptionSourceId());
			superviseeForm.setSuperviseeNameSource(CodeHelper.getCodeDescription(PDCodeTableConstants.DESCRIPTORSOURCE, superviseeDetailResponseEvent.getDescriptionSourceId()));
			superviseeForm.setSuperviseeListIndex(superviseeDetailResponseEvent.getSsn());
			
			/* DESCRIPTIIVE INFORMATION */
			superviseeForm.setDateOfBirth(DateUtil.dateToString(superviseeDetailResponseEvent.getDateOfBirth(), UIConstants.DATE_FMT_1));
			superviseeForm.setBuild(CodeHelper.getCodeDescription(PDCodeTableConstants.BUILD, superviseeDetailResponseEvent.getBuildId()));
			superviseeForm.setRace(CodeHelper.getCodeDescription(PDCodeTableConstants.RACE, superviseeDetailResponseEvent.getRaceId()));
			superviseeForm.setSex(CodeHelper.getCodeDescription(PDCodeTableConstants.SEX, superviseeDetailResponseEvent.getSexId()));
			superviseeForm.setRawHeight(superviseeDetailResponseEvent.getHeight());
			superviseeForm.setWeight(superviseeDetailResponseEvent.getWeight());
			superviseeForm.setComplexion(CodeHelper.getCodeDescription(PDCodeTableConstants.SKIN_TONE, superviseeDetailResponseEvent.getComplexionId()));
			superviseeForm.setEyeColor(CodeHelper.getCodeDescription(PDCodeTableConstants.EYE_COLOR, superviseeDetailResponseEvent.getEyeColorId()));
			superviseeForm.setHairColor(CodeHelper.getCodeDescription(PDCodeTableConstants.HAIR_COLOR, superviseeDetailResponseEvent.getHairColorId()));
			superviseeForm.setScarsMarksDescription(superviseeDetailResponseEvent.getScars());
			superviseeForm.setTattoosDescription(superviseeDetailResponseEvent.getTattoos());
			superviseeForm.setUsCitizenshipInd(superviseeDetailResponseEvent.getUsCitizenId());
			
			if (superviseeDetailResponseEvent.getFingerprinted()) {
				superviseeForm.setFingerprintedInd("YES");
			} else {
				superviseeForm.setFingerprintedInd("NO");
			}
			
			//superviseeForm.setFingerprintedInd(superviseeDetailResponseEvent.getFingerprinted().toString());
			
			superviseeForm.setEthnicity(CodeHelper.getCodeDescription(PDCodeTableConstants.ETHNICITY, superviseeDetailResponseEvent.getEthnicityId()));
			superviseeForm.setMaritalStatus(CodeHelper.getCodeDescription(PDCodeTableConstants.MARITAL_STATUS, superviseeDetailResponseEvent.getMaritalStatusId()));
			superviseeForm.setPlaceOfBirth(superviseeDetailResponseEvent.getBirthPlaceId());
			superviseeForm.setStateCountryOfBirth(superviseeDetailResponseEvent.getBirthStateCountryId());
			//superviseeForm.setDescriptionSource(superviseeDetailResponseEvent.getDescriptionSourceId());
			superviseeForm.setDescriptionSource(CodeHelper.getCodeDescription(PDCodeTableConstants.DESCRIPTORSOURCE, superviseeDetailResponseEvent.getDescriptionSourceId()));
					
			/* ADDRESS INFORMATION */
			superviseeForm.setSuperviseeStreetNumber(superviseeDetailResponseEvent.getCurrentAddress().getStreetNum());
			superviseeForm.setSuperviseeStreetName(superviseeDetailResponseEvent.getCurrentAddress().getStreetName());
			superviseeForm.setSuperviseeStreetType(superviseeDetailResponseEvent.getCurrentAddress().getStreetTypeId());
			superviseeForm.setSuperviseeApartmentNumber(superviseeDetailResponseEvent.getCurrentAddress().getAptNum());
			superviseeForm.setSuperviseeCity(superviseeDetailResponseEvent.getCurrentAddress().getCity());
			superviseeForm.setSuperviseeState(superviseeDetailResponseEvent.getCurrentAddress().getStateId());
			superviseeForm.setSuperviseeZipCode(superviseeDetailResponseEvent.getCurrentAddress().getCompleteZipCode());
			superviseeForm.setSuperviseeAddressType(superviseeDetailResponseEvent.getCurrentAddress().getAddressTypeCode());
			if (superviseeDetailResponseEvent.getHomePhoneNum() != null && !superviseeDetailResponseEvent.getHomePhoneNum().equals("")){
				phoneNum = phoneNumber.formatPhoneNumberWithDashes(superviseeDetailResponseEvent.getHomePhoneNum());
			}
			superviseeForm.setSuperviseePhoneNumber(phoneNum);
			
			/* ID NUMBER INFORMATION */	
			superviseeForm.setSuperviseeListIndex(superviseeDetailResponseEvent.getSsn());
			superviseeForm.setDriverLicenseNumber(superviseeDetailResponseEvent.getDriversLicenseNum());
			superviseeForm.setDriverLicenseState(superviseeDetailResponseEvent.getDriversLicenseStateId());
			superviseeForm.setStateIdNumber(superviseeDetailResponseEvent.getSidNum());
			superviseeForm.setFbiNumber(superviseeDetailResponseEvent.getFbiNum());
			
			/* EMPLOYER INFORMATION */
			phoneNum = "";
			superviseeForm.setEmployerName(superviseeDetailResponseEvent.getCurrentEmployer().getEmployerName());
			superviseeForm.setEmployerOccupation(superviseeDetailResponseEvent.getCurrentEmployer().getOccupation());
			if (superviseeDetailResponseEvent.getCurrentEmployer().getPhoneNum() != null && !superviseeDetailResponseEvent.getCurrentEmployer().getPhoneNum().equals("")){
				phoneNum = phoneNumber.formatPhoneNumberWithDashes(superviseeDetailResponseEvent.getCurrentEmployer().getPhoneNum());
			}
			superviseeForm.setEmployePhoneNum(phoneNum);
			superviseeForm.setEmployerEmploymentStatus(superviseeDetailResponseEvent.getCurrentEmployer().getEmploymentStatusId());
			superviseeForm.setEmployerStreetNum(superviseeDetailResponseEvent.getCurrentEmployer().getStreetNum());
			superviseeForm.setEmployerStreetName(superviseeDetailResponseEvent.getCurrentEmployer().getStreetName());
			superviseeForm.setEmployerStreetType(superviseeDetailResponseEvent.getCurrentEmployer().getStreetTypeId());
			//superviseeForm.setEmployerAptNum(superviseeDetailResponseEvent.getCurrentEmployer().get);
			superviseeForm.setEmployerCity(superviseeDetailResponseEvent.getCurrentEmployer().getCity());
			superviseeForm.setEmployerState(superviseeDetailResponseEvent.getCurrentEmployer().getState());
			superviseeForm.setEmployerZipCode(superviseeDetailResponseEvent.getCurrentEmployer().getCompleteZipCode());
			
			/* EDUCATION INFORMATION */	
			superviseeForm.setIntakeDate(DateUtil.dateToString(superviseeDetailResponseEvent.getIntakeDate(), UIConstants.DATE_FMT_1));
			superviseeForm.setHighestGradeCompleted(superviseeDetailResponseEvent.getEducationLevel());
	
			if (superviseeDetailResponseEvent.getHasReportedEducationData()) {
			
				if (superviseeDetailResponseEvent.isHighSchoolDiplomaInd()) {
					superviseeForm.setHighSchoolDiploma("YES");
				} else {
					superviseeForm.setHighSchoolDiploma("NO");
				}
			
				if (superviseeDetailResponseEvent.isGedInd()) {
					superviseeForm.setGED("YES");
				} else {
					superviseeForm.setGED("NO");
				}
				
				if (StringUtils.isNotEmpty(superviseeDetailResponseEvent.getAdvancedDegree())) {
					superviseeForm.setAdvancedDegreeEarned(superviseeDetailResponseEvent.getAdvancedDegree());
				} else {
					superviseeForm.setAdvancedDegreeEarned("NO");
				}
			
			} else {
				superviseeForm.setHighSchoolDiploma("");
				superviseeForm.setGED("");
				superviseeForm.setAdvancedDegreeEarned("");
			}
			
			if (superviseeDetailResponseEvent.getHasAssementData()) {
			
				if (superviseeDetailResponseEvent.isGedVerifiedInd()) {
					superviseeForm.setGEDVerified("YES");
				} else {
					superviseeForm.setGEDVerified("NO");
				}
			
					
				if (superviseeDetailResponseEvent.isHighSchoolDiplomaVerifiedInd()) {
					superviseeForm.setHsDiplomaVerified("YES");
				} else {
					superviseeForm.setHsDiplomaVerified("NO");
				}
			
			} else {
				superviseeForm.setGEDVerified("");
				superviseeForm.setHsDiplomaVerified("");
			}
			
			
			superviseeForm.setGEDAttainedDate(DateUtil.dateToString(superviseeDetailResponseEvent.getGedDate(), UIConstants.DATE_FMT_1));
			superviseeForm.setHsDiplomaAttainedDate(DateUtil.dateToString(superviseeDetailResponseEvent.getHighSchoolDiplomaDate(), UIConstants.DATE_FMT_1));
			superviseeForm.setAssessmentDate(DateUtil.dateToString(superviseeDetailResponseEvent.getAssessmentDate(), UIConstants.DATE_FMT_1));
			String s = "";
			if (superviseeDetailResponseEvent.getAssessedLevel() != null && !superviseeDetailResponseEvent.getAssessedLevel().equals("")){
				String assessLevel = superviseeDetailResponseEvent.getAssessedLevel();
				if (assessLevel.length() == 2){
					s = assessLevel.substring(0,2) + "." + assessLevel.substring(2,3);
				} else {
					s = assessLevel;
				}
			}
			superviseeForm.setAssessmentLevel(s);		
			superviseeForm.setAssessmentMethod(superviseeDetailResponseEvent.getAssessmentMethod());
			superviseeForm.setReportedLevel(superviseeDetailResponseEvent.getReportedLevelId());
			
			/* View LOS History */
			superviseeForm.setLosHistories(superviseeDetailResponseEvent.getLevelOfSupervisionHistories());
			SupervisionLevelResponseEvent currLos = superviseeDetailResponseEvent.getCurrentLevelOfSupervision();

			if (currLos != null) {
				UIAdministerSuperviseeHelper.populateCurrentLOSInformation(superviseeForm, superviseeDetailResponseEvent.getCurrentLevelOfSupervision());
			} else {
				UIAdministerSuperviseeHelper.blankCurrentLOSInformation(superviseeForm);
			}
						
			/* View Program Tracker History */
			if (superviseeDetailResponseEvent.getProgramTrackerId() !=  null){
				superviseeForm.setProgramTrackerEffectiveDate(DateUtil.dateToString(superviseeDetailResponseEvent.getProgramTrackerEffectiveDate(), UIConstants.DATE_FMT_1));
				superviseeForm.setProgramTrackerEndDate(DateUtil.dateToString(superviseeDetailResponseEvent.getProgramTrackerEndDate(), UIConstants.DATE_FMT_1));
				superviseeForm.setProgramTrackerId(superviseeDetailResponseEvent.getProgramTrackerId());
				if (superviseeDetailResponseEvent.getProgramTrackerEndDate() != null){
					superviseeForm.setProgramTrackerEnded("Y");
				}
			} else {
				superviseeForm.setProgramTrackerId("");
				superviseeForm.setProgramTrackerEffectiveDate("");
				superviseeForm.setProgramTrackerEndDate("");
				superviseeForm.setProgramTrackerDesc("");
			}
			superviseeForm.setProgramTrackerHistories(getPgmTrackingHistories(superviseeDetailResponseEvent.getProgramTrackerHistories()));
			
			/* View DNA */
			if (superviseeDetailResponseEvent.isDnaFlagInd() && superviseeDetailResponseEvent.getDnaCollectedDate() != null ) {
				// used to keep original values during changes in pages
				superviseeForm.setDnaCurrentRecordFlagInd(superviseeDetailResponseEvent.isDnaFlagInd());
				superviseeForm.setDnaCurrentRecordDate(DateUtil.dateToString(superviseeDetailResponseEvent.getDnaCollectedDate(), UIConstants.DATE_FMT_1));
				// used by pages during updates, etc
				superviseeForm.setDnaFlagInd(superviseeDetailResponseEvent.isDnaFlagInd());
				superviseeForm.setDnaCollectedDate(DateUtil.dateToString(superviseeDetailResponseEvent.getDnaCollectedDate(), UIConstants.DATE_FMT_1));
			} else {
				superviseeForm.setSuperviseeHistoryId("");
				superviseeForm.setDnaCollectedDate("");
				superviseeForm.setDnaCurrentRecordDate("");
				superviseeForm.setDnaFlagInd(false);
				superviseeForm.setDnaCurrentRecordFlagInd(false);
				superviseeForm.setDnaEntryDate("");
			}
			superviseeForm.setDnaHistories(superviseeDetailResponseEvent.getDnaHistories());
			
			GetTransfersEvent request = (GetTransfersEvent)EventFactory.getInstance(SuperviseeControllerServiceNames.GETTRANSFERS);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			request.setSuperviseeId(superviseeId);
			request.setSearchType("C");
			dispatch.postEvent(request);
	
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
	
			List transfers = MessageUtil.compositeToList(compositeResponse, TransferResponseEvent.class);
			
			Collection harrisCountyCases = new ArrayList();
			Collection courtesyCountyCases = new ArrayList();
			//counties = CodeHelper.getCountyCodes();
	
		    if (transfers != null && transfers.size() > 0) {
		    	
				Iterator it = transfers.iterator();
				while (it.hasNext()){
					
					TransferResponseEvent evt = (TransferResponseEvent)it.next();
					
					if (evt.getCdi().equals("010")) {
							//Check to see if ooc case was transferred in and not transferred out.
						if (evt.getOutOfCountyTransferInDate() != null
							&& !evt.getOutOfCountyTransferInDate().equals("")
							&& (evt.getOutOfCountyTransferOutDate() == null
									|| evt.getOutOfCountyTransferOutDate().equals(""))){
								courtesyCountyCases.add(evt);
							}
					} else if (evt.getHcTransferOutDate() != null
								&& !evt.getHcTransferOutDate().equals("")
								&& (evt.getHcTransferInDate() == null
										|| evt.getHcTransferInDate().equals(""))){
								//hc case is currently transferred out.
								harrisCountyCases.add(evt); 
					}
				}
			}
			superviseeForm.setCourtesyCases(courtesyCountyCases);
			superviseeForm.setHarrisCountyCases(harrisCountyCases);
		}
		
	}
	
	/**
	 * Populates the most current LOS information on a superviseeForm 
	 * @param superviseeForm
	 */
	 public static void populateCurrentLOSInformationFromHistory(SuperviseeForm superviseeForm) {
		List losHistoriesList = getLOSHistory(superviseeForm);
		
		Iterator iter = losHistoriesList.iterator();

		while (iter.hasNext()) {
			
			SupervisionLevelResponseEvent supervisionLevel = (SupervisionLevelResponseEvent) iter
					.next();
			
			superviseeForm.setSupervisionLevelHistoryId(supervisionLevel
					.getSupervisionLevelHistoryId());
			superviseeForm.setEffectiveDate(DateUtil.dateToString(
					supervisionLevel.getLosEffectiveDate(),
					UIConstants.DATE_FMT_1));
			superviseeForm.setSupervisionLevel(supervisionLevel
					.getSupervisionLevelId());
			superviseeForm.setSupervisionLevelDesc(supervisionLevel
					.getSupervisionLevelDesc());
			superviseeForm.setLosComments(supervisionLevel.getComments());

			break;

		}
	}
	 
	public static List getLOSHistory(SuperviseeForm superviseeForm){
		List losHistoriesList = superviseeForm.getLosHistories();	
		if (losHistoriesList != null){
			Collections.sort(losHistoriesList); 
		}
		return losHistoriesList;
	}
	
	public static void populateCurrentLOSInformation(SuperviseeForm superviseeForm, SupervisionLevelResponseEvent supervisionLevel) {

		superviseeForm.setSupervisionLevelHistoryId(supervisionLevel
				.getSupervisionLevelHistoryId());
		superviseeForm.setEffectiveDate(DateUtil.dateToString(
				supervisionLevel.getLosEffectiveDate(),
				UIConstants.DATE_FMT_1));
		superviseeForm.setEffectiveLosDateCurrentRecord(DateUtil.dateToString(
				supervisionLevel.getLosEffectiveDate(),
				UIConstants.DATE_FMT_1)); 
		superviseeForm.setSupervisionLevel(supervisionLevel
				.getSupervisionLevelId());	
		superviseeForm.setSupervisionLevelCurrentRecord(supervisionLevel
				.getSupervisionLevelId()); 
		superviseeForm.setSupervisionLevelDesc(supervisionLevel
				.getSupervisionLevelDesc());
		
		List histList = getLOSHistory(superviseeForm);
		if (histList != null && histList.size() > 0){
			SupervisionLevelResponseEvent slre = (SupervisionLevelResponseEvent) histList.get(0);
			superviseeForm.setLosComments(slre.getComments());
			superviseeForm.setLosCommentsCurrentRecord(slre.getComments());
		} else {
			superviseeForm.setLosComments(PDConstants.BLANK);
			superviseeForm.setLosCommentsCurrentRecord(PDConstants.BLANK);
		}
	}
	/**
	 * Blanks current LOS information on a superviseeForm 
	 * @param superviseeForm
	 */
	public static void blankCurrentLOSInformation(SuperviseeForm superviseeForm) {
		superviseeForm.setSupervisionLevelHistoryId("");
		superviseeForm.setEffectiveDate("");
		superviseeForm.setSupervisionLevel("");
		superviseeForm.setSupervisionLevelDesc("");
		superviseeForm.setLosComments("");
	}

	/**
	 * @param superviseeDetailResponseEvent.getProgramTrackerHistories
	 * @return List
	 */	
	public static List getPgmTrackingHistories( List ptHistories) {
		List trackingHistories = new ArrayList();
		if (ptHistories != null && !ptHistories.isEmpty()){
			if (ptHistories.size() == 1){
				trackingHistories = ptHistories;
			} else {
				List temp = sortPgmTrackingHistoriesList(ptHistories);
				List workList1 = new ArrayList();
				List workList2 = new ArrayList();
/** load lists based on end date being present or not */				
				for (int t=0; t < temp.size(); t++){
					ProgramTrackerResponseEvent ptre = (ProgramTrackerResponseEvent) temp.get(t);
					if (ptre.getProgramTrackerEndDate() == null){
						workList1.add(ptre);
					} else {
						workList2.add(ptre);
					}
				}
/** compare lists and load return list */	
				for (int x = 0; x<workList1.size(); x++){
					ProgramTrackerResponseEvent ptre1 = (ProgramTrackerResponseEvent) workList1.get(x);
					boolean match = false;
					for (int y = 0; y<workList2.size(); y++){
						ProgramTrackerResponseEvent ptre2 = (ProgramTrackerResponseEvent) workList2.get(y);
						if (ptre1.getProgramTrackerEffectiveDate().equals(ptre2.getProgramTrackerEffectiveDate() ) ) { 
							trackingHistories.add(ptre2);
							match = true;
							break;
						}
					}
					if (match == false){
						trackingHistories.add(ptre1);
					}
				}
			}	
		}
		return trackingHistories;
	}
	
	/**
	 * @param superviseeDetailResponseEvent.getProgramTrackerHistories
	 * @return List
	 */
	public static List sortPgmTrackingHistoriesList(List pthList){
		if (pthList.size() > 1){
			String effDate = " ";
			String endDate = " ";
			SortedMap map = new TreeMap();
			for (int x = 0; x < pthList.size(); x++){
				ProgramTrackerResponseEvent ptre = (ProgramTrackerResponseEvent) pthList.get(x);
				if (ptre.getProgramTrackerEffectiveDate() != null){
					effDate = DateUtil.dateToString(ptre.getProgramTrackerEffectiveDate(), "yyyyMMdd");
				}				
				if (ptre.getProgramTrackerEndDate() != null){
					endDate = DateUtil.dateToString(ptre.getProgramTrackerEndDate(), "yyyyMMdd");
				}
				map.put(effDate + endDate, ptre);
				effDate = " ";
				endDate = " ";
			}
			pthList = new ArrayList(map.values());
		}
		return pthList;
	}
	
	public static List getDNAHistory(List dnaHist){
		List dnaHistories = new ArrayList();
		if (dnaHist != null && !dnaHist.isEmpty()){
			if (dnaHist.size() == 1){
				dnaHistories = dnaHist;
			} else {
				List temp = sortDnaHistoriesList(dnaHist);
				List workList = new ArrayList();
/** load lists based on end date being present or not */				
				for (int t=0; t < temp.size(); t++){
					DNAResponseEvent dre = (DNAResponseEvent) temp.get(t);
						workList.add(dre);
				}
/** compare lists and load return list */	
				for (int x = 0; x<workList.size(); x++){
					DNAResponseEvent dre1 = (DNAResponseEvent) workList.get(x);
//					boolean match = false;
//					for (int y = 0; y<workList.size(); y++){
//						DNAResponseEvent dre1 = (DNAResponseEvent) workList.get(y);
//				        if (dre.getDnaCollectedDate().equals(dre1.getDnaCollectedDate() ) ) { 
						  dnaHistories.add(dre1);
//						  match = true;
//						  break;
//					    }
					  }
//					{
//					dnaHistories.add(dre);
//					}
				}
			}	
//		}
		return dnaHistories;
	}
	
	public static List sortDnaHistoriesList(List dnaList){
		if (dnaList.size() > 1){
			String dnaDate = " ";
			SortedMap map = new TreeMap();
			for (int x = 0; x < dnaList.size(); x++){
				DNAResponseEvent dre = (DNAResponseEvent) dnaList.get(x);
				if (dre.getDnaCollectedDate() != null){
					dnaDate = DateUtil.dateToString(dre.getDnaCollectedDate(), "yyyyMMdd");
				}
				map.put(dnaDate, dre);
				dnaDate = " ";
			}
			dnaList = new ArrayList(map.values());
		}
		return dnaList;
	}
	
	/**
	 * Populates the most current DNA information on a superviseeForm 
	 * @param superviseeForm
	 */
	 public static void populateCurrentDNAInformationFromHistory(SuperviseeForm superviseeForm) {
		 List dnaHistoriesList = superviseeForm.getDnaHistories();
			if(dnaHistoriesList != null){
				Collections.sort(dnaHistoriesList, DNAResponseEvent.DNAResponseEventOidComparator);			
			}
		Iterator iter = dnaHistoriesList.iterator();

		while (iter.hasNext()) {
			
			DNAResponseEvent dna = (DNAResponseEvent) iter
					.next();
			
			superviseeForm.setSuperviseeHistoryId(dna
					.getSuperviseeHistoryId());
			// set this value to represent the current record until a new record is created
			superviseeForm.setDnaCurrentRecordDate(DateUtil.dateToString(
					dna.getDnaCollectedDate(),
					UIConstants.DATE_FMT_1));
			// use this value to represent the value updated in pages etc as the user enters information
			superviseeForm.setDnaCollectedDate(DateUtil.dateToString(
					dna.getDnaCollectedDate(),
					UIConstants.DATE_FMT_1));
			// set this value to represent the current record until a new record is created
			superviseeForm.setDnaCurrentRecordFlagInd(dna
					.isDnaFlagInd());
			// use this value to represent the value updated in pages etc as the user enters information
			superviseeForm.setDnaFlagInd(dna
					.isDnaFlagInd());
			// original creation date
			superviseeForm.setDnaEntryDate(DateUtil.dateToString(dna.getEntryDate(),UIConstants.DATE_FMT_1));
			// original creation user name
			superviseeForm.setDnaEntryUser(dna.getUserName());
			break;

		}
	}
	
	
	public static void populateCurrentDNAInformation(SuperviseeForm superviseeForm, DNAResponseEvent dna) {

		superviseeForm.setSuperviseeHistoryId(dna
				.getSuperviseeHistoryId());
		superviseeForm.setDnaCollectedDate(DateUtil.dateToString(
				dna.getDnaCollectedDate(),
				UIConstants.DATE_FMT_1));
		superviseeForm.setDnaFlagInd(dna
				.isDnaFlagInd());
		superviseeForm.setDnaEntryDate(DateUtil.dateToString(
				dna.getEntryDate(),
				UIConstants.DATE_FMT_1));
	}
		
}
