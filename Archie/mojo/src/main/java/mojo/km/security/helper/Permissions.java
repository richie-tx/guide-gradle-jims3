/*
 * Class Permissions.java
 * Created on May 13, 2002, 11:07:48 AM
 */

package mojo.km.security.helper;

import java.util.Hashtable;
import mojo.km.security.NamingException;
import java.util.Enumeration;

/**
 * Maintains a collection of application Permission for the given user.
 * This collection is used by the application to determine permission
 * access at runtime.
 * @author Chad Oliver
 * @modelguid {0382C801-E063-437C-A31F-74A749B4C72A}
 */
public class Permissions implements IPermissions {
    /**
     * Contains a collection of permission for the given user.  The 'key'
     * is the permission name and the 'value' is the permission level.
     * @modelguid {1D84C32A-5682-4325-8ADA-6608247F165F}
     */
    private Hashtable role = new Hashtable();
	/** @modelguid {A9216C21-1463-4F27-8903-C337D1CA67B3} */
    private boolean readOnly = false;

    /**
     * Add a permission for the given user.  If the permission being added already exist then the permission with the
     * higher access level is saved.
     * @param key - the permission name
     * @param value - the permission level
     * @modelguid {F2277CB4-87A0-41B6-8065-F6AA12464E6B}
     */
    public void put(Object key, Permission value) throws NamingException {
        if (this.readOnly) {
            throw new NamingException("Unable to modifiy, has been set to read only.");
        }

        Permission currentPermissionValue = null;
        currentPermissionValue = (Permission)role.get(key);

        // Permission does not currently exist in collection.
        // Add permission.
        if (currentPermissionValue == null) {
            role.put(key, value);
        }
        // Permission does exist.  Make sure the PermissionType "value"
        // saved has the most security access.
        // For example, if the current value is "read", and the new
        // value is "read_write" then overwrite the current value with
        // "read_write".
        else if (currentPermissionValue.compareTo(value) > 0) {
            role.put(key, value);
        }
    }

    /**
     * Determines weither the user has the requested permission at the requested access level.
     * @param key - the permssion name
     * @param accessLevel - the permission access level
     * @return boolean
     * @modelguid {C8156546-173F-44DA-B789-CA2F74AF6903}
     */
    public boolean contains(Object key, Permission value) {
        Permission currentPermissionValue = null;

        currentPermissionValue = (Permission)role.get(key);

        // permission does not exist.
        if (currentPermissionValue == null) {
            return false;
        }
        // permission does exist.
        else if (value.compareTo(currentPermissionValue) >= 0) {
            return true;
        }

        // has permission, but at a lower level.
        return false;
    }

    /**
     * Return the number of Permission the given user has.
     * @return int
     * @modelguid {EE4A4C65-1194-42D0-81FC-AFF2FFC04102}
     */
    public int size() {
        return role.size();
    }

    /**
     * Returns the value to which the specified key is mapped in this hashtable.
     * @param key - the name of the permission
     * @return Object - a reference of the requested permission, returns null if not found
     * @modelguid {5FBD8083-EC79-48F3-A618-6FDB249B2AD4}
     */
    public Object get(Object key) {
        return role.get(key);
    }

    /**
     * Prevent the permission collection from being modifed.
     * @modelguid {AACA7D6D-3024-41E5-AE1C-3DD73E428182}
     */
    public void setReadOnly() {
        this.readOnly = true;
    }

	/** @modelguid {80493DA2-448A-4570-8F0E-AD3A9492BF15} */
	public Enumeration elements()	 
	{
		return this.role.elements();
	}
}
