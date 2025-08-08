package pd.juvenilewarrant;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import pd.codetable.person.ScarsMarksTattoosCode;

/**
 * @author ryoung
 *
 */
public class JuvenileWarrantScarsMarksScarsMarksTattoosCode extends mojo.km.persistence.PersistentObject implements IPersistentObjectAssociation
{
	private JuvenileWarrant parent;
	private String childId;
	private String parentId;
	private ScarsMarksTattoosCode child;
	/**
	 * Set the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	 * @param childId
	 */
	public void setChildId(String lchildId)
	{
		if (this.childId == null || !this.childId.equals(lchildId))
		{
			markModified();
		}
		child = null;
		this.childId = lchildId;
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
	* @return ScarsMarksTatoosCode child
	*/
	public ScarsMarksTattoosCode getChild()
	{
		fetch();
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	*/
	public void setChild(ScarsMarksTattoosCode achild)
	{
		if (this.child == null || !this.child.equals(achild))
		{
			markModified();
		}
		if (achild.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(achild);
		}
		setChildId(achild.getCode());
		this.child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(achild).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilewarrant.JuvenileWarrant
	*/
	public void setParentId(String aparentId)
	{
		if (this.parentId == null || !this.parentId.equals(aparentId))
		{
			markModified();
		}
		parent = null;
		this.parentId = aparentId;
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
	* @return JuvenileWarrant parent
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
	public void setParent(JuvenileWarrant aparent)
	{
		if (this.parent == null || !this.parent.equals(aparent))
		{
			markModified();
		}
		if (aparent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + aparent.getOID());
		this.parent = (JuvenileWarrant) new mojo.km.persistence.Reference(aparent).getObject();
	}
	
	static public Iterator findAll(String attrName, String attrValue)
	{
	        IHome home = new Home();
	        Iterator scarMarks = null;
	        scarMarks = home.findAll(attrName, attrValue, JuvenileWarrantScarsMarksScarsMarksTattoosCode.class);
	        return scarMarks;
	}
}
