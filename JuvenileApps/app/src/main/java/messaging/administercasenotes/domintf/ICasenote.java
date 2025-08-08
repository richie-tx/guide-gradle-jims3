package messaging.administercasenotes.domintf;

import java.util.Collection;
import java.util.Date;

/**
 * @author jmcnabb
 *
 * Interface for Casenotes
 */
public interface ICasenote
{
	/**
	 * @return Returns the agencyId.
	 */
	String getAgencyId();

	/**
	 * @return Returns the collection of associates.
	 */
	Collection getAssociates();
	boolean getAutoSaved();
	
	String getCasenoteTypeCodeId();
	
	void setCasenoteTypeCodeId(String casenoteTypeCodeId);

	/**
	 * @return Returns the caseId.
	 */
	String getCaseId();

	/**
	 * @return Returns the casenoteId.
	 */
	String getCasenoteId();

	/**
	 * @return Returns the casenoteStatusId.
	 */
	String getCasenoteStatusId();

	/**
	 * @return Returns the casenoteTypeId.
	 */
	String getCasenoteTypeId();

	/**
	 * @return Returns the collateralId.
	 */
	String getCollateralId();
    String getCollateralInfo();

	/**
	 * @return Returns the contactMethodId.
	 */
	String getContactMethodId();

	/**
	 * @return Returns the contextType.
	 */
	String getContextType();

	/**
	 * @return Returns the creatorId.
	 */
	String getCreatorId();
	
	/**
	 * @return Returns the migrateCreator.
	 */
	String getMigrateCreator();

	/**
	 * @return Returns the entryDate.
	 */
	Date getEntryDate();

	/**
	 * @return Returns the howGeneratedId.
	 */
	String getHowGeneratedId();

	/**
	 * @return Returns the notes.
	 */
	String getNotes();

	/**
	 * @return Returns the collection of subjects.
	 */
	Collection getSubjects();

	/**
	 * @return Returns the superviseeId.
	 */
	String getSuperviseeId();

	/**
	 * @return Returns the supervisionOrderId.
	 */
	String getSupervisionOrderId();

	/**
	 * @return Returns the supervisionPeriodId.
	 */
	String getSupervisionPeriodId();

	/**
	 * @return  Indicates whether the casenote is to be saved as draft or complete.
	 */
	boolean isSaveAsDraft();
	
	/**
	 * @return Returns the createDate.
	 */
	Date getCreateDate();
	
	/**
	 * @param anAgencyId  The agencyId to set.
	 */
	void setAgencyId(String anAgencyId);

	/**
	 * @param associateColl  The collection of associates to set.
	 */
	void setAssociates(Collection associateColl);
	void setAutoSaved(boolean b);

	/**
	 * @param aCaseId  The caseId to set.
	 */
	void setCaseId(String aCaseId);

	/**
	 * @param aCasenoteId  The casenoteId to set.
	 */
	void setCasenoteId(String aCasenoteId);

	/**
	 * @param aCasenoteStatusId   The casenoteStatusId to set.
	 */
	void setCasenoteStatusId(String aCasenoteStatusId);

	/**
	 * @param casenoteTypeId   The casenoteTypeId to set.
	 */
	void setCasenoteTypeId(String casenoteTypeId);

	/**
	 * @param collateralId   The collateralId to set.
	 */
	void setCollateralId(String collateralId);
    void setCollateralInfo(String collateralInfo);

	/**
	 * @param contactMethodId  The contactMethodId to set.
	 */
	void setContactMethodId(String contactMethodId);

	/**
	 * @param aContextType  The contactType to set.
	 */
	void setContextType(String aContextType);

	/**
	 * @param creatorId  The creatorId to set.
	 */
	void setCreatorId(String creatorId);
	

	/**
	 * @param migrateCreator The migrateCreator to set.
	 */
	void setMigrateCreator(String migrateCreator);

	/**
	 * @param entryDate  The entryDate to set.
	 */
	void setEntryDate(Date entryDate);

	/**
	 * @param howGeneratedid   The howGeneratedid to set.
	 */
	void setHowGeneratedId(String howGeneratedId);

	/**
	 * @param notes   The notes to set.
	 */
	void setNotes(String notes);

	/**
	 * @param value   The value to set.
	 */
	void setSaveAsDraft(boolean value);

	/**
	 * @param aSubjectCollection  The subjects to set.
	 */
	void setSubjects(Collection aSubjectCollection);

	/**
	 * @param superviseeId  The superviseeId to set.
	 */
	void setSuperviseeId(String aSuperviseeId);

	/**
	 * @param supervisionOrderId  The supervisionOrderId to set.
	 */
	void setSupervisionOrderId(String aSupervisionOrderId);

	/**
	 * @param supervisionPeriodid  The supervisionPeriodid to set.
	 */
	void setSupervisionPeriodId(String aSupervisionPeriodId);
	
	/**
	 * @param createDate  The createDate to set.
	 */
	void setCreateDate(Date createDate);
}
