// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\CreateMedicalMedicationEvent.java

package messaging.juvenile;

import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.messaging.RequestEvent;

public class CreateMedicalMedicationEvent extends RequestEvent {
	public String juvenileNum;

	public String medicationTypeId;

	public String medicationReason;
	public String modificationReason; //added a new field

	//public boolean currentlyTaking;

	public String dosage;

	public IName physicianName;

	public IPhoneNumber physicianPhone;

	public String frequencyId;
	
	public String currentlyTakingMedId; //added new for dropdown
	public String medicationListId; //added new for update funtionality

	public Date entryDate;

	/**
	 * @roseuid 462CE3AC00FE
	 */
	public CreateMedicalMedicationEvent() {

	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @return Returns the currentlyTaking.
	 *//*
	public boolean isCurrentlyTaking() {
		return currentlyTaking;
	}

	*//**
	 * @roseuid 462CE3AC00FE
	 * @param currentlyTaking
	 *            The currentlyTaking to set.
	 *//*
	public void setCurrentlyTaking(boolean currentlyTaking) {
		this.currentlyTaking = currentlyTaking;
	}*/

	/**
	 * @roseuid 462CE3AC00FE
	 * @return Returns the dosage.
	 */
	public String getDosage() {
		return dosage;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @param dosage
	 *            The dosage to set.
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @return Returns the frequencyId.
	 */
	public String getFrequencyId() {
		return frequencyId;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @param frequencyId
	 *            The frequencyId to set.
	 */
	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	/**
	 * @return the currentlyTakingMedId
	 */
	public String getCurrentlyTakingMedId() {
		return currentlyTakingMedId;
	}

	/**
	 * @param currentlyTakingMedId the currentlyTakingMedId to set
	 */
	public void setCurrentlyTakingMedId(String currentlyTakingMedId) {
		this.currentlyTakingMedId = currentlyTakingMedId;
	}

	/**
	 * @return the medicationListId
	 */
	public String getMedicationListId() {
		return medicationListId;
	}

	/**
	 * @param medicationListId the medicationListId to set
	 */
	public void setMedicationListId(String medicationListId) {
		this.medicationListId = medicationListId;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
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
	 * @roseuid 462CE3AC00FE
	 * @return Returns the medicationReason.
	 */
	public String getMedicationReason() {
		return medicationReason;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @param medicationReason
	 *            The medicationReason to set.
	 */
	public void setMedicationReason(String medicationReason) {
		this.medicationReason = medicationReason;
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
	 * @roseuid 462CE3AC00FE
	 * @return Returns the physicianPhone.
	 */
	public String getPhysicianPhone() {
		return physicianPhone.getPhoneNumber();
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @param physicianPhone
	 *            The physicianPhone to set.
	 */
	public void setPhysicianPhone(IPhoneNumber physicianPhone) {
		this.physicianPhone = physicianPhone;
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @return Returns the physicianName.
	 */
	public String getPhysicianName() {
		return physicianName.getFormattedName();
	}

	/**
	 * @roseuid 462CE3AC00FE
	 * @param physicianName
	 *            The physicianName to set.
	 */
	public void setPhysicianName(IName physicianName) {
		this.physicianName = physicianName;
	}
}
