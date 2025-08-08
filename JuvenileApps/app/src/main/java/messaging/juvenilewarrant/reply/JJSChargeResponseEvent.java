/*
 * Created on Dec 2, 2004
 */
package messaging.juvenilewarrant.reply;

import java.text.SimpleDateFormat;
import java.util.Date;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JJSChargeResponseEvent extends ResponseEvent implements ICode, Comparable {
	private String juvenileNum;

	private String severity;

	private String offense; //allegation

	private String offenseCodeId;

	private String amend;

	private String court;

	private String courtId;

	private Date petitionDate;

	private String referralNum;

	private String petitionNum;

	private String chargeSeqNum;

	private boolean selected;

	//Fields added for updated Assigned Referrals pages
	private String petitionStatus = "";
	private String petitionStatusId ="";
	private String penalCategory = "";
	private String levelDegree = "";
	private String dpsCode = ""; //added DPS code
	
	private Date filedAmendDate;
	private Date terminationDate;
	
	//Added for Common App, since citation source is combined with citation code (aka penalCategory here
	//to make Penal Code
	private String citationSource = "";

	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		
		JJSChargeResponseEvent evt = (JJSChargeResponseEvent)obj;
		if(this.petitionDate==null)
			return 1;
		else if (evt.getPetitionDate()==null){
			return -1;
		}
		return petitionDate.compareTo(evt.getPetitionDate());
	}
	
	/**
	 * @return uniqueId of petitionNum, referralNum, chargeSeqNum
	 */
	public String getUniqueId() {
		return this.petitionNum + this.referralNum + this.chargeSeqNum;
	}

	/**
	 * @return
	 */
	public String getAmend() {
		return amend;
	}

	/**
	 * @return
	 */
	public String getChargeSeqNum() {
		return chargeSeqNum;
	}

	/**
	 * @param amend
	 */
	public void setAmend(String amend) {
		this.amend = amend;
	}

	/**
	 * @param sequenceNum
	 */
	public void setChargeSeqNum(String chargeSeqNum) {
		this.chargeSeqNum = chargeSeqNum;
	}

	/**
	 * @return
	 */
	public boolean isSelected() {
		return this.selected;
	}

	/**
	 * @param b
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getOffense() {
		return offense;
	}

	/**
	 * @return
	 */
	public Date getPetitionDate() {
		return petitionDate;
	}

	public String getPetitionDateFormatted() {
		if (this.petitionDate != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			return formatter.format(this.petitionDate);
		}
		return "";
	}

	/**
	 * @return
	 */
	public String getReferralNum() {
		return referralNum;
	}

	/**
	 * @return
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string) {
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setOffense(String string) {
		offense = string;
	}

	/**
	 * @param date
	 */
	public void setPetitionDate(Date date) {
		petitionDate = date;
	}

	/**
	 * @param string
	 */
	public void setReferralNum(String string) {
		referralNum = string;
	}

	/**
	 * @param string
	 */
	public void setSeverity(String string) {
		severity = string;
	}

	/**
	 * @return
	 */
	public String getCourt() {
		return court;
	}

	/**
	 * @return
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}

	/**
	 * @return
	 */
	public String getPetitionNum() {
		return petitionNum;
	}

	/**
	 * @param string
	 */
	public void setCourt(String string) {
		court = string;
	}

	/**
	 * @param string
	 */
	public void setOffenseCodeId(String string) {
		offenseCodeId = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionNum(String string) {
		petitionNum = string;
	}

	/**
	 * @return
	 */
	public String getCourtId() {
		return courtId;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string) {
		courtId = string;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	public String getCode() {
		return this.getUniqueId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.codetable.reply.ICode#getDescription()
	 */
	public String getDescription() {
		return this.getOffense();
	}

	/**
	 * @return Returns the petitionStatus.
	 */
	public String getPetitionStatus() {
		return petitionStatus;
	}

	/**
	 * @param petitionStatus
	 *            The petitionStatus to set.
	 */
	public void setPetitionStatus(String petitionStatus) {
		this.petitionStatus = petitionStatus;
	}

	/**
	 * @return Returns the penalCategory.
	 */
	public String getPenalCategory() {
		return penalCategory;
	}

	/**
	 * @param penalCategory
	 *            The penalCategory to set.
	 */
	public void setPenalCategory(String penalCategory) {
		this.penalCategory = penalCategory;
	}

	/**
	 * @return Returns the levelDegree.
	 */
	public String getLevelDegree() {
		return levelDegree;
	}

	/**
	 * @param levelDegree
	 *            The levelDegree to set.
	 */
	public void setLevelDegree(String levelDegree) {
		this.levelDegree = levelDegree;
	}
	/**
	 * @return Returns the dpsCode.
	 */
	public String getDpsCode() {
		return dpsCode;
	}

	/**
	 * @param dpsCode
	 *            The dpsCode to set.
	 */
	public void setDpsCode(String dpsCode) {
		this.dpsCode = dpsCode;
	}
	/**
	 * @return Returns the petitionStatusId.
	 */
	public String getPetitionStatusId() {
		return petitionStatusId;
	}
	/**
	 * @param petitionStatusId The petitionStatusId to set.
	 */
	public void setPetitionStatusId(String petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}
	public String getCitationSource() {
		return citationSource;
	}
	public void setCitationSource(String citationSource) {
		this.citationSource = citationSource;
	}

	public Date getFiledAmendDate()
	{
	    return filedAmendDate;
	}

	public void setFiledAmendDate(Date filedAmendDate)
	{
	    this.filedAmendDate = filedAmendDate;
	}

	public Date getTerminationDate()
	{
	    return terminationDate;
	}

	public void setTerminationDate(Date terminationDate)
	{
	    this.terminationDate = terminationDate;
	}
}
