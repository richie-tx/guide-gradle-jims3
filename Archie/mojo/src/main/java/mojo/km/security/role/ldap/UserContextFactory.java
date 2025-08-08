/*
 * Class UserContextFactory.java
 * Created on May 9, 2002, 2:36:44 PM
 */

package mojo.km.security.role.ldap;

/** @author Chad Oliver 
 * @modelguid {F3C5A916-F830-4043-AAED-6C54EC82D74A}
 */
public class UserContextFactory {
    /** @link dependency */

    /*# UserContext lnkUserContext; 
     * @modelguid {43EBF1E9-3E6F-42E2-BE8E-DC796AD866FA}
     */

    public static void main(String[] argv) {
        IUserContext uc = UserContextFactory.getUserContext("cn=coliver, ou=abc, o=com");

        System.out.println("dn=" + uc.getDn());
        System.out.println("cn=" + uc.getCn());
        System.out.println("context=" + uc.getContext());
        System.out.println("an=" + uc.getApplicationName());
    }

	/** @modelguid {D4091759-3D9F-43F7-98D2-304EAF6389D6} */
    public static IUserContext getUserContext(String distinguishedName) {
        Mediator mediator = new Mediator();

        UserContext userContext = new UserContext(mediator, distinguishedName);

        return userContext;
    }
}
