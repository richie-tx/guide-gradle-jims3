

package messaging.supervisionorder.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

/**
 * 
 *  
 */
public class SupervisionOrderStartEndDatesResponseEvent extends ResponseEvent{
		
	

	private Date caseSupervisionBeginDate=null; 

	private Date caseSupervisionEndDate=null;

	
	
	public Date getCaseSupervisionBeginDate() {
		return caseSupervisionBeginDate;
	}


	public void setCaseSupervisionBeginDate(Date caseSupervisionBeginDate) {
		this.caseSupervisionBeginDate = caseSupervisionBeginDate;
	}


	public Date getCaseSupervisionEndDate() {
		return caseSupervisionEndDate;
	}


	public void setCaseSupervisionEndDate(Date caseSupervisionEndDate) {
		this.caseSupervisionEndDate = caseSupervisionEndDate;}


  

	/**
     * @return
     */
    public String getCaseSupervisionBeginDateAsString() {
        if (caseSupervisionBeginDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(caseSupervisionBeginDate, UIConstants.DATE_FMT_1);
        }
    }



	/**
     * @return
     */
    public String getCaseSupervisionEndDateAsString() {
        if (caseSupervisionEndDate == null) {
            return "";
        } else {
            return DateUtil.dateToString(caseSupervisionEndDate, UIConstants.DATE_FMT_1);
        }
    }
	
	
}