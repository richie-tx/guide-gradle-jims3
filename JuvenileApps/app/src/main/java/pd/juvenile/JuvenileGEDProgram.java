package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.GetJuvenileGEDProgramEvent;
import messaging.juvenile.reply.JuvenileGEDProgramResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileGEDProgram extends PersistentObject 
{
	
	private boolean gedAwarded;
	
	private Date   gedAwardedDate;
	
	private Date   enrollmentDate;		 
	
	private Date   verifiedDate;	
	
	private String enrollmentStatusCd;
	
	private String participationLevelCd;
	
	private Date   completionDate;
	
	private String gedProgramCd;
	
	private String otherProgramDesc;
	
	private String juvenileNum;
	
	private Date   createDate;
	
	private boolean gedCompleted;
	
	/**
	 * @roseuid 42B062E7022B
	 */
	public JuvenileGEDProgram()
	{
	}

	/**
	 * @return the gedAwarded
	 */
	public boolean isGedAwarded() {
		fetch();
		return gedAwarded;
	}

	/**
	 * @return the gedtAwardedDate
	 */
	public Date getGedAwardedDate() {
		fetch();
		return gedAwardedDate;
	}

	/**
	 * @return the enrollmentDate
	 */
	public Date getEnrollmentDate() {
		fetch();
		return enrollmentDate;
	}

	/**
	 * @return the enrolledStatusCd
	 */
	public String getEnrollmentStatusCd() {
		fetch();
		return enrollmentStatusCd;
	}

	/**
	 * @return the participationLevelCd
	 */
	public String getParticipationLevelCd() {
		fetch();
		return participationLevelCd;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		fetch();
		return completionDate;
	}

	/**
	 * @return the gedProgramCd
	 */
	public String getGedProgramCd() {
		fetch();
		return gedProgramCd;
	}

	/**
	 * @return the otherProgramDesc
	 */
	public String getOtherProgramDesc() {
		fetch();
		return otherProgramDesc;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @return the current value of the verifiedDate property
	 */
	public Date getVerifiedDate()
	{
		fetch();
		return verifiedDate;
	}

	/**
	 * @param gedAwarded the gedAwarded to set
	 */
	public void setGedAwarded(boolean gedAwarded) {
		if (this.gedAwarded != gedAwarded) {
			markModified();
		}
		this.gedAwarded = gedAwarded;
	}

	/**
	 * @param gedAwardedDate the gedAwardedDate to set
	 */
	public void setGedAwardedDate(Date gedAwardedDate) {
		if (this.gedAwardedDate == null || !this.gedAwardedDate.equals(gedAwardedDate))
		{
			markModified();
		}
		this.gedAwardedDate = gedAwardedDate;
	}

	/**
	 * @param enrollmentDate the enrollmentDate to set
	 */
	public void setEnrollmentDate(Date enrollmentDate) {
		if (this.enrollmentDate == null || !this.enrollmentDate.equals(enrollmentDate))
		{
			markModified();
		}
		this.enrollmentDate = enrollmentDate;
	}

	/**
	 * @param verifiedDate the verifiedDate to set
	 */
	public void setVerifiedDate(Date verifiedDate) {
		if (this.verifiedDate == null || !this.verifiedDate.equals(verifiedDate))
		{
			markModified();
		}
		this.verifiedDate = verifiedDate;
	}

	/**
	 * @param enrolledStatusCd the enrolledStatusCd to set
	 */
	public void setEnrollmentStatusCd(String enrollmentStatusCd) {
		if (this.enrollmentStatusCd == null || !this.enrollmentStatusCd.equals(enrollmentStatusCd))
		{
			markModified();
		}
		this.enrollmentStatusCd = enrollmentStatusCd;
	}

	/**
	 * @param participationCd the participationLevelCd to set
	 */
	public void setParticipationLevelCd(String participationLevelCd) {
		if (this.participationLevelCd == null || !this.participationLevelCd.equals(participationLevelCd))
		{
			markModified();
		}
		this.participationLevelCd = participationLevelCd;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		if (this.completionDate == null || !this.completionDate.equals(completionDate))
		{
			markModified();
		}
		this.completionDate = completionDate;
	}

	/**
	 * @param gedProgramCd the gedProgramCd to set
	 */
	public void setGedProgramCd(String gedProgramCd) {
		if (this.gedProgramCd == null || !this.gedProgramCd.equals(gedProgramCd))
		{
			markModified();
		}
		this.gedProgramCd = gedProgramCd;
	}

	/**
	 * @param otherProgramDesc the otherProgramDesc to set
	 */
	public void setOtherProgramDesc(String otherProgramDesc) {
		if (this.otherProgramDesc == null || !this.otherProgramDesc.equals(otherProgramDesc))
		{
			markModified();
		}
		this.otherProgramDesc = otherProgramDesc;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		if (this.createDate == null || !this.createDate.equals(createDate))
		{
			markModified();
		}
		this.createDate = createDate;
	}

	/**
	 * @return
	 * @param JuvenileGEGProgram
	 */
	static public JuvenileGEDProgram find(String getProgramId)
	{
		return (JuvenileGEDProgram) new Home().find(getProgramId, JuvenileGEDProgram.class);
	}

	/**
	 * @return
	 * @param schoolEvent
	 */
	static public Iterator findJuvenileGEDProgram(GetJuvenileGEDProgramEvent gedEvent)
	{
		IHome home = new Home();
		return home.findAll(gedEvent, JuvenileGEDProgram.class);
	}

	public boolean isGedCompleted()
	{
	    fetch();
	    return gedCompleted;
	}

	public void setGedCompleted(boolean gedCompleted)
	{
	    if (this.gedCompleted != gedCompleted) {
		markModified();
	}
	    this.gedCompleted = gedCompleted;
	}

	/**
	 * @return
	 */
	public JuvenileGEDProgramResponseEvent getValueObject()
	{
		JuvenileGEDProgramResponseEvent event = new JuvenileGEDProgramResponseEvent();
		event.setAwarded(this.isGedAwarded());
		event.setAwardedDate(this.getGedAwardedDate());
		event.setCompletionDate(this.getCompletionDate());
		event.setEnrollmentDate(this.getEnrollmentDate());
		event.setEnrollmentStatusCd(this.getEnrollmentStatusCd());
		event.setJuvenileNum(this.getJuvenileNum());
		event.setParticipationCd(this.getParticipationLevelCd());
		event.setProgramCd(this.getGedProgramCd());
		event.setProgramOtherDesc(this.getOtherProgramDesc());
		event.setVerificationDate(this.getVerifiedDate());
		event.setGedCompleted(this.isGedCompleted());
		event.setCreateDate(this.getCreateDate());
		return event;
	}

	/**
	 * @param saveEvent
	 */
	public void hydrate(JuvenileGEDProgramResponseEvent gedEvent)
	{
		this.setCompletionDate(gedEvent.getCompletionDate());
		this.setEnrollmentStatusCd(gedEvent.getEnrollmentStatusCd());
		this.setEnrollmentDate(gedEvent.getEnrollmentDate());
		this.setGedAwarded(gedEvent.isAwarded());
		this.setGedAwardedDate(gedEvent.getAwardedDate());
		this.setGedProgramCd(gedEvent.getProgramCd());
		this.setJuvenileNum(gedEvent.getJuvenileNum());
		this.setOtherProgramDesc(gedEvent.getProgramOtherDesc());
		this.setParticipationLevelCd(gedEvent.getParticipationCd());
		this.setVerifiedDate(gedEvent.getVerificationDate());
		this.setGedCompleted( gedEvent.isGedCompleted());
	}
}	