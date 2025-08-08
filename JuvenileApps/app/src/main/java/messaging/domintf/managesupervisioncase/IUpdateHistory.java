package messaging.domintf.managesupervisioncase;

import java.util.Date;

import messaging.contact.domintf.IName;

/**
 * @author jmcnabb
 *
 * Interface for OOC Case Update History.
 */
public interface IUpdateHistory
{
	/**
	 * @return Returns the caseId.
	 */
	String getCaseId();

	/**
	 * @param aCaseId The caseId to set.
	 */
	void setCaseId(String aCaseId);

	/**
	 * @return Returns the reasonForUpdate.
	 */
	String getReasonForUpdate();

	/**
	 * @param aReasonForUpdate The reasonForUpdate to set.
	 */
	void setReasonForUpdate(String aReasonForUpdate);

	/**
	 * @return Returns the updateUserId.
	 */
	String getUpdateUserId();

	/**
	 * @param aUserId The updateUserId to set.
	 */
	void setUpdateUserId(String aUserId);

	/**
	 * @return Returns the updateDate.
	 */
	Date getUpdateDate();

	/**
	 * @param aDate The updateDate to set.
	 */
	void setUpdateDate(Date aDate);

	/**
	 * @return Returns the updateTime.
	 */
	String getUpdateTime();

	/**
	 * @param aTime The updateTime to set.
	 */
	void setUpdateTime(String aTime);

	/**
	 * @return Returns the updateUserName.
	 */
	IName getUpdateUserName();

	/**
	 * @param aUserName The updateUserName to set.
	 */
	void setUpdateUserName(IName aUserName);
	
	String getDispositionTypeId();
	void setDispositionTypeId(String dispositionTypeId);
	Date getTransferDate();
	void setTransferDate(Date transferDate);
	

}
