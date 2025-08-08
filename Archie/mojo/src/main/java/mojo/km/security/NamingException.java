/*
 * Class NamingException.java
 * Created on May 21, 2002, 8:19:07 AM
 */

package mojo.km.security;

/** @modelguid {19FE1585-3D8C-44FB-AD1A-F471D40EF998} */
public class NamingException extends Exception {
	/** @modelguid {CE846218-8E22-4CCE-8B73-5DDFD5CC185B} */
    public NamingException() { }

	/** @modelguid {488B25EB-FC2C-430D-A4E4-12382BB1D48D} */
    public NamingException(String message) {
        super(message);
    }
}
