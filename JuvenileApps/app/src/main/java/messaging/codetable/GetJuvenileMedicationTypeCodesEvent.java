/*
 * Created on May 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenileMedicationTypeCodesEvent extends RequestEvent {
	
	private String tradeName;

	private String dosageAdmin;
	
	private String strength;
	
	private String usage;
	
	private String medicationTypeId;
	
	//private String code;
	
	private String categoryId;

	private String medication;
	
	private boolean flagfind; // used to check whether to call find or find all in the command
	
	public GetJuvenileMedicationTypeCodesEvent(){}
	
	
	
	/**
	 * @return Returns the code.
	 *//*
	public String getCode() {
		return code;
	}
	*//**
	 * @param code The code to set.
	 *//*
	public void setCode(String code) {
		this.code = code;
	}*/
	/**
	 * @return Returns the dosageAdmin.
	 */
	public String getDosageAdmin() {
		return dosageAdmin;
	}
	/**
	 * @param dosageAdmin The dosageAdmin to set.
	 */
	public void setDosageAdmin(String dosageAdmin) {
		this.dosageAdmin = dosageAdmin;
	}
	/**
	 * @return Returns the medicationTypeId.
	 */
	public String getMedicationTypeId() {
		return medicationTypeId;
	}
	/**
	 * @param medicationTypeId The medicationTypeId to set.
	 */
	public void setMedicationTypeId(String medicationTypeId) {
		this.medicationTypeId = medicationTypeId;
	}
	/**
	 * @return Returns the strength.
	 */
	public String getStrength() {
		return strength;
	}
	/**
	 * @param strength The strength to set.
	 */
	public void setStrength(String strength) {
		this.strength = strength;
	}
	/**
	 * @return Returns the tradeName.
	 */
	public String getTradeName() {
		return tradeName;
	}
	/**
	 * @param tradeName The tradeName to set.
	 */
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	/**
	 * @return Returns the usage.
	 */
	public String getUsage() {
		return usage;
	}
	/**
	 * @param usage The usage to set.
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return Returns the medication.
	 */
	public String getMedication() {
		return medication;
	}
	/**
	 * @param medication The medication to set.
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	/**
	 * @return Returns the flagfind.
	 */
	public boolean isFlagfind() {
		return flagfind;
	}
	/**
	 * @param flagfind The flagfind to set.
	 */
	public void setFlagfind(boolean flagfind) {
		this.flagfind = flagfind;
	}
}
