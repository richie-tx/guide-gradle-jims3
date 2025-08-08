//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\ReactivateOutOfCountyCaseEvent.java

package messaging.managesupervisioncase;


public class ReactivateOutOfCountyCaseEvent extends UpdateOutOfCountyCaseEvent 
{
//   private String caseNum;
//   private String cdi;
//   private String dispositionTypeId;
//   private Date dispositionDate;
//   private Date supervisionBeginDate;
//   private Date supervisionEndDate;
//   private Date pretrialInterventionBeginDate;
//   private Date pretrialInterventionEndDate;
//   private String contactFirstName;
//   private String contactLastName;
//   private String contactMiddleName;
//   private String contactJobTitle;
//   private String contactPhoneNum;
//   private String contactPhoneExt;
//   private String originalAgencyName;
//   private IAddress agencyAddress;
//   
//   /**
//    * @roseuid 4443FBA30261
//    */
//   public ReactivateOutOfCountyCaseEvent() 
//   {
//    
//   }
//   
//   /**
//    * Access method for the caseNum property.
//    * 
//    * @return   the current value of the caseNum property
//    */
//   public String getCaseNum() 
//   {
//      return caseNum;
//   }
//   
//   /**
//    * Sets the value of the caseNum property.
//    * 
//    * @param aCaseNum the new value of the caseNum property
//    */
//   public void setCaseNum(String aCaseNum) 
//   {
//      caseNum = aCaseNum;
//   }
//   
//   /**
//    * Access method for the cdi property.
//    * 
//    * @return   the current value of the cdi property
//    */
//   public String getCdi() 
//   {
//      return cdi;
//   }
//   
//   /**
//    * Sets the value of the cdi property.
//    * 
//    * @param aCdi the new value of the cdi property
//    */
//   public void setCdi(String aCdi) 
//   {
//      cdi = aCdi;
//   }
//   
//   /**
//    * Access method for the dispositionTypeId property.
//    * 
//    * @return   the current value of the dispositionTypeId property
//    */
//   public String getDispositionTypeId() 
//   {
//      return dispositionTypeId;
//   }
//   
//   /**
//    * Sets the value of the dispositionTypeId property.
//    * 
//    * @param aDispositionTypeId the new value of the dispositionTypeId property
//    */
//   public void setDispositionTypeId(String aDispositionTypeId) 
//   {
//      dispositionTypeId = aDispositionTypeId;
//   }
//   
//   /**
//    * Access method for the dispositionDate property.
//    * 
//    * @return   the current value of the dispositionDate property
//    */
//   public Date getDispositionDate() 
//   {
//      return dispositionDate;
//   }
//   
//   /**
//    * Sets the value of the dispositionDate property.
//    * 
//    * @param aDispositionDate the new value of the dispositionDate property
//    */
//   public void setDispositionDate(Date aDispositionDate) 
//   {
//      dispositionDate = aDispositionDate;
//   }
//   
//   /**
//    * Access method for the supervisionBeginDate property.
//    * 
//    * @return   the current value of the supervisionBeginDate property
//    */
//   public Date getSupervisionBeginDate() 
//   {
//      return supervisionBeginDate;
//   }
//   
//   /**
//    * Sets the value of the supervisionBeginDate property.
//    * 
//    * @param aSupervisionBeginDate the new value of the supervisionBeginDate property
//    */
//   public void setSupervisionBeginDate(Date aSupervisionBeginDate) 
//   {
//      supervisionBeginDate = aSupervisionBeginDate;
//   }
//   
//   /**
//    * Access method for the supervisionEndDate property.
//    * 
//    * @return   the current value of the supervisionEndDate property
//    */
//   public Date getSupervisionEndDate() 
//   {
//      return supervisionEndDate;
//   }
//   
//   /**
//    * Sets the value of the supervisionEndDate property.
//    * 
//    * @param aSupervisionEndDate the new value of the supervisionEndDate property
//    */
//   public void setSupervisionEndDate(Date aSupervisionEndDate) 
//   {
//      supervisionEndDate = aSupervisionEndDate;
//   }
//   
//   /**
//    * Access method for the pretrialInterventionBeginDate property.
//    * 
//    * @return   the current value of the pretrialInterventionBeginDate property
//    */
//   public Date getPretrialInterventionBeginDate() 
//   {
//      return pretrialInterventionBeginDate;
//   }
//   
//   /**
//    * Sets the value of the pretrialInterventionBeginDate property.
//    * 
//    * @param aSupervisionBeginDate the new value of the pretrialInterventionBeginDate property
//    */
//   public void setPretrialInterventionBeginDate(Date aPretrialInterventionBeginDate) 
//   {
//      pretrialInterventionBeginDate = aPretrialInterventionBeginDate;
//   }
//   
//   /**
//    * Access method for the pretrialInterventionEndDate property.
//    * 
//    * @return   the current value of the pretrialInterventionEndDate property
//    */
//   public Date getPretrialInterventionEndDate() 
//   {
//      return pretrialInterventionEndDate;
//   }
//   
//   /**
//    * Sets the value of the pretrialInterventionEndDate property.
//    * 
//    * @param aSupervisionEndDate the new value of the pretrialInterventionEndDate property
//    */
//   public void setPretrialInterventionEndDate(Date aPretrialInterventionEndDate) 
//   {
//      pretrialInterventionEndDate = aPretrialInterventionEndDate;
//   }
//   
//   /**
//    * Access method for the originalAgencyName property.
//    * 
//    * @return   the current value of the originalAgencyName property
//    */
//   public String getOriginalAgencyName() 
//   {
//      return originalAgencyName;
//   }
//   
//   /**
//    * Sets the value of the originalAgencyName property.
//    * 
//    * @param aOriginalAgencyName the new value of the originalAgencyName property
//    */
//   public void setOriginalAgencyName(String aOriginalAgencyName) 
//   {
//      originalAgencyName = aOriginalAgencyName;
//   }
//   
//   /**
//    * Access method for the contactFirstName property.
//    * 
//    * @return   the current value of the contactFirstName property
//    */
//   public String getContactFirstName() 
//   {
//      return contactFirstName;
//   }
//   
//   /**
//    * Sets the value of the contactFirstName property.
//    * 
//    * @param aContactFirstName the new value of the contactFirstName property
//    */
//   public void setContactFirstName(String aContactFirstName) 
//   {
//      contactFirstName = aContactFirstName;
//   }
//   
//   /**
//    * Access method for the contactLastName property.
//    * 
//    * @return   the current value of the contactLastName property
//    */
//   public String getContactLastName() 
//   {
//      return contactLastName;
//   }
//   
//   /**
//    * Sets the value of the contactLastName property.
//    * 
//    * @param aContactLastName the new value of the contactLastName property
//    */
//   public void setContactLastName(String aContactLastName) 
//   {
//      contactLastName = aContactLastName;
//   }
//   
//   /**
//    * Access method for the contactMiddleName property.
//    * 
//    * @return   the current value of the contactMiddleName property
//    */
//   public String getContactMiddleName() 
//   {
//      return contactMiddleName;
//   }
//   
//   /**
//    * Sets the value of the contactMiddleName property.
//    * 
//    * @param aContactMiddleName the new value of the contactMiddleName property
//    */
//   public void setContactMiddleName(String aContactMiddleName) 
//   {
//      contactMiddleName = aContactMiddleName;
//   }
//   
//   /**
//    * Access method for the contactJobTitle property.
//    * 
//    * @return   the current value of the contactJobTitle property
//    */
//   public String getContactJobTitle() 
//   {
//      return contactJobTitle;
//   }
//   
//   /**
//    * Sets the value of the contactJobTitle property.
//    * 
//    * @param aContactJobTitle the new value of the contactJobTitle property
//    */
//   public void setContactJobTitle(String aContactJobTitle) 
//   {
//      contactJobTitle = aContactJobTitle;
//   }
//   
//   /**
//    * Access method for the contactPhoneNum property.
//    * 
//    * @return   the current value of the contactPhoneNum property
//    */
//   public String getContactPhoneNum() 
//   {
//      return contactPhoneNum;
//   }
//   
//   /**
//    * Sets the value of the contactPhoneNum property.
//    * 
//    * @param aContactPhoneNum the new value of the contactPhoneNum property
//    */
//   public void setContactPhoneNum(String aContactPhoneNum) 
//   {
//      contactPhoneNum = aContactPhoneNum;
//   }
//   
//   /**
//    * Access method for the contactPhoneExt property.
//    * 
//    * @return   the current value of the contactPhoneExt property
//    */
//   public String getContactPhoneExt() 
//   {
//      return contactPhoneExt;
//   }
//   
//   /**
//    * Sets the value of the contactPhoneExt property.
//    * 
//    * @param aContactPhoneExt the new value of the contactPhoneExt property
//    */
//   public void setContactPhoneExt(String aContactPhoneExt) 
//   {
//      contactPhoneExt = aContactPhoneExt;
//   }
//   
//   /**
//    * Access method for the agencyAddress property.
//    * 
//    * @return   the current value of the agencyAddress property
//    */
//   public IAddress getAgencyAddress() 
//   {
//      return agencyAddress;
//   }
//   
//   /**
//    * Sets the value of the agencyAddress property.
//    * 
//    * @param aAgencyAddress the new value of the agencyAddress property
//    */
//   public void setAgencyAddress(IAddress aAgencyAddress) 
//   {
//      agencyAddress = aAgencyAddress;
//   }
}
