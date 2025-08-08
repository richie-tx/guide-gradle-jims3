//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\domintf\\managesupervisioncase\\ISupervisionCase.java

package messaging.domintf.managesupervisioncase;

import java.util.Date;

import messaging.contact.domintf.IAddress;


public interface ISupervisionCase 
{
   
   /**
    * @return String
    * @roseuid 4443FA6C03A5
    */
   String getCdi();
   
   /**
    * @param aCdi
    * @roseuid 4443FA7802EE
    */
   void setCdi(String aCdi);

   /**
	* Access method for the caseNum property.
	* 
	* @return   the current value of the caseNum property
	*/
   String getCaseNum();
   
   /**
	* Sets the value of the caseNum property.
	* 
	* @param aCaseNum the new value of the caseNum property
	*/
   void setCaseNum(String aCaseNum);
   
   /**
	* Access method for the stateOffenseCodeId property.
	* 
	* @return   the current value of the stateOffenseCodeId property
	*/
   String getStateOffenseCodeId();
   
   /**
	* Sets the value of the stateOffenseCodeId property.
	* 
	* @param aStateOffenseCodeId the new value of the stateOffenseCodeId property
	*/
   void setStateOffenseCodeId(String aStateOffenseCodeId);
   
   /**
	* Access method for the outOfCountyCaseTypeId property.
	* 
	* @return   the current value of the outOfCountyCaseTypeId property
	*/
   String getOutOfCountyCaseTypeId();
   
   /**
	* Sets the value of the outOfCountyCaseTypeId property.
	* 
	* @param aOutOfCountyCaseType the new value of the outOfCountyCaseTypeId property
	*/
   void setOutOfCountyCaseTypeId(String aOutOfCountyCaseTypeId);
   
   /**
	* Access method for the offenseDate property.
	* 
	* @return   the current value of the offenseDate property
	*/
   Date getOffenseDate();
   
   /**
	* Sets the value of the offenseDate property.
	* 
	* @param aOffenseDate the new value of the offenseDate property
	*/
   void setOffenseDate(Date aOffenseDate);
   
   /**
	* Access method for the arrestDate property.
	* 
	* @return   the current value of the arrestDate property
	*/
   Date getArrestDate();
   
   /**
	* Sets the value of the arrestDate property.
	* 
	* @param aArrestDate the new value of the arrestDate property
	*/
   void setArrestDate(Date aArrestDate);
   
   /**
	* Access method for the supervisionBeginDate property.
	* 
	* @return   the current value of the supervisionBeginDate property
	*/
   Date getSupervisionBeginDate();
   
   /**
	* Sets the value of the supervisionBeginDate property.
	* 
	* @param aSupervisionBeginDate the new value of the supervisionBeginDate property
	*/
   void setSupervisionBeginDate(Date aSupervisionBeginDate);
   
   /**
	* Access method for the supervisionEndDate property.
	* 
	* @return   the current value of the supervisionEndDate property
	*/
   Date getSupervisionEndDate();
   
   /**
	* Sets the value of the supervisionEndDate property.
	* 
	* @param aSupervisionEndDate the new value of the supervisionEndDate property
	*/
   void setSupervisionEndDate(Date aSupervisionEndDate);
   
   /**
	* Access method for the originalCaseNum property.
	* 
	* @return   the current value of the originalCaseNum property
	*/
   String getOriginalCaseNum();
   
   /**
	* Sets the value of the originalCaseNum property.
	* 
	* @param aOriginalCaseNum the new value of the originalCaseNum property
	*/
   void setOriginalCaseNum(String aOriginalCaseNum);
   
   /**
	* Access method for the originalCourtNum property.
	* 
	* @return   the current value of the originalCourtNum property
	*/
   String getOriginalCourtNum();
   
   /**
	* Sets the value of the originalCourtNum property.
	* 
	* @param aOriginalCourtNum the new value of the originalCourtNum property
	*/
   void setOriginalCourtNum(String aOriginalCourtNum);
   
   /**
	* Access method for the personId property.
	* 
	* @return   the current value of the personId property
	*/
   String getPersonId();
   
   /**
	* Sets the value of the personId property.
	* 
	* @param aPersonId the new value of the personId property
	*/
   void setPersonId(String aPersonId);
   
   /**
	* Access method for the transferInDate property.
	* 
	* @return   the current value of the transferInDate property
	*/
   Date getTransferInDate();
   
   /**
	* Sets the value of the transferInDate property.
	* 
	* @param aTransferInDate the new value of the transferInDate property
	*/
   void setTransferInDate(Date aTransferInDate);
   
   /**
	* Access method for the originalCountyId property.
	* 
	* @return   the current value of the originalCountyId property
	*/
   String getOriginalCountyId();
   
   /**
	* Sets the value of the originalCountyId property.
	* 
	* @param aOriginalCountyId the new value of the originalCounty property
	*/
   void setOriginalCountyId(String aOriginalCounty);
      
   /**
	* Access method for the originalStateId property.
	* 
	* @return   the current value of the originalStateId property
	*/
   String getOriginalStateId();
   
   /**
	* Sets the value of the originalStateId property.
	* 
	* @param aOriginalStateId the new value of the originalStateId property
	*/
   void setOriginalStateId(String aOriginalStateId);
   
   /**
	* Access method for the originalAgencyName property.
	* 
	* @return   the current value of the originalAgencyName property
	*/
   String getOriginalAgencyName();
   
   /**
	* Sets the value of the originalAgencyName property.
	* 
	* @param aOriginalAgencyName the new value of the originalAgencyName property
	*/
   void setOriginalAgencyName(String aOriginalAgencyName);
   
   /**
	* Access method for the contactFirstName property.
	* 
	* @return   the current value of the contactFirstName property
	*/
   String getContactFirstName();
   
   /**
	* Sets the value of the contactFirstName property.
	* 
	* @param aContactFirstName the new value of the contactFirstName property
	*/
   void setContactFirstName(String aContactFirstName);
   
   /**
	* Access method for the contactLastName property.
	* 
	* @return   the current value of the contactLastName property
	*/
   String getContactLastName();
   
   /**
	* Sets the value of the contactLastName property.
	* 
	* @param aContactLastName the new value of the contactLastName property
	*/
   void setContactLastName(String aContactLastName);
   
   /**
	* Access method for the contactMiddleName property.
	* 
	* @return   the current value of the contactMiddleName property
	*/
   String getContactMiddleName();
   
   /**
	* Sets the value of the contactMiddleName property.
	* 
	* @param aContactMiddleName the new value of the contactMiddleName property
	*/
   void setContactMiddleName(String aContactMiddleName);
   
   /**
	* Access method for the contactJobTitle property.
	* 
	* @return   the current value of the contactJobTitle property
	*/
   String getContactJobTitle();
   
   /**
	* Sets the value of the contactJobTitle property.
	* 
	* @param aContactJobTitle the new value of the contactJobTitle property
	*/
   void setContactJobTitle(String aContactJobTitle);
   
   /**
	* Access method for the contactPhoneNum property.
	* 
	* @return   the current value of the contactPhoneNum property
	*/
   String getContactPhoneNum();
   
   /**
	* Sets the value of the contactPhoneNum property.
	* 
	* @param aContactPhoneNum the new value of the contactPhoneNum property
	*/
   void setContactPhoneNum(String aContactPhoneNum);
   
   /**
	* Access method for the contactPhoneExt property.
	* 
	* @return   the current value of the contactPhoneExt property
	*/
   String getContactPhoneExt();
   
   /**
	* Sets the value of the contactPhoneExt property.
	* 
	* @param aContactPhoneExt the new value of the contactPhoneExt property
	*/
   void setContactPhoneExt(String aContactPhoneExt);
   
   /**
	* Access method for the violentOffenseInd property.
	* 
	* @return   the current value of the violentOffenseInd property
	*/
   String getViolentOffenseInd();
   
   /**
	* Sets the value of the violentOffenseInd property.
	* 
	* @param aViolentOffenseInd the new value of the violentOffenseInd property
	*/
   void setViolentOffenseInd(String aViolentOffenseInd);
   
   /**
	* Access method for the familyViolenceInd property.
	* 
	* @return   the current value of the familyViolenceInd property
	*/
   String getFamilyViolenceInd();
   
   /**
	* Sets the value of the familyViolenceInd property.
	* 
	* @param aFamilyViolenceInd the new value of the familyViolenceInd property
	*/
   void setFamilyViolenceInd(String aFamilyViolenceInd);
   
   /**
	* Access method for the agencyAddress property.
	* 
	* @return   the current value of the agencyAddress property
	*/
   IAddress getAgencyAddress();
   
   /**
	* Sets the value of the agencyAddress property.
	* 
	* @param aAgencyAddress the new value of the agencyAddress property
	*/
   void setAgencyAddress(IAddress aAgencyAddress);
   
   /**
	* Access method for the cjisNum property.
	* 
	* @return   the current value of the cjisNum property
	*/
   String getCjisNum();
   
   /**
	* Sets the value of the cjisNum property.
	* 
	* @param aCjisNum the new value of the cjisNum property
	*/
   void setCjisNum(String aCjisNum);
   
   /**
	* Access method for the dispositionTypeId property.
	* 
	* @return   the current value of the dispositionTypeId property
	*/
   String getDispositionTypeId();
   
   /**
	* Sets the value of the dispositionTypeId property.
	* 
	* @param aDispositionTypeId the new value of the dispositionTypeId property
	*/
   void setDispositionTypeId(String aDispositionTypeId);
   
   /**
	* Access method for the dispositionDate property.
	* 
	* @return   the current value of the dispositionDate property
	*/
   Date getDispositionDate();
   
   /**
	* Sets the value of the dispositionDate property.
	* 
	* @param aDispositionDate the new value of the dispositionDate property
	*/
   void setDispositionDate(Date aDispositionDate);
   
   /**
	* Access method for the sentenceDate property.
	* 
	* @return   the current value of the sentenceDate property
	*/
   Date getSentenceDate();

   
   /**
	* Sets the value of the sentenceDate property.
	* 
	* @param aSentenceDate the new value of the sentenceDate property
	*/
   void setSentenceDate(Date aSentenceDate);
   
   /**
	* Access method for the supervisionYears property.
	* 
	* @return   the current value of the supervisionYears property
	*/
   int getSupervisionYears();
   
   /**
	* Sets the value of the supervisionYears property.
	* 
	* @param aSupervisionYears the new value of the supervisionYears property
	*/
   void setSupervisionYears(int aSupervisionYears);
   
   /**
	* Access method for the supervisionMonths property.
	* 
	* @return   the current value of the supervisionMonths property
	*/
   int getSupervisionMonths();
   
   /**
	* Sets the value of the supervisionMonths property.
	* 
	* @param aSupervisionMonths the new value of the supervisionMonths property
	*/
   void setSupervisionMonths(int aSupervisionMonths);
   
   /**
	* Access method for the supervisionDays property.
	* 
	* @return   the current value of the supervisionDays property
	*/
   int getSupervisionDays();
   
   /**
	* Sets the value of the supervisionDays property.
	* 
	* @param aSupervisionDays the new value of the supervisionDays property
	*/
   void setSupervisionDays(int aSupervisionDays);
   
   /**
	* Access method for the confinementYears property.
	* 
	* @return   the current value of the confinementYears property
	*/
   int getConfinementYears();
   
   /**
	* Sets the value of the confinementYears property.
	* 
	* @param aConfinementYears the new value of the confinementYears property
	*/
   void setConfinementYears(int aConfinementYears);
   
   /**
	* Access method for the confinementMonths property.
	* 
	* @return   the current value of the confinementMonths property
	*/
   int getConfinementMonths();
   
   /**
	* Sets the value of the confinementMonths property.
	* 
	* @param aConfinementMonths the new value of the confinementMonths property
	*/
   void setConfinementMonths(int aConfinementMonths);   
   /**
	* Access method for the confinementDays property.
	* 
	* @return   the current value of the confinementDays property
	*/
   int getConfinementDays();
   
   /**
	* Sets the value of the confinementDays property.
	* 
	* @param aConfinementDays the new value of the confinementDays property
	*/
   void setConfinementDays(int aConfinementDays);

   /**
	* Access method for the pretrialInterventionBeginDate property.
	* 
	* @return   the current value of the pretrialInterventionBeginDate property
	*/
   Date getPretrialInterventionBeginDate();
   
   /**
	* Sets the value of the pretrialInterventionBeginDate property.
	* 
	* @param aPretrialInterventionBeginDate the new value of the pretrialInterventionBeginDate property
	*/
   void setPretrialInterventionBeginDate(Date aPretrialInterventionBeginDate);
   
   /**
	* Access method for the pretrialInterventionEndDate property.
	* 
	* @return   the current value of the pretrialInterventionEndDate property
	*/
   Date getPretrialInterventionEndDate();
   
   /**
	* Sets the value of the pretrialInterventionEndDate property.
	* 
	* @param aPretrialInterventionEndDate the new value of the pretrialInterventionEndDate property
	*/
   void setPretrialInterventionEndDate(Date aPretrialInterventionEndDate);
   
   /**
	* Access method for the stateId property.
	* 
	* @return   the current value of the stateId property
	*/
   String getStateId();

   /**
	* Sets the value of the stateId property.
	* 
	* @param aStateId the new value of the stateId property
	*/
   void setStateId(String aStateId);

   /**
	* Access method for the countyId property.
	* 
	* @return   the current value of the countyId property
	*/
   String getCountyId();

   /**
	* Sets the value of the countyId property.
	* 
	* @param aCountyId the new value of the countyId property
	*/
   void setCountyId(String aCountyId);

   /**
	* Access method for the spn property.
	* 
	* @return   the current value of the spn property
	*/
   String getSpn();

   /**
	* Sets the value of the spn property.
	* 
	* @param aSpn the new value of the spn property
	*/
   void setSpn(String aSpn);

   /**
	* Indicates whether the OutOfCountyCase can be reactivated or not.
	* 
	* @return true / false
	*/
   boolean canBeReactivated();
   
   /**
	* Sets the value of the reactivationInd property.
	* 
	* @param aValue the new value of the reactivationInd property
	*/
   void setReactivationInd(boolean aValue);

   /**
	* Indicates whether the OutOfCountyCase is being created or not.
	* 
	* @return the value of the createInd (true / false)
	*/
   boolean isCreate();
   
   /**
	* Sets the value of the createInd property.
	* 
	* @param aValue the new value of the createInd property
	*/
   void setCreateInd(boolean aValue);

   /**
	* Sets the value of the filingDate property.
	* 
	* @param aFilingDate the new value of the filingDate property
	*/
   void setFilingDate(Date aFilingDate);
   
   /**
	* Access method for the filingDate property.
	* 
	* @return   the current value of the filingDate property
	*/
   Date getFilingDate();
   
   /**
	* Access method for the instrumentTypeId property.
	* 
	* @return   the current value of the instrumentTypeId property
	*/
   String getInstrumentTypeId();

   /**
	* Sets the value of the instrumentTypeId property.
	* 
	* @param anInstrumentTypeId the new value of the instrumentTypeId property
	*/
   void setInstrumentTypeId(String anInstrumentTypeId);

   /**
	* Access method for the courtNum property.
	* 
	* @return   the current value of the courtNum property
	*/
   String getCourtNum();
   
   /**
	* Sets the value of the courtNum property.
	* 
	* @param aCourtNum the new value of the courtNum property
	*/
   void setCourtNum(String aCourtNum);
   
   /**
	* Access method for the defendantId property.
	* 
	* @return   the current value of the defendantId property
	*/
   String getDefendantId();

   /**
	* Sets the value of the defendantId property.
	* 
	* @param aDefendantId the new value of the defendantId property
	*/
   void setDefendantId(String aDefendantId);

   /**
	* Access method for the defendantId property.
	* 
	* @return   the current value of the defendantId property
	*/
  String getDefendantName();

  /**
	* Sets the value of the defendantName property.
	* 
	* @param aName the new value of the defendantName property
	*/
  void setDefendantName(String aName);

	/**
	 * Access method for the reasonForUpdateId property.
	 * 
	 * @return   the current value of the reasonForUpdateId property
	 */
	public String getReasonForUpdateId();

	/**
	 * Sets the value of the reasonForUpdateId property.
	 * 
	 * @param aReasonForUpdateId the new value of the reasonForUpdateId property
	 */
	public void setReasonForUpdateId(String aReasonForUpdateId);

   /**
	* Access method for the previousDispositionTypeId property.
	* 
	* @return   the current value of the previousDispositionTypeId property
	*/
   String getPreviousDispositionTypeId();
   
   /**
	* Sets the value of the previousDispositionTypeId property.
	* 
	* @param aDispositionTypeId the new value of the previousDispositionTypeId property
	*/
   void setPreviousDispositionTypeId(String aDispositionTypeId);
   
}
