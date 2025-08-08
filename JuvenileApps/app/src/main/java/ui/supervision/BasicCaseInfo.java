/*
 * Created on Feb 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision;

import java.util.Date;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BasicCaseInfo implements Comparable{
	
	protected String caseNum;
	protected String court;
	protected Date caseSupPeriodBeginDate;
	protected Date caseSupPeriodEndDate;
	protected String cdi="";
	protected String caseNCourt;
	protected String superviseeName="";
	
	
	
		public String getCdiNCaseNum(){
			return cdi + caseNum;
		}
		
		public String getcaseNCourt(){
			return caseNum +" "+ court;
		}
		
		/**
		 * @return the superviseeName
		 */
		public String getSuperviseeName() {
			return superviseeName;
		}

		/**
		 * @param superviseeName the superviseeName to set
		 */
		public void setSuperviseeName(String superviseeName) {
			this.superviseeName = superviseeName;
		}

		/**
		 * @param caseId The caseId to set.
		 */
		public void setCaseNCourt(String caseNCourt) {
			this.caseNCourt = caseNCourt;
		}
	
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(Object o) {
			if(o!=null){
				BasicCaseInfo myInfo=(BasicCaseInfo)o;
				if(this.caseSupPeriodBeginDate==null){
					if(myInfo.getCaseSupPeriodBeginDate()==null)
						return 0;
					else 
						return -1;
				}
				else{
					if(myInfo.getCaseSupPeriodBeginDate()==null)
						return 1;
					else 
						return -1*caseSupPeriodBeginDate.compareTo(myInfo.getCaseSupPeriodBeginDate());
				}
			}
			else
				return -1;
		}
		
		/**
		 * @return Returns the caseId.
		 */
		public String getCaseNum() {
			return caseNum;
		}
		/**
		 * @param caseId The caseId to set.
		 */
		public void setCaseNum(String caseId) {
			this.caseNum = caseId;
		}
		/**
		 * @return Returns the court.
		 */
		public String getCourt() {
			return court;
		}
		/**
		 * @param court The court to set.
		 */
		public void setCourt(String court) {
			this.court = court;
		}
		/**
		 * @return Returns the caseSupPeriodBeginDate.
		 */
		public Date getCaseSupPeriodBeginDate() {
			return caseSupPeriodBeginDate;
		}
		public String getCaseSupPeriodBeginDateAsStr() {
			if(caseSupPeriodBeginDate==null)
				return "";
			else
				return DateUtil.dateToString(caseSupPeriodBeginDate,UIConstants.DATE_FMT_1);
		}
		/**
		 * @param caseSupPeriodBeginDate The caseSupPeriodBeginDate to set.
		 */
		public void setCaseSupPeriodBeginDate(Date caseSupPeriodBeginDate) {
			this.caseSupPeriodBeginDate = caseSupPeriodBeginDate;
		}
		/**
		 * @return Returns the caseSupPeriodEndDate.
		 */
		public Date getCaseSupPeriodEndDate() {
			return caseSupPeriodEndDate;
		}
		
		public String getCaseSupPeriodEndDateAsStr() {
			if(caseSupPeriodEndDate==null)
				return "";
			else
				return DateUtil.dateToString(caseSupPeriodEndDate,UIConstants.DATE_FMT_1);
		}
		/**
		 * @param caseSupPeriodEndDate The caseSupPeriodEndDate to set.
		 */
		public void setCaseSupPeriodEndDate(Date caseSupPeriodEndDate) {
			this.caseSupPeriodEndDate = caseSupPeriodEndDate;
		}
		
		/**
		 * @return Returns the cdi.
		 */
		public String getCdi() {
			return cdi;
		}
		/**
		 * @param cdi The cdi to set.
		 */
		public void setCdi(String cdi) {
			this.cdi = cdi;
		}
	
}
