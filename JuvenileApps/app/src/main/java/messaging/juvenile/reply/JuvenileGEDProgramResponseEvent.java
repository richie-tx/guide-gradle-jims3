/*
 * Created on Jun 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cshimek
 *
 */
public class JuvenileGEDProgramResponseEvent extends ResponseEvent implements Comparable
{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private boolean awarded;
	private Date awardedDate;
	private Date completionDate;
	private Date createDate;
	private Date enrollmentDate;
	private String enrollmentStatusCd;
	private String enrollmentStatusDesc;
	private String juvenileNum;
	private String participationCd;
	private String participationDesc;
	private String programCd;
	private String programDesc;
	private String programOtherDesc;
	private Date verificationDate;	
	private boolean gedCompleted;

	/**
	 * @return the awarded
	 */
	public boolean isAwarded() {
		return awarded;
	}

	/**
	 * @param awarded the awarded to set
	 */
	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	/**
	 * @return the awardedDate
	 */
	public Date getAwardedDate() {
		return awardedDate;
	}

	/**
	 * @param awardedDate the awardedDate to set
	 */
	public void setAwardedDate(Date awardedDate) {
		this.awardedDate = awardedDate;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the enrollmentDate
	 */
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	/**
	 * @param enrollmentDate the enrollmentDate to set
	 */
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	/**
	 * @return the enrollmentStatusCd
	 */
	public String getEnrollmentStatusCd() {
		return enrollmentStatusCd;
	}

	/**
	 * @param enrollmentStatusCd the enrollmentStatusCd to set
	 */
	public void setEnrollmentStatusCd(String enrollmentStatusCd) {
		this.enrollmentStatusCd = enrollmentStatusCd;
	}

	/**
	 * @return the enrollmentStatusDesc
	 */
	public String getEnrollmentStatusDesc() {
		return enrollmentStatusDesc;
	}

	/**
	 * @param enrollmentStatusDesc the enrollmentStatusDesc to set
	 */
	public void setEnrollmentStatusDesc(String enrollmentStatusDesc) {
		this.enrollmentStatusDesc = enrollmentStatusDesc;
	}
	
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the participationCd
	 */
	public String getParticipationCd() {
		return participationCd;
	}

	/**
	 * @param participationCd the participationCd to set
	 */
	public void setParticipationCd(String participationCd) {
		this.participationCd = participationCd;
	}

	/**
	 * @return the participationDesc
	 */
	public String getParticipationDesc() {
		return participationDesc;
	}

	/**
	 * @param participationDesc the participationDesc to set
	 */
	public void setParticipationDesc(String participationDesc) {
		this.participationDesc = participationDesc;
	}

	/**
	 * @return the programCd
	 */
	public String getProgramCd() {
		return programCd;
	}

	/**
	 * @param programCd the programCd to set
	 */
	public void setProgramCd(String programCd) {
		this.programCd = programCd;
	}

	/**
	 * @return the programDesc
	 */
	public String getProgramDesc() {
		return programDesc;
	}

	/**
	 * @param programDesc the programDesc to set
	 */
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	/**
	 * @return the programOtherDesc
	 */
	public String getProgramOtherDesc() {
		return programOtherDesc;
	}

	/**
	 * @param programOtherDesc the programOtherDesc to set
	 */
	public void setProgramOtherDesc(String programOtherDesc) {
		this.programOtherDesc = programOtherDesc;
	}

	/**
	 * @return the verificationDate
	 */
	public Date getVerificationDate() {
		return verificationDate;
	}

	/**
	 * @param verificationDate the verificationDate to set
	 */
	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	public boolean isGedCompleted()
	{
	    return gedCompleted;
	}

	public void setGedCompleted(boolean gedCompleted)
	{
	    this.gedCompleted = gedCompleted;
	}

	/** Sorts by enrollment date
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object programObj)
	{
		JuvenileGEDProgramResponseEvent respEvent = (JuvenileGEDProgramResponseEvent) programObj;
		return this.getEnrollmentDate().compareTo(respEvent.getEnrollmentDate());
	}	
}