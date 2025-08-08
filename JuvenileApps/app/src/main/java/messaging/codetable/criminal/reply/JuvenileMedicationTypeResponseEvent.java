/*
 * Created on May 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.codetable.criminal.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JuvenileMedicationTypeResponseEvent extends ResponseEvent {

	//OID and code are the same.
	private String medicationTypeId;

	private String code;

	private String tradeName;

	private String dosageAdmin;

	private String ingredient;

	private String categoryId;

	private String strength;
	
	private String usage;
	
	private String medication;
	

	/**
	 *  
	 */
	public JuvenileMedicationTypeResponseEvent() {
	}

	
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return Returns the category.
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param category
	 *            The category to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return Returns the dosageAdmin.
	 */
	public String getDosageAdmin() {
		return dosageAdmin;
	}

	/**
	 * @param dosageAdmin
	 *            The dosageAdmin to set.
	 */
	public void setDosageAdmin(String dosageAdmin) {
		this.dosageAdmin = dosageAdmin;
	}

	/**
	 * @return Returns the ingredient.
	 */
	public String getIngredient() {
		return ingredient;
	}

	/**
	 * @param ingredient
	 *            The ingredient to set.
	 */
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
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
	 * @return Returns the strength.
	 */
	public String getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            The strength to set.
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
	 * @param tradeName
	 *            The tradeName to set.
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
}
