/*
 * Class Roles.java
 * Created on May 22, 2002, 3:02:52 PM
 */

package mojo.km.security.role;

import java.util.Enumeration;
import java.util.Vector;
import mojo.km.security.NamingException;

/** Maintains a collection of Roles the given user has membership with. 
 * @modelguid {81143074-6769-460B-A352-966970E94246}
 */
public class Roles implements IRoles {
	/** @modelguid {DA2A8980-A502-4F3A-B954-3077C14022DE} */
    private Vector roles = new Vector();
	/** @modelguid {1CB0C609-0F42-4318-BE78-5A049BABA19C} */
    private boolean readOnly = false;
	/** @modelguid {247BCA80-64A1-4B52-B060-CE4BDA09E0B6} */
    private int size = 0;

    /**
     * Appends the specified Role to the end of this Vector.
     * @param object - is the name of a new Role
     * @modelguid {2BCEF5CB-D61F-4624-A122-19FF567A5103}
     */
    public void add(String object) throws NamingException {
        if (this.readOnly) {
            throw new NamingException("Unable to modifiy, has been set to read only.");
        }

        this.roles.add(object);
        size += 1;
    }

    /** Prevent new Roles from being added. 
     * @modelguid {30E18C71-410F-46A8-9D7F-2CEA224EE03D}
     */
    public void setReadOnly() {
        this.readOnly = true;
    }

    /** Returns the number of Roles in this collection. 
     * @modelguid {58AC6BD8-FE82-445A-BA8D-B46849F921EA}
     */
    public int size() {
        return this.size;
    }

    /** Returns an enumeration of the Roles in this collection. 
     * @modelguid {6F32ACCA-CA48-4FBC-B90C-587ADED8B53E}
     */
    public Enumeration getEnumeration() {
        return this.roles.elements();
    }

    /**
     * Copies the components of this vector into the specified array.
     * @param anArray - an array in which the elements of the vector are copied to
     * @modelguid {5A0A95AC-1F94-41D9-8EB3-90A72E834D0E}
     */
    public void copyInto(Object[] anArray) {
        this.roles.copyInto(anArray);
    }
}
