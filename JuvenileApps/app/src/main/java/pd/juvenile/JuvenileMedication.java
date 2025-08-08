package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * 
 * @roseuid 462D056A0360
 */
public class JuvenileMedication extends PersistentObject
{
	private boolean currentlyTakingMedication;
	private String dosage;
	private String physicianName;
//	private String physicianLastName;
//	private String physicianMiddleName;
//	private String physicianFirstName;
	private String physicianPhoneNum;
	private String reasonForMedication;
	private String modificationReason;
	private JuvenileMedication[] medications;
	/**
	 * Properties for medication
	 */
	private Code medication = null;
	/**
	 * Properties for frequency
	 */
	private Code frequency = null;
	private String medicationId;
	private String medicationTypeId;
	private String frequencyId;
	private Date entryDate;
	private String juvenileNum;
	
	//for currently taking medicine
	private Code currentlyTakingMed = null;
	private String currentlyTakingMedId;
	/**
	 * 
	 * @roseuid 462D056A0360
	 */
	public JuvenileMedication()
	{
	}

	/**
	 * @param event
	 * @return
	 * @roseuid 45AF7A0A0190
	 */
	static public JuvenileMedication find(String oid)
	{
		IHome home = new Home();
		JuvenileMedication juvenileMedication = (JuvenileMedication) home.find(oid, JuvenileMedication.class);
		return juvenileMedication;
	}
	/**
	 * @param event
	 * @return
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileMedication.class);
	}
	
	/**
	 * 
	 * @return Returns the medicationId.
	 */
	public String getMedicationId()
	{
		fetch();
		return medicationId;
	}
	
	/**
	 * 
	 * @param medicationId The medicationId to set.
	 */
	public void setMedicationId(String medicationId)
	{
		if (this.medicationId == null || !this.medicationId.equals(medicationId))
		{
			markModified();
		}
		this.medicationId = medicationId;
	}
	
	/**
	 * 
	 * @return Returns the dosage.
	 */
	public String getDosage()
	{
		fetch();
		return dosage;
	}

	/**
	 * 
	 * @param dosage The dosage to set.
	 */
	public void setDosage(String dosage)
	{
		if (this.dosage == null || !this.dosage.equals(dosage))
		{
			markModified();
		}
		this.dosage = dosage;
	}

	/**
	 * 
	 * @return Returns the frequency.
	 */
	public Code getFrequency()
	{
		initFrequency();
		return frequency;
	}

	/**
	 * 
	 * @param frequency The frequency to set.
	 */
	public void setFrequency(Code frequency)
	{
		if (this.frequency == null || !this.frequency.equals(frequency))
		{
			markModified();
		}
		this.frequency = frequency;
	}

	/**
	 * 
	 * @return Returns the frequencyId.
	 */
	public String getFrequencyId()
	{
		fetch();
		return frequencyId;
	}

	/**
	 * 
	 * @param frequencyId The frequencyId to set.
	 */
	public void setFrequencyId(String frequencyId)
	{
		if (this.frequencyId == null || !this.frequencyId.equals(frequencyId))
		{
			markModified();
		}
		this.frequencyId = frequencyId;
	}

	

	/**
	 * 
	 * @return Returns the currentlyTakingMed.
	 */
	public Code getCurrentlyTakingMed()
	{
		initCurrentlyTakingMed();
		return currentlyTakingMed;
	}

	/**
	 * 
	 * @param currentlyTakingMed The currentlyTakingMed to set.
	 */
	public void setcurrentlyTakingMed(Code currentlyTakingMed)
	{
		if (this.currentlyTakingMed == null || !this.currentlyTakingMed.equals(currentlyTakingMed))
		{
			markModified();
		}
		this.currentlyTakingMed = currentlyTakingMed;
	}

	/**
	 * 
	 * @return Returns the currentlyTakingMedId.
	 */
	public String getCurrentlyTakingMedId()
	{
		fetch();
		return currentlyTakingMedId;
	}

	/**
	 * 
	 * @param currentlyTakingMedId The currentlyTakingMedId to set.
	 */
	public void setCurrentlyTakingMedId(String currentlyTakingMedId)
	{
		if (this.currentlyTakingMedId == null || !this.currentlyTakingMedId.equals(currentlyTakingMedId))
		{
			markModified();
		}
		this.currentlyTakingMedId = currentlyTakingMedId;
	}

	/**
	 * 
	 * @return Returns the isCurrentlyTakingMedication.
	 */
	public boolean isCurrentlyTakingMedication()
	{
		fetch();
		return currentlyTakingMedication;
	}

	/**
	 * 
	 * @param isCurrentlyTakingMedication The isCurrentlyTakingMedication to set.
	 */
	public void setCurrentlyTakingMedication(boolean currentlyTakingMedication)
	{
		if (this.currentlyTakingMedication != currentlyTakingMedication)
		{
			markModified();
		}
		this.currentlyTakingMedication = currentlyTakingMedication;
	}

	/**
	 * 
	 * @return Returns the medication.
	 */
	public Code getMedication()
	{
		initMedication();
		return medication;
	}

	/**
	 * 
	 * @param medication The medication to set.
	 */
	public void setMedication(Code medication)
	{
		if (this.medication == null || !this.medication.equals(medication))
		{
			markModified();
		}
		this.medication = medication;
	}

	/**
	 * 
	 * @return Returns the medicationTypeId.
	 */
	public String getMedicationTypeId()
	{
		fetch();
		return medicationTypeId;
	}

	/**
	 * 
	 * @param medicationTypeId The medicationTypeId to set.
	 */
	public void setMedicationTypeId(String medicationTypeId)
	{
		if (this.medicationTypeId == null || !this.medicationTypeId.equals(medicationTypeId))
		{
			markModified();
		}
		this.medicationTypeId = medicationTypeId;
	}

	/**
	 * 
	 * @return Returns the medications.
	 */
	public JuvenileMedication[] getMedications()
	{
		fetch();
		return medications;
	}

	/**
	 * 
	 * @param medications The medications to set.
	 */
	public void setMedications(JuvenileMedication[] medications)
	{
		if (this.medications == null || !this.medications.equals(medications))
		{
			markModified();
		}
		this.medications = medications;
	}

	/**
	 * 
	 * @return Returns the physicianFirstName.
	 *//*
	public String getPhysicianFirstName()
	{
		fetch();
		return physicianFirstName;
	}

	*//**
	 * 
	 * @param physicianFirstName The physicianFirstName to set.
	 *//*
	public void setPhysicianFirstName(String physicianFirstName)
	{
		if (this.physicianFirstName == null || !this.physicianFirstName.equals(physicianFirstName))
		{
			markModified();
		}
		this.physicianFirstName = physicianFirstName;
	}

	*//**
	 * 
	 * @return Returns the physicianLastName.
	 *//*
	public String getPhysicianLastName()
	{
		fetch();
		return physicianLastName;
	}

	*//**
	 * 
	 * @param physicianLastName The physicianLastName to set.
	 *//*
	public void setPhysicianLastName(String physicianLastName)
	{
		if (this.physicianLastName == null || !this.physicianLastName.equals(physicianLastName))
		{
			markModified();
		}
		this.physicianLastName = physicianLastName;
	}

	*//**
	 * 
	 * @return Returns the physicianMiddleName.
	 *//*
	public String getPhysicianMiddleName()
	{
		fetch();
		return physicianMiddleName;
	}

	*//**
	 * 
	 * @param physicianMiddleName The physicianMiddleName to set.
	 *//*
	public void setPhysicianMiddleName(String physicianMiddleName)
	{
		if (this.physicianMiddleName == null || !this.physicianMiddleName.equals(physicianMiddleName))
		{
			markModified();
		}
		this.physicianMiddleName = physicianMiddleName;
	}*/

	/**
	 * 
	 * @return Returns the physicianPhoneNum.
	 */
	public String getPhysicianPhoneNum()
	{
		fetch();
		return physicianPhoneNum;
	}

	/**
	 * 
	 * @param physicianPhoneNum The physicianPhoneNum to set.
	 */
	public void setPhysicianPhoneNum(String physicianPhoneNum)
	{
		if (this.physicianPhoneNum == null || !this.physicianPhoneNum.equals(physicianPhoneNum))
		{
			markModified();
		}
		this.physicianPhoneNum = physicianPhoneNum;
	}

	/**
	 * 
	 * @return Returns the reasonForMedication.
	 */
	public String getReasonForMedication()
	{
		fetch();
		return reasonForMedication;
	}

	/**
	 * 
	 * @param reasonForMedication The reasonForMedication to set.
	 */
	public void setReasonForMedication(String reasonForMedication)
	{
		if (this.reasonForMedication == null || !this.reasonForMedication.equals(reasonForMedication))
		{
			markModified();
		}
		this.reasonForMedication = reasonForMedication;
	}
	
	/**
	 * 
	 * @return Returns the modificationReason.
	 */
	public String getModificationReason()
	{
		fetch();
		return modificationReason;
	}

	/**
	 * 
	 * @param modificationReason The modificationReason to set.
	 */
	public void setModificationReason(String modificationReason)
	{
		if (this.modificationReason == null || !this.modificationReason.equals(modificationReason))
		{
			markModified();
		}
		this.modificationReason = modificationReason;
	}


	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initMedication()
	{
		if (medication == null)
		{
			medication = (Code) new mojo.km.persistence.Reference(medicationTypeId, Code.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFrequency()
	{
		if (frequency == null)
		{
			frequency = (Code) new mojo.km.persistence.Reference(frequencyId, Code.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCurrentlyTakingMed()
	{
		if (currentlyTakingMed == null)
		{
			currentlyTakingMed = (Code) new mojo.km.persistence.Reference(currentlyTakingMedId, Code.class)
					.getObject();
		}
	}
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}
	
	/**
	 * @roseuid 45AF7A0A0190
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * @roseuid 45AF7A0A0190
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}
	/**
	 * 
	 * @return Returns the physicianName.
	 */
	public String getPhysicianName()
	{
		fetch();
		return physicianName;
	}

	/**
	 * 
	 * @param physicianFirstName The physicianName to set.
	 */
	public void setPhysicianName(String physicianName)
	{
		if (this.physicianName == null || !this.physicianName.equals(physicianName))
		{
			markModified();
		}
		this.physicianName = physicianName;
	}

}
