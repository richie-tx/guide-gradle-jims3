package pd.supervision.managesupervisioncase;

import java.util.Date;
import java.util.Iterator;

import messaging.domintf.managesupervisioncase.IUpdateHistory;
import messaging.managesupervisioncase.GetOOCCaseUpdateHistoryEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author jmcnabb
 *
 * This class represents the history of updates (changes) made to an Out of 
 */
public class OutOfCountyCaseHistory extends PersistentObject
{

	private String reasonForUpdate;
	private Date updateDate;
	private String updateTime;
	private String dispositionTypeId;
	private Date transferDate;


	/**
	 * @return Returns the reasonForUpdate.
	 */
	public String getReasonForUpdate() {
		fetch();
		return reasonForUpdate;
	}

	/**
	 * @param aReasonForUpdate The reasonForUpdate to set.
	 */
	public void setReasonForUpdate(String aReasonForUpdate) {
		if (reasonForUpdate == null || !reasonForUpdate.equals(aReasonForUpdate))
		{
			markModified();
		}
		reasonForUpdate = aReasonForUpdate;
	}

	/**
	 * @return Returns the updateDate.
	 */
	public Date getUpdateDate() {
		fetch();
		return updateDate;
	}

	/**
	 * @param aDate The updateDate to set.
	 */
	public void setUpdateDate(Date aDate) {
		if (updateDate == null || !updateDate.equals(aDate))
		{
			markModified();
		}
		updateDate = aDate;
	}

	/**
	 * @return Returns the updateTime.
	 */
	public String getUpdateTime() {
		fetch();
		return updateTime;
	}

	/**
	 * @param aTime The updateTime to set.
	 */
	public void setUpdateTime(String aTime) {
		if (updateTime == null || !updateTime.equals(aTime))
		{
			markModified();
		}
		updateTime = aTime;
	}

	/**
	 * Gets all values for an OutOfCountyProbationCase.
	 * 
	 * @param oocCase
	 */
	public void fillOutOfCountyCaseHistory(IUpdateHistory oocHistory)
	{
		oocHistory.setReasonForUpdate(getReasonForUpdate());
		oocHistory.setUpdateUserId(getUpdateUserID());
		oocHistory.setUpdateDate(getUpdateDate());
		oocHistory.setUpdateTime(getUpdateTime());
		oocHistory.setDispositionTypeId(getDispositionTypeId());
		oocHistory.setTransferDate(getTransferDate());
	}

	public static Iterator findAll(GetOOCCaseUpdateHistoryEvent historyEvent)
	{
		return new Home().findAll(historyEvent, OutOfCountyCaseHistory.class);
	}

	public void setDispositionTypeId(String dispositionTypeId) {
		if (dispositionTypeId == null || !dispositionTypeId.equals(dispositionTypeId))
		{
			markModified();
		}

		this.dispositionTypeId = dispositionTypeId;
	}

	public String getDispositionTypeId() {
		fetch();
		return dispositionTypeId;
	}
	public Date getTransferDate() {
		fetch();
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		if (transferDate == null || !transferDate.equals(transferDate))
		{
			markModified();
		}

		this.transferDate = transferDate;
	}
	
}
