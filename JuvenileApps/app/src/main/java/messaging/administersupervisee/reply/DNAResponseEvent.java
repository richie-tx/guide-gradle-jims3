/**
 * 
 */
package messaging.administersupervisee.reply;

import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dwilliamson
 *
 */
public class DNAResponseEvent extends ResponseEvent implements Comparable {
	
	private Date dnaCollectedDate;
	private boolean dnaFlagInd; 
	private Date entryDate;
	private String superviseeHistoryId;
	private String userName;
	/**
	 * @return the dnaCollectedDate
	 */
	public Date getDnaCollectedDate() {
		return dnaCollectedDate;
	}
	/**
	 * @param dnaCollectedDate the dnaCollectedDate to set
	 */
	public void setDnaCollectedDate(Date dnaCollectedDate) {
		this.dnaCollectedDate = dnaCollectedDate;
	}
	/**
	 * @return the dnaFlagInd
	 */
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}
	/**
	 * @param dnaFlagInd the dnaFlagInd to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}
	
	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	/**
	 * @return the superviseeHistoryId
	 */
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}
	/**
	 * @param superviseeHistoryId the superviseeHistoryId to set
	 */
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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

        DNAResponseEvent other = (DNAResponseEvent) obj;

        // Both dates are null.
        if (getDnaCollectedDate() == null && other.getDnaCollectedDate() == null)
        {
            return 0;
        }

        // This date is null making it larger
        if (getDnaCollectedDate() == null)
        {
            return 1;
        }

        // The other date is null making it larger
        if (other.getDnaCollectedDate() == null)
        {
            return -1;
        }

        // Neither are null
        return other.getDnaCollectedDate().compareTo(getDnaCollectedDate());
    }
    
	/**
	 * sort based on the oid
	 */
	public static Comparator DNAResponseEventOidComparator = new Comparator() {
		public int compare(Object dnaResponseEventVersion, Object previousDnaResponseEventVersion){
		
		if(dnaResponseEventVersion == null || previousDnaResponseEventVersion == null || !(dnaResponseEventVersion instanceof DNAResponseEvent) 
				||  !(previousDnaResponseEventVersion instanceof DNAResponseEvent))
		{
			return 0;
		}
		DNAResponseEvent dnaResponseEvent = (DNAResponseEvent)dnaResponseEventVersion;
		DNAResponseEvent previousDnaResponseEvent = (DNAResponseEvent)previousDnaResponseEventVersion;

		return previousDnaResponseEvent.getSuperviseeHistoryId().compareTo(dnaResponseEvent.getSuperviseeHistoryId());
		}
	};
    
	
}
