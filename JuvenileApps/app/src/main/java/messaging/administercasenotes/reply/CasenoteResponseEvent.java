//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\CasenoteEvent.java

package messaging.administercasenotes.reply;

import java.util.Collection;
import java.util.Date;

import messaging.administercasenotes.domintf.ICasenote;
import mojo.km.messaging.ResponseEvent;

public class CasenoteResponseEvent extends ResponseEvent implements ICasenote
{

	private String agencyId;
	//private String contextId;
	private Collection associates;
	private boolean autoSaved;
	private String caseId;
	private String casenoteId;
	private String casenoteStatusId;
	private String casenoteTypeId;
	private String casenoteTypeCodeId;
	private String collateralId;
	private String collateralInfo;
	private String contactMethodId;
	private String contextType;
	private Date createDate;
	private String creatorId;
	private String migrateCreator;
	private Date entryDate;
	private String howGeneratedId;
	private String notes;
	private Collection subjects;
	private String superviseeId;
	private String supervisionOrderId;
	private String supervisionPeriodId;

   /**
    * @roseuid 44F4616F0118
    */
   public CasenoteResponseEvent() 
   {
	  
   }
   
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return Returns the collection of associates.
	 */
	public Collection getAssociates()
	{
		return associates;
	}

	/**
	 * @return Returns the autoSaved.
	 */
	public boolean getAutoSaved() {
		return autoSaved;
	}

	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId()
	{
		return caseId;
	}

	/**
	 * @return Returns the casenoteId.
	 */
	public String getCasenoteId()
	{
		return casenoteId;
	}

	/**
	 * @return Returns the casenoteStatusId.
	 */
	public String getCasenoteStatusId()
	{
		return casenoteStatusId;
	}

	/**
	 * @return Returns the casenoteTypeId.
	 */
	public String getCasenoteTypeId()
	{
		return casenoteTypeId;
	}

	/**
	 * @return Returns the collateralId.
	 */
	public String getCollateralId()
	{
		return collateralId;
	}
    /**
     * @return Returns the collateralInfo.
     */
    public String getCollateralInfo() {
        return collateralInfo;
    }

	/**
	 * @return Returns the contactMethodId.
	 */
	public String getContactMethodId()
	{
		return contactMethodId;
	}

	/**
	 * @return Returns the contextId.
	 */
//	public String getContextId()
//	{
//		return contextId;
//	}

	/**
	 * @return Returns the contextType.
	 */
	public String getContextType()
	{
		return contextType;
	}

	/**
	 * @return Returns the creatorId.
	 */
	public String getCreatorId()
	{
		return creatorId;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return Returns the howGeneratedid.
	 */
	public String getHowGeneratedId()
	{
		return howGeneratedId;
	}

	/**
	 * @return Returns the notes.
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @return Returns the collection of subjectIds.
	 */
	public Collection getSubjects()
	{
		return subjects;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId()
	{
		return superviseeId;
	}

	/**
	 * @return Returns the supervisionOrderId.
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId()
	{
		return supervisionPeriodId;
	}

	/**
	 *  Dummy operation; only to implement interface ICasenote.
	 * @return  false.
	 */
	public boolean isSaveAsDraft()
	{
		return false;
	}

	/**
	 * @param anAgencyId  The agencyId to set.
	 */
	public void setAgencyId(String anAgencyId)
	{
		this.agencyId = anAgencyId;
	}

	/**
	 * @param associateColl  The collection of associates to set.
	 */
	public void setAssociates(Collection associateColl)
	{
		this.associates = associateColl;
	}
	/**
	 * @param autoSaved The autoSaved to set.
	 */
	public void setAutoSaved(boolean autoSaved) {
		this.autoSaved = autoSaved;
	}

	/**
	 * @param aCaseId  The caseId to set.
	 */
	public void setCaseId(String aCaseId)
	{
		this.caseId = aCaseId;
	}

	/**
	 * @param aCasenoteId  The casenoteId to set.
	 */
	public void setCasenoteId(String aCasenoteId)
	{
		this.casenoteId = aCasenoteId;
	}

	/**
	 * @param aCasenoteStatusId  The casenoteStatusId to set.
	 */
	public void setCasenoteStatusId(String aCasenoteStatusId)
	{
		this.casenoteStatusId = aCasenoteStatusId;
	}

	/**
	 * @param casenoteTypeId   The casenoteTypeId to set.
	 */
	public void setCasenoteTypeId(String casenoteTypeId)
	{
		this.casenoteTypeId = casenoteTypeId;
	}

	/**
	 * @param collateralId   The collateralId to set.
	 */
	public void setCollateralId(String collateralId)
	{
		this.collateralId = collateralId;
	}

    /**
     * @param collateralInfo The collateralInfo to set.
     */
    public void setCollateralInfo(String collateralInfo) {
        this.collateralInfo = collateralInfo;
    }

	/**
	 * @param contactMethodId  The contactMethodId to set.
	 */
	public void setContactMethodId(String contactMethodId)
	{
		this.contactMethodId = contactMethodId;
	}

	/**
	 * @param aContextId   The contextId to set.
	 */
//	public void setContextId(String aContextId)
//	{
//		this.contextId = aContextId;
//	}

	/**
	 * @param aContextType   The contextType to set.
	 */
	public void setContextType(String aContextType)
	{
		this.contextType = aContextType;
	}

	/**
	 * @param creatorId  The creatorId to set.
	 */
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}

	/**
	 * @param entryDate  The entryDate to set.
	 */
	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * @param howGeneratedid   The howGeneratedid to set.
	 */
	public void setHowGeneratedId(String howGeneratedId)
	{
		this.howGeneratedId = howGeneratedId;
	}

	/**
	 * @param notes   The notes to set.
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	/**
	 *  Dummy operation; only to implement interface ICasenote.
	 * @param value   The value to set.
	 */
	public void setSaveAsDraft(boolean value)
	{
		// do nothing
	}

	/**
	 * @param aSubjectCollection  The subjectCollection to set.
	 */
	public void setSubjects(Collection aSubjectCollection)
	{
		this.subjects = aSubjectCollection;
	}

	/**
	 * @param superviseeId  The superviseeId to set.
	 */
	public void setSuperviseeId(String aSuperviseeId)
	{
		this.superviseeId = aSuperviseeId;
	}

	/**
	 * @param supervisionOrderId  The supervisionOrderId to set.
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		this.supervisionOrderId = aSupervisionOrderId;
	}

	/**
	 * @param supervisionPeriodId  The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String aSupervisionPeriodId)
	{
		this.supervisionPeriodId = aSupervisionPeriodId;
	}
	/**
	 * @return Returns the casenoteTypeCodeId.
	 */
	public String getCasenoteTypeCodeId() {
		return casenoteTypeCodeId;
	}
	/**
	 * @param casenoteTypeCodeId The casenoteTypeCodeId to set.
	 */
	public void setCasenoteTypeCodeId(String casenoteTypeCodeId) {
		this.casenoteTypeCodeId = casenoteTypeCodeId;
	}

	public String getMigrateCreator() {
		return migrateCreator;
	}

	public void setMigrateCreator(String migrateCreator) {
		this.migrateCreator = migrateCreator;
	}
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

		
}
