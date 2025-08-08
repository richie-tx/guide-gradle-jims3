/*
 * Class AuthenticationException.java
 * Created on May 21, 2002, 8:16:38 AM
 */

package mojo.km.security.role;

/** @modelguid {D9E03747-2617-4580-BC07-6A2D50AB5480} */
public class AuthenticationException extends Exception {
	/** @modelguid {B1BA00E0-3F50-4901-8A31-8A964F0D3949} */
    public AuthenticationException() { }

	/** @modelguid {B5E152D0-5F93-46C1-9A44-344DD154D0C7} */
    public AuthenticationException(String message) {
        super(message);
    }
}
