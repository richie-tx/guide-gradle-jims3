/*
 * Created on Nov 18, 2004
 * 
 * This class represents a class association relationship between two other
 * persistent classes.
 */
package mojo.km.persistence;

import mojo.naming.ModellingConstants;

/**
 * @author Jim Fisher
 *
 */
public abstract class Association extends PersistentObject
{
	private String associationType;

	private PersistentObject child;
	private String childId;

	private PersistentObject parent;
	private String parentId;

	private Class childClass;

	private Class parentClass;

	public Association(Class parentClass, Class childClass, String associationType)
	{
		this.parentClass = parentClass;
		this.childClass = childClass;
		this.associationType = associationType;
	}

	/**
	 * Gets referenced type pd.security.authorization.SystemActivity
	 */
	public PersistentObject getChild()
	{
		fetch();
		this.initChild(childClass);
		return child;
	}

	/**
	 * Get the reference to the child class
	 */
	public String getChildId()
	{
		fetch();
		return childId;
	}

	/**
	* Gets referenced type pd.security.authorization.Role
	*/
	public PersistentObject getParent()
	{
		fetch();
		return parent;
	}

	/**
	* Get the reference value to class :: pd.security.authorization.Role
	*/
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	 * Initialize child class relationship
	 */
	private void initChild(Class childClass)
	{
		if (child == null)
		{
			try
			{
				Reference reference = new Reference(childId, childClass);
				this.child = reference.getObject();
			}
			catch (Throwable t)
			{
				// TODO Is this catch necessary?
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.security.authorization.Role
	 */
	private void initParent()
	{
		if (parent == null)
		{
			try
			{
				parent = new Reference(parentId, parentClass).getObject();
			}
			catch (Throwable t)
			{
				// TODO Is this catch necessary?
			}
		}
	}

	public boolean isSimpleAssociation()
	{
		return ModellingConstants.SIMPLE_ASSOCIATION.equals(this.associationType);
	}

	public boolean isComposite()
	{
		return ModellingConstants.COMPOSITE_ASSOCIATION.equals(this.associationType);
	}

	public String getAssociationType()
	{
		return this.associationType;
	}

	/**
	 * Set the type reference for class member child
	 */
	public void setChild(PersistentObject child)
	{
		if (this.child == null || !this.child.equals(child))
		{
			markModified();
		}
		if (child.getOID() == null)
		{
			new Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = new Reference(child).getObject();
	}

	/**
	* Set the reference value to the child class
	*/
	public void setChildId(String childId)
	{
		if (this.childId == null || !this.childId.equals(childId))
		{
			markModified();
		}

		child = null;
		this.childId = childId;
	}

	/**
	* Set the type reference for class member parent
	*/
	public void setParent(PersistentObject parent)
	{
		if (this.parent == null || !this.parent.equals(parent))
		{
			markModified();
		}
		if (parent.getOID() == null)
		{
			new Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = new Reference(parent).getObject();
	}

	/**
	* Set the reference value to parent class
	*/
	public void setParentId(String parentId)
	{
		if (this.parentId == null || !this.parentId.equals(parentId))
		{
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}
}
