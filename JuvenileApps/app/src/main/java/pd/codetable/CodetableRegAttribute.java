package pd.codetable;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * 
 * @roseuid 45BA30DA00B9
 */
public class CodetableRegAttribute extends PersistentObject
{
	private String displayName;
	private String displayOrder;
	private String dbcolumn;
	private String compContextKey;
	private String compEntityName;
	private boolean required;
	private boolean audit;
	private boolean updatable;
	private boolean validCheckRequired;
	private boolean unique;
	private boolean numeric;
	private String type;
	private String compoundAttributeName;
	private String compoundAttributeId;
	private String compoundType;
	private String codetableRegId;
	private String codetableRegAttributeId;
	private int maxLength;
	private int minLength;

	/**
	 * 
	 * @roseuid 45BA30DA00B9
	 */
	public CodetableRegAttribute()
	{
	}
	
	/**
	* @return CodetableRegAttribute
	* @param codetableAttrRegId
	* @roseuid 4107B06D01BB
	*/
	static public CodetableRegAttribute find(String codetableAttrRegId)
	{
		IHome home = new Home();
		return (CodetableRegAttribute) home.find(codetableAttrRegId, CodetableRegAttribute.class);
	}

	/**
	 * @return Returns the compoundAttributeName.
	 */
	public String getCompoundAttributeName()
	{
		fetch();
		return compoundAttributeName;
	}

	/**
	 * @param compoundAttributeName The compoundAttributeName to set.
	 */
	public void setCompoundAttributeName(String compoundAttributeName)
	{
		if (this.compoundAttributeName == null || !this.compoundAttributeName.equals(compoundAttributeName))
		{
			markModified();
		}
		this.compoundAttributeName = compoundAttributeName;
	}

	/**
	 * @return Returns the compContextKey.
	 */
	public String getCompContextKey()
	{
		fetch();
		return compContextKey;
	}

	/**
	 * @param compContextKey The contextKey to set.
	 */
	public void setCompContextKey(String contextKey)
	{
		if (this.compContextKey == null || !this.compContextKey.equals(contextKey))
		{
			markModified();
		}
		this.compContextKey = contextKey;
	}

	/**
	 * @return Returns the dbcolumn.
	 */
	public String getDbcolumn()
	{
		fetch();
		return dbcolumn;
	}

	/**
	 * @param dbcolumn The dbcolumn to set.
	 */
	public void setDbcolumn(String dbcolumn)
	{
		if (this.dbcolumn == null || !this.dbcolumn.equals(dbcolumn))
		{
			markModified();
		}
		this.dbcolumn = dbcolumn;
	}

	/**
	 * @return Returns the displayName.
	 */
	public String getDisplayName()
	{
		fetch();
		return displayName;
	}

	/**
	 * @param displayName The displayName to set.
	 */
	public void setDisplayName(String displayName)
	{
		if (this.displayName == null || !this.displayName.equals(displayName))
		{
			markModified();
		}
		this.displayName = displayName;
	}

	/**
	 * @return Returns the displayOrder.
	 */
	public String getDisplayOrder()
	{
		fetch();
		return displayOrder;
	}

	/**
	 * @param displayOrder The displayOrder to set.
	 */
	public void setDisplayOrder(String displayOrder)
	{
		if (this.displayOrder == null || !this.displayOrder.equals(displayOrder))
		{
			markModified();
		}
		this.displayOrder = displayOrder;
	}

	/**
	 * @return Returns the compEntityName.
	 */
	public String getCompEntityName()
	{
		fetch();
		return compEntityName;
	}

	/**
	 * @param compEntityName The entityName to set.
	 */
	public void setCompEntityName(String entityName)
	{
		if (this.compEntityName == null || !this.compEntityName.equals(entityName))
		{
			markModified();
		}
		this.compEntityName = entityName;
	}

	/**
	 * @param audit The audit to set.
	 */
	public void setAudit(boolean audit)
	{
		if (this.audit != audit)
		{
			markModified();
		}
		this.audit = audit;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		fetch();
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type)
	{
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}

	/**
	 * Set the reference value to class :: pd.codetable.CodetableReg
	 */
	public void setCodetableRegId(String codetableRegId)
	{
		this.codetableRegId = codetableRegId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.CodetableReg
	 */
	public String getCodetableRegId()
	{
		return codetableRegId;
	}
	
	/**
	 * @param isRequired The isRequired to set.
	 */
	public void setRequired(boolean required) {
		if (this.required != required)
		{
			markModified();
		}
		this.required = required;
	}
	/**
	 * @return Returns the audit.
	 */
	public boolean isAudit() {
		fetch();
		return audit;
	}
	/**
	 * @return Returns the required.
	 */
	public boolean isRequired() {
		fetch();
		return required;
	}
	/**
	 * @return Returns the codetableRegAttributeId.
	 */
	public String getCodetableRegAttributeId() {
		fetch();
		return codetableRegAttributeId;
	}
	/**
	 * @param codetableRegAttributeId The codetableRegAttributeId to set.
	 */
	public void setCodetableRegAttributeId(String codetableRegAttributeId) {
		if (this.codetableRegAttributeId == null || !this.codetableRegAttributeId.equals(codetableRegAttributeId))
		{
			markModified();
		}
		this.setOID(codetableRegAttributeId);
		this.codetableRegAttributeId = codetableRegAttributeId;
	}
	/**
	 * @return Returns the compoundAttributeId.
	 */
	public String getCompoundAttributeId() {
		fetch();
		return compoundAttributeId;
	}
	/**
	 * @param compoundAttributeId The compoundAttributeId to set.
	 */
	public void setCompoundAttributeId(String compoundAttributeId) {
		if (this.compoundAttributeId == null || !this.compoundAttributeId.equals(compoundAttributeId))
		{
			markModified();
		}
		this.compoundAttributeId = compoundAttributeId;
	}
	/**
	 * @return Returns the compoundType.
	 */
	public String getCompoundType() {
		fetch();
		return compoundType;
	}
	/**
	 * @param compoundType The compoundType to set.
	 */
	public void setCompoundType(String compoundType) {
		if (this.compoundType == null || !this.compoundType.equals(compoundType))
		{
			markModified();
		}
		this.compoundType = compoundType;
	}
	/**
	 * @return Returns the updatable.
	 */
	public boolean isUpdatable() {
		fetch();
		return updatable;
	}
	/**
	 * @param updatable The updatable to set.
	 */
	public void setUpdatable(boolean updatable) {
		if (this.updatable != updatable)
		{
			markModified();
		}
		this.updatable = updatable;
	}
	/**
	 * @return Returns the maxLength.
	 */
	public int getMaxLength() {
		fetch();
		return maxLength;
	}
	/**
	 * @param maxLength The maxLength to set.
	 */
	public void setMaxLength(int maxLength) {
		if (this.maxLength != 0)
		{
			markModified();
		}
		this.maxLength = maxLength;
	}
	/**
	 * @return Returns the minLength.
	 */
	public int getMinLength() {
		fetch();
		return minLength;
	}
	/**
	 * @param minLength The minLength to set.
	 */
	public void setMinLength(int minLength) {
		if (this.minLength != 0)
		{
			markModified();
		}
		this.minLength = minLength;
	}
	/**
	 * @return Returns the unique.
	 */
	public boolean isUnique() {
		fetch();
		return unique;
	}
	/**
	 * @param unique The unique to set.
	 */
	public void setUnique(boolean unique) {
		if (this.unique != unique)
		{
			markModified();
		}
		this.unique = unique;
	}
	/**
	 * @return Returns the validCheckRequired.
	 */
	public boolean isValidCheckRequired() {
		fetch();
		return validCheckRequired;
	}
	/**
	 * @param validCheckRequired The validCheckRequired to set.
	 */
	public void setValidCheckRequired(boolean validCheckRequired) {
		if (this.validCheckRequired != validCheckRequired)
		{
			markModified();
		}
		this.validCheckRequired = validCheckRequired;
	}
	/**
	 * @return Returns the numeric.
	 */
	public boolean isNumeric() {
		fetch();
		return numeric;
	}
	/**
	 * @param numeric The numeric to set.
	 */
	public void setNumeric(boolean numeric) {
		if (this.numeric != numeric)
		{
			markModified();
		}
		this.numeric = numeric;
	}
}
