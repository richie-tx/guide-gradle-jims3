//Source file: C:\\views\\commonfunctionality_07\\app\\src\\pd\\codetable\\CodetableRegAgency.java

package pd.codetable;

import pd.contact.agency.Agency;
import pd.transferobjects.helper.AgencyHelper;
import mojo.km.persistence.PersistentObject;

public class CodetableRegAgency extends PersistentObject
{

	CodetableReg parent;

	private String childId;

	private String parentId;

	Agency child;

	/**
	 * Properties for Agency
	 * 
	 * @referencedType pd.contact.Agency
	 * @detailerDoNotGenerate false
	 */

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency;
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
	 * Get the reference value to class ::pd.contact.agency.Agency
	 */
	public String getChildId()
	{
		fetch();
		return childId;
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initChild()
	{
		if (child == null)
		{
			try
			{
				child = Agency.find(this.childId);//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(childId,
						//pd.contact.agency.Agency.class).getObject();
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}
		}
	}

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 */
	public Agency getChild()
	{
		fetch();
		initChild();
		return child;
	}

	/**
	 * set the type reference for class member child
	 */
	public void setChild(Agency child)
	{
		/*if (this.child == null || !this.child.equals(child))
		{
			markModified();
		}*/
		/*if (child.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(child);
		}*/
		setChildId("" + child.getAgencyId());
		this.child = child; //87191//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(child).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.CodetableReg
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

	/**
	 * Get the reference value to class :: pd.codetable.CodetableReg
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.CodetableReg
	 */
	private void initParent()
	{
		if (parent == null)
		{
			try
			{
				parent = (CodetableReg) new mojo.km.persistence.Reference(parentId,
						CodetableReg.class).getObject();
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.CodetableReg
	 */
	public CodetableReg getParent()
	{
		fetch();
		initParent();
		return parent;
	}

	/**
	 * set the type reference for class member parent
	 */
	public void setParent(CodetableReg parent)
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
		this.parent = (CodetableReg) new mojo.km.persistence.Reference(parent).getObject();
	}
}
