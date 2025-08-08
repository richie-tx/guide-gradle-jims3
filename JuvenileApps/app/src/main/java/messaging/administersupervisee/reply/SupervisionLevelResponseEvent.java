package messaging.administersupervisee.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

public class SupervisionLevelResponseEvent extends ResponseEvent implements Comparable {
	
	private Date losEffectiveDate;
	private String supervisionLevelId;
	private String supervisionLevelDesc;
	private String supervisionLevelHistoryId;
	private String comments;
	private boolean sentToState;
	private Date entryDate;
	private String userName;
	private boolean currentLOS;
	/**
	 * @return comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return losEffectiveDate
	 */
	public Date getLosEffectiveDate() {
		return losEffectiveDate;
	}
	/**
	 * @param losEffectiveDate
	 */
	public void setLosEffectiveDate(Date losEffectiveDate) {
		this.losEffectiveDate = losEffectiveDate;
	}
	/**
	 * @return supervisionLevelHistoryId
	 */
	public String getSupervisionLevelHistoryId() {
		return supervisionLevelHistoryId;
	}
	/**
	 * @param supervisionLevelHistoryId
	 */
	public void setSupervisionLevelHistoryId(String supervisionLevelHistoryId) {
		this.supervisionLevelHistoryId = supervisionLevelHistoryId;
	}
	/**
	 * @return supervisionLevelId
	 */
	public String getSupervisionLevelId() {
		return supervisionLevelId;
	}
	/**
	 * @param supervisionLevelId
	 */
	public void setSupervisionLevelId(String supervisionLevelId) {
		this.supervisionLevelId = supervisionLevelId;
	}
	/**
	 * @return sentToState
	 */
	public boolean isSentToState() {
		return sentToState;
	}
	/**
	 * @param sentToState
	 */
	public void setSentToState(boolean sentToState) {
		this.sentToState = sentToState;
	}
	/**
	 * @return entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return supervisionLevelDesc
	 */
	public String getSupervisionLevelDesc() {
		return supervisionLevelDesc;
	}
	/**
	 * @param supervisionLevelDesc
	 */
	public void setSupervisionLevelDesc(String supervisionLevelDesc) {
		this.supervisionLevelDesc = supervisionLevelDesc;
	}
	
	/**
     * Defaults to a descending sort. Newest dates first, nulls at the bottom.
     */
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object obj) throws ClassCastException
    {
        if (obj == null)
        {
            return -1;
        }

        SupervisionLevelResponseEvent other = (SupervisionLevelResponseEvent) obj;

        // Both dates are null.
        if (getLosEffectiveDate() == null && other.getLosEffectiveDate() == null)
        {
            return 0;
        }

        // This date is null making it larger
        if (getLosEffectiveDate() == null)
        {
            return 1;
        }

        // The other date is null making it larger
        if (other.getLosEffectiveDate() == null)
        {
            return -1;
        }

        // Neither are null
        return other.getLosEffectiveDate().compareTo(getLosEffectiveDate());
    }
	public void setCurrentLOS(boolean currentLOS) {
		this.currentLOS = currentLOS;
	}
	public boolean isCurrentLOS() {
		return currentLOS;
	}
    
}
