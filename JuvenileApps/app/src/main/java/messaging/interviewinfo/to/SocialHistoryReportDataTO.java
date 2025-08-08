package messaging.interviewinfo.to;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.reply.ActivityResponseEvent;

/**
 *
 */
public class SocialHistoryReportDataTO
{
	// The following attributes are implemented as strings for the
	// benefit of the reporting tools where they are used.
	private String juvenileName = "";
	private String dateOfBirth = "";
	private String juvenileNumber = "";
	private String dateInterviewed = "";
	private String cupsUnit = "";
	private String numberInHousehold = "";
	private boolean allGuardiansInHome = true;
	private String detentionAdmitDate = "";
			
	private boolean verifiedDOB; 
	private String verifiedDOBAsString;
	
	private boolean multiracial;
	private String multiracialAsString;
	
	private boolean hispanic;
	private String hispanicAsString;

	private List gangTraits = new ArrayList(); // of TraitTO (.traitComments)
	private List strengthTraits = new ArrayList(); // of TraitTO
	private List substanceAbuseTraits = new ArrayList(); // of TraitTO
	private List substanceAbuseInformation = new ArrayList(); // of SubstanceAbuseInformationTO
	private List educationalHistory = new ArrayList(); // of SchoolHistoryTO
	private List schoolAttendanceTraits = new ArrayList(); // of TraitTO - trait code: 12
	private List schoolBehaviorTraits = new ArrayList(); // of TraitTO - trait code: 11
	private List educationalPerformanceTraits = new ArrayList(); // of TraitTO - trait code: 1

	private List juvenileFacilityStayRecord = new ArrayList();
	private List mentalHealthTestingHistory = new ArrayList();
	private List programReferrals = new ArrayList();
	private List programReferralList = new ArrayList();

	private List employmentHistory = new ArrayList(); // of EmploymentHistoryTO
	private List familyFinancialHistory = new ArrayList(); // of FamilyInformationTO
	private List familyInformation= new ArrayList(); // of FamilyInformationTO
	private List juvTransferredOffenses = new ArrayList(); //added for 11181 MJCW Add transferred offense data
	private List compliantSupervisionRules = new ArrayList(); // of SupervisionRuleTO (? .resolvedDesc)
	private List noncompliantSupervisionRules = new ArrayList(); // of SupervisionRuleTO
	private List warrantHistory = new ArrayList(); // of WarrantInformationTO
	private List refInitiationWarrantHistory = new ArrayList(); // of WarrantInformationTO
	private List referralDetails = new ArrayList(); // ?
	private List referralHistory = new ArrayList(); // of ReferralTO ?
	private List juvenileOffenses = new ArrayList(); // of OffenseInformationTO
	private List presentOffenses = new ArrayList(); // of OffenseInformationTO
	private List presentOffensesForGeneric = new ArrayList(); // of OffenseInformationTO
	private List subsequentReferralDetails = new ArrayList(); // ?
	private List previousReferralDetails = new ArrayList(); // ?
	private List currentReferralDetails = new ArrayList(); // added for US #14450
	private List JPCourtReferrals = new ArrayList(); // of JPCourtReferralTO. Used for the UI list of defendants.
	private List JPCourtReferralConvictions = new ArrayList(); // of JPCourtReferralConvictionTO.
																															// Used for the convictions of a defendant.
	// Significant Information section (only for EVS type activity)
	private List evsActivityComments = new ArrayList();
	private List pofActivities = new ArrayList();
	//added for Task 37996; User story 11077 : CRIS Report Speciality Supervision Type
	private List<ReferralTO> supPrgmRefList = new ArrayList<ReferralTO> ();
	private String probationOfficer;
	private String jpoSupervisor;

	// JPO Data / Court DispositionAlternatives
	private boolean useCourtDispositionAlternatives = false;
	private String courtDispositionAlternative1 = null;
	private String courtDispositionAlternative2 = null;
	private String courtDispositionAlternative3 = null;

	private boolean reportToRefereeInitiation = false;
	
	// JPO Data / Detention Reasons
	private boolean useDetentionReason = false;
	private String detentionReasons = "";
	private String notifiedMethod = "";
	private String notifiedPerson = "";
	private String notificationReason = "";
	private String currentUser = "";
	private Date attorneyNotificationDate = null;
	private Date personNotificationDate = null;

	// by default isGeneric is selected
	private boolean isGeneric = true;
	//<KISHORE>JIMS200060775 : Add Social Hist. link to Program Ref Detail(PD)-KK
	private boolean warrantHistoryNeeded = true;
	
	private String gender = "";
	private String race = "";
	private String ageInYears = "";
	
	//added for Bug 12932
	private boolean hasPreviousTransferredOffense;
	private boolean hasSubsequentTransferredOffense;
	private boolean hasPresentTransferredOffense;
	private String draft = "";
	
	private String facilityName= ""; //added for US11094, task# 41503
	boolean inFacility = false; //added for US11094, task# 41503
	
	//added for US 11078, Task 47899
	private List facilityHistoryInfo = new ArrayList(); 
	
	public List getFacilityHistoryInfo() {
	    return facilityHistoryInfo;
	}

	public void setFacilityHistoryInfo(List facilityHistoryInfo) {
	    this.facilityHistoryInfo = facilityHistoryInfo;
	}

	public boolean isInFacility() {
	    return inFacility;
	}

	public void setInFacility(boolean inFacility) {
	    this.inFacility = inFacility;
	}
	
	public String getFacilityName() {
	    return facilityName;
	}

	public void setFacilityName(String facilityName) {
	    this.facilityName = facilityName;
	}
	
	/**
	 * @return Returns the courtDispositionAlternative1.
	 */
	public String getCourtDispositionAlternative1()
	{
		return courtDispositionAlternative1;
	}

	/**
	 * @param courtDispositionAlternative1
	 *          The courtDispositionAlternative1 to set.
	 */
	public void setCourtDispositionAlternative1(String courtDispositionAlternative1)
	{
		this.courtDispositionAlternative1 = courtDispositionAlternative1;
	}

	/**
	 * @return Returns the courtDispositionAlternative2.
	 */
	public String getCourtDispositionAlternative2()
	{
		return courtDispositionAlternative2;
	}

	/**
	 * @param courtDispositionAlternative2
	 *          The courtDispositionAlternative2 to set.
	 */
	public void setCourtDispositionAlternative2(String courtDispositionAlternative2)
	{
		this.courtDispositionAlternative2 = courtDispositionAlternative2;
	}

	/**
	 * @return Returns the courtDispositionAlternative3.
	 */
	public String getCourtDispositionAlternative3()
	{
		return courtDispositionAlternative3;
	}

	/**
	 * @param courtDispositionAlternative3
	 *          The courtDispositionAlternative3 to set.
	 */
	public void setCourtDispositionAlternative3(String courtDispositionAlternative3)
	{
		this.courtDispositionAlternative3 = courtDispositionAlternative3;
	}

	/**
	 * @return Returns the detentionReasons.
	 */
	public String getDetentionReasons()
	{
		return detentionReasons;
	}

	/**
	 * @param detentionReasons
	 *          The detentionReasons to set.
	 */
	public void setDetentionReasons(String detentionReasons)
	{
		this.detentionReasons = detentionReasons;
	}

	/**
	 * @return Returns the notificationReason.
	 */
	public String getNotificationReason()
	{
		return notificationReason;
	}

	/**
	 * @param notificationReason
	 *          The notificationReason to set.
	 */
	public void setNotificationReason(String notificationReason)
	{
		this.notificationReason = notificationReason;
	}

	/**
	 * @return Returns the notifiedMethod.
	 */
	public String getNotifiedMethod()
	{
		return notifiedMethod;
	}

	/**
	 * @param notifiedMethod
	 *          The notifiedMethod to set.
	 */
	public void setNotifiedMethod(String notifiedMethod)
	{
		this.notifiedMethod = notifiedMethod;
	}

	/**
	 * @return Returns the notifiedPerson.
	 */
	public String getNotifiedPerson()
	{
		return notifiedPerson;
	}

	/**
	 * @param notifiedPerson
	 *          The notifiedPerson to set.
	 */
	public void setNotifiedPerson(String notifiedPerson)
	{
		this.notifiedPerson = notifiedPerson;
	}

	/**
	 * @return Returns the useCourtDispositionAlternatives.
	 */
	public boolean isUseCourtDispositionAlternatives()
	{
		return useCourtDispositionAlternatives;
	}

	/**
	 * @param useCourtDispositionAlternatives
	 *          The useCourtDispositionAlternatives to set.
	 */
	public void setUseCourtDispositionAlternatives(boolean useCourtDispositionAlternatives)
	{
		this.useCourtDispositionAlternatives = useCourtDispositionAlternatives;
	}

	/**
	 * @return Returns the useDetentionReason.
	 */
	public boolean isUseDetentionReason()
	{
		return useDetentionReason;
	}

	/**
	 * @param useDetentionReason
	 *          The useDetentionReason to set.
	 */
	public void setUseDetentionReason(boolean useDetentionReason)
	{
		this.useDetentionReason = useDetentionReason;
	}

	/**
	 * @return Returns the educationalHistory.
	 */
	public List getEducationalHistory()
	{
		return educationalHistory;
	}

	/**
	 * @return Returns the employmentHistory.
	 */
	public List getEmploymentHistory()
	{
		return employmentHistory;
	}

	/**
	 * @return Returns the financialHistoryFamilyInformation.
	 */
	public List getFamilyFinancialHistory()
	{
		return familyFinancialHistory;
	}
	
	/**
	 * @return Returns the financialHistoryFamilyInformation.
	 */
	public List getFamilyInformation()
	{
		return familyInformation;
	}
	
	/**
	 * @return Returns the juvTransferredOffenses.
	 */
	public List getJuvTransferredOffenses()
	{
		return juvTransferredOffenses;
	}
	
	public void setJuvTransferredOffenses(List juvTransferredOffenses) {
		this.juvTransferredOffenses = juvTransferredOffenses;
	}

	/**
	 * @return Returns the GangTraits.
	 */
	public List getGangTraits()
	{
		return gangTraits;
	}

	/**
	 * @return Returns the StrengthTraits.
	 */
	public List getStrengthTraits()
	{
		return strengthTraits;
	}

	/**
	 * @return Returns the SubstanceAbuseInformation.
	 */
	public List getSubstanceAbuseInformation()
	{
		return substanceAbuseInformation;
	}

	/**
	 * @return Returns the SubstanceAbuseTraits.
	 */
	public List getSubstanceAbuseTraits()
	{
		return substanceAbuseTraits;
	}

	/**
	 * @return Returns the presentOffense.
	 */
	public List getPresentOffenses()
	{
		return presentOffenses;
	}
	
	/**
	 * @return Returns the juvenileOffenses.
	 */
	public List getJuvenileOffenses()
	{
		return juvenileOffenses;
	}
	/**
	 * @return Returns the presentOffense.
	 */
	public List getPresentOffensesForGeneric()
	{
		return presentOffensesForGeneric;
	}

	/**
	 * @return Returns the referralHistory.
	 */
	public List getReferralHistory()
	{
		return referralHistory;
	}

	/**
	 * @return Returns the supervisionRulesComplient.
	 */
	public List getCompliantSupervisionRules()
	{
		return compliantSupervisionRules;
	}

	/**
	 * @return Returns the supervisionRulesNoncomplient.
	 */
	public List getNoncompliantSupervisionRules()
	{
		return noncompliantSupervisionRules;
	}

	/**
	 * @return Returns the warrantHistory.
	 */
	public List getWarrantHistory()
	{
		return warrantHistory;
	}

	/**
	 * @return Returns the courtDate.
	 */
	public String getCourtDateAsString()
	{
		Date date = getPresentOffense().getCourtDate();
		if( date != null )
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
			return sdf.format(date);
		}

		return "";
	}

	public String getPersonNotificationDateAsString()
	{
		if( personNotificationDate != null )
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
			return sdf.format(personNotificationDate);
		}

		return "";
	}

	public String getAttorneyNotificationDateAsString()
	{
		if( attorneyNotificationDate != null )
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
			return sdf.format(attorneyNotificationDate);
		}

		return "";
	}
	

	public OffenseInformationTO getPresentOffense()
	{
		OffenseInformationTO futureOffense = null;

		if( presentOffenses != null && presentOffenses.size() > 0 )
		{
			for( Iterator<OffenseInformationTO> presentOffenseIter = presentOffenses.iterator(); 
			presentOffenseIter.hasNext(); /* empty */)
			{
				OffenseInformationTO offense = presentOffenseIter.next();
				
				Calendar cal = Calendar.getInstance() ;
				cal.set( Calendar.HOUR_OF_DAY, 0 ) ;
				cal.set( Calendar.MINUTE, 0 ) ; 
				cal.set( Calendar.SECOND, 0 ) ; 
				cal.set( Calendar.MILLISECOND, 0 );
				Date aDate = cal.getTime();
				
				if( futureOffense == null && 
						offense.getCourtDate() != null && 
						( offense.getCourtDate().equals(aDate) || offense.getCourtDate().after(aDate) ) )
				{
					futureOffense = offense;
				}
				else if( offense.getCourtDate() != null && 
						futureOffense != null && 
						offense.getCourtDate().after(futureOffense.getCourtDate()) )
				{
					futureOffense = offense;
				}
			}
		}

		if( futureOffense == null )
		{
			futureOffense = new OffenseInformationTO();
		}

		return futureOffense;
	}

	public String getAllPetitionNum()
	{
		StringBuffer petitionNum = new StringBuffer();

		if( presentOffenses != null && presentOffenses.size() > 0 )
		{
			for( Iterator<OffenseInformationTO> petitionIter = presentOffenses.iterator(); petitionIter.hasNext(); /* empty */)
			{
				OffenseInformationTO petition = petitionIter.next();
				if( petition.getPetitionNumber() != null )
				{
					petitionNum.append(petition.getPetitionNumber());
					if( petitionIter.hasNext() )
					{
						petitionNum.append(", ");
					}
				}
			} // for
		}

		return petitionNum.toString();
	}
	
	public String getAllPetitionNumForGeneric()
	{
		StringBuffer petitionNum = new StringBuffer();

		if( presentOffensesForGeneric != null && presentOffensesForGeneric.size() > 0 )
		{
			for( Iterator<OffenseInformationTO> petitionIter = presentOffensesForGeneric.iterator(); petitionIter.hasNext(); /* empty */)
			{
				OffenseInformationTO petition = petitionIter.next();
				if( petition.getPetitionNumber() != null )
				{
					petitionNum.append(petition.getPetitionNumber());
					if( petitionIter.hasNext() )
					{
						petitionNum.append(", ");
					}
				}
			} // for
		}

		return petitionNum.toString();
	}
	
	public String getAllAmendNumForGeneric()
	{
		StringBuffer amendNum = new StringBuffer();

		if( presentOffensesForGeneric != null && presentOffensesForGeneric.size() > 0 )
		{
			for( Iterator<OffenseInformationTO> petitionIter = presentOffensesForGeneric.iterator(); petitionIter.hasNext(); /* empty */)
			{
				OffenseInformationTO petition = petitionIter.next();
				if( petition.getAmendmentNumber() != null )
				{
				    amendNum.append(petition.getAmendmentNumber());
					if( petitionIter.hasNext() )
					{
					    amendNum.append(", ");
					}
				}
			} // for
		}

		return amendNum.toString();
	}
	
	public String getAllPetitionAmendNumForGeneric(){
	    StringBuffer petitionAmendNum = new StringBuffer();
	    

		if( presentOffensesForGeneric != null && presentOffensesForGeneric.size() > 0 )
		{
			for( Iterator<OffenseInformationTO> petitionIter = presentOffensesForGeneric.iterator(); petitionIter.hasNext(); /* empty */)
			{
				OffenseInformationTO petition = petitionIter.next();
				if( petition.getPetitionNumber() != null )
				{
				    petitionAmendNum.append(petition.getPetitionNumber());
				    if( petition.getAmendmentNumber() != null
					    && petition.getAmendmentNumber().length() > 0 ){
					petitionAmendNum.append(" - " + petition.getAmendmentNumber());
				    }
				    if( petitionIter.hasNext() )
				    {
					    petitionAmendNum.append(", ");
				    }
				}
			} // for
		}
	    
	    return petitionAmendNum.toString();
	}
	
	public OffenseInformationTO getRecentPetitionForGeneric()
	{
	    
	    OffenseInformationTO recentPetition = new OffenseInformationTO () ;
	    	
	    if( presentOffensesForGeneric != null && presentOffensesForGeneric.size() > 0 )
		{
		    List<OffenseInformationTO> petitionNumList = presentOffensesForGeneric;
		    Collections.sort(petitionNumList);
		    recentPetition= petitionNumList.get(0);
		}
	    
	    
	    return recentPetition;
	}
	

	/**
	 * @return Returns the cupsUnit.
	 */
	public String getCupsUnit()
	{
		return cupsUnit;
	}

	/**
	 * @param cupsUnit
	 *          The cupsUnit to set.
	 */
	public void setCupsUnit(String cupsUnit)
	{
		this.cupsUnit = cupsUnit;
	}

	/**
	 * @return Returns the dateInerviewed.
	 */
	public String getDateInterviewed()
	{
		return dateInterviewed;
	}

	/**
	 * @param dateInerviewed
	 *          The dateInerviewed to set.
	 */
	public void setDateInterviewed(String dateInterviewed)
	{
		this.dateInterviewed = dateInterviewed;
	}

	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *          The dateOfBirth to set.
	 */
	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName()
	{
		return juvenileName;
	}

	/**
	 * @param juvenileName
	 *          The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName)
	{
		this.juvenileName = juvenileName;
	}

	/**
	 * @return Returns the juvenileNunber.
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param juvenileNunber
	 *          The juvenileNunber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber)
	{
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * @return Returns the numberInHousehold.
	 */
	public String getNumberInHousehold()
	{
		return numberInHousehold;
	}

	/**
	 * @param numberInHousehold
	 *          The numberInHousehold to set.
	 */
	public void setNumberInHousehold(String numberInHousehold)
	{
		this.numberInHousehold = numberInHousehold;
	}

	/**
	 * @return Returns the totalGrossFamilyIncome.
	 */
	public String getTotalGrossFamilyIncome()
	{
		double totalGross = 0;

		for( Iterator<FamilyInformationTO> iter = familyFinancialHistory.iterator(); 
				iter.hasNext(); /*empty*/)
		{
			FamilyInformationTO fam = iter.next();
			totalGross += fam.getTotalGross();
		}

		DecimalFormat fmt = new DecimalFormat();
		fmt.setMinimumFractionDigits(2);
		fmt.setMaximumFractionDigits(2);

		return fmt.format(totalGross);
	}

	/**
	 * @return Returns the jPCourtReferrals.
	 */
	public List getJPCourtReferrals()
	{
		return JPCourtReferrals;
	}

	/**
	 * @return Returns the jPCourtReferralConvictions.
	 */
	public List getJPCourtReferralConvictions()
	{
		return JPCourtReferralConvictions;
	}

	/**
	 * @return Returns the educationalPerformanceTraits.
	 */
	public List getEducationalPerformanceTraits()
	{
		return educationalPerformanceTraits;
	}

	/**
	 * @return Returns the schoolAttendanceTraits.
	 */
	public List getSchoolAttendanceTraits()
	{
		return schoolAttendanceTraits;
	}

	/**
	 * @return Returns the schoolBehaviorTraits.
	 */
	public List getSchoolBehaviorTraits()
	{
		return schoolBehaviorTraits;
	}

	/**
	 * @return Returns the allGuardiansInHome.
	 */
	public boolean isAllGuardiansInHome()
	{
		return allGuardiansInHome;
	}

	/**
	 * @param allGuardiansInHome
	 *          The allGuardiansInHome to set.
	 */
	public void setAllGuardiansInHome(boolean allGuardiansInHome)
	{
		this.allGuardiansInHome = allGuardiansInHome;
	}

	/**
	 * 
	 */
	public String getProbationOfficer()
	{
		return this.probationOfficer;
	}

	/**
	 * 
	 */
	public void setProbationOfficer(String aName)
	{
		this.probationOfficer = aName;
	}

	/**
	 * @return Returns the jpoSupervisor.
	 */
	public String getJpoSupervisor()
	{
		return jpoSupervisor;
	}

	/**
	 * @param jpoSupervisor
	 *          The jpoSupervisor to set.
	 */
	public void setJpoSupervisor(String jpoSupervisor)
	{
		this.jpoSupervisor = jpoSupervisor;
	}

	/**
	 * @return Returns the currentUser.
	 */
	public String getCurrentUser()
	{
		return currentUser;
	}

	/**
	 * @param currentUser
	 *          The currentUser to set.
	 */
	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}

	/**
	 * @return Returns the evsActivityComments.
	 */
	public List getEvsActivityComments()
	{
		return evsActivityComments;
	}

	/**
	 * @return Returns the personNotificationDate.
	 */
	public Date getPersonNotificationDate()
	{
		return personNotificationDate;
	}

	/**
	 * @param personNotificationDate
	 *          The personNotificationDate to set.
	 */
	public void setPersonNotificationDate(Date personNotificationDate)
	{
		this.personNotificationDate = personNotificationDate;
	}

	/**
	 * @return Returns the referralDetails.
	 */
	public List getReferralDetails()
	{
		return referralDetails;
	}

	/**
	 * @return Returns the referralDetails.
	 */
	public List getRefWPetDetails()
	{
		ArrayList myList = new ArrayList();

		if( referralDetails != null && referralDetails.size() > 0 )
		{
			for( Iterator<ReferralTO> iterator = referralDetails.iterator(); iterator.hasNext(); /* empty */)
			{
				ReferralTO myTo = iterator.next();
				if( myTo.isIncluded() && myTo.isPetitionAvailable() )
				{
					myList.add(myTo);
				}
			}
		}

		return myList;
	}

	/**
	 * @return Returns the referralDetails.
	 */
	public List getRefWOPetDetails()
	{
		ArrayList myList = new ArrayList();
		
		if( referralDetails != null && referralDetails.size() > 0 )
		{
			for( Iterator<ReferralTO> iterator = referralDetails.iterator();
					iterator.hasNext(); /*empty*/ )
			{
				ReferralTO myTo = iterator.next();
				if( myTo.isIncluded()  &&  !myTo.isPetitionAvailable() )
				{
					myList.add(myTo);
				}
			}
		}

		return myList;
	}

	/**
	 * @return Returns the referralDetails.
	 */
	public List getIncludedReferralDetails()
	{
		ArrayList myList = new ArrayList();
		if( referralDetails != null && referralDetails.size() > 0 )
		{
			for( Iterator<ReferralTO> iterator = referralDetails.iterator();
					iterator.hasNext(); /*empty*/ )
			{
				ReferralTO myTo = iterator.next();
				if( myTo.isIncluded() )
				{
					myList.add(myTo);
				}
			}
		}
		
		return myList;
	}

	/**
	 * @param referralDetails
	 *          The referralDetails to set.
	 */
	public void setReferralDetails(List referralDetails)
	{
		this.referralDetails = referralDetails;
	}

	/**
	 * @return Returns the attorneyNotificationDate.
	 */
	public Date getAttorneyNotificationDate()
	{
		return attorneyNotificationDate;
	}

	/**
	 * @param attorneyNotificationDate
	 *          The attorneyNotificationDate to set.
	 */
	public void setAttorneyNotificationDate(Date attorneyNotificationDate)
	{
		this.attorneyNotificationDate = attorneyNotificationDate;
	}

	/**
	 * @return Returns the activities.
	 */
	public List getPofActivities()
	{
		return pofActivities;
	}

	/**
	 * @param activities
	 *          The activities to set.
	 */
	public void setPofActivities(List pofActivities)
	{
		this.pofActivities = pofActivities;
	}

	public ActivityResponseEvent getLatestPofActivity()
	{
		if( pofActivities != null && pofActivities.size() > 0 )
		{
			return( (ActivityResponseEvent)pofActivities.get(pofActivities.size() -1) );
		}
		
		return null;
	}

	public ActivityResponseEvent getLatestEvsActivity()
	{
		if( evsActivityComments != null && evsActivityComments.size() > 0 )
		{
			return( (ActivityResponseEvent)evsActivityComments.get(evsActivityComments.size() -1) );
		}
		
		return null;
	}

	public boolean isGeneric()
	{
		return isGeneric;
	}

	public void setGeneric(boolean isGeneric)
	{
		this.isGeneric = isGeneric;
	}

	public List getJuvenileFacilityStayRecord()
	{
		return juvenileFacilityStayRecord;
	}

	public void setJuvenileFacilityStayRecord(List juvenileFacilityStayRecord)
	{
		this.juvenileFacilityStayRecord = juvenileFacilityStayRecord;
	}

	public List getMentalHealthTestingHistory()
	{
		return mentalHealthTestingHistory;
	}

	public void setMentalHealthTestingHistory(List mentalHealthTestingHistory)
	{
		this.mentalHealthTestingHistory = mentalHealthTestingHistory;
	}

	public List getProgramReferrals()
	{
		return programReferrals;
	}

	public void setProgramReferrals(List programReferrals)
	{
		this.programReferrals = programReferrals;
	}

	//37996
	
	public List getProgramReferralList()
	{
		return programReferralList;
	}

	public void setProgramReferralList(List programReferralList)
	{
		this.programReferralList = programReferralList;
	}

	/**
	 * @return the warrantHistoryNeeded
	 */
	public boolean isWarrantHistoryNeeded() {
		return warrantHistoryNeeded;
	}

	/**
	 * @param warrantHistoryNeeded the warrantHistoryNeeded to set
	 */
	public void setWarrantHistoryNeeded(boolean warrantHistoryNeeded) {
		this.warrantHistoryNeeded = warrantHistoryNeeded;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the sex
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param ageInYears the ageInYears to set
	 */
	public void setAgeInYears(String ageInYears) {
		this.ageInYears = ageInYears;
	}

	/**
	 * @return the hasPreviousTransferredOffense
	 */
	public boolean isHasPreviousTransferredOffense() {
		return hasPreviousTransferredOffense;
	}

	/**
	 * @param hasPreviousTransferredOffense the hasPreviousTransferredOffense to set
	 */
	public void setHasPreviousTransferredOffense(
			boolean hasPreviousTransferredOffense) {
		this.hasPreviousTransferredOffense = hasPreviousTransferredOffense;
	}

	/**
	 * @return the hasSubsequentTransferredOffense
	 */
	public boolean isHasSubsequentTransferredOffense() {
		return hasSubsequentTransferredOffense;
	}

	/**
	 * @param hasSubsequentTransferredOffense the hasSubsequentTransferredOffense to set
	 */
	public void setHasSubsequentTransferredOffense(
			boolean hasSubsequentTransferredOffense) {
		this.hasSubsequentTransferredOffense = hasSubsequentTransferredOffense;
	}

	/**
	 * @return the hasPresentTransferredOffense
	 */
	public boolean isHasPresentTransferredOffense() {
		return hasPresentTransferredOffense;
	}

	/**
	 * @param hasPresentTransferredOffense the hasPresentTransferredOffense to set
	 */
	public void setHasPresentTransferredOffense(boolean hasPresentTransferredOffense) {
		this.hasPresentTransferredOffense = hasPresentTransferredOffense;
	}

	/**
	 * @return the ageInYears
	 */
	public String getAgeInYears() {
		return ageInYears;
	}

	public void setDetentionAdmitDate(String detentionAdmitDate) {
		this.detentionAdmitDate = detentionAdmitDate;
	}

	public String getDetentionAdmitDate() {
		return detentionAdmitDate;
	}

	public void setVerifiedDOB(boolean verifiedDOB) {
		this.verifiedDOB = verifiedDOB;
	}

	public boolean isVerifiedDOB() {
		return verifiedDOB;
	}
	
	public String getVerifiedDOBAsString() {
		return verifiedDOB ? "Yes" : "No";
	}

	public void setMultiracial(boolean multiracial) {
		this.multiracial = multiracial;
	}

	public boolean isMultiracial() {
		return multiracial;
	}
	
	public String getMultiracialAsString() {
		return multiracial ? "Yes" : "No";
	}

	public void setHispanic(boolean hispanic) {
		this.hispanic = hispanic;
	}
	
	public boolean isHispanic() {
		return hispanic;
	}
	
	public String getHispanicAsString() {
		return hispanic ? "Yes" : "No";
	}

	public void setSubsequentReferralDetails(List subsequentReferralDetails) {
		this.subsequentReferralDetails = subsequentReferralDetails;
	}

	public List getSubsequentReferralDetails() {
		return subsequentReferralDetails;
	}

	public void setPreviousReferralDetails(List previousReferralDetails) {
		this.previousReferralDetails = previousReferralDetails;
	}

	public List getPreviousReferralDetails() {
		return previousReferralDetails;
	}

	public boolean isReportToRefereeInitiation() {
		return reportToRefereeInitiation;
	}

	public void setReportToRefereeInitiation(boolean reportToRefereeInitiation) {
		this.reportToRefereeInitiation = reportToRefereeInitiation;
	}

	public List getRefInitiationWarrantHistory() {
		return refInitiationWarrantHistory;
	}

	public void setRefInitiationWarrantHistory(List refInitiationWarrantHistory) {
		this.refInitiationWarrantHistory = refInitiationWarrantHistory;
	}

	public List getCurrentReferralDetails() {
		return currentReferralDetails;
	}

	public void setCurrentReferralDetails(List currentReferralDetails) {
		this.currentReferralDetails = currentReferralDetails;
	}

	public List<ReferralTO> getSupPrgmRefList() {
		return supPrgmRefList;
	}

	public void setSupPrgmRefList(List<ReferralTO> supPrgmRefList) {
		this.supPrgmRefList = supPrgmRefList;
	}

	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}

	
}
