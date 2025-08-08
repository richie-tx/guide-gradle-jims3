package pd.juvenile;

import mojo.km.persistence.PersistentObject;
import pd.codetable.person.ScarsMarksTattoosCode;

public class JuvenileScarsAndMarksScarsMarksTattoosCode extends PersistentObject
{
	ScarsMarksTattoosCode child;

	private String parentId;

	private String childId;
	
	private String entryDate;

	/**
	 * Set the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
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
	 * Get the reference value to class :: pd.codetable.person.ScarsMarksTattoosCode
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
				child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(childId,
						ScarsMarksTattoosCode.class).getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.person.ScarsMarksTattoosCode
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
	public void setChild(ScarsMarksTattoosCode child)
	{
		if (this.child == null || !this.child.equals(child))
		{
			markModified();
		}
		if (child.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (ScarsMarksTattoosCode) new mojo.km.persistence.Reference(child).getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.Juvenile
	 */
	public void setParentId(String parentId)
	{
		if (this.parentId == null || !this.parentId.equals(parentId))
		{
			markModified();
		}
		this.parentId = parentId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.Juvenile
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
}
