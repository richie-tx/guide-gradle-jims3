package messaging.family;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class SaveBenefitsAssessmentEvent extends RequestEvent 
{
	public String assessmentId;
	public String guardianId;
   
	// Eligibility determination
	private boolean isEligibleForMedicaid;
	private boolean isReceivingMedicaid;
	private boolean isEligibleForTitleIVe;
	private boolean isReceivingTitleIVe;
	
	private String casefileId;
   
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
	 * Question 2a1-207.
	 */
	private String pweRelationshipToJuvenile;
   
	/**
	 * Question 2a2-208.
	 */
	private boolean pweWorkedSteadyLessThan100Hours;
   
	/**
	 * Question 2a3-209.
	 */
	private boolean pweWorkedIrregularLessThan100HoursAvg;
   
	/**
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
	 */
	private Collection afdcIncomeWorksheetItems = new ArrayList();
   
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
	 * Question 4-403.
	 */
	private int afdcIncomeStepparentsMonthlyGross;
   
	/**
	 * Question 4-404.
	 */
	private int afdcIncomeStepparentWorkRelatedExpenses;
   
	/**
	 * Countable earned monthly ioncome.
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeStepparentCountableEarnedMonthy;
   
	/**
	 * Question 4-405.
	 */
	private int afdcIncomeStepparentOtherMonthlyIncome;
   
	/**
	 * Total countable monthly ioncome.
	 * Monetary (saved in pennies)
	 */
	private int afdcIncomeStepparentTotalCountableMonthy;
   
	/**
	 * Question 4-406.
	 */
	private int afdcIncomeStepparentMonthyPaymentsToDependent;
   
	/**
	 * Question 4-407.
	 */
	private int afdcIncomeStepparentMonthyAlimonyChildSupport;
   
	/**
	 * Non-certifed dependents count. 
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
	  * 
	  */    
	private Collection sourcesForAFDCInformation = new ArrayList();
   
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
   
   
	private String eligibiltyType;
	
	private String titleIVeOfficerId;

	private String titleIVeOfficerName;
	
   
	 /**
	  * @return
	  */
	 public String getAssessmentId()
	 {
		 return assessmentId;
	 }
	
	 /**
	  * @param string
	  */
	 public void setAssessmentId(String string)
	 {
		 assessmentId = string;
	 }

	 /**
	  * @return
	  */
	 public boolean getAfdcIncomeLimitsMet()
	 {
		 return afdcIncomeLimitsMet;
	 }
	
	 /**
	  * @return
	  */
	 public int getAfdcIncomeStepparentMonthyAlimonyChildSupport()
	 {
		 return afdcIncomeStepparentMonthyAlimonyChildSupport;
	 }
	
	 /**
	  * @return
	  */
	 public int getAfdcIncomeStepparentMonthyPaymentsToDependent()
	 {
		 return afdcIncomeStepparentMonthyPaymentsToDependent;
	 }
	
	 /**
	  * @return
	  */
	 public int getAfdcIncomeStepparentOtherMonthlyIncome()
	 {
		 return afdcIncomeStepparentOtherMonthlyIncome;
	 }
	
	 /**
	  * @return
	  */
	 public int getAfdcIncomeStepparentsMonthlyGross()
	 {
		 return afdcIncomeStepparentsMonthlyGross;
	 }
	
	 /**
	  * @return
	  */
	 public int getAfdcIncomeStepparentWorkRelatedExpenses()
	 {
		 return afdcIncomeStepparentWorkRelatedExpenses;
	 }
	
	 /**
	  * @return
	  */
	 public Collection getAfdcIncomeWorksheetItems()
	 {
		 return afdcIncomeWorksheetItems;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getChildMeetsAFDCAndOrderRequirements()
	 {
		 return childMeetsAFDCAndOrderRequirements;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getChildMeetsEligibilityCriteria()
	 {
		 return childMeetsEligibilityCriteria;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getDeathOrAbsence()
	 {
		 return deathOrAbsence;
	 }
	
	 /**
	  * @return
	  */
	 public String getEligibiltyType()
	 {
		 return eligibiltyType;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getIncapacityOrDisabilityOfParent()
	 {
		 return incapacityOrDisabilityOfParent;
	 }
	
	 /**
	  * @return
	  */
	 public boolean isEligibleForMedicaid()
	 {
		 return isEligibleForMedicaid;
	 }
	
	 /**
	  * @return
	  */
	 public boolean isEligibleForTitleIVe()
	 {
		 return isEligibleForTitleIVe;
	 }
	
	 /**
	  * @return
	  */
	 public boolean isLegalResident()
	 {
		 return isLegalResident;
	 }
	
	 /**
	  * @return
	  */
	 public boolean isReceivingMedicaid()
	 {
		 return isReceivingMedicaid;
	 }
	
	 /**
	  * @return
	  */
	 public boolean isReceivingTitleIVe()
	 {
		 return isReceivingTitleIVe;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getOneParentIsStepparent()
	 {
		 return oneParentIsStepparent;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getOrderContainsBestInterestLanguage()
	 {
		 return orderContainsBestInterestLanguage;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getOrdersIncludeResponsibilityForCareAndPlacement()
	 {
		 return ordersIncludeResponsibilityForCareAndPlacement;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getPrimaryWageEarnerUnderemployment()
	 {
		 return primaryWageEarnerUnderemployment;
	 }
	
	 /**
	  * @return
	  */
	 public int getPweGrossMonthlyIncomeForOver100Hours()
	 {
		 return pweGrossMonthlyIncomeForOver100Hours;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getPweIncomeLessThanUnderemployedLimit()
	 {
		 return pweIncomeLessThanUnderemployedLimit;
	 }
	
	 /**
	  * @return
	  */
	 public String getPweRelationshipToJuvenile()
	 {
		 return pweRelationshipToJuvenile;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getPweWorkedIrregularLessThan100HoursAvg()
	 {
		 return pweWorkedIrregularLessThan100HoursAvg;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getPweWorkedLessThen100Hours()
	 {
		 return pweWorkedLessThen100Hours;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getPweWorkedSteadyLessThan100Hours()
	 {
		 return pweWorkedSteadyLessThan100Hours;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getResonableEffortsMadeWithin60Days()
	 {
		 return resonableEffortsMadeWithin60Days;
	 }
	
	 /**
	  * @return
	  */
	 public Collection getSourcesForAFDCInformation()
	 {
		 return sourcesForAFDCInformation;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getUnder10KLimit()
	 {
		 return under10KLimit;
	 }
	
	 /**
	  * @return
	  */
	 public boolean getWasChildLivingWithParent()
	 {
		 return wasChildLivingWithParent;
	 }
	
	 /**
	  * @param b
	  */
	 public void setAfdcIncomeLimitsMet(boolean b)
	 {
		 afdcIncomeLimitsMet = b;
	 }
	
	 /**
	  * @param i
	  */
	 public void setAfdcIncomeStepparentMonthyAlimonyChildSupport(int i)
	 {
		 afdcIncomeStepparentMonthyAlimonyChildSupport = i;
	 }
	
	 /**
	  * @param i
	  */
	 public void setAfdcIncomeStepparentMonthyPaymentsToDependent(int i)
	 {
		 afdcIncomeStepparentMonthyPaymentsToDependent = i;
	 }
	
	 /**
	  * @param i
	  */
	 public void setAfdcIncomeStepparentOtherMonthlyIncome(int i)
	 {
		 afdcIncomeStepparentOtherMonthlyIncome = i;
	 }
	
	 /**
	  * @param i
	  */
	 public void setAfdcIncomeStepparentsMonthlyGross(int i)
	 {
		 afdcIncomeStepparentsMonthlyGross = i;
	 }
	
	 /**
	  * @param i
	  */
	 public void setAfdcIncomeStepparentWorkRelatedExpenses(int i)
	 {
		 afdcIncomeStepparentWorkRelatedExpenses = i;
	 }
	
	 /**
	  * @param collection
	  */
	 public void setAfdcIncomeWorksheetItems(Collection collection)
	 {
		 afdcIncomeWorksheetItems = collection;
	 }
	
	 /**
	  * @param b
	  */
	 public void setChildMeetsAFDCAndOrderRequirements(boolean b)
	 {
		 childMeetsAFDCAndOrderRequirements = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setChildMeetsEligibilityCriteria(boolean b)
	 {
		 childMeetsEligibilityCriteria = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setDeathOrAbsence(boolean b)
	 {
		 deathOrAbsence = b;
	 }
	
	 /**
	  * @param string
	  */
	 public void setEligibiltyType(String string)
	 {
		 eligibiltyType = string;
	 }
	
	 /**
	  * @param b
	  */
	 public void setIncapacityOrDisabilityOfParent(boolean b)
	 {
		 incapacityOrDisabilityOfParent = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setEligibleForMedicaid(boolean b)
	 {
		 isEligibleForMedicaid = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setEligibleForTitleIVe(boolean b)
	 {
		 isEligibleForTitleIVe = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setLegalResident(boolean b)
	 {
		 isLegalResident = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setReceivingMedicaid(boolean b)
	 {
		 isReceivingMedicaid = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setReceivingTitleIVe(boolean b)
	 {
		 isReceivingTitleIVe = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setOneParentIsStepparent(boolean b)
	 {
		 oneParentIsStepparent = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setOrderContainsBestInterestLanguage(boolean b)
	 {
		 orderContainsBestInterestLanguage = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setOrdersIncludeResponsibilityForCareAndPlacement(boolean b)
	 {
		 ordersIncludeResponsibilityForCareAndPlacement = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setPrimaryWageEarnerUnderemployment(boolean b)
	 {
		 primaryWageEarnerUnderemployment = b;
	 }
	
	 /**
	  * @param i
	  */
	 public void setPweGrossMonthlyIncomeForOver100Hours(int i)
	 {
		 pweGrossMonthlyIncomeForOver100Hours = i;
	 }
	
	 /**
	  * @param b
	  */
	 public void setPweIncomeLessThanUnderemployedLimit(boolean b)
	 {
		 pweIncomeLessThanUnderemployedLimit = b;
	 }
	
	 /**
	  * @param string
	  */
	 public void setPweRelationshipToJuvenile(String string)
	 {
		 pweRelationshipToJuvenile = string;
	 }
	
	 /**
	  * @param b
	  */
	 public void setPweWorkedIrregularLessThan100HoursAvg(boolean b)
	 {
		 pweWorkedIrregularLessThan100HoursAvg = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setPweWorkedLessThen100Hours(boolean b)
	 {
		 pweWorkedLessThen100Hours = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setPweWorkedSteadyLessThan100Hours(boolean b)
	 {
		 pweWorkedSteadyLessThan100Hours = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setResonableEffortsMadeWithin60Days(boolean b)
	 {
		 resonableEffortsMadeWithin60Days = b;
	 }
	
	 /**
	  * @param collection
	  */
	 public void setSourcesForAFDCInformation(Collection collection)
	 {
		 sourcesForAFDCInformation = collection;
	 }
	
	 /**
	  * @param b
	  */
	 public void setUnder10KLimit(boolean b)
	 {
		 under10KLimit = b;
	 }
	
	 /**
	  * @param b
	  */
	 public void setWasChildLivingWithParent(boolean b)
	 {
		 wasChildLivingWithParent = b;
	 }

	/**
	 * @return
	 */
	public String getTitleIVeOfficerId()
	{
		return titleIVeOfficerId;
	}
	
	/**
	 * @return
	 */
	public String getTitleIVeOfficerName()
	{
		return titleIVeOfficerName;
	}
	
	/**
	 * @param string
	 */
	public void setTitleIVeOfficerId(String string)
	{
		titleIVeOfficerId = string;
	}
	
	/**
	 * @param string
	 */
	public void setTitleIVeOfficerName(String string)
	{
		titleIVeOfficerName = string;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeCertifiedGroupLimit()
	{
		return afdcIncomeCertifiedGroupLimit;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeCertifiedGroupParentsSize()
	{
		return afdcIncomeCertifiedGroupParentsSize;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeCertifiedGroupSize()
	{
		return afdcIncomeCertifiedGroupSize;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeStepparentAllowanceAmount()
	{
		return afdcIncomeStepparentAllowanceAmount;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeStepparentAppliedIncome()
	{
		return afdcIncomeStepparentAppliedIncome;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeStepparentCountableEarnedMonthy()
	{
		return afdcIncomeStepparentCountableEarnedMonthy;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeStepparentTotalCountableMonthy()
	{
		return afdcIncomeStepparentTotalCountableMonthy;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeTotalCountable()
	{
		return afdcIncomeTotalCountable;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeTotalMonthy()
	{
		return afdcIncomeTotalMonthy;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeCertifiedGroupLimit(int i)
	{
		afdcIncomeCertifiedGroupLimit = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeCertifiedGroupParentsSize(int i)
	{
		afdcIncomeCertifiedGroupParentsSize = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeCertifiedGroupSize(int i)
	{
		afdcIncomeCertifiedGroupSize = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeStepparentAllowanceAmount(int i)
	{
		afdcIncomeStepparentAllowanceAmount = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeStepparentAppliedIncome(int i)
	{
		afdcIncomeStepparentAppliedIncome = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeStepparentCountableEarnedMonthy(int i)
	{
		afdcIncomeStepparentCountableEarnedMonthy = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeStepparentTotalCountableMonthy(int i)
	{
		afdcIncomeStepparentTotalCountableMonthy = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeTotalCountable(int i)
	{
		afdcIncomeTotalCountable = i;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeTotalMonthy(int i)
	{
		afdcIncomeTotalMonthy = i;
	}

	/**
	 * @return
	 */
	public String getPweRelationshipToJuvenileId()
	{
		return pweRelationshipToJuvenileId;
	}

	/**
	 * @param string
	 */
	public void setPweRelationshipToJuvenileId(String string)
	{
		pweRelationshipToJuvenileId = string;
	}

	/**
	 * @return
	 */
	public int getAfdcIncomeStepparentNoncertifiedCount()
	{
		return afdcIncomeStepparentNoncertifiedCount;
	}

	/**
	 * @param i
	 */
	public void setAfdcIncomeStepparentNoncertifiedCount(int i)
	{
		afdcIncomeStepparentNoncertifiedCount = i;
	}

	/**
	 * @return
	 */
	public String getGuardianId()
	{
		return guardianId;
	}

	/**
	 * @param string
	 */
	public void setGuardianId(String string)
	{
		guardianId = string;
	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}
