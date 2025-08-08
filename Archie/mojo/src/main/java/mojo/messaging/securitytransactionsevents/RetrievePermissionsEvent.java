package mojo.messaging.securitytransactionsevents;

import mojo.km.messaging.ResponseEvent;
import java.util.Vector;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Responsible for housing data that will be returned to boundry command RetrievePermissionsCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {F930FE8F-14CE-4490-BA9B-BB7FA8F19A00}
 */
public class RetrievePermissionsEvent extends ResponseEvent {
	/** @modelguid {A48565D5-521C-44B9-A551-266BF4BCA92D} */
    private String distinguishedName = "";
	/** @modelguid {FCA78951-FB43-45DA-BF7C-5220FDC1A66F} */
    private Collection permissions = new Vector();
	/** @modelguid {918ABAB5-198E-4EEC-BD34-3FE08E0D8933} */
    private String message = "ok";
	 
	/** @modelguid {ACFEF9D1-6636-4D6B-8491-098A7E968E47} */
    public RetrievePermissionsEvent() { }

	/** @modelguid {AE7BC5F6-A21E-4E6E-B56C-0AA160A64528} */
    public void setDistinguishedName(String distinguishedName)
	{
		this.distinguishedName = distinguishedName;
	}

	/** @modelguid {07C35245-3215-48C8-9B97-2AECDB102860} */
	public String getDistinguishedName()
	{
		return this.distinguishedName;
	}
	
	/** @modelguid {BA41ED31-3645-4AE4-AC33-B2AFC1E8E598} */
    public Iterator getPermissions() {
        return permissions.iterator();
    }

	/** @modelguid {23A0BBDB-7F3E-4AAE-91BB-E9220A702260} */
    public void setPermissions(Collection permissions) {
        this.permissions = permissions;
    }

	/** @modelguid {435D9CBD-D098-44E7-A178-4E1192133578} */
    public void insertPermission(RetrievePermissionEvent retrievePermissionEvent)
	{
		this.permissions.add(retrievePermissionEvent);
	}

	/** @modelguid {98BBD9EE-DA37-4A33-88FF-D78494D427CC} */
	public String getMessage()
	{
		return this.message;
	}

	/** @modelguid {4BAF6CD9-001A-4E12-96F9-6F0A13A665F1} */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/** @modelguid {FF944983-13B1-44AB-9A6B-9B6CDC1150DE} */
	public void sort()
	{
		Collections.sort((Vector)permissions);
	}
}
