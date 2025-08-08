package messaging.managesupervisioncase.reply;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.domintf.managesupervisioncase.IUpdateHistory;
import mojo.km.utilities.DateUtil;

/**
 * @author jmcnabb
 * 
 * For displaying the list of Out of County Case update history.
 */
public class OOCCaseUpdateHistoryTO implements IUpdateHistory, Serializable, Comparable {
    private String reasonForUpdate;

    private String updateUserId;

    private Date updateDate;

    private String updateTime;

    private String caseId;

    private IName updateUserName;
    
    public OOCCaseUpdateHistoryTO() {

    }

    /**
     * @return Returns the caseId.
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * @param aCaseId
     *            The caseId to set.
     */
    public void setCaseId(String aCaseId) {
        caseId = aCaseId;
    }

    /**
     * @return Returns the reasonForUpdate.
     */
    public String getReasonForUpdate() {
        return reasonForUpdate;
    }

    /**
     * @param aReasonForUpdate
     *            The reasonForUpdate to set.
     */
    public void setReasonForUpdate(String aReasonForUpdate) {
        reasonForUpdate = aReasonForUpdate;
    }

    /**
     * @return Returns the updateUserId.
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * @param aUserId
     *            The updateUserId to set.
     */
    public void setUpdateUserId(String aUserId) {
        updateUserId = aUserId;
    }

    /**
     * @return Returns the updateUserName.
     */
    public IName getUpdateUserName() {
        return updateUserName;
    }

    /**
     * @param aUserName
     *            The updateUserId to set.
     */
    public void setUpdateUserName(IName aUserName) {
        updateUserName = aUserName;
    }

    /**
     * @return Returns the updateDate.
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param aDate
     *            The updateDate to set.
     */
    public void setUpdateDate(Date aDate) {
        updateDate = aDate;
    }

    /**
     * @return Returns the updateTime.
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * @param aTime
     *            The updateTime to set.
     */
    public void setUpdateTime(String aTime) {
        updateTime = aTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object aCase) {
        final String dateFormatTime = "hh:mm:ss";
        //final String dateFormat
        if (aCase instanceof OOCCaseUpdateHistoryTO) {
            OOCCaseUpdateHistoryTO history = (OOCCaseUpdateHistoryTO) aCase;

            // Use update date/time for comparison
            
            //Date myUpdateTimestamp =
            // DateUtil.convertStringDateTimeToDate(DateUtil.convertDateToYYYYMMDD(getUpdateDate()),
            // stripOutColons(getUpdateTime()));

            int month = new Integer(DateUtil.dateToString(this.getUpdateDate(),"MM")).intValue();
            int day = new Integer(DateUtil.dateToString(this.getUpdateDate(), "dd")).intValue();
            int year = new Integer(DateUtil.dateToString(this.getUpdateDate(),"yyyy")).intValue();
            
            Date aDate = DateUtil.stringToDate(getUpdateTime(), dateFormatTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(aDate);
            cal.set(Calendar.MONTH,month);
            cal.set(Calendar.DAY_OF_MONTH, month);
            cal.set(Calendar.YEAR, year);
            Date myUpdateTimestamp = cal.getTime();

            //Date anotherUpdateTimestamp = DateUtil.convertStringDateTimeToDate(DateUtil.convertDateToYYYYMMDD(history
            //        .getUpdateDate()), stripOutColons(history.getUpdateTime()));
            //Date anotherUpdateTimestamp = DateUtil.stringToDate(history.getUpdateTime(), dateFormatTime);
            month = new Integer(DateUtil.dateToString(history.getUpdateDate(),"MM")).intValue();
            day = new Integer(DateUtil.dateToString(history.getUpdateDate(), "dd")).intValue();
            year = new Integer(DateUtil.dateToString(history.getUpdateDate(),"yyyy")).intValue();
            
            aDate = DateUtil.stringToDate(history.getUpdateTime(), dateFormatTime);
            cal.setTime(aDate);
            cal.set(Calendar.MONTH,month);
            cal.set(Calendar.DAY_OF_MONTH, month);
            cal.set(Calendar.YEAR, year);
            Date anotherUpdateTimestamp = cal.getTime();
           
            return myUpdateTimestamp.compareTo(anotherUpdateTimestamp);
        } else {
            throw new IllegalArgumentException("Must compare to another OOCCaseUpdateHistoryTO object.");
        }
    }

    private String stripOutColons(String inTimeHHMMSS) {
        StringBuffer outTimeHHMMSS = new StringBuffer();

        String hours = inTimeHHMMSS.substring(0, 2);
        String minutes = inTimeHHMMSS.substring(3, 5);
        String seconds = inTimeHHMMSS.substring(6, 8);

        outTimeHHMMSS.append(hours);
        outTimeHHMMSS.append(minutes);
        outTimeHHMMSS.append(seconds);

        return outTimeHHMMSS.toString();
    }

	public String getDispositionTypeId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getTransferDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDispositionTypeId(String dispositionTypeId) {
		// TODO Auto-generated method stub
		
	}

	public void setTransferDate(Date transferDate) {
		// TODO Auto-generated method stub
		
	}
}
