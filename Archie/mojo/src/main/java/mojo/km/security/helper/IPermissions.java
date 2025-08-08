/*
 * Class IPermissions.java
 * Created on May 13, 2002, 11:07:48 AM
 */

package mojo.km.security.helper;

import mojo.km.security.NamingException;
import java.util.Enumeration;
import java.io.Serializable;

/**
 * Maintains a collection of ACLs for the given user.  This collection is
 * used by the application to determine permission access at runtime.
 * @author Chad Oliver
 * @modelguid {29473152-201F-453B-962F-753CA759D989}
 */
public interface IPermissions extends Serializable {
    /**
     * Add a permission for the given user.  If the permission being added
     * already exist then the permission with the higher access level is saved.
     * @param key - the permission name
     * @param value - the permission level
     * @modelguid {9DE06B3D-D6CA-4AA2-9AA4-ADBEF9D028AD}
     */
    public void put(Object key, Permission value) throws NamingException;

    /**
     * Determines weither the user has the requested permission at the
     * requested access level.
     * @param key - the permssion name
     * @param value - the permission 
     * @return boolean
     * @modelguid {1DE1F673-239E-4F29-8EE1-7180D50CA60E}
     */
    public boolean contains(Object key, Permission value);

    /**
     * Returns the value to which the specified key is mapped in this hashtable.
     * @param key - the name of the permission
     * @return Object - a reference of the requested permission, returns null if not found
     * @modelguid {8CE4A5ED-66FE-4002-B182-E2A1145AC7A0}
     */
    public Object get(Object key);

	/** @modelguid {96B7C996-B1F7-4772-8EC2-4856B97324ED} */
    public int size();

	/** @modelguid {CF22B8EF-7CF1-4AFB-9D0A-E88CF0957051} */
	public Enumeration elements();
}
