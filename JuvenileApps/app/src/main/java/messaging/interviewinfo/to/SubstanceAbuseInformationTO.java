package messaging.interviewinfo.to;


/**
 *
 */
public class SubstanceAbuseInformationTO extends EntryDateTO 
{
	private int onsetAge = 0;
	private String drugType = "";
	private String frequency = "";
	private String locationOfUse = "";
	private String amountSpent = "";
	private String degree = "";
	
	
	/**
	 * @return Returns the amountSpent.
	 */
	public String getAmountSpent() {
		return amountSpent;
	}
	/**
	 * @param amountSpent The amountSpent to set.
	 */
	public void setAmountSpent(String amountSpent) {
		this.amountSpent = amountSpent;
	}
	/**
	 * @return Returns the degree.
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree The degree to set.
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	/**
	 * @return Returns the drugType.
	 */
	public String getDrugType() {
		return drugType;
	}
	/**
	 * @param drugType The drugType to set.
	 */
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	/**
	 * @return Returns the frequency.
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency The frequency to set.
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return Returns the locationOfUse.
	 */
	public String getLocationOfUse() {
		return locationOfUse;
	}
	/**
	 * @param locationOfUse The locationOfUse to set.
	 */
	public void setLocationOfUse(String locationOfUse) {
		this.locationOfUse = locationOfUse;
	}
	/**
	 * @return Returns the onsetAge.
	 */
	public int getOnsetAge() {
		return onsetAge;
	}
	/**
	 * @param onsetAge The onsetAge to set.
	 */
	public void setOnsetAge(int onsetAge) {
		this.onsetAge = onsetAge;
	}
}
