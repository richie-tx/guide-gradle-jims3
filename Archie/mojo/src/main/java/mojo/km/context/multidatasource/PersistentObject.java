package mojo.km.context.multidatasource;

import java.sql.Timestamp;

import mojo.km.context.ContextManager;
import mojo.km.persistence.IPersistentObject;
import mojo.km.transaction.multidatasource.TransactionManager;

public class PersistentObject implements java.io.Serializable, IPersistentObject
{
	public boolean Dirty;

	/**
	 * The timestamp marking the expiration date of this objects. Expriation dates are an
	 * alternative to physical deletes from the persistent store. This attribute need not be used if
	 * business requirements do not prohibit physical deletes.
	 */
	private Timestamp EXP;

	private mojo.km.persistence.PersistentObject externalPObj;

	private boolean isModified = false;

	private String lockedByUser = null;

	public boolean Modified;

	private String OID = null;

	public Object Persistor;

	/**
	 * An identifier, usually a user's ID, of the client who last updated the persistent state of
	 * this object.
	 * 
	 */
	private String SID;

	/**
	 * The timestamp marking the last update to the persistent state of this object.
	 * 
	 */
	private Timestamp STP;

	private String workflowID = null;

	public String WorkflowID;

	public PersistentObject()
	{
	}

	public PersistentObject(mojo.km.persistence.PersistentObject pObj)
	{
		externalPObj = pObj;
	}

	/** @modelguid {C3698DEB-5181-4ADF-8869-A2650294674E} */
	public boolean dirty()
	{
		return true;
	}

	/** @modelguid {3C6D3FF7-4F58-4206-935C-50A1820F319A} */
	public void dirty(boolean value)
	{
	}

	public void fetch()
	{
	}

	/**
	 * Returns the Object ID of this object.
	 * 
	 * @return Timestamp - The value of the object ID.
	 */
	public final Timestamp getEXP()
	{
		return EXP;
	}

	public String getLockedByUser()
	{
		return lockedByUser;
	}

	/**
	 * Get object identifier
	 * 
	 * @return oid
	 * @modelguid {638C478B-5E2E-4BE3-B3FD-14961DA04795}
	 */
	public synchronized String getOID()
	{
		return OID;
	}

	/**
	 * Get a session ID of a user using a instance of this class. This is normally the user ID.
	 * 
	 * @return String - The user session ID.
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
		return STP;
	}

	/**
	 * @return <{String}>
	 */
	public final String getWorkflowID()
	{
		return workflowID;
	}

	/**
	 * @return boolean
	 */
	public boolean isDirty()
	{
		return false;
	}

	public boolean isModified()
	{
		return isModified;

	}

	/**
	 * locks the persistent object while updating.
	 */
	public void lock()
	{
		lockedByUser = ContextManager.getSession().getUserID();
	}

	public void markModified()
	{
		if (!isModified)
		{
			if (TransactionManager.getInstance().addUpdated(externalPObj))
			{
				isModified = true;
			}
		}
	}

	/** @modelguid {D2AB76CF-A29F-4672-B86F-A774260EDC68} */
	public void modified(boolean value)
	{
	}

	public Object persistor()
	{
		return (Object) null;
	}

	/** @modelguid {3C122DE4-9A40-4182-90D9-7F8572AEC982} */
	public void persistor(Object value)
	{
	}

	/**
	 * @param dirty -
	 *            dirty
	 */
	public void setDirty(boolean dirty)
	{
	}

	/**
	 * Set the expiration date.
	 * 
	 * @param anEXP -
	 *            the expiration date of this object
	 */
	public final void setEXP(Timestamp anEXP)
	{
		markModified();
		EXP = anEXP;
	}

	/**
	 * Marks object dirty
	 * 
	 * @param modified -
	 *            true/false flag
	 */
	public void setModified(boolean modified)
	{
		// Adding this per James McNabb - MTP 01/13/05
		if (isModified && modified == false)
		{
			TransactionManager.getInstance().removeUpdated(externalPObj);
		}

		isModified = modified;
	}

	/**
	 * Set the value of the object ID.
	 * 
	 * @param anOID -
	 *            The value of the object ID.
	 */
	public final void setOID(String anOID)
	{
		markModified();
		this.OID = anOID;
	}

	/**
	 * Sets the value of the session timestamp.
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
	 * @param workflowID -
	 *            workflowID
	 */
	public final void setWorkflowID(String workflowID)
	{
		this.workflowID = workflowID;
	}

	/**
	 * unlocks the persistent object after updating.
	 */
	public void unlock()
	{
		lockedByUser = null;
	}

	public String workflowID()
	{
		return (String) null;
	}

	public void workflowID(String value)
	{
	}
}
