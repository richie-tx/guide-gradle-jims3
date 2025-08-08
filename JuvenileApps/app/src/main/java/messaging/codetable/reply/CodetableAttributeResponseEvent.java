/*
 * Created on Jan 30, 2007
 *
 */
package messaging.codetable.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class CodetableAttributeResponseEvent extends ResponseEvent implements Comparable
{
	private String displayName;
	private String displayOrder;
	private String dbColumn;
	private String type;
	private boolean isRequired;
	private boolean isAudit;
	private boolean isUpdatable;
	private boolean compound = false;
	private boolean validCheckRequired;
	private boolean unique;
	private boolean numeric = false; 
	private String codetableRegAttributeId;
	private String contextKey;
	private String entityName;
	private int maxLength;
	private int minLength;
	private String compundType;
	private String compoundName;
	private String compoundId;
	
	
	/**
	 * @return Returns the codetableRegAttributeId.
	 */
	public String getCodetableRegAttributeId() {
		return codetableRegAttributeId;
	}
	/**
	 * @param codetableRegAttributeId The codetableRegAttributeId to set.
	 */
	public void setCodetableRegAttributeId(String codetableRegAttributeId) {
		this.codetableRegAttributeId = codetableRegAttributeId;
	}
	/**
	 * @return Returns the displayName.
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName The displayName to set.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return Returns the displayOrder.
	 */
	public String getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder The displayOrder to set.
	 */
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the isAudit.
	 */
	public boolean isAudit() {
		return isAudit;
	}
	/**
	 * @param isAudit The isAudit to set.
	 */
	public void setAudit(boolean isAudit) {
		this.isAudit = isAudit;
	}
	/**
	 * @return Returns the isRequired.
	 */
	public boolean isRequired() {
		return isRequired;
	}
	/**
	 * @param isRequired The isRequired to set.
	 */
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		CodetableAttributeResponseEvent evt = (CodetableAttributeResponseEvent) obj;
		int result = 0;
		
		try{
			if(this.getDisplayOrder()!=null || evt.getDisplayOrder()!=null){
				if(evt.getDisplayOrder()==null && !(evt.getDisplayOrder().trim().equals("")))
					return 1; 
				if(this.getDisplayOrder()==null && !(this.getDisplayOrder().trim().equals("")))
					return -1; 

				if(Integer.parseInt(this.getDisplayOrder()) == Integer.parseInt(evt.getDisplayOrder()))
				{
					return 0;	
				}
				else if(Integer.parseInt(this.getDisplayOrder()) < Integer.parseInt(evt.getDisplayOrder()))
				{
					return -1;	
				}
				else
				{
					return 1;
				}
			}
		}
		catch(NumberFormatException e){
			result = 0;
		}
		return 0;
	}
	/**
	 * @return Returns the contextKey.
	 */
	public String getContextKey() {
		return contextKey;
	}
	/**
	 * @param contextKey The contextKey to set.
	 */
	public void setContextKey(String contextKey) {
		this.contextKey = contextKey;
	}
	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @return Returns the isUpdatable.
	 */
	public boolean isUpdatable() {
		return isUpdatable;
	}
	/**
	 * @param isUpdatable The isUpdatable to set.
	 */
	public void setUpdatable(boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}	
	/**
	 * @return Returns the compound.
	 */
	public boolean isCompound() {
		return compound;
	}
	/**
	 * @param compound The compound to set.
	 */
	public void setCompound(boolean compound) {
		this.compound = compound;
	}
	/**
	 * @return Returns the maxLength.
	 */
	public int getMaxLength() {
		return maxLength;
	}
	/**
	 * @param maxLength The maxLength to set.
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	/**
	 * @return Returns the minLength.
	 */
	public int getMinLength() {
		return minLength;
	}
	/**
	 * @param minLength The minLength to set.
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	/**
	 * @return Returns the unique.
	 */
	public boolean isUnique() {
		return unique;
	}
	/**
	 * @param unique The unique to set.
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	/**
	 * @return Returns the validCheckRequired.
	 */
	public boolean isValidCheckRequired() {
		return validCheckRequired;
	}
	/**
	 * @param validCheckRequired The validCheckRequired to set.
	 */
	public void setValidCheckRequired(boolean validCheckRequired) {
		this.validCheckRequired = validCheckRequired;
	}
	/**
	 * @return Returns the compundType.
	 */
	public String getCompundType() {
		return compundType;
	}
	/**
	 * @param compundType The compundType to set.
	 */
	public void setCompundType(String compundType) {
		this.compundType = compundType;
	}
	/**
	 * @return Returns the numeric.
	 */
	public boolean isNumeric() {
		return numeric;
	}
	/**
	 * @param numeric The numeric to set.
	 */
	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}	
	/**
	 * @return Returns the dbColumn.
	 */
	public String getDbColumn() {
		return dbColumn;
	}
	/**
	 * @param dbColumn The dbColumn to set.
	 */
	public void setDbColumn(String dbColumn) {
		this.dbColumn = dbColumn;
	}
	
	
	/**
	 * @return Returns the compoundId.
	 */
	public String getCompoundId() {
		return compoundId;
	}
	/**
	 * @param compoundId The compoundId to set.
	 */
	public void setCompoundId(String compoundId) {
		this.compoundId = compoundId;
	}
	/**
	 * @return Returns the compoundName.
	 */
	public String getCompoundName() {
		return compoundName;
	}
	/**
	 * @param compoundName The compoundName to set.
	 */
	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}
}
