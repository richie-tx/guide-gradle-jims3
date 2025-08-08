/*
 * Created on May 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.criminal;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.ICodetable;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JuvenileMedicationTypeCode extends PersistentObject implements ICodetable {

	//OID and code are same.
	private String medicationTypeId;

	private String tradeName;

	private String dosageAdmin;

	private String ingredient;
	
	private String medication;

	/**
	 * Properties for type
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey NCIC_MEDICATION
	 */
	private Code category = null;
	
	private String categoryId;

	private String strength;
	
	private String usage;
	
	//private String code;

	/**
	 * @roseuid 45771CA9003F
	 *  
	 */
	public JuvenileMedicationTypeCode() {
	}

	/*
	 * (non-Javadoc)
	 * @roseuid 45771CA9003F
	 * @see pd.codetable.ICodetable#findAll()
	 */
	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, JuvenileMedicationTypeCode.class);
	}

	/**
	* Finds all medCodes by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator medCodes = home.findAll(attributeName, attributeValue, JuvenileMedicationTypeCode.class);
		return medCodes;
	}
	
	/**
	* @return JuvenileMedicationTypeCode
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public JuvenileMedicationTypeCode find(String medicationTypeId) {
		
		JuvenileMedicationTypeCode juvenileMedicationTypeCode = null;
		IHome home = new Home();
		juvenileMedicationTypeCode = (JuvenileMedicationTypeCode) home.find(medicationTypeId, JuvenileMedicationTypeCode.class);
		return juvenileMedicationTypeCode;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @roseuid 45771CA9003F
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub

	}

	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the category. 
	 * Get the reference value to class :: pd.codetable.Code
	 */
	 
	public String getCategoryId() {
		fetch();
		return categoryId;
	}

	/**
	 * 
	 * Set the reference value to class :: pd.codetable.Code
	 * @roseuid 45771CA9003F
	 * @param categoryId
	 *            The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		if (this.categoryId == null || !this.categoryId.equals(categoryId)) {
			markModified();
		}
		category = null;
		this.categoryId = categoryId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCategory()
	{
		if (category == null)
		{
			category = (Code) new mojo.km.persistence.Reference(categoryId, Code.class,
					"NCIC_MEDICATION").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCategory()
	{
		initCategory();
		return category;
	}

	/**
	 * set the type reference for class member category
	 */
	public void setCategory(Code category)
	{
		if (this.category == null || !this.category.equals(category))
		{
			markModified();
		}
		if (category.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(category);
		}
		setCategoryId("" + category.getOID());
		category.setContext("NCIC_MEDICATION");
		this.category = (Code) new mojo.km.persistence.Reference(category).getObject();
	}
	
	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the dosageAdmin.
	 */
	public String getDosageAdmin() {
		fetch();
		return dosageAdmin;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @param dosageAdmin
	 *            The dosageAdmin to set.
	 */
	public void setDosageAdmin(String dosageAdmin) {
		if (this.dosageAdmin == null || !this.dosageAdmin.equals(dosageAdmin)) {
			markModified();
		}
		this.dosageAdmin = dosageAdmin;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the ingredient.
	 */
	public String getIngredient() {
		fetch();
		return ingredient;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @param ingredient
	 *            The ingredient to set.
	 */
	public void setIngredient(String ingredient) {
		if (this.ingredient == null || !this.ingredient.equals(ingredient)) {
			markModified();
		}
		this.ingredient = ingredient;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the medicationTypeId.
	 */
	public String getMedicationTypeId() {
		fetch();
		return this.getOID().toString();
	}

	/**
	 * @roseuid 45771CA9003F
	 * @param medicationTypeId
	 *            The medicationTypeId to set.
	 */
	public void setMedicationTypeId(String medicationTypeId) {
		if (this.medicationTypeId == null || !this.medicationTypeId.equals(medicationTypeId)) {
			markModified();
		}
		this.setOID(medicationTypeId);
		this.medicationTypeId = medicationTypeId;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the strength.
	 */
	public String getStrength() {
		fetch();
		return strength;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @param strength
	 *            The strength to set.
	 */
	public void setStrength(String strength) {
		if (this.strength == null || !this.strength.equals(strength)) {
			markModified();
		}
		this.strength = strength;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the tradeName.
	 */
	public String getTradeName() {
		fetch();
		return tradeName;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @param tradeName
	 *            The tradeName to set.
	 */
	public void setTradeName(String tradeName) {
		if (this.tradeName == null || !this.tradeName.equals(tradeName)) {
			markModified();
		}
		this.tradeName = tradeName;
	}

	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#findAll()
	 */
	public Iterator findAll() {
		return new Home().findAll(JuvenileMedicationTypeCode.class);
	}
	
	/**
	 *  @roseuid 45771CA9003F
	 * @return Returns the usage.
	 */
	public String getUsage() {
		fetch();
		return usage;
	}
	/**
	 *  @roseuid 45771CA9003F
	 * @param usage The usage to set.
	 */
	public void setUsage(String usage) {
		if (this.usage == null || !this.usage.equals(usage)) {
			markModified();
		}
		this.usage = usage;
	}
	
	/**
	 * @roseuid 45771CA9003F
	 * @return Returns the strength.
	 */
	public String getMedication() {
		fetch();
		return medication;
	}

	/**
	 * @roseuid 45771CA9003F
	 * @param strength
	 *            The strength to set.
	 */
	public void setMedication(String medication) {
		if (this.medication == null || !this.medication.equals(medication)) {
			markModified();
		}
		this.medication = medication;
	}
	/**
	 *  @roseuid 45771CA9003F
	 * @return Returns the code.
	 *//*
	public String getCode() {
		fetch();
		return code;
	}
	*//**
	 *  @roseuid 45771CA9003F
	 * @param code The code to set.
	 *//*
	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code)) {
			markModified();
		}
		this.code = code;
	}*/
	
}
