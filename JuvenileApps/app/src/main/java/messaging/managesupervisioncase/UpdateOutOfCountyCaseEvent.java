//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\UpdateOutOfCountyCaseEvent.java

package messaging.managesupervisioncase;

import java.util.Date;

import messaging.contact.domintf.IAddress;
import messaging.domintf.managesupervisioncase.ISupervisionCase;
import mojo.km.messaging.RequestEvent;

public class UpdateOutOfCountyCaseEvent extends RequestEvent implements ISupervisionCase 
{
   private String caseNum;
   private String cdi;
   private String spn;
   private String stateOffenseCodeId;
   private String outOfCountyCaseTypeId;
   private Date offenseDate;
   private Date arrestDate;
   private Date supervisionBeginDate;
   private Date supervisionEndDate;
   private String originalCaseNum;
   private String originalCourtNum;
   private String personId;
   private Date transferInDate;
   private String originalCountyId;
   private String originalStateId;
   private String originalAgencyName;
   private String contactFirstName;
   private String contactLastName;
   private String contactMiddleName;
   private String contactJobTitle;
   private String contactPhoneNum;
   private String contactPhoneExt;
   private String violentOffenseInd;
   private String familyViolenceInd;
   private IAddress agencyAddress;
   private String cjisNum;
   private String dispositionTypeId;
   private Date dispositionDate;
   private Date sentenceDate;
   private int supervisionYears;
   private int supervisionMonths;
   private int supervisionDays;
   private int confinementYears;
   private int confinementMonths;
   private int confinementDays;
   private Date pretrialInterventionBeginDate;
   private Date pretrialInterventionEndDate;
   private String stateId;
   private String countyId;
   private boolean createInd;
   private String instrumentTypeId;
   private Date filingDate;
   private String courtNum;
   private String defendantId;
   private String defendantName;
   private String previousDispositionTypeId;
   private String reasonForUpdateId;
   private String namePtr;
   private String nameSeqNum;
   
   public String getNamePtr() {
	   return namePtr;
   }

   public void setNamePtr(String namePtr) {
	this.namePtr = namePtr;
   }

   private boolean isCscCreate;
   private boolean isCscUpdate;
   
   
   /**
    * @roseuid 4443D89300F6
    */
   public UpdateOutOfCountyCaseEvent() 
   {
    
   }
   
   /**
    * Access method for the caseNum property.
    * 
    * @return   the current value of the caseNum property
    */
   public String getCaseNum() 
   {
      return caseNum;
   }
   
   /**
    * Sets the value of the caseNum property.
    * 
    * @param aCaseNum the new value of the caseNum property
    */
   public void setCaseNum(String aCaseNum) 
   {
      caseNum = aCaseNum;
   }
   
   /**
    * Access method for the cdi property.
    * 
    * @return   the current value of the cdi property
    */
   public String getCdi() 
   {
      return cdi;
   }
   
   /**
    * Sets the value of the cdi property.
    * 
    * @param aCdi the new value of the cdi property
    */
   public void setCdi(String aCdi) 
   {
      cdi = aCdi;
   }
   
   /**
    * Access method for the spn property.
    * 
    * @return   the current value of the spn property
    */
   public String getSpn() 
   {
      return spn;
   }
   
   /**
    * Sets the value of the spn property.
    * 
    * @param aSpn the new value of the spn property
    */
   public void setSpn(String aSpn) 
   {
      spn = aSpn;
   }
   
   /**
    * Access method for the stateOffenseCodeId property.
    * 
    * @return   the current value of the stateOffenseCodeId property
    */
   public String getStateOffenseCodeId() 
   {
      return stateOffenseCodeId;
   }
   
   /**
    * Sets the value of the stateOffenseCodeId property.
    * 
    * @param aStateOffenseCode the new value of the stateOffenseCodeId property
    */
   public void setStateOffenseCodeId(String aStateOffenseCodeId) 
   {
      stateOffenseCodeId = aStateOffenseCodeId;
   }
   
   /**
    * Access method for the outOfCountyCaseType property.
    * 
    * @return   the current value of the outOfCountyCaseType property
    */
   public String getOutOfCountyCaseTypeId() 
   {
      return outOfCountyCaseTypeId;
   }
   
   /**
    * Sets the value of the outOfCountyCaseTypeId property.
    * 
    * @param aOutOfCountyCaseTypeId the new value of the outOfCountyCaseTypeId property
    */
   public void setOutOfCountyCaseTypeId(String aOutOfCountyCaseTypeId) 
   {
      outOfCountyCaseTypeId = aOutOfCountyCaseTypeId;
   }
   
   /**
    * Access method for the offenseDate property.
    * 
    * @return   the current value of the offenseDate property
    */
   public Date getOffenseDate() 
   {
      return offenseDate;
   }
   
   /**
    * Sets the value of the offenseDate property.
    * 
    * @param aOffenseDate the new value of the offenseDate property
    */
   public void setOffenseDate(Date aOffenseDate) 
   {
      offenseDate = aOffenseDate;
   }
   
   /**
    * Access method for the arrestDate property.
    * 
    * @return   the current value of the arrestDate property
    */
   public Date getArrestDate() 
   {
      return arrestDate;
   }
   
   /**
    * Sets the value of the arrestDate property.
    * 
    * @param aArrestDate the new value of the arrestDate property
    */
   public void setArrestDate(Date aArrestDate) 
   {
      arrestDate = aArrestDate;
   }
   
   /**
    * Access method for the supervisionBeginDate property.
    * 
    * @return   the current value of the supervisionBeginDate property
    */
   public Date getSupervisionBeginDate() 
   {
      return supervisionBeginDate;
   }
   
   /**
    * Sets the value of the supervisionBeginDate property.
    * 
    * @param aSupervisionBeginDate the new value of the supervisionBeginDate property
    */
   public void setSupervisionBeginDate(Date aSupervisionBeginDate) 
   {
      supervisionBeginDate = aSupervisionBeginDate;
   }
   
   /**
    * Access method for the supervisionEndDate property.
    * 
    * @return   the current value of the supervisionEndDate property
    */
   public Date getSupervisionEndDate() 
   {
      return supervisionEndDate;
   }
   
   /**
    * Sets the value of the supervisionEndDate property.
    * 
    * @param aSupervisionEndDate the new value of the supervisionEndDate property
    */
   public void setSupervisionEndDate(Date aSupervisionEndDate) 
   {
      supervisionEndDate = aSupervisionEndDate;
   }
   
   /**
    * Access method for the originalCaseNum property.
    * 
    * @return   the current value of the originalCaseNum property
    */
   public String getOriginalCaseNum() 
   {
      return originalCaseNum;
   }
   
   /**
    * Sets the value of the originalCaseNum property.
    * 
    * @param aOriginalCaseNum the new value of the originalCaseNum property
    */
   public void setOriginalCaseNum(String aOriginalCaseNum) 
   {
      originalCaseNum = aOriginalCaseNum;
   }
   
   /**
    * Access method for the originalCourtNum property.
    * 
    * @return   the current value of the originalCourtNum property
    */
   public String getOriginalCourtNum() 
   {
      return originalCourtNum;
   }
   
   /**
    * Sets the value of the originalCourtNum property.
    * 
    * @param aOriginalCourtNum the new value of the originalCourtNum property
    */
   public void setOriginalCourtNum(String aOriginalCourtNum) 
   {
      originalCourtNum = aOriginalCourtNum;
   }
   
   /**
    * Access method for the personId property.
    * 
    * @return   the current value of the personId property
    */
   public String getPersonId() 
   {
      return personId;
   }
   
   /**
    * Sets the value of the personId property.
    * 
    * @param aPersonId the new value of the personId property
    */
   public void setPersonId(String aPersonId) 
   {
      personId = aPersonId;
   }
   
   /**
    * Access method for the transferInDate property.
    * 
    * @return   the current value of the transferInDate property
    */
   public Date getTransferInDate() 
   {
      return transferInDate;
   }
   
   /**
    * Sets the value of the transferInDate property.
    * 
    * @param aTransferInDate the new value of the transferInDate property
    */
   public void setTransferInDate(Date aTransferInDate) 
   {
      transferInDate = aTransferInDate;
   }
   
   /**
    * Access method for the originalCountyId property.
    * 
    * @return   the current value of the originalCountyId property
    */
   public String getOriginalCountyId() 
   {
      return originalCountyId;
   }
   
   /**
    * Sets the value of the originalCountyId property.
    * 
    * @param aOriginalCountyId the new value of the originalCountyId property
    */
   public void setOriginalCountyId(String aOriginalCountyId) 
   {
      originalCountyId = aOriginalCountyId;
   }
   
   /**
    * Access method for the originalStateId property.
    * 
    * @return   the current value of the originalStateId property
    */
   public String getOriginalStateId() 
   {
      return originalStateId;
   }
   
   /**
    * Sets the value of the originalStateId property.
    * 
    * @param aOriginalState the new value of the originalStateId property
    */
   public void setOriginalStateId(String aOriginalStateId) 
   {
      originalStateId = aOriginalStateId;
   }
   
   /**
    * Access method for the originalAgencyName property.
    * 
    * @return   the current value of the originalAgencyName property
    */
   public String getOriginalAgencyName() 
   {
      return originalAgencyName;
   }
   
   /**
    * Sets the value of the originalAgencyName property.
    * 
    * @param aOriginalAgencyName the new value of the originalAgencyName property
    */
   public void setOriginalAgencyName(String aOriginalAgencyName) 
   {
      originalAgencyName = aOriginalAgencyName;
   }
   
   /**
    * Access method for the contactFirstName property.
    * 
    * @return   the current value of the contactFirstName property
    */
   public String getContactFirstName() 
   {
      return contactFirstName;
   }
   
   /**
    * Sets the value of the contactFirstName property.
    * 
    * @param aContactFirstName the new value of the contactFirstName property
    */
   public void setContactFirstName(String aContactFirstName) 
   {
      contactFirstName = aContactFirstName;
   }
   
   /**
    * Access method for the contactLastName property.
    * 
    * @return   the current value of the contactLastName property
    */
   public String getContactLastName() 
   {
      return contactLastName;
   }
   
   /**
    * Sets the value of the contactLastName property.
    * 
    * @param aContactLastName the new value of the contactLastName property
    */
   public void setContactLastName(String aContactLastName) 
   {
      contactLastName = aContactLastName;
   }
   
   /**
    * Access method for the contactMiddleName property.
    * 
    * @return   the current value of the contactMiddleName property
    */
   public String getContactMiddleName() 
   {
      return contactMiddleName;
   }
   
   /**
    * Sets the value of the contactMiddleName property.
    * 
    * @param aContactMiddleName the new value of the contactMiddleName property
    */
   public void setContactMiddleName(String aContactMiddleName) 
   {
      contactMiddleName = aContactMiddleName;
   }
   
   /**
    * Access method for the contactJobTitle property.
    * 
    * @return   the current value of the contactJobTitle property
    */
   public String getContactJobTitle() 
   {
      return contactJobTitle;
   }
   
   /**
    * Sets the value of the contactJobTitle property.
    * 
    * @param aContactJobTitle the new value of the contactJobTitle property
    */
   public void setContactJobTitle(String aContactJobTitle) 
   {
      contactJobTitle = aContactJobTitle;
   }
   
   /**
    * Access method for the contactPhoneNum property.
    * 
    * @return   the current value of the contactPhoneNum property
    */
   public String getContactPhoneNum() 
   {
      return contactPhoneNum;
   }
   
   /**
    * Sets the value of the contactPhoneNum property.
    * 
    * @param aContactPhoneNum the new value of the contactPhoneNum property
    */
   public void setContactPhoneNum(String aContactPhoneNum) 
   {
      contactPhoneNum = aContactPhoneNum;
   }
   
   /**
    * Access method for the contactPhoneExt property.
    * 
    * @return   the current value of the contactPhoneExt property
    */
   public String getContactPhoneExt() 
   {
      return contactPhoneExt;
   }
   
   /**
    * Sets the value of the contactPhoneExt property.
    * 
    * @param aContactPhoneExt the new value of the contactPhoneExt property
    */
   public void setContactPhoneExt(String aContactPhoneExt) 
   {
      contactPhoneExt = aContactPhoneExt;
   }
   
   /**
    * Access method for the violentOffenseInd property.
    * 
    * @return   the current value of the violentOffenseInd property
    */
   public String getViolentOffenseInd() 
   {
      return violentOffenseInd;
   }
   
   /**
    * Sets the value of the violentOffenseInd property.
    * 
    * @param aViolentOffenseInd the new value of the violentOffenseInd property
    */
   public void setViolentOffenseInd(String aViolentOffenseInd) 
   {
      violentOffenseInd = aViolentOffenseInd;
   }
   
   /**
    * Access method for the familyViolenceInd property.
    * 
    * @return   the current value of the familyViolenceInd property
    */
   public String getFamilyViolenceInd() 
   {
      return familyViolenceInd;
   }
   
   /**
    * Sets the value of the familyViolenceInd property.
    * 
    * @param aFamilyViolenceInd the new value of the familyViolenceInd property
    */
   public void setFamilyViolenceInd(String aFamilyViolenceInd) 
   {
      familyViolenceInd = aFamilyViolenceInd;
   }
   
   /**
    * Access method for the agencyAddress property.
    * 
    * @return   the current value of the agencyAddress property
    */
   public IAddress getAgencyAddress() 
   {
      return agencyAddress;
   }
   
   /**
    * Sets the value of the agencyAddress property.
    * 
    * @param aAgencyAddress the new value of the agencyAddress property
    */
   public void setAgencyAddress(IAddress aAgencyAddress) 
   {
      agencyAddress = aAgencyAddress;
   }
   
   /**
    * Access method for the cjisNum property.
    * 
    * @return   the current value of the cjisNum property
    */
   public String getCjisNum() 
   {
      return cjisNum;
   }
   
   /**
    * Sets the value of the cjisNum property.
    * 
    * @param aCjisNum the new value of the cjisNum property
    */
   public void setCjisNum(String aCjisNum) 
   {
      cjisNum = aCjisNum;
   }
   
   /**
    * Access method for the dispositionTypeId property.
    * 
    * @return   the current value of the dispositionTypeId property
    */
   public String getDispositionTypeId() 
   {
      return dispositionTypeId;
   }
   
   /**
    * Sets the value of the dispositionTypeId property.
    * 
    * @param aDispositionTypeId the new value of the dispositionTypeId property
    */
   public void setDispositionTypeId(String aDispositionTypeId) 
   {
      dispositionTypeId = aDispositionTypeId;
   }
   
   /**
    * Access method for the dispositionDate property.
    * 
    * @return   the current value of the dispositionDate property
    */
   public Date getDispositionDate() 
   {
      return dispositionDate;
   }
   
   /**
    * Sets the value of the dispositionDate property.
    * 
    * @param aDispositionDate the new value of the dispositionDate property
    */
   public void setDispositionDate(Date aDispositionDate) 
   {
		dispositionDate = aDispositionDate;
   }
   
   /**
	* Access method for the previousDispositionTypeId property.
	* 
	* @return   the current value of the previousDispositionTypeId property
	*/
   public String getPreviousDispositionTypeId() 
   {
		return previousDispositionTypeId;
   }
   
   /**
	* Sets the value of the previousDispositionTypeId property.
	* 
	* @param aDispositionTypeId the new value of the previousDispositionTypeId property
	*/
   public void setPreviousDispositionTypeId(String aDispositionTypeId) 
   {
		previousDispositionTypeId = aDispositionTypeId;
   }
   
   /**
    * Access method for the sentenceDate property.
    * 
    * @return   the current value of the sentenceDate property
    */
   public Date getSentenceDate() 
   {
      return sentenceDate;
   }
   
   /**
    * Sets the value of the sentenceDate property.
    * 
    * @param aSentenceDate the new value of the sentenceDate property
    */
   public void setSentenceDate(Date aSentenceDate) 
   {
      sentenceDate = aSentenceDate;
   }
   
   /**
    * Access method for the supervisionYears property.
    * 
    * @return   the current value of the supervisionYears property
    */
   public int getSupervisionYears() 
   {
      return supervisionYears;
   }
   
   /**
    * Sets the value of the supervisionYears property.
    * 
    * @param aSupervisionYears the new value of the supervisionYears property
    */
   public void setSupervisionYears(int aSupervisionYears) 
   {
      supervisionYears = aSupervisionYears;
   }
   
   /**
    * Access method for the supervisionMonths property.
    * 
    * @return   the current value of the supervisionMonths property
    */
   public int getSupervisionMonths() 
   {
      return supervisionMonths;
   }
   
   /**
    * Sets the value of the supervisionMonths property.
    * 
    * @param aSupervisionMonths the new value of the supervisionMonths property
    */
   public void setSupervisionMonths(int aSupervisionMonths) 
   {
      supervisionMonths = aSupervisionMonths;
   }
   
   /**
    * Access method for the supervisionDays property.
    * 
    * @return   the current value of the supervisionDays property
    */
   public int getSupervisionDays() 
   {
      return supervisionDays;
   }
   
   /**
    * Sets the value of the supervisionDays property.
    * 
    * @param aSupervisionDays the new value of the supervisionDays property
    */
   public void setSupervisionDays(int aSupervisionDays) 
   {
      supervisionDays = aSupervisionDays;
   }
   
   /**
    * Access method for the confinementYears property.
    * 
    * @return   the current value of the confinementYears property
    */
   public int getConfinementYears() 
   {
      return confinementYears;
   }
   
   /**
    * Sets the value of the confinementYears property.
    * 
    * @param aConfinementYears the new value of the confinementYears property
    */
   public void setConfinementYears(int aConfinementYears) 
   {
      confinementYears = aConfinementYears;
   }
   
   /**
    * Access method for the confinementMonths property.
    * 
    * @return   the current value of the confinementMonths property
    */
   public int getConfinementMonths() 
   {
      return confinementMonths;
   }
   
   /**
    * Sets the value of the confinementMonths property.
    * 
    * @param aConfinementMonths the new value of the confinementMonths property
    */
   public void setConfinementMonths(int aConfinementMonths) 
   {
      confinementMonths = aConfinementMonths;
   }
   
   /**
    * Access method for the confinementDays property.
    * 
    * @return   the current value of the confinementDays property
    */
   public int getConfinementDays() 
   {
      return confinementDays;
   }
   
   /**
    * Sets the value of the confinementDays property.
    * 
    * @param aConfinementDays the new value of the confinementDays property
    */
   public void setConfinementDays(int aConfinementDays) 
   {
      confinementDays = aConfinementDays;
   }
   
   /**
	* Access method for the pretrialInterventionBeginDate property.
	* 
	* @return   the current value of the pretrialInterventionBeginDate property
	*/
   public Date getPretrialInterventionBeginDate() 
   {
	  return pretrialInterventionBeginDate;
   }
   
   /**
	* Sets the value of the pretrialInterventionBeginDate property.
	* 
	* @param aSupervisionBeginDate the new value of the pretrialInterventionBeginDate property
	*/
   public void setPretrialInterventionBeginDate(Date aPretrialInterventionBeginDate) 
   {
	pretrialInterventionBeginDate = aPretrialInterventionBeginDate;
   }
   
   /**
	* Access method for the supervisionEndDate property.
	* 
	* @return   the current value of the supervisionEndDate property
	*/
   public Date getPretrialInterventionEndDate() 
   {
	  return pretrialInterventionEndDate;
   }
   
   /**
	* Sets the value of the pretrialInterventionEndDate property.
	* 
	* @param aSupervisionEndDate the new value of the pretrialInterventionEndDate property
	*/
   public void setPretrialInterventionEndDate(Date aPretrialInterventionEndDate) 
   {
	pretrialInterventionEndDate = aPretrialInterventionEndDate;
   }
   
   /**
	* Access method for the stateId property.
	* 
	* @return   the current value of the stateId property
	*/
   public String getStateId()
   {
	   return stateId;
   }

   /**
	* Sets the value of the stateId property.
	* 
	* @param aStateId the new value of the stateId property
	*/
   public void setStateId(String aStateId)
   {
	   stateId = aStateId;
   }

   /**
	* Access method for the countyId property.
	* 
	* @return   the current value of the countyId property
	*/
   public String getCountyId()
   {
	   return countyId;
   }

   /**
	* Sets the value of the countyId property.
	* 
	* @param aCountyId the new value of the countyId property
	*/
   public void setCountyId(String aCountyId)
   {
	   countyId = aCountyId;
   }

   /**
	* Indicates whether the OutOfCountyCase can be reactivated or not.
	* 
	* @return
	*/
   public boolean canBeReactivated()
   {
	   return false;
   }
   
   /**
	* Sets the value of the reactivationInd property.
	* 
	* @param aValue the new value of the reactivationInd property
	*/
   public void setReactivationInd(boolean aValue)
   {
   }

   /**
	* Indicates whether the OutOfCountyCase is being created or not.
	* 
	* @return the value of the createInd (true / false)
	*/
   public boolean isCreate()
   {
		return createInd;
   }
   
   /**
	* Sets the value of the createInd property.
	* 
	* @param aValue the new value of the createInd property
	*/
   public void setCreateInd(boolean aValue)
   {
   		createInd = aValue;
   }

   /**
	* Sets the value of the filingDate property.
	* 
	* @param aFilingDate the new value of the filingDate property
	*/
   public void setFilingDate(Date aFilingDate)
   {
		filingDate = aFilingDate;
   }
   
   /**
	* Access method for the filingDate property.
	* 
	* @return   the current value of the filingDate property
	*/
   public Date getFilingDate()
   {
		return filingDate;
   }
   
   /**
	* Access method for the instrumentTypeId property.
	* 
	* @return   the current value of the instrumentTypeId property
	*/
   public String getInstrumentTypeId()
   {
   		return instrumentTypeId;
   }

   /**
	* Sets the value of the instrumentTypeId property.
	* 
	* @param anInstrumentTypeId the new value of the instrumentTypeId property
	*/
   public void setInstrumentTypeId(String anInstrumentTypeId)
   {
   		instrumentTypeId = anInstrumentTypeId;
   }

   /**
	* Access method for the courtNum property.
	* 
	* @return   the current value of the courtNum property
	*/
   public String getCourtNum()
   {
		return courtNum;
   }
   
   /**
	* Sets the value of the courtNum property.
	* 
	* @param aCourtNum the new value of the courtNum property
	*/
   public void setCourtNum(String aCourtNum)
   {
		courtNum = aCourtNum;
   }

   /**
	* Access method for the defendantId property.
	* 
	* @return   the current value of the defendantId property
	*/
   public String getDefendantId()
   {
	   return defendantId;
   }
   
   /**
	* Sets the value of the defendantId property.
	* 
	* @param aDefendantId the new value of the defendantId property
	*/
	public void setDefendantId(String aDefendantId)
	{
		defendantId = aDefendantId;
	}

	/**
	 * Access method for the defendantName property.
	 * 
	 * @return   the current value of the defendantName property
	 */
	public String getDefendantName()
	{
		return defendantName;
	}
   
	/**
	 * Sets the value of the defendantName property.
	 * 
	 * @param aName  Used to set the defendantName property
	 */
	public void setDefendantName(String aName)
	{
		this.defendantName = aName;
	}
	/**
	 * Access method for the reasonForUpdateId property.
	 * 
	 * @return   the current value of the reasonForUpdateId property
	 */
	public String getReasonForUpdateId()
	{
		return reasonForUpdateId;
	}

	/**
	 * Sets the value of the reasonForUpdateId property.
	 * 
	 * @param aNameSeq the new value of the reasonForUpdateId property
	 */
	public void setReasonForUpdateId(String aReasonForUpdateId)
	{
		reasonForUpdateId = aReasonForUpdateId;
	}

	/**
	 * @return the isCscCreate
	 */
	public boolean isCscCreate() {
		return isCscCreate;
	}

	/**
	 * @param isCscCreate the isCscCreate to set
	 */
	public void setCscCreate(boolean isCscCreate) {
		this.isCscCreate = isCscCreate;
	}

	/**
	 * @return the isCscUpdate
	 */
	public boolean isCscUpdate() {
		return isCscUpdate;
	}

	/**
	 * @param isCscUpdate the isCscUpdate to set
	 */
	public void setCscUpdate(boolean isCscUpdate) {
		this.isCscUpdate = isCscUpdate;
	}

	public void setNameSeqNum(String nameSeqNum) {
		this.nameSeqNum = nameSeqNum;
	}

	public String getNameSeqNum() {
		return nameSeqNum;
	}

}
