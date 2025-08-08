package pd.contact.party;

import pd.codetable.person.ScarsMarksTattoosCode;

/**
 * @author dgibler
 */
public class PartyScarsMarksScarsMarksTattoosCode extends mojo.km.persistence.PersistentObject
{
	private Party parent;
	private String childId;
	private String parentId;
	private ScarsMarksTattoosCode child;
	/**
	* Set the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	* @param aChildId
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
	* Get the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
	* @return java.lang.String
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
			try
			{
				child =
					(ScarsMarksTattoosCode) new mojo
						.km
						.persistence
						.Reference(childId, ScarsMarksTattoosCode.class)
						.getObject();
			}
			catch (Throwable t)
			{
				child = null;
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.person.ScarsMarksTattoosCode
	* @return ScarsMarksTattoosCode
	*/
	public ScarsMarksTattoosCode getChild()
	{
		fetch();
		initChild();
		return child;
	}
	/**
	* set the type reference for class member child
	* @param aChild
	*/
	public void setChild(ScarsMarksTattoosCode aChild)
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
		this.child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(aChild).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.party.Party
	* @param aParentId
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
	* Get the reference value to class :: pd.contact.party.Party
	* @return java.lang.String
	*/
	public String getParentId()
	{
		fetch();
		return parentId;
	}
	/**
	* Initialize class relationship to class pd.contact.party.Party
	*/
	private void initParent()
	{
		if (parent == null)
		{
			try
			{
				parent = (Party) new mojo.km.persistence.Reference(parentId, Party.class).getObject();
			}
			catch (Throwable t)
			{
				parent = null;
			}
		}
	}
	/**
	* Gets referenced type pd.contact.party.Party
	* @return Party
	*/
	public Party getParent()
	{
		fetch();
		initParent();
		return parent;
	}
	/**
	* set the type reference for class member parent
	* @param aParent
	*/
	public void setParent(Party aParent)
	{
		if (this.parent == null || !this.parent.equals(aParent))
		{
			markModified();
		}
		if (aParent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aParent);
		}
		setParentId("" + aParent.getOID());
		this.parent = (Party) new mojo.km.persistence.Reference(aParent).getObject();
	}
}

