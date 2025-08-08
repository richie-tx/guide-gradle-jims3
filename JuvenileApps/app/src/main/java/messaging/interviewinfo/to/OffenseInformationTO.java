package messaging.interviewinfo.to;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import messaging.codetable.reply.ICode;

/**
 *
 */
public class OffenseInformationTO extends EntryDateTO implements Comparable
{
	private boolean presentAllegedOffense = false;
	private String presentOffense = "";
	private Date offenseDate;
	private String offenseDateAsString = "";
	private Date courtDate;
	private String courtDateAsString = "";
	private String courtName;
	private String courtCodeId;
	private String judgesName;
	private String petitionAllegation = "";
	private String referralNumber = "";
	private String petitionNumber = "";
	private String jjsmspetitionId = "";
	private String DALogNumber = "";
	private String transactionNumber = "";
	private String seqNum = "";
	private List adultCoActors = new ArrayList();
	//added for Bug 12932 - CRIS Report and Referree Report Transferred Offense Details Need to be Moved
	private String transferredOffenseFromCounty = "";
	private String transferredOffenseOriginatingOff = "";
	private String transferredOffenseAdjuDate;
	private boolean transferredOffensePresent;
	private String amendmentNumber = "";
	//ended
	
	/**
	 * @return Returns the dALogNumber.
	 */
	public String getDALogNumber() {
		return DALogNumber;
	}
	/**
	 * @param logNumber The dALogNumber to set.
	 */
	public void setDALogNumber(String logNumber) {
		DALogNumber = logNumber;
	}
	/**
	 * @return Returns the petitionAllegation.
	 */
	public String getPetitionAllegation() {
		return petitionAllegation;
	}
	/**
	 * @param petitionAllegation The petitionAllegation to set.
	 */
	public void setPetitionAllegation(String petitionAllegation) {
		this.petitionAllegation = petitionAllegation;
	}
	/**
	 * @return Returns the petitionNumber.
	 */
	public String getPetitionNumber() {
		return petitionNumber;
	}
	/**
	 * @param petitionNumber The petitionNumber to set.
	 */
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	/**
	 * @return Returns the presentOffense.
	 */
	public String getPresentOffense() {
		return presentOffense;
	}
	/**
	 * @param presentOffense The presentOffense to set.
	 */
	public void setPresentOffense(String presentOffense) {
		this.presentOffense = presentOffense;
	}
	/**
	 * @return Returns the transactionNumber.
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}
	/**
	 * @param transactionNumber The transactionNumber to set.
	 */
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	/**
	 * @return Returns the adultCoActors.
	 */
	public List getAdultCoActors() {
		return adultCoActors;
	}
	/**
	 * @return Returns the offenseDate.
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}
	/**
	 * @param offenseDate The offenseDate to set.
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	/**
	 * @return the offenseDateAsString
	 */
	public String getOffenseDateAsString() {
		return offenseDateAsString;
	}
	/**
	 * @param offenseDateAsString the offenseDateAsString to set
	 */
	public void setOffenseDateAsString(String offenseDateAsString) {
		this.offenseDateAsString = offenseDateAsString;
	}
	/**
	 * @return Returns the courtDate.
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate The courtDate to set.
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return the courtDateAsString
	 */
	public String getCourtDateAsString() {
		return courtDateAsString;
	}
	/**
	 * @param courtDateAsString the courtDateAsString to set
	 */
	public void setCourtDateAsString(String courtDateAsString) {
		this.courtDateAsString = courtDateAsString;
	}
	/**
	 * @return Returns the courtName.
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName The courtName to set.
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return Returns the judgesName.
	 */
	public String getJudgesName() {
		return judgesName;
	}
	/**
	 * @param judgesName The judgesName to set.
	 */
	public void setJudgesName(String judgesName) {
		this.judgesName = judgesName;
	}
	/**
	 * @return Returns the courtCodeId.
	 */
	public String getCourtCodeId() {
		return courtCodeId;
	}
	/**
	 * @param courtCodeId The courtCodeId to set.
	 */
	public void setCourtCodeId(String courtCodeId) {
		this.courtCodeId = courtCodeId;
	}
	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	public boolean isPresentAllegedOffense() {
		return presentAllegedOffense;
	}
	public void setPresentAllegedOffense(boolean presentAllegedOffense) {
		this.presentAllegedOffense = presentAllegedOffense;
	}
	public String getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	/**
	 * @return the transferredOffenseFromCounty
	 */
	public String getTransferredOffenseFromCounty() {
		return transferredOffenseFromCounty;
	}
	/**
	 * @param transferredOffenseFromCounty the transferredOffenseFromCounty to set
	 */
	public void setTransferredOffenseFromCounty(String transferredOffenseFromCounty) {
		this.transferredOffenseFromCounty = transferredOffenseFromCounty;
	}
	/**
	 * @return the transferredOffenseOriginatingOff
	 */
	public String getTransferredOffenseOriginatingOff() {
		return transferredOffenseOriginatingOff;
	}
	/**
	 * @param transferredOffenseOriginatingOff the transferredOffenseOriginatingOff to set
	 */
	public void setTransferredOffenseOriginatingOff(
			String transferredOffenseOriginatingOff) {
		this.transferredOffenseOriginatingOff = transferredOffenseOriginatingOff;
	}
	/**
	 * @return the transferredOffenseAdjuDate
	 */
	public String getTransferredOffenseAdjuDate() {
		return transferredOffenseAdjuDate;
	}
	/**
	 * @param transferredOffenseAdjuDate the transferredOffenseAdjuDate to set
	 */
	public void setTransferredOffenseAdjuDate(String transferredOffenseAdjuDate) {
		this.transferredOffenseAdjuDate = transferredOffenseAdjuDate;
	}
	/**
	 * @return the isTransferredOffensePresent
	 */
	public boolean isTransferredOffensePresent() {
		return transferredOffensePresent;
	}
	/**
	 * @param isTransferredOffensePresent the isTransferredOffensePresent to set
	 */
	public void setTransferredOffensePresent(boolean transferredOffensePresent) {
		this.transferredOffensePresent = transferredOffensePresent;
	}
	
	
	public String getJjsmspetitionId()
	{
	    return jjsmspetitionId;
	}
	public void setJjsmspetitionId(String jjsmspetitionId)
	{
	    this.jjsmspetitionId = jjsmspetitionId;
	}
	
	public String getAmendmentNumber()
	{
	    return amendmentNumber;
	}
	public void setAmendmentNumber(String amendmentNumber)
	{
	    this.amendmentNumber = amendmentNumber;
	}
	
	public int compareTo(OffenseInformationTO offenseInfo ) throws ClassCastException {
		return this.jjsmspetitionId.compareTo(offenseInfo.getJjsmspetitionId());
	}
	
}
