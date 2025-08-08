/**
 * 
 */
package ui.juvenilecase.casefile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import messaging.address.reply.AddressResponseEvent;
import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.GetCommonAppFinancialInfoEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.CommonAppFinancialResponseEvent;
import messaging.casefile.reply.DSMDiagnosisResponseEvent;
import messaging.codetable.GetJuvenileMedicationTypeCodesEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.GetNonDetentionJuvenileFacilitiesEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileMedicationTypeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetBenefitsAssessmentGuardiansEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.family.GetFamilyMemberBenefitsEvent;
import messaging.family.GetFamilyMemberContactEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.family.GetFamilyMemberInsuranceEvent;
import messaging.family.GetFamilyMemberTraitEvent;
import messaging.family.GetFamilyTraitsEvent;
import messaging.family.GetGuardianFinancialInfoEvent;
import messaging.family.GetJuvenileAgeAtfamilyMemberDeathEvent;
import messaging.interviewinfo.GetJuvenileInsuranceEvent;
import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import messaging.interviewinfo.to.FamilyInformationTO;
import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.GetJuvenileDrugsEvent;
import messaging.juvenile.GetJuvenileMedicalHealthIssuesListEvent;
import messaging.juvenile.GetJuvenileMedicalMedicationListEvent;
import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JJSReferralResponseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileDrugsResponseEvent;
import messaging.juvenile.reply.JuvenileHealthIssueResponseEvent;
import messaging.juvenile.reply.JuvenileMedicationResponseEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentGuardianResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationTraitsResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberInsuranceResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberTraitResponseEvent;
import messaging.juvenilecase.reply.GuardianFinancialInfoResponseEvent;
import messaging.juvenilecase.reply.JuvenileAgeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsByJuvNumEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.mentalhealth.GetMentalHealthIQResultsEvent;
import messaging.mentalhealth.GetMentalHealthTestingResultsEvent;
import messaging.mentalhealth.reply.IQTestResponseEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import messaging.referral.GetCommonAppJusticeHistoryEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileCasefileReferralDetailsEvent;
import messaging.referral.reply.CommonAppJusticeHistoryResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.ujac.util.BeanComparator;

import pd.juvenilecase.referral.JJSOffense;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.Question;
import ui.common.QuestionGroup;
import ui.common.SimpleCodeTableHelper;
import ui.common.StringUtil;
import ui.common.UIUtil;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.CommonAppForm.Placement;
import ui.juvenilecase.casefile.form.CommonAppForm.SchoolHistory;
import ui.juvenilecase.casefile.form.DeceasedFamilyMemberBean;
import ui.juvenilecase.casefile.form.FamilyMemberTraitBean;
import ui.juvenilecase.casefile.form.JJSReferralBean;
import ui.juvenilecase.casefile.form.PersonInAndOutHomeBean;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileFamilyForm.Trait;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * @author sthyagarajan
 * //added for #34334
 */
public class CommonAppHelper{
	
	
	/**
	 * prepareBean
	 * @param form
	 * @param juvenileNum
	 * @param casefileId
	 * @return
	 */
	public static CommonAppReportPrintBean prepareBean(CommonAppForm form,String juvenileNum, String casefileId) {
		CommonAppReportPrintBean printDataBean = new CommonAppReportPrintBean();
		Collection<QuestionGroup> questionsAnswers =  null;
		
		if(form.getCummulativeExitReportQuestions()!=null && form.getCummulativeExitReportQuestions().containsKey("CASP")){
			Map exitReport  = form.getCummulativeExitReportQuestions();
			 Iterator<Entry> it = exitReport.entrySet().iterator();
			    while (it.hasNext()) {
			        Entry pairs = (Entry)it.next();
			        questionsAnswers=(Collection) pairs.getValue();
			        CommonAppHelper.addToPrintDataBean(questionsAnswers,printDataBean);
			    }
		}else{
			questionsAnswers = form.getExitReportQuestions();
			CommonAppHelper.addToPrintDataBean(questionsAnswers,printDataBean);
		}

		// Section 9 School History

		Collection<JuvenileSchoolHistoryResponseEvent> schoolHistories = new ArrayList<JuvenileSchoolHistoryResponseEvent>();
		schoolHistories = UIJuvenileHelper.fetchSchoolHistory(juvenileNum);
		ArrayList<SchoolHistory> mySchoolHistories = new ArrayList<SchoolHistory>();
		if (schoolHistories != null) {
			Collections.sort((List<JuvenileSchoolHistoryResponseEvent>) schoolHistories,new SchoolHistoryComparator());
			Iterator<JuvenileSchoolHistoryResponseEvent> myIter = schoolHistories.iterator();
			while (myIter.hasNext()) {
				JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp = (JuvenileSchoolHistoryResponseEvent) myIter.next();
				SchoolHistory mySchoolHistory = new SchoolHistory();
				UIJuvenileCasefileClosingHelper.setCommonAppSchoolHistFROMJuvenileSchoolHistRespEvt(mySchoolHistory, mySchoolHistoryResp);
				mySchoolHistories.add(mySchoolHistory);
			}
		}

		if (mySchoolHistories != null && mySchoolHistories.size() != 0) {
			Iterator<SchoolHistory> schoolI = mySchoolHistories.iterator();
			Iterator<SchoolHistory> schoolTrauncyHistoryIterator = mySchoolHistories.iterator();

			SchoolHistory schoolHistory = schoolI.next();
			if (schoolHistory.getGradeLevel() != null  && !schoolHistory.getGradeLevel().equals("")) {
				printDataBean.setCurrentGradeLevel(getCompletedGradelevel(schoolHistory.getGradeLevel()));
			}
			if (schoolHistory.getExitTypeId() != null && !schoolHistory.getExitTypeId().equals("")) {
				printDataBean.setCurrentlyAttending(schoolHistory.getExitType()); //35987 //30823
			}
			if (schoolHistory.getProgramAttending() != null	&& !schoolHistory.getProgramAttending().equals("")) {
				printDataBean.setProgramAttending(schoolHistory.getProgramAttending());
			}
			printDataBean.setTeaSchoolNumber(schoolHistory.getTEASchoolNumber());
			printDataBean.setSchoolName(schoolHistory.getSchool());
			printDataBean.setSchoolDistrict(schoolHistory.getSchoolDistrict());
			//Bug Fix : 11239 starts
			printDataBean.setSchoolAddress(schoolHistory.getSchoolAddress());
			printDataBean.setSchoolCity(schoolHistory.getSchoolCity());
			printDataBean.setSchoolState(schoolHistory.getSchoolState());
			printDataBean.setSchoolZip(schoolHistory.getSchoolZip());
			//Bug Fix : 11239 ends

			//ER 11032 starts.
			boolean hasTruancyAttendanceTrait=false;
			Collection<JuvenileTraitResponseEvent> juvTraits =  UIJuvenileCaseworkHelper.fetchJuvTraits(juvenileNum);
			if(juvTraits!=null && !juvTraits.isEmpty()){
				Iterator<JuvenileTraitResponseEvent> juvTraitsIter = juvTraits.iterator();
				while(juvTraitsIter.hasNext()){
					JuvenileTraitResponseEvent juvTraitResp = (JuvenileTraitResponseEvent) juvTraitsIter.next();
					if(juvTraitResp!=null && juvTraitResp.getTraitTypeId().equals(UIConstants.TRAIT_TYPE_ID_EXCESSIVELY_TRUANT)||juvTraitResp.getTraitTypeId().equals(UIConstants.TRAIT_TYPE_ID_MODERATELY_TRUANT)
							||juvTraitResp.getTraitTypeId().equals(UIConstants.TRAIT_TYPE_ID_OCCASIONALLY_TRUANT)){
						hasTruancyAttendanceTrait=true;
						break;
					}
				}
			}
			//ER 11032 ends.
			while (schoolTrauncyHistoryIterator.hasNext()) {
				SchoolHistory temp =  schoolTrauncyHistoryIterator.next();
				if (temp.getTrauncyHistory() != null && !temp.getTrauncyHistory().equalsIgnoreCase("") && hasTruancyAttendanceTrait) {
					printDataBean.setTrauncyHistory("YES");
					break;
				}
			}
		}

		// Section 8 Financial Info
		setMedicaidInfo(printDataBean, juvenileNum);

		if (form.getGuardianList() != null && form.getGuardianList().size() > 0) {
			printDataBean.setGuardianFinancialInfo(form.getGuardianList());
		} else {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetCommonAppFinancialInfoEvent request = (GetCommonAppFinancialInfoEvent) EventFactory
					.getInstance(JuvenileCasefileControllerServiceNames.GETCOMMONAPPFINANCIALINFO);
			request.setJuvenileNum(juvenileNum);
			dispatch.postEvent(request);
			CommonAppFinancialResponseEvent resp = (CommonAppFinancialResponseEvent) MessageUtil.filterComposite((CompositeResponse) dispatch.getReply(),
							CommonAppFinancialResponseEvent.class);
			if (resp != null) {
				printDataBean.setGuardianFinancialInfo(resp.getFinacialInfo());
			}
		}

		printDataBean.setInsuranceApplicableToChild(getInsuranceApplicableToChild(juvenileNum, (List<FamilyInformationTO>) form.getGuardianFinancialInfo()));

		// Page 1 Section 1 of Common app report

		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		requestEvent.setJuvenileNum(juvenileNum);

		CompositeResponse replyEvent = postRequestEvent(requestEvent);

		JuvenileProfileDetailResponseEvent juvenile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
						JuvenileProfileDetailResponseEvent.class);

		// Getting SID from Juvenile Master Info, need to refactor this.
		printDataBean.setSidNumber(juvenile.getSID());
		if (juvenile.isAdopted()) {
			printDataBean.setAdoptionComments(StringUtil.removeTimeStampFromComments(juvenile.getAdoptionComments()));
			printDataBean.setNumberOfFailedAdoptionPlacement(juvenile.getFailedPlacements());
		}

		if (juvenile.getMiddleName() != null && !"".equals(juvenile.getMiddleName())) {
			printDataBean.setJuvName(juvenile.getFirstName() + " "	+ juvenile.getMiddleName() + " " + juvenile.getLastName());
		} else {
			printDataBean.setJuvName(juvenile.getFirstName() + " "	+ juvenile.getLastName());
		}

		printDataBean.setStateID(juvenile.getSID());
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		printDataBean.setJuvDOB(sdf.format(juvenile.getDateOfBirth()));
		if (juvenile.getDateOfBirth() != null) {
			// Get age based on year
			int age = UIUtil.getAgeInYears(juvenile.getDateOfBirth());
			if (age != 0){
				printDataBean.setJuvAge(String.valueOf(age));
			}else{
				printDataBean.setJuvAge("");
			}
		}
		printDataBean.setJuvSSN(juvenile.getSSN());

		if (juvenile.getSex() != null) {
			printDataBean.setJuvSex(juvenile.getSex());
		}
		if (juvenile.getEthnicity() != null) {
			String ethnicity = CodeHelper.getFastCodeDescription(PDCodeTableConstants.JUVENILE_ETHNICITY, juvenile
					.getEthnicity());
			if(ethnicity==null){
				ethnicity="";
			}
			printDataBean.setJuvEthnicity(ethnicity);
		}

		if (juvenile.getPrimaryLanguage() != null) {
			printDataBean.setJuvPrimaryLanguage(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LANGUAGE, juvenile
							.getPrimaryLanguage()));
		}
		if(juvenile.getBirthCity()!=null && !juvenile.getBirthCity().isEmpty())
		{
			printDataBean.setJuvBirthCity(juvenile.getBirthCity());
		}else{
			printDataBean.setJuvBirthCity("");
		}
		

		if (juvenile.getBirthState() != null && !juvenile.getBirthState().isEmpty()) {
			printDataBean.setJuvBirthState(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR, juvenile
							.getBirthState()));
		}else{
			printDataBean.setJuvBirthState("");
		}

		if (juvenile.getBirthCounty() != null && !juvenile.getBirthCounty().isEmpty() ) {
			printDataBean.setJuvBirthCounty(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.COUNTY, juvenile
							.getBirthCounty()));
		}else{
			printDataBean.setJuvBirthCounty("");
		}

		printDataBean.setJuvAgencyID(juvenile.getJuvenileNum());

		if (juvenile.getReligion() != null && !juvenile.getReligion().isEmpty()) {
			printDataBean.setJuvReligiousPreference(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELIGION, juvenile
							.getReligion()));
		}else{
			printDataBean.setJuvReligiousPreference("");
		}

		if (!"".equals(juvenile.getNationality()) && juvenile.getNationality() != null) {
			printDataBean.setJuvCountryOfCitizenship(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLACE_OF_BIRTH, juvenile
							.getNationality()));
		}else{
			printDataBean.setJuvCountryOfCitizenship("");
		}
		
		if (printDataBean.getAnswer25() != null ) {
		    printDataBean.setSpecializedProgramReq(printDataBean.getAnswer25().toUpperCase());
		    if ( printDataBean.getAnswer25().equals("Yes") ) {
			printDataBean.setSpecializedProgramSpecify("See Screening Profile section #2, Special program Needs....");
		    }
		}

		GetJuvenilePhysicalAttributesEvent reqEvent = (GetJuvenilePhysicalAttributesEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPHYSICALATTRIBUTES);
		reqEvent.setJuvenileNum(juvenileNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);

		replyEvent = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(replyEvent);
		List<JuvenilePhysicalAttributesResponseEvent> physicalChars = MessageUtil.compositeToList(replyEvent,PDJuvenileCaseConstants.JUVENILE_PHYSICAL_CHARACTERISTICS_TOPIC);

		if (physicalChars != null && !physicalChars.isEmpty()) {
			// sort list based on entryDate
			Collections.sort(physicalChars);

			JuvenilePhysicalAttributesResponseEvent latestRecord =  physicalChars.get(0);

			printDataBean.setJuvHeight(latestRecord.getHeightFeet() + "\'"	+ latestRecord.getHeightInch() + "\"");
			printDataBean.setJuvWeight(latestRecord.getWeight());
		}

		// Page 1 Section 2 of Common app report - Traits
		Collection<JuvenileTraitResponseEvent> juvenileTraits;
		GetJuvenileTraitsEvent myJuvTraitEvt = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);

		myJuvTraitEvt.setJuvenileNum(juvenileNum);
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(myJuvTraitEvt);
		replyEvent = (CompositeResponse) dispatch.getReply();

		juvenileTraits = MessageUtil.compositeToCollection(replyEvent, JuvenileTraitResponseEvent.class);

		List<String> juvenileGangInfo = new ArrayList<String>();
		List<String> suicideHistoryTraitsInfo = new ArrayList<String>();
		List<String> assaultiveBehaviorTraitsInfo = new ArrayList<String>();
		List<String> runawayHistoryTraitsInfo = new ArrayList<String>();

		if (juvenileTraits != null && juvenileTraits.size() > 0) {
			Iterator<JuvenileTraitResponseEvent> juvTraits = juvenileTraits.iterator();
			Collection<String> traitsSpecify = new ArrayList<String>();
			while (juvTraits.hasNext()) {
				JuvenileTraitResponseEvent myTrait = juvTraits.next();
				String traitTypeId = myTrait.getTraitTypeId().trim();
				// if(traitTypeId.equalsIgnoreCase("DR02")){
				if(printDataBean.getChildConsideredDangerToSelf()!="YES"){ // added for U.S 35663
					if (traitTypeId.equalsIgnoreCase("CDS")	&& !myTrait.getStatusId().equalsIgnoreCase("FO")) {
						printDataBean.setChildConsideredDangerToSelf("YES");
					}else{
						printDataBean.setChildConsideredDangerToSelf("NO");
					}
				}
				// if(traitTypeId.equalsIgnoreCase("DR01")){
				if(printDataBean.getChildConsideredDangerToOthers()!="YES"){ // added for U.S 35663
					if (traitTypeId.equalsIgnoreCase("CDO")	&& !myTrait.getStatusId().equalsIgnoreCase("FO")) {
						printDataBean.setChildConsideredDangerToOthers("YES");
					}else{
						printDataBean.setChildConsideredDangerToOthers("NO");
					}
				}
				// if(traitTypeId.equalsIgnoreCase("DV14")){
				// TODO need to check for developmental and history of fires trait type. # U.S 35663 
				if(printDataBean.getHistoryOfSettingFires()!="YES"){ // added for U.S 35663
					if (traitTypeId.equalsIgnoreCase("FSA") || (myTrait.getTraitTypeName().equals(UIConstants.DEVELOPMENTAL) && traitTypeId.equalsIgnoreCase("HSF"))) { // added for U.S 35663
						printDataBean.setHistoryOfSettingFires("YES");
					}else{
						printDataBean.setHistoryOfSettingFires("NO");
					}
				}
				if (myTrait.getTraitTypeName().trim().equalsIgnoreCase("SPECIAL NEEDS")
						&& !myTrait.getStatusId().equalsIgnoreCase("FO")) {
					if (traitTypeId.equalsIgnoreCase("104")) {
						printDataBean.setSpecialNeedsMaternity("YES");
					} else if (traitTypeId.equalsIgnoreCase("106")) {
						printDataBean.setSpecialNeedsPreparationForAdultLiving("YES");
					} else {
						printDataBean.setSpecialNeedsOthers("YES");
					}
					//bug fix: 11244 starts
					//traitsSpecify.add(myTrait.getTraitTypeName() + " - " + myTrait.getTraitComments());
					traitsSpecify.add(myTrait.getTraitTypeDescription() + " - " +StringUtil.removeTimeStampFromComments(myTrait.getTraitComments()));
					//bug fix:11244 & 32220 ends
				}
				// if(traitTypeId.equalsIgnoreCase("16")){
				/*if(printDataBean.getSpecializedProgramReq()!="YES"){ // added for U.S 35663
					if (traitTypeId.equalsIgnoreCase("15") && !myTrait.getStatusId().equalsIgnoreCase("FO")) {
						printDataBean.setSpecializedProgramReq("YES");
						printDataBean.setSpecializedProgramSpecify("See Screening Profile section #2, Special program Needs....");
					}
				}*/
				
				

				if (traitTypeId.equals(UIConstants.JUVENILE_GANG_RELATED_TRAIT)) {
					StringBuffer sb = new StringBuffer();
					String traitComments=  StringUtil.removeTimeStampFromComments(myTrait.getTraitComments());
					sb.append(traitComments);
					if(!traitComments.isEmpty())
					sb.append("|");
					sb.append("Status: ");
					sb.append(myTrait.getStatus());
					juvenileGangInfo.add(sb.toString());
				}

				if (traitTypeId.equals(UIConstants.SUICIDE_IDEATION_TRAIT) || traitTypeId.equals(UIConstants.SUICIDE_ATTEMPTS_TRAIT)) {
					StringBuffer sb = new StringBuffer();
					String traitComments=  StringUtil.removeTimeStampFromComments(myTrait.getTraitComments());
					sb.append(traitComments);
					if(!traitComments.isEmpty())
					sb.append("|");
					sb.append("Status: ");
					sb.append(myTrait.getStatus());
					suicideHistoryTraitsInfo.add(sb.toString());
				}
				if (traitTypeId.equals(UIConstants.ASSAULTIVE_BEHAVIOR_TOWARD_FAMILY_MEMBERS_TRAIT)|| traitTypeId.equals(UIConstants.ASSAULTIVE_BEHAVIOR_TOWARD_NON_FAMILY_MEMBERS_TRAIT)) {
					StringBuffer sb = new StringBuffer();
					String traitComments=  StringUtil.removeTimeStampFromComments(myTrait.getTraitComments());
					sb.append(traitComments);
					if(!traitComments.isEmpty())
					sb.append("|");
					sb.append("Status: ");
					sb.append(myTrait.getStatus());
					assaultiveBehaviorTraitsInfo.add(sb.toString());
				}
				if (traitTypeId.equals(UIConstants.ESCAPE_OR_RUNAWAY_TENDANCY_TRAIT)) {
					StringBuffer sb = new StringBuffer();
					String traitComments=  StringUtil.removeTimeStampFromComments(myTrait.getTraitComments());
					sb.append(traitComments);
					if(!traitComments.isEmpty())
						sb.append("|");
					sb.append("Status: ");
					sb.append(myTrait.getStatus());
					runawayHistoryTraitsInfo.add(sb.toString());
				}
				//ER changes 11454
				if(myTrait.getTraitTypeName()!=null){
					if (myTrait.getTraitTypeName().equals(UIConstants.MEDICAL_ISSUES)&& myTrait.getTraitTypeId().equals("PYD")) {
						printDataBean.setHasMedicalIssue("YES");
					}
				}
				/**
				 * ADD-Adoptive parents divorced
				 * ADM-Adoptive parents married
				 * ADS-Adoptive parents Separated
				 * ER changes 13442
				 */
				if (juvenile.isAdopted()) {
					String maritalStatus[]={"ADD","ADM","ADS"};
					List<String> maritalStatusList=Arrays.asList(maritalStatus);
					if(maritalStatusList.contains(traitTypeId)){
						printDataBean.setAdoptedParentsMaritalStatus(myTrait.getTraitTypeDescription());
					}
				}//ER changes 13442 ends
			}
			if (traitsSpecify != null && traitsSpecify.size() > 0) {
				printDataBean.setSpecialNeedsSpecify(traitsSpecify);
			}

			if (!juvenileGangInfo.isEmpty()) {
				printDataBean.setJuvenileGangInfo(juvenileGangInfo);
				printDataBean.setChildAdmitGangAffiliation("YES");
			}
			if (!suicideHistoryTraitsInfo.isEmpty()) {
				printDataBean.setSuicideHistoryTraitsInfo(suicideHistoryTraitsInfo);
			}
			if (!assaultiveBehaviorTraitsInfo.isEmpty()) {
				printDataBean.setAssaultiveBehaviorTraitsInfo(assaultiveBehaviorTraitsInfo);
			}
			if (!runawayHistoryTraitsInfo.isEmpty()) {
				printDataBean.setRunawayHistoryTraitsInfo(runawayHistoryTraitsInfo);
			}

		}

		// Substance Abuse History
		GetJuvenileDrugsEvent drugsEvent = (GetJuvenileDrugsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEDRUGS);
		drugsEvent.setJuvenileNum(juvenileNum);
		replyEvent = MessageUtil.postRequest(drugsEvent);
		Collection<JuvenileDrugsResponseEvent> drugs = MessageUtil.compositeToCollection(replyEvent,JuvenileDrugsResponseEvent.class);

		if (drugs != null && drugs.size() > 0) {
			printDataBean.setSubstanceAbuseHistory("YES");
			Iterator<JuvenileDrugsResponseEvent> juvDrugs = drugs.iterator();
			Collection<String> drugInfo = new ArrayList<String>();
			while (juvDrugs.hasNext()) {
				JuvenileDrugsResponseEvent dResp = (JuvenileDrugsResponseEvent) juvDrugs.next();
				drugInfo.add(dResp.getDrugType() + " - Degree # " + dResp.getDegree());
			}
			if (drugInfo != null && drugInfo.size() > 0) {
				printDataBean.setDrugTypeAndDegree(drugInfo);
			}
		}

		// History of Abuse/ Neglect of Referred Child
		GetJuvenileAbuseListEvent abuseListEvent = (GetJuvenileAbuseListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEABUSELIST);
		abuseListEvent.setJuvenileNum(juvenileNum);
		//abuseListEvent.setCasefileId(casefileId); # bug fix 35685
		replyEvent = MessageUtil.postRequest(abuseListEvent);
		Collection<JuvenileAbuseResponseEvent> abuses = MessageUtil.compositeToCollection(replyEvent, JuvenileAbuseResponseEvent.class);
		if (abuses != null && abuses.size() > 0) {
			printDataBean.setAbuseNeglectHistory("YES");
			Iterator<JuvenileAbuseResponseEvent> juvAbuses = abuses.iterator();
			Collection<String> abuseInfo = new ArrayList<String>();
			Collection<JuvenileAbuseResponseEvent> abuseRespEvtList = new ArrayList<JuvenileAbuseResponseEvent>();
			while (juvAbuses.hasNext()) {
				JuvenileAbuseResponseEvent abuseResp = (JuvenileAbuseResponseEvent) juvAbuses.next();
				if(abuseResp!=null ){
					//bug fix 27324 starts
					if(abuseResp.getAbuseDetails()!=null && !abuseResp.getAbuseDetails().isEmpty() && abuseResp.getAbuseDetails().contains("[")){
						String abuseDetails = StringUtils.split(abuseResp.getAbuseDetails(), '[')[0];
						abuseResp.setAbuseDetails(abuseDetails);
					}
					if(abuseResp.getAbuseEvent()!=null && !abuseResp.getAbuseEvent().isEmpty() && abuseResp.getAbuseEvent().contains("[")){
						String abuseEvent = StringUtils.split(abuseResp.getAbuseEvent(), '[')[0];
						abuseResp.setAbuseEvent(abuseEvent);
					}
					//bug fix 27324 ends
					abuseRespEvtList.add(abuseResp);
					if (abuseResp.getTraitTypeId().trim().equalsIgnoreCase("ABN")) {
						printDataBean.setAbandonment("YES");
					}
					abuseInfo.add(abuseResp.getTraitTypeName() + " - Degree # "	+ abuseResp.getAbuseLevelDescription());
				}
			}
			printDataBean.setJuvenileAbuseResponseEvent(abuseRespEvtList); //bug fix 27324
			if (abuseInfo != null && abuseInfo.size() > 0) {
				printDataBean.setAbuseTypeAndDegree(abuseInfo);
			}
		}

		
		// Education IQ score section
		GetMentalHealthIQResultsEvent iqEvent = (GetMentalHealthIQResultsEvent) EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHIQRESULTS);
		iqEvent.setJuvenileNum(juvenileNum);
		replyEvent = MessageUtil.postRequest(iqEvent);
		Collection<IQTestResponseEvent> collIQRecords = MessageUtil.compositeToCollection(replyEvent, IQTestResponseEvent.class);
		if (collIQRecords != null && collIQRecords.size() > 0) {
			Collections.sort((List<IQTestResponseEvent>) collIQRecords);
			Iterator<IQTestResponseEvent> iterIQ = collIQRecords.iterator();
			IQTestResponseEvent iqResp = iterIQ.next();
			if (iqResp.getFullScore() != null && !iqResp.getFullScore().equals("")) {
				printDataBean.setFullScore(iqResp.getFullScore());
			} else {
				printDataBean.setFullScore("Unknown");
			}
			if (iqResp.getPerformanceScore() != null && !iqResp.getPerformanceScore().equals("")) {
				printDataBean.setPerformanceScore(iqResp.getPerformanceScore());
			} else {
				printDataBean.setPerformanceScore("Unknown");
			}
			if (iqResp.getVerbalScore() != null && !iqResp.getVerbalScore().equals("")) {
				printDataBean.setVerbalScore(iqResp.getVerbalScore());
			} else {
				printDataBean.setVerbalScore("Unknown");
			}
			printDataBean.setTestDate(iqResp.getTestDate());
			printDataBean.setTestName(iqResp.getTestName());
		}

		// Physical Health/ Disabilities

		GetJuvenileMedicalHealthIssuesListEvent healthIssueEvent = (GetJuvenileMedicalHealthIssuesListEvent) EventFactory
				.getInstance(JuvenileControllerServiceNames.GETJUVENILEMEDICALHEALTHISSUESLIST);
		healthIssueEvent.setJuvenileNum(juvenileNum);
		replyEvent = MessageUtil.postRequest(healthIssueEvent);
		Collection<JuvenileHealthIssueResponseEvent> healthIssueList = MessageUtil.compositeToCollection(replyEvent, JuvenileHealthIssueResponseEvent.class);
		if (healthIssueList != null && healthIssueList.size() > 0) {
			Iterator<JuvenileHealthIssueResponseEvent> iterHealthIssueList = healthIssueList.iterator();
			Collection<JuvenileHealthIssueResponseEvent> issues = new ArrayList<JuvenileHealthIssueResponseEvent>();
			while (iterHealthIssueList.hasNext()) {
				JuvenileHealthIssueResponseEvent resp = (JuvenileHealthIssueResponseEvent) iterHealthIssueList.next();
				String issueCode = resp.getIssueId();
				resp.setIssueDesc(SimpleCodeTableHelper.getDescrByCode("HEALTH_ISSUE", issueCode));
				resp.setConditionLevelDesc(SimpleCodeTableHelper.getDescrByCode("HEALTH_CONDITION_LEVEL", resp.getConditionLevelId()));
				resp.setConditionSeverityDesc(SimpleCodeTableHelper.getDescrByCode("HEALTH_CONDITION_SEVERITY", resp.getConditionSeverityId()));
				issues.add(resp);
			}
			Collections.sort((List<JuvenileHealthIssueResponseEvent>) issues);
			printDataBean.setHealthIssues(issues);
		}

		// Medication list for physical health/ disablities section

		GetJuvenileMedicalMedicationListEvent medicationEvent = (GetJuvenileMedicalMedicationListEvent) EventFactory
				.getInstance(JuvenileControllerServiceNames.GETJUVENILEMEDICALMEDICATIONLIST);
		medicationEvent.setJuvenileNum(juvenileNum);
		replyEvent = MessageUtil.postRequest(medicationEvent);
		Collection<JuvenileMedicationResponseEvent> medications = MessageUtil.compositeToCollection(replyEvent, JuvenileMedicationResponseEvent.class);
		Collection<String> medicationNames = new ArrayList<String>();
		if (medications != null && medications.size() > 0) {
			Iterator<JuvenileMedicationResponseEvent> medicationsIter = medications.iterator();
			while (medicationsIter.hasNext()) {
				JuvenileMedicationResponseEvent resp = (JuvenileMedicationResponseEvent) medicationsIter.next();
				GetJuvenileMedicationTypeCodesEvent medicationCodesEvent = (GetJuvenileMedicationTypeCodesEvent) EventFactory
						.getInstance(CodeTableControllerServiceNames.GETJUVENILEMEDICATIONTYPECODES);
				medicationCodesEvent.setMedicationTypeId(resp.getMedicationTypeId());
				medicationCodesEvent.setFlagfind(true);
				replyEvent = MessageUtil.postRequest(medicationCodesEvent);
				Collection<JuvenileMedicationTypeResponseEvent> medicationCodes = MessageUtil.compositeToCollection(replyEvent, JuvenileMedicationTypeResponseEvent.class);
				if (medicationCodes != null && medicationCodes.size() > 0) {
					Iterator<JuvenileMedicationTypeResponseEvent> codesIter = medicationCodes.iterator();
					while (codesIter.hasNext()) {
						JuvenileMedicationTypeResponseEvent codeRespEvent = codesIter.next();
						if (codeRespEvent.getMedicationTypeId().equals(resp.getMedicationTypeId())) {
							medicationNames.add(codeRespEvent.getTradeName()); //ER 11008 changes.
						}
					}
				}
			}
		}
		printDataBean.setMedications(medicationNames);
		// mental Health section
		GetMentalHealthTestingResultsEvent listEvent = (GetMentalHealthTestingResultsEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHTESTINGRESULTS);
		listEvent.setJuvenileNum(juvenileNum);
		replyEvent = MessageUtil.postRequest(listEvent);
		Collection<TestingSessionResponseEvent> testingSession = MessageUtil.compositeToCollection(replyEvent, TestingSessionResponseEvent.class);
		if (testingSession != null && testingSession.size() > 0) {
			Collections.sort((List<TestingSessionResponseEvent>) testingSession);
			Iterator<TestingSessionResponseEvent> testingSessionIter = testingSession.iterator();
			while (testingSessionIter.hasNext()) {
				TestingSessionResponseEvent testingSessionResp = (TestingSessionResponseEvent) testingSessionIter.next();
				if (testingSessionResp.getPsychiatricAssessment() != null
						&& testingSessionResp.getPsychiatricAssessment().equalsIgnoreCase("YES")) {
					printDataBean.setTestingSessionDate(testingSessionResp.getSessionDate());
					break;
				}
				if (testingSessionResp.getPsychologicalAssessment() != null
						&& testingSessionResp.getPsychologicalAssessment().equalsIgnoreCase("YES")) {
					printDataBean.setTestingSessionDate(testingSessionResp.getSessionDate());
					break;
				}
			}
		}

		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		IUserInfo userInfo = securityManager.getIUserInfo();
		if (userInfo != null) {
			String fName = userInfo.getFirstName();
			String lName = userInfo.getLastName();
			if (fName != null && lName != null) {
				printDataBean.setUserCompletingCommonAppFormName(fName + " " + lName);
			}
		}

		return printDataBean;
	}
	

	/**
	 * Bug Fix for 11238
	 * @param gradeLevel
	 * @return completed grade level
	 */
	public static String getCompletedGradelevel(String gradeLevel) {
		String completedGradeLevel=null;
		String grade = gradeLevel.substring(0, gradeLevel.length()-2);
		String gradeSuffix = gradeLevel.substring(gradeLevel.length()-2, gradeLevel.length());
		
		if (gradeLevel.equalsIgnoreCase(UIConstants.KINDERGARTEN)) {
			completedGradeLevel=UIConstants.NA;
		}
		else if (grade.equals("1")) {
			completedGradeLevel = UIConstants.KINDERGARTEN;
		} else if (gradeLevel.equalsIgnoreCase(UIConstants.COLLEGE)) {
			completedGradeLevel = "12TH";
		} else {
			if (grade.equals("4")) {
				gradeSuffix = "RD";
			} else if (grade.equals("3")) {
				gradeSuffix = "ND";
			} else if (grade.equals("2")) {
				gradeSuffix = "ST";
			}
			completedGradeLevel = String.valueOf((Integer.valueOf(grade)- 1))+ gradeSuffix;
		}
		return completedGradeLevel;
	}
	
	
	/**
	 * getInsuranceApplicableToChild
	 * @param juvenileNum
	 * @param guardianList
	 * @return
	 */
	public static List<JuvenileMemberForm.MemberInsurance> getInsuranceApplicableToChild(String juvenileNum, List<FamilyInformationTO> guardianList) {
		List<JuvenileMemberForm.MemberInsurance> insuranceList = new ArrayList<JuvenileMemberForm.MemberInsurance>();
		//Used for adding uniqueInsuranceTypes.
		Map<String,JuvenileMemberForm.MemberInsurance> uniqueInsuranceType = new HashMap<String,JuvenileMemberForm.MemberInsurance>();

		for (Iterator<FamilyInformationTO> guardianIter = guardianList.iterator(); guardianIter.hasNext();) {
			FamilyInformationTO guardian = (FamilyInformationTO) guardianIter.next();

			GetFamilyMemberInsuranceEvent getFamilyMemberInsuranceEvent = (GetFamilyMemberInsuranceEvent) EventFactory
					.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERINSURANCE);
			getFamilyMemberInsuranceEvent.setFamilyMemberId(guardian.getFamilyConstellationMemberId());
			CompositeResponse response = postRequestEvent(getFamilyMemberInsuranceEvent);
			Collection<FamilyMemberInsuranceResponseEvent> guardianInsuranceList = MessageUtil.compositeToCollection(response, FamilyMemberInsuranceResponseEvent.class);
			Collections.sort((List<FamilyMemberInsuranceResponseEvent>) guardianInsuranceList);
			if (guardianInsuranceList != null && guardianInsuranceList.size() > 0) {
				for (Iterator<FamilyMemberInsuranceResponseEvent> guardianInsuranceIter = guardianInsuranceList.iterator(); guardianInsuranceIter.hasNext();) {
					FamilyMemberInsuranceResponseEvent guardianInsurance =  guardianInsuranceIter.next();
					String insuranceType = CodeHelper.getCodeDescription(PDCodeTableConstants.INSURANCE_TYPE, guardianInsurance.getTypeId());
					JuvenileMemberForm.MemberInsurance memberForm=null;
					if(!uniqueInsuranceType.containsKey(insuranceType))
					{
						memberForm = new JuvenileMemberForm().getCurrentInsurance();
						memberForm.setInsuranceType(insuranceType);
						memberForm.setInsuranceCarrier(guardianInsurance.getCarrier());
						memberForm.setPolicyNumber(guardianInsurance.getPolicyNum());
						insuranceList.add(memberForm);
						uniqueInsuranceType.put(insuranceType, memberForm);
					}
				}
			}
		}

		GetJuvenileInsuranceEvent getJuvenileInsuranceEvent = new GetJuvenileInsuranceEvent();
		getJuvenileInsuranceEvent.setJuvenileNum(juvenileNum);
		CompositeResponse resp = postRequestEvent(getJuvenileInsuranceEvent);
		Collection<JuvenileInsuranceResponseEvent> juvenileInsuranceList = MessageUtil.compositeToCollection(resp, JuvenileInsuranceResponseEvent.class);
		Collections.sort((List<JuvenileInsuranceResponseEvent>) juvenileInsuranceList);
		if (juvenileInsuranceList != null && juvenileInsuranceList.size() > 0) {
			for (Iterator<JuvenileInsuranceResponseEvent> juvenileInsuranceIter = juvenileInsuranceList.iterator(); juvenileInsuranceIter.hasNext();) {
				JuvenileInsuranceResponseEvent juvenileInsurance =  juvenileInsuranceIter.next();
				String insuranceType = CodeHelper.getCodeDescription(PDCodeTableConstants.INSURANCE_TYPE, juvenileInsurance.getTypeId());
				JuvenileMemberForm.MemberInsurance memberForm=null;
				if(!uniqueInsuranceType.containsKey(insuranceType))
				{
					memberForm = new JuvenileMemberForm().getCurrentInsurance();
					memberForm.setInsuranceType(insuranceType);
					memberForm.setInsuranceCarrier(juvenileInsurance.getCarrier());
					memberForm.setPolicyNumber(juvenileInsurance.getPolicyNum());
					insuranceList.add(memberForm);
					uniqueInsuranceType.put(insuranceType, memberForm);
				}
			}
		}
		return insuranceList;
	}
	
	/**
	 * getOffenseInformation
	 * @param myReportBean
	 * @param juvenileNum
	 * @param casefileId
	 * @param myCommonAppForm
	 */
	public static void getOffenseInformation(CommonAppReportPrintBean myReportBean,String juvenileNum, String casefileId,CommonAppForm myCommonAppForm) {
		Collection<JuvenileProfileReferralListResponseEvent> referrals = UIJuvenileCaseworkHelper.fetchJuvenileProfileReferralsList(juvenileNum);
		int referralCount =0;
		List<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
		
		//Sort by referralNumber changes for ER 13220 starts
		sortFields.add(new ReverseComparator(new BeanComparator("referralNumber")));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort((List<JuvenileProfileReferralListResponseEvent>)referrals, multiSort);
		//Sort by referralNumber changes for ER 13220 ends
		
		List<JJSReferralBean> referralPrintBeans = new ArrayList<JJSReferralBean>();
		int countNumberOfRunaways = 0;
		//bug 31958 fix starts
		int totalNumberOfAdjudications =0;
		String dispCodes[] = {"CR","IA","RD"}; 	
		Set<Date> uniqueDispDate = new HashSet<Date>();
		//bug 31958 fix ends 
		
		//added for user-story 32111
		//Get the transferred offenses.
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffensesList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
		
		//Code Changes for 12106 
	//	String[] offenseCode = {"TRNSIN","TRNDSP","ISCOIN"}; // modified for task #36517
	//	List<String> offenseCodeList = new ArrayList<String>(Arrays.asList(offenseCode)); modified for task #36517
		
		HashMap<Date,String> referralsMap = new HashMap<Date,String> ();
		// HashMap adjudicationMap = new HashMap();
		// HashMap cinsAdjudicationMap = new HashMap();
		for (Iterator<JuvenileProfileReferralListResponseEvent> referralIter = referrals.iterator(); referralIter.hasNext();) {
			JJSReferralBean referralBean = new JJSReferralBean();
			JuvenileProfileReferralListResponseEvent referral = referralIter.next();
			//Code changes for 12106.#36517 uses severity subtype T
			Date referralDate = referral.getReferralDate();
			if (referralDate != null) {
				String referralResponse = referralsMap.get(referralDate);
				if (referralResponse == null) { //avoid duplicates.
					Collection<JJSOffense> offenses = referral.getOffenses();
					if(offenses!=null && offenses.size()==1){
							JJSOffense offense = offenses.iterator().next();
							if(offense!=null && !referralsMap.containsKey(referralDate)){
									if(!offense.getSeverity().equals("0") || (offense.getOffenseCode()!=null && offense.getOffenseCode().getSeveritySubtype()!=null && offense.getOffenseCode().getSeveritySubtype().equals("T"))){
										referralsMap.put(referralDate, offense.getReferralNum());
										referralCount++;
									}
								}
					}else if(offenses!=null && offenses.size()>1){ // for more than one offenses.
						for(Iterator<JJSOffense> offensesIter = offenses.iterator(); offensesIter.hasNext();) {
							JJSOffense offense = offensesIter.next();
							if(offense!=null && !referralsMap.containsKey(referralDate)){
								if((!offense.getSeverity().equals("0") ||  offense.getOffenseCode().getSeveritySubtype().equals("T"))){
									referralsMap.put(referralDate, offense.getReferralNum());
									referralCount++;
								}
							}
						}
					}
				}
			}

			referralBean.setReferralId(referral.getReferralNumber()); //referral number 
			referralBean.setReferralDate(referralDate);//referral date


			GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
			getOffenses.setJuvenileNum(juvenileNum);
			getOffenses.setReferralNum(referral.getReferralNumber());
			CompositeResponse replyEvent = MessageUtil.postRequest(getOffenses);

			Collection<JJSOffenseResponseEvent> offenses = MessageUtil.compositeToCollection(replyEvent,JJSOffenseResponseEvent.class);
			List<JJSOffenseResponseEvent> filteredOffenses = new ArrayList<JJSOffenseResponseEvent>();
			Collections.sort((List<JJSOffenseResponseEvent>) offenses);
			
			JuvenileOffenseCodeResponseEvent juvOffenseCode =null;
			
			for (Iterator<JJSOffenseResponseEvent> offenseIter = offenses.iterator(); offenseIter.hasNext();) {
				JJSOffenseResponseEvent offense = offenseIter.next();
				if (offense.getOffenseReportGroup() != null	&& offense.getOffenseReportGroup().equals("21")) {
					countNumberOfRunaways++;
				}
				
				// added for user-story 32111
				GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
				jocEvent.setAlphaCode(offense.getOffenseCode());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(jocEvent);
				CompositeResponse response = (CompositeResponse)dispatch.getReply();
				
				juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
				
				//added for userstory #32111
				if(juvOffenseCode!=null && referral.getReferralNumber()!=null){
					//create an offense object and populate the information.
					 JJSOffenseResponseEvent offenseResp = new JJSOffenseResponseEvent();
					 offenseResp.setCatagory(juvOffenseCode.getCategory());
					 offenseResp.setOffenseCode(juvOffenseCode.getOffenseCode()); //#bug 31346    
					 offenseResp.setOffenseDescription(juvOffenseCode.getShortDescription());
					 offenseResp.setCitationCode(juvOffenseCode.getCitationCode());
					 offenseResp.setCitationSource(juvOffenseCode.getCitationSource());
					 offenseResp.setReferralNum(referral.getReferralNumber());
					 filteredOffenses.add(offenseResp);
				}

				//added for userstory #32111
				
				if(offense!=null && offense.getCatagory()!=null && offense.getCatagory().equals("AC"))
					continue;
				
				filteredOffenses.add(offense);
			}
			referralBean.setOffenses((List<JJSOffenseResponseEvent>) filteredOffenses);
			Collection<JJSOffenseResponseEvent> dispositions = buildDispositionList(referral.getReferralNumber(), juvenileNum,filteredOffenses,transferredOffensesList,juvOffenseCode);
			//bug 31958 fix starts
			List<String> dispCodesList=new ArrayList<String>(Arrays.asList(dispCodes));
			if(dispositions!=null){
				Iterator<JJSOffenseResponseEvent> dispItr =  dispositions.iterator();
				while (dispItr.hasNext()) {
					JJSOffenseResponseEvent offense = dispItr.next();
					if (offense!=null && !dispCodesList.contains(offense.getDispositionCode()) && !uniqueDispDate.contains(offense.getDispositionDate())) {
						uniqueDispDate.add(offense.getDispositionDate());
						totalNumberOfAdjudications++;
					}
				}
			}
			//bug 31958 fix ends
			// LevelDegree, Penal Code (Citation Code + Citation Source),
			// offense (allegation) **11029** changes includes disposition type and date.
			referralBean.setDispositions((List<JJSOffenseResponseEvent>) dispositions);
		//	if(!referralBean.getOffenses().isEmpty()) //referral count fix:17333  modified for task #36517
		//	referralCount++; modified for task #36517
			
			referralPrintBeans.add(referralBean);
		}

		setJuvenileJusticeHistoryData(myReportBean, juvenileNum);
		// return referralPrintBeans;
		myReportBean.setReferrals(referralPrintBeans);
		// myReportBean.setCountAdjudicationsTotal(adjudicationMap.size());
		// myReportBean.setCountCINSAdjudication(cinsAdjudicationMap.size());
		myReportBean.setTotalNumberOfAdjudications(totalNumberOfAdjudications); ////bug 31958 fix 
		myReportBean.setCountReferrals(referralCount);

		myReportBean.setNoRunawaysFromHome(countNumberOfRunaways);

		// This is to get the current offense from controlling referral
		String controllingReferralId = getControllingReferralId(casefileId);
		
		//#31346 Starts  
		if(controllingReferralId==null || controllingReferralId.isEmpty()){
			// pick from the common app court order page selected controlling referral.
			controllingReferralId = myCommonAppForm.getSelectedControllingReferral();
		}
		//#31346 ends  
		if (controllingReferralId != null && controllingReferralId.length() > 0) {
			StringTokenizer st = new StringTokenizer(controllingReferralId,	" -");
			controllingReferralId = st.nextToken();
		}
		myReportBean.setCurrentOffense(getCurrentPetitionAllegation(referralPrintBeans, controllingReferralId));
		myReportBean.setcontrlrefID(controllingReferralId);  // Controlling refernece Number * 35996*

	}
	
	
	/** Added for US 155588 
	 * getOffenseInformation
	 * @param myReportBean
	 * @param juvenileNum
	 * @param casefileId
	 * @param myCommonAppForm
	 */
	public static void getOffenseInformationNew(CommonAppReportPrintBean myReportBean,String juvenileNum, String casefileId,CommonAppForm myCommonAppForm) 
	{
		Collection<JuvenileProfileReferralListResponseEvent> referrals = UIJuvenileCaseworkHelper.fetchJuvenileProfileReferralsList(juvenileNum);
		int referralCount =0;
		List<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
		
		//Sort by referralNumber changes for ER 13220 starts
		sortFields.add(new ReverseComparator(new BeanComparator("referralNumber")));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort((List<JuvenileProfileReferralListResponseEvent>)referrals, multiSort);
		//Sort by referralNumber changes for ER 13220 ends
		
		List<JJSReferralBean> referralPrintBeans = new ArrayList<JJSReferralBean>();
		int countNumberOfRunaways = 0;
		//bug 31958 fix starts
		int totalNumberOfAdjudications =0;
		String dispCodes[] = {"CR","IA","RD"}; 	
		Set<Date> uniqueDispDate = new HashSet<Date>();
		//bug 31958 fix ends 
		
		//added for user-story 32111
		//Get the transferred offenses.
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffensesList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
		
		HashMap<Date,String> referralsMap = new HashMap<Date,String> ();
		for (Iterator<JuvenileProfileReferralListResponseEvent> referralIter = referrals.iterator(); referralIter.hasNext();) {
			JJSReferralBean referralBean = new JJSReferralBean();
			JuvenileProfileReferralListResponseEvent referral = referralIter.next();
			//Code changes for 12106.#36517 uses severity subtype T
			Date referralDate = referral.getReferralDate();
			if (referralDate != null) {
				String referralResponse = referralsMap.get(referralDate);
				if (referralResponse == null) { //avoid duplicates.
					Collection<JJSOffense> offenses = referral.getOffenses();
					if(offenses!=null && offenses.size()==1){
							JJSOffense offense = offenses.iterator().next();
							if(offense!=null && !referralsMap.containsKey(referralDate)){
									if(!offense.getSeverity().equals("0") || (offense.getOffenseCode()!=null && offense.getOffenseCode().getSeveritySubtype()!=null && offense.getOffenseCode().getSeveritySubtype().equals("T"))){
										referralsMap.put(referralDate, offense.getReferralNum());
										referralCount++;
									}
								}
					}else if(offenses!=null && offenses.size()>1){ // for more than one offenses.
						for(Iterator<JJSOffense> offensesIter = offenses.iterator(); offensesIter.hasNext();) {
							JJSOffense offense = offensesIter.next();
							if(offense!=null && !referralsMap.containsKey(referralDate)){
								if((!offense.getSeverity().equals("0") ||  offense.getOffenseCode().getSeveritySubtype().equals("T"))){
									referralsMap.put(referralDate, offense.getReferralNum());
									referralCount++;
								}
							}
						}
					}
				}
			}

			referralBean.setReferralId(referral.getReferralNumber()); //referral number 
			referralBean.setReferralDate(referralDate);//referral date
			GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
			getOffenses.setJuvenileNum(juvenileNum);
			getOffenses.setReferralNum(referral.getReferralNumber());
			CompositeResponse replyEvent = MessageUtil.postRequest(getOffenses);

			Collection<JJSOffenseResponseEvent> offenses = MessageUtil.compositeToCollection(replyEvent,JJSOffenseResponseEvent.class);
			List<JJSOffenseResponseEvent> filteredOffenses = new ArrayList<JJSOffenseResponseEvent>();
			Collections.sort((List<JJSOffenseResponseEvent>) offenses);
			
			JuvenileOffenseCodeResponseEvent juvOffenseCode =null;
			
			for (Iterator<JJSOffenseResponseEvent> offenseIter = offenses.iterator(); offenseIter.hasNext();) {
				JJSOffenseResponseEvent offense = offenseIter.next();
				if (offense.getOffenseReportGroup() != null	&& offense.getOffenseReportGroup().equals("21")) {
					countNumberOfRunaways++;
				}
				GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
				jocEvent.setAlphaCode(offense.getOffenseCode());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(jocEvent);
				CompositeResponse response = (CompositeResponse)dispatch.getReply();
				
				juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
				
				if(juvOffenseCode!=null && referral.getReferralNumber()!=null){
				    if(offense!=null && offense.getCatagory()!=null && (offense.getCatagory().equals("AC") && juvOffenseCode.getSeveritySubtype()!= null && !juvOffenseCode.getSeveritySubtype().equalsIgnoreCase("T"))){
					continue;
				    }else
				    {
					//create an offense object and populate the information.
					 JJSOffenseResponseEvent offenseResp = new JJSOffenseResponseEvent();
					 offenseResp.setCatagory(juvOffenseCode.getCategory());
					 offenseResp.setOffenseCode(juvOffenseCode.getOffenseCode()); //#bug 31346    
					 offenseResp.setOffenseDescription(juvOffenseCode.getShortDescription());
					 offenseResp.setCitationCode(juvOffenseCode.getCitationCode());
					 offenseResp.setCitationSource(juvOffenseCode.getCitationSource());
					 offenseResp.setReferralNum(referral.getReferralNumber());
					 offenseResp.setSequenceNum(offense.getSequenceNum()); //added for BUG 156375
					 filteredOffenses.add(offenseResp);
				}
				}
			}
			referralBean.setOffenses((List<JJSOffenseResponseEvent>) filteredOffenses);
			    Collection<JJSOffenseResponseEvent> dispositions = buildDispositionList( referral.getReferralNumber(), juvenileNum,filteredOffenses,transferredOffensesList,juvOffenseCode);
			
			//bug 31958 fix starts
			List<String> dispCodesList=new ArrayList<String>(Arrays.asList(dispCodes));
			if(dispositions!=null){
				Iterator<JJSOffenseResponseEvent> dispItr =  dispositions.iterator();
				while (dispItr.hasNext()) {
					JJSOffenseResponseEvent offense = dispItr.next();
					if (offense!=null && !dispCodesList.contains(offense.getDispositionCode()) && !uniqueDispDate.contains(offense.getDispositionDate())) {
						uniqueDispDate.add(offense.getDispositionDate());
						totalNumberOfAdjudications++;
					}
				}
			}
			//bug 31958 fix ends
			referralBean.setDispositions((List<JJSOffenseResponseEvent>) dispositions);
			referralPrintBeans.add(referralBean);
		}

		setJuvenileJusticeHistoryData(myReportBean, juvenileNum);
		// return referralPrintBeans;
		myReportBean.setReferrals(referralPrintBeans);
		myReportBean.setTotalNumberOfAdjudications(totalNumberOfAdjudications); ////bug 31958 fix 
		myReportBean.setCountReferrals(referralCount);
		myReportBean.setNoRunawaysFromHome(countNumberOfRunaways);

		// This is to get the current offense from controlling referral
		String controllingReferralId = getControllingReferralId(casefileId);
		
		//#31346 Starts  
		if(controllingReferralId==null || controllingReferralId.isEmpty()){
			// pick from the common app court order page selected controlling referral.
			controllingReferralId = myCommonAppForm.getSelectedControllingReferral();
		}
		//#31346 ends  
		if (controllingReferralId != null && controllingReferralId.length() > 0) {
			StringTokenizer st = new StringTokenizer(controllingReferralId,	" -");
			controllingReferralId = st.nextToken();
		}
		//myReportBean.setCurrentOffense(getCurrentPetitionAllegation(referralPrintBeans, controllingReferralId)); //commented for 155588
		myReportBean.setCurrentOffense(getCurrentOffense(juvenileNum)); // Added for US 155588 
		myReportBean.setcontrlrefID(controllingReferralId);  // Controlling refernece Number * 35996*
	}
	
	/**
	 * Added for 11046
	 * @param questionsAnswers
	 * @param printDataBean
	 */
	public static void addToPrintDataBean(Collection<QuestionGroup> questionsAnswers,CommonAppReportPrintBean printDataBean){
		Iterator<QuestionGroup> iGroup = questionsAnswers.iterator();

		QuestionGroup questionGroup = new QuestionGroup();
		Map<String,String> questionMap = new HashMap<String,String>();
		Map<String,String>  answerMap = new HashMap<String,String> ();

		Iterator<Question> questionsList = null;
		Question question = null;
		String questionID = null;
		String answerValue = null;
		String questionText = null;
	

		while (iGroup.hasNext()) {
			questionGroup =  iGroup.next();

			questionsList = questionGroup.getQuestions().iterator();

			while (questionsList.hasNext()) {
				question = questionsList.next();
				questionID = question.getQuestionId();
				questionText = question.getQuestionText();
				

				questionMap.put(questionID, question.getQuestionText());
				answerValue = question.getResponse();
				
				if (answerValue == null) {
					answerValue = "";
				}
				answerMap.put(questionID, answerValue);
				printDataBean.addAnswer(questionID, answerValue);
			}
		}
	}
	
	
	/**
	 * Pass in List of JJSReferralBean & controlling referral number
	 * 
	 */
	public static String getCurrentPetitionAllegation(List<JJSReferralBean> referrals,	String controllingReferralId) {
		if (referrals == null || referrals.size() < 1 || controllingReferralId == null || controllingReferralId.length() < 1) {
			return "";
		}
		//bug fix #31346 starts  
		StringBuffer currentOffense = new StringBuffer();
		for (Iterator<JJSReferralBean> referralsIter = referrals.iterator(); referralsIter.hasNext();) {
			JJSReferralBean referral =  referralsIter.next();
			if (referral.getReferralId().equals(controllingReferralId)) {
				List<JJSOffenseResponseEvent> dispositions = referral.getDispositions();
				if (dispositions != null && dispositions.size() > 0) {
					for (Iterator<JJSOffenseResponseEvent> dispositionsIter = dispositions.iterator(); dispositionsIter.hasNext();) {
						JJSOffenseResponseEvent offense =  dispositionsIter.next();
						if (offense.getOffenseCodeId() != null && offense.getOffenseCodeId().length() > 0) {
							currentOffense.append(offense.getOffenseDescription());
						}
					}
				}
			}
		}
		//bug fix #31346 ends  
		return currentOffense.toString();
	}
	
	
	/** Added for US 155588 
	 * @param juvenileNum
	 * @return
	 */
	
	public static String getCurrentOffense(String juvenileNum){
	    //the highest JJSMSPETITION_ID for that Referral 
	    GetJJSPetitionsByJuvNumEvent petitionEvent = (GetJJSPetitionsByJuvNumEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONSBYJUVNUM);
		petitionEvent.setJuvenileNum(juvenileNum);
		  IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		//petitionEvent.setReferralNum(reffNum); //need ALL petitions for the juvNum
		dispatch.postEvent(petitionEvent);
		CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
		List<PetitionResponseEvent> petResponses = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
		String currentOffenseCode = new String();
		if(petResponses != null && !petResponses.isEmpty()){
		Collections.sort((List<PetitionResponseEvent>) petResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		    @Override
			public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
			{ 
			if(evt1.getOID()!= null && evt2.getOID() != null)
			return evt1.getOID().compareTo(evt2.getOID());
			else return -1;
			}
		}));
		PetitionResponseEvent petition = petResponses.iterator().next();
		currentOffenseCode = petition.getOffenseCodeId();
		return currentOffenseCode;
		}
		else{
		    //if no entries for the juvenile in JJSMSPETITION table get the highest JJS_MS_OFFENSE_ID of a given referral from Offense table 
		    GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
			getOffenses.setJuvenileNum(juvenileNum);
			//getOffenses.setReferralNum(referral.getReferralNumber());
			CompositeResponse replyEvent = MessageUtil.postRequest(getOffenses);
			Collection<JJSOffenseResponseEvent> allOffenses = MessageUtil.compositeToCollection(replyEvent,JJSOffenseResponseEvent.class);
			
			   Collections.sort((List<JJSOffenseResponseEvent>) allOffenses, Collections.reverseOrder(new Comparator<JJSOffenseResponseEvent>() {
				@Override
				public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2)
				{
				    return Integer.valueOf(evt1.getOID()).compareTo(Integer.valueOf(evt2.getOID()));
				}
			    }));		
			   JJSOffenseResponseEvent offenseEvt = allOffenses.iterator().next();
			   currentOffenseCode = offenseEvt.getOffenseCodeId();
		    return currentOffenseCode;
		}
	}
	
	/**
	 * getControllingReferralId
	 * @param supervisionNumber
	 * @return
	 */
	public static String getControllingReferralId(String supervisionNumber) {
		GetCasefileClosingDetailsEvent casefileClosingDetailEvent = (GetCasefileClosingDetailsEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETCASEFILECLOSINGDETAILS);
		casefileClosingDetailEvent.setSupervisionNumber(supervisionNumber);

		CompositeResponse compositeResponse = postRequestEvent(casefileClosingDetailEvent);

		CasefileClosingResponseEvent event = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);

		if (event != null) {
			return event.getControllingReferralId();
		} else {
			return null;
		}
	}
	
	/**
	 * 11029 - bug fix.17333 
	 * @param referralNum
	 * @param juvenileNum
	 * @param offenses
	 * @return
	 */
	public static List<JJSOffenseResponseEvent> buildDispositionList(String referralNum, String juvenileNum,List<JJSOffenseResponseEvent> offenses,List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffensesList,JuvenileOffenseCodeResponseEvent juvOffenseCode) {
		//ER changes 13220 starts
		JJSOffenseResponseEvent offenseEvent =null;
		List<JJSOffenseResponseEvent> dispositions = null;

		if(offenses!=null && !offenses.isEmpty())
		{
		//offenseEvent = offenses.get(0); // get first offense.
		/*int offListSize = offenses.size(); //use the lowest JJS_MS_OFFENSE.seqnum BUG 156375
		event = offenses.get(offListSize-1);*/
		//added sort based on seqNum ascending for BUG 156375
		    Collections.sort((List<JJSOffenseResponseEvent>) offenses, new Comparator<JJSOffenseResponseEvent>() {
			@Override
			public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2)
			{
			    if(evt1.getSequenceNum()!= null && evt2.getSequenceNum()!= null)
				    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			    else 
				return -1;
			}
		    });
		    offenseEvent = offenses.get(0);   
		}
		GetJuvenileCasefileReferralDetailsEvent getReferralDetails = new GetJuvenileCasefileReferralDetailsEvent();
		getReferralDetails.setJuvenileNum(juvenileNum);
		getReferralDetails.setReferralNum(referralNum);
		CompositeResponse replyEvent = MessageUtil.postRequest(getReferralDetails);
		JJSReferralResponseEvent referralDetail = (JJSReferralResponseEvent) MessageUtil.filterComposite(replyEvent, JJSReferralResponseEvent.class);

		if( offenseEvent!=null && referralDetail!=null){
			dispositions = new ArrayList<JJSOffenseResponseEvent>();
			Iterator<Entry<Date, String>> dispositionDetailMap = referralDetail.getDispositions().entrySet().iterator();
			while (dispositionDetailMap.hasNext()) {
		        Entry disposition = dispositionDetailMap.next();
		        
		        //added for userstory #32111
		        GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
				
		      //added for userstory #35134
				if(juvOffenseCode!=null && juvOffenseCode.getSeveritySubtype()!=null && juvOffenseCode.getSeveritySubtype().equals("T") && transferredOffensesList!=null)
				{
					for (Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffensesList.iterator(); transferredOffenseIter.hasNext();) {
						JuvenileCasefileTransferredOffenseResponseEvent transferredOffense = transferredOffenseIter.next();
						if(offenseEvent.getReferralNum()!=null && offenseEvent.getReferralNum().equals(transferredOffense.getReferralNum())){
							//calling Juvenile Offense code Response Event.
							jocEvent.setAlphaCode(transferredOffense.getOffenseCode());
							IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
							dispatch.postEvent(jocEvent);
							CompositeResponse response = (CompositeResponse)dispatch.getReply();
							
							juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
								
							//create an offense object and populate it from JuvenileOffenseCodeResponseEvent
                                			    JJSOffenseResponseEvent offense = new JJSOffenseResponseEvent();
                                			    offense.setCatagory(juvOffenseCode.getCategory());
                                			    offense.setOffenseCodeId(offenseEvent.getOffenseCodeId()); //#bug: 31346
                                			    offense.setOffenseCode(juvOffenseCode.getOffenseCode()); //#bug 31346  
                                			    if(referralDetail.getCourtPetitionAllegation() != null && !referralDetail.getCourtPetitionAllegation().isEmpty()){
                                				JuvenileOffenseCodeResponseEvent jocREvent = JuvenileReferralHelper.validateOffenseCd(referralDetail.getCourtPetitionAllegation());
                                				if( jocREvent != null ){
                                				    
                                				    offense.setOffenseDescription(jocREvent.getShortDescription());
                                				}
                                			    }else{
                                				offense.setOffenseDescription(juvOffenseCode.getShortDescription());
                                			    }
                                			    //offense.setOffenseDescription(juvOffenseCode.getShortDescription());
                                			    offense.setCitationCode(juvOffenseCode.getCitationCode());
                                			    offense.setCitationSource(juvOffenseCode.getCitationSource());
                                			    offense.setDispositionCode(disposition.getValue().toString());
                                			    offense.setDispositionDate((Date) disposition.getKey());
                                			    dispositions.add(offense);
                                			    break;
						}
					}
				}
				//added for userstory #32111
		        
		        //Ignore the disposition records if the offense level is AC. 
		        if(offenseEvent.getCatagory()!=null && offenseEvent.getCatagory().equals("AC"))
		        continue;
		       
		        JJSOffenseResponseEvent offense = new JJSOffenseResponseEvent();
		       
		        //if condition added for BUG 156574
    		if (referralDetail.getCourtPetitionAllegation() != null && !referralDetail.getCourtPetitionAllegation().isEmpty())
    		{
    		    JuvenileOffenseCodeResponseEvent jocREvent = JuvenileReferralHelper.validateOffenseCd(referralDetail.getCourtPetitionAllegation());
    		    if (jocREvent != null)
    		    {
    			offense.setOffenseDescription(jocREvent.getShortDescription());
    			offense.setCatagory(jocREvent.getCategory());
    			offense.setCitationSource(jocREvent.getCitationSource());
    			offense.setCitationCode(jocREvent.getCitationCode());
    		    }
    		}
    		else
    		{
    		    offense.setOffenseDescription(juvOffenseCode.getShortDescription());
    		    offense.setCatagory(offenseEvent.getCatagory());
    		    offense.setCitationSource(offenseEvent.getCitationSource());
    		    offense.setCitationCode(offenseEvent.getCitationCode());
    		}
            		
 		        offense.setOffenseCodeId(offenseEvent.getOffenseCodeId()); //#bug: 31346  
 		        offense.setOffenseCode(offenseEvent.getOffenseCode()); //#bug 31346    
        		offense.setDispositionCode(disposition.getValue().toString());
        		offense.setDispositionDate((Date) disposition.getKey());
        		dispositions.add(offense);
	    	}
	    }
		
		//Sort by dispositionDate changes for ER 13220 starts
		List<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
		sortFields.add(new ReverseComparator(new BeanComparator("dispositionDate")));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		if(dispositions!=null && !dispositions.isEmpty()){
			Collections.sort((List<JJSOffenseResponseEvent>)dispositions, multiSort);
		}
		//Sort by dispositionDate changes for ER 13220 ends

		//ER changes 13220 ends
		return dispositions;
	}
	
	
	/**
	 * getFamilyHistoryInfo
	 * @param myReportBean
	 * @param juvenileNum
	 * @param casefileId
	 */
	public static void getFamilyHistoryInfo(CommonAppReportPrintBean myReportBean, String juvenileNum, String casefileId) {
		Address familyAddress = null;
		JuvenileMemberForm.MemberContact familyPhone = null;

		List<FamilyMemberDetailResponseEvent> inHomeMembers = new ArrayList<FamilyMemberDetailResponseEvent>(); // FamilyMemberDetailResponseEvent
		List<FamilyMemberDetailResponseEvent> outOfHomeMembers = new ArrayList<FamilyMemberDetailResponseEvent>(); // FamilyMemberDetailResponseEvent
		List<DeceasedFamilyMemberBean> deceasedMembers = new ArrayList<DeceasedFamilyMemberBean>(); // DeceasedFamilyMemberBean
		List<PersonInAndOutHomeBean> personsInHome = new ArrayList<PersonInAndOutHomeBean>();
		List<PersonInAndOutHomeBean> personsOutHome = new ArrayList<PersonInAndOutHomeBean>();
		List<FamilyMemberTraitBean>  familyMembersTraits = new ArrayList<FamilyMemberTraitBean> ();
		CompositeResponse replyEvent;

		Collection<FamilyConstellationListResponseEvent> constellations = getCurrentActiveConstellation(juvenileNum);
		List<String> familyMemberGangInfo = new ArrayList<String>();

		if (constellations != null && !constellations.isEmpty()) {
			// Only 1 active constellation at a time, therefore it's safe to get
			// the first in collection
			FamilyConstellationListResponseEvent activeConstellation =  constellations.iterator().next();

			// Get Family Traits for Family Dynamic info
			GetFamilyTraitsEvent getFamilyTraitsEvent = (GetFamilyTraitsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYTRAITS);
			getFamilyTraitsEvent.setFamilyNum(activeConstellation.getFamilyNum());
			replyEvent = MessageUtil.postRequest(getFamilyTraitsEvent);
			List<FamilyConstellationTraitsResponseEvent> familyDynamicTraits =  (List<FamilyConstellationTraitsResponseEvent>) MessageUtil.compositeToCollection(replyEvent,FamilyConstellationTraitsResponseEvent.class);
			Collections.sort(familyDynamicTraits);

			if (familyDynamicTraits != null && familyDynamicTraits.size() > 0) {
				List<Trait> uiFamilyDynamicTraits = new ArrayList<Trait>();
				for (Iterator<FamilyConstellationTraitsResponseEvent> familyDynamicIter = familyDynamicTraits.iterator(); familyDynamicIter.hasNext();) {
					FamilyConstellationTraitsResponseEvent familyDynamicTrait = (FamilyConstellationTraitsResponseEvent) familyDynamicIter.next();

					Trait trait = new Trait();
					trait.setEntryDate(DateUtil.dateToString(familyDynamicTrait.getEntryDate(), UIConstants.DATE_FMT_1));
					trait.setTraitId(familyDynamicTrait.getId());
					trait.setTraitStatusId(familyDynamicTrait.getStatusId());
					trait.setTraitLevelId(familyDynamicTrait.getLevelId());

					// over-ride the standard level description
					if ("SE".equalsIgnoreCase(trait.getTraitLevelId()) || "AC".equalsIgnoreCase(trait.getTraitLevelId()) || "CH".equalsIgnoreCase(trait.getTraitLevelId())) {
						trait.setTraitLevel("VERY MUCH"); //11009 changes 
					}else{
					 	if ("MI".equalsIgnoreCase(trait.getTraitLevelId()) || "MO".equalsIgnoreCase(trait.getTraitLevelId())) { 
							trait.setTraitLevel("SOMEWHAT");
						}
					}
					
					trait.setTraitComments(StringUtil.removeTimeStampFromComments(familyDynamicTrait.getComments()));
					trait.setTraitDescId(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FAMILY_TRAIT,familyDynamicTrait.getDynamicTypeId())); //bug fix #37899;
					uiFamilyDynamicTraits.add(trait);
				}
				myReportBean.setFamilyDynamicTraits(uiFamilyDynamicTraits);
			}
			
			Map<String,String> uniqueMembers = new HashMap<String,String>();
			//get active constellation family details.
			GetFamilyConstellationDetailsEvent getActiveConstellationDetails = new GetFamilyConstellationDetailsEvent();
			getActiveConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
			replyEvent = MessageUtil.postRequest(getActiveConstellationDetails);
			Collection<FamilyConstellationMemberListResponseEvent> activeConstFamilyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);
			
			StringBuffer bufFamilyMemberComments = new StringBuffer("");
			StringBuffer bufManagingConservator = new StringBuffer("");
			
			if (activeConstFamilyMembers != null && activeConstFamilyMembers.size() > 0) {
				Iterator<FamilyConstellationMemberListResponseEvent> iterator = activeConstFamilyMembers.iterator();
				
				while (iterator.hasNext()) {
					FamilyConstellationMemberListResponseEvent activeMember = (FamilyConstellationMemberListResponseEvent) iterator.next();
					if(uniqueMembers.containsKey(activeMember.getMemberNum())){
						continue;
					}else{
						uniqueMembers.put(activeMember.getMemberNum(),activeMember.getFamilyNum());
					}
					
					GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
					getMemberDetails.setMemberNum(activeMember.getMemberNum());
					replyEvent = MessageUtil.postRequest(getMemberDetails);
					FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);

					if (memberDetail != null && memberDetail.getComments() != null && !memberDetail.getComments().equalsIgnoreCase("")) {
						bufFamilyMemberComments.append(StringUtil.removeTimeStampFromComments(memberDetail.getComments())); // ER 11031 changes remove timestamp
						bufFamilyMemberComments.append(System.getProperty("line.separator"));
						myReportBean.setFamilyMemberComments(bufFamilyMemberComments.toString());
					}

					if (activeMember.isGuardian()) {
						bufManagingConservator.append(memberDetail.getFirstName() + "," + memberDetail.getLastName());
						bufManagingConservator.append(" Relationship:" + activeMember.getRelationToJuvenile());
						bufManagingConservator.append(System.getProperty("line.separator"));
								
						myReportBean.setManagingConservator(bufManagingConservator.toString());
						
						if (activeMember.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_FATHER) || activeMember.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_MOTHER)) {
							Collection<FamilyMemberMartialStatusResponseEvent> maritalStatuses = MessageUtil.compositeToCollection(replyEvent,FamilyMemberMartialStatusResponseEvent.class);
							if (maritalStatuses != null	&& maritalStatuses.size() > 0) {
								// get only the latest martial status
								Collections.sort((List<FamilyMemberMartialStatusResponseEvent>) maritalStatuses);
								FamilyMemberMartialStatusResponseEvent status = (FamilyMemberMartialStatusResponseEvent) maritalStatuses.iterator().next();
								myReportBean.setBirthParentsMaritalStatus(CodeHelper.getCodeDescription(PDCodeTableConstants.MARITAL_STATUS,status.getMartialStatusId()));
							}
						}
						if (activeMember.isInHomeStatus()) { 
							if (familyAddress == null) {
								familyAddress = getFamilyMemberAddress(activeMember.getMemberNum());
							}
							familyPhone = getFamilyMemberPhone(activeMember.getMemberNum());
						}
						myReportBean.setFamilyAddress(familyAddress);
						myReportBean.setFamilyPhone(familyPhone);
					}
					
					//Changes for uer story 11007 activeMember
					if ("BF".equalsIgnoreCase(activeMember.getRelationToJuvenileId())) {
						if(activeMember.isParentalRightsTerminated()){
							myReportBean.setFatherParentalRightsTerminated("YES");
						}
					}
					if ("BM".equalsIgnoreCase(activeMember.getRelationToJuvenileId())) {
						if(activeMember.isParentalRightsTerminated()){
							myReportBean.setMotherParentalRightsTerminated("YES");
						}
					}
					//Changes for uer story 11007 ends
					familyDetails(activeMember,familyMemberGangInfo,familyMembersTraits,personsInHome,personsOutHome,deceasedMembers,inHomeMembers,outOfHomeMembers,myReportBean,juvenileNum);
				}
			}
			
			//user story #11119 changes for update family characteristics.Check other constellation as well.
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetFamilyConstellationsEvent event = (GetFamilyConstellationsEvent)
					EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
			event.setJuvenileNum(juvenileNum);
			dispatch.postEvent(event);
			// Get PD Response Event	
			CompositeResponse response = (CompositeResponse) dispatch.getReply();

			// Perform Error handling
			MessageUtil.processReturnException(response);
			Map dataMap = MessageUtil.groupByTopic(response);
			if( dataMap != null )
			{
				Collection<FamilyConstellationListResponseEvent> families  = (Collection<FamilyConstellationListResponseEvent>)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
				
				if (families != null && families.size() > 0) {
					Iterator<FamilyConstellationListResponseEvent> constellationItr = families.iterator();
					while (constellationItr.hasNext()) {
						FamilyConstellationListResponseEvent resp = constellationItr.next();
					
						GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
						getConstellationDetails.setConstellationNum(resp.getFamilyNum());
						replyEvent = MessageUtil.postRequest(getConstellationDetails);
						
						Collection<FamilyConstellationMemberListResponseEvent> familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);
						
						// find first in-home guardian and get the address
						if (familyMembers != null && familyMembers.size() > 0) {
							Iterator<FamilyConstellationMemberListResponseEvent> iter = familyMembers.iterator();
							
							while (iter.hasNext()) {
								FamilyConstellationMemberListResponseEvent member = (FamilyConstellationMemberListResponseEvent) iter.next();
								if(uniqueMembers.containsKey(member.getMemberNum())){
									continue;
								}else{
									uniqueMembers.put(member.getMemberNum(),member.getFamilyNum());
								}
								familyDetails(member,familyMemberGangInfo,familyMembersTraits,personsInHome,personsOutHome,deceasedMembers,inHomeMembers,outOfHomeMembers,myReportBean,juvenileNum);
							} //first while.
						}//if
					}//while
				}//endif
			}//data map
		}

		myReportBean.setFamilyMemberGangInfo(familyMemberGangInfo);
		myReportBean.setPersonsInHome(personsInHome);
		myReportBean.setPersonsOutHome(personsOutHome);
		myReportBean.setDeceasedFamilyMembers(deceasedMembers);
		myReportBean.setFamilyMembersTraits(familyMembersTraits);
	}
	
	
	/**
	 * familyDetails
	 * @param member
	 * @param uniqueMembers
	 * @param familyMemberGangInfo
	 * @param familyMembersTraits
	 * @param personsInHome
	 * @param personsOutHome
	 * @param deceasedMembers
	 * @param inHomeMembers
	 * @param outOfHomeMembers
	 */
	public static void familyDetails(FamilyConstellationMemberListResponseEvent member,
			List<String> familyMemberGangInfo ,List<FamilyMemberTraitBean> familyMembersTraits,List<PersonInAndOutHomeBean> personsInHome,List<PersonInAndOutHomeBean> personsOutHome,
			List<DeceasedFamilyMemberBean> deceasedMembers, List<FamilyMemberDetailResponseEvent> inHomeMembers,List<FamilyMemberDetailResponseEvent> outOfHomeMembers,CommonAppReportPrintBean myReportBean, String juvenileNum){
		
		GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
		getMemberDetails.setMemberNum(member.getMemberNum());
		CompositeResponse replyEvent = MessageUtil.postRequest(getMemberDetails);
		
		FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);

		// get the dynamics between family members
		GetFamilyMemberTraitEvent getFamilyMemberTraitEvent = (GetFamilyMemberTraitEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERTRAIT);
		getFamilyMemberTraitEvent.setFamilyMemberNum(member.getMemberNum());
		CompositeResponse familyMemberTraitReplyEvent = MessageUtil.postRequest(getFamilyMemberTraitEvent);
		Collection<FamilyMemberTraitResponseEvent> memberTraits = MessageUtil.compositeToCollection(familyMemberTraitReplyEvent, FamilyMemberTraitResponseEvent.class);

		
		Collections.sort((List<FamilyMemberTraitResponseEvent>) memberTraits);  //bug fix:32652 
		Iterator<FamilyMemberTraitResponseEvent> iterMemberTraits = memberTraits.iterator();
		List<String> listMemberTraits = new ArrayList<String>();
		Map<String,String> removeDuplicates = new HashMap<String,String>();  //bug fix:32652 
		while (iterMemberTraits.hasNext()) {
			FamilyMemberTraitResponseEvent famMemResp = (FamilyMemberTraitResponseEvent) iterMemberTraits.next();

			String traitTypeId = famMemResp.getTraitTypeId().trim();
			if (Arrays.asList(UIConstants.FAMILY_MEMBER_TRAITS_REQUIRED_FOR_COMMON_APP).contains(traitTypeId)) {
				listMemberTraits.add(CodeHelper.getCodeDescription(PDCodeTableConstants.MEMBER_TRAIT_TYPE,famMemResp.getTraitTypeId()));
			}
			if (traitTypeId.equalsIgnoreCase(UIConstants.FAMILY_MEMBER_INVOLVED_IN_GANG_TRAIT)) { // Involved in gang activity
				StringBuffer sb = new StringBuffer();
				myReportBean.setAnyFamilyMemberHaveGangAffiliation("YES");
				Trait famMemTrait = UIJuvenileHelper.getFamilyMemberFormTraitFROMTraitInfoRespEvt(famMemResp, true);
				if(famMemTrait!=null && !removeDuplicates.containsKey(famMemResp.getMemberId())){ //bug fix:32652 
					removeDuplicates.put(famMemResp.getMemberId(),famMemTrait.getEntryDate());  //bug fix:32652 
					sb.append("Status: ");
					sb.append(famMemTrait.getTraitStatus());
					if(famMemTrait.getTraitComments()!=null && !famMemTrait.getTraitComments().isEmpty()){
						sb.append(" | Comments: ");
						sb.append(StringUtil.removeTimeStampFromComments(famMemTrait.getTraitComments()));
					}
					familyMemberGangInfo.add(sb.toString());
				}
			}
		
		} //end of while

		if (listMemberTraits.size() > 0) {
			if (member.isInHomeStatus()){ //in home status as yes.
				FamilyMemberTraitBean familyMemberTrait = new FamilyMemberTraitBean();
				familyMemberTrait.setFamilyMemberName(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
				familyMemberTrait.setRelationship(member.getRelationToJuvenile()); //ER changes 11119
				familyMemberTrait.setTraits((List<String>) listMemberTraits);
				familyMembersTraits.add(familyMemberTrait);
			}
		}

		String rel = member.getRelationToJuvenileId();

		if (member.isInHomeStatus()) {
			if (rel.equalsIgnoreCase(UIConstants.BIRTH_FATHER) || rel.equalsIgnoreCase(UIConstants.STEP_FATHER)	|| rel.equalsIgnoreCase(UIConstants.ADOPTIVE_FATHER)) {
				PersonInAndOutHomeBean pbean = new PersonInAndOutHomeBean();
				pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
				pbean.setDateofBirth(memberDetail.getDateOfBirth());
				pbean.setRelationshipType(member.getRelationToJuvenile());
				pbean.setSSN(memberDetail.getSsn());
				personsInHome.add(pbean);
			} else if (rel.equalsIgnoreCase(UIConstants.BIRTH_MOTHER) || rel.equalsIgnoreCase(UIConstants.STEP_MOTHER) || rel.equalsIgnoreCase(UIConstants.ADOPTIVE_MOTHER)) {
				PersonInAndOutHomeBean pbean = new PersonInAndOutHomeBean();
				pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));			
				pbean.setDateofBirth(memberDetail.getDateOfBirth());
				pbean.setRelationshipType(member.getRelationToJuvenile());
				pbean.setSSN(memberDetail.getSsn());
				personsInHome.add(pbean);
			} else {
				PersonInAndOutHomeBean pbean = new PersonInAndOutHomeBean();
				pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
				pbean.setDateofBirth(memberDetail.getDateOfBirth());
				pbean.setRelationshipType(member.getRelationToJuvenile());
				personsInHome.add(pbean);
			}
		} else {
			//do the same for out of home person.//32114
			PersonInAndOutHomeBean pbean = new PersonInAndOutHomeBean();
			pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
			pbean.setDateofBirth(memberDetail.getDateOfBirth());
			pbean.setRelationshipType(member.getRelationToJuvenile());
			pbean.setSSN(memberDetail.getSsn());
			pbean.setRelationshipTypeId(rel);
			String involvementLevel = CodeHelper.getCodeDescription(PDCodeTableConstants.INVOLVEMENT_LEVEL,	member.getInvolvmentLevelId());
			if (involvementLevel.equals("SOME")	|| involvementLevel.equals("INTENSE")) {
				involvementLevel = "Y";
			} else {
				involvementLevel = "N";
			}

			pbean.setInvolvementLevel(involvementLevel);
			pbean.setPersonAddress(getFamilyMemberAddress(member.getMemberNum()));
			pbean.setPersonPhone(getFamilyMemberPhone(member.getMemberNum()).getContactPhoneNumber());
			personsOutHome.add(pbean);
		}

		if (member.isDeceased()) {
			DeceasedFamilyMemberBean dbean = new DeceasedFamilyMemberBean();
			dbean.setRelationshipToJuvenile(member.getRelationToJuvenile());
			dbean.setDeceasedMemberName(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
			GetJuvenileAgeAtfamilyMemberDeathEvent rEvent = (GetJuvenileAgeAtfamilyMemberDeathEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETJUVENILEAGEATFAMILYMEMBERDEATH);
			rEvent.setFamilyMemberId(member.getMemberNum());
			rEvent.setAction("retrieve");
			rEvent.setJuvenileNum(juvenileNum);
			
			replyEvent = MessageUtil.postRequest(rEvent);
			JuvenileAgeResponseEvent ageResp = (JuvenileAgeResponseEvent) MessageUtil.filterComposite(replyEvent,JuvenileAgeResponseEvent.class);
			if (ageResp != null && ageResp.getJuvenileAgeAtDeath() != null) {
				dbean.setJuvenileAgeAtDeath(ageResp.getJuvenileAgeAtDeath());
			}
			deceasedMembers.add(dbean);
		}

		if (member.isInHomeStatus()) {
			inHomeMembers.add(memberDetail);
		} else {
			outOfHomeMembers.add(memberDetail);
		}
	}
	
	
	/**
	 * getCurrentActiveConstellation
	 * @param juvenileNum
	 * @return
	 */
	public static Collection<FamilyConstellationListResponseEvent> getCurrentActiveConstellation(String juvenileNum) {
		GetActiveFamilyConstellationEvent getCurrentConstellation = new GetActiveFamilyConstellationEvent();
		getCurrentConstellation.setJuvenileNum(juvenileNum);
		CompositeResponse replyEvent = MessageUtil.postRequest(getCurrentConstellation);
		Collection<FamilyConstellationListResponseEvent> constellations = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationListResponseEvent.class);
		return constellations;
	}
	
	/**
	 * getFamilyMemberAddress
	 * @param memberNum
	 * @return
	 */
	public static Address getFamilyMemberAddress(String memberNum) {
		Address familyAddress = null;
		GetFamilyMemberAddressEvent getFamilyMemberAddress = new GetFamilyMemberAddressEvent();
		getFamilyMemberAddress.setMemberNum(memberNum);
		CompositeResponse replyEvent = MessageUtil.postRequest(getFamilyMemberAddress);
		Collection<AddressResponseEvent> familyMemberAddresses = MessageUtil.compositeToCollection(replyEvent, AddressResponseEvent.class);
		if (familyMemberAddresses != null) {
			Collections.sort((List<AddressResponseEvent>) familyMemberAddresses);
			Iterator<AddressResponseEvent> addrIter = familyMemberAddresses.iterator();
			while (addrIter.hasNext()) {
				AddressResponseEvent addr = (AddressResponseEvent) addrIter.next();
				// get only latest home address
				if (addr != null && addr.getAddressTypeId().equalsIgnoreCase("RES")) {
					familyAddress = new Address();
					familyAddress.setStreetNum(addr.getStreetNum());
					familyAddress.setStreetNumSuffix(addr.getStreetNumSuffix());
					familyAddress.setStreetName(addr.getStreetName());
					familyAddress.setAptNum(addr.getAptNum());
					familyAddress.setCity(addr.getCity());
					familyAddress.setState(addr.getState());
					familyAddress.setCounty(addr.getCounty());
					familyAddress.setZipCode(addr.getZipCode());
					familyAddress.setAdditionalZipCode(addr.getAdditionalZipCode());
					break;
				}
			}
		}
		return familyAddress;
	}
	
	
	/**
	 * getFamilyMemberPhone
	 * @param memberNum
	 * @return
	 */
	public static JuvenileMemberForm.MemberContact getFamilyMemberPhone(String memberNum) {
		JuvenileMemberForm.MemberContact familyPhone = new JuvenileMemberForm.MemberContact();

		GetFamilyMemberContactEvent getFamilyMemberContact = new GetFamilyMemberContactEvent();
		getFamilyMemberContact.setMemberId(memberNum);
		CompositeResponse replyEvent = MessageUtil.postRequest(getFamilyMemberContact);
		Collection<FamilyMemberPhoneResponseEvent> familyMemberPhones = MessageUtil.compositeToCollection(replyEvent, FamilyMemberPhoneResponseEvent.class);

		if (familyMemberPhones != null) {
			Iterator<FamilyMemberPhoneResponseEvent> phoneIter = familyMemberPhones.iterator();
			List<JuvenileMemberForm.MemberContact> filteredList = new ArrayList<JuvenileMemberForm.MemberContact>();
			while (phoneIter.hasNext()) {
				FamilyMemberPhoneResponseEvent phone = (FamilyMemberPhoneResponseEvent) phoneIter.next();
				// get only latest home phone/mobile phone
				if (phone.getPhoneTypeId().equalsIgnoreCase("HM") || phone.getPhoneTypeId().equalsIgnoreCase("MO")) {
					// familyPhone = phone;
					JuvenileMemberForm.MemberContact myContact = UIJuvenileHelper.getJuvMemberFormMemberContactFROMContactRespEvt(phone);
					filteredList.add(myContact);
				}
			}
			Collections.sort(filteredList);
			if (filteredList.size() > 0) {
				familyPhone = (JuvenileMemberForm.MemberContact) filteredList.get(0);
			}
		}
		return familyPhone;
	}
	
	
	/**
	 * setJuvenileJusticeHistoryData
	 * @param myReportBean
	 * @param juvenileNum
	 */
	public static void setJuvenileJusticeHistoryData(CommonAppReportPrintBean myReportBean, String juvenileNum) {
		if (juvenileNum != null && !juvenileNum.equals("")) {
			GetCommonAppJusticeHistoryEvent reqEvent = (GetCommonAppJusticeHistoryEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETCOMMONAPPJUSTICEHISTORY);
			reqEvent.setJuvenileNum(juvenileNum);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(reqEvent);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(compositeResponse);
			CommonAppJusticeHistoryResponseEvent resp = (CommonAppJusticeHistoryResponseEvent) MessageUtil.filterComposite(compositeResponse,CommonAppJusticeHistoryResponseEvent.class);
			if (resp != null) {
				myReportBean.setCountAdjudicationsTotal(resp.getAdjudicationEvents());
				myReportBean.setCountCINSAdjudication(resp.getCINSAdjudicationNumber());
			}
		}
	}
	
	/**
	 * setGuardianFinancialInfo
	 * @param myReportBean
	 * @param juvenileNum
	 */
	public static void setGuardianFinancialInfo(CommonAppReportPrintBean myReportBean, String juvenileNum) {
		GetGuardianFinancialInfoEvent reqGuardFin = (GetGuardianFinancialInfoEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETGUARDIANFINANCIALINFO);
		reqGuardFin.setJuvenileId(juvenileNum);
		CompositeResponse composite = MessageUtil.postRequest(reqGuardFin);
		Collection<GuardianFinancialInfoResponseEvent> guardians = MessageUtil.compositeToCollection(composite,	GuardianFinancialInfoResponseEvent.class);
		ArrayList<CommonAppForm.GuardianFinancialInformation> myGuardians = new ArrayList<CommonAppForm.GuardianFinancialInformation>();
		Iterator<GuardianFinancialInfoResponseEvent> myIter = guardians.iterator();
		while (myIter.hasNext()) {
			GuardianFinancialInfoResponseEvent myGuardianResp = (GuardianFinancialInfoResponseEvent) myIter.next();
			CommonAppForm.GuardianFinancialInformation myGuardian = new CommonAppForm.GuardianFinancialInformation();
			if (myGuardianResp != null) {
				myGuardian.setFirstName(myGuardianResp.getFirstName());
				myGuardian.setMiddleName(myGuardianResp.getMiddleName());
				myGuardian.setLastName(myGuardianResp.getLastName());
				myGuardian.setDisabled(myGuardianResp.isDisabled());
				myGuardian.setOccupation(myGuardianResp.getOccupation());
				myGuardian.setEmployerName(myGuardianResp.getEmployerName());
				myGuardian.setSalary(myGuardianResp.getSalary());
				myGuardian.setSalaryRate(myGuardianResp.getSalaryRate());
				myGuardian.setEmployerAddressId(myGuardianResp.getEmployerAddressId());
				myGuardian.setOtherIncomeAmount(myGuardianResp.getOtherIncomeAmount());
				myGuardian.setAnnualNetIncome(myGuardianResp.getAnnualNetIncome());
				myGuardian.setFoodStamps(myGuardianResp.isFoodStamps());
				myGuardian.setIntangibleProperty(myGuardianResp.getIntangibleProperty());
				myGuardian.setMonthlyLifeInsurancePremium(myGuardianResp.getMonthlyLifeInsurancePremium());
				myGuardian.setPropertyValue(myGuardianResp.getPropertyValue());
				myGuardian.setTanf(myGuardianResp.getTanf());
				myGuardian.setSsi(myGuardianResp.getSsi());
			}
			myGuardians.add(myGuardian);
		}
		myReportBean.setGuardianFinancialInfo(myGuardians);
	}
	
	
	
	/**
	 * setMedicaidInfo
	 * @param myReportBean
	 * @param juvenileNum
	 */
	public static void setMedicaidInfo(CommonAppReportPrintBean myReportBean,	String juvenileNum) {
		List<FamilyMemberBenefitResponseEvent> benefitsList = new ArrayList<FamilyMemberBenefitResponseEvent>();
		GetBenefitsAssessmentGuardiansEvent guardianInfo = (GetBenefitsAssessmentGuardiansEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETBENEFITSASSESSMENTGUARDIANS);
		guardianInfo.setJuvenileId(juvenileNum);
		CompositeResponse composite = MessageUtil.postRequest(guardianInfo);
		Collection<BenefitsAssessmentGuardianResponseEvent> guardians = MessageUtil.compositeToCollection(composite,BenefitsAssessmentGuardianResponseEvent.class);
		Iterator<BenefitsAssessmentGuardianResponseEvent> myIter = guardians.iterator();
		while (myIter.hasNext()) {
			BenefitsAssessmentGuardianResponseEvent myGuardianResp = (BenefitsAssessmentGuardianResponseEvent) myIter.next();
			
			GetFamilyMemberBenefitsEvent familyMemberBenefitEvent = (GetFamilyMemberBenefitsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERBENEFITS);
			familyMemberBenefitEvent.setMemberId(myGuardianResp.getMemberId());
			CompositeResponse compositeResponse = MessageUtil.postRequest(familyMemberBenefitEvent);
			Collection<FamilyMemberBenefitResponseEvent> benefits = MessageUtil.compositeToCollection(compositeResponse,FamilyMemberBenefitResponseEvent.class);
			Iterator<FamilyMemberBenefitResponseEvent> iterator = benefits.iterator();
			while (iterator.hasNext()) {
				FamilyMemberBenefitResponseEvent benefit = (FamilyMemberBenefitResponseEvent) iterator.next();
				if (benefit.getEligibilityTypeId().equals("MCAD")) {
					benefitsList.add(benefit);
				}
			}
		}
		if (benefitsList.size() > 0) {
            List<FamilyMemberBenefitResponseEvent> theList = new ArrayList(benefitsList);
            ArrayList<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
            sortFields.add(new ReverseComparator(new BeanComparator("entryDate")));
            ComparatorChain multiSort = new ComparatorChain(sortFields);
            Collections.sort(theList, multiSort);
            benefitsList = new ArrayList<FamilyMemberBenefitResponseEvent>(theList);

            FamilyMemberBenefitResponseEvent familyBenefit = (FamilyMemberBenefitResponseEvent) benefitsList.get(0);
			myReportBean.setEligibleForMedicaid("NO");
			myReportBean.setReceivingMedicaid("NO");
			if (familyBenefit.isElgibleForBenefits()) {
				myReportBean.setEligibleForMedicaid("YES");
			}
			if (familyBenefit.isReceivingBenefits()) {
				myReportBean.setReceivingMedicaid("YES");
			}
		}
	}
	
	/**
	 * getDSMResults
	 * @param myReportBean
	 * @param myCommonAppForm
	 */
	public static void getDSMResults(CommonAppReportPrintBean myReportBean,CommonAppForm myCommonAppForm) {
		Collection<DSMDiagnosisResponseEvent> dsm = new ArrayList<DSMDiagnosisResponseEvent> ();
		if (myCommonAppForm.getDsmResults() != null	&& myCommonAppForm.getDsmResults().size() > 0) {
			Iterator<CommonAppForm.Diagnosis> iterDSM = myCommonAppForm.getDsmResults().iterator();
			while (iterDSM.hasNext()) {
				CommonAppForm.Diagnosis diagnosis = (CommonAppForm.Diagnosis) iterDSM.next();
				if (diagnosis.getDiagnosisResults() != null	&& diagnosis.getDiagnosisResults().size() > 0) {
					Iterator<DSMDiagnosisResponseEvent> iterDiagnosis = diagnosis.getDiagnosisResults().iterator();
					while (iterDiagnosis.hasNext()) {
						dsm.add(iterDiagnosis.next());
					}
				}
			}
		}
		if (dsm != null && dsm.size() > 0) {
			myReportBean.setDsmResults(dsm);
		}
	}
	
	
	/**
	 * This method will return all of juvenile's placement history (not only
	 * detention). Additionally,
	 * 
	 * @param juvenileNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static double getJuvenilePlacementHistory(CommonAppForm myCommonAppForm, String juvenileNum, List<Placement> juvenilePlacementHistory,
                                                     List<Placement> outofHomeplacements, StringBuilder sbRecentOutOfHomeDischargeDate)
	{
		double numberOfChildCareDays = 0;
		HashMap<String,String> uniqueFacilityByRefMap = new HashMap<String,String>();
		HashMap<String,String>  uniqueFacilityMapTJPC = new HashMap<String,String> ();
		HashMap<String,JuvenileFacilityResponseEvent> facilityMap = new HashMap<String,JuvenileFacilityResponseEvent> ();
		
		GetNonDetentionJuvenileFacilitiesEvent facilityEvent = new GetNonDetentionJuvenileFacilitiesEvent();
		CompositeResponse response = MessageUtil.postRequest(facilityEvent);
		List<JuvenileFacilityResponseEvent> facilityList = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);
		if(facilityList!=null){
			Iterator<JuvenileFacilityResponseEvent > facilityItr = facilityList.iterator();
			while(facilityItr.hasNext()){
				JuvenileFacilityResponseEvent facility = (JuvenileFacilityResponseEvent) facilityItr.next();
				if(facility.getJuvTJPCPlacementType()!=null && !facility.getJuvTJPCPlacementType().equals("D")){
					facilityMap.put(facility.getCode(), facility);
				}
			}
		}
		//16062 changes starts:
		Collection<JuvenileDetentionFacilitiesResponseEvent> detentionFacilities = JuvenileFacilityHelper.getJuvFacilityDetails(juvenileNum,null);
		if (detentionFacilities != null) 
		{
			Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent> )detentionFacilities, Collections.reverseOrder(new  Comparator<JuvenileDetentionFacilitiesResponseEvent>() {
				public int compare(JuvenileDetentionFacilitiesResponseEvent m1, JuvenileDetentionFacilitiesResponseEvent m2) {
				    	if(m1.getReleaseDate()!=null && m2.getReleaseDate()==null){
				    		return -1;
				    	}
				    	if(m1.getReleaseDate()==null && m2.getReleaseDate()!=null){
				    		return 1;
	    				} 
						if(m1.getReleaseDate()==null && m2.getReleaseDate()==null){
							return 0;
					    }
				        return m1.getReleaseDate().compareTo(m2.getReleaseDate());
				    }
			}));
			
			//16062 changes ends
			Iterator<JuvenileDetentionFacilitiesResponseEvent> detFacItr = detentionFacilities.iterator();
			
			while (detFacItr.hasNext()) 
			{
				JuvenileDetentionFacilitiesResponseEvent resp = (JuvenileDetentionFacilitiesResponseEvent) detFacItr.next();
				JuvenileFacilityResponseEvent facilityCode = (JuvenileFacilityResponseEvent) facilityMap.get(resp.getDetainedFacility());
				if(facilityCode!=null){
					Placement uiPlacement = getFacilityFromResp(myCommonAppForm, resp, facilityCode);
					
					if (uniqueFacilityMapTJPC.get(resp.getDetainedFacility()) == null) {
						juvenilePlacementHistory.add(uiPlacement);
						uniqueFacilityMapTJPC.put(resp.getDetainedFacility(), resp.getDetainedFacility());
					}
					//Changes for ER:11479 excluding D,E,H.
					if (uniqueFacilityByRefMap.get(resp.getReferralNumber()) == null) { //bug fix 16062: Post discussion with carla. Check for unique referral no not the facilty code.
						outofHomeplacements.add(uiPlacement);
						uniqueFacilityByRefMap.put(resp.getReferralNumber(), resp.getDetainedFacility());
					}
					numberOfChildCareDays += uiPlacement.getDetTime();
				}
			}
			//get recent date of out of home placement JIMS200077404 
			if(juvenilePlacementHistory != null && juvenilePlacementHistory.size()>0)
			{
				Date mostRecentDischargeDate = null;
				for(int i=0;i<juvenilePlacementHistory.size();i++)
				{
					Placement uiPlacement = (Placement)juvenilePlacementHistory.get(i);
					
					Date releaseDate = DateUtil.stringToDate(uiPlacement.getReleaseDate(),DateUtil.DATE_FMT_1);
					//bug fix 16062:For the juvenile currently in facility.
					if(releaseDate==null){
				    //11026 changes for LOC of current/most recent out of home placement.
						if(uiPlacement.getFacilityInfo()!=null && myCommonAppForm.getPlacement()!=null && myCommonAppForm.getPlacement().getRecentLevelOfCare()==null){
							myCommonAppForm.getPlacement().setRecentLevelOfCare(uiPlacement.getFacilityInfo().getLevelOfCare());
						}
						if(uiPlacement.getOutcome()!=null && !uiPlacement.getOutcome().equals("")){
							myCommonAppForm.getPlacement().setRecentReasonForDischarge(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_OUTCOME, uiPlacement.getOutcome()));
						}
						sbRecentOutOfHomeDischargeDate.append("");
						break;
					 }
					    
 					 if(mostRecentDischargeDate == null){ //For single placement history. 
 					    	mostRecentDischargeDate = releaseDate;
					    	//11026 changes for LOC of current/most recent out of home placement.
				    		if(uiPlacement.getFacilityInfo()!=null && myCommonAppForm.getPlacement()!=null && myCommonAppForm.getPlacement().getRecentLevelOfCare()==null){
				    			myCommonAppForm.getPlacement().setRecentLevelOfCare(uiPlacement.getFacilityInfo().getLevelOfCare());
				    		}
							if(uiPlacement.getOutcome()!=null && !uiPlacement.getOutcome().equals("")){
								myCommonAppForm.getPlacement().setRecentReasonForDischarge(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_OUTCOME, uiPlacement.getOutcome()));
							}
					 }
 					 else
 					 { //For more than one placement history
					        if(releaseDate!=null && releaseDate.after(mostRecentDischargeDate)){
							    mostRecentDischargeDate = releaseDate;
							    //11026 changes for LOC of current/most recent out of home placement.
							    if(uiPlacement.getFacilityInfo()!=null && myCommonAppForm.getPlacement()!=null && myCommonAppForm.getPlacement().getRecentLevelOfCare()==null){
							    	myCommonAppForm.getPlacement().setRecentLevelOfCare(uiPlacement.getFacilityInfo().getLevelOfCare());
							    }
							    		
							    if(uiPlacement.getOutcome()!=null && !uiPlacement.getOutcome().equals("")){
									myCommonAppForm.getPlacement().setRecentReasonForDischarge(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_OUTCOME, uiPlacement.getOutcome()));
								}
					        }
					}
				} //end of for loop.
				if(mostRecentDischargeDate != null){		
					sbRecentOutOfHomeDischargeDate.append(DateUtil.dateToString(mostRecentDischargeDate, "MM/dd/yyyy"));
				}
			}
		}
		if (uniqueFacilityByRefMap.size() == 0) {
			return 0;
		} else {
			return numberOfChildCareDays / uniqueFacilityByRefMap.size();
		}
	}
	/**
	 * getFacilityFromResp
	 * @param commonAppForm
	 * @param resp
	 * @param facResp
	 * @return
	 */
	public static Placement getFacilityFromResp(CommonAppForm commonAppForm,
			JuvenileDetentionFacilitiesResponseEvent resp, JuvenileFacilityResponseEvent facResp )
	{
		final long DAYS_CONVERSION = (1000 * 3600 * 24) ;
		final String DAYS_STR = " Days" ;

		Placement place = new Placement();
		//hard coded as completed, populate it with outcome.
		place.setAdmitReason( resp.getOutcomeDesc()); // added for task #34121 
		place.setFacilityName( resp.getDetainedFacilityDesc());
		place.setAdmitDate( DateUtil.dateToString( resp.getAdmitDate(), "MM/dd/yyyy" ) );
		place.setAdmitTime(resp.getAdmitTime());
		place.setReferralNumber(resp.getReferralNumber());
		place.setReleaseTime( resp.getReleaseTime());
		place.setReleaseDate( DateUtil.dateToString( resp.getReleaseDate(), "MM/dd/yyyy" ) );
		//11026 changes 
		place.setOutcome(resp.getOutcome());
		place.setStayed( false );
		if(resp.getAdmitDate()!=null)
		{
			if( resp.getReleaseDate() != null )
			{
				long days = ((resp.getReleaseDate().getTime() - resp.getAdmitDate().getTime()) / DAYS_CONVERSION) + 1;
				place.setDetTime( days );
				place.setTotalTime( UIConstants.EMPTY_STRING + days + DAYS_STR );
			}
			else
			{
				long days = ((DateUtil.getCurrentDate().getTime() - resp.getAdmitDate().getTime()) / DAYS_CONVERSION) + 1;
				place.setDetTime( days );
				place.setTotalTime( UIConstants.EMPTY_STRING + days + DAYS_STR );
			}
		}
		place.setFacilityInfo( facResp );
		
		Collection<Placement> coll = commonAppForm.getCourtOrder().getSelectedPlacement();
		Iterator<Placement> iter = coll.iterator();
		while (iter.hasNext()) {
			Placement formPlacement = (Placement) iter.next();
			if (formPlacement.getFacilityName().equals(place.getFacilityName())) {
				place.setContinuedStay(formPlacement.isContinuedStay());
			}
		}
		return place;
	}

	
	/**
	 * Utility method to help post method to the PD. Takes in a completed set event, posts it to the PD and runs it through MessageUtil.processReturnException
	 * before returning the composite response to the user
	 * @param event -- the RequestEvent to post to the PD
	 * @return -- the Composite Resposnse, can be null
	 */
	private static CompositeResponse postRequestEvent(RequestEvent event) {
		if(event==null){
			return null;
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);	
		return response;
	}
}
