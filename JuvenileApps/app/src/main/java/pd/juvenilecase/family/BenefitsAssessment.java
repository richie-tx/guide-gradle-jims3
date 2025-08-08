package pd.juvenilecase.family;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.family.GetRequestedBenefitsAssessmentByJuvenileEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeHelper;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;

/**
 * NOTE: Question numbers below are from the "Title IV-E Placement Screening Sheet" (TJPC-FIS-15-04). 
 * The part after the '-' is added to uniquely identify a part of a question.
 */
public class BenefitsAssessment extends PersistentObject 
{
	/**
	 * Juvenile
	 */
    private String juvenileId;
	//private Juvenile juvenile;		// transient
    	private JuvenileCore juvenile;

	/**
	 * Casefile
	 */
	private String casefileId;
	private JuvenileCasefile casefile;	// transient

   
	/**
	 * Guardian
	 */
   	private String guardianId;
   	private FamilyConstellationMember guardian;	// transient

   	/**
   	 * Date stamp when the request was made. Date is assigned 
   	 * when the assessment is created.
   	 */ 
   	private Date requestDate;
   	
   	/**
   	 * Date stamp for when the assessment was completed. Date is assigned when 
   	 * the assessment is saved.
   	 */
   	private Date entryDate;
   
   
   	/**
   	 * Medicaid eligibility.
   	 */
   	private boolean isEligibleForMedicaid;
   	private boolean isReceivingMedicaid;
   	
	/**
	 * Title IVe eligibility.
	 */
   	private boolean isEligibleForTitleIVe;
   	private boolean isReceivingTitleIVe;
   
	/**
	 * Question 1-100.
	 */
	private boolean isLegalResident;
   
    /**
     * Question 2a-201.
     */
    private boolean oneParentIsStepparent;
   
    /**
     * Question 2a-202.
     */
    private boolean deathOrAbsence;
   
    /**
     * Question 2a-203.
     */
    private boolean incapacityOrDisabilityOfParent;
   
    /**
     * Question 2a-204.
     */
    private boolean primaryWageEarnerUnderemployment;
   
    /**
     * Question 2a-205.
     */
    private boolean pweWorkedLessThen100Hours;
   
    /**
     * Question 2a-206.
     */
    private boolean pweIncomeLessThanUnderemployedLimit;
   
    /**
     * Question 2a1-207.
     */
    private String pweRelationshipToJuvenileId;
   
	/**
	 *  
	 */
	private String pweRelationshipToJuvenile;
   
    /**
     * Question 2a2-208.
     * 
     * This question and the following (2a3-209) my both be answered no but only
     * one may be answered yes.  
     */
    private boolean pweWorkedSteadyLessThan100Hours;
   
    /**
     * Question 2a3-209.
     */
    private boolean pweWorkedIrregularLessThan100HoursAvg;
   
    /**
     * Monetary (saved in pennies)
     * Question 2a4-210.
     */
    private int pweGrossMonthlyIncomeForOver100Hours;
   
    /**
     * Question 3-301.
     */
    private boolean wasChildLivingWithParent;
   
    /**
     * Question 4-401.
     */
    private boolean afdcIncomeLimitsMet;
   
    /**
     * Question 4-402.
     * 
     * Contains the IndividualIncomeDetermination entries.
     */
    private Collection afdcIncomeWorksheetItems;
   
	/**
	 * Total number of people in certified group.
	 */
	private int afdcIncomeCertifiedGroupSize;
   
	/**
	 * Number of parents in the certified group.
	 */
	private int afdcIncomeCertifiedGroupParentsSize;
   
	/**
	 * Income limit for the certified group.
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeCertifiedGroupLimit;
   
    /**
     * Stepparents monthly gross earned income.
     * Monetary (saved in pennies)
     * Question 4-403.
     */
    private int afdcIncomeStepparentsMonthlyGross;
   
    /**
     * Work related expenses (standard deduction of $90)  
     * Monetary (saved in pennies)
     * Question 4-404.
     */
    private int afdcIncomeStepparentWorkRelatedExpenses;
   
	/**
	 * Countable earned monthly ioncome.
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeStepparentCountableEarnedMonthy;
   
    /**
     * Other monthly income of stepparent.
     * Monetary (saved in pennies)
     * Question 4-405.
     */
    private int afdcIncomeStepparentOtherMonthlyIncome;
   
	/**
	 * Total countable monthly ioncome.
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeStepparentTotalCountableMonthy;
   
    /**
     * Monthly payments to dependents outside of home. 
     * Monetary (saved in pennies)
     * Question 4-406.
     */
    private int afdcIncomeStepparentMonthyPaymentsToDependent;
   
    /**
     * Monthly alimony and child support payments outside of home.
     * Monetary (saved in pennies)
     * Question 4-407.
     */
    private int afdcIncomeStepparentMonthyAlimonyChildSupport;
   
	/**
	 * Allowance amount for stepparent and non-certifed dependents taken 
	 * from the stepparent deduction chart. 
	 * Monetary (saved in pennies)
	 * 
	 * 
	 */
	private int afdcIncomeStepparentNoncertifiedCount;
   
	/**
	 * Allowance amount for stepparent and non-certifed dependents taken 
	 * from the stepparent deduction chart. 
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeStepparentAllowanceAmount;
   
	/**
	 * Applied income of stepparent.
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeStepparentAppliedIncome;
   
	/**
	 * Total monthly gross income of certified group. 
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeTotalMonthy;
   
	/**
	 * Total countable income. 
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeTotalCountable;
   
    /**
     * Question 5-501.
     */
    private boolean under10KLimit;
   
    /**
     * Question 6-601.
     */
    private boolean childMeetsEligibilityCriteria;
    
    /**
     * Contains ?.  probably a Code table. 
     */
    private Collection sourcesForAFDCInformation;
   
    /**
     * Question 7a-701.
     */
    private boolean orderContainsBestInterestLanguage;
   
    /**
     * Question 7b-702.
     */
    private boolean resonableEffortsMadeWithin60Days;
   
    /**
     * Question 7c-703.
     */
    private boolean ordersIncludeResponsibilityForCareAndPlacement;
   
    /**
     * Question 8-801.
     */
	private boolean childMeetsAFDCAndOrderRequirements;
   
	/**
	 * 
	 */
	private String titleIVeOfficerId;
	private String probationOfficerId;
	/**
	 * 
	 */
	private String titleIVeOfficerName;
	private String probationOfficerName;
	
	/*
	 * 
	 */
	private String requesterName;
	
	/**
	 * Review comments
	 */
	private Collection reviewComments;
	


	/* ------------------------------------------------------------------------
	 * Locator and Creational class methods.
	 */

	/**
	 * @param familyMemberNum
	 * @roseuid 436A39B301EF
	 */
	static public Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, BenefitsAssessment.class);
	}

	/**
	 * @param title4EId
	 * @roseuid 436A39B301F4
	 */
	static public BenefitsAssessment find( String id )
	{
		return (BenefitsAssessment) new Home().find(id, BenefitsAssessment.class);
	}
   
	/**
	 * @param title4EId
	 * @roseuid 436A39B301F4
	 */
	static public Iterator findAll( String attributeName, Object attributeValue )
	{
		return new Home().findAll( attributeName, attributeValue, BenefitsAssessment.class);
	}
   
	/**
	 * @param title4EId
	 * @roseuid 436A39B301F4
	 */
	static public BenefitsAssessment find( String attributeName, Object attributeValue )
	{
		return (BenefitsAssessment) new Home().find( attributeName, attributeValue, BenefitsAssessment.class);
	}
   
		
	/**
	 * Create a new assessment if one does not exist for the juvenile, otherwise return the existing one.
	 * @param juvenileNum
	 * @roseuid 436A39B301F6
	 */
	static public BenefitsAssessment requestNew( String juvId, String casefileId,String requesterName ) 
	{
		BenefitsAssessment ben;
		GetRequestedBenefitsAssessmentByJuvenileEvent evt = new GetRequestedBenefitsAssessmentByJuvenileEvent();
		evt.setJuvenileNum( juvId );
		Iterator iter = BenefitsAssessment.findAll( evt );
		if ( iter.hasNext() ) 
		{
			ben = (BenefitsAssessment)iter.next();
		}
		else
		{
			//ben = new BenefitsAssessment( juvId, casefileId );
			ben = new BenefitsAssessment( juvId, casefileId, requesterName );
			
		}

		return ben;
	}

	/* ------------------------------------------------------------------------
	 * Public methods
	 */
	
	/**
	 *  
	 */   
	public void startEntryForGuardian( FamilyConstellationMember aGuardian )
	{
		if ( isCompleted() )
		{
			throw new IllegalStateException( "This assessment has already been completed." );
		}
		
		if ( ! aGuardian.getFamilyConstellation().getJuvenile().equals(getJuvenile()) )
		{
			throw new IllegalStateException( "Guardian (id=" + aGuardian.getOID().toString() + ") is not a legal guardian for juvenile (id=" + getJuvenile().getOID().toString() + ")." );
		}
		
		
		
		setGuardianId( aGuardian.getOID().toString() );  

		// Setup certified family group.
		clearAFDCIncomeWorksheetItems();

		FamilyConstellation constellation = null;
		Iterator constellations = getJuvenile().getFamilyConstellationList().iterator();
		while ( constellations.hasNext() )
		{
			constellation = (FamilyConstellation)constellations.next();
			if ( constellation.isActive() )
			{
				break;
			}
		}
		// Profile stripping fix task 97538
		//Juvenile juv = getJuvenile();
		JuvenileCore juv = getJuvenile();
		//

		/*
		 * Create certified group for income determination
		 * Step 1: Add the juvenile
		 * Step 2: Add all guardians
		 * Step 3: Add sibligs under age 18.
		 */

		// Add Juvenile to Certified Group
		IndividualIncomeDetermination incDeter = new IndividualIncomeDetermination();
		incDeter.setBenefitsAssessmentId( getOID().toString() );
		incDeter.setMemberId( juv.getOID().toString() );
		incDeter.setRelationshipToJuvenileId( null );
		if(juv.getLastName()==null || juv.getLastName().equalsIgnoreCase("null")){
			juv.setLastName("");
		}
		if(juv.getFirstName()==null || juv.getFirstName().equalsIgnoreCase("null")){
			juv.setFirstName("");
		}
		if(juv.getLastName().equals("") && juv.getFirstName().equals(""))
			incDeter.setName("");
		else if(juv.getLastName().equals("") && !juv.getFirstName().equals(""))
			incDeter.setName(", " + juv.getFirstName() );
		else if(!juv.getLastName().equals("") && juv.getFirstName().equals(""))
			incDeter.setName(juv.getLastName() );
		
		int age = 0;
		Date dobDate = juv.getDateOfBirth();
		if ( dobDate != null )
		{
			GregorianCalendar dob = new GregorianCalendar();
			dob.setTime(dobDate);
			GregorianCalendar now = new GregorianCalendar();
			age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
			if ( now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR) )
			{
				age--;
			}
		}
		incDeter.setAge( age );
		insertAFDCIncomeWorksheetItems( incDeter );

		// Add Guardians and Siblings		
		/* --- Relationship Codes ---
		 * 
		 * The Certified Group information is a list of names 
		 * (Including the child, legal parent(s), and siblings 
		 * under 18 who were living in the home at the time of 
		 * intake (if screening at intake) or living in the home 
		 * of removal at time of removal) and includes income of 
		 * all persons included in the certified group.  
		 * NOTE:  The earned income of a child under 18 is 
		 * not counted if the child is attending school full-time 
		 * or attending school part-time and working less than 
		 * 30 hours per week. 	(See form TJPCFIS1505)	
		 * This list will be in Code Table JUVENILE_RELATIONSHIP_AFDC			
		 * 
		 */
		//NOTE: There's a bug with PDCodeHelper.getCodes(String, true) => 
		//sorting of the codes doesn't work and will throw class cast exception
		Collection afdcRelationshipCode = PDCodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE_AFDC, false);
		List afdcList = new ArrayList();
		
		for(Iterator afdcCodeIter = afdcRelationshipCode.iterator();afdcCodeIter.hasNext();) {
			Code code = (Code)afdcCodeIter.next();
			afdcList.add(code.getCode());
		}
		
		if ( constellation != null )
		{
			Iterator iter = constellation.getFamilyConstellationMembers().iterator();
			while ( iter.hasNext() )
			{
				FamilyConstellationMember constMember = (FamilyConstellationMember)iter.next();
				Code code = constMember.getRelationshipToJuvenile();
				
				// create a set of guardians and siblings.
				if ( constMember.isGuardian() ||
						afdcList.contains(code.getCode()) ) {
					FamilyMember famMember = constMember.getTheFamilyMember();

					age = 0;
					dobDate = famMember.getDateOfBirth();
					if ( dobDate != null )
					{
						GregorianCalendar dob = new GregorianCalendar();
						dob.setTime(dobDate);
						GregorianCalendar now = new GregorianCalendar();
						age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
						if ( now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR) )
						{
							age--;
						}
					}
					
					// Filter out the siblings 18 and over.
					//TODO: Restriction for age will be put off till later, 
					//since age is not a required field in family use-case
					//if ( constMember.isGuardian() || age < 18 )
					
					
						// Add the individual to the sertified group. 
						incDeter = new IndividualIncomeDetermination();
						incDeter.setBenefitsAssessmentId( getOID().toString() );
						incDeter.setMemberId( famMember.getOID().toString() );
						incDeter.setRelationshipToJuvenileId( code.getCode() );
						incDeter.setRelationshipToJuvenile( code.getDescription() );
						incDeter.setName( famMember.getLastName() + ", " + famMember.getFirstName() );
						incDeter.setAge( age );
						insertAFDCIncomeWorksheetItems( incDeter );
					
				}
			}
		}
	}
   
	/**
	 * 
	 */   
	public void completeEntry()
	{
		setEntryDate( new Date() );
	}
   
	/**
	 * 
	 */   
	public boolean isCompleted()
	{
		return getEntryDate() != null;
	}
   
   /**
    * Access method for the juvenileId property.
    * 
    * @return   the current value of the juvenileId property
    */
   public String getJuvenileId() 
   {
		fetch();
		return juvenileId;
   }
   
   /**
	* 
	*/
   //public Juvenile getJuvenile()
   public JuvenileCore getJuvenile()
   {
   		if ( juvenile == null )
   		{
   		    	// Profile stripping fix task 97538
			//juvenile = Juvenile.find( juvenileId);
   		    	juvenile = JuvenileCore.findCore( juvenileId);
			
   		}
		return juvenile;			
   }
   
   /**
    * Access method for the guardianId property.
    * 
    * @return   the current value of the guardianId property
    */
   public String getGuardianId() 
   {
		fetch();
    	return guardianId;
   }
   
   /**
	* Access method for the guardianId property.
	* 
	* @return   the current value of the guardianId property
	*/
   public FamilyConstellationMember getGuardian() 
   {
   		if ( guardian == null && getGuardianId() != null )
   		{
			guardian = (FamilyConstellationMember) new Home().find( guardianId, FamilyConstellationMember.class );
   		}
		return guardian;
   }
   
   /**
    * Access method for the requestDate property.
    * 
    * @return   the current value of the requestDate property
    */
   public Date getRequestDate() 
   {
   		fetch();
		return requestDate;
   }
   
   /**
    * Access method for the entryDate property.
    * 
    * @return   the current value of the entryDate property
    */
   public Date getEntryDate() 
   {
   		fetch();
		return entryDate;
   }
   
   /**
    * Determines if the isEligibleForMedicaid property is true.
    * 
    * @return   <code>true<code> if the isEligibleForMedicaid property is true
    */
   	public boolean isEligibleForMedicaid() 
   	{
		fetch();
		return isEligibleForMedicaid;
   	}

	// non-grammatical framework hack.
	public boolean getIsEligibleForMedicaid()
	{
		 return isEligibleForMedicaid();
	}
   
   /**
    * Sets the value of the isEligibleForMedicaid property.
    * 
    * @param aIsEligibleForMedicaid the new value of the isEligibleForMedicaid property
    */
   public void setIsEligibleForMedicaid(boolean aIsEligibleForMedicaid) 
   {
	markModified();
      isEligibleForMedicaid = aIsEligibleForMedicaid;
   }
   
   /**
    * Determines if the isReceivingMedicaid property is true.
    * 
    * @return   <code>true<code> if the isReceivingMedicaid property is true
    */
   	public boolean isReceivingMedicaid() 
   	{
		fetch();
      	return isReceivingMedicaid;
   	}
   
	// non-grammatical framework hack.
	public boolean getIsReceivingMedicaid()
	{
		 return isReceivingMedicaid();
	}
   
   /**
    * Sets the value of the isReceivingMedicaid property.
    * 
    * @param aIsReceivingMedicaid the new value of the isReceivingMedicaid property
    */
   	public void setIsReceivingMedicaid(boolean aIsReceivingMedicaid) 
   	{
		markModified();
      	isReceivingMedicaid = aIsReceivingMedicaid;
   	}
   
   /**
    * Determines if the isEligibleForTitleIVe property is true.
    * (English prose version)
    * 
    * @return   <code>true<code> if the isEligibleForTitleIVe property is true
    */
   	public boolean isEligibleForTitleIVe() 
   	{
   		fetch();
      	return isEligibleForTitleIVe;
   	}
   
	// non-grammatical framework hack.
	public boolean getIsEligibleForTitleIVe()
	{
		 return isEligibleForTitleIVe();
	}
   
   /**
    * Sets the value of the isEligibleForTitleIVe property.
    * 
    * @param aIsEligibleForTitleIVe the new value of the isEligibleForTitleIVe property
    */
   	public void setIsEligibleForTitleIVe(boolean aIsEligibleForTitleIVe) 
   	{
		markModified();
      	isEligibleForTitleIVe = aIsEligibleForTitleIVe;
   	}
   
   /**
    * Determines if the isReceivingTitleIVe property is true.
    * (English prose version)
    * 
    * @return   <code>true<code> if the isReceivingTitleIVe property is true
    */
   	public boolean isReceivingTitleIVe() 
   	{
		fetch();
      	return isReceivingTitleIVe;
   	}
   
	// non-grammatical framework hack.
	public boolean getIsReceivingTitleIVe()
	{
		 return isReceivingTitleIVe();
	}
   
   /**
    * Sets the value of the isReceivingTitleIVe property.
    * 
    * @param aIsReceivingTitleIVe the new value of the isReceivingTitleIVe property
    */
   	public void setIsReceivingTitleIVe(boolean aIsReceivingTitleIVe) 
   	{
		markModified();
      	isReceivingTitleIVe = aIsReceivingTitleIVe;
  	}
   
   /**
    * Determines if the isLegalResident property is true.
    * (English prose version)
    * 
    * @return   <code>true<code> if the isLegalResident property is true
    */
   	public boolean isLegalResident() 
   	{
		fetch();
      	return isLegalResident;
   	}
   
	// non-grammatical framework hack.
	public boolean getIsLegalResident()
	{
		 return isLegalResident();
	}
   
   /**
    * Sets the value of the isLegalResident property.
    * 
    * @param aIsLegalResident the new value of the isLegalResident property
    */
   public void setIsLegalResident(boolean aIsLegalResident) 
   {
	markModified();
      isLegalResident = aIsLegalResident;
   }
   
   /**
    * Determines if the oneParentIsStepparent property is true.
    * 
    * @return   <code>true<code> if the oneParentIsStepparent property is true
    */
   public boolean getOneParentIsStepparent() 
   {
	fetch();
      return oneParentIsStepparent;
   }
   
   /**
    * Sets the value of the oneParentIsStepparent property.
    * 
    * @param aOneParentIsStepparent the new value of the oneParentIsStepparent property
    */
   public void setOneParentIsStepparent(boolean aOneParentIsStepparent) 
   {
	markModified();
      oneParentIsStepparent = aOneParentIsStepparent;
   }
   
   /**
    * Determines if the deathOrAbsence property is true.
    * 
    * @return   <code>true<code> if the deathOrAbsence property is true
    */
   public boolean getDeathOrAbsence() 
   {
	fetch();
      return deathOrAbsence;
   }
   
   /**
    * Sets the value of the deathOrAbsence property.
    * 
    * @param aDeathOrAbsence the new value of the deathOrAbsence property
    */
   public void setDeathOrAbsence(boolean aDeathOrAbsence) 
   {
	markModified();
      deathOrAbsence = aDeathOrAbsence;
   }
   
   /**
    * Determines if the incapacityOrDisabilityOfParent property is true.
    * 
    * @return   <code>true<code> if the incapacityOrDisabilityOfParent property is true
    */
   public boolean getIncapacityOrDisabilityOfParent() 
   {
	fetch();
      return incapacityOrDisabilityOfParent;
   }
   
   /**
    * Sets the value of the incapacityOrDisabilityOfParent property.
    * 
    * @param aIncapacityOrDisabilityOfParent the new value of the incapacityOrDisabilityOfParent property
    */
   public void setIncapacityOrDisabilityOfParent(boolean aIncapacityOrDisabilityOfParent) 
   {
	markModified();
      incapacityOrDisabilityOfParent = aIncapacityOrDisabilityOfParent;
   }
   
   /**
    * Determines if the primaryWageEarnerUnderemployment property is true.
    * 
    * @return   <code>true<code> if the primaryWageEarnerUnderemployment property is true
    */
   public boolean getPrimaryWageEarnerUnderemployment() 
   {
	fetch();
      return primaryWageEarnerUnderemployment;
   }
   
   /**
    * Sets the value of the primaryWageEarnerUnderemployment property.
    * 
    * @param aPrimaryWageEarnerUnderemployment the new value of the primaryWageEarnerUnderemployment property
    */
   public void setPrimaryWageEarnerUnderemployment(boolean aPrimaryWageEarnerUnderemployment) 
   {
	markModified();
      primaryWageEarnerUnderemployment = aPrimaryWageEarnerUnderemployment;
   }
   
   /**
    * Determines if the pweWorkedLessThen100Hours property is true.
    * 
    * @return   <code>true<code> if the pweWorkedLessThen100Hours property is true
    */
   public boolean getPweWorkedLessThen100Hours() 
   {
	fetch();
      return pweWorkedLessThen100Hours;
   }
   
   /**
    * Sets the value of the pweWorkedLessThen100Hours property.
    * 
    * @param aPweWorkedLessThen100Hours the new value of the pweWorkedLessThen100Hours property
    */
   public void setPweWorkedLessThen100Hours(boolean aPweWorkedLessThen100Hours) 
   {
	markModified();
      pweWorkedLessThen100Hours = aPweWorkedLessThen100Hours;
   }
   
   /**
    * Determines if the pweIncomeLessThanUnderemployedLimit property is true.
    * 
    * @return   <code>true<code> if the pweIncomeLessThanUnderemployedLimit property is true
    */
   public boolean getPweIncomeLessThanUnderemployedLimit() 
   {
	fetch();
      return pweIncomeLessThanUnderemployedLimit;
   }
   
   /**
    * Sets the value of the pweIncomeLessThanUnderemployedLimit property.
    * 
    * @param aPweIncomeLessThanUnderemployedLimit the new value of the pweIncomeLessThanUnderemployedLimit property
    */
   public void setPweIncomeLessThanUnderemployedLimit(boolean aPweIncomeLessThanUnderemployedLimit) 
   {
	markModified();
      pweIncomeLessThanUnderemployedLimit = aPweIncomeLessThanUnderemployedLimit;
   }
   
   /**
    * Determines if the pweWorkedSteadyLessThan100Hours property is true.
    * 
    * @return   <code>true<code> if the pweWorkedSteadyLessThan100Hours property is true
    */
   public boolean getPweWorkedSteadyLessThan100Hours() 
   {
		fetch();
    	return pweWorkedSteadyLessThan100Hours;
   }
   
   /**
    * Sets the value of the pweWorkedSteadyLessThan100Hours property.
    * 
    * @param aPweWorkedSteadyLessThan100Hours the new value of the pweWorkedSteadyLessThan100Hours property
    */
   public void setPweWorkedSteadyLessThan100Hours(boolean aPweWorkedSteadyLessThan100Hours) 
   {
	markModified();
      pweWorkedSteadyLessThan100Hours = aPweWorkedSteadyLessThan100Hours;
   }
   
   /**
    * Determines if the pweWorkedIrregularLessThan100HoursAvg property is true.
    * 
    * @return   <code>true<code> if the pweWorkedIrregularLessThan100HoursAvg property is true
    */
   public boolean getPweWorkedIrregularLessThan100HoursAvg() 
   {
	fetch();
      return pweWorkedIrregularLessThan100HoursAvg;
   }
   
   /**
    * Sets the value of the pweWorkedIrregularLessThan100HoursAvg property.
    * 
    * @param aPweWorkedIrregularLessThan100HoursAvg the new value of the pweWorkedIrregularLessThan100HoursAvg property
    */
   public void setPweWorkedIrregularLessThan100HoursAvg(boolean aPweWorkedIrregularLessThan100HoursAvg) 
   {
	markModified();
      pweWorkedIrregularLessThan100HoursAvg = aPweWorkedIrregularLessThan100HoursAvg;
   }
   
   /**
    * Access method for the pweGrossMonthlyIncomeForOver100Hours property.
    * 
    * @return   the current value of the pweGrossMonthlyIncomeForOver100Hours property
    */
   public int getPweGrossMonthlyIncomeForOver100Hours() 
   {
		fetch();
      	return pweGrossMonthlyIncomeForOver100Hours;
   }
   
   /**
    * Sets the value of the pweGrossMonthlyIncomeForOver100Hours property.
    * 
    * @param aPweGrossMonthlyIncomeForOver100Hours the new value of the pweGrossMonthlyIncomeForOver100Hours property
    */
   public void setPweGrossMonthlyIncomeForOver100Hours(int aPweGrossMonthlyIncomeForOver100Hours) 
   {
	markModified();
      pweGrossMonthlyIncomeForOver100Hours = aPweGrossMonthlyIncomeForOver100Hours;
   }
   
   /**
    * Determines if the wasChildLivingWithParent property is true.
    * 
    * @return   <code>true<code> if the wasChildLivingWithParent property is true
    */
   public boolean getWasChildLivingWithParent() 
   {
	fetch();
      return wasChildLivingWithParent;
   }
   
   /**
    * Sets the value of the wasChildLivingWithParent property.
    * 
    * @param aWasChildLivingWithParent the new value of the wasChildLivingWithParent property
    */
   public void setWasChildLivingWithParent(boolean aWasChildLivingWithParent) 
   {
	markModified();
      wasChildLivingWithParent = aWasChildLivingWithParent;
   }
   
   /**
    * Determines if the afdcIncomeLimitsMet property is true.
    * 
    * @return   <code>true<code> if the afdcIncomeLimitsMet property is true
    */
   public boolean getAfdcIncomeLimitsMet() 
   {
	fetch();
      return afdcIncomeLimitsMet;
   }
   
   /**
    * Sets the value of the afdcIncomeLimitsMet property.
    * 
    * @param aAfdcIncomeLimitsMet the new value of the afdcIncomeLimitsMet property
    */
   public void setAfdcIncomeLimitsMet(boolean aAfdcIncomeLimitsMet) 
   {
	markModified();
      afdcIncomeLimitsMet = aAfdcIncomeLimitsMet;
   }
   
   /**
    * Access method for the afdcIncomeStepparentsMonthlyGross property.
    * 
    * @return   the current value of the afdcIncomeStepparentsMonthlyGross property
    */
   public int getAfdcIncomeStepparentsMonthlyGross() 
   {
	fetch();
      return afdcIncomeStepparentsMonthlyGross;
   }
   
   /**
    * Sets the value of the afdcIncomeStepparentsMonthlyGross property.
    * 
    * @param aAfdcIncomeStepparentsMonthlyGross the new value of the afdcIncomeStepparentsMonthlyGross property
    */
   public void setAfdcIncomeStepparentsMonthlyGross(int aAfdcIncomeStepparentsMonthlyGross) 
   {
	markModified();
      afdcIncomeStepparentsMonthlyGross = aAfdcIncomeStepparentsMonthlyGross;
   }
   
   /**
    * Access method for the afdcIncomeStepparentWorkRelatedExpenses property.
    * 
    * @return   the current value of the afdcIncomeStepparentWorkRelatedExpenses property
    */
   public int getAfdcIncomeStepparentWorkRelatedExpenses() 
   {
	fetch();
      return afdcIncomeStepparentWorkRelatedExpenses;
   }
   
   /**
    * Sets the value of the afdcIncomeStepparentWorkRelatedExpenses property.
    * 
    * @param aAfdcIncomeStepparentWorkRelatedExpenses the new value of the afdcIncomeStepparentWorkRelatedExpenses property
    */
   public void setAfdcIncomeStepparentWorkRelatedExpenses(int aAfdcIncomeStepparentWorkRelatedExpenses) 
   {
		markModified();
		afdcIncomeStepparentWorkRelatedExpenses = aAfdcIncomeStepparentWorkRelatedExpenses;
   }
   
   /**
    * Access method for the afdcIncomeStepparentOtherMonthlyExpenses property.
    * 
    * @return   the current value of the afdcIncomeStepparentOtherMonthlyExpenses property
    */
   public int getAfdcIncomeStepparentOtherMonthlyIncome() 
   {
		fetch();
		return afdcIncomeStepparentOtherMonthlyIncome;
   }
   
   /**
    * Sets the value of the afdcIncomeStepparentOtherMonthlyExpenses property.
    * 
    * @param aAfdcIncomeStepparentOtherMonthlyExpenses the new value of the afdcIncomeStepparentOtherMonthlyExpenses property
    */
   public void setAfdcIncomeStepparentOtherMonthlyIncome(int aAfdcIncomeStepparentOtherMonthlyIncome) 
   {
		markModified();
		afdcIncomeStepparentOtherMonthlyIncome = aAfdcIncomeStepparentOtherMonthlyIncome;
   }
   
   /**
    * Access method for the afdcIncomeStepparentMonthyPaymentsToDependent property.
    * 
    * @return   the current value of the afdcIncomeStepparentMonthyPaymentsToDependent property
    */
   public int getAfdcIncomeStepparentMonthyPaymentsToDependent() 
   {
	fetch();
      return afdcIncomeStepparentMonthyPaymentsToDependent;
   }
   
   /**
    * Sets the value of the afdcIncomeStepparentMonthyPaymentsToDependent property.
    * 
    * @param aAfdcIncomeStepparentMonthyPaymentsToDependent the new value of the afdcIncomeStepparentMonthyPaymentsToDependent property
    */
   public void setAfdcIncomeStepparentMonthyPaymentsToDependent(int aAfdcIncomeStepparentMonthyPaymentsToDependent) 
   {
	markModified();
      afdcIncomeStepparentMonthyPaymentsToDependent = aAfdcIncomeStepparentMonthyPaymentsToDependent;
   }
   
   /**
    * Access method for the afdcIncomeStepparentMonthyAimonyChilSupport property.
    * 
    * @return   the current value of the afdcIncomeStepparentMonthyAimonyChilSupport property
    */
   public int getAfdcIncomeStepparentMonthyAlimonyChildSupport() 
   {
	fetch();
      return afdcIncomeStepparentMonthyAlimonyChildSupport;
   }
   
   /**
    * Sets the value of the afdcIncomeStepparentMonthyAimonyChilSupport property.
    * 
    * @param aAfdcIncomeStepparentMonthyAimonyChilSupport the new value of the afdcIncomeStepparentMonthyAimonyChilSupport property
    */
   public void setAfdcIncomeStepparentMonthyAlimonyChildSupport(int aAfdcIncomeStepparentMonthyAlimonyChildSupport) 
   {
	markModified();
      afdcIncomeStepparentMonthyAlimonyChildSupport = aAfdcIncomeStepparentMonthyAlimonyChildSupport;
   }
   
   /**
    * Determines if the under10KLimit property is true.
    * 
    * @return   <code>true<code> if the under10KLimit property is true
    */
   public boolean getUnder10KLimit() 
   {
	fetch();
      return under10KLimit;
   }
   
   /**
    * Sets the value of the under10KLimit property.
    * 
    * @param aUnder10KLimit the new value of the under10KLimit property
    */
   public void setUnder10KLimit(boolean aUnder10KLimit) 
   {
	markModified();
      under10KLimit = aUnder10KLimit;
   }
   
   /**
    * Access method for the childMeetsEligibilityCriteria property.
    * 
    * @return   the current value of the childMeetsEligibilityCriteria property
    */
   public boolean getChildMeetsEligibilityCriteria() 
   {
	fetch();
      return childMeetsEligibilityCriteria;
   }
   
   /**
    * Sets the value of the childMeetsEligibilityCriteria property.
    * 
    * @param aChildMeetsEligibilityCriteria the new value of the childMeetsEligibilityCriteria property
    */
   public void setChildMeetsEligibilityCriteria(boolean aChildMeetsEligibilityCriteria) 
   {
	markModified();
      childMeetsEligibilityCriteria = aChildMeetsEligibilityCriteria;
   }
   
   /**
    * Determines if the orderContainsBestInterestLanguage property is true.
    * 
    * @return   <code>true<code> if the orderContainsBestInterestLanguage property is true
    */
   public boolean getOrderContainsBestInterestLanguage() 
   {
	fetch();
      return orderContainsBestInterestLanguage;
   }
   
   /**
    * Sets the value of the orderContainsBestInterestLanguage property.
    * 
    * @param aOrderContainsBestInterestLanguage the new value of the orderContainsBestInterestLanguage property
    */
   public void setOrderContainsBestInterestLanguage(boolean aOrderContainsBestInterestLanguage) 
   {
	markModified();
      orderContainsBestInterestLanguage = aOrderContainsBestInterestLanguage;
   }
   
   /**
    * Determines if the resonableEffortsMadeWithin60Days property is true.
    * 
    * @return   <code>true<code> if the resonableEffortsMadeWithin60Days property is true
    */
   public boolean getResonableEffortsMadeWithin60Days() 
   {
	fetch();
      return resonableEffortsMadeWithin60Days;
   }
   
   /**
    * Sets the value of the resonableEffortsMadeWithin60Days property.
    * 
    * @param aResonableEffortsMadeWithin60Days the new value of the resonableEffortsMadeWithin60Days property
    */
   public void setResonableEffortsMadeWithin60Days(boolean aResonableEffortsMadeWithin60Days) 
   {
	markModified();
      resonableEffortsMadeWithin60Days = aResonableEffortsMadeWithin60Days;
   }
   
   /**
    * Determines if the ordersIncludeResponsibilityForCareAndPlacement property is true.
    * 
    * @return   <code>true<code> if the ordersIncludeResponsibilityForCareAndPlacement property is true
    */
   public boolean getOrdersIncludeResponsibilityForCareAndPlacement() 
   {
	fetch();
      return ordersIncludeResponsibilityForCareAndPlacement;
   }
   
   /**
    * Sets the value of the ordersIncludeResponsibilityForCareAndPlacement property.
    * 
    * @param aOrdersIncludeResponsibilityForCareAndPlacement the new value of the ordersIncludeResponsibilityForCareAndPlacement property
    */
   public void setOrdersIncludeResponsibilityForCareAndPlacement(boolean aOrdersIncludeResponsibilityForCareAndPlacement) 
   {
	markModified();
      ordersIncludeResponsibilityForCareAndPlacement = aOrdersIncludeResponsibilityForCareAndPlacement;
   }
   
   /**
    * Determines if the childMeetsAFDCAndOrderRequirements property is true.
    * 
    * @return   <code>true<code> if the childMeetsAFDCAndOrderRequirements property is true
    */
   public boolean getChildMeetsAFDCAndOrderRequirements() 
   {
	fetch();
      return childMeetsAFDCAndOrderRequirements;
   }
   
   /**
    * Sets the value of the childMeetsAFDCAndOrderRequirements property.
    * 
    * @param aChildMeetsAFDCAndOrderRequirements the new value of the childMeetsAFDCAndOrderRequirements property
    */
   public void setChildMeetsAFDCAndOrderRequirements(boolean aChildMeetsAFDCAndOrderRequirements) 
   {
	markModified();
      childMeetsAFDCAndOrderRequirements = aChildMeetsAFDCAndOrderRequirements;
   }
   
   /**
   * Initialize class relationship implementation for pd.juvenilecase.family.IndividualIncomeDetermination
   */
   private void initAFDCIncomeWorksheetItems() 
   {
	   if ( afdcIncomeWorksheetItems == null ) 
	   {
		   if (this.getOID() == null) {
			   new mojo.km.persistence.Home().bind(this);
		   }
		   try {
			afdcIncomeWorksheetItems =
				   new mojo.km.persistence.ArrayList(IndividualIncomeDetermination.class, "benefitsAssessmentId", "" + getOID());
		   } catch (Throwable t) {
			afdcIncomeWorksheetItems = new ArrayList();
		   }
	   }
   }

   /**
   * returns a collection of pd.juvenilecase.family.IndividualIncomeDetermination
   */
   public Collection getAFDCIncomeWorksheetItems()
   {
	    initAFDCIncomeWorksheetItems();
		return afdcIncomeWorksheetItems;
   }

   /**
   * insert a pd.supervision.supervisionoptions.VariableElement into class relationship collection.
   */
   public void insertAFDCIncomeWorksheetItems(IndividualIncomeDetermination anObject)
   {
		initAFDCIncomeWorksheetItems();
	   if (this.getOID() == null) {
		   new Home().bind(this);
	   }
	   if (anObject.getOID() == null) {
		   new Home().bind(anObject);
	   }
//		anObject.setBenefitsAssessmentId(getOID().toString());
		afdcIncomeWorksheetItems.add(anObject);
   }
   
   /**
   * Removes a pd.supervision.supervisionoptions.VariableElement from class relationship collection.
   */
   public void removeAFDCIncomeWorksheetItems(IndividualIncomeDetermination anObject)
   {
		initAFDCIncomeWorksheetItems();
//		anObject.setBenefitsAssessmentId(null);
		afdcIncomeWorksheetItems.remove(anObject);
   }
   
   /**
   * Clears all pd.supervision.supervisionoptions.VariableElement from class relationship collection.
   */
   public void clearAFDCIncomeWorksheetItems() 
   {
		initAFDCIncomeWorksheetItems();
		
		//afdcIncomeWorksheetItems.clear(); // clear() does not work correctly, replace with remove().
		ArrayList tmp = new ArrayList( afdcIncomeWorksheetItems );
		Iterator iter = tmp.iterator();
		while ( iter.hasNext() )
		{
			Object obj = iter.next();
			afdcIncomeWorksheetItems.remove( obj );
		}
   }


   /**
   * Initialize class relationship implementation for pd.codetable.supervision.SupervisionCode
   */
   private void initSourcesForAFDCInformation()
   {
	   if (sourcesForAFDCInformation == null)
	   {
		   if (this.getOID() == null)
		   {
			   new mojo.km.persistence.Home().bind(this);
		   }
		   try
		   {
				sourcesForAFDCInformation =
				   new mojo.km.persistence.ArrayList(
					   BenefitsAssessmentSourceCode.class,
					   "parentId",
					   "" + getOID());
		   }
		   catch (Throwable t)
		   {
				sourcesForAFDCInformation = new ArrayList();
		   }
	   }
   }
   
   /**
	* Access method for the sourcesForAFDCInformation property.
	* 
	* @return   the current value of the
	*  sourcesForAFDCInformation property
	*/
   public Collection getSourcesForAFDCInformation()
   {
		initSourcesForAFDCInformation();
		
		ArrayList retVal = new ArrayList();
		Iterator i = sourcesForAFDCInformation.iterator();
		while (i.hasNext())
		{
			BenefitsAssessmentSourceCode actual =
				(BenefitsAssessmentSourceCode) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
   }
   

   /**
   * insert a pd.codetable.supervision.SupervisionCode into class relationship collection.
   */
   public void insertSourcesForAFDCInformation(Code anObject)
   {
		initSourcesForAFDCInformation();
		BenefitsAssessmentSourceCode actual =
		   new BenefitsAssessmentSourceCode();
	   if (this.getOID() == null)
	   {
		   new Home().bind(this);
	   }
	   if (anObject.getOID() == null)
	   {
		   new Home().bind(anObject);
	   }
	   actual.setChild(anObject);
	   actual.setParent(this);
		sourcesForAFDCInformation.add(actual);
   }

   /**
   * Removes a pd.codetable.supervision.SupervisionCode from class relationship collection.
   */
   public void removeSourcesForAFDCInformation(Code anObject)
   {
		initSourcesForAFDCInformation();
	   try
	   {
		   mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		   assocEvent.setChildId((String) anObject.getOID());
		   assocEvent.setParentId((String) this.getOID());
		BenefitsAssessmentSourceCode actual =
			   (BenefitsAssessmentSourceCode) new mojo
				   .km
				   .persistence
				   .Reference(assocEvent, BenefitsAssessmentSourceCode.class)
				   .getObject();
			sourcesForAFDCInformation.remove(actual);
	   }
	   catch (Throwable t)
	   {
	   }
   }

   /**
   * Clears all pd.codetable.supervision.SupervisionCode from class relationship collection.
   */
   public void clearSourcesForAFDCInformation()
   {
		initSourcesForAFDCInformation();
		sourcesForAFDCInformation.clear();
   }


   /**
   * Initialize class relationship implementation for pd.codetable.supervision.SupervisionCode
   */
   private void initReviewComments()
   {
	   if (reviewComments == null)
	   {
		   if (this.getOID() == null)
		   {
			   new mojo.km.persistence.Home().bind(this);
		   }
		   try
		   {
				reviewComments =
				   new mojo.km.persistence.ArrayList(
					   BenefitsAssessmentReview.class,
					   "benefitsAssessmentId",
					   "" + getOID());
		   }
		   catch (Throwable t)
		   {
				reviewComments = new ArrayList();
		   }
	   }
   }
   
   /**
	* Access method for the sourcesForAFDCInformation property.
	* 
	* @return   the current value of the sourcesForAFDCInformation property
	*/
   public Collection getReviewComments()
   {
		initReviewComments();
		return reviewComments;
   }
   

   /**
   * insert a pd.codetable.supervision.SupervisionCode into class relationship collection.
   */
   public void insertReviewComments(BenefitsAssessmentReview anObject)
   {
		initReviewComments();
	   	if (anObject.getOID() == null)
	   	{
		   	new Home().bind(anObject);
	   	}
		reviewComments.add(anObject);
   }

   /**
   * Removes a pd.codetable.supervision.SupervisionCode from class relationship collection.
   */
   public void removeReviewComments(BenefitsAssessmentReview anObject)
   {
		initReviewComments();
	   try
	   {
			reviewComments.remove(anObject);
	   }
	   catch (Throwable t)
	   {
	   }
   }

   /**
   * Clears all pd.codetable.supervision.SupervisionCode from class relationship collection.
   */
   public void clearReviewComments()
   {
		initReviewComments();
		reviewComments.clear();
   }

   /**
	* @return
	*/
   public int getAfdcIncomeCertifiedGroupLimit()
   {
		fetch();
	   return afdcIncomeCertifiedGroupLimit;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeCertifiedGroupParentsSize()
   {
	fetch();
	   return afdcIncomeCertifiedGroupParentsSize;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeCertifiedGroupSize()
   {
	fetch();
	   return afdcIncomeCertifiedGroupSize;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeStepparentAllowanceAmount()
   {
	fetch();
	   return afdcIncomeStepparentAllowanceAmount;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeStepparentAppliedIncome()
   {
	fetch();
	   return afdcIncomeStepparentAppliedIncome;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeStepparentCountableEarnedMonthy()
   {
	fetch();
	   return afdcIncomeStepparentCountableEarnedMonthy;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeStepparentTotalCountableMonthy()
   {
	fetch();
	   return afdcIncomeStepparentTotalCountableMonthy;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeTotalCountable()
   {
	fetch();
	   return afdcIncomeTotalCountable;
   }

   /**
	* @return
	*/
   public int getAfdcIncomeTotalMonthy()
   {
	fetch();
	   return afdcIncomeTotalMonthy;
   }

   /**
	* @return
	*/
   public String getPweRelationshipToJuvenile()
   {
	fetch();
	   return pweRelationshipToJuvenile;
   }

   /**
	* @return
	*/
   public String getPweRelationshipToJuvenileId()
   {
	fetch();
	   return pweRelationshipToJuvenileId;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeCertifiedGroupLimit(int i)
   {
   		markModified();
	   afdcIncomeCertifiedGroupLimit = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeCertifiedGroupParentsSize(int i)
   {
	markModified();
	   afdcIncomeCertifiedGroupParentsSize = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeCertifiedGroupSize(int i)
   {
	markModified();
	   afdcIncomeCertifiedGroupSize = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeStepparentAllowanceAmount(int i)
   {
	markModified();
	   afdcIncomeStepparentAllowanceAmount = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeStepparentAppliedIncome(int i)
   {
	markModified();
	   afdcIncomeStepparentAppliedIncome = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeStepparentCountableEarnedMonthy(int i)
   {
	markModified();
	   afdcIncomeStepparentCountableEarnedMonthy = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeStepparentTotalCountableMonthy(int i)
   {
	markModified();
	   afdcIncomeStepparentTotalCountableMonthy = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeTotalCountable(int i)
   {
	markModified();
	   afdcIncomeTotalCountable = i;
   }

   /**
	* @param i
	*/
   public void setAfdcIncomeTotalMonthy(int i)
   {
	markModified();
	   afdcIncomeTotalMonthy = i;
   }

   /**
	* @param string
	*/
   public void setPweRelationshipToJuvenile(String string)
   {
	markModified();
	   pweRelationshipToJuvenile = string;
   }

   /**
	* @param string
	*/
   public void setPweRelationshipToJuvenileId(String string)
   {
	markModified();
	   pweRelationshipToJuvenileId = string;
   }


   /* -------------------------------------------------------------------------
	* PRIVATE: The following methods are for internal use only. 
	* Their access is public only for persistence mapping.  
	*/
   
	/**
 	 * PRIVATE: Not for external use. 
	 * @roseuid 436FB31101A6
	 */
	public BenefitsAssessment() 
	{
    
	}
   
	/**
	 * PRIVATE: Not for external use. 
	 * @roseuid 436FB31101A6
	 */
	public BenefitsAssessment( String juvId, String casefileId ) 
	{
		new Home().bind( this );  // get the oid.
			
		setJuvenileId( juvId );
		setCasefileId( casefileId );
		setRequestDate( new Date() );
	}
   
	//Begin
	/**
	 * PRIVATE: Not for external use. 
	 * @roseuid 436FB31101A6
	 */
	public BenefitsAssessment( String juvId, String casefileId, String reqName) 
	{
		new Home().bind( this );  // get the oid.
			
		setJuvenileId( juvId );
		setCasefileId( casefileId );
		setRequestDate( new Date() );
		setRequesterName( reqName );
		
	}
	
	//End
	/**
	 * PRIVATE: Not for external use. 
	 */
	public void setJuvenileId( String aJuvenileId ) 
	{
		if ( aJuvenileId == null )
		{
			throw new IllegalArgumentException( "Juvenile cannot be set to null." );
		}
			
		if ( getJuvenileId() != null && getJuvenileId() != aJuvenileId )
		{
			throw new IllegalStateException( "Juvenile cannot be changed." );
		}
	   		
		juvenileId = aJuvenileId;
		markModified();
	}
   
	/**
	 * PRIVATE: Not for external use. 
	 */
	public void setGuardianId( String aGuardianId ) 
	{
		guardianId = aGuardianId;
		markModified();
	}
   
	/**
	 * PRIVATE: Not for external use. 
	 */
	public void setRequestDate( Date aRequestDate ) 
	{
		requestDate = aRequestDate;
		markModified();
	}
   
	
	/**
	 * PRIVATE: Not for external use. 
	 */
	public void setEntryDate( Date aEntryDate ) 
	{
		entryDate = aEntryDate;
		markModified();
	}
   
   
	/**
	 * @return
	 */
	public Code getEligibiltyType()
	{
		//return eligibiltyType;
		return null;
	}

	/**
	 * @param code
	 */
	public void setEligibiltyType(Code code)
	{
		//eligibiltyType = code;
	}

	/**
	 * @return
	 */
	public String getTitleIVeOfficerId()
	{
		fetch();
		return titleIVeOfficerId;
	}

	/**
	 * @return
	 */
	public String getTitleIVeOfficerName()
	{
		fetch();
		return titleIVeOfficerName;
	}

	/**
	 * @param string
	 */
	public void setTitleIVeOfficerId(String string)
	{
		titleIVeOfficerId = string;
		markModified();
	}

	/**
	 * @param string
	 */
	public void setTitleIVeOfficerName(String string)
	{
		titleIVeOfficerName = string;
		markModified();
	}

	/**
	 * @return Returns the probationOfficerName.
	 */
	public String getProbationOfficerName() {
		fetch();
		return probationOfficerName;
	}
	/**
	 * @param probationOfficerName The probationOfficerName to set.
	 */
	public void setProbationOfficerName(String probationOfficerName) {
		this.probationOfficerName = probationOfficerName;
		markModified();
	}
	/**
	 * @return
	 */
	public int getAfdcIncomeStepparentNoncertifiedCount()
	{
		fetch();
		return afdcIncomeStepparentNoncertifiedCount;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeStepparentNoncertifiedCount(int i)
	{
		afdcIncomeStepparentNoncertifiedCount = i;
		markModified();
	}

	/**
	 * @return
	 */
	public JuvenileCasefile getCasefile()
	{
		if ( casefile == null )
		{
			casefile = JuvenileCasefile.find( casefileId );
		}
		return casefile;
	}

	/**
	 * @return
	 */
	public String getCasefileId()
	{
		fetch();
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
		casefile = null;
		markModified();
	}

	/**
	 * @return Returns the probationOfficerId.
	 */
	public String getProbationOfficerId() {
		fetch();
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId The probationOfficerId to set.
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
		markModified();
	}
	
	/**
	 * @return Returns the requesterName.
	 */
	public String getRequesterName() {
		return requesterName;
	}
	/**
	 * @param requesterName The requesterName to set.
	 */
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
		markModified();
	}
	}
