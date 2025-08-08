package pd.supervision.manageworkgroup;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import pd.contact.user.UserProfile;

public class WorkGroupUserProfilesUserProfile extends mojo.km.persistence.PersistentObject
{
	private UserProfile child;
	private WorkGroup parent;
	private String childId;
	private String parentId;

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
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
	 * Get the reference value to class :: pd.contact.user.UserProfile
	 */
	public String getChildId()
	{
		fetch();
		return childId;
	}

	/**
	 * Initialize class relationship to class pd.contact.user.UserProfile
	 */
	private void initChild()
	{
		if (child == null)
		{
			child = UserProfile.find(childId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(childId,
					//pd.contact.user.UserProfile.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.contact.user.UserProfile
	 */
	public UserProfile getChild()
	{
		fetch();
		initChild();
		return child;
	}

	/**
	 * set the type reference for class member child
	 */
	public void setChild(UserProfile child)
	{
		/*if (this.child == null || !this.child.equals(child))
		{
			markModified();
		}
		if (child.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(child);
		}*/
		setChildId("" + child.getUserID());
		this.child = child;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(child).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.manageworkgroup.WorkGroup
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
	 * Get the reference value to class :: pd.supervision.manageworkgroup.WorkGroup
	 */
	public String getParentId()
	{
		fetch();
		initParent();
		return parentId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.manageworkgroup.WorkGroup
	 */
	private void initParent()
	{
		if (parent == null)
		{
			parent = (WorkGroup) new mojo.km.persistence.Reference(parentId,
					WorkGroup.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.manageworkgroup.WorkGroup
	 */
	public WorkGroup getParent()
	{
		fetch();
		return parent;
	}

	/**
	 * set the type reference for class member parent
	 */
	public void setParent(WorkGroup parent)
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
		this.parent = (WorkGroup) new mojo.km.persistence.Reference(parent).getObject();
	}
	
	/**
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static Iterator findAll(String attrName, String attrValue){
	  	IHome home = new Home();
	  	return home.findAll(attrName, attrValue, WorkGroupUserProfilesUserProfile.class);
	}
}
