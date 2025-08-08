package pd.juvenilewarrant;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import pd.codetable.person.ScarsMarksTattoosCode;

/**
 * @author ryoung
 *
 */
public class JuvenileWarrantTattoosScarsMarksTattoosCode extends mojo.km.persistence.PersistentObject implements IPersistentObjectAssociation
{
	private JuvenileWarrant parent;
	private String childId;
	private String parentId;
	private ScarsMarksTattoosCode child;
	/**
	 * Set the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	 * @param childId
	 */
	public void setChildId(String achildId)
	{
		if (this.childId == null || !this.childId.equals(achildId))
		{
			markModified();
		}
		child = null;
		this.childId = achildId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	* @return String childId
	*/
	public String getChildId()
	{
		fetch();
		return childId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.ScarsMarksTattoosCode
	*/
	private void initChild()
	{
		if (child == null)
		{
			child =
				(ScarsMarksTattoosCode) new mojo
					.km
					.persistence
					.Reference(childId, ScarsMarksTattoosCode.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.person.ScarsMarksTattoosCode
	* @return child
	*/
	public ScarsMarksTattoosCode getChild()
	{
		fetch();
		initChild();
		return child;
	}
	/**
	 * set the type reference for class member child
	 * @param child
	 */
	public void setChild(ScarsMarksTattoosCode lchild)
	{
		if (this.child == null || !this.child.equals(lchild))
		{
			markModified();
		}
		if (lchild.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(lchild);
		}
		setChildId(lchild.getCode());
		this.child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(lchild).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
	*/
	public void setParentId(String lparentId)
	{
		if (this.parentId == null || !this.parentId.equals(lparentId))
		{
			markModified();
		}
		parent = null;
		this.parentId = lparentId;
	}
	/**
	* Get the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
	* @return String parentId
	*/
	public String getParentId()
	{
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.juvenilewarrant.JuvenileWarrant
	*/
	private void initParent()
	{
		if (parent == null)
		{
			parent =
				(JuvenileWarrant) new mojo
					.km
					.persistence
					.Reference(parentId, JuvenileWarrant.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilewarrant.JuvenileWarrant
	* @return parent
	*/
	public JuvenileWarrant getParent()
	{
		fetch();
		initParent();
		return parent;
	}
	/**
	* set the type reference for class member parent
	*/
	public void setParent(JuvenileWarrant lparent)
	{
		if (this.parent == null || !this.parent.equals(lparent))
		{
			markModified();
		}
		if (lparent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(lparent);
		}
		setParentId("" + lparent.getOID());
		this.parent = (JuvenileWarrant) new mojo.km.persistence.Reference(lparent).getObject();
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
	        IHome home = new Home();
	        Iterator tattoos = null;
	        tattoos = home.findAll(attrName, attrValue, JuvenileWarrantTattoosScarsMarksTattoosCode.class);
	        return tattoos;
	}
}
