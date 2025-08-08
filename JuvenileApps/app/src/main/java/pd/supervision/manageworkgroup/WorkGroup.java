package pd.supervision.manageworkgroup;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.user.UserProfile;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import pd.supervision.supervisionoptions.StringSet;
import java.util.Set;
import messaging.manageworkgroup.SaveWorkGroupEvent;
import messaging.manageworkgroup.ValidateWorkGroupEvent;
import mojo.km.dispatch.IDispatch;
import mojo.km.dispatch.EventManager;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.messaging.IEvent;

/**
 * @roseuid 45DB611B00DA
 */
public class WorkGroup extends PersistentObject
{
	private String name;
	private String description;
	private String agencyId;
	/**
	 * Properties for userProfiles
	 * @referencedType pd.contact.user.UserProfile
	 * @associationType simple
	 */
	private Collection userProfiles = null;
	/**
	 * Properties for associatedTasks
	 * @referencedType pd.notification.Task
	 */
	private Collection associatedTasks = null;
	/**
	 * Properties for type
	 * @contextKey WORKGROUP_TYPE
	 */
	public Code type = null;
	private String typeId;

	/**
	 * @roseuid 45DB611B00DA
	 */
	public WorkGroup()
	{
	}

	/**
	 * @return 
	 * @param oid
	 */
	static public WorkGroup find(String oid)
	{
		IHome home = new Home();
		WorkGroup workGroup = (WorkGroup) home.find(oid, WorkGroup.class);
		return workGroup;
	}

	/**
	 * @return 
	 * @param event
	 * @roseuid 438F22CA0277
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, WorkGroup.class);
	}

	/**
	 * @return 
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		return home.findAll(attributeName, attributeValue, WorkGroup.class);
	}

	static public WorkGroup save(SaveWorkGroupEvent saveWorkGroupEvent)
	{
		WorkGroup workGroup = null;
		if (saveWorkGroupEvent.getWorkGroupId() == null || ("").equals(saveWorkGroupEvent.getWorkGroupId()))
		{
			// new workGroup
			workGroup = new WorkGroup();
		}
		else
		{
			// existing condition
			workGroup = WorkGroup.find(saveWorkGroupEvent.getWorkGroupId());
		}
		// check for duplicate name
		if (WorkGroup.isDuplicate(saveWorkGroupEvent.getWorkGroupId(), saveWorkGroupEvent.getName(), saveWorkGroupEvent
				.getAgencyId()))
		{
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(saveWorkGroupEvent.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
		}
		else
		{
			workGroup.setName(saveWorkGroupEvent.getName());
			workGroup.setDescription(saveWorkGroupEvent.getDescription());
			workGroup.setAgencyId(saveWorkGroupEvent.getAgencyId());
			workGroup.setTypeId(saveWorkGroupEvent.getType());
			//-------------users--------------------------------//
			StringSet exUsers = createUserProfileSet(workGroup.getUserProfiles());
			StringSet passedUsers = createPassedUserSet(saveWorkGroupEvent.getUserIds());
			Set newUsers = passedUsers.complement(exUsers);
			Set removedUsers = exUsers.complement(passedUsers);
			//new users
			for (Iterator iter = newUsers.iterator(); iter.hasNext();)
			{
				String userId = (String) iter.next();
				if (!userId.equals(""))
				{
					UserProfile user = UserProfile.find(userId);
					workGroup.insertUserProfiles(user);
				}
			}
			// removed documents
			for (Iterator iter = removedUsers.iterator(); iter.hasNext();)
			{
				String userId = (String) iter.next();
				if (!userId.equals(""))
				{
					UserProfile user = UserProfile.find(userId);
					workGroup.removeUserProfiles(user);
				}
			}
		}
		return workGroup;
	}

	static private StringSet createUserProfileSet(Collection objectIds)
	{
		StringSet set = new StringSet(objectIds, new StringSet.Stringizer() {
			public String toString(Object obj)
			{   String retOID = new String(""); 
				UserProfile userProfile = (UserProfile) obj;
				if (userProfile != null){
					retOID = userProfile.getOID();
				}

				//return userProfile.getOID().toString();
			    return retOID;
			}
		});
		return set;
	}

	static private StringSet createPassedUserSet(String[] userIds)
	{
		Collection coll = new ArrayList();
		for (int i = 0; i < userIds.length;)
		{
			coll.add(userIds[i++]);
		}
		StringSet set = new StringSet(coll, new StringSet.Stringizer() {
			public String toString(Object obj)
			{
				String userId = (String) obj;
				return userId;
			}
		});
		return set;
	}

	static public boolean isDuplicate(String wgId, String name, String agencyId)
	{
		ValidateWorkGroupEvent validateEvent = new ValidateWorkGroupEvent();
		validateEvent.setAgencyId(agencyId);
		validateEvent.setWorkGroupId(wgId);
		validateEvent.setName(name);
		return isDuplicate(validateEvent);
	}

	static public boolean isDuplicate(ValidateWorkGroupEvent validateEvent)
	{
		boolean result = false;
		// OID is of type numeric, hence set some numeric value if it is null
		if (validateEvent.getWorkGroupId() == null || validateEvent.getWorkGroupId().equals(""))
		{
			validateEvent.setWorkGroupId("0");
		}
		// get WorkGroup by name and agencyId		
		Iterator wgIter = WorkGroup.findAll(validateEvent);
		if (wgIter.hasNext())
		{
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @return Returns the agencyId.
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * 
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			markModified();
		}
		this.agencyId = agencyId;
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
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}

	/**
	 * 
	 * @return Returns the name.
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * 
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		if (this.name == null || !this.name.equals(name))
		{
			markModified();
		}
		this.name = name;
	}

	/**
	 * @roseuid 45DB5B2500EF
	 */
	public void find()
	{
		fetch();
	}

	/**
	 * @roseuid 45DB5B2500F0
	 */
	public void findAll()
	{
		fetch();
	}

	/**
	 * Initialize class relationship implementation for pd.notification.Task
	 */
	private void initAssociatedTasks()
	{
		if (associatedTasks == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			associatedTasks = new mojo.km.persistence.ArrayList(pd.notification.Task.class, "workGroupId", ""
					+ getOID());
		}
	}

	/**
	 * returns a collection of pd.notification.Task
	 */
	public Collection getAssociatedTasks()
	{
		fetch();
		initAssociatedTasks();
		return associatedTasks;
	}

	/**
	 * insert a pd.notification.Task into class relationship collection.
	 */
	public void insertAssociatedTasks(pd.notification.Task anObject)
	{
		initAssociatedTasks();
		associatedTasks.add(anObject);
	}

	/**
	 * Removes a pd.notification.Task from class relationship collection.
	 */
	public void removeAssociatedTasks(pd.notification.Task anObject)
	{
		initAssociatedTasks();
		associatedTasks.remove(anObject);
	}

	/**
	 * Clears all pd.notification.Task from class relationship collection.
	 */
	public void clearAssociatedTasks()
	{
		initAssociatedTasks();
		associatedTasks.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.contact.user.UserProfile
	 */
	private void initUserProfiles()
	{
		if (userProfiles == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			userProfiles = new mojo.km.persistence.ArrayList(
					WorkGroupUserProfilesUserProfile.class, "parentId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.contact.user.UserProfile
	 */
	public Collection getUserProfiles()
	{
		initUserProfiles();
		ArrayList retVal = new ArrayList();
		Iterator i = userProfiles.iterator();
		while (i.hasNext())
		{
			WorkGroupUserProfilesUserProfile actual = (WorkGroupUserProfilesUserProfile) i
					.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * insert a pd.contact.user.UserProfile into class relationship collection.
	 */
	public void insertUserProfiles(UserProfile anObject)
	{
		initUserProfiles();
		WorkGroupUserProfilesUserProfile actual = new WorkGroupUserProfilesUserProfile();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		userProfiles.add(actual);
	}

	/**
	 * Removes a pd.contact.user.UserProfile from class relationship collection.
	 */
	public void removeUserProfiles(UserProfile anObject)
	{
		initUserProfiles();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		WorkGroupUserProfilesUserProfile actual = (WorkGroupUserProfilesUserProfile) new mojo.km.persistence.Reference(
				assocEvent, WorkGroupUserProfilesUserProfile.class).getObject();
		userProfiles.remove(actual);
	}

	/**
	 * Clears all pd.contact.user.UserProfile from class relationship collection.
	 */
	public void clearUserProfiles()
	{
		initUserProfiles();
		userProfiles.clear();
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
					"WORKGROUP_TYPE").getObject();
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
		type.setContext("WORKGROUP_TYPE");
		this.type = (Code) new mojo.km.persistence.Reference(type).getObject();
	}
}
