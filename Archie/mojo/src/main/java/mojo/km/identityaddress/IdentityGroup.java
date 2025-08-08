/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.identityaddress;

import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *
 */
public class IdentityGroup extends PersistentObject
{
	private IdentityAddress parent;
	private String parentId;
	private IdentityAddress child;
	private String childId;

	public IdentityGroup()
	{
	}

	/**
	 * @return
	 */
	public String getChildId()
	{
		fetch();
		return childId;
	}

	/**
	* Initialize class relationship to class mojo.km.identityaddress.IdentityAddress
	*/
	private void initChild()
	{
		if (child == null)
		{
			child = (IdentityAddress) new mojo.km.persistence.Reference(childId, IdentityAddress.class).getObject();
		}
	}

	/**
	 * @return
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	 * @param string
	 */
	public void setChildId(String aChildId)
	{
		if (this.childId == null || !this.childId.equals(aChildId))
		{
			markModified();
		}
		child = null;
		this.childId = aChildId;
	}

	/**
	* Initialize class relationship to class pd.juvenilewarrant.JuvenileWarrant
	*/
	private void initParent()
	{
		if (parent == null)
		{
			parent = (IdentityAddress) new mojo.km.persistence.Reference(parentId, IdentityAddress.class).getObject();
		}
	}

	/**
	 * @param string
	 */
	public void setParentId(String aParentId)
	{
		if (this.parentId == null || !this.parentId.equals(aParentId))
		{
			markModified();
		}
		parent = null;
		this.parentId = aParentId;
	}

	/**
	 * @return
	 */
	public Object getChild()
	{
		fetch();
		initChild();
		return child;
	}

	/**
	 * @return
	 */
	public Object getParent()
	{
		fetch();
		initParent();
		return parent;
	}

	/**
	 * @param object
	 */
	public void setChild(IdentityAddress aChild)
	{
		if (this.child == null || !this.child.equals(aChild))
		{
			markModified();
		}
		if (aChild.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aChild);
		}
		setChildId("" + aChild.getOID());
		this.child = (IdentityAddress) new mojo.km.persistence.Reference(aChild).getObject();
	}

	/**
	 * @param object
	 */
	public void setParent(IdentityAddress aParent)
	{
		if (this.parent == null || !this.parent.equals(aParent))
		{
			markModified();
		}
		if (aParent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + aParent.getOID());
		this.parent = (IdentityAddress) new mojo.km.persistence.Reference(aParent).getObject();
	}

}
