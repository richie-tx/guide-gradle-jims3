package mojo.km.persistence;

import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.messaging.IEvent;
import java.util.Iterator;
import mojo.km.transaction.multidatasource.*;
import mojo.km.config.MojoProperties;
/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * Responsible for handling one to one reference to
 * another persistent object
 * 
 * @author Eric A Amundson
 */
public class Reference implements IReference
{
	private PersistentObject internal;
	private Class type;
	private String OID;
	private PersistentObject parent;
	private String theMemberName;
	private IReference deligate = null;
	private IEvent theQueryEvent = null;
	private String contextKey;

	public Reference(String theOID, Class theType)
	{
		this.OID = theOID;
		this.type = theType;
	}

	public Reference(String theOID, Class theType, String aContextKey)
	{
		this.OID = theOID;
		this.type = theType;
		contextKey = aContextKey;
	}
	
	public Reference(IEvent queryEvent, Class theType)
	{
		//this.OID = theOID;
		theQueryEvent = queryEvent;
		this.type = theType;
	}
	
	public Reference(IEvent queryEvent, Class theType, String aContextKey)
	{
		//this.OID = theOID;
		theQueryEvent = queryEvent;
		this.type = theType;
		contextKey = aContextKey;
	}
	
	public Reference(String theOID, Class theType, PersistentObject theParent, String memberName)
	{
		this.OID = theOID;
		this.type = theType;
		parent = theParent;
		theMemberName = memberName;	
	}
	
	public Reference(IEvent queryEvent, Class theType, PersistentObject theParent, String memberName)
	{
		theQueryEvent = queryEvent;
		this.type = theType;
		parent = theParent;
		theMemberName = memberName;	
	}
	
	public Reference(PersistentObject newInternal)
	{
		this.internal = newInternal;
		this.OID = "" + newInternal.getOID();
		this.type = newInternal.getClass();
		this.contextKey = newInternal.getContextKey();
	}

	/** 
	 * Get the persistent object being managed by this reference class.
	 **/
	public PersistentObject getObject()
	{
		initObject();
		return internal;
	}

	/**
	 * Get persistent object's ID.
	 * @return OID
	 */
	public String getOID()
	{
		initObject();
		if (internal != null)
		{
			return "" + internal.getOID();
		}

		return "";
	}

	/**
	 * Initializes the reference to the foreign object
	 * 
	 * TODO:  a little polymorphism/inheritence would greatly improve the design of this method and class. This is 
	 * to cohesive.  Being lazy.
	 */
	private void initObject()
	{
		if (internal == null )
		{
			if (contextKey == null) {
				contextKey = type.getName();
				if (parent != null) {
					contextKey = parent.getClass().getName() + MojoProperties.MEMBERSEPARATOR + theMemberName + MojoProperties.CONTEXTSEPARATOR + contextKey;
				}
			}
			if (OID != null && !OID.equals("")) {
				try
				{
					OIDEvent oidEvent = new OIDEvent();
					oidEvent.setOID(OID);
					Iterator i = TransactionManager.getInstance().retrieve(type, oidEvent, contextKey);
					if (i.hasNext()) {
						internal = (PersistentObject) i.next();
					}
				}
				catch (Throwable t)
				{
					ExceptionHandler.executeCallbacks(t);
				}
			} else if (theQueryEvent != null) {
					Iterator i = TransactionManager.getInstance().retrieve(type, theQueryEvent, contextKey);
					if (i.hasNext()) {
						internal = (PersistentObject) i.next();
					}
			}
			if (internal != null && parent != null) {
				internal.setContext(parent, theMemberName);
			}

		}
	}

}
