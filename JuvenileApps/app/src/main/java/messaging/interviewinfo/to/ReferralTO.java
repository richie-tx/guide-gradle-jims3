package messaging.interviewinfo.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;

/**
 *
 */
public class ReferralTO extends ExcludedTO implements Comparable 
{	
	private List offenses = new ArrayList(); //OffenseInformationTO
	
	private String courtDisposition;
	private Date dispositionDate;		// disposition date or intake date
	private Date probationEndDate;     // Task 36529
	private String probationEndDateString;   // Task 36529 
	

	private Date intakeDate;
	private String intakeDateString;
	//intakeDecision is in JuvenileCasefileReferralsResponseEvent
	JuvenileCasefileReferralsResponseEvent referralData;
	
	//For now, lets say there's only 1 petition for each referral
	private String petitionAllegation;
	private Date petitionFileDate;
	private boolean petitionAvailable;
	private String referralNumber;
	
	//added for Bug 12932 - CRIS Report and Referree Report Transferred Offense Details Need to be Moved and stay with the corresponding present or previous referral
	private String transferredOffenseFromCounty = "";
	private String transferredOffenseOriginatingOff = "";
	private String transferredOffenseAdjuDate;
	private boolean transferredOffensePresent;
	
	//added for Task 37996; User story 11077 : CRIS Report Speciality Supervision Type
	private String supervisionCategoryId="";
	private String activationDate;
	private String closingDate;
	private String supervisionOutcomeDescriptionId="";
	private String supervisionOutcome="";
	
	
	
	
	


	public ReferralTO()
	{
		referralData = new JuvenileCasefileReferralsResponseEvent();
	}
	
	public String getReferralNumber() {
		referralNumber = "";
		if (referralData != null) {
			referralNumber = referralData.getReferralNumber();
		} 
		return referralNumber;
	}
	
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	
	/**
	 * @return Returns the referralData.
	 */
	public JuvenileCasefileReferralsResponseEvent getReferralData() {
		return referralData;
	}
	/**
	 * @param referralData The referralData to set.
	 */
	public void setReferralData(JuvenileCasefileReferralsResponseEvent referralData) {
		this.referralData = referralData;
	}
	
	/**
	 * @return Returns the dispositionDate.
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}
	/**
	 * @param dispositionDate The dispositionDate to set.
	 */
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	/**
	 * @return Returns the offenses.
	 */
	public List getOffenses() {
		return offenses;
	}
	/**
	 * @param offenses The offenses to set.
	 */
	public void setOffenses(List offenses) {
		this.offenses = offenses;
	}
	/**
	 * @return Returns the courtDisposition.
	 */
	public String getCourtDisposition() {
		return courtDisposition;
	}
	/**
	 * @param courtDisposition The courtDisposition to set.
	 */
	public void setCourtDisposition(String courtDisposition) {
		this.courtDisposition = courtDisposition;
	}
	/**
	 * @return Returns the intakeDate.
	 */
	public Date getIntakeDate() {
		return intakeDate;
	}
	/**
	 * @param intakeDate The intakeDate to set.
	 */
	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}
	/**
	 * @return the intakeDateString
	 */
	public String getIntakeDateString() {
		return intakeDateString;
	}

	
	public Date getProbationEndDate() {
		return probationEndDate;
	}

	public void setProbationEndDate(Date probationEndDate) {
		this.probationEndDate = probationEndDate;
	}
	/**
	 * @param intakeDateString the intakeDateString to set
	 */
	public void setIntakeDateString(String intakeDateString) {
		this.intakeDateString = intakeDateString;
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
	 * @return Returns the petitionFileDate.
	 */
	public Date getPetitionFileDate() {
		return petitionFileDate;
	}
	/**
	 * @param petitionFileDate The petitionFileDate to set.
	 */
	public void setPetitionFileDate(Date petitionFileDate) {
		this.petitionFileDate = petitionFileDate;
	}
	
	
	/**
	 * @return Returns the petitionAvailable.
	 */
	public boolean isPetitionAvailable() {
		return petitionAvailable;
	}
	/**
	 * @param petitionAvailable The petitionAvailable to set.
	 */
	public void setPetitionAvailable(boolean petitionAvailable) {
		this.petitionAvailable = petitionAvailable;
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
	 * @return the transferredOffensePresent
	 */
	public boolean isTransferredOffensePresent() {
		return transferredOffensePresent;
	}

	/**
	 * @param transferredOffensePresent the transferredOffensePresent to set
	 */
	public void setTransferredOffensePresent(boolean transferredOffensePresent) {
		this.transferredOffensePresent = transferredOffensePresent;
	}

	public int compareTo( Object obj )
	{
		int result = 0;
		
		if ( obj != null && ! (obj instanceof ReferralTO) )
		{
			throw new IllegalArgumentException( "'obj' must be of type EntryDateTO." );
		}
		
		Date input = ((ReferralTO)obj).getReferralData().getReferralDate();
		
		if ( getReferralData().getReferralDate() == null && input == null)
		{
			result = 0;
		}
		else if(getReferralData().getReferralDate() == null)
		{
			result = -1;
		}
		else if(input == null)
		{
			result = 1;
		}
		else
		{
			result = getReferralData().getReferralDate().compareTo(input);
		}
		
		result = -result;
			
			
		
		return result;  
	}

	public String getProbationEndDateString() {
		return probationEndDateString;
	}

	public void setProbationEndDateString(String probationEndDateString) {
		this.probationEndDateString = probationEndDateString;
	}

	public String getSupervisionOutcome() {
		return supervisionOutcome;
	}

	public void setSupervisionOutcome(String supervisionOutcome) {
		this.supervisionOutcome = supervisionOutcome;
	}

	public String getSupervisionCategoryId() {
		return supervisionCategoryId;
	}

	public void setSupervisionCategoryId(String supervisionCategoryId) {
		this.supervisionCategoryId = supervisionCategoryId;
	}

	public String getSupervisionOutcomeDescriptionId() {
		return supervisionOutcomeDescriptionId;
	}

	public void setSupervisionOutcomeDescriptionId(
			String supervisionOutcomeDescriptionId) {
		this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public String getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	
}
