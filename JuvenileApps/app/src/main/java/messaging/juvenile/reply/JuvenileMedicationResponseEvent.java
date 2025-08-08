/*
 * Created on Apr 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JuvenileMedicationResponseEvent extends ResponseEvent implements Comparable {

	private String medicationId;

	private String medicationTypeId;

	private Date entryDate;

	//private boolean currentlyTakingMedication; //commented to get rid of radio button and introduce dropdown
	private String currentlyTakingMedication; 
	private String currentlyTakingMedicationFull; 
    private String modificationReason; 
	private String dosage;

	private String juvenileNum;

	private String reasonForMedication;

	private String frequencyId;

	private String physicianName;

	private String physicianPhoneNum;
	
	private String medicationName;

	/**
	 * @param _service
	 *//*
	public JuvenileMedicationResponseEvent(String _service) {
		super(_service);
		// TODO Auto-generated constructor stub
	}*/

	/**
	 *  
	 */
	public JuvenileMedicationResponseEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the currentlyTakingMedication.
	 */
	public String getCurrentlyTakingMedication() {
		return currentlyTakingMedication;
	}

	/**
	 * @param currentlyTakingMedication
	 *            The currentlyTakingMedication to set.
	 */
	public void setCurrentlyTakingMedication(String currentlyTakingMedication) {
		this.currentlyTakingMedication = currentlyTakingMedication;
	}

	/**
	 * @return the currentlyTakingMedicationFull
	 */
	public String getCurrentlyTakingMedicationFull() {
		return currentlyTakingMedicationFull;
	}

	/**
	 * @param currentlyTakingMedicationFull the currentlyTakingMedicationFull to set
	 */
	public void setCurrentlyTakingMedicationFull(
			String currentlyTakingMedicationFull) {
		this.currentlyTakingMedicationFull = currentlyTakingMedicationFull;
	}

	/**
	 * @return Returns the dosage.
	 */
	public String getDosage() {
		return dosage;
	}

	/**
	 * @param dosage
	 *            The dosage to set.
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return Returns the medicationId.
	 */
	public String getMedicationId() {
		return medicationId;
	}

	/**
	 * @param medicationId
	 *            The medicationId to set.
	 */
	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
	}

	/**
	 * @return Returns the medicationTypeId.
	 */
	public String getMedicationTypeId() {
		return medicationTypeId;
	}

	/**
	 * @param medicationTypeId
	 *            The medicationTypeId to set.
	 */
	public void setMedicationTypeId(String medicationTypeId) {
		this.medicationTypeId = medicationTypeId;
	}

	/**
	 * @return Returns the frequencyId.
	 */
	public String getFrequencyId() {
		return frequencyId;
	}

	/**
	 * @param frequencyId
	 *            The frequencyId to set.
	 */
	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	/**
	 * @return Returns the physicianName.
	 */
	public String getPhysicianName() {
		return physicianName;
	}

	/**
	 * @param physicianName
	 *            The physicianName to set.
	 */
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	/**
	 * @return Returns the physicianPhoneNum.
	 */
	public String getPhysicianPhoneNum() {
		return physicianPhoneNum;
	}

	/**
	 * @param physicianPhoneNum
	 *            The physicianPhoneNum to set.
	 */
	public void setPhysicianPhoneNum(String physicianPhoneNum) {
		this.physicianPhoneNum = physicianPhoneNum;
	}

	/**
	 * @return Returns the reasonForMedication.
	 */
	public String getReasonForMedication() {
		return reasonForMedication;
	}

	/**
	 * @param reasonForMedication
	 *            The reasonForMedication to set.
	 */
	public void setReasonForMedication(String reasonForMedication) {
		this.reasonForMedication = reasonForMedication;
	}

	/**
	 * @return the modificationReason
	 */
	public String getModificationReason() {
		return modificationReason;
	}

	/**
	 * @param modificationReason the modificationReason to set
	 */
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}

	/**
	 * @return Returns the medicationName.
	 */
	public String getMedicationName() {
		return medicationName;
	}
	/**
	 * @param medicationName The medicationName to set.
	 */
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		JuvenileMedicationResponseEvent evt = (JuvenileMedicationResponseEvent)obj;
		return evt.getEntryDate().compareTo(entryDate);
	}

}
