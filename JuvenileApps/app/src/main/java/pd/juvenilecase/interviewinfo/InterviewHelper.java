package pd.juvenilecase.interviewinfo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import messaging.casefile.GetCasefileClosingDetailsEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.codetable.GetJuvenileReferralDecisionCodeEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileReferralDecisionResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.interviewinfo.CreateHireAttorneyReportEvent;
import messaging.interviewinfo.CreateRequestAttorneyReportEvent;
import messaging.interviewinfo.to.CoActorTO;
import messaging.interviewinfo.to.EmploymentHistoryTO;
import messaging.interviewinfo.to.FamilyInformationTO;
import messaging.interviewinfo.to.HireAttorneyGuardianDataTO;
import messaging.interviewinfo.to.HireAttorneyReportDataTO;
import messaging.interviewinfo.to.JOTChargeTO;
import messaging.interviewinfo.to.JOTTO;
import messaging.interviewinfo.to.JPCourtReferralTO;
import messaging.interviewinfo.to.OffenseInformationTO;
import messaging.interviewinfo.to.ParentalStatementReportDataTO;
import messaging.interviewinfo.to.PropertyLossTO;
import messaging.interviewinfo.to.ReferralTO;
import messaging.interviewinfo.to.RequestAppointedAttorneyReportDataTO;
import messaging.interviewinfo.to.SchoolHistoryTO;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import messaging.interviewinfo.to.SubstanceAbuseInformationTO;
import messaging.interviewinfo.to.SupervisionRuleTO;
import messaging.interviewinfo.to.TraitTO;
import messaging.interviewinfo.to.WarrantInformationTO;
import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenilecase.GetJCCConvictionEvent;
import messaging.juvenilecase.GetJCCDefendantEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.GetTraitParentByCategoryEvent;
import messaging.juvenilecase.reply.JPCourtReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.GetPetitionByCJISEvent;
import messaging.juvenilewarrant.SearchJuvenileWarrantEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.ujac.util.BeanComparator;

import pd.address.Address;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileCourt;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.Phone;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileDrugs;
import pd.juvenile.JuvenileJob;
import pd.juvenile.JuvenileSchoolHistory;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JCCConviction;
import pd.juvenilecase.JCCDefendant;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileTrait;
import pd.juvenilecase.SupervisionTypeTJJDMap;
import pd.juvenilecase.TraitType;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberEmployment;
import pd.juvenilecase.family.FamilyMemberFinancial;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.referral.Disposition;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import pd.juvenilewarrant.JuvenileOffenderTrackingCoActor;
import pd.juvenilewarrant.JuvenileOffenderTrackingProperty;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantService;
import pd.supervision.Group;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import pd.supervision.administerserviceprovider.administerlocation.Location;
import pd.supervision.supervisionoptions.Condition;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;

/*
 * How it Works...
 * 
 * Helper classes usually reflect a defect in design, and this is no exception. 
 * 
 * Much of this class is functionality that probably should have been on the 
 * entities but wasn't. The remaining is functionality that needs to be shared between 
 * multiple commands or would have bloated the commands with to many details.
 * 
 * ** A furture improvement could be to refactor the report specific code into a 
 * report class for each of the reports, leaving only the shared code in this 
 * class. The commands could then use the report classes.  
 * 
 * Naming Conventions
 * ------------------
 *  - Methods starting with 'build' work on Tansfer Objects (TOs). They either take a TO
 *    to be modified or create a TO or collection of TOs. Note that TOs in this context
 *    are just containers of data.  
 *  - Methods starting with 'get' return entities or collections of entities.
 * 
 * Primary Entry Points
 * --------------------
 *  - buildRequestAppointedAttorneyReportData
 *  - buildHireAttorneyReportData (aka Rights of Parents Worksheet)
 *  - buildParentalStatementReportData
 *  - buildSocialHistoryReportData
 */
public class InterviewHelper
{

	/***********************************************************************************************************
	 * 
	 */
	public static RequestAppointedAttorneyReportDataTO buildRequestAppointedAttorneyReportData(
			CreateRequestAttorneyReportEvent request)
	{
		String casefileId = request.getCasefileId();
		JuvenileCasefile casefile = JuvenileCasefile.find(casefileId);
		// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
		JuvenileCore juvenile = casefile.getJuvenile();
		//
		String juvenileId = casefile.getJuvenileNum();

		RequestAppointedAttorneyReportDataTO to = new RequestAppointedAttorneyReportDataTO();

		JJSReferral referral = getCurrentReferralForJuvenile(juvenile);
		JJSPetition petition = getPetitionForReferral(juvenile, referral);

		// Juvenile name
		to.setJuvenileName(formatNameFirstMiddleLastSuffix(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName(), juvenile.getNameSuffix()));
		to.setJuvenileNumber(juvenileId);

		if (petition != null)
		{
			to.setPetitionNumber(petition.getPetitionNum());
		}

		if (referral != null)
		{
			to.setCourtName(referral.getCourt() != null ? referral.getCourt().getDescription() : "");
			to.setCourtDate(DateHelper.format(referral.getCourtDate(), DateHelper.TXT_DATE));
		}

		// Guardian information
		ArrayList familyInfoList = new ArrayList();
		if (request.getGuardian1() != null && request.getGuardian1().isIncluded())
		{
			familyInfoList.add(request.getGuardian1());
			if (request.getGuardian2() != null && request.getGuardian2().isIncluded())
			{
				familyInfoList.add(request.getGuardian2());
			}
		}

		// Temps
		int dependents = 0;
		double rentExpenses = 0;
		double utilitiesExpenses = 0;
		double groceryExpenses = 0;
		double schoolExpenses = 0;
		double childSupportPaid = 0;
		double medicalExpenses = 0;
		double lifeInsurancePremium = 0;
		double propertyValue = 0;
		double intangibleValue = 0;
		double savings = 0;
		double otherIncome = 0;
		double foodStamps = 0;
		double childSupportReceived = 0;
		double tanfAfdc = 0;
		double annualGrossIncome = 0;
		double totalSelStepParentIncome = 0;
		double totalGuardianInHomeIncome = 0;
		double ssi = 0;

		RequestAppointedAttorneyReportDataTO.GuardianHomeInfo guardianHomeInfo = 
				new RequestAppointedAttorneyReportDataTO.GuardianHomeInfo();
		to.getGuardianHomeInfo().add(guardianHomeInfo);

		// Sum the expenses for both guardians.
		Iterator iter = familyInfoList.iterator();
		while (iter.hasNext())
		{
			FamilyInformationTO familyInfoTO = (FamilyInformationTO) iter.next();
			if( notNullNotEmptyString( familyInfoTO.getFamilyConstellationMemberId() ) )
			{
				FamilyConstellationMember constMember = FamilyConstellationMember.find(familyInfoTO.getFamilyConstellationMemberId());

				// Financial - use latest financial
				FamilyMemberFinancial financial = null;
				Iterator<FamilyMemberFinancial> finIter = constMember.getConstellationMemberFinancials().iterator();
				while (finIter.hasNext())
				{
					FamilyMemberFinancial thisFinancial = finIter.next();
					if (financial == null || financial.getEntryDate().compareTo(thisFinancial.getEntryDate()) < 0)
					{
						financial = thisFinancial;
					}
				}

				// only need financial statement of guardian 
				// who is in-home & selected by the user
				if (financial != null && constMember.isInHomeStatus() && familyInfoTO.isFinancialInfoSelected())
				{
					dependents += financial.getNumberOfDependents();

					rentExpenses = Math.max(rentExpenses, financial.getRentExpenses());
					utilitiesExpenses = Math.max(utilitiesExpenses, financial.getUtilitiesExpenses());
					groceryExpenses = Math.max(groceryExpenses, financial.getGroceryExpenses());
					schoolExpenses = Math.max(schoolExpenses, financial.getSchoolExpenses());
					childSupportPaid = Math.max(childSupportPaid, financial.getChildSupportPaid());
					medicalExpenses = Math.max(medicalExpenses, financial.getMedicalExpenses());
					lifeInsurancePremium = Math.max(lifeInsurancePremium, financial.getLifeInsurancePremium());

					propertyValue = Math.max(propertyValue, financial.getPropertyValue());
					intangibleValue = Math.max(intangibleValue, financial.getIntangibleValue());
					savings = Math.max(savings, financial.getSavings());
					childSupportReceived = Math.max(childSupportReceived, financial.getChildSupportReceived());
					otherIncome = Math.max(foodStamps, financial.getOtherIncome());
					foodStamps = Math.max(foodStamps, financial.getFoodStamps());
					tanfAfdc = Math.max(tanfAfdc, financial.getTanfAfdc());
					ssi = Math.max(ssi, financial.getSsi());
				}
			}
		}
		double totalMonthlyExpense = rentExpenses + utilitiesExpenses 
				+ groceryExpenses + schoolExpenses 
				+ childSupportPaid + medicalExpenses + lifeInsurancePremium;
		double totalAnnualExpense = totalMonthlyExpense * 12;

		StringAppender relationToJuvenile = new StringAppender();

		// Process each guardian.
		iter = familyInfoList.iterator();
		while (iter.hasNext())
		{
			FamilyInformationTO familyInfoTO = (FamilyInformationTO) iter.next();

			RequestAppointedAttorneyReportDataTO.GuardianHomeInfo.GuardianInfo guardianInfo = 
					new RequestAppointedAttorneyReportDataTO.GuardianHomeInfo.GuardianInfo();
			guardianHomeInfo.getGuardianInfo().add(guardianInfo);

			if( notNullNotEmptyString( familyInfoTO.getFamilyConstellationMemberId() ) )
			{
				FamilyConstellationMember constMember = FamilyConstellationMember.find(
						familyInfoTO.getFamilyConstellationMemberId());
				FamilyMember famMember = constMember.getTheFamilyMember();

				guardianInfo.setGuardian(formatName(famMember.getFirstName(), famMember.getMiddleName(), famMember.getLastName()));
				guardianInfo.setResidesWith(constMember.isInHomeStatus() ? "Yes" : "No");

				Iterator addresses = famMember.getAddresses().iterator();
				if (addresses.hasNext())
				{
					guardianInfo.setAddress(InterviewHelper.formatAddress((Address) addresses.next()));
				}

				Iterator phones = famMember.getPhoneNumbers().iterator();
				if (phones.hasNext())
				{
					guardianInfo.setPhone(formatPhone(((FamilyMemberPhone) phones.next()).getPhoneMaster()));
				}

				// Financial - use latest financial
				FamilyMemberFinancial financial = null;
				Iterator finIter = constMember.getConstellationMemberFinancials().iterator();
				while (finIter.hasNext())
				{
					FamilyMemberFinancial thisFinancial = (FamilyMemberFinancial) finIter.next();
					if (financial == null || financial.getEntryDate().compareTo(thisFinancial.getEntryDate()) < 0)
					{
						financial = thisFinancial;
					}
				}

				/**
				 * Other Reponsible Adult Income: Sum of the user-identified
				 * JUVENILE_FAMILY_EMPLOYMENT.AnnualGrossIncome of the family member
				 * where JUVENILE_FAMILY_CONSTELLATION.Relationship = StepMother or
				 * StepFather and JUVENILE_ADDRESS data is the same as the guardian that
				 * lives in the home AND the step-parent is NOT the juvenile's guardian.
				 */
				// totalSelStepParentIncome > 0 means that it has been calc before.
				if (constMember.isInHomeStatus())
				{
					if (totalSelStepParentIncome == 0)
					{
						Iterator stepParentsEmpIter = request.getStepParentEmploymentIds().iterator();
						while (stepParentsEmpIter.hasNext())
						{
							FamilyMemberEmployment emp = FamilyMemberEmployment.find((String) stepParentsEmpIter.next());

							double annualNetIncome = emp.getAnnualNetIncome();
							if (annualNetIncome == 0)
							{
								annualNetIncome = calculateAnnualIncome(emp.getSalary(), emp.getSalaryRateId(), emp.getWorkHours());
							}

							totalSelStepParentIncome += annualNetIncome;
						}
					}
					guardianInfo.setIncomeOfOtherAdult(CurrencyHelper.format(totalSelStepParentIncome));
				}
				else
				{
					guardianInfo.setIncomeOfOtherAdult("N/A");
				}

				if (financial != null)
				{
					if (financial.getOtherIncome() >= 0.01)
					{
						guardianInfo.setHasOtherIncome("<u> X </u>");
					}
				}

				StringAppender guardianJobs = new StringAppender();
				Iterator emps = familyInfoTO.getEmploymentHistory().iterator();

				annualGrossIncome = 0;// reset annual gross income
				while (emps.hasNext())
				{
					EmploymentHistoryTO empTO = (EmploymentHistoryTO) emps.next();
					if (empTO.isIncluded())
					{
						FamilyMemberEmployment emp = FamilyMemberEmployment.find(empTO.getOID());

						if (emp.getAnnualNetIncome() == 0)
						{
							annualGrossIncome += calculateAnnualIncome(emp.getSalary(), emp.getSalaryRateId(), emp.getWorkHours());
						}
						else
						{
							annualGrossIncome += emp.getAnnualNetIncome();
						}

						guardianJobs.append(emp.getCurrentEmployer(), "; ");
						guardianJobs.append(emp.getJobTitle(), ", ");
					}
				}

				// only guardian who is in home, will contribute to total support income
				if (constMember.isInHomeStatus())
				{
					totalGuardianInHomeIncome += annualGrossIncome;
				}

				guardianInfo.setAnnualGrossIncome(CurrencyHelper.format(annualGrossIncome));
				guardianInfo.setAnnualNetIncome(CurrencyHelper.format(annualGrossIncome - totalAnnualExpense));
				guardianInfo.setEmploymentTitle(guardianJobs.toString());

				relationToJuvenile.append(constMember.getRelationshipToJuvenile().getDescription(), "; ");
			}

		}

		/**
		 * For the family member where the JUVENILE_FAMILY_CONSTELLATION.InHome
		 * status = Yes, use their JUVENILE_FAMILY_EMPLOYMENT.AnnualGrossIncome (+)
		 * 1/2 of amount in Other Reponsible Adult Income (+)
		 * {JUVENILE_FAMILY_FINANCIAL.OtherIncome x 12}.
		 */
		guardianHomeInfo.setTotalSupportingIncome(CurrencyHelper.format(
				totalGuardianInHomeIncome + (totalSelStepParentIncome / 2) + (otherIncome * 12)));

		guardianHomeInfo.setDependents(Integer.toString(dependents));
		guardianHomeInfo.setHousePaymentExpenses(CurrencyHelper.format(rentExpenses));
		guardianHomeInfo.setUtilitiesExpenses(CurrencyHelper.format(utilitiesExpenses));
		guardianHomeInfo.setFoodExpenses(CurrencyHelper.format(groceryExpenses));
		guardianHomeInfo.setSchoolExpenses(CurrencyHelper.format(schoolExpenses));
		guardianHomeInfo.setChildSupportPaid(CurrencyHelper.format(childSupportPaid));
		guardianHomeInfo.setMedicalExpenses(CurrencyHelper.format(medicalExpenses));
		guardianHomeInfo.setInsuranceExpense(CurrencyHelper.format(lifeInsurancePremium));
		guardianHomeInfo.setPropertyValue(CurrencyHelper.format(propertyValue));
		guardianHomeInfo.setIntangibleValue(CurrencyHelper.format(intangibleValue)); // Stocks
		guardianHomeInfo.setSavings(CurrencyHelper.format(savings));
		guardianHomeInfo.setChildSupportReceived(CurrencyHelper.format(childSupportReceived));
		guardianHomeInfo.setOtherIncome(CurrencyHelper.format(otherIncome));
		guardianHomeInfo.setFoodStamps(CurrencyHelper.format(foodStamps));
		guardianHomeInfo.setTANF(CurrencyHelper.format(tanfAfdc));
		guardianHomeInfo.setTotal(CurrencyHelper.format(totalMonthlyExpense));
		guardianHomeInfo.setSsi(CurrencyHelper.format(ssi));
		guardianHomeInfo.setGuardianRelationToJuvenile(relationToJuvenile.toString());

		OfficerProfile officer = casefile.getProbationOfficer();
		to.setProbationOfficer(formatName(officer.getFirstName(), officer.getMiddleName(), officer.getLastName()));
		to.setJpoSupervisor(formatName(officer.getManagerFirstName(), officer.getManagerMiddleName(), officer.getManagerLastName()));

		if (request.getNotes() != null)
		{
			to.setNotes(request.getNotes());
		}

		return to;
	}

	/*
	 * 
	 */
	private static List getFutureAssignedReferrals(String casefileId, String juvenileNum)
	{
		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR, 23);
		today.set(Calendar.MINUTE, 59);
		today.set(Calendar.SECOND, 59);
		today.set(Calendar.MILLISECOND, 59);

		List assignedReferrals = new ArrayList();

		Iterator referrals = Assignment.findAll("caseFileId", casefileId);
		while (referrals.hasNext())
		{
			Assignment ref = (Assignment) referrals.next();

			GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
			refEvent.setJuvenileNum(juvenileNum);
			refEvent.setReferralNum(ref.getReferralNumber());
			Iterator i = JJSReferral.findAll(refEvent);
			if (i != null && i.hasNext())
			{
				JJSReferral referral = (JJSReferral) i.next();
				if (referral.getCourtDate() != null && referral.getCourtDate().after(today.getTime()))
				{
					assignedReferrals.add(referral);
				}
			}
		}
		return assignedReferrals;
	}

	/*
	 * 
	 */
	private static Date getEarliestOffenseDate(JJSReferral referral)
	{
		Date earliestOffenseDate = null;

		return earliestOffenseDate;
	}

	/**
	 * a.k.a. - Rights of Parents Worksheet.
	 */
	public static HireAttorneyReportDataTO buildHireAttorneyReportData(CreateHireAttorneyReportEvent request)
	{
		String casefileId = request.getCasefileId();
		String juvenileNum = request.getJuvenileNum();

		HireAttorneyReportDataTO to = new HireAttorneyReportDataTO();
		to.setVictimsPhysicalInjuries(request.getVictimsPhysicalInjuries());

		List futureAssignedReferrals = getFutureAssignedReferrals(casefileId, juvenileNum);
		List jjsTOList = new ArrayList();

		// A JOT record may be associated to multiple JOTCharge.
		// Since JJS and JOT data is only linked from Petition to
		// JOTCharge through petition number, a HashMap will put the
		// JOTCharges to their respective JOT Parents.
		HashMap jotMap = new HashMap();

		int referralsCount = futureAssignedReferrals.size();
		for (int i = 0; i < referralsCount; i++)
		{
			JJSReferral referral = (JJSReferral) futureAssignedReferrals.get(i);

			// get all of the petition by referral number & juvenile number
			GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
			petitionEvent.setJuvenileNum(juvenileNum);
			petitionEvent.setReferralNum(referral.getReferralNum());

			Iterator petitionIter = JJSPetition.findAll(petitionEvent);

			while (petitionIter.hasNext())
			{
				JJSPetition petition = (JJSPetition) petitionIter.next();
				List jotCharges = getChargesForPetition(petition);

				// Only use JJS Offense if JOT Record does not exist, since
				// JOTCharge takes precedence over JJS Offense.
				if (jotCharges == null || jotCharges.size() < 1)
				{
					Date earliestOffenseDate = null;
					GetJuvenileCasefileOffensesEvent casefileOffensesEvent = new GetJuvenileCasefileOffensesEvent();
					casefileOffensesEvent.setJuvenileNum(juvenileNum);
					casefileOffensesEvent.setReferralNum(referral.getReferralNum());
					Iterator offenses = JJSOffense.findAll(casefileOffensesEvent);
					while (offenses.hasNext())
					{
						JJSOffense jjsOffense = (JJSOffense) offenses.next();
						if (earliestOffenseDate == null || earliestOffenseDate.after(jjsOffense.getOffenseDate()))
						{
							earliestOffenseDate = jjsOffense.getOffenseDate();
						}
					}

					// use petition records.
					JOTChargeTO jotChargeTO = new JOTChargeTO();
					jotChargeTO.setOffenseDate(earliestOffenseDate);
					jotChargeTO.setChargeDescription(petition.getOffenseCode().getLongDescription());
					jotChargeTO.setOffenseLevel(petition.getOffenseCode().getCategory());
					jotChargeTO.setPenalCode(petition.getOffenseCode().getCitationCode());
					jjsTOList.add(jotChargeTO);
				}
				else
				{
					buildJOTData(jotCharges, jotMap);
				}
			}
		}

		to.setJjsOffenses(jjsTOList);
		ArrayList test = new ArrayList();
		test.addAll(jotMap.values());
		to.setJotRecord(test);

		Iterator<CreateHireAttorneyReportEvent.GuardianInfo> guardianIter = request.getGuardianInfo().iterator();
		while (guardianIter.hasNext())
		{
			CreateHireAttorneyReportEvent.GuardianInfo guardianInfo = guardianIter.next();

			FamilyConstellationMember guardian = FamilyConstellationMember.find(guardianInfo.getGuardianId());
			FamilyMember member = guardian.getTheFamilyMember();

			HireAttorneyGuardianDataTO guardianTO = new HireAttorneyGuardianDataTO();
			to.getGuardianRelatedInformation().add(guardianTO);

			guardianTO.setInformationExplainedTo(formatName(member.getFirstName(), member.getMiddleName(), member.getLastName()));
			guardianTO.setRelationshipToChild(guardian.getRelationshipToJuvenile().getDescription());
			guardianTO.setInformationExplained(guardianInfo.getContactMethod());
		}

		JuvenileCasefile casefile = JuvenileCasefile.find(casefileId);
		OfficerProfile officer = casefile.getProbationOfficer();
		to.setProbationOfficer(formatName(officer.getFirstName(), officer.getMiddleName(), officer.getLastName()));
		to.setDateInformationGiven(DateHelper.format(new Date(), DateHelper.TXT_DATE));

		return to;
	}

	/*
	 * This method will add to jotMap being passed in, any jot information, as
	 * well as append the jotCharges to the jot record.
	 */
	private static void buildJOTData(List jotCharges, HashMap jotMap)
	{
		if (jotMap == null)
		{
			jotMap = new HashMap();
		}

		int jotChargesSize = jotCharges.size();
		for( int chargesIdx = 0; chargesIdx < jotChargesSize; chargesIdx++)
		{
			JuvenileOffenderTrackingCharge charge = (JuvenileOffenderTrackingCharge) jotCharges.get(chargesIdx);
			JOTChargeTO jotChargeTO = new JOTChargeTO();
			jotChargeTO.setOffenseDate(charge.getOffenseDate());

			if (charge.getOffenseCode() != null)
			{
				if (charge.getOffenseCode().getJuvOffenseCode() != null)
				{
					JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",charge.getOffenseCode().getJuvOffenseCode());
					jotChargeTO.setChargeDescription(offenseCode.getLongDescription());
				}
				jotChargeTO.setOffenseLevel(charge.getOffenseCode().getLevel());
				jotChargeTO.setOffenseDegree(charge.getOffenseCode().getDegree());
				jotChargeTO.setPenalCode(charge.getOffenseCode().getPenalCode());
			}

			jotChargeTO.setCaseTypeGroup(charge.getCaseTypeGroup());
			if ("Y".equals(charge.getDrugInfluence()))
			{
				jotChargeTO.setAlcoholInfluence(true);
			}
			else
			{
				jotChargeTO.setAlcoholInfluence(false);
			}

			if (charge.getWeaponType() != null)
			{
				jotChargeTO.setWeaponUsed(true);
				jotChargeTO.setWeaponType(charge.getWeaponType().getDescription());
			}
			else if( notNullNotEmptyString( charge.getUnlistedWeapon() ) )
			{
				jotChargeTO.setWeaponUsed(true);
				jotChargeTO.setWeaponType(charge.getUnlistedWeapon());
			}
			else
			{
				jotChargeTO.setWeaponUsed(false);
			}

			// Get the JOT Information for each charge
			if (jotMap.get(charge.getDaLogNum()) == null)
			{
				JOTTO jotto = new JOTTO();
				JuvenileOffenderTracking jot = JuvenileOffenderTracking.find(charge.getDaLogNum());
				jotto.setDateTakenIntoCustody(DateHelper.format(jot.getArrestDate(), DateHelper.TXT_DATE));
				jotto.setTimeTakenIntoCustody(formatTime(jot.getArrestTime()));
				jotto.setJuvenileCoActors(jot.getCoDefendants());

				List adultCoActors = new ArrayList();
				Iterator<JuvenileOffenderTrackingCoActor> coActors = 
						JuvenileOffenderTrackingCoActor.findAllByDaLogNum(charge.getDaLogNum());
				while (coActors.hasNext())
				{
					StringBuffer sb = new StringBuffer();
					JuvenileOffenderTrackingCoActor coActor = coActors.next();
					if (sb.length() > 0)
					{
						sb.append("; ");
					}
					sb.append(coActor.getName());
					sb.append(" (");
					sb.append(coActor.getAge());
					sb.append(")");
					adultCoActors.add(sb.toString());
				}
				jotto.setAdultCoActors(adultCoActors);

				List propertyLossList = new ArrayList();
				Iterator<JuvenileOffenderTrackingProperty> properties = 
						JuvenileOffenderTrackingProperty.findAllByDaLogNum(charge.getDaLogNum());
				while (properties.hasNext())
				{
					JuvenileOffenderTrackingProperty property = properties.next();
					if( notNullNotEmptyString( property.getValue() ) )
					{
						// make this into a list
						PropertyLossTO propertyLoss = new PropertyLossTO();
						propertyLoss.setDescription(property.getDescription());

						if( notNullNotEmptyString( property.getValue() ))
						{
							float damageAmt = Float.parseFloat(property.getValue()) / 100;
							Locale locale = Locale.US; // will need to set this in configuration file
							propertyLoss.setValue(NumberFormat.getCurrencyInstance(locale).format(damageAmt));
						}
						propertyLossList.add(propertyLoss);
					}
				}
				jotto.setPropertyLossList(propertyLossList);
				List jotChargesList = new ArrayList();
				jotChargesList.add(jotChargeTO);
				jotto.setJotCharges(jotChargesList);
				jotMap.put(charge.getDaLogNum(), jotto);
			}
			else
			{
				JOTTO jotto = (JOTTO) jotMap.get(charge.getDaLogNum());
				jotto.getJotCharges().add(jotChargeTO);
			}
		}
	}

	/**
	 * 
	 */
	//public static void buildParentalStatementReportData(
			//ParentalStatementReportDataTO to, Juvenile juvenile, JuvenileCasefile casefile)
	public static void buildParentalStatementReportData(
		ParentalStatementReportDataTO to, JuvenileCore juvenile, JuvenileCasefile casefile)
	{
		OfficerProfile officer = casefile.getProbationOfficer();
		if (officer != null)
		{
			String locationId = officer.getJuvLocationId();

			if( notNullNotEmptyString( locationId ) )
			{
				Location juvLocation = Location.find(locationId);
				Address juvLocationAddress = juvLocation.getAddress();
				messaging.contact.to.Address addressTo = new messaging.contact.to.Address();
				addressTo.setStreetNum(juvLocationAddress.getStreetNum());
				addressTo.setStreetName(juvLocationAddress.getStreetName());
				//bug fix for 143584
				//addressTo.setStreetTypeCode(juvLocationAddress.getStreetType().getDescription());
				if (juvLocationAddress.getStreetType() != null)
				{
				    addressTo.setStreetTypeCode(juvLocationAddress.getStreetType().getDescription());
				}
				//
				addressTo.setAptNum(juvLocationAddress.getAptNum());
				addressTo.setCity(juvLocationAddress.getCity());

				if (juvLocationAddress.getState() != null)
				{
					addressTo.setStateCode(juvLocationAddress.getState().getDescription());
				}
				addressTo.setZipCode(juvLocationAddress.getZipCode());
				addressTo.setAdditionalZipCode(juvLocationAddress.getAdditionalZipCode());

				// uiAddress
				to.setLocationAddress(addressTo);
				to.setLocationAddress1(addressTo);
				to.setLocationAddress2(addressTo);

				if (officer.getJuvUnitId() != null && !"".equals(officer.getJuvUnitId()))
				{
					JuvLocationUnit juvLocUnit = JuvLocationUnit.find(officer.getJuvUnitId());
					if (juvLocUnit != null)
					{
						to.setLocationUnitPhone(new PhoneNumberBean(juvLocUnit.getPhoneNumber()));
						to.setFormattedPhoneNumber(new PhoneNumberBean(juvLocUnit.getPhoneNumber()));
						to.setLocationUnitName(juvLocUnit.getLocationUnitName());
					}
				}
			}
		}

		to.setJuvenileName(formatName(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName()));

		JJSReferral referral = getCurrentReferralForJuvenile(juvenile);
		if (referral != null)
		{
			if (referral.getCourtDate() != null)
			{
				to.setCourtDate(DateHelper.format(referral.getCourtDate(), DateHelper.TXT_DATE).toUpperCase());
			}

			if (referral.getCourt() != null)
			{
				to.setCourtName(referral.getCourt().getDescription());
			}
		}

		JJSPetition petition = getPetitionForReferral(juvenile, referral);
		if (petition != null)
		{
			to.setPetitionNumber(petition.getPetitionNum());
		}
	}

	/*
	 * 
	 */
	private static String formatTime(String time)
	{
		if (time != null && time.length() > 0)
		{
			StringBuffer sb = new StringBuffer(time);
			sb.insert(2, ':');
			return sb.toString();
		}
		else
		{
			return time;
		}
	}

	/**
	 * 
	 */
	// Profile stripping fix task 97536
	//public static void buildSocialHistoryReportData(
			//SocialHistoryReportDataTO to, Juvenile juvenile, Juvenile jcJuvenile, JuvenileCasefile casefile)
	public static void buildSocialHistoryReportData(
		SocialHistoryReportDataTO to, JuvenileCore juvenile, Juvenile jcJuvenile, JuvenileCasefile casefile)
	{
		String juvenileId = juvenile.getOID().toString();
		String casefileId = casefile.getOID().toString();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		to.setJuvenileName(formatNameLastFirstMiddleSuffix(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName(), juvenile.getNameSuffix()));
		
		//Gender
		if (juvenile.getSexCodeDescription() != null)
		{
			to.setGender(juvenile.getSexCodeDescription());
		}
		
		//Race
		if (juvenile.getRaceCodeDescription() != null)
		{
			to.setRace(juvenile.getRaceCodeDescription());
		}
		
		// Juvenile Date of Birth
		if (juvenile.getDateOfBirth() != null)
		{
			//juvenile.
			to.setVerifiedDOB(jcJuvenile.isVerifiedDOB());
			to.setDateOfBirth(sdf.format(juvenile.getDateOfBirth()));
			to.setAgeInYears(String.valueOf(juvenile.getAgeInYears(juvenile.getDateOfBirth())));
		}

		// Juvenile Number
		to.setJuvenileNumber(juvenile.getJuvenileNum());
		// Hispanic //U.S 88526
		if(jcJuvenile.getHispanic()!=null && jcJuvenile.getHispanic().equalsIgnoreCase("Y")){
		    to.setHispanic(true);
		}else{
		    to.setHispanic(false);
		}//U.S 88526
		// Hispanic
		to.setMultiracial(jcJuvenile.isMultiracial());

		// Date interviewed.
		Interview last = Interview.findLastForCasefile(casefile.getOID().toString());
		if (last != null)
		{
			to.setDateInterviewed(sdf.format(last.getInterviewDateTime()));
		}

		// TODO Deferred until Keymap is implemented
		to.setCupsUnit("");

		to.getGangTraits().addAll(buildTraitsByTraitsCategory(juvenileId, PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_GANGS));
		to.getStrengthTraits().addAll(buildTraitsByTraitsCategory(juvenileId, PDJuvenileCaseConstants.TRAIT_CATEGORY_STRENGTHS));
		to.getSchoolBehaviorTraits().addAll(buildTraitsByTraitsCategory(juvenileId, PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SCHOOL_BEHAVIOR));
		to.getSchoolAttendanceTraits().addAll(buildTraitsByTraitsCategory(juvenileId, PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_SCHOOL_ATTENDANCE));
		to.getEducationalPerformanceTraits().addAll(buildTraitsByTraitsCategory(juvenileId, PDJuvenileCaseConstants.TRAIT_CATEGORY_NAME_EDUCATIONAL_PERFORMANCE));

		to.getSubstanceAbuseTraits().addAll(buildSubstanceAbuseTraits(juvenileId));
		to.getSubstanceAbuseInformation().addAll(buildSubstanceAbuseInformation(juvenileId));
		to.getEducationalHistory().addAll(buildSchoolHistory(juvenileId)); // Has to be here for school tab to show data
		to.getEmploymentHistory().addAll(buildEmploymentHistory(juvenileId));
		to.getFamilyFinancialHistory().addAll(buildFinancialHistory(juvenile));
		to.getFamilyInformation().addAll(buildFamilyInformation(juvenile)); //Builds complete list of all Family members from all constellation
		
		to.setNumberInHousehold(Integer.toString(getNumberLivingInHome(to.getFamilyFinancialHistory())));

		//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
		if(to.isWarrantHistoryNeeded())
			to.getWarrantHistory().addAll(buildWarrantHistory(juvenileId));
		
		to.getReferralHistory().addAll(buildReferralHistory(juvenile));
		to.getJPCourtReferrals().addAll(buildJPCourtReferrals(juvenile));
		to.getJuvenileOffenses().addAll(InterviewHelper.buildJuvenileOffenses(juvenileId));
		to.getPresentOffenses().addAll(InterviewHelper.buildOffenses(juvenileId, casefileId));
		to.getPresentOffensesForGeneric().addAll(InterviewHelper.buildPresentReferralsForGenericReport(juvenileId, casefileId));
		to.getJuvenileFacilityStayRecord().addAll(buildJuvenileFacilityStayRecord(juvenileId));

		Iterator<SupervisionRuleTO> rules = buildSupervisionRules(casefileId).iterator();
		while (rules.hasNext())
		{
			SupervisionRuleTO rule = rules.next();
			if ("N".equalsIgnoreCase(rule.getCompletionStatusId()))
			{
				to.getNoncompliantSupervisionRules().add(rule);
			}
			else
			{
				to.getCompliantSupervisionRules().add(rule);
			}
		}

		to.setAllGuardiansInHome(true);
		FamilyConstellation constellation = juvenile.getCurrentFamilyConstellation();
		Iterator<FamilyConstellationMember> members = constellation.getFamilyConstellationMembers().iterator();
		while (members.hasNext())
		{
			FamilyConstellationMember constMember = members.next();
			if (constMember.isGuardian() && !constMember.isInHomeStatus())
			{
				to.setAllGuardiansInHome(false);
				break;
			}
		}

		OfficerProfile officer = casefile.getProbationOfficer();
		to.setProbationOfficer(formatName(officer.getFirstName(), officer.getMiddleName(), officer.getLastName()));
		to.setJpoSupervisor(formatName(officer.getManagerFirstName(), officer.getManagerMiddleName(), officer.getManagerLastName()));
		
		List referralDetails = buildReferralDetails(to);
		to.setReferralDetails(referralDetails);
		to.setDetentionAdmitDate(DateHelper.format(getDetentionAdmitDate(juvenile.getJuvenileNum(), referralDetails), DateHelper.STD));
	}

	/**
	 * Prepare the Social History report data for printing. The data has already
	 * been presented to the user and selections made to it.
	 * @throws ParseException 
	 */
	public static void prepareSocialHistoryReportData(SocialHistoryReportDataTO reportTO, String casefileId, boolean reportToReferee ) throws ParseException
	{
		// Update JPCourtConvictions using the first non-excluded JPCourtReferralTO.
		JPCourtReferralTO referral = null;
		Iterator<JPCourtReferralTO> iter = reportTO.getJPCourtReferrals().iterator();

		// reset in case report is re-generated with different data
		reportTO.getJPCourtReferralConvictions().clear();
		while (iter.hasNext())
		{
			referral = iter.next();
			if (referral.isExcluded())
			{
				continue;
			}
			else
			{
				reportTO.getJPCourtReferralConvictions().addAll(buildJPCourtReferralConvictions(referral));
				break;
			}
		}
 
		if( !reportTO.isReportToRefereeInitiation() )
		{
			reportTO.setSubsequentReferralDetails(buildSubsequentReferralDetails(reportTO, casefileId));	
		}
		//changed for US #14450
		List prevAndCurrReferrals = buildPreviousAndCurrReferralDetails(reportTO);
		List previousReferrals = new ArrayList();
		List currentReferrals = new ArrayList();
		//separate current and previous referrals
		String juvenileNum = reportTO.getJuvenileNumber();
		List<ReferralTO> supvPrgrmRefList = buildSupervisionDetails(juvenileNum); // added for Task 37996
		//Date todaysDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date todaysDate = sdf.parse(sdf.format(new Date()));
		for(Iterator<ReferralTO> refIter=prevAndCurrReferrals.iterator(); refIter.hasNext();)
		{
			ReferralTO refTO = (ReferralTO) refIter.next();
			Date refCourtDate=new Date();
			if(refTO.getReferralData().getCourtDate()!=null)
				refCourtDate=sdf.parse(sdf.format(refTO.getReferralData().getCourtDate()));
			if(refTO.getReferralData().getCourtDate() == null || refCourtDate.before(todaysDate))
				previousReferrals.add(refTO);							
			else
				currentReferrals.add(refTO);							 
		}
		
		reportTO.setPreviousReferralDetails(previousReferrals);
		reportTO.setCurrentReferralDetails(currentReferrals);
		reportTO.setSupPrgmRefList(supvPrgrmRefList);  // added for Task 37996
		//added for transferred offenses: task 11181
		 List juvTransferredOffenceList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(reportTO.getJuvenileNumber());
		 if(juvTransferredOffenceList != null && juvTransferredOffenceList.size()>0 ){
		 reportTO.setJuvTransferredOffenses(juvTransferredOffenceList);
		 }
		
		
				
		// Update Guardian Financial calculations.
		Iterator famIter = reportTO.getFamilyFinancialHistory().iterator();
		while (famIter.hasNext())
		{
			FamilyInformationTO famInfo = (FamilyInformationTO) famIter.next();

			double annualGrossIncome = 0;

			Iterator<EmploymentHistoryTO> empIter = famInfo.getEmploymentHistory().iterator();
			while (empIter.hasNext())
			{
				EmploymentHistoryTO empInfo = empIter.next();

				if (empInfo.isIncluded())
				{
					annualGrossIncome += empInfo.getAnnualGrossIncome();
				}
			}

			annualGrossIncome += famInfo.getTANFAssistance() * 12;
			annualGrossIncome += famInfo.getOtherIncome();

			famInfo.setTotalGross(annualGrossIncome);
		}

	}
	/**
	 * 
	 * @param reportTO
	 * @param juvenileNum
	 * @return
	 */
	// added for Task 37996
	private static List<ReferralTO> buildSupervisionDetails(String juvenileNum) {
		List<ReferralTO> supvPrgmDetails = new ArrayList<ReferralTO>();
		SimpleDateFormat sdfs = new SimpleDateFormat("MMM d, yyyy");

		SearchJuvenileProfileCasefileListEvent search = (SearchJuvenileProfileCasefileListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);
		search.setJuvenileId(juvenileNum);

		List<JuvenileProfileCasefileListResponseEvent> caseFileCollection = MessageUtil.postRequestListFilter(search,JuvenileProfileCasefileListResponseEvent.class);

		SupervisionTypeTJJDMap supervisionType = null;
		String supID = null;
		String supNumber = null;
		// Since not able to get required results on sorting string activationDate in final collection , sorting in the original collection 
		Collections.sort(caseFileCollection);
		
		
		Iterator<JuvenileProfileCasefileListResponseEvent> juvCasefileRespEvtItr = caseFileCollection.iterator();
		
		while (juvCasefileRespEvtItr.hasNext()) {
			ReferralTO refTo = new ReferralTO();
			JuvenileProfileCasefileListResponseEvent juvenileCasefileResponseEvent = juvCasefileRespEvtItr.next();
			if (juvenileCasefileResponseEvent != null) {
				supID = juvenileCasefileResponseEvent.getSupervisionTypeId();
				supNumber = juvenileCasefileResponseEvent.getSupervisionNum();

				// call the codetable.
				Iterator<SupervisionTypeTJJDMap> stpItr = SupervisionTypeTJJDMap.findAll("supervisionTypeId", supID);
				while (stpItr.hasNext()) {
					supervisionType = stpItr.next();
					if (supervisionType != null) {
						String supervisonTypeId = supervisionType.getSupervisionTypeId();
						if (supID.equalsIgnoreCase(supervisonTypeId)) {
							if(supervisionType.getSplCategoryId()!=null){
							// Checking supervisionTypeId description against TJJD code table value
							if(supervisionType.getSplCategoryId().equalsIgnoreCase("SF")  || supervisionType.getSplCategoryId().equalsIgnoreCase("SC"))
							 {
								refTo.setSupervisionCategoryId(juvenileCasefileResponseEvent.getSupervisionType());
								
								if(juvenileCasefileResponseEvent.getAssignmentAddDate()!= null){
									Date casefileAssignAddDate = DateUtil.stringToDate(juvenileCasefileResponseEvent.getAssignmentAddDate(), "MM/d/yyyy");
									refTo.setActivationDate(sdfs.format(casefileAssignAddDate));
								
								}
								String superCategory = juvenileCasefileResponseEvent.getSupervisionOutcomeDescriptionId();
								String superCategoryAdd = superCategory+"/";
								// Actual End Date from JCCASECLOSING TABLE 
								GetCasefileClosingDetailsEvent rEvent = (GetCasefileClosingDetailsEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETCASEFILECLOSINGDETAILS);
								rEvent.setSupervisionNumber(supNumber);
								IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
								dispatch.postEvent(rEvent);
								CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
								CasefileClosingResponseEvent closeEvent = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse, CasefileClosingResponseEvent.class);
								
								//SuperVision Description ID Logic ( some of the values are null /empty)
								if (closeEvent !=null && closeEvent.getSupervisionOutcomeDescriptionId()!=null){
									if(!closeEvent.getSupervisionOutcomeDescriptionId().isEmpty()){
									String supervisionDescriptionID=closeEvent.getSupervisionOutcomeDescriptionId();
									List<JuvenileCodeTableChildCodesResponseEvent> supervisionCode = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
									Iterator<JuvenileCodeTableChildCodesResponseEvent> supervisionCodeItr= supervisionCode.iterator();
									while(supervisionCodeItr.hasNext()){
									JuvenileCodeTableChildCodesResponseEvent descriptionCodeCheck = supervisionCodeItr.next();
									String supDescriptionCode= descriptionCodeCheck.getCode();
									if(supervisionDescriptionID.equalsIgnoreCase(supDescriptionCode)){
										String supDescription= superCategoryAdd+descriptionCodeCheck.getDescription();
										refTo.setSupervisionOutcome(supDescription);}									
											}
										}else{
											refTo.setSupervisionOutcome(superCategory);					
										}
									} 
									else
									{refTo.setSupervisionOutcome(superCategory);					}
								
								
								if (closeEvent !=null && closeEvent.getSupervisionEndDate()!= null ){
																				
												refTo.setClosingDate(sdfs.format(closeEvent.getSupervisionEndDate()));
												
											} // Expected End Date Logic 
								else
											{
												GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent)EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE ) ;
												getCasefileEvent.setSupervisionNumber(supNumber) ;

												IDispatch dispatchE = EventManager.getSharedInstance( EventManager.REQUEST ) ;
												dispatchE.postEvent(getCasefileEvent) ;
												CompositeResponse response = (CompositeResponse)dispatch.getReply( ) ;

												JuvenileCasefileResponseEvent casefileE = (JuvenileCasefileResponseEvent)MessageUtil.filterComposite( response, JuvenileCasefileResponseEvent.class ) ;
												if(casefileE.getSupervisionEndDate()!= null){
													refTo.setClosingDate(sdfs.format(casefileE.getSupervisionEndDate()));}
													}
								supvPrgmDetails.add(refTo);
							}
						}
						}
					}
					
				}
			}// end of second while
		}// end of first while
		
/*		ArrayList sortFields = new ArrayList();
		sortFields.add(new BeanComparator("activationDate"));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(supvPrgmDetails, multiSort);
*/		
		
		return supvPrgmDetails;
	}

	 
	private static List buildSubsequentReferralDetails(SocialHistoryReportDataTO reportTO, String casefileId) {
		
		List temporaryScrubbedList = new ArrayList();
		List subsequentReferralDetails = new ArrayList();
		HashMap referralScrubHashMap = new HashMap();
		HashMap referralsNumbersAssociatedToCasefile = new HashMap();
		List presentReferrals = reportTO.getPresentOffensesForGeneric();
		List referralDetails = reportTO.getReferralDetails();
		List presentAllegedOffenses = reportTO.getJuvenileOffenses();
		int mostRecentReferralNumber = 0;
		int mostRecentRefNumber = 0;
		ReferralTO mostRecentReferral = null;
		
		
		
		//Place referral numbers assigned to casefile into a HashMap
		Iterator<Assignment> referralsAssignedToCasefile = Assignment.findAll("caseFileId", casefileId);
		while (referralsAssignedToCasefile.hasNext())
		{
			Assignment referralAssignedToCasefile = referralsAssignedToCasefile.next();
			referralsNumbersAssociatedToCasefile.put(referralAssignedToCasefile.getReferralNumber(), "");
		}
		
		//Add present referral to a scrub list
		Iterator<OffenseInformationTO>presentReferralsIter = presentReferrals.iterator();
		while( presentReferralsIter.hasNext() ){
			OffenseInformationTO presentReferralTO = presentReferralsIter.next();
			if (Integer.parseInt(presentReferralTO.getReferralNumber()) > mostRecentReferralNumber) {
				mostRecentReferralNumber = Integer.parseInt(presentReferralTO.getReferralNumber());
			}
			referralScrubHashMap.put(presentReferralTO.getReferralNumber(), "");
		}
		
		//Add presentAllegedReferral to a scrub list
		Iterator<OffenseInformationTO>presentAllegedOffensesIter = presentAllegedOffenses.iterator();
		while( presentAllegedOffensesIter.hasNext() ){
			OffenseInformationTO allegedReferralTO = presentAllegedOffensesIter.next();
			if (allegedReferralTO.isPresentAllegedOffense()) {
				if (Integer.parseInt(allegedReferralTO.getReferralNumber()) > mostRecentRefNumber) {
					mostRecentRefNumber = Integer.parseInt(allegedReferralTO.getReferralNumber());
				}
				referralScrubHashMap.put(allegedReferralTO.getReferralNumber(), "");
			}
		}
		
		//Add only referrals not on the present referral scrub list to the temporaryScrubList list
		for( Iterator<ReferralTO> referralIter = referralDetails.iterator(); referralIter.hasNext(); )
		{
			ReferralTO referral = referralIter.next();
			
			if (mostRecentReferralNumber == Integer.parseInt(referral.getReferralData().getReferralNumber())) {
				mostRecentReferral = referral;
			}
			
			if (!referralScrubHashMap.containsKey(referral.getReferralData().getReferralNumber())) 
			{
				temporaryScrubbedList.add(referral);
			}
			
		}
		
		//Add any referrals to subsequentReferralDetails from the temporaryScrubList that pass the citeria
		Iterator<ReferralTO> temporaryScrubbedListIter = temporaryScrubbedList.iterator();
		while (temporaryScrubbedListIter.hasNext())
		{
			
			ReferralTO referralTO = temporaryScrubbedListIter.next();
			boolean referralAssociatedToCaseFile = false;
			String courtId = referralTO.getReferralData().getCourtId();
			JJSPetition petition = null;
			
			if (referralsNumbersAssociatedToCasefile.containsKey(referralTO.getReferralData().getReferralNumber())) {
				referralAssociatedToCaseFile = true;
			}
			
			//Find out if referral has an Intake Decision
			String intakeDecisionCode = referralTO.getReferralData().getIntakeDecisionCode(); 
			
			// Retrieve the petitions for a referral (if one exist) by referral number & juvenile number
			GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
			petitionEvent.setJuvenileNum(reportTO.getJuvenileNumber());
			petitionEvent.setReferralNum(referralTO.getReferralData().getReferralNumber());
			Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
			//order by OID
			List<JJSPetition>  petitions = new ArrayList<JJSPetition>();
			while (petitionIter.hasNext()) 
			{
			    	petitions.add(petitionIter.next());
			    	
			}
			
			if ( petitions.size() > 0 ) {
				Collections.sort(petitions, new Comparator<JJSPetition>(){
        				@Override
        				public int compare(JJSPetition p1, JJSPetition p2) {
        				    return (p1.getOID().compareTo(p2.getOID()));
        				  }
				});
				Collections.reverse(petitions);
				petition = petitions.get(0);
			}
			/*if (petitionIter.hasNext()) 
			{
				petition = petitionIter.next();
			}*/
			//added for bug 12932: Show transferred Offense Details with correponding referral details section
			String refNumForSubsequent = referralTO.getReferralNumber();
			List juvTransferredOffenceList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(reportTO.getJuvenileNumber());
			
			if (referralAssociatedToCaseFile) // true
			{
				
				if (petition == null 
						&& (courtId == null || (courtId != null && courtId.length() == 0)) 
						&& (intakeDecisionCode != null 
								&& (intakeDecisionCode.equalsIgnoreCase("PIP") || intakeDecisionCode.equalsIgnoreCase("PAT") || intakeDecisionCode.equalsIgnoreCase("PSE") || intakeDecisionCode.equalsIgnoreCase("INF") || intakeDecisionCode.equalsIgnoreCase("FOP")) )
						) 
				{
					//added for Bug 12932 - checking Transferred Offense ref num with each prev referral details record and adding to the same bean.showing together in the report
					 for (Iterator juvTransferredOffenceListIter = juvTransferredOffenceList.iterator(); juvTransferredOffenceListIter.hasNext();) 
					 {
						 JuvenileCasefileTransferredOffenseResponseEvent juvTransferredOffence = (JuvenileCasefileTransferredOffenseResponseEvent) juvTransferredOffenceListIter.next();
						 String tempRefNum = juvTransferredOffence.getReferralNum();
						 if(tempRefNum !=null && tempRefNum.equals(refNumForSubsequent)){
							 referralTO.setReferralNumber(tempRefNum);
							 referralTO.setTransferredOffenseFromCounty(juvTransferredOffence.getCountyDesc());
							 referralTO.setTransferredOffenseOriginatingOff(juvTransferredOffence.getOffenseShortDesc());
							 referralTO.setTransferredOffenseAdjuDate(juvTransferredOffence.getAdjudicationDateStr());
							 referralTO.setTransferredOffensePresent(true);
							 reportTO.setHasSubsequentTransferredOffense(true);
						 }
					 }
					//ended
					
					subsequentReferralDetails.add(referralTO);
				}
				
			}
			else // false 
			{
				if (referralTO != null && 
						referralTO.getReferralData() != null && 
						referralTO.getReferralData().getReferralDate() != null &&
						mostRecentReferral != null &&
						mostRecentReferral.getReferralData() != null &&
						mostRecentReferral.getReferralData().getReferralDate() != null
						)
				{
					if (referralTO.getReferralData().getReferralDate().after(mostRecentReferral.getReferralData().getReferralDate())) {
						
						
						//added for Bug 12932 - checking Transferred Offense ref num with each prev referral details record and adding to the same bean.showing together in the report
						 for (Iterator juvTransferredOffenceListIter = juvTransferredOffenceList.iterator(); juvTransferredOffenceListIter.hasNext();) 
						 {
							 JuvenileCasefileTransferredOffenseResponseEvent juvTransferredOffence = (JuvenileCasefileTransferredOffenseResponseEvent) juvTransferredOffenceListIter.next();
							 String tempRefNum = juvTransferredOffence.getReferralNum();
							 if(tempRefNum !=null && tempRefNum.equals(refNumForSubsequent)){
								 referralTO.setReferralNumber(tempRefNum);
								 referralTO.setTransferredOffenseFromCounty(juvTransferredOffence.getCountyDesc());
								 referralTO.setTransferredOffenseOriginatingOff(juvTransferredOffence.getOffenseShortDesc());
								 referralTO.setTransferredOffenseAdjuDate(juvTransferredOffence.getAdjudicationDateStr());
								 referralTO.setTransferredOffensePresent(true);
								 reportTO.setHasSubsequentTransferredOffense(true);
							 }
						 }
						//ended
						subsequentReferralDetails.add(referralTO);
					}
				}
			}
			
		}

		ArrayList sortFields = new ArrayList();
		sortFields.add(new BeanComparator("referralNumber"));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(subsequentReferralDetails, multiSort);
		
		return subsequentReferralDetails;
	}
	//changed for US #14450
	private static List buildPreviousAndCurrReferralDetails(SocialHistoryReportDataTO reportTO) {
		
		List previousReferralDetails = new ArrayList();
		List referralScrubList = new ArrayList();
		List subsequentReferralDetails = reportTO.getSubsequentReferralDetails();
		List presentReferrals = reportTO.getPresentOffensesForGeneric();
		List referralDetails = reportTO.getReferralDetails();
		List presentAllegedOffenses = reportTO.getJuvenileOffenses();
		
		//Add subsequent referral #s to scrubList
		Iterator<ReferralTO> subReferralDetailsIter = subsequentReferralDetails.iterator();
		while( subReferralDetailsIter.hasNext() ){
			ReferralTO subReferralTO = subReferralDetailsIter.next();
			referralScrubList.add(subReferralTO.getReferralData().getReferralNumber());
		}
		
		//Add present referral #s to scrubList
		Iterator<OffenseInformationTO>presentReferralsIter = presentReferrals.iterator();
		while( presentReferralsIter.hasNext() ){
			OffenseInformationTO presentReferralTO = presentReferralsIter.next();
			referralScrubList.add(presentReferralTO.getReferralNumber());
		}
		
		//Add presentAllegedReferral to a scrub list
		Iterator<OffenseInformationTO>presentAllegedOffensesIter = presentAllegedOffenses.iterator();
		while( presentAllegedOffensesIter.hasNext() ){
			OffenseInformationTO allegedReferralTO = presentAllegedOffensesIter.next();
			if (allegedReferralTO.isPresentAllegedOffense()) {
				referralScrubList.add(allegedReferralTO.getReferralNumber());
			} 
		}
		
		for( Iterator<ReferralTO> referralIter = referralDetails.iterator(); referralIter.hasNext(); )
		{
			ReferralTO referral = referralIter.next();
			
			boolean include = true;
			
			/*Iterator referralsInfo = referralScrubList.keySet().iterator();
			while(referralsInfo.hasNext()) {
				
				String referralNumber = (String)referralsInfo.next();
				Boolean included = (Boolean)referralScrubList.get(referralNumber);
				 
				 if (referral.getReferralData().getReferralNumber().equalsIgnoreCase(referralNumber) && included == true)
				 {
					include = false;
					break;
				}
				
			}*/
			
			Iterator<String> referralNumbers = referralScrubList.iterator();
			while( referralNumbers.hasNext() ){
			 	String referralNumber = referralNumbers.next();
			 	if (referral.getReferralData().getReferralNumber().equalsIgnoreCase(referralNumber))
			 	{
			 		include = false;
			 		break;
			 	}
			}
			
			if (include) 
			{
				//added for Bug 12932 - checking Transferred Offense ref num with each prev referral details record and adding to the same bean.showing together in the report
				String refNumForPrev = referral.getReferralNumber();
				List juvTransferredOffenceList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(reportTO.getJuvenileNumber());
				
				 for (Iterator juvTransferredOffenceListIter = juvTransferredOffenceList.iterator(); juvTransferredOffenceListIter.hasNext();) 
				 {
					 JuvenileCasefileTransferredOffenseResponseEvent juvTransferredOffence = (JuvenileCasefileTransferredOffenseResponseEvent) juvTransferredOffenceListIter.next();
					 String tempRefNum = juvTransferredOffence.getReferralNum();
					 if(tempRefNum !=null && tempRefNum.equals(refNumForPrev)){
						 referral.setReferralNumber(tempRefNum);
						 referral.setTransferredOffenseFromCounty(juvTransferredOffence.getCountyDesc());
						 referral.setTransferredOffenseOriginatingOff(juvTransferredOffence.getOffenseShortDesc());
						 referral.setTransferredOffenseAdjuDate(juvTransferredOffence.getAdjudicationDateStr());
						 referral.setTransferredOffensePresent(true);
						 reportTO.setHasPreviousTransferredOffense(true);
					 }
				 }
				//ended
				previousReferralDetails.add(referral);				
				
			}
		}
		ArrayList sortFields = new ArrayList();
		sortFields.add(new ReverseComparator(new BeanComparator("referralNumber")));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(previousReferralDetails, multiSort);
		
		return previousReferralDetails;
	}
	
	private static ReferralTO getPresentReferral(List referralDetails, String casefileId)
	{
		ReferralTO futureReferral = null;
		List limitedList = new ArrayList();

		//Compare Referrals Assinged to the Casefile versus All Referrals
		//Only keep referrals assigned to casefile
		if( referralDetails != null && referralDetails.size() > 0 )
		{
			for( Iterator<ReferralTO> referralIter = referralDetails.iterator(); 
			referralIter.hasNext(); /* empty */)
			{
				ReferralTO referral = referralIter.next();
				Iterator<Assignment> referralsAssignedToCasefile = Assignment.findAll("caseFileId", casefileId);
				while( referralsAssignedToCasefile.hasNext() ){
					Assignment assignment = referralsAssignedToCasefile.next();
					if (referral.getReferralData().getReferralNumber().equalsIgnoreCase(assignment.getReferralNumber())) {
						limitedList.add(referral);
						break;
					}
				}
				
			}
		}
		
		//Get the present referral
		if( limitedList != null && limitedList.size() > 0 )
		{
			for( Iterator<ReferralTO> referralIter = limitedList.iterator(); 
			referralIter.hasNext(); /* empty */)
			{
				ReferralTO referral = referralIter.next();
				
				if( futureReferral == null )
				{
					futureReferral = referral;
				}
				else if( futureReferral != null && 
						referral.getReferralData().getReferralDate().after(futureReferral.getReferralData().getReferralDate()) )
				{
					futureReferral = referral;
				}
			}
		}

		if( futureReferral == null )
		{
			futureReferral = new ReferralTO();
		}

		return futureReferral;
	}

	/**
	 * List all Referrals where JUVENILE_JJS.REFERRAL ReferralDate is equal to or
	 * less than current system date. If there is a Petition Record for the
	 * Referral, the list the associated PetitionAllegation (literal),
	 * PetitionFiledDate, Court Disposition and Court Date. If there is no
	 * Petition record, then list the associated OffenseCode (literal),
	 * OffenseDate, IntakeDecision, and Intake Date.
	 * 
	 * @param reportTO
	 * @return
	 */
	private static List buildReferralDetails(SocialHistoryReportDataTO reportTO)
	{

		List referrals = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		if (reportTO.getReferralHistory() != null)
		{
			for(Iterator referralIter = reportTO.getReferralHistory().iterator(); 
					referralIter.hasNext(); /*empty*/)
			{
				ReferralTO referral = (ReferralTO) referralIter.next();
				if (referral.isIncluded())
				{
					GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
					refEvent.setJuvenileNum(reportTO.getJuvenileNumber());
					refEvent.setReferralNum(referral.getReferralData().getReferralNumber());

					Iterator<JJSReferral> i = JJSReferral.findAll(refEvent);
					while (i.hasNext())
					{
						JJSReferral ref = i.next();
						ReferralTO refData = new ReferralTO();
						if(null != ref.getReferralDate()) {
							refData.getReferralData().setReferralDate(ref.getReferralDate());
							refData.getReferralData().setReferralDateString(sdf.format(ref.getReferralDate()));
						}
						refData.getReferralData().setReferralNumber(ref.getReferralNum());
						// Profile stripping fix task 97540
						//Juvenile juvenile = Juvenile.find(reportTO.getJuvenileNumber());
						JuvenileCore juvenile = JuvenileCore.findCore(reportTO.getJuvenileNumber());
						JJSPetition petition = getPetitionForReferral(juvenile, ref);
						// If there is petition record ...
						if (petition != null)
						{
							refData.setPetitionAvailable(true);
							refData.setPetitionAllegation(petition.getOffenseCode().getShortDescription());
							refData.setPetitionFileDate(petition.getPetitionDate());
						}
						else
						{
							refData.setPetitionAvailable(false);
							
						}
						
						if (ref.getCourtDispositionId() != null)
						{
							// TODO Should the courtDispositionId also be returned? It is
							// not on the response
							JuvenileDispositionCode courtDisposition = ref.getCourtDisposition();
							if (courtDisposition != null)
							{
								refData.setCourtDisposition(courtDisposition.getLongDesc());
							} else {
								refData.setCourtDisposition("NONE");
							}
						}
		
						if(null != ref.getCourtDate()) {
							refData.getReferralData().setCourtDate(ref.getCourtDate());
							refData.getReferralData().setCourtDateString(sdf.format(ref.getCourtDate()));							
													}
						refData.getReferralData().setCourtId(ref.getCourtId());
						// Task 36529				
						if(null != ref.getProbationEndDate()) {
							refData.getReferralData().setProbationEndDate(ref.getProbationEndDate());
							refData.getReferralData().setProbationEndDateString(sdf.format(ref.getProbationEndDate()));
							
						}
						
						
						
						if(null != ref.getIntakeDate()) {
							refData.setIntakeDate(ref.getIntakeDate());
							refData.setIntakeDateString(sdf.format(ref.getIntakeDate()));
						}
						if (ref.getIntakeDecision() != null)
						{
							refData.getReferralData().setIntakeDecision(ref.getIntakeDecision().getDescription());
							refData.getReferralData().setIntakeDecisionCode(ref.getIntakeDecision().getCode());
						}

						//List offenseList = getOffensesForReferral(juvenile, ref);
						List offenseList = InterviewHelper.getOffensesForReferral(ref.getJuvenileNum(), ref.getReferralNum() );
						if (offenseList != null)
						{
							Iterator<JJSOffense> offenseIter = offenseList.iterator();
							//getting all offense records 
							while (offenseIter.hasNext())
							{
								JJSOffense jjsOffense = offenseIter.next();
								if (jjsOffense != null)
								{
									OffenseInformationTO offenseTO = new OffenseInformationTO();
									offenseTO.setPetitionAllegation(jjsOffense.getOffenseCode().getLongDescription());
									if(null != jjsOffense.getOffenseDate()) {
										offenseTO.setOffenseDate(jjsOffense.getOffenseDate());
										offenseTO.setOffenseDateAsString(sdf.format(jjsOffense.getOffenseDate()));
									}
									refData.getOffenses().add(offenseTO);
								}
							} 
						}					
						referrals.add(refData);
					}
				}
			}
		}

		Collections.sort(referrals);
		return referrals;
	}
	
	public static List buildPresentReferralsForGenericReport(String juvenileNum, String casefileId)
	{
		// Use a Hash Map to avoid duplicated petitions
		HashMap presentOffenses = new HashMap();
		//Current System Date
		Date currentSystemDate = DateUtil.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		//added for bug 12932 getting all the transferred referral details
		List juvTransferredOffenceList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
		
		 //ended
		
		//Assignments are a list of referrals for the casefile
		Iterator<Assignment> assignments = Assignment.findAll("caseFileId", casefileId);
		while (assignments.hasNext())
		{
			boolean referralClearedToBePresentReferral = false;
			
			Assignment assignment = assignments.next();
			
			//Get Referral Information for the Offense
			GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
			refEvent.setJuvenileNum(juvenileNum);
			refEvent.setReferralNum(assignment.getReferralNumber());
			
			// Will only bring back one referral
			Iterator iterReferral = JJSReferral.findAll(refEvent); // Will only bring back one referral
			if (iterReferral.hasNext()) {
				JJSReferral referral = (JJSReferral) iterReferral.next(); // Get Detailed Information about the referral
			
				JuvenileCourt juvenileCourt = referral.getCourt();
				String courtId = null;
				if (juvenileCourt != null) 
				{
					courtId = juvenileCourt.getCode();
				}
			
				Date courtDate = referral.getCourtDate();
			
				//Find out if referral has a Court deposition
				JuvenileDispositionCode courtDisposition = referral.getCourtDisposition();
			
				//Find out if referral has an Intake Decision
				Code intakeDecision = referral.getIntakeDecision();
				String intakeDecisionCode = null;
				if (intakeDecision != null) 
				{
					intakeDecisionCode = intakeDecision.getCode();
					//intakeDecisionCode = intakeDecision.getCodeId();
				}
			
				// Retrieve the petitions for a referral (if one exist) by referral number & juvenile number
				JJSPetition petition = null;
				GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
				petitionEvent.setJuvenileNum(juvenileNum);
				petitionEvent.setReferralNum(referral.getReferralNum());
				Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
				List<JJSPetition>  petitions = new ArrayList<JJSPetition>();
				while (petitionIter.hasNext()) 
				{
				    	petitions.add(petitionIter.next());
				    	
				}
				
				if ( petitions.size() > 0 ) {
        				Collections.sort(petitions, new Comparator<JJSPetition>(){
                				@Override
                				public int compare(JJSPetition p1, JJSPetition p2) {
                				    return (p1.getOID().compareTo(p2.getOID()));
                				  }
        				});
        				Collections.reverse(petitions);
        				petition = petitions.get(0);
				}
			
				//Logic to determine whether this referral is added to the present referral/offense list
				//US 173009 changes starts
				GetJuvenileReferralDecisionCodeEvent refDecisionEvt = (GetJuvenileReferralDecisionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEREFERRALDECISIONCODE);
				refDecisionEvt.setCode(intakeDecisionCode);
				List<JuvenileReferralDecisionResponseEvent> responses = MessageUtil.postRequestListFilter(refDecisionEvt, JuvenileReferralDecisionResponseEvent.class);
				String fund = "";
				for( int x=0;x< responses.size();x++){
				    
				    JuvenileReferralDecisionResponseEvent disp = responses.get(x);
				    if (disp != null)
					{
					    fund = disp.getFund();
				    }
				}
				//US 173009 changes ends
				Date currentDate=DateUtil.stringToDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1), DateUtil.DATE_FMT_1) ;
				if ( (courtDisposition != null) 
					&& (petition != null)
					&& (courtDate.after(currentDate) || courtDate.equals(currentDate)) ) 
				{
					referralClearedToBePresentReferral = true;
				} else if ( (courtDisposition == null)
						&& (courtId != null && courtId.length() > 0)
						&& (petition != null) 
						&& (fund.equalsIgnoreCase("CR")) //added this check for CR and commented the line below for US 173009 
						/*&& (intakeDecisionCode != null 
								&& (intakeDecisionCode.equalsIgnoreCase("PIP") || intakeDecisionCode.equalsIgnoreCase("PAT") ||intakeDecisionCode.equalsIgnoreCase("PSE") ||intakeDecisionCode.equalsIgnoreCase("INF") || intakeDecisionCode.equalsIgnoreCase("FOP")) )*/
						&& ( courtDate.after(currentDate) || courtDate.equals(currentDate) ) )  
				{
					referralClearedToBePresentReferral = true;
				}
			
				if (referralClearedToBePresentReferral) 
				{
					OffenseInformationTO offense = new OffenseInformationTO();
				
					//If offense has not already been added to the list, then add it
					if (presentOffenses.get(petition.getPetitionNum()) == null)
					{
						presentOffenses.put(petition.getPetitionNum(), offense);

						offense.setCourtDate(referral.getCourtDate());
						if (juvenileCourt != null)
						{
							offense.setCourtCodeId(juvenileCourt.getCode());
							offense.setJudgesName(juvenileCourt.getJudgesName());
							offense.setCourtName(juvenileCourt.getDescription());
							if(null != referral.getReferralDate()) {
								//Bug #39796 - it should be from offense
								//get all the offenses for the referral and find the one that is most severe
								//offense.setOffenseDate(referral.getReferralDate()); // This date will be overridden if a charge is found for a petition
								//offense.setOffenseDateAsString(sdf.format(referral.getReferralDate()));
								GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
								offEvent.setJuvenileNum(juvenileNum);
								offEvent.setReferralNum(referral.getReferralNum());

								Iterator offenses =  JJSOffense.findAll(offEvent);
								 int numericCode=0;
								 JJSOffense tempOffense = new JJSOffense();
								 while(offenses.hasNext())
					    		 {
					    			   JJSOffense off = (JJSOffense)offenses.next();
					    			   if(off!=null)
					    			   {
					    				   JuvenileOffenseCode offCode = off.getOffenseCode();
					    				   if(offCode != null && !offCode.getNumericCode().equals(""))
					    				   {
					    					   int numCodeFrmOfCode=Integer.parseInt(offCode.getNumericCode());
							    				  if(numericCode==0 || numCodeFrmOfCode<numericCode)
							    				   {
							    					   numericCode = numCodeFrmOfCode;
							    					   tempOffense=off;
							    				   }
					    				   }
					    			   }
					    		 }
			    			   if(tempOffense!=null && tempOffense.getOffenseDate()!=null)
			    			   {
			    				   offense.setOffenseDate(tempOffense.getOffenseDate());
			    				   offense.setOffenseDateAsString(sdf.format(tempOffense.getOffenseDate()));
			    			   }
							}
						}
					
						offense.setReferralNumber(petition.getReferralNum());
						offense.setSeqNum(petition.getSequenceNum());
						offense.setPetitionNumber(petition.getPetitionNum());
						offense.setJjsmspetitionId(petition.getOID());
						offense.setPresentOffense(petition.getOffenseCode().getShortDescription());
						offense.setPetitionAllegation(petition.getOffenseCodeId());
						offense.setAmendmentNumber(petition.getAmend());
					
						// See if a JOT Charge can be found for the petition by matching on
						// the petition number.
						Iterator charges = InterviewHelper.getChargesForPetition(petition).iterator();
						while (charges.hasNext())
						{
							JuvenileOffenderTrackingCharge charge = (JuvenileOffenderTrackingCharge) charges.next();
							//User Story 13967 - Offense date will be set from charge if there is no date in JJS
							if(null == offense.getOffenseDate()) {							
								offense.setOffenseDate(charge.getOffenseDate()); //Overwrite offense date from referral
								offense.setOffenseDateAsString(sdf.format(charge.getOffenseDate()));
							}
							offense.setDALogNumber(charge.getDaLogNum());
							offense.setTransactionNumber(charge.getTransactionNum());
							break;
						} // end while - charges
						
						//added for Bug 12932 - checking Transferred Offense ref num with present referral details record and adding to the same bean.showing together in the report					
						
						 for (Iterator juvTransferredOffenceListIter = juvTransferredOffenceList.iterator(); juvTransferredOffenceListIter.hasNext();) 
						 {
							 JuvenileCasefileTransferredOffenseResponseEvent juvTransferredOffence = (JuvenileCasefileTransferredOffenseResponseEvent) juvTransferredOffenceListIter.next();
							 String tempRefNum = juvTransferredOffence.getReferralNum();
							 if(tempRefNum !=null && tempRefNum.equals(offense.getReferralNumber())){
								 offense.setReferralNumber(tempRefNum);
								 offense.setTransferredOffenseFromCounty(juvTransferredOffence.getCountyDesc());
								 offense.setTransferredOffenseOriginatingOff(juvTransferredOffence.getOffenseShortDesc());
								 offense.setTransferredOffenseAdjuDate(juvTransferredOffence.getAdjudicationDateStr());
								 offense.setTransferredOffensePresent(true);
							 }
						 }
						//ended
						
						
						
					} // end if - presentOffenses
				} // end if - referralClearedToBePresentReferral
			} // end if referralIter
		} // end while - assignments
		
		List presentOffensesList = new ArrayList(presentOffenses.values());
		ArrayList sortFields = new ArrayList();
		sortFields.add(new ReverseComparator(new BeanComparator("referralNumber")));
		sortFields.add(new BeanComparator ("seqNum"));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(presentOffensesList, multiSort);
		return presentOffensesList;
	}
	/**
	 * Social History Report - Juvenile Offenses
	 */
	public static List buildJuvenileOffenses(String juvenileNum)
	{
		// Use a Hash Map to avoid duplicated offenses
		// HashMap juvPresentOffenses = new HashMap();
		// 07/18/2012  Defect #73957 - removed hashmap since different referrals can have the same offenses
		List juvPresentOffenses = new ArrayList();
		// get all offenses by juvenile number
		Iterator<JJSOffense> offenseIter = JJSOffense.findAll( "juvenileNum", juvenileNum );
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");	
		//Cycle through each offense adding offense information 
		while (offenseIter.hasNext())
		{
			JJSOffense jjsOffense = offenseIter.next();
			OffenseInformationTO offense = new OffenseInformationTO();
			
			if (jjsOffense.getReferralNum() != null)
			{
				juvPresentOffenses.add(offense);
				// 09/17/2012  Defect #74075  revised to use offense date from offense date by default per requirements
				if(null != jjsOffense.getOffenseDate()) { 				
					offense.setOffenseDate(jjsOffense.getOffenseDate()); // This date will be overridden if a charge is found for a petition
					offense.setOffenseDateAsString(sdf.format(jjsOffense.getOffenseDate()));
				}
				//Get Referral Information for the Offense
				GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
				refEvent.setJuvenileNum(juvenileNum);
				refEvent.setReferralNum(jjsOffense.getReferralNum());
				Iterator i = JJSReferral.findAll(refEvent); // Will only bring back one referral
				if (i != null && i.hasNext())
				{
					JJSReferral referral = (JJSReferral) i.next();
					if(null != referral.getCourtDate() ) {
						offense.setCourtDate(referral.getCourtDate());
						offense.setCourtDateAsString(sdf.format(referral.getCourtDate()));
					}
					if (referral.getCourt() != null)
					{
						offense.setCourtCodeId(referral.getCourt().getCode());
						offense.setJudgesName(referral.getCourt().getJudgesName());
						offense.setCourtName(referral.getCourt().getDescription());
						// 09/17/2012  Defect #74075  revised to use offense date from offense date by default per requirements 				
//						offense.setOffenseDate(referral.getReferralDate()); // This date will be overridden is a charge is found for a petition
					}
				}
				JJSPetition petition = null;
				GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
				petitionEvent.setJuvenileNum(juvenileNum);
				petitionEvent.setReferralNum(jjsOffense.getReferralNum());
				Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
				if (petitionIter.hasNext()) 
				{
					petition = petitionIter.next();
				}
				offense.setReferralNumber(jjsOffense.getReferralNum());
				offense.setSeqNum(jjsOffense.getSequenceNum());
				offense.setPresentOffense(jjsOffense.getOffenseCode().getShortDescription());
				offense.setPetitionAllegation(jjsOffense.getOffenseCodeId());

				if (petition != null) {
					offense.setPetitionNumber(petition.getPetitionNum());
			
					// See if a JOT Charge can be found for the petition by matching on
					// the petition number.
					Iterator charges = InterviewHelper.getChargesForPetition(petition).iterator();
					while (charges.hasNext())
					{
						JuvenileOffenderTrackingCharge charge = (JuvenileOffenderTrackingCharge) charges.next();
						
						//User Story 13967 - Offense date will be set from JJSOFFENSE, if it is null then set from charge
						if(offense.getOffenseDate()==null)
								offense.setOffenseDate(charge.getOffenseDate()); //Overwrite offense date from referral
						offense.setDALogNumber(charge.getDaLogNum());
						offense.setTransactionNumber(charge.getTransactionNum());
					
						//Add co-actors
						Iterator<JuvenileOffenderTrackingCoActor> coActors = getAdultCoactors(charge.getDaLogNum()).iterator();
						while (coActors.hasNext())
						{
							JuvenileOffenderTrackingCoActor coActor = coActors.next();
							CoActorTO coActorTO = new CoActorTO();
							offense.getAdultCoActors().add(coActorTO);
							coActorTO.setName(coActor.getName());
							coActorTO.setAge(coActor.getAge());
//							offense.getAdultCoActors().add(coActor); 
						} // end while - co-actors 
						break;
					} // end while - charges
				} // end if	
			} // end if
		} // end while - petitions
		//sort offenses by referral number
//		List juvOffenses = new ArrayList(juvPresentOffenses.values());
		ArrayList srtFields = new ArrayList();
		srtFields.add(new ReverseComparator(new BeanComparator("referralNumber")));
		srtFields.add(new BeanComparator ("seqNum"));
		ComparatorChain multiSrt = new ComparatorChain(srtFields);
		Collections.sort(juvPresentOffenses, multiSrt);
		return juvPresentOffenses;
	}
	/**
	 * Social History Report - Present Offense
	 */
	public static List buildOffenses(String juvenileNum, String casefileId)
	{
		// Use a Hash Map to avoid duplicated petition
		HashMap presentOffenses = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		//Assignments are how referrals are related to referrals from M204
		Iterator<Assignment> referrals = Assignment.findAll("caseFileId", casefileId);
		while (referrals.hasNext())
		{
			Assignment ref = referrals.next();

			// get all of the petition by referral number & juvenile number
			GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
			petitionEvent.setJuvenileNum(juvenileNum);
			petitionEvent.setReferralNum(ref.getReferralNumber());
			

			//Find all petitions for a referral
			Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
			
			//Cycle through each petition adding offense information 
			while (petitionIter.hasNext())
			{
				JJSPetition petition = petitionIter.next();
				OffenseInformationTO offense = new OffenseInformationTO();
				if (presentOffenses.get(petition.getPetitionNum()) == null)
				{
				    	
					presentOffenses.put(petition.getPetitionNum(), offense);

					//Get Referral Information for the Offense
					GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
					refEvent.setJuvenileNum(juvenileNum);
					refEvent.setReferralNum(ref.getReferralNumber());
					
					Iterator i = JJSReferral.findAll(refEvent); // Will only bring back one referral
					if (i != null && i.hasNext())
					{
						JJSReferral referral = (JJSReferral) i.next();
						offense.setCourtDate(referral.getCourtDate());
						if (referral.getCourt() != null)
						{
							offense.setCourtCodeId(referral.getCourt().getCode());
							offense.setJudgesName(referral.getCourt().getJudgesName());
							offense.setCourtName(referral.getCourt().getDescription());
							if(null != referral.getReferralDate()) {
								offense.setOffenseDate(referral.getReferralDate()); // This date will be overridden if a charge is found for a petition
								offense.setOffenseDateAsString(sdf.format(referral.getReferralDate()));
							}
						}
					}
					
					offense.setReferralNumber(petition.getReferralNum());
					offense.setSeqNum(petition.getSequenceNum());
					offense.setPetitionNumber(petition.getPetitionNum());
					offense.setPresentOffense(petition.getOffenseCode().getShortDescription());
					offense.setPetitionAllegation(petition.getOffenseCodeId());
					
					// See if a JOT Charge can be found for the petition by matching on
					// the petition number.
					Iterator charges = InterviewHelper.getChargesForPetition(petition).iterator();
					while (charges.hasNext())
					{
						JuvenileOffenderTrackingCharge charge = (JuvenileOffenderTrackingCharge) charges.next();
						//User Story 13967 - Offense date will be set from JJSOFFENSE, if null it will set from charge
						if(offense.getOffenseDate()==null)
							offense.setOffenseDate(charge.getOffenseDate()); //Overwrite offense date from referral
						offense.setDALogNumber(charge.getDaLogNum());
						offense.setTransactionNumber(charge.getTransactionNum());
						
						//Add co-actors
						Iterator<JuvenileOffenderTrackingCoActor> coActors = getAdultCoactors(charge.getDaLogNum()).iterator();
						while (coActors.hasNext())
						{
							JuvenileOffenderTrackingCoActor coActor = coActors.next();
							CoActorTO coActorTO = new CoActorTO();
							offense.getAdultCoActors().add(coActorTO);
							coActorTO.setName(coActor.getName());
							coActorTO.setAge(coActor.getAge());
							
//							offense.getAdultCoActors().add(coActor); 
						} // end while - co-actors 
						
						break;
					} // end while - chargers
				
				} // end if
				
			} // end while - petitions
			 
		} // end while - 

		//sort offenses by referral number
		List presOffenses = new ArrayList(presentOffenses.values());
		ArrayList srtFlds = new ArrayList();
		srtFlds.add(new ReverseComparator(new BeanComparator("referralNumber")));
		srtFlds.add(new BeanComparator ("seqNum"));
		ComparatorChain mltiSrt = new ComparatorChain(srtFlds);
		Collections.sort(presOffenses, mltiSrt);
		return presOffenses;
	}

	/*
	 * 
	 */
	private static String getParentTraitIdFromCategory(String category)
	{
		String parentTraitId = "";

		GetTraitParentByCategoryEvent traitEvent = (GetTraitParentByCategoryEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETTRAITPARENTBYCATEGORY);
		traitEvent.setTraitCategoryName(category);

		Iterator<TraitType> i = TraitType.findByType(traitEvent);
		while (i.hasNext())
		{
			TraitType traitType = i.next();
			if (!traitType.getParentTypeId().equals("0"))
			{
				parentTraitId = traitType.getParentTypeId();
			}
			else
			{
				parentTraitId = traitType.getTraitTypeId();
			}
		}

		return parentTraitId;
	}

	/*
	 * 
	 */
	public static List buildTraitsByTraitsCategory(String juvNum, String traitsCategory)
	{
		List list = new ArrayList();

		String parentTraitId = getParentTraitIdFromCategory(traitsCategory);

		GetJuvenileTraitsByParentTypeEvent query = new GetJuvenileTraitsByParentTypeEvent();
		query.setJuvenileNum(juvNum);
		query.setTraitType(parentTraitId);

		Iterator<JuvenileTrait> iter = JuvenileTrait.findAll(query);
		while (iter.hasNext())
		{
			JuvenileTrait trait = iter.next();

			TraitTO to = new TraitTO();
			to.setEntryDate(trait.getCreatedDate());
			to.setTraitTypeName(trait.getTraitParent());
			to.setTraitTypeDescription(trait.getTraitType().getName());
			to.setTraitComments(trait.getComments());
			to.setStatusId(trait.getStatusId());
			to.setStatus( trait.getStatus().getDescription());
			list.add(to);
		}
		return list;
	}

	/**
	 * Social History Report - Juvenile Traits - Substance Abuse
	 */
	public static List buildSubstanceAbuseTraits(String juvNum)
	{
		List list = new ArrayList();
		String parentTraitId = getParentTraitIdFromCategory("SUBSTANCE_ABUSE");

		GetJuvenileTraitsByParentTypeEvent query = new GetJuvenileTraitsByParentTypeEvent();
		query.setJuvenileNum(juvNum);
		query.setTraitType(parentTraitId);

		Iterator<JuvenileTrait> iter = JuvenileTrait.findAll(query);
		while (iter.hasNext())
		{
			JuvenileTrait trait = iter.next();

			TraitTO to = new TraitTO();
			to.setEntryDate(trait.getCreatedDate());
			to.setTraitTypeName(trait.getTraitParent());
			to.setTraitTypeDescription(trait.getTraitType().getName());
			to.setTraitComments(trait.getComments());
			to.setStatusId(trait.getStatusId());
			to.setStatus( trait.getStatus().getDescription());
			list.add(to);
		}
		return list;
	}

	/**
	 * Social History Report - Juvenile Traits - Substance Abuse Information
	 */
	public static List buildSubstanceAbuseInformation(String juvNum)
	{
		List list = new ArrayList();

		Iterator<JuvenileDrugs> iter = JuvenileDrugs.findAll("juvenileId", juvNum);
		while (iter.hasNext())
		{
			JuvenileDrugs drug = iter.next();

			SubstanceAbuseInformationTO to = new SubstanceAbuseInformationTO();
			to.setEntryDate(drug.getEntryDate());
			to.setOnsetAge(drug.getOnsetAge());
			to.setDrugType(drug.getDrugType() != null ? drug.getDrugType().getDescription() : "");
			to.setFrequency(drug.getFrequency() != null ? drug.getFrequency().getDescription() : "");
			to.setLocationOfUse(drug.getLocationOfUse() != null ? drug.getLocationOfUse().getDescription() : "");
			to.setAmountSpent(drug.getAmountSpent() != null ? drug.getAmountSpent() : "");
			to.setDegree(drug.getDegree() != null ? drug.getDegree().getDescription() : "");
			list.add(to);
		}
		return list;
	}

	/*
	 * Returns latest Detention Admit Date only if the release date is empty
	 */
	public static Date getDetentionAdmitDate(String juvenileNum, List referralDetails)
	{
		Date admitDate = null;
		
		List juvenileFacilityStayRecord 
			= buildJuvenileFacilityStayRecord(juvenileNum);
		
		Iterator<JuvenileDetentionFacilitiesResponseEvent>iter = juvenileFacilityStayRecord.iterator();
		while (iter.hasNext())
		{
			JuvenileDetentionFacilitiesResponseEvent juvDetentionFac = iter.next();
			
			if ( juvDetentionFac.getReleaseDate() == null ) 
			{
				Iterator<ReferralTO> iterRefTo = referralDetails.iterator();
				while (iterRefTo.hasNext())
				{
					ReferralTO referralTO = iterRefTo.next();
					if (juvDetentionFac.getReferralNumber().equalsIgnoreCase(referralTO.getReferralData().getReferralNumber())) {
						admitDate = juvDetentionFac.getAdmitDate();
						break;
					}
				}
				
			}
			
		}

		return admitDate;

	}
	
	/*
	 * 
	 */
	public static List buildJuvenileFacilityStayRecord(String juvenileNum)
	{
		List juvenileFacilityStayRecord = new ArrayList();

		GetJuvenileDetentionFacilitiesEvent getJuvenileFacilityStay = new GetJuvenileDetentionFacilitiesEvent();
		getJuvenileFacilityStay.setJuvenileNum(juvenileNum);
		Iterator<JJSFacility>iter = JJSFacility.findAll(getJuvenileFacilityStay);
		while (iter.hasNext())
		{
			JJSFacility fac = iter.next();
			JuvenileDetentionFacilitiesResponseEvent resp = new JuvenileDetentionFacilitiesResponseEvent();
			resp.setAdmitReason(fac.getAdmitReason());
			resp.setReferralNumber(fac.getReferralNumber());
			resp.setAdmitDate(fac.getAdmitDate());
			resp.setReleaseDate(fac.getReleaseDate());
			resp.setAdmitTime(fac.getAdmitTime());
			resp.setReleaseTime(fac.getReleaseTime());
			resp.setDetainedFacility( fac.getDetainedFacility());
			resp.setReleaseBy(fac.getReleaseBy());
			resp.setReleaseTo(fac.getReleaseTo());
			juvenileFacilityStayRecord.add(resp);
		}

		return juvenileFacilityStayRecord;

	}

	/**
	 * Social History Report - School History
	 */
	public static List buildSchoolHistory(String juvNum)
	{
		List list = new ArrayList();

		GetJuvenileSchoolEvent schoolEvent = new GetJuvenileSchoolEvent();
		schoolEvent.setJuvenileNum(juvNum);
		Iterator<JuvenileSchoolHistory> i = JuvenileSchoolHistory.findJuvenileSchoolHistory(schoolEvent);
		while (i.hasNext())
		{
			JuvenileSchoolHistory school = i.next();

			SchoolHistoryTO to = new SchoolHistoryTO();			
			to.setOID(school.getOID().toString());
			to.setEntryDate(school.getCreateDate());
			to.setGPA(school.getGradeAverage());
			to.setVerifiedDate(school.getVerifiedDate());
			//Changes for JIMS200077279: MJCW:  Add Eligible for Enrollment Dte to TEAt(UI)
			to.setEligibilityEnrollmentDate(school.getEligibilityEnrollmentDate());
			to.setGradeLevel(school.getGradeLevel() != null ? school.getGradeLevel().getDescription() : "");
			to.setAppropriateLevel(school.getAppropriateLevel() != null ? school.getAppropriateLevel().getDescription() : "");
			to.setEnrollmentStatus(school.getExitType() != null ? school.getExitType().getDescription() : "");
			to.setLastDateAttended(school.getLastAttendedDate());
			to.setStatus(school.getSchoolAttendanceStatus() != null ? school.getSchoolAttendanceStatus().getDescription() : "");
			to.setSpecificSchoolName(school.getSpecifiedSchoolName());
			to.setEducationService(school.getEducationService());
			if (school.getProgramAttending() != null)
			{
				to.setProgram(school.getProgramAttending().getDescription());
			}

			if (school.getParticipation() != null)
			{
				to.setParticipation(school.getParticipation().getDescription());
			}

			if (school.getRuleInfraction() != null)
			{
				to.setRuleInfraction(school.getRuleInfraction().getDescription());
			}

			if (school.getGradesRepeated() != null)
			{
				to.setGradesRepeated(school.getGradesRepeated().getDescription());
			}

			to.setNumberOfGradeRepeated(school.getGradesRepeatNumber());

			if (school.getSchool() != null)
			{
				to.setDistrict(school.getSchool().getDistrictDescription() + ", " + school.getSchool().getSchoolDescription());
			}
			list.add(to);
		}
		return list;
	}

	/**
	 * Social History Report - Employment History
	 */
	public static List buildEmploymentHistory(String juvNum)
	{
		List list = new ArrayList();

		Iterator<JuvenileJob> i = JuvenileJob.findAll("juvenileId", juvNum);
		while (i.hasNext())
		{
			JuvenileJob job = i.next();

			EmploymentHistoryTO to = new EmploymentHistoryTO();
			list.add(to);

			to.setOID(job.getOID().toString());
			to.setEntryDate(job.getEntryDate());
			to.setPlaceEmployed(job.getEmploymentPlace());
			to.setWorkHours(Double.toString(job.getWorkHours()));
			to.setJobDescription(job.getJobDescription());
			to.setEmploymentStatus(job.getEmploymentStatus() != null ? job.getEmploymentStatus().getDescription() : "");

			String supervisorName = "";
			if (job.getSupervisorFirstName() != null)
			{
				supervisorName += job.getSupervisorFirstName() + " ";
			}
			if (job.getSupervisorLastName() != null)
			{
				supervisorName += job.getSupervisorLastName();
			}
			to.setSupervisorName(supervisorName);
		}
		return list;
	}

	/*
	 * 
	 */
	private static int getNumberLivingInHome(List financial)
	{
		int numberLivingInHome = 0;

		if (financial != null && financial.size() > 0)
		{
			FamilyInformationTO family = (FamilyInformationTO) financial.iterator().next();
			numberLivingInHome = family.getNumberLivingInHome();

		}

		return numberLivingInHome;
	}

	/**
	 * Social History Report - Financial History
	 */
	// Profile stripping fix task 97536
	//public static List buildFamilyInformation(Juvenile juvenile)
	public static List buildFamilyInformation(JuvenileCore juvenile)
	{
		List list = new ArrayList();

		//Build HashMap of Current Constellation of Family members
		HashMap currentFamilyMembers = new HashMap();
		FamilyConstellation currentFamilyConstellation = juvenile.getCurrentFamilyConstellation();
		String currentFamilyConstellationId = currentFamilyConstellation.getFamilyConstellationId();
		Collection currentFamilyConstellationMembers = currentFamilyConstellation.getFamilyConstellationMembers();
		Iterator<FamilyConstellationMember> currentMembers = currentFamilyConstellationMembers.iterator();
		while (currentMembers.hasNext())
		{
			FamilyConstellationMember constMember = currentMembers.next();
			FamilyMember famMember = constMember.getTheFamilyMember();
			
			currentFamilyMembers.put(famMember.getFamilyMemberId(), "");
			
		}
		
		Collection allFamilyConstellations = juvenile.getFamilyConstellationList();
		Iterator famConstellations = allFamilyConstellations.iterator();
		HashMap allFamilyMembersNoDuplicatesMap = new HashMap();
		
		while (famConstellations.hasNext()) { //first while
			
			FamilyConstellation constellation = (FamilyConstellation) famConstellations.next();
			
			if (constellation != null)
			{
				Collection coll = constellation.getFamilyConstellationMembers();
				if (coll != null)
				{
					
					Iterator<FamilyConstellationMember> members = coll.iterator();
					while (members.hasNext())
					{
						FamilyConstellationMember constMember = members.next();
						FamilyMember famMember = constMember.getTheFamilyMember();
						
						boolean mapContainsKey = allFamilyMembersNoDuplicatesMap.containsKey(famMember.getFamilyMemberId());
	
						//Check to see if Family Member already exist in the List
						if( ( !mapContainsKey ) 
								|| (  mapContainsKey && constellation.getFamilyConstellationId().equals(currentFamilyConstellationId)  ) )
			        	{
							
							if ( mapContainsKey && constellation.getFamilyConstellationId().equals(currentFamilyConstellationId)) 
							{
								Iterator<FamilyInformationTO> familyInformationTOIter = list.iterator();
								while (familyInformationTOIter.hasNext())
								{
									FamilyInformationTO familyInformationTO = familyInformationTOIter.next();
									if (familyInformationTO.getFamilyMemberId().equals(famMember.getFamilyMemberId())) {
										list.remove(familyInformationTO);
										break;
									}
								}
								
							}
							
							//Add family member to hashmap
							allFamilyMembersNoDuplicatesMap.put(famMember.getFamilyMemberId(), "");
			        	
							//Create a new FamilyInformationTO to add to the list
							FamilyInformationTO to = new FamilyInformationTO();
							
							//SetExclude
							to.setExcluded(true); //Has affect on whether checkboxes are on by default in the Juvenile Info tab
							
							//Family Constellation Member ID
							to.setFamilyConstellationMemberId(constMember.getFamilyConstellationId());
							
							//Family Member ID
							to.setFamilyMemberId(famMember.getFamilyMemberId());
							
							// Family Member Name
							to.setFamilyMemberName(InterviewHelper.formatNameLastNameFirst(
									famMember.getFirstName(), famMember.getMiddleName(), famMember.getLastName()));
							
							String familyMemberId = famMember.getFamilyMemberId();
							String familyConstellationId = constMember.getFamilyConstellationId();
							boolean memberCurrentInConstellation = currentFamilyMembers.containsKey(familyMemberId);
							
							if (memberCurrentInConstellation && currentFamilyConstellationId.equals(familyConstellationId)) 
							//if( (currentFamilyMembers.containsKey(famMember.getFamilyMemberId())) && ((currentFamilyMembers.get(famMember.getFamilyMemberId()).equals(currentFamilyConstellationId)))    )
				        	{
								//Set Member of CurrentConstellation
				        		to.setMemberOfCurrentConstellation(true);
								//Guardian Status
				        		if (constMember.isGuardian()) {
				        			to.setGuardianStatus(constMember.isGuardian());
				        		}
				        		//Primary Care Giver 11063
				        		to.setPrimaryCareGiver(constMember.isPrimaryCareGiver()); //Er changes 11063
				        		
								// In Home
				        		if (constMember.isInHomeStatus()) {
				        			to.setInHome(constMember.isInHomeStatus());
				        		}
				        		//Parental Rights Terminated
								to.setParentalRightsTerminated(constMember.isParentalRightsTerminated());
				        	}
							
							// Relationship
							to.setRelationship(constMember.getRelationshipToJuvenile().getDescription());
	
							// Deceased
							to.setDeceased(famMember.isDeceased());
							
							//Incarcerated
							to.setIncarcerated(famMember.isIncarcerated());
														
							//ER 71034 - Only print address and phone for family members who aren't deceased or incarcerated
							if (!famMember.isDeceased() && !famMember.isIncarcerated()) {
								// Address - get latest Residential address
								Address latestAddress = null;
								Collection addresses = famMember.getAddresses();
							
								ArrayList sortFields = new ArrayList();
								sortFields.add(new ReverseComparator(new BeanComparator("createDate")));
								ComparatorChain multiSort = new ComparatorChain(sortFields);
								Collections.sort((List<Address>) addresses, multiSort);
								Iterator<Address> addressesIt = addresses.iterator();
							
								while (addressesIt.hasNext())
								{
									Address address = addressesIt.next();
									if ("RES".equals(address.getAddressTypeId()) && latestAddress == null)
									{
										latestAddress = address;
									}
								}
								if (latestAddress != null)
								{
									to.setAddress(InterviewHelper.formatAddress(latestAddress));
								}
	
								// Phone - get latest Home phone
								FamilyMemberPhone latestPhone = null;
								Collection phones = famMember.getPhoneNumbers();
							
								ArrayList sortingFields = new ArrayList();
								sortingFields.add(new ReverseComparator(new BeanComparator("createTimestamp")));
								ComparatorChain multiSorting = new ComparatorChain(sortingFields);
								Collections.sort((List<FamilyMemberPhone>) phones, multiSorting);
								Iterator<FamilyMemberPhone> phonesIt = phones.iterator();
								//changed for User Story #11107 - pick up primary phone for a member irrespective of phone type
								while (phonesIt.hasNext())
								{							
									FamilyMemberPhone fmPhone = phonesIt.next();
									if (fmPhone.getPhoneMaster()!=null && fmPhone.getPhoneMaster().isPrimaryInd() && latestPhone == null)
									{
										latestPhone = fmPhone;
										break;
									}
								}
								//if no primary phone get the latest phone
								if (phones.size()!=0 && latestPhone == null)
									latestPhone=(FamilyMemberPhone)((ArrayList)phones).get(0);
								if (latestPhone != null)
								{
									Phone phone = latestPhone.getPhoneMaster();
									 to.setPhone(phone.getPhoneNumber());
								}
							}
							//Add Member to List
							list.add(to);
			        	}
											
					} // while another member
				}
			}
			
		}//first while
		
		if (list.size() > 1) {
			ArrayList sortFields = new ArrayList();
			sortFields.add(new BeanComparator("familyMemberName"));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(list, multiSort);
		}

		return list;
	}
	
	/**
	 * Social History Report - Financial History
	 */
	// Profile stripping fix task 97536
	//public static List buildFinancialHistory(Juvenile juvenile)
	public static List buildFinancialHistory(JuvenileCore juvenile)
	{
		List list = new ArrayList();

		//FamilyConstellation constellation = juvenile.getAllFamilyConstellations();
		FamilyConstellation constellation = juvenile.getCurrentFamilyConstellation();
		
		if (constellation != null)
		{
			Collection coll = constellation.getFamilyConstellationMembers();
			if (coll != null)
			{
				// only show employment information record of 1 guardian
				// if both guardians are living in-home with the juvenile
				boolean prevGuardiansInHome = false;

				Iterator<FamilyConstellationMember> members = coll.iterator();
				while (members.hasNext())
				{
					FamilyConstellationMember constMember = members.next();
					FamilyMember famMember = constMember.getTheFamilyMember();

					if (constMember.isGuardian())
					{
						FamilyInformationTO to = new FamilyInformationTO();
						list.add(to);

						// Guardian
						to.setGuardian(InterviewHelper.formatName(
								famMember.getFirstName(), famMember.getMiddleName(), famMember.getLastName()));

						// Relationship
						to.setRelationship(constMember.getRelationshipToJuvenile().getDescription());
						
						//ER 71034 - Only print address and phone for family members who aren't deceased or incarcerated
						if (!famMember.isDeceased() && !famMember.isIncarcerated()) {
							// Address - get latest Residential address
							Address latestAddress = null;
							Iterator<Address> addresses = famMember.getAddresses().iterator();
							while (addresses.hasNext())
							{
								Address address = addresses.next();
								if ("RES".equals(address.getAddressTypeId()))
								{
									if (address.getCreateDate() != null || (latestAddress == null && 
											latestAddress.getCreateDate().compareTo(address.getCreateDate()) < 0))
									{
										latestAddress = address;
									}
								}
							}
							if (latestAddress != null)
							{
								to.setAddress(InterviewHelper.formatAddress(latestAddress));
							}

							// Phone - get latest Home phone
							FamilyMemberPhone latestPhone = null;
							Iterator<FamilyMemberPhone> phones = famMember.getPhoneNumbers().iterator();
							while (phones.hasNext())
							{
								FamilyMemberPhone fmPhone = phones.next();
								if ("HM".equals(fmPhone.getPhoneTypeId()))
								{
									if (fmPhone.getCreateTimestamp() != null || (latestPhone != null && 
											latestPhone.getCreateTimestamp() != null && 
											latestPhone.getCreateTimestamp().compareTo(fmPhone.getCreateTimestamp()) < 0))
									{
										latestPhone = fmPhone;
									}
								}

							}
						
							if (latestPhone != null)
							{
								Phone phone = latestPhone.getPhoneMaster();
								to.setPhone(phone.getPhoneNumber());
							}
						}
						// In Home
						to.setInHome(constMember.isInHomeStatus());
						
						// Deceased
						to.setDeceased(famMember.isDeceased());
						
						//Incarcerated
						to.setIncarcerated(famMember.isIncarcerated());
						
						//Parental Rights Terminated
						to.setParentalRightsTerminated(constMember.isParentalRightsTerminated());

						// Financial - financials are bases on latest record.
						FamilyMemberFinancial latestFin = null;
						Iterator<FamilyMemberFinancial> finIter = constMember.getConstellationMemberFinancials().iterator();
						while (finIter.hasNext())
						{
							FamilyMemberFinancial fin = finIter.next();
							if (latestFin == null || latestFin.getEntryDate().compareTo(fin.getEntryDate()) < 0)
							{
								latestFin = fin;
							}
						}

						if (latestFin != null)
						{
							to.setTANFAssistance(latestFin.getTanfAfdc());
							to.setOtherIncome(latestFin.getOtherIncome());

							to.setRentExpenses(latestFin.getRentExpenses());
							to.setUtilitiesExpenses(latestFin.getUtilitiesExpenses());
							to.setGroceryExpenses(latestFin.getGroceryExpenses());
							to.setSchoolExpenses(latestFin.getSchoolExpenses());
							to.setChildSupportPaid(latestFin.getChildSupportPaid());
							to.setMedicalExpenses(latestFin.getMedicalExpenses());
							to.setLifeInsurancePremium(latestFin.getLifeInsurancePremium());
							to.setPropertyValue(latestFin.getPropertyValue());
							to.setIntangibleValue(latestFin.getIntangibleValue());
							to.setSavings(latestFin.getSavings());
							to.setChildSupportReceived(latestFin.getChildSupportReceived());

							// TODO Fix food stamps when type is changed to double.
							to.setFoodStamps(latestFin.getFoodStamps());

							to.setNumberLivingInHome(latestFin.getNumberLivingInHome());
						}

						// When both guardians are in home, only show the first guardian's
						// employment info & income info
						if (!(prevGuardiansInHome && constMember.isInHomeStatus()))
						{
							// Employment
							FamilyMemberEmployment latestEmp = null;
							Iterator<FamilyMemberEmployment> empIter = famMember.getEmployments().iterator();
							while (empIter.hasNext())
							{
								FamilyMemberEmployment emp = empIter.next();

								// Save the latest employment record for income calculations
								if (latestEmp == null || latestEmp.getCreateTimestamp().compareTo(emp.getCreateTimestamp()) < 0)
								{
									latestEmp = emp;
								}

								EmploymentHistoryTO empTO = new EmploymentHistoryTO();
								to.getEmploymentHistory().add(empTO);

								empTO.setExcluded(true);
								empTO.setOID(emp.getOID().toString());
								empTO.setPlaceEmployed(emp.getCurrentEmployer());
								empTO.setWorkHours(Double.toString(emp.getWorkHours()));

								if (emp.getEmploymentStatus() != null)
									empTO.setEmploymentStatus(emp.getEmploymentStatus().getDescription());

								empTO.setSupervisorName("");
								empTO.setJobDescription(emp.getJobTitle());
								empTO.setEntryDate(emp.getCreateTimestamp());
								double annualGrossIncome = emp.getAnnualNetIncome();
								if (annualGrossIncome == 0)
								{
									annualGrossIncome = calculateAnnualIncome(emp.getSalary(), emp.getSalaryRateId(), emp.getWorkHours());
								}
								empTO.setAnnualGrossIncome(annualGrossIncome);
							}

							// Sort employment records newest to oldest
							Collections.sort(to.getEmploymentHistory());

							double grossIncome = 0;
							grossIncome += to.getTANFAssistance() * 12;
							grossIncome += to.getOtherIncome();

							to.setTotalGross(grossIncome);
							if (latestEmp != null)
							{
								to.setAnnualNetIncome(latestEmp.getAnnualNetIncome());
							}
						}

						prevGuardiansInHome = constMember.isInHomeStatus();
					} // if guardian
				} // while another member
			}
		}

		return list;
	}

	/**
	 * Social History Report - Supervision Rules
	 */
	public static List buildSupervisionRules(String casefileId)
	{
		List list = new ArrayList();

		Iterator<JuvenileCaseSupervisionRule> i = JuvenileCaseSupervisionRule.findAll("casefileId", casefileId);
		while (i.hasNext())
		{
			JuvenileCaseSupervisionRule rule = i.next();

			SupervisionRuleTO to = new SupervisionRuleTO();
			list.add(to);

			to.setOID(rule.getOID().toString());
			to.setRuleId(rule.getOID().toString());
			to.setStatusChangeDate(rule.getCompletionDate());
			to.setCompletionStatusId(rule.getCompletionStatusId());
			to.setCompletionStatus(rule.getCompletionStatus() != null ? rule.getCompletionStatus().getDescription() : "");
			to.setResolvedDesc(rule.getResolvedDesc());

			Condition cond = rule.getCondition();
			Group group = cond.getGroup();
			if (group != null)
			{
				Group[] groups = group.getGroupList();
				to.setCategory(groups[0] != null ? groups[0].getGroupName() : "");
				to.setType(groups[1] != null ? groups[1].getGroupName() : "");
				to.setSubtype(groups[2] != null ? groups[2].getGroupName() : "");
			}
		}
		return list;
	}

	/**
	 * Social History Report - Warrant History
	 */
	public static List buildWarrantHistory(String juvenileId)
	{
		List list = new ArrayList();

		SearchJuvenileWarrantEvent event = new SearchJuvenileWarrantEvent();
		event.setJuvenileNum(Integer.valueOf(juvenileId));
		Iterator<JuvenileWarrant> warrants = JuvenileWarrant.findAll(event);
		while (warrants.hasNext())
		{
			JuvenileWarrant warrantHeader = warrants.next();
			JuvenileWarrant warrant = JuvenileWarrant.find(warrantHeader.getOID().toString());
			WarrantInformationTO to = new WarrantInformationTO();
			list.add(to);

			to.setWarrantNumber(warrant.getWarrantNum());
			to.setDateOfIssue(warrant.getDateOfIssue());
			to.setWarrantType(warrant.getWarrantType().getDescription());
			to.setWarrantStatus(warrant.getWarrantStatus().getDescription());

			JuvenileWarrantService latestService = null;
			Iterator<JuvenileWarrantService> serviceIter = warrant.getServices().iterator();
			while (serviceIter.hasNext())
			{
				JuvenileWarrantService service = serviceIter.next();
				if (latestService == null)
				{
					latestService = service;
				}
			}

			if (latestService != null)
			{
				to.setServiceDate(latestService.getServiceTimeStamp());
				to.setServiceStatus(latestService.getServiceStatus().getDescription());
			}
		}

		return list;
	}

	/**
	 * Social History Report - will get all of the referrals for the user, across casefiles
	 */
	// Profile stripping fix task 97536
	//public static List buildReferralHistory(Juvenile juvenile)
	public static List buildReferralHistory(JuvenileCore juvenile)
	{
		List list = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		
		GetJuvenileProfileReferralListEvent refEvent = new GetJuvenileProfileReferralListEvent();
		refEvent.setJuvenileNum(juvenile.getJuvenileNum());

		Iterator<JJSReferral> referralList = JJSReferral.findAll(refEvent);
		while (referralList.hasNext())
		{
			JJSReferral ref = referralList.next();
			ReferralTO referral = new ReferralTO();

			JuvenileCasefileReferralsResponseEvent resp = new JuvenileCasefileReferralsResponseEvent();
			resp.setReferralNumber(ref.getReferralNum());
			resp.setCourtId(ref.getCourtId());
			resp.setReferralFound(true);
			if(null != ref.getReferralDate()) {
				resp.setReferralDate(ref.getReferralDate());
				resp.setReferralDateString(sdf.format(ref.getReferralDate()));
			}
			if (null != ref.getCourtDate()) {
				resp.setCourtDate(ref.getCourtDate());
				resp.setCourtDateString(sdf.format(ref.getCourtDate()));
			}
			if( notNullNotEmptyString( ref.getIntakeDecisionId() ))
			{
				Code intakeDescision = ref.getIntakeDecision();
				resp.setIntakeDecision(intakeDescision.getDescription());
				resp.setIntakeDecisionCode(intakeDescision.getCode());
			}

			referral.setReferralData(resp);

			list.add(referral);
		}

		return list;
	}

	/**
	 * Social History Report - JPO Referrals
	 */
	// Profile stripping fix task 97536
	//public static List buildJPCourtReferrals(Juvenile juvenile)
	public static List buildJPCourtReferrals(JuvenileCore juvenile)
	{
		List list = new ArrayList();

		if( notNullNotEmptyString( juvenile.getLastName() ))
		{
			// Search for juveniles with same first two letters of last name and exact
			// birthdate.
			GetJCCDefendantEvent queryEvt = new GetJCCDefendantEvent();
			//queryEvt.setLastName(juvenile.getLastName().substring(0, juvenile.getLastName().length() > 1 ? 2 : 1) +"%");
			queryEvt.setLastName( juvenile.getLastName() );
			queryEvt.setDateOfBirth(DateUtil.dateToString( juvenile.getDateOfBirth(), "yyyyMMdd"));

			Iterator<JCCDefendant> jpCourtJuveniles = JCCDefendant.findAll(queryEvt);
			while (jpCourtJuveniles.hasNext())
			{
				JCCDefendant defendant = jpCourtJuveniles.next();

				JPCourtReferralTO referral = new JPCourtReferralTO();
				list.add(referral);
				referral.setExcluded(true);
				referral.setM204JuvNumber(defendant.getM204JuvNumber());
				referral.setName(InterviewHelper.formatName(
								defendant.getFirstName(), defendant.getMiddleName(), defendant.getLastName()));
				referral.setFirstName(defendant.getFirstName());
				referral.setMiddleName(defendant.getMiddleName());
				referral.setLastName(defendant.getLastName());
				referral.setDateOfBirth( DateUtil.IntToDate(defendant.getDateOfBirth(),DateUtil.DATE_FMT_2 ));
				referral.setRace(defendant.getRace() != null ? defendant.getRace().getDescription() : "");
			}
		}

		return list;
	}

	/**
	 * Social History Report - JPO Referrals
	 */
	public static List buildJPCourtReferralConvictions(JPCourtReferralTO referral)
	{
		// duplicate from GetJPCourtReferralsCommand
		List list = new ArrayList();

		GetJCCConvictionEvent queryEvt = new GetJCCConvictionEvent();
		queryEvt.setM204JuvNumber(referral.getM204JuvNumber());
		Iterator<JCCConviction> iter = JCCConviction.findAll(queryEvt);
		while (iter.hasNext())
		{
			JCCConviction conviction = iter.next();
			JPCourtReferralResponseEvent response = new JPCourtReferralResponseEvent();

			response.setOffenseId(conviction.getOffenseId());
			response.setOffenseDescription(conviction.getOffenseDescription());

			Code court = conviction.getCourt();
			if (court != null)
			{
				response.setCourtName(court.getDescription());
			}

			response.setConvictionDate(DateUtil.IntToDate( conviction.getConvictionDate(),DateUtil.DATE_FMT_2));
			response.setFileDate(DateUtil.IntToDate( conviction.getFilingDate(),DateUtil.DATE_FMT_2));
			response.setDisposition(conviction.getDisposition());
			response.setCaseNumber(conviction.getCaseNumber());

			list.add(response);
		}

		return list;
	}

	/***********************************************************************************************************
	 * Misc. accessor methods.
	 */

	/**
	 * Returns the referrals where the petition.jpoId == user.id and
	 * petition.courtDate > today.
	 */
	//public static JJSReferral getCurrentReferralForJuvenile(Juvenile juv)
	public static JJSReferral getCurrentReferralForJuvenile(JuvenileCore juv)
	{
		Iterator<JJSReferral> iter = getReferralsForJuvenile(juv).iterator();
		if (iter.hasNext())
		{
			return iter.next();
		}
		return null;
	}

	/**
	 * Returns Referrals From a single Casefiles for a juvenile
	 */
	// Profile stripping fix task 97536
	//public static List getReferralsForJuvenilesCasefile(Juvenile juv, String casefileId)	
	public static List getReferralsForJuvenilesCasefile(JuvenileCore juv, String casefileId)
	{
		List referralList = new ArrayList();
		Map referralIndex = new HashMap();

		// Build referrals index
		GetJuvenileProfileReferralListEvent refEvent = new GetJuvenileProfileReferralListEvent();
		refEvent.setJuvenileNum(juv.getOID().toString());
		Iterator<JJSReferral> referralIter = JJSReferral.findAll(refEvent);
		while (referralIter.hasNext())
		{
			JJSReferral referral = referralIter.next();
			referralIndex.put(referral.getReferralNum(), referral);
		}

		Iterator<JuvenileCasefile> casefiles = juv.getCaseFiles().iterator();
		while (casefiles.hasNext())
		{
			JuvenileCasefile casefile = casefiles.next();
			if (casefile.getOID().equalsIgnoreCase(casefileId)) 
			{
				Iterator<Assignment> assignments = casefile.getAssignments().iterator();
				while (assignments.hasNext())
				{
					Assignment assignment = assignments.next();

					JJSReferral referral = (JJSReferral) referralIndex.get(assignment.getReferralNumber());
					if (referral != null)
					{
						referralList.add(referral);
					}
				}
			}
			
		}

		return referralList;
	}
	
	/**
	 * Returns Referrals From All Casefiles for a Juvenile
	 */
	//public static List getReferralsForJuvenile(Juvenile juv)
	public static List getReferralsForJuvenile(JuvenileCore juv)
	{
		List referralList = new ArrayList();
		Map referralIndex = new HashMap();

		// Build referrals index
		GetJuvenileProfileReferralListEvent refEvent = new GetJuvenileProfileReferralListEvent();
		refEvent.setJuvenileNum(juv.getOID().toString());
		Iterator<JJSReferral> referralIter = JJSReferral.findAll(refEvent);
		while (referralIter.hasNext())
		{
			JJSReferral referral = referralIter.next();
			referralIndex.put(referral.getReferralNum(), referral);
		}

		Iterator<JuvenileCasefile> casefiles = juv.getCaseFiles().iterator();
		while (casefiles.hasNext())
		{
			JuvenileCasefile casefile = casefiles.next();

			Iterator<Assignment> assignments = casefile.getAssignments().iterator();
			while (assignments.hasNext()) 
			{
				Assignment assignment = assignments.next();

				JJSReferral referral = (JJSReferral) referralIndex.get(assignment.getReferralNumber());
				if (referral != null)
				{
					if (!referralList.contains(referral))
					{
						referralList.add(referral);
					}
				}
			}
		}

		return referralList;
	}
	
	/**
	 * Returns Referrals From All Casefiles for a Juvenile
	 */
	public static List getReferralsForJuvenileNoAssignments(Juvenile juv)
	{
		List referralList = new ArrayList();
		
		// Build referrals index
		GetJuvenileProfileReferralListEvent refEvent = new GetJuvenileProfileReferralListEvent();
		refEvent.setJuvenileNum(juv.getOID().toString());
		
		Iterator<JJSReferral> referralIter = JJSReferral.findAll(refEvent);
		while (referralIter.hasNext())
		{
			JJSReferral referral = referralIter.next();
			referralList.add(referral);
		}
		
		return referralList;
	}

	/**
	 * Returns the first petition for the first referral where the petition.jpoId
	 * == user.id and petition.courtDate > today.
	 */
	//public static JJSPetition getPetitionForReferral(Juvenile juvenile, JJSReferral referral)
	public static JJSPetition getPetitionForReferral(JuvenileCore juvenile, JJSReferral referral)
	{
		List petitionList = getPetitionsForReferral(juvenile, referral);
	    	
		//order the list by oid
		
		if (petitionList != null)
		{
		    if ( petitionList.size() > 0 ) {
			Collections.sort(petitionList, new Comparator<JJSPetition>(){
				@Override
				public int compare(JJSPetition p1, JJSPetition p2) {
				    return (p1.getOID().compareTo(p2.getOID()));
				  }
			});
			Collections.reverse(petitionList);
		    }
			Iterator<JJSPetition> petitionIter = petitionList.iterator();
			if (petitionIter.hasNext())
			{
				return petitionIter.next();
			}
		}
		return null;
	}

	/**
	 * 
	 */
	//public static List getPetitionsForReferral(Juvenile juvenile, JJSReferral referral)
	public static List getPetitionsForReferral(JuvenileCore juvenile, JJSReferral referral)
	{
		List petitionList = new ArrayList();

		// check for precondition here
		if (referral == null || 
				referral.getReferralNum() == null || 
				referral.getReferralNum().length() < 0)
		{
			return null;
		}

		GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
		petitionEvent.setJuvenileNum(juvenile.getOID().toString());
		petitionEvent.setReferralNum(referral.getReferralNum());

		Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
		while (petitionIter.hasNext())
		{
			JJSPetition petition = petitionIter.next();
			petitionList.add(petition);
		}

		return petitionList;
	}
	
	//public static List getPetitionsForReferral(Juvenile juvenile, JJSReferral referral)
	public static List getPetitionsForReferral(String juvenileNum, JJSReferral referral)
	{
		List petitionList = new ArrayList();

		// check for precondition here
		if (referral == null || 
				referral.getReferralNum() == null || 
				referral.getReferralNum().length() < 0)
		{
			return null;
		}

		GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
		petitionEvent.setJuvenileNum(juvenileNum);
		petitionEvent.setReferralNum(referral.getReferralNum());

		Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
		while (petitionIter.hasNext())
		{
			JJSPetition petition = petitionIter.next();
			petitionList.add(petition);
		}

		return petitionList;
	}	
	
	public static List<PetitionResponseEvent> getPetitionsRespForReferral(Juvenile juvenile, JJSReferral referral)
	{
		List petitionList = new ArrayList();

		// check for precondition here
		if (referral == null || 
				referral.getReferralNum() == null || 
				referral.getReferralNum().length() < 0)
		{
			return null;
		}

		GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
		petitionEvent.setJuvenileNum(juvenile.getOID().toString());
		petitionEvent.setReferralNum(referral.getReferralNum());
		PetitionResponseEvent resp = new PetitionResponseEvent();
		
		Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
		while (petitionIter.hasNext())
		{
			JJSPetition petition = petitionIter.next();
			resp = petition.valueObject();
			petitionList.add( resp );
		}

		return petitionList;
	}

    /**
     * task 25750 Get Petition for juvnumber and referral number combination
     * 
     * @param juvNum
     * @param referralNumber
     * @return list of petitions
     */
    public static List<PetitionResponseEvent> getPetitionsRespForReferral(
	    String juvNum, String referralNumber)
    {
	List petitionList = new ArrayList();

	// check for precondition here
	if (referralNumber == null || referralNumber == null || referralNumber.length() < 0)
	{
	    return null;
	}

	GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
	petitionEvent.setJuvenileNum(juvNum);
	petitionEvent.setReferralNum(referralNumber);
	PetitionResponseEvent resp = new PetitionResponseEvent();

	Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
	while (petitionIter.hasNext())
	{
	    JJSPetition petition = petitionIter.next();
	    resp = petition.valueObject();
	    petitionList.add(resp);
	}

	return petitionList;
    }
    
    

    /**
     * @param juvNum
     * @return
     */
    public static List<PetitionResponseEvent> getPetitionsForJuv(String juvNum)
    {
	List juvPetitions = new ArrayList();
	PetitionResponseEvent resp = new PetitionResponseEvent();
	Iterator<JJSPetition> petitionIter = JJSPetition.findAll("juvenileNum", juvNum);
	while (petitionIter.hasNext())
	{
	    JJSPetition petition = petitionIter.next();
	    resp = petition.valueObject();
	    juvPetitions.add(resp);
	}

	return juvPetitions;
    }
    /**
     * @param petitionnum
     * @return
     */
    public static List<PetitionResponseEvent> getPetitionsForpetNum(String petNum)
    {
	List juvPetitions = new ArrayList();
	PetitionResponseEvent resp = new PetitionResponseEvent();
	Iterator<JJSPetition> petitionIter = JJSPetition.findAll("petitionNum", petNum);
	while (petitionIter.hasNext())
	{
	    JJSPetition petition = petitionIter.next();
	    resp = petition.valueObject();
	    juvPetitions.add(resp);
	}

	return juvPetitions;
    }
    
    /**
     * @param cjis
     * @return
     */
    public static List<PetitionResponseEvent> getPetitionsForCJIS(String cjis)
    {
	List juvPetitions = new ArrayList();
	PetitionResponseEvent resp = new PetitionResponseEvent();
	
	GetPetitionByCJISEvent getPetitionEvt = new GetPetitionByCJISEvent();
	getPetitionEvt.setCJISNumber(cjis+"%");
	
	Iterator<JJSPetition> petitionItr = new ArrayList().iterator(); // empty iterator

	
	Iterator<JJSPetition> petitionIter = JJSPetition.findAll(getPetitionEvt); //
	while (petitionIter.hasNext())
	{
	    JJSPetition petition = petitionIter.next();
	    resp = petition.valueObject();
	    juvPetitions.add(resp);
	}

	return juvPetitions;
    }
	/**
	 * 
	 */
	public static JJSOffense getOffenseForReferral(Juvenile juvenile, JJSReferral referral)
	{
		//List referralList = getOffensesForReferral(juvenile, referral);
		List referralList = InterviewHelper.getOffensesForReferral(referral.getJuvenileNum(), referral.getReferralNum() );
		
		if (referralList != null)
		{
			Iterator iter = referralList.iterator();
			if (iter.hasNext())
			{
				return (JJSOffense) iter.next();
			}
		}
		return null;
	}
	
	
	public static JJSOffense getOffenseForReferral(String juvenileNum, String referralNum)
	{
		List referralList = getOffensesForReferral(juvenileNum, referralNum);
		if (referralList != null)
		{
			Iterator iter = referralList.iterator();
			if (iter.hasNext())
			{
				return (JJSOffense) iter.next();
			}
		}
		return null;
	}

	/**
	 * 
	 */
	//public static List<JJSOffense> getOffensesForReferral(Juvenile juvenile, JJSReferral referral)
	public static List<JJSOffense> getOffensesForReferral(JuvenileCore juvenile, JJSReferral referral)
	{
		List<JJSOffense> list = new ArrayList<JJSOffense>();

		// check for precondition here
		if (referral == null || 
				referral.getReferralNum() == null || 
				referral.getReferralNum().length() < 0)
		{
			return null;
		}

		GetJuvenileCasefileOffensesEvent event = new GetJuvenileCasefileOffensesEvent();
		event.setJuvenileNum(juvenile.getOID().toString());
		event.setReferralNum(referral.getReferralNum());

		Iterator<JJSOffense> iter = JJSOffense.findAll(event);
		while (iter.hasNext())
		{
			JJSOffense offense = iter.next();
			list.add(offense);
		}
		return list;
	}
	
	
	public static List<JJSOffense> getOffensesForReferral(String juvenileNum, String referralNum)
	{
		List<JJSOffense> list = new ArrayList<JJSOffense>();

		// check for precondition here
		if (referralNum == null
			|| referralNum == "")
		{
			return null;
		}

		GetJuvenileCasefileOffensesEvent event = new GetJuvenileCasefileOffensesEvent();
		event.setJuvenileNum(juvenileNum);
		event.setReferralNum(referralNum);

		Iterator<JJSOffense> iter = JJSOffense.findAll(event);
		while (iter.hasNext())
		{
			JJSOffense offense = iter.next();
			list.add(offense);
		}
		return list;
	}
	
	public static List<JJSOffenseResponseEvent> getOffenses(String juvenileNum, String referralNum){
	    
	    
	    if (referralNum == null
			|| referralNum == "")
		{
			return null;
		}
	    
	    GetJuvenileCasefileOffensesEvent getOffensesEvent = new GetJuvenileCasefileOffensesEvent();
	    getOffensesEvent.setJuvenileNum(juvenileNum);
	    getOffensesEvent.setReferralNum(referralNum);
	    CompositeResponse replyEvent = MessageUtil.postRequest(getOffensesEvent);
	    List<JJSOffenseResponseEvent> offenses = MessageUtil.compositeToList(replyEvent, JJSOffenseResponseEvent.class);
	    
	    
	    return offenses;

	}
	
	
	
	

	/**
	 * 
	 */
	public static JuvenileOffenderTrackingCharge getChargeForPetition(JJSPetition petition)
	{
		Iterator charges = getChargesForPetition(petition).iterator();
		if (charges.hasNext())
		{
			return (JuvenileOffenderTrackingCharge) charges.next();
		}
		
		return null;
	}

	/**
	 * 
	 */
	public static List getChargesForPetition(JJSPetition petition)
	{
		List chargeList = new ArrayList();

		Iterator<JuvenileOffenderTrackingCharge> charges = 
				new Home().findAll("petitionNum", petition.getPetitionNum(), JuvenileOffenderTrackingCharge.class);
		while (charges.hasNext())
		{
			JuvenileOffenderTrackingCharge charge = charges.next();
			chargeList.add(charge);
		}

		return chargeList;
	}

	/**
	 * 
	 */
	public static Disposition getDispositionForPetition(JJSPetition petition)
	{
		Iterator dispositions = new Home().findAll("peititionNum", petition.getPetitionNum(), Disposition.class);
		if (dispositions.hasNext())
		{
			return (Disposition) dispositions.next();
		}
		return null;
	}

	/**
	 * 
	 */
	public static List getOffensesForJuvenile(Juvenile juvenile)
	{
		List offenseList = new ArrayList();

		Iterator<JJSReferral> referralIter = getReferralsForJuvenile(juvenile).iterator();
		while (referralIter.hasNext())
		{
			JJSReferral referral = referralIter.next();

			GetJuvenileCasefileOffensesEvent casefileOffensesEvent = new GetJuvenileCasefileOffensesEvent();
			casefileOffensesEvent.setJuvenileNum(juvenile.getOID().toString());
			casefileOffensesEvent.setReferralNum(referral.getReferralNum());
			Iterator<JJSOffense> offenses = JJSOffense.findAll(casefileOffensesEvent);
			while (offenses.hasNext())
			{
				JJSOffense jjsOffense = offenses.next();
				offenseList.add(jjsOffense);
			}
		}

		return offenseList;
	}

    /** created for BUG 87254
     * @param juvenileNumber
     * @return
     */
    public static List getOffensesForJuvenile(String juvenileNumber) 
    {
	List offenseList = new ArrayList();

	Iterator i = JJSOffense.findAll("juvenileNum", juvenileNumber);
	while (i.hasNext())
	{
	    JJSOffense o = (JJSOffense) i.next();
	    JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();
	    resp.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
	    resp.setJuvenileNum(o.getJuvenileNum());
	    resp.setOffenseDate(o.getOffenseDate());
	    resp.setOffenseDescription(o.getOffenseDescription());
	    resp.setReferralNum(o.getReferralNum());
	    resp.setSequenceNum(o.getSequenceNum());
	    resp.setCatagory(o.getCatagory());
	    resp.setCitationCode(o.getCitationCode());
	    resp.setCitationSource(o.getCitationSource());
	    resp.setSequenceNum(o.getSequenceNum());
	    resp.setInvestigationNum(o.getInvestigationNumber());
	    resp.setOffenseReportGroup(o.getOffenseReportGroup());
	    resp.setOffenseCodeId(o.getOffenseCodeId());
	    resp.setOffenseCode(o.getOffenseCode().getOffenseCode());
	    resp.setSeveritySubtype(o.getOffenseCode().getSeveritySubtype()); //added for user-story #32226
	    resp.setKeyMapLocation(o.getKeyMapLocation());
	    offenseList.add(resp);
	}

	return offenseList;
    }
	
	/**
	 * 
	 */
	public static int getMembersInHousehold(Juvenile juvenile)
	{
		int membersInHome = 0;
		FamilyConstellation constellation = juvenile.getCurrentFamilyConstellation();
		
		Iterator<FamilyConstellationMember> members = constellation.getFamilyConstellationMembers().iterator();
		while (members.hasNext())
		{
			FamilyConstellationMember constMember = members.next();
			if (constMember.isInHomeStatus())
			{
				membersInHome++;
			}
		}
		return membersInHome;
	}

	/**
	 * 
	 */
	public static int getGuardiansInHousehold(Juvenile juvenile)
	{
		int guardiansInHome = 0;
		
		Iterator<FamilyConstellationMember> guardians = getGuardiansForJuvenile(juvenile).iterator();
		while (guardians.hasNext())
		{
			FamilyConstellationMember guardian = guardians.next();
			if (guardian.isInHomeStatus())
			{
				guardiansInHome++;
			}
		}
		return guardiansInHome;
	}

	/**
	 * 
	 */
	public static List getGuardiansForJuvenile(Juvenile juvenile)
	{
		ArrayList guardians = new ArrayList();
		FamilyConstellation constellation = juvenile.getCurrentFamilyConstellation();
		
		Iterator<FamilyConstellationMember> members = constellation.getFamilyConstellationMembers().iterator();
		while (members.hasNext())
		{
			FamilyConstellationMember constMember = members.next();
			if (constMember.isGuardian())
			{
				guardians.add(constMember);
			}
		}

		return guardians;
	}

	/**
	 * 
	 */
	public static List getAdultCoactors(String daLogNumber)
	{
		ArrayList list = new ArrayList();

		Iterator<JuvenileOffenderTrackingCoActor> coActors = JuvenileOffenderTrackingCoActor.findAllByDaLogNum(daLogNumber);
		while (coActors.hasNext())
		{
			JuvenileOffenderTrackingCoActor coActor = coActors.next();
			try
			{
				int age = Integer.parseInt(coActor.getAge());
				if (age >= 18)
				{
					list.add(coActor);
				}
			}
			catch (NumberFormatException ex)
			{
				// Skip coActor
			}
		}

		return list;
	}

	/***********************************************************************************************************
	 * Misc. Utiltiy methods.
	 */

	/**
	 * 
	 */
	public static String formatName(String first, String middle, String last)
	{
		return new StringAppender().append(first, "").append(middle, " ").append(last, " ").toString();
	}
	
	/**
	 * 
	 */
	public static String formatNameLastNameFirst(String first, String middle, String last)
	{
		return new StringAppender().append(last, "").append(", ", "").append(first, " ").append(middle, " ").toString();
	}
	
	/**
	 * 
	 */
	public static String formatNameFirstMiddleLastSuffix(String first, String middle, String last, String suffix)
	{
		return new StringAppender().append(first, " ").append(middle, " ").append(last, " ").append(suffix, " ").toString();
	}
	
	/**
	 * 
	 */
	public static String formatNameLastFirstMiddleSuffix(String first, String middle, String last, String suffix)
	{
		return new StringAppender().append(last, "").append(", ", "").append(first, " ").append(middle, " ").append(suffix, " ").toString();
	}

	/**
	 * 
	 */
	public static String formatAddress(Address address)
	{
		return new StringAppender().append(address.getStreetNum(), "").append(address.getStreetNumSuffix(), " ").append(address.getStreetName(), " ").append(address.getStreetType(), " ").append(address.getAptNum(), " ").append(address.getAddress2(), ", ").append(address.getCity(), ", ").append(address.getState(), ", ").append(address.getZipCode(), " ").toString();
	}

	/**
	 * 
	 */
	public static String formatAddress1(Address address)
	{
		StringBuffer formattedAddress1 = new StringBuffer();
		if (StringUtils.isNotEmpty(address.getStreetNum())){
			formattedAddress1.append(address.getStreetNum());
		}
		
		if (null != address.getStreetNumSuffix() && 
				StringUtils.isNotEmpty(address.getStreetNumSuffix().getDescription())){
			formattedAddress1.append(address.getStreetNumSuffix().getDescription());
			formattedAddress1.append(" ");
		}
		if (StringUtils.isNotEmpty(address.getStreetName())){
			formattedAddress1.append(address.getStreetName());
			formattedAddress1.append(" ");
		}
		if (null != address.getStreetType() &&
				StringUtils.isNotEmpty(address.getStreetType().getDescription())){
			formattedAddress1.append(address.getStreetType().getDescription());
			formattedAddress1.append(" ");
		}
		if (StringUtils.isNotEmpty(address.getAptNum())){
			formattedAddress1.append("Apt. ");
			formattedAddress1.append(address.getAptNum());
		}
		
		return formattedAddress1.toString();
	}
	
	/**
	 * 
	 */
	public static String formatAddress2(Address address)
	{
		StringBuffer formattedAddress2 = new StringBuffer();
		if (StringUtils.isNotEmpty(address.getCity())){
			formattedAddress2.append(address.getCity());
			formattedAddress2.append(", ");
		}
		
		if (StringUtils.isNotEmpty(address.getStateId())){
			formattedAddress2.append(address.getStateId());
			formattedAddress2.append(" ");
		}
		if (StringUtils.isNotEmpty(address.getZipCode())){
			formattedAddress2.append(address.getZipCode());
		}
		
		return formattedAddress2.toString();
	}	
	
	/**
	 * 
	 */
	public static String formatPhone(Phone phone)
	{
		PhoneNumberBean p = new PhoneNumberBean(phone.getPhoneNumber());
		return new StringAppender().append(p.getFormattedPhoneNumber(), "").append(phone.getPhoneExt(), " x").toString();
	}

	/**
	 * @param salaryStr
	 * @param salaryRateId
	 * @param workHoursStr
	 * @return
	 */
	public static double calculateAnnualIncome(double salary, String salaryRateId, double workHours)
	{
		try
		{
			if ("HR".equals(salaryRateId))
			{
				return salary * workHours * 52;
			}
			else if ("WK".equals(salaryRateId))
			{
				return salary * 52;
			}
			else if ("BW".equals(salaryRateId))
			{
				return salary * 26;
			}
			else if ("MO".equals(salaryRateId))
			{
				return salary * 12;
			}
			else if ("YR".equals(salaryRateId))
			{
				return salary;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 */
	private static class StringAppender
	{
		private StringBuffer	buf	= new StringBuffer();

		/*
		 * 
		 */
		public StringAppender append(String value, String joiner)
		{
			if ( notNullNotEmptyString( value ))
			{
				if (buf.length() > 0 && joiner != null)
				{
					buf.append(joiner);
				}
				buf.append(value);
			}
			return this;
		}

		/*
		 * 
		 */
		public StringAppender append(Code code, String joiner)
		{
			if (code != null && code.getDescription() != null)
			{
				return append(code.getDescription(), joiner);
			}
			return this;
		}

		public String toString()
		{
			return buf.toString();
		}
	}

	/**
	 * 
	 */
	private static class StringHelper
	{
		/*
		 * 
		 */
		public static StringAppender append(StringAppender appender, String aString, String joiner)
		{
			if (appender == null)
			{
				appender = new StringAppender();
			}

			appender.append(aString, joiner);

			return appender;
		}
	}

	/**
	 * 
	 */
	private static class CurrencyHelper
	{
		private static NumberFormat	formatter	= DecimalFormat.getCurrencyInstance();

		static
		{
			formatter.setMaximumFractionDigits(2);
		}

		/*
		 * 
		 */
		public static String format(double amount)
		{
			return formatter.format(amount);
		}
	}

	/**
	 * 
	 */
	private static class DateHelper
	{
		public final static SimpleDateFormat	STD				= new SimpleDateFormat("MM/dd/yyyy");
		public final static SimpleDateFormat	TXT_DATE	= new SimpleDateFormat("MMMM d, yyyy");
		public final static SimpleDateFormat	TXT_TIME	= new SimpleDateFormat("h:mm a");

		/*
		 * 
		 */
		public static String format(Date date)
		{
			return format(date, STD);
		}

		/*
		 * 
		 */
		public static String format(Date date, SimpleDateFormat formatter)
		{
			if (date == null)
			{
				return "";
			}
			return formatter.format(date);
		}
		
		public static String format(int date, SimpleDateFormat formatter)
		{
			if ( date == 0 )
			{
				return "";
			}
			return formatter.format(date);
		}
	}

	private static boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}

}
