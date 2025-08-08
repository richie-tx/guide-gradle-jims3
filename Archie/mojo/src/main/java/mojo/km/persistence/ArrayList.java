package mojo.km.persistence;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import mojo.km.naming.PersistenceConstants;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.Reflection;
import mojo.km.config.MojoProperties;
import mojo.km.caching.generic.CacheManager;
/**
 *
 * Responsible for handling reference to a collection of persistent objects.
 */
public class ArrayList implements java.io.Serializable, Collection
{
	public ArrayList(Class type, String fieldName, String pOID)
	{
		this.collectionType = type;
		this.memberFieldName = fieldName;
		this.parentOID = pOID;
		this.theMemberName = "";
		this.wasSet = false;
	}

	public ArrayList(Class type, String fieldName, String pOID, String aContextKey)
	{
		this(type, fieldName, pOID);
		this.contextKey = aContextKey;
		this.delegate = new java.util.ArrayList();
	}

	public ArrayList(Class type, String fieldName, PersistentObject parent, String memberName)
	{
		this(type, fieldName, parent.getOID());
		this.theParent = parent;
		this.theMemberName = memberName;
	}

	private Class collectionType;

	transient private String contextKey;

	transient private java.util.ArrayList delegate;

	private String memberFieldName;

	private String parentOID;

	private String theMemberName;

	transient private PersistentObject theParent;

	transient private boolean wasSet;

	/**
	 * @see Collection#add(Object)
	 */
	public boolean add(Object arg0)
	{
		retrieve();
		if (arg0 instanceof PersistentObject)
		{
			PersistentObject aPObj = (PersistentObject) arg0;
			Reflection.invokeMutatorMethod(aPObj.getClass(), aPObj, memberFieldName, parentOID);
			if (theParent != null)
			{
				aPObj.setContext(theParent, theMemberName);
			}
			else if (contextKey != null && !contextKey.equals(""))
			{
				aPObj.setContext(contextKey);
			}
			AttributeEvent anEvent = new AttributeEvent();
			anEvent.setAttributeName(theMemberName);
			anEvent.setAttributeValue(parentOID);
			if (TransactionManager.getInstance().hasCachingRegistration(aPObj, anEvent))
			{
				CacheManager.addEntity(aPObj, anEvent, aPObj.getContextKey());
			}
			Iterator internal = delegate.iterator();
			while (internal.hasNext())
			{
				PersistentObject pObj = (PersistentObject) internal.next();
				if (pObj != null && pObj.getOID() != null && pObj.getOID().equals(aPObj.getOID()))
				{
					delegate.remove(pObj);
					break;
				}
			}
		}
		return delegate.add(arg0);
	}

	/**
	 * @see Collection#addAll(Collection)
	 * @modelguid {FCA953A0-2125-45BB-9016-75B80C6E7115}
	 */
	public boolean addAll(Collection arg0)
	{
		retrieve();
		AttributeEvent anEvent = new AttributeEvent();
		anEvent.setAttributeName(theMemberName);
		anEvent.setAttributeValue(parentOID);
		for (Iterator i = arg0.iterator(); i.hasNext();)
		{
			Object obj = i.next();
			if (obj instanceof PersistentObject)
			{
				PersistentObject pObj = (PersistentObject) obj;
				Reflection.invokeMutatorMethod(pObj.getClass(), pObj, memberFieldName, parentOID);
				if (theParent != null)
				{
					pObj.setContext(theParent, theMemberName);
				}
				else if (contextKey != null && !contextKey.equals(""))
				{
					pObj.setContext(contextKey);
				}
				if (TransactionManager.getInstance().hasCachingRegistration(pObj, anEvent))
				{
					CacheManager.addEntity(pObj, anEvent, pObj.getContextKey());
				}
			}
		}
		return delegate.addAll(arg0);
	}

	/**
	 * @see Collection#clear()
	 * @modelguid {3238B0D1-96FA-48A8-9DBE-A6222A78D627}
	 */
	public void clear()
	{
		retrieve();

		Iterator internal = delegate.iterator();
		while (internal.hasNext())
		{
			PersistentObject pObj = (PersistentObject) internal.next();
			if (pObj != null && pObj.getOID() != null)
			{
				if (memberFieldName.equals(PersistenceConstants.PARENT_ID_FIELD))
				{
					pObj.setDeleted();
					IHome home = new Home();
					home.bind(pObj);
				}

				if (theParent != null)
				{
					pObj.setContext(theParent, theMemberName);
				}
				else if (contextKey != null && contextKey.equals("") == false)
				{
					pObj.setContext(contextKey);
				}

				//delegate.remove(pObj);				
			}
		}
	}

	/**
	 * @see Collection#contains(Object)
	 * @modelguid {B066538A-19BA-4DEF-B370-BE6073B014F5}
	 */
	public boolean contains(Object arg0)
	{
		retrieve();
		return delegate.contains(arg0);
	}

	/**
	 * @see Collection#containsAll(Collection)
	 * @modelguid {9C18B22B-644C-4654-BAC4-43A1718E366D}
	 */
	public boolean containsAll(Collection arg0)
	{
		retrieve();
		return delegate.containsAll(arg0);
	}

	/**
	 * @see Collection#isEmpty()
	 * @modelguid {2C131261-5A31-4593-8608-4E3869C4B08B}
	 */
	public boolean isEmpty()
	{
		retrieve();
		return delegate.isEmpty();
	}

	/**
	 * @see Collection#iterator()
	 * @modelguid {C4FFD101-ED74-438D-BDFA-D27991C37A59}
	 */
	public Iterator iterator()
	{
		retrieve();
		return delegate.iterator();
	}

	/**
	 * @see Collection#remove(Object)
	 * @modelguid {8E8BC51D-7C45-4F90-A002-62C0425D1CCE}
	 */
	public boolean remove(Object arg0)
	{
		boolean done = false;
		boolean success = false;

		retrieve();
		PersistentObject aPObj = (PersistentObject) arg0;
		if (aPObj != null && aPObj.getOID() != null)
		{
			Iterator internal = delegate.iterator();
			while (internal.hasNext() && done == false)
			{
				PersistentObject pObj = (PersistentObject) internal.next();
				if (pObj != null && pObj.getOID() != null && pObj.getOID().equals(aPObj.getOID()))
				{
					if (memberFieldName.equals(PersistenceConstants.PARENT_ID_FIELD))
					{
						pObj.setDeleted();
					}
					else
					{
						aPObj.delete();
					}

					if (theParent != null)
					{
						pObj.setContext(theParent, theMemberName);
					}
					else if (contextKey != null && !contextKey.equals(""))
					{
						pObj.setContext(contextKey);
					}

					success = delegate.remove(pObj);
					done = true;
				}
			}
		}
		return success;
	}

	/**
	 * @see Collection#removeAll(Collection)
	 * @modelguid {5F5C6711-2856-41E1-A10E-70FFE8A57408}
	 */
	public boolean removeAll(Collection arg0)
	{
		retrieve();
		for (Iterator i = arg0.iterator(); i.hasNext();)
		{
			PersistentObject pObj = (PersistentObject) i.next();
			if (delegate.contains(pObj))
			{
				pObj.delete();
				if (theParent != null)
				{
					pObj.setContext(theParent, theMemberName);
				}
				else if (contextKey != null && !contextKey.equals(""))
				{
					pObj.setContext(contextKey);
				}
			}
		}
		return delegate.removeAll(arg0);
	}

	/**
	 * @see Collection#retainAll(Collection)
	 * @modelguid {76616D40-24BE-49F5-8E43-D30DD6E15189}
	 */
	public boolean retainAll(Collection arg0)
	{
		retrieve();
		return delegate.retainAll(arg0);
	}

	/** @modelguid {931438DD-185D-4BED-A40E-304B404EC7B8} 
	 *
	 *  
	 * TODO:  a little polymorphism/inheritence would greatly improve the design of this method and class. This is not 
	 * too cohesive.  Being lazy.
	 **/
	private void retrieve()
	{
		if (delegate == null)
		{
			delegate = new java.util.ArrayList();
		}
		if (delegate.isEmpty() && !wasSet && parentOID != null && !parentOID.equals(""))
		{
			AttributeEvent aEvent = new AttributeEvent();
			aEvent.setAttributeName(memberFieldName);
			aEvent.setAttributeValue(parentOID);
			List results = null;
			if (this.theParent != null || contextKey != null)
			{
				if (contextKey == null)
				{
					contextKey =
						theParent.getContextKey()
							+ MojoProperties.MEMBERSEPARATOR
							+ theMemberName
							+ MojoProperties.CONTEXTSEPARATOR
							+ collectionType.getName();
				}
				results = TransactionManager.getInstance().retrieveList(collectionType, aEvent, contextKey);
			}
			else
			{
				results = TransactionManager.getInstance().retrieveList(collectionType, aEvent);
			}

			int len = results.size();
			for(int i=0;i<len;i++)
			{
				PersistentObject pObj = (PersistentObject) results.get(i);
				if (this.theParent != null)
				{
					pObj.setContext(theParent, theMemberName);
				}
				delegate.add(pObj);
			}
			wasSet = true;
		}
	}

	/**
	 * @see Collection#size()
	 * @modelguid {6F955403-7017-4310-AED7-19C2EA602D27}
	 */
	public int size()
	{
		retrieve();
		return delegate.size();
	}

	/**
	 * @see Collection#toArray()
	 * @modelguid {D68883D2-49F0-4DC6-8AB6-829FD275E680}
	 */
	public Object[] toArray()
	{
		retrieve();
		return delegate.toArray();
	}

	/**
	 * @see Collection#toArray(Object[])
	 */
	public Object[] toArray(Object[] arg0)
	{
		retrieve();
		return delegate.toArray(arg0);
	}

}
