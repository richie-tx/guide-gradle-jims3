package mojo.km.persistence;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.context.ContextManager;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.Serializable;
import mojo.km.config.MojoProperties;

/**
 * Marks the object as modified so the Persistor knows to store the object.
 * 
 */
public class PersistentObject implements Serializable, IPersistentObject
{

	/**
	 * 
	 * @author eamundson
	 * 
	 * Responsible for managing references being made to this class. This will enable tracking of
	 * the data mapping context.
	 */
	public class ReferenceTracker
	{

		private String contextKey;

		private String memberName;

		private PersistentObject parent;

		public ReferenceTracker(PersistentObject parent, String memberName)
		{
			this.parent = parent;
			this.memberName = memberName;
		}

		public ReferenceTracker(String aContextKey)
		{
			contextKey = aContextKey;
		}

		public String getContext()
		{
			if (contextKey == null)
			{
				String parentContextName = parent.getContextKey();
				StringBuilder buffer = new StringBuilder();
				buffer.append(parentContextName);
				buffer.append(MojoProperties.MEMBERSEPARATOR);
				buffer.append(memberName);
				buffer.append(MojoProperties.CONTEXTSEPARATOR);
				buffer.append(PersistentObject.this.getClass().getName());
				contextKey = buffer.toString();
			}
			return contextKey;
		}

		public String getMemberName()
		{
			return memberName;
		}

		public PersistentObject getParent()
		{
			return parent;
		}
	}

	private int computedHashCode = -1;

	private String createJIMS2UserID;

	private Timestamp createTimestamp;

	private String createUserID;

	private transient ReferenceTracker currentContext;

	/***********************************************************************************************
	 * Helpers to support member level context for execution.
	 * *************************************************************************
	 */
	private transient HashMap currentParents = new HashMap();

	/**
	 * The timestamp marking the expiration date of this objects. Expriation dates are an
	 * alternative to physical deletes from the persistent store. This attribute need not be used if
	 * business requirements do not prohibit physical deletes.
	 * 
	 */
	private Timestamp EXP;

	private transient boolean isComposedFlag = false;

	private transient boolean isDeletedFlag = false;

	private transient boolean isNewFlag = true;

	/**
	 * @fieldType VARCHAR2
	 * @fieldSize 20
	 * @fieldModifiers PRIMARY KEY
	 * @modelguid {8636C191-6C7E-40D8-8529-551C8F01AC52}
	 */
	private String OID;

	/**
	 * @mapped false
	 */
	private IPersistentObject persistentDeligate;

	/**
	 * Map of rules. The key is a service name and the value is a Vector of Rules.
	 * 
	 * @mapped false
	 * @associates Vector
	 */
	private Map rulesMap;

	/**
	 * An identifier, usually a user's ID, of the client who last updated the persistent state of
	 * this object.
	 */
	private String SID;

	/**
	 * The timestamp marking the last update to the persistent state of this object.
	 */
	private Timestamp STP;

	private String updateJIMS2UserID;

	private Timestamp updateTimestamp;

	private String updateUserID;

	/**
	 * The timestamp marking the last update to the persistent state of this object.
	 */
	private String userID;

	/**
	 * An identifier for the workflow that this persistent object is a part of
	 * 
	 * @mapped false
	 */
	private String workflowID;

	public PersistentObject()
	{
		if (ContextManager.isSet())
		{
			persistentDeligate = ContextManager.currentContext().newPersistentObject(this);
		}
		currentParents = new HashMap();

	}

	public void clearReferences()
	{
		currentParents.clear();
		currentContext = null;
	}

	/**
	 * Clears the list of Rules for the given service.
	 * 
	 * @param service
	 *            The service to which the Rules are mapped.
	 */
	public void clearRules(String service)
	{
		List lRules = (List) rulesMap.get(service);
		if (lRules == null)
		{
			return;
		}
		lRules.clear();
		rulesMap.put(service, lRules);
	}

	public void delete()
	{
		persistentDeligate.setModified(false);
		isDeletedFlag = true;
		if (persistentDeligate != null)
		{
			persistentDeligate.markModified();
		}
	}

	/**
	 * implements equals method to check if persistent object oid and class type match.
	 * 
	 */
	public boolean equals(Object obj)
	{
		if (this.OID == null)
		{
			return super.equals(obj);
		}
		String uniqueID = this.OID + this.getClass().getName();
		if (!(obj instanceof PersistentObject))
		{
			return false;
		}
		String inUniqueID = ((PersistentObject) obj).getOID() + obj.getClass().getName();
		return uniqueID.equals(inUniqueID);
	}

	/**
	 * Fetch object from persistent store.
	 * 
	 */
	public synchronized void fetch()
	{
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.fetch();
	}

	/**
	 * Method to eable lookup of mapping base upon parent context.
	 * 
	 * @return the hash key for mapping context
	 * 
	 */
	public String getContextKey()
	{
		if (currentContext == null)
		{
			return this.getClass().getName();
		}
		return currentContext.getContext();
	}

	/**
	 * @return
	 */

	public String getCreateJIMS2UserID()
	{
		return createJIMS2UserID;
	}

	/**
	 * @return
	 */
	public Timestamp getCreateTimestamp()
	{
		return createTimestamp;
	}

	/**
	 * @return
	 */
	public String getCreateUserID()
	{
		return createUserID;
	}

	/**
	 * Returns the Object ID of this object.
	 * 
	 * @return Timestamp - The value of the object ID.
	 */
	public final Timestamp getEXP()
	{
		fetch();
		return EXP;
	}

	public String getLockedByUser()
	{
		if (persistentDeligate == null)
		{
			return null;
		}
		return persistentDeligate.getLockedByUser();
	}

	/**
	 * Get object identifier
	 * 
	 * @return oid
	 */
	public synchronized String getOID()
	{
		return OID;
	}

	/**
	 * Returns the list of Rules for the given service.
	 * 
	 * @param service
	 *            The service to which the Rules are mapped.
	 * @return Iterator An Iterator over the list of Rules for the given service.
	 */
	public Iterator getRules(String service)
	{
		List lRules = (List) rulesMap.get(service);
		if (lRules == null)
		{
			lRules = new ArrayList();
		}
		return lRules.iterator();
	}

	/**
	 * Get a security resource ID using a instance of this class. This is normally the a root object
	 * OID.
	 * 
	 * @return String - The security resource ID.
	 */
	public final String getSID()
	{
		return SID;
	}

	/**
	 * Get the value of the a user STP. If the current time is greater than an hour then the
	 * database lock will be dropped.
	 * 
	 * @return Timestamp - Value of the session time stamp.
	 */
	public final Timestamp getSTP()
	{
		fetch();
		return STP;
	}

	public String getUID()
	{
		return this.OID;
	}

	/**
	 * @return
	 */

	public String getUpdateJIMS2UserID()
	{
		return updateJIMS2UserID;
	}

	/**
	 * @return
	 */
	public Timestamp getUpdateTimestamp()
	{
		return updateTimestamp;
	}

	/**
	 * @return
	 */
	public String getUpdateUserID()
	{
		return updateUserID;
	}

	/**
	 * Get a security session ID using a instance of this class. This is normally the a root object
	 * OID.
	 * 
	 * @return String - The user session ID.
	 */
	public final String getUserID()
	{
		return userID;
	}

	public final String getWorkflowID()
	{
		fetch();
		return workflowID;
	}

	public int hashCode()
	{
		if (computedHashCode == -1)
		{
			if (this.OID == null)
			{
				computedHashCode = super.hashCode();
			}
			else
			{
				String uniqueID = this.OID + this.getClass().getName();
				computedHashCode = uniqueID.hashCode();
			}
		}
		return computedHashCode;
	}

	/**
	 * Initializes the association detail for this persistent object
	 * 
	 * @param associationList
	 *            reference to the collection detail objects
	 * @param associationClass
	 *            the association detail class type
	 * @param foreignProperty
	 *            the property that relates this persistent object to the association class
	 * @return a Collection of association classes that reference this persistent object's children
	 *         detail
	 */
	public Collection initAssociation(Collection associationList, Class associationClass, String foreignProperty)
	{
		Collection association = null;

		if (this.OID == null)
		{
			association = new ArrayList();
		}
		else
		{
			association = new mojo.km.persistence.ArrayList(associationClass, foreignProperty, OID);

			Iterator i = association.iterator();
			while (i.hasNext())
			{
				Association actual = (Association) i.next();
				associationList.add(actual.getChild());
			}
		}

		return association;
	}

	public boolean isComposed()
	{
		return isComposedFlag;
	}

	public boolean isDeleted()
	{
		return isDeletedFlag;
	}

	/** @modelguid {D0812E93-4F3E-4580-8ECC-92C5D1058454} */
	public boolean isDirty()
	{
		if (persistentDeligate == null)
		{
			return false;
		}
		return persistentDeligate.isDirty();
	}

	public boolean isNew()
	{
		return isNewFlag;
	}

	public Iterator listContext()
	{
		Iterator i = currentParents.values().iterator();
		Collection refListing = new ArrayList();
		while (i.hasNext())
		{
			ReferenceTracker ref = (ReferenceTracker) i.next();
			refListing.add(ref.getContext());
		}
		return refListing.iterator();
	}

	/**
	 * locks the persistent object while updating.
	 */
	public void lock()
	{
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.lock();
	}

	/**
	 * Mark persistent object as modified so persistent store knows to save object.
	 */
	public synchronized void markModified()
	{
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.markModified();
	}

	/**
	 * Add a parent reference track to the persistent object for tracking the context.
	 * 
	 * @param parent
	 * @param memberName
	 */
	public void setContext(PersistentObject parent, String memberName)
	{
		if (currentParents == null)
		{
			currentParents = new HashMap();
		}
		ReferenceTracker ref = new ReferenceTracker(parent, memberName);
		if (!currentParents.containsKey(ref.getContext()))
		{
			currentParents.put(ref.getContext(), ref);
		}
		currentContext = ref;
	}

	/**
	 * Add a parent reference track to the persistent object for tracking the context.
	 * 
	 * @param parent
	 * @param memberName
	 */
	public void setContext(String contextKey)
	{
		if (currentParents == null)
		{
			currentParents = new HashMap();
		}
		ReferenceTracker ref = new ReferenceTracker(contextKey);
		if (!currentParents.containsKey(ref.getContext()))
		{
			currentParents.put(ref.getContext(), ref);
		}
		currentContext = ref;
	}

	/**
	 * @param timestamp
	 */

	public void setCreateJIMS2UserID(String string)
	{
		createJIMS2UserID = string;
	}

	/**
	 * @param timestamp
	 */
	public void setCreateTimestamp(Timestamp timestamp)
	{
		createTimestamp = timestamp;
	}

	/**
	 * @param string
	 */
	public void setCreateUserID(String string)
	{
		createUserID = string;
	}

	public void setDeleted()
	{
		delete();
	}

	/** @modelguid {4827DCBA-1CE3-47DC-B8CB-342157D27A60} */
	public void setDirty(boolean isDirty)
	{
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.setDirty(isDirty);
	}

	/**
	 * Set the expiration date of this object.
	 * 
	 * <pre>
	 * 
	 *  
	 *       
	 *       	Note: This method is utilized by the framework to provide versioning.  Pd classes
	 *       	      may invoke this method to mimic object deletion.
	 *      
	 *     
	 *    
	 *   
	 *  
	 * </pre>
	 * 
	 * @param anEXP
	 *            The expirtation date of this object.
	 */
	public final void setEXP(Timestamp anEXP)
	{
		markModified();
		EXP = anEXP;
	}

	/**
	 * If type is part of another object then let parent object markModified define save of this
	 * child.
	 * 
	 * @param trueFalse
	 */
	public void setIsComposed(boolean trueFalse)
	{
		isComposedFlag = trueFalse;
	}

	/**
	 * Marks dirty
	 * 
	 * @param isModified -
	 *            true/false flag
	 */
	public void setModified(boolean isModified)
	{
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.setModified(isModified);
	}

	public void setNotNew()
	{
		isNewFlag = false;
	}

	/**
	 * Set the value of the object ID.
	 * 
	 * @param anOID -
	 *            The value of the object ID.
	 */
	public final void setOID(String anOID)
	{
		OID = anOID;
		if (persistentDeligate != null)
		{
			persistentDeligate.markModified();
		}
	}

	/**
	 * Sets the value of the security resource identifier.
	 * 
	 * @param value -
	 *            Identifies the time this object became effective.
	 */
	public final void setSID(String value)
	{
		markModified();
		SID = value;
	}

	/**
	 * Sets the value of the session timestamp.
	 * 
	 * @param anSTP -
	 *            Identifies the time this object became effective.
	 */
	public final void setSTP(Timestamp anSTP)
	{
		markModified();
		STP = anSTP;
	}

	/**
	 * This is to be used to ensure the object will not get stored during a command invocation.
	 * 
	 * @modelguid {9D792278-9F14-49AE-A9DF-4D76546776CA}
	 */
	public void setTransient()
	{
		setModified(true);
	}

	/**
	 * set UID property for hibernate mapping.
	 */
	public void setUID(String aUID)
	{
		setOID(aUID);
	}

	/**
	 * @param timestamp
	 */

	public void setUpdateJIMS2UserID(String string)
	{
		updateJIMS2UserID = string;
	}

	/**
	 * @param timestamp
	 */
	public void setUpdateTimestamp(Timestamp timestamp)
	{
		updateTimestamp = timestamp;
	}

	/**
	 * @param string
	 */
	public void setUpdateUserID(String string)
	{
		updateUserID = string;
	}

	/**
	 * Sets the actual user or session ID.
	 * 
	 * @param userID
	 */
	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public final void setWorkflowID(String workflowID)
	{
		markModified();
		this.workflowID = workflowID;
	}

	public void undelete()
	{
		isDeletedFlag = false;
		isNewFlag = true;
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.markModified();
	}

	/**
	 * unlocks the persistent object after updating.
	 */
	public void unlock()
	{
		if (persistentDeligate == null)
		{
			return;
		}
		persistentDeligate.unlock();
	}

}
