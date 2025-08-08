/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.legacyupdates;

import java.util.Date;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateAssignmentDataEvent extends LegacyUpdatesRequestEvent {
    private String cjadNum;
    private String probationOfficerInd;
    private Date assignmentDate;
    private Date transactionDate;
	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	/**
	 * @return the cjadNum
	 */
	public String getCjadNum() {
		return (cjadNum == null)?"":cjadNum;
	}
	/**
	 * @return the probationOfficerInd
	 */
	public String getProbationOfficerInd() {
		return (probationOfficerInd == null)?"":probationOfficerInd;
	}
	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	/**
	 * @param cjadNum the cjadNum to set
	 */
	public void setCjadNum(String cjadNum) {
		this.cjadNum = cjadNum;
	}
	/**
	 * @param probationOfficerInd the probationOfficerInd to set
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
