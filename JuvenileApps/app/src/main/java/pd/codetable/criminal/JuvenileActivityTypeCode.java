package pd.codetable.criminal;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;
import pd.codetable.ICodetable;

/**
 * Properties for type
 * @detailerDoNotGenerate false
 * @referencedType pd.codetable.Code
 * @contextKey ACTIVITY_TYPE
 */
public class JuvenileActivityTypeCode extends PersistentObject implements ICodetable, ICode
{
	private String code;
	private String description;
	private Date activityDate;
	private String status;
	/**
	 * Properties for type
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey ACTIVITY_TYPE
	 */
	private Code type = null;
	/**
	 * Properties for type
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey ACTIVITY_CATEGORY
	 */
	private Code category = null;
	
	private Code permission = null;
	
	private String typeId;
	private String categoryId;
	private String activityTypeId;
	private String permissionId;

	/**
	 * @roseuid 45771CA9003F
	 */
	public JuvenileActivityTypeCode()
	{
	}

	/**
	 * Access method for the code property.
	 * @return the current value of the code property
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * @param aCode the new value of the code property
	 */
	public void setCode(String aCode)
	{
		if (this.code == null || !this.code.equals(aCode))
		{
			markModified();
		}
		code = aCode;
	}

	/**
	 * Access method for the description property.
	 * @return the current value of the description property
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * @param aDescription the new value of the description property
	 */
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}

	/**
	 * Access method for the activityDate property.
	 * @return the current value of the activityDate property
	 */
	public Date getActivityDate()
	{
		fetch();
		return activityDate;
	}

	/**
	 * Sets the value of the activityDate property.
	 * @param aActivityDate the new value of the activityDate property
	 */
	public void setActivityDate(Date aActivityDate)
	{
		if (this.activityDate == null || !this.activityDate.equals(aActivityDate))
		{
			markModified();
		}
		activityDate = aActivityDate;
	}

	/**
	 * Access method for the status property.
	 * @return the current value of the status property
	 */
	public String getStatus()
	{
		fetch();
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * @param aStatus the new value of the status property
	 */
	public void setStatus(String aStatus)
	{
		if (this.status == null || !this.status.equals(aStatus))
		{
			markModified();
		}
		status = aStatus;
	}

	/**
	 * @roseuid 45771BE501A2
	 */
	public Iterator findAll()
	{
		return new Home().findAll(JuvenileActivityTypeCode.class);
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setTypeId(String typeId)
	{
		if (this.typeId == null || !this.typeId.equals(typeId))
		{
			markModified();
		}
		type = null;
		this.typeId = typeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getTypeId()
	{
		fetch();
		return typeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initType()
	{
		if (type == null)
		{
			type = (Code) new mojo.km.persistence.Reference(typeId, Code.class,
					"ACTIVITY_TYPE").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getType()
	{
		initType();
		return type;
	}

	/**
	 * set the type reference for class member type
	 */
	public void setType(Code type)
	{
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		if (type.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(type);
		}
		setTypeId("" + type.getOID());
		type.setContext("ACTIVITY_TYPE");
		this.type = (Code) new mojo.km.persistence.Reference(type).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCategoryId(String categoryId)
	{
		if (this.categoryId == null || !this.categoryId.equals(categoryId))
		{
			markModified();
		}
		category = null;
		this.categoryId = categoryId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCategoryId()
	{
		fetch();
		return categoryId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCategory()
	{
		if (category == null)
		{
			category = (Code) new mojo.km.persistence.Reference(categoryId, Code.class,
					"ACTIVITY_CATEGORY").getObject();
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
		category.setContext("ACTIVITY_CATEGORY");
		this.category = (Code) new mojo.km.persistence.Reference(category).getObject();
	}
	
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setPermissionId(String permissionId)
	{
		if (this.permissionId == null || !this.permissionId.equals(permissionId))
		{
			markModified();
		}
		permission = null;
		this.permissionId = permissionId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getPermissionId()
	{
		fetch();
		return permissionId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPermission()
	{
		if (permission == null)
		{
			permission = (Code) new mojo.km.persistence.Reference(permissionId, Code.class,
					"ACTIVITY_PERMISSION").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getPermission()
	{
		initPermission();
		return permission;
	}

	/**
	 * set the type reference for class member type
	 */
	public void setPermission(Code permission)
	{
		if (this.permission == null || !this.permission.equals(permission))
		{
			markModified();
		}
		if (permission.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(permission);
		}
		setPermissionId("" + permission.getOID());
		permission.setContext("ACTIVITY_PERMISSION");
		this.permission = (Code) new mojo.km.persistence.Reference(permission).getObject();
	}
			
	/*
	 * @param activityTypeId
	 * @return JuvenileActivityTypeCode
	 */
	static public JuvenileActivityTypeCode find(String activityTypeId)
	{
		return (JuvenileActivityTypeCode) new Home().find(activityTypeId,JuvenileActivityTypeCode.class);
	}
	
	/*
	 * @param event
	 * @return Iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, JuvenileActivityTypeCode.class);
	}
	
	/**
	* Finds all JuvenileActivityTypeCode by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileActivityTypeCode.class);
	}
	
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the activityTypeId.
	 */
	public String getActivityTypeId() {
		fetch();
		return this.getOID().toString();
	}
	/**
	 * @param activityTypeId The activityTypeId to set.
	 */
	public void setActivityTypeId(String activityTypeId) {
		if (this.activityTypeId == null || !this.activityTypeId.equals(activityTypeId))
		{
			markModified();
		}
		this.setOID(activityTypeId);
		this.activityTypeId = activityTypeId;
	}
}

