//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\UpdateSuperviseeSignatureEvent.java

package messaging.supervisionorder;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateSuperviseeSignatureEvent extends RequestEvent 
{
	// supervisionOrderId, signedDate and defendantSignatureInd
	private boolean defendantSignatureInd;
	private String supervisionOrderId;
	private Date signedDate;
	private String userLogonId;
	private boolean userIsCLO=false;
	private String casenum;
	private String cdi;
	private Date judgeSignedDate;
   
   /**
    * @roseuid 4421688D0136
    */
   public UpdateSuperviseeSignatureEvent() 
   {
    
   }
	
	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}


	/**
	 * @param string
	 */
	public void setSupervisionOrderId(String string)
	{
		supervisionOrderId = string;
	}

	/**
	 * @return
	 */
	public boolean isDefendantSignatureInd()
	{
		return defendantSignatureInd;
	}

	/**
	 * @param b
	 */
	public void setDefendantSignatureInd(boolean b)
	{
		defendantSignatureInd = b;
	}

	/**
	 * @return
	 */
	public Date getSignedDate()
	{
		return signedDate;
	}

	/**
	 * @param date
	 */
	public void setSignedDate(Date date)
	{
		signedDate = date;
	}

	public void setJudgeSignedDate(Date judgeSignedDate) {
		this.judgeSignedDate = judgeSignedDate;
	}

	public Date getJudgeSignedDate() {
		return judgeSignedDate;
	}

	/**
	 * @return Returns the userLogonId.
	 */
	public String getUserLogonId() {
		return userLogonId;
	}
	/**
	 * @param userLogonId The userLogonId to set.
	 */
	public void setUserLogonId(String userLogonId) {
		this.userLogonId = userLogonId;
	}
	/**
	 * @return Returns the userIsCLO.
	 */
	public boolean isUserIsCLO() {
		return userIsCLO;
	}
	/**
	 * @param userIsCSO The userIsCSO to set.
	 */
	public void setUserIsCLO(boolean userIsCLO) {
		this.userIsCLO = userIsCLO;
	}
	/**
	 * @return Returns the casenum.
	 */
	public String getCasenum() {
		return casenum;
	}
	/**
	 * @param casenum The casenum to set.
	 */
	public void setCasenum(String casenum) {
		this.casenum = casenum;
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
