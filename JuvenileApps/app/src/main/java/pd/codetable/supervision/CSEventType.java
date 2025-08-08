package pd.codetable.supervision;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;

import java.util.Iterator;

import pd.contact.party.Party;

/**
 * 
 * @author Nagalla
 TODO To change the template for this generated type comment go to
 Window - Preferences - Java - Code Style - Code Templates
 */
public class CSEventType extends PersistentObject
{
	private String code;
	private String description;
	private String accessContext;
	private String displayContext;
	/**
	 * Properties for type
	 * @referencedType pd.codetable.supervision.SupervisionCode
	 * @detailerDoNotGenerate false
	 * @contextKey CAL_EVENT_CATEGORY
	 */
	private SupervisionCode category = null;
	private String categoryId;

	/**
	 * Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public void setCategoryId(String categoryId)
	{
		if (this.categoryId == null || !this.categoryId.equals(categoryId))
		{
			markModified();
		}
		this.categoryId = null;
		this.categoryId = categoryId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	 */
	public String getCategoryId()
	{
		fetch();
		return categoryId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	 */
	private void initCategory()
	{
		if (category == null)
		{
//			category = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(categoryId,
//					pd.codetable.supervision.SupervisionCode.class, "CAL_EVENT_CATEGORY").getObject();
			category = PDSupervisionCodeHelper.getSupervisionCodeByValue(
//	        		PDSecurityHelper.getUserAgencyId(), 
					PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.CAL_EVENT_CATEGORY, categoryId);
		}
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode
	 */
	public SupervisionCode getCategory()
	{
		initCategory();
		return category;
	}

	/**
	 * set the type reference for class member category
	 */
	public void setCategory(SupervisionCode category)
	{
		if (this.category == null || !this.category.equals(category))
		{
			markModified();
		}
//		if (category.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(category);
//		}
//		setCategoryId("" + category.getOID());
//		category.setContext("CAL_EVENT_CATEGORY");
//		this.category = (pd.codetable.supervision.SupervisionCode) new mojo.km.persistence.Reference(category)
//				.getObject();
		setCategoryId(category.getCode());
		category.setContext("CAL_EVENT_CATEGORY");
		this.category = (SupervisionCode) new mojo.km.persistence.Reference(category)
				.getObject();
	}

	/**
	 * 
	 * @return Returns the accessContext.
	 */
	public String getAccessContext()
	{
		fetch();
		return accessContext;
	}

	/**
	 * 
	 * @return Returns the code.
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * 
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * 
	 * @return Returns the displayContext.
	 */
	public String getDisplayContext()
	{
		fetch();
		return displayContext;
	}

	/**
	 * 
	 * @param accessContext The accessContext to set.
	 */
	public void setAccessContext(String accessContext)
	{
		if (this.accessContext == null || !this.accessContext.equals(accessContext))
		{
			markModified();
		}
		this.accessContext = null;
		this.accessContext = accessContext;
	}

	/**
	 * 
	 * @param code The code to set.
	 */
	public void setCode(String code)
	{
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = null;
		this.code = code;
	}

	/**
	 * 
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = null;
		this.description = description;
	}

	/**
	 * 
	 * @param displayContext The displayContext to set.
	 */
	public void setDisplayContext(String displayContext)
	{
		if (this.displayContext == null || !this.displayContext.equals(displayContext))
		{
			markModified();
		}
		this.displayContext = null;
		this.displayContext = displayContext;
	}

	/**
	 * Find all CSEventType objects
	 */
	static public Iterator findAll()
	{
		//initialize lookup objects
		IHome home = new Home();
		//use delegate to locate all CSEvent type objects
		Iterator iter = home.findAll(CSEventType.class);
		return iter;
	}

	static public CSEventType find(String eventTypeId)
	{
		return (CSEventType) new Home().find(eventTypeId, CSEventType.class);
	}
	
	/**
	 * Returns CSEventType object matching attribute name/value pair.
	 * 
	 * @return CSEventType
	 * @param attrName
	 * @param attrValue
	 */
	static public CSEventType find(String attrName, String attrValue) {
		IHome home = new Home();
		CSEventType csEventType = null;
		try {
			csEventType = (CSEventType) home.find(attrName, attrValue, CSEventType.class);
		} catch (ObjectNotFoundException e) {
			csEventType = null;
		}
		return csEventType;
	}

	/**
	 * Finds all CSEventType by an attribute value
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, CSEventType.class);
	}
}
