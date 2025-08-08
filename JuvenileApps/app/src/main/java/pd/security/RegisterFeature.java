package pd.security;

import java.util.Iterator;
import pd.codetable.Code;
import pd.codetable.ICodetable;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
/**
 * This Entity is used for maintaining JIMS2 Features through Code Table Registration Only
 * Do not make any changes to this class without discussing it with Robbie and Debbie N.
 */
public class RegisterFeature extends PersistentObject implements ICodetable {

	private String description;

	/**
	 * Properties for featureCategory
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey JIMS2_SUBSYSTEMS
	 */
	private Code featureCategory;
	private String featureCategoryId;

	/**
	 * Properties for featureType
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey FEATURE_TYPE
	 */
	private Code featureType;
	private String featureTypeId;

	private String name;

	/**
	 * Properties for parentId
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey FEATURE_PARENT_ID
	 */
	private Code parent;
	private String parentId;

	/**
	 * @roseuid 422C77F300BE
	 */
	public RegisterFeature() {
	}

	/**
	 * Access method for the description property.
	 * 
	 * @return the current value of the description property
	 */
	public String getDescription() {
		fetch();
		return description;
	}

	/**
	 * Access method for the name property.
	 * 
	 * @return the current value of the name property
	 */
	public String getName() {
		fetch();
		return name;
	}


	/**
	 * Sets the value of the description property.
	 * 
	 * @param aDescription
	 *            the new value of the description property
	 */
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}
	
	/**
	 * Sets the value of the name property.
	 * 
	 * @param aName
	 *            the new value of the name property
	 */
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}

	static public RegisterFeature find(String featureId) {
		return (RegisterFeature) new Home().find(featureId, RegisterFeature.class);
	}

	/**
	 * @return java.util.Iterator
	 */
	static public Iterator findAll(IEvent event) {
		return new Home().findAll(event, RegisterFeature.class);
	}

	/**
	 * @return java.util.Iterator
	 * @param attrName
	 * @param attrValue
	 * @roseuid 4236ED9401A2
	 */
	static public Iterator findAll(String attrName, String attrValue) {
		return new Home().findAll(attrName, attrValue, RegisterFeature.class);
	}
	
	/**
	 * @roseuid 45771BE501A2
	 */
	public Iterator findAll()
	{
		return new Home().findAll(RegisterFeature.class);
	}
	
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets referenced featureCategory pd.codetable.Code
	 */
	public Code getFeatureCategory()
	{
		initFeatureCategory();
		return featureCategory;
	}

	/**
	 * set the type reference for class member featureCategory
	 */
	public void setFeatureCategory(Code featureCategory)
	{
		if (this.featureCategory == null || !this.featureCategory.equals(featureCategory))
		{
			markModified();
		}
		if (featureCategory.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(featureCategory);
		}
		setFeatureCategoryId("" + featureCategory.getOID());
		featureCategory.setContext("JIMS2_SUBSYSTEMS");
		this.featureCategory = (Code) new mojo.km.persistence.Reference(featureCategory).getObject();
	}
	
	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getFeatureCategoryId()
	{
		fetch();
		return featureCategoryId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFeatureCategory()
	{
		if (featureCategory == null)
		{
			featureCategory = (Code) new mojo.km.persistence.Reference(featureCategoryId, Code.class,
					"JIMS2_SUBSYSTEMS").getObject();
		}
	}
	
	/**
	 * @param featureCategoryId The featureCategoryId to set.
	 */
	public void setFeatureCategoryId(String featureCategoryId) {
		if (this.featureCategoryId == null || !this.featureCategoryId.equals(featureCategoryId))
		{
			markModified();
		}
		this.featureCategoryId = featureCategoryId;
	}
	/**
	 * Gets referenced featureType pd.codetable.Code
	 */
	public Code getFeatureType()
	{
		initFeatureType();
		return featureType;
	}

	/**
	 * set the type reference for class member featureType
	 */
	public void setFeatureType(Code featureType)
	{
		if (this.featureType == null || !this.featureType.equals(featureType))
		{
			markModified();
		}
		if (featureType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(featureType);
		}
		setFeatureTypeId("" + featureType.getOID());
		featureType.setContext("FEATURE_TYPE");
		this.featureType = (Code) new mojo.km.persistence.Reference(featureType).getObject();
	}
	
	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getFeatureTypeId()
	{
		fetch();
		return featureTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFeatureType()
	{
		if (featureType == null)
		{
			featureType = (Code) new mojo.km.persistence.Reference(featureTypeId, Code.class,
					"FEATURE_TYPE").getObject();
		}
	}
	
	/**
	 * @param featureTypeId The featureTypeId to set.
	 */
	public void setFeatureTypeId(String featureTypeId) {
		if (this.featureTypeId == null || !this.featureTypeId.equals(featureTypeId))
		{
			markModified();
		}
		this.featureTypeId = featureTypeId;
	}
	
	/**
	 * Gets referenced parent pd.codetable.Code
	 */
	public Code getParent()
	{
		initParent();
		return parent;
	}

	/**
	 * set the type reference for class member parent
	 */
	public void setParent(Code parent)
	{
		if (this.parent == null || !this.parent.equals(parent))
		{
			markModified();
		}
		if (parent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		parent.setContext("FEATURE_PARENT_ID");
		this.parent = (Code) new mojo.km.persistence.Reference(parent).getObject();
	}
	
	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initParent()
	{
		if (parent == null)
		{
			parent = (Code) new mojo.km.persistence.Reference(parentId, Code.class,
					"FEATURE_PARENT_ID").getObject();
		}
	}
	
	/**
	 * @param featureCategoryId The featureCategoryId to set.
	 */
	public void setParentId(String parentId) {
		if (this.parentId == null || !this.parentId.equals(parentId))
		{
			markModified();
		}
		this.parentId = parentId;
	}
}
