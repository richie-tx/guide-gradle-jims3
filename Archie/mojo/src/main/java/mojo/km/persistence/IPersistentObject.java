package mojo.km.persistence;

import java.sql.Timestamp;

/**
 * Defines the behavior for a persistent object.
 * 
 * <pre>
 * 
 * 	Change History:
 * 	
 * 	Author          Date        Explanation
 * 	Shannon Ewing  11/6/1999    Added documentation
 *   |
 *   Matt Pomroy    12/28/99     Added documentation
 *   |
 *   Matt Pomroy    3/13/00      Added initialize(IMessageHolder)
 *   Matt Pomroy    9/26/00      toMessage now throws mojo.messaging.exception.MessagingException
 *   Egan Royal     08/27/2001   IOIDable
 * 	
 * </pre>
 * 
 * @author Matt Pomroy 11/1/1999
 * @modelguid {5421A907-7E5E-4EFC-B123-886172EB6F26}
 */
public interface IPersistentObject extends mojo.km.utilities.IOIDable
{
    /**
     * Return the expiration date of this object. If the return value is not null, then this object has expired.
     * 
     * <pre>
     * 
     * 	Note: This method is utilized by the framework and is not to be directly
     * 	      invoked.
     * 	
     * </pre>
     * 
     * @return Timestamp The value of the expiration date.
     * @modelguid {3D0C9383-8D6C-4013-9FF2-B84D5B1FB512}
     */
    public Timestamp getEXP();

    /**
     * Returns the Object ID of this object.
     * 
     * @return Object The value of the object ID.
     * @modelguid {22B8D02A-C5CA-4164-BA43-98D7D69E17E8}
     */
    public String getOID();

    /**
     * Returns the session ID of this object.
     * 
     * @return String The value of the last user who updated this object.
     * @modelguid {C0D76A95-DB40-4A60-A5B9-153FBCFBA977}
     */
    public String getSID();

    /**
     * Returns the session timestamp of this object. My also be refered to as an effective date.
     * 
     * @return Timestamp Time this object became effective
     * @modelguid {E50A7548-E94C-4795-8F74-BBEB9EDC99EA}
     */
    public Timestamp getSTP();

    /**
     * Set the expiration date of this object.
     * 
     * <pre>
     * 
     * 	Note: This method is utilized by the framework to provide versioning.  Pd classes
     * 	      may invoke this method to mimic object deletion.
     * 	
     * </pre>
     * 
     * @param aTimestamp
     *            The expirtation date of this object.
     * @modelguid {AB41C59C-FE65-46C3-9F93-299BE3B48C10}
     */
    public void setEXP(Timestamp aTimestamp);

    /**
     * Sets the value of the object ID.
     * 
     * @param anOID
     *            The value of the object ID.
     * @modelguid {C43B61AC-1019-4D78-864A-9E43CC7CD81D}
     */
    public void setOID(String anOID);

    /**
     * Sets the value of the session or user ID that last modified this object.
     * 
     * @param anSID
     *            The value of the session or user ID.
     * @modelguid {448DD462-C895-4F1C-96E2-A30177956460}
     */
    public void setSID(String anSID);

    /**
     * Sets the value of the session timestamp.
     * 
     * @param aTimestamp
     *            Identifies the time this object became effective.
     * @modelguid {EC86D8C7-CFDB-4D60-92D0-09A5C6955BD8}
     */
    public void setSTP(Timestamp aTimestamp);

    /**
     * @return java.lang.String
     * @modelguid {587571BB-6773-40BE-9112-D2B87454A21D}
     */
    public String getWorkflowID();

    /**
     * @param workflowID -
     *            workflowID
     * @modelguid {73F6E9D9-8F31-464B-A528-02E4D2A65254}
     */
    public void setWorkflowID(String workflowID);

    /**
     * Fetch object from persistent store.
     * 
     * @modelguid {FF5043C3-7EED-4403-A07F-F87B0E1BEED5}
     */
    public void fetch();

    /**
     * Mark persistent object as modified so persistent store knows to save object.
     * 
     * @modelguid {CF94E205-51CE-4B74-87F3-76B7F96C8E9D}
     */
    public void markModified();

    /**
     * Marks dirty
     * 
     * @param isModified -
     *            true/false flag
     * @modelguid {A36B6B60-6CD3-413D-964D-9B541F42CDB3}
     */
    public void setModified(boolean isModified);

    /**
     * @return boolean
     * @modelguid {E20917BD-E24E-4126-961F-DFDD768BAFD4}
     */
    public boolean isDirty();

    /**
     * @param dirty -
     *            dirty
     * @modelguid {85A35855-5AA6-4A31-9BDE-D72162F1F86B}
     */
    public void setDirty(boolean dirty);

    /**
     * unlocks the persistent object after updating.
     */
    public void lock();

    /**
     * locks the persistent object for updating.
     */
    public void unlock();

    /**
     * @return String the lock user (session)
     */
    public String getLockedByUser();
}
